package com.snapbundle.util;

public final class HexUtil
{
    private HexUtil()
    {
    }

    public static char toHexChar(int i)
    {
        if ((0 <= i) && (i <= 9))
        {
            return (char) ('0' + i);
        } else
        {
            return (char) ('a' + (i - 10));
        }
    }

    public static String bytesToHex(byte[] data)
    {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < data.length; i++)
        {
            buf.append(byteToHex(data[i]).toUpperCase());
        }
        return (buf.toString());
    }

    public static String byteToHex(byte data)
    {
        StringBuilder buf = new StringBuilder();
        buf.append(toHexChar((data >>> 4) & 0x0F));
        buf.append(toHexChar(data & 0x0F));
        return buf.toString();
    }
}

