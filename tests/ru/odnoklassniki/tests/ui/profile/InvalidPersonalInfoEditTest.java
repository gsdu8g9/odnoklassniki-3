package ru.odnoklassniki.tests.ui.profile;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ru.odnoklassniki.tests.ui.api.WIBrowser;
import ru.odnoklassniki.tests.ui.api.WIBrowserFactory;
import ru.odnoklassniki.tests.ui.api.dialog.WIProfilePersoanlDialog;
import ru.odnoklassniki.tests.ui.api.locale.Locale;


public class InvalidPersonalInfoEditTest {
	
	WIBrowser b;
	WIProfilePersoanlDialog d;
	
	@BeforeClass()
	public void setup() {
		b = WIBrowserFactory.getNewBrowser("http://www.odnoklassniki.ru");
//		b.login("dummy_user1@mail.ru", "pwd123456");
		b.login("dummy_user123", "pwd123456");
		d = b.getProfile().dlgPersonalInfo; 
	}
	
	@AfterClass(alwaysRun=true)
	public void clean() {
		b.close();
	}
	
	@AfterMethod(alwaysRun=true)
	public void testClean() {
		d.cancel();
	}
	
	@Test
	public void testEmptyName() {
		d.go();
		d.inpName.setValue("");
		d.btnSave.click();
		d.inpName.propError.waitVisible();
		d.inpName.propError.assertValue(Locale.ERR_SPECIFY_NAME);
	}

	@Test
	public void testEmptySurame() {
		d.go();
		d.inpSurname.setValue("");
		d.btnSave.click();
		d.inpSurname.propError.waitVisible();
		d.inpSurname.propError.assertValue(Locale.ERR_SPECIFY_SURNAME);
	}
	
	@Test
	public void testEmptyCity() {
		d.go();
		d.inpCity.setValue("");
		d.btnSave.click();
		d.inpCity.propError.waitVisible();
		d.inpCity.propError.assertValue(Locale.ERR_SPECIFY_CITY);
	}
	
//	@Test
	public void testEmptyName2() {
		d.go();
		d.inpName.setValue("Петр");
		d.inpSurname.setValue("Smith");
		d.inpBirthDay.setValue("8");
		d.inpBirthMonth.setValue("март");
		d.inpBirthYear.setValue("2000");
		d.inpCity.setValue("Васюки");
		d.inpBirthCity.setValue("Питер");
		d.save();
	}
	
}
