package com.sangeng.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class PathUtils {
    // 生成文件路径
    public static String generateFilePath(String fileName){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); // 规定格式
        String datePath = sdf.format(new Date());

        // uuid 作为文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        // 获取文件后缀名
        int index = fileName.lastIndexOf(".");
        String fileType = fileName.substring(index);

        // 拼接所有文件名并返回
        return new StringBuilder().append(datePath).append(uuid).append(fileType).toString();
    }
}
