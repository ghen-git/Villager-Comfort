package dev.ghen.villagercomfort;

import com.mojang.logging.LogUtils;
import dev.ghen.villagercomfort.common.capabilty.IComfortValuesCap;
import dev.ghen.villagercomfort.core.config.CommonConfig;
import dev.ghen.villagercomfort.core.world.entity.ai.memory.ModMemoryModuleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(VillagerComfort.ID)
public class VillagerComfort
{
    public static final String ID = "villagercomfort";
    public static final String NAME = "Villager Comfort";
    public static final String VERSION = "1.0.0";

    public static final Logger LOGGER = LogUtils.getLogger();

    public VillagerComfort()
    {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        modBus.addListener(this::commonSetup);
        modBus.addListener(this::clientSetup);
        modBus.addListener(this::registerCapabilities);

        ModMemoryModuleType.MEMORIES.register(modBus);

        CommonConfig.setup();
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // ThirstModPacketHandler.init();
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        // ThirstBarRenderer.register();
    }

    public void registerCapabilities(RegisterCapabilitiesEvent event)
    {
        event.register(IComfortValuesCap.class);
    }

    //this is from Create but it looked very cool
    public static ResourceLocation asResource(String path)
    {
        return new ResourceLocation(ID, path);
    }
}
