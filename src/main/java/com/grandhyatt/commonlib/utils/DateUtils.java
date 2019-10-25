package com.grandhyatt.commonlib.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间相关函数工具类
 *
 * @author
 * @mail
 * @create 17/3/10 下午6:17
 */

public class DateUtils {

    /**
     * 将时间戳转换为String
     *
     * @param timeStamp 时间戳
     * @return String 类型
     */
    public static String timeStampToString(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(timeStamp * 1000);
        return strDate;
    }

    /**
     * 将时间戳转换为String
     *
     * @param timeStamp 时间戳
     * @return String 类型
     */
    public static String timeStampToString(String format, long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String strDate = sdf.format(timeStamp * 1000);
        return strDate;
    }

    /**
     * 将时间戳转换为Date
     *
     * @param timeStamp 时间戳
     * @return Date 类型
     */
    public static Date timeStampToDate(long timeStamp) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = sdf.format(timeStamp * 1000);
            Date date = sdf.parse(strDate);
            return date;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 字符串转日期
     *
     * @param dateStr 日期字符串
     * @return 日期类型
     */
    public static Date strToDate(String dateStr) {
        try {
            dateStr = dateStr.replace("/", "-");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateStr);
            return date;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 字符串转日期
     *
     * @param format  日期格式
     * @param dateStr 日期字符串
     * @return 日期类型
     */
    public static Date strToDate(String format, String dateStr) {
        try {
            dateStr = dateStr.replace("/", "-");
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date = sdf.parse(dateStr);
            return date;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 日期转字符串
     *
     * @param date 日期
     * @return 日期转换后的字符串
     */
    public static String dateToStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date).replace(" ", "T");
    }

    /**
     * 日期转字符串
     *
     * @param date   日期
     * @param format 日期格式
     */
    public static String dateToStr(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String dateToStrHour00_00(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        String ret = sdf.format(date) + ":00:00";
        return ret;
    }

    public static String dateToStrHour59_59(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        String ret = sdf.format(date) + ":59:59";
        return ret;
    }

    public static String dateToStrDat00_00_00(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ret = sdf.format(date) + "00:00:00";
        return ret;
    }

    public static String dateToStrDat23_59_59(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ret = sdf.format(date) + "23:59:59";
        return ret;
    }

    public static Date strToDate_2(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(dateStr);
            return date;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 日期转时间戳
     */
    public static long dateToTimestamp(Date date) {
        return date.getTime();
    }

    /**
     * 日期转时间戳
     */
    public static int dateToTimestampInt(Date date) {
        return (int) (date.getTime() / 1000);
    }

    /**
     *
     * */
    public static int getDateIndex(Date dateTime, int index) {
        int i = 0;
        switch (index) {
            case 1:
                i = dateTime.getMonth();
                break;
            case 2:
                i = dateTime.getDay();
                break;
            case 3:
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateTime);
                i = calendar.DAY_OF_WEEK;
                break;
            case 4:
                i = dateTime.getYear();
                break;
        }
        return i;
    }

    /**
     * 获取指定时间与当前时间的豪秒数
     *
     * @author
     * @create 2017/5/30 上午2:39
     */
    public static long getDateTimeSpan(Date date) {
        return new Date().getTime() - date.getTime();
    }

    /**
     * 获取指定时间与当前时间的豪秒数
     *
     * @author
     * @create 2017/5/30 上午2:39
     */
    public static long getDateTimeSpan(Date end, Date start) {
        return end.getTime() - start.getTime();
    }

    public static boolean getDateTimeSpan(Date date, long timeSpan) {
        return getDateTimeSpan(date) > timeSpan;
    }

    public static boolean getDateTimeSpan(Date end, Date start, long timeSpan) {
        return getDateTimeSpan(end, start) > timeSpan;
    }

    public static long getDateTimeSpanDays(Date end, Date start) {
        return (int) ((end.getTime() / 1000 - start.getTime() / 1000) / (24 * 60 * 60));
    }

    public static int getDateYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getDateMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getDateDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDateHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getDateMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    public static int getDateSeconds(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    public static String spliteDateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("s");
        return sdf.format(date);
    }

    public static String spliteDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String spliteTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(date);
    }

    public static String getDateStringLunar(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        return sdf.format(date);
    }

    /**
     * 判断当前日期是星期几
     *
     * @param pTime 设置的需要判断的时间  //格式如2012-09-08
     * @return dayForWeek 判断结果
     * @Exception 发生异常
     */
    public static String getWeek(String pTime) {
        String Week = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "星期天";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "星期一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "星期二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "星期三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "星期四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "星期五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "星期六";
        }
        return Week;
    }

    /**
     * 根据毫秒返回时分秒
     *
     * @param time
     * @return
     */
    public static String getFormatDHMSString(long time) {
        String strDateTime = null;

        long days = time / (60 * 60 * 24);
        long hours = (time % (60 * 60 * 24)) / (60 * 60);
        long minutes = (time % (60 * 60)) / 60;
        long seconds = time % 60;

        if (days > 0) {
            strDateTime = days + "天" + hours + "小时" + minutes + "分钟" + seconds + "秒";
        } else if (hours > 0) {
            strDateTime = hours + "小时" + minutes + "分钟" + seconds + "秒";
        } else if (minutes > 0) {
            strDateTime = minutes + "分钟" + seconds + "秒";
        } else {
            strDateTime = seconds + "秒";
        }

        return strDateTime;
    }

    public static String getFormatDHMS(long time) {
        String strDateTime = null;
        time = time / 1000;
        long days = time / (60 * 60 * 24);
        long hours = (time % (60 * 60 * 24)) / (60 * 60);
        long minutes = (time % (60 * 60)) / 60;
        long seconds = time % 60;

        strDateTime = String.format("%02d:%02d:%02d",hours,minutes,seconds);

        return strDateTime;
    }

    public static  Date parse(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(strDate);
    }
    /**
     * 由出生日期获得年龄
     *
     */
    public static  int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            }else{
                age--;
            }
        }
        return age;
    }
}
