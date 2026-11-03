package com.twitter.sdk.android.core.internal;

import android.content.Context;
import android.text.TextUtils;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.internal.persistence.PreferenceStore;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public class AdvertisingInfoProvider {
    private static final String PREFKEY_ADVERTISING_ID = "advertising_id";
    private static final String PREFKEY_LIMIT_AD_TRACKING = "limit_ad_tracking_enabled";
    private final Context context;
    private final PreferenceStore preferenceStore;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdvertisingInfoProvider(Context context, PreferenceStore preferenceStore) {
        this.context = context.getApplicationContext();
        this.preferenceStore = preferenceStore;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdvertisingInfo getAdvertisingInfo() {
        AdvertisingInfo infoFromPreferences = getInfoFromPreferences();
        if (isInfoValid(infoFromPreferences)) {
            Twitter.getLogger().d("Twitter", "Using AdvertisingInfo from Preference Store");
            refreshInfoIfNeededAsync(infoFromPreferences);
            return infoFromPreferences;
        }
        AdvertisingInfo advertisingInfoFromStrategies = getAdvertisingInfoFromStrategies();
        storeInfoToPreferences(advertisingInfoFromStrategies);
        return advertisingInfoFromStrategies;
    }

    private void refreshInfoIfNeededAsync(final AdvertisingInfo advertisingInfo) {
        new Thread(new Runnable() { // from class: com.twitter.sdk.android.core.internal.AdvertisingInfoProvider$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AdvertisingInfoProvider.this.m720x1d26c6d0(advertisingInfo);
            }
        }).start();
    }

    /* renamed from: lambda$refreshInfoIfNeededAsync$0$com-twitter-sdk-android-core-internal-AdvertisingInfoProvider  reason: not valid java name */
    public /* synthetic */ void m720x1d26c6d0(AdvertisingInfo advertisingInfo) {
        AdvertisingInfo advertisingInfoFromStrategies = getAdvertisingInfoFromStrategies();
        if (advertisingInfo.equals(advertisingInfoFromStrategies)) {
            return;
        }
        Twitter.getLogger().d("Twitter", "Asychronously getting Advertising Info and storing it to preferences");
        storeInfoToPreferences(advertisingInfoFromStrategies);
    }

    private void storeInfoToPreferences(AdvertisingInfo advertisingInfo) {
        if (isInfoValid(advertisingInfo)) {
            PreferenceStore preferenceStore = this.preferenceStore;
            preferenceStore.save(preferenceStore.edit().putString(PREFKEY_ADVERTISING_ID, advertisingInfo.advertisingId).putBoolean(PREFKEY_LIMIT_AD_TRACKING, advertisingInfo.limitAdTrackingEnabled));
            return;
        }
        PreferenceStore preferenceStore2 = this.preferenceStore;
        preferenceStore2.save(preferenceStore2.edit().remove(PREFKEY_ADVERTISING_ID).remove(PREFKEY_LIMIT_AD_TRACKING));
    }

    private AdvertisingInfo getInfoFromPreferences() {
        return new AdvertisingInfo(this.preferenceStore.get().getString(PREFKEY_ADVERTISING_ID, ""), this.preferenceStore.get().getBoolean(PREFKEY_LIMIT_AD_TRACKING, false));
    }

    private AdvertisingInfoStrategy getReflectionStrategy() {
        return new AdvertisingInfoReflectionStrategy(this.context);
    }

    private boolean isInfoValid(AdvertisingInfo advertisingInfo) {
        return (advertisingInfo == null || TextUtils.isEmpty(advertisingInfo.advertisingId)) ? false : true;
    }

    private AdvertisingInfo getAdvertisingInfoFromStrategies() {
        AdvertisingInfo advertisingInfo = getReflectionStrategy().getAdvertisingInfo();
        if (!isInfoValid(advertisingInfo)) {
            Twitter.getLogger().d("Twitter", "AdvertisingInfo not present");
        } else {
            Twitter.getLogger().d("Twitter", "Using AdvertisingInfo from Reflection Provider");
        }
        return advertisingInfo;
    }
}
