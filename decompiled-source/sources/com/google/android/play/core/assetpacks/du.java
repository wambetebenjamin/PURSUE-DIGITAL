package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.IOException;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
final class du {

    /* renamed from: a  reason: collision with root package name */
    private static final com.google.android.play.core.assetpacks.internal.o f16723a = new com.google.android.play.core.assetpacks.internal.o("MergeSliceTaskHandler");

    /* renamed from: b  reason: collision with root package name */
    private final bh f16724b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public du(bh bhVar) {
        this.f16724b = bhVar;
    }

    private static void b(File file, File file2) {
        File[] listFiles;
        if (file.isDirectory()) {
            file2.mkdirs();
            for (File file3 : file.listFiles()) {
                b(file3, new File(file2, file3.getName()));
            }
            if (!file.delete()) {
                throw new ck("Unable to delete directory: ".concat(String.valueOf(String.valueOf(file))));
            }
        } else if (file2.exists()) {
            throw new ck("File clashing with existing file from other slice: ".concat(file2.toString()));
        } else {
            if (!file.renameTo(file2)) {
                throw new ck("Unable to move file: ".concat(String.valueOf(String.valueOf(file))));
            }
        }
    }

    public final void a(dt dtVar) {
        File q = this.f16724b.q(dtVar.l, dtVar.f16720a, dtVar.f16721b, dtVar.f16722c);
        if (!q.exists()) {
            throw new ck(String.format("Cannot find verified files for slice %s.", dtVar.f16722c), dtVar.k);
        }
        File j2 = this.f16724b.j(dtVar.l, dtVar.f16720a, dtVar.f16721b);
        if (!j2.exists()) {
            j2.mkdirs();
        }
        b(q, j2);
        try {
            this.f16724b.A(dtVar.l, dtVar.f16720a, dtVar.f16721b, this.f16724b.b(dtVar.l, dtVar.f16720a, dtVar.f16721b) + 1);
        } catch (IOException e2) {
            f16723a.b("Writing merge checkpoint failed with %s.", e2.getMessage());
            throw new ck("Writing merge checkpoint failed.", e2, dtVar.k);
        }
    }
}
