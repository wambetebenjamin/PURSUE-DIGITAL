package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.internal.zzg;
import com.google.android.gms.internal.games_v2.zzfu;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class SnapshotContentsEntity extends zzg implements SnapshotContents {
    private Contents zzb;
    private static final Object zza = new Object();
    public static final Parcelable.Creator<SnapshotContentsEntity> CREATOR = new zza();

    public SnapshotContentsEntity(Contents contents) {
        this.zzb = contents;
    }

    private final boolean zzc(int i2, byte[] bArr, int i3, int i4, boolean z) {
        Preconditions.checkState(!isClosed(), "Must provide a previously opened SnapshotContents");
        synchronized (zza) {
            FileOutputStream fileOutputStream = new FileOutputStream(this.zzb.getParcelFileDescriptor().getFileDescriptor());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            try {
                FileChannel channel = fileOutputStream.getChannel();
                channel.position(i2);
                bufferedOutputStream.write(bArr, i3, i4);
                if (z) {
                    channel.truncate(bArr.length);
                }
                bufferedOutputStream.flush();
            } catch (IOException e2) {
                zzfu.zzd("SnapshotContentsEntity", "Failed to write snapshot data", e2);
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotContents
    public final ParcelFileDescriptor getParcelFileDescriptor() {
        Preconditions.checkState(!isClosed(), "Cannot mutate closed contents!");
        return this.zzb.getParcelFileDescriptor();
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotContents
    public final boolean isClosed() {
        return this.zzb == null;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotContents
    public final boolean modifyBytes(int i2, byte[] bArr, int i3, int i4) {
        return zzc(i2, bArr, i3, bArr.length, false);
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotContents
    public final byte[] readFully() throws IOException {
        byte[] readInputStreamFully;
        Preconditions.checkState(!isClosed(), "Must provide a previously opened Snapshot");
        synchronized (zza) {
            FileInputStream fileInputStream = new FileInputStream(this.zzb.getParcelFileDescriptor().getFileDescriptor());
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            try {
                fileInputStream.getChannel().position(0L);
                readInputStreamFully = IOUtils.readInputStreamFully(bufferedInputStream, false);
                fileInputStream.getChannel().position(0L);
            } catch (IOException e2) {
                zzfu.zzf("SnapshotContentsEntity", "Failed to read snapshot data", e2);
                throw e2;
            }
        }
        return readInputStreamFully;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotContents
    public final boolean writeBytes(byte[] bArr) {
        return zzc(0, bArr, 0, bArr.length, true);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzb, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotContents
    public final Contents zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotContents
    public final void zzb() {
        this.zzb = null;
    }
}
