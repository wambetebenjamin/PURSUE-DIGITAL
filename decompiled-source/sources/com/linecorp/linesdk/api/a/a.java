package com.linecorp.linesdk.api.a;

import com.linecorp.linesdk.LineAccessToken;
import com.linecorp.linesdk.LineApiResponse;
import com.linecorp.linesdk.api.LineApiClient;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes9.dex */
public final class a {

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.linecorp.linesdk.api.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public static class C0312a implements InvocationHandler {

        /* renamed from: a  reason: collision with root package name */
        private final LineApiClient f17282a;

        /* renamed from: b  reason: collision with root package name */
        private final Map<Method, Boolean> f17283b;

        public /* synthetic */ C0312a(LineApiClient lineApiClient, byte b2) {
            this(lineApiClient);
        }

        private C0312a(LineApiClient lineApiClient) {
            this.f17282a = lineApiClient;
            this.f17283b = new ConcurrentHashMap(0);
        }

        @Override // java.lang.reflect.InvocationHandler
        public final Object invoke(Object obj, Method method, Object[] objArr) {
            try {
                Object invoke = method.invoke(this.f17282a, objArr);
                if (a(method)) {
                    if ((invoke instanceof LineApiResponse) && ((LineApiResponse) invoke).getErrorData().getHttpResponseCode() == 403) {
                        LineApiResponse<LineAccessToken> refreshAccessToken = this.f17282a.refreshAccessToken();
                        if (!refreshAccessToken.isSuccess()) {
                            return refreshAccessToken.isNetworkError() ? refreshAccessToken : invoke;
                        }
                        try {
                            return method.invoke(this.f17282a, objArr);
                        } catch (InvocationTargetException e2) {
                            throw e2.getTargetException();
                        }
                    }
                }
                return invoke;
            } catch (InvocationTargetException e3) {
                throw e3.getTargetException();
            }
        }

        private boolean a(Method method) {
            Boolean bool = this.f17283b.get(method);
            if (bool != null) {
                return bool.booleanValue();
            }
            String name = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class<?> cls = this.f17282a.getClass(); cls != null; cls = cls.getSuperclass()) {
                if (((c) cls.getDeclaredMethod(name, parameterTypes).getAnnotation(c.class)) != null) {
                    this.f17283b.put(method, true);
                    return true;
                }
                continue;
            }
            this.f17283b.put(method, false);
            return false;
        }
    }
}
