package com.oasis.location;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public abstract class LocationAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static LocationAgent f17542a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17543b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17544c = true;

    /* loaded from: classes9.dex */
    class a implements DialogInterface.OnClickListener {
        a() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            ActivityManager.getActivity().startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 10);
        }
    }

    /* loaded from: classes9.dex */
    class b implements DialogInterface.OnClickListener {
        b() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
        }
    }

    public LocationAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static LocationAgent createInstance(Context context) {
        if (f17544c || f17542a == null) {
            try {
                String string = context.getString(R.string.location_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("LocationAgent", "Create the LocationAgent:" + string);
                    f17542a = (LocationAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("LocationAgent", "Create location agent failed.", e2);
            }
            if (f17542a == null) {
                Logger.i("LocationAgent", "Create default LocationAgent.");
                f17542a = new com.oasis.location.a();
                f17543b = true;
            }
            return f17542a;
        }
        throw new AssertionError();
    }

    public static LocationAgent getInstance() {
        if (f17544c || f17542a != null) {
            return f17542a;
        }
        throw new AssertionError();
    }

    public void asyncGetLocationByIp(LocationListener locationListener) {
    }

    public void deletePOI(String str, String str2, LBSListener lBSListener) {
    }

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public void getDetailedLocation(LocationListener locationListener) {
    }

    public abstract void getLocation(LocationListener locationListener);

    public String getLocationByIp() {
        return "";
    }

    public void getNearPOI(String str, double d2, double d3, int i2, String str2, LBSListener lBSListener) {
    }

    public void getNearPOIWithCoordinate(String str, double d2, double d3, double d4, double d5, int i2, String str2, LBSListener lBSListener) {
    }

    public boolean isUseDefault() {
        return f17543b;
    }

    public boolean needRequestStoragePermission() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void openGpsGuide() {
        new AlertDialog.Builder(ActivityManager.getActivity()).setTitle("提示").setMessage("当前应用需要打开定位功能。\n\n请点击\"设置\"-\"定位服务\"-打开定位功能。").setNegativeButton("取消", new b()).setPositiveButton("设置", new a()).setCancelable(false).show();
    }

    public void reportPOI(String str, String str2, String str3, LBSListener lBSListener) {
    }

    public void reportPOIWithCoordinate(String str, String str2, String str3, double d2, double d3, LBSListener lBSListener) {
    }

    public void requestAndUpdateCurrentLocation(LBSListener lBSListener) {
    }

    public void requestPermission() {
    }
}
