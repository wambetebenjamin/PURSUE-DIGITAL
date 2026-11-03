package com.bytedance.react.framework;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.react.framework.core.BRNLoaderManager;
import com.bytedance.react.framework.core.BRNLogger;
import com.bytedance.react.framework.model.BRNBundleInfo;
import com.bytedance.react.framework.model.RNResponse;
import com.bytedance.react.framework.utils.BundleInfoFormat;
import com.bytedance.react.framework.utils.CpuUtils;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class RNUtil {
    private static final String CONFIG_FILE_NAME = "config.json";
    private static final String TAG = "RNUtil";

    public static RNResponse assembleResponse(int i2, String str, Object obj) {
        RNResponse rNResponse = new RNResponse();
        rNResponse.code = i2;
        rNResponse.msg = str;
        rNResponse.data = obj;
        return rNResponse;
    }

    public static String getErrorRsp() {
        return getErrorRsp("");
    }

    public static String getSuccessRsp() {
        return getSuccessRsp("");
    }

    public static String getErrorRsp(String str) {
        return encodeToJson(assembleResponse(-1, str, new Object()));
    }

    public static String getSuccessRsp(String str) {
        return encodeToJson(assembleResponse(0, str, new Object()));
    }

    public static String getSuccessRsp(Object obj) {
        return encodeToJson(assembleResponse(0, "success", obj));
    }

    public static Bundle convertJsonToBundle(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            Bundle bundle = new Bundle();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!jSONObject.isNull(next)) {
                    bundle.putString(next, jSONObject.get(next).toString());
                }
            }
            return bundle;
        } catch (Exception unused) {
            return new Bundle();
        }
    }

    public static void putParamsToBundle(Bundle bundle, Map<String, ? extends Object> map) {
        Gson gson = new Gson();
        for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                if (entry.getValue() instanceof Integer) {
                    bundle.putInt(entry.getKey(), ((Integer) entry.getValue()).intValue());
                } else if (entry.getValue() instanceof Long) {
                    bundle.putLong(entry.getKey(), ((Long) entry.getValue()).longValue());
                } else if (entry.getValue() instanceof Float) {
                    bundle.putFloat(entry.getKey(), ((Float) entry.getValue()).floatValue());
                } else if (entry.getValue() instanceof Double) {
                    bundle.putDouble(entry.getKey(), ((Double) entry.getValue()).doubleValue());
                } else if (entry.getValue() instanceof String) {
                    bundle.putString(entry.getKey(), (String) entry.getValue());
                } else if (entry.getValue() instanceof Short) {
                    bundle.putShort(entry.getKey(), ((Short) entry.getValue()).shortValue());
                } else if (entry.getValue() instanceof Boolean) {
                    bundle.putBoolean(entry.getKey(), ((Boolean) entry.getValue()).booleanValue());
                } else if (entry.getValue() instanceof JSONObject) {
                    bundle.putString(entry.getKey(), ((JSONObject) entry.getValue()).toString());
                } else if (entry.getValue() instanceof JSONArray) {
                    bundle.putString(entry.getKey(), ((JSONArray) entry.getValue()).toString());
                } else {
                    bundle.putString(entry.getKey(), gson.toJson(entry.getValue()));
                }
            }
        }
    }

    public static String convertBundleToJson(Bundle bundle) {
        Set<String> keySet;
        if (bundle == null || (keySet = bundle.keySet()) == null || keySet.isEmpty()) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : keySet) {
            try {
                if (Build.VERSION.SDK_INT >= 19) {
                    jSONObject.put(str, JSONObject.wrap(bundle.get(str)));
                } else {
                    jSONObject.put(str, bundle.get(str));
                }
            } catch (JSONException unused) {
            }
        }
        return jSONObject.toString();
    }

    public static Map<String, String> decodeFromJson(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            HashMap hashMap = new HashMap();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!jSONObject.isNull(next)) {
                    hashMap.put(next, jSONObject.get(next).toString());
                }
            }
            return hashMap;
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean isSupportRN() {
        return Build.VERSION.SDK_INT >= 16 && CpuUtils.checkIfSupportReactNativeLib();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [android.content.res.AssetManager] */
    /* JADX WARN: Type inference failed for: r4v11, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v12, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r5v8 */
    public static void copyFileFromAssets(Context context, String str, String str2) {
        Throwable th;
        FileOutputStream fileOutputStream;
        IOException e2;
        if (context == null) {
            return;
        }
        BRNLogger.i(TAG, "start to copyFileFromAssets, from:" + ((String) str) + ", to:" + str2);
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        } else if (file.isFile()) {
            file.delete();
            file.mkdirs();
        }
        File file2 = new File(str2 + File.separator + ((String) str));
        if (file2.exists()) {
            deleteFolder(file);
            file.mkdirs();
        }
        ?? assets = context.getAssets();
        try {
            try {
                try {
                    assets = assets.open(str);
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    fileOutputStream = new FileOutputStream(file2);
                    try {
                        byte[] bArr = new byte[2048];
                        while (true) {
                            int read = assets.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        BRNLogger.i(TAG, "copyFileFromAssets, success");
                        if (assets != 0) {
                            assets.close();
                        }
                        fileOutputStream.close();
                    } catch (IOException e3) {
                        e2 = e3;
                        e2.printStackTrace();
                        file2.delete();
                        BRNLogger.e(TAG, "copyFileFromAssets IOException :" + e2.toString());
                        if (assets != 0) {
                            assets.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    }
                } catch (IOException e4) {
                    e2 = e4;
                    fileOutputStream = null;
                } catch (Throwable th3) {
                    th = th3;
                    str = 0;
                    if (assets != 0) {
                        try {
                            assets.close();
                        } catch (Exception e5) {
                            BRNLogger.e(TAG, e5.toString());
                            throw th;
                        }
                    }
                    if (str != 0) {
                        str.close();
                    }
                    throw th;
                }
            } catch (IOException e6) {
                fileOutputStream = null;
                e2 = e6;
                assets = 0;
            } catch (Throwable th4) {
                str = 0;
                th = th4;
                assets = 0;
            }
        } catch (Exception e7) {
            BRNLogger.e(TAG, e7.toString());
        }
    }

    public static void deleteFolder(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles == null || listFiles.length == 0) {
                    file.delete();
                    return;
                }
                for (File file2 : listFiles) {
                    deleteFolder(file2);
                }
                file.delete();
            }
        }
    }

    private static String getRnBundleZipFileName() {
        return RNConfig.getRnPath() + File.separator + RNConfig.RN_BUNDLE_ZIP_FILE_NAME;
    }

    public static boolean unZip(String str, String str2) {
        return unZip(str, str2, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0062 A[Catch: all -> 0x0032, TryCatch #3 {, blocks: (B:5:0x0004, B:6:0x002b, B:19:0x0057, B:21:0x0062, B:22:0x0065, B:24:0x006b, B:26:0x0077, B:28:0x007d, B:31:0x008a, B:33:0x00ac, B:35:0x00b2, B:36:0x00b5, B:37:0x00b9, B:39:0x00e3, B:40:0x00ea, B:42:0x00f0, B:52:0x010d, B:55:0x0113, B:57:0x0118, B:58:0x011e, B:73:0x0153, B:77:0x015b, B:79:0x0160, B:84:0x016b, B:88:0x0173, B:91:0x0180, B:90:0x0177, B:92:0x0181, B:14:0x0037), top: B:104:0x0004, inners: #2, #7, #11 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006b A[Catch: all -> 0x0032, TryCatch #3 {, blocks: (B:5:0x0004, B:6:0x002b, B:19:0x0057, B:21:0x0062, B:22:0x0065, B:24:0x006b, B:26:0x0077, B:28:0x007d, B:31:0x008a, B:33:0x00ac, B:35:0x00b2, B:36:0x00b5, B:37:0x00b9, B:39:0x00e3, B:40:0x00ea, B:42:0x00f0, B:52:0x010d, B:55:0x0113, B:57:0x0118, B:58:0x011e, B:73:0x0153, B:77:0x015b, B:79:0x0160, B:84:0x016b, B:88:0x0173, B:91:0x0180, B:90:0x0177, B:92:0x0181, B:14:0x0037), top: B:104:0x0004, inners: #2, #7, #11 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized boolean unZip(java.lang.String r10, java.lang.String r11, boolean r12) {
        /*
            Method dump skipped, instructions count: 399
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.react.framework.RNUtil.unZip(java.lang.String, java.lang.String, boolean):boolean");
    }

    public static HashMap<String, Object> getBundleInfo(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int i2 = 0;
        if (BRNLoaderManager.checkNativeBundleInfo(str)[0]) {
            i2 = 1;
        } else if (BRNLoaderManager.checkAssetBundleInfo(context, str)[0]) {
            i2 = 2;
        }
        HashMap<String, Object> parseBundleInfo = i2 != 0 ? BundleInfoFormat.parseBundleInfo(context, str, i2) : null;
        if (parseBundleInfo == null) {
            parseBundleInfo = new HashMap<>();
        }
        parseBundleInfo.put(RNConfig.FRORM_WHERE, Integer.valueOf(i2));
        return parseBundleInfo;
    }

    public static String getModuleVersion(Context context, String str) {
        boolean z;
        if (TextUtils.isEmpty(str)) {
            return "0.0";
        }
        if (BRNLoaderManager.checkNativeBundleInfo(str)[0]) {
            z = true;
        } else {
            z = BRNLoaderManager.checkAssetBundleInfo(context, str)[0] ? true : false;
        }
        if (z) {
            BRNBundleInfo bRNBundleInfo = null;
            try {
                bRNBundleInfo = BundleInfoFormat.getPluginVersion(context, str, z);
            } catch (Exception unused) {
            }
            return bRNBundleInfo != null ? bRNBundleInfo.version : "0.0";
        }
        return "0.0";
    }

    public static boolean jsFileExits(String str) {
        String[] list;
        String modulePath = RNConfig.getModulePath(str);
        if (TextUtils.isEmpty(modulePath)) {
            return false;
        }
        File file = new File(modulePath);
        return file.exists() && (list = file.list()) != null && list.length > 0;
    }

    public static String encodeToJson(Object obj) {
        try {
            return new Gson().toJson(obj);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r5v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v12, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9, types: [java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String parseConfigDouYinKey(android.content.Context r5) {
        /*
            r0 = 0
            android.content.res.AssetManager r5 = r5.getAssets()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5c
            java.lang.String r1 = "config.json"
            java.io.InputStream r5 = r5.open(r1)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5c
            int r1 = r5.available()     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L67
            byte[] r1 = new byte[r1]     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L67
            r5.read(r1)     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L67
            java.lang.String r2 = new java.lang.String     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L67
            java.lang.String r3 = "UTF-8"
            r2.<init>(r1, r3)     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L67
            com.google.gson.JsonParser r1 = new com.google.gson.JsonParser     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L67
            r1.<init>()     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L67
            com.google.gson.JsonElement r1 = r1.parse(r2)     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L67
            com.google.gson.JsonObject r1 = (com.google.gson.JsonObject) r1     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L67
            java.lang.String r2 = "channel"
            com.google.gson.JsonObject r1 = r1.getAsJsonObject(r2)     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L67
            com.bytedance.react.framework.RNUtil$1 r2 = new com.bytedance.react.framework.RNUtil$1     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L67
            r2.<init>()     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L67
            java.lang.reflect.Type r2 = r2.getType()     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L67
            com.google.gson.Gson r3 = new com.google.gson.Gson     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L67
            r3.<init>()     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L67
            java.lang.Object r1 = r3.fromJson(r1, r2)     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L67
            java.util.Map r1 = (java.util.Map) r1     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L67
            java.lang.String r2 = "aweme_platform_key"
            java.lang.Object r1 = r1.get(r2)     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L67
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L67
            if (r5 == 0) goto L66
            r5.close()     // Catch: java.io.IOException -> L50
            goto L66
        L50:
            r5 = move-exception
            r5.printStackTrace()
            goto L66
        L55:
            r1 = move-exception
            goto L5e
        L57:
            r5 = move-exception
            r4 = r0
            r0 = r5
            r5 = r4
            goto L68
        L5c:
            r1 = move-exception
            r5 = r0
        L5e:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L67
            if (r5 == 0) goto L66
            r5.close()     // Catch: java.io.IOException -> L50
        L66:
            return r0
        L67:
            r0 = move-exception
        L68:
            if (r5 == 0) goto L72
            r5.close()     // Catch: java.io.IOException -> L6e
            goto L72
        L6e:
            r5 = move-exception
            r5.printStackTrace()
        L72:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.react.framework.RNUtil.parseConfigDouYinKey(android.content.Context):java.lang.String");
    }

    public static Map<String, Object> jsonToMap(String str) {
        HashMap hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!jSONObject.isNull(next)) {
                    hashMap.put(next, jSONObject.get(next));
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return hashMap;
    }

    public static boolean convertBundleValueToBool(Bundle bundle, String str) {
        boolean z = false;
        if (bundle.containsKey(str)) {
            try {
                z = Boolean.parseBoolean(bundle.getString(str, "false"));
            } catch (Exception unused) {
            }
            bundle.putBoolean(str, z);
        }
        return z;
    }
}
