package com.roje.rojemusic.utils;


import android.os.Environment;
import android.util.Base64;

import java.io.File;
import java.io.FileWriter;

public class Utils {
    private static final String key = "Encrypt";
    public static void write2File(String filename,String data){
        File file = new File(Environment.getExternalStorageDirectory()+"/"+filename);
        try {
            if (!file.exists())
                file.createNewFile();
            else {
                file.delete();
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            writer.write(data);
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static String decode(String arrby) {
        int n;
        int n2;
        int n3;
        int n4;
        byte[] bytes;
        if (arrby == null) {
            return null;
        }
        try {
            bytes = Base64.decode(arrby, Base64.DEFAULT);
            n2 = bytes.length;
            n = key.length();
            n3 = 0;
            n4 = 0;
        }
        catch (Exception exception) {
            return null;
        }
        while (n3 < n2) {
            int n5 = n4;
            if (n4 >= n) {
                n5 = 0;
            }
            bytes[n3] = (byte)(bytes[n3] ^ key.charAt(n5));
            ++n3;
            n4 = n5 + 1;
        }
        return new String(bytes);
    }
}
