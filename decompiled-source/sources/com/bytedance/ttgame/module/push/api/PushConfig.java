package com.bytedance.ttgame.module.push.api;

import android.text.TextUtils;
import com.bytedance.bdturing.setting.g;
import com.bytedance.push.interfaze.ah;
import com.bytedance.sdk.account.save.database.a;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
/* compiled from: PushConfig.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0014\u0018\u00002\u00020\u0001:\u0001=B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010<\u001a\u00020\u0006H\u0016R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR$\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000bR$\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u000bR$\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR$\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\t\"\u0004\b\u001d\u0010\u000bR(\u0010\u001e\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\t\"\u0004\b \u0010\u000bR(\u0010!\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\t\"\u0004\b#\u0010\u000bR$\u0010$\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0018\"\u0004\b&\u0010\u001aR$\u0010'\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0018\"\u0004\b)\u0010\u001aR$\u0010+\u001a\u00020*2\u0006\u0010\u0005\u001a\u00020*@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R$\u00100\u001a\u00020*2\u0006\u0010\u0005\u001a\u00020*@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010-\"\u0004\b2\u0010/R$\u00103\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0018\"\u0004\b5\u0010\u001aR$\u00106\u001a\u00020*2\u0006\u0010\u0005\u001a\u00020*@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010-\"\u0004\b8\u0010/R$\u00109\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\t\"\u0004\b;\u0010\u000b¨\u0006>"}, d2 = {"Lcom/bytedance/ttgame/module/push/api/PushConfig;", "", "builder", "Lcom/bytedance/ttgame/module/push/api/PushConfig$Builder;", "(Lcom/bytedance/ttgame/module/push/api/PushConfig$Builder;)V", "value", "", "appDomesticHost", "getAppDomesticHost", "()Ljava/lang/String;", "setAppDomesticHost", "(Ljava/lang/String;)V", "appLogChannel", "getAppLogChannel", "setAppLogChannel", "appName", "getAppName", "setAppName", "appOverseaHost", "getAppOverseaHost", "setAppOverseaHost", "", "autoRequestPermission", "getAutoRequestPermission", "()Z", "setAutoRequestPermission", "(Z)V", "blackList", "getBlackList", "setBlackList", "channelId", "getChannelId", "setChannelId", "channelName", "getChannelName", "setChannelName", "disablePushSubprocess", "getDisablePushSubprocess", "setDisablePushSubprocess", "enableForegroundPush", "getEnableForegroundPush", "setEnableForegroundPush", "", "fakeAppId", "getFakeAppId", "()I", "setFakeAppId", "(I)V", "pushAid", "getPushAid", "setPushAid", "redBadgeShow", "getRedBadgeShow", "setRedBadgeShow", "redMaxShowTime", "getRedMaxShowTime", "setRedMaxShowTime", "redStrategy", "getRedStrategy", "setRedStrategy", "toString", "Builder", "push_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class PushConfig {
    private String appDomesticHost;
    private String appLogChannel;
    private String appName;
    private String appOverseaHost;
    private boolean autoRequestPermission;
    private String blackList;
    private String channelId;
    private String channelName;
    private boolean disablePushSubprocess;
    private boolean enableForegroundPush;
    private int fakeAppId;
    private int pushAid;
    private boolean redBadgeShow;
    private int redMaxShowTime;
    private String redStrategy;

    public /* synthetic */ PushConfig(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    private PushConfig(Builder builder) {
        this.appLogChannel = "";
        this.blackList = "";
        this.appName = "";
        this.appOverseaHost = "";
        this.appDomesticHost = "";
        this.redStrategy = "";
        this.autoRequestPermission = true;
        this.enableForegroundPush = true;
        setPushAid(builder.getPushAid());
        setAppLogChannel(builder.getApplogChannel());
        setChannelId(builder.getChannelId());
        setChannelName(builder.getChannelName());
        setBlackList(String.valueOf(builder.getBlackList()));
        setAppName(builder.getPushAppName());
        setAppOverseaHost(builder.getAppOverseaHost());
        setAppDomesticHost(builder.getAppDomesticHost());
        setRedBadgeShow(builder.getRedBadgeShow());
        setRedMaxShowTime(builder.getRedMaxShowTime());
        setRedStrategy(builder.getRedStrategy());
        setFakeAppId(builder.getFakeAppId());
        setAutoRequestPermission(builder.getAutoRequestPermission());
        setEnableForegroundPush(builder.getEnableForegroundPush());
        setDisablePushSubprocess(builder.getDisablePushSubprocess());
    }

    public final String getAppLogChannel() {
        return this.appLogChannel;
    }

    private final void setAppLogChannel(String str) {
        this.appLogChannel = str;
    }

    public final int getPushAid() {
        return this.pushAid;
    }

    private final void setPushAid(int i2) {
        this.pushAid = i2;
    }

    public final String getChannelId() {
        return this.channelId;
    }

    private final void setChannelId(String str) {
        this.channelId = str;
    }

    public final String getChannelName() {
        return this.channelName;
    }

    private final void setChannelName(String str) {
        this.channelName = str;
    }

    public final String getBlackList() {
        return this.blackList;
    }

    private final void setBlackList(String str) {
        this.blackList = str;
    }

    public final String getAppName() {
        return this.appName;
    }

    private final void setAppName(String str) {
        this.appName = str;
    }

    public final String getAppOverseaHost() {
        return this.appOverseaHost;
    }

    private final void setAppOverseaHost(String str) {
        this.appOverseaHost = str;
    }

    public final String getAppDomesticHost() {
        return this.appDomesticHost;
    }

    private final void setAppDomesticHost(String str) {
        this.appDomesticHost = str;
    }

    public final boolean getRedBadgeShow() {
        return this.redBadgeShow;
    }

    private final void setRedBadgeShow(boolean z) {
        this.redBadgeShow = z;
    }

    public final int getRedMaxShowTime() {
        return this.redMaxShowTime;
    }

    private final void setRedMaxShowTime(int i2) {
        this.redMaxShowTime = i2;
    }

    public final String getRedStrategy() {
        return this.redStrategy;
    }

    private final void setRedStrategy(String str) {
        this.redStrategy = str;
    }

    public final int getFakeAppId() {
        return this.fakeAppId;
    }

    private final void setFakeAppId(int i2) {
        this.fakeAppId = i2;
    }

    public final boolean getAutoRequestPermission() {
        return this.autoRequestPermission;
    }

    private final void setAutoRequestPermission(boolean z) {
        this.autoRequestPermission = z;
    }

    public final boolean getEnableForegroundPush() {
        return this.enableForegroundPush;
    }

    private final void setEnableForegroundPush(boolean z) {
        this.enableForegroundPush = z;
    }

    public final boolean getDisablePushSubprocess() {
        return this.disablePushSubprocess;
    }

    private final void setDisablePushSubprocess(boolean z) {
        this.disablePushSubprocess = z;
    }

    /* compiled from: PushConfig.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010;\u001a\u00020\u0003J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010<\u001a\u00020\u0010J\u0010\u0010=\u001a\u00020\u00002\b\u0010>\u001a\u0004\u0018\u00010\u0003J\u0006\u0010?\u001a\u00020@J\u000e\u0010\"\u001a\u00020\u00002\u0006\u0010<\u001a\u00020\u0010J\u000e\u0010%\u001a\u00020\u00002\u0006\u0010<\u001a\u00020\u0010J\u000e\u0010)\u001a\u00020\u00002\u0006\u0010)\u001a\u00020(J\u000e\u0010.\u001a\u00020\u00002\u0006\u0010A\u001a\u00020(J\u0010\u0010B\u001a\u00020\u00002\b\u0010C\u001a\u0004\u0018\u00010\u0003J\u0010\u0010D\u001a\u00020\u00002\b\u0010E\u001a\u0004\u0018\u00010\u0003J\u0010\u0010\t\u001a\u00020\u00002\b\u0010F\u001a\u0004\u0018\u00010\u0003J\u0010\u0010\f\u001a\u00020\u00002\b\u0010F\u001a\u0004\u0018\u00010\u0003J\u0010\u0010G\u001a\u00020\u00002\b\u0010H\u001a\u0004\u0018\u00010\u0003J\u000e\u0010I\u001a\u00020\u00002\u0006\u0010J\u001a\u00020\u0010J\u000e\u0010K\u001a\u00020\u00002\u0006\u0010L\u001a\u00020(R$\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0004R$\u0010\n\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\u0004R$\u0010\r\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\b\"\u0004\b\u000f\u0010\u0004R$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u0010@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R(\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\u0005\u001a\u0004\u0018\u00010\u0016@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR(\u0010\u001c\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\b\"\u0004\b\u001e\u0010\u0004R(\u0010\u001f\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\b\"\u0004\b!\u0010\u0004R$\u0010\"\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u0010@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0013\"\u0004\b$\u0010\u0015R$\u0010%\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u0010@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0013\"\u0004\b'\u0010\u0015R$\u0010)\u001a\u00020(2\u0006\u0010\u0005\u001a\u00020(@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R$\u0010.\u001a\u00020(2\u0006\u0010\u0005\u001a\u00020(@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010+\"\u0004\b0\u0010-R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\bR$\u00102\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u0010@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u0013\"\u0004\b4\u0010\u0015R$\u00105\u001a\u00020(2\u0006\u0010\u0005\u001a\u00020(@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010+\"\u0004\b7\u0010-R$\u00108\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\b\"\u0004\b:\u0010\u0004¨\u0006M"}, d2 = {"Lcom/bytedance/ttgame/module/push/api/PushConfig$Builder;", "", "pushAppName", "", "(Ljava/lang/String;)V", "value", "appDomesticHost", "getAppDomesticHost", "()Ljava/lang/String;", "setAppDomesticHost", "appOverseaHost", "getAppOverseaHost", "setAppOverseaHost", "applogChannel", "getApplogChannel", "setApplogChannel", "", "autoRequestPermission", "getAutoRequestPermission", "()Z", "setAutoRequestPermission", "(Z)V", "Lorg/json/JSONArray;", "blackList", "getBlackList", "()Lorg/json/JSONArray;", "setBlackList", "(Lorg/json/JSONArray;)V", "channelId", "getChannelId", "setChannelId", "channelName", "getChannelName", "setChannelName", "disablePushSubprocess", "getDisablePushSubprocess", "setDisablePushSubprocess", "enableForegroundPush", "getEnableForegroundPush", "setEnableForegroundPush", "", "fakeAppId", "getFakeAppId", "()I", "setFakeAppId", "(I)V", "pushAid", "getPushAid", "setPushAid", "getPushAppName", "redBadgeShow", "getRedBadgeShow", "setRedBadgeShow", "redMaxShowTime", "getRedMaxShowTime", "setRedMaxShowTime", "redStrategy", "getRedStrategy", "setRedStrategy", "channel", "auto", "awakenBlackList", "list", "build", "Lcom/bytedance/ttgame/module/push/api/PushConfig;", "aid", "pushChannelId", "id", "pushChannelName", "name", g.HOST_KEY, "setRedBadgeStrategy", "strategy", "showRedBadge", ah.f11396a, "showRedTime", a.FIELD_TIME, "push_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final class Builder {
        private String appDomesticHost;
        private String appOverseaHost;
        private String applogChannel;
        private boolean autoRequestPermission;
        private JSONArray blackList;
        private String channelId;
        private String channelName;
        private boolean disablePushSubprocess;
        private boolean enableForegroundPush;
        private int fakeAppId;
        private int pushAid;
        private final String pushAppName;
        private boolean redBadgeShow;
        private int redMaxShowTime;
        private String redStrategy;

        public Builder(String pushAppName) {
            Intrinsics.checkNotNullParameter(pushAppName, "pushAppName");
            this.pushAppName = pushAppName;
            this.applogChannel = "";
            this.blackList = new JSONArray();
            this.appOverseaHost = "";
            this.appDomesticHost = "";
            this.redStrategy = "";
            this.autoRequestPermission = true;
            this.enableForegroundPush = true;
        }

        public final String getPushAppName() {
            return this.pushAppName;
        }

        public final String getApplogChannel() {
            return this.applogChannel;
        }

        private final void setApplogChannel(String str) {
            this.applogChannel = str;
        }

        public final int getPushAid() {
            return this.pushAid;
        }

        private final void setPushAid(int i2) {
            this.pushAid = i2;
        }

        public final String getChannelId() {
            return this.channelId;
        }

        private final void setChannelId(String str) {
            this.channelId = str;
        }

        public final String getChannelName() {
            return this.channelName;
        }

        private final void setChannelName(String str) {
            this.channelName = str;
        }

        public final JSONArray getBlackList() {
            return this.blackList;
        }

        private final void setBlackList(JSONArray jSONArray) {
            this.blackList = jSONArray;
        }

        public final String getAppOverseaHost() {
            return this.appOverseaHost;
        }

        private final void setAppOverseaHost(String str) {
            this.appOverseaHost = str;
        }

        public final String getAppDomesticHost() {
            return this.appDomesticHost;
        }

        private final void setAppDomesticHost(String str) {
            this.appDomesticHost = str;
        }

        public final boolean getRedBadgeShow() {
            return this.redBadgeShow;
        }

        private final void setRedBadgeShow(boolean z) {
            this.redBadgeShow = z;
        }

        public final int getRedMaxShowTime() {
            return this.redMaxShowTime;
        }

        private final void setRedMaxShowTime(int i2) {
            this.redMaxShowTime = i2;
        }

        public final String getRedStrategy() {
            return this.redStrategy;
        }

        private final void setRedStrategy(String str) {
            this.redStrategy = str;
        }

        public final int getFakeAppId() {
            return this.fakeAppId;
        }

        private final void setFakeAppId(int i2) {
            this.fakeAppId = i2;
        }

        public final boolean getAutoRequestPermission() {
            return this.autoRequestPermission;
        }

        private final void setAutoRequestPermission(boolean z) {
            this.autoRequestPermission = z;
        }

        public final boolean getEnableForegroundPush() {
            return this.enableForegroundPush;
        }

        private final void setEnableForegroundPush(boolean z) {
            this.enableForegroundPush = z;
        }

        public final boolean getDisablePushSubprocess() {
            return this.disablePushSubprocess;
        }

        private final void setDisablePushSubprocess(boolean z) {
            this.disablePushSubprocess = z;
        }

        public final Builder applogChannel(String channel) {
            Intrinsics.checkNotNullParameter(channel, "channel");
            setApplogChannel(channel);
            return this;
        }

        public final Builder pushAid(int i2) {
            setPushAid(i2);
            return this;
        }

        public final Builder pushChannelName(String str) {
            setChannelName(str);
            return this;
        }

        public final Builder pushChannelId(String str) {
            setChannelId(str);
            return this;
        }

        public final Builder awakenBlackList(String str) {
            try {
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (TextUtils.isEmpty(str)) {
                return this;
            }
            setBlackList(new JSONArray(str));
            return this;
        }

        /* renamed from: setAppOverseaHost  reason: collision with other method in class */
        public final Builder m218setAppOverseaHost(String str) {
            if (str == null) {
                str = "";
            }
            setAppOverseaHost(str);
            return this;
        }

        /* renamed from: setAppDomesticHost  reason: collision with other method in class */
        public final Builder m217setAppDomesticHost(String str) {
            if (str == null) {
                str = "";
            }
            setAppDomesticHost(str);
            return this;
        }

        public final Builder showRedBadge(boolean z) {
            setRedBadgeShow(z);
            return this;
        }

        public final Builder showRedTime(int i2) {
            setRedMaxShowTime(i2);
            return this;
        }

        public final Builder setRedBadgeStrategy(String str) {
            if (str == null) {
                str = "";
            }
            setRedStrategy(str);
            return this;
        }

        public final Builder fakeAppId(int i2) {
            setFakeAppId(i2);
            return this;
        }

        public final Builder autoRequestPermission(boolean z) {
            setAutoRequestPermission(z);
            return this;
        }

        public final Builder enableForegroundPush(boolean z) {
            setEnableForegroundPush(z);
            return this;
        }

        public final Builder disablePushSubprocess(boolean z) {
            setDisablePushSubprocess(z);
            return this;
        }

        public final PushConfig build() {
            return new PushConfig(this, null);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PushConfig(appLogChannel='").append(this.appLogChannel).append("', pushAid=").append(this.pushAid).append(", channelId=").append(this.channelId).append(", channelName=").append(this.channelName).append(", blackList='").append(this.blackList).append("', appName='").append(this.appName).append("', appOverseaHost='").append(this.appOverseaHost).append("',appDomesticHost='").append(this.appDomesticHost).append("', redBadgeShow=").append(this.redBadgeShow).append(", redMaxShowTime=").append(this.redMaxShowTime).append(", redStrategy='").append(this.redStrategy).append("', fakeAppId='");
        sb.append(this.fakeAppId).append("', autoRequestPermission='").append(this.autoRequestPermission).append("', enableForegroundPush='").append(this.enableForegroundPush).append("', disablePushSubprocess='").append(this.disablePushSubprocess).append("')");
        return sb.toString();
    }
}
