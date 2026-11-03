package com.bytedance.retrofit2;

import com.bytedance.retrofit2.BuiltInConverters;
import com.bytedance.retrofit2.CallAdapter;
import com.bytedance.retrofit2.Converter;
import com.bytedance.retrofit2.cache.ICacheServer;
import com.bytedance.retrofit2.client.Client;
import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.intercept.Interceptor;
import com.bytedance.retrofit2.mime.TypedInput;
import com.bytedance.retrofit2.mime.TypedOutput;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public final class Retrofit {
    @Nullable
    private static volatile CopyOnWriteArrayList<Interceptor> sCommonInterceptors;
    @Nullable
    private final ICacheServer cacheServer;
    private final List<CallAdapter.Factory> callAdapterFactories;
    private final Executor callbackExecutor;
    private final Client.Provider clientProvider;
    private final List<Converter.Factory> converterFactories;
    private final Executor httpExecutor;
    private final List<Interceptor> interceptors;
    private final Endpoint server;
    private final Map<Method, ServiceMethod<?>> serviceMethodCache;
    private final ConcurrentHashMap<Method, Object> serviceMethodCacheNew;
    private final boolean validateEagerly;

    Retrofit(Endpoint endpoint, Client.Provider provider, List<Interceptor> list, List<Converter.Factory> list2, List<CallAdapter.Factory> list3, Executor executor, Executor executor2, boolean z) {
        this(endpoint, provider, list, list2, list3, executor, executor2, z, null);
    }

    Retrofit(Endpoint endpoint, Client.Provider provider, List<Interceptor> list, List<Converter.Factory> list2, List<CallAdapter.Factory> list3, Executor executor, Executor executor2, boolean z, @Nullable ICacheServer iCacheServer) {
        this.serviceMethodCache = new ConcurrentHashMap();
        this.serviceMethodCacheNew = new ConcurrentHashMap<>();
        this.server = endpoint;
        this.clientProvider = provider;
        this.interceptors = list;
        this.converterFactories = Collections.unmodifiableList(list2);
        this.callAdapterFactories = Collections.unmodifiableList(list3);
        this.httpExecutor = executor;
        this.callbackExecutor = executor2;
        this.validateEagerly = z;
        this.cacheServer = iCacheServer;
    }

    public static void setCommonInterceptor(CopyOnWriteArrayList<Interceptor> copyOnWriteArrayList) {
        sCommonInterceptors = copyOnWriteArrayList;
    }

    public <T> T create(final Class<T> cls) {
        Utils.validateServiceInterface(cls);
        if (this.validateEagerly) {
            eagerlyValidateMethods(cls);
        }
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: com.bytedance.retrofit2.Retrofit.1
            private final Platform platform = Platform.get();
            private final Object[] emptyArgs = new Object[0];

            @Override // java.lang.reflect.InvocationHandler
            @Nullable
            public Object invoke(Object obj, Method method, @Nullable Object[] objArr) {
                if (method.getDeclaringClass() == Object.class) {
                    return method.invoke(this, objArr);
                }
                if (this.platform.isDefaultMethod(method)) {
                    return this.platform.invokeDefaultMethod(method, cls, obj, objArr);
                }
                ServiceMethod<?> loadServiceMethod = Retrofit.this.loadServiceMethod(method);
                if (objArr == null) {
                    objArr = this.emptyArgs;
                }
                return loadServiceMethod.invoke(objArr);
            }
        });
    }

    private void eagerlyValidateMethods(Class<?> cls) {
        Method[] declaredMethods;
        Platform platform = Platform.get();
        for (Method method : cls.getDeclaredMethods()) {
            if (!platform.isDefaultMethod(method) && !Modifier.isStatic(method.getModifiers())) {
                loadServiceMethod(method);
            }
        }
    }

    ServiceMethod<?> loadServiceMethod(Method method) {
        ServiceMethod<?> serviceMethod;
        if (RetrofitConfig.isLoadServiceMethodOptOpen()) {
            return loadServiceMethodNew(method);
        }
        ServiceMethod<?> serviceMethod2 = this.serviceMethodCache.get(method);
        if (serviceMethod2 != null) {
            if (serviceMethod2 instanceof HttpServiceMethod) {
                RetrofitMetrics retrofitMetrics = new RetrofitMetrics();
                retrofitMetrics.bizInvokeRetrofitRequestInterfaceStart(true);
                ((HttpServiceMethod) serviceMethod2).requestFactory.setRetrofitMetrics(retrofitMetrics);
            }
            return serviceMethod2;
        }
        synchronized (this.serviceMethodCache) {
            serviceMethod = this.serviceMethodCache.get(method);
            if (serviceMethod == null) {
                RetrofitMetrics retrofitMetrics2 = new RetrofitMetrics();
                retrofitMetrics2.bizInvokeRetrofitRequestInterfaceStart(false);
                serviceMethod = ServiceMethod.parseAnnotations(this, method, retrofitMetrics2);
                this.serviceMethodCache.put(method, serviceMethod);
            }
        }
        return serviceMethod;
    }

    ServiceMethod<?> loadServiceMethodNew(Method method) {
        ServiceMethod<?> serviceMethod;
        Object obj = this.serviceMethodCacheNew.get(method);
        if (obj instanceof ServiceMethod) {
            if (obj instanceof HttpServiceMethod) {
                RetrofitMetrics retrofitMetrics = new RetrofitMetrics();
                retrofitMetrics.bizInvokeRetrofitRequestInterfaceStart(true);
                ((HttpServiceMethod) obj).requestFactory.setRetrofitMetrics(retrofitMetrics);
            }
            return (ServiceMethod) obj;
        }
        if (obj == null) {
            Object obj2 = new Object();
            synchronized (obj2) {
                obj = this.serviceMethodCacheNew.putIfAbsent(method, obj2);
                if (obj == null) {
                    RetrofitMetrics retrofitMetrics2 = new RetrofitMetrics();
                    retrofitMetrics2.bizInvokeRetrofitRequestInterfaceStart(false);
                    ServiceMethod<?> parseAnnotations = ServiceMethod.parseAnnotations(this, method, retrofitMetrics2);
                    this.serviceMethodCacheNew.put(method, parseAnnotations);
                    return parseAnnotations;
                }
            }
        }
        synchronized (obj) {
            serviceMethod = (ServiceMethod) this.serviceMethodCacheNew.get(method);
        }
        return serviceMethod;
    }

    public Client.Provider clientProvider() {
        return this.clientProvider;
    }

    public Executor httpExecutor() {
        return this.httpExecutor;
    }

    public List<Interceptor> interceptors() {
        return this.interceptors;
    }

    @Nullable
    public ICacheServer cacheServer() {
        return this.cacheServer;
    }

    public Endpoint server() {
        return this.server;
    }

    public List<CallAdapter.Factory> callAdapterFactories() {
        return this.callAdapterFactories;
    }

    public CallAdapter<?, ?> callAdapter(Type type, Annotation[] annotationArr) {
        return nextCallAdapter(null, type, annotationArr);
    }

    public CallAdapter<?, ?> nextCallAdapter(@Nullable CallAdapter.Factory factory, Type type, Annotation[] annotationArr) {
        Utils.checkNotNull(type, "returnType == null");
        Utils.checkNotNull(annotationArr, "annotations == null");
        int indexOf = this.callAdapterFactories.indexOf(factory) + 1;
        int size = this.callAdapterFactories.size();
        for (int i2 = indexOf; i2 < size; i2++) {
            CallAdapter<?, ?> callAdapter = this.callAdapterFactories.get(i2).get(type, annotationArr, this);
            if (callAdapter != null) {
                return callAdapter;
            }
        }
        StringBuilder append = new StringBuilder("Could not locate call adapter for ").append(type).append(".\n");
        if (factory != null) {
            append.append("  Skipped:");
            for (int i3 = 0; i3 < indexOf; i3++) {
                append.append("\n   * ").append(this.callAdapterFactories.get(i3).getClass().getName());
            }
            append.append('\n');
        }
        append.append("  Tried:");
        int size2 = this.callAdapterFactories.size();
        while (indexOf < size2) {
            append.append("\n   * ").append(this.callAdapterFactories.get(indexOf).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(append.toString());
    }

    public List<Converter.Factory> converterFactories() {
        return this.converterFactories;
    }

    public <T> Converter<T, TypedOutput> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        return nextRequestBodyConverter(null, type, annotationArr, annotationArr2);
    }

    public <T> Converter<T, TypedOutput> nextRequestBodyConverter(@Nullable Converter.Factory factory, Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(annotationArr, "parameterAnnotations == null");
        Utils.checkNotNull(annotationArr2, "methodAnnotations == null");
        int indexOf = this.converterFactories.indexOf(factory) + 1;
        int size = this.converterFactories.size();
        for (int i2 = indexOf; i2 < size; i2++) {
            Converter<T, TypedOutput> converter = (Converter<T, TypedOutput>) this.converterFactories.get(i2).requestBodyConverter(type, annotationArr, annotationArr2, this);
            if (converter != null) {
                return converter;
            }
        }
        StringBuilder append = new StringBuilder("Could not locate RequestBody converter for ").append(type).append(".\n");
        if (factory != null) {
            append.append("  Skipped:");
            for (int i3 = 0; i3 < indexOf; i3++) {
                append.append("\n   * ").append(this.converterFactories.get(i3).getClass().getName());
            }
            append.append('\n');
        }
        append.append("  Tried:");
        int size2 = this.converterFactories.size();
        while (indexOf < size2) {
            append.append("\n   * ").append(this.converterFactories.get(indexOf).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(append.toString());
    }

    public <T> Converter<TypedInput, T> responseBodyConverter(Type type, Annotation[] annotationArr) {
        return nextResponseBodyConverter(null, type, annotationArr);
    }

    public <T> Converter<TypedInput, T> nextResponseBodyConverter(@Nullable Converter.Factory factory, Type type, Annotation[] annotationArr) {
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(annotationArr, "annotations == null");
        int indexOf = this.converterFactories.indexOf(factory) + 1;
        int size = this.converterFactories.size();
        for (int i2 = indexOf; i2 < size; i2++) {
            Converter<TypedInput, T> converter = (Converter<TypedInput, T>) this.converterFactories.get(i2).responseBodyConverter(type, annotationArr, this);
            if (converter != null) {
                return converter;
            }
        }
        StringBuilder append = new StringBuilder("Could not locate TypedInput converter for ").append(type).append(".\n");
        if (factory != null) {
            append.append("  Skipped:");
            for (int i3 = 0; i3 < indexOf; i3++) {
                append.append("\n   * ").append(this.converterFactories.get(i3).getClass().getName());
            }
            append.append('\n');
        }
        append.append("  Tried:");
        int size2 = this.converterFactories.size();
        while (indexOf < size2) {
            append.append("\n   * ").append(this.converterFactories.get(indexOf).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(append.toString());
    }

    public <T> Converter<T, Object> objectConverter(Type type, Annotation[] annotationArr) {
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(annotationArr, "annotations == null");
        int size = this.converterFactories.size();
        for (int i2 = 0; i2 < size; i2++) {
            Converter<T, Object> converter = (Converter<T, Object>) this.converterFactories.get(i2).objectConverter(type, annotationArr, this);
            if (converter != null) {
                return converter;
            }
        }
        throw new IllegalArgumentException("Could not locate object converter");
    }

    public <T> Converter<T, String> stringConverter(Type type, Annotation[] annotationArr) {
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(annotationArr, "annotations == null");
        int size = this.converterFactories.size();
        for (int i2 = 0; i2 < size; i2++) {
            Converter<T, String> converter = (Converter<T, String>) this.converterFactories.get(i2).stringConverter(type, annotationArr, this);
            if (converter != null) {
                return converter;
            }
        }
        return BuiltInConverters.ToStringConverter.INSTANCE;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public <T> Converter<T, Header> headerConverter(Type type, Annotation[] annotationArr) {
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(annotationArr, "annotations == null");
        int size = this.converterFactories.size();
        for (int i2 = 0; i2 < size; i2++) {
            Converter<T, Header> converter = (Converter<T, Header>) this.converterFactories.get(i2).headerConverter(type, annotationArr, this);
            if (converter != null) {
                return converter;
            }
        }
        throw new IllegalArgumentException("Could not locate header converter");
    }

    public Executor callbackExecutor() {
        return this.callbackExecutor;
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        @Nullable
        private ICacheServer cacheServer;
        private final List<CallAdapter.Factory> callAdapterFactories;
        @Nullable
        private Executor callbackExecutor;
        @Nullable
        private Client.Provider clientProvider;
        private final List<Converter.Factory> converterFactories;
        @Nullable
        private Endpoint endpoint;
        @Nullable
        private Executor httpExecutor;
        private final List<Interceptor> interceptors;
        private final Platform platform;
        private boolean validateEagerly;

        Builder(Platform platform) {
            this.interceptors = new CopyOnWriteArrayList();
            this.converterFactories = new ArrayList();
            this.callAdapterFactories = new ArrayList();
            this.platform = platform;
        }

        public Builder() {
            this(Platform.get());
        }

        Builder(Retrofit retrofit) {
            this.interceptors = new CopyOnWriteArrayList();
            this.converterFactories = new ArrayList();
            this.callAdapterFactories = new ArrayList();
            this.platform = Platform.get();
            this.clientProvider = retrofit.clientProvider;
            this.endpoint = retrofit.server;
            for (int i2 = 1; i2 < retrofit.interceptors.size() - this.platform.defaultConverterFactoriesSize(); i2++) {
                this.interceptors.add((Interceptor) retrofit.interceptors.get(i2));
            }
            int size = retrofit.converterFactories.size() - this.platform.defaultConverterFactoriesSize();
            for (int i3 = 1; i3 < size; i3++) {
                this.converterFactories.add((Converter.Factory) retrofit.converterFactories.get(i3));
            }
            int size2 = retrofit.callAdapterFactories.size() - this.platform.defaultCallAdapterFactoriesSize();
            for (int i4 = 0; i4 < size2; i4++) {
                this.callAdapterFactories.add((CallAdapter.Factory) retrofit.callAdapterFactories.get(i4));
            }
            this.httpExecutor = retrofit.httpExecutor;
            this.callbackExecutor = retrofit.callbackExecutor;
            this.validateEagerly = retrofit.validateEagerly;
            this.cacheServer = retrofit.cacheServer;
        }

        public Builder client(Client.Provider provider) {
            return provider((Client.Provider) Utils.checkNotNull(provider, "provider == null"));
        }

        public Builder provider(Client.Provider provider) {
            this.clientProvider = (Client.Provider) Utils.checkNotNull(provider, "provider == null");
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            this.interceptors.add((Interceptor) Utils.checkNotNull(interceptor, "interceptor == null"));
            return this;
        }

        public Builder removeInterceptor(Interceptor interceptor) {
            this.interceptors.remove((Interceptor) Utils.checkNotNull(interceptor, "interceptor == null"));
            return this;
        }

        public Builder setEndpoint(String str) {
            if (str == null || str.trim().length() == 0) {
                throw new NullPointerException("Endpoint may not be blank.");
            }
            this.endpoint = Endpoints.newFixedEndpoint(str);
            return this;
        }

        public Builder setEndpoint(Endpoint endpoint) {
            Objects.requireNonNull(endpoint, "Endpoint may not be null.");
            this.endpoint = endpoint;
            return this;
        }

        public Builder addConverterFactory(Converter.Factory factory) {
            this.converterFactories.add((Converter.Factory) Utils.checkNotNull(factory, "factory == null"));
            return this;
        }

        public Builder addCallAdapterFactory(CallAdapter.Factory factory) {
            this.callAdapterFactories.add((CallAdapter.Factory) Utils.checkNotNull(factory, "factory == null"));
            return this;
        }

        public Builder callbackExecutor(Executor executor) {
            this.callbackExecutor = (Executor) Utils.checkNotNull(executor, "executor == null");
            return this;
        }

        public Builder httpExecutor(Executor executor) {
            this.httpExecutor = (Executor) Utils.checkNotNull(executor, "httpExecutor == null");
            return this;
        }

        public List<CallAdapter.Factory> callAdapterFactories() {
            return this.callAdapterFactories;
        }

        public List<Converter.Factory> converterFactories() {
            return this.converterFactories;
        }

        public Builder validateEagerly(boolean z) {
            this.validateEagerly = z;
            return this;
        }

        public Builder cacheServer(ICacheServer iCacheServer) {
            this.cacheServer = iCacheServer;
            return this;
        }

        public Retrofit build() {
            if (this.endpoint == null) {
                throw new IllegalStateException("Endpoint may not be null.");
            }
            if (this.clientProvider == null) {
                throw new IllegalStateException("ClientProvider may not be null.");
            }
            if (this.httpExecutor == null) {
                throw new IllegalStateException("HttpExecutor may not be null.");
            }
            Executor executor = this.callbackExecutor;
            if (executor == null) {
                executor = this.platform.defaultCallbackExecutor();
            }
            Executor executor2 = executor;
            ArrayList arrayList = new ArrayList(this.callAdapterFactories);
            arrayList.addAll(this.platform.defaultCallAdapterFactories(executor2));
            ArrayList arrayList2 = new ArrayList(this.converterFactories.size() + 1 + this.platform.defaultConverterFactoriesSize());
            arrayList2.add(new BuiltInConverters());
            arrayList2.addAll(this.converterFactories);
            arrayList2.addAll(this.platform.defaultConverterFactories());
            if (Retrofit.sCommonInterceptors != null) {
                Iterator it = Retrofit.sCommonInterceptors.iterator();
                while (it.hasNext()) {
                    Interceptor interceptor = (Interceptor) it.next();
                    if (!this.interceptors.contains(interceptor)) {
                        this.interceptors.add(interceptor);
                    }
                }
            }
            return new Retrofit(this.endpoint, this.clientProvider, this.interceptors, arrayList2, arrayList, this.httpExecutor, executor2, this.validateEagerly, this.cacheServer);
        }
    }
}
