package com.ttnet.org.chromium.base;

import android.text.TextUtils;
import com.facebook.internal.security.CertificateUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
/* loaded from: classes9.dex */
public class CpuUtils {
    private static String TAG = "CpuUtils";
    private static int sCpuCores = 0;
    private static String sCpuDirectory = "/sys/devices/system/cpu/";
    private static String sCpuInfoPath = "/proc/cpuinfo";
    private static String sCpuModelTitle = "Hardware";
    private static String sCpuPossiblePath = "/sys/devices/system/cpu/possible";
    private static String sCpuPresentPath = "/sys/devices/system/cpu/present";
    private static String sCpuVendorTag = "vendor";
    private static boolean sInitialized = false;
    private static String sModel = "unknown";
    private static final Object sModelLock = new Object();
    private static final Object sCpuCoresLock = new Object();
    private static final FileFilter CPU_FILTER = new FileFilter() { // from class: com.ttnet.org.chromium.base.CpuUtils$$ExternalSyntheticLambda0
        @Override // java.io.FileFilter
        public final boolean accept(File file) {
            return CpuUtils.lambda$static$0(file);
        }
    };

    public static void initialize() {
        if (sInitialized) {
            return;
        }
        if (fetchCPUModel() || fetchCPUCores()) {
            sInitialized = true;
        }
    }

    public static String getCPUModel() {
        String str;
        synchronized (sModelLock) {
            str = sModel;
        }
        return str;
    }

    public static String getCPUCores() {
        String valueOf;
        synchronized (sCpuCoresLock) {
            valueOf = String.valueOf(sCpuCores);
        }
        return valueOf;
    }

    private static boolean fetchCPUModel() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        String str = null;
        try {
            try {
                fileReader = new FileReader(sCpuInfoPath);
            } catch (Throwable th) {
                th = th;
            }
            try {
                bufferedReader2 = new BufferedReader(fileReader);
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine != null) {
                            if (readLine.contains(sCpuModelTitle) && readLine.contains(CertificateUtil.DELIMITER)) {
                                str = readLine.substring(readLine.indexOf(58) + 1).trim();
                                break;
                            }
                        } else {
                            break;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        android.util.Log.w(TAG, "Cannot get CPU model from " + sCpuInfoPath, e);
                        if (0 != 0) {
                            synchronized (sModelLock) {
                                sModel = null;
                            }
                        }
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (Exception unused) {
                            }
                        }
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (Exception unused2) {
                            }
                        }
                        return false;
                    }
                }
                if (str != null && str.contains(sCpuVendorTag)) {
                    str = str.substring(str.indexOf(sCpuVendorTag) + sCpuVendorTag.length() + 1).trim();
                }
                if (str != null) {
                    synchronized (sModelLock) {
                        sModel = str;
                    }
                }
                try {
                    bufferedReader2.close();
                } catch (Exception unused3) {
                }
                try {
                    fileReader.close();
                } catch (Exception unused4) {
                }
                return true;
            } catch (Exception e3) {
                e = e3;
                bufferedReader2 = null;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
                if (0 != 0) {
                    synchronized (sModelLock) {
                        sModel = null;
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception unused5) {
                    }
                }
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (Exception unused6) {
                    }
                }
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            fileReader = null;
            bufferedReader2 = null;
        } catch (Throwable th3) {
            th = th3;
            fileReader = null;
            bufferedReader = null;
        }
    }

    private static boolean fetchCPUCores() {
        return fetchCoresFromFile(sCpuPossiblePath) || fetchCoresFromFile(sCpuPresentPath) || fetchCoresFromFileList();
    }

    private static boolean fetchCoresFromFile(String str) {
        FileReader fileReader;
        Throwable th;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            fileReader = new FileReader(str);
            try {
                bufferedReader = new BufferedReader(fileReader);
                try {
                    String readLine = bufferedReader.readLine();
                    int firstInteger = (TextUtils.isEmpty(readLine) || !readLine.startsWith("0-")) ? -1 : getFirstInteger(readLine.substring(2)) + 1;
                    if (firstInteger != -1) {
                        synchronized (sCpuCoresLock) {
                            sCpuCores = firstInteger;
                        }
                    }
                    try {
                        bufferedReader.close();
                    } catch (Exception unused) {
                    }
                    try {
                        fileReader.close();
                    } catch (Exception unused2) {
                    }
                    return true;
                } catch (Exception unused3) {
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Exception unused4) {
                        }
                    }
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (Exception unused5) {
                        }
                    }
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception unused6) {
                        }
                    }
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (Exception unused7) {
                        }
                    }
                    throw th;
                }
            } catch (Exception unused8) {
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
            }
        } catch (Exception unused9) {
            fileReader = null;
        } catch (Throwable th4) {
            fileReader = null;
            th = th4;
            bufferedReader = null;
        }
    }

    private static int getFirstInteger(String str) {
        int i2;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= str.length()) {
                i3 = 0;
                i2 = 0;
                break;
            } else if (Character.isDigit(str.charAt(i3))) {
                i2 = i3 + 1;
                while (i2 < str.length() && Character.isDigit(str.charAt(i2))) {
                    i2++;
                }
            } else {
                i3++;
            }
        }
        try {
            return Integer.parseInt(str.substring(i3, i2));
        } catch (Exception unused) {
            return 0;
        }
    }

    private static boolean fetchCoresFromFileList() {
        File[] listFiles = new File(sCpuDirectory).listFiles(CPU_FILTER);
        if (listFiles != null) {
            synchronized (sCpuCoresLock) {
                sCpuCores = listFiles.length;
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$0(File file) {
        String name = file.getName();
        if (name.startsWith("cpu")) {
            for (int i2 = 3; i2 < name.length(); i2++) {
                if (!Character.isDigit(name.charAt(i2))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
