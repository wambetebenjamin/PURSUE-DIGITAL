package com.bytedance.crash.protector;

import android.os.Build;
import android.util.Pair;
import com.bytedance.apm.constant.p;
import com.bytedance.crash.jni.NativeBridge;
import com.bytedance.crash.util.y;
import com.google.common.base.Ascii;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: CrashProtectorConfig.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6988a = "NPTH_PROT";

    /* renamed from: b  reason: collision with root package name */
    private static final boolean f6989b = true;

    /* renamed from: c  reason: collision with root package name */
    private static final byte[] f6990c = {80, 82, 79, 84};

    /* renamed from: d  reason: collision with root package name */
    private static final byte[] f6991d = {48, 48, 52};

    /* renamed from: e  reason: collision with root package name */
    private static final byte[] f6992e = {120, 86, 52, Ascii.DC2};

    /* renamed from: f  reason: collision with root package name */
    private static final Pattern f6993f = Pattern.compile("^com\\.bytedance(\\.\\w+)*$");

    /* renamed from: g  reason: collision with root package name */
    private ArrayList<String> f6994g;

    /* renamed from: h  reason: collision with root package name */
    private ArrayList<String> f6995h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f6996i;

    /* renamed from: j  reason: collision with root package name */
    private byte[] f6997j = null;
    private int k = 0;
    private boolean l = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CrashProtectorConfig.java */
    /* loaded from: classes2.dex */
    public enum b {
        ELF_BUILD_ID,
        ELF_PATH,
        ELF_NAME
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CrashProtectorConfig.java */
    /* renamed from: com.bytedance.crash.protector.c$c  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public enum EnumC0100c {
        SYMBOL_ALL,
        SYMBOL_OFFSET,
        SYMBOL_NAME
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(JSONObject jSONObject) {
        this.f6996i = false;
        if (a(jSONObject)) {
            this.f6996i = true;
        }
    }

    public boolean a() {
        return this.f6996i;
    }

    public byte[] b() {
        return this.f6997j;
    }

    public int c() {
        return this.k;
    }

    private boolean a(JSONObject jSONObject) {
        JSONObject jSONObject2;
        JSONObject jSONObject3 = null;
        if (jSONObject != null) {
            r1 = jSONObject.optInt(p.SETTING_TRACING_ENABLE) != 0;
            jSONObject3 = jSONObject.optJSONObject("scope_list");
            jSONObject2 = jSONObject.optJSONObject("config_list");
        } else {
            jSONObject2 = null;
        }
        return a(r1, jSONObject3, jSONObject2);
    }

    private boolean a(boolean z, JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (Throwable unused) {
            }
        }
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        jSONObject.put("com.bytedance.__default__", 1);
        jSONObject2.put("__default__.unwindstack.maps", "{ \"scope\": \"com.bytedance.__default__.unwindstack.maps\",\"signals\": [\"SIGSEGV\"], \"backtraces\":[ { \"elf_name\": \"libunwindstack.so\", \"symbol_name\": \"_ZN11unwindstack6Global19FindAndReadVariableEPNS_4MapsEPKc\", \"return_from_current_stack\": 1 }, { \"elf_name\": \"libunwindstack.so\", \"symbol_name\": \"_ZN11unwindstack15GlobalDebugImplINS_7DexFileEmNS_8Uint64_AEE14ForEachSymfileIZNS3_15GetFunctionNameEPNS_4MapsEmPNS_12SharedStringEPmEUlPS1_E_EEbS6_mT_\" }, { \"elf_name\": \"libunwindstack.so\", \"symbol_name\": \"_ZN11unwindstack15GlobalDebugImplINS_7DexFileEmNS_8Uint64_AEE15GetFunctionNameEPNS_4MapsEmPNS_12SharedStringEPm\" }, { \"elf_name\": \"libunwindstack.so\", \"symbol_name\": \"_ZN11unwindstack8Unwinder14FillInDexFrameEv\" } ] } ");
        z = true;
        return z && a(jSONObject, jSONObject2);
    }

    private boolean a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (b(jSONObject)) {
            this.l = NativeBridge.i();
            com.bytedance.crash.protector.a aVar = new com.bytedance.crash.protector.a();
            aVar.a(f6990c);
            aVar.a(f6991d);
            aVar.b(this.l ? 2 : 1);
            aVar.a(f6992e);
            int e2 = aVar.e(0);
            int e3 = aVar.e(0);
            aVar.f(8);
            int c2 = aVar.c();
            int a2 = a(aVar, jSONObject2);
            y.i(f6988a, "config count: " + a2);
            if (a2 <= 0) {
                return false;
            }
            aVar.a(e3, aVar.c() - c2);
            aVar.a(e2, a2);
            a(aVar);
            this.f6997j = aVar.a();
            this.k = aVar.c();
            return true;
        }
        return false;
    }

    private void a(com.bytedance.crash.protector.a aVar) {
        byte[] bArr = new byte[8];
        aVar.f(8);
        byte[] a2 = aVar.a();
        int c2 = aVar.c();
        for (int i2 = 0; i2 < c2; i2 += 8) {
            for (int i3 = 0; i3 < 8; i3++) {
                bArr[i3] = (byte) (bArr[i3] ^ a2[i2 + i3]);
            }
        }
        aVar.a(bArr);
    }

    private boolean b(JSONObject jSONObject) {
        if (jSONObject == null) {
            return false;
        }
        Iterator<String> keys = jSONObject.keys();
        this.f6994g = new ArrayList<>();
        this.f6995h = new ArrayList<>();
        while (true) {
            if (keys.hasNext()) {
                String next = keys.next();
                if (b(next)) {
                    if (jSONObject.optInt(next) != 0) {
                        this.f6994g.add(next);
                    } else {
                        this.f6995h.add(next);
                    }
                }
            } else {
                y.i(f6988a, "white list size: " + this.f6994g.size());
                return !this.f6994g.isEmpty();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x000e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int a(com.bytedance.crash.protector.a r5, org.json.JSONObject r6) {
        /*
            r4 = this;
            r0 = 0
            if (r6 != 0) goto L4
            return r0
        L4:
            java.util.Iterator r1 = r6.keys()
        L8:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L2d
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r2 = r6.optString(r2)
            boolean r3 = r2.isEmpty()
            if (r3 == 0) goto L1f
            goto L8
        L1f:
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L8
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L8
            boolean r2 = r4.b(r5, r3)     // Catch: java.lang.Throwable -> L8
            if (r2 == 0) goto L8
            int r0 = r0 + 1
            goto L8
        L2d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.crash.protector.c.a(com.bytedance.crash.protector.a, org.json.JSONObject):int");
    }

    private boolean b(com.bytedance.crash.protector.a aVar, JSONObject jSONObject) {
        boolean z;
        if (jSONObject == null) {
            return false;
        }
        String optString = jSONObject.optString("scope");
        if (a(optString, this.f6995h, this.f6994g)) {
            int optInt = jSONObject.optInt("min_api_level", 0);
            int optInt2 = jSONObject.optInt("max_api_level", 100);
            if (Build.VERSION.SDK_INT < optInt || Build.VERSION.SDK_INT > optInt2) {
                return false;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("api_levels");
            if (optJSONArray != null) {
                int i2 = 0;
                while (true) {
                    if (i2 >= optJSONArray.length()) {
                        z = false;
                        break;
                    } else if (Build.VERSION.SDK_INT == optJSONArray.optInt(i2)) {
                        z = true;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (!z) {
                    return false;
                }
            }
            a aVar2 = new a();
            aVar2.f6998a = optString;
            String optString2 = jSONObject.optString("abi", "both");
            if (optString2.equals("both") || ((this.l && optString2.equals("arm64-v8a")) || (!this.l && optString2.equals("armeabi-v7a")))) {
                JSONArray optJSONArray2 = jSONObject.optJSONArray("signals");
                if (optJSONArray2 != null) {
                    for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                        int a2 = a(optJSONArray2.optString(i3));
                        if (a2 != 0) {
                            aVar2.f7007j |= 1 << a2;
                        }
                    }
                }
                aVar2.f6999b = jSONObject.optString("abort_message");
                aVar2.f7005h = jSONObject.optInt("max_resume_count", 3);
                aVar2.f7006i = jSONObject.optInt("interval_time_ms", 3000);
                if (jSONObject.optInt("dump_all") != 0) {
                    aVar2.f7000c |= 1;
                }
                int optInt3 = jSONObject.optInt("max_ignore_depth_before_meet_first_match");
                if (optInt3 < 0) {
                    optInt3 = 0;
                } else if (optInt3 > 255) {
                    optInt3 = 255;
                }
                aVar2.f7003f = optInt3;
                if (a(aVar2, jSONObject.optJSONArray("backtraces"))) {
                    a(aVar2, jSONObject.optJSONObject("registers"));
                    aVar2.a(aVar);
                    y.i(f6988a, "add scope: " + optString);
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private int a(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1849149425:
                if (str.equals("SIGBUS")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1849145750:
                if (str.equals("SIGFPE")) {
                    c2 = 1;
                    break;
                }
                break;
            case -1849142984:
                if (str.equals("SIGILL")) {
                    c2 = 2;
                    break;
                }
                break;
            case -1849132964:
                if (str.equals("SIGSYS")) {
                    c2 = 3;
                    break;
                }
                break;
            case -1489105324:
                if (str.equals("SIGABRT")) {
                    c2 = 4;
                    break;
                }
                break;
            case -1488651809:
                if (str.equals("SIGPIPE")) {
                    c2 = 5;
                    break;
                }
                break;
            case -1488566542:
                if (str.equals("SIGSEGV")) {
                    c2 = 6;
                    break;
                }
                break;
            case -1488524450:
                if (str.equals("SIGTRAP")) {
                    c2 = 7;
                    break;
                }
                break;
            case -274378251:
                if (str.equals("SIGSTKFLT")) {
                    c2 = '\b';
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return 7;
            case 1:
                return 8;
            case 2:
                return 4;
            case 3:
                return 31;
            case 4:
                return 6;
            case 5:
                return 13;
            case 6:
                return 11;
            case 7:
                return 5;
            case '\b':
                return 16;
            default:
                return 0;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0052, code lost:
        if (r4 != 's') goto L22;
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x000f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean a(com.bytedance.crash.protector.c.a r13, org.json.JSONObject r14) {
        /*
            r12 = this;
            r0 = 0
            if (r14 != 0) goto L4
            return r0
        L4:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r13.m = r1
            java.util.Iterator r1 = r14.keys()
        Lf:
            boolean r2 = r1.hasNext()
            r3 = 1
            if (r2 == 0) goto Lae
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            int r4 = r2.length()
            r5 = 2
            if (r4 == r5) goto L24
            goto Lf
        L24:
            char r4 = r2.charAt(r0)
            char r4 = java.lang.Character.toLowerCase(r4)
            char r3 = r2.charAt(r3)
            int r3 = r3 + (-48)
            if (r3 >= 0) goto L35
            goto Lf
        L35:
            boolean r5 = r12.l
            r6 = 119(0x77, float:1.67E-43)
            r7 = 114(0x72, float:1.6E-43)
            r8 = 100
            r9 = 115(0x73, float:1.61E-43)
            if (r5 == 0) goto L57
            r5 = 8
            if (r3 >= r5) goto Lf
            r5 = 120(0x78, float:1.68E-43)
            if (r4 == r5) goto L50
            if (r4 == r6) goto L50
            if (r4 == r8) goto L50
            if (r4 == r9) goto L50
            goto Lf
        L50:
            if (r4 == r8) goto L54
            if (r4 != r9) goto L5d
        L54:
            int r3 = r3 + 34
            goto L5d
        L57:
            r5 = 4
            if (r3 >= r5) goto Lf
            if (r4 == r7) goto L5d
            goto Lf
        L5d:
            java.lang.Object r5 = r14.opt(r2)
            java.lang.Long r5 = a(r5)
            if (r5 == 0) goto L6c
            long r10 = r5.longValue()
            goto L88
        L6c:
            if (r4 != r8) goto L77
            double r10 = r14.optDouble(r2)
            long r10 = java.lang.Double.doubleToLongBits(r10)
            goto L88
        L77:
            if (r4 != r9) goto L84
            double r10 = r14.optDouble(r2)
            float r2 = (float) r10
            int r2 = java.lang.Float.floatToIntBits(r2)
            long r10 = (long) r2
            goto L88
        L84:
            long r10 = r14.optLong(r2)
        L88:
            if (r4 == r6) goto L8e
            if (r4 == r9) goto L8e
            if (r4 != r7) goto L94
        L8e:
            r4 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r10 = r10 & r4
        L94:
            r4 = 0
            int r2 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r2 == 0) goto Lf
            java.util.ArrayList<android.util.Pair<java.lang.Integer, java.lang.Long>> r2 = r13.m
            android.util.Pair r4 = new android.util.Pair
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.Long r5 = java.lang.Long.valueOf(r10)
            r4.<init>(r3, r5)
            r2.add(r4)
            goto Lf
        Lae:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.crash.protector.c.a(com.bytedance.crash.protector.c$a, org.json.JSONObject):boolean");
    }

    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v14 */
    private boolean a(a aVar, JSONArray jSONArray) {
        b bVar;
        byte[] bytes;
        boolean z;
        JSONArray jSONArray2 = jSONArray;
        ?? r2 = 0;
        if (jSONArray2 == null) {
            return false;
        }
        int length = jSONArray.length();
        int i2 = 255;
        if (length > 255) {
            length = 255;
        }
        aVar.f7004g = length;
        aVar.l = new com.bytedance.crash.protector.a();
        com.bytedance.crash.protector.a aVar2 = aVar.l;
        aVar.k = new com.bytedance.crash.protector.a();
        com.bytedance.crash.protector.a aVar3 = aVar.k;
        int i3 = -1;
        int i4 = 0;
        int i5 = -1;
        while (i4 < length) {
            JSONObject optJSONObject = jSONArray2.optJSONObject(i4);
            if (i5 == i3 && optJSONObject.optInt("return_from_current_stack") != 0) {
                i5 = i4;
            }
            String optString = optJSONObject.optString("elf_build_id");
            String optString2 = optJSONObject.optString("elf_path");
            String optString3 = optJSONObject.optString("elf_name");
            if (!optString.isEmpty()) {
                optString = optString.replace(" ", "");
                int length2 = optString.length() / 2;
                bytes = new byte[length2];
                for (int i6 = r2; i6 < length2; i6++) {
                    int i7 = i6 * 2;
                    try {
                        bytes[i6] = (byte) (Integer.parseInt(optString.substring(i7, i7 + 2), 16) & i2);
                    } catch (Throwable unused) {
                        return r2;
                    }
                }
                bVar = b.ELF_BUILD_ID;
            } else if (!optString2.isEmpty()) {
                if (optString2.charAt(r2) != '/') {
                    return r2;
                }
                bVar = b.ELF_PATH;
                bytes = optString2.getBytes();
            } else if (optString3.isEmpty()) {
                return r2;
            } else {
                bVar = b.ELF_NAME;
                bytes = optString3.getBytes();
            }
            if (bytes == null || bytes.length >= 256) {
                return r2;
            }
            byte[] a2 = aVar3.a();
            int i8 = r2;
            while (i8 < aVar3.c()) {
                byte b2 = a2[i8];
                byte b3 = a2[i8 + 1];
                if (b2 == bVar.ordinal() && b3 == bytes.length) {
                    int i9 = 0;
                    while (true) {
                        if (i9 >= bytes.length) {
                            z = true;
                            break;
                        } else if (a2[i8 + 2 + i9] != bytes[i9]) {
                            z = false;
                            break;
                        } else {
                            i9++;
                        }
                    }
                    if (z) {
                        break;
                    }
                }
                i8 += b3 + 3;
            }
            if (i8 > aVar3.c()) {
                return false;
            }
            if (i8 == aVar3.c()) {
                aVar.f7001d++;
                aVar3.b(bVar.ordinal());
                aVar3.b(bytes.length);
                aVar3.a(bytes);
                aVar3.b(0);
            }
            aVar2.a(i8);
            if (optJSONObject.optInt("redacted_match") != 0) {
                aVar2.b(EnumC0100c.SYMBOL_ALL.ordinal());
            } else if (optJSONObject.has("offset_start")) {
                if (optString.isEmpty()) {
                    return false;
                }
                Long a3 = a(optJSONObject.opt("offset_start"));
                if (a3 == null) {
                    a3 = Long.valueOf(optJSONObject.optLong("offset_start"));
                }
                Long a4 = a(optJSONObject.opt("offset_next"));
                if (a4 == null) {
                    a4 = Long.valueOf(optJSONObject.optLong("offset_next"));
                }
                if (a3.longValue() == 0 || a4.longValue() == 0 || a3.longValue() >= a4.longValue()) {
                    return false;
                }
                aVar2.b(EnumC0100c.SYMBOL_OFFSET.ordinal());
                aVar2.a(a3.longValue());
                aVar2.a(a4.longValue());
            } else {
                String optString4 = optJSONObject.optString("symbol_name");
                if (optString4.isEmpty()) {
                    return false;
                }
                aVar2.b(EnumC0100c.SYMBOL_NAME.ordinal());
                aVar2.a(optString4);
            }
            i4++;
            jSONArray2 = jSONArray;
            r2 = 0;
            i2 = 255;
            i3 = -1;
        }
        boolean z2 = r2;
        if (i5 == i3) {
            return z2;
        }
        aVar.f7002e = i5;
        return true;
    }

    private static Long a(Object obj) {
        if (obj != null && (obj instanceof String)) {
            String str = (String) obj;
            if (str.isEmpty()) {
                return null;
            }
            if (str.startsWith("0x") || str.startsWith("0X")) {
                try {
                    return Long.valueOf(Long.parseLong(str.substring(2), 16));
                } catch (NumberFormatException unused) {
                }
            }
            return null;
        }
        return null;
    }

    private static boolean a(String str, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        if (b(str)) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                if (str.startsWith(arrayList.get(i2)) && (arrayList.get(i2).length() == str.length() || str.charAt(arrayList.get(i2).length()) == '.')) {
                    return false;
                }
            }
            for (int i3 = 0; i3 < arrayList2.size(); i3++) {
                if (str.startsWith(arrayList2.get(i3)) && (arrayList2.get(i3).length() == str.length() || str.charAt(arrayList2.get(i3).length()) == '.')) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private static boolean b(String str) {
        return str.length() < 256 && f6993f.matcher(str).matches();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CrashProtectorConfig.java */
    /* loaded from: classes2.dex */
    public class a {

        /* renamed from: a  reason: collision with root package name */
        String f6998a;

        /* renamed from: b  reason: collision with root package name */
        String f6999b;
        com.bytedance.crash.protector.a k;
        com.bytedance.crash.protector.a l;

        /* renamed from: c  reason: collision with root package name */
        int f7000c = 0;

        /* renamed from: d  reason: collision with root package name */
        int f7001d = 0;

        /* renamed from: e  reason: collision with root package name */
        int f7002e = 0;

        /* renamed from: f  reason: collision with root package name */
        int f7003f = 0;

        /* renamed from: g  reason: collision with root package name */
        int f7004g = 0;

        /* renamed from: h  reason: collision with root package name */
        int f7005h = 0;

        /* renamed from: i  reason: collision with root package name */
        int f7006i = 0;

        /* renamed from: j  reason: collision with root package name */
        long f7007j = 0;
        ArrayList<Pair<Integer, Long>> m = null;

        a() {
        }

        public void a(com.bytedance.crash.protector.a aVar) {
            aVar.f(8);
            int c2 = aVar.c();
            int i2 = 0;
            int e2 = aVar.e(0);
            aVar.d(this.f7000c);
            aVar.b(this.f6998a.getBytes().length);
            aVar.b(this.f7001d);
            aVar.b(this.f7002e);
            aVar.b(this.f7003f);
            aVar.b(this.f7004g);
            ArrayList<Pair<Integer, Long>> arrayList = this.m;
            aVar.b(arrayList == null ? 0 : arrayList.size());
            aVar.d(this.f7005h);
            aVar.d(this.f7006i);
            aVar.b(this.f7007j);
            int e3 = aVar.e(0);
            int e4 = aVar.e(0);
            int e5 = aVar.e(0);
            int e6 = aVar.e(0);
            int e7 = aVar.e(0);
            int e8 = aVar.e(0);
            aVar.a(e3, aVar.c() - c2);
            aVar.a(this.f6998a.getBytes());
            aVar.b(0);
            String str = this.f6999b;
            if (str != null && !str.isEmpty()) {
                aVar.a(e4, aVar.c() - c2);
                aVar.a(this.f6999b.getBytes());
                aVar.b(0);
            }
            aVar.a(e5, aVar.c() - c2);
            aVar.a(this.k.a(), 0, this.k.c());
            aVar.a(e6, aVar.c() - c2);
            aVar.a(this.l.a(), 0, this.l.c());
            ArrayList<Pair<Integer, Long>> arrayList2 = this.m;
            if (arrayList2 != null && !arrayList2.isEmpty()) {
                aVar.a(e7, aVar.c() - c2);
                for (int i3 = 0; i3 < this.m.size(); i3++) {
                    aVar.b(((Integer) this.m.get(i3).first).intValue());
                }
                if (c.this.l) {
                    aVar.f(8);
                    aVar.a(e8, aVar.c() - c2);
                    while (i2 < this.m.size()) {
                        aVar.b(((Long) this.m.get(i2).second).longValue());
                        i2++;
                    }
                } else {
                    aVar.f(4);
                    aVar.a(e8, aVar.c() - c2);
                    while (i2 < this.m.size()) {
                        aVar.d(((Long) this.m.get(i2).second).intValue());
                        i2++;
                    }
                }
            }
            aVar.f(8);
            aVar.a(e2, aVar.c() - c2);
        }
    }
}
