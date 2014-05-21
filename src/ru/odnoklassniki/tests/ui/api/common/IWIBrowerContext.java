package ru.odnoklassniki.tests.ui.api.common;

import ru.odnoklassniki.tests.ui.api.WIBrowser;

/**
 * Interface IWIBrowserContext provide access to WIBrowser context where current
 * object was created. Each WI object inherits browser context from its parent
 * object created it. On the top of this hierarchy is an instance of
 * WIBrowser. There are many instances of WIBrowser can be created and each one
 * contains own independent hierarchy of WI objects.
 */
public interface IWIBrowerContext {

	/**
	 * Returns current browser context
	 * 
	 * @return the current browser context
	 */
	WIBrowser getBrowser();

}
