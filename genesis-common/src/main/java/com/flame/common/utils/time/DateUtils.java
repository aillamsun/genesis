package com.flame.common.utils.time;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: zhouzh
 * Date: 15-3-3
 * Time: 下午1:54
 * To change this template use File | Settings | File Templates.
 */
public class DateUtils {

    public static final int DATEDIFF_DAY = 1;
    public static final int DATEDIFF_HOUR = 2;
    public static final int DATEDIFF_MINUTE = 3;
    public static final int DATEDIFF_SECOND = 4;
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public enum DateFiled {
        YEAR, MONTH, DATE, HOUR, MINUTE
    }

    public static boolean isNight(Date date) {
        int hour = date.getHours();
        if (hour > 22 || hour < 5)
            return true;
        else
            return false;
    }

    public static Date string2Date(String str, String format) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);//小写的mm表示的是分钟
            date = sdf.parse(str);
        } catch (Exception ex) {
            ex.printStackTrace();
            date = null;
        }
        return date;
    }

    public static String toLongDateString(Date dt) {
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
        return myFmt.format(dt);
    }

    public static String toShortDateString(Date dt) {
        SimpleDateFormat myFmt = new SimpleDateFormat("yy年MM月dd日 HH时mm分");
        return myFmt.format(dt);
    }

    public static String toLongTimeString(Date dt) {
        SimpleDateFormat myFmt = new SimpleDateFormat("HH mm ss SSSS");
        return myFmt.format(dt);
    }

    public static String toShortTimeString(Date dt) {
        SimpleDateFormat myFmt = new SimpleDateFormat("yy/MM/dd HH:mm");
        return myFmt.format(dt);
    }

    /**
     * 取得前i天所有日期
     *
     * @param i
     * @return
     */
    public static ArrayList<String> getDays(int i) {
        ArrayList<String> list = new ArrayList<String>();
        for (int j = -i; j < 0; j++) {
            Calendar cal = Calendar.getInstance();//使用日历类
            SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
            cal.add(Calendar.DAY_OF_MONTH, j);  //设置为前j天
            String defaultStartDate = myFmt.format(cal.getTime());
            list.add(defaultStartDate);
        }

        return list;
    }

    public static long getDateDiff(Date d1, Date d2, int calendar) {
        long x = d1.getTime() - d2.getTime();

        double x1 = new Double(x);
        double ret = x1;
        switch (calendar) {
            case DATEDIFF_DAY:
                ret = DateUtils.div(x1, (1000 * 60 * 60 * 24), 2);
                break;
            case DATEDIFF_HOUR:
                ret = DateUtils.div(x1, (1000 * 60 * 60), 2);
                break;
            case DATEDIFF_MINUTE:
                ret = DateUtils.div(x1, (1000 * 60), 2);
                break;
            case DATEDIFF_SECOND:
                ret = DateUtils.div(x1, 100, 2);
                break;
            default:
                break;
        }
        double retdouble = DateUtils.round(ret, 0);
        return (long) retdouble;
    }

    public static long getDateDiff(Date d1, Date d2) {
        return getDateDiff(d1, d2, 0);
    }

    public static long getDateDiff(String date1, String date2, int calendar) {
        try {
            if (date1 == null || "".equals(date1) || date2 == null
                    || "".equals(date2)) {
                return 0;
            }
            String fmt = "yyyy-MM-dd HH:mm:ss";
            switch (calendar) {
                case DATEDIFF_DAY:
                    fmt = "yyyy-MM-dd";
                    break;
                default:
                    break;
            }
            SimpleDateFormat sd = new SimpleDateFormat(fmt);
            Date d1 = sd.parse(date1);
            Date d2 = sd.parse(date2);
            return getDateDiff(d1, d2, calendar);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getCurrentTime(String format) {
        SimpleDateFormat fmt = new SimpleDateFormat((format != null && !""
                .equals(format)) ? format : DEFAULT_FORMAT);
        return fmt.format(Calendar.getInstance().getTime());
    }

    public static Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }

    public static String fmtDate(Date date, String fmt) {
        try {
            if (date == null)
                return null;
            SimpleDateFormat sdf = new SimpleDateFormat((fmt != null && !"".equals(fmt)) ? fmt : DEFAULT_FORMAT);
            return sdf.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return date.toString();
        }
    }

    public static Date getTime(String time, String fmt) {
        SimpleDateFormat sdf = new SimpleDateFormat((fmt != null && !"".equals(fmt)) ? fmt : DEFAULT_FORMAT);
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date modifyDate(Date date, int amount, DateFiled filed) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (filed == DateFiled.YEAR) {
            c.add(Calendar.YEAR, amount);
        } else if (filed == DateFiled.MONTH) {
            c.add(Calendar.MONTH, amount);
        } else if (filed == DateFiled.DATE) {
            c.add(Calendar.DAY_OF_MONTH, amount);
        } else if (filed == DateFiled.HOUR) {
            c.add(Calendar.HOUR, amount);
        } else if (filed == DateFiled.MINUTE) {
            c.add(Calendar.MINUTE, amount);
        }
        return c.getTime();
    }

    public static Date parse(String strDate) {
        Date date;
        SimpleDateFormat sdf = new SimpleDateFormat();

        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(strDate);
            return date;
        } catch (Exception e) {

        }

        sdf.applyPattern("yyyy-MM-dd HH:mm");
        try {
            date = sdf.parse(strDate);
            return date;
        } catch (Exception e) {

        }

        sdf.applyPattern("yyyy-MM-dd");
        try {
            date = sdf.parse(strDate);
            System.out.println("" + date);
            return date;
        } catch (Exception e) {

        }

        sdf.applyPattern("yyyy/MM/dd");
        try {
            String xx = strDate.substring(strDate.lastIndexOf("/"));
            if (xx.length() > 2) {
                sdf.applyPattern("dd/MM/yyyy");
                date = sdf.parse(strDate);
            } else {
                date = sdf.parse(strDate);
            }

            System.out.println("" + date);
            return date;
        } catch (Exception e) {

        }

        sdf.applyPattern("yyyyMMdd");
        try {
            date = sdf.parse(strDate);
            return date;
        } catch (Exception e) {

        }

        sdf.applyPattern("yyyy年MM月dd日");
        try {
            date = sdf.parse(strDate);
            return date;
        } catch (Exception e) {

        }

        sdf.applyPattern("yyyy年MM月");
        try {
            date = sdf.parse(strDate);
            return date;
        } catch (Exception e) {

        }

        sdf.applyPattern("yyyy年WW周");
        try {
            date = sdf.parse(strDate);
            return date;
        } catch (Exception e) {

        }

        sdf.applyPattern("yyyy年");
        try {
            date = sdf.parse(strDate);
            return date;
        } catch (Exception e) {

        }

        sdf.applyPattern("yyyyMM");
        try {
            date = sdf.parse(strDate);
            return date;
        } catch (Exception e) {

        }

        sdf.applyPattern("yyyy");
        try {
            date = sdf.parse(strDate);
            return date;
        } catch (Exception e) {

        }

        sdf.applyPattern("yyyy年MM月dd日 HH时mm分ss秒");
        try {
            date = sdf.parse(strDate);
            return date;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static String parse(Date date, String pattern) {
        String strDate;
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern(pattern);
        try {
            strDate = sdf.format(date);
            return strDate;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static String parse(String date, String pattern) {
        try {
            return parse(parse(date), pattern);
        } catch (Exception e) {

        }
        return null;
    }

    public static Date composeDate(String year, String month, String day) throws Exception {
        int y = 0, m = 0, d = 0;
        try {
            y = Integer.parseInt(year);
            m = Integer.parseInt(month);
            d = Integer.parseInt(day);

            Calendar c = Calendar.getInstance();
            c.set(y, m, d, 0, 0, 0);
            return c.getTime();
        } catch (Exception e) {
            throw new Exception("时间格式不正确");
        }
    }

    public static String composeDate(String pattern, String year, String month, String day) throws Exception {
        int y = 0, m = 0, d = 0;
        String sy, sm, sd;
        try {
            y = Integer.parseInt(year);
            m = Integer.parseInt(month) - 1;
            d = Integer.parseInt(day);
            Calendar c = Calendar.getInstance();
            c.set(y, m, d, 0, 0, 0);
            c.getTime();// let calendar to recalculate
            if (pattern == "YYYYMMDD") {
                sy = String.valueOf(c.get(Calendar.YEAR));
                sm = String.valueOf(c.get(Calendar.MONTH) + 1);
                if (sm.length() == 1) {
                    sm = "0" + sm;
                }
                sd = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
                if (sd.length() == 1) {
                    sd = "0" + sd;
                }
                return sy + sm + sd;
            }
            return "";
        } catch (Exception e) {
            throw new Exception("时间格式不正确");
        }
    }

    public static Date increase(Date sdate, String UnitType, int length) {
        Date rdate = null;
        UnitType = UnitType.trim();
        Calendar c = Calendar.getInstance();
        c.setTime(sdate);
        if ((UnitType.compareToIgnoreCase("年") == 0)
                || UnitType.compareToIgnoreCase("year") == 0) {
            c.set(Calendar.YEAR, c.get(Calendar.YEAR) + length);
            return c.getTime();
        } else if ((UnitType.compareToIgnoreCase("月") == 0)
                || (UnitType.compareToIgnoreCase("month") == 0)) {
            c.set(Calendar.MONTH, c.get(Calendar.MONTH) + length);
            return c.getTime();
        } else if ((UnitType.compareToIgnoreCase("周") == 0)
                || (UnitType.compareToIgnoreCase("week") == 0)) {
            c.set(Calendar.WEEK_OF_MONTH, c.get(Calendar.WEEK_OF_MONTH)
                    + length);
            return c.getTime();
        } else if ((UnitType.compareToIgnoreCase("日") == 0)
                || (UnitType.compareToIgnoreCase("day") == 0)) {
            c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + length);
            return c.getTime();
        }
        return rdate;
    }

    /**
     * 添加一个单位
     *
     * @param sdate
     * @param UnitType
     * @return
     */
    public static Date increase(Date sdate, String UnitType) {
        return increase(sdate, UnitType, 1);
    }

    public static boolean isTime(String stime) {
        if (parse(stime) == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 根据开始时间和结束时间的标识，构造按照月来统计 To change the template for this
     */
    public static String composeDate(String pattern, String year, String month, String day, String type) throws Exception {
        int y = 0, m = 0, d = 0;
        String sy, sm, sd;
        try {
            Calendar c = Calendar.getInstance();
            y = Integer.parseInt(year);
            m = Integer.parseInt(month) - 1;
            if (day == null || day.trim().compareTo("") == 0) {
                if (type == "start") {
                    day = "1";
                } else if (type == "end") {
                    c.set(y, m, 1);
                    day = String.valueOf(c.getActualMaximum(Calendar.DAY_OF_MONTH));
                }
            }
            d = Integer.parseInt(day);
            c.set(y, m, d, 0, 0, 0);
            c.getTime();// let calendar to recalculate
            if (pattern == "YYYYMMDD") {
                sy = String.valueOf(c.get(Calendar.YEAR));
                sm = String.valueOf(c.get(Calendar.MONTH) + 1);
                if (sm.length() == 1) {
                    sm = "0" + sm;
                }
                sd = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
                if (sd.length() == 1) {
                    sd = "0" + sd;
                }
                return sy + sm + sd;
            }
            return "";
        } catch (Exception e) {
            throw new Exception("时间格式不正确");
        }
    }

    // 得到日期类型的第一天
    public static Date getFirst(Date sdate, String UnitType, String date) {
        return getFirst(sdate, UnitType, 1, Integer.parseInt(date));
    }

    // 得到日期类型的第一天
    public static Date getFirst(Date sdate, String UnitType, int num, int date) {
        try {
            sdate = increase(sdate, UnitType, num);
            Calendar c = Calendar.getInstance();
            c.setTime(sdate);
            if ((UnitType.compareToIgnoreCase("周") == 0)
                    || (UnitType.compareToIgnoreCase("week") == 0)) {
                c.set(Calendar.DAY_OF_WEEK, date);
                sdate = c.getTime();
            } else if ((UnitType.compareToIgnoreCase("月") == 0)
                    || (UnitType.compareToIgnoreCase("month") == 0)) {
                c.set(Calendar.DAY_OF_MONTH, date);
                sdate = c.getTime();
            }
        } catch (Exception ce) {

        }
        return sdate;
    }

    // 返回给定日期的月份,从0开始.
    public static int getMonth(Date sdate) {
        Calendar c = Calendar.getInstance();
        c.setTime(sdate);
        return c.get(Calendar.MONTH);
    }

    // 返回给定日期的天,从1开始.
    public static int getDate(Date sdate) {
        Calendar c = Calendar.getInstance();
        c.setTime(sdate);
        return c.get(Calendar.DATE);
    }

    public static Date setTime(Date sdate, String time) {
        if (time == null || time.indexOf(":") == -1) {
            return sdate;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(sdate);
        String[] tmp = time.split(":");
        c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(tmp[0]));
        c.set(Calendar.MINUTE, Integer.parseInt(tmp[1]));
        c.set(Calendar.SECOND, Integer.parseInt(tmp[2]));
        return c.getTime();
    }

    public static String parseDate(String date) {
        String year = "";
        String month = "";
        String day = "";
        String retDate = date;
        String temptimes[] = date.split("/");
        if (temptimes.length > 0) {
            if (temptimes.length == 3) {
                year = temptimes[2];
                month = temptimes[0];
                day = temptimes[1];
                retDate = year + "/" + month + "/" + day;
            }
        }
        return retDate;
    }

    /**
     * 获取时间差
     *
     * @param dateA
     * @param dateB
     * @return
     */
    public static long getMillSecondsBetweenDayNumber(String dateA, String dateB) {
        long dayNumber = 0;
        // 1小时=60分钟=3600秒=3600000
        long mins = 60L * 1000L; // 计算分钟之差
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1 = df.parse(dateA);
            Date d2 = df.parse(dateB);
            dayNumber = (d2.getTime() - d1.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dayNumber;
    }

    public static int[] getEveryMonthFirst() {
        int value[] = new int[12];
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < 12; i++) {
            c.set(Calendar.YEAR, c.get(Calendar.YEAR));
            c.set(Calendar.MONTH, i);
            value[i] = c.getActualMinimum(Calendar.DAY_OF_MONTH);
        }
        return value;
    }

    public static int[] getEveryMonthLast() {
        int value[] = new int[12];
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < 12; i++) {
            c.set(Calendar.YEAR, c.get(Calendar.YEAR));
            c.set(Calendar.MONTH, i);
            value[i] = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        return value;
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 获取传入时间的周一
     *
     * @param date 当前时间
     * @return 返回传入时间当周星期一
     */
    public static Date getNowWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -1); //解决周日会出现 并到下一周的情况
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * 查询当周周日
     *
     * @param date 当前时间
     * @return 当周周日
     */
    public static Date getNowWeekSunday(Date date) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, -1);
        c.add(Calendar.WEEK_OF_YEAR, 1);// 一周
        c.set(Calendar.DAY_OF_WEEK, 1);
        return c.getTime();
    }

    /**
     * 比较传入when是否相等在now之后
     * 按照年与日进行比较 不比较时分秒
     *
     * @param now  当前时间
     * @param when 比较时间
     * @return
     */
//    public static Boolean compareAfterDay(Date now, Date when) {
//        Date nowDate = org.apache.commons.lang.time.DateUtils.truncate(now, Calendar.DATE);
//        Date whenDate = org.apache.commons.lang.time.DateUtils.truncate(when, Calendar.DATE);
//        if (org.apache.commons.lang.time.DateUtils.isSameDay(nowDate, whenDate)) {
//            return Boolean.TRUE;
//        }
//        return whenDate.after(nowDate);
//    }

    /**
     * 比较传入when是否在now之后
     * 按照年与日进行比较 不比较时分秒
     *
//     * @param now  当前时间
//     * @param when 比较时间
     * @return
     */
//    public static Boolean compareDayAfter(Date now, Date when) {
//        Date nowDate = org.apache.commons.lang.time.DateUtils.truncate(now, Calendar.DATE);
//        Date whenDate = org.apache.commons.lang.time.DateUtils.truncate(when, Calendar.DATE);
//        return whenDate.after(nowDate);
//    }
    public static Date string2Date(String dateString) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                date = sdf.parse(dateString);
            } catch (Exception ex) {
                System.out.println(e.getMessage());
                date = new Date();
            }
        }

        return date;
    }

    /**
     * 时间转换
     *
     * @param time
     * @return
     */
    public static int changeTime(String time) {//3时9分14秒
        int index = 0;
        int hours = 0;
        int minute = 0;
        int second = 0;
        float total_time;
        if (time.contains("时")) {
            int n1 = time.indexOf("时");
            index = n1;
            hours = Integer.parseInt(time.substring(0, (n1 - 1)));
            hours = hours * 60 * 60;
        }
        if (time.contains("分")) {
            int n2 = time.indexOf("分");
            if (index == 0) {
                minute = Integer.parseInt(time.substring(index, n2));
            } else {
                minute = Integer.parseInt(time.substring(index + 1, n2));
            }
            index = n2;
            minute = minute * 60;
        }
        if (time.contains("秒")) {
            int n3 = time.indexOf("秒");
            if (index == 0) {
                second = Integer.parseInt(time.substring(index, n3));
            } else {
                second = Integer.parseInt(time.substring(index + 1, n3));
            }
        }
        total_time = hours + minute + second;
        return (int) total_time;
    }

    public static Date getDateMonthBefore(Date date, int month_num) {
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(calendar.MONTH, month_num); //设置为前3月
        dBefore = calendar.getTime(); //得到前3月的时间

        return dBefore;
    }


    public static long getAfterDayTime(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        return cal.getTimeInMillis();
    }

    /**
     * 比较两个时间
     *
     * @param DATE1
     * @param DATE2
     * @return
     */
    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat(DEFAULT_FORMAT);
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
//                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
//                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     *
     * @return
     */
    public static String timeStampToDate(long timestamp){
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
        String date = sdf.format(new Date(timestamp));
        return date;
    }

    public static String getBeforeMonth(int day){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, day);
//        System.out.println(new Date(cal.getTimeInMillis()));
        Date date = new Date(cal.getTimeInMillis());
        String d = sdf.format(date);
        return d;
    }


    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static void main(String[] args) {
//        System.out.println(getAfterDayTime(new Date(), 30));

        getBeforeMonth(30);
    }


}
