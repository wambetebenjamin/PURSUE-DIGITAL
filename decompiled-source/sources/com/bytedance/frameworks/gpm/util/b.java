package com.bytedance.frameworks.gpm.util;

import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/* compiled from: CPUUsage.java */
/* loaded from: classes2.dex */
class b {

    /* renamed from: a  reason: collision with root package name */
    private long f8546a = 0;

    /* renamed from: b  reason: collision with root package name */
    private long f8547b = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b() {
        a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double a() {
        long e2;
        long b2 = b();
        if (Build.VERSION.SDK_INT <= 26) {
            e2 = d();
        } else {
            e2 = e();
        }
        long j2 = this.f8547b;
        int i2 = (e2 > j2 ? 1 : (e2 == j2 ? 0 : -1));
        double d2 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        double d3 = i2 == 0 ? 0.0d : ((b2 - this.f8546a) * 1.0d) / (e2 - j2);
        if (d3 >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            d2 = d3;
        }
        this.f8546a = b2;
        this.f8547b = e2;
        return d2;
    }

    private long b() {
        FileReader fileReader;
        Throwable th;
        BufferedReader bufferedReader;
        FileReader fileReader2 = null;
        long j2 = 0;
        try {
            fileReader = new FileReader("/proc/" + Process.myPid() + "/stat");
            try {
                bufferedReader = new BufferedReader(fileReader);
            } catch (IOException unused) {
                bufferedReader = null;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
            try {
                String readLine = bufferedReader.readLine();
                if (!TextUtils.isEmpty(readLine)) {
                    String[] split = readLine.split(" ");
                    j2 = Long.parseLong(split[13]) + Long.parseLong(split[14]) + Long.parseLong(split[15]) + Long.parseLong(split[16]);
                }
                c.a(fileReader);
            } catch (IOException unused2) {
                fileReader2 = fileReader;
                c.a(fileReader2);
                c.a(bufferedReader);
                return j2;
            } catch (Throwable th3) {
                th = th3;
                c.a(fileReader);
                c.a(bufferedReader);
                throw th;
            }
        } catch (IOException unused3) {
            bufferedReader = null;
        } catch (Throwable th4) {
            fileReader = null;
            th = th4;
            bufferedReader = null;
        }
        c.a(bufferedReader);
        return j2;
    }

    private long c() {
        FileReader fileReader;
        Throwable th;
        BufferedReader bufferedReader;
        FileReader fileReader2 = null;
        long j2 = 0;
        try {
            fileReader = new FileReader("/proc/stat");
            try {
                bufferedReader = new BufferedReader(fileReader);
            } catch (IOException unused) {
                bufferedReader = null;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
        } catch (IOException unused2) {
            bufferedReader = null;
        } catch (Throwable th3) {
            fileReader = null;
            th = th3;
            bufferedReader = null;
        }
        try {
            String readLine = bufferedReader.readLine();
            if (!TextUtils.isEmpty(readLine)) {
                String[] split = readLine.split(" ");
                j2 = Long.parseLong(split[2]) + Long.parseLong(split[3]) + Long.parseLong(split[4]) + Long.parseLong(split[5]) + Long.parseLong(split[6]) + Long.parseLong(split[7]) + Long.parseLong(split[8]);
            }
            c.a(fileReader);
        } catch (IOException unused3) {
            fileReader2 = fileReader;
            c.a(fileReader2);
            c.a(bufferedReader);
            return j2;
        } catch (Throwable th4) {
            th = th4;
            c.a(fileReader);
            c.a(bufferedReader);
            throw th;
        }
        c.a(bufferedReader);
        return j2;
    }

    private long d() {
        FileReader fileReader;
        Throwable th;
        BufferedReader bufferedReader;
        FileReader fileReader2 = null;
        long j2 = 0;
        try {
            fileReader = new FileReader("/proc/stat");
            try {
                bufferedReader = new BufferedReader(fileReader);
            } catch (IOException unused) {
                bufferedReader = null;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
        } catch (IOException unused2) {
            bufferedReader = null;
        } catch (Throwable th3) {
            fileReader = null;
            th = th3;
            bufferedReader = null;
        }
        try {
            String readLine = bufferedReader.readLine();
            if (!TextUtils.isEmpty(readLine)) {
                String[] split = readLine.split(" ");
                j2 = Long.parseLong(split[2]) + Long.parseLong(split[3]) + Long.parseLong(split[4]) + Long.parseLong(split[5]) + Long.parseLong(split[6]) + Long.parseLong(split[7]) + Long.parseLong(split[8]);
            }
            c.a(fileReader);
        } catch (IOException unused3) {
            fileReader2 = fileReader;
            c.a(fileReader2);
            c.a(bufferedReader);
            return j2;
        } catch (Throwable th4) {
            th = th4;
            c.a(fileReader);
            c.a(bufferedReader);
            throw th;
        }
        c.a(bufferedReader);
        return j2;
    }

    private long e() {
        File[] listFiles;
        BufferedReader bufferedReader;
        long j2 = 0;
        try {
            File file = new File("/sys/devices/system/cpu/");
            if (file.exists() && file.isDirectory() && (listFiles = file.listFiles()) != null && listFiles.length > 0) {
                for (File file2 : listFiles) {
                    if (file2.getName().startsWith("cpu") && file2.getName().matches("cpu\\d+")) {
                        File file3 = new File(file2, "cpufreq/stats/time_in_state");
                        FileReader fileReader = null;
                        try {
                            FileReader fileReader2 = new FileReader(file3);
                            try {
                                bufferedReader = new BufferedReader(fileReader2);
                                while (true) {
                                    try {
                                        String readLine = bufferedReader.readLine();
                                        if (TextUtils.isEmpty(readLine)) {
                                            break;
                                        }
                                        j2 += Long.parseLong(readLine.split(" ")[1]);
                                    } catch (IOException unused) {
                                        fileReader = fileReader2;
                                        c.a(fileReader);
                                        c.a(bufferedReader);
                                    } catch (Throwable th) {
                                        th = th;
                                        fileReader = fileReader2;
                                        c.a(fileReader);
                                        c.a(bufferedReader);
                                        throw th;
                                    }
                                }
                                c.a(fileReader2);
                            } catch (IOException unused2) {
                                bufferedReader = null;
                            } catch (Throwable th2) {
                                th = th2;
                                bufferedReader = null;
                            }
                        } catch (IOException unused3) {
                            bufferedReader = null;
                        } catch (Throwable th3) {
                            th = th3;
                            bufferedReader = null;
                        }
                        c.a(bufferedReader);
                    }
                }
            }
        } catch (Throwable unused4) {
        }
        return j2;
    }
}
