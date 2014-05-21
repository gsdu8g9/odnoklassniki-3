package ru.odnoklassniki.tests.common;

public enum Messages implements IMessage {

	FAILED_READ_LOG_CONFIG("Failed read log config %s"),
	WAIT_INTERRUPTED("Wait interrupted, %s"),
	FAILED_FORMAT_STRING("Failed format string \"%s\"  %s"),
	FAILED_PARSE_DATE("Failed format date \"%s\" (%s) %s"),
	INVALID_URL1("Invalid URL %s://%s:%s/%s"),
	INVALID_URL2("Invalid URL %s"),
	FAILED_WRITE_FILE("Failed write file '%s' %s"),
	FAILED_DELETE_PATH("Failed delete %s, %s"),
	FAILED_CREATE_PATH("Failed create directories %s, %s");

	private String message;

	Messages(String message) {
		this.message = message;
	}

	@Override
	public String getValue(Object... params) {
		return Utils.format(message, params);
	}

}
