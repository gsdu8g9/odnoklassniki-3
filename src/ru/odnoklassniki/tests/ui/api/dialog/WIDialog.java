package ru.odnoklassniki.tests.ui.api.dialog;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.controls.WIElement;
import ru.odnoklassniki.tests.ui.api.locale.Text;

public class WIDialog extends WIElement {

	public static final String WI_DIALOG_TYPE = "dialog";

	public WIDialog(IWIRoad road, Text name) {
		super(
		        road,
		        "//*[@id='mp_mm_cont' and .//*[@class='panelLayer_head_headerSimple' and text()='"
		                + name.getValue() + "']]", name.getName(),
		        WI_DIALOG_TYPE);
	}

	public WIDialog(IWIRoad road, String locator, String name) {
		super(road, locator, name, WI_DIALOG_TYPE);
	}

	@Override
	public XPathBuilderBehaviour getRoadBuilderType() {
		return XPathBuilderBehaviour.GLOBAL;
	}

}
