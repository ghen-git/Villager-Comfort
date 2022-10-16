package dev.ghen.villagercomfort.core.world.entity.ai.memory;

import com.mojang.serialization.Codec;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;

public class ModMemoryModuleType
{
    public static final DeferredRegister<MemoryModuleType<?>> MEMORIES = DeferredRegister.create(ForgeRegistries.MEMORY_MODULE_TYPES, "thirst");

    public static final RegistryObject<MemoryModuleType<Integer>> BEDROOM_WIDTH = MEMORIES.register("bedroom_width", () -> new MemoryModuleType<>(Optional.of(Codec.INT)));
}
