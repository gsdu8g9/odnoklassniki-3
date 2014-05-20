package ru.odnoklassniki.tests.ui.profile;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.odnoklassniki.tests.ui.api.WIBrowser;
import ru.odnoklassniki.tests.ui.api.WIBrowserFactory;
import ru.odnoklassniki.tests.ui.api.dialog.WIProfilePersoanlDialog;
import ru.odnoklassniki.tests.ui.api.locale.Locale;

public class InvalidPersonalInfoEditTest {
	
	WIBrowser b;
	WIProfilePersoanlDialog d;
	
	static final String INVALID_SYMBOLS = "~`!@#$%^&*_=+\\|[{]}:\"'<>?/"; 
	static final String CORRECT_SYMBOLS = "()-"; 
	
	@DataProvider(name = "symbols")
	public Iterator<Object[]> createData() {
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		for (char c : INVALID_SYMBOLS.toCharArray()) {
			data.add(new Object[] { c });
		}
		return data.iterator();
	}
	
	@BeforeClass()
	public void setup() {
		b = WIBrowserFactory.getNewBrowser("http://www.odnoklassniki.ru");
		b.login("dummy_user1@mail.ru", "pwd123456");
//		b.login("dummy_user123", "pwd123456");
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

	@Test(dataProvider = "symbols")
	public void testSymbolName(char symbol) {
		d.go();
		d.inpName.setValue("»‚‡Ì" + symbol);
		d.btnSave.click();
		d.inpName.propError.waitVisible();
		d.inpName.propError.assertValue(Locale.ERR_USE_ALPHA_ONLY);
	}

	@Test
	public void testEmptySurame() {
		d.go();
		d.inpSurname.setValue("");
		d.btnSave.click();
		d.inpSurname.propError.waitVisible();
		d.inpSurname.propError.assertValue(Locale.ERR_SPECIFY_SURNAME);
	}
	
	@Test(dataProvider = "symbols")
	public void testSymbolSurame(char symbol) {
		d.go();
		d.inpSurname.setValue("»‚‡ÌÓ‚" + symbol);
		d.btnSave.click();
		d.inpSurname.propError.waitVisible();
		d.inpSurname.propError.assertValue(Locale.ERR_USE_ALPHA_ONLY);
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
		d.inpName.setValue("–ü–µ—Ç—Ä");
		d.inpSurname.setValue("Smith");
		d.inpBirthDay.setValue("8");
		d.inpBirthMonth.setValue("–º–∞—Ä—Ç");
		d.inpBirthYear.setValue("2000");
		d.inpCity.setValue("–í–∞—Å—é–∫–∏");
		d.inpBirthCity.setValue("–ü–∏—Ç–µ—Ä");
		d.save();
	}
	
}
