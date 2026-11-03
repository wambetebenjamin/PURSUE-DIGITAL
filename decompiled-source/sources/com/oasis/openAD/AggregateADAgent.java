package com.oasis.openAD;

import android.app.Application;
import android.content.Context;
import com.bytedance.sdk.account.platform.base.i;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public abstract class AggregateADAgent implements ApplicationListener, ActivityListener {
    protected static final int BANNER_TYPE = 3;
    protected static final int NATIVE_EXPRESS_TYPE = 6;
    protected static final int NATIVE_ICON_TYPE = 5;
    protected static final int NATIVE_STREAM_TYPE = 7;
    protected static final int SPLASH_TYPE = 4;
    protected static final int VIDEO_TYPE_INTERSTITIAL = 2;
    protected static final int VIDEO_TYPE_REWARDED = 1;

    /* renamed from: a  reason: collision with root package name */
    private static AggregateADAgent f17559a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17560b = false;

    /* renamed from: c  reason: collision with root package name */
    private static String f17561c = "";

    /* renamed from: d  reason: collision with root package name */
    static final /* synthetic */ boolean f17562d = true;
    protected static Application thisApp;
    protected long timeout = 60000;

    public AggregateADAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static AggregateADAgent createInstance(Context context) {
        if (f17562d || f17559a == null) {
            try {
                String string = context.getString(R.string.aggregate_ad_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("AggregateADAgent", "Create the AggregateADAgent:" + string);
                    f17559a = (AggregateADAgent) cls.newInstance();
                    char c2 = 65535;
                    switch (string.hashCode()) {
                        case -2068927611:
                            if (string.equals("com.oasis.xiaomiadsdk.XiaomiADAgent")) {
                                c2 = 4;
                                break;
                            }
                            break;
                        case -1762208636:
                            if (string.equals("com.oasis.rocketcn.RocketCNADAgent")) {
                                c2 = 0;
                                break;
                            }
                            break;
                        case -1661806970:
                            if (string.equals("com.rocket.vivoadsdk.VivoAdAgent")) {
                                c2 = 2;
                                break;
                            }
                            break;
                        case 105718843:
                            if (string.equals("com.oasis.oppoadsdk.OppoADAgent")) {
                                c2 = 3;
                                break;
                            }
                            break;
                        case 746467646:
                            if (string.equals("com.oasis.momoyusdk.MomoyuADAgent")) {
                                c2 = 5;
                                break;
                            }
                            break;
                        case 1433280557:
                            if (string.equals("com.oasis.huaweiadsdk.HuaweiADAgent")) {
                                c2 = 1;
                                break;
                            }
                            break;
                    }
                    f17561c = c2 != 0 ? c2 != 1 ? c2 != 2 ? c2 != 3 ? c2 != 4 ? c2 != 5 ? "" : "momoyu" : "xiaomi" : "oppo" : "vivo" : "huawei" : "pangle";
                }
            } catch (Exception e2) {
                Logger.e("AggregateADAgent", "Create AggregateADAgent failed.", e2);
            }
            if (f17559a == null) {
                Logger.i("AggregateADAgent", "Create default AggregateADAgent.");
                f17559a = new a();
                f17560b = true;
            }
            return f17559a;
        }
        throw new AssertionError();
    }

    public static AggregateADAgent getInstance() {
        if (f17562d || f17559a != null) {
            return f17559a;
        }
        throw new AssertionError();
    }

    public void enableRecommendAd(boolean z) {
    }

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public String getAdChannel() {
        return f17561c;
    }

    public int getAdNetworkPlatformId(int i2, String str) {
        return -3;
    }

    public String getAdNetworkRitId(int i2, String str) {
        return i.a.ERROR_SDK_INIT;
    }

    public boolean getHasUserConsent() {
        return false;
    }

    public String getPreEcpm(int i2, String str) {
        return i.a.ERROR_SDK_INIT;
    }

    public abstract void initSDK(AdInitListener adInitListener, String str);

    public abstract boolean isAdReady(int i2, String str);

    public boolean isEnabledRecommend() {
        return true;
    }

    public boolean isUseDefault() {
        return f17560b;
    }

    public abstract void loadAd(int i2, String str, String str2);

    @Override // com.oasis.android.ApplicationListener
    public void onApplicationCreate(Application application) {
        thisApp = application;
    }

    public void openTestTool() {
    }

    public abstract void setAdListener(AggregateAdListener aggregateAdListener);

    public void setDataProcessingOptions(String str, int i2, int i3) {
    }

    public void setDoNotSell(boolean z) {
    }

    public void setHasUserConsent(boolean z) {
    }

    public void setIsAgeRestrictedUser(boolean z) {
    }

    public void setShowAdTimeout(long j2) {
        this.timeout = j2;
    }

    public abstract void showAd(int i2, String str);

    public void showAdV2(int i2, String str, String str2) {
        showAd(i2, str);
    }
}
