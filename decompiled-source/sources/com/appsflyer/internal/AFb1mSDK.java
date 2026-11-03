package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import com.bytedance.common.utility.n;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class AFb1mSDK {
    public static String AFInAppEventType(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(n.SHA_256);
            messageDigest.update(str.getBytes(Charset.defaultCharset()));
            return valueOf(messageDigest.digest());
        } catch (Exception e2) {
            AFLogger.afErrorLog("Error turning data to SHA-256 string", e2);
            return null;
        }
    }

    private static String valueOf(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            sb.append(Integer.toString((b2 & 255) + 256, 16).substring(1));
        }
        return sb.toString();
    }

    public static byte[] valueOf(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(n.SHA_256);
            messageDigest.update(str.getBytes(Charset.defaultCharset()));
            return messageDigest.digest();
        } catch (Exception e2) {
            AFLogger.afErrorLog("Error turning string to SHA-256 byte array", e2);
            return null;
        }
    }

    public static String AFInAppEventParameterName(String str, String str2) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(str2.getBytes(Charset.defaultCharset()), "HmacSHA256"));
            return valueOf(mac.doFinal(str.getBytes(Charset.defaultCharset()))).toLowerCase(Locale.getDefault());
        } catch (InvalidKeyException | NoSuchAlgorithmException e2) {
            AFLogger.afErrorLog(e2.getMessage(), e2, true);
            return e2.getMessage();
        }
    }

    public static boolean AFInAppEventType(Map<String, Object> map, String[] strArr, AFd1rSDK aFd1rSDK) throws IllegalStateException {
        if (map == null || map.isEmpty()) {
            return false;
        }
        for (String str : strArr) {
            if (!map.containsKey(str)) {
                return false;
            }
        }
        String str2 = (String) map.remove("sig");
        if (str2 == null) {
            return false;
        }
        return AFInAppEventParameterName(new StringBuilder().append(new JSONObject(map)).append(AFd1rSDK.AFKeystoreWrapper()).toString(), AFb1lSDK.values(aFd1rSDK.AFKeystoreWrapper, aFd1rSDK.AFInAppEventParameterName)).equals(str2);
    }
}
