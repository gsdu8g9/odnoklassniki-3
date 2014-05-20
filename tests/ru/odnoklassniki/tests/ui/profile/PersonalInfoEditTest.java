package ru.odnoklassniki.tests.ui.profile;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ru.odnoklassniki.tests.ui.api.WIBrowser;
import ru.odnoklassniki.tests.ui.api.WIBrowserFactory;
import ru.odnoklassniki.tests.ui.api.dialog.WIProfilePersoanlDialog;
import ru.odnoklassniki.tests.ui.api.locale.Locale;


public class PersonalInfoEditTest {
	
	WIBrowser b;
	WIProfilePersoanlDialog d;
	
	String oldValue, newValue;
	
	@BeforeClass()
	public void setup() {
		b = WIBrowserFactory.getNewBrowser("http://www.odnoklassniki.ru");
//		b.login("dummy_user1@mail.ru", "pwd123456");
		b.login("dummy_user123", "pwd123456");
		d = b.getProfile().dlgPersonalInfo; 
	}
	
	@AfterMethod(alwaysRun=true)
	public void clean() {
		d.cancel();
	}
	
	private String getNewValue(String oldValue) {
		return oldValue.substring(1, oldValue.length() - 1) + (oldValue.endsWith("1") ? "2" : "1"); 
	}
	
	@Test
	public void testChangeName() {
		d.go();
		
		oldValue = d.inpName.getValue();
		newValue = getNewValue(oldValue);
		
		d.inpName.setValue(newValue);
		d.save();
		// TOTO check name value
	}

	@Test
	public void testChangeSurame() {
		d.go();
		
		oldValue = d.inpSurname.getValue();
		newValue = getNewValue(oldValue);
		
		d.inpSurname.setValue(newValue);
		d.save();
		// TOTO check name value
	}
	
//	@Test
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
