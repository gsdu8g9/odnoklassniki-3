package ru.odnoklassniki.tests.ui.api.controls;

import ru.odnoklassniki.tests.common.Scenario;
import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.locale.Locale;

import com.thoughtworks.selenium.SeleniumException;

public class WIMenuItem extends WIClickable {

	public static final String WI_MENU_ITEM_TYPE = "menu";

	public static WIMenuItem Footer(IWIRoad aRoad, Locale aName) {
		return new WIMenuItem(aRoad, "//*[@id='footer']//*[text()='" + aName.getValue() + "']", aName.getName());
	}
	
	public static WIMenuItem Link(IWIRoad aRoad, Locale aName) {
		return new WIMenuItem(aRoad, "//a[text()='" + aName.getValue() + "']", aName.getName());
	}

	private WIMenuItem(IWIRoad aRoad, String aId, String aName) {
		super(aRoad, aId, aName, WI_MENU_ITEM_TYPE);
	}

	public void click() {
		go();
		// FIXME Simple "click" doesn't work here		
		getBrowser().clickAt(getGlobalID(), "1,1");
		Scenario.ui.info("Click " + this);
	}
	
	@Override
	public XPathBuilderBehaviour getRoadBuilderType() {
		return XPathBuilderBehaviour.USE;
	}

	private String item_id;
	private String item_parent_id;
	private String item_child_id;

	/*
	 * Returns unique item id in the tree
	 */
	private String getId() {
		if (item_id != null) {
			return item_id;
		} else {
			String locator = this.getGlobalID() + "/ancestor::tr[1]/@_afrrk";
			String id = getBrowser().getAttribute(locator);
			if (id.equals("")) {
				throw new RuntimeException(
						"Attribute _affrk expected for menu item, but wasn't detected for locator: "
								+ locator);
			}
			setId(id);
			return id;
		}
	}

	private void setId(String id) {
		item_id = id;
	}

	/*
	 * Returns true if item is root (has no parents in the tree)
	 */
	public Boolean isRoot() {
		return getParentId().equals("");

	}

	/*
	 * Returns generated parent id which is: "" for root item "parentId" for
	 * root's child "parentParentId_parentId" for other children
	 */
	private String getParentId() {
		if (item_parent_id != null) {
			return item_parent_id;
		} else {
			String id = "";
			try {
				id = getBrowser().getAttribute(
						this.getGlobalID() + "/ancestor::tr[1]/@_afrap");
			} catch (SeleniumException e) {
				// expected attribute not found that means element is root
			}
			setParentId(id);
			return id;
		}
	}

	private void setParentId(String id) {
		item_parent_id = id;
	}

	/*
	 * Returns generated parent id for children of this item
	 */
	private String getChildId() {
		if (item_child_id != null) {
			return item_child_id;
		} else {
			String suffix = getId();
			String prefix = "";
			if (!isRoot()) {
				prefix = getParentId() + "_";
			}
			String id = prefix + suffix;
			setChildId(id);
			return id;
		}
	}

	private void setChildId(String id) {
		item_child_id = id;
	}

}
