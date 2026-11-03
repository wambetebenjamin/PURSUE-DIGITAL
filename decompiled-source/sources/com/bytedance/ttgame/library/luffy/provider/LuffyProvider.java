package com.bytedance.ttgame.library.luffy.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.bytedance.ttgame.library.luffy.core.g;
import com.bytedance.ttgame.library.luffy.core.k;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: LuffyProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0006H\u0016J/\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0010H\u0016¢\u0006\u0002\u0010\u0011J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00042\u0006\u0010\f\u001a\u00020\rH\u0016J\u001c\u0010\u0013\u001a\u0004\u0018\u00010\r2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016JK\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\f\u001a\u00020\r2\u000e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00102\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00102\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0002\u0010\u001cJ9\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0010H\u0016¢\u0006\u0002\u0010\u001eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/bytedance/ttgame/library/luffy/provider/LuffyProvider;", "Landroid/content/ContentProvider;", "()V", "TAG", "", "call", "Landroid/os/Bundle;", "method", "arg", "extras", "delete", "", "uri", "Landroid/net/Uri;", ReactTextInputShadowNode.PROP_SELECTION, "selectionArgs", "", "(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I", "getType", "insert", "values", "Landroid/content/ContentValues;", "onCreate", "", "query", "Landroid/database/Cursor;", "projection", "sortOrder", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "update", "(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "luffy_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public final class LuffyProvider extends ContentProvider {
    private final String TAG = "LuffyProvider";

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return null;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return 0;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        k.INSTANCE.d(this.TAG, "onCreate");
        return false;
    }

    @Override // android.content.ContentProvider
    public Bundle call(String method, String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(method, "method");
        try {
            if (Intrinsics.areEqual("request", method)) {
                Bundle bundle2 = new Bundle();
                bundle2.putBoolean("result", g.Companion.getRequested().get());
                return bundle2;
            }
        } catch (Throwable th) {
            k.INSTANCE.e(this.TAG, "fail to call " + method + ", " + th);
        }
        return super.call(method, str, bundle);
    }
}
