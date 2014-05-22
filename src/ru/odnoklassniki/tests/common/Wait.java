package ru.odnoklassniki.tests.common;

import static ru.odnoklassniki.tests.common.Messages.WAIT_INTERRUPTED;
import ru.odnoklassniki.tests.runner.TestboxException;

/**
 * Wait for specified period of time and check condition each specified interval
 * until returns true
 * 
 */
public abstract class Wait {

	public class TimedOutException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		public TimedOutException() {
			super();
		}

		public TimedOutException(String message, Throwable cause) {
			super(message, cause);
		}

		public TimedOutException(String message) {
			super(message);
		}

		public TimedOutException(Throwable cause) {
			super(cause);
		}

	}

	public static final TimeSpan DEFAULT_TIMEOUT = TimeSpan.Seconds(30);
	public static final TimeSpan DEFAULT_INTERVAL = TimeSpan.Milliseconds(500);

	public Wait() {
	}

	public Wait(String messageToShowIfTimeout) {
		wait(messageToShowIfTimeout, DEFAULT_TIMEOUT, DEFAULT_INTERVAL);
	}

	public abstract boolean until();

	/**
	 * Wait condition is true for default interval and raise exception if
	 * condition is false
	 * 
	 * @param message
	 *            exception message
	 */
	public void wait(String message) {
		wait(message, DEFAULT_TIMEOUT, DEFAULT_INTERVAL);
	}

	/**
	 * Wait condition is true for specified interval and raise exception if
	 * condition is false
	 * 
	 * @param message
	 *            exception message
	 * @param timeout
	 *            time interval
	 */
	public void wait(String message, TimeSpan timeout) {
		wait(message, timeout, DEFAULT_INTERVAL);
	}

	public void wait(String message, TimeSpan timeout, TimeSpan interval) {
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
				throw new TestboxException(WAIT_INTERRUPTED, e);
			}
		}
		throw new TimedOutException(message);
	}

}
