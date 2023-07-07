package com.qafox.utils;

import org.apache.log4j.Logger;

import com.qafox.utils.LogManager;

public class LogManager {

	public static Logger Log = Logger.getLogger(LogManager.class.getName());

	public static void info(String msg) {
		Log.info(msg);
	}

	public static void warn(String msg) {
		Log.warn(msg);
	}

	public static void error(String msg) {
		Log.error(msg);
	}

	public static void debug(String msg) {
		Log.debug(msg);
	}
}
