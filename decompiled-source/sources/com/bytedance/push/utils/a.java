package com.bytedance.push.utils;

import android.net.Uri;
import java.util.ArrayList;
import java.util.List;
/* compiled from: Component.java */
/* loaded from: classes2.dex */
public class a {
    public String authorities;
    public List<b> intentFilter = new ArrayList();
    public String name;
    public String permission;
    public String processName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof a) {
            a aVar = (a) obj;
            List<b> list = this.intentFilter;
            if (list == null ? aVar.intentFilter == null : list.equals(aVar.intentFilter)) {
                String str = this.name;
                if (str == null ? aVar.name == null : str.equals(aVar.name)) {
                    String str2 = this.processName;
                    if (str2 == null ? aVar.processName == null : str2.equals(aVar.processName)) {
                        String str3 = this.permission;
                        if (str3 == null ? aVar.permission == null : str3.equals(aVar.permission)) {
                            String str4 = this.authorities;
                            String str5 = aVar.authorities;
                            return str4 == null ? str5 == null : str4.equals(str5);
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        List<b> list = this.intentFilter;
        int hashCode = (list != null ? list.hashCode() : 0) * 31;
        String str = this.name;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.processName;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.permission;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.authorities;
        return hashCode4 + (str4 != null ? str4.hashCode() : 0);
    }

    public a(String str) {
        this.name = str;
    }

    public String toString() {
        return "Component{name='" + this.name + "', intentFilter=" + this.intentFilter + ", processName='" + this.processName + "', permission='" + this.permission + "', authorities='" + this.authorities + "'}";
    }

    /* compiled from: Component.java */
    /* loaded from: classes2.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        List<String> f12020a;

        /* renamed from: b  reason: collision with root package name */
        List<String> f12021b;

        /* renamed from: c  reason: collision with root package name */
        Uri f12022c;

        /* renamed from: d  reason: collision with root package name */
        String f12023d;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof b) {
                b bVar = (b) obj;
                List<String> list = this.f12020a;
                if (list == null ? bVar.f12020a == null : list.equals(bVar.f12020a)) {
                    List<String> list2 = this.f12021b;
                    if (list2 == null ? bVar.f12021b == null : list2.equals(bVar.f12021b)) {
                        String str = this.f12023d;
                        if (str == null ? bVar.f12023d == null : str.equals(bVar.f12023d)) {
                            Uri uri = this.f12022c;
                            Uri uri2 = bVar.f12022c;
                            return uri != null ? uri.equals(uri2) : uri2 == null;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public String toString() {
            return "IntentFilter{actions=" + this.f12020a + ", categories=" + this.f12021b + ", data=" + this.f12022c + ", mimetype=" + this.f12023d + '}';
        }

        public int hashCode() {
            List<String> list = this.f12020a;
            int hashCode = (list != null ? list.hashCode() : 0) * 31;
            List<String> list2 = this.f12021b;
            int hashCode2 = (hashCode + (list2 != null ? list2.hashCode() : 0)) * 31;
            String str = this.f12023d;
            int hashCode3 = (hashCode2 + (str != null ? str.hashCode() : 0)) * 31;
            Uri uri = this.f12022c;
            return hashCode3 + (uri != null ? uri.hashCode() : 0);
        }

        public b(List<String> list, List<String> list2, String str) {
            this.f12020a = list;
            this.f12021b = list2;
            this.f12023d = str;
        }

        public b(List<String> list, List<String> list2, Uri uri) {
            this.f12020a = list;
            this.f12021b = list2;
            this.f12022c = uri;
        }

        public b(List<String> list, List<String> list2) {
            this.f12020a = list;
            this.f12021b = list2;
        }

        public b(List<String> list) {
            this(list, null);
        }
    }

    private static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* compiled from: Component.java */
    /* renamed from: com.bytedance.push.utils.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class C0171a {

        /* renamed from: a  reason: collision with root package name */
        private a f12019a;

        public C0171a(String str) {
            this.f12019a = new a(str);
        }

        public C0171a setProcess(String str) {
            this.f12019a.processName = str;
            return this;
        }

        public C0171a setPermission(String str) {
            this.f12019a.permission = str;
            return this;
        }

        public C0171a addIntentFilter(b bVar) {
            if (bVar == null) {
                return this;
            }
            this.f12019a.intentFilter.add(bVar);
            return this;
        }

        public C0171a setAuthorities(String str) {
            this.f12019a.authorities = str;
            return this;
        }

        public a build() {
            return this.f12019a;
        }

        public static C0171a create(String str) {
            return new C0171a(str);
        }
    }
}
