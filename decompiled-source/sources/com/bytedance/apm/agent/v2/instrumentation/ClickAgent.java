package com.bytedance.apm.agent.v2.instrumentation;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import com.bytedance.apm.agent.monitor.a;
import com.bytedance.apm.c;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class ClickAgent {
    private static final String ACTION_NAME = "view_click";
    private static final String CLICK_TYPE = "click_type";
    private static final String VIEW_ID = "view_id";
    private static final String VIEW_NAME = "view_name";
    private static final String VIEW_TEXT = "view_text";
    private static final int VIEW_TEXT_LENGTH_LIMIT = 100;

    public static void onClick(View view) {
        CharSequence text;
        if (view == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            Resources resources = view.getContext().getResources();
            if (view.getId() != -1) {
                jSONObject.put(VIEW_ID, view.getId());
                jSONObject.put(VIEW_NAME, resources.getResourceEntryName(view.getId()));
            }
            if ((view instanceof TextView) && (text = ((TextView) view).getText()) != null) {
                String charSequence = text.toString();
                if (charSequence.length() > 100) {
                    charSequence = charSequence.substring(0, 100);
                }
                jSONObject.put(VIEW_TEXT, charSequence);
            }
            if (view.getParent() != null) {
                String simpleName = view.getParent().getClass().getSimpleName();
                if (view.getParent().getParent() != null) {
                    simpleName = view.getParent().getParent().getClass().getSimpleName() + "#" + simpleName + "#" + view.getClass().getSimpleName();
                }
                jSONObject.put("view_path", simpleName);
            }
            jSONObject.put(CLICK_TYPE, "View#OnClick");
            a.monitorUIAction(ACTION_NAME, "", jSONObject);
        } catch (Exception e2) {
            if (c.isDebugMode()) {
                e2.printStackTrace();
            }
        }
    }

    public static void onTabChanged(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(VIEW_NAME, str);
            jSONObject.put(CLICK_TYPE, "TabHost#OnTabChanged");
            a.monitorUIAction(ACTION_NAME, "", jSONObject);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
