package ru.odnoklassniki.tests.common;

import org.slf4j.Logger;

public class Scenario {
	
	public final static Logger ws = LogFactory.getLogger("ws");
	public final static Logger ui = LogFactory.getLogger("ui");
	public final static Logger core = LogFactory.getLogger("core");
	public final static Logger framework = LogFactory.getLogger("framework");
	public final static Logger exec = LogFactory.getLogger("exec");
	public final static Logger selenium = LogFactory.getLogger("selenium");
	public final static Logger cli = LogFactory.getLogger("cli");
	
	private final static Logger action = LogFactory.getLogger("action");

	public static void action(String message, Object... params) {
		action.info(Utils.format(message, params));
	}
	
}
