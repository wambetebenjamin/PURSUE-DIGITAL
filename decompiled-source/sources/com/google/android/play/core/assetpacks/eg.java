package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
final class eg {

    /* renamed from: a  reason: collision with root package name */
    private static final com.google.android.play.core.assetpacks.internal.o f16763a = new com.google.android.play.core.assetpacks.internal.o("PatchSliceTaskHandler");

    /* renamed from: b  reason: collision with root package name */
    private final bh f16764b;

    /* renamed from: c  reason: collision with root package name */
    private final com.google.android.play.core.assetpacks.internal.aq f16765c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public eg(bh bhVar, com.google.android.play.core.assetpacks.internal.aq aqVar) {
        this.f16764b = bhVar;
        this.f16765c = aqVar;
    }

    public final void a(ef efVar) {
        File h2 = this.f16764b.h(efVar.l, efVar.f16755a, efVar.f16756b);
        File file = new File(this.f16764b.i(efVar.l, efVar.f16755a, efVar.f16756b), efVar.f16760f);
        try {
            InputStream inputStream = efVar.f16762h;
            if (efVar.f16759e == 2) {
                inputStream = new GZIPInputStream(inputStream, 8192);
            }
            bk bkVar = new bk(h2, file);
            File p = this.f16764b.p(efVar.l, efVar.f16757c, efVar.f16758d, efVar.f16760f);
            if (!p.exists()) {
                p.mkdirs();
            }
            eo eoVar = new eo(this.f16764b, efVar.l, efVar.f16757c, efVar.f16758d, efVar.f16760f);
            com.google.android.play.core.assetpacks.internal.am.a(bkVar, inputStream, new cn(p, eoVar), efVar.f16761g);
            eoVar.i(0);
            inputStream.close();
            f16763a.d("Patching and extraction finished for slice %s of pack %s.", efVar.f16760f, efVar.l);
            ((y) this.f16765c.a()).g(efVar.k, efVar.l, efVar.f16760f, 0);
            try {
                efVar.f16762h.close();
            } catch (IOException unused) {
                f16763a.e("Could not close file for slice %s of pack %s.", efVar.f16760f, efVar.l);
            }
        } catch (IOException e2) {
            f16763a.b("IOException during patching %s.", e2.getMessage());
            throw new ck(String.format("Error patching slice %s of pack %s.", efVar.f16760f, efVar.l), e2, efVar.k);
        }
    }
}
