package com.bytedance.ttgame.main.internal.net;

import android.os.Looper;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.bytedance.ttgame.framework.module.util.Objects;
/* loaded from: classes3.dex */
public abstract class NetworkOnlyBoundResource<ResultType> {
    private final MediatorLiveData<Resource<ResultType>> result;

    protected abstract LiveData<ApiResponse<ResultType>> createCall();

    protected void onFetchFailed() {
    }

    public NetworkOnlyBoundResource() {
        MediatorLiveData<Resource<ResultType>> mediatorLiveData = new MediatorLiveData<>();
        this.result = mediatorLiveData;
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            mediatorLiveData.setValue(Resource.loading(null));
        } else {
            mediatorLiveData.postValue(Resource.loading(null));
        }
        fetchFromNetwork();
    }

    private void setValue(Resource<ResultType> resource) {
        if (Objects.equals(this.result.getValue(), resource)) {
            return;
        }
        this.result.setValue(resource);
    }

    private void fetchFromNetwork() {
        final LiveData<ApiResponse<ResultType>> createCall = createCall();
        this.result.addSource(createCall, new Observer() { // from class: com.bytedance.ttgame.main.internal.net.NetworkOnlyBoundResource$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                NetworkOnlyBoundResource.this.m160x27fbb4da(createCall, (ApiResponse) obj);
            }
        });
    }

    /* renamed from: lambda$fetchFromNetwork$0$com-bytedance-ttgame-main-internal-net-NetworkOnlyBoundResource  reason: not valid java name */
    public /* synthetic */ void m160x27fbb4da(LiveData liveData, ApiResponse apiResponse) {
        this.result.removeSource(liveData);
        if (apiResponse != null && apiResponse.isSuccessful()) {
            setValue(Resource.success(processResponse(apiResponse), apiResponse.logId));
            return;
        }
        onFetchFailed();
        setValue(Resource.error(apiResponse.errorMessage, null, apiResponse.logId, apiResponse.throwable, apiResponse.requestUrl));
    }

    public LiveData<Resource<ResultType>> asLiveData() {
        return this.result;
    }

    private ResultType processResponse(ApiResponse<ResultType> apiResponse) {
        return apiResponse.body;
    }
}
