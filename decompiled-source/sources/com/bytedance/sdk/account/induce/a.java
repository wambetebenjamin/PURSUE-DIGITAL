package com.bytedance.sdk.account.induce;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
/* compiled from: FrequencyController.java */
/* loaded from: classes2.dex */
class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12496a = "FrequencyController";

    /* renamed from: b  reason: collision with root package name */
    private final c f12497b;

    /* renamed from: c  reason: collision with root package name */
    private Map<String, List<Long>> f12498c;

    /* renamed from: d  reason: collision with root package name */
    private ArrayList<Long> f12499d;

    /* compiled from: FrequencyController.java */
    /* renamed from: com.bytedance.sdk.account.induce.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    interface InterfaceC0192a {
        public static final String DAY = "day";
        public static final String HOUR = "hour";
        public static final String MINUTE = "minute";
        public static final String WEEK = "week";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(c cVar) {
        this.f12497b = cVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(String str, int i2, int i3, int i4) {
        c();
        return a(this.f12499d, str, i2, i3, i4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(String str, String str2, int i2, int i3, int i4) {
        c();
        Map<String, List<Long>> map = this.f12498c;
        if (map == null) {
            return true;
        }
        return a(map.get(str), str2, i2, i3, i4);
    }

    private boolean a(List<Long> list, String str, int i2, int i3, int i4) {
        if (list == null || list.isEmpty() || i2 == 0) {
            return true;
        }
        int size = list.size();
        if (i4 == -1 || size < i4) {
            long a2 = a(str, i2);
            int i5 = 0;
            while (true) {
                if (i5 >= size) {
                    break;
                } else if (list.get((size - 1) - i5).longValue() < a2) {
                    size = i5;
                    break;
                } else if (i5 >= i3) {
                    return false;
                } else {
                    i5++;
                }
            }
            return size < i3;
        }
        return false;
    }

    private long a(String str, int i2) {
        long currentTimeMillis;
        long millis;
        long currentTimeMillis2 = System.currentTimeMillis();
        if (InterfaceC0192a.WEEK.equalsIgnoreCase(str)) {
            currentTimeMillis = System.currentTimeMillis();
            millis = TimeUnit.DAYS.toMillis(i2) * 7;
        } else if (InterfaceC0192a.DAY.equalsIgnoreCase(str)) {
            currentTimeMillis = System.currentTimeMillis();
            millis = TimeUnit.DAYS.toMillis(i2);
        } else if (InterfaceC0192a.HOUR.equalsIgnoreCase(str)) {
            currentTimeMillis = System.currentTimeMillis();
            millis = TimeUnit.HOURS.toMillis(i2);
        } else if (!InterfaceC0192a.MINUTE.equalsIgnoreCase(str)) {
            return currentTimeMillis2;
        } else {
            currentTimeMillis = System.currentTimeMillis();
            millis = TimeUnit.MINUTES.toMillis(i2);
        }
        return currentTimeMillis - millis;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        c();
        c(str);
        List<Long> list = this.f12498c.get(str);
        if (list == null) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        list.add(Long.valueOf(currentTimeMillis));
        this.f12499d.add(Long.valueOf(currentTimeMillis));
        Collections.sort(list);
        Collections.sort(this.f12499d);
        a(str, list);
    }

    private void c() {
        if (this.f12499d == null || this.f12498c == null) {
            this.f12499d = new ArrayList<>();
            this.f12498c = new HashMap();
            Set<String> d2 = this.f12497b.d();
            if (!d2.isEmpty()) {
                for (String str : d2) {
                    List<Long> d3 = d(this.f12497b.b(str));
                    Collections.sort(d3);
                    this.f12498c.put(str, d3);
                    this.f12499d.addAll(d3);
                }
            }
            Collections.sort(this.f12499d);
        }
    }

    private void c(String str) {
        if (this.f12498c.get(str) == null) {
            this.f12498c.put(str, new ArrayList());
        }
    }

    private List<Long> d(String str) {
        String[] split;
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str) && (split = str.split(",")) != null && split.length > 0) {
            try {
                for (String str2 : split) {
                    arrayList.add(Long.valueOf(Long.parseLong(str2)));
                }
            } catch (Exception e2) {
                com.ss.android.e.b(f12496a, "transferTimesStringToList", e2);
            }
        }
        return arrayList;
    }

    void a(String str, List<Long> list) {
        StringBuilder sb = new StringBuilder();
        for (Long l : list) {
            if (sb.length() > 0) {
                sb.append(",").append(l);
            } else {
                sb.append(l);
            }
        }
        this.f12497b.a(str, sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long a() {
        c();
        if (this.f12499d.isEmpty()) {
            return 0L;
        }
        ArrayList<Long> arrayList = this.f12499d;
        return arrayList.get(arrayList.size() - 1).longValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long b(String str) {
        c();
        List<Long> list = this.f12498c.get(str);
        if (list == null || list.isEmpty()) {
            return 0L;
        }
        return list.get(list.size() - 1).longValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        Map<String, List<Long>> map = this.f12498c;
        if (map != null) {
            map.clear();
        }
        ArrayList<Long> arrayList = this.f12499d;
        if (arrayList != null) {
            arrayList.clear();
        }
        this.f12497b.c();
    }
}
