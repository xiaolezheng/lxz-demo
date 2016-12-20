package com.lxz.xxx.common.util;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

@Slf4j
public class DateUtil {
    /**
     * 格式化日期工具类,不要自己随便创建工具类,直接引用这个就可以
     */
    public static FastDateFormat YYYY = FastDateFormat.getInstance("yyyy");
    public static FastDateFormat YYYY_MM = FastDateFormat.getInstance("yyyy-MM");
    public static FastDateFormat YYYY_MM_DD = FastDateFormat.getInstance("yyyy-MM-dd");
    public static FastDateFormat YYYY_MM_DD_HHMMSS = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    public static FastDateFormat HHMMSS = FastDateFormat.getInstance("HH:mm:ss");
    public static FastDateFormat YYYYMMDD = FastDateFormat.getInstance("yyyyMMdd");
    public static FastDateFormat YYYY_MM_DD$ = FastDateFormat.getInstance("yyyy/MM/dd");
    public static FastDateFormat YYYYMMDDHHMMSS = FastDateFormat.getInstance("yyyyMMddHHmmss");

    private static final Map<String, Integer> truncateFieldMap = Maps.newHashMap();
    private static final Map<String, FastDateFormat> cache = Maps.newHashMap();

    static {
        cache.put("yyyy", YYYY);
        cache.put("yyyy-MM", YYYY_MM);
        cache.put("yyyy-MM-dd", YYYY_MM_DD);
        cache.put("yyyy-MM-dd HH:mm:ss", YYYY_MM_DD_HHMMSS);
        cache.put("HH:mm:ss", HHMMSS);
        cache.put("yyyyMMdd", YYYYMMDD);
        cache.put("yyyyMMddHHmmss", YYYYMMDDHHMMSS);
        cache.put("yyyy/MM/dd", YYYY_MM_DD$);
    }

    static {
        truncateFieldMap.put("yyyy", Calendar.YEAR);
        truncateFieldMap.put("yyyy-MM", Calendar.MONTH);
        truncateFieldMap.put("yyyy-MM-dd", Calendar.DAY_OF_MONTH);
        truncateFieldMap.put("yyyy-MM-dd HH:mm:ss", Calendar.SECOND);
        truncateFieldMap.put("HH:mm:ss", Calendar.SECOND);
        truncateFieldMap.put("yyyyMMdd", Calendar.DAY_OF_MONTH);
        truncateFieldMap.put("yyyyMMddHHmmss", Calendar.SECOND);
        truncateFieldMap.put("yyyy/MM/dd", Calendar.DAY_OF_MONTH);
    }

    public static FastDateFormat getInstance(String formatPattern) {
        if (StringUtils.isNotEmpty(formatPattern)) {
            FastDateFormat fastDateFormat = cache.get(formatPattern);
            if (fastDateFormat != null) {
                return fastDateFormat;
            }
        }

        return YYYY_MM_DD;
    }

    public static int getTruncateField(String formatPattern) {
        if (StringUtils.isNotEmpty(formatPattern)) {
            Integer truncateField = truncateFieldMap.get(formatPattern);
            if (truncateField != null) {
                return truncateField;
            }
        }

        return Calendar.DAY_OF_MONTH;
    }

    public static int daysOfTwo(Date toDate, Date fromDate) {
        Calendar calStart = Calendar.getInstance();
        Calendar calEnd = Calendar.getInstance();

        calStart.setTime(fromDate);
        calEnd.setTime(toDate);

        //设置时间为0时
        calStart.set(Calendar.HOUR_OF_DAY, 0);
        calStart.set(Calendar.MINUTE, 0);
        calStart.set(Calendar.SECOND, 0);
        calEnd.set(Calendar.HOUR_OF_DAY, 0);
        calEnd.set(Calendar.MINUTE, 0);
        calEnd.set(Calendar.SECOND, 0);
        //得到两个日期相差的天数
        int days = (int) ((calEnd.getTime().getTime() - calStart.getTime().getTime()) / 1000 / 3600 / 24);

        return days;
    }

    public static int yearsOfTwo(Date toDate, Date fromDate) {
        float ONE_YEAR_DAYS = 365f;
        int days = daysOfTwo(toDate, fromDate);
        return (int) Math.ceil(days / ONE_YEAR_DAYS);
    }

    public static Date fewDaysToDate(Date date, int days) {
        Calendar now = Calendar.getInstance(getTimeZone());
        now.setTime(date);
        now.add(Calendar.DATE, days);
        return now.getTime();
    }

    /**
     * 是否为周六周日
     *
     * @param date
     * @return
     */
    public static boolean isOffDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Date date1 = new Date();
        Date date2 = org.apache.commons.lang3.time.DateUtils.addDays(date1, 365);
        System.out.println(daysOfTwo(date2, date1));

        System.out.println(org.apache.commons.lang3.time.DateUtils.truncate(date1, Calendar.DAY_OF_MONTH));

        System.out.println(DateUtil.yearsOfTwo(date2, date1));

        Duration duration = Duration.between(date1.toInstant(), date2.toInstant());

        System.out.println(duration.toDays());

    }

    /**
     * 获取主机所在时区
     *
     * @return
     */
    public static TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }
}
