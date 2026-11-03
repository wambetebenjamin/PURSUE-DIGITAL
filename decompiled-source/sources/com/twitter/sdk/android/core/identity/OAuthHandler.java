package com.twitter.sdk.android.core.identity;

import android.app.Activity;
import android.content.Intent;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterSession;
/* loaded from: classes10.dex */
class OAuthHandler extends AuthHandler {
    /* JADX INFO: Access modifiers changed from: package-private */
    public OAuthHandler(TwitterAuthConfig twitterAuthConfig, Callback<TwitterSession> callback, int i2) {
        super(twitterAuthConfig, callback, i2);
    }

    @Override // com.twitter.sdk.android.core.identity.AuthHandler
    public boolean authorize(Activity activity) {
        activity.startActivityForResult(newIntent(activity), this.requestCode);
        return true;
    }

    Intent newIntent(Activity activity) {
        Intent intent = new Intent(activity, OAuthActivity.class);
        intent.putExtra("auth_config", getAuthConfig());
        return intent;
    }
}
