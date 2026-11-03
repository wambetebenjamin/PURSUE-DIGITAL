package com.bumptech.glide.request.target;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Preconditions;
import com.bytedance.notification.NotificationDeleteBroadcastReceiver;
/* loaded from: classes.dex */
public class NotificationTarget extends SimpleTarget<Bitmap> {
    private final Context context;
    private final Notification notification;
    private final int notificationId;
    private final String notificationTag;
    private final RemoteViews remoteViews;
    private final int viewId;

    @Override // com.bumptech.glide.request.target.Target
    public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
        onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
    }

    public NotificationTarget(Context context, int i2, RemoteViews remoteViews, Notification notification, int i3) {
        this(context, i2, remoteViews, notification, i3, null);
    }

    public NotificationTarget(Context context, int i2, RemoteViews remoteViews, Notification notification, int i3, String str) {
        this(context, Integer.MIN_VALUE, Integer.MIN_VALUE, i2, remoteViews, notification, i3, str);
    }

    public NotificationTarget(Context context, int i2, int i3, int i4, RemoteViews remoteViews, Notification notification, int i5, String str) {
        super(i2, i3);
        this.context = (Context) Preconditions.checkNotNull(context, "Context must not be null!");
        this.notification = (Notification) Preconditions.checkNotNull(notification, "Notification object can not be null!");
        this.remoteViews = (RemoteViews) Preconditions.checkNotNull(remoteViews, "RemoteViews object can not be null!");
        this.viewId = i4;
        this.notificationId = i5;
        this.notificationTag = str;
    }

    private void update() {
        ((NotificationManager) Preconditions.checkNotNull((NotificationManager) this.context.getSystemService(NotificationDeleteBroadcastReceiver.VALUE_MSG_TYPE_NOTIFICATION))).notify(this.notificationTag, this.notificationId, this.notification);
    }

    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
        this.remoteViews.setImageViewBitmap(this.viewId, bitmap);
        update();
    }
}
