package com.bytedance.bdinstall.util;

import android.text.TextUtils;
import com.bytedance.bdinstall.am;
/* compiled from: OptionsForCommonParamProxy.java */
/* loaded from: classes.dex */
public class n {

    /* renamed from: a  reason: collision with root package name */
    private com.bytedance.bdinstall.h f4064a;

    /* renamed from: b  reason: collision with root package name */
    private am f4065b;

    public n(am amVar) {
        this.f4065b = amVar;
    }

    public n(com.bytedance.bdinstall.h hVar) {
        this.f4064a = hVar;
    }

    public String a() {
        am amVar = this.f4065b;
        if (amVar != null) {
            String m = amVar.m();
            return TextUtils.isEmpty(m) ? this.f4065b.l() : m;
        }
        com.bytedance.bdinstall.h hVar = this.f4064a;
        if (hVar != null) {
            String tweakedChannel = hVar.getTweakedChannel();
            return TextUtils.isEmpty(tweakedChannel) ? this.f4064a.getChannel() : tweakedChannel;
        }
        return null;
    }

    public long b() {
        int aid;
        am amVar = this.f4065b;
        if (amVar != null) {
            aid = amVar.a();
        } else {
            com.bytedance.bdinstall.h hVar = this.f4064a;
            if (hVar == null) {
                return 0L;
            }
            aid = hVar.getAid();
        }
        return aid;
    }

    public String c() {
        am amVar = this.f4065b;
        if (amVar != null) {
            return amVar.k();
        }
        com.bytedance.bdinstall.h hVar = this.f4064a;
        if (hVar != null) {
            return hVar.getAppName();
        }
        return null;
    }

    public long d() {
        am amVar = this.f4065b;
        if (amVar != null) {
            return amVar.h();
        }
        com.bytedance.bdinstall.h hVar = this.f4064a;
        if (hVar != null) {
            return hVar.getVersionCode();
        }
        return 0L;
    }

    public String e() {
        am amVar = this.f4065b;
        if (amVar != null) {
            return amVar.f();
        }
        com.bytedance.bdinstall.h hVar = this.f4064a;
        if (hVar != null) {
            return hVar.getVersion();
        }
        return null;
    }

    public long f() {
        am amVar = this.f4065b;
        if (amVar != null) {
            return amVar.j();
        }
        com.bytedance.bdinstall.h hVar = this.f4064a;
        if (hVar != null) {
            return hVar.getManifestVersionCode();
        }
        return 0L;
    }

    public long g() {
        am amVar = this.f4065b;
        if (amVar != null) {
            return amVar.i();
        }
        com.bytedance.bdinstall.h hVar = this.f4064a;
        if (hVar != null) {
            return hVar.getUpdateVersionCode();
        }
        return 0L;
    }
}
