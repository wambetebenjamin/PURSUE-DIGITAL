package gsdk.impl.webview.DEFAULT;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SandboxUtil.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/bytedance/ttgame/module/webview/util/AddMediaResult;", "", "contentResolver", "Landroid/content/ContentResolver;", "contentValues", "Landroid/content/ContentValues;", "uri", "Landroid/net/Uri;", "(Landroid/content/ContentResolver;Landroid/content/ContentValues;Landroid/net/Uri;)V", "getContentResolver", "()Landroid/content/ContentResolver;", "getContentValues", "()Landroid/content/ContentValues;", "getUri", "()Landroid/net/Uri;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "webview_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class bo {

    /* renamed from: a  reason: collision with root package name */
    private final ContentResolver f20994a;

    /* renamed from: b  reason: collision with root package name */
    private final ContentValues f20995b;

    /* renamed from: c  reason: collision with root package name */
    private final Uri f20996c;

    public static /* synthetic */ bo a(bo boVar, ContentResolver contentResolver, ContentValues contentValues, Uri uri, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            contentResolver = boVar.f20994a;
        }
        if ((i2 & 2) != 0) {
            contentValues = boVar.f20995b;
        }
        if ((i2 & 4) != 0) {
            uri = boVar.f20996c;
        }
        return boVar.a(contentResolver, contentValues, uri);
    }

    public final bo a(ContentResolver contentResolver, ContentValues contentValues, Uri uri) {
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        Intrinsics.checkNotNullParameter(contentValues, "contentValues");
        return new bo(contentResolver, contentValues, uri);
    }

    public final ContentResolver d() {
        return this.f20994a;
    }

    public final ContentValues e() {
        return this.f20995b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof bo) {
            bo boVar = (bo) obj;
            return Intrinsics.areEqual(this.f20994a, boVar.f20994a) && Intrinsics.areEqual(this.f20995b, boVar.f20995b) && Intrinsics.areEqual(this.f20996c, boVar.f20996c);
        }
        return false;
    }

    public final Uri f() {
        return this.f20996c;
    }

    public int hashCode() {
        int hashCode = ((this.f20994a.hashCode() * 31) + this.f20995b.hashCode()) * 31;
        Uri uri = this.f20996c;
        return hashCode + (uri == null ? 0 : uri.hashCode());
    }

    public String toString() {
        return "AddMediaResult(contentResolver=" + this.f20994a + ", contentValues=" + this.f20995b + ", uri=" + this.f20996c + ')';
    }

    public bo(ContentResolver contentResolver, ContentValues contentValues, Uri uri) {
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        Intrinsics.checkNotNullParameter(contentValues, "contentValues");
        this.f20994a = contentResolver;
        this.f20995b = contentValues;
        this.f20996c = uri;
    }

    public final ContentResolver a() {
        return this.f20994a;
    }

    public final ContentValues b() {
        return this.f20995b;
    }

    public final Uri c() {
        return this.f20996c;
    }
}
