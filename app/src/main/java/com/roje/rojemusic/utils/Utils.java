package com.roje.rojemusic.utils;


import android.os.Environment;

import java.io.File;
import java.io.FileWriter;

public class Utils {
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
}
