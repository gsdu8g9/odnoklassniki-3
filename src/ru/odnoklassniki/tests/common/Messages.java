package ru.odnoklassniki.tests.common;

public enum Messages implements IMessage {

	FAILED_FORMAT_STRING("Failed format string \"%s\" because of %s:%s", null),
	INVALID_URL2("Invalid URL %s");

	private String problem;
	private String solution;

	Messages(String problem) {
		this.problem = problem;
	}

	Messages(String problem, String solution) {
		this.problem = problem;
		this.solution = solution;
	}

	@Override
	public String getProblem(Object... params) {
		return format(problem, params);
	}

	@Override
	public String getSolution(Object... params) {
		return format(solution, params);
	}

	public static String format(String text, Object... params) {
		// If formats starts with "%s..." that first character can be lower-case
		// so replace it with upper-case
		return text == null ? null : capitalLeadingChar(Utils.format(text,
				params));
	}

	private static String capitalLeadingChar(String text) {
		return text.isEmpty() ? "" : text.substring(0, 1).toUpperCase()
				+ text.substring(1);
	}

}
