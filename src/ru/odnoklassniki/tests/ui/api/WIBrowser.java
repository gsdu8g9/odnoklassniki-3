package ru.odnoklassniki.tests.ui.api;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.dialog.WILoginDialog;
import ru.odnoklassniki.tests.ui.api.pages.WIHomePage;
import ru.odnoklassniki.tests.ui.api.pages.WIProfilePage;
import ru.odnoklassniki.tests.ui.api.selenium.DefaultSelenium2;

public class WIBrowser extends DefaultSelenium2 implements IWIRoad {

	private WIHomePage cntGlobal;

	private WILoginDialog dlgLogin;

	private WIProfilePage pageProfile;

	WIBrowser(String host, int port, String browser, String url) {
		super(host, port, browser, url);
		start();
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
		return null == dlgLogin ? (dlgLogin = new WILoginDialog(this))
		        : dlgLogin;
	}

	public WIHomePage getGlobalContainer() {
		return null == cntGlobal ? (cntGlobal = new WIHomePage(this))
		        : cntGlobal;
	}

	public WIProfilePage getProfile() {
		return null == pageProfile ? (pageProfile = new WIProfilePage(
		        getGlobalContainer().mnuAbout.getRoad())) : pageProfile;
	}

	public WIBrowser login(String name, String password) {
		getLoginDialog().login(name, password);
		return this;
	}

}
