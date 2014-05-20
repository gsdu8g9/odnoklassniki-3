package ru.odnoklassniki.tests.ui.api.locale;


public enum Locale {
	
	BTN_LOGIN,
	BTN_SAVE,
	BTN_CANCEL,
	BTN_CLOSE,
	
	MENU_ABOUT,
	
	LINK_PROFILE_PERSONAL,
	
	TITLE_CHANGE_PERSONAL_INFO,
	
	FIELD_NAME,
	FIELD_SURNAME,
	FIELD_BIRTH_DATE,
	
	FIELD_CITY,
	FIELD_BIRTH_CITY,
	
	MSG_PERSONAL_INFO_CHANGED,
	
	ERR_SPECIFY_NAME,
	ERR_SPECIFY_SURNAME,
	ERR_USE_LETTERS_ONLY,
	ERR_SPECIFY_CITY,
	ERR_CHOOSE_CITY_FROM_LIST	
	;
	
	public String getValue() {
		return Text.getCurrent().getProperty(name());
	}
	
	public String getName() {
		return Text.getDefault().getProperty(name());
	}
}
