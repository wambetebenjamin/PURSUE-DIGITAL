package gsdk.impl.dynamic.DEFAULT;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.react.framework.RNConfig;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.main.internal.IMainInternalService;
import com.bytedance.ttgame.module.dynamic.modules.DynamicPackages;
import com.bytedance.ttgame.module.rn.api.IRNEngineService;
import com.bytedance.ttgame.sdk.module.core.internal.RocketConstants;
import com.facebook.react.ReactPackage;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DynamicUtils.java */
/* loaded from: classes10.dex */
public class ah {

    /* renamed from: b  reason: collision with root package name */
    private static ah f20092b;

    /* renamed from: a  reason: collision with root package name */
    private Application f20093a;

    /* renamed from: c  reason: collision with root package name */
    private Context f20094c;

    /* renamed from: d  reason: collision with root package name */
    private IRNEngineService f20095d;

    /* renamed from: e  reason: collision with root package name */
    private int f20096e = -1;

    /* renamed from: f  reason: collision with root package name */
    private int f20097f = -1;

    /* renamed from: g  reason: collision with root package name */
    private int f20098g = -1;

    public static ah a() {
        if (f20092b == null) {
            f20092b = new ah();
        }
        return f20092b;
    }

    private ah() {
    }

    public Context b() {
        if (this.f20094c == null) {
            this.f20094c = ((IMainInternalService) ModuleManager.INSTANCE.getService(IMainInternalService.class)).getAppContext();
        }
        return this.f20094c;
    }

    public Application c() {
        Context appContext;
        if (this.f20093a == null && (appContext = ((IMainInternalService) ModuleManager.INSTANCE.getService(IMainInternalService.class)).getAppContext()) != null) {
            this.f20093a = (Application) appContext;
        }
        return this.f20093a;
    }

    public boolean d() {
        int i2 = this.f20096e;
        if (i2 != -1) {
            return i2 == 1;
        }
        if (this.f20095d == null) {
            this.f20095d = (IRNEngineService) ModuleManager.INSTANCE.getService(IRNEngineService.class);
        }
        IRNEngineService iRNEngineService = this.f20095d;
        if (iRNEngineService != null) {
            boolean rNDebug = iRNEngineService.getRNDebug();
            this.f20096e = rNDebug ? 1 : 0;
            return rNDebug;
        }
        return false;
    }

    public static boolean a(Context context, String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            if (!TextUtils.isEmpty(str3)) {
                str2 = str3;
            }
            try {
                String[] list = context.getResources().getAssets().list(RNConfig.RN_ASSET_PATH + File.separator + str);
                if (list.length <= 0) {
                    return false;
                }
                for (String str4 : list) {
                    if ((str2 + ".bundle").equals(str4)) {
                        return true;
                    }
                }
            } catch (IOException unused) {
            }
        }
        return false;
    }

    public static boolean a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return false;
        }
        if (!TextUtils.isEmpty(str3)) {
            str2 = str3;
        }
        String a2 = af.a(str);
        if (TextUtils.isEmpty(a2)) {
            return false;
        }
        File file = new File(a2 + File.separator + str2 + ".bundle");
        return file.exists() && file.isFile() && file.length() > 0;
    }

    public static boolean[] a(String str) {
        if (TextUtils.isEmpty(str)) {
            return new boolean[]{false, false};
        }
        String a2 = af.a(str);
        if (TextUtils.isEmpty(a2)) {
            return new boolean[]{false, false};
        }
        File file = new File(a2);
        String[] list = file.list();
        if (list == null || file.length() < 1) {
            return new boolean[]{false, false};
        }
        boolean z = false;
        boolean z2 = false;
        for (int i2 = 0; i2 < list.length; i2++) {
            if (list[i2] != null && (list[i2].endsWith(".bundle") || "code".equals(list[i2]))) {
                z = true;
            }
            if ("bundle.json".equals(list[i2])) {
                z2 = true;
            }
        }
        return new boolean[]{z, z2};
    }

    public static boolean[] a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return new boolean[]{false, false};
        }
        try {
            String[] list = context.getResources().getAssets().list(RNConfig.RN_ASSET_PATH + File.separator + str);
            if (list.length <= 0) {
                return new boolean[]{false, false};
            }
            boolean z = false;
            boolean z2 = false;
            for (String str2 : list) {
                if ("bundle.json".equals(str2)) {
                    z2 = true;
                }
                if (str2 != null && str2.endsWith(".bundle")) {
                    z = true;
                }
            }
            return new boolean[]{z, z2};
        } catch (IOException unused) {
            return new boolean[]{false, false};
        }
    }

    public static HashMap<String, Object> b(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int i2 = 0;
        if (a(str)[0]) {
            i2 = 1;
        } else if (a(context, str)[0]) {
            i2 = 2;
        }
        HashMap<String, Object> a2 = i2 != 0 ? ae.a(context, str, i2) : null;
        if (a2 == null) {
            a2 = new HashMap<>();
        }
        a2.put(RNConfig.FRORM_WHERE, Integer.valueOf(i2));
        return a2;
    }

    public boolean b(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return false;
        }
        if (!TextUtils.isEmpty(str3)) {
            str2 = str3;
        }
        String a2 = af.a(str);
        if (TextUtils.isEmpty(a2)) {
            return false;
        }
        File file = new File(a2 + File.separator + str2 + ".bundle");
        return file.exists() && file.isFile() && file.length() > 0;
    }

    public boolean c(Context context, String str) {
        String[] list;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            list = context.getResources().getAssets().list(RNConfig.RN_ASSET_PATH + File.separator + str);
        } catch (IOException unused) {
        }
        if (list.length <= 0) {
            return false;
        }
        for (String str2 : list) {
            if ("bundle.json".equals(str2)) {
                return true;
            }
        }
        return false;
    }

    public static HashMap<String, Object> a(JSONObject jSONObject) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                Object obj = jSONObject.get(next);
                if (obj instanceof JSONArray) {
                    ArrayList arrayList = new ArrayList();
                    JSONArray jSONArray = (JSONArray) obj;
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        Object obj2 = jSONArray.get(i2);
                        if (obj2 instanceof JSONObject) {
                            arrayList.add(a((JSONObject) obj2));
                        }
                    }
                    hashMap.put(next, arrayList);
                } else if (obj instanceof JSONObject) {
                    hashMap.put(next.toString(), a((JSONObject) obj));
                } else if ((obj instanceof Boolean) || (obj instanceof Byte) || (obj instanceof Character) || (obj instanceof Double) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Short) || (obj instanceof String)) {
                    hashMap.put(next, obj);
                }
            } catch (JSONException e2) {
                Log.d("KV_Config", "parse to hashmap failed" + e2.toString());
                e2.printStackTrace();
            }
        }
        return hashMap;
    }

    public boolean e() {
        int i2 = this.f20097f;
        if (i2 != -1) {
            return i2 == 1;
        }
        if (this.f20095d == null) {
            this.f20095d = (IRNEngineService) ModuleManager.INSTANCE.getService(IRNEngineService.class);
        }
        IRNEngineService iRNEngineService = this.f20095d;
        if (iRNEngineService != null) {
            boolean isRNSupported = iRNEngineService.isRNSupported();
            this.f20097f = isRNSupported ? 1 : 0;
            return isRNSupported;
        }
        return false;
    }

    public static Bundle b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            Bundle bundle = new Bundle();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!jSONObject.isNull(next)) {
                    bundle.putString(next, jSONObject.get(next).toString());
                }
            }
            return bundle;
        } catch (Exception unused) {
            return new Bundle();
        }
    }

    public static void a(Bundle bundle, Map<String, ? extends Object> map) {
        Gson gson = new Gson();
        for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                if (entry.getValue() instanceof Integer) {
                    bundle.putInt(entry.getKey(), ((Integer) entry.getValue()).intValue());
                } else if (entry.getValue() instanceof Long) {
                    bundle.putLong(entry.getKey(), ((Long) entry.getValue()).longValue());
                } else if (entry.getValue() instanceof Float) {
                    bundle.putFloat(entry.getKey(), ((Float) entry.getValue()).floatValue());
                } else if (entry.getValue() instanceof Double) {
                    bundle.putDouble(entry.getKey(), ((Double) entry.getValue()).doubleValue());
                } else if (entry.getValue() instanceof String) {
                    bundle.putString(entry.getKey(), (String) entry.getValue());
                } else if (entry.getValue() instanceof Short) {
                    bundle.putShort(entry.getKey(), ((Short) entry.getValue()).shortValue());
                } else if (entry.getValue() instanceof Boolean) {
                    bundle.putBoolean(entry.getKey(), ((Boolean) entry.getValue()).booleanValue());
                } else if (entry.getValue() instanceof JSONObject) {
                    bundle.putString(entry.getKey(), ((JSONObject) entry.getValue()).toString());
                } else if (entry.getValue() instanceof JSONArray) {
                    bundle.putString(entry.getKey(), ((JSONArray) entry.getValue()).toString());
                } else if (entry.getValue() instanceof Serializable) {
                    bundle.putSerializable(entry.getKey(), (Serializable) entry.getValue());
                } else {
                    bundle.putString(entry.getKey(), gson.toJson(entry.getValue()));
                }
            }
        }
    }

    public void a(Exception exc, HashMap hashMap) {
        if (d()) {
            return;
        }
        String str = ((IMainInternalService) ModuleManager.INSTANCE.getService(IMainInternalService.class)).getSdkConfig().appId;
        if (hashMap == null) {
            hashMap = new HashMap();
        }
        hashMap.put("AID", str);
        hashMap.put(RocketConstants.GSDK_ENVIRONMENT, ((IMainInternalService) ModuleManager.INSTANCE.getService(IMainInternalService.class)).getEnv());
        a.a(exc, hashMap);
    }

    public boolean f() {
        if (this.f20098g == -1) {
            if (this.f20095d == null) {
                this.f20095d = (IRNEngineService) ModuleManager.INSTANCE.getService(IRNEngineService.class);
            }
            IRNEngineService iRNEngineService = this.f20095d;
            if (iRNEngineService == null) {
                return false;
            }
            this.f20098g = iRNEngineService.isEmulator();
        }
        return this.f20098g == 1;
    }

    public ArrayList<ReactPackage> g() {
        ArrayList<Object> reactPackages;
        ArrayList<ReactPackage> arrayList = new ArrayList<>();
        if (this.f20095d == null) {
            this.f20095d = (IRNEngineService) ModuleManager.INSTANCE.getService(IRNEngineService.class);
        }
        IRNEngineService iRNEngineService = this.f20095d;
        if (iRNEngineService != null && (reactPackages = iRNEngineService.getReactPackages()) != null) {
            Iterator<Object> it = reactPackages.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (next != null) {
                    arrayList.add((ReactPackage) next);
                }
            }
        }
        arrayList.add(new DynamicPackages());
        return arrayList;
    }

    public void h() {
        if (this.f20095d == null) {
            this.f20095d = (IRNEngineService) ModuleManager.INSTANCE.getService(IRNEngineService.class);
        }
        IRNEngineService iRNEngineService = this.f20095d;
        if (iRNEngineService != null) {
            iRNEngineService.checkupdate("dynamic", true, false);
        }
    }
}
