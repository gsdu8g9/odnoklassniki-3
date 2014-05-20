package ru.odnoklassniki.tests.common;

import java.text.DecimalFormat;

public class Time {
	
	private static final DecimalFormat DF00 = new DecimalFormat("00");
	private static final DecimalFormat DF000 = new DecimalFormat("000");
	
	private long m_milliseconds;
	
	public static Time Milliseconds(long value) {
		return new Time(value);
	}
	
	public static Time Seconds(long value) {
		return new Time(value * 1000);
	}
	
	public static Time Minutes(long value) {
		return new Time(value * 1000 * 60);
	}
	
	private Time(long milliseconds) {
		m_milliseconds = milliseconds;
	}
	
	public long toMilliseconds() {
		return m_milliseconds;
	}
	
	public long toSeconds() {
		return toMilliseconds() / 1000;
	}
	
	public long toMinutes() {
		return toSeconds() / 60;
	}
	
	@Override
	public String toString() {
		long min = toMinutes();
		long sec = toSeconds() - min * 60;
		long msec = toMilliseconds() - (min * 60 + sec) * 1000;
		String result = "";
		if (min > 0) {
			if (sec > 0) {
				result = min + "." + DF00.format(sec) + " min";
			} else {
				result = min + " min";
			}
			return result;
		}
		if (sec > 0) {
			if (msec > 0) {
				result = sec + "." + DF000.format(msec) + " sec";
			} else {
				result = sec + " sec";
			}
			return result;
		}
		return msec + " msec";
	}
	
}
