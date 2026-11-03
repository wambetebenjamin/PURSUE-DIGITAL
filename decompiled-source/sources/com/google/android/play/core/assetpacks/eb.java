package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class eb {

    /* renamed from: a  reason: collision with root package name */
    private static final com.google.android.play.core.assetpacks.internal.o f16746a = new com.google.android.play.core.assetpacks.internal.o("PackMetadataManager");

    /* renamed from: b  reason: collision with root package name */
    private final bh f16747b;

    /* renamed from: c  reason: collision with root package name */
    private final ed f16748c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public eb(bh bhVar, ed edVar) {
        this.f16747b = bhVar;
        this.f16748c = edVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String a(String str) {
        if (this.f16747b.G(str)) {
            int a2 = this.f16748c.a();
            bh bhVar = this.f16747b;
            File k = bhVar.k(str, a2, bhVar.c(str));
            try {
                if (!k.exists()) {
                    return String.valueOf(a2);
                }
                FileInputStream fileInputStream = new FileInputStream(k);
                Properties properties = new Properties();
                properties.load(fileInputStream);
                fileInputStream.close();
                String property = properties.getProperty("moduleVersionTag");
                return property == null ? String.valueOf(a2) : property;
            } catch (IOException unused) {
                f16746a.b("Failed to read pack version tag for pack %s", str);
                return "";
            }
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(String str, int i2, long j2, String str2) throws IOException {
        if (str2 == null || str2.isEmpty()) {
            str2 = String.valueOf(i2);
        }
        Properties properties = new Properties();
        properties.put("moduleVersionTag", str2);
        File k = this.f16747b.k(str, i2, j2);
        k.getParentFile().mkdirs();
        k.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(k);
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
            }
            throw th;
        }
    }
}
