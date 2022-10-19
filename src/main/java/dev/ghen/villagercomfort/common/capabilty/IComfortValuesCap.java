package dev.ghen.villagercomfort.common.capabilty;

import net.minecraft.nbt.CompoundTag;

public interface IComfortValuesCap
{
    int getBedroomSize();
    void setBedroomSize(int value);
    int getBedsCount();
    void setBedsCount(int bedsCount);
    int getBedroomLight();
    void setBedroomLight(int bedroomLight);
    boolean getIsWorkstationInBedroom();
    void setIsWorkstationInBedroom(boolean getIsWorkstationInBedroom);
    int getWorkplaceSize();
    void setWorkplaceSize(int workplaceSize);
    int getWorkstationsCount();
    void setWorkstationsCount(int workstationsCount);
    int getWorkplaceLight();
    void setWorkplaceLight(int workplaceLight);
    int getOutsideSeconds();
    void setOutsideSeconds(int outsideSeconds);
    void addOutsideSeconds(int outsideSeconds);
    int getDaysWithoutOutside();
    void setDaysWithoutOutside(int daysWithoutOutside);
    void addDaysWithoutOutside(int daysWithoutOutside);
    int getDaysWithoutZombie();
    void setDaysWithoutZombie(int daysWithoutZombie);
    void addDaysWithoutZombie(int daysWithoutZombie);
    int getBedWorkstationDistance();
    void setBedWorkstationDistance(int bedWorkstationDistance);
    int getComfort();
    void setComfort(int comfort);


    CompoundTag serializeNBT();
    void deserializeNBT(CompoundTag tag);
}

