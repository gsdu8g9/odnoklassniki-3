package ru.odnoklassniki.tests.common;

import static ru.odnoklassniki.tests.ui.api.Messages.ERR_ARGUMENT_IS_EMPTY;
import static ru.odnoklassniki.tests.ui.api.Messages.ERR_ARGUMENT_IS_NULL;
import static ru.odnoklassniki.tests.ui.api.Messages.ERR_ARGUMENT_NO_SIZE;

import java.lang.reflect.Array;
import java.util.Collection;

public class Argument<T> {

	private String name;
	private T value;

	private Argument(String name, T value) {
		this.name = name;
		this.value = value;
	}

	public static <T> Argument<T> argument(T value, String name) {
		return new Argument<T>(name, value);
	}

	public Argument<T> notNull() {
		if (null == value) {
			throw new IllegalArgumentException(
			        ERR_ARGUMENT_IS_NULL.getValue(name));
		}
		return this;
	}

	public Argument<T> notEmpty() {
		boolean isEmpty = false;
		if (value instanceof Collection) {
			isEmpty = ((Collection<?>) value).size() == 0;
		} else if (value.getClass().isArray()) {
			isEmpty = Array.getLength(value) == 0;
		} else if (value instanceof String) {
			isEmpty = ((String) value).length() == 0;
		} else {
			throw new IllegalArgumentException(
			        ERR_ARGUMENT_NO_SIZE.getValue(name));
		}
		if (isEmpty) {
			throw new IllegalArgumentException(
			        ERR_ARGUMENT_IS_EMPTY.getValue(name));
		}
		return this;
	}

}
