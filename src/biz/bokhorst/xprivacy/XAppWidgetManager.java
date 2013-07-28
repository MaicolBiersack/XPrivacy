package biz.bokhorst.xprivacy;

import java.util.ArrayList;

import android.appwidget.AppWidgetProviderInfo;
import android.util.Log;

import de.robv.android.xposed.XC_MethodHook.MethodHookParam;

public class XAppWidgetManager extends XHook {

	public XAppWidgetManager(String methodName, String restrictionName) {
		super(restrictionName, methodName, null);
	}

	public String getClassName() {
		return "android.appwidget.AppWidgetManager";
	}

	// public List<AppWidgetProviderInfo> getInstalledProviders()
	// frameworks/base/core/java/android/appwidget/AppWidgetManager.java
	// http://developer.android.com/reference/android/appwidget/AppWidgetManager.html

	@Override
	protected void before(MethodHookParam param) throws Throwable {
		// Do nothing
	}

	@Override
	protected void after(MethodHookParam param) throws Throwable {
		String methodName = param.method.getName();
		if (methodName.equals("getInstalledProviders")) {
			if (param.getResult() != null && isRestricted(param))
				param.setResult(new ArrayList<AppWidgetProviderInfo>());
		} else
			Util.log(this, Log.WARN, "Unknown method=" + methodName);
	}
}
