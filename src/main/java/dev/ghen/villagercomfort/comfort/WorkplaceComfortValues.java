package dev.ghen.villagercomfort.comfort;

import dev.ghen.villagercomfort.VillagerComfort;
import dev.ghen.villagercomfort.common.capabilty.IComfortValuesCap;
import dev.ghen.villagercomfort.core.config.CommonConfig;
import dev.ghen.villagercomfort.room.RoomHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiSection;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class WorkplaceComfortValues
{
    public static void setValuesToCap(Villager villager, IComfortValuesCap comfortValuesCap)
    {
        villager.getBrain().getMemory(MemoryModuleType.JOB_SITE).ifPresent(workstation ->
        {
            ServerLevel level = (ServerLevel) villager.level;
            BlockPos workstationPos = workstation.pos();
            BlockState workstationBlock = level.getBlockState(workstationPos);

            AtomicInteger workstationsCount = new AtomicInteger(0);
            AtomicInteger roomSize = new AtomicInteger(0);
            AtomicInteger maxLightValue = new AtomicInteger(0);

            RoomHelper.runForEveryBlock(villager.level, workstationPos, CommonConfig.MAX_WORKPLACE_SIZE.get().intValue(), (pos) ->
            {
                if(!pos.equals(workstationPos) && level.getBlockState(pos).is(workstationBlock.getBlock()))
                {
                    //counts number of workstations
                    workstationsCount.getAndIncrement();
                    //level.getPoiManager().getType(pos).ifPresent(poi -> workstationsCount.getAndIncrement());
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

            comfortValuesCap.setWorkplaceSize(roomSize.get());
            comfortValuesCap.setWorkstationsCount(workstationsCount.get() / 2);
            comfortValuesCap.setWorkplaceLight(maxLightValue.get());
        });
    }
}
