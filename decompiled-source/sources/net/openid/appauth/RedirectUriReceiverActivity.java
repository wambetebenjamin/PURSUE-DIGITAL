package net.openid.appauth;

import android.app.Activity;
import android.os.Bundle;
/* loaded from: classes10.dex */
public class RedirectUriReceiverActivity extends Activity {
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        startActivity(AuthorizationManagementActivity.createResponseHandlingIntent(this, getIntent().getData()));
        finish();
    }
}
