package ru.odnoklassniki.tests.ui.profile;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ru.odnoklassniki.tests.ui.api.WIBrowser;
import ru.odnoklassniki.tests.ui.api.WIBrowserFactory;
import ru.odnoklassniki.tests.ui.api.dialog.WIProfilePersoanlDialog;


public class PersonalInfoEditTest {

	private static final String USERNAME = "dummy_user123";
	private static final String PASSWORD = "pwd123456";
	
	private static enum Data {
		NAME("����", "����"),
		SURNAME("������", "������"),
		BIRTH_DAY("1", "2"),
		BIRTH_MONTH("3", "4"),
		BITH_YEAR("2005", "2006"),
		CITY("�����-���������, ������", "�. ��������� (�����-��������� �), ������"),
		BIRTH_CITY("������, ������", "�. ���������� (������ �), ������");
		
		private String[] values;
		
		Data(String... values) {
			this.values = values;
		}
		
		public String getNewValue(String oldValue) {
			return values[0] == oldValue ? values[1] : values[0];
		}
	};
	
	WIBrowser b;
	WIProfilePersoanlDialog d;
	
	String oldValue, newValue;
	
	@BeforeClass()
	public void setupClass() {
		b = WIBrowserFactory.getNewBrowser("http://www.odnoklassniki.ru");
//		b.login("dummy_user1@mail.ru", "pwd123456");
		b.login(USERNAME, PASSWORD);
		d = b.getProfile().dlgPersonalInfo; 
	}

	@Test
	public void testChangeName() {
		d.go();
		
	    oldValue = d.inpName.getValue();
	    newValue = Data.NAME.getNewValue(oldValue);
	    		
		d.inpName.setValue(newValue);
		d.save();
		// TODO check name value
	}

	@Test
	public void testChangeSurame() {
		d.go();

	    oldValue = d.inpSurname.getValue();
	    newValue = Data.SURNAME.getNewValue(oldValue);

		d.inpSurname.setValue(newValue);
		d.save();
		// TODO check name value
	}
	
	@Test
	public void testChangeCity() {
		d.go();

	    oldValue = d.inpCity.getValue();
	    newValue = Data.CITY.getNewValue(oldValue);

		d.inpCity.setValue(newValue);
		d.save();
		// TODO check name value
	}
	
	@Test
	public void testChangeBithCity() {
		d.go();

	    oldValue = d.inpBirthCity.getValue();
	    newValue = Data.BIRTH_CITY.getNewValue(oldValue);

		d.inpBirthCity.setValue(newValue);
		d.save();
		// TODO check name value
	}
	
}
