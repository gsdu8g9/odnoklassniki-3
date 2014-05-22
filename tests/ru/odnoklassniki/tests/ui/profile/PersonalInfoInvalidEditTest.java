package ru.odnoklassniki.tests.ui.profile;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.odnoklassniki.tests.ui.Environment;
import ru.odnoklassniki.tests.ui.api.WIBrowser;
import ru.odnoklassniki.tests.ui.api.WIBrowserFactory;
import ru.odnoklassniki.tests.ui.api.dialog.WIProfilePersoanlDialog;
import ru.odnoklassniki.tests.ui.api.locale.Text;

public class PersonalInfoInvalidEditTest {
	
	WIBrowser b;
	WIProfilePersoanlDialog d;
	
	@DataProvider(name = "symbols")
	public Iterator<Object[]> createData() {
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		for (char c : Environment.INVALID_SYMBOLS.toCharArray()) {
			data.add(new Object[] { c });
		}
		return data.iterator();
	}
	
	@BeforeClass()
	public void setup() {
		b = WIBrowserFactory.getNewBrowser(Environment.BASE_URL);
		b.login(Environment.USERNAME, Environment.PASSWORD);
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
	public void testNameEmpty() {
		d.go();
		d.inpName.setValue("");
		d.btnSave.click();
		d.inpName.propError.waitVisible();
		d.inpName.propError.assertValue(Text.ERR_SPECIFY_NAME);
	}

	@Test(dataProvider = "symbols")
	public void testNameSymbol(char symbol) {
		d.go();
		d.inpName.setValue("Иван" + symbol);
		d.btnSave.click();
		d.inpName.propError.waitVisible();
		d.inpName.propError.assertValue(Text.ERR_USE_ALPHA_ONLY);
	}

	@Test
	public void testSurameEmpty() {
		d.go();
		d.inpSurname.setValue("");
		d.btnSave.click();
		d.inpSurname.propError.waitVisible();
		d.inpSurname.propError.assertValue(Text.ERR_SPECIFY_SURNAME);
	}
	
	@Test(dataProvider = "symbols")
	public void testSurameSymbol(char symbol) {
		d.go();
		d.inpSurname.setValue("Иванов" + symbol);
		d.btnSave.click();
		d.inpSurname.propError.waitVisible();
		d.inpSurname.propError.assertValue(Text.ERR_USE_ALPHA_ONLY);
	}
	
	@Test
	public void testCityEmpty() {
		d.go();
		d.inpCity.setValue("");
		d.btnSave.click();
		d.inpCity.propError.waitVisible();
		d.inpCity.propError.assertValue(Text.ERR_SPECIFY_CITY);
	}
	
	@Test
	public void testCityUnknown() {
		d.go();
		d.inpCity.setValue("foo");
		d.btnSave.click();
		d.inpCity.propError.waitVisible();
		d.inpCity.propError.assertValue(Text.ERR_CHOOSE_CITY_FROM_LIST);
	}
	
	@Test
	public void testBirthCityUnknown() {
		d.go();
		d.inpCity.setValue("bar");
		d.btnSave.click();
		d.inpCity.propError.waitVisible();
		d.inpCity.propError.assertValue(Text.ERR_CHOOSE_CITY_FROM_LIST);
	}
	
}
