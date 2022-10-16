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


    CompoundTag serializeNBT();
    void deserializeNBT(CompoundTag tag);
}

