package com.bytedance.common.utility;

import android.text.TextUtils;
import android.util.Base64;
import com.facebook.internal.security.CertificateUtil;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.Character;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.json.JSONObject;
/* compiled from: StringUtils.java */
/* loaded from: classes2.dex */
public final class o {
    public static final String EMPTY = "";

    /* renamed from: a  reason: collision with root package name */
    private static final int f5309a = 255;

    /* renamed from: b  reason: collision with root package name */
    private static final int f5310b = 31;

    /* renamed from: c  reason: collision with root package name */
    private static final int f5311c = 32;

    /* renamed from: d  reason: collision with root package name */
    private static final int f5312d = 127;

    /* renamed from: e  reason: collision with root package name */
    private static final int f5313e = 12288;

    /* renamed from: f  reason: collision with root package name */
    private static final int f5314f = 65248;

    /* renamed from: g  reason: collision with root package name */
    private static final int f5315g = 65280;

    /* renamed from: h  reason: collision with root package name */
    private static final int f5316h = 65375;

    /* renamed from: i  reason: collision with root package name */
    private static final long f5317i = 1024;

    /* renamed from: j  reason: collision with root package name */
    private static final long f5318j = 1048576;
    private static final long k = 1073741824;
    private static final long l = 1099511627776L;
    private static final int m = 5;

    private o() {
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean equal(String str, String str2) {
        return (isEmpty(str) && isEmpty(str2)) || (str != null && str.equals(str2));
    }

    public static String ellipsize(String str, int i2) {
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i3 >= str.length()) {
                break;
            }
            char charAt = str.charAt(i3);
            i4 = (charAt <= 0 || charAt > 255) ? i4 + 2 : i4 + 1;
            if (i4 > i2) {
                sb.append("...");
                break;
            }
            sb.append(charAt);
            i3++;
        }
        return sb.toString();
    }

    public static void escapeEcmaScript(StringBuilder sb, String str, boolean z) {
        if (z) {
            sb.append("\"");
        }
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt == '\f') {
                sb.append("\\f");
            } else if (charAt == '\r') {
                sb.append("\\r");
            } else if (charAt == '\"' || charAt == '\\') {
                sb.append('\\').append(charAt);
            } else if (charAt == 8232 || charAt == 8233) {
                sb.append(String.format("\\u%04x", Integer.valueOf(charAt)));
            } else {
                switch (charAt) {
                    case '\b':
                        sb.append("\\b");
                        continue;
                    case '\t':
                        sb.append("\\t");
                        continue;
                    case '\n':
                        sb.append("\\n");
                        continue;
                    default:
                        if (charAt > 31) {
                            sb.append(charAt);
                            break;
                        } else {
                            sb.append(String.format("\\u%04x", Integer.valueOf(charAt)));
                            continue;
                        }
                }
            }
        }
        if (z) {
            sb.append("\"");
        }
    }

    public static String removeBlank(String str) {
        if (isEmpty(str)) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) >= '0' && str.charAt(i2) <= '9') {
                stringBuffer.append(str.charAt(i2));
            }
        }
        return stringBuffer.toString();
    }

    public static HashMap<String, String> parseResponse(String str) {
        String[] split = str.substring(str.indexOf(123) + 1, str.lastIndexOf(125)).split(",");
        for (int i2 = 0; i2 < split.length; i2++) {
            split[i2] = split[i2].trim();
        }
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str2 : split) {
            String[] split2 = str2.split(CertificateUtil.DELIMITER, 2);
            hashMap.put(clean(split2[0]), clean(split2[1]));
        }
        return hashMap;
    }

    public static String clean(String str) {
        int indexOf = str.indexOf(34);
        int lastIndexOf = str.lastIndexOf(34);
        return (indexOf < 0 || lastIndexOf <= indexOf) ? str : str.substring(indexOf + 1, lastIndexOf).trim();
    }

    public static String getShortDateTime(String str) {
        int indexOf = str.indexOf(45) + 1;
        int lastIndexOf = str.lastIndexOf(58);
        return (indexOf <= 0 || lastIndexOf <= 0) ? str : str.substring(indexOf, lastIndexOf);
    }

    public static String toSBC(String str) {
        char[] charArray = str.toCharArray();
        for (int i2 = 0; i2 < charArray.length; i2++) {
            if (charArray[i2] == ' ') {
                charArray[i2] = 12288;
            } else if (charArray[i2] < 127 && charArray[i2] > ' ') {
                charArray[i2] = (char) (charArray[i2] + f5314f);
            }
        }
        return new String(charArray);
    }

    public static String toDBC(String str) {
        char[] charArray = str.toCharArray();
        for (int i2 = 0; i2 < charArray.length; i2++) {
            if (isChinese(charArray[i2])) {
                if (charArray[i2] == f5313e) {
                    charArray[i2] = ' ';
                } else if (charArray[i2] > 65280 && charArray[i2] < f5316h) {
                    charArray[i2] = (char) (charArray[i2] - f5314f);
                }
            }
        }
        return new String(charArray);
    }

    public static boolean isChinese(char c2) {
        Character.UnicodeBlock of = Character.UnicodeBlock.of(c2);
        return of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || of == Character.UnicodeBlock.GENERAL_PUNCTUATION || of == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || of == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    public static String handleAdClickTrackUrl(String str) {
        if (isEmpty(str)) {
            return str;
        }
        try {
            return str.replace("[ss_random]", String.valueOf(new Random().nextLong())).replace("[ss_timestamp]", String.valueOf(System.currentTimeMillis()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return str;
        }
    }

    private static String a(long j2, long j3, String str) {
        double d2 = j2;
        if (j3 > 1) {
            d2 /= j3;
        }
        return new DecimalFormat("#.##").format(d2) + " " + str;
    }

    public static String bytesToHuman(long j2) {
        long[] jArr = {1099511627776L, 1073741824, 1048576, 1024, 1};
        String[] strArr = {"TB", "GB", "MB", "KB", "B"};
        if (j2 < 1) {
            return "0 " + strArr[4];
        }
        for (int i2 = 0; i2 < 5; i2++) {
            long j3 = jArr[i2];
            if (j2 >= j3) {
                return a(j2, j3, strArr[i2]);
            }
        }
        return null;
    }

    public static String mapToString(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            for (String str : map.keySet()) {
                String str2 = map.get(str);
                if (!isEmpty(str) && !isEmpty(str2)) {
                    jSONObject.put(str, str2);
                }
            }
            return jSONObject.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    public static Map<String, String> stringToMap(String str, Map<String, String> map) {
        if (isEmpty(str) || map == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                String string = jSONObject.getString(next);
                if (!isEmpty(next) && !isEmpty(string)) {
                    map.put(next, string);
                }
            }
        } catch (Exception unused) {
        }
        return map;
    }

    public static String strEncode(String str) {
        if (isEmpty(str)) {
            return str;
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    public static String encryptWithXor(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            byte[] bytes = str.getBytes("UTF-8");
            for (int i2 = 0; i2 < bytes.length; i2++) {
                bytes[i2] = (byte) (bytes[i2] ^ 5);
            }
            return f.toHexString(bytes, 0, bytes.length);
        } catch (Exception unused) {
            return str;
        }
    }

    public static String decryptWithXor(String str, Boolean... boolArr) {
        byte[] hexStringToBytes;
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (boolArr != null && boolArr.length > 0 && boolArr[0].booleanValue()) {
                hexStringToBytes = Base64.decode(str, 0);
            } else {
                hexStringToBytes = f.hexStringToBytes(str);
            }
            for (int i2 = 0; i2 < hexStringToBytes.length; i2++) {
                hexStringToBytes[i2] = (byte) (hexStringToBytes[i2] ^ 5);
            }
            return new String(hexStringToBytes, 0, hexStringToBytes.length, "UTF-8");
        } catch (Exception unused) {
            return str;
        }
    }

    public static String trimString(String str) {
        return (str == null || str.length() == 0) ? str : str.trim();
    }

    public static String compressWithGzip(String str) {
        ByteArrayOutputStream byteArrayOutputStream;
        GZIPOutputStream gZIPOutputStream;
        if (isEmpty(str)) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream(str.length());
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream.write(str.getBytes());
                    gZIPOutputStream.close();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    String hexString = f.toHexString(byteArray, 0, byteArray.length);
                    try {
                        gZIPOutputStream.close();
                        byteArrayOutputStream.close();
                    } catch (Throwable unused) {
                    }
                    return hexString;
                } catch (Throwable th) {
                    th = th;
                    try {
                        Logger.d("Thread", th.getMessage());
                        if (gZIPOutputStream != null) {
                            try {
                                gZIPOutputStream.close();
                            } catch (Throwable unused2) {
                                return null;
                            }
                        }
                        return null;
                    } finally {
                        if (gZIPOutputStream != null) {
                            try {
                                gZIPOutputStream.close();
                            } catch (Throwable unused3) {
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                gZIPOutputStream = null;
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
            gZIPOutputStream = null;
        }
    }

    public static String decompressWithGzip(String str) {
        GZIPInputStream gZIPInputStream;
        ByteArrayInputStream byteArrayInputStream;
        BufferedReader bufferedReader;
        if (isEmpty(str)) {
            return null;
        }
        try {
            byteArrayInputStream = new ByteArrayInputStream(f.hexStringToBytes(str));
            try {
                gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(gZIPInputStream, "UTF-8"));
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = null;
                }
            } catch (Throwable th2) {
                th = th2;
                gZIPInputStream = null;
                bufferedReader = null;
            }
        } catch (Throwable th3) {
            th = th3;
            gZIPInputStream = null;
            byteArrayInputStream = null;
            bufferedReader = null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            String sb2 = sb.toString();
            try {
                bufferedReader.close();
                gZIPInputStream.close();
                byteArrayInputStream.close();
            } catch (Throwable unused) {
            }
            return sb2;
        } catch (Throwable th4) {
            th = th4;
            try {
                Logger.d("Thread", th.getMessage());
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable unused2) {
                        return null;
                    }
                }
                return null;
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable unused3) {
                    }
                }
                if (gZIPInputStream != null) {
                    gZIPInputStream.close();
                }
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
            }
        }
    }
}
