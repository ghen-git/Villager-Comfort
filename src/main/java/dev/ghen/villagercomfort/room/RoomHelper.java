package dev.ghen.villagercomfort.room;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;

public class RoomHelper
{
    // this is set to whatever the "maximum ideal room radius" should be
    private static final int DEF_MAX_DISTANCE = 5;

    public static void runForEveryBlock(Level level, BlockPos roomStartPos, IRoomBlockAction action)
    {
        List<BlockPos> room = new ArrayList<>();

        addBlockToRoom(level, roomStartPos, room, DEF_MAX_DISTANCE, action);
    }

    public static void runForEveryBlock(Level level, BlockPos roomStartPos, int maxDistance, IRoomBlockAction action)
    {
        List<BlockPos> room = new ArrayList<>();

        addBlockToRoom(level, roomStartPos, room, maxDistance / 2, action);
    }

    static void addBlockToRoom(Level level, BlockPos pos, List<BlockPos> room, int maxDistance, IRoomBlockAction action)
    {
        if(!room.contains(pos) && isPosInBounds(pos, room, maxDistance))
        {
            if(room.isEmpty() || (level.getBlockState(pos).is(Blocks.AIR) && level.getBlockState(pos.above()).is(Blocks.AIR)))
            {
                room.add(pos);

                addBlockToRoom(level, new BlockPos(pos.north()), room, maxDistance, action);
                addBlockToRoom(level, new BlockPos(pos.south()), room, maxDistance, action);
                addBlockToRoom(level, new BlockPos(pos.west()), room, maxDistance, action);
                addBlockToRoom(level, new BlockPos(pos.east()), room, maxDistance, action);
                addBlockToRoom(level, new BlockPos(pos.above()), room, maxDistance, action);
                addBlockToRoom(level, new BlockPos(pos.below()), room, maxDistance, action);
            }

            action.run(pos);
        }
    }

    static boolean isPosInBounds(BlockPos pos, List<BlockPos> room, int distance)
    {
        if(room.isEmpty())
            return true;

        BlockPos startingPos = room.get(0);

        return pos.getX() > startingPos.getX() - distance && pos.getX() < startingPos.getX() + distance &&
                pos.getY() > startingPos.getY() - distance && pos.getY() < startingPos.getY() + distance &&
                pos.getZ() > startingPos.getZ() - distance && pos.getZ() < startingPos.getZ() + distance;
    }
}
