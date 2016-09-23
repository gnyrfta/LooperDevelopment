package com.gnyrfta.david.gnyrftalibrary;

/**
 * Created by david on 2016-09-22.
 */
public class GoodStuffs
{
    public byte[] arrayRotation(byte[] array,int delayLength)
    {
        byte[] temp = new byte[array.length];
        for(int i=0;i<array.length;i++)
        {
            temp[i]=array[i];
        }
        for(int i=0;i<array.length;i++)
        {
            int n=i+delayLength;
            int m=n%array.length;
            array[m]=temp[i];
        }
        return array;
    }
}
