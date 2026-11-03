package com.bytedance.bdinstall.util;

import android.content.Context;
import android.util.Pair;
import com.bytedance.bdinstall.am;
/* compiled from: SensitiveUtils.java */
/* loaded from: classes.dex */
public class q {

    /* renamed from: a  reason: collision with root package name */
    private static final r<Pair<String, Boolean>> f4077a = new r<Pair<String, Boolean>>() { // from class: com.bytedance.bdinstall.util.q.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bytedance.bdinstall.util.r
        /* renamed from: a */
        public Pair<String, Boolean> b(Object... objArr) {
            try {
                com.bytedance.bdinstall.sensitive.a ad = ((am) objArr[0]).ad();
                if (ad != null) {
                    return ad.getGAID();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return new Pair<>(null, false);
        }
    };

    public static String a(Context context) {
        return "";
    }

    public static String a(am amVar) {
        com.bytedance.bdinstall.sensitive.a ad = amVar.ad();
        return ad != null ? ad.getNetworkOperatorName() : "";
    }

    public static String b(am amVar) {
        com.bytedance.bdinstall.sensitive.a ad = amVar.ad();
        return ad != null ? ad.getNetworkOperator() : "";
    }

    public static String c(am amVar) {
        com.bytedance.bdinstall.sensitive.a ad = amVar.ad();
        return ad != null ? ad.getSimCountryIso() : "";
    }

    public static String d(am amVar) {
        com.bytedance.bdinstall.sensitive.a ad = amVar.ad();
        return ad != null ? ad.getCountry() : "";
    }

    public static Pair<String, Boolean> e(am amVar) {
        return f4077a.c(amVar);
    }
}
