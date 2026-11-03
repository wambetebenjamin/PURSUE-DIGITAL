package com.appsflyer.internal;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.appsflyer.AFLogger;
import com.appsflyer.internal.AFa1qSDK;
import com.facebook.share.internal.ShareConstants;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes.dex */
public final class AFc1gSDK implements AFc1eSDK {
    private boolean AFInAppEventType;
    Map<String, Object> valueOf;
    private final AFd1lSDK values;

    public AFc1gSDK(AFd1lSDK aFd1lSDK) {
        Intrinsics.checkNotNullParameter(aFd1lSDK, "");
        this.values = aFd1lSDK;
    }

    private boolean AFInAppEventParameterName() {
        return this.AFInAppEventType;
    }

    @Override // com.appsflyer.internal.AFc1eSDK
    public final void valueOf(boolean z) {
        this.AFInAppEventType = z;
    }

    @Override // com.appsflyer.internal.AFc1eSDK
    public final Map<String, Object> valueOf() {
        return this.valueOf;
    }

    @Override // com.appsflyer.internal.AFc1eSDK
    public final void AFKeystoreWrapper() {
        Context context;
        if (AFInAppEventParameterName() && (context = this.values.AFInAppEventParameterName) != null) {
            this.valueOf = new LinkedHashMap();
            final AFa1tSDK aFa1tSDK = new AFa1tSDK(System.currentTimeMillis());
            try {
                Class.forName("com.facebook.FacebookSdk").getMethod("sdkInitialize", Context.class).invoke(null, context);
                final Class<?> cls = Class.forName("com.facebook.applinks.AppLinkData");
                Class<?> cls2 = Class.forName("com.facebook.applinks.AppLinkData$CompletionHandler");
                Method method = cls.getMethod("fetchDeferredAppLinkData", Context.class, String.class, cls2);
                Object newProxyInstance = Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, new InvocationHandler() { // from class: com.appsflyer.internal.AFa1qSDK.4
                    @Override // java.lang.reflect.InvocationHandler
                    public final Object invoke(Object obj, Method method2, Object[] objArr) throws Throwable {
                        String str;
                        String str2;
                        String str3;
                        Bundle bundle;
                        if (method2.getName().equals("onDeferredAppLinkDataFetched")) {
                            if (objArr[0] != null) {
                                Bundle bundle2 = (Bundle) Bundle.class.cast(cls.getMethod("getArgumentBundle", new Class[0]).invoke(cls.cast(objArr[0]), new Object[0]));
                                if (bundle2 != null) {
                                    str2 = bundle2.getString("com.facebook.platform.APPLINK_NATIVE_URL");
                                    str3 = bundle2.getString("target_url");
                                    Bundle bundle3 = bundle2.getBundle("extras");
                                    str = (bundle3 == null || (bundle = bundle3.getBundle(ShareConstants.DEEPLINK_CONTEXT)) == null) ? null : bundle.getString(ShareConstants.PROMO_CODE);
                                } else {
                                    str = null;
                                    str2 = null;
                                    str3 = null;
                                }
                                AFa1uSDK aFa1uSDK = aFa1tSDK;
                                if (aFa1uSDK != null) {
                                    aFa1uSDK.AFInAppEventType(str2, str3, str);
                                }
                            } else {
                                AFa1uSDK aFa1uSDK2 = aFa1tSDK;
                                if (aFa1uSDK2 != null) {
                                    aFa1uSDK2.AFInAppEventType(null, null, null);
                                }
                            }
                            return null;
                        }
                        AFa1uSDK aFa1uSDK3 = aFa1tSDK;
                        if (aFa1uSDK3 != null) {
                            aFa1uSDK3.AFInAppEventType("onDeferredAppLinkDataFetched invocation failed");
                        }
                        return null;
                    }
                });
                String string = context.getString(context.getResources().getIdentifier("facebook_app_id", "string", context.getPackageName()));
                if (TextUtils.isEmpty(string)) {
                    aFa1tSDK.AFInAppEventType("Facebook app id not defined in resources");
                } else {
                    method.invoke(null, context, string, newProxyInstance);
                }
            } catch (ClassNotFoundException e2) {
                AFLogger.afErrorLogForExcManagerOnly("FB class missing error", e2);
                aFa1tSDK.AFInAppEventType(e2.toString());
            } catch (IllegalAccessException e3) {
                AFLogger.afErrorLogForExcManagerOnly("FB illegal access", e3);
                aFa1tSDK.AFInAppEventType(e3.toString());
            } catch (NoSuchMethodException e4) {
                AFLogger.afErrorLogForExcManagerOnly("FB method missing error", e4);
                aFa1tSDK.AFInAppEventType(e4.toString());
            } catch (InvocationTargetException e5) {
                AFLogger.afErrorLogForExcManagerOnly("FB invocation error", e5);
                aFa1tSDK.AFInAppEventType(e5.toString());
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class AFa1tSDK implements AFa1qSDK.AFa1uSDK {
        private /* synthetic */ long valueOf;

        AFa1tSDK(long j2) {
            this.valueOf = j2;
        }

        @Override // com.appsflyer.internal.AFa1qSDK.AFa1uSDK
        public final void AFInAppEventType(String str, String str2, String str3) {
            Map<String, Object> map;
            if (str != null) {
                AFLogger.afInfoLog("Facebook Deferred AppLink data received: ".concat(String.valueOf(str)));
                Map<String, Object> map2 = AFc1gSDK.this.valueOf;
                if (map2 != null) {
                    map2.put("link", str);
                }
                if (str2 != null && (map = AFc1gSDK.this.valueOf) != null) {
                    map.put("target_url", str2);
                }
                if (str3 != null) {
                    AFc1gSDK aFc1gSDK = AFc1gSDK.this;
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                    linkedHashMap2.put(ShareConstants.PROMO_CODE, str3);
                    linkedHashMap.put(ShareConstants.DEEPLINK_CONTEXT, linkedHashMap2);
                    Map<String, Object> map3 = aFc1gSDK.valueOf;
                    if (map3 != null) {
                        map3.put("extras", linkedHashMap);
                    }
                }
            } else {
                Map<String, Object> map4 = AFc1gSDK.this.valueOf;
                if (map4 != null) {
                    map4.put("link", "");
                }
            }
            String valueOf = String.valueOf(System.currentTimeMillis() - this.valueOf);
            Map<String, Object> map5 = AFc1gSDK.this.valueOf;
            if (map5 != null) {
                map5.put("ttr", valueOf);
            }
        }

        @Override // com.appsflyer.internal.AFa1qSDK.AFa1uSDK
        public final void AFInAppEventType(String str) {
            Map<String, Object> map = AFc1gSDK.this.valueOf;
            if (map != null) {
                map.put("error", str);
            }
        }
    }

    @Override // com.appsflyer.internal.AFc1eSDK
    public final boolean AFInAppEventType() {
        if (AFInAppEventParameterName()) {
            Map<String, Object> map = this.valueOf;
            if (map == null || map.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
