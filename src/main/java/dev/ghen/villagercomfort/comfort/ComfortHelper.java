package dev.ghen.villagercomfort.comfort;

import dev.ghen.villagercomfort.VillagerComfort;
import dev.ghen.villagercomfort.common.capabilty.IComfortValuesCap;
import dev.ghen.villagercomfort.common.capabilty.ModCapabilities;
import dev.ghen.villagercomfort.core.config.CommonConfig;
import dev.ghen.villagercomfort.core.math.MathHelper;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.Villager;

public class ComfortHelper
{
    public static int calculatePriceModifier(Villager villager, IComfortValuesCap cap, int basePrice)
    {
        int comfort = getVillagerComfort(villager);
        int finalPriceDiff = 0;

        if(comfort > 0)
        {
            finalPriceDiff = -MathHelper.scale(comfort, 100, basePrice);
        }
        else
        {
            finalPriceDiff = MathHelper.scale(comfort, -100, 64 - basePrice);
        }

        return finalPriceDiff;
    }

    public static int getVillagerComfort(Villager villager)
    {
        IComfortValuesCap cap = villager.getCapability(ModCapabilities.COMFORT_VALUES_CAP).orElse(null);

        if(cap != null)
        {
            int comfort = 0;

            //bedroom related
            if(villager.getBrain().hasMemoryValue(MemoryModuleType.LAST_SLEPT))
            {
                // bedroom size
                if(cap.getBedroomSize() >= CommonConfig.MAX_BEDROOM_SIZE.get().intValue())
                {
                    comfort += CommonConfig.MAX_BEDROOM_SIZE_COMFORT.get().intValue();
                }
                else if(cap.getBedroomSize() > CommonConfig.AVERAGE_BEDROOM_SIZE.get().intValue())
                {
                    comfort += MathHelper.scale(cap.getBedroomSize() - CommonConfig.AVERAGE_BEDROOM_SIZE.get().intValue(),
                            CommonConfig.MAX_BEDROOM_SIZE.get().intValue() - CommonConfig.AVERAGE_BEDROOM_SIZE.get().intValue(),
                            CommonConfig.MAX_BEDROOM_SIZE_COMFORT.get().intValue());
                }
                else if(cap.getBedroomSize() < CommonConfig.AVERAGE_BEDROOM_SIZE.get().intValue())
                {
                    comfort += MathHelper.scale(CommonConfig.AVERAGE_BEDROOM_SIZE.get().intValue() - cap.getBedroomSize(),
                            CommonConfig.AVERAGE_BEDROOM_SIZE.get().intValue(),
                            CommonConfig.MIN_BEDROOM_SIZE_COMFORT.get().intValue());
                }

                //beds
                if(cap.getBedsCount() > CommonConfig.BEDS_THRESHOLD.get().intValue())
                    comfort += (cap.getBedsCount() - CommonConfig.BEDS_THRESHOLD.get().intValue()) * CommonConfig.COMFORT_PER_BED.get().intValue();

                //bedroom light
                if(cap.getBedroomLight() > CommonConfig.BEDROOM_LIGHT_AVERAGE.get().intValue())
                {
                    comfort += (cap.getBedroomLight() - CommonConfig.BEDROOM_LIGHT_AVERAGE.get().intValue()) * CommonConfig.COMFORT_PER_MORE_BEDROOM_LIGHT.get().intValue();
                }
                else if(cap.getBedroomLight() < CommonConfig.BEDROOM_LIGHT_AVERAGE.get().intValue())
                {
                    comfort += (CommonConfig.BEDROOM_LIGHT_AVERAGE.get().intValue() - cap.getBedroomLight()) * CommonConfig.COMFORT_PER_LESS_BEDROOM_LIGHT.get().intValue();
                }

                //workstation in bedroom
                if(cap.getIsWorkstationInBedroom())
                    comfort += CommonConfig.WORKSTATION_IN_BEDROOM_COMFORT.get().intValue();
            }

            //workplace related
            if(villager.getBrain().hasMemoryValue(MemoryModuleType.JOB_SITE))
            {
                // workplace size
                if(cap.getWorkplaceSize() >= CommonConfig.MAX_WORKPLACE_SIZE.get().intValue())
                {
                    comfort += CommonConfig.MAX_WORKPLACE_SIZE_COMFORT.get().intValue();
                }
                else if(cap.getWorkplaceSize() > CommonConfig.AVERAGE_WORKPLACE_SIZE.get().intValue())
                {
                    comfort += MathHelper.scale(cap.getWorkplaceSize() - CommonConfig.AVERAGE_WORKPLACE_SIZE.get().intValue(),
                            CommonConfig.MAX_WORKPLACE_SIZE.get().intValue() - CommonConfig.AVERAGE_WORKPLACE_SIZE.get().intValue(),
                            CommonConfig.MAX_WORKPLACE_SIZE_COMFORT.get().intValue());
                }
                else if(cap.getWorkplaceSize() < CommonConfig.AVERAGE_WORKPLACE_SIZE.get().intValue())
                {
                    comfort += MathHelper.scale(CommonConfig.AVERAGE_WORKPLACE_SIZE.get().intValue() - cap.getWorkplaceSize(),
                            CommonConfig.AVERAGE_WORKPLACE_SIZE.get().intValue(),
                            CommonConfig.MIN_WORKPLACE_SIZE_COMFORT.get().intValue());
                }

                //workstations
                if(cap.getWorkstationsCount() > CommonConfig.WORKSTATIONS_THRESHOLD.get().intValue())
                    comfort += (cap.getWorkstationsCount() - CommonConfig.WORKSTATIONS_THRESHOLD.get().intValue()) * CommonConfig.COMFORT_PER_WORKSTATION.get().intValue();

                //workplace light
                if(cap.getWorkplaceLight() > CommonConfig.WORKPLACE_LIGHT_AVERAGE.get().intValue())
                {
                    comfort += (cap.getWorkplaceLight() - CommonConfig.WORKPLACE_LIGHT_AVERAGE.get().intValue()) * CommonConfig.COMFORT_PER_MORE_WORKPLACE_LIGHT.get().intValue();
                }
                else if(cap.getWorkplaceLight() < CommonConfig.WORKPLACE_LIGHT_AVERAGE.get().intValue())
                {
                    comfort += (CommonConfig.WORKPLACE_LIGHT_AVERAGE.get().intValue() - cap.getWorkplaceLight()) * CommonConfig.COMFORT_PER_LESS_WORKPLACE_LIGHT.get().intValue();
                }
            }

            // distance between a villager's bed and their workplace
            if(villager.getBrain().hasMemoryValue(MemoryModuleType.LAST_SLEPT) &&
                    villager.getBrain().hasMemoryValue(MemoryModuleType.JOB_SITE))
            {
                if(cap.getBedWorkstationDistance() > CommonConfig.MAX_BED_WORKPLACE_DISTANCE.get().intValue())
                {
                    comfort += CommonConfig.MAX_BED_WORKPLACE_DISTANCE.get().intValue() - CommonConfig.AVERAGE_DISTANCE_BED_WORKPLACE.get().intValue() *
                            CommonConfig.COMFORT_PER_MORE_BED_WORKPLACE_DISTANCE.get().intValue();
                }
                else if(cap.getBedWorkstationDistance() > CommonConfig.AVERAGE_DISTANCE_BED_WORKPLACE.get().intValue())
                {
                    comfort += cap.getBedWorkstationDistance() - CommonConfig.AVERAGE_DISTANCE_BED_WORKPLACE.get().intValue() *
                            CommonConfig.COMFORT_PER_MORE_BED_WORKPLACE_DISTANCE.get().intValue();
                }
                else if(cap.getBedWorkstationDistance() < CommonConfig.AVERAGE_DISTANCE_BED_WORKPLACE.get().intValue())
                {
                    comfort += CommonConfig.AVERAGE_DISTANCE_BED_WORKPLACE.get().intValue() - cap.getBedWorkstationDistance() *
                            CommonConfig.COMFORT_PER_LESS_BED_WORKPLACE_DISTANCE.get().intValue();
                }
            }

            // time spent outside
            if(cap.getDaysWithoutOutside() > 0)
                comfort += cap.getDaysWithoutOutside() * CommonConfig.COMFORT_PER_DAY_WITHOUT_OUTSIDE.get().intValue();

            // days without zombie attacks
            if(cap.getDaysWithoutZombie() > CommonConfig.MAX_DAYS_WITHOUT_ZOMBIE.get().intValue())
                comfort += CommonConfig.MAX_DAYS_WITHOUT_ZOMBIE.get().intValue() * CommonConfig.COMFORT_PER_DAY_WITHOUT_ZOMBIE.get().intValue();
            else if(cap.getDaysWithoutZombie() > 0)
                comfort += cap.getDaysWithoutZombie() * CommonConfig.COMFORT_PER_DAY_WITHOUT_ZOMBIE.get().intValue();

            // days without sleeping
            if(villager.getBrain().getMemory(MemoryModuleType.LAST_SLEPT).orElse(0L) > 24000L)
                comfort += (villager.getBrain().getMemory(MemoryModuleType.LAST_SLEPT).orElse(0L) / 24000L) *
                        CommonConfig.COMFORT_PER_DAY_WITHOUT_SLEEP.get().intValue();

            comfort = Mth.clamp(comfort, -100, 100);
            cap.setComfort(comfort);

            return comfort;
        }
        else
            return 0;
    }
}
