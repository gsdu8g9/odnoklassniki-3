package ru.odnoklassniki.tests.ui.api.selenium;

import static ru.odnoklassniki.tests.common.Loggers.selenium;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ru.odnoklassniki.tests.common.Utils;

import com.thoughtworks.selenium.CommandProcessor;

public class LogggedCommandProcessor implements CommandProcessor {

	// Some Selenium commands produce huge text result, don't log it
	@SuppressWarnings("serial")
	private static final List<String> hugeResult = new ArrayList<String>() {
		{
			add("getHtmlSource");
			add("captureEntirePageScreenshotToString");
		}
	};

	private CommandProcessor processor;

	public LogggedCommandProcessor(CommandProcessor processor) {
		this.processor = processor;
	}

	private String toString(Object obj) {
		if (null == obj) {
			return "";
		}
		if (obj.getClass().isArray()) {
			return Utils.join((Object[]) obj);
		}
		if (obj instanceof Collection) {
			return Utils.join((Collection<?>) obj);
		}
		return obj.toString();
	}

	private void log(String command, Object params, Object result) {
		// Command "getHtmlSource" return huge HTML code which should be ignored
		// by logger
		if (hugeResult.contains(command)) {
			result = "...";
		}
		selenium.debug(command + " (" + toString(params) + ")"
		        + (null == result ? "" : " -> [" + toString(result) + "]"));
	}

	public String doCommand(String command, String[] params) {
		String result = processor.doCommand(command, params);
		log(command, params, result);
		return result;
	}

	public boolean getBoolean(String command, String[] params) {
		boolean result = processor.getBoolean(command, params);
		log(command, params, result);
		return result;
	}

	public boolean[] getBooleanArray(String command, String[] params) {
		boolean[] result = processor.getBooleanArray(command, params);
		log(command, params, result);
		return result;
	}

	public Number getNumber(String command, String[] params) {
		Number result = processor.getNumber(command, params);
		log(command, params, result);
		return result;
	}

	public Number[] getNumberArray(String command, String[] params) {
		Number[] result = processor.getNumberArray(command, params);
		log(command, params, result);
		return result;
	}

	public String getString(String command, String[] params) {
		String result = processor.getString(command, params);
		log(command, params, result);
		return result;
	}

	public String[] getStringArray(String command, String[] params) {
		String[] result = processor.getStringArray(command, params);
		log(command, params, result);
		return result;
	}

	public void start() {
		log("start", null, null);
		processor.start();
	}

	public void start(Object param) {
		log("start", param, null);
		processor.start(param);
	}

	public void start(String param) {
		log("start", param, null);
		processor.start(param);
	}

	public void stop() {
		log("stop", null, null);
		processor.stop();
	}

	public void setExtensionJs(String extension) {
		log("setExtensionJs", extension, null);
		processor.setExtensionJs(extension);
	}

	public String getRemoteControlServerLocation() {
		String result = processor.getRemoteControlServerLocation();
		log("getRemoteControlServerLocation", null, result);
		return result;
	}

}
