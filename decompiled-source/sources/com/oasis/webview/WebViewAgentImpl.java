package com.oasis.webview;

import android.content.Intent;
import androidx.core.net.MailTo;
import com.just.agentweb.DefaultWebClient;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityManager;
import com.oasis.android.WebViewAgent;
import com.oasis.android.WebViewUrlListener;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class WebViewAgentImpl extends WebViewAgent {
    private static final String TAG = "WebViewAgentImpl";
    public static WebViewUrlListener listener;

    @Override // com.oasis.android.WebViewAgent
    public void open(String str, String str2) {
        Logger.d(TAG, "open() called with: title = [" + str + "], url = [" + str2 + "]");
        if (str2.startsWith(MailTo.MAILTO_SCHEME)) {
            startActivityToSendEmail(str2);
        } else if (str2.startsWith(DefaultWebClient.WEBCHAT_PAY_SCHEME) || str2.startsWith(DefaultWebClient.ALIPAYS_SCHEME)) {
            startActivityToPay(str2);
        } else {
            Intent intent = new Intent(ActivityManager.getActivity().getBaseContext(), WebViewActivity.class);
            intent.putExtra("url", str2);
            intent.putExtra("title", str);
            ActivityManager.getActivity().startActivity(intent);
        }
    }

    @Override // com.oasis.android.WebViewAgent
    public void openWithExtra(String str, String str2, String str3) {
        Logger.d(TAG, "open() called with: title = [" + str + "], url = [" + str2 + "]");
        if (str2.startsWith(MailTo.MAILTO_SCHEME)) {
            startActivityToSendEmail(str2);
        } else if (str2.startsWith(DefaultWebClient.WEBCHAT_PAY_SCHEME) || str2.startsWith(DefaultWebClient.ALIPAYS_SCHEME)) {
            startActivityToPay(str2);
        } else {
            Intent intent = new Intent(ActivityManager.getActivity().getBaseContext(), WebViewActivity.class);
            intent.putExtra("url", str2);
            intent.putExtra("title", str);
            try {
                JSONObject jSONObject = new JSONObject(str3);
                if (jSONObject.has("orientation")) {
                    intent.putExtra("orientation", jSONObject.getString("orientation"));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            ActivityManager.getActivity().startActivity(intent);
        }
    }

    @Override // com.oasis.android.WebViewAgent
    public void registerWebViewUrlListener(WebViewUrlListener webViewUrlListener) {
        listener = webViewUrlListener;
    }

    @Override // com.oasis.android.WebViewAgent
    public void unregisterWebViewUrlListener() {
        listener = null;
    }
}
