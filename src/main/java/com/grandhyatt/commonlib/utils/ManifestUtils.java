package com.grandhyatt.commonlib.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Manifest工具类
 *
 * @author
 * @email
 * @mobile
 * @create 2018/6/1 10:50
 */
public class ManifestUtils {
    public static String channel;

    public static String getUmengChannel(Context context) {
        return getChannel(context);
    }

    /**
     * 获取META-INFO下面的渠道
     *
     * @param context
     * @return
     */
    public static String getChannel(Context context) {
        if (!TextUtils.isEmpty(channel)) {
            return channel;
        }
        ApplicationInfo appinfo = context.getApplicationInfo();
        String sourceDir = appinfo.sourceDir;
        ZipFile zipfile = null;
        final String start_flag = "META-INF/mtchannel_";
        try {
            zipfile = new ZipFile(sourceDir);
            Enumeration<?> entries = zipfile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String entryName = entry.getName();
                if (entryName.contains(start_flag)) {
                    channel = entryName.replaceAll(start_flag, "");
                    return channel;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zipfile != null) {
                try {
                    zipfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";

    }
}