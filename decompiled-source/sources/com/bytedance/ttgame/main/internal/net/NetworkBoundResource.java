package com.bytedance.ttgame.main.internal.net;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.bytedance.ttgame.framework.module.util.Objects;
import java.util.concurrent.ExecutorService;
/* loaded from: classes3.dex */
public abstract class NetworkBoundResource<ResultType, RequestType> {
    private final ExecutorService executor;
    private final ExecutorService main;
    private final MediatorLiveData<Resource<ResultType>> result;

    protected abstract LiveData<ApiResponse<RequestType>> createCall();

    protected abstract LiveData<ResultType> loadFromDb();

    protected void onFetchFailed(RequestType requesttype) {
    }

    protected abstract void saveCallResult(RequestType requesttype);

    protected abstract boolean shouldFetch(ResultType resulttype);

    public NetworkBoundResource(ExecutorService executorService, ExecutorService executorService2) {
        MediatorLiveData<Resource<ResultType>> mediatorLiveData = new MediatorLiveData<>();
        this.result = mediatorLiveData;
        this.executor = executorService;
        this.main = executorService2;
        final LiveData<ResultType> loadFromDb = loadFromDb();
        mediatorLiveData.addSource(loadFromDb, new Observer() { // from class: com.bytedance.ttgame.main.internal.net.NetworkBoundResource$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                NetworkBoundResource.this.m159xcfab6da3(loadFromDb, obj);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: lambda$new$1$com-bytedance-ttgame-main-internal-net-NetworkBoundResource  reason: not valid java name */
    public /* synthetic */ void m159xcfab6da3(LiveData liveData, Object obj) {
        this.result.removeSource(liveData);
        if (shouldFetch(obj)) {
            fetchFromNetwork(liveData);
        } else {
            this.result.addSource(liveData, new Observer() { // from class: com.bytedance.ttgame.main.internal.net.NetworkBoundResource$$ExternalSyntheticLambda1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj2) {
                    NetworkBoundResource.this.m158x1535cd22(obj2);
                }
            });
        }
    }

    /* renamed from: lambda$new$0$com-bytedance-ttgame-main-internal-net-NetworkBoundResource  reason: not valid java name */
    public /* synthetic */ void m158x1535cd22(Object obj) {
        setValue(Resource.success(obj));
    }

    private void setValue(Resource<ResultType> resource) {
        if (Objects.equals(this.result.getValue(), resource)) {
            return;
        }
        this.result.setValue(resource);
    }

    private void fetchFromNetwork(final LiveData<ResultType> liveData) {
        final LiveData<ApiResponse<RequestType>> createCall = createCall();
        this.result.addSource(liveData, new Observer() { // from class: com.bytedance.ttgame.main.internal.net.NetworkBoundResource$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                NetworkBoundResource.this.m152xc91db28(obj);
            }
        });
        this.result.addSource(createCall, new Observer() { // from class: com.bytedance.ttgame.main.internal.net.NetworkBoundResource$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                NetworkBoundResource.this.m157xb0ddfdad(createCall, liveData, (ApiResponse) obj);
            }
        });
    }

    /* renamed from: lambda$fetchFromNetwork$2$com-bytedance-ttgame-main-internal-net-NetworkBoundResource  reason: not valid java name */
    public /* synthetic */ void m152xc91db28(Object obj) {
        setValue(Resource.loading(obj));
    }

    /* renamed from: lambda$fetchFromNetwork$7$com-bytedance-ttgame-main-internal-net-NetworkBoundResource  reason: not valid java name */
    public /* synthetic */ void m157xb0ddfdad(LiveData liveData, LiveData liveData2, final ApiResponse apiResponse) {
        this.result.removeSource(liveData);
        this.result.removeSource(liveData2);
        if (apiResponse.isSuccessful()) {
            this.executor.execute(new Runnable() { // from class: com.bytedance.ttgame.main.internal.net.NetworkBoundResource$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    NetworkBoundResource.this.m155x3bf2bcab(apiResponse);
                }
            });
            return;
        }
        onFetchFailed(processResponse(apiResponse));
        this.result.addSource(liveData2, new Observer() { // from class: com.bytedance.ttgame.main.internal.net.NetworkBoundResource$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                NetworkBoundResource.this.m156xf6685d2c(apiResponse, obj);
            }
        });
    }

    /* renamed from: lambda$fetchFromNetwork$5$com-bytedance-ttgame-main-internal-net-NetworkBoundResource  reason: not valid java name */
    public /* synthetic */ void m155x3bf2bcab(final ApiResponse apiResponse) {
        saveCallResult(processResponse(apiResponse));
        this.main.execute(new Runnable() { // from class: com.bytedance.ttgame.main.internal.net.NetworkBoundResource$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                NetworkBoundResource.this.m154x817d1c2a(apiResponse);
            }
        });
    }

    /* renamed from: lambda$fetchFromNetwork$4$com-bytedance-ttgame-main-internal-net-NetworkBoundResource  reason: not valid java name */
    public /* synthetic */ void m154x817d1c2a(final ApiResponse apiResponse) {
        this.result.addSource(loadFromDb(), new Observer() { // from class: com.bytedance.ttgame.main.internal.net.NetworkBoundResource$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                NetworkBoundResource.this.m153xc7077ba9(apiResponse, obj);
            }
        });
    }

    /* renamed from: lambda$fetchFromNetwork$3$com-bytedance-ttgame-main-internal-net-NetworkBoundResource  reason: not valid java name */
    public /* synthetic */ void m153xc7077ba9(ApiResponse apiResponse, Object obj) {
        setValue(Resource.success(obj, apiResponse.logId));
    }

    /* renamed from: lambda$fetchFromNetwork$6$com-bytedance-ttgame-main-internal-net-NetworkBoundResource  reason: not valid java name */
    public /* synthetic */ void m156xf6685d2c(ApiResponse apiResponse, Object obj) {
        setValue(Resource.error(apiResponse.errorMessage, obj, apiResponse.logId));
    }

    public LiveData<Resource<ResultType>> asLiveData() {
        return this.result;
    }

    protected RequestType processResponse(ApiResponse<RequestType> apiResponse) {
        return apiResponse.body;
    }
}
