package com.bytedance.ttgame.module.rn.market;

import android.net.Uri;
import android.text.TextUtils;
import com.bytedance.react.framework.RNConfig;
import com.bytedance.react.framework.core.RNLogUtil;
import com.bytedance.ttgame.module.rn.api.IUnityNotificationListener;
import com.bytedance.ttgame.module.rn.api.model.UnityMessage;
import java.util.Map;
/* loaded from: classes6.dex */
public class UnityRegisterManager {
    public static final String ERROR_MESSAGE = "error_message";
    public static final String ICON_CLICK_ACTION = "show_icon";
    private static IUnityNotificationListener rnUnityNotificationListener;

    public static void addOnUnityRegisterUpdateListener(IUnityNotificationListener iUnityNotificationListener) {
        if (iUnityNotificationListener == null) {
            return;
        }
        rnUnityNotificationListener = iUnityNotificationListener;
    }

    public static void notifyUnityListener(UnityMessage unityMessage) {
        RNLogUtil.reportLog(RNLogUtil.RN_SEND_TO_UNITY, RNLogUtil.CATEGORY_BEGIN, unityMessage == null ? "" : "action:" + unityMessage.getAction() + "&params:" + unityMessage.getParams());
        IUnityNotificationListener iUnityNotificationListener = rnUnityNotificationListener;
        if (iUnityNotificationListener != null) {
            iUnityNotificationListener.OnUnityRegisterUpdateListener(unityMessage);
        } else {
            RNLogUtil.reportLog(RNLogUtil.RN_SEND_TO_UNITY, "error", unityMessage == null ? "" : "action:" + unityMessage.getAction() + "&params:" + unityMessage.getParams() + "&error:not have listener");
        }
        RNLogUtil.reportLog(RNLogUtil.RN_SEND_TO_UNITY, "end", unityMessage != null ? "action:" + unityMessage.getAction() + "&params:" + unityMessage.getParams() : "");
    }

    public static UnityMessage produceMessage(String str, Map map) {
        return produceMessage(str, map, "", "");
    }

    public static UnityMessage produceMessage(String str, Map map, String str2, String str3) {
        UnityMessage unityMessage = new UnityMessage();
        unityMessage.setAction(str);
        unityMessage.setParams(map);
        unityMessage.setMessage(str2);
        unityMessage.setMessageType(str3);
        return unityMessage;
    }

    public static String getRtcModule(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        Uri parse = Uri.parse(str);
        if (parse.isHierarchical()) {
            for (String str2 : parse.getQueryParameterNames()) {
                if (!TextUtils.isEmpty(str2) && RNConfig.RN_COMPONENT.equals(str2)) {
                    String queryParameter = parse.getQueryParameter(str2);
                    return queryParameter == null ? "" : queryParameter;
                }
            }
            return "";
        }
        return "";
    }
}
