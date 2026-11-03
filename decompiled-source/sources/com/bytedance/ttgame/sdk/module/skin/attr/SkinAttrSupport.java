package com.bytedance.ttgame.sdk.module.skin.attr;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.bytedance.ttgame.gsdk_base_common_ui.R;
import com.bytedance.ttgame.sdk.module.skin.constants.SkinConfig;
import com.facebook.internal.security.CertificateUtil;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class SkinAttrSupport {
    public static List<SkinView> getSkinViews(Activity activity, int i2) {
        ArrayList arrayList = new ArrayList();
        addSkinViews((ViewGroup) activity.findViewById(i2), arrayList);
        return arrayList;
    }

    public static List<SkinView> getSkinViewsFromDialog(View view, int i2) {
        ArrayList arrayList = new ArrayList();
        addSkinViews((ViewGroup) view.findViewById(i2), arrayList);
        return arrayList;
    }

    public static List<SkinView> getSkinViewsFromPopupWindow(View view, int i2) {
        ArrayList arrayList = new ArrayList();
        addSkinViews((ViewGroup) view.findViewById(i2), arrayList);
        return arrayList;
    }

    public static void addSkinViews(View view, List<SkinView> list) {
        SkinView skinView = getSkinView(view);
        if (skinView != null) {
            list.add(skinView);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                addSkinViews(viewGroup.getChildAt(i2), list);
            }
        }
    }

    public static SkinView getSkinView(View view) {
        if (view == null) {
            return null;
        }
        Object tag = view.getTag(R.id.skin_tag_id);
        if (tag == null) {
            tag = view.getTag();
        }
        if (tag instanceof String) {
            List<SkinAttr> parseTag = parseTag((String) tag);
            if (parseTag.isEmpty()) {
                return null;
            }
            changeViewTag(view);
            return new SkinView(view, parseTag);
        }
        return null;
    }

    private static void changeViewTag(View view) {
        if (view.getTag(R.id.skin_tag_id) == null) {
            view.setTag(R.id.skin_tag_id, view.getTag());
            view.setTag(null);
        }
    }

    private static List<SkinAttr> parseTag(String str) {
        String[] split;
        SkinAttrType supportAttrType;
        Log.w(SkinConfig.TAG, "parse tag: " + str);
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        for (String str2 : str.split("[|]")) {
            if (str2.startsWith(SkinConfig.SKIN_PREFIX)) {
                String[] split2 = str2.split(CertificateUtil.DELIMITER);
                if (split2.length == 3 && (supportAttrType = getSupportAttrType(split2[2])) != null) {
                    arrayList.add(new SkinAttr(supportAttrType, split2[1]));
                }
            }
        }
        return arrayList;
    }

    private static SkinAttrType getSupportAttrType(String str) {
        SkinAttrType[] values;
        for (SkinAttrType skinAttrType : SkinAttrType.values()) {
            if (skinAttrType.getAttrType().equals(str)) {
                return skinAttrType;
            }
        }
        return null;
    }
}
