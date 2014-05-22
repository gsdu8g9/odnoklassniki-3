package ru.odnoklassniki.tests.ui.api.pages;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.controls.WIMenuItem;
import ru.odnoklassniki.tests.ui.api.controls.WIPage;
import ru.odnoklassniki.tests.ui.api.controls.property.WITextProperty;
import ru.odnoklassniki.tests.ui.api.locale.Text;

/**
 * User home page
 *
 */
public class WIHomePage extends WIPage {

	public final WIMenuItem mnuAbout = WIMenuItem.Footer(this, Text.MENU_ABOUT);

	public final WITextProperty proUsername = new WITextProperty(this,
	        "//*[@id='portal-headline_login']", "Username");

	public WIHomePage(IWIRoad road) {
		super(road, null, "Home");
	}

	@Override
	public boolean isVisible() {
		return proUsername.isVisible();
	}

}
