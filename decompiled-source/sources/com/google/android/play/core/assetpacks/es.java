package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
final class es {

    /* renamed from: a  reason: collision with root package name */
    private static final com.google.android.play.core.assetpacks.internal.o f16797a = new com.google.android.play.core.assetpacks.internal.o("VerifySliceTaskHandler");

    /* renamed from: b  reason: collision with root package name */
    private final bh f16798b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public es(bh bhVar) {
        this.f16798b = bhVar;
    }

    private final void b(er erVar, File file) {
        try {
            File o = this.f16798b.o(erVar.l, erVar.f16793a, erVar.f16794b, erVar.f16795c);
            if (o.exists()) {
                try {
                    if (!dq.a(eq.a(file, o)).equals(erVar.f16796d)) {
                        throw new ck(String.format("Verification failed for slice %s.", erVar.f16795c), erVar.k);
                    }
                    f16797a.d("Verification of slice %s of pack %s successful.", erVar.f16795c, erVar.l);
                    return;
                } catch (IOException e2) {
                    throw new ck(String.format("Could not digest file during verification for slice %s.", erVar.f16795c), e2, erVar.k);
                } catch (NoSuchAlgorithmException e3) {
                    throw new ck("SHA256 algorithm not supported.", e3, erVar.k);
                }
            }
            throw new ck(String.format("Cannot find metadata files for slice %s.", erVar.f16795c), erVar.k);
        } catch (IOException e4) {
            throw new ck(String.format("Could not reconstruct slice archive during verification for slice %s.", erVar.f16795c), e4, erVar.k);
        }
    }

    public final void a(er erVar) {
        File p = this.f16798b.p(erVar.l, erVar.f16793a, erVar.f16794b, erVar.f16795c);
        if (p.exists()) {
            b(erVar, p);
            File q = this.f16798b.q(erVar.l, erVar.f16793a, erVar.f16794b, erVar.f16795c);
            if (!q.exists()) {
                q.mkdirs();
            }
            if (!p.renameTo(q)) {
                throw new ck(String.format("Failed to move slice %s after verification.", erVar.f16795c), erVar.k);
            }
            return;
        }
        throw new ck(String.format("Cannot find unverified files for slice %s.", erVar.f16795c), erVar.k);
    }
}
