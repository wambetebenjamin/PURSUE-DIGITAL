package com.bytedance.android.service.manager.push.notification;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import com.bytedance.android.service.manager.R;
import com.bytedance.common.support.b;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class NotificationBody {
    public static final int TYPE_IMAGE_BIG = 1;
    public static final int TYPE_IMAGE_NONE = 0;
    public static final int TYPE_IMAGE_SMALL = 2;
    public String androidGroup;
    public String appName;
    public String bdPushStr;
    public String channelId;
    public int channelImportance;
    public String content;
    public JSONObject eventExtra;
    public int groupFoldNum;
    public String groupId;
    public Bitmap iconBitmap;
    public long id;
    public Bitmap imageBitmap;
    public int imageType;
    public String imageUrl;
    public String notificationCategory;
    public String openUrl;
    public int redBadgeNum;
    public boolean showWhen;
    public Uri sound;
    public String title;
    public boolean useLED;
    public boolean useSound;
    public boolean useVibrator;

    /* loaded from: classes.dex */
    public @interface ImageType {
    }

    public NotificationBody(Builder builder) {
        this.id = builder.id;
        this.showWhen = builder.showWhen;
        this.title = builder.title;
        this.content = builder.content;
        this.groupId = builder.groupId;
        this.channelId = builder.channelId;
        this.redBadgeNum = builder.redBadgeNum;
        this.imageUrl = builder.imageUrl;
        this.imageBitmap = builder.imageBitmap;
        this.imageType = builder.imageType;
        this.useLED = builder.useLED;
        this.useSound = builder.useSound;
        this.useVibrator = builder.useVibrator;
        this.androidGroup = builder.androidGroup;
        this.groupFoldNum = builder.groupFoldNum;
        this.sound = builder.sound;
        this.bdPushStr = builder.bdPushStr;
        this.iconBitmap = builder.iconBitmap;
        this.appName = builder.appName;
        this.openUrl = builder.openUrl;
        this.eventExtra = builder.eventExtra;
        this.channelImportance = builder.channelImportance;
        this.notificationCategory = builder.notificationCategory;
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private String androidGroup;
        private String appName;
        private String bdPushStr;
        private String channelId;
        private int channelImportance;
        private String content;
        private JSONObject eventExtra;
        private int groupFoldNum;
        private String groupId;
        private Bitmap iconBitmap;
        private long id;
        private Bitmap imageBitmap;
        private int imageType;
        private String imageUrl;
        private String notificationCategory;
        private String openUrl;
        private int redBadgeNum;
        private boolean showWhen;
        private Uri sound;
        private String title;
        private boolean useLED;
        private boolean useSound;
        private boolean useVibrator;

        public Builder id(long j2) {
            this.id = j2;
            return this;
        }

        public Builder showWhen(boolean z) {
            this.showWhen = z;
            return this;
        }

        public Builder title(String str) {
            this.title = str;
            return this;
        }

        public Builder content(String str) {
            this.content = str;
            return this;
        }

        public Builder groupId(String str) {
            this.groupId = str;
            return this;
        }

        public Builder channelId(String str) {
            this.channelId = str;
            return this;
        }

        public Builder redBadgeNum(int i2) {
            this.redBadgeNum = i2;
            return this;
        }

        public Builder imageType(int i2) {
            this.imageType = i2;
            return this;
        }

        public Builder imageUrl(String str) {
            this.imageUrl = str;
            return this;
        }

        public Builder useLED(boolean z) {
            this.useLED = z;
            return this;
        }

        public Builder useSound(boolean z) {
            this.useSound = z;
            return this;
        }

        public Builder useVibrator(boolean z) {
            this.useVibrator = z;
            return this;
        }

        public Builder androidGroup(String str) {
            this.androidGroup = str;
            return this;
        }

        public Builder groupFoldNum(int i2) {
            this.groupFoldNum = i2;
            return this;
        }

        public Builder setImageBitmap(Bitmap bitmap) {
            this.imageBitmap = bitmap;
            return this;
        }

        public Builder setSound(Uri uri) {
            this.sound = uri;
            return this;
        }

        public Builder setBdPushStr(String str) {
            this.bdPushStr = str;
            return this;
        }

        public Builder setIconBitmap(Bitmap bitmap) {
            this.iconBitmap = bitmap;
            return this;
        }

        public Builder setAppName(String str) {
            this.appName = str;
            return this;
        }

        public Builder setOpenUrl(String str) {
            this.openUrl = str;
            return this;
        }

        public Builder setEventExtra(JSONObject jSONObject) {
            this.eventExtra = jSONObject;
            return this;
        }

        public Builder setChannelImportance(int i2) {
            this.channelImportance = i2;
            return this;
        }

        public Builder setNotificationCategory(String str) {
            this.notificationCategory = str;
            return this;
        }

        public NotificationBody build() {
            if (this.imageType != 0 && TextUtils.isEmpty(this.imageUrl) && this.imageBitmap == null) {
                this.imageType = 0;
            }
            if (this.iconBitmap == null) {
                this.iconBitmap = BitmapFactory.decodeResource(b.getInstance().getPushConfigurationService().getPushCommonConfiguration().mApplication.getResources(), R.drawable.status_icon);
            }
            return new NotificationBody(this);
        }
    }
}
