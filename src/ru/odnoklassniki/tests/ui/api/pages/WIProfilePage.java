package ru.odnoklassniki.tests.ui.api.pages;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.controls.WIMenuItem;
import ru.odnoklassniki.tests.ui.api.controls.WIPage;
import ru.odnoklassniki.tests.ui.api.dialog.WIProfilePersoanlDialog;
import ru.odnoklassniki.tests.ui.api.locale.Locale;


public class WIProfilePage extends WIPage {

	public final WIMenuItem mnuPersonalInfo = WIMenuItem.Link(this, Locale.LINK_PROFILE_PERSONAL);
	
	public final WIProfilePersoanlDialog dlgPersonalInfo = new WIProfilePersoanlDialog(mnuPersonalInfo.getRoad());
	
    public WIProfilePage(IWIRoad aRoad) {
        super(aRoad, "hook_Block_UserProfileSummary", "User Profile");
    }

}
