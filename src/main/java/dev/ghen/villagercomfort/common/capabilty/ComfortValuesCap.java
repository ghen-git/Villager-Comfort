package dev.ghen.villagercomfort.common.capabilty;

import net.minecraft.nbt.CompoundTag;

public class ComfortValuesCap implements IComfortValuesCap
{
    private int bedroomSize;
    private int bedsCount;
    private int bedroomLight;
    private boolean isWorkstationInBedroom;
    private int workplaceSize;
    private int workstationsCount;
    private int workplaceLight;

    @Override
    public int getBedroomSize()
    {
        return bedroomSize;
    }

    @Override
    public void setBedroomSize(int value)
    {
        bedroomSize = value;
    }

    @Override
    public CompoundTag serializeNBT()
    {
        CompoundTag nbt = new CompoundTag();

        nbt.putInt("bedroomSize", bedroomSize);
        nbt.putInt("bedsCount", bedsCount);
        nbt.putInt("bedroomLight", bedroomLight);
        nbt.putBoolean("isWorkstationInBedroom", isWorkstationInBedroom);

        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag tag)
    {
        bedroomSize = tag.getInt("bedroomSize");
        bedsCount = tag.getInt("bedsCount");
        bedroomLight = tag.getInt("bedroomLight");
        isWorkstationInBedroom = tag.getBoolean("isWorkstationInBedroom");
    }

    @Override
    public int getBedsCount()
    {
        return bedsCount;
    }

    @Override
    public void setBedsCount(int bedsCount)
    {
        this.bedsCount = bedsCount;
    }

    @Override
    public int getBedroomLight()
    {
        return bedroomLight;
    }

    @Override
    public void setBedroomLight(int bedroomLight)
    {
        this.bedroomLight = bedroomLight;
    }

    @Override
    public boolean getIsWorkstationInBedroom()
    {
        return isWorkstationInBedroom;
    }

    @Override
    public void setIsWorkstationInBedroom(boolean workstationInBedroom)
    {
        isWorkstationInBedroom = workstationInBedroom;
    }

    @Override
    public int getWorkplaceSize()
    {
        return workplaceSize;
    }

    @Override
    public void setWorkplaceSize(int workplaceSize)
    {
        this.workplaceSize = workplaceSize;
    }

    @Override
    public int getWorkstationsCount()
    {
        return workstationsCount;
    }

    @Override
    public void setWorkstationsCount(int workstationsCount)
    {
        this.workstationsCount = workstationsCount;
    }

    public int getWorkplaceLight()
    {
        return workplaceLight;
    }

    public void setWorkplaceLight(int workplaceLight)
    {
        this.workplaceLight = workplaceLight;
    }
}
