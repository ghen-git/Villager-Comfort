package dev.ghen.villagercomfort.core.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class CommonConfig
{
    private static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.ConfigValue<Number> COMFORT_INFLUENCE_ON_PRICE;
    public static final ForgeConfigSpec.ConfigValue<Number> MAX_BEDROOM_DIAMETER;
    public static final ForgeConfigSpec.ConfigValue<Number> MAX_WORKPLACE_DIAMETER;

    // bedroom-relative values
    public static final ForgeConfigSpec.ConfigValue<Number> MIN_BEDROOM_SIZE_COMFORT;
    public static final ForgeConfigSpec.ConfigValue<Number> MAX_BEDROOM_SIZE_COMFORT;
    public static final ForgeConfigSpec.ConfigValue<Number> AVERAGE_BEDROOM_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Number> MAX_BEDROOM_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Number> BEDS_THRESHOLD;
    public static final ForgeConfigSpec.ConfigValue<Number> COMFORT_PER_BED;
    public static final ForgeConfigSpec.ConfigValue<Number> BEDROOM_LIGHT_AVERAGE;
    public static final ForgeConfigSpec.ConfigValue<Number> COMFORT_PER_LESS_BEDROOM_LIGHT;
    public static final ForgeConfigSpec.ConfigValue<Number> COMFORT_PER_MORE_BEDROOM_LIGHT;
    public static final ForgeConfigSpec.ConfigValue<Number> WORKSTATION_IN_BEDROOM_COMFORT;

    // workplace-relative values
    public static final ForgeConfigSpec.ConfigValue<Number> MIN_WORKPLACE_SIZE_COMFORT;
    public static final ForgeConfigSpec.ConfigValue<Number> MAX_WORKPLACE_SIZE_COMFORT;
    public static final ForgeConfigSpec.ConfigValue<Number> AVERAGE_WORKPLACE_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Number> MAX_WORKPLACE_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Number> WORKSTATIONS_THRESHOLD;
    public static final ForgeConfigSpec.ConfigValue<Number> COMFORT_PER_WORKSTATION;
    public static final ForgeConfigSpec.ConfigValue<Number> WORKPLACE_LIGHT_AVERAGE;
    public static final ForgeConfigSpec.ConfigValue<Number> COMFORT_PER_LESS_WORKPLACE_LIGHT;
    public static final ForgeConfigSpec.ConfigValue<Number> COMFORT_PER_MORE_WORKPLACE_LIGHT;

    // other comfort values
    public static final ForgeConfigSpec.ConfigValue<Number> AVERAGE_DISTANCE_BED_WORKPLACE;
    public static final ForgeConfigSpec.ConfigValue<Number> MAX_BED_WORKPLACE_DISTANCE;
    public static final ForgeConfigSpec.ConfigValue<Number> COMFORT_PER_MORE_BED_WORKPLACE_DISTANCE;
    public static final ForgeConfigSpec.ConfigValue<Number> COMFORT_PER_LESS_BED_WORKPLACE_DISTANCE;
    public static final ForgeConfigSpec.ConfigValue<Number> AVERAGE_TIME_OUTSIDE;
    public static final ForgeConfigSpec.ConfigValue<Number> COMFORT_PER_DAY_WITHOUT_OUTSIDE;
    public static final ForgeConfigSpec.ConfigValue<Number> COMFORT_PER_DAY_WITHOUT_ZOMBIE;
    public static final ForgeConfigSpec.ConfigValue<Number> MAX_DAYS_WITHOUT_ZOMBIE;
    public static final ForgeConfigSpec.ConfigValue<Number> COMFORT_PER_DAY_WITHOUT_SLEEP;

    static
    {
        BUILDER.push("Prices");
        COMFORT_INFLUENCE_ON_PRICE = BUILDER.comment("Value from 0 to 1, influences how much price changes relative to a villager's comfort",
                "for example, if the value is 0.5, the base price is 8 emeralds, and comfort is at its max, the new price will be 4 emeralds",
                "if the value was 1.0 instead, the price for a villager at max. comfort would be 1 emerald").define("comfortInfluenceOnPrice", 0.8);
        BUILDER.pop();

        BUILDER.push("Interiors dimensions").comment("Values relative to the size of the different rooms villagers usually spend time in,",
                "mind that the bigger this values get, the more imprecise evaluations may be (i.e if you have a wide opening to a courtyard",
                "without any blocks to separate it from the inside, the courtyard will be included in the calculations.",
                "Having more blocks to check is also heavier to evaluate performance-wise, so don't go overboard with these values");
        MAX_BEDROOM_DIAMETER = BUILDER.comment("Max. diameter for a bedroom (beyond which size won't matter to comfort)").define("maxBedroomDiameter", 10);
        MAX_WORKPLACE_DIAMETER = BUILDER.comment("Max. diameter for a workplace (beyond which size won't matter to comfort)").define("maxWorkplaceDiameter", 10);
        BUILDER.pop();

        BUILDER.push("Bedroom comfort values").comment("Various parameters relative to how comfortable is a villager's bedroom");
        AVERAGE_BEDROOM_SIZE = BUILDER.comment("How much empty space (in blocks) should the average bedroom have").define("averageBedroomSize", 9);
        MAX_BEDROOM_SIZE = BUILDER.comment("Amount of empty blocks above which the extra space doesn't add comfort to the bedroom").define("maxBedroomSize", 48);
        MIN_BEDROOM_SIZE_COMFORT = BUILDER.comment("Maximum variation in comfort if the bedroom is smaller than average").define("minBedroomSizeComfort", -12);
        MAX_BEDROOM_SIZE_COMFORT = BUILDER.comment("Maximum variation in comfort if the bedroom is bigger than average").define("maxBedroomSizeComfort", 12);
        BEDS_THRESHOLD = BUILDER.comment("Number of beds in the same room above which comfort starts changing").define("bedsThreshold", 2);
        COMFORT_PER_BED = BUILDER.comment("Variation in comfort relative to each extra bed in a bedroom").define("comfortPerBed", -10);
        BEDROOM_LIGHT_AVERAGE = BUILDER.comment("Max light level (even on a single block) the average bedroom should have").define("bedroomLightAverage", 7);
        COMFORT_PER_LESS_BEDROOM_LIGHT = BUILDER.comment("If the max light level in the room is below average, comfort variation per light level difference").define("comfortPerLessBedroomLight", -5);
        COMFORT_PER_MORE_BEDROOM_LIGHT = BUILDER.comment("If the max light level in the room is above average, comfort variation per light level difference").define("comfortPerMoreBedroomLight", 0);
        WORKSTATION_IN_BEDROOM_COMFORT = BUILDER.comment("Variation in comfort if the villager's workstation is in their bedroom").define("workstationInBedroomComfort", -20);
        BUILDER.pop();


        BUILDER.push("Workplace comfort values").comment("Various parameters relative to how comfortable is a villager's workplace");
        AVERAGE_WORKPLACE_SIZE = BUILDER.comment("How much empty space (in blocks) should the average workplace have").define("averageWorkplaceSize", 9);
        MAX_WORKPLACE_SIZE = BUILDER.comment("Amount of empty blocks above which the extra space doesn't add comfort to the workplace").define("maxWorkplaceSize", 48);
        MIN_WORKPLACE_SIZE_COMFORT = BUILDER.comment("Maximum variation in comfort if the workplace is smaller than average").define("minWorkplaceSizeComfort", -25);
        MAX_WORKPLACE_SIZE_COMFORT = BUILDER.comment("Maximum variation in comfort if the workplace is bigger than average").define("maxWorkplaceSizeComfort", 25);
        WORKSTATIONS_THRESHOLD = BUILDER.comment("Number of workstations in the same room above which a variation in comfort will occur").define("workstationsThreshold", 3);
        COMFORT_PER_WORKSTATION = BUILDER.comment("Variation in comfort relative to each extra workstation in the room").define("comfortPerWorkstation", -5);
        WORKPLACE_LIGHT_AVERAGE = BUILDER.comment("Max light level (even on a single block) the average workplace should have").define("workplaceLightAverage", 7);
        COMFORT_PER_LESS_WORKPLACE_LIGHT = BUILDER.comment("If the max light level in the room is below average, comfort variation per light level difference").define("comfortPerLessWorkplaceLight", -5);
        COMFORT_PER_MORE_WORKPLACE_LIGHT = BUILDER.comment("If the max light level in the room is above average, comfort variation per light level difference").define("comfortPerMoreWorkplaceLight", 0);
        BUILDER.pop();

        BUILDER.push("Other comfort values");
        AVERAGE_DISTANCE_BED_WORKPLACE = BUILDER.comment("The average distance (in blocks) between a villager's bed and their workplace").define("averageDistanceBedWorkplace", 12);
        MAX_BED_WORKPLACE_DISTANCE = BUILDER.comment("The distance (in blocks) between a villager's bed and their workplace above which no comfort variation will occur").define("maxBedWorkplaceDistance", 24);
        COMFORT_PER_MORE_BED_WORKPLACE_DISTANCE = BUILDER.comment("The variation in comfort for each block above average between a villager's bed and their workplace").define("comfortPerMoreBedWorkplaceDistance", 1);
        COMFORT_PER_LESS_BED_WORKPLACE_DISTANCE = BUILDER.comment("The variation in comfort for each block below average between a villager's bed and their workplace").define("comfortPerLessBedWorkplaceDistance", -1);
        AVERAGE_TIME_OUTSIDE = BUILDER.comment("Average minutes a villager spends outside").define("averageTimeOutside", 60);
        COMFORT_PER_DAY_WITHOUT_OUTSIDE = BUILDER.comment("Comfort variation for each day a villager spends without spending their average time outside").define("comfortPerDayWithoutOutside", -5);
        COMFORT_PER_DAY_WITHOUT_ZOMBIE = BUILDER.comment("Comfort variation for each day a villager spends without getting scared by a zombie").define("comfortPerDayWithoutZombie", 5);
        MAX_DAYS_WITHOUT_ZOMBIE = BUILDER.comment("Number of days after which a villager's comfort won't vary while not getting scared by zombies").define("maxDaysWithoutZombie", 7);
        COMFORT_PER_DAY_WITHOUT_SLEEP = BUILDER.comment("Comfort variation for each day a villager spends without sleeping").define("comfortPerDayWithoutSleep", -5);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }

    public static void setup()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SPEC, "villager-comfort.toml");
    }
}
