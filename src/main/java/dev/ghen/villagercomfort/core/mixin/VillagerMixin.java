package dev.ghen.villagercomfort.core.mixin;

import dev.ghen.villagercomfort.comfort.BedroomComfortValues;
import dev.ghen.villagercomfort.comfort.ComfortHelper;
import dev.ghen.villagercomfort.comfort.WorkplaceComfortValues;
import dev.ghen.villagercomfort.common.capabilty.ModCapabilities;
import dev.ghen.villagercomfort.core.config.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.LightLayer;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Villager.class)
public class VillagerMixin
{
    @Shadow @Final private static Logger LOGGER;

    /**
     * Hook to calculate comfort values relative to a villager's bedroom
     * */
    @Inject(method = "startSleeping", at = @At("HEAD"))
    private void scanBedroomComfortValues(BlockPos pos, CallbackInfo ci)
    {
        Villager villager = ((Villager)(Object)this);

        villager.getCapability(ModCapabilities.COMFORT_VALUES_CAP).ifPresent(cap ->
                {
                    BedroomComfortValues.setValuesToCap(villager, pos, cap);

                    cap.addDaysWithoutZombie(1);

                    if(cap.getOutsideSeconds() < CommonConfig.AVERAGE_TIME_OUTSIDE.get().intValue())
                        cap.addDaysWithoutOutside(1);
                    else
                        cap.setDaysWithoutOutside(0);

                    cap.setOutsideSeconds(0);

                    cap.setHasBed(true);
                    if(cap.hasWorkplace())
                        cap.setHasWorkplace(villager.getBrain().hasMemoryValue(MemoryModuleType.JOB_SITE));
                });
    }

    /**
     * Hook to calculate comfort values relative to a villager's workplace
     * */
    @Inject(method = "playWorkSound", at = @At("HEAD"))
    private void scanWorkstationComfortValues(CallbackInfo ci)
    {
        Villager villager = ((Villager)(Object)this);

        villager.getCapability(ModCapabilities.COMFORT_VALUES_CAP).ifPresent(cap ->
        {
            WorkplaceComfortValues.setValuesToCap(villager, cap);

            if(!cap.hasWorkplace())
                cap.setHasWorkplace(true);
        });
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void checkSunlightTick(CallbackInfo ci)
    {
        Villager villager = ((Villager)(Object)this);

        if(!villager.level.isClientSide() && (villager.tickCount % 20 == 0))
        {
            villager.getCapability(ModCapabilities.COMFORT_VALUES_CAP).ifPresent(cap ->
                    {
                        if(villager.level.getBrightness(LightLayer.SKY, villager.blockPosition()) > 12)
                            cap.addOutsideSeconds(1);

                        if(villager.getBrain().isActive(Activity.PANIC) && cap.getDaysWithoutZombie() > 0)
                            cap.setDaysWithoutZombie(0);
                    });
        }
    }


    /**
     * Applies the comfort modifier to the villager's trades
     * */
    @Inject(method = "updateSpecialPrices", at = @At("TAIL"))
    private void setComfortModifiers(Player player, CallbackInfo ci)
    {
        Villager villager = ((Villager)(Object)this);

        villager.getCapability(ModCapabilities.COMFORT_VALUES_CAP).ifPresent(cap ->
        {
            for(MerchantOffer merchantOffer : villager.getOffers())
                merchantOffer.addToSpecialPriceDiff(ComfortHelper.calculatePriceModifier(villager, cap, merchantOffer.getBaseCostA().getCount()));
        });
    }
}
