package dev.ghen.villagercomfort.core.mixin;

import dev.ghen.villagercomfort.comfort.BedroomComfortValues;
import dev.ghen.villagercomfort.comfort.ComfortValuesHelper;
import dev.ghen.villagercomfort.comfort.WorkplaceComfortValues;
import dev.ghen.villagercomfort.common.capabilty.ModCapabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
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
                BedroomComfortValues.setValuesToCap(villager, pos, cap));
    }

    /**
     * Hook to calculate comfort values relative to a villager's workplace
     * */
    @Inject(method = "playWorkSound", at = @At("HEAD"))
    private void scanWorkstationComfortValues(CallbackInfo ci)
    {
        Villager villager = ((Villager)(Object)this);

        villager.getCapability(ModCapabilities.COMFORT_VALUES_CAP).ifPresent(cap ->
                WorkplaceComfortValues.setValuesToCap(villager, cap));
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void checkSunlightTick(CallbackInfo ci)
    {
        Villager villager = ((Villager)(Object)this);

        if(!villager.level.isClientSide() && (villager.tickCount % 20 == 0)
                && villager.level.getBrightness(LightLayer.SKY, villager.blockPosition()) == 15)
        {
            villager.getCapability(ModCapabilities.COMFORT_VALUES_CAP).ifPresent(cap ->
                    cap.addOutsideSeconds(1));
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
                merchantOffer.addToSpecialPriceDiff(ComfortValuesHelper.calculatePriceModifier(villager, cap, merchantOffer.getBaseCostA().getCount()));
        });
    }
}
