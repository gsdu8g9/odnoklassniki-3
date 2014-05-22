package ru.odnoklassniki.tests.ui.api.pages;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.controls.WIElement;
import ru.odnoklassniki.tests.ui.api.controls.WIMenuItem;
import ru.odnoklassniki.tests.ui.api.controls.property.WITextProperty;
import ru.odnoklassniki.tests.ui.api.locale.Text;

public class WIGlobalContainer extends WIElement {

	public static final String WI_GLOBAL_CONTAINER_TYPE = "GlobalContainer";

	public final WIMenuItem mnuAbout = WIMenuItem.Footer(this, Text.MENU_ABOUT);

	public final WITextProperty proUsername = new WITextProperty(this,
	        "//*[@id='portal-headline_login']", "Username");

	public WIGlobalContainer(IWIRoad road) {
		super(road, null, "Global Container", WI_GLOBAL_CONTAINER_TYPE);
	}

	@Override
	public boolean isVisible() {
		return proUsername.isVisible();
	}

}
