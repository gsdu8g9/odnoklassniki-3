package ru.odnoklassniki.tests.ui.api;

import static ru.odnoklassniki.tests.ui.api.Messages.ERR_ARGUMENT_IS_EMPTY;
import static ru.odnoklassniki.tests.ui.api.Messages.ERR_ARGUMENT_IS_NULL;

import java.util.Collection;

public class Requirements {

	public static <T> T argumentNotNull(T argValue, String argName) {
		if (null == argValue) {
			throw new IllegalArgumentException(
			        ERR_ARGUMENT_IS_NULL.getValue(argName));
		}
		return argValue;
	}

	public static <T> Collection<T> argumentNotEmpty(Collection<T> argValue,
	        String argName) {
		if (0 == argValue.size()) {
			throw new IllegalArgumentException(
			        ERR_ARGUMENT_IS_EMPTY.getValue(argName));
		}
		return argValue;
	}

	public static <T> T[] argumentNotEmpty(T[] argValue, String argName) {
		if (0 == argValue.length) {
			throw new IllegalArgumentException(
			        ERR_ARGUMENT_IS_EMPTY.getValue(argName));
		}
		return argValue;
	}

}
