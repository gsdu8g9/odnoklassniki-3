package ru.odnoklassniki.tests.common;

import java.util.HashMap;
import java.util.Map;

public abstract class Wait {

	public class WaitTimedOutException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		public WaitTimedOutException() {
			super();
		}

		public WaitTimedOutException(String message, Throwable cause) {
			super(message, cause);
		}

		public WaitTimedOutException(String message) {
			super(message);
		}

		public WaitTimedOutException(Throwable cause) {
			super(cause);
		}

	}

	private Map<String, Object> keys = new HashMap<String, Object>();

	public Wait() {
	}

	public Wait(String messageToShowIfTimeout) {
		wait(messageToShowIfTimeout, DEFAULT_TIMEOUT, DEFAULT_INTERVAL);
	}

	public abstract boolean until();

	public static final Time DEFAULT_TIMEOUT = Time.Seconds(30);

	public static final Time DEFAULT_INTERVAL = Time.Milliseconds(500);

	public void wait(String message) {
		wait(message, DEFAULT_TIMEOUT, DEFAULT_INTERVAL);
	}

	// TODO Remove this
	public void wait(String message, long timeout) {
		wait(message, Time.Milliseconds(timeout), DEFAULT_INTERVAL);
	}
	
	public void wait(String message, Time timeout) {
		wait(message, timeout, DEFAULT_INTERVAL);
	}

	public void wait(String message, Time timeout, Time interval) {
		wait(message, timeout.toMilliseconds(), interval.toMilliseconds());
	}
	
	private void wait(String message, long timeoutInMilliseconds, long intervalInMilliseconds) {
		long start = System.currentTimeMillis();
		long end = start + timeoutInMilliseconds;
		while (System.currentTimeMillis() < end) {
			if (until())
				return;
			try {
				Thread.sleep(intervalInMilliseconds);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		throw new WaitTimedOutException(getMessage(message));
	}

	public boolean waitSilent(long timeout) {
		return waitSilent(Time.Milliseconds(timeout));
	}
	
	public boolean waitSilent(Time timeout) {
		return waitSilent(timeout, DEFAULT_INTERVAL);
	}

	public boolean waitSilent(long  timeout, long interval) {
		return waitSilent(Time.Milliseconds(timeout), Time.Milliseconds(interval));
	}
	
	public boolean waitSilent(Time timeout, Time interval) {
		try {
			wait(null, timeout, interval);
			return true;
		} catch (WaitTimedOutException e) {
			return false;
		}
	}

	protected <T> T setKey(String key, T value) {
		keys.put(key, value);
		return value;
	}
	
	private String getMessage(String message) {
		String result = message;
		for(String key : keys.keySet()) {
			result = result.replaceAll("\\$\\{" + key + "\\}", keys.get(key).toString());
		}
		return result;
	}

}
