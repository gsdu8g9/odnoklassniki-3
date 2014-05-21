package ru.odnoklassniki.tests.ui.api.pages;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.controls.WIContainer;
import ru.odnoklassniki.tests.ui.api.controls.WIMenuItem;
import ru.odnoklassniki.tests.ui.api.controls.property.WIUsernameProperty;
import ru.odnoklassniki.tests.ui.api.locale.Text;

public class WIGlobalContainer extends WIContainer {

	public static final String WI_GLOBAL_CONTAINER_TYPE = "GlobalContainer";

	public final WIMenuItem mnuAbout = WIMenuItem.Footer(this, Text.MENU_ABOUT);

	public final WIUsernameProperty proUsername = new WIUsernameProperty(this,
	        "//*[@id='portal-headline_login']", "Username");

	public WIGlobalContainer(IWIRoad aRoad) {
		super(aRoad, null, "Global Container", WI_GLOBAL_CONTAINER_TYPE);
	}

	@Override
	public boolean isVisible() {
		return proUsername.isVisible();
	}

}
