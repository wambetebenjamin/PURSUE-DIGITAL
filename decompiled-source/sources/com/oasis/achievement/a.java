package com.oasis.achievement;

import com.oasis.Logger.Logger;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class a extends AchievementAgent {
    @Override // com.oasis.achievement.AchievementAgent
    public void increaseAchievementProgress(String str, int i2, AchievementsActionListener achievementsActionListener) {
        Logger.i("AchievementAgent", "DefaultAchievementAgent increaseAchievementProgress");
    }

    @Override // com.oasis.achievement.AchievementAgent
    public void showAchievements(AchievementsActionListener achievementsActionListener) {
        Logger.i("AchievementAgent", "DefaultAchievementAgent showAchievements");
    }

    @Override // com.oasis.achievement.AchievementAgent
    public void unlockAchievement(String str, AchievementsActionListener achievementsActionListener) {
        Logger.i("AchievementAgent", "DefaultAchievementAgent unlockAchievement");
    }
}
