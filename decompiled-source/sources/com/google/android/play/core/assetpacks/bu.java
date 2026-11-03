package com.google.android.play.core.assetpacks;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.tasks.Tasks;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
final class bu {

    /* renamed from: a  reason: collision with root package name */
    private final com.google.android.play.core.assetpacks.internal.aq f16544a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bu(com.google.android.play.core.assetpacks.internal.aq aqVar) {
        this.f16544a = aqVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final InputStream a(int i2, String str, String str2, int i3) {
        try {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) Tasks.await(((y) this.f16544a.a()).a(i2, str, str2, i3));
            if (parcelFileDescriptor == null || parcelFileDescriptor.getFileDescriptor() == null) {
                throw new ck(String.format("Corrupted ParcelFileDescriptor, session %s packName %s sliceId %s, chunkNumber %s", Integer.valueOf(i2), str, str2, Integer.valueOf(i3)), i2);
            }
            return new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor);
        } catch (InterruptedException e2) {
            throw new ck("Extractor was interrupted while waiting for chunk file.", e2, i2);
        } catch (ExecutionException e3) {
            throw new ck(String.format("Error opening chunk file, session %s packName %s sliceId %s, chunkNumber %s", Integer.valueOf(i2), str, str2, Integer.valueOf(i3)), e3, i2);
        }
    }
}
