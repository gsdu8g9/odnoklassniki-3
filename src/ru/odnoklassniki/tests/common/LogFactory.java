package ru.odnoklassniki.tests.common;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

public class LogFactory {

	static {
		loadConfig();
	}
	
	public static void loadConfig() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(
					LogFactory.class.getResourceAsStream("log4j.properties.xml")
				);
			DOMConfigurator.configure(doc.getDocumentElement());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Logger getLogger(Class<?> clazz) {
		return getLogger(clazz.getName());
	}

	public static Logger getLogger(String name) {
		return LoggerFactory.getLogger(name);
	}
}
