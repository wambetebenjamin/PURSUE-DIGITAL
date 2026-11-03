package net.openid.appauth;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.internal.Logger;
import org.json.JSONException;
/* loaded from: classes10.dex */
public class AuthorizationManagementActivity extends Activity {
    static final String KEY_AUTHORIZATION_STARTED = "authStarted";
    static final String KEY_AUTH_INTENT = "authIntent";
    static final String KEY_AUTH_REQUEST = "authRequest";
    static final String KEY_CANCEL_INTENT = "cancelIntent";
    static final String KEY_COMPLETE_INTENT = "completeIntent";
    private Intent mAuthIntent;
    private AuthorizationRequest mAuthRequest;
    private boolean mAuthorizationStarted = false;
    private PendingIntent mCancelIntent;
    private PendingIntent mCompleteIntent;

    public static Intent createStartIntent(Context context, AuthorizationRequest authorizationRequest, Intent intent, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        Intent createBaseIntent = createBaseIntent(context);
        createBaseIntent.putExtra(KEY_AUTH_INTENT, intent);
        createBaseIntent.putExtra(KEY_AUTH_REQUEST, authorizationRequest.jsonSerializeString());
        createBaseIntent.putExtra(KEY_COMPLETE_INTENT, pendingIntent);
        createBaseIntent.putExtra(KEY_CANCEL_INTENT, pendingIntent2);
        return createBaseIntent;
    }

    public static Intent createStartForResultIntent(Context context, AuthorizationRequest authorizationRequest, Intent intent) {
        return createStartIntent(context, authorizationRequest, intent, null, null);
    }

    public static Intent createResponseHandlingIntent(Context context, Uri uri) {
        Intent createBaseIntent = createBaseIntent(context);
        createBaseIntent.setData(uri);
        createBaseIntent.addFlags(603979776);
        return createBaseIntent;
    }

    private static Intent createBaseIntent(Context context) {
        return new Intent(context, AuthorizationManagementActivity.class);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            extractState(getIntent().getExtras());
        } else {
            extractState(bundle);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (!this.mAuthorizationStarted) {
            startActivity(this.mAuthIntent);
            this.mAuthorizationStarted = true;
            return;
        }
        if (getIntent().getData() != null) {
            handleAuthorizationComplete();
        } else {
            handleAuthorizationCanceled();
        }
        finish();
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(KEY_AUTHORIZATION_STARTED, this.mAuthorizationStarted);
        bundle.putParcelable(KEY_AUTH_INTENT, this.mAuthIntent);
        bundle.putString(KEY_AUTH_REQUEST, this.mAuthRequest.jsonSerializeString());
        bundle.putParcelable(KEY_COMPLETE_INTENT, this.mCompleteIntent);
        bundle.putParcelable(KEY_CANCEL_INTENT, this.mCancelIntent);
    }

    private void handleAuthorizationComplete() {
        Uri data = getIntent().getData();
        Intent extractResponseData = extractResponseData(data);
        if (extractResponseData == null) {
            Logger.error("Failed to extract OAuth2 response from redirect", new Object[0]);
            return;
        }
        extractResponseData.setData(data);
        if (this.mCompleteIntent != null) {
            Logger.debug("Authorization complete - invoking completion intent", new Object[0]);
            try {
                this.mCompleteIntent.send(this, 0, extractResponseData);
                return;
            } catch (PendingIntent.CanceledException e2) {
                Logger.error("Failed to send completion intent", e2);
                return;
            }
        }
        setResult(-1, extractResponseData);
    }

    private void handleAuthorizationCanceled() {
        Logger.debug("Authorization flow canceled by user", new Object[0]);
        Intent intent = AuthorizationException.fromTemplate(AuthorizationException.GeneralErrors.USER_CANCELED_AUTH_FLOW, null).toIntent();
        PendingIntent pendingIntent = this.mCancelIntent;
        if (pendingIntent != null) {
            try {
                pendingIntent.send(this, 0, intent);
                return;
            } catch (PendingIntent.CanceledException e2) {
                Logger.error("Failed to send cancel intent", e2);
                return;
            }
        }
        setResult(0, intent);
        Logger.debug("No cancel intent set - will return to previous activity", new Object[0]);
    }

    private void extractState(Bundle bundle) {
        if (bundle == null) {
            Logger.warn("No stored state - unable to handle response", new Object[0]);
            finish();
            return;
        }
        this.mAuthIntent = (Intent) bundle.getParcelable(KEY_AUTH_INTENT);
        this.mAuthorizationStarted = bundle.getBoolean(KEY_AUTHORIZATION_STARTED, false);
        try {
            String string = bundle.getString(KEY_AUTH_REQUEST, null);
            this.mAuthRequest = string != null ? AuthorizationRequest.jsonDeserialize(string) : null;
            this.mCompleteIntent = (PendingIntent) bundle.getParcelable(KEY_COMPLETE_INTENT);
            this.mCancelIntent = (PendingIntent) bundle.getParcelable(KEY_CANCEL_INTENT);
        } catch (JSONException e2) {
            throw new IllegalStateException("Unable to deserialize authorization request", e2);
        }
    }

    private Intent extractResponseData(Uri uri) {
        if (uri.getQueryParameterNames().contains("error")) {
            return AuthorizationException.fromOAuthRedirect(uri).toIntent();
        }
        AuthorizationResponse build = new AuthorizationResponse.Builder(this.mAuthRequest).fromUri(uri).build();
        if ((this.mAuthRequest.state == null && build.state != null) || (this.mAuthRequest.state != null && !this.mAuthRequest.state.equals(build.state))) {
            Logger.warn("State returned in authorization response (%s) does not match state from request (%s) - discarding response", build.state, this.mAuthRequest.state);
            return AuthorizationException.AuthorizationRequestErrors.STATE_MISMATCH.toIntent();
        }
        return build.toIntent();
    }
}
