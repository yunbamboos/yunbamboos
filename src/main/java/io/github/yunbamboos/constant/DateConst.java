package io.github.yunbamboos.constant;
/**
 * 日期格式常量
 * */
public final class DateConst {

    private DateConst(){}

    /**
     * 日期格式，年月日时分秒，年月日用横杠分开，时分秒用冒号分开
     * 例如：2005-05-10 23：20：00，2008-08-08 20:08:08
     */
    public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期格式，年月日时分秒毫秒，年月日用横杠分开，时分秒用冒号分开.毫秒
     * 例如：2005-05-10 23：20：00.000，2008-08-08 20:08:08.000
     */
    public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * 日期格式，年月日
     * 例如：20050510，20080808
     */
    public static final String DATE_TIME_FORMAT_YY_MM_DD = "yyyyMMdd";
    /**
     * 日期格式，年月日，年月日用横杠分开
     * 例如：2005-05-10，2008-08-08
     */
    public static final String DATE_TIME_FORMAT_YY_MM_DD1 = "yyyy-MM-dd";
    /**
     * 日期格式，年月，年月横杠分开
     * 例如：2005-05-10，2008-08-08
     */
    public static final String DATE_TIME_FORMAT_YY_MM = "yyyy-MM";
}