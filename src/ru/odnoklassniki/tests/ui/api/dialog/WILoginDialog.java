package ru.odnoklassniki.tests.ui.api.dialog;

import static ru.odnoklassniki.tests.common.Loggers.ui;
import static ru.odnoklassniki.tests.common.Time.Seconds;
import static ru.odnoklassniki.tests.ui.api.Requirements.argumentNotNull;
import static ru.odnoklassniki.tests.ui.api.locale.Text.BTN_LOGIN;
import ru.odnoklassniki.tests.common.Utils;
import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.controls.WIButton;
import ru.odnoklassniki.tests.ui.api.controls.input.WITextInput;
import ru.odnoklassniki.tests.ui.api.locale.LocaleManager;

public class WILoginDialog extends WIDialog {

	// TODO Need label oriented xpath
	public final WITextInput inpUsername = new WITextInput(this, "field_email", "Username");

	public final WITextInput inpPassword = new WITextInput(this, "field_password", "Password");

	public final WIButton btnLogin = new WIButton.Submit(this, BTN_LOGIN);

	public WILoginDialog(IWIRoad road) {
		super(road, "//*[@class='anonym_login']", "Login");
	}

	public void login(String name, String password) {
		loginSuccessful(name, password);
	}

	public void loginSuccessful(String name, String password) {
		argumentNotNull(name, "name");
		argumentNotNull(password, "password");

		waitVisible();
		inpUsername.setValue(name);
		inpPassword.setValue(password);
		btnLogin.click();
		Utils.pause(Seconds(3), "FIXME Selenium crashes", ui);
		waitInvisible();

		// After login locale can be changed depends on user's settings
		LocaleManager.autoDetect(getBrowser());

		// Login is successful if page shows username property
		getBrowser().getGlobalContainer().proUsername.waitVisible();
	}

}
