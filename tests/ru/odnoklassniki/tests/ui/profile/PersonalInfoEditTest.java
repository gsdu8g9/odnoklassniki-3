package ru.odnoklassniki.tests.ui.profile;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ru.odnoklassniki.tests.ui.api.WIBrowser;
import ru.odnoklassniki.tests.ui.api.WIBrowserFactory;
import ru.odnoklassniki.tests.ui.api.dialog.WIProfilePersoanlDialog;
import ru.odnoklassniki.tests.ui.api.locale.Locale;


public class PersonalInfoEditTest {

	private static final String BASE_URL = "http://www.odnoklassniki.ru";
//	private static final String USERNAME = "dummy_user1";
	private static final String USERNAME = "dummy_user123";
//	private static final String USERNAME = "dummy_user456";
	private static final String PASSWORD = "pwd123456";
	
	private static enum Data {
		NAME("Иван", "Петр"),
		SURNAME("Иванов", "Петров"),
		BIRTH_DAY("2", "3"),
		BIRTH_MONTH(Locale.MONTH_APRIL.getValue(), Locale.MONTH_MAY.getValue()),
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
	
	WIBrowser b;
	WIProfilePersoanlDialog d;
	
	String oldValue, newValue;
	
	@BeforeClass()
	public void setupClass() {
		b = WIBrowserFactory.getNewBrowser(BASE_URL);
		b.login(USERNAME, PASSWORD);
		d = b.getProfile().dlgPersonalInfo; 
		d.go();
	}

	@AfterClass()
	public void cleanClass() {
		b.close();
	}
	
	@Test
	public void testChangeName() {
	    oldValue = d.inpName.getValue();
	    newValue = Data.NAME.getNewValue(oldValue);
	    		
		d.inpName.setValue(newValue);
		d.save();
		
		d.go();
		d.inpName.assertValue(newValue);
	}

	@Test
	public void testChangeSurame() {
	    oldValue = d.inpSurname.getValue();
	    newValue = Data.SURNAME.getNewValue(oldValue);

		d.inpSurname.setValue(newValue);
		d.save();
		
		d.go();
		d.inpSurname.assertValue(newValue);
	}
	
	@Test
	public void testChangeBirthDay() {
	    oldValue = d.inpBirthDay.getValue();
	    newValue = Data.BIRTH_DAY.getNewValue(oldValue);

		d.inpBirthDay.setValue(newValue);
		d.save();

		d.go();
		d.inpBirthDay.assertValue(newValue);
	}
	
	@Test
	public void testChangeBirthMonth() {
	    oldValue = d.inpBirthMonth.getValue();
	    newValue = Data.BIRTH_MONTH.getNewValue(oldValue);

		d.inpBirthMonth.setValue(newValue);
		d.save();

		d.go();
		d.inpBirthMonth.assertValue(newValue);
	}
	
	@Test
	public void testChangeBirthYear() {
	    oldValue = d.inpBirthYear.getValue();
	    newValue = Data.BIRTH_YEAR.getNewValue(oldValue);

		d.inpBirthYear.setValue(newValue);
		d.save();

		d.go();
		d.inpBirthYear.assertValue(newValue);
	}
	
	@Test
	public void testChangeCity() {
	    oldValue = d.inpCity.getValue();
	    newValue = Data.CITY.getNewValue(oldValue);

		d.inpCity.setValue(newValue);
		d.save();

		d.go();
		d.inpCity.assertValue(newValue);
	}
	
	@Test
	public void testChangeBithCity() {
	    oldValue = d.inpBirthCity.getValue();
	    newValue = Data.BIRTH_CITY.getNewValue(oldValue);

		d.inpBirthCity.setValue(newValue);
		d.save();
		
		d.go();
		d.inpBirthCity.assertValue(newValue);
	}
	
}
