package com.facebook.react.uimanager;

import android.view.View;
import android.view.ViewGroup;
import com.bytedance.apm.util.i;
import com.facebook.react.bridge.UiThreadUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes8.dex */
public class ViewHierarchyDumper {
    public static JSONObject toJSON(View view) throws JSONException {
        UiThreadUtil.assertOnUiThread();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("n", view.getClass().getName());
        jSONObject.put(i.TAG, System.identityHashCode(view));
        Object tag = view.getTag();
        if (tag != null && (tag instanceof String)) {
            jSONObject.put("t", tag);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() > 0) {
                JSONArray jSONArray = new JSONArray();
                for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                    jSONArray.put(i2, toJSON(viewGroup.getChildAt(i2)));
                }
                jSONObject.put("c", jSONArray);
            }
        }
        return jSONObject;
    }
}
