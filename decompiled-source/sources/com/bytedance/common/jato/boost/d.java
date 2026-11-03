package com.bytedance.common.jato.boost;

import android.util.Log;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.TreeMap;
/* compiled from: CpuFreqFetcher.java */
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static int[] f4559a = null;

    /* renamed from: b  reason: collision with root package name */
    private static int[] f4560b = null;

    /* renamed from: c  reason: collision with root package name */
    private static long f4561c = -1;

    /* renamed from: d  reason: collision with root package name */
    private static TreeMap<Long, LinkedList<Integer>> f4562d = new TreeMap<>();

    /* renamed from: e  reason: collision with root package name */
    private static volatile boolean f4563e = false;

    /* renamed from: f  reason: collision with root package name */
    private static volatile boolean f4564f = false;

    public static synchronized boolean fetch() {
        BufferedReader bufferedReader;
        Throwable th;
        boolean z;
        int i2;
        BufferedReader bufferedReader2;
        synchronized (d.class) {
            if (f4563e) {
                return f4564f;
            }
            boolean z2 = false;
            try {
                try {
                    bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"), 50);
                    int i3 = 0;
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            } else if (readLine.startsWith("processor")) {
                                i3++;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            throw th;
                        }
                    }
                    bufferedReader.close();
                    Log.d("jato", "default cpu number: " + i3);
                    i2 = 0;
                    for (int i4 = 0; i4 < i3; i4++) {
                        try {
                            try {
                                bufferedReader2 = new BufferedReader(new FileReader("/sys/devices/system/cpu/cpu" + i4 + "/cpufreq/scaling_available_frequencies"), 5);
                            } catch (Exception unused) {
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                        try {
                            String readLine2 = bufferedReader2.readLine();
                            String substring = readLine2.substring(0, readLine2.length() - 1);
                            i2++;
                            long max = Math.max(Long.parseLong(substring.substring(substring.lastIndexOf(" ") + 1)), Long.parseLong(substring.substring(0, substring.indexOf(" "))));
                            f4561c = Math.max(f4561c, max);
                            Log.d("jato", "cpu num: " + i4 + ", maxFreq: " + max);
                            LinkedList<Integer> linkedList = f4562d.get(Long.valueOf(max));
                            if (linkedList == null) {
                                linkedList = new LinkedList<>();
                                f4562d.put(Long.valueOf(max), linkedList);
                            }
                            linkedList.add(Integer.valueOf(i4));
                            bufferedReader2.close();
                            bufferedReader = bufferedReader2;
                        } catch (Exception unused2) {
                            bufferedReader = bufferedReader2;
                            Log.d("jato", "not found in cpu core: " + i3);
                            bufferedReader.close();
                        } catch (Throwable th4) {
                            th = th4;
                            bufferedReader = bufferedReader2;
                            bufferedReader.close();
                            throw th;
                        }
                    }
                    Log.d("jato", "maxFreq in all cpu core: " + f4561c + " real cpu core num is: " + i2);
                } catch (Throwable th5) {
                    th = th5;
                }
                if (i2 < 4) {
                    Log.d("jato", "the number of cpu is smaller than 4, stop cpuset!");
                    f4564f = false;
                    f4563e = true;
                    return false;
                }
                LinkedList linkedList2 = new LinkedList();
                LinkedList linkedList3 = new LinkedList();
                try {
                    if (i2 == 4) {
                        Object[] objArr = 1;
                        for (LinkedList<Integer> linkedList4 : f4562d.values()) {
                            if (objArr == null) {
                                linkedList3.addAll(linkedList4);
                            } else if (linkedList4.size() != 2) {
                                Log.d("jato", "the number of cpu is 4, but number of small cpu core is more then 2, stop cpuset!");
                                z = false;
                                break;
                            } else {
                                linkedList2.addAll(linkedList4);
                            }
                            objArr = null;
                        }
                    } else {
                        int i5 = i2 / 2;
                        Object[] objArr2 = 1;
                        for (LinkedList<Integer> linkedList5 : f4562d.values()) {
                            if (objArr2 != null) {
                                linkedList2.addAll(linkedList5);
                            } else {
                                linkedList3.addAll(linkedList5);
                            }
                            if (linkedList2.size() >= i5) {
                                objArr2 = null;
                            }
                        }
                    }
                    z = true;
                    if (z) {
                        try {
                            f4559a = new int[linkedList2.size()];
                            for (int i6 = 0; i6 < linkedList2.size(); i6++) {
                                int intValue = ((Integer) linkedList2.get(i6)).intValue();
                                Log.d("jato", "little cpu core: " + intValue);
                                f4559a[i6] = intValue;
                            }
                            f4560b = new int[linkedList3.size()];
                            for (int i7 = 0; i7 < linkedList3.size(); i7++) {
                                int intValue2 = ((Integer) linkedList3.get(i7)).intValue();
                                Log.d("jato", "big cpu core: " + intValue2);
                                f4560b[i7] = intValue2;
                            }
                        } catch (Throwable th6) {
                            z2 = z;
                            th = th6;
                            Log.e("jato", "error when init", th);
                            f4564f = z2;
                            f4563e = true;
                            z = z2;
                            return z;
                        }
                    }
                    f4564f = z;
                    f4563e = true;
                } catch (Throwable th7) {
                    th = th7;
                    z2 = true;
                }
                return z;
            } catch (Throwable th8) {
                bufferedReader = null;
                th = th8;
            }
        }
    }

    public static int[] getSmallCoreNum() {
        return f4559a;
    }

    public static int[] getBigCoreNum() {
        return f4560b;
    }
}
