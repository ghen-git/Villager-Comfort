package dev.ghen.villagercomfort.core.math;

public class MathHelper
{
    public static int scale(int value, int prevScale, int newScale)
    {
        return (int)(((float)value / (float)prevScale) * newScale);
    }
}
