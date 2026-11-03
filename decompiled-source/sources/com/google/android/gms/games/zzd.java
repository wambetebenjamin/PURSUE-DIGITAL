package com.google.android.gms.games;

import com.google.android.gms.common.Feature;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzd {
    public static final Feature zza;
    public static final Feature zzb;
    public static final Feature zzc;
    public static final Feature zzd;
    public static final Feature zze;
    public static final Feature zzf;
    public static final Feature zzg;
    public static final Feature zzh;
    public static final Feature zzi;
    public static final Feature zzj;
    public static final Feature zzk;
    public static final Feature zzl;
    public static final Feature[] zzm;

    static {
        Feature feature = new Feature("games_get_account_selection_intent", 1L, true);
        zza = feature;
        Feature feature2 = new Feature("games_get_privacy_settings_intent", 1L, true);
        zzb = feature2;
        Feature feature3 = new Feature("games_load_player_force_reload", 1L, true);
        zzc = feature3;
        Feature feature4 = new Feature("games_load_profile_capabilities", 2L, true);
        zzd = feature4;
        Feature feature5 = new Feature("games_recall", 1L, true);
        zze = feature5;
        Feature feature6 = new Feature("games_report_player", 1L, true);
        zzf = feature6;
        Feature feature7 = new Feature("games_app_shortcuts", 1L, true);
        zzg = feature7;
        Feature feature8 = new Feature("games_game_streaks", 1L, true);
        zzh = feature8;
        Feature feature9 = new Feature("games_public_gamer_profile", 1L, true);
        zzi = feature9;
        Feature feature10 = new Feature("games_player_game_events", 1L, true);
        zzj = feature10;
        Feature feature11 = new Feature("games_player_game_events_immediate", 1L, false);
        zzk = feature11;
        Feature feature12 = new Feature("games_rewards_internal", 1L, false);
        zzl = feature12;
        zzm = new Feature[]{feature, feature2, feature3, feature4, feature5, feature6, feature7, feature8, feature9, feature10, feature11, feature12};
    }
}
