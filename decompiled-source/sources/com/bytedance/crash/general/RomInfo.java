package com.bytedance.crash.general;

import com.bytedance.crash.util.q;
import com.bytedance.crash.util.u;
import java.io.File;
import java.io.Serializable;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class RomInfo implements Serializable {
    private static final String FILE_NAME = "rom.inf";
    public static final String KEY_OS_TYPE = "os";
    private static final String KEY_ROM_HARMONY_OS = "is_hm_os";
    private static final String KEY_ROM_INCREMENTAL = "rom";
    public static final String KEY_ROM_KERNEL_VERSION = "kernel_version";
    public static final String KEY_ROM_OS_API = "os_api";
    public static final String KEY_ROM_OS_VERSION = "os_version";
    private static final String KEY_ROM_VERSION = "rom_version";
    private static final long serialVersionUID = 1;
    private boolean mIsHarmonyOs;
    private String mKernelVersion;
    private int mOsApiLevel;
    private String mOsVersion;
    private String mRandomDeviceId;
    private String mRomIncremental;
    private String mRomVersion;

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getRandomDeviceId() {
        return this.mRandomDeviceId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RomInfo get(File file) {
        RomInfo romInfo;
        File file2 = new File(file, FILE_NAME);
        String h2 = c.h();
        try {
            romInfo = (RomInfo) u.a(file2);
            RomInfo romInfo2 = romInfo;
        } catch (Exception unused) {
            romInfo = null;
        }
        if (romInfo == null || !h2.equals(String.valueOf(romInfo.mRomVersion))) {
            RomInfo romInfo3 = new RomInfo();
            romInfo3.mRomVersion = h2;
            romInfo3.mOsVersion = c.b();
            romInfo3.mIsHarmonyOs = c.f();
            romInfo3.mKernelVersion = c.e();
            romInfo3.mRomIncremental = c.g();
            romInfo3.mOsApiLevel = c.c();
            romInfo3.mRandomDeviceId = c.d();
            u.a(file2, romInfo3);
            return romInfo3;
        }
        return romInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void putTo(JSONObject jSONObject) {
        q.a(jSONObject, "os", (Object) com.bytedance.bdturing.utils.b.f4370d);
        q.a(jSONObject, "os_version", (Object) this.mOsVersion);
        q.a(jSONObject, "os_api", Integer.valueOf(this.mOsApiLevel));
        q.a(jSONObject, "is_hm_os", Boolean.valueOf(this.mIsHarmonyOs));
        q.a(jSONObject, "kernel_version", (Object) this.mKernelVersion);
        q.a(jSONObject, "rom", (Object) this.mRomIncremental);
        q.a(jSONObject, "rom_version", (Object) this.mRomVersion);
    }
}
