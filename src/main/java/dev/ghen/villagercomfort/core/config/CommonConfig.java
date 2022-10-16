package dev.ghen.villagercomfort.core.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class CommonConfig
{
    private static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.ConfigValue<Number> MAX_BEDROOM_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Number> MAX_WORKPLACE_SIZE;

    static
    {
        BUILDER.push("Interiors dimensions").comment("Values relative to the size of the different rooms villagers usually spend time in,",
                "mind that the bigger this values get, the more imprecise evaluations may be (i.e if you have a wide opening to a courtyard",
                "without any blocks to separate it from the inside, the courtyard will be included in the calculations.",
                "Having more blocks to check is also heavier to evaluate performance-wise, so don't go overboard with these values");
        MAX_BEDROOM_SIZE = BUILDER.comment("Max. diameter for a bedroom (beyond which size won't matter to comfort)").define("maxBedroomSize", 10);
        MAX_WORKPLACE_SIZE = BUILDER.comment("Max. diameter for a workplace (beyond which size won't matter to comfort)").define("maxWorkplaceSize", 10);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }

    public static void setup()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SPEC, "villager-fairness.toml");
    }
}
