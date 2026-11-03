package net.openid.appauth.browser;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import java.util.Iterator;
/* loaded from: classes10.dex */
public final class BrowserSelector {
    static final String ACTION_CUSTOM_TABS_CONNECTION = "android.support.customtabs.action.CustomTabsService";
    static final Intent BROWSER_INTENT = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
    private static final String SCHEME_HTTP = "http";
    private static final String SCHEME_HTTPS = "https";

    /* JADX WARN: Removed duplicated region for block: B:13:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x007d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0079 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<net.openid.appauth.browser.BrowserDescriptor> getAllBrowsers(android.content.Context r10) {
        /*
            android.content.pm.PackageManager r10 = r10.getPackageManager()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 64
            r3 = 23
            if (r1 < r3) goto L15
            r1 = 131136(0x20040, float:1.8376E-40)
            goto L16
        L15:
            r1 = r2
        L16:
            android.content.Intent r3 = net.openid.appauth.browser.BrowserSelector.BROWSER_INTENT
            r4 = 0
            android.content.pm.ResolveInfo r5 = r10.resolveActivity(r3, r4)
            if (r5 == 0) goto L24
            android.content.pm.ActivityInfo r5 = r5.activityInfo
            java.lang.String r5 = r5.packageName
            goto L25
        L24:
            r5 = 0
        L25:
            java.util.List r1 = r10.queryIntentActivities(r3, r1)
            java.util.Iterator r1 = r1.iterator()
        L2d:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L81
            java.lang.Object r3 = r1.next()
            android.content.pm.ResolveInfo r3 = (android.content.pm.ResolveInfo) r3
            boolean r6 = isFullBrowser(r3)
            if (r6 != 0) goto L40
            goto L2d
        L40:
            android.content.pm.ActivityInfo r6 = r3.activityInfo     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
            java.lang.String r6 = r6.packageName     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
            android.content.pm.PackageInfo r6 = r10.getPackageInfo(r6, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
            android.content.pm.ActivityInfo r7 = r3.activityInfo     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
            java.lang.String r7 = r7.packageName     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
            boolean r7 = hasWarmupService(r10, r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
            r8 = 1
            if (r7 == 0) goto L69
            net.openid.appauth.browser.BrowserDescriptor r7 = new net.openid.appauth.browser.BrowserDescriptor     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
            r7.<init>(r6, r8)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
            android.content.pm.ActivityInfo r9 = r3.activityInfo     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
            java.lang.String r9 = r9.packageName     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
            boolean r9 = r9.equals(r5)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
            if (r9 == 0) goto L66
            r0.add(r4, r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
            goto L6a
        L66:
            r0.add(r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
        L69:
            r8 = r4
        L6a:
            net.openid.appauth.browser.BrowserDescriptor r7 = new net.openid.appauth.browser.BrowserDescriptor     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
            r7.<init>(r6, r4)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
            android.content.pm.ActivityInfo r3 = r3.activityInfo     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
            java.lang.String r3 = r3.packageName     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
            boolean r3 = r3.equals(r5)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
            if (r3 == 0) goto L7d
            r0.add(r8, r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
            goto L2d
        L7d:
            r0.add(r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2d
            goto L2d
        L81:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.openid.appauth.browser.BrowserSelector.getAllBrowsers(android.content.Context):java.util.List");
    }

    public static BrowserDescriptor select(Context context, BrowserMatcher browserMatcher) {
        BrowserDescriptor browserDescriptor = null;
        for (BrowserDescriptor browserDescriptor2 : getAllBrowsers(context)) {
            if (browserMatcher.matches(browserDescriptor2)) {
                if (browserDescriptor2.useCustomTab.booleanValue()) {
                    return browserDescriptor2;
                }
                if (browserDescriptor == null) {
                    browserDescriptor = browserDescriptor2;
                }
            }
        }
        return browserDescriptor;
    }

    private static boolean hasWarmupService(PackageManager packageManager, String str) {
        Intent intent = new Intent();
        intent.setAction("android.support.customtabs.action.CustomTabsService");
        intent.setPackage(str);
        return packageManager.resolveService(intent, 0) != null;
    }

    private static boolean isFullBrowser(ResolveInfo resolveInfo) {
        if (resolveInfo.filter.hasAction("android.intent.action.VIEW") && resolveInfo.filter.hasCategory("android.intent.category.BROWSABLE") && resolveInfo.filter.schemesIterator() != null && resolveInfo.filter.authoritiesIterator() == null) {
            Iterator<String> schemesIterator = resolveInfo.filter.schemesIterator();
            boolean z = false;
            boolean z2 = false;
            while (schemesIterator.hasNext()) {
                String next = schemesIterator.next();
                z |= "http".equals(next);
                z2 |= "https".equals(next);
                if (z && z2) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
