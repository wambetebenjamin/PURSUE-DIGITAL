package com.bytedance.sdk.account.platform.base;

import android.net.Uri;
import android.os.Bundle;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* compiled from: Request.java */
/* loaded from: classes2.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    public final String f12799a;

    /* renamed from: b  reason: collision with root package name */
    public final String f12800b;

    /* renamed from: c  reason: collision with root package name */
    public final String f12801c;
    public final String callerLocalEntry;

    /* renamed from: d  reason: collision with root package name */
    public final int f12802d;

    /* renamed from: e  reason: collision with root package name */
    public final Map<String, String> f12803e;
    public final Bundle extra;
    public final Set<String> optionalScopes0;
    public final Set<String> optionalScopes1;
    public final Uri redirectUri;
    public final String responseType;
    public final Set<String> scopes;
    public final String state;

    /* compiled from: Request.java */
    /* loaded from: classes2.dex */
    public interface b {
        public static final String CODE = "code";
        public static final String ID_TOKEN = "id_token";
        public static final String TOKEN = "token";
    }

    private k(a aVar) {
        if (aVar.scopes != null) {
            this.scopes = aVar.scopes;
        } else {
            this.scopes = new HashSet();
        }
        if (aVar.optionalScopes0 != null) {
            this.optionalScopes0 = aVar.optionalScopes0;
        } else {
            this.optionalScopes0 = new HashSet();
        }
        if (aVar.optionalScopes1 != null) {
            this.optionalScopes1 = aVar.optionalScopes1;
        } else {
            this.optionalScopes1 = new HashSet();
        }
        this.state = aVar.state;
        this.callerLocalEntry = aVar.callerLocalEntry;
        if (aVar.extra != null) {
            this.extra = aVar.extra;
        } else {
            this.extra = new Bundle();
        }
        this.responseType = aVar.f12804a;
        this.f12799a = aVar.f12806c;
        this.redirectUri = aVar.f12805b;
        this.f12800b = aVar.f12807d;
        this.f12801c = aVar.f12808e;
        this.f12802d = aVar.f12809f;
        this.f12803e = aVar.f12810g;
    }

    public a a() {
        return new a(this);
    }

    /* compiled from: Request.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        String f12804a;

        /* renamed from: b  reason: collision with root package name */
        Uri f12805b;

        /* renamed from: c  reason: collision with root package name */
        String f12806c;
        String callerLocalEntry;

        /* renamed from: d  reason: collision with root package name */
        String f12807d;

        /* renamed from: e  reason: collision with root package name */
        String f12808e;
        Bundle extra;

        /* renamed from: f  reason: collision with root package name */
        int f12809f;

        /* renamed from: g  reason: collision with root package name */
        Map<String, String> f12810g;
        Set<String> optionalScopes0;
        Set<String> optionalScopes1;
        Set<String> scopes;
        String state;

        public a() {
        }

        a(k kVar) {
            this.scopes = kVar.scopes;
            this.optionalScopes0 = kVar.optionalScopes0;
            this.optionalScopes1 = kVar.optionalScopes1;
            this.state = kVar.state;
            this.callerLocalEntry = kVar.callerLocalEntry;
            this.extra = kVar.extra;
            this.f12804a = kVar.responseType;
            this.f12806c = kVar.f12799a;
            this.f12805b = kVar.redirectUri;
            this.f12807d = kVar.f12800b;
            this.f12808e = kVar.f12801c;
            this.f12809f = kVar.f12802d;
            this.f12810g = kVar.f12803e;
        }

        public a setScopes(Set<String> set) {
            this.scopes = set;
            return this;
        }

        public a setOptionalScopes0(Set<String> set) {
            this.optionalScopes0 = set;
            return this;
        }

        public a setOptionalScopes1(Set<String> set) {
            this.optionalScopes1 = set;
            return this;
        }

        public a setState(String str) {
            this.state = str;
            return this;
        }

        public a setCallerLocalEntry(String str) {
            this.callerLocalEntry = str;
            return this;
        }

        public a setExtra(Bundle bundle) {
            this.extra = bundle;
            return this;
        }

        public a a(String str) {
            this.f12804a = str;
            return this;
        }

        public a b(String str) {
            this.f12806c = str;
            return this;
        }

        public a setRedirectUri(Uri uri) {
            this.f12805b = uri;
            return this;
        }

        public a c(String str) {
            this.f12807d = str;
            return this;
        }

        public a d(String str) {
            this.f12808e = str;
            return this;
        }

        public a a(int i2) {
            this.f12809f = i2;
            return this;
        }

        public a a(Map<String, String> map) {
            this.f12810g = map;
            return this;
        }

        public k build() {
            return new k(this);
        }
    }
}
