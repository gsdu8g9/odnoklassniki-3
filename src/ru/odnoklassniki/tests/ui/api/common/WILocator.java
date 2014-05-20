package ru.odnoklassniki.tests.ui.api.common;

public class WILocator {
	
	public static class ID {
		
		public static String matches(String regexp) {
			return "xpath2=//*[matches(@id,'" + regexp + "')]";
		}
		
		public static String mask(String mask) {
			mask = mask.replaceAll("\\*", ".*");
			mask = mask.replaceAll("\\?", ".+");
			return matches(mask);
		}
		
		public static String ends(String text) {
			return "xpath2=//*[ends-with(@id,'" + text + "')]";
		}
		
		public static String starts(String text) {
			return "xpath2=//*[starts-with(@id,'" + text + "')]";
		}
		
	}
	
	public static class Text {
		
		public static String equals(String text) {
			return "//*[text()='" + text + "']";
		}
		
	}
	
	public static class Button {
		
		public static String caption(String caption) {
			return "//button[string(.)='" + caption + "']/span";
		}
		
	}

}
