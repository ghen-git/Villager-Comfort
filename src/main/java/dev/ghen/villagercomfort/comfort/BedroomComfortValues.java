package dev.ghen.villagercomfort.comfort;

import dev.ghen.villagercomfort.common.capabilty.IComfortValuesCap;
import dev.ghen.villagercomfort.core.config.CommonConfig;
import dev.ghen.villagercomfort.room.RoomHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class BedroomComfortValues
{
    public static void setValuesToCap(Villager villager, BlockPos bedPos, IComfortValuesCap comfortValuesCap)
    {
        Level level = villager.level;
        GlobalPos workstation = villager.getBrain().getMemory(MemoryModuleType.JOB_SITE).orElse(null);

        AtomicInteger bedsCount = new AtomicInteger(0);
        AtomicInteger roomSize = new AtomicInteger(0);
        AtomicInteger maxLightValue = new AtomicInteger(0);
        AtomicBoolean isWorkstationInBedroom = new AtomicBoolean(false);

        RoomHelper.runForEveryBlock(villager.level, bedPos, CommonConfig.MAX_BEDROOM_SIZE.get().intValue(), (pos) ->
        {
            if(workstation != null && pos.equals(workstation.pos()))
            {
                //checks for villager workstation
                isWorkstationInBedroom.set(true);
            }
            else if(level.getBlockState(pos).is(BlockTags.BEDS))
            {
                //counts number of beds
                bedsCount.getAndIncrement();
            }
            else if(level.getBlockState(pos).is(Blocks.AIR))
            {
                //checks for max. light value
                if(level.getBrightness(LightLayer.SKY, pos) > maxLightValue.get())
                    maxLightValue.set(level.getBrightness(LightLayer.SKY, pos));

                //keeps track of room size
                roomSize.getAndIncrement();
            }
        });

        comfortValuesCap.setBedroomSize(roomSize.get());
        comfortValuesCap.setBedsCount(bedsCount.get() / 2);
        comfortValuesCap.setBedroomLight(maxLightValue.get());
        comfortValuesCap.setIsWorkstationInBedroom(isWorkstationInBedroom.get());
    }


}
