Selenium.prototype.doStoreXPath = function(locator, identifier) {
    var element = this.browserbot.findElement(locator);
    storedVars[identifier] = getElementXPath(element);
}

function getElementXPath(elt)
{
     var path = "";
     for (; elt && elt.nodeType == 1; elt = elt.parentNode)
     {
   	idx = getElementIdx(elt);
	xname = elt.tagName;
	if (idx > 1) xname += "[" + idx + "]";
	path = "/" + xname + path;
     }
     return "/" + path;	
}

function getElementIdx(elt)
{
    var count = 1;
    for (var sib = elt.previousSibling; sib ; sib = sib.previousSibling)
    {
        if(sib.nodeType == 1 && sib.tagName == elt.tagName)	count++
    }
    return count;
}

Selenium.prototype.doAlert = function(value, varName) {
	alert(value);
};

Selenium.prototype.doSetShouldHighlightElement = function(value) {
	this.browserbot.setShouldHighlightElement(value);
};

Selenium.prototype.isXElementPresent = function(locator, identifier) {
    /**
    * Verifies that the specified element is somewhere on the page.
    * @param locator an <a href="#locators">element locator</a>
    * @return boolean true if the element is present, false otherwise
    */
    var element = this.browserbot.findElement(locator);
    if (element == null) {
        return false;
    }

    var match = locator.match(/.*(\[.*?\])$/);
    if (match) {
       storedVars[identifier] = getElementXPath(element) + match[1];
    } else {
       storedVars[identifier] = getElementXPath(element);
    }

    return true;
};

// ***********************************************************************
// Selenium doesn't allow to register locator with a numbers in the name
// To fix this go to selenium-server.jar/core/scripts/htmlutils.js*
// Find function parse_locator and replace string 
//    var result = locator.match(/^([A-Za-z]+)=(.+)/);
// to string 
//    var result = locator.match(/^([A-Za-z0-9]+)=(.+)/);
//
// To fix Selenium IDE go to browser profile find htmlutils.js and
// made the same replacement. Restart browser after that but not only IDE 
// ***********************************************************************
PageBot.prototype.locateElementByXPath2 = function(xpath, inDocument, inWindow) {
	enableXPath2(this.xpathEvaluator);
    var results = this.locateElementByXPath(xpath, inDocument, inWindow);
	disableXPath2(this.xpathEvaluator);
    return results;
};

// Override existing method to be able use XPath2
Selenium.prototype.getXpathCount = function(xpath) {
	var useXPath2 = xpath.match(/^xpath2=.*/);
	if (useXPath2) {
		enableXPath2(this.browserbot.xpathEvaluator);
		xpath = xpath.replace( /^xpath2?=/, "")
	}

    var result = this.browserbot.evaluateXPathCount(xpath, this.browserbot.getDocument());
    
	if (useXPath2) {
		disableXPath2(this.browserbot.xpathEvaluator);
	}
	
    return result;
}

var old_isAllowNativeXPath;
var old_currentEngine;

function enableXPath2(xpathEvaluator) {
    old_isAllowNativeXPath = xpathEvaluator.isAllowNativeXPath(false);
    old_currentEngine = xpathEvaluator.getCurrentEngine();
    xpathEvaluator.setAllowNativeXPath(false);
    // Function "matches" supports only by ajaxslt XPAth engine 
    xpathEvaluator.setCurrentEngine('ajaxslt');
}

function disableXPath2(xpathEvaluator) {
    xpathEvaluator.setAllowNativeXPath(old_isAllowNativeXPath);
    xpathEvaluator.setCurrentEngine(old_currentEngine);
}

Selenium.prototype.getValues = function(xpath, exec) {

	var useXPath2 = xpath.match(/^xpath2=.*/);
	if (useXPath2) {
		enableXPath2(this.browserbot.xpathEvaluator);
		xpath = xpath.replace( /^xpath2?=/, "")
	}
	
    var inDocument = this.browserbot.getDocument();
    var inWindow = this.browserbot.currentWindow;
    var x = this.browserbot.locateElementsByXPath(xpath, inDocument, inWindow);
    
	if (useXPath2) {
		disableXPath2(this.browserbot.xpathEvaluator);
	}

	var result = new Array();
	exec = exec.replace( /{item}/, "x[i]")
	for(i=0;i<x.length;i++) { 
		result[i] = eval(exec); 
	};
	
	return result; 	
}

Selenium.prototype.doValues = function(xpath, exec) {
	alert(this.getValues(xpath, exec));
}

Selenium.prototype.doEval = function(exec) {
    alert(eval(exec));
}

Selenium.prototype.getElementEval = function(xpath, exec) {

    var useXPath2 = xpath.match(/^xpath2=.*/);
    if (useXPath2) {
        enableXPath2(this.browserbot.xpathEvaluator);
        xpath = xpath.replace( /^xpath2?=/, "")
    }

    var inDocument = this.browserbot.getDocument();
    var inWindow = this.browserbot.currentWindow;
    var elem = this.browserbot.locateElementByXPath(xpath, inDocument, inWindow);

    if (useXPath2) {
        disableXPath2(this.browserbot.xpathEvaluator);
    }

    return elem == null ? "" : eval(exec);
}

Selenium.prototype.doElementEval = function(xpath, exec) {
    alert(this.getElementEval(xpath, exec));
}
