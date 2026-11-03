package com.google.android.gms.games;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.tasks.Task;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
@Deprecated
/* loaded from: classes5.dex */
public interface GamesClient {
    @Deprecated
    Task<Bundle> getActivationHint();

    @Deprecated
    Task<String> getAppId();

    @Deprecated
    Task<String> getCurrentAccountName();

    @Deprecated
    Task<Intent> getSettingsIntent();

    @Deprecated
    Task<Void> setGravityForPopups(int i2);

    @Deprecated
    Task<Void> setViewForPopups(View view);
}
