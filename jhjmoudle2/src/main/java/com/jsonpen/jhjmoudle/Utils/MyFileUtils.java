package com.jsonpen.jhjmoudle.Utils;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 存取文件的工具类
 */
public class MyFileUtils {

    /**
     * 判断SD卡是否挂载
     */
    public static boolean isSDCardAvailable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 获取文件中保存的String字符串
     * @param context
     * @param filename
     * @return
     */
    public static String getFileText(Context context, String filename){
        String result ="";
        File file =new File(context.getFilesDir(),filename);
        try {
            BufferedReader br=new BufferedReader(new FileReader(file));
            String line="";
            StringBuffer buffer = new StringBuffer();
            while((line=br.readLine())!=null){
                buffer.append(line);
            }result= buffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 向内存文件中写入String字符串
     * @param context
     * @param content
     * @param filename
     * @return
     */
    public static boolean writeFileText(Context context,String content, String filename){
        boolean succ =false;
        File path =context.getFilesDir();
        File file =new File(path,filename);
        try {
            if(!file.exists()){
                file.createNewFile();
            }else {
                file.delete();
                file.createNewFile();
            }
            FileWriter fileWritter = new FileWriter(file,true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(content);
            bufferWritter.close();
            succ =true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //true = append file
        return succ;
    }

    /**
     * 新建一张图片返回文件信息
     *
     * @return imgNameStr 图片名称如 abc.png
     */
    public static File CreateImageFile(String imgNameStr) {
//        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURE‌S)
        File tmpDir = new File(Environment.getExternalStorageDirectory() + "/makeplay/Img/");
        if (!tmpDir.exists()) {
            tmpDir.mkdirs();
        }
//        String imgNameStr = UUID.randomUUID().toString();
        File outputImage = new File(tmpDir.getAbsolutePath() + "/" + imgNameStr );
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputImage;
    }

    /**
     * 删除指定文件夹下的所有文件
     * @param file
     * @return del结果
     */
    public static boolean delete(File file) {
        if (file.isFile()) {
            return file.delete();
        }

        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                return file.delete();
            }

            for (File childFile : childFiles) {
                delete(childFile);
            }
            return file.delete();
        }
        return false;
    }

}
