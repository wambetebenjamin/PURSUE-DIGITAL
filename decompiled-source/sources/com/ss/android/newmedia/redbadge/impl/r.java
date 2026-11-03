package com.ss.android.newmedia.redbadge.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.bytedance.librarian.c;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
/* compiled from: XiaomiHomeBadger.java */
/* loaded from: classes9.dex */
public class r implements com.ss.android.newmedia.redbadge.a {
    public static final String EXTRA_UPDATE_APP_COMPONENT_NAME = "android.intent.extra.update_application_component_name";
    public static final String EXTRA_UPDATE_APP_MSG_TEXT = "android.intent.extra.update_application_message_text";
    public static final String INTENT_ACTION = "android.intent.action.APPLICATION_MESSAGE_UPDATE";

    @Override // com.ss.android.newmedia.redbadge.a
    public void executeBadge(Context context, ComponentName componentName, int i2) {
        if (context == null || componentName == null) {
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        try {
            Object newInstance = Class.forName("android.app.MiuiNotification").newInstance();
            Field declaredField = newInstance.getClass().getDeclaredField("messageCount");
            declaredField.setAccessible(true);
            declaredField.set(newInstance, String.valueOf(i2 == 0 ? "" : Integer.valueOf(i2)));
        } catch (Throwable th) {
            Intent intent = new Intent(INTENT_ACTION);
            intent.putExtra(EXTRA_UPDATE_APP_COMPONENT_NAME, componentName.getPackageName() + c.a.SEPARATOR + componentName.getClassName());
            intent.putExtra(EXTRA_UPDATE_APP_MSG_TEXT, String.valueOf(i2 != 0 ? Integer.valueOf(i2) : ""));
            if (com.ss.android.newmedia.redbadge.util.a.canResolveBroadcast(context, intent)) {
                try {
                    context.sendBroadcast(intent);
                    return;
                } catch (Throwable th2) {
                    th2.printStackTrace();
                    throw new com.ss.android.newmedia.redbadge.d(th.getMessage());
                }
            }
            throw new com.ss.android.newmedia.redbadge.d("unable to resolve intent: " + intent.toString());
        }
    }

    @Override // com.ss.android.newmedia.redbadge.a
    public List<String> getSupportLaunchers() {
        return Arrays.asList("com.miui.miuilite", "com.miui.home", "com.miui.miuihome", "com.miui.miuihome2", "com.miui.mihome", "com.miui.mihome2", "com.i.miui.launcher");
    }
}
