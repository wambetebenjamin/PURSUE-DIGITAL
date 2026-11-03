package com.linecorp.linesdk.auth.internal;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.linecorp.linesdk.LineApiError;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
final class a {

    /* renamed from: a  reason: collision with root package name */
    static final com.linecorp.linesdk.auth.internal.b f17291a = new com.linecorp.linesdk.auth.internal.b(6, 9, 0);

    /* renamed from: b  reason: collision with root package name */
    final d f17292b;

    /* loaded from: classes9.dex */
    static class b {

        /* renamed from: a  reason: collision with root package name */
        final Intent f17296a;

        /* renamed from: b  reason: collision with root package name */
        final Bundle f17297b;

        /* renamed from: c  reason: collision with root package name */
        final String f17298c;

        /* renamed from: d  reason: collision with root package name */
        final boolean f17299d;

        /* JADX INFO: Access modifiers changed from: package-private */
        public b(Intent intent, Bundle bundle, String str, boolean z) {
            this.f17296a = intent;
            this.f17297b = bundle;
            this.f17298c = str;
            this.f17299d = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(d dVar) {
        this.f17292b = dVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<Intent> a(Uri uri, Collection<ResolveInfo> collection, Bundle bundle) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (ResolveInfo resolveInfo : collection) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(uri);
            intent.setPackage(resolveInfo.activityInfo.packageName);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            arrayList.add(intent);
        }
        return arrayList;
    }

    /* loaded from: classes9.dex */
    static class c {

        /* renamed from: a  reason: collision with root package name */
        final String f17300a;

        /* renamed from: b  reason: collision with root package name */
        private final String f17301b;

        /* renamed from: c  reason: collision with root package name */
        private final String f17302c;

        /* renamed from: d  reason: collision with root package name */
        private final String f17303d;

        /* JADX INFO: Access modifiers changed from: package-private */
        public c(String str, String str2, String str3, String str4) {
            this.f17300a = str;
            this.f17301b = str2;
            this.f17302c = str3;
            this.f17303d = str4;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static c a(String str) {
            return new c(null, null, null, str);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final boolean a() {
            return !TextUtils.isEmpty(this.f17300a);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final boolean b() {
            return TextUtils.isEmpty(this.f17303d) && !a();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final LineApiError c() {
            if (b()) {
                try {
                    return new LineApiError(new JSONObject().putOpt("error", this.f17301b).putOpt("error_description", this.f17302c).toString());
                } catch (JSONException e2) {
                    return new LineApiError(e2);
                }
            }
            return new LineApiError(this.f17303d);
        }
    }

    /* renamed from: com.linecorp.linesdk.auth.internal.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    static class C0313a {

        /* renamed from: a  reason: collision with root package name */
        final Intent f17293a;

        /* renamed from: b  reason: collision with root package name */
        final Bundle f17294b;

        /* renamed from: c  reason: collision with root package name */
        final boolean f17295c;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C0313a(Intent intent, Bundle bundle, boolean z) {
            this.f17293a = intent;
            this.f17294b = bundle;
            this.f17295c = z;
        }
    }
}
