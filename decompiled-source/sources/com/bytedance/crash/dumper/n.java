package com.bytedance.crash.dumper;

import com.bytedance.crash.jni.NativeBridge;
import java.io.File;
/* compiled from: ProcInfo.java */
/* loaded from: classes2.dex */
public class n {

    /* renamed from: a  reason: collision with root package name */
    public static final long f6570a = 1024;

    /* renamed from: b  reason: collision with root package name */
    public static final long f6571b = 1048576;

    /* renamed from: c  reason: collision with root package name */
    public static final long f6572c = 1073741824;

    private static long a(boolean z) {
        return z ? 4294967296L : 3221225472L;
    }

    public static String a(int i2) {
        return i2 > 750 ? "(750 - MAX)" : i2 > 650 ? "(650 - 750]" : i2 > 550 ? "(550 - 650]" : i2 > 450 ? "(450 - 550]" : i2 > 350 ? "(350 - 450]" : i2 > 250 ? "(250 - 350]" : i2 > 150 ? "(150 - 250]" : "[0 - 150]";
    }

    public static String a(long j2) {
        long j3 = j2 * 1024;
        return j3 > 25769803776L ? "(24GB - MAX)" : j3 > 21474836480L ? "(20GB - 24GB]" : j3 > 17179869184L ? "(16GB - 20GB]" : j3 > 12884901888L ? "(12GB - 16GB]" : j3 > 10737418240L ? "(10GB - 12GB]" : j3 > 9663676416L ? "(9GB - 10GB]" : j3 > 8589934592L ? "(8GB - 9GB]" : j3 > 7516192768L ? "(7GB - 8GB]" : j3 > 6442450944L ? "(6GB - 7GB]" : j3 > 5368709120L ? "(5GB - 6GB]" : j3 > 4294967296L ? "(4GB - 5GB]" : "[0GB - 4GB]";
    }

    public static String b(long j2) {
        long j3 = j2 * 1024;
        return j3 > 5368709120L ? "(5GB - MAX)" : j3 > 4294967296L ? "(4GB - 5GB]" : j3 > 3221225472L ? "(3GB - 4GB]" : j3 > 2147483648L ? "(2GB - 3GB]" : j3 > 1073741824 ? "(1GB - 2GB]" : j3 > 939524096 ? "(896MB - 1GB]" : j3 > 805306368 ? "(768MB - 896MB]" : j3 > 671088640 ? "(640MB - 768MB]" : j3 > 536870912 ? "(512MB - 640MB]" : j3 > 402653184 ? "(384MB - 512MB]" : j3 > 268435456 ? "(256MB - 384MB]" : j3 > 134217728 ? "(128MB - 256MB]" : "(0MB - 128MB]";
    }

    public static boolean b(int i2) {
        return i2 > 350;
    }

    public static String c(int i2) {
        return i2 > 2000 ? "(2000 - MAX)" : i2 > 1500 ? "(1500 - 2000]" : i2 > 1200 ? "(1200 - 1500]" : i2 > 960 ? "(960 - 1200]" : i2 > 800 ? "(800 - 960]" : i2 > 600 ? "(600 - 800]" : i2 > 400 ? "(400 - 600]" : "(0 - 400]";
    }

    public static boolean c(long j2) {
        return j2 > 209715200;
    }

    public static String e(int i2) {
        return i2 > 120 ? "(120 - MAX]" : i2 > 100 ? "(100 - 120]" : i2 > 80 ? "(80 - 100]" : i2 > 60 ? "(60 - 80]" : i2 > 40 ? "(40 - 60]" : "(0 - 40]";
    }

    public static boolean f(int i2) {
        return i2 > 100;
    }

    public static boolean g(int i2) {
        return i2 > 60000;
    }

    public static boolean d(int i2) {
        return ((double) i2) > ((double) NativeBridge.h()) * 0.9d;
    }

    public static String a(long j2, boolean z) {
        float f2 = (float) (j2 * 1024);
        float a2 = (float) a(z);
        return f2 > 0.95f * a2 ? "(95% - 100%]" : f2 > 0.9f * a2 ? "(90% - 95%]" : f2 > 0.85f * a2 ? "(85% - 90%]" : f2 > 0.8f * a2 ? "(80% - 85%]" : f2 > 0.7f * a2 ? "(70% - 80%]" : f2 > 0.6f * a2 ? "(60% - 70%]" : f2 > a2 * 0.5f ? "(50% - 60%]" : "[0% - 50%]";
    }

    public static boolean b(long j2, boolean z) {
        return ((float) (j2 * 1024)) > ((float) a(z)) * 0.9f;
    }

    public static int a(File file) {
        return NativeBridge.e(file.getAbsolutePath());
    }

    public static int b(File file) {
        return NativeBridge.f(file.getAbsolutePath());
    }

    public static int c(File file) {
        return NativeBridge.m(file.getAbsolutePath());
    }

    public static long d(File file) {
        return NativeBridge.b(file.getAbsolutePath(), false);
    }

    public static long e(File file) {
        return NativeBridge.b(file.getAbsolutePath(), true);
    }

    public static long f(File file) {
        return NativeBridge.g(file.getAbsolutePath());
    }

    public static int g(File file) {
        return NativeBridge.n(file.getAbsolutePath());
    }
}
