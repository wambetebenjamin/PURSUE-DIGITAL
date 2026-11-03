package com.bytedance.frameworks.baselib.network.utils;

import java.util.regex.Pattern;
/* compiled from: PatternWrap.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private final Pattern f8512a;

    public a(String str) {
        this.f8512a = Pattern.compile(str);
    }

    public boolean a(CharSequence charSequence) {
        Pattern pattern = this.f8512a;
        if (pattern == null) {
            return false;
        }
        return pattern.matcher(charSequence).matches();
    }

    public boolean b(CharSequence charSequence) {
        Pattern pattern = this.f8512a;
        if (pattern == null) {
            return false;
        }
        return pattern.matcher(charSequence).find();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            a aVar = (a) obj;
            Pattern pattern = this.f8512a;
            if (pattern == null && aVar.f8512a == null) {
                return true;
            }
            if (pattern != null && aVar.f8512a != null) {
                return pattern.pattern().equals(aVar.f8512a.pattern()) && this.f8512a.flags() == aVar.f8512a.flags();
            }
        }
        return false;
    }
}
