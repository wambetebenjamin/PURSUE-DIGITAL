package com.bytedance.retrofit2.mime;

import android.text.TextUtils;
import android.util.Log;
import com.bytedance.common.utility.Logger;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class TTRequestCompressManager {
    private static final String BLOCK_PATH_EQUAL_KEY = "block_path_equal_list";
    private static final String BLOCK_PATH_REGEX_KEY = "block_path_regex_list";
    private static final String BROTLI_COMPRESS_HEADER = "br";
    private static final String COMPRESS_BROTLI_QUALITY_KEY = "br_level";
    private static final String COMPRESS_ENABLED_KEY = "enabled";
    private static final int COMPRESS_MAX_BODY_SIZE = 1048576;
    private static final String COMPRESS_MAX_BODY_SIZE_KEY = "max_body_size";
    private static final int COMPRESS_MIN_BODY_SIZE = 100;
    private static final String COMPRESS_MIN_BODY_SIZE_KEY = "min_body_size";
    private static final String COMPRESS_REQUEST_KEY = "tt_compress";
    private static final String COMPRESS_REQUEST_TYPE_KEY = "type";
    private static final String COMPRESS_ZSTD_LEVEL_KEY = "zstd_level";
    private static final int DEFAULT_BROTLI_QUALITY = 4;
    private static final int DEFAULT_ZSTD_LEVEL = 6;
    private static final String EQUAL_PATH_KEY = "equal_path";
    private static final String GZIP_COMPRESS_HEADER = "gzip";
    private static final String HOST_GROUP_KEY = "host_group";
    private static final int MAX_BROTLI_QUALITY = 11;
    private static final int MAX_ZSTD_LEVEL = 22;
    private static final int MIN_BROTLI_QUALITY = 0;
    private static final int MIN_ZSTD_LEVEL = 1;
    private static final String OPT_ENABLED_KEY = "opt_enabled";
    private static final String PREFIX_PATH_KEY = "prefix_path";
    private static final String REGEX_PATH_KEY = "regex_path";
    private static final String TAG = "TTRequestCompress";
    private static final String ZSTD_COMPRESS_HEADER = "zstd";
    private static final String ZSTD_EQUAL_PATH_KEY = "zstd_equal_path";
    private static final String ZSTD_PREFIX_PATH_KEY = "zstd_prefix_path";
    private static final String ZSTD_REGEX_PATH_KEY = "zstd_regex_path";
    private static AddZstdCompressCallback sAddZstdCompressCallback = null;
    private static int sBrotliCompressQuality = 4;
    public static volatile CompressDataCallback sCallback = null;
    private static volatile boolean sCompressOptEnabled = false;
    private static int sMaxBodyCompressSize = 1048576;
    private static int sMinBodyCompressSize = 100;
    private static volatile boolean sRequestCompressEnabled = false;
    private static int sZstdCompressLevel = 6;
    private static CompressType sCompressType = CompressType.NONE;
    private static CopyOnWriteArraySet<String> sEqualPathArraySet = new CopyOnWriteArraySet<>();
    private static CopyOnWriteArraySet<String> sPrefixPathArraySet = new CopyOnWriteArraySet<>();
    private static CopyOnWriteArraySet<Pattern> sRegexPathArraySet = new CopyOnWriteArraySet<>();
    private static CopyOnWriteArraySet<String> sZstdEqualPathArraySet = new CopyOnWriteArraySet<>();
    private static CopyOnWriteArraySet<String> sZstdPrefixPathArraySet = new CopyOnWriteArraySet<>();
    private static CopyOnWriteArraySet<Pattern> sZstdRegexPathArraySet = new CopyOnWriteArraySet<>();
    private static CopyOnWriteArraySet<String> sHostGroupArraySet = new CopyOnWriteArraySet<>();
    private static CopyOnWriteArraySet<String> sBlockEqualPathArraySet = new CopyOnWriteArraySet<>();
    private static CopyOnWriteArraySet<Pattern> sBlockPatternPathArraySet = new CopyOnWriteArraySet<>();
    public static volatile Boolean sGlobalCompressDisabled = false;
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    /* loaded from: classes2.dex */
    public interface AddZstdCompressCallback {
        byte[] compress(byte[] bArr, int i2);

        byte[] decompress(byte[] bArr, int i2);
    }

    /* loaded from: classes2.dex */
    public interface CompressDataCallback {
        byte[] compressData(byte[] bArr, int i2, int i3, int i4);

        byte[] decompressData(byte[] bArr, int i2, int i3);
    }

    /* loaded from: classes2.dex */
    public enum CompressType {
        NONE(0),
        GZIP(1),
        BROTLI(2),
        ZSTD(3);
        
        private final int mType;

        CompressType(int i2) {
            this.mType = i2;
        }

        public int getType() {
            return this.mType;
        }
    }

    /* loaded from: classes2.dex */
    public enum DisableCompress {
        NONE(0),
        GLOBAL_DISABLE(1),
        TNC_DISABLE(2),
        URL_MISMATCH(3),
        BODY_SIZE_MISMATCH(4),
        BODY_SIZE_OVERFLOW(5),
        ZSTD_PREFIX_MISMATCH(6),
        GZIP_PREFIX_MISMATCH(7),
        BODY_ENCRYPTED(8),
        REMOVE_ENCODING(9),
        DATA_NULL(10);
        
        private final int reason;

        DisableCompress(int i2) {
            this.reason = i2;
        }

        public int getReason() {
            return this.reason;
        }
    }

    private static void resetCompressConfig() {
        sRequestCompressEnabled = false;
        sMaxBodyCompressSize = 1048576;
        sMinBodyCompressSize = 100;
        sCompressType = CompressType.NONE;
        sEqualPathArraySet.clear();
        sPrefixPathArraySet.clear();
        sRegexPathArraySet.clear();
        sZstdEqualPathArraySet.clear();
        sZstdPrefixPathArraySet.clear();
        sZstdRegexPathArraySet.clear();
        sHostGroupArraySet.clear();
        sBlockEqualPathArraySet.clear();
        sBlockPatternPathArraySet.clear();
    }

    public static boolean isBodyOptEnabled() {
        return sCompressOptEnabled;
    }

    public static void onServerConfigChanged(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        resetCompressConfig();
        JSONObject optJSONObject = jSONObject.optJSONObject(COMPRESS_REQUEST_KEY);
        if (optJSONObject == null) {
            return;
        }
        sRequestCompressEnabled = optJSONObject.optInt("enabled", 0) > 0;
        sCompressOptEnabled = optJSONObject.optInt(OPT_ENABLED_KEY, 0) > 0;
        sMaxBodyCompressSize = optJSONObject.optInt(COMPRESS_MAX_BODY_SIZE_KEY, 1048576);
        sMinBodyCompressSize = optJSONObject.optInt(COMPRESS_MIN_BODY_SIZE_KEY, 100);
        int optInt = optJSONObject.optInt(COMPRESS_BROTLI_QUALITY_KEY, 4);
        if (optInt >= 0 && optInt <= 11) {
            sBrotliCompressQuality = optInt;
        }
        int optInt2 = optJSONObject.optInt(COMPRESS_ZSTD_LEVEL_KEY, 6);
        if (optInt2 >= 1 && optInt2 <= 22) {
            sZstdCompressLevel = optInt2;
        }
        int optInt3 = optJSONObject.optInt("type", CompressType.GZIP.getType());
        covertIntToCompressType(optInt3);
        parseArraySetStringConfig(optJSONObject.optJSONArray(EQUAL_PATH_KEY), sEqualPathArraySet);
        parseArraySetStringConfig(optJSONObject.optJSONArray(PREFIX_PATH_KEY), sPrefixPathArraySet);
        parseArraySetPatternConfig(optJSONObject.optJSONArray(REGEX_PATH_KEY), sRegexPathArraySet);
        parseArraySetStringConfig(optJSONObject.optJSONArray(ZSTD_EQUAL_PATH_KEY), sZstdEqualPathArraySet);
        parseArraySetStringConfig(optJSONObject.optJSONArray(ZSTD_PREFIX_PATH_KEY), sZstdPrefixPathArraySet);
        parseArraySetPatternConfig(optJSONObject.optJSONArray(ZSTD_REGEX_PATH_KEY), sZstdRegexPathArraySet);
        parseArraySetStringConfig(optJSONObject.optJSONArray(HOST_GROUP_KEY), sHostGroupArraySet);
        parseArraySetStringConfig(optJSONObject.optJSONArray(BLOCK_PATH_EQUAL_KEY), sBlockEqualPathArraySet);
        parseArraySetPatternConfig(optJSONObject.optJSONArray(BLOCK_PATH_REGEX_KEY), sBlockPatternPathArraySet);
        if (Logger.debug()) {
            Log.d(TAG, "sRequestCompressEnabled:" + sRequestCompressEnabled + " type:" + optInt3);
        }
    }

    private static void parseArraySetStringConfig(JSONArray jSONArray, CopyOnWriteArraySet<String> copyOnWriteArraySet) {
        if (jSONArray == null || copyOnWriteArraySet == null) {
            return;
        }
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            try {
                String string = jSONArray.getString(i2);
                if (!TextUtils.isEmpty(string)) {
                    copyOnWriteArraySet.add(string);
                }
            } catch (JSONException unused) {
                return;
            }
        }
    }

    private static void parseArraySetPatternConfig(JSONArray jSONArray, CopyOnWriteArraySet<Pattern> copyOnWriteArraySet) {
        if (jSONArray == null || copyOnWriteArraySet == null) {
            return;
        }
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            try {
                String string = jSONArray.getString(i2);
                if (!TextUtils.isEmpty(string)) {
                    try {
                        copyOnWriteArraySet.add(Pattern.compile(string, 2));
                    } catch (Throwable unused) {
                    }
                }
            } catch (JSONException unused2) {
                return;
            }
        }
    }

    private static void covertIntToCompressType(int i2) {
        if (i2 == 1) {
            sCompressType = CompressType.GZIP;
        } else if (i2 == 2) {
            sCompressType = CompressType.BROTLI;
        } else if (i2 == 3) {
            sCompressType = CompressType.ZSTD;
        } else {
            sCompressType = CompressType.NONE;
        }
    }

    public static void setCompressDataCallback(CompressDataCallback compressDataCallback) {
        sCallback = compressDataCallback;
    }

    public static void setGlobalForbidenCompress() {
        sGlobalCompressDisabled = true;
    }

    private static CompressType getCompressType(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return CompressType.NONE;
        }
        if (!isEmptySet(sZstdEqualPathArraySet) && sZstdEqualPathArraySet.contains(str2)) {
            return CompressType.ZSTD;
        }
        if (!isEmptySet(sZstdPrefixPathArraySet)) {
            Iterator<String> it = sZstdPrefixPathArraySet.iterator();
            while (it.hasNext()) {
                if (str2.startsWith(it.next())) {
                    return CompressType.ZSTD;
                }
            }
        }
        if (!isEmptySet(sZstdRegexPathArraySet)) {
            Iterator<Pattern> it2 = sZstdRegexPathArraySet.iterator();
            while (it2.hasNext()) {
                Pattern next = it2.next();
                if (next != null) {
                    try {
                        if (next.matcher(str2).matches()) {
                            return CompressType.ZSTD;
                        }
                        continue;
                    } catch (IllegalArgumentException unused) {
                        continue;
                    }
                }
            }
        }
        if (!isEmptySet(sEqualPathArraySet) && sEqualPathArraySet.contains(str2)) {
            return sCompressType;
        }
        if (!isEmptySet(sPrefixPathArraySet)) {
            Iterator<String> it3 = sPrefixPathArraySet.iterator();
            while (it3.hasNext()) {
                if (str2.startsWith(it3.next())) {
                    return sCompressType;
                }
            }
        }
        if (!isEmptySet(sRegexPathArraySet)) {
            Iterator<Pattern> it4 = sRegexPathArraySet.iterator();
            while (it4.hasNext()) {
                Pattern next2 = it4.next();
                if (next2 != null) {
                    try {
                        if (next2.matcher(str2).matches()) {
                            return sCompressType;
                        }
                        continue;
                    } catch (IllegalArgumentException unused2) {
                        continue;
                    }
                }
            }
        }
        if (isEmptySet(sHostGroupArraySet) || !isHostMatch(str)) {
            return CompressType.NONE;
        }
        if (!isEmptySet(sBlockEqualPathArraySet) && sBlockEqualPathArraySet.contains(str2)) {
            return CompressType.NONE;
        }
        if (!isEmptySet(sBlockPatternPathArraySet)) {
            Iterator<Pattern> it5 = sBlockPatternPathArraySet.iterator();
            while (it5.hasNext()) {
                Pattern next3 = it5.next();
                if (next3 != null) {
                    try {
                        if (next3.matcher(str2).matches()) {
                            return CompressType.NONE;
                        }
                        continue;
                    } catch (IllegalArgumentException unused3) {
                        continue;
                    }
                }
            }
        }
        return sCompressType;
    }

    private static boolean isHostMatch(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Iterator<String> it = sHostGroupArraySet.iterator();
        while (it.hasNext()) {
            if (matchPattern(str, it.next())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isEmptySet(Set<?> set) {
        return set == null || set.isEmpty();
    }

    /* loaded from: classes2.dex */
    public static class CompressData {
        public String contentEncoding;
        public byte[] data;
        public int failedCode;
        public String failedPrefixData;
        public CompressType type;

        CompressData(int i2) {
            this.failedCode = i2;
        }
    }

    public static CompressData compressBody(byte[] bArr, int i2, String str, String str2, boolean z) {
        CompressData compressData = new CompressData(0);
        if (sGlobalCompressDisabled.booleanValue()) {
            if (Logger.debug()) {
                Log.d(TAG, "global compress is disabled.");
            }
            compressData.failedCode = DisableCompress.GLOBAL_DISABLE.getReason();
            return compressData;
        } else if (!sRequestCompressEnabled || sCompressType == CompressType.NONE) {
            if (Logger.debug()) {
                Log.d(TAG, "Request compress is disabled.");
            }
            compressData.failedCode = DisableCompress.TNC_DISABLE.getReason();
            return compressData;
        } else {
            CompressType compressType = getCompressType(str, str2);
            if (!z && compressType == CompressType.NONE) {
                if (Logger.debug()) {
                    Log.d(TAG, "Url is not matched:" + str + str2);
                }
                compressData.failedCode = DisableCompress.URL_MISMATCH.getReason();
                return compressData;
            } else if (i2 > sMaxBodyCompressSize || i2 < sMinBodyCompressSize) {
                if (Logger.debug()) {
                    Log.d(TAG, "Request body size is not matched:" + i2);
                }
                compressData.failedCode = DisableCompress.BODY_SIZE_MISMATCH.getReason();
                return compressData;
            } else if (compressType == CompressType.GZIP) {
                byte[] gzipCompress = gzipCompress(bArr, i2);
                if (canUseCompressedBody(i2, gzipCompress, compressType, compressData)) {
                    compressData.data = gzipCompress;
                    compressData.type = compressType;
                    compressData.contentEncoding = "gzip";
                    return compressData;
                }
                return compressData;
            } else if (compressType == CompressType.BROTLI) {
                byte[] brotliCompress = brotliCompress(bArr, i2, sBrotliCompressQuality);
                if (canUseCompressedBody(i2, brotliCompress, compressType, compressData)) {
                    compressData.data = brotliCompress;
                    compressData.type = compressType;
                    compressData.contentEncoding = BROTLI_COMPRESS_HEADER;
                    return compressData;
                }
                return compressData;
            } else if (compressType == CompressType.ZSTD) {
                byte[] zstdCompress = zstdCompress(bArr, sZstdCompressLevel);
                if (canUseCompressedBody(i2, zstdCompress, compressType, compressData)) {
                    compressData.data = zstdCompress;
                    compressData.type = compressType;
                    compressData.contentEncoding = "zstd";
                    return compressData;
                }
                return compressData;
            } else {
                return null;
            }
        }
    }

    private static String byteArrayToHexString(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = bArr[i2] & 255;
            int i4 = i2 * 2;
            char[] cArr2 = HEX_ARRAY;
            cArr[i4] = cArr2[i3 >>> 4];
            cArr[i4 + 1] = cArr2[i3 & 15];
        }
        return new String(cArr);
    }

    public static boolean canUseCompressedBody(int i2, byte[] bArr, CompressType compressType, CompressData compressData) {
        if (bArr == null || bArr.length <= 3) {
            return false;
        }
        if (sCompressOptEnabled) {
            if (bArr.length >= i2) {
                compressData.failedCode = DisableCompress.BODY_SIZE_OVERFLOW.getReason();
                return false;
            } else if (compressType == CompressType.GZIP) {
                byte b2 = bArr[0];
                byte b3 = bArr[1];
                byte b4 = bArr[2];
                if (b2 == 31 && b3 == -117 && b4 == 8) {
                    return true;
                }
                compressData.failedCode = DisableCompress.GZIP_PREFIX_MISMATCH.getReason();
                compressData.failedPrefixData = byteArrayToHexString(new byte[]{b2, b3, b4});
                return false;
            } else if (compressType == CompressType.ZSTD) {
                byte b5 = bArr[0];
                byte b6 = bArr[1];
                byte b7 = bArr[2];
                if (b5 == 40 && b6 == -75 && b7 == 47) {
                    return true;
                }
                compressData.failedCode = DisableCompress.ZSTD_PREFIX_MISMATCH.getReason();
                compressData.failedPrefixData = byteArrayToHexString(new byte[]{b5, b6, b7});
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    public static byte[] brotliCompress(byte[] bArr, int i2, int i3) {
        if (bArr == null || sCallback == null) {
            return null;
        }
        return sCallback.compressData(bArr, i2, i3, CompressType.BROTLI.getType());
    }

    public static byte[] brotliDecompress(byte[] bArr, int i2) {
        if (bArr == null || sCallback == null) {
            return null;
        }
        return sCallback.decompressData(bArr, i2, CompressType.BROTLI.getType());
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0053 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] gzipCompress(byte[] r5, int r6) {
        /*
            r6 = 0
            if (r5 != 0) goto L4
            return r6
        L4:
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            java.util.zip.GZIPOutputStream r1 = new java.util.zip.GZIPOutputStream     // Catch: java.lang.Throwable -> L23 java.io.IOException -> L25
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L23 java.io.IOException -> L25
            r1.write(r5)     // Catch: java.io.IOException -> L21 java.lang.Throwable -> L4f
            r1.close()     // Catch: java.io.IOException -> L18
            r0.close()     // Catch: java.io.IOException -> L18
            goto L1c
        L18:
            r5 = move-exception
            r5.printStackTrace()
        L1c:
            byte[] r5 = r0.toByteArray()
            return r5
        L21:
            r5 = move-exception
            goto L27
        L23:
            r5 = move-exception
            goto L51
        L25:
            r5 = move-exception
            r1 = r6
        L27:
            java.lang.String r2 = "TTRequestCompress"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4f
            r3.<init>()     // Catch: java.lang.Throwable -> L4f
            java.lang.String r4 = "Compress with gzip exception: "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch: java.lang.Throwable -> L4f
            java.lang.StringBuilder r5 = r3.append(r5)     // Catch: java.lang.Throwable -> L4f
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L4f
            android.util.Log.w(r2, r5)     // Catch: java.lang.Throwable -> L4f
            if (r1 == 0) goto L47
            r1.close()     // Catch: java.io.IOException -> L45
            goto L47
        L45:
            r5 = move-exception
            goto L4b
        L47:
            r0.close()     // Catch: java.io.IOException -> L45
            goto L4e
        L4b:
            r5.printStackTrace()
        L4e:
            return r6
        L4f:
            r5 = move-exception
            r6 = r1
        L51:
            if (r6 == 0) goto L59
            r6.close()     // Catch: java.io.IOException -> L57
            goto L59
        L57:
            r6 = move-exception
            goto L5d
        L59:
            r0.close()     // Catch: java.io.IOException -> L57
            goto L60
        L5d:
            r6.printStackTrace()
        L60:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.retrofit2.mime.TTRequestCompressManager.gzipCompress(byte[], int):byte[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0061 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] gzipDecompress(byte[] r6) {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r1 = 0
            java.util.zip.GZIPInputStream r2 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L33
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L33
            r3.<init>(r6)     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L33
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L33
            r6 = 4096(0x1000, float:5.74E-42)
            byte[] r6 = new byte[r6]     // Catch: java.io.IOException -> L2f java.lang.Throwable -> L5d
        L14:
            int r3 = r2.read(r6)     // Catch: java.io.IOException -> L2f java.lang.Throwable -> L5d
            if (r3 < 0) goto L1f
            r4 = 0
            r0.write(r6, r4, r3)     // Catch: java.io.IOException -> L2f java.lang.Throwable -> L5d
            goto L14
        L1f:
            r2.close()     // Catch: java.io.IOException -> L26
            r0.close()     // Catch: java.io.IOException -> L26
            goto L2a
        L26:
            r6 = move-exception
            r6.printStackTrace()
        L2a:
            byte[] r6 = r0.toByteArray()
            return r6
        L2f:
            r6 = move-exception
            goto L35
        L31:
            r6 = move-exception
            goto L5f
        L33:
            r6 = move-exception
            r2 = r1
        L35:
            java.lang.String r3 = "TTRequestCompress"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5d
            r4.<init>()     // Catch: java.lang.Throwable -> L5d
            java.lang.String r5 = "Decompress with gzip exception: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch: java.lang.Throwable -> L5d
            java.lang.StringBuilder r6 = r4.append(r6)     // Catch: java.lang.Throwable -> L5d
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L5d
            android.util.Log.w(r3, r6)     // Catch: java.lang.Throwable -> L5d
            if (r2 == 0) goto L55
            r2.close()     // Catch: java.io.IOException -> L53
            goto L55
        L53:
            r6 = move-exception
            goto L59
        L55:
            r0.close()     // Catch: java.io.IOException -> L53
            goto L5c
        L59:
            r6.printStackTrace()
        L5c:
            return r1
        L5d:
            r6 = move-exception
            r1 = r2
        L5f:
            if (r1 == 0) goto L67
            r1.close()     // Catch: java.io.IOException -> L65
            goto L67
        L65:
            r0 = move-exception
            goto L6b
        L67:
            r0.close()     // Catch: java.io.IOException -> L65
            goto L6e
        L6b:
            r0.printStackTrace()
        L6e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.retrofit2.mime.TTRequestCompressManager.gzipDecompress(byte[]):byte[]");
    }

    public static void setAddZstdCompressCallback(AddZstdCompressCallback addZstdCompressCallback) {
        sAddZstdCompressCallback = addZstdCompressCallback;
    }

    public static byte[] zstdCompress(byte[] bArr, int i2) {
        AddZstdCompressCallback addZstdCompressCallback = sAddZstdCompressCallback;
        if (addZstdCompressCallback != null) {
            return addZstdCompressCallback.compress(bArr, i2);
        }
        return null;
    }

    public static byte[] zstdDecompress(byte[] bArr, int i2) {
        AddZstdCompressCallback addZstdCompressCallback = sAddZstdCompressCallback;
        if (addZstdCompressCallback != null) {
            return addZstdCompressCallback.decompress(bArr, i2);
        }
        return null;
    }

    public static byte[] decompressDataByType(byte[] bArr, String str, int i2) {
        if (bArr == null || TextUtils.isEmpty(str)) {
            return bArr;
        }
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case 3152:
                if (str.equals(BROTLI_COMPRESS_HEADER)) {
                    c2 = 0;
                    break;
                }
                break;
            case 3189082:
                if (str.equals("gzip")) {
                    c2 = 1;
                    break;
                }
                break;
            case 3748713:
                if (str.equals("zstd")) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return brotliDecompress(bArr, bArr.length);
            case 1:
                return gzipDecompress(bArr);
            case 2:
                return zstdDecompress(bArr, i2);
            default:
                return bArr;
        }
    }

    private static boolean matchPattern(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        int length = str.length();
        int length2 = str2.length();
        int i2 = 0;
        int i3 = 0;
        int i4 = -1;
        int i5 = -1;
        while (i2 < length) {
            if (i3 < length2 && (str2.charAt(i3) == '?' || str2.charAt(i3) == str.charAt(i2))) {
                i2++;
                i3++;
            } else if (i3 < length2 && str2.charAt(i3) == '*') {
                i5 = i2;
                i4 = i3;
                i3++;
            } else if (i4 == -1) {
                return false;
            } else {
                i3 = i4 + 1;
                i5++;
                i2 = i5;
            }
        }
        while (i3 < length2) {
            if (str2.charAt(i3) != '*') {
                return false;
            }
            i3++;
        }
        return true;
    }
}
