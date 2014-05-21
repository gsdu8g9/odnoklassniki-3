package ru.odnoklassniki.tests.common;

import static ru.odnoklassniki.tests.common.Messages.WAIT_INTERRUPTED;
import ru.odnoklassniki.tests.runner.TestboxException;

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

	public void wait(String message, Time timeout) {
		wait(message, timeout, DEFAULT_INTERVAL);
	}

	public void wait(String message, Time timeout, Time interval) {
		wait(message, timeout.toMilliseconds(), interval.toMilliseconds());
	}

	private void wait(String message, long timeoutInMilliseconds,
	        long intervalInMilliseconds) {
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
		throw new WaitTimedOutException(message);
	}

}
