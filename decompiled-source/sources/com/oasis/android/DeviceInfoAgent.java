package com.oasis.android;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import com.oasis.Logger.Logger;
import com.oasis.channel.ChannelAgent;
/* loaded from: classes9.dex */
public abstract class DeviceInfoAgent implements ApplicationListener {
    public static String TAG = "DeviceInfoAgent";

    /* renamed from: a  reason: collision with root package name */
    private static DeviceInfoAgent f17360a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17361b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17362c = true;
    public static boolean isAllowPrivacy = false;
    protected Application application;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public class a extends DeviceInfoAgent {
        a() {
        }
    }

    public DeviceInfoAgent() {
        OasisApplication.registerListener(this);
    }

    public static DeviceInfoAgent createInstance(Context context) {
        if (f17362c || f17360a == null) {
            try {
                String string = context.getString(R.string.device_info_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i(TAG, "Create the DeviceInfoAgent:" + string);
                    f17360a = (DeviceInfoAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e(TAG, "Create DeviceInfoAgent failed.", e2);
            }
            if (f17360a == null) {
                Logger.i(TAG, "Create default DeviceInfoAgent.");
                f17360a = new a();
                f17361b = true;
            }
            return f17360a;
        }
        throw new AssertionError();
    }

    public static DeviceInfoAgent getInstance() {
        if (f17362c || f17360a != null) {
            return f17360a;
        }
        throw new AssertionError();
    }

    @Override // com.oasis.android.ApplicationListener
    public void attachBaseContext(Context context) {
    }

    public void finalize() {
        OasisApplication.unregisterListener(this);
    }

    public String getAndroidID() {
        return null;
    }

    public int getAndroidSDKVersionCode() {
        return Build.VERSION.SDK_INT;
    }

    public String getDeviceBrand() {
        String str = Build.BRAND;
        return str.isEmpty() ? "" : str;
    }

    public String getDeviceID() {
        return ChannelAgent.getInstance().getDeviceID();
    }

    public String getDeviceModel() {
        String str = Build.MODEL;
        return str.isEmpty() ? "" : str;
    }

    public String getGUID() {
        return null;
    }

    public String getIMEI() {
        return null;
    }

    public String getInstallID() {
        return ChannelAgent.getInstance().getInstallID();
    }

    public String getOAID() {
        return null;
    }

    public void getOAIDAsync(OAIDListener oAIDListener) {
    }

    public String getPseudoID() {
        return null;
    }

    public String getRandomUUID() {
        return ChannelAgent.getInstance().getRandomUUID();
    }

    public String getWidevineID() {
        return null;
    }

    public boolean isEyeProtectionMode() {
        return d.a();
    }

    public boolean isGooglePC() {
        return ActivityManager.getActivity().getPackageManager().hasSystemFeature("com.google.android.play.feature.HPE_EXPERIENCE");
    }

    public boolean isSupportedOAID() {
        return false;
    }

    public boolean isUseDefault() {
        return f17361b;
    }

    public void navigatorToSetting() {
        d.b();
    }

    @Override // com.oasis.android.ApplicationListener
    public void onApplicationCreate(Application application) {
    }

    @Override // com.oasis.android.ApplicationListener
    public void onLowMemory(Application application) {
    }

    @Override // com.oasis.android.ApplicationListener
    public void onTerminate(Application application) {
    }

    @Override // com.oasis.android.ApplicationListener
    public void onTrimMemory(Application application, int i2) {
    }

    public void startRequest() {
    }
}
