package ru.odnoklassniki.tests.ui.profile;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.odnoklassniki.tests.common.Loggers;
import ru.odnoklassniki.tests.common.TimeSpan;
import ru.odnoklassniki.tests.common.Utils;
import ru.odnoklassniki.tests.ui.Environment;
import ru.odnoklassniki.tests.ui.api.WIBrowser;
import ru.odnoklassniki.tests.ui.api.WIBrowserFactory;
import ru.odnoklassniki.tests.ui.api.dialog.WIProfilePersoanlDialog;
import ru.odnoklassniki.tests.ui.api.locale.Text;

public class PersonalInfoEditTest {

    // Personal information set 
	private static enum Data {
		NAME("Иван", "Петр"),
		SURNAME("Иванов", "Петров"),
		BIRTH_DAY("2", "3"),
		BIRTH_MONTH(Text.APRIL.getValue(), Text.MAY.getValue()),
		BIRTH_YEAR("2006", "2007"),
		CITY("Санкт-Петербург, Россия", "г. Кронштадт (Санкт-Петербург г), Россия"),
		BIRTH_CITY("Москва, Россия", "г. Зеленоград (Москва г), Россия");
		
		private String[] values;
		
		Data(String... values) {
			this.values = values;
		}
		
		// Switch old value to new one
		public String getNewValue(String oldValue) {
			return values[0].equals(oldValue) ? values[1] : values[0];
		}
	};
	
	@DataProvider(name = "symbols")
	public Iterator<Object[]> createData() {
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		for (char c : Environment.CORRECT_SYMBOLS.toCharArray()) {
			data.add(new Object[] { c });
		}
		return data.iterator();
	}
	
	WIBrowser b;
	WIProfilePersoanlDialog d;
	
	@BeforeClass()
	public void setupClass() {
		b = WIBrowserFactory.getNewBrowser(Environment.BASE_URL);
		b.login(Environment.USERNAME, Environment.PASSWORD);
		d = b.getProfile().dlgPersonalInfo;

		// FIXME For some reason dialog can't be reopened immediately after first saving
		// so make warm up saving and wait a bit
		d.go();
		d.save();
		Utils.pause(TimeSpan.Seconds(3), "Warm up edit dialog", Loggers.ui);
	}

	@AfterClass()
	public void cleanClass() {
		b.close();
	}
	
	@Test(description="Change name")
	public void testNameChange() {
		d.go();
		
		String oldValue = d.inpName.getValue();
		String newValue = Data.NAME.getNewValue(oldValue);
	    		
		d.inpName.setValue(newValue);
		d.save();
		
		d.go();
		d.inpName.assertValue(newValue);
	}

	@Test(description="Use digits for name")
	public void testNameDigits() {
		String newValue = "1234567890";
	    		
		d.go();
		d.inpName.setValue(newValue);
		d.save();
		
		d.go();
		d.inpName.assertValue(newValue);
	}

	@Test(description="Use symbols for name", dataProvider = "symbols")
	public void testNameSymbols(char symbol) {
		String newValue = "Иван" + symbol;
		
		d.go();
		d.inpName.setValue(newValue);
		d.save();
		
		d.go();
		d.inpName.assertValue(newValue);
	}
	
	@Test(description="Set long name")
	public void testNameLong() {
		String original = Utils.fillString(20);
		String expected = Utils.fillString(16);

		d.go();
		d.inpName.setValue(original);
		d.inpName.assertValue(expected);
		d.save();
		
		d.go();
		d.inpName.assertValue(expected);
	}

	@Test(description="Change surname")
	public void testSurameChange() {
		d.go();

		String oldValue = d.inpSurname.getValue();
		String newValue = Data.SURNAME.getNewValue(oldValue);

		d.inpSurname.setValue(newValue);
		d.save();
		
		d.go();
		d.inpSurname.assertValue(newValue);
	}
	
	@Test(description="User digits for surname")
	public void testSurnameDigits() {
		String newValue = "1234567890";
	    		
		d.go();
		d.inpSurname.setValue(newValue);
		d.save();
		
		d.go();
		d.inpSurname.assertValue(newValue);
	}
	
	@Test(description="Use symbols for surname", dataProvider = "symbols")
	public void testSurnameSymbols(char symbol) {
		String newValue = "Иванов" + symbol;
		
		d.go();
		d.inpSurname.setValue(newValue);
		d.save();
		
		d.go();
		d.inpSurname.assertValue(newValue);
	}
	
	@Test(description="Set long surname")
	public void testSurnameLong() {
		String original = Utils.fillString(30);
		String expected = Utils.fillString(24);

		d.go();
		d.inpSurname.setValue(original);
		d.inpSurname.assertValue(expected);
		d.save();
		
		d.go();
		d.inpSurname.assertValue(expected);
	}

	@Test(description="Change birth day")
	public void testBirthDayChange() {
		d.go();

		String oldValue = d.inpBirthDay.getValue();
		String newValue = Data.BIRTH_DAY.getNewValue(oldValue);

		d.inpBirthDay.setValue(newValue);
		d.save();

		d.go();
		d.inpBirthDay.assertValue(newValue);
	}
	
	@Test(description="Change birth month")
	public void testBirthMonthChange() {
		d.go();

		String oldValue = d.inpBirthMonth.getValue();
	    String newValue = Data.BIRTH_MONTH.getNewValue(oldValue);

		d.inpBirthMonth.setValue(newValue);
		d.save();

		d.go();
		d.inpBirthMonth.assertValue(newValue);
	}
	
	@Test(description="Change birth year")
	public void testBirthYearChange() {
		d.go();

		String oldValue = d.inpBirthYear.getValue();
		String newValue = Data.BIRTH_YEAR.getNewValue(oldValue);

		d.inpBirthYear.setValue(newValue);
		d.save();

		d.go();
		d.inpBirthYear.assertValue(newValue);
	}
	
	@Test(description="Change gender")
	public void testGendeChanger() {
		d.go();

		boolean isMale = d.inpMale.isChecked();
		
		if (isMale) {
			d.inpFemale.setValue(true);
		} else {
			d.inpMale.setValue(true);
		}

		d.save();

		d.go();
		d.inpMale.assertValue(!isMale);
		d.inpFemale.assertValue(isMale);
	}
	
	@Test(description="Change city")
	public void testCityChange() {
		d.go();

		String oldValue = d.inpCity.getValue();
		String newValue = Data.CITY.getNewValue(oldValue);

		d.inpCity.setValue(newValue);
		d.save();

		d.go();
		d.inpCity.assertValue(newValue);
	}

	@Test(description="Set long city name")
	public void testCityLong() {
		String original = Utils.fillString(60);
		String expected = Utils.fillString(50);
		
		d.go();
		d.inpCity.setValue(original);
		d.inpCity.assertValue(expected);
		
		// Need real long city name to save so just cancel
		d.cancel();
	}

	@Test(description="Change birth city")
	public void testBithCityChange() {
		d.go();

		String oldValue = d.inpBirthCity.getValue();
		String newValue = Data.BIRTH_CITY.getNewValue(oldValue);

		d.inpBirthCity.setValue(newValue);
		d.save();
		
		d.go();
		d.inpBirthCity.assertValue(newValue);
	}
	
	@Test(description="Set empty birth city")
	public void testBirthCityEmpty() {
		d.go();
		d.inpBirthCity.setValue("");
		d.save();
		
		d.go();
		d.inpBirthCity.assertValue("");
	}
	
	@Test(description="Set long birth city name")
	public void testBiirthCityLong() {
		String original = Utils.fillString(60);
		String expected = Utils.fillString(50);
		
		d.go();
		d.inpBirthCity.setValue(original);
		d.inpBirthCity.assertValue(expected);
		
		// Need real long city name to save so just cancel
		d.cancel();
	}

}
