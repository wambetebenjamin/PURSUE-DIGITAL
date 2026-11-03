package com.google.android.gms.games;

import android.content.Intent;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.tasks.Task;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public interface AchievementsClient {
    Task<Intent> getAchievementsIntent();

    void increment(String str, int i2);

    Task<Boolean> incrementImmediate(String str, int i2);

    Task<AnnotatedData<AchievementBuffer>> load(boolean z);

    void reveal(String str);

    Task<Void> revealImmediate(String str);

    void setSteps(String str, int i2);

    Task<Boolean> setStepsImmediate(String str, int i2);

    void unlock(String str);

    Task<Void> unlockImmediate(String str);
}
