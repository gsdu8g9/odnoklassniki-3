package ru.odnoklassniki.tests.ui.profile;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.odnoklassniki.tests.ui.Environment;
import ru.odnoklassniki.tests.ui.api.WIBrowser;
import ru.odnoklassniki.tests.ui.api.WIBrowserFactory;
import ru.odnoklassniki.tests.ui.api.dialog.WIProfilePersoanlDialog;
import ru.odnoklassniki.tests.ui.api.locale.Text;

public class PersonalInfoEditTest {

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
		d.go();
	}

	@AfterClass()
	public void cleanClass() {
		b.close();
	}
	
	@Test
	public void testNameChange() {
		String oldValue = d.inpName.getValue();
		String newValue = Data.NAME.getNewValue(oldValue);
	    		
		d.inpName.setValue(newValue);
		d.save();
		
		d.go();
		d.inpName.assertValue(newValue);
	}

	@Test
	public void testNameDigits() {
		String newValue = "1234567890";
	    		
		d.inpName.setValue(newValue);
		d.save();
		
		d.go();
		d.inpName.assertValue(newValue);
	}

	@Test(dataProvider = "symbols")
	public void testNameSymbols(char symbol) {
		String newValue = "Иван" + symbol;
		
		d.inpName.setValue(newValue);
		d.save();
		
		d.go();
		d.inpName.assertValue(newValue);
	}
	
	@Test
	public void testNameMaxLength() {
		d.inpName.setValue("12345678901234567890");
		d.inpName.assertValue("1234567890123456");
		d.save();
		
		d.go();
		d.inpName.assertValue("1234567890123456");
	}

	@Test
	public void testSurameChange() {
		String oldValue = d.inpSurname.getValue();
		String newValue = Data.SURNAME.getNewValue(oldValue);

		d.inpSurname.setValue(newValue);
		d.save();
		
		d.go();
		d.inpSurname.assertValue(newValue);
	}
	
	@Test
	public void testSurnameDigits() {
		String newValue = "1234567890";
	    		
		d.inpSurname.setValue(newValue);
		d.save();
		
		d.go();
		d.inpSurname.assertValue(newValue);
	}
	
	@Test(dataProvider = "symbols")
	public void testSurnameSymbols(char symbol) {
		String newValue = "Иванов" + symbol;
		
		d.inpSurname.setValue(newValue);
		d.save();
		
		d.go();
		d.inpSurname.assertValue(newValue);
	}
	
	@Test
	public void testSurnameMaxLength() {
		d.inpSurname.setValue("1234567890123456789001234567890");
		d.inpSurname.assertValue("123456789012345678901234");
		d.save();
		
		d.go();
		d.inpSurname.assertValue("123456789012345678901234");
	}

	@Test
	public void testBirthDayChange() {
		String oldValue = d.inpBirthDay.getValue();
		String newValue = Data.BIRTH_DAY.getNewValue(oldValue);

		d.inpBirthDay.setValue(newValue);
		d.save();

		d.go();
		d.inpBirthDay.assertValue(newValue);
	}
	
	@Test
	public void testBirthMonthChange() {
		String oldValue = d.inpBirthMonth.getValue();
	    String newValue = Data.BIRTH_MONTH.getNewValue(oldValue);

		d.inpBirthMonth.setValue(newValue);
		d.save();

		d.go();
		d.inpBirthMonth.assertValue(newValue);
	}
	
	@Test
	public void testBirthYearChange() {
		String oldValue = d.inpBirthYear.getValue();
		String newValue = Data.BIRTH_YEAR.getNewValue(oldValue);

		d.inpBirthYear.setValue(newValue);
		d.save();

		d.go();
		d.inpBirthYear.assertValue(newValue);
	}
	
	@Test
	public void testGendeChanger() {
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
	
	@Test
	public void testCityChange() {
		String oldValue = d.inpCity.getValue();
		String newValue = Data.CITY.getNewValue(oldValue);

		d.inpCity.setValue(newValue);
		d.save();

		d.go();
		d.inpCity.assertValue(newValue);
	}
	
	@Test
	public void testBithCityChange() {
		String oldValue = d.inpBirthCity.getValue();
		String newValue = Data.BIRTH_CITY.getNewValue(oldValue);

		d.inpBirthCity.setValue(newValue);
		d.save();
		
		d.go();
		d.inpBirthCity.assertValue(newValue);
	}
	
	@Test
	public void testBirthCityEmpty() {
		d.inpBirthCity.setValue("");
		d.save();
		
		d.go();
		d.inpBirthCity.assertValue("");
	}
	
}
