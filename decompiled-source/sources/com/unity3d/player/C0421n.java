package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import com.google.android.play.core.assetpacks.AssetPackLocation;
import com.google.android.play.core.assetpacks.AssetPackManager;
import com.google.android.play.core.assetpacks.AssetPackManagerFactory;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
/* renamed from: com.unity3d.player.n  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
final class C0421n {

    /* renamed from: d  reason: collision with root package name */
    private static C0421n f19026d;

    /* renamed from: a  reason: collision with root package name */
    private AssetPackManager f19027a;

    /* renamed from: b  reason: collision with root package name */
    private HashSet f19028b;

    /* renamed from: c  reason: collision with root package name */
    private Object f19029c;

    private C0421n(Context context) {
        if (f19026d != null) {
            throw new RuntimeException("AssetPackManagerWrapper should be created only once. Use getInstance() instead.");
        }
        this.f19027a = AssetPackManagerFactory.getInstance(context);
        this.f19028b = new HashSet();
    }

    public static C0421n a(Context context) {
        if (f19026d == null) {
            f19026d = new C0421n(context);
        }
        return f19026d;
    }

    public final Object a(IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback) {
        C0409h c0409h = new C0409h(this, iAssetPackManagerDownloadStatusCallback, Looper.myLooper());
        this.f19027a.registerListener(c0409h);
        return c0409h;
    }

    public final String a(String str) {
        AssetPackLocation packLocation = this.f19027a.getPackLocation(str);
        return packLocation == null ? "" : packLocation.assetsPath();
    }

    public final void a(Activity activity, IAssetPackManagerMobileDataConfirmationCallback iAssetPackManagerMobileDataConfirmationCallback) {
        this.f19027a.showCellularDataConfirmation(activity).addOnSuccessListener(new C0413j(iAssetPackManagerMobileDataConfirmationCallback));
    }

    public final void a(Object obj) {
        if (obj instanceof C0409h) {
            this.f19027a.unregisterListener((C0409h) obj);
        }
    }

    public final void a(String[] strArr) {
        this.f19027a.cancel(Arrays.asList(strArr));
    }

    public final void a(String[] strArr, IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback) {
        for (String str : strArr) {
            this.f19027a.getPackStates(Collections.singletonList(str)).addOnCompleteListener(new C0415k(str, iAssetPackManagerDownloadStatusCallback));
        }
    }

    public final void a(String[] strArr, IAssetPackManagerStatusQueryCallback iAssetPackManagerStatusQueryCallback) {
        this.f19027a.getPackStates(Arrays.asList(strArr)).addOnCompleteListener(new C0419m(strArr, iAssetPackManagerStatusQueryCallback));
    }

    public final void b(String str) {
        this.f19027a.removePack(str);
    }
}
