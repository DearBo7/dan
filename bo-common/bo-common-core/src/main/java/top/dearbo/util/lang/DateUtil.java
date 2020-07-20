package top.dearbo.util.lang;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Dan on 2017/7/21.
 */
public class DateUtil {

    /**
     * 英文 如：2010-12-01
     */
    public static final String FORMAT_SHORT = "yyyy-MM-dd";

    /**
     * 英文全称 如：2010-12-01 23:15:06
     */
    public static final String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间  23:15:06
     */
    public static final String FORMAT_TIME = "HH:mm:ss";

    private static final int DAY_SECOND_I = 24 * 60 * 60;

    private static final long DAY_SECOND = 24 * 60 * 60;

    /**
     * 毫秒转天
     */
    public static final long DAY_MILLI = 24 * 60 * 60 * 1000;

    /**
     * 毫秒转小时
     */
    public static final long HOUR_MILLI = 60 * 60 * 1000;

    /**
     * 毫秒转分钟
     */
    public static final long MINUTE_MILLI = 60 * 1000;

    /**
     * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 中文全称 如：2010年12月01日 23时15分06秒
     */
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日 HH时mm分ss秒";

    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒";

    /**
     * 时间戳
     */
    public static String FORMAT_FILE_CN = "yyyyMMddHHmmssSSS";

    /**
     * 年月
     */
    public static String FORMAT_YY_MM = "yyyy-MM";

    public static String FORMAT_YY = "yyyy";

    public static String FORMAT_MM = "MM";

    public static Date getDate() {
        return getDate(System.currentTimeMillis());
    }

    public static Date getDate(long millis) {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_LONG);
        String format1 = format.format(millis);
        return parseToDate(format1, FORMAT_LONG);
    }

    public static long getTime() {
        return System.currentTimeMillis();
    }

    public static String getTimeToString(String parse) {
        long millis = System.currentTimeMillis();
        if (StringUtils.isBlank(parse)) {
            parse = FORMAT_LONG;
        }
        SimpleDateFormat format = new SimpleDateFormat(parse);
        return format.format(millis);
    }

    /**
     * 日期换为短日期 时分秒 00:00:00
     *
     * @param date 时间
     * @return Date
     */
    public static Date formatShort(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_SHORT);
        String format = df.format(date) + " 00:00:00";
        return parseToDate(format, FORMAT_LONG);
    }

    /**
     * 获取日期年份
     *
     * @param date 日期
     * @return String类型年
     */
    public static String getYearString(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    /**
     * 获取日期年份
     *
     * @param date 日期
     * @return int 类型年
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 功能描述：返回月
     *
     * @param date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 功能描述：返回日
     *
     * @param date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 功能描述：返回小时
     *
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 功能描述：返回分
     *
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     *
     * @param date Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 功能描述：返回毫
     *
     * @param date 日期
     * @return 返回毫
     */
    public static long getMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取某年某月有多少天
     *
     * @param year  年
     * @param month 月
     */
    public static int getDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        //输入类型为int类型
        calendar.set(year, month, 0);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 使用预设格式将字符串转为Date yyyy-MM-dd
     *
     * @param strDate 时间字符串
     */
    public static Date parseToDate(String strDate) {

        return StringUtils.isBlank(strDate) ? null : parseToDate(strDate, FORMAT_SHORT);
    }

    /**
     * 使用预设格式将字符串转为Date yyyy-MM-dd HH:mm:ss
     *
     * @param strDate 时间字符串
     * @return Date
     */
    public static Date parseDateHMS(String strDate) {
        return StringUtils.isBlank(strDate) ? null : parseToDate(strDate, FORMAT_LONG);
    }

    /**
     * 使用参数Format将字符串转为Date
     *
     * @param strDate String时间字符串
     * @param pattern 格式
     * @return Date
     */
    public static Date parseToDate(String strDate, String pattern) {
        try {
            return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(pattern).parse(strDate);
        } catch (ParseException e) {
            System.out.println(strDate + "转换Date失败...格式:" + pattern);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Date转为字符串String
     *
     * @param date yyyy-MM-dd HH:mm:ss
     * @return 字符串
     */
    public static String parseToString(Date date) {

        return date == null ? "" : parseToString(date, FORMAT_LONG);
    }

    /**
     * 使用参数Date转为字符串String，指定时间格式
     *
     * @return 字符串
     */
    public static String parseToString(Date date, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            return "";
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 在日期上增/减 数天
     *
     * @return Date
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, n);
        return cal.getTime();
    }

    /**
     * 在日期上增/减 数天 默认格式 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate 时间字符串
     * @param n       天数
     * @return Date
     */
    public static Date addDay(String strDate, int n) {

        return addDay(strDate, n, FORMAT_LONG);

    }

    /**
     * 在日期上增/减 数天
     *
     * @param strDate 时间字符串
     * @param n       天数
     * @param pattern 转换格式
     * @return Date
     */
    public static Date addDay(String strDate, int n, String pattern) {

        return addDay(parseToDate(strDate, pattern), n);

    }

    /**
     * 在日期上增/减数个N月
     *
     * @return Date
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    /**
     * 在日期上增/减数个N年
     *
     * @return Date
     */
    public static Date addYear(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //当前时间减去一年，即一年前的时间
        cal.add(Calendar.YEAR, n);
        return cal.getTime();
    }

    /**
     * 指定日期加/减N小时
     *
     * @param date 日期
     * @param hour 小时
     * @return Date
     */
    public static Date addHour(Date date, int hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR, hour);
        return c.getTime();
    }

    /**
     * 获取时间差 毫秒  默认格式 yyyy-MM-dd HH:mm:ss
     *
     * @param strStartDate 开始时间
     * @param strEndDate   结束时间
     * @return 毫秒数
     */
    public static Long getMistiming(String strStartDate, String strEndDate) {

        return getMistiming(strStartDate, strEndDate, FORMAT_LONG);
    }

    /**
     * 获取时间差 毫秒
     *
     * @param strStartDate 开始时间
     * @param strEndDate   结束时间
     * @param pattern      时间格式
     * @return 毫秒数
     */
    public static Long getMistiming(String strStartDate, String strEndDate, String pattern) {

        return getMistiming(parseToDate(strStartDate, pattern), parseToDate(strEndDate, pattern));
    }

    /**
     * 时间差 默认格式 yyyy-MM-dd HH:mm:ss
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 时间差
     */
    public static Long getMistiming(Date startDate, Date endDate) {

        return (endDate.getTime() - startDate.getTime());
    }

    /**
     * 得到两个时间差 小时数 默认格式 yyyy-MM-dd HH:mm:ss
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 时间差
     */
    public static long getMistimingHour(Date startDate, Date endDate) {
        return getMistiming(startDate, endDate) / HOUR_MILLI;
    }

    /**
     * 得到两个时间差 小时数 默认格式 yyyy-MM-dd HH:mm:ss
     *
     * @param strStartDate 开始时间
     * @param strEndDate   结束时间
     * @return 时间差
     */
    public static long getMistimingHour(String strStartDate, String strEndDate) {
        return getMistiming(strStartDate, strEndDate) / HOUR_MILLI;
    }

    /**
     * 计算2个日期间的相差天数
     *
     * @param date1 较大的时间
     * @param date2 较小的时间
     * @return 相差天数
     */
    public static long diffDay(Date date1, Date date2) {
        return ((date1.getTime() - date2.getTime()) / DAY_MILLI);
    }

    /**
     * 获取一个时间段所有日期-(天数)
     * 开始日期-结束日期(小于/等于)
     * <pre>
     *     (2019-11-15 10:00:10,2019-11-30 23:59:59)==>2019-11-15 00:00:00->2019-11-30 00:00:00
     * </pre>
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return List<Date>
     */
    public static List<Date> getDatesByDay(Date beginDate, Date endDate) {
        return getDates(beginDate, endDate, Calendar.DAY_OF_MONTH, 1);
    }

    /**
     * 获取一个时间段所有日期-(月)
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return List<Date>
     */
    public static List<Date> getDatesByMonth(Date beginDate, Date endDate) {
        return getDates(beginDate, endDate, Calendar.MONTH, 1);
    }

    /**
     * 获取一个时间段所有日期-(天数)
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @param format    时间格式
     * @return List<String>
     */
    public static List<String> getDatesByDayString(Date beginDate, Date endDate, String format) {
        return getDatesString(beginDate, endDate, Calendar.DAY_OF_MONTH, 1, format);
    }

    /**
     * 获取一个时间段所有日期-(月)
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @param format    时间格式
     * @return List<String>
     */
    public static List<String> getDatesByMonthString(Date beginDate, Date endDate, String format) {
        return getDatesString(beginDate, endDate, Calendar.MONTH, 1, format);
    }

    /**
     * 获取一个时间段指定类型所有日期
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @param field     增加的类型 (年/月/日等)  Calendar.DAY_OF_MONTH  (日)
     * @param amount    增加的时间量             1
     * @return List<Date>                       开始时间-结束时间(所有天数)
     */
    public static List<Date> getDates(Date beginDate, Date endDate, int field, int amount) {
        List<Date> returnDate = new ArrayList<Date>();
        returnDate.add(beginDate);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(beginDate);
        // 测试此日期是否在指定日期之后
        while (endDate.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(field, amount);
            //处理有可能,还没有加之前开始时间小于结束日期,增加后就大于结束日期了
            if (calBegin.getTime().getTime() <= endDate.getTime()) {
                returnDate.add(calBegin.getTime());
            }
        }
        return returnDate;
    }

    /**
     * 获取一个时间段指定类型所有日期
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @param field     增加的类型 (年/月/日等)  Calendar.DAY_OF_MONTH  (日)
     * @param amount    增加的时间量             1
     * @param format    日期格式 默认：yyyy-MM-dd
     * @return List<String>                       开始时间-结束时间(所有天数)
     */
    public static List<String> getDatesString(Date beginDate, Date endDate, int field, int amount, String format) {
        List<String> returnDateStr = new ArrayList<String>();
        SimpleDateFormat sd = new SimpleDateFormat(format == null ? FORMAT_SHORT : format);
        returnDateStr.add(sd.format(beginDate));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(beginDate);
        // 测试此日期是否在指定日期之后
        while (endDate.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(field, amount);
            if (calBegin.getTime().getTime() <= endDate.getTime()) {
                returnDateStr.add(sd.format(calBegin.getTime()));
            }
        }
        return returnDateStr;
    }

    /**
     * 获取当月第一天
     *
     * @param currentDate 日期
     * @return 第一天
     */
    public static Date getDateFirstDay(Date currentDate) {
        if (currentDate == null) {
            return null;
        }
        return getTimeFirstDay(getYear(currentDate), getMonth(currentDate));
    }

    /**
     * 获取当月第一天
     *
     * @param currentYear  年
     * @param currentMonth 月
     * @return Date
     */
    public static Date getTimeFirstDay(Integer currentYear, Integer currentMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(currentYear, currentMonth, 1, 0, 0, 0);
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        //毫秒
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取当月最后一天
     *
     * @param currentDate 日期
     * @return 最后一天
     */
    public static Date getDateLastDay(Date currentDate) {
        if (currentDate == null) {
            return null;
        }
        return getTimeLastDay(getYear(currentDate), getMonth(currentDate));
    }

    /**
     * 获取当月最后一天
     *
     * @param currentYear  年
     * @param currentMonth 月
     * @return Date
     */
    public static Date getTimeLastDay(Integer currentYear, Integer currentMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(currentYear, currentMonth, 1, 23, 59, 59);
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 增加减少 N月,获取第一天
     *
     * @param date 当前时间
     * @param n    月数
     */
    public static Date addMonthOneDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        cal.set(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH) + 1), 1, 0, 0, 0);
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 增加减少 N月,获取最后一天
     *
     * @param date 当前时间
     * @param n    月数
     */
    public static Date addMonthLastDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        cal.set(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH) + 1), 1, 23, 59, 59);
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 获得当前最大时间 23:59:59
     *
     * @param date
     */
    public static Date getEndOfDay(Date date) {
        if (date == null) {
            return date;
        }
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获得当前最小时间 00:00:00
     *
     * @param date
     */
    public static Date getStartOfDay(Date date) {
        if (date == null) {
            return date;
        }
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 判断两个日期是否相同
     */
    public static Boolean equalsDate(String strStartDate, String strEndDate) {
        if (StringUtils.isBlank(strStartDate) || StringUtils.isBlank(strEndDate)) {
            return false;
        }
        return equalsDate(DateUtil.parseToDate(strStartDate), DateUtil.parseToDate(strEndDate));
    }

    /**
     * 判断两个日期是否相同
     */
    public static Boolean equalsDate(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return false;
        }
        /*String strStartDate = parseToString(startDate, DateUtil.FORMAT_SHORT);
        String strEndDate = parseToString(endDate, DateUtil.FORMAT_SHORT);
        return strStartDate.equals(strEndDate);*/
        LocalDate localDate1 = ZonedDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = ZonedDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault()).toLocalDate();
        return localDate1.isEqual(localDate2);
    }

    /**
     * 日期转化为cron表达式
     *
     * @param date 日期
     * @return Cron表达式
     */
    public static String parseDateToCron(Date date) {
        String dateFormat = "ss mm HH dd MM ? yyyy";
        return parseToString(date, dateFormat);
    }

    /**
     * Cron表达式转为日期
     *
     * @param cron Cron表达式
     * @return
     */
    public static Date parsetCronToDate(String cron) {
        String dateFormat = "ss mm HH dd MM ? yyyy";

        return parseToDate(cron, dateFormat);
    }

}