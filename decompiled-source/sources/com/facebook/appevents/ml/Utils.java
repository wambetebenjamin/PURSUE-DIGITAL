package com.facebook.appevents.ml;

import android.text.TextUtils;
import com.facebook.FacebookSdk;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: Utils.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004J\u001e\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0006\u0010\f\u001a\u00020\u0006H\u0007J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/facebook/appevents/ml/Utils;", "", "()V", "DIR_NAME", "", "getMlDir", "Ljava/io/File;", "normalizeString", "str", "parseModelWeights", "", "Lcom/facebook/appevents/ml/MTensor;", "file", "vectorize", "", "texts", "maxLen", "", "facebook-core_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes8.dex */
public final class Utils {
    private static final String DIR_NAME = "facebook_ml/";
    public static final Utils INSTANCE = new Utils();

    private Utils() {
    }

    public final int[] vectorize(String texts, int i2) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(texts, "texts");
            int[] iArr = new int[i2];
            String normalizeString = normalizeString(texts);
            Charset forName = Charset.forName("UTF-8");
            Intrinsics.checkNotNullExpressionValue(forName, "forName(\"UTF-8\")");
            if (normalizeString != null) {
                byte[] bytes = normalizeString.getBytes(forName);
                Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
                if (i2 > 0) {
                    int i3 = 0;
                    while (true) {
                        int i4 = i3 + 1;
                        if (i3 < bytes.length) {
                            iArr[i3] = bytes[i3] & 255;
                        } else {
                            iArr[i3] = 0;
                        }
                        if (i4 >= i2) {
                            break;
                        }
                        i3 = i4;
                    }
                }
                return iArr;
            }
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    @JvmStatic
    public static final File getMlDir() {
        if (CrashShieldHandler.isObjectCrashing(Utils.class)) {
            return null;
        }
        try {
            FacebookSdk facebookSdk = FacebookSdk.INSTANCE;
            File file = new File(FacebookSdk.getApplicationContext().getFilesDir(), DIR_NAME);
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    return null;
                }
            }
            return file;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Utils.class);
            return null;
        }
    }

    @JvmStatic
    public static final Map<String, MTensor> parseModelWeights(File file) {
        Map<String, MTensor> map;
        Map<String, MTensor> map2 = null;
        if (CrashShieldHandler.isObjectCrashing(Utils.class)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(file, "file");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                int available = fileInputStream.available();
                DataInputStream dataInputStream = new DataInputStream(fileInputStream);
                byte[] bArr = new byte[available];
                dataInputStream.readFully(bArr);
                dataInputStream.close();
                if (available < 4) {
                    return null;
                }
                int i2 = 0;
                ByteBuffer wrap = ByteBuffer.wrap(bArr, 0, 4);
                wrap.order(ByteOrder.LITTLE_ENDIAN);
                int i3 = wrap.getInt();
                int i4 = i3 + 4;
                if (available < i4) {
                    return null;
                }
                JSONObject jSONObject = new JSONObject(new String(bArr, 4, i3, Charsets.UTF_8));
                JSONArray names = jSONObject.names();
                int length = names.length();
                String[] strArr = new String[length];
                int i5 = length - 1;
                if (i5 >= 0) {
                    int i6 = 0;
                    while (true) {
                        int i7 = i6 + 1;
                        strArr[i6] = names.getString(i6);
                        if (i7 > i5) {
                            break;
                        }
                        i6 = i7;
                    }
                }
                ArraysKt.sort((Object[]) strArr);
                HashMap hashMap = new HashMap();
                int i8 = 0;
                while (i8 < length) {
                    String str = strArr[i8];
                    i8++;
                    if (str != null) {
                        JSONArray jSONArray = jSONObject.getJSONArray(str);
                        int length2 = jSONArray.length();
                        int[] iArr = new int[length2];
                        int i9 = length2 - 1;
                        int i10 = 1;
                        if (i9 >= 0) {
                            while (true) {
                                int i11 = i2 + 1;
                                try {
                                    iArr[i2] = jSONArray.getInt(i2);
                                    i10 *= iArr[i2];
                                    if (i11 > i9) {
                                        break;
                                    }
                                    i2 = i11;
                                } catch (Exception unused) {
                                    return null;
                                } catch (Throwable th) {
                                    th = th;
                                    map = null;
                                    CrashShieldHandler.handleThrowable(th, Utils.class);
                                    return map;
                                }
                            }
                        }
                        int i12 = i10 * 4;
                        int i13 = i4 + i12;
                        if (i13 > available) {
                            return null;
                        }
                        ByteBuffer wrap2 = ByteBuffer.wrap(bArr, i4, i12);
                        wrap2.order(ByteOrder.LITTLE_ENDIAN);
                        MTensor mTensor = new MTensor(iArr);
                        wrap2.asFloatBuffer().get(mTensor.getData(), 0, i10);
                        hashMap.put(str, mTensor);
                        i4 = i13;
                        i2 = 0;
                        map2 = null;
                    }
                }
                return hashMap;
            } catch (Exception unused2) {
                return map2;
            }
        } catch (Throwable th2) {
            th = th2;
            map = map2;
        }
    }

    public final String normalizeString(String str) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(str, "str");
            String str2 = str;
            int length = str2.length() - 1;
            int i2 = 0;
            boolean z = false;
            while (i2 <= length) {
                boolean z2 = Intrinsics.compare((int) str2.charAt(!z ? i2 : length), 32) <= 0;
                if (z) {
                    if (!z2) {
                        break;
                    }
                    length--;
                } else if (z2) {
                    i2++;
                } else {
                    z = true;
                }
            }
            Object[] array = new Regex("\\s+").split(str2.subSequence(i2, length + 1).toString(), 0).toArray(new String[0]);
            if (array != null) {
                String join = TextUtils.join(" ", (String[]) array);
                Intrinsics.checkNotNullExpressionValue(join, "join(\" \", strArray)");
                return join;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }
}
