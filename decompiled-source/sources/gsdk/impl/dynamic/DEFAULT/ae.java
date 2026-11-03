package gsdk.impl.dynamic.DEFAULT;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.react.framework.RNConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: BundleInfoFormat.java */
/* loaded from: classes10.dex */
public class ae {
    public static String a(Context context, String str, boolean z) {
        String str2;
        boolean exists;
        if (z) {
            str2 = RNConfig.RN_ASSET_PATH + File.separator + str + File.separator + "bundle.json";
            exists = ah.a().c(context, str);
        } else {
            String a2 = af.a(str);
            if (TextUtils.isEmpty(a2)) {
                return null;
            }
            str2 = a2 + File.separator + "bundle.json";
            exists = new File(str2).exists();
        }
        if (exists) {
            return b(context, str2, z);
        }
        return null;
    }

    public static HashMap<String, Object> a(Context context, String str, int i2) {
        JSONObject jSONObject;
        String a2 = a(context, str, i2 == 2);
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        try {
            jSONObject = new JSONObject(a2);
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        try {
            return ah.a(jSONObject);
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static String b(Context context, String str, boolean z) {
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        try {
            if (z) {
                bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(str)));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(str))));
            }
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return sb.toString();
    }
}
