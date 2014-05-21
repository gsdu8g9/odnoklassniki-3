package ru.odnoklassniki.tests.common;

import static ru.odnoklassniki.tests.common.Messages.FAILED_CREATE_PATH;
import static ru.odnoklassniki.tests.common.Messages.FAILED_DELETE_PATH;
import static ru.odnoklassniki.tests.common.Messages.FAILED_FORMAT_STRING;
import static ru.odnoklassniki.tests.common.Messages.FAILED_PARSE_DATE;
import static ru.odnoklassniki.tests.common.Messages.FAILED_WRITE_FILE;
import static ru.odnoklassniki.tests.common.Messages.INVALID_URL1;
import static ru.odnoklassniki.tests.common.Messages.INVALID_URL2;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;

import ru.odnoklassniki.tests.runner.TestboxException;

public class Utils {

	public static final String DEFAULT_ARRAY_DELIMITER = ",";

	public static void clean(File folder) {
		delete(folder);
		mkdirs(folder);
	}

	public static File delete(File folder) {
		File[] files = folder.listFiles();
		if (files == null) {
			return folder;
		}
		for (File file : files) {
			if (file.isDirectory()) {
				delete(file);
			}
			if (!file.delete()) {
				// log.error("Failed delete " + file);
			}
		}
		if (!folder.delete()) {
			throw new TestboxException(FAILED_DELETE_PATH, folder);
		}
		return folder;
	}

	public static void mkdirs(File folder) {
		if (!folder.mkdirs()) {
			throw new TestboxException(FAILED_CREATE_PATH, folder);
		}
	}

	public static void copy(InputStream is, OutputStream out) {
		try {
			byte[] buf = new byte[1024];
			int i = 0;
			while ((i = is.read(buf)) != -1) {
				out.write(buf, 0, i);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void copy(InputStream is, File file) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			try {
				copy(is, fos);
			} finally {
				fos.close();
			}
			file.setWritable(true);
			file.setExecutable(true);
			file.setReadable(true);
		} catch (Exception e) {
			throw new TestboxException(FAILED_WRITE_FILE, file, e);
		}
	}

	public static void copy(InputStream is, String out) {
		copy(is, new File(out));
	}

	public static void setText(File file, String text) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			try {
				setText(fos, text);
			} finally {
				fos.close();
			}
		} catch (IOException e) {
			throw new TestboxException(FAILED_WRITE_FILE, file, e);
		}
	}

	public static void setText(OutputStream stream, String text) throws IOException {
		copy(new ByteArrayInputStream(text.getBytes()), stream);
	}

	public static String getText(InputStream in) throws IOException {
		if (in == null) {
			return null;
		}
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			return sb.toString();
		} finally {
			in.close();
		}
	}

	public static String toXPath(String locator) {
		// Locator is xpath
		if (locator.startsWith("/")) {
			return locator;
		}
		// Locator is ID
		if (locator.startsWith("id=")) {
			if (locator.endsWith("'")) {
				return format("//*[@%s]", locator);
			}
			return format("//*[@id='%s']", locator.substring(3));
		}
		// Locator is ID
		return format("//*[@id='%s']", locator);
	}

	public static String concateXPath(String... locators) {
		String result = "";
		for (String locator : locators) {
			result += toXPath(locator);
		}
		return result;
	}

	public static void pause(Time interval, Logger logger) {
		pause(interval, "", logger);
	}

	public static void pause(Time interval, String message, Logger logger) {
		logger.debug("Wait " + interval + ". " + message);
		pause(interval);
	}

	public static void pause(Time interval) {
		try {
			Thread.sleep(interval.toMilliseconds());
		} catch (InterruptedException e) {
			// DO NOTHING
		}
	}

	public static URI getURI(String url, IMessage errorMessage) {
		try {
			return new URI(url);
		} catch (URISyntaxException e) {
			throw new TestboxException(errorMessage, url);
		}
	}

	public static URI getURI(String protocol, String host, int port, String path, IMessage errorMessage) {
		try {
			return new URI(protocol, null, host, port, path, null, null);
		} catch (URISyntaxException e) {
			throw new TestboxException(errorMessage, protocol, host, port, path);
		}
	}

	public static URL getURL(String url) {
		try {
			return new URL(url);
		} catch (MalformedURLException e) {
			throw new TestboxException(INVALID_URL2, url);
		}
	}

	public static URL getURL(String protocol, String host, int port, String path) {
		try {
			return new URL(protocol, host, port, path);
		} catch (MalformedURLException e) {
			throw new TestboxException(INVALID_URL1, protocol, host, port, path);
		}
	}

	public static <T> String join(T... values) {
		return joinWithDelimiter(DEFAULT_ARRAY_DELIMITER, values);
	}

	public static <T> String joinWithDelimiter(String delimiter, T... values) {
		if (values.length == 0) {
			return "";
		}
		StringBuilder s = new StringBuilder();
		for (int i = 0;;) {
			s.append(values[i]);
			if (++i == values.length) {
				return s.toString();
			}
			s.append(delimiter);
		}
	}

	public static String join(Collection<?> values) {
		return join(DEFAULT_ARRAY_DELIMITER, values);
	}

	public static String join(String delimiter, Collection<?> values) {
		return joinWithDelimiter(delimiter, values.toArray());
	}

	public static String format(String format, Object... params) {
		try {
			return String.format(format, params);
		} catch (Exception e) {
			throw new TestboxException(FAILED_FORMAT_STRING, format, e);
		}
	}

	public static Date parseDate(String format, String text) {
		DateFormat df = new SimpleDateFormat(format);
		try {
			return df.parse(text);
		} catch (ParseException e) {
			throw new TestboxException(FAILED_PARSE_DATE, text, format, e);
		}
	}
	
	public static String fillString(int count) {
	    StringBuilder sb = new StringBuilder(count);
	    for( int i = 1; i <= count; i++ ) {
	        sb.append(Character.forDigit(i % 10, 10)); 
	    }
	    return sb.toString();
	}
	
}
