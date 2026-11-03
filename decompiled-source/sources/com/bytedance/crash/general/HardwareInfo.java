package com.bytedance.crash.general;

import android.content.Context;
import android.util.DisplayMetrics;
import com.bytedance.crash.util.q;
import com.bytedance.crash.util.u;
import java.io.File;
import java.io.Serializable;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class HardwareInfo implements Serializable {
    private static final String FILE_NAME = "hardware.inf";
    public static final String KEY_HW_CPU_ABI = "cpu_abi";
    public static final String KEY_HW_CPU_HARDWARE = "hardware";
    public static final String KEY_HW_CPU_MODEL = "cpu_model";
    public static final String KEY_HW_DENSITY_DPI = "density_dpi";
    public static final String KEY_HW_DISPLAY_DENSITY = "display_density";
    public static final String KEY_HW_DISPLAY_RESOLUTION = "resolution";
    public static final String KEY_HW_RAM_SIZE = "ram_size";
    public static final String KEY_HW_VENDOR_BRAND = "device_brand";
    public static final String KEY_HW_VENDOR_MANUFACTURER = "device_manufacturer";
    public static final String KEY_HW_VENDOR_MODEL = "device_model";
    private static final long serialVersionUID = 20241001154124L;
    private String mCpuAbi;
    private String mCpuHardware;
    private String mCpuModel;
    private String mDisplayDensity;
    private int mDisplayDensityDpi;
    private String mDisplayResolution;
    private long mJiffies;
    private String mRamSize;
    private String mVendorBrand;
    private String mVendorManufacturer;
    private String mVendorModel;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HardwareInfo get(Context context, File file) {
        HardwareInfo hardwareInfo;
        File file2 = new File(file, FILE_NAME);
        try {
            hardwareInfo = (HardwareInfo) u.a(file2);
            HardwareInfo hardwareInfo2 = hardwareInfo;
        } catch (Exception unused) {
            hardwareInfo = null;
        }
        if (hardwareInfo == null) {
            hardwareInfo = new HardwareInfo();
            hardwareInfo.mVendorModel = b.g();
            hardwareInfo.mVendorBrand = b.f();
            hardwareInfo.mVendorManufacturer = b.e();
            hardwareInfo.mJiffies = b.a();
            hardwareInfo.mCpuAbi = b.b();
            hardwareInfo.mCpuModel = b.d();
            hardwareInfo.mCpuHardware = b.c();
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            if (displayMetrics != null) {
                hardwareInfo.mDisplayResolution = b.a(displayMetrics);
                hardwareInfo.mDisplayDensity = b.c(displayMetrics);
                hardwareInfo.mDisplayDensityDpi = b.b(displayMetrics);
            }
            hardwareInfo.mRamSize = b.h();
            u.a(file2, hardwareInfo);
        }
        return hardwareInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void putTo(JSONObject jSONObject) {
        q.a(jSONObject, "device_brand", (Object) this.mVendorBrand);
        q.a(jSONObject, "device_model", (Object) this.mVendorModel);
        q.a(jSONObject, "device_manufacturer", (Object) this.mVendorManufacturer);
        q.a(jSONObject, "density_dpi", Integer.valueOf(this.mDisplayDensityDpi));
        q.a(jSONObject, "resolution", (Object) this.mDisplayResolution);
        q.a(jSONObject, "display_density", (Object) this.mDisplayDensity);
        q.a(jSONObject, "cpu_abi", (Object) this.mCpuAbi);
        q.a(jSONObject, KEY_HW_CPU_HARDWARE, (Object) this.mCpuHardware);
        q.a(jSONObject, KEY_HW_CPU_MODEL, (Object) this.mCpuModel);
        q.a(jSONObject, KEY_HW_RAM_SIZE, (Object) this.mRamSize);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getJiffy() {
        return this.mJiffies;
    }
}
