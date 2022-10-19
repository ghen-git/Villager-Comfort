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
    private int outsideSeconds;
    private int daysWithoutOutside;
    private int daysWithoutZombie;
    private int bedWorkstationDistance;
    private int comfort;

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
        nbt.putInt("workplaceSize", workplaceSize);
        nbt.putInt("workstationsCount", workstationsCount);
        nbt.putInt("workplaceLight", workplaceLight);
        nbt.putInt("outsideSeconds", outsideSeconds);
        nbt.putInt("daysWithoutOutside", daysWithoutOutside);
        nbt.putInt("daysWithoutZombie", daysWithoutZombie);
        nbt.putInt("bedWorkstationDistance", bedWorkstationDistance);
        nbt.putInt("comfort", comfort);

        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag tag)
    {
        bedroomSize = tag.getInt("bedroomSize");
        bedsCount = tag.getInt("bedsCount");
        bedroomLight = tag.getInt("bedroomLight");
        isWorkstationInBedroom = tag.getBoolean("isWorkstationInBedroom");
        workplaceSize = tag.getInt("workplaceSize");
        workstationsCount = tag.getInt("workstationsCount");
        workplaceLight = tag.getInt("workplaceLight");
        outsideSeconds = tag.getInt("outsideSeconds");
        daysWithoutOutside = tag.getInt("daysWithoutOutside");
        daysWithoutZombie = tag.getInt("daysWithoutZombie");
        bedWorkstationDistance = tag.getInt("bedWorkstationDistance");
        comfort = tag.getInt("comfort");
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

    @Override
    public int getOutsideSeconds()
    {
        return outsideSeconds;
    }

    @Override
    public void setOutsideSeconds(int outsideSeconds)
    {
        this.outsideSeconds = outsideSeconds;
    }

    @Override
    public void addOutsideSeconds(int outsideSeconds)
    {
        this.outsideSeconds += outsideSeconds;
    }

    @Override
    public int getDaysWithoutZombie()
    {
        return daysWithoutZombie;
    }

    @Override
    public void setDaysWithoutZombie(int daysWithoutZombie)
    {
        this.daysWithoutZombie = daysWithoutZombie;
    }

    @Override
    public void addDaysWithoutZombie(int daysWithoutZombie)
    {
        this.daysWithoutZombie += daysWithoutZombie;
    }

    @Override
    public int getBedWorkstationDistance()
    {
        return bedWorkstationDistance;
    }

    @Override
    public void setBedWorkstationDistance(int bedWorkstationDistance)
    {
        this.bedWorkstationDistance = bedWorkstationDistance;
    }

    @Override
    public int getComfort()
    {
        return comfort;
    }

    @Override
    public void setComfort(int comfort)
    {
        this.comfort = comfort;
    }

    @Override
    public int getDaysWithoutOutside()
    {
        return daysWithoutOutside;
    }

    @Override
    public void setDaysWithoutOutside(int daysWithoutOutside)
    {
        this.daysWithoutOutside = daysWithoutOutside;
    }

    @Override
    public void addDaysWithoutOutside(int daysWithoutOutside)
    {
        this.daysWithoutOutside += daysWithoutOutside;
    }
}
