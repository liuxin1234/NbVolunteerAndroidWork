package com.example.renhao.wevolunteer.utils;


import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.math.BigDecimal;

public class DataCleanManager
{
    public static void clearAllCache(Context paramContext)
    {
        deleteDir(paramContext.getCacheDir());
        if (Environment.getExternalStorageState().equals("mounted"))
            deleteDir(paramContext.getExternalCacheDir());
    }

    private static boolean deleteDir(File paramFile)
    {
        String[] arrayOfString = new String[0];
        if ((paramFile != null) && (paramFile.isDirectory()))
            arrayOfString = paramFile.list();
        for (int i = 0; ; i++)
        {
            if (i >= arrayOfString.length)
                return paramFile.delete();
            if (!deleteDir(new File(paramFile, arrayOfString[i])))
                return false;
        }
    }

    private static long getFolderSize(File paramFile)
            throws Exception
    {
        long l1 = 0L;
        while (true)
        {
            int i;
            try
            {
                File[] arrayOfFile = paramFile.listFiles();
                i = 0;
                if (i >= arrayOfFile.length)
                    return l1;
                if (arrayOfFile[i].isDirectory())
                {
                    l1 += getFolderSize(arrayOfFile[i]);
                }
                else
                {
                    long l2 = arrayOfFile[i].length();
                    l1 += l2;
                }
            }
            catch (Exception localException)
            {
                localException.printStackTrace();
                return l1;
            }
            i++;
        }
    }

    private static String getFormatSize(double paramDouble)
    {
        double d1 = paramDouble / 1024.0D;
        if (d1 < 1.0D)
            return "0KB";
        double d2 = d1 / 1024.0D;
        if (d2 < 1.0D)
            return new BigDecimal(Double.toString(d1)).setScale(2, 4).toPlainString() + "KB";
        double d3 = d2 / 1024.0D;
        if (d3 < 1.0D)
            return new BigDecimal(Double.toString(d2)).setScale(2, 4).toPlainString() + "MB";
        double d4 = d3 / 1024.0D;
        if (d4 < 1.0D)
            return new BigDecimal(Double.toString(d3)).setScale(2, 4).toPlainString() + "GB";
        return new BigDecimal(d4).setScale(2, 4).toPlainString() + "TB";
    }

    public static String getTotalCacheSize(Context paramContext)
            throws Exception
    {
        long l = getFolderSize(paramContext.getCacheDir());
        if (Environment.getExternalStorageState().equals("mounted"))
            l += getFolderSize(paramContext.getExternalCacheDir());
        return getFormatSize(l);
    }
}
