package com.bytedance.praisedialoglib.manager;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.praisedialoglib.depend.d;
import com.bytedance.praisedialoglib.depend.e;
import com.bytedance.praisedialoglib.depend.f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: PraiseDialogCfgManager.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11117a = "PraiseDialogCfgManager";

    /* renamed from: b  reason: collision with root package name */
    private d f11118b;

    /* renamed from: c  reason: collision with root package name */
    private com.bytedance.praisedialoglib.depend.a f11119c;

    /* renamed from: d  reason: collision with root package name */
    private e f11120d;

    /* renamed from: e  reason: collision with root package name */
    private com.bytedance.praisedialoglib.depend.c f11121e;

    /* renamed from: f  reason: collision with root package name */
    private String f11122f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f11123g;

    /* renamed from: h  reason: collision with root package name */
    private String f11124h;

    /* renamed from: i  reason: collision with root package name */
    private String f11125i;

    /* renamed from: j  reason: collision with root package name */
    private String f11126j;
    private String k;
    private com.bytedance.praisedialoglib.callback.b l;
    private boolean m;
    private f n;

    /* compiled from: PraiseDialogCfgManager.java */
    /* renamed from: com.bytedance.praisedialoglib.manager.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    private static class C0165a {

        /* renamed from: a  reason: collision with root package name */
        private static a f11127a = new a();

        private C0165a() {
        }
    }

    private a() {
        this.f11122f = "";
        this.f11124h = "";
        this.f11125i = "";
        this.f11126j = "";
        this.k = "";
        this.m = false;
    }

    public static a getInstance() {
        return C0165a.f11127a;
    }

    public void init(com.bytedance.praisedialoglib.depend.b bVar) {
        if (bVar != null) {
            this.f11118b = bVar.getNetworkConfig();
            this.f11119c = bVar.getAppConfig();
            this.f11120d = bVar.getUiConfig();
            this.f11121e = bVar.getLocalConfig();
            this.n = bVar.a();
        }
    }

    public String executePost(int i2, String str, Map<String, String> map) {
        d dVar = this.f11118b;
        if (dVar != null) {
            return dVar.executePost(i2, str, map);
        }
        return null;
    }

    public void a(Runnable runnable) {
        f fVar = this.n;
        if (fVar != null) {
            fVar.a(runnable);
        } else {
            com.bytedance.praisedialoglib.ThreadPool.c.submitRunnable(runnable);
        }
    }

    public boolean isMatchLocalCondition() {
        com.bytedance.praisedialoglib.depend.c cVar = this.f11121e;
        if (cVar != null) {
            return cVar.isMatchLocalCondition();
        }
        return false;
    }

    public boolean isCurDemo() {
        com.bytedance.praisedialoglib.depend.c cVar = this.f11121e;
        if (cVar != null) {
            return cVar.isCurDemo();
        }
        return false;
    }

    public String a() {
        com.bytedance.praisedialoglib.depend.a aVar = this.f11119c;
        if (aVar != null) {
            return aVar.a();
        }
        return null;
    }

    public String b() {
        com.bytedance.praisedialoglib.depend.a aVar = this.f11119c;
        if (aVar != null) {
            return aVar.b();
        }
        return null;
    }

    public String getAppId() {
        com.bytedance.praisedialoglib.depend.a aVar = this.f11119c;
        if (aVar != null) {
            return aVar.getAppId();
        }
        return null;
    }

    public String getPackageName() {
        com.bytedance.praisedialoglib.depend.a aVar = this.f11119c;
        if (aVar != null) {
            return aVar.getPackageName();
        }
        return null;
    }

    public String getDeviceId() {
        com.bytedance.praisedialoglib.depend.a aVar = this.f11119c;
        if (aVar != null) {
            return aVar.getDeviceId();
        }
        return null;
    }

    public void onEvent(String str, JSONObject jSONObject) {
        com.bytedance.praisedialoglib.depend.a aVar = this.f11119c;
        if (aVar != null) {
            aVar.onEvent(str, jSONObject);
        }
    }

    public void goToFeedback(Context context) {
        com.bytedance.praisedialoglib.depend.a aVar = this.f11119c;
        if (aVar != null) {
            aVar.goToFeedback(context, this.f11124h);
        }
    }

    public boolean handleNoAppMarket() {
        com.bytedance.praisedialoglib.depend.a aVar = this.f11119c;
        if (aVar != null) {
            return aVar.handleNoAppMarket();
        }
        return false;
    }

    public Activity getTopActivity() {
        com.bytedance.praisedialoglib.depend.a aVar = this.f11119c;
        if (aVar != null) {
            return aVar.c();
        }
        return null;
    }

    public void setAppData(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        Log.d(f11117a, "appData is " + jSONObject);
        this.f11122f = jSONObject.optString("app_market_order", "");
        this.f11123g = jSONObject.optInt("market_feedback_dialog_enable", 0) > 0;
        this.f11124h = jSONObject.optString("market_feedback_url", "");
        this.f11125i = jSONObject.optString("guide_style_1_market_list", "");
        this.f11126j = jSONObject.optString("guide_style_2_market_list", "");
        this.k = jSONObject.optString("guide_style_3_market_list", "");
    }

    public void setIPraiseDialogActionCallback(com.bytedance.praisedialoglib.callback.b bVar) {
        this.l = bVar;
    }

    public com.bytedance.praisedialoglib.callback.b getIPraiseDialogActionCallback() {
        return this.l;
    }

    public void setDebugMode(boolean z) {
        this.m = z;
    }

    public boolean isDebugMode() {
        return this.m;
    }

    public boolean getDialogEnable() {
        return this.f11123g;
    }

    public String getMarketOrder() {
        return this.f11122f;
    }

    public boolean needShowDefaultDialog() {
        com.bytedance.praisedialoglib.depend.a aVar = this.f11119c;
        if (aVar != null) {
            return aVar.needShowDefaultDialog();
        }
        return false;
    }

    public ArrayList<String> getGuideStyle1List() {
        if (TextUtils.isEmpty(this.f11125i)) {
            Log.d(f11117a, "initMarketOrder is called, but mMarketOrder is empty");
            return null;
        }
        String[] split = this.f11125i.split("\\|");
        if (split.length <= 0) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, split);
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    public ArrayList<String> getGuideStyle2List() {
        if (TextUtils.isEmpty(this.f11126j)) {
            Log.d(f11117a, "initMarketOrder is called, but mMarketOrder is empty");
            return null;
        }
        String[] split = this.f11126j.split("\\|");
        if (split.length <= 0) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, split);
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    public ArrayList<String> getGuideStyle3List() {
        if (TextUtils.isEmpty(this.k)) {
            Log.d(f11117a, "initMarketOrder is called, but mMarketOrder is empty");
            return null;
        }
        String[] split = this.k.split("\\|");
        if (split.length <= 0) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, split);
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    public String getTitleStr() {
        e eVar = this.f11120d;
        if (eVar == null || TextUtils.isEmpty(eVar.getMainTitleString())) {
            return null;
        }
        return this.f11120d.getMainTitleString();
    }

    public String getTitleStrColor() {
        e eVar = this.f11120d;
        if (eVar == null || TextUtils.isEmpty(eVar.getMainTitleStrColor())) {
            return null;
        }
        return this.f11120d.getMainTitleStrColor();
    }

    public int getTitleStrSize() {
        e eVar = this.f11120d;
        if (eVar != null) {
            return eVar.getMainTitleStrSize();
        }
        return -1;
    }

    public String getSecondTitleStr() {
        e eVar = this.f11120d;
        if (eVar == null || TextUtils.isEmpty(eVar.getSecondTitleString())) {
            return null;
        }
        return this.f11120d.getSecondTitleString();
    }

    public String getSecondTitleStrColor() {
        e eVar = this.f11120d;
        if (eVar == null || TextUtils.isEmpty(eVar.getSecondTitleStrColor())) {
            return null;
        }
        return this.f11120d.getSecondTitleStrColor();
    }

    public int getSecondTitleStrSize() {
        e eVar = this.f11120d;
        if (eVar != null) {
            return eVar.getSecondTitleStrSize();
        }
        return -1;
    }

    public String getNegativeBtnStr() {
        e eVar = this.f11120d;
        if (eVar == null || TextUtils.isEmpty(eVar.getNegativeBtnText())) {
            return null;
        }
        return this.f11120d.getNegativeBtnText();
    }

    public String getNegativeBtnColor() {
        e eVar = this.f11120d;
        if (eVar == null || TextUtils.isEmpty(eVar.getNegativeBtnTextColor())) {
            return null;
        }
        return this.f11120d.getNegativeBtnTextColor();
    }

    public int getNegativeBtnSize() {
        e eVar = this.f11120d;
        if (eVar != null) {
            return eVar.getNegativeBtnTextSize();
        }
        return -1;
    }

    public String getPositiveBtnStr() {
        e eVar = this.f11120d;
        if (eVar == null || TextUtils.isEmpty(eVar.getPositiveBtnText())) {
            return null;
        }
        return this.f11120d.getPositiveBtnText();
    }

    public String getPositiveBtnColor() {
        e eVar = this.f11120d;
        if (eVar == null || TextUtils.isEmpty(eVar.getPositiveBtnTextColor())) {
            return null;
        }
        return this.f11120d.getPositiveBtnTextColor();
    }

    public int getPositiveBtnSize() {
        e eVar = this.f11120d;
        if (eVar != null) {
            return eVar.getPositiveBtnTextSize();
        }
        return -1;
    }

    public int getMiddleImgResId() {
        e eVar = this.f11120d;
        if (eVar != null) {
            return eVar.getMiddleImageResId();
        }
        return -1;
    }

    public int getMiddleImgResIdStyle2() {
        e eVar = this.f11120d;
        if (eVar != null) {
            return eVar.getMiddleImageStyle2ResId();
        }
        return -1;
    }

    public int getMiddleImgResIdStyle3() {
        e eVar = this.f11120d;
        if (eVar != null) {
            return eVar.getMiddleImageStyle3ResId();
        }
        return -1;
    }

    public int getBackgroundResId() {
        e eVar = this.f11120d;
        if (eVar != null) {
            return eVar.getMainBackgroundResId();
        }
        return -1;
    }

    public int getPositiveBtnResId() {
        e eVar = this.f11120d;
        if (eVar != null) {
            return eVar.getPositiveBtnResId();
        }
        return -1;
    }

    public int getNegativeBtnResId() {
        e eVar = this.f11120d;
        if (eVar != null) {
            return eVar.getNegativeBtnResId();
        }
        return -1;
    }
}
