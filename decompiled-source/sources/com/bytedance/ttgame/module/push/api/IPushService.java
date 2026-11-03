package com.bytedance.ttgame.module.push.api;

import com.brentvatne.react.ReactVideoView;
import com.bytedance.bdturing.methods.l;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.module.push.PushService;
import java.util.Calendar;
import kotlin.Metadata;
/* compiled from: IPushService.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J<\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH&J<\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\t\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0011H&J<\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH&J<\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\t\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH&JD\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH&J<\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH&J\b\u0010\u0018\u001a\u00020\u0019H&J\u0010\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0019H&J\b\u0010\u001c\u001a\u00020\u0003H&J\u0010\u0010\u001d\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0005H&J\u0010\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0019H&¨\u0006 "}, d2 = {"Lcom/bytedance/ttgame/module/push/api/IPushService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "addLocalNotification", "", "title", "", "body", PushService.KEY_CALENDAR, "Ljava/util/Calendar;", ReactVideoView.EVENT_PROP_METADATA_IDENTIFIER, "pushInfo", "Lcom/bytedance/ttgame/module/push/api/PushInfo;", l.VALUE_CALLBACK, "Lcom/bytedance/ttgame/module/push/api/ILocalPushCallback;", "timeInterval", "", "addPushCallback", "Lcom/bytedance/ttgame/module/push/api/IPushCallback;", "addRepeatDayLocalNotification", "addRepeatLocalNotification", "addRepeatMonthLocalNotification", "type", "", "addRepeatWeekLocalNotification", "areNotificationEnabled", "", "enableNotification", "showDialog", "removeAllLocalNotification", "removeLocalNotification", "setUseDefaultPushCallback", "useDefault", "push_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface IPushService extends IModuleApi {
    void addLocalNotification(String str, String str2, long j2, String str3, PushInfo pushInfo, ILocalPushCallback iLocalPushCallback);

    void addLocalNotification(String str, String str2, Calendar calendar, String str3, PushInfo pushInfo, ILocalPushCallback iLocalPushCallback);

    void addPushCallback(IPushCallback iPushCallback);

    void addRepeatDayLocalNotification(String str, String str2, Calendar calendar, String str3, PushInfo pushInfo, ILocalPushCallback iLocalPushCallback);

    void addRepeatLocalNotification(String str, String str2, long j2, String str3, PushInfo pushInfo, ILocalPushCallback iLocalPushCallback);

    void addRepeatMonthLocalNotification(int i2, String str, String str2, Calendar calendar, String str3, PushInfo pushInfo, ILocalPushCallback iLocalPushCallback);

    void addRepeatWeekLocalNotification(String str, String str2, Calendar calendar, String str3, PushInfo pushInfo, ILocalPushCallback iLocalPushCallback);

    boolean areNotificationEnabled();

    void enableNotification(boolean z);

    void removeAllLocalNotification();

    void removeLocalNotification(String str);

    void setUseDefaultPushCallback(boolean z);
}
