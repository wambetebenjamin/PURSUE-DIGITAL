package com.bytedance.crash.dumper;

import android.text.TextUtils;
import com.bytedance.crash.jni.NativeBridge;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: BuildId.java */
/* loaded from: classes2.dex */
public class c {
    public static void a(JSONObject jSONObject, File file, boolean z) {
        String j2;
        HashMap hashMap = new HashMap();
        ArrayList<String> arrayList = new ArrayList();
        if (z) {
            a(hashMap, new File(file, "tombstone.txt"));
            new C0092c(new File(file, "nstack.txt")).a(arrayList);
        }
        new d(new File(file, "rountines.txt")).a(arrayList);
        for (String str : arrayList) {
            if (!c(str) && !hashMap.containsKey(str) && (j2 = NativeBridge.j(str)) != null) {
                hashMap.put(str, j2);
            }
        }
        a(jSONObject, hashMap);
    }

    public static void a(JSONObject jSONObject, File file) {
        String j2;
        HashMap hashMap = new HashMap();
        ArrayList<String> arrayList = new ArrayList();
        new b(file).a(arrayList);
        for (String str : arrayList) {
            if (!c(str) && !hashMap.containsKey(str) && (j2 = NativeBridge.j(str)) != null) {
                hashMap.put(str, j2);
            }
        }
        a(jSONObject, hashMap);
    }

    public static void b(JSONObject jSONObject, File file) {
        String j2;
        HashMap hashMap = new HashMap();
        ArrayList<String> arrayList = new ArrayList();
        new a(file).a(arrayList);
        for (String str : arrayList) {
            if (!c(str) && !hashMap.containsKey(str) && (j2 = NativeBridge.j(str)) != null) {
                hashMap.put(str, j2);
            }
        }
        a(jSONObject, hashMap);
    }

    private static void a(JSONObject jSONObject, Map<String, String> map) {
        JSONArray jSONArray = new JSONArray();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            try {
                String a2 = a(entry.getKey());
                if (!TextUtils.isEmpty(a2)) {
                    JSONObject jSONObject2 = new JSONObject();
                    com.bytedance.crash.util.q.a(jSONObject2, com.bytedance.crash.entity.b.LIB_NAME, (Object) a2);
                    com.bytedance.crash.util.q.a(jSONObject2, com.bytedance.crash.entity.b.LIB_UUID, (Object) b(entry.getValue()));
                    jSONArray.put(jSONObject2);
                }
            } catch (Throwable unused) {
            }
        }
        com.bytedance.crash.util.q.a(jSONObject, com.bytedance.crash.entity.b.CRASH_LIB_UUID, jSONArray);
    }

    private static String a(String str) {
        int lastIndexOf;
        if (TextUtils.isEmpty(str) || (lastIndexOf = str.lastIndexOf(47)) == -1) {
            return null;
        }
        String substring = str.substring(lastIndexOf + 1);
        int indexOf = substring.indexOf(".apk!");
        return indexOf != -1 ? substring.substring(indexOf + 5) : substring;
    }

    private static String b(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            if (str.length() < 16) {
                sb.append(str);
            } else {
                sb.append(str.charAt(6));
                sb.append(str.charAt(7));
                sb.append(str.charAt(4));
                sb.append(str.charAt(5));
                sb.append(str.charAt(2));
                sb.append(str.charAt(3));
                sb.append(str.charAt(0));
                sb.append(str.charAt(1));
                sb.append(str.charAt(10));
                sb.append(str.charAt(11));
                sb.append(str.charAt(8));
                sb.append(str.charAt(9));
                sb.append(str.charAt(14));
                sb.append(str.charAt(15));
                sb.append(str.charAt(12));
                sb.append(str.charAt(13));
                if (str.length() >= 32) {
                    sb.append((CharSequence) str, 16, 32);
                    sb.append('0');
                }
            }
        } catch (Throwable unused) {
        }
        return sb.toString().toUpperCase();
    }

    private static boolean c(String str) {
        return str.startsWith("/system/lib") || str.startsWith("/vendor/lib") || str.startsWith("/apex/");
    }

    private static void a(Map<String, String> map, File file) {
        BufferedReader bufferedReader;
        if (file.exists()) {
            Pattern compile = Pattern.compile("^ {4}(/(?:[^/]+/)+[^.]+\\.so) \\(BuildId: ([0-9a-f]+)\\).*");
            BufferedReader bufferedReader2 = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        } else if (readLine.startsWith("build id:")) {
                            while (true) {
                                String readLine2 = bufferedReader.readLine();
                                if (readLine2 != null && readLine2.startsWith("    /")) {
                                    Matcher matcher = compile.matcher(readLine2);
                                    if (matcher.find() && matcher.groupCount() == 2) {
                                        String group = matcher.group(1);
                                        String group2 = matcher.group(2);
                                        if (group != null && !c(group) && !map.containsKey(group)) {
                                            map.put(group, group2);
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Throwable unused) {
                        bufferedReader2 = bufferedReader;
                        bufferedReader = bufferedReader2;
                        com.bytedance.crash.util.k.a(bufferedReader);
                    }
                }
            } catch (Throwable unused2) {
            }
            com.bytedance.crash.util.k.a(bufferedReader);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BuildId.java */
    /* loaded from: classes2.dex */
    public static abstract class e {

        /* renamed from: a  reason: collision with root package name */
        private final File f6506a;

        /* renamed from: b  reason: collision with root package name */
        private final Pattern f6507b = a();

        abstract Pattern a();

        abstract boolean a(String str);

        e(File file) {
            this.f6506a = file;
        }

        void a(List<String> list) {
            BufferedReader bufferedReader;
            if (this.f6506a.exists()) {
                BufferedReader bufferedReader2 = null;
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.f6506a)));
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            } else if (a(readLine)) {
                                Matcher matcher = this.f6507b.matcher(readLine);
                                if (matcher.find() && matcher.groupCount() == 1) {
                                    String group = matcher.group(1);
                                    if (!list.contains(group)) {
                                        list.add(group);
                                    }
                                }
                            }
                        } catch (Throwable unused) {
                            bufferedReader2 = bufferedReader;
                            bufferedReader = bufferedReader2;
                            com.bytedance.crash.util.k.a(bufferedReader);
                        }
                    }
                } catch (Throwable unused2) {
                }
                com.bytedance.crash.util.k.a(bufferedReader);
            }
        }
    }

    /* compiled from: BuildId.java */
    /* loaded from: classes2.dex */
    static class f extends e {
        f(File file) {
            super(file);
        }

        @Override // com.bytedance.crash.dumper.c.e
        Pattern a() {
            return Pattern.compile("^ {4}#\\d{1,3} pc [0-9a-f]{8,16} {2}(/(?:[^/]+/)+.*\\.so).*");
        }

        @Override // com.bytedance.crash.dumper.c.e
        boolean a(String str) {
            return str.startsWith("    ");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BuildId.java */
    /* renamed from: com.bytedance.crash.dumper.c$c  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class C0092c extends e {
        C0092c(File file) {
            super(file);
        }

        @Override // com.bytedance.crash.dumper.c.e
        Pattern a() {
            return Pattern.compile("^#\\d{1,3} pc [0-9a-f]{8,16} {2}(/(?:[^/]+/)+.*\\.so).*");
        }

        @Override // com.bytedance.crash.dumper.c.e
        boolean a(String str) {
            return str.startsWith("#");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BuildId.java */
    /* loaded from: classes2.dex */
    public static class d extends e {
        d(File file) {
            super(file);
        }

        @Override // com.bytedance.crash.dumper.c.e
        Pattern a() {
            return Pattern.compile("^0x[0-9a-f]{1,16}:(/(?:[^/]+/)+[^.]+\\.so):.*");
        }

        @Override // com.bytedance.crash.dumper.c.e
        boolean a(String str) {
            return str.startsWith("0x");
        }
    }

    /* compiled from: BuildId.java */
    /* loaded from: classes2.dex */
    static class b extends e {
        b(File file) {
            super(file);
        }

        @Override // com.bytedance.crash.dumper.c.e
        Pattern a() {
            return Pattern.compile("^ {4}#\\d{1,3} 0x[0-9a-f]{1,16} {2}\\((/(?:[^/]+/)+.*\\.so)\\+0x[0-9a-f]{1,16}\\)$");
        }

        @Override // com.bytedance.crash.dumper.c.e
        boolean a(String str) {
            return str.startsWith("    #");
        }
    }

    /* compiled from: BuildId.java */
    /* loaded from: classes2.dex */
    static class a extends e {
        a(File file) {
            super(file);
        }

        @Override // com.bytedance.crash.dumper.c.e
        Pattern a() {
            return Pattern.compile("^  native: #\\d{1,3} pc [0-9a-f]{8,16} {2}(/(?:[^/]+/)+.*\\.so).*");
        }

        @Override // com.bytedance.crash.dumper.c.e
        boolean a(String str) {
            return str.startsWith("  native: #");
        }
    }
}
