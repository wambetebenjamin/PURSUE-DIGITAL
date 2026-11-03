package com.google.android.gms.games.snapshot;

import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.drive.Contents;
import java.io.IOException;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public interface SnapshotContents extends Parcelable {
    ParcelFileDescriptor getParcelFileDescriptor();

    boolean isClosed();

    boolean modifyBytes(int i2, byte[] bArr, int i3, int i4);

    byte[] readFully() throws IOException;

    boolean writeBytes(byte[] bArr);

    Contents zza();

    void zzb();
}
