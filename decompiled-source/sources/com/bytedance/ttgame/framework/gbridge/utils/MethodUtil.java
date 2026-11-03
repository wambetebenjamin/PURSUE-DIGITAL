package com.bytedance.ttgame.framework.gbridge.utils;

import android.text.TextUtils;
import com.bytedance.ttgame.framework.gbridge.IBridgeModule;
import com.bytedance.ttgame.framework.gbridge.annotation.GBridgeMethod;
import com.bytedance.ttgame.framework.gbridge.annotation.GBridgeParam;
import com.bytedance.ttgame.framework.gbridge.exception.MethodExistException;
import com.bytedance.ttgame.framework.gbridge.model.MethodInfo;
import com.bytedance.ttgame.framework.gbridge.model.ParamInfo;
import com.facebook.internal.security.CertificateUtil;
import com.meituan.robust.Constants;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes3.dex */
public class MethodUtil {
    public static void parseModule(IBridgeModule iBridgeModule, Map<String, MethodInfo> map) {
        Method[] methodArr;
        Method[] methodArr2;
        Method[] declaredMethods = iBridgeModule.getClass().getDeclaredMethods();
        int length = declaredMethods.length;
        int i2 = 0;
        while (i2 < length) {
            Method method = declaredMethods[i2];
            GBridgeMethod gBridgeMethod = (GBridgeMethod) method.getAnnotation(GBridgeMethod.class);
            if (gBridgeMethod != null) {
                MethodInfo methodInfo = new MethodInfo();
                methodInfo.setSync(gBridgeMethod.sync());
                methodInfo.setMixCall(gBridgeMethod.mixCall());
                methodInfo.setThreading(gBridgeMethod.threading());
                if (!TextUtils.isEmpty(gBridgeMethod.callName())) {
                    methodInfo.setCallName(gBridgeMethod.callName());
                } else {
                    methodInfo.setCallName(method.getName());
                }
                ArrayList arrayList = new ArrayList();
                Class<?>[] parameterTypes = method.getParameterTypes();
                Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                if (parameterAnnotations != null && parameterTypes != null) {
                    int i3 = 0;
                    int i4 = 0;
                    while (i3 < parameterAnnotations.length) {
                        Annotation[] annotationArr = parameterAnnotations[i3];
                        if (annotationArr != null) {
                            int length2 = annotationArr.length;
                            int i5 = 0;
                            while (i5 < length2) {
                                Annotation annotation = annotationArr[i5];
                                ParamInfo paramInfo = new ParamInfo();
                                GBridgeParam gBridgeParam = (GBridgeParam) annotation;
                                paramInfo.setName(gBridgeParam.value());
                                paramInfo.setOptional(gBridgeParam.optional());
                                paramInfo.setType(parameterTypes[i4].getName());
                                paramInfo.setSimpleType(parameterTypes[i4].getSimpleName());
                                arrayList.add(paramInfo);
                                i5++;
                                declaredMethods = declaredMethods;
                            }
                            methodArr2 = declaredMethods;
                            i4++;
                        } else {
                            methodArr2 = declaredMethods;
                        }
                        i3++;
                        declaredMethods = methodArr2;
                    }
                }
                methodArr = declaredMethods;
                methodInfo.setMethod(method);
                methodInfo.setObj(iBridgeModule);
                methodInfo.setParams(arrayList);
                if (map.get(methodInfo.getCallName()) != null) {
                    throw new MethodExistException("Method:[" + methodInfo.getCallName() + "] exist,Make sure every method have different name.");
                }
                map.put(methodInfo.getCallName(), methodInfo);
                if (LogUtil.DEBUG) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(Constants.ARRAY_TYPE).append(methodInfo.getCallName()).append("]");
                    sb.append(method.getName());
                    sb.append("(");
                    int i6 = 0;
                    while (i6 < arrayList.size()) {
                        ParamInfo paramInfo2 = arrayList.get(i6);
                        sb.append(paramInfo2.getName());
                        sb.append(CertificateUtil.DELIMITER);
                        sb.append(paramInfo2.getSimpleType());
                        i6++;
                        if (i6 < arrayList.size()) {
                            sb.append(",");
                        }
                    }
                    sb.append(")");
                    LogUtil.d(sb.toString());
                }
            } else {
                methodArr = declaredMethods;
            }
            i2++;
            declaredMethods = methodArr;
        }
    }

    public static Object callMethod(Method method, Object obj, List<Object> list) {
        try {
            return method.invoke(obj, list.toArray());
        } catch (Exception e2) {
            LogUtil.e("callMethod", e2);
            return null;
        }
    }
}
