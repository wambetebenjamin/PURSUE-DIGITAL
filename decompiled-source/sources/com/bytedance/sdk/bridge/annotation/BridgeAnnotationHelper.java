package com.bytedance.sdk.bridge.annotation;

import android.text.TextUtils;
import com.bytedance.sdk.bridge.g;
import com.bytedance.sdk.bridge.h;
import com.bytedance.sdk.bridge.monitor.a;
import com.bytedance.sdk.bridge.o;
import com.bytedance.sdk.bridge.p;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class BridgeAnnotationHelper {
    private static final String TAG = "BridgeAnnotationHelper";
    private static Map<Class<?>, p> sSubscriberInfoIndex = new ConcurrentHashMap();

    public static void setSubscriberInfoIndex(Map<Class<?>, p> map) {
        if (map != null) {
            o.INSTANCE.d(TAG, "setSubscriberInfoIndex " + map.size());
            sSubscriberInfoIndex.putAll(map);
        }
    }

    public static Map<Class<?>, p> getSubscriberInfoIndex() {
        return sSubscriberInfoIndex;
    }

    public static p getSubscriberInfo(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        return getInfoByReflect(cls);
    }

    private static p getInfoByReflect(Class<?> cls) {
        if (cls != null) {
            o.INSTANCE.d(TAG, "getInfoByReflect - " + cls.getSimpleName());
        }
        p pVar = new p();
        LinkedList linkedList = new LinkedList();
        linkedList.add(cls);
        while (!linkedList.isEmpty()) {
            Class cls2 = (Class) linkedList.remove();
            Class<?>[] interfaces = cls2.getInterfaces();
            for (int i2 = 0; interfaces != null && i2 < interfaces.length; i2++) {
                if (IBridgeModule.class.isAssignableFrom(interfaces[i2]) && interfaces[i2] != IBridgeModule.class) {
                    linkedList.add(interfaces[i2]);
                }
            }
            Class superclass = cls2.getSuperclass();
            if (superclass != null && !isSystemClass(superclass.getName())) {
                linkedList.add(superclass);
            }
            parseBridgeClass(cls2, pVar);
        }
        return pVar;
    }

    private static void parseBridgeClass(Class cls, p pVar) {
        Method[] methodArr;
        Method[] methodArr2;
        Object obj;
        Object defaultString;
        p pVar2 = sSubscriberInfoIndex.get(cls);
        if (pVar2 != null) {
            for (g gVar : pVar2.getMethodInfos()) {
                if (!pVar.hasBridgeMethod(gVar.getBridgeMethodName())) {
                    pVar.putMethodInfo(gVar.getBridgeMethodName(), gVar);
                }
            }
            return;
        }
        p pVar3 = new p();
        Method[] declaredMethods = cls.getDeclaredMethods();
        int length = declaredMethods.length;
        int i2 = 0;
        while (i2 < length) {
            Method method = declaredMethods[i2];
            BridgeMethod bridgeMethod = (BridgeMethod) method.getAnnotation(BridgeMethod.class);
            if (bridgeMethod != null) {
                String value = bridgeMethod.value();
                if (!TextUtils.isEmpty(value) && !pVar.hasBridgeMethod(value)) {
                    method.setAccessible(true);
                    String privilege = bridgeMethod.privilege();
                    String sync = bridgeMethod.sync();
                    Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    h[] hVarArr = new h[parameterAnnotations.length];
                    int i3 = 0;
                    while (i3 < parameterAnnotations.length) {
                        int i4 = 0;
                        while (true) {
                            if (i4 >= parameterAnnotations[i3].length) {
                                methodArr2 = declaredMethods;
                                break;
                            } else if (parameterAnnotations[i3][i4] instanceof BridgeParam) {
                                BridgeParam bridgeParam = (BridgeParam) parameterAnnotations[i3][i4];
                                Class<?> cls2 = parameterTypes[i3];
                                String value2 = bridgeParam.value();
                                methodArr2 = declaredMethods;
                                if (cls2 == Integer.TYPE) {
                                    defaultString = Integer.valueOf(bridgeParam.defaultInt());
                                } else if (cls2 == Long.TYPE) {
                                    defaultString = Long.valueOf(bridgeParam.defaultLong());
                                } else if (cls2 == Boolean.TYPE) {
                                    defaultString = Boolean.valueOf(bridgeParam.defaultBoolean());
                                } else if (cls2 == Double.TYPE) {
                                    defaultString = Double.valueOf(bridgeParam.defaultDouble());
                                } else if (cls2 == Float.TYPE) {
                                    defaultString = Float.valueOf(bridgeParam.defaultFloat());
                                } else if (cls2 == String.class) {
                                    defaultString = bridgeParam.defaultString();
                                } else {
                                    obj = null;
                                    hVarArr[i3] = new h(0, cls2, value2, obj, bridgeParam.required());
                                }
                                obj = defaultString;
                                hVarArr[i3] = new h(0, cls2, value2, obj, bridgeParam.required());
                            } else {
                                methodArr2 = declaredMethods;
                                if (parameterAnnotations[i3][i4] instanceof BridgeContext) {
                                    hVarArr[i3] = new h(1);
                                    break;
                                } else {
                                    i4++;
                                    declaredMethods = methodArr2;
                                }
                            }
                        }
                        if (hVarArr[i3] == null) {
                            throw new IllegalArgumentException("Bridge param must be Annotated! Please check the bridge method [" + value + "]");
                        }
                        i3++;
                        declaredMethods = methodArr2;
                    }
                    methodArr = declaredMethods;
                    g gVar2 = new g(method, value, privilege, sync, hVarArr);
                    pVar.putMethodInfo(value, gVar2);
                    pVar3.putMethodInfo(value, gVar2);
                    i2++;
                    declaredMethods = methodArr;
                }
            }
            methodArr = declaredMethods;
            i2++;
            declaredMethods = methodArr;
        }
        if (pVar3.getMethodInfos().isEmpty()) {
            return;
        }
        sSubscriberInfoIndex.put(cls, pVar3);
    }

    private static p getInfoByIndex(Class<?> cls) {
        p pVar = null;
        while (cls != null && !isSystemClass(cls.getName())) {
            if (sSubscriberInfoIndex.containsKey(cls)) {
                for (g gVar : sSubscriberInfoIndex.get(cls).getMethodInfos()) {
                    if (pVar == null) {
                        pVar = new p();
                    }
                    if (!pVar.hasBridgeMethod(gVar.getBridgeMethodName())) {
                        pVar.putMethodInfo(gVar.getBridgeMethodName(), gVar);
                    } else {
                        JSONObject jSONObject = new JSONObject();
                        JSONObject jSONObject2 = new JSONObject();
                        try {
                            jSONObject2.put("error_msg", "There is already a bridge method named [" + gVar.getBridgeMethodName() + "], and the old one will be overwritten.");
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                        a.INSTANCE.monitorEvent(1, a.STATUS_MSG_GET_INFO_BY_INDEX, jSONObject, jSONObject2);
                        o.INSTANCE.w(TAG, "NOTE！NOTE！NOTE！ There is already a bridge method named [" + gVar.getBridgeMethodName() + "], and the old one will be overwritten.");
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        return pVar;
    }

    private static boolean isSystemClass(String str) {
        return str.startsWith("java.") || str.startsWith("javax.") || str.startsWith("android.");
    }
}
