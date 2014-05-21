package ru.odnoklassniki.tests.ui.api.selenium;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import ru.odnoklassniki.tests.common.Loggers;
import ru.odnoklassniki.tests.common.Utils;

/**
 * Class BrowserService cooperates functionality of two techniques - Selenium
 * and WebDriver
 * 
 */
public class BrowserService implements Selenium2, WebDriver {

	public static final long DEFAULT_SERVER_TIMEOUT = 30000;
	
	private Selenium2 m_selenium;
	private WebDriver m_webdriver;

	protected BrowserService(String aHost, int aPort, String aBrowser, String aUrl) {

		// Use combination of Selenium and WebDriver
		// http://code.google.com/p/selenium/wiki/SeleniumEmulation

		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName("firefox");
		m_selenium = new DefaultSelenium2(aHost, aPort, aBrowser, aUrl);
		m_selenium.start();
	}
	
	public String getCurrentBase() {
		URL url = Utils.getURL(getCurrentUrl());
		String result = url.getProtocol() + "://" + url.getHost();
		if (url.getPort() != -1) {
			result += ":" + url.getPort();
		}
		return result;
	}

	/**************************************************************************
	 * THE REST OF FILE IS AUTO GENERATED CODE. DON'T MODIFY IT MANUALLY. To
	 * generate code automatically go to menu "Source/Generate Delegate Methods"
	 * and choose m_selenium and m_webwriver fields.
	 *************************************************************************/

	/***************** Delegate WebDriver implementation **************/

	public void get(String url) {
		m_webdriver.get(url);
	}

	public String getCurrentUrl() {
		return m_webdriver.getCurrentUrl();
	}

	public List<WebElement> findElements(By by) {
		return m_webdriver.findElements(by);
	}

	public WebElement findElement(By by) {
		return m_webdriver.findElement(by);
	}

	public String getPageSource() {
		return m_webdriver.getPageSource();
	}

	public void quit() {
		Loggers.ui.info("Close browser");
		m_webdriver.quit();
	}

	public Set<String> getWindowHandles() {
		return m_webdriver.getWindowHandles();
	}

	public String getWindowHandle() {
		return m_webdriver.getWindowHandle();
	}

	public TargetLocator switchTo() {
		return m_webdriver.switchTo();
	}

	public Navigation navigate() {
		return m_webdriver.navigate();
	}

	public Options manage() {
		return m_webdriver.manage();
	}

	/***************** Delegate Selenium implementation **************/

	public void setShouldHighlightElement(boolean value) {
		m_selenium.setShouldHighlightElement(value);
	}

	public void setExtensionJs(String extensionJs) {
		m_selenium.setExtensionJs(extensionJs);
	}

	public void start() {
		m_selenium.start();
	}

	public void start(String optionsString) {
		m_selenium.start(optionsString);
	}

	public void start(Object optionsObject) {
		m_selenium.start(optionsObject);
	}

	public void stop() {
		m_selenium.stop();
	}

	public void showContextualBanner() {
		m_selenium.showContextualBanner();
	}

	public void showContextualBanner(String className, String methodName) {
		m_selenium.showContextualBanner(className, methodName);
	}

	public void click(String locator) {
//		waitForServer();
		m_selenium.click(locator);
	}

	public void doubleClick(String locator) {
//		waitForServer();
		m_selenium.doubleClick(locator);
	}

	public void contextMenu(String locator) {
		m_selenium.contextMenu(locator);
	}

	public void clickAt(String locator, String coordString) {
//		waitForServer();
		m_selenium.clickAt(locator, coordString);
	}

	public void doubleClickAt(String locator, String coordString) {
		m_selenium.doubleClickAt(locator, coordString);
	}

	public void contextMenuAt(String locator, String coordString) {
		m_selenium.contextMenuAt(locator, coordString);
	}

	public void fireEvent(String locator, String eventName) {
		m_selenium.fireEvent(locator, eventName);
	}

	public void focus(String locator) {
		m_selenium.focus(locator);
	}

	public void keyPress(String locator, String keySequence) {
		m_selenium.keyPress(locator, keySequence);
	}

	public void shiftKeyDown() {
		m_selenium.shiftKeyDown();
	}

	public void shiftKeyUp() {
		m_selenium.shiftKeyUp();
	}

	public void metaKeyDown() {
		m_selenium.metaKeyDown();
	}

	public void metaKeyUp() {
		m_selenium.metaKeyUp();
	}

	public void altKeyDown() {
		m_selenium.altKeyDown();
	}

	public void altKeyUp() {
		m_selenium.altKeyUp();
	}

	public void controlKeyDown() {
		m_selenium.controlKeyDown();
	}

	public void controlKeyUp() {
		m_selenium.controlKeyUp();
	}

	public void keyDown(String locator, String keySequence) {
		m_selenium.keyDown(locator, keySequence);
	}

	public void keyUp(String locator, String keySequence) {
		m_selenium.keyUp(locator, keySequence);
	}

	public void mouseOver(String locator) {
		m_selenium.mouseOver(locator);
	}

	public void mouseOut(String locator) {
		m_selenium.mouseOut(locator);
	}

	public void mouseDown(String locator) {
		m_selenium.mouseDown(locator);
	}

	public void mouseDownRight(String locator) {
		m_selenium.mouseDownRight(locator);
	}

	public void mouseDownAt(String locator, String coordString) {
		m_selenium.mouseDownAt(locator, coordString);
	}

	public void mouseDownRightAt(String locator, String coordString) {
		m_selenium.mouseDownRightAt(locator, coordString);
	}

	public void mouseUp(String locator) {
		m_selenium.mouseUp(locator);
	}

	public void mouseUpRight(String locator) {
		m_selenium.mouseUpRight(locator);
	}

	public void mouseUpAt(String locator, String coordString) {
		m_selenium.mouseUpAt(locator, coordString);
	}

	public void mouseUpRightAt(String locator, String coordString) {
		m_selenium.mouseUpRightAt(locator, coordString);
	}

	public void mouseMove(String locator) {
		m_selenium.mouseMove(locator);
	}

	public void mouseMoveAt(String locator, String coordString) {
		m_selenium.mouseMoveAt(locator, coordString);
	}

	public void type(String locator, String value) {
//		waitForServer();
		m_selenium.type(locator, value);
	}

	public void typeKeys(String locator, String value) {
		m_selenium.typeKeys(locator, value);
	}

	public void setSpeed(String value) {
		m_selenium.setSpeed(value);
	}

	public String getSpeed() {
		return m_selenium.getSpeed();
	}

	public String getLog() {
		return m_selenium.getLog();
	}

	public void check(String locator) {
		m_selenium.check(locator);
	}

	public void uncheck(String locator) {
		m_selenium.uncheck(locator);
	}

	public void select(String selectLocator, String optionLocator) {
//		waitForServer();
		m_selenium.select(selectLocator, optionLocator);
	}

	public void addSelection(String locator, String optionLocator) {
		m_selenium.addSelection(locator, optionLocator);
	}

	public void removeSelection(String locator, String optionLocator) {
		m_selenium.removeSelection(locator, optionLocator);
	}

	public void removeAllSelections(String locator) {
		m_selenium.removeAllSelections(locator);
	}

	public void submit(String formLocator) {
		m_selenium.submit(formLocator);
	}

	public void open(String url, String ignoreResponseCode) {
		m_selenium.open(url, ignoreResponseCode);
	}

	public void open(String url) {
		Loggers.ui.info("Open browser " + url);
		m_selenium.open(url);
	}

	public void open(URL url) {
		Loggers.ui.info("Open browser " + url);
		m_selenium.open(url);
	}

	public void openWindow(String url, String windowID) {
		m_selenium.openWindow(url, windowID);
	}

	public void selectWindow(String windowID) {
		m_selenium.selectWindow(windowID);
	}

	public void selectPopUp(String windowID) {
		m_selenium.selectPopUp(windowID);
	}

	public void deselectPopUp() {
		m_selenium.deselectPopUp();
	}

	public void selectFrame(String locator) {
		m_selenium.selectFrame(locator);
	}

	public boolean getWhetherThisFrameMatchFrameExpression(
			String currentFrameString, String target) {
		return m_selenium.getWhetherThisFrameMatchFrameExpression(
				currentFrameString, target);
	}

	public boolean getWhetherThisWindowMatchWindowExpression(
			String currentWindowString, String target) {
		return m_selenium.getWhetherThisWindowMatchWindowExpression(
				currentWindowString, target);
	}

	public void waitForPopUp(String windowID, String timeout) {
		m_selenium.waitForPopUp(windowID, timeout);
	}

	public void chooseCancelOnNextConfirmation() {
		m_selenium.chooseCancelOnNextConfirmation();
	}

	public void chooseOkOnNextConfirmation() {
		m_selenium.chooseOkOnNextConfirmation();
	}

	public void answerOnNextPrompt(String answer) {
		m_selenium.answerOnNextPrompt(answer);
	}

	public void goBack() {
		m_selenium.goBack();
	}

	public void refresh() {
		m_selenium.refresh();
	}

	public void close() {
		Loggers.ui.info("Close browser");
		m_selenium.close();
	}

	public boolean isAlertPresent() {
		return m_selenium.isAlertPresent();
	}

	public boolean isPromptPresent() {
		return m_selenium.isPromptPresent();
	}

	public boolean isConfirmationPresent() {
		return m_selenium.isConfirmationPresent();
	}

	public String getAlert() {
		return m_selenium.getAlert();
	}

	public String getConfirmation() {
		return m_selenium.getConfirmation();
	}

	public String getPrompt() {
		return m_selenium.getPrompt();
	}

	public String getLocation() {
		return m_selenium.getLocation();
	}

	public String getTitle() {
		return m_selenium.getTitle();
	}

	public String getBodyText() {
		return m_selenium.getBodyText();
	}

	public String getValue(String locator) {
		return m_selenium.getValue(locator);
	}

	public String getText(String locator) {
		return m_selenium.getText(locator);
	}

	public void highlight(String locator) {
		m_selenium.highlight(locator);
	}

	public String getEval(String script) {
		return m_selenium.getEval(script);
	}

	public boolean isChecked(String locator) {
		return m_selenium.isChecked(locator);
	}

	public String getTable(String tableCellAddress) {
		return m_selenium.getTable(tableCellAddress);
	}

	public String[] getSelectedLabels(String selectLocator) {
		return m_selenium.getSelectedLabels(selectLocator);
	}

	public String getSelectedLabel(String selectLocator) {
		return m_selenium.getSelectedLabel(selectLocator);
	}

	public String[] getSelectedValues(String selectLocator) {
		return m_selenium.getSelectedValues(selectLocator);
	}

	public String getSelectedValue(String selectLocator) {
		return m_selenium.getSelectedValue(selectLocator);
	}

	public String[] getSelectedIndexes(String selectLocator) {
		return m_selenium.getSelectedIndexes(selectLocator);
	}

	public String getSelectedIndex(String selectLocator) {
		return m_selenium.getSelectedIndex(selectLocator);
	}

	public String[] getSelectedIds(String selectLocator) {
		return m_selenium.getSelectedIds(selectLocator);
	}

	public String getSelectedId(String selectLocator) {
		return m_selenium.getSelectedId(selectLocator);
	}

	public boolean isSomethingSelected(String selectLocator) {
		return m_selenium.isSomethingSelected(selectLocator);
	}

	public String[] getSelectOptions(String selectLocator) {
		return m_selenium.getSelectOptions(selectLocator);
	}

	public String getAttribute(String attributeLocator) {
		return m_selenium.getAttribute(attributeLocator);
	}

	public boolean isTextPresent(String pattern) {
		return m_selenium.isTextPresent(pattern);
	}

	public boolean isElementPresent(String locator) {
		return m_selenium.isElementPresent(locator);
	}

	public boolean isVisible(String locator) {
		return m_selenium.isVisible(locator);
	}

	public boolean isEditable(String locator) {
		return m_selenium.isEditable(locator);
	}

	public String[] getAllButtons() {
		return m_selenium.getAllButtons();
	}

	public String[] getAllLinks() {
		return m_selenium.getAllLinks();
	}

	public String[] getAllFields() {
		return m_selenium.getAllFields();
	}

	public String[] getAttributeFromAllWindows(String attributeName) {
		return m_selenium.getAttributeFromAllWindows(attributeName);
	}

	public void dragdrop(String locator, String movementsString) {
		m_selenium.dragdrop(locator, movementsString);
	}

	public void setMouseSpeed(String pixels) {
		m_selenium.setMouseSpeed(pixels);
	}

	public Number getMouseSpeed() {
		return m_selenium.getMouseSpeed();
	}

	public void dragAndDrop(String locator, String movementsString) {
		m_selenium.dragAndDrop(locator, movementsString);
	}

	public void dragAndDropToObject(String locatorOfObjectToBeDragged,
			String locatorOfDragDestinationObject) {
		m_selenium.dragAndDropToObject(locatorOfObjectToBeDragged,
				locatorOfDragDestinationObject);
	}

	public void windowFocus() {
		m_selenium.windowFocus();
	}

	public void windowMaximize() {
		m_selenium.windowMaximize();
	}

	public String[] getAllWindowIds() {
		return m_selenium.getAllWindowIds();
	}

	public String[] getAllWindowNames() {
		return m_selenium.getAllWindowNames();
	}

	public String[] getAllWindowTitles() {
		return m_selenium.getAllWindowTitles();
	}

	public String getHtmlSource() {
		return m_selenium.getHtmlSource();
	}

	public void getHtmlSource(File file) {
		m_selenium.getHtmlSource(file);
	}

	public void setCursorPosition(String locator, String position) {
		m_selenium.setCursorPosition(locator, position);
	}

	public Number getElementIndex(String locator) {
		return m_selenium.getElementIndex(locator);
	}

	public boolean isOrdered(String locator1, String locator2) {
		return m_selenium.isOrdered(locator1, locator2);
	}

	public Number getElementPositionLeft(String locator) {
		return m_selenium.getElementPositionLeft(locator);
	}

	public Number getElementPositionTop(String locator) {
		return m_selenium.getElementPositionTop(locator);
	}

	public Number getElementWidth(String locator) {
		return m_selenium.getElementWidth(locator);
	}

	public Number getElementHeight(String locator) {
		return m_selenium.getElementHeight(locator);
	}

	public Number getCursorPosition(String locator) {
		return m_selenium.getCursorPosition(locator);
	}

	public String getExpression(String expression) {
		return m_selenium.getExpression(expression);
	}

	public Number getXpathCount(String xpath) {
		return m_selenium.getXpathCount(xpath);
	}

	public Number getCssCount(String css) {
		return m_selenium.getCssCount(css);
	}

	public void assignId(String locator, String identifier) {
		m_selenium.assignId(locator, identifier);
	}

	public void allowNativeXpath(String allow) {
		m_selenium.allowNativeXpath(allow);
	}

	public void ignoreAttributesWithoutValue(String ignore) {
		m_selenium.ignoreAttributesWithoutValue(ignore);
	}

	public void waitForCondition(String script, String timeout) {
		m_selenium.waitForCondition(script, timeout);
	}

	public void setTimeout(String timeout) {
		m_selenium.setTimeout(timeout);
	}

	public void waitForPageToLoad(String timeout) {
		m_selenium.waitForPageToLoad(timeout);
	}

	public void waitForFrameToLoad(String frameAddress, String timeout) {
		m_selenium.waitForFrameToLoad(frameAddress, timeout);
	}

	public String getCookie() {
		return m_selenium.getCookie();
	}

	public String getCookieByName(String name) {
		return m_selenium.getCookieByName(name);
	}

	public boolean isCookiePresent(String name) {
		return m_selenium.isCookiePresent(name);
	}

	public void createCookie(String nameValuePair, String optionsString) {
		m_selenium.createCookie(nameValuePair, optionsString);
	}

	public void deleteCookie(String name, String optionsString) {
		m_selenium.deleteCookie(name, optionsString);
	}

	public void deleteAllVisibleCookies() {
		m_selenium.deleteAllVisibleCookies();
	}

	public void setBrowserLogLevel(String logLevel) {
		m_selenium.setBrowserLogLevel(logLevel);
	}

	public void runScript(String script) {
		m_selenium.runScript(script);
	}

	public void addLocationStrategy(String strategyName,
			String functionDefinition) {
		m_selenium.addLocationStrategy(strategyName, functionDefinition);
	}

	public void captureEntirePageScreenshot(String filename, String kwargs) {
		m_selenium.captureEntirePageScreenshot(filename, kwargs);
	}

	public void rollup(String rollupName, String kwargs) {
		m_selenium.rollup(rollupName, kwargs);
	}

	public void addScript(String scriptContent, String scriptTagId) {
		m_selenium.addScript(scriptContent, scriptTagId);
	}

	public void removeScript(String scriptTagId) {
		m_selenium.removeScript(scriptTagId);
	}

	public void useXpathLibrary(String libraryName) {
		m_selenium.useXpathLibrary(libraryName);
	}

	public void setContext(String context) {
		m_selenium.setContext(context);
	}

	public void attachFile(String fieldLocator, String fileLocator) {
		m_selenium.attachFile(fieldLocator, fileLocator);
	}

	public void captureScreenshot(String filename) {
		m_selenium.captureScreenshot(filename);
	}

	public String captureScreenshotToString() {
		return m_selenium.captureScreenshotToString();
	}

	public String captureNetworkTraffic(String type) {
		return m_selenium.captureNetworkTraffic(type);
	}

	public void addCustomRequestHeader(String key, String value) {
		m_selenium.addCustomRequestHeader(key, value);
	}

	public String captureEntirePageScreenshotToString(String kwargs) {
		return m_selenium.captureEntirePageScreenshotToString(kwargs);
	}

	public void shutDownSeleniumServer() {
		m_selenium.shutDownSeleniumServer();
	}

	public String retrieveLastRemoteControlLogs() {
		return m_selenium.retrieveLastRemoteControlLogs();
	}

	public void keyDownNative(String keycode) {
		m_selenium.keyDownNative(keycode);
	}

	public void keyUpNative(String keycode) {
		m_selenium.keyUpNative(keycode);
	}

	public void keyPressNative(String keycode) {
		m_selenium.keyPressNative(keycode);
	}

}
