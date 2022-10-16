package dev.ghen.villagercomfort.common.capabilty;

import dev.ghen.villagercomfort.VillagerComfort;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ComfortValuesManager
{
    @SubscribeEvent
    public static void attachCapabilityToEntityHandler(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof Villager)
        {
            IComfortValuesCap comfortValuesCap = new ComfortValuesCap();
            LazyOptional<IComfortValuesCap> capOptional = LazyOptional.of(() -> comfortValuesCap);
            Capability<IComfortValuesCap> capability = ModCapabilities.COMFORT_VALUES_CAP;

            ICapabilityProvider provider = new ICapabilitySerializable<CompoundTag>()
            {
                @Nonnull
                @Override
                public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction direction)
                {
                    if (cap == capability)
                    {
                        return capOptional.cast();
                    }
                    return LazyOptional.empty();
                }

                @Override
                public CompoundTag serializeNBT()
                {
                    return comfortValuesCap.serializeNBT();
                }

                @Override
                public void deserializeNBT(CompoundTag nbt)
                {
                    comfortValuesCap.deserializeNBT(nbt);
                }
            };

            event.addCapability(VillagerComfort.asResource("comfort_values"), provider);
        }
    }
}
