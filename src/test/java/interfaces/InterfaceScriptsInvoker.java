package interfaces;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

public interface InterfaceScriptsInvoker {
	JavascriptExecutor javaScriptInvoke();
	Actions actionBuilder();
}