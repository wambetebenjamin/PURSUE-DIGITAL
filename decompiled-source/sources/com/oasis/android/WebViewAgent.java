package com.oasis.android;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.MailTo;
import android.net.ParseException;
import android.net.Uri;
import com.oasis.Logger.Logger;
/* loaded from: classes9.dex */
public abstract class WebViewAgent {

    /* renamed from: a  reason: collision with root package name */
    private static WebViewAgent f17457a = null;

    /* renamed from: b  reason: collision with root package name */
    static final /* synthetic */ boolean f17458b = true;

    public static WebViewAgent createInstance(Context context) {
        if (f17458b || f17457a == null) {
            try {
                String string = context.getString(R.string.webview_agent_class);
                Logger.i("WebViewAgent", "Start to create the WebViewAgent: " + string);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("WebViewAgent", "Create the WebViewAgent: " + string);
                    f17457a = (WebViewAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("WebViewAgent", "Create webview agent failed.", e2);
            }
            if (f17457a == null) {
                Logger.i("WebViewAgent", "Create default WebViewAgent.");
                f17457a = new c();
            }
            return f17457a;
        }
        throw new AssertionError();
    }

    public static WebViewAgent getInstance() {
        if (f17458b || f17457a != null) {
            return f17457a;
        }
        throw new AssertionError();
    }

    public static void startActivityToPay(String str) {
        Logger.d("WebViewAgent", "startActivityToSendEmail() called with: url = [" + str + "]");
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            ActivityManager.getActivity().startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    public static void startActivityToSendEmail(String str) {
        Logger.d("WebViewAgent", "startActivityToPay() called with: url = [" + str + "]");
        try {
            MailTo parse = MailTo.parse(str);
            Logger.i("WebViewAgent", "Email to " + parse.getTo() + "\nEmail subject " + parse.getSubject() + "\nEmail body " + parse.getBody());
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("message/rfc822");
            intent.putExtra("android.intent.extra.EMAIL", new String[]{parse.getTo()});
            intent.putExtra("android.intent.extra.SUBJECT", parse.getSubject());
            intent.putExtra("android.intent.extra.TEXT", parse.getBody());
            ActivityManager.getActivity().startActivity(Intent.createChooser(intent, "Send Email"));
        } catch (ActivityNotFoundException | ParseException e2) {
            e2.printStackTrace();
        }
    }

    public abstract void open(String str, String str2);

    public abstract void openWithExtra(String str, String str2, String str3);

    public abstract void registerWebViewUrlListener(WebViewUrlListener webViewUrlListener);

    public abstract void unregisterWebViewUrlListener();
}
