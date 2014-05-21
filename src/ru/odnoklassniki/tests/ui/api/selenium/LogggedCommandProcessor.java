package ru.odnoklassniki.tests.ui.api.selenium;

import static ru.odnoklassniki.tests.common.Loggers.selenium;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ru.odnoklassniki.tests.common.Utils;

import com.thoughtworks.selenium.CommandProcessor;

public class LogggedCommandProcessor implements CommandProcessor {
	
	@SuppressWarnings("serial")
	private static final List<String> s_hugeResult = new ArrayList<String>() {{
		add("getHtmlSource");
		add("captureEntirePageScreenshotToString");
	}};
	
	private CommandProcessor m_processor;

	public LogggedCommandProcessor(CommandProcessor processor) {
		m_processor = processor;
	}
	
	private String toString(Object obj) {
		if (obj == null) {
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
		// Command "getHtmlSource" return huge HTML code which should be ignored by logger
		if (s_hugeResult.contains(command)) {
			result = "...";
		}
		selenium.debug(
				command + " (" + toString(params) + ")" + 
				(result == null ? "" : " -> [" + toString(result) + "]"));
	}
	
	public String doCommand(String command, String[] params) {
		String result = m_processor.doCommand(command, params);
		log(command, params, result);
		return result;
	}

	public boolean getBoolean(String command, String[] params) {
		boolean result = m_processor.getBoolean(command, params);
		log(command, params, result);
		return result;
	}

	public boolean[] getBooleanArray(String command, String[] params) {
		boolean[] result = m_processor.getBooleanArray(command, params);
		log(command, params, result);
		return result;
	}

	public Number getNumber(String command, String[] params) {
		Number result = m_processor.getNumber(command, params);
		log(command, params, result);
		return result;
	}

	public Number[] getNumberArray(String command, String[] params) {
		Number[] result = m_processor.getNumberArray(command, params);
		log(command, params, result);
		return result;
	}

	public String getString(String command, String[] params) {
		String result = m_processor.getString(command, params);
		log(command, params, result);
		return result;
	}

	public String[] getStringArray(String command, String[] params) {
		String[] result = m_processor.getStringArray(command, params);
		log(command, params, result);
		return result;
	}

	public void start() {
		log("start", null, null);
		m_processor.start();
	}

	public void start(Object param) {
		log("start",  param, null);
		m_processor.start(param);
	}

	public void start(String param) {
		log("start", param, null);
		m_processor.start(param);
	}

	public void stop() {
		log("stop", null, null);
		m_processor.stop();
	}

	public void setExtensionJs(String extension) {
		log("setExtensionJs", extension, null);
		m_processor.setExtensionJs(extension);
	}

	public String getRemoteControlServerLocation() {
		String result = m_processor.getRemoteControlServerLocation();
		log("getRemoteControlServerLocation", null, result);
		return result;
	}

}
