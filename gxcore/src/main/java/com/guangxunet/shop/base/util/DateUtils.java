package com.guangxunet.shop.base.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理日期的工具
 * Created by Administrator on 2016/8/28. 
 */
public class DateUtils {

		private static  final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);
		/**
		 * 时间转换为yyyy-MM-dd HH:mm:ss格式的字符串
		 * 
		 * @param date
		 * @return
		 */
		public static String dateToString(Date date) {
			if (null == date)
				return "";

			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		}

		/**
		 * 时间转换为yyyy-MM-dd格式的字符串
		 * 
		 * @param date
		 * @return
		 */
		public static String dateToString1(Date date) {
			if (null == date)
				return "";

			return new SimpleDateFormat("yyyy-MM-dd").format(date);
		}

		/**
		 * 时间转换为yyyy/MM/dd HH:mm:ss格式的字符串
		 * 
		 * @param date
		 * @return
		 */
		public static String dateToString2(Date date) {
			if (null == date)
				return "";

			return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
		}

		/**
		 * 时间转换为yyyyMMdd格式的字符串
		 * 
		 * @param date
		 * @return
		 */
		public static String simple(Date date) {
			if (null == date)
				return "";

			return new SimpleDateFormat("yyyyMMdd").format(date);
		}

		/**
		 * 时间转换为yyyyMMddHHmmss格式的字符串
		 * 
		 * @param date
		 * @return
		 */
		public static String simple2(Date date) {
			if (null == date)
				return "";

			return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
		}
		
		/**
		 * 时间转换为yyyyMMdd-HHmmss格式的字符串
		 * 
		 * @param date
		 * @return
		 */
		public static String simple3() {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmm");
			return format.format(new Date());
		}

		public static Date strToDate(String dateString) {
			if (null == dateString)
				return new Date();

			try {
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
			} catch (ParseException e) {
				return new Date();
			}
		}

		public static Date strToYYMMDDDate(String dateString) {
			if (null == dateString)
				return new Date();

			try {
				return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
			} catch (ParseException e) {
				return new Date();
			}
		}
		
		public static String strToDateByFormat(String dateString,String format) {
			

			try {
				Date transferDate = new SimpleDateFormat(format).parse(dateString);
				String returnDate = simple(transferDate);
				return returnDate;
			} catch (ParseException e) {
				return null;
			}
		}

		// 获取系统时间并返回时间格式
		public static Date currentDate() {
			DateFormat YYYY_MM_DD_MM_HH_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			try {
				String currentDates = YYYY_MM_DD_MM_HH_SS.format(new Date());

				return YYYY_MM_DD_MM_HH_SS.parse(currentDates);
			} catch (ParseException e) {
				return new Date();
			}
		}

		/**
		 * 得到当前时间距2013-11-01 00:00:00的小时数
		 * 
		 * @return
		 * @throws ParseException
		 */
		public long getHours() {
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = null;
			try {
				date = simple.parse("2013-11-01 00:00:00");
			} catch (ParseException e) {
				LOGGER.error("得到当前时间距2013-11-01 00:00:00的小时数",e);
			}
			long millisecond = System.currentTimeMillis() - date.getTime();
			long temp = 1000 * 60 * 60;
			return millisecond / temp;
		}

		/**
		 * 计算两个日期之间相差的天数
		 * 
		 * @param smdate
		 *            较小的时间
		 * @param bdate
		 *            较大的时间
		 * @return 相差天数
		 * @throws ParseException
		 */
		public static int daysBetween(Date smdate, Date bdate) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				smdate = sdf.parse(sdf.format(smdate));
			} catch (ParseException e) {
				LOGGER.error("sdf.parse()",e);
			}
			try {
				bdate = sdf.parse(sdf.format(bdate));
			} catch (ParseException e) {
				LOGGER.error("sdf.parse()",e);
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);

			return Integer.parseInt(String.valueOf(between_days));
		}

		/**
		 * 计算两个时间之间相差的天数
		 * 
		 * @param startDate
		 * @param endDate
		 * @return
		 */
		public static long diffDays(Date startDate, Date endDate) {
			long days = 0;
			long start = startDate.getTime();
			long end = endDate.getTime();
			// 一天的毫秒数1000 * 60 * 60 * 24=86400000
			days = (end - start) / 86400000;
			return days;
		}


		/**
		 * 计算两个时间之间相差的分钟数
		 * 
		 * @param startDate
		 * @param endDate
		 * @return
		 */
		public static long diffMinutes(Date startDate, Date endDate) {
			return (endDate.getTime() - startDate.getTime()) / 60000;
		}

		/**
		 * 日期加上月数的时间
		 * 
		 * @param date
		 * @param month
		 * @return
		 */
		public static Date dateAddMonth(Date date, int month) {
			return add(date, Calendar.MONTH, month);
		}

		/**
		 * 日期加上天数的时间
		 * 
		 * @param date
		 * @param month
		 * @return
		 */
		public static Date dateAddDay(Date date, int day) {
			return add(date, Calendar.DAY_OF_YEAR, day);
		}

		/**
		 * 日期加上年数的时间
		 * 
		 * @param date
		 * @param year
		 * @return
		 */
		public static Date dateAddYear(Date date, int year) {
			return add(date, Calendar.YEAR, year);
		}

		/**
		 * 计算剩余时间 (多少天多少时多少分)
		 * 
		 * @param startDateStr
		 * @param endDateStr
		 * @return
		 */
		public static String remainDateToString(Date startDate, Date endDate) {
			StringBuilder result = new StringBuilder();
			if (endDate == null) {
				return "过期";
			}
			long times = endDate.getTime() - startDate.getTime();
			if (times < -1) {
				result.append("过期");
			} else {
				long temp = 1000 * 60 * 60 * 24;
				// 天数
				long d = times / temp;

				// 小时数
				times %= temp;
				temp /= 24;
				long m = times / temp;
				// 分钟数
				times %= temp;
				temp /= 60;
				long s = times / temp;

				result.append(d);
				result.append("天");
				result.append(m);
				result.append("小时");
				result.append(s);
				result.append("分");
			}
			return result.toString();
		}

		/**
		 * 增加日期 (多少天多少时多少分)
		 * 
		 * @param date 输入日期
		 * @param type(Calendar.DATE,Calendar.MONTH,Calendar.YEAR)
		 * @return
		 */
		public static Date add(Date date, int type, int value) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(type, value);
			return calendar.getTime();
		}

		/**
		 * @MethodName: getLinkUrl
		 * @Param: DateUtil flag ： true 转换 false 不转换
		 * @Author: gang.lv
		 * @Date: 2013-5-8 下午02:52:44
		 * @Return:
		 * @Descb:
		 * @Throws:
		 */
		public static String getLinkUrl(boolean flag, String content, String id) {
			if (flag) {
				content = "<a href='finance.do?id=" + id + "'>" + content + "</a>";
			}
			return content;
		}

		/**
		 * 时间转换为时间戳
		 * 
		 * @param format
		 * @param date
		 * @return
		 * @throws ParseException
		 */
		public static long getTimeCur(String format, String date) throws ParseException {
			SimpleDateFormat sf = new SimpleDateFormat(format);
			return sf.parse(sf.format(date)).getTime();
		}

		/**
		 * 时间转换为时间戳
		 * 
		 * @param format
		 * @param date
		 * @return
		 * @throws ParseException
		 */
		public static long getTimeCur(String format, Date date) throws ParseException {
			SimpleDateFormat sf = new SimpleDateFormat(format);
			return sf.parse(sf.format(date)).getTime();
		}

		/**
		 * 将时间戳转为字符串
		 * 
		 * @param cc_time
		 * @return
		 */
		public static String getStrTime(String cc_time) {
			String re_StrTime = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
			long lcc_time = Long.valueOf(cc_time);
			re_StrTime = sdf.format(new Date(lcc_time * 1000L));
			return re_StrTime;
		}

		public static int getAge(Date birthday) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(birthday);
			Calendar nowDate = Calendar.getInstance();

			return nowDate.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
		}

		/**
		 * 当前时间减去分钟数得到的时间
		 * 
		 * @param minutes
		 * @return
		 * @throws ParseException
		 */
		public static String getDateMinusMinutes(int minutes) throws ParseException {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date nowTime = new Date();
			String datetest = formatter.format(nowTime);
			Date date = formatter.parse(datetest);
			long Time1 = (date.getTime() / 1000) - 60 * minutes;// 减去30分
			date.setTime(Time1 * 1000);
			return formatter.format(date);
		}

		/**
		 * 和当前时间比较是否在给定的时长内
		 * 
		 * @param validTime
		 *            给定的时间
		 * @param time
		 *            给定的时长（s）
		 * @return true 有效 false 无效
		 */
		public static boolean inValidTime(Date validTime, int time) {
			long currTime = System.currentTimeMillis();
			long valid = validTime.getTime();

			return ((currTime - valid) < time * 1000);
		}

		/**
		 * 获取年
		 * 
		 * @param time
		 * @return
		 */
		public static int getYear(Date time) {
			if (time == null) {
				return -1;
			}

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(time);

			return calendar.get(Calendar.YEAR);
		}

		/**
		 * 获取月
		 * 
		 * @param time
		 * @return
		 */
		public static int getMonth(Date time) {
			if (time == null) {
				return -1;
			}

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(time);

			return calendar.get(Calendar.MONTH) + 1;
		}

		/**
		 * 获取日
		 * 
		 * @param time
		 * @return
		 */
		public static int getDay(Date time) {
			if (time == null) {
				return -1;
			}

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(time);

			return calendar.get(Calendar.DAY_OF_MONTH);
		}

		/**
		 * 获取年
		 * 
		 * @return
		 */
		public static int getYear() {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());

			return calendar.get(Calendar.YEAR);
		}

		/**
		 * 获取月
		 * 
		 * @return
		 */
		public static int getMonth() {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());

			return calendar.get(Calendar.MONTH) + 1;
		}

		/**
		 * 获取日
		 * 
		 * @return
		 */
		public static int getDay() {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());

			return calendar.get(Calendar.DAY_OF_MONTH);
		}

		/**
		 * 将yyyy-MM-dd拼接成yyyy-MM-dd :HH:mm:ss
		 * 
		 * @param startDateStr
		 * @return
		 */
		public static Date strDateToStartDate(String startDateStr) {

			return DateUtils.strToDate(startDateStr + " 00:00:00");
		}

		/**
		 * 将yyyy-MM-dd拼接成yyyy-MM-dd :HH:mm:ss
		 * 
		 * @param startDateStr
		 * @return
		 */
		public static Date strDateToEndDate(String endDateStr) {

			return DateUtils.strToDate(endDateStr + " 23:59:59");
		}

		/**
		 * 当前时间减去天数得到的时间
		 * 
		 * @param minutes
		 * @return
		 * @throws ParseException
		 */
		public static Date getDateMinusDay(int day) {
			Date time = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(time);
			c.set(Calendar.DATE, c.get(Calendar.DATE) - day);
			return c.getTime();
		}

		public static String getDate() {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			return format.format(new Date());
		}

		/**
		 * 获取输入日期当天最后一毫秒的时间
		 * 
		 * @param date
		 * @return
		 */
		public static Date getDayEnd(Date date) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 999);
			return cal.getTime();
		}
		
		/**
		 * 获取输入日期当月最后一毫秒的时间
		 * 
		 * @param date
		 * @return
		 */
		public static Date getMonthLastDay(Date date) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
//			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 999);
			return cal.getTime();
		}
		
		/**
		 * 获取输入日期当前月第一天
		 * @return
		 */
		public static Date  getFirstDayOfMonth(Date date) {
			Calendar cal = Calendar.getInstance();    
			cal.setTime(date);
			cal.add(Calendar.MONTH, 0);
	        cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
	        cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
	        return cal.getTime();
		}

		/**
		 * 判断2个日期是否是同一天
		 * 
		 * @param date1
		 * @param date2
		 * @return true 同一天<br/>
		 *         false不是同一天
		 */
		public static boolean isSameDate(Date date1, Date date2) {
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date1);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date2);
			if (cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR)) {
				return false;
			}
			if (cal1.get(Calendar.MONTH) != cal2.get(Calendar.MONTH)) {
				return false;
			}
			if (cal1.get(Calendar.DATE) != cal2.get(Calendar.DATE)) {
				return false;
			}

			return true;
		}
		
		/**
		 * 获取当前月第一天
		 * @return
		 */
		public static String  getFirstDayOfMonth() {
			Calendar c = Calendar.getInstance();    
	        c.add(Calendar.MONTH, 0);
	        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
	       
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	        String firstDay = format.format(c.getTime());
	        return firstDay;
		}
		
		/**
		 * 获取上个月第一天
		 * 
		 * @return
		 */
		public static String  getFirstDayOfLastMonth() {
			Calendar c = Calendar.getInstance();    
	        c.add(Calendar.MONTH, -1);
	        c.set(Calendar.DAY_OF_MONTH,1); 
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	        String firstDay = format.format(c.getTime());
	        return firstDay;
		}
		
		/**
		 * 获取上个月最后一天
		 * 
		 * @return
		 */
		public static String  getLastDayOfLastMonth() {
			Calendar c = Calendar.getInstance();    
	        c.add(Calendar.MONTH, -1);
	        c.set(Calendar.DAY_OF_MONTH,c.getActualMaximum(Calendar.DAY_OF_MONTH)); 
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	        String lastDay = format.format(c.getTime());
	        return lastDay;
		}
		
		/**
		 * 获取上个月最后一天
		 * 
		 * @return
		 */
		public static List<String> getDates(Calendar p_start, Calendar p_end) {
	        List<String> result = new ArrayList<String>();
	        Calendar temp = (Calendar) p_start.clone();
	        result.add(dateToString1(temp.getTime()));
	        temp.add(Calendar.DAY_OF_YEAR, 1);
	        while (temp.before(p_end)) {
	            result.add(dateToString1(temp.getTime()));
	            temp.add(Calendar.DAY_OF_YEAR, 1);
	        }
	        return result;
	    }
		
		/**
		 * 获取输入时间的零时零分零秒
		 * @return
		 */
		public static Date getZeroTimeByDay(Date date) {
			Calendar cal = Calendar.getInstance();    
			cal.setTime(date);
	        cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
	        return cal.getTime();
		}
		
		/**
	     * 把传入的时间设置为起始时间，把时分秒设置为00:00
	     *
	     * @param current
	     * @return
	     */
	    public static Date getBeginDate(Date current) {
	        Calendar c = Calendar.getInstance();
	        c.setTime(current);
	        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);
	        return c.getTime();
	    }

	    /**
	     * 把传入的结束时间设置为结束时间59：59
	     *
	     * @return
	     */
	    public static Date getEndDate(Date current) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(current);
	        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
	        calendar.add(Calendar.DATE, 1);
	        calendar.add(Calendar.SECOND, -1);
	        return calendar.getTime();
	    }

	    /**
	     * 得到两个时间的间隔数，参数不分顺序
	     *
	     * @param date1
	     * @param date2
	     * @return
	     */
	    public static long getBetweenSecond(Date date1, Date date2) {
	        return Math.abs((date1.getTime() - date2.getTime()) / 1000);
	    }

		public static void main(String[] args) {
	       
	        Calendar start = Calendar.getInstance();
	        Calendar end = Calendar.getInstance();
	        end.add(Calendar.DAY_OF_YEAR, 5);
	        List<String> dates = getDates(start, end);
	 
	        
	        for (String date : dates) {
	        	
	            System.out.println("===Date:"+date);
	        }
	        Date strToDate = DateUtils.strToDate("2017-04-02 11:22:33");
	        System.out.println(strToDate.toString());
			Date date = getFirstDayOfMonth(new Date());
			System.out.println(date.toString());
			Date lastDay = getZeroTimeByDay(new Date());
			System.out.println("零时零分零秒"+lastDay.toString());
	        
	    }
		

    
}


