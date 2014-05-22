package ru.odnoklassniki.tests.ui.api.controls;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.locale.Text;

public class WIMenuItem extends WIClickable {

	public static final String WI_MENU_ITEM_TYPE = "menu";

	public static WIMenuItem Footer(IWIRoad road, Text name) {
		return new WIMenuItem(road, "//*[@id='footer']//*[text()='"
		        + name.getValue() + "']", name.getName());
	}

	public static WIMenuItem Link(IWIRoad road, Text name) {
		return new WIMenuItem(road, "//a[text()='" + name.getValue() + "']",
		        name.getName());
	}

	private WIMenuItem(IWIRoad road, String locator, String name) {
		super(road, locator, name, WI_MENU_ITEM_TYPE);
	}

}
