package ru.odnoklassniki.tests.ui.api;

import static ru.odnoklassniki.tests.ui.api.Messages.ERR_ARGUMENT_IS_EMPTY;
import static ru.odnoklassniki.tests.ui.api.Messages.ERR_ARGUMENT_IS_NULL;

import java.util.Collection;

public class Requirements {

	public static <T> T argumentNotNull(T aArgValue, String aArgName) {
		if (null == aArgValue) {
			throw new IllegalArgumentException(
					ERR_ARGUMENT_IS_NULL.getProblem(aArgName));
		}
		return aArgValue;
	}

	public static <T> Collection<T> argumentNotEmpty(Collection<T> aArgValue, String aArgName) {
		if (0 == aArgValue.size()) {
			throw new IllegalArgumentException(
					ERR_ARGUMENT_IS_EMPTY.getProblem(aArgName));
		}
		return aArgValue;
	}
	
	public static <T> T[] argumentNotEmpty(T[] aArgValue, String aArgName) {
		if (0 == aArgValue.length) {
			throw new IllegalArgumentException(
					ERR_ARGUMENT_IS_EMPTY.getProblem(aArgName));
		}
		return aArgValue;
	}

}
