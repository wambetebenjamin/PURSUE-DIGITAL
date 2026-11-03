package com.bytedance.crash.general;

import com.bytedance.crash.entity.Header;
import com.bytedance.crash.util.q;
import com.bytedance.crash.util.u;
import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class AppInfo implements Serializable {
    private static final String KEY_APP_DISPLAY_NAME = "display_name";
    private static final String KEY_APP_PACKAGE = "package";
    private static final String KEY_APP_RELEASE_BUILD = "release_build";
    private static final String KEY_APP_VERIFY_INFO = "verify_info";
    private static final String KEY_NPTH_BYTRACE_ID = "bytrace_id";
    public static final String KEY_NPTH_VERSION_CODE = "sdk_version";
    public static final String KEY_NPTH_VERSION_NAME = "sdk_version_name";
    private static final int MAX_FILE_COUNT = 3;
    private static final String SUFFIX = ".aif";
    private static final long TOW_WEEKS = 1209600000;
    private static final long serialVersionUID = 1;
    Map<String, Object> mAppExtension;
    int mAppVersionCode;
    String mAppVersionName;
    String mBitWide;
    String mDisplayName;
    long mLastUpdateTime;
    String mNpthBytraceId;
    int mNpthVersionCode;
    String mNpthVersionName;
    String mPackageName;
    String mReleaseBuild;

    private static File[] getSortedFilesArray(File file) {
        File[] listFiles = file.listFiles(new FilenameFilter() { // from class: com.bytedance.crash.general.AppInfo.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str) {
                return str.endsWith(AppInfo.SUFFIX);
            }
        });
        if (listFiles != null) {
            Arrays.sort(listFiles, new Comparator<File>() { // from class: com.bytedance.crash.general.AppInfo.2
                @Override // java.util.Comparator
                /* renamed from: a */
                public int compare(File file2, File file3) {
                    return Long.compare(AppInfo.getLastUpdateTime(file3), AppInfo.getLastUpdateTime(file2));
                }
            });
        }
        return listFiles;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AppInfo findAppInfo(File file, long j2) {
        File[] sortedFilesArray = getSortedFilesArray(file);
        if (sortedFilesArray != null) {
            for (File file2 : sortedFilesArray) {
                try {
                    AppInfo appInfo = (AppInfo) u.a(file2);
                    AppInfo appInfo2 = appInfo;
                    if (appInfo != null && j2 > appInfo.mLastUpdateTime) {
                        return appInfo;
                    }
                } catch (Throwable unused) {
                }
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.bytedance.crash.general.AppInfo get(android.content.Context r7, java.io.File r8) {
        /*
            java.lang.String r0 = r7.getPackageName()
            r1 = 0
            android.content.pm.PackageManager r2 = r7.getPackageManager()     // Catch: java.lang.Throwable -> L37
            r3 = 0
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r0, r3)     // Catch: java.lang.Throwable -> L37
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L34
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L34
            r4.<init>()     // Catch: java.lang.Throwable -> L34
            long r5 = r2.lastUpdateTime     // Catch: java.lang.Throwable -> L34
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch: java.lang.Throwable -> L34
            java.lang.String r5 = ".aif"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch: java.lang.Throwable -> L34
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L34
            r3.<init>(r8, r4)     // Catch: java.lang.Throwable -> L34
            java.lang.Object r1 = com.bytedance.crash.util.u.a(r3)     // Catch: java.lang.Throwable -> L35
            com.bytedance.crash.general.AppInfo r1 = (com.bytedance.crash.general.AppInfo) r1     // Catch: java.lang.Throwable -> L35
            r4 = r1
            com.bytedance.crash.general.AppInfo r4 = (com.bytedance.crash.general.AppInfo) r4     // Catch: java.lang.Throwable -> L35
            if (r1 == 0) goto L39
            return r1
        L34:
            r3 = r1
        L35:
            r1 = r2
            goto L38
        L37:
            r3 = r1
        L38:
            r2 = r1
        L39:
            com.bytedance.crash.general.AppInfo r1 = new com.bytedance.crash.general.AppInfo
            r1.<init>()
            r1.mPackageName = r0
            boolean r0 = com.bytedance.crash.jni.NativeBridge.i()
            if (r0 == 0) goto L49
            java.lang.String r0 = "64"
            goto L4b
        L49:
            java.lang.String r0 = "32"
        L4b:
            r1.mBitWide = r0
            java.lang.String r0 = com.bytedance.crash.config.a.b()
            r1.mReleaseBuild = r0
            r0 = 4020188(0x3d57dc, float:5.633483E-39)
            r1.mNpthVersionCode = r0
            java.lang.String r0 = com.bytedance.crash.config.h.f6371a
            r1.mNpthVersionName = r0
            java.lang.String r0 = com.bytedance.crash.util.c.a()
            r1.mNpthBytraceId = r0
            java.util.Map r0 = com.bytedance.crash.config.a.a()
            r1.mAppExtension = r0
            if (r2 == 0) goto L86
            long r4 = r2.lastUpdateTime
            r1.mLastUpdateTime = r4
            int r0 = r2.versionCode
            r1.mAppVersionCode = r0
            java.lang.String r0 = r2.versionName
            r1.mAppVersionName = r0
            android.content.pm.ApplicationInfo r0 = r2.applicationInfo
            if (r0 == 0) goto L86
            android.content.pm.ApplicationInfo r0 = r2.applicationInfo
            int r0 = r0.labelRes
            if (r0 <= 0) goto L86
            java.lang.String r7 = r7.getString(r0)
            r1.mDisplayName = r7
        L86:
            if (r3 == 0) goto L8e
            clear(r8)
            com.bytedance.crash.util.u.a(r3, r1)
        L8e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.crash.general.AppInfo.get(android.content.Context, java.io.File):com.bytedance.crash.general.AppInfo");
    }

    private static void clear(File file) {
        File[] sortedFilesArray = getSortedFilesArray(file);
        if (sortedFilesArray != null) {
            if (sortedFilesArray.length > 3) {
                long currentTimeMillis = System.currentTimeMillis();
                for (int i2 = 3; i2 < sortedFilesArray.length; i2++) {
                    long lastUpdateTime = getLastUpdateTime(sortedFilesArray[i2]);
                    if (lastUpdateTime < 0 || currentTimeMillis - lastUpdateTime > TOW_WEEKS) {
                        sortedFilesArray[i2].delete();
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long getLastUpdateTime(File file) {
        try {
            String name = file.getName();
            return Long.parseLong(name.substring(0, name.length() - 4));
        } catch (Throwable unused) {
            return -1L;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void putTo(JSONObject jSONObject) {
        q.a(jSONObject, "package", (Object) this.mPackageName);
        q.a(jSONObject, "display_name", (Object) this.mDisplayName);
        q.a(jSONObject, "app_version", (Object) this.mAppVersionName);
        q.a(jSONObject, "version_code", Integer.valueOf(this.mAppVersionCode));
        q.a(jSONObject, "verify_info", (Object) this.mBitWide);
        q.a(jSONObject, "release_build", (Object) this.mReleaseBuild);
        q.a(jSONObject, KEY_NPTH_BYTRACE_ID, (Object) this.mNpthBytraceId);
        q.a(jSONObject, "sdk_version", Integer.valueOf(this.mNpthVersionCode));
        q.a(jSONObject, "sdk_version_name", (Object) this.mNpthVersionName);
        q.a(jSONObject, Header.f6637c, Long.valueOf(this.mLastUpdateTime));
        Map<String, Object> map = this.mAppExtension;
        if (map != null) {
            for (String str : map.keySet()) {
                q.a(jSONObject, str, this.mAppExtension.get(str));
            }
        }
    }
}
