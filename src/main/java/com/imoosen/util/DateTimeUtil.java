package com.imoosen.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
public class DateTimeUtil {
    private static DateTimeUtil m_instance = null;
    public static String dateformat = "yyyy-MM-dd HH:mm:ss";
    public static String dateformat_yyyymmdd = "yyyy-MM-dd";

    public static synchronized DateTimeUtil getInstance() {
        if(m_instance == null) {
            m_instance = new DateTimeUtil();
        }

        return m_instance;
    }

    private DateTimeUtil() {
    }

    public static String getNowDateTime() {
        SimpleDateFormat format = new SimpleDateFormat(dateformat);
        return format.format(new Date());
    }

    public static String getNowDate() {
        SimpleDateFormat format = new SimpleDateFormat(dateformat_yyyymmdd);
        return format.format(new Date());
    }

    public Date formatStringToDate(String date, String dateformat) {
        if(date == null && date.length() == 0) {
            return null;
        } else if(dateformat == null && dateformat.length() == 0) {
            return null;
        } else {
            SimpleDateFormat format = new SimpleDateFormat(dateformat);

            try {
                return format.parse(date);
            } catch (ParseException var5) {
                return null;
            }
        }
    }

    public String formatDateToString(Date date, String dateformat) {
        if(date == null) {
            return null;
        } else if(dateformat == null && dateformat.length() == 0) {
            return null;
        } else {
            SimpleDateFormat format = new SimpleDateFormat(dateformat);
            return format.format(date);
        }
    }

    public Date addTime(Date date, Long time) {
        Date now = new Date();
        long afterTime = date.getTime() / 1000L + time.longValue();
        now.setTime(afterTime * 1000L);
        return now;
    }

    public Date reduceTime(Date date, Long time) {
        Date now = new Date();
        long afterTime = date.getTime() / 1000L - time.longValue();
        now.setTime(afterTime * 1000L);
        return now;
    }

    public Date reduceNowTime(Long time) {
        Date now = new Date();
        long afterTime = now.getTime() / 1000L - time.longValue();
        now.setTime(afterTime * 1000L);
        return now;
    }

    public Date addNowTime(Long time) {
        Date now = new Date();
        long afterTime = now.getTime() / 1000L + time.longValue();
        now.setTime(afterTime * 1000L);
        return now;
    }

    public boolean addNowTimeCompare(Date date, Long time) {
        Date now = new Date();
        long afterTime = date.getTime() / 1000L + time.longValue();
        date.setTime(afterTime * 1000L);
        return now.after(date);
    }

    public static Date getLastDate(int month) {
        Date current = getCurrentTime();
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(current);
        c.add(2, -month);
        return c.getTime();
    }

    public static Date getCurrentTime() {
        return new Date();
    }

    public static Date getCurrentDate() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.clear(10);
        c.clear(12);
        c.clear(13);
        c.clear(14);
        date = c.getTime();
        return date;
    }

    public static Date getDate(String d) {
        Timestamp ts = Timestamp.valueOf(d);
        return ts;
    }

    public static String getFormatDate(Date dt, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String sDate = formatter.format(dt);
        return sDate;
    }

    public static String getDateMonth(Date date) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy\'-\'MM\'-\'dd");
        format1.setLenient(false);
        String dateStr = format1.format(date);
        int begin = dateStr.indexOf(45) + 1;
        int end = dateStr.lastIndexOf(45);
        String month = dateStr.substring(begin, end);
        return month;
    }

    public static Date beginOfDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(11, 0);
        c.set(12, 0);
        c.set(13, 0);
        c.set(14, 0);
        date = c.getTime();
        return date;
    }

    public static Date endOfDay(Date date) {
        date = beginOfDay(date);
        return endOfDayByBeginOfDate(date);
    }

    public static Date endOfDayByBeginOfDate(Date date) {
        date = nextDay(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(14, -1);
        date = c.getTime();
        return date;
    }

    public static Date afterDaysSinceDate(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(5, days);
        date = c.getTime();
        return date;
    }

    public static boolean isTwoDatesInSameDay(Date date1, Date date2) {
        Date preDate1 = preDay(date1);
        Date nextDate1 = nextDay(date1);
        return date2.after(preDate1) && date2.before(nextDate1);
    }

    public static Date beginOfNextDay(Date date) {
        date = nextDay(date);
        return beginOfDay(date);
    }

    public static Date nextDay(Date date) {
        return addDay((Date)date, 1);
    }

    public static Date addDay(Date date, int d) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(5, d);
        date = c.getTime();
        return date;
    }

    public static Date addDay(String dateStr, int d) throws ParseException {
        Date date = getFormatDate(dateStr);
        return addDay(date, d);
    }

    public static Date preDay(Date date) {
        return subDay((Date)date, 1);
    }

    public static Date preDay(String dateStr) throws ParseException {
        return subDay((String)dateStr, 1);
    }

    public static Date subDay(Date date, int d) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(5, -d);
        date = c.getTime();
        return date;
    }

    public static Date subDay(String dateStr, int d) throws ParseException {
        Date date = getFormatDate(dateStr);
        return subDay(date, d);
    }

    public static Date addMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(2, 1);
        date = c.getTime();
        return date;
    }

    public static Date getLastDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(2, 1);
        c.set(5, 1);
        c.add(5, -1);
        date = c.getTime();
        return date;
    }

    public static Date getFirstDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(5, 1);
        date = c.getTime();
        return date;
    }

    public static boolean inTimeSegment(Date start, Date end, Date date) {
        start = preDay(start);
        end = nextDay(end);
        return date.after(start) && date.before(end);
    }

    public static boolean isCurrentDateInTimeSegment(Date start, Date end) {
        Date date = getCurrentDate();
        return inTimeSegment(start, end, date);
    }

    public static int betweenDaysInOneMonth(Date start, Date end) {
        String startStr = getFormatDate(start, "yyyyMMdd");
        String endStr = getFormatDate(end, "yyyyMMdd");
        int days = Integer.parseInt(endStr) - Integer.parseInt(startStr) + 1;
        return days;
    }

    public static int getBetweenDays(Date start, Date end) {
        if(start.after(end)) {
            return -1;
        } else {
            Calendar startC = Calendar.getInstance();
            startC.setTime(start);
            Calendar endC = Calendar.getInstance();
            endC.setTime(end);
            endC.add(6, 1);
            int days = 0;

            do {
                ++days;
                startC.add(6, 1);
            } while(startC.before(endC));

            return days - 1;
        }
    }

    public static int getBetweenDays(String start, String end) throws ParseException {
        return getBetweenDays(start, end, "yyyy-MM-dd");
    }

    public static int getBetweenDays(String start, String end, String pattern) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date startDate = format.parse(start);
        Date endDate = format.parse(end);
        return getBetweenDays(startDate, endDate);
    }

    public static int daysInMonth(Date date) {
        Date start = getFirstDayOfMonth(date);
        Date end = getLastDayOfMonth(date);
        int days = betweenDaysInOneMonth(start, end);
        return days;
    }

    public static boolean isTimeOverlap(Date start1, Date end1, Date start2, Date end2) {
        return inTimeSegment(start1, start2, end2) || inTimeSegment(end1, start2, end2);
    }

    public static int compare(String date1, String date2) throws ParseException {
        return compare((String)date1, (String)date2, (DateFormat)(new SimpleDateFormat("yyyy-MM-dd")));
    }

    public static int compare(String date1, String date2, String pattern) throws ParseException {
        return compare((String)date1, (String)date2, (DateFormat)(new SimpleDateFormat(pattern)));
    }

    public static int compareDay(Date date1, Date date2) throws ParseException {
        return compare(date1, date2, "yyyy-MM-dd");
    }

    public static int compare(Date date1, Date date2, String pattern) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return compare((String)format.format(date1), (String)format.format(date2), (DateFormat)format);
    }

    public static int compare(String date1, String date2, DateFormat format) throws ParseException {
        String curr_date1 = date1;
        String curr_date2 = date2;
        if(date1.indexOf("至今") > -1) {
            curr_date1 = format.format(new Date());
        }

        if(date2.indexOf("至今") > -1) {
            curr_date2 = format.format(new Date());
        }

        curr_date1 = curr_date1.replaceAll("/", "-");
        curr_date2 = curr_date2.replaceAll("/", "-");
        Date dt1 = format.parse(curr_date1);
        Date dt2 = format.parse(curr_date2);
        return dt1.getTime() > dt2.getTime()?1:(dt1.getTime() < dt2.getTime()?-1:0);
    }

    public static int compareCurrentTime(String formmartTimeString) {
        Calendar currentTime = Calendar.getInstance();
        Calendar comparedTime = (Calendar)currentTime.clone();
        comparedTime.set(1, Integer.parseInt(formmartTimeString.substring(0, 4)));
        comparedTime.set(2, Integer.parseInt(formmartTimeString.substring(4, 6)) - 1);
        comparedTime.set(5, Integer.parseInt(formmartTimeString.substring(6, 8)));
        int comparedResult = comparedTime.compareTo(currentTime);
        return comparedResult;
    }

    public static Date getDateFromFormattingString(String dateString) {
        Calendar currentTime = Calendar.getInstance();
        currentTime.set(1, Integer.parseInt(dateString.substring(0, 4)));
        currentTime.set(2, Integer.parseInt(dateString.substring(5, 7)) - 1);
        currentTime.set(5, Integer.parseInt(dateString.substring(8, 10)));
        currentTime.set(11, Integer.parseInt(dateString.substring(11, 13)));
        currentTime.set(12, Integer.parseInt(dateString.substring(14, 16)));
        currentTime.set(13, Integer.parseInt(dateString.substring(17, 19)));
        return currentTime.getTime();
    }

    public static Date getFormatDateStr(String str) {
        try {
            return getFormatDate(str);
        } catch (ParseException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static Date getFormatDate(String str) throws ParseException {
        return str != null && !"".equals(str)?(str.length() <= 10?getDateByString(str, "yyyy-MM-dd"):getDateByString(str, "yyyy-MM-dd HH:mm")):null;
    }

    public static Date getDateByString(String str, String pattern) throws ParseException {
        SimpleDateFormat df3 = new SimpleDateFormat();
        df3.applyPattern(pattern);
        return df3.parse(str);
    }

    public static Date addDate(Date date, int num) {
        return new Date(date.getTime() + (long)num * 24L * 3600L * 1000L);
    }

    public static Date createDate(Date date, Long hour) {
        Date result = null;
        if(date != null) {
            try {
                result = (new SimpleDateFormat("yyyy-MM-dd")).parse((new SimpleDateFormat("yyyy-MM-dd")).format(date));
            } catch (ParseException var4) {
                var4.printStackTrace();
            }
        }

        if(result != null && hour != null) {
            result.setHours(hour.intValue());
        }

        return result;
    }

    public static Date createDate(Date date, Long hour, Long minute) {
        Date result = null;
        if(date != null) {
            try {
                result = (new SimpleDateFormat("yyyy-MM-dd")).parse((new SimpleDateFormat("yyyy-MM-dd")).format(date));
            } catch (ParseException var5) {
                var5.printStackTrace();
            }
        }

        if(result != null) {
            if(hour != null) {
                result.setHours(hour.intValue());
            }

            if(minute != null) {
                result.setMinutes(minute.intValue());
            }
        }

        return result;
    }

    public static Date getSqlLessDate(Date resourceDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(resourceDate);
        c.add(5, 1);
        c.clear(10);
        c.clear(12);
        c.clear(13);
        c.clear(14);
        return c.getTime();
    }

    public static Date addDateIgnoreSaturdaySunday(Date date, long days) {
        if(date != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            int i = 0;

            while((long)i < days) {
                c.add(5, 1);
                if(c.get(7) != 7 && c.get(7) != 1) {
                    ++i;
                }
            }

            date = c.getTime();
        }

        return date;
    }

    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dat = format.format(new Date());
        System.out.println(dat);
        System.out.println((new Random()).nextInt(3));
    }
}
