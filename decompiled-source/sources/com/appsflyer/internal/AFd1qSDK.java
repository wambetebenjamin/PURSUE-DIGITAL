package com.appsflyer.internal;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ExpandableListView;
import com.appsflyer.AFLogger;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import kotlin.io.encoding.Base64;
/* loaded from: classes.dex */
public final class AFd1qSDK extends HashMap<String, Object> {
    private static int $10 = 0;
    private static int $11 = 1;
    private static char AFInAppEventType = 0;
    private static char AFKeystoreWrapper = 0;
    private static int AFLogger = 0;

    /* renamed from: d  reason: collision with root package name */
    private static byte[] f55d = null;

    /* renamed from: e  reason: collision with root package name */
    private static int f56e = 0;
    private static int force = 0;
    private static char registerClient = 0;
    private static int unregisterClient = 0;
    private static short[] v = null;
    private static char valueOf = 0;
    private static int w = 1;
    private final Map<String, Object> AFInAppEventParameterName;
    private final Context values;

    static {
        AFInAppEventType();
        ViewConfiguration.getScrollDefaultDelay();
        Gravity.getAbsoluteGravity(0, 0);
        Color.red(0);
        ViewConfiguration.getFadingEdgeLength();
        TextUtils.indexOf("", "", 0, 0);
        TypedValue.complexToFraction(0, 0.0f, 0.0f);
        TextUtils.indexOf("", "", 0);
        View.resolveSize(0, 0);
        TextUtils.indexOf("", "", 0, 0);
        ExpandableListView.getPackedPositionType(0L);
        Color.red(0);
        TextUtils.indexOf((CharSequence) "", '0', 0, 0);
        Color.blue(0);
        TextUtils.indexOf("", "", 0);
        TextUtils.indexOf("", "");
        TextUtils.getOffsetAfter("", 0);
        View.resolveSize(0, 0);
        TextUtils.indexOf("", "", 0);
        TextUtils.indexOf((CharSequence) "", '0');
        int i2 = force + 17;
        w = i2 % 128;
        int i3 = i2 % 2;
    }

    static void AFInAppEventType() {
        AFKeystoreWrapper = (char) 24264;
        registerClient = (char) 63721;
        AFInAppEventType = (char) 5476;
        valueOf = (char) 64623;
        f56e = -1309634689;
        AFLogger = 1099409257;
        unregisterClient = -804683925;
        f55d = new byte[]{-56, -79, -7, 0, -5, 84, -92, Ascii.EM, -13, SignedBytes.MAX_POWER_OF_TWO, -77, 10, -13, SignedBytes.MAX_POWER_OF_TWO, -78, -14, 6, -10, Ascii.CAN, -4, Ascii.RS, -12, 2, Ascii.SI, 68, -79, Ascii.FF, -14, 8, 5, -16, 71, -92, Ascii.EM, 19, Ascii.DC2, -47, -21, Ascii.FS, Ascii.SI, 68, -105, 113, -68, -73, -90, -71, -80, -69, -112, -56, -10, 101, -87, -80, -85, 4, 84, -55, -93, -16, 99, -70, -93, -16, 98, -94, -74, -90, -56, -84, -50, -92, -78, -65, -12, 97, -68, -94, -72, -75, -96, -85, -85, -85, 118, -88, -111, Ascii.GS, 59, 49, -16, 41, Ascii.EM, Ascii.NAK, 56, 57, -12, 45, 45, 37, 43, 34, 43, 34, 35, 44, -7, Ascii.RS, Ascii.FS, -4, 83, -49, 6, Ascii.SI, 92, -50, Ascii.SO, 2, -14, Ascii.DC4, -8, Ascii.SUB, -16, Ascii.RS, Ascii.VT, SignedBytes.MAX_POWER_OF_TWO, -51, 8, Ascii.SO, 4, 1, Ascii.FF, 67, -52, -7, Ascii.RS, Ascii.FS, Ascii.FS, 37, -19, -25, Ascii.CAN, Ascii.VT, 53, 59, 60, Ascii.SO, 103, 34, 7, 102, 62, Base64.padSymbol, 55, -15, 54, 62, 57, 59, Base64.padSymbol, 84, 106, 102, 116, 88, 122, 82, 100, 97, 86, -7, -73, -85, Ascii.SO, Ascii.SO, Ascii.SO, Ascii.SO, Ascii.SO, Ascii.SO, Ascii.SO, Ascii.SO, Ascii.SO, Ascii.SO, Ascii.SO};
    }

    public AFd1qSDK(Map<String, Object> map, Context context) {
        this.AFInAppEventParameterName = map;
        this.values = context;
        put(valueOf(), AFInAppEventParameterName());
    }

    private static StringBuilder AFKeystoreWrapper(String... strArr) throws Exception {
        int i2;
        ArrayList arrayList = new ArrayList();
        int length = strArr.length;
        for (int i3 = 0; i3 < 3; i3++) {
            arrayList.add(Integer.valueOf(strArr[i3].length()));
        }
        Collections.sort(arrayList);
        int intValue = ((Integer) arrayList.get(0)).intValue();
        StringBuilder sb = new StringBuilder();
        int i4 = 0;
        while (i4 < intValue) {
            Integer num = null;
            int length2 = strArr.length;
            int i5 = 0;
            while (true) {
                if (!(i5 < 3 ? true : true)) {
                    break;
                }
                int i6 = w + 107;
                force = i6 % 128;
                int i7 = i6 % 2;
                int charAt = strArr[i5].charAt(i4);
                if (num != null) {
                    charAt ^= num.intValue();
                    i2 = force + 57;
                    w = i2 % 128;
                } else {
                    i2 = w + 13;
                    force = i2 % 128;
                }
                int i8 = i2 % 2;
                num = Integer.valueOf(charAt);
                i5++;
            }
            sb.append(Integer.toHexString(num.intValue()));
            i4++;
            int i9 = w + 55;
            force = i9 % 128;
            int i10 = i9 % 2;
        }
        return sb;
    }

    private String valueOf() {
        int i2 = force + 89;
        w = i2 % 128;
        int i3 = i2 % 2;
        try {
            String num = Integer.toString(Build.VERSION.SDK_INT);
            Map<String, Object> map = this.AFInAppEventParameterName;
            Object[] objArr = new Object[1];
            a("뷍獃㟧껳皵蘨㐬疒刊\ue36fҸ\uf739", (ViewConfiguration.getScrollFriction() > 0.0f ? 1 : (ViewConfiguration.getScrollFriction() == 0.0f ? 0 : -1)) + 11, objArr);
            String obj = map.get(((String) objArr[0]).intern()).toString();
            Map<String, Object> map2 = this.AFInAppEventParameterName;
            Object[] objArr2 = new Object[1];
            a("踄\ue9ed嵨뼦瘺\uf25e", 4 - TextUtils.lastIndexOf("", '0', 0), objArr2);
            String obj2 = map2.get(((String) objArr2[0]).intern()).toString();
            if (obj2 == null) {
                Object[] objArr3 = new Object[1];
                a("庍Ͱ븅鼂稰\ueec8\ue3ac샡", 8 - ExpandableListView.getPackedPositionType(0L), objArr3);
                obj2 = ((String) objArr3[0]).intern();
            }
            StringBuilder sb = new StringBuilder(obj);
            sb.reverse();
            StringBuilder AFKeystoreWrapper2 = AFKeystoreWrapper(num, obj2, sb.toString());
            int length = AFKeystoreWrapper2.length();
            if (length <= 4) {
                while (true) {
                    if ((length < 4 ? 'V' : 'N') == 'N') {
                        break;
                    }
                    length++;
                    AFKeystoreWrapper2.append('1');
                    int i4 = w + 25;
                    force = i4 % 128;
                    int i5 = i4 % 2;
                }
            } else {
                int i6 = force + 5;
                w = i6 % 128;
                if (i6 % 2 == 0) {
                    AFKeystoreWrapper2.delete(2, length);
                } else {
                    AFKeystoreWrapper2.delete(4, length);
                }
            }
            Object[] objArr4 = new Object[1];
            b((short) (TextUtils.getOffsetAfter("", 0) + 59), (byte) View.MeasureSpec.getSize(0), 1852909574 - (ViewConfiguration.getTouchSlop() >> 8), (-100) - (PointF.length(0.0f, 0.0f) > 0.0f ? 1 : (PointF.length(0.0f, 0.0f) == 0.0f ? 0 : -1)), 260626319 - (ViewConfiguration.getFadingEdgeLength() >> 16), objArr4);
            AFKeystoreWrapper2.insert(0, ((String) objArr4[0]).intern());
            return AFKeystoreWrapper2.toString();
        } catch (Exception e2) {
            Object[] objArr5 = new Object[1];
            b((short) ((-2) - (SystemClock.elapsedRealtime() > 0L ? 1 : (SystemClock.elapsedRealtime() == 0L ? 0 : -1))), (byte) (ViewConfiguration.getPressedStateDuration() >> 16), (ViewConfiguration.getScrollBarFadeDuration() >> 16) + 1852909499, TextUtils.indexOf((CharSequence) "", '0') - 62, 260626321 - (TypedValue.complexToFloat(0) > 0.0f ? 1 : (TypedValue.complexToFloat(0) == 0.0f ? 0 : -1)), objArr5);
            AFLogger.afErrorLogForExcManagerOnly(((String) objArr5[0]).intern(), e2);
            StringBuilder sb2 = new StringBuilder();
            Object[] objArr6 = new Object[1];
            b((short) (ExpandableListView.getPackedPositionChild(0L) + 78), (byte) (ViewConfiguration.getTouchSlop() >> 8), (Process.getElapsedCpuTime() > 0L ? 1 : (Process.getElapsedCpuTime() == 0L ? 0 : -1)) + 1852909568, (-61) - TextUtils.getTrimmedLength(""), 260626360 - TextUtils.indexOf("", "", 0), objArr6);
            AFLogger.afRDLog(sb2.append(((String) objArr6[0]).intern()).append(e2).toString());
            Object[] objArr7 = new Object[1];
            b((short) (TextUtils.indexOf((CharSequence) "", '0', 0) + 92), (byte) KeyEvent.normalizeMetaState(0), (SystemClock.elapsedRealtime() > 0L ? 1 : (SystemClock.elapsedRealtime() == 0L ? 0 : -1)) + 1852909573, (-97) - TextUtils.lastIndexOf("", '0', 0), (ViewConfiguration.getEdgeSlop() >> 16) + 260626401, objArr7);
            return ((String) objArr7[0]).intern();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0360  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0366 A[Catch: Exception -> 0x0399, TryCatch #1 {Exception -> 0x0399, blocks: (B:8:0x019e, B:12:0x01ce, B:13:0x0210, B:15:0x021b, B:23:0x0279, B:29:0x0366, B:31:0x0372, B:32:0x037c, B:33:0x0382), top: B:40:0x019e }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0382 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String AFInAppEventParameterName() {
        /*
            Method dump skipped, instructions count: 1026
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFd1qSDK.AFInAppEventParameterName():java.lang.String");
    }

    /* loaded from: classes.dex */
    public static class AFa1ySDK {
        static byte[] AFKeystoreWrapper(String str) throws Exception {
            return str.getBytes(Charset.defaultCharset());
        }

        static byte[] values(byte[] bArr) throws Exception {
            for (int i2 = 0; i2 < bArr.length; i2++) {
                bArr[i2] = (byte) (bArr[i2] ^ ((i2 % 2) + 42));
            }
            return bArr;
        }
    }

    private static void a(String str, int i2, Object[] objArr) {
        char[] charArray;
        if ((str != null ? 'O' : '%') != 'O') {
            charArray = str;
        } else {
            charArray = str.toCharArray();
            int i3 = $10 + 109;
            $11 = i3 % 128;
            int i4 = i3 % 2;
        }
        char[] cArr = charArray;
        AFj1pSDK aFj1pSDK = new AFj1pSDK();
        char[] cArr2 = new char[cArr.length];
        aFj1pSDK.AFKeystoreWrapper = 0;
        char[] cArr3 = new char[2];
        int i5 = $10 + 65;
        $11 = i5 % 128;
        int i6 = i5 % 2;
        while (true) {
            if (aFj1pSDK.AFKeystoreWrapper >= cArr.length) {
                objArr[0] = new String(cArr2, 0, i2);
                return;
            }
            cArr3[0] = cArr[aFj1pSDK.AFKeystoreWrapper];
            cArr3[1] = cArr[aFj1pSDK.AFKeystoreWrapper + 1];
            int i7 = 58224;
            int i8 = 0;
            while (true) {
                if ((i8 < 16 ? '7' : (char) 15) != 15) {
                    int i9 = $11;
                    int i10 = i9 + 1;
                    $10 = i10 % 128;
                    int i11 = i10 % 2;
                    cArr3[1] = (char) (cArr3[1] - (((cArr3[0] + i7) ^ ((cArr3[0] << 4) + ((char) (valueOf ^ (-153834358171198606L))))) ^ ((cArr3[0] >>> 5) + ((char) (registerClient ^ (-153834358171198606L))))));
                    cArr3[0] = (char) (cArr3[0] - (((cArr3[1] + i7) ^ ((cArr3[1] << 4) + ((char) (AFInAppEventType ^ (-153834358171198606L))))) ^ ((cArr3[1] >>> 5) + ((char) (AFKeystoreWrapper ^ (-153834358171198606L))))));
                    i7 -= 40503;
                    i8++;
                    int i12 = i9 + 31;
                    $10 = i12 % 128;
                    int i13 = i12 % 2;
                }
            }
            cArr2[aFj1pSDK.AFKeystoreWrapper] = cArr3[0];
            cArr2[aFj1pSDK.AFKeystoreWrapper + 1] = cArr3[1];
            aFj1pSDK.AFKeystoreWrapper += 2;
        }
    }

    private static void b(short s, byte b2, int i2, int i3, int i4, Object[] objArr) {
        boolean z;
        int i5;
        int i6;
        int length;
        byte[] bArr;
        int i7;
        AFj1nSDK aFj1nSDK = new AFj1nSDK();
        StringBuilder sb = new StringBuilder();
        int i8 = i3 + ((int) (AFLogger ^ 7817788349036865294L));
        if ((i8 == -1 ? 'I' : (char) 3) != 'I') {
            z = false;
        } else {
            int i9 = $11 + 29;
            $10 = i9 % 128;
            int i10 = i9 % 2;
            z = true;
        }
        if (z) {
            byte[] bArr2 = f55d;
            if (!(bArr2 == null)) {
                int i11 = $11 + 1;
                $10 = i11 % 128;
                if (i11 % 2 != 0) {
                    length = bArr2.length;
                    bArr = new byte[length];
                    i7 = 1;
                } else {
                    length = bArr2.length;
                    bArr = new byte[length];
                    i7 = 0;
                }
                while (true) {
                    if (!(i7 < length)) {
                        break;
                    }
                    bArr[i7] = (byte) (bArr2[i7] ^ 7817788349036865294L);
                    i7++;
                }
                bArr2 = bArr;
            }
            if ((bArr2 != null ? 'T' : (char) 0) != 0) {
                i8 = (byte) (((byte) (f55d[i4 + ((int) (f56e ^ 7817788349036865294L))] ^ 7817788349036865294L)) + ((int) (AFLogger ^ 7817788349036865294L)));
            } else {
                i8 = (short) (((short) (v[i4 + ((int) (f56e ^ 7817788349036865294L))] ^ 7817788349036865294L)) + ((int) (AFLogger ^ 7817788349036865294L)));
                int i12 = $10 + 35;
                $11 = i12 % 128;
                int i13 = i12 % 2;
            }
        }
        if (i8 > 0) {
            int i14 = $11 + 125;
            $10 = i14 % 128;
            int i15 = i14 % 2;
            aFj1nSDK.AFInAppEventParameterName = ((i4 + i8) - 2) + ((int) (f56e ^ 7817788349036865294L)) + (z ? 1 : 0);
            aFj1nSDK.valueOf = (char) (((int) (unregisterClient ^ 7817788349036865294L)) + i2);
            sb.append(aFj1nSDK.valueOf);
            aFj1nSDK.values = aFj1nSDK.valueOf;
            byte[] bArr3 = f55d;
            if (bArr3 != null) {
                int length2 = bArr3.length;
                byte[] bArr4 = new byte[length2];
                int i16 = 0;
                while (true) {
                    if ((i16 < length2 ? (char) 0 : 'K') != 0) {
                        break;
                    }
                    bArr4[i16] = (byte) (bArr3[i16] ^ 7817788349036865294L);
                    i16++;
                }
                bArr3 = bArr4;
            }
            boolean z2 = (bArr3 != null ? 'B' : '@') == 'B';
            aFj1nSDK.AFKeystoreWrapper = 1;
            while (aFj1nSDK.AFKeystoreWrapper < i8) {
                if (z2) {
                    byte[] bArr5 = f55d;
                    aFj1nSDK.AFInAppEventParameterName = aFj1nSDK.AFInAppEventParameterName - 1;
                    aFj1nSDK.valueOf = (char) (aFj1nSDK.values + (((byte) (((byte) (bArr5[i6] ^ 7817788349036865294L)) + s)) ^ b2));
                } else {
                    short[] sArr = v;
                    aFj1nSDK.AFInAppEventParameterName = aFj1nSDK.AFInAppEventParameterName - 1;
                    aFj1nSDK.valueOf = (char) (aFj1nSDK.values + (((short) (((short) (sArr[i5] ^ 7817788349036865294L)) + s)) ^ b2));
                }
                sb.append(aFj1nSDK.valueOf);
                aFj1nSDK.values = aFj1nSDK.valueOf;
                aFj1nSDK.AFKeystoreWrapper++;
            }
        }
        objArr[0] = sb.toString();
    }
}
