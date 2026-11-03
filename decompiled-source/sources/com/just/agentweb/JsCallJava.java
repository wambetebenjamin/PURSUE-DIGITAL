package com.just.agentweb;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import com.bytedance.frameworks.baselib.network.e;
import com.bytedance.sdk.account.platform.base.i;
import com.facebook.internal.security.CertificateUtil;
import gsdk.impl.account.toutiao.da;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class JsCallJava {
    private static final String[] IGNORE_UNSAFE_METHODS = {"getClass", "hashCode", da.el, "notifyAll", "equals", "toString", "wait"};
    private static final String KEY_ARGS = "args";
    private static final String KEY_METHOD = "method";
    private static final String KEY_OBJ = "obj";
    private static final String KEY_TYPES = "types";
    private static final String MSG_PROMPT_HEADER = "AgentWeb:";
    private static final String RETURN_RESULT_FORMAT = "{\"CODE\": %d, \"result\": %s}";
    private static final String TAG = "JsCallJava";
    private Object mInterfaceObj;
    private String mInterfacedName;
    private HashMap<String, Method> mMethodsMap;
    private String mPreloadInterfaceJs;

    public JsCallJava(Object obj, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                throw new Exception("injected name can not be null");
            }
            this.mInterfaceObj = obj;
            this.mInterfacedName = str;
            this.mMethodsMap = new HashMap<>();
            Method[] methods = this.mInterfaceObj.getClass().getMethods();
            StringBuilder sb = new StringBuilder("javascript:(function(b){console.log(\"");
            sb.append(this.mInterfacedName);
            sb.append(" init begin\");var a={queue:[],callback:function(){var d=Array.prototype.slice.call(arguments,0);var c=d.shift();var e=d.shift();this.queue[c].apply(this,d);if(!e){delete this.queue[c]}}};");
            for (Method method : methods) {
                Log.i("Info", "method:" + method);
                String genJavaMethodSign = genJavaMethodSign(method);
                if (genJavaMethodSign != null) {
                    this.mMethodsMap.put(genJavaMethodSign, method);
                    sb.append(String.format("a.%s=", method.getName()));
                }
            }
            sb.append("function(){var f=Array.prototype.slice.call(arguments,0);if(f.length<1){throw\"");
            sb.append(this.mInterfacedName);
            sb.append(" call result, message:miss method name\"}var e=[];for(var h=1;h<f.length;h++){var c=f[h];var j=typeof c;e[e.length]=j;if(j==\"function\"){var d=a.queue.length;a.queue[d]=c;f[h]=d}}var k = new Date().getTime();var l = f.shift();var m=prompt('");
            sb.append(MSG_PROMPT_HEADER);
            sb.append("'+JSON.stringify(");
            sb.append(promptMsgFormat("'" + this.mInterfacedName + "'", "l", e.f7575a, "f"));
            sb.append("));console.log(\"invoke \"+l+\", time: \"+(new Date().getTime()-k));var g=JSON.parse(m);if(g.CODE!=200){throw\"");
            sb.append(this.mInterfacedName);
            sb.append(" call result, CODE:\"+g.CODE+\", message:\"+g.result}return g.result};Object.getOwnPropertyNames(a).forEach(function(d){var c=a[d];if(typeof c===\"function\"&&d!==\"callback\"){a[d]=function(){return c.apply(a,[d].concat(Array.prototype.slice.call(arguments,0)))}}});b.");
            sb.append(this.mInterfacedName);
            sb.append("=a;console.log(\"");
            sb.append(this.mInterfacedName);
            sb.append(" init end\")})(window)");
            this.mPreloadInterfaceJs = sb.toString();
            sb.setLength(0);
        } catch (Exception e2) {
            if (LogUtils.isDebug()) {
                Log.e(TAG, "init js result:" + e2.getMessage());
            }
        }
    }

    private String genJavaMethodSign(Method method) {
        String name = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        for (String str : IGNORE_UNSAFE_METHODS) {
            if (str.equals(name)) {
                if (LogUtils.isDebug()) {
                    Log.w(TAG, "method(" + name + ") is unsafe, will be pass");
                    return null;
                }
                return null;
            }
        }
        for (Class<?> cls : parameterTypes) {
            if (cls == String.class) {
                name = name + "_S";
            } else if (cls == Integer.TYPE || cls == Long.TYPE || cls == Float.TYPE || cls == Double.TYPE) {
                name = name + "_N";
            } else if (cls == Boolean.TYPE) {
                name = name + "_B";
            } else if (cls == JSONObject.class) {
                name = name + "_O";
            } else if (cls == JsCallback.class) {
                name = name + "_F";
            } else {
                name = name + "_P";
            }
        }
        return name;
    }

    public String getPreloadInterfaceJs() {
        return this.mPreloadInterfaceJs;
    }

    public String call(WebView webView, JSONObject jSONObject) {
        long uptimeMillis = LogUtils.isDebug() ? SystemClock.uptimeMillis() : 0L;
        if (jSONObject != null) {
            try {
                String string = jSONObject.getString("method");
                JSONArray jSONArray = jSONObject.getJSONArray(KEY_TYPES);
                JSONArray jSONArray2 = jSONObject.getJSONArray("args");
                int length = jSONArray.length();
                Object[] objArr = new Object[length];
                int i2 = 0;
                for (int i3 = 0; i3 < length; i3++) {
                    String optString = jSONArray.optString(i3);
                    Object obj = null;
                    if ("string".equals(optString)) {
                        string = string + "_S";
                        if (!jSONArray2.isNull(i3)) {
                            obj = jSONArray2.getString(i3);
                        }
                        objArr[i3] = obj;
                    } else if (i.CT_KEY_PHONE.equals(optString)) {
                        string = string + "_N";
                        i2 = (i2 * 10) + i3 + 1;
                    } else if ("boolean".equals(optString)) {
                        string = string + "_B";
                        objArr[i3] = Boolean.valueOf(jSONArray2.getBoolean(i3));
                    } else if ("object".equals(optString)) {
                        string = string + "_O";
                        if (!jSONArray2.isNull(i3)) {
                            obj = jSONArray2.getJSONObject(i3);
                        }
                        objArr[i3] = obj;
                    } else if ("function".equals(optString)) {
                        string = string + "_F";
                        objArr[i3] = new JsCallback(webView, this.mInterfacedName, jSONArray2.getInt(i3));
                    } else {
                        string = string + "_P";
                    }
                }
                Method method = this.mMethodsMap.get(string);
                if (method == null) {
                    return getReturn(jSONObject, 500, "not found method(" + string + ") with valid parameters", uptimeMillis);
                }
                if (i2 > 0) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    while (i2 > 0) {
                        int i4 = (i2 - ((i2 / 10) * 10)) - 1;
                        Class<?> cls = parameterTypes[i4];
                        if (cls == Integer.TYPE) {
                            objArr[i4] = Integer.valueOf(jSONArray2.getInt(i4));
                        } else if (cls == Long.TYPE) {
                            objArr[i4] = Long.valueOf(Long.parseLong(jSONArray2.getString(i4)));
                        } else {
                            objArr[i4] = Double.valueOf(jSONArray2.getDouble(i4));
                        }
                        i2 /= 10;
                    }
                }
                return getReturn(jSONObject, 200, method.invoke(this.mInterfaceObj, objArr), uptimeMillis);
            } catch (Exception e2) {
                LogUtils.safeCheckCrash(TAG, "call", e2);
                if (e2.getCause() != null) {
                    return getReturn(jSONObject, 500, "method execute result:" + e2.getCause().getMessage(), uptimeMillis);
                }
                return getReturn(jSONObject, 500, "method execute result:" + e2.getMessage(), uptimeMillis);
            }
        }
        return getReturn(jSONObject, 500, "call data empty", uptimeMillis);
    }

    private String getReturn(JSONObject jSONObject, int i2, Object obj, long j2) {
        String valueOf;
        if (obj == null) {
            valueOf = "null";
        } else if (obj instanceof String) {
            valueOf = "\"".concat(String.valueOf(((String) obj).replace("\"", "\\\""))).concat("\"");
        } else {
            valueOf = String.valueOf(obj);
        }
        String format = String.format(RETURN_RESULT_FORMAT, Integer.valueOf(i2), valueOf);
        if (LogUtils.isDebug()) {
            Log.d(TAG, "call time: " + (SystemClock.uptimeMillis() - j2) + ", request: " + jSONObject + ", result:" + format);
        }
        return format;
    }

    private static String promptMsgFormat(String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(KEY_OBJ).append(CertificateUtil.DELIMITER).append(str).append(",");
        sb.append("method").append(CertificateUtil.DELIMITER).append(str2).append(",");
        sb.append(KEY_TYPES).append(CertificateUtil.DELIMITER).append(str3).append(",");
        sb.append("args").append(CertificateUtil.DELIMITER).append(str4);
        sb.append("}");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSafeWebViewCallMsg(String str) {
        return str.startsWith(MSG_PROMPT_HEADER);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JSONObject getMsgJSONObject(String str) {
        try {
            return new JSONObject(str.substring(9));
        } catch (JSONException e2) {
            e2.printStackTrace();
            return new JSONObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getInterfacedName(JSONObject jSONObject) {
        return jSONObject.optString(KEY_OBJ);
    }
}
