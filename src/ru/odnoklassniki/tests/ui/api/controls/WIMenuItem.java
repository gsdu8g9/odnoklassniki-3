package ru.odnoklassniki.tests.ui.api.controls;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.locale.Text;

public class WIMenuItem extends WIClickable {

	public static final String WI_MENU_ITEM_TYPE = "menu";

	public static WIMenuItem Footer(IWIRoad aRoad, Text aName) {
		return new WIMenuItem(aRoad, "//*[@id='footer']//*[text()='"
		        + aName.getValue() + "']", aName.getName());
	}

	public static WIMenuItem Link(IWIRoad aRoad, Text aName) {
		return new WIMenuItem(aRoad, "//a[text()='" + aName.getValue() + "']",
		        aName.getName());
	}

	private WIMenuItem(IWIRoad aRoad, String aId, String aName) {
		super(aRoad, aId, aName, WI_MENU_ITEM_TYPE);
	}

}
