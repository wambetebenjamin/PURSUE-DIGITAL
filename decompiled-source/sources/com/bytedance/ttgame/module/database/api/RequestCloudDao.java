package com.bytedance.ttgame.module.database.api;

import androidx.lifecycle.LiveData;
/* loaded from: classes5.dex */
public interface RequestCloudDao {
    LiveData<RequestCloudData> getLast();

    RequestCloudData getLastWithThread();

    void insert(RequestCloudData requestCloudData);
}
