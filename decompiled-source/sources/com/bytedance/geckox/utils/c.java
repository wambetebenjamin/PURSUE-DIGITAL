package com.bytedance.geckox.utils;

import android.text.TextUtils;
import com.bytedance.geckox.model.CheckRequestBodyModel;
import com.bytedance.geckox.model.CheckRequestParamModel;
import com.bytedance.geckox.policy.loop.model.LoopRequestModel;
import com.bytedance.geckox.policy.v4.model.V4RequestModel;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/* compiled from: ChannelUtils.java */
/* loaded from: classes2.dex */
public class c {
    public static String a(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        Collections.sort(list, new Comparator<String>() { // from class: com.bytedance.geckox.utils.c.1
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(String str, String str2) {
                if (str == null && str2 == null) {
                    return 0;
                }
                if (str == null) {
                    return -1;
                }
                if (str2 == null) {
                    return 1;
                }
                return str.compareTo(str2);
            }
        });
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : list) {
            if (str != null) {
                stringBuffer.append(str);
            }
        }
        return stringBuffer.toString();
    }

    public static String b(List<CheckRequestBodyModel.TargetChannel> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        Collections.sort(list, new Comparator<CheckRequestBodyModel.TargetChannel>() { // from class: com.bytedance.geckox.utils.c.2
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(CheckRequestBodyModel.TargetChannel targetChannel, CheckRequestBodyModel.TargetChannel targetChannel2) {
                if (targetChannel == null && targetChannel2 == null) {
                    return 0;
                }
                if (targetChannel == null) {
                    return -1;
                }
                if (targetChannel2 == null) {
                    return 1;
                }
                return targetChannel.channelName.compareTo(targetChannel2.channelName);
            }
        });
        StringBuffer stringBuffer = new StringBuffer();
        for (CheckRequestBodyModel.TargetChannel targetChannel : list) {
            if (targetChannel != null && !TextUtils.isEmpty(targetChannel.channelName)) {
                stringBuffer.append(targetChannel.channelName);
            }
        }
        return stringBuffer.toString();
    }

    public static String a(String str, Map<String, List<CheckRequestBodyModel.TargetChannel>> map) {
        if (map == null || map.isEmpty()) {
            return str;
        }
        TreeMap treeMap = new TreeMap(new Comparator<String>() { // from class: com.bytedance.geckox.utils.c.3
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(String str2, String str3) {
                return str2.compareTo(str3);
            }
        });
        for (String str2 : map.keySet()) {
            treeMap.put(str2, str + "-" + b(map.get(str2)));
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (String str3 : treeMap.keySet()) {
            stringBuffer.append(str3).append("-").append((String) treeMap.get(str3));
        }
        return stringBuffer.toString();
    }

    public static String a(Map<String, CheckRequestParamModel> map) {
        TreeMap treeMap = new TreeMap(new Comparator<String>() { // from class: com.bytedance.geckox.utils.c.4
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(String str, String str2) {
                return str.compareTo(str2);
            }
        });
        for (String str : map.keySet()) {
            CheckRequestParamModel checkRequestParamModel = map.get(str);
            treeMap.put(str, checkRequestParamModel.getGroup() + "-" + b(checkRequestParamModel.getTargetChannels()));
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (String str2 : treeMap.keySet()) {
            stringBuffer.append(str2).append("-").append((String) treeMap.get(str2));
        }
        return stringBuffer.toString();
    }

    public static String b(Map<String, LoopRequestModel> map) {
        TreeMap treeMap = new TreeMap(new Comparator<String>() { // from class: com.bytedance.geckox.utils.c.5
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(String str, String str2) {
                return str.compareTo(str2);
            }
        });
        for (String str : map.keySet()) {
            LoopRequestModel loopRequestModel = map.get(str);
            treeMap.put(str, loopRequestModel.getDeployment().getGroupName() + "-" + b(loopRequestModel.getDeployment().getTargetChannels()));
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (String str2 : treeMap.keySet()) {
            stringBuffer.append(str2).append("-").append((String) treeMap.get(str2));
        }
        return stringBuffer.toString();
    }

    public static String c(Map<String, V4RequestModel> map) {
        TreeMap treeMap = new TreeMap(new Comparator<String>() { // from class: com.bytedance.geckox.utils.c.6
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(String str, String str2) {
                return str.compareTo(str2);
            }
        });
        for (String str : map.keySet()) {
            V4RequestModel v4RequestModel = map.get(str);
            treeMap.put(str, v4RequestModel.getDeployment().getGroupName() + "-" + b(v4RequestModel.getDeployment().getTargetChannels()));
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (String str2 : treeMap.keySet()) {
            stringBuffer.append(str2).append("-").append((String) treeMap.get(str2));
        }
        return stringBuffer.toString();
    }

    public static String a(Map<String, String> map, String str, String str2, String str3) {
        if (map == null) {
            return null;
        }
        String str4 = map.get(str + "-" + str3);
        if (TextUtils.isEmpty(str4) && !TextUtils.isEmpty(str2)) {
            str4 = map.get(str + "-" + str2);
        }
        return TextUtils.isEmpty(str4) ? map.get(str) : str4;
    }

    public static void a(int i2, com.bytedance.geckox.buffer.a aVar, String str, String str2) {
        if (i2 == 1) {
            r.a(new com.bytedance.geckox.buffer.stream.a(aVar), str, str2);
            aVar.persistence();
        } else if (i2 == 2) {
            aVar.persistence();
            r.a(aVar.swap(), str, str2);
        } else {
            aVar.persistence();
            r.b(aVar.swap(), str, str2);
        }
    }
}
