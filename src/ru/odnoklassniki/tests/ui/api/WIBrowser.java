package ru.odnoklassniki.tests.ui.api;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.dialog.WILoginDialog;
import ru.odnoklassniki.tests.ui.api.pages.WIGlobalContainer;
import ru.odnoklassniki.tests.ui.api.pages.WIProfilePage;
import ru.odnoklassniki.tests.ui.api.selenium.BrowserService;


public class WIBrowser extends BrowserService implements IWIRoad {

	private WIGlobalContainer cntGlobal;

	private WILoginDialog dlgLogin;

	private WIProfilePage pageProfile;

	WIBrowser(String host, int port, String browser, String url) {
		super(host, port, browser, url);
	}

	@Override
	public void go() {
		// Browser is accessible for any operation always so do nothing here
	}

	@Override
	public WIBrowser getBrowser() {
		return this;
	}

	@Override
	public IWIRoad getParent() {
		return null;
	}

	public WILoginDialog getLoginDialog() {
		return dlgLogin == null ? (dlgLogin = new WILoginDialog(this)) : dlgLogin;
	}

	public WIGlobalContainer getGlobalContainer() {
		return cntGlobal == null ? (cntGlobal = new WIGlobalContainer(this)) : cntGlobal;
	}

	public WIProfilePage getProfile() {
		return pageProfile == null ? (pageProfile = new WIProfilePage(getGlobalContainer().mnuAbout.getRoad()))
				: pageProfile;
	}

	public WIBrowser login(String name, String password) {
		getLoginDialog().login(name, password);
		return this;
	}

}
