package ru.odnoklassniki.tests.ui.api.pages;

import java.util.Calendar;

import ru.odnoklassniki.tests.common.Utils;
import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.controls.WIMenuItem;
import ru.odnoklassniki.tests.ui.api.controls.WIPage;
import ru.odnoklassniki.tests.ui.api.dialog.WIProfilePersoanlDialog;
import ru.odnoklassniki.tests.ui.api.locale.Text;

public class WIProfilePage extends WIPage {

	public final WIMenuItem mnuPersonalInfo = WIMenuItem.Link(this,
	        Text.LINK_EDIT_PROFILE);

	public final WIProfilePersoanlDialog dlgPersonalInfo = new WIProfilePersoanlDialog(
	        mnuPersonalInfo.getRoad());

	public WIProfilePage(IWIRoad aRoad) {
		super(aRoad, "hook_Block_UserProfileSummary", "User Profile");
	}

	public void setPersonalInfo(String aName, String aSurname,
	        String aBirthdate, String aCity, String aBithCity) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(Utils.parseDate("yyyy-MM-dd", aBirthdate));
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);

		dlgPersonalInfo.go();
		dlgPersonalInfo.inpName.setValue(aName);
		dlgPersonalInfo.inpSurname.setValue(aSurname);
		dlgPersonalInfo.inpBirthDay.setValue("" + day);
		dlgPersonalInfo.inpBirthDay.setValue("" + month);
		dlgPersonalInfo.inpBirthDay.setValue("" + year);
		dlgPersonalInfo.inpCity.setValue(aCity);
		dlgPersonalInfo.inpBirthCity.setValue(aBithCity);
		dlgPersonalInfo.save();
	}

}
