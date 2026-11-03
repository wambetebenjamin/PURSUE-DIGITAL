package com.bytedance.router.route;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.net.Uri;
import android.text.TextUtils;
/* compiled from: RouteFactory.java */
/* loaded from: classes2.dex */
public class e {
    public static b createRoute(String str, String str2, com.bytedance.router.g gVar) {
        Class<?> cls = null;
        if (TextUtils.isEmpty(str)) {
            com.bytedance.router.util.b.e("RouteFactory#createRoute url is null!!!");
            return null;
        }
        Uri parse = Uri.parse(str);
        if (!parse.isHierarchical()) {
            com.bytedance.router.util.b.e("RouteFactory#createRoute url is not illegal!!!");
            return null;
        }
        String scheme = parse.getScheme();
        if (TextUtils.isEmpty(scheme)) {
            com.bytedance.router.util.b.e("RouteFactory#createRoute scheme is null!!!");
            return null;
        }
        String host = parse.getHost();
        if (TextUtils.isEmpty(host)) {
            com.bytedance.router.util.b.e("RouteFactory#createRoute host is null!!!");
            return null;
        } else if (!a(scheme, gVar)) {
            com.bytedance.router.util.b.e(String.format("RouteFactory#createRoute router cannot support this scheme protocol: %s\nRouteConfig:\n%s", str, gVar.toString()));
            return null;
        } else if (host.startsWith("bt.")) {
            Class<? extends b> routeClass = gVar.getRouteClass(host);
            if (routeClass == null) {
                com.bytedance.router.util.b.e("RouteFactory#createRoute there is no route for this host: " + host);
                return null;
            }
            try {
                return routeClass.newInstance();
            } catch (Exception e2) {
                com.bytedance.router.util.b.e("RouteFactory#createRoute routeClass new instance exception!!!");
                e2.printStackTrace();
                return null;
            }
        } else {
            try {
                cls = Class.forName(str2);
            } catch (ClassNotFoundException e3) {
                e3.printStackTrace();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (cls != null) {
                if (Service.class.isAssignableFrom(cls)) {
                    return new f();
                }
                if (BroadcastReceiver.class.isAssignableFrom(cls)) {
                    return new c();
                }
            }
            return new a();
        }
    }

    private static boolean a(String str, com.bytedance.router.g gVar) {
        if (str.equals(gVar.getScheme())) {
            return true;
        }
        String[] otherSchemes = gVar.getOtherSchemes();
        if (otherSchemes != null && otherSchemes.length > 0) {
            for (String str2 : otherSchemes) {
                if (str.equals(str2)) {
                    return true;
                }
            }
        }
        return false;
    }
}
