package com.oasis.android;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import com.facebook.internal.security.CertificateUtil;
import java.security.MessageDigest;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class AppTool {
    public static final String MD5 = "MD5";
    public static final String SHA1 = "SHA1";
    public static final String SHA256 = "SHA256";

    /* renamed from: b  reason: collision with root package name */
    private static AppTool f17350b = null;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17351c = true;

    /* renamed from: a  reason: collision with root package name */
    private final Context f17352a;

    private AppTool(Context context) {
        this.f17352a = context;
    }

    private String a(Signature signature, String str) {
        try {
            byte[] digest = MessageDigest.getInstance(str).digest(signature.toByteArray());
            StringBuilder sb = new StringBuilder();
            for (byte b2 : digest) {
                sb.append(Integer.toHexString((b2 & 255) | 256).substring(1, 3).toUpperCase());
                sb.append(CertificateUtil.DELIMITER);
            }
            return sb.substring(0, sb.length() - 1);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private Signature[] a(Context context, String str) {
        Signature[] signatureArr = new Signature[0];
        try {
            return context.getPackageManager().getPackageInfo(str, 64).signatures;
        } catch (Exception e2) {
            e2.printStackTrace();
            return signatureArr;
        }
    }

    public static AppTool createInstance(Context context) {
        if (f17351c || f17350b == null) {
            AppTool appTool = new AppTool(context);
            f17350b = appTool;
            return appTool;
        }
        throw new AssertionError();
    }

    public static AppTool getInstance() {
        if (f17351c || f17350b != null) {
            return f17350b;
        }
        throw new AssertionError();
    }

    public String getAppName() {
        try {
            return this.f17352a.getResources().getString(this.f17352a.getPackageManager().getPackageInfo(this.f17352a.getPackageName(), 0).applicationInfo.labelRes);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public String getPackageName() {
        try {
            return this.f17352a.getPackageManager().getPackageInfo(this.f17352a.getPackageName(), 0).packageName;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> getSignInfo(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Signature signature : a(this.f17352a, getPackageName())) {
            arrayList.add(a(signature, str));
        }
        return arrayList;
    }

    public int getVersionCode() {
        try {
            return this.f17352a.getPackageManager().getPackageInfo(this.f17352a.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public String getVersionName() {
        try {
            return this.f17352a.getPackageManager().getPackageInfo(this.f17352a.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
