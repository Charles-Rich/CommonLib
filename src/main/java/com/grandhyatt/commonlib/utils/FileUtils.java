package com.grandhyatt.commonlib.utils;

import android.content.Context;
import android.media.ExifInterface;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 文件工具类
 *
 * @author
 * @email
 * @mobile
 * @create 2018/7/20 22:43
 */
public class FileUtils {

    private static final String TAG = FileUtils.class.getSimpleName();

    public static final String AUDIO = Environment.DIRECTORY_MUSIC;
    public static final String IMAGE = Environment.DIRECTORY_PICTURES;
    public static final String OTHER = Environment.DIRECTORY_PICTURES;

    static boolean mExternalStorageAvailable = false;
    static boolean mExternalStorageWriteable = false;

    /**
     * 检索当前系统是否包含扩展存储卡
     *
     * @return 0:没有存储卡|1:有只读存储卡|2:有可读写存储卡
     */
    public static int hasExternalStorage() {
        final String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            return 2;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return 1;
        } else {
            // 其它状态是错误的，即不能读也不能写
            return 0;
        }
    }

    /**
     * 获取扩展存储卡剩余空间
     */
    public static long getAvailableExternalStorageSize() {
        if (2 == hasExternalStorage()) {
            StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());

            long blockSize = localStatFs.getBlockSize();
            long blockCount = localStatFs.getAvailableBlocks();
            return blockSize * blockCount;
        }
        return 0;
    }

    /**
     * 复制一个文件
     */
    public static int copyFile(String fromFile, String toFile) {
        try {
            InputStream fosfrom = new FileInputStream(fromFile);
            FileOutputStream fosto = new FileOutputStream(toFile);
            byte[] bys = new byte[4096];
            int c;
            while ((c = fosfrom.read(bys)) > 0) {
                fosto.write(bys, 0, c);
            }
            fosfrom.close();
            fosto.close();
            return 0;

        } catch (Exception ex) {
            return -1;
        }
    }

    /**
     * 获取files目录，优先返回存储卡中的目录
     */
    public static File getFilesDir(Context context) {
        if (2 == hasExternalStorage()) {
            return context.getExternalFilesDir(null);
        } else {
            return context.getFilesDir();
        }
    }

    /**
     * 获取缓存路径（优先返回存储卡中的目录）
     */
    public static File getCacheDir(Context context) {
        if (2 == hasExternalStorage()) {
            return context.getExternalCacheDir();
        } else {
            return context.getCacheDir();
        }
    }

    /**
     * 返回指定目录中一个唯一的空文件
     * <p>
     * 注：方法成功返回时文件已创建，用后请删除
     *
     * @param fDir   文件目录 如果为null则会在扩展存储卡中的CacheDir中生成文件
     *               如果扩展存储卡不存在则在应用CacheDir中生成文件
     * @param suffix 文件后缀 null则使用.tmp
     * @return 返回则返回null
     */
    public static File createTempFile(Context context, File fDir, String suffix) {
        if (fDir == null) {
            if (2 == hasExternalStorage()) {
                fDir = context.getExternalCacheDir();
            } else {
                fDir = context.getCacheDir();
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHH");
        String prefix = sdf.format(new Date());
        try {
            return File.createTempFile(prefix, suffix, fDir);
        } catch (IOException ex) {
            Log.e(TAG, ex.getMessage());
            return null;
        }
    }

    /**
     * 获取一个空的临时文件
     *
     * @throws IOException
     */
    public static File createTempFile(Context context, String suffix) {
        return createTempFile(context, null, suffix);
    }

    /**
     * 获取随机图片文件名
     *
     * @param
     * @return
     * @author
     */
    static public File getImageFilePath(Context context) {
        String suffix = ".jpg";
        File dir = getDirByType(context, IMAGE);
        File file = getFile(suffix, dir);
        return file;
    }

    /**
     * 获取图片和音频外的文件名
     *
     * @param sufix
     * @return
     * @author
     */
    static public File getOtherFilePath(Context context, String sufix) {
        String suffix = "." + sufix;
        File dir = getDirByType(context, OTHER);
        File file = getFile(suffix, dir);
        return file;
    }

    /**
     * 获取音频随机文件名
     *
     * @param
     * @return
     * @author
     */
    static public File getAudioFilePath(Context context) {
        String suffix = ".amr";
        File dir = getDirByType(context, AUDIO);
        File file = getFile(suffix, dir);
        return file;
    }

    /**
     * 获取音频随机文件名
     *
     * @param suffix
     * @param dir
     * @return
     * @author
     */
    public static File getFile(String suffix, File dir) {
        if (dir == null) {
            throw new NullPointerException("Dir can not be null!");
        } else if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            return File.createTempFile("fx_temp_", suffix, dir);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取图片文件扩展信息(方向)
     *
     * @param filepath 文件路径
     * @return int 图片的旋转角度
     * @author
     */
    public static int getExifOrientation(String filepath) {
        int degree = 0;
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(filepath);
        } catch (IOException ex) {
            Log.e(TAG, ex.getMessage());
        }
        if (exif != null) {
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
            if (orientation != -1) {
                // We only recognize a subset of orientation tag values.
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                }

            }
        }
        return degree;
    }

    /**
     * 检测外部存储卡状态
     *
     * @param
     * @return
     * @author
     */
    private static void checkStorageState() {
        final String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // 外部存储卡可读写
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // 外部存储卡只读
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            // 外部存储卡不可用
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }
    }

    /**
     * 获取音频随机文件
     *
     * @param
     * @return
     * @author
     */
    public static File getAudioDir(Context context) {
        return getDirByType(context, AUDIO);
    }

    /**
     * 获取图片随机文件
     *
     * @param
     * @return
     * @author
     */
    public static File getImageDir(Context context) {
        return getDirByType(context, IMAGE);
    }

    /**
     * @param typeName
     * @return
     * @author
     */
    static public File getDirByType(Context context, String typeName) {
        checkStorageState();
        if (mExternalStorageWriteable) {
            return context.getExternalFilesDir(typeName);
        } else {
            return context.getCacheDir();
        }
    }

    /**
     * @param
     * @return
     * @author WLF
     */
    public static File getFileDir(Context context) {
        checkStorageState();
        if (mExternalStorageWriteable) {
            return context.getExternalFilesDir(null);
        } else {
            return context.getCacheDir();
        }
    }

    /**
     * 检测SD卡是否可写
     *
     * @param
     * @return
     * @author WLF
     */
    public static boolean isSdcardWriteable() {
        checkStorageState();
        return mExternalStorageWriteable;
    }

    /**
     * 检测文件是否可用
     *
     * @param
     * @return
     * @author WLF
     */
    public static boolean checkFile(File f) {
        if (f != null && f.exists() && f.canRead() && (f.isDirectory() || (f.isFile() && f.length() > 0))) {
            return true;
        }
        return false;
    }

    /**
     * 检测文件是否可用
     *
     * @param
     * @return
     * @author WLF
     */
    public static boolean checkFile(String path) {
        if (!TextUtils.isEmpty(path)) {
            File f = new File(path);
            if (f != null && f.exists() && f.canRead() && (f.isDirectory() || (f.isFile() && f.length() > 0)))
                return true;
        }
        return false;
    }

    public static String readAssetsFileString(Context context, String fileName) {
        String str = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            str = new String(buffer, "GB2312");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 返回指定的路径字符串的扩展名
     *
     * @param path 路径字符串
     * @return 包含指定路径的扩展名（包括“.”）的 System.String、null 或 System.String.Empty。如果
     * path 为 null，则 GetExtension 返回 null。如果 path 不具有扩展名信息，则
     * GetExtension 返回 Empty。
     */
    public static String getExtension(String path) {
        if (!TextUtils.isEmpty(path)) {
            int dot = path.lastIndexOf('.');
            if ((dot > -1) && (dot < (path.length() - 1))) {
                return path.substring(dot);
            }
        }
        return path;
    }


}
