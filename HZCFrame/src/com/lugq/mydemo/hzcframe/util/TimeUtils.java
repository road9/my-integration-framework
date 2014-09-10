package com.lugq.mydemo.hzcframe.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * dispose time class.
 * @ClassName: TimeUtils
 * @author lugq
 * @date 2014��9��10�� ����5:59:01
 *
 */
public class TimeUtils {
    public static String calendarFormat(Calendar calendar) {
        if (calendar == null)
            return null;
        Date date = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public static int getDate(String date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(df.parse(date));
        }
        catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public static long getDateMillisecond(String date) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long millis = 0;
        try {
            Date date1 = format1.parse(date);
            millis = date1.getTime();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return millis;
    }

    public static String showSportsEndTime(long millis) {
        SimpleDateFormat format1 = new SimpleDateFormat("MM-dd HH:mm");
        return format1.format(millis);
    }

    /**
     * ��ȡ�����ĵ���ʱʱ����ʾ
     * 
     * @param millis ʱ���ܺ�
     * @return ��ʾ�ĵ���ʱ��ʽ
     */
    public static String getNormalCountDownTime(long millis) {
        String betLastTime;
        int seconds = (int) millis / 1000;
        int minutes = seconds / 60;
        int hours = minutes / 60;
        int day = hours / 24;
        seconds %= 60;
        minutes %= 60;
        hours %= 24;
        day %= 24;
        betLastTime = day + "��" + hours + "ʱ";
        if (minutes < 10)
            betLastTime += "0";
        betLastTime += minutes + "��";
        if (seconds < 10)
            betLastTime += "0";
        betLastTime += seconds + "��";
        return betLastTime;
    }

    /**
     * ��ȡ�򵥵ĵ���ʱʱ����ʾ
     * 
     * @param millis ʱ���ܺ�
     * @return ��ʾ�ĵ���ʱ��ʽ
     */
    public static String getSimpleCountDownTime(long millis) {
        String betLastTime = "";
        int seconds = (int) millis / 1000;
        int minutes = seconds / 60;
        int hours = minutes / 60;
        int day = hours / 24;
        seconds %= 60;
        minutes %= 60;
        hours %= 24;
        day %= 24;
        if (day != 0) {
            betLastTime = day + "��" + hours + "ʱ";
            if (minutes < 10)
                betLastTime += "0";
            betLastTime += minutes + "��";
            if (seconds < 10)
                betLastTime += "0";
            betLastTime += seconds + "��";
        }
        else {
            if (hours != 0) {
                betLastTime = hours + "ʱ";
                if (minutes < 10)
                    betLastTime += "0";
                betLastTime += minutes + "��";
                if (seconds < 10)
                    betLastTime += "0";
                betLastTime += seconds + "��";
            }
            else {
                if (minutes != 0) {
                    if (minutes < 10)
                        betLastTime += "0";
                    betLastTime += minutes + "��";
                    if (seconds < 10)
                        betLastTime += "0";
                    betLastTime += seconds + "��";
                }
                else {
                    if (seconds < 10)
                        betLastTime = "0";
                    betLastTime += seconds + "��";
                }
            }
        }
        return betLastTime;
    }

    /**
     * ��ȡ���Ի��ĵ���ʱʱ����ʾ
     * 
     * @param millis ʱ���ܺ�
     * @return ��ʾ�ĵ���ʱ��ʽ
     */
    private static StringBuilder betLastTime;

    public static String getCountDownTime(long millis) {
        if (betLastTime == null) {
            betLastTime = new StringBuilder();
        }
        betLastTime.delete(0, betLastTime.length());
        int seconds = (int) millis / 1000;
        int minutes = seconds / 60;
        int hours = minutes / 60;
        int day = hours / 24;
        seconds %= 60;
        minutes %= 60;
        hours %= 24;
        day %= 24;
        if (day != 0) {
            betLastTime.append(day + "��" + hours + "Сʱ");
        }
        else {
            if (hours != 0) {
                betLastTime.append(hours + "Сʱ");
                if (minutes < 10)
                    betLastTime.append("0");
                betLastTime.append(minutes + "��");
            }
            else {
                if (minutes < 10)
                    betLastTime.append("0");
                betLastTime.append(minutes + "��");
                if (seconds < 10)
                    betLastTime.append("0");
                betLastTime.append(seconds + "��");
            }
        }
        return betLastTime.toString();
    }

    /**
     * ��ʾ�����ʱ���ʽ
     * 
     * @param adateStrteStr
     * @param format ԭʼ�ĸ�ʽ����"yyyy-MM-dd HH:mm:ss"
     * @return ���ض�Ӧ��ʱ���ʽ�ַ���
     */
    public static String stringDate(String adateStrteStr, String format) {
        Date date = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            date = simpleDateFormat.parse(adateStrteStr);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
            return dateFormat.format(date);
        }
        catch (Exception ex) {

        }
        return null;
    }

    /**
     * ת��ʱ���ʽ
     * 
     * @param adateStrteStr ԭʼʱ���ַ���
     * @param oldFormat ԭʼʱ���ʽ
     * @param format ת���ɵ�ʱ���ʽ
     * @return
     */
    public static String convertDate(String adateStrteStr, String oldFormat, String format) {
        Date date = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(oldFormat);
            date = simpleDateFormat.parse(adateStrteStr);
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date);
        }
        catch (Exception ex) {

        }
        return null;
    }

    /**
     * string��ʽ������ת��ΪData����
     * 
     * @param date
     * @return
     */
    public static Date stringConvertToDate(String date) {
        Date toDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            toDate = dateFormat.parse(date);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return toDate;
    }

    /**
     * ������Ӧ�������ܼ�
     * 
     * @param date
     * @return
     */
    public static String dayOfWeek(Date date) {
        String[] weekDays = {"����", "��һ", "�ܶ�", "����", "����", "����", "����"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
}
