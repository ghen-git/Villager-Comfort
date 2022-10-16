package dev.ghen.villagercomfort.common.capabilty;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class ModCapabilities
{
    public static final Capability<IComfortValuesCap> COMFORT_VALUES_CAP = CapabilityManager.get(new CapabilityToken<>() {});
}
