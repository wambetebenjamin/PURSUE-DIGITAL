package com.google.android.gms.games.snapshot;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public interface Snapshot extends Freezable<Snapshot>, Parcelable {
    SnapshotMetadata getMetadata();

    SnapshotContents getSnapshotContents();
}
