package com.appsflyer.internal;

import com.appsflyer.AFLogger;
@Deprecated
/* loaded from: classes.dex */
public final class AFb1gSDK {
    private final AFa1uSDK values = new AFa1uSDK() { // from class: com.appsflyer.internal.AFb1gSDK.1
        @Override // com.appsflyer.internal.AFb1gSDK.AFa1uSDK
        public final Class<?> values(String str) throws ClassNotFoundException {
            return Class.forName(str);
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface AFa1uSDK {
        Class<?> values(String str) throws ClassNotFoundException;
    }

    public final String AFInAppEventType() {
        AFa1ySDK[] values;
        for (AFa1ySDK aFa1ySDK : AFa1ySDK.values()) {
            if (values(aFa1ySDK.afWarnLog)) {
                return aFa1ySDK.f40i;
            }
        }
        return AFa1ySDK.DEFAULT.f40i;
    }

    private boolean values(String str) {
        try {
            this.values.values(str);
            AFLogger.afRDLog(new StringBuilder("Class: ").append(str).append(" is found.").toString());
            return true;
        } catch (ClassNotFoundException e2) {
            AFLogger.afErrorLogForExcManagerOnly(new StringBuilder("Class: ").append(str).append(" is  not found. (Platform extension)").toString(), e2, true);
            return false;
        } catch (Throwable th) {
            AFLogger.afErrorLog(th.getMessage(), th);
            return false;
        }
    }

    /* loaded from: classes.dex */
    enum AFa1ySDK {
        ADOBE_AIR("android_adobe_air", "com.appsflyer.adobeair.AppsFlyerExtension"),
        ADOBE_MOBILE_SDK("android_adobe_mobile", "com.appsflyer.adobeextension.AppsFlyerAdobeExtension"),
        COCOS2DX("android_cocos2dx", "org.cocos2dx.lib.Cocos2dxActivity"),
        CORDOVA("android_cordova", "com.appsflyer.cordova.plugin.AppsFlyerPlugin"),
        DEFAULT("android_native", "android_native"),
        FLUTTER("android_flutter", "com.appsflyer.appsflyersdk.AppsflyerSdkPlugin"),
        M_PARTICLE("android_mparticle", "com.mparticle.kits.AppsFlyerKit"),
        NATIVE_SCRIPT("android_native_script", "com.tns.NativeScriptActivity"),
        EXPO("android_expo", "expo.modules.devmenu.react.DevMenuAwareReactActivity"),
        REACT_NATIVE("android_reactNative", "com.appsflyer.reactnative.RNAppsFlyerModule"),
        UNITY("android_unity", "com.appsflyer.unity.AppsFlyerAndroidWrapper"),
        UNREAL_ENGINE("android_unreal", "com.epicgames.ue4.GameActivity"),
        XAMARIN("android_xamarin", "mono.android.Runtime"),
        CAPACITOR("android_capacitor", "capacitor.plugin.appsflyer.sdk.AppsFlyerPlugin");
        
        private final String afWarnLog;

        /* renamed from: i  reason: collision with root package name */
        private final String f40i;

        AFa1ySDK(String str, String str2) {
            this.f40i = str;
            this.afWarnLog = str2;
        }
    }
}
