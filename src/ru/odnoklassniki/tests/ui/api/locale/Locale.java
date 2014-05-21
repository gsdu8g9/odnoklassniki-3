package ru.odnoklassniki.tests.ui.api.locale;

public enum Locale {

	MONTH_JANUARY,
	MONTH_FEBRUARY,
	MONTH_MARCH,
	MONTH_APRIL,
	MONTH_MAY,
	MONTH_JUNE,
	MONTH_JULY,
	MONTH_AUGUST,
	MONTH_SEPTEMBER,
	MONTH_OCTOBER,
	MONTH_NOVEMBER,
	MONTH_DECEMBER,	
	
	BTN_LOGIN,
	BTN_SAVE,
	BTN_CANCEL,
	BTN_CLOSE,
	
	MENU_ABOUT,
	
	LINK_EDIT_PROFILE,
	
	TITLE_CHANGE_PERSONAL_INFO,
	
	FIELD_NAME,
	FIELD_SURNAME,
	FIELD_BIRTH_DATE,
	
	FIELD_CITY,
	FIELD_BIRTH_CITY,
	
	MSG_PERSONAL_INFO_CHANGED,
	
	ERR_SPECIFY_NAME,
	ERR_SPECIFY_SURNAME,
	ERR_USE_ALPHA_ONLY,
	ERR_SPECIFY_CITY,
	ERR_CHOOSE_CITY_FROM_LIST	
	;
	
	public String getValue() {
		return LocaleManager.getCurrent().getProperty(name());
	}
	
	public String getName() {
		return LocaleManager.getDefault().getProperty(name());
	}
}
