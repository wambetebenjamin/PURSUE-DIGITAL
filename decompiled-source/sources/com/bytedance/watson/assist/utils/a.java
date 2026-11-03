package com.bytedance.watson.assist.utils;

import android.util.Log;
import com.bytedance.librarian.c;
import com.bytedance.watson.assist.utils.c;
import com.facebook.internal.security.CertificateUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
/* compiled from: CpuUtils.java */
/* loaded from: classes8.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f16270a = "/sys/devices/system/cpu";

    /* renamed from: b  reason: collision with root package name */
    private static final String f16271b = "/sys/devices/system/cpu/cpufreq";

    /* renamed from: c  reason: collision with root package name */
    private static final String f16272c = "/proc/cpuinfo";

    /* renamed from: d  reason: collision with root package name */
    private static final String f16273d = "/scaling_min_freq";

    /* renamed from: e  reason: collision with root package name */
    private static final String f16274e = "/scaling_max_freq";

    /* renamed from: f  reason: collision with root package name */
    private static final String f16275f = "/scaling_cur_freq";

    /* renamed from: g  reason: collision with root package name */
    private static int f16276g = -1;

    /* renamed from: h  reason: collision with root package name */
    private static String f16277h = null;

    /* renamed from: i  reason: collision with root package name */
    private static List<C0264a> f16278i = null;

    /* renamed from: j  reason: collision with root package name */
    private static List<Integer> f16279j = null;
    public static final int kClockTicksMs = 10;

    public static int getCpuCoreNum() {
        if (f16276g == -1) {
            f16276g = new File(f16270a).listFiles(new FilenameFilter() { // from class: com.bytedance.watson.assist.utils.a.1
                @Override // java.io.FilenameFilter
                public boolean accept(File file, String str) {
                    return Pattern.matches("cpu[0-9]", str);
                }
            }).length;
        }
        return f16276g;
    }

    public static String getCpuHardware() {
        String str = f16277h;
        if (str != null) {
            return str;
        }
        c.readFile(f16272c, new c.a() { // from class: com.bytedance.watson.assist.utils.a.2
            @Override // com.bytedance.watson.assist.utils.c.a
            public boolean onReadLine(String str2) {
                if (str2 == null || !str2.contains("Hardware")) {
                    return true;
                }
                String[] split = str2.split(CertificateUtil.DELIMITER);
                if (split.length > 1) {
                    String unused = a.f16277h = split[1].trim();
                    return false;
                }
                return false;
            }
        });
        return f16277h;
    }

    public static List<C0264a> getCpuClusterInfoList() {
        List<C0264a> list = f16278i;
        if (list != null) {
            return list;
        }
        f16278i = new ArrayList();
        File[] listFiles = new File(f16271b).listFiles(new FilenameFilter() { // from class: com.bytedance.watson.assist.utils.a.3
            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str) {
                return Pattern.matches("policy[0-9]", str);
            }
        });
        Arrays.sort(listFiles, new Comparator<File>() { // from class: com.bytedance.watson.assist.utils.a.4
            @Override // java.util.Comparator
            public int compare(File file, File file2) {
                return file.getName().compareTo(file2.getName());
            }
        });
        if (listFiles != null) {
            for (File file : listFiles) {
                C0264a c0264a = new C0264a(file.getName());
                final ArrayList arrayList = new ArrayList();
                final ArrayList arrayList2 = new ArrayList();
                c.readFile(file.getAbsolutePath() + "/scaling_available_frequencies", new c.a() { // from class: com.bytedance.watson.assist.utils.a.5
                    @Override // com.bytedance.watson.assist.utils.c.a
                    public boolean onReadLine(String str) {
                        if (str != null && !str.isEmpty()) {
                            for (String str2 : str.split(" ")) {
                                arrayList.add(Long.valueOf(Long.parseLong(str2)));
                            }
                        }
                        return true;
                    }
                });
                c.readFile(file.getAbsolutePath() + "/affected_cpus", new c.a() { // from class: com.bytedance.watson.assist.utils.a.6
                    @Override // com.bytedance.watson.assist.utils.c.a
                    public boolean onReadLine(String str) {
                        if (str != null && !str.isEmpty()) {
                            for (String str2 : str.split(" ")) {
                                arrayList2.add(Integer.valueOf(Integer.parseInt(str2)));
                            }
                        }
                        return true;
                    }
                });
                c0264a.setFreqList(arrayList);
                c0264a.setAffectedCpuList(arrayList2);
                f16278i.add(c0264a);
            }
        }
        return f16278i;
    }

    public static List<Integer> getClusterCpuNum() {
        List<Integer> list = f16279j;
        if (list != null) {
            return list;
        }
        List<C0264a> cpuClusterInfoList = getCpuClusterInfoList();
        f16279j = new ArrayList();
        for (C0264a c0264a : cpuClusterInfoList) {
            f16279j.add(Integer.valueOf(c0264a.getAffectedCpuList().size()));
        }
        return f16279j;
    }

    public static long getClusterCurrentFreq(int i2) {
        return a(i2, f16275f);
    }

    public static long getClusterMinFreq(int i2) {
        return a(i2, f16273d);
    }

    public static long getClusterMaxFreq(int i2) {
        return a(i2, f16274e);
    }

    public static int getClusterCurrentFreqLevel(int i2) {
        return a(i2, a(i2, f16275f));
    }

    public static int getClusterMinFreqLevelLevel(int i2) {
        return a(i2, a(i2, f16273d));
    }

    public static int getClusterMaxFreqLevel(int i2) {
        return a(i2, a(i2, f16274e));
    }

    private static long a(int i2, String str) {
        Long l = -1L;
        if (i2 < 0 || str == null) {
            return l.longValue();
        }
        String str2 = c.a.SEPARATOR;
        List<C0264a> cpuClusterInfoList = getCpuClusterInfoList();
        if (cpuClusterInfoList != null && cpuClusterInfoList.size() > i2) {
            str2 = c.a.SEPARATOR + cpuClusterInfoList.get(i2).getName();
        }
        BufferedReader openFile = c.openFile(new File(f16271b + str2 + str));
        if (openFile == null) {
            return l.longValue();
        }
        try {
            try {
                String readLine = openFile.readLine();
                if (readLine != null) {
                    l = Long.valueOf(Long.parseLong(readLine));
                }
            } catch (Exception e2) {
                b.w("get cluster freq error : " + str + Log.getStackTraceString(e2));
            }
            return l.longValue();
        } finally {
            c.closeFile(openFile);
        }
    }

    private static int a(int i2, long j2) {
        List<C0264a> cpuClusterInfoList;
        C0264a c0264a;
        if (i2 >= 0 && j2 > 0 && (cpuClusterInfoList = getCpuClusterInfoList()) != null && cpuClusterInfoList.size() >= i2 + 1 && (c0264a = cpuClusterInfoList.get(i2)) != null && c0264a.getFreqList() != null) {
            int size = c0264a.getFreqList().size();
            for (int i3 = 0; i3 < size; i3++) {
                if (c0264a.getFreqList().get(i3).longValue() == j2) {
                    int i4 = i3 / (size / 4);
                    if (i4 > 3) {
                        return 3;
                    }
                    return i4;
                }
            }
        }
        return -1;
    }

    /* compiled from: CpuUtils.java */
    /* renamed from: com.bytedance.watson.assist.utils.a$a  reason: collision with other inner class name */
    /* loaded from: classes8.dex */
    public static class C0264a {

        /* renamed from: a  reason: collision with root package name */
        private String f16282a;

        /* renamed from: b  reason: collision with root package name */
        private List<Integer> f16283b = new ArrayList();

        /* renamed from: c  reason: collision with root package name */
        private List<Long> f16284c = new ArrayList();

        public C0264a(String str) {
            this.f16282a = str;
        }

        public String getName() {
            return this.f16282a;
        }

        public List<Integer> getAffectedCpuList() {
            return this.f16283b;
        }

        public void setAffectedCpuList(List<Integer> list) {
            this.f16283b = list;
        }

        public List<Long> getFreqList() {
            return this.f16284c;
        }

        public void setFreqList(List<Long> list) {
            this.f16284c = list;
        }

        public String toString() {
            return "CpuClusterInfo{name='" + this.f16282a + "', affectedCpuList=" + this.f16283b + ", freqList=" + this.f16284c + '}';
        }
    }
}
