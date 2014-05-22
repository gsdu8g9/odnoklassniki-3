package ru.odnoklassniki.tests.common;

import java.text.DecimalFormat;

/**
 * TimeSpan object is wrapper for time interval
 * 
 */
public class TimeSpan {

	private static final DecimalFormat DF00 = new DecimalFormat("00");
	private static final DecimalFormat DF000 = new DecimalFormat("000");

	private long milliseconds;

	/**
	 * Create time interval in milliseconds
	 * 
	 * @param value
	 *            milliseconds
	 * @return TimeSpan object
	 */
	public static TimeSpan Milliseconds(long value) {
		return new TimeSpan(value);
	}

	/**
	 * Create time interval in seconds
	 * 
	 * @param value
	 *            seconds
	 * @return TimeSpan object
	 */
	public static TimeSpan Seconds(long value) {
		return new TimeSpan(value * 1000);
	}

	/**
	 * Create time interval in minutes
	 * 
	 * @param value
	 *            minutes
	 * @return TimeSpan object
	 */
	public static TimeSpan Minutes(long value) {
		return new TimeSpan(value * 1000 * 60);
	}

	private TimeSpan(long milliseconds) {
		this.milliseconds = milliseconds;
	}

	/**
	 * Convert to milliseconds
	 * 
	 * @return time interval in milliseconds
	 */
	public long toMilliseconds() {
		return milliseconds;
	}

	/**
	 * Convert to seconds
	 * 
	 * @return time interval in seconds
	 */
	public long toSeconds() {
		return toMilliseconds() / 1000;
	}

	/**
	 * Convert to minutes
	 * 
	 * @return time interval in minutes
	 */
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
