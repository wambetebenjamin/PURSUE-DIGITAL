package com.bytedance.notification.style;

import android.app.Notification;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.bytedance.android.service.manager.push.notification.NotificationBody;
import com.bytedance.common.notification.R;
import com.bytedance.notification.interfaze.c;
import com.bytedance.push.utils.m;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
/* compiled from: BackGroundNotificationStyle.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static c f9903a = null;

    /* renamed from: b  reason: collision with root package name */
    private static final String f9904b = "PushNotification";

    /* renamed from: c  reason: collision with root package name */
    private Context f9905c;

    /* renamed from: d  reason: collision with root package name */
    private com.bytedance.notification.extra.b f9906d;

    /* renamed from: e  reason: collision with root package name */
    private NotificationBody f9907e;

    public a(Context context, com.bytedance.notification.extra.b bVar, NotificationBody notificationBody) {
        this.f9905c = context;
        this.f9906d = bVar;
        this.f9907e = notificationBody;
    }

    public Notification.Builder a(Notification.Builder builder) {
        com.bytedance.notification.extra.b bVar = this.f9906d;
        if (bVar != null && !bVar.f9876j && this.f9906d.mEnableNotificationHighLight && Build.VERSION.SDK_INT >= 23) {
            builder.setSmallIcon(Icon.createWithBitmap(this.f9907e.iconBitmap));
            RemoteViews remoteViews = null;
            Notification build = builder.build();
            if (build.contentView != null) {
                remoteViews = build.contentView;
            } else if (Build.VERSION.SDK_INT >= 24) {
                remoteViews = builder.createContentView();
            }
            if (remoteViews != null) {
                View apply = remoteViews.apply(this.f9905c.getApplicationContext(), new FrameLayout(this.f9905c.getApplicationContext()));
                if (m.getInstance().isHigherMi12()) {
                    remoteViews.setViewPadding(Resources.getSystem().getIdentifier("icon", "id", "android"), com.bytedance.notification.utils.b.dp2px(this.f9905c, 2.0f), 0, 0, 0);
                }
                if (m.getInstance().isMiOS() && !m.getInstance().isHigherMi12()) {
                    remoteViews = a(apply);
                    a(remoteViews, apply);
                } else {
                    if (this.f9906d.mResetAllTextToBlack) {
                        ArrayList<Integer> arrayList = new ArrayList();
                        a(apply, arrayList);
                        for (Integer num : arrayList) {
                            remoteViews.setTextColor(num.intValue(), ViewCompat.MEASURED_STATE_MASK);
                        }
                    }
                    a(remoteViews, apply);
                    if (this.f9906d.mProxyNotificationExtra != null && this.f9906d.mProxyNotificationExtra.mProxyType == 2) {
                        a(remoteViews, apply, this.f9906d.mProxyNotificationExtra.mTargetAppName);
                    }
                    RemoteViews remoteViews2 = new RemoteViews(this.f9905c.getPackageName(), R.layout.highlight_notification_image_back);
                    if (this.f9906d.f9870b != null) {
                        remoteViews2.setBitmap(R.id.notification_background_img, "setImageBitmap", this.f9906d.f9870b);
                    }
                    try {
                        Method method = RemoteViews.class.getMethod("addView", Integer.TYPE, RemoteViews.class, Integer.TYPE);
                        method.setAccessible(true);
                        method.invoke(remoteViews, Integer.valueOf(Resources.getSystem().getIdentifier("status_bar_latest_event_content", "id", "android")), remoteViews2, 0);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    remoteViews.reapply(this.f9905c.getApplicationContext(), apply);
                    c cVar = f9903a;
                    if (cVar != null) {
                        cVar.a(apply);
                    }
                }
                if (remoteViews != null && Build.VERSION.SDK_INT >= 24) {
                    builder.setCustomBigContentView(remoteViews);
                    builder.setCustomContentView(remoteViews);
                }
            }
        }
        return builder;
    }

    private void a(View view, List<Integer> list) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (childAt != null) {
                    a(childAt, list);
                }
            }
        } else if (view instanceof TextView) {
            list.add(Integer.valueOf(view.getId()));
        }
    }

    private void a(RemoteViews remoteViews, View view, String str) {
        int identifier = Resources.getSystem().getIdentifier("app_name_text", "id", "android");
        if (view.findViewById(identifier) instanceof TextView) {
            remoteViews.setTextViewText(identifier, str);
        }
    }

    private void a(RemoteViews remoteViews, View view) {
        int i2 = this.f9906d.mNotificationHeaderColor;
        if (i2 != 0) {
            a(remoteViews, "app_name_text", "id", "android", i2, view);
            a(remoteViews, "time_divider", "id", "android", i2, view);
            a(remoteViews, com.bytedance.sdk.account.save.database.a.FIELD_TIME, "id", "android", i2, view);
            if (com.ss.android.message.util.b.isTargetBrandDevice(com.ss.android.message.util.b.VIVO)) {
                if (this.f9906d.mEnableOnTop) {
                    a(remoteViews, "sub_time_divider", "id", "vivo", 0, view);
                    a(remoteViews, "sub_time", "id", "vivo", 0, view);
                } else {
                    a(remoteViews, "sub_time_divider", "id", "vivo", i2, view);
                    a(remoteViews, "sub_time", "id", "vivo", i2, view);
                }
            }
        }
        if (this.f9906d.mNotificationTitleColor != 0) {
            a(remoteViews, "title", "id", "android", this.f9906d.mNotificationTitleColor, view);
        }
        if (this.f9906d.mNotificationContentColor != 0) {
            a(remoteViews, "text", "id", "android", this.f9906d.mNotificationContentColor, view);
        }
    }

    private void a(RemoteViews remoteViews, String str, String str2, String str3, int i2, View view) {
        int identifier = Resources.getSystem().getIdentifier(str, str2, str3);
        if (view.findViewById(identifier) instanceof TextView) {
            remoteViews.setTextColor(identifier, i2);
        }
    }

    private RemoteViews a(View view) {
        if (view == null) {
            return null;
        }
        RemoteViews remoteViews = new RemoteViews(this.f9905c.getPackageName(), R.layout.notification_style_layout_xm);
        remoteViews.setBitmap(R.id.notification_background_img, "setImageBitmap", this.f9906d.f9870b);
        TextView textView = (TextView) view.findViewById(Resources.getSystem().getIdentifier("title", "id", "android"));
        remoteViews.setTextColor(R.id.push_notification_title, this.f9906d.mNotificationTitleColor == 0 ? textView.getCurrentTextColor() : this.f9906d.mNotificationTitleColor);
        remoteViews.setTextViewTextSize(R.id.push_notification_title, 0, textView.getTextSize());
        remoteViews.setTextViewText(R.id.push_notification_title, this.f9907e.title);
        TextView textView2 = (TextView) view.findViewById(Resources.getSystem().getIdentifier("text", "id", "android"));
        remoteViews.setTextColor(R.id.push_notification_content, this.f9906d.mNotificationContentColor == 0 ? textView2.getCurrentTextColor() : this.f9906d.mNotificationContentColor);
        remoteViews.setTextViewTextSize(R.id.push_notification_content, 0, textView2.getTextSize());
        remoteViews.setTextViewText(R.id.push_notification_content, this.f9907e.content);
        if (this.f9907e.imageType == 1) {
            this.f9907e.imageType = 2;
        }
        if (this.f9907e.imageBitmap != null && this.f9907e.imageType == 2) {
            if (m.getInstance().isMi12()) {
                remoteViews.setBitmap(R.id.push_notification_small_picture, "setImageBitmap", this.f9907e.imageBitmap);
                remoteViews.setViewVisibility(R.id.push_notification_small_picture, 0);
                int dp2px = com.bytedance.notification.utils.b.dp2px(this.f9905c, 24.0f);
                NotificationBody notificationBody = this.f9907e;
                notificationBody.iconBitmap = Bitmap.createScaledBitmap(notificationBody.iconBitmap, dp2px, dp2px, true);
                remoteViews.setBitmap(R.id.push_notification_small_icon, "setImageBitmap", this.f9907e.iconBitmap);
            } else {
                int dp2px2 = com.bytedance.notification.utils.b.dp2px(this.f9905c, 40.0f);
                NotificationBody notificationBody2 = this.f9907e;
                notificationBody2.imageBitmap = Bitmap.createScaledBitmap(notificationBody2.imageBitmap, dp2px2, dp2px2, true);
                remoteViews.setBitmap(R.id.push_notification_small_icon, "setImageBitmap", this.f9907e.imageBitmap);
                int dp2px3 = com.bytedance.notification.utils.b.dp2px(this.f9905c, 4.0f);
                remoteViews.setViewPadding(R.id.push_notification_small_icon, dp2px3, dp2px3, dp2px3, dp2px3);
                remoteViews.setViewVisibility(R.id.push_notification_small_picture, 8);
            }
        } else {
            if (m.getInstance().isMi12()) {
                int dp2px4 = com.bytedance.notification.utils.b.dp2px(this.f9905c, 24.0f);
                NotificationBody notificationBody3 = this.f9907e;
                notificationBody3.iconBitmap = Bitmap.createScaledBitmap(notificationBody3.iconBitmap, dp2px4, dp2px4, true);
            } else {
                int dp2px5 = com.bytedance.notification.utils.b.dp2px(this.f9905c, 36.0f);
                NotificationBody notificationBody4 = this.f9907e;
                notificationBody4.iconBitmap = Bitmap.createScaledBitmap(notificationBody4.iconBitmap, dp2px5, dp2px5, true);
                int dp2px6 = com.bytedance.notification.utils.b.dp2px(this.f9905c, 4.0f);
                remoteViews.setViewPadding(R.id.push_notification_small_icon, dp2px6, dp2px6, dp2px6, dp2px6);
            }
            remoteViews.setBitmap(R.id.push_notification_small_icon, "setImageBitmap", this.f9907e.iconBitmap);
            remoteViews.setViewVisibility(R.id.push_notification_small_picture, 8);
        }
        return remoteViews;
    }
}
