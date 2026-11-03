package com.bytedance.ttgame.module.push.bridge;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.channel.account.ErrorConstants;
import com.bytedance.ttgame.framework.gbridge.GBridgeContext;
import com.bytedance.ttgame.framework.gbridge.IApplicationProvider;
import com.bytedance.ttgame.framework.gbridge.annotation.GBridgeMethod;
import com.bytedance.ttgame.framework.gbridge.annotation.GBridgeParam;
import com.bytedance.ttgame.framework.gbridge.module.BaseModule;
import com.bytedance.ttgame.framework.gbridge.plugin.SdkLog;
import com.bytedance.ttgame.framework.gbridge.spi.ServiceManager;
import com.bytedance.ttgame.module.push.api.ILocalPushCallback;
import com.bytedance.ttgame.module.push.api.IPushService;
import com.bytedance.ttgame.module.push.api.PushInfo;
import com.facebook.react.uimanager.ViewProps;
import java.util.Calendar;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class PushModule implements BaseModule {
    private static final String KEY_BODY = "body";
    private static final String KEY_CODE = "code";
    private static final String KEY_DAY = "day";
    private static final String KEY_HOUR = "hour";
    private static final String KEY_IDENTIFIER = "identifier";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_MINUTE = "minute";
    private static final String KEY_MONTH = "month";
    private static final String KEY_SECOND = "second";
    private static final String KEY_TIME_INTERVAL = "timeInterval";
    private static final String KEY_TITLE = "title";
    private static final String KEY_USER_INFO = "userInfo";
    private static final String KEY_WEEK = "week";
    private static final String KEY_WEEKDAY = "weekday";
    private static final String KEY_YEAR = "year";
    private static final String TAG = "PushModule";
    private IApplicationProvider mGameApplication;
    private String mTunnel;

    public PushModule(String str, IApplicationProvider iApplicationProvider) {
        this.mTunnel = str;
        this.mGameApplication = iApplicationProvider;
    }

    @GBridgeMethod(callName = "localPushCountdownOnce")
    public void localPushCountdownOnce(@GBridgeParam("title") String str, @GBridgeParam("body") String str2, @GBridgeParam("timeInterval") long j2, @GBridgeParam("userInfo") JSONObject jSONObject, @GBridgeParam("identifier") String str3, @GBridgeParam final GBridgeContext gBridgeContext) {
        SdkLog.v(TAG, "localPushCountdownOnce");
        ((IPushService) ServiceManager.get().getService(IPushService.class)).addLocalNotification(str, str2, j2, str3, new PushInfo("", jSONObject == null ? "" : jSONObject.toString()), new ILocalPushCallback() { // from class: com.bytedance.ttgame.module.push.bridge.PushModule.1
            @Override // com.bytedance.ttgame.module.push.api.ILocalPushCallback
            public void onSuccess(String str4) {
                SdkLog.d(PushModule.TAG, "onSuccess, identifier: " + str4);
                JSONObject jSONObject2 = new JSONObject();
                BaseModule.add(jSONObject2, "identifier", str4);
                gBridgeContext.callBackResult(jSONObject2);
            }

            @Override // com.bytedance.ttgame.module.push.api.ILocalPushCallback
            public void onFail(GSDKError gSDKError) {
                SdkLog.d(PushModule.TAG, "onFail, error: " + gSDKError.toString());
                JSONObject jSONObject2 = new JSONObject();
                BaseModule.add(jSONObject2, "code", gSDKError.getCode());
                BaseModule.add(jSONObject2, "message", gSDKError.getMessage());
                BaseModule.add(jSONObject2, ErrorConstants.EXTRA_ERROR_CODE, gSDKError.getExtraErrorCode());
                BaseModule.add(jSONObject2, ErrorConstants.EXTRA_ERROR_MESSAGE, gSDKError.getExtraErrorMessage());
                BaseModule.add(jSONObject2, ErrorConstants.ADDITIONAL_INFO, gSDKError.getAdditionalInfo());
                gBridgeContext.callBackResult(jSONObject2);
            }
        });
    }

    @GBridgeMethod(callName = "localPushTimingOnce")
    public void localPushTimingOnce(@GBridgeParam("title") String str, @GBridgeParam("body") String str2, @GBridgeParam("year") int i2, @GBridgeParam("month") int i3, @GBridgeParam("day") int i4, @GBridgeParam("hour") int i5, @GBridgeParam("minute") int i6, @GBridgeParam("second") int i7, @GBridgeParam("userInfo") JSONObject jSONObject, @GBridgeParam("identifier") String str3, @GBridgeParam final GBridgeContext gBridgeContext) {
        SdkLog.v(TAG, "localPushTimingOnce");
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i2);
        calendar.set(2, i3 - 1);
        calendar.set(5, i4);
        calendar.set(11, i5);
        calendar.set(12, i6);
        calendar.set(13, i7);
        ((IPushService) ServiceManager.get().getService(IPushService.class)).addLocalNotification(str, str2, calendar, str3, new PushInfo("", jSONObject == null ? "" : jSONObject.toString()), new ILocalPushCallback() { // from class: com.bytedance.ttgame.module.push.bridge.PushModule.2
            @Override // com.bytedance.ttgame.module.push.api.ILocalPushCallback
            public void onSuccess(String str4) {
                SdkLog.d(PushModule.TAG, "onSuccess, identifier: " + str4);
                JSONObject jSONObject2 = new JSONObject();
                BaseModule.add(jSONObject2, "identifier", str4);
                gBridgeContext.callBackResult(jSONObject2);
            }

            @Override // com.bytedance.ttgame.module.push.api.ILocalPushCallback
            public void onFail(GSDKError gSDKError) {
                SdkLog.d(PushModule.TAG, "onFail, erroor: " + gSDKError);
                JSONObject jSONObject2 = new JSONObject();
                BaseModule.add(jSONObject2, "code", gSDKError.getCode());
                BaseModule.add(jSONObject2, "message", gSDKError.getMessage());
                BaseModule.add(jSONObject2, ErrorConstants.EXTRA_ERROR_CODE, gSDKError.getExtraErrorCode());
                BaseModule.add(jSONObject2, ErrorConstants.EXTRA_ERROR_MESSAGE, gSDKError.getExtraErrorMessage());
                BaseModule.add(jSONObject2, ErrorConstants.ADDITIONAL_INFO, gSDKError.getAdditionalInfo());
                gBridgeContext.callBackResult(jSONObject2);
            }
        });
    }

    @GBridgeMethod(callName = "localPushCountdownRepeat")
    public void localPushCountdownRepeat(@GBridgeParam("title") String str, @GBridgeParam("body") String str2, @GBridgeParam("timeInterval") long j2, @GBridgeParam("userInfo") JSONObject jSONObject, @GBridgeParam("identifier") String str3, @GBridgeParam final GBridgeContext gBridgeContext) {
        SdkLog.v(TAG, "localPushCountdownRepeat");
        ((IPushService) ServiceManager.get().getService(IPushService.class)).addRepeatLocalNotification(str, str2, j2, str3, new PushInfo("", jSONObject == null ? "" : jSONObject.toString()), new ILocalPushCallback() { // from class: com.bytedance.ttgame.module.push.bridge.PushModule.3
            @Override // com.bytedance.ttgame.module.push.api.ILocalPushCallback
            public void onSuccess(String str4) {
                SdkLog.d(PushModule.TAG, "onSuccess, identifier: " + str4);
                JSONObject jSONObject2 = new JSONObject();
                BaseModule.add(jSONObject2, "identifier", str4);
                gBridgeContext.callBackResult(jSONObject2);
            }

            @Override // com.bytedance.ttgame.module.push.api.ILocalPushCallback
            public void onFail(GSDKError gSDKError) {
                SdkLog.d(PushModule.TAG, "onFail, error: " + gSDKError);
                JSONObject jSONObject2 = new JSONObject();
                BaseModule.add(jSONObject2, "code", gSDKError.getCode());
                BaseModule.add(jSONObject2, "message", gSDKError.getMessage());
                BaseModule.add(jSONObject2, ErrorConstants.EXTRA_ERROR_CODE, gSDKError.getExtraErrorCode());
                BaseModule.add(jSONObject2, ErrorConstants.EXTRA_ERROR_MESSAGE, gSDKError.getExtraErrorMessage());
                BaseModule.add(jSONObject2, ErrorConstants.ADDITIONAL_INFO, gSDKError.getAdditionalInfo());
                gBridgeContext.callBackResult(jSONObject2);
            }
        });
    }

    @GBridgeMethod(callName = "localPushTimingRepeatDay")
    public void localPushTimingRepeatDay(@GBridgeParam("title") String str, @GBridgeParam("body") String str2, @GBridgeParam("hour") int i2, @GBridgeParam("minute") int i3, @GBridgeParam("second") int i4, @GBridgeParam("userInfo") JSONObject jSONObject, @GBridgeParam("identifier") String str3, @GBridgeParam final GBridgeContext gBridgeContext) {
        SdkLog.v(TAG, "localPushTimingRepeatDay");
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, i2);
        calendar.set(12, i3);
        calendar.set(13, i4);
        ((IPushService) ServiceManager.get().getService(IPushService.class)).addRepeatDayLocalNotification(str, str2, calendar, str3, new PushInfo("", jSONObject == null ? "" : jSONObject.toString()), new ILocalPushCallback() { // from class: com.bytedance.ttgame.module.push.bridge.PushModule.4
            @Override // com.bytedance.ttgame.module.push.api.ILocalPushCallback
            public void onSuccess(String str4) {
                SdkLog.d(PushModule.TAG, "onSuccess, identifier: " + str4);
                JSONObject jSONObject2 = new JSONObject();
                BaseModule.add(jSONObject2, "identifier", str4);
                gBridgeContext.callBackResult(jSONObject2);
            }

            @Override // com.bytedance.ttgame.module.push.api.ILocalPushCallback
            public void onFail(GSDKError gSDKError) {
                SdkLog.d(PushModule.TAG, "onFail, error: " + gSDKError);
                JSONObject jSONObject2 = new JSONObject();
                BaseModule.add(jSONObject2, "code", gSDKError.getCode());
                BaseModule.add(jSONObject2, "message", gSDKError.getMessage());
                BaseModule.add(jSONObject2, ErrorConstants.EXTRA_ERROR_CODE, gSDKError.getExtraErrorCode());
                BaseModule.add(jSONObject2, ErrorConstants.EXTRA_ERROR_MESSAGE, gSDKError.getExtraErrorMessage());
                BaseModule.add(jSONObject2, ErrorConstants.ADDITIONAL_INFO, gSDKError.getAdditionalInfo());
                gBridgeContext.callBackResult(jSONObject2);
            }
        });
    }

    @GBridgeMethod(callName = "localPushTimingRepeatWeek")
    public void localPushTimingRepeatWeek(@GBridgeParam("title") String str, @GBridgeParam("body") String str2, @GBridgeParam("weekday") int i2, @GBridgeParam("hour") int i3, @GBridgeParam("minute") int i4, @GBridgeParam("second") int i5, @GBridgeParam("userInfo") JSONObject jSONObject, @GBridgeParam("identifier") String str3, @GBridgeParam final GBridgeContext gBridgeContext) {
        SdkLog.v(TAG, "localPushTimingRepeatWeek");
        Calendar calendar = Calendar.getInstance();
        calendar.set(7, i2);
        calendar.set(11, i3);
        calendar.set(12, i4);
        calendar.set(13, i5);
        ((IPushService) ServiceManager.get().getService(IPushService.class)).addRepeatWeekLocalNotification(str, str2, calendar, str3, new PushInfo("", jSONObject == null ? "" : jSONObject.toString()), new ILocalPushCallback() { // from class: com.bytedance.ttgame.module.push.bridge.PushModule.5
            @Override // com.bytedance.ttgame.module.push.api.ILocalPushCallback
            public void onSuccess(String str4) {
                SdkLog.d(PushModule.TAG, "onSuccess, identifier: " + str4);
                JSONObject jSONObject2 = new JSONObject();
                BaseModule.add(jSONObject2, "identifier", str4);
                gBridgeContext.callBackResult(jSONObject2);
            }

            @Override // com.bytedance.ttgame.module.push.api.ILocalPushCallback
            public void onFail(GSDKError gSDKError) {
                SdkLog.d(PushModule.TAG, "onFail, error: " + gSDKError);
                JSONObject jSONObject2 = new JSONObject();
                BaseModule.add(jSONObject2, "code", gSDKError.getCode());
                BaseModule.add(jSONObject2, "message", gSDKError.getMessage());
                BaseModule.add(jSONObject2, ErrorConstants.EXTRA_ERROR_CODE, gSDKError.getExtraErrorCode());
                BaseModule.add(jSONObject2, ErrorConstants.EXTRA_ERROR_MESSAGE, gSDKError.getExtraErrorMessage());
                BaseModule.add(jSONObject2, ErrorConstants.ADDITIONAL_INFO, gSDKError.getAdditionalInfo());
                gBridgeContext.callBackResult(jSONObject2);
            }
        });
    }

    @GBridgeMethod(callName = "localPushTimingRepeatMonth")
    public void localPushTimingRepeatMonth(@GBridgeParam("title") String str, @GBridgeParam("body") String str2, @GBridgeParam("year") int i2, @GBridgeParam("month") int i3, @GBridgeParam("day") int i4, @GBridgeParam("week") int i5, @GBridgeParam("weekday") int i6, @GBridgeParam("hour") int i7, @GBridgeParam("minute") int i8, @GBridgeParam("second") int i9, @GBridgeParam("userInfo") JSONObject jSONObject, @GBridgeParam("identifier") String str3, @GBridgeParam final GBridgeContext gBridgeContext) {
        int i10;
        SdkLog.v(TAG, "localPushTimingRepeatMonth");
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        if (i2 != 0) {
            calendar.set(1, i2);
        }
        if (i3 != 0) {
            calendar.set(2, i3 - 1);
        }
        if (i5 == 0) {
            calendar.set(5, i4);
            i10 = 1;
        } else {
            calendar.set(4, i5);
            calendar.set(7, i6);
            i10 = 2;
        }
        calendar.set(11, i7);
        calendar.set(12, i8);
        calendar.set(13, i9);
        ((IPushService) ServiceManager.get().getService(IPushService.class)).addRepeatMonthLocalNotification(i10, str, str2, calendar, str3, new PushInfo("", jSONObject == null ? "" : jSONObject.toString()), new ILocalPushCallback() { // from class: com.bytedance.ttgame.module.push.bridge.PushModule.6
            @Override // com.bytedance.ttgame.module.push.api.ILocalPushCallback
            public void onSuccess(String str4) {
                SdkLog.d(PushModule.TAG, "onSuccess, identifier: " + str4);
                JSONObject jSONObject2 = new JSONObject();
                BaseModule.add(jSONObject2, "identifier", str4);
                gBridgeContext.callBackResult(jSONObject2);
            }

            @Override // com.bytedance.ttgame.module.push.api.ILocalPushCallback
            public void onFail(GSDKError gSDKError) {
                SdkLog.d(PushModule.TAG, "onFail, error: " + gSDKError);
                JSONObject jSONObject2 = new JSONObject();
                BaseModule.add(jSONObject2, "code", gSDKError.getCode());
                BaseModule.add(jSONObject2, "message", gSDKError.getMessage());
                BaseModule.add(jSONObject2, ErrorConstants.EXTRA_ERROR_CODE, gSDKError.getExtraErrorCode());
                BaseModule.add(jSONObject2, ErrorConstants.EXTRA_ERROR_MESSAGE, gSDKError.getExtraErrorMessage());
                BaseModule.add(jSONObject2, ErrorConstants.ADDITIONAL_INFO, gSDKError.getAdditionalInfo());
                gBridgeContext.callBackResult(jSONObject2);
            }
        });
    }

    @GBridgeMethod(callName = "localPushRemoveIdentifier")
    public void localPushRemoveIdentifier(@GBridgeParam("identifier") String str) {
        SdkLog.v(TAG, "localPushRemoveIdentifier");
        ((IPushService) ServiceManager.get().getService(IPushService.class)).removeLocalNotification(str);
    }

    @GBridgeMethod(callName = "localPushRemoveAllIdentifier")
    public void localPushRemoveAllIdentifier() {
        SdkLog.v(TAG, "localPushRemoveAllIdentifier");
        ((IPushService) ServiceManager.get().getService(IPushService.class)).removeAllLocalNotification();
    }

    @GBridgeMethod(callName = "areNotificationEnabled", sync = true)
    public boolean areNotificationEnabled() {
        SdkLog.v(TAG, "areNotificationEnabled");
        return ((IPushService) ServiceManager.get().getService(IPushService.class)).areNotificationEnabled();
    }

    @GBridgeMethod(callName = "areNotificationEnabledAsync")
    public void areNotificationEnabledAsync(@GBridgeParam GBridgeContext gBridgeContext) {
        SdkLog.v(TAG, "areNotificationEnabledAsync");
        JSONObject jSONObject = new JSONObject();
        BaseModule.add(jSONObject, ViewProps.ENABLED, Boolean.valueOf(((IPushService) ServiceManager.get().getService(IPushService.class)).areNotificationEnabled()));
        gBridgeContext.callBackResult(jSONObject);
    }

    @GBridgeMethod(callName = "enableNotification")
    public void enableNotification(@GBridgeParam("showDialog") boolean z) {
        SdkLog.v(TAG, "enableNotification");
        ((IPushService) ServiceManager.get().getService(IPushService.class)).enableNotification(z);
    }

    @Override // com.bytedance.ttgame.framework.gbridge.IBridgeModule
    public String getBridgeTunnel() {
        return this.mTunnel;
    }
}
