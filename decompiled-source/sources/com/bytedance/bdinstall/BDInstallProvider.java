package com.bytedance.bdinstall;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
/* loaded from: classes.dex */
public class BDInstallProvider extends ContentProvider {
    private static final int MATCH_DATA = 65536;
    private static Uri sBaseUri;
    private static Context sContext;
    private static UriMatcher sMatcher;
    private static String sShareAuthority;

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    public BDInstallProvider() {
        try {
            ax.a(true);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        sContext = context;
        if (providerInfo != null) {
            sShareAuthority = providerInfo.authority;
        }
        ax.a(true);
        super.attachInfo(context, providerInfo);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        if (sMatcher == null) {
            try {
                init(getContext());
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }
        try {
            j.a((Application) ax.c(getContext()));
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return "vnd.android.cursor.item/vnd." + sShareAuthority + ".item";
    }

    private static void init(Context context) {
        if (TextUtils.isEmpty(sShareAuthority)) {
            sShareAuthority = getProviderAuthority(context, BDInstallProvider.class.getName());
        }
        if (TextUtils.isEmpty(sShareAuthority)) {
            throw new IllegalStateException("Must Set MultiProcessSharedProvider Authority");
        }
        UriMatcher uriMatcher = new UriMatcher(-1);
        sMatcher = uriMatcher;
        uriMatcher.addURI(sShareAuthority, "*/*", 65536);
        sBaseUri = Uri.parse("content://" + sShareAuthority);
    }

    private static String getProviderAuthority(Context context, String str) {
        ProviderInfo[] providerInfoArr;
        if (context == null) {
            return null;
        }
        try {
            if (!TextUtils.isEmpty(str)) {
                for (ProviderInfo providerInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 8).providers) {
                    if (str.equals(providerInfo.name)) {
                        return providerInfo.authority;
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return context.getPackageName() + ".bdinstall.provider";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized Uri getContentUri(Context context, String str) {
        Uri build;
        synchronized (BDInstallProvider.class) {
            if (sBaseUri == null) {
                try {
                    init(context);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
            build = sBaseUri.buildUpon().appendPath(str).build();
        }
        return build;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Context getFallbackContext() {
        return sContext;
    }
}
