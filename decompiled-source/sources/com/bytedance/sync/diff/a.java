package com.bytedance.sync.diff;

import com.bytedance.librarian.c;
import com.facebook.internal.security.CertificateUtil;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.meituan.robust.Constants;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.text.Typography;
/* compiled from: DiffMatchPatch.java */
/* loaded from: classes3.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f13420a = true;
    public float Diff_Timeout = 1.0f;
    public short Diff_EditCost = 4;
    public float Match_Threshold = 0.5f;
    public int Match_Distance = 1000;
    public float Patch_DeleteThreshold = 0.5f;
    public short Patch_Margin = 4;

    /* renamed from: b  reason: collision with root package name */
    private short f13421b = 32;

    /* renamed from: c  reason: collision with root package name */
    private Pattern f13422c = Pattern.compile("\\n\\r?\\n\\Z", 32);

    /* renamed from: d  reason: collision with root package name */
    private Pattern f13423d = Pattern.compile("\\A\\r?\\n\\r?\\n", 32);

    /* compiled from: DiffMatchPatch.java */
    /* loaded from: classes3.dex */
    public enum c {
        DELETE,
        INSERT,
        EQUAL
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* compiled from: DiffMatchPatch.java */
    /* loaded from: classes3.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        protected String f13425a;

        /* renamed from: b  reason: collision with root package name */
        protected String f13426b;

        /* renamed from: c  reason: collision with root package name */
        protected List<String> f13427c;

        protected b(String str, String str2, List<String> list) {
            this.f13425a = str;
            this.f13426b = str2;
            this.f13427c = list;
        }
    }

    public LinkedList<C0205a> diff_main(String str, String str2) {
        return diff_main(str, str2, true);
    }

    public LinkedList<C0205a> diff_main(String str, String str2, boolean z) {
        return a(str, str2, z, this.Diff_Timeout <= 0.0f ? Long.MAX_VALUE : System.currentTimeMillis() + (this.Diff_Timeout * 1000.0f));
    }

    private LinkedList<C0205a> a(String str, String str2, boolean z, long j2) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException("Null inputs. (diff_main)");
        }
        if (str.equals(str2)) {
            LinkedList<C0205a> linkedList = new LinkedList<>();
            if (str.length() != 0) {
                linkedList.add(new C0205a(c.EQUAL, str));
            }
            return linkedList;
        }
        int diff_commonPrefix = diff_commonPrefix(str, str2);
        String substring = str.substring(0, diff_commonPrefix);
        String substring2 = str.substring(diff_commonPrefix);
        String substring3 = str2.substring(diff_commonPrefix);
        int diff_commonSuffix = diff_commonSuffix(substring2, substring3);
        String substring4 = substring2.substring(substring2.length() - diff_commonSuffix);
        LinkedList<C0205a> b2 = b(substring2.substring(0, substring2.length() - diff_commonSuffix), substring3.substring(0, substring3.length() - diff_commonSuffix), z, j2);
        if (substring.length() != 0) {
            b2.addFirst(new C0205a(c.EQUAL, substring));
        }
        if (substring4.length() != 0) {
            b2.addLast(new C0205a(c.EQUAL, substring4));
        }
        diff_cleanupMerge(b2);
        return b2;
    }

    private LinkedList<C0205a> b(String str, String str2, boolean z, long j2) {
        LinkedList<C0205a> linkedList = new LinkedList<>();
        if (str.length() == 0) {
            linkedList.add(new C0205a(c.INSERT, str2));
            return linkedList;
        } else if (str2.length() == 0) {
            linkedList.add(new C0205a(c.DELETE, str));
            return linkedList;
        } else {
            String str3 = str.length() > str2.length() ? str : str2;
            String str4 = str.length() > str2.length() ? str2 : str;
            int indexOf = str3.indexOf(str4);
            if (indexOf != -1) {
                c cVar = str.length() > str2.length() ? c.DELETE : c.INSERT;
                linkedList.add(new C0205a(cVar, str3.substring(0, indexOf)));
                linkedList.add(new C0205a(c.EQUAL, str4));
                linkedList.add(new C0205a(cVar, str3.substring(indexOf + str4.length())));
                return linkedList;
            } else if (str4.length() == 1) {
                linkedList.add(new C0205a(c.DELETE, str));
                linkedList.add(new C0205a(c.INSERT, str2));
                return linkedList;
            } else {
                String[] c2 = c(str, str2);
                if (c2 != null) {
                    String str5 = c2[0];
                    String str6 = c2[1];
                    String str7 = c2[2];
                    String str8 = c2[3];
                    String str9 = c2[4];
                    LinkedList<C0205a> a2 = a(str5, str7, z, j2);
                    LinkedList<C0205a> a3 = a(str6, str8, z, j2);
                    a2.add(new C0205a(c.EQUAL, str9));
                    a2.addAll(a3);
                    return a2;
                } else if (z && str.length() > 100 && str2.length() > 100) {
                    return b(str, str2, j2);
                } else {
                    return a(str, str2, j2);
                }
            }
        }
    }

    private LinkedList<C0205a> b(String str, String str2, long j2) {
        b a2 = a(str, str2);
        String str3 = a2.f13425a;
        String str4 = a2.f13426b;
        List<String> list = a2.f13427c;
        LinkedList<C0205a> a3 = a(str3, str4, false, j2);
        a(a3, list);
        diff_cleanupSemantic(a3);
        a3.add(new C0205a(c.EQUAL, ""));
        ListIterator<C0205a> listIterator = a3.listIterator();
        C0205a next = listIterator.next();
        String str5 = "";
        String str6 = str5;
        int i2 = 0;
        int i3 = 0;
        while (next != null) {
            int i4 = AnonymousClass1.f13424a[next.operation.ordinal()];
            if (i4 == 1) {
                i2++;
                str6 = str6 + next.text;
            } else if (i4 == 2) {
                i3++;
                str5 = str5 + next.text;
            } else if (i4 == 3) {
                if (i3 >= 1 && i2 >= 1) {
                    listIterator.previous();
                    for (int i5 = 0; i5 < i3 + i2; i5++) {
                        listIterator.previous();
                        listIterator.remove();
                    }
                    Iterator<C0205a> it = a(str5, str6, false, j2).iterator();
                    while (it.hasNext()) {
                        listIterator.add(it.next());
                    }
                }
                str5 = "";
                str6 = str5;
                i2 = 0;
                i3 = 0;
            }
            next = listIterator.hasNext() ? listIterator.next() : null;
        }
        a3.removeLast();
        return a3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DiffMatchPatch.java */
    /* renamed from: com.bytedance.sync.diff.a$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f13424a;

        static {
            int[] iArr = new int[c.values().length];
            f13424a = iArr;
            try {
                iArr[c.INSERT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13424a[c.DELETE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f13424a[c.EQUAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0061, code lost:
        if (r7[r14 - 1] < r7[r14 + 1]) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00f1, code lost:
        if (r20[r1 - 1] < r20[r1 + 1]) goto L102;
     */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0094 A[EDGE_INSN: B:105:0x0094->B:35:0x0094 ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0134 A[EDGE_INSN: B:112:0x0134->B:72:0x0134 ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0089 A[LOOP:3: B:29:0x0079->B:33:0x0089, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0125 A[LOOP:5: B:66:0x0107->B:70:0x0125, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x013c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected java.util.LinkedList<com.bytedance.sync.diff.a.C0205a> a(java.lang.String r25, java.lang.String r26, long r27) {
        /*
            Method dump skipped, instructions count: 419
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sync.diff.a.a(java.lang.String, java.lang.String, long):java.util.LinkedList");
    }

    private LinkedList<C0205a> a(String str, String str2, int i2, int i3, long j2) {
        String substring = str.substring(0, i2);
        String substring2 = str2.substring(0, i3);
        String substring3 = str.substring(i2);
        String substring4 = str2.substring(i3);
        LinkedList<C0205a> a2 = a(substring, substring2, false, j2);
        a2.addAll(a(substring3, substring4, false, j2));
        return a2;
    }

    protected b a(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        arrayList.add("");
        return new b(a(str, arrayList, hashMap, 40000), a(str2, arrayList, hashMap, 65535), arrayList);
    }

    private String a(String str, List<String> list, Map<String, Integer> map, int i2) {
        StringBuilder sb = new StringBuilder();
        int i3 = 0;
        int i4 = -1;
        while (i4 < str.length() - 1) {
            i4 = str.indexOf(10, i3);
            if (i4 == -1) {
                i4 = str.length() - 1;
            }
            String substring = str.substring(i3, i4 + 1);
            if (map.containsKey(substring)) {
                sb.append(String.valueOf((char) map.get(substring).intValue()));
            } else {
                if (list.size() == i2) {
                    substring = str.substring(i3);
                    i4 = str.length();
                }
                list.add(substring);
                map.put(substring, Integer.valueOf(list.size() - 1));
                sb.append(String.valueOf((char) (list.size() - 1)));
            }
            i3 = i4 + 1;
        }
        return sb.toString();
    }

    protected void a(List<C0205a> list, List<String> list2) {
        for (C0205a c0205a : list) {
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < c0205a.text.length(); i2++) {
                sb.append(list2.get(c0205a.text.charAt(i2)));
            }
            c0205a.text = sb.toString();
        }
    }

    public int diff_commonPrefix(String str, String str2) {
        int min = Math.min(str.length(), str2.length());
        for (int i2 = 0; i2 < min; i2++) {
            if (str.charAt(i2) != str2.charAt(i2)) {
                return i2;
            }
        }
        return min;
    }

    public int diff_commonSuffix(String str, String str2) {
        int length = str.length();
        int length2 = str2.length();
        int min = Math.min(length, length2);
        for (int i2 = 1; i2 <= min; i2++) {
            if (str.charAt(length - i2) != str2.charAt(length2 - i2)) {
                return i2 - 1;
            }
        }
        return min;
    }

    protected int b(String str, String str2) {
        int length = str.length();
        int length2 = str2.length();
        if (length == 0 || length2 == 0) {
            return 0;
        }
        if (length > length2) {
            str = str.substring(length - length2);
        } else if (length < length2) {
            str2 = str2.substring(0, length);
        }
        int min = Math.min(length, length2);
        if (str.equals(str2)) {
            return min;
        }
        int i2 = 1;
        int i3 = 0;
        while (true) {
            int indexOf = str2.indexOf(str.substring(min - i2));
            if (indexOf == -1) {
                return i3;
            }
            i2 += indexOf;
            if (indexOf == 0 || str.substring(min - i2).equals(str2.substring(0, i2))) {
                i3 = i2;
                i2++;
            }
        }
    }

    protected String[] c(String str, String str2) {
        if (this.Diff_Timeout <= 0.0f) {
            return null;
        }
        String str3 = str.length() > str2.length() ? str : str2;
        String str4 = str.length() > str2.length() ? str2 : str;
        if (str3.length() < 4 || str4.length() * 2 < str3.length()) {
            return null;
        }
        String[] b2 = b(str3, str4, (str3.length() + 3) / 4);
        String[] b3 = b(str3, str4, (str3.length() + 1) / 2);
        if (b2 == null && b3 == null) {
            return null;
        }
        if (b3 != null && (b2 == null || b2[4].length() <= b3[4].length())) {
            b2 = b3;
        }
        return str.length() > str2.length() ? b2 : new String[]{b2[2], b2[3], b2[0], b2[1], b2[4]};
    }

    private String[] b(String str, String str2, int i2) {
        int i3;
        String substring = str.substring(i2, (str.length() / 4) + i2);
        int i4 = -1;
        String str3 = "";
        int i5 = -1;
        String str4 = "";
        String str5 = str4;
        String str6 = str5;
        String str7 = str6;
        while (true) {
            i5 = str2.indexOf(substring, i5 + 1);
            if (i5 == i4) {
                break;
            }
            int diff_commonPrefix = diff_commonPrefix(str.substring(i2), str2.substring(i5));
            int diff_commonSuffix = diff_commonSuffix(str.substring(0, i2), str2.substring(0, i5));
            if (str3.length() < diff_commonSuffix + diff_commonPrefix) {
                int i6 = i5 - diff_commonSuffix;
                String substring2 = str.substring(0, i2 - diff_commonSuffix);
                str5 = str.substring(i2 + diff_commonPrefix);
                str6 = str2.substring(0, i6);
                str7 = str2.substring(i5 + diff_commonPrefix);
                str3 = str2.substring(i6, i5) + str2.substring(i5, i3);
                str4 = substring2;
            }
            i4 = -1;
        }
        if (str3.length() * 2 >= str.length()) {
            return new String[]{str4, str5, str6, str7, str3};
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00eb  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:46:0x00e5 -> B:48:0x00e9). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void diff_cleanupSemantic(java.util.LinkedList<com.bytedance.sync.diff.a.C0205a> r15) {
        /*
            Method dump skipped, instructions count: 413
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sync.diff.a.diff_cleanupSemantic(java.util.LinkedList):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0129 A[LOOP:0: B:14:0x002f->B:45:0x0129, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x002e A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x002e -> B:14:0x002f). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void diff_cleanupSemanticLossless(java.util.LinkedList<com.bytedance.sync.diff.a.C0205a> r15) {
        /*
            Method dump skipped, instructions count: 306
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sync.diff.a.diff_cleanupSemanticLossless(java.util.LinkedList):void");
    }

    private int d(String str, String str2) {
        if (str.length() == 0 || str2.length() == 0) {
            return 6;
        }
        char charAt = str.charAt(str.length() - 1);
        char charAt2 = str2.charAt(0);
        boolean z = !Character.isLetterOrDigit(charAt);
        boolean z2 = !Character.isLetterOrDigit(charAt2);
        boolean z3 = z && Character.isWhitespace(charAt);
        boolean z4 = z2 && Character.isWhitespace(charAt2);
        boolean z5 = z3 && Character.getType(charAt) == 15;
        boolean z6 = z4 && Character.getType(charAt2) == 15;
        boolean z7 = z5 && this.f13422c.matcher(str).find();
        boolean z8 = z6 && this.f13423d.matcher(str2).find();
        if (z7 || z8) {
            return 5;
        }
        if (z5 || z6) {
            return 4;
        }
        if (z && !z3 && z4) {
            return 3;
        }
        if (z3 || z4) {
            return 2;
        }
        return (z || z2) ? 1 : 0;
    }

    public void diff_cleanupEfficiency(LinkedList<C0205a> linkedList) {
        int i2;
        if (linkedList.isEmpty()) {
            return;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        ListIterator<C0205a> listIterator = linkedList.listIterator();
        C0205a next = listIterator.next();
        C0205a c0205a = next;
        String str = null;
        boolean z = false;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (next != null) {
            if (next.operation == c.EQUAL) {
                if (next.text.length() < this.Diff_EditCost && (i3 != 0 || i4 != 0)) {
                    arrayDeque.push(next);
                    str = next.text;
                    next = c0205a;
                } else {
                    arrayDeque.clear();
                    str = null;
                    i3 = i5;
                    i4 = i6;
                }
                c0205a = next;
                i5 = i3;
                i6 = i4;
                i3 = 0;
                i4 = 0;
            } else {
                if (next.operation == c.DELETE) {
                    i4 = 1;
                } else {
                    i3 = 1;
                }
                if (str != null && ((i5 != 0 && i6 != 0 && i3 != 0 && i4 != 0) || (str.length() < this.Diff_EditCost / 2 && i5 + i6 + i3 + i4 == 3))) {
                    while (next != arrayDeque.peek()) {
                        next = listIterator.previous();
                    }
                    listIterator.next();
                    listIterator.set(new C0205a(c.DELETE, str));
                    C0205a c0205a2 = new C0205a(c.INSERT, str);
                    listIterator.add(c0205a2);
                    arrayDeque.pop();
                    if (i5 != 0 && i6 != 0) {
                        arrayDeque.clear();
                        c0205a = c0205a2;
                        i2 = 1;
                    } else {
                        if (!arrayDeque.isEmpty()) {
                            arrayDeque.pop();
                        }
                        do {
                        } while ((arrayDeque.isEmpty() ? c0205a : (C0205a) arrayDeque.peek()) != listIterator.previous());
                        i2 = 0;
                    }
                    i3 = i2;
                    i4 = i3;
                    str = null;
                    z = true;
                }
            }
            next = listIterator.hasNext() ? listIterator.next() : null;
        }
        if (z) {
            diff_cleanupMerge(linkedList);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x0188 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0180 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x02af A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02a7 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void diff_cleanupMerge(java.util.LinkedList<com.bytedance.sync.diff.a.C0205a> r15) {
        /*
            Method dump skipped, instructions count: 696
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sync.diff.a.diff_cleanupMerge(java.util.LinkedList):void");
    }

    public int diff_xIndex(List<C0205a> list, int i2) {
        C0205a c0205a;
        Iterator<C0205a> it = list.iterator();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (true) {
            if (!it.hasNext()) {
                c0205a = null;
                break;
            }
            c0205a = it.next();
            if (c0205a.operation != c.INSERT) {
                i3 += c0205a.text.length();
            }
            if (c0205a.operation != c.DELETE) {
                i4 += c0205a.text.length();
            }
            if (i3 > i2) {
                break;
            }
            i5 = i3;
            i6 = i4;
        }
        return (c0205a == null || c0205a.operation != c.DELETE) ? i6 + (i2 - i5) : i6;
    }

    public String diff_prettyHtml(List<C0205a> list) {
        StringBuilder sb = new StringBuilder();
        for (C0205a c0205a : list) {
            String replace = c0205a.text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, "&para;<br>");
            int i2 = AnonymousClass1.f13424a[c0205a.operation.ordinal()];
            if (i2 == 1) {
                sb.append("<ins style=\"background:#e6ffe6;\">").append(replace).append("</ins>");
            } else if (i2 == 2) {
                sb.append("<del style=\"background:#ffe6e6;\">").append(replace).append("</del>");
            } else if (i2 == 3) {
                sb.append("<span>").append(replace).append("</span>");
            }
        }
        return sb.toString();
    }

    public String diff_text1(List<C0205a> list) {
        StringBuilder sb = new StringBuilder();
        for (C0205a c0205a : list) {
            if (c0205a.operation != c.INSERT) {
                sb.append(c0205a.text);
            }
        }
        return sb.toString();
    }

    public String diff_text2(List<C0205a> list) {
        StringBuilder sb = new StringBuilder();
        for (C0205a c0205a : list) {
            if (c0205a.operation != c.DELETE) {
                sb.append(c0205a.text);
            }
        }
        return sb.toString();
    }

    public int diff_levenshtein(List<C0205a> list) {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            for (C0205a c0205a : list) {
                int i5 = AnonymousClass1.f13424a[c0205a.operation.ordinal()];
                if (i5 == 1) {
                    i3 += c0205a.text.length();
                } else if (i5 == 2) {
                    i4 += c0205a.text.length();
                } else if (i5 != 3) {
                }
            }
            return i2 + Math.max(i3, i4);
            i2 += Math.max(i3, i4);
            i3 = 0;
        }
    }

    public String diff_toDelta(List<C0205a> list) {
        StringBuilder sb = new StringBuilder();
        for (C0205a c0205a : list) {
            int i2 = AnonymousClass1.f13424a[c0205a.operation.ordinal()];
            if (i2 == 1) {
                try {
                    sb.append("+").append(URLEncoder.encode(c0205a.text, "UTF-8").replace('+', ' ')).append("\t");
                } catch (UnsupportedEncodingException e2) {
                    throw new Error("This system does not support UTF-8.", e2);
                }
            } else if (i2 == 2) {
                sb.append("-").append(c0205a.text.length()).append("\t");
            } else if (i2 == 3) {
                sb.append("=").append(c0205a.text.length()).append("\t");
            }
        }
        String sb2 = sb.toString();
        return sb2.length() != 0 ? c(sb2.substring(0, sb2.length() - 1)) : sb2;
    }

    public LinkedList<C0205a> diff_fromDelta(String str, String str2) {
        String[] split;
        LinkedList<C0205a> linkedList = new LinkedList<>();
        int i2 = 0;
        for (String str3 : str2.split("\t")) {
            if (str3.length() != 0) {
                String substring = str3.substring(1);
                char charAt = str3.charAt(0);
                if (charAt == '+') {
                    String replace = substring.replace("+", "%2B");
                    try {
                        linkedList.add(new C0205a(c.INSERT, URLDecoder.decode(replace, "UTF-8")));
                    } catch (UnsupportedEncodingException e2) {
                        throw new Error("This system does not support UTF-8.", e2);
                    } catch (IllegalArgumentException e3) {
                        throw new IllegalArgumentException("Illegal escape in diff_fromDelta: " + replace, e3);
                    }
                } else if (charAt == '-' || charAt == '=') {
                    try {
                        int parseInt = Integer.parseInt(substring);
                        if (parseInt < 0) {
                            throw new IllegalArgumentException("Negative number in diff_fromDelta: " + substring);
                        }
                        int i3 = parseInt + i2;
                        try {
                            String substring2 = str.substring(i2, i3);
                            if (str3.charAt(0) == '=') {
                                linkedList.add(new C0205a(c.EQUAL, substring2));
                            } else {
                                linkedList.add(new C0205a(c.DELETE, substring2));
                            }
                            i2 = i3;
                        } catch (StringIndexOutOfBoundsException e4) {
                            throw new IllegalArgumentException("Delta length (" + i3 + ") larger than source text length (" + str.length() + ").", e4);
                        }
                    } catch (NumberFormatException e5) {
                        throw new IllegalArgumentException("Invalid number in diff_fromDelta: " + substring, e5);
                    }
                } else {
                    throw new IllegalArgumentException("Invalid diff operation in diff_fromDelta: " + str3.charAt(0));
                }
            }
        }
        if (i2 == str.length()) {
            return linkedList;
        }
        throw new IllegalArgumentException("Delta length (" + i2 + ") smaller than source text length (" + str.length() + ").");
    }

    public int match_main(String str, String str2, int i2) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException("Null inputs. (match_main)");
        }
        int max = Math.max(0, Math.min(i2, str.length()));
        if (str.equals(str2)) {
            return 0;
        }
        if (str.length() == 0) {
            return -1;
        }
        return (str2.length() + max > str.length() || !str.substring(max, str2.length() + max).equals(str2)) ? a(str, str2, max) : max;
    }

    protected int a(String str, String str2, int i2) {
        if (f13420a || this.f13421b == 0 || str2.length() <= this.f13421b) {
            Map<Character, Integer> a2 = a(str2);
            double d2 = this.Match_Threshold;
            int indexOf = str.indexOf(str2, i2);
            int i3 = -1;
            int i4 = 0;
            if (indexOf != -1) {
                d2 = Math.min(a(0, indexOf, i2, str2), d2);
                int lastIndexOf = str.lastIndexOf(str2, str2.length() + i2);
                if (lastIndexOf != -1) {
                    d2 = Math.min(a(0, lastIndexOf, i2, str2), d2);
                }
            }
            int i5 = 1;
            int length = 1 << (str2.length() - 1);
            int length2 = str2.length() + str.length();
            int[] iArr = new int[0];
            int i6 = 0;
            while (i6 < str2.length()) {
                int i7 = i4;
                int i8 = length2;
                while (i7 < length2) {
                    if (a(i6, i2 + length2, i2, str2) <= d2) {
                        i7 = length2;
                    } else {
                        i8 = length2;
                    }
                    length2 = ((i8 - i7) / 2) + i7;
                }
                int max = Math.max(i5, (i2 - length2) + i5);
                int min = Math.min(i2 + length2, str.length()) + str2.length();
                int[] iArr2 = new int[min + 2];
                iArr2[min + 1] = (i5 << i6) - 1;
                while (true) {
                    if (min < max) {
                        break;
                    }
                    int i9 = i3;
                    i3 = min - 1;
                    int intValue = (str.length() <= i3 || !a2.containsKey(Character.valueOf(str.charAt(i3)))) ? 0 : a2.get(Character.valueOf(str.charAt(i3))).intValue();
                    if (i6 == 0) {
                        iArr2[min] = ((iArr2[min + 1] << 1) | 1) & intValue;
                    } else {
                        int i10 = min + 1;
                        iArr2[min] = (((iArr2[i10] << 1) | 1) & intValue) | ((iArr[i10] | iArr[min]) << 1) | 1 | iArr[i10];
                    }
                    if ((iArr2[min] & length) != 0) {
                        double a3 = a(i6, i3, i2, str2);
                        if (a3 <= d2) {
                            if (i3 <= i2) {
                                i5 = 1;
                                d2 = a3;
                                break;
                            }
                            i5 = 1;
                            max = Math.max(1, (i2 * 2) - i3);
                            d2 = a3;
                            min--;
                        }
                    }
                    i5 = 1;
                    i3 = i9;
                    min--;
                }
                i6++;
                if (a(i6, i2, i2, str2) > d2) {
                    break;
                }
                iArr = iArr2;
                i4 = 0;
            }
            return i3;
        }
        throw new AssertionError("Pattern too long for this application.");
    }

    private double a(int i2, int i3, int i4, String str) {
        float length = i2 / str.length();
        int abs = Math.abs(i4 - i3);
        int i5 = this.Match_Distance;
        if (i5 == 0) {
            if (abs == 0) {
                return length;
            }
            return 1.0d;
        }
        return length + (abs / i5);
    }

    protected Map<Character, Integer> a(String str) {
        HashMap hashMap = new HashMap();
        char[] charArray = str.toCharArray();
        for (char c2 : charArray) {
            hashMap.put(Character.valueOf(c2), 0);
        }
        int i2 = 0;
        for (char c3 : charArray) {
            hashMap.put(Character.valueOf(c3), Integer.valueOf(((Integer) hashMap.get(Character.valueOf(c3))).intValue() | (1 << ((str.length() - i2) - 1))));
            i2++;
        }
        return hashMap;
    }

    protected void a(d dVar, String str) {
        if (str.length() == 0) {
            return;
        }
        String substring = str.substring(dVar.start2, dVar.start2 + dVar.length1);
        int i2 = 0;
        while (str.indexOf(substring) != str.lastIndexOf(substring)) {
            int length = substring.length();
            short s = this.f13421b;
            short s2 = this.Patch_Margin;
            if (length >= (s - s2) - s2) {
                break;
            }
            i2 += s2;
            substring = str.substring(Math.max(0, dVar.start2 - i2), Math.min(str.length(), dVar.start2 + dVar.length1 + i2));
        }
        int i3 = i2 + this.Patch_Margin;
        String substring2 = str.substring(Math.max(0, dVar.start2 - i3), dVar.start2);
        if (substring2.length() != 0) {
            dVar.diffs.addFirst(new C0205a(c.EQUAL, substring2));
        }
        String substring3 = str.substring(dVar.start2 + dVar.length1, Math.min(str.length(), dVar.start2 + dVar.length1 + i3));
        if (substring3.length() != 0) {
            dVar.diffs.addLast(new C0205a(c.EQUAL, substring3));
        }
        dVar.start1 -= substring2.length();
        dVar.start2 -= substring2.length();
        dVar.length1 += substring2.length() + substring3.length();
        dVar.length2 += substring2.length() + substring3.length();
    }

    public LinkedList<d> patch_make(String str, String str2) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException("Null inputs. (patch_make)");
        }
        LinkedList<C0205a> diff_main = diff_main(str, str2, true);
        if (diff_main.size() > 2) {
            diff_cleanupSemantic(diff_main);
            diff_cleanupEfficiency(diff_main);
        }
        return patch_make(str, diff_main);
    }

    public LinkedList<d> patch_make(LinkedList<C0205a> linkedList) {
        if (linkedList == null) {
            throw new IllegalArgumentException("Null inputs. (patch_make)");
        }
        return patch_make(diff_text1(linkedList), linkedList);
    }

    @Deprecated
    public LinkedList<d> patch_make(String str, String str2, LinkedList<C0205a> linkedList) {
        return patch_make(str, linkedList);
    }

    public LinkedList<d> patch_make(String str, LinkedList<C0205a> linkedList) {
        if (str == null || linkedList == null) {
            throw new IllegalArgumentException("Null inputs. (patch_make)");
        }
        LinkedList<d> linkedList2 = new LinkedList<>();
        if (linkedList.isEmpty()) {
            return linkedList2;
        }
        d dVar = new d();
        Iterator<C0205a> it = linkedList.iterator();
        d dVar2 = dVar;
        int i2 = 0;
        int i3 = 0;
        String str2 = str;
        while (it.hasNext()) {
            C0205a next = it.next();
            if (dVar2.diffs.isEmpty() && next.operation != c.EQUAL) {
                dVar2.start1 = i2;
                dVar2.start2 = i3;
            }
            int i4 = AnonymousClass1.f13424a[next.operation.ordinal()];
            if (i4 == 1) {
                dVar2.diffs.add(next);
                dVar2.length2 += next.text.length();
                str2 = str2.substring(0, i3) + next.text + str2.substring(i3);
            } else if (i4 == 2) {
                dVar2.length1 += next.text.length();
                dVar2.diffs.add(next);
                str2 = str2.substring(0, i3) + str2.substring(next.text.length() + i3);
            } else if (i4 == 3) {
                if (next.text.length() <= this.Patch_Margin * 2 && !dVar2.diffs.isEmpty() && next != linkedList.getLast()) {
                    dVar2.diffs.add(next);
                    dVar2.length1 += next.text.length();
                    dVar2.length2 += next.text.length();
                }
                if (next.text.length() >= this.Patch_Margin * 2 && !dVar2.diffs.isEmpty() && !dVar2.diffs.isEmpty()) {
                    a(dVar2, str);
                    linkedList2.add(dVar2);
                    dVar2 = new d();
                    str = str2;
                    i2 = i3;
                }
            }
            if (next.operation != c.INSERT) {
                i2 += next.text.length();
            }
            if (next.operation != c.DELETE) {
                i3 += next.text.length();
            }
        }
        if (!dVar2.diffs.isEmpty()) {
            a(dVar2, str);
            linkedList2.add(dVar2);
        }
        return linkedList2;
    }

    public LinkedList<d> patch_deepCopy(LinkedList<d> linkedList) {
        LinkedList<d> linkedList2 = new LinkedList<>();
        Iterator<d> it = linkedList.iterator();
        while (it.hasNext()) {
            d next = it.next();
            d dVar = new d();
            Iterator<C0205a> it2 = next.diffs.iterator();
            while (it2.hasNext()) {
                C0205a next2 = it2.next();
                dVar.diffs.add(new C0205a(next2.operation, next2.text));
            }
            dVar.start1 = next.start1;
            dVar.start2 = next.start2;
            dVar.length1 = next.length1;
            dVar.length2 = next.length2;
            linkedList2.add(dVar);
        }
        return linkedList2;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object[] patch_apply(java.util.LinkedList<com.bytedance.sync.diff.a.d> r17, java.lang.String r18) {
        /*
            Method dump skipped, instructions count: 434
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sync.diff.a.patch_apply(java.util.LinkedList, java.lang.String):java.lang.Object[]");
    }

    public String patch_addPadding(LinkedList<d> linkedList) {
        C0205a last;
        short s = this.Patch_Margin;
        String str = "";
        for (short s2 = 1; s2 <= s; s2 = (short) (s2 + 1)) {
            str = str + String.valueOf((char) s2);
        }
        Iterator<d> it = linkedList.iterator();
        while (it.hasNext()) {
            d next = it.next();
            next.start1 += s;
            next.start2 += s;
        }
        d first = linkedList.getFirst();
        LinkedList<C0205a> linkedList2 = first.diffs;
        if (linkedList2.isEmpty() || linkedList2.getFirst().operation != c.EQUAL) {
            linkedList2.addFirst(new C0205a(c.EQUAL, str));
            first.start1 -= s;
            first.start2 -= s;
            first.length1 += s;
            first.length2 += s;
        } else if (s > linkedList2.getFirst().text.length()) {
            C0205a first2 = linkedList2.getFirst();
            int length = s - first2.text.length();
            first2.text = str.substring(first2.text.length()) + first2.text;
            first.start1 -= length;
            first.start2 -= length;
            first.length1 += length;
            first.length2 += length;
        }
        d last2 = linkedList.getLast();
        LinkedList<C0205a> linkedList3 = last2.diffs;
        if (linkedList3.isEmpty() || linkedList3.getLast().operation != c.EQUAL) {
            linkedList3.addLast(new C0205a(c.EQUAL, str));
            last2.length1 += s;
            last2.length2 += s;
        } else if (s > linkedList3.getLast().text.length()) {
            int length2 = s - linkedList3.getLast().text.length();
            last.text += str.substring(0, length2);
            last2.length1 += length2;
            last2.length2 += length2;
        }
        return str;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:5:0x0014 -> B:6:0x0015). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void patch_splitMax(java.util.LinkedList<com.bytedance.sync.diff.a.d> r15) {
        /*
            Method dump skipped, instructions count: 520
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sync.diff.a.patch_splitMax(java.util.LinkedList):void");
    }

    public String patch_toText(List<d> list) {
        StringBuilder sb = new StringBuilder();
        for (d dVar : list) {
            sb.append(dVar);
        }
        return sb.toString();
    }

    public List<d> patch_fromText(String str) {
        char charAt;
        String replace;
        LinkedList linkedList = new LinkedList();
        if (str.length() == 0) {
            return linkedList;
        }
        LinkedList linkedList2 = new LinkedList(Arrays.asList(str.split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE)));
        Pattern compile = Pattern.compile("^@@ -(\\d+),?(\\d*) \\+(\\d+),?(\\d*) @@$");
        while (!linkedList2.isEmpty()) {
            Matcher matcher = compile.matcher((CharSequence) linkedList2.getFirst());
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Invalid patch string: " + ((String) linkedList2.getFirst()));
            }
            d dVar = new d();
            linkedList.add(dVar);
            dVar.start1 = Integer.parseInt(matcher.group(1));
            if (matcher.group(2).length() == 0) {
                dVar.start1--;
                dVar.length1 = 1;
            } else if (matcher.group(2).equals("0")) {
                dVar.length1 = 0;
            } else {
                dVar.start1--;
                dVar.length1 = Integer.parseInt(matcher.group(2));
            }
            dVar.start2 = Integer.parseInt(matcher.group(3));
            if (matcher.group(4).length() == 0) {
                dVar.start2--;
                dVar.length2 = 1;
            } else if (matcher.group(4).equals("0")) {
                dVar.length2 = 0;
            } else {
                dVar.start2--;
                dVar.length2 = Integer.parseInt(matcher.group(4));
            }
            linkedList2.removeFirst();
            while (true) {
                if (!linkedList2.isEmpty()) {
                    try {
                        charAt = ((String) linkedList2.getFirst()).charAt(0);
                        replace = ((String) linkedList2.getFirst()).substring(1).replace("+", "%2B");
                    } catch (IndexOutOfBoundsException unused) {
                        linkedList2.removeFirst();
                    }
                    try {
                        String decode = URLDecoder.decode(replace, "UTF-8");
                        if (charAt == '-') {
                            dVar.diffs.add(new C0205a(c.DELETE, decode));
                        } else if (charAt == '+') {
                            dVar.diffs.add(new C0205a(c.INSERT, decode));
                        } else if (charAt == ' ') {
                            dVar.diffs.add(new C0205a(c.EQUAL, decode));
                        } else if (charAt != '@') {
                            throw new IllegalArgumentException("Invalid patch mode '" + charAt + "' in: " + decode);
                        }
                        linkedList2.removeFirst();
                    } catch (UnsupportedEncodingException e2) {
                        throw new Error("This system does not support UTF-8.", e2);
                    } catch (IllegalArgumentException e3) {
                        throw new IllegalArgumentException("Illegal escape in patch_fromText: " + replace, e3);
                    }
                }
            }
        }
        return linkedList;
    }

    /* compiled from: DiffMatchPatch.java */
    /* renamed from: com.bytedance.sync.diff.a$a  reason: collision with other inner class name */
    /* loaded from: classes3.dex */
    public static class C0205a {
        public c operation;
        public String text;

        public C0205a(c cVar, String str) {
            this.operation = cVar;
            this.text = str;
        }

        public String toString() {
            return "Diff(" + this.operation + ",\"" + this.text.replace('\n', Typography.paragraph) + "\")";
        }

        public int hashCode() {
            c cVar = this.operation;
            int hashCode = cVar == null ? 0 : cVar.hashCode();
            String str = this.text;
            return hashCode + ((str != null ? str.hashCode() : 0) * 31);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                C0205a c0205a = (C0205a) obj;
                if (this.operation != c0205a.operation) {
                    return false;
                }
                String str = this.text;
                if (str == null) {
                    if (c0205a.text != null) {
                        return false;
                    }
                } else if (!str.equals(c0205a.text)) {
                    return false;
                }
                return true;
            }
            return false;
        }
    }

    /* compiled from: DiffMatchPatch.java */
    /* loaded from: classes3.dex */
    public static class d {
        public LinkedList<C0205a> diffs = new LinkedList<>();
        public int length1;
        public int length2;
        public int start1;
        public int start2;

        public String toString() {
            String str;
            String str2;
            int i2 = this.length1;
            if (i2 == 0) {
                str = this.start1 + ",0";
            } else if (i2 == 1) {
                str = Integer.toString(this.start1 + 1);
            } else {
                str = (this.start1 + 1) + "," + this.length1;
            }
            int i3 = this.length2;
            if (i3 == 0) {
                str2 = this.start2 + ",0";
            } else if (i3 == 1) {
                str2 = Integer.toString(this.start2 + 1);
            } else {
                str2 = (this.start2 + 1) + "," + this.length2;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("@@ -").append(str).append(" +").append(str2).append(" @@\n");
            Iterator<C0205a> it = this.diffs.iterator();
            while (it.hasNext()) {
                C0205a next = it.next();
                int i4 = AnonymousClass1.f13424a[next.operation.ordinal()];
                if (i4 == 1) {
                    sb.append('+');
                } else if (i4 == 2) {
                    sb.append('-');
                } else if (i4 == 3) {
                    sb.append(' ');
                }
                try {
                    sb.append(URLEncoder.encode(next.text, "UTF-8").replace('+', ' ')).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                } catch (UnsupportedEncodingException e2) {
                    throw new Error("This system does not support UTF-8.", e2);
                }
            }
            return a.c(sb.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String c(String str) {
        return str.replace("%21", "!").replace("%7E", "~").replace("%27", "'").replace("%28", "(").replace("%29", ")").replace("%3B", Constants.PACKNAME_END).replace("%2F", c.a.SEPARATOR).replace("%3F", "?").replace("%3A", CertificateUtil.DELIMITER).replace("%40", "@").replace("%26", "&").replace("%3D", "=").replace("%2B", "+").replace("%24", "$").replace("%2C", ",").replace("%23", "#");
    }
}
