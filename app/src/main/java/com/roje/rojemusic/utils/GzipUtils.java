package com.roje.rojemusic.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class GzipUtils {
    public static String uncompress(InputStream is){
        String result = null;
        try{
            GZIPInputStream gZipis = new GZIPInputStream(is);
            ByteArrayOutputStream byteAos = new ByteArrayOutputStream();
            int length = -1;
            byte[] bytes = new byte[length];
            while ((length = gZipis.read(bytes)) != -1)
            byteAos.write(bytes,0,length);
            byteAos.flush();
            result = byteAos.toString();
            byteAos.close();
            gZipis.close();
            return result;
        }catch (IOException e){
            e.printStackTrace();
            return result;
        }

    }
}
