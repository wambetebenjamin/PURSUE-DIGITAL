package com.oasis.download;

import com.bytedance.hades.downloader.api.data.DownloadInfo;
import com.bytedance.hades.downloader.api.data.DownloadMultiInfo;
import com.bytedance.hades.tgrp.api.ResInfo;
import com.bytedance.ttgame.module.compliance.impl.realname.RealNameService;
import com.oasis.Logger.Logger;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class RocketDownloaderUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int ErrorCode_NoError = 0;
    private static final String Tag = "com.oasis.download.RocketDownloaderUtils";
    private static final Map<Integer, Integer> remapMap;

    static {
        HashMap hashMap = new HashMap();
        remapMap = hashMap;
        hashMap.put(0, 0);
        hashMap.put(1, 1);
        hashMap.put(2, 1);
        hashMap.put(3, 1);
        hashMap.put(4, 1);
        hashMap.put(-3, 2);
        hashMap.put(-1, 3);
        hashMap.put(-2, 4);
        hashMap.put(-4, 5);
        hashMap.put(5, 6);
    }

    public static int remapStatusCode(int i2) {
        Map<Integer, Integer> map = remapMap;
        return map.containsKey(Integer.valueOf(i2)) ? map.get(Integer.valueOf(i2)).intValue() : i2;
    }

    public static String getErrorMsg(DownloadMultiInfo downloadMultiInfo) {
        JSONObject jSONObject = new JSONObject();
        try {
            int errCode = downloadMultiInfo.getErrCode();
            String str = RealNameService.SUCCESS_MESSAGE;
            if (errCode != 0) {
                str = downloadMultiInfo.getErrMsg();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("code", errCode);
                jSONObject2.put("message", str);
                jSONObject.put("gsdkError", jSONObject2);
            }
            jSONObject.put(DBDefinition.TASK_ID, downloadMultiInfo.getId());
            jSONObject.put("status", remapStatusCode(downloadMultiInfo.getStatus()));
            jSONObject.put("internalStatus", downloadMultiInfo.getStatus());
            jSONObject.put("code", errCode);
            jSONObject.put("message", str);
            jSONObject.put("errorCode", errCode);
            jSONObject.put("msg", str);
        } catch (JSONException e2) {
            Logger.e(Tag, e2.toString());
        }
        return jSONObject.toString();
    }

    public static String getErrorMsg(DownloadInfo downloadInfo) {
        JSONObject jSONObject = new JSONObject();
        try {
            int errCode = downloadInfo.getErrCode();
            String str = RealNameService.SUCCESS_MESSAGE;
            if (errCode != 0) {
                str = downloadInfo.getErrMsg();
            }
            jSONObject.put(DBDefinition.TASK_ID, downloadInfo.getId());
            jSONObject.put("url", downloadInfo.getUrl());
            jSONObject.put("status", remapStatusCode(downloadInfo.getStatus()));
            jSONObject.put("internalStatus", downloadInfo.getStatus());
            jSONObject.put("code", errCode);
            jSONObject.put("message", str);
            jSONObject.put("errorCode", errCode);
            jSONObject.put("msg", str);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("code", errCode);
            jSONObject2.put("message", str);
            jSONObject.put("gsdkError", jSONObject2);
        } catch (JSONException e2) {
            Logger.e(Tag, e2.toString());
        }
        return jSONObject.toString();
    }

    public static String getErrorMsg(int i2, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", i2);
            jSONObject.put("message", str);
            jSONObject.put("errorCode", i2);
            jSONObject.put("msg", str);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("code", i2);
            jSONObject2.put("message", str);
            jSONObject.put("gsdkError", jSONObject2);
        } catch (JSONException e2) {
            Logger.e(Tag, e2.toString());
        }
        return jSONObject.toString();
    }

    public static JSONObject resInfoToJson(ResInfo resInfo) {
        JSONObject jSONObject = new JSONObject();
        if (resInfo == null) {
            return jSONObject;
        }
        try {
            jSONObject.put("absPath", resInfo.getAbsPath());
            jSONObject.put("resPath", resInfo.getResPath());
            jSONObject.put("md5", resInfo.getMd5());
            jSONObject.put("dataLength", resInfo.getDataLength());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static JSONArray resInfoArrayToJsonArray(ResInfo[] resInfoArr) {
        JSONArray jSONArray = new JSONArray();
        if (resInfoArr == null) {
            return jSONArray;
        }
        for (ResInfo resInfo : resInfoArr) {
            jSONArray.put(resInfoToJson(resInfo));
        }
        return jSONArray;
    }

    public static JSONArray resInfoArrayToResPathJsonArray(ResInfo[] resInfoArr) {
        JSONArray jSONArray = new JSONArray();
        if (resInfoArr == null) {
            return jSONArray;
        }
        for (ResInfo resInfo : resInfoArr) {
            jSONArray.put(resInfo.getResPath());
        }
        return jSONArray;
    }
}
