package com.qafox.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {

	SimpleDateFormat format;

	// "yyyyMMddHHmmss"
	public String getCurrentDateFormat(String dateFormat) {
		format = new SimpleDateFormat(dateFormat);
		String date = format.format(new Date());
		return date;
	}

	public String getDateFullMonthYearFormat(String date) throws ParseException {
		format = new SimpleDateFormat("dd-MMM-yyyy");
		Date strDate = format.parse(date);
		SimpleDateFormat out = new SimpleDateFormat("MMMM yyyy");
		String givenDate = out.format(strDate);
		return givenDate;

	}

	public String[] getDateMonthAndYear(String date) throws ParseException {

		format = new SimpleDateFormat("dd/MM/yy");
		Date strDate = format.parse(date);
		String givenDate = format.format(strDate);
		System.out.println(givenDate);
		String datearr[] = givenDate.split("/");
		return datearr;
	}

	
	public int getCurrntTime() {
		String[] currentTime = null;
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm:ss");
			String currenFulltTime = dateformat.format(cal.getTime());
			System.out.println(currenFulltTime);
			currentTime = currenFulltTime.split(":");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Integer.parseInt(currentTime[0]);
	}

	public String getHostName() throws UnknownHostException {
		InetAddress addr = InetAddress.getLocalHost();
		String hostname = addr.getHostName();

		return hostname;
	}

	public String osEnvironment() {
		return "Current suit executed on : " + System.getProperty("os.name") + "/version : "
				+ System.getProperty("os.version") + "/Architecture : " + System.getProperty("os.arch");
	}

}
