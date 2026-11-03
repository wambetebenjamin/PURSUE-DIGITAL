package com.bytedance.ttgame.main.internal.emulator;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.GET;
/* loaded from: classes3.dex */
public interface EmulatorApi {
    @GET("gsdk/misc/emulator_status")
    Call<EmulatorResponse> whetherDeviceIsSimulator(@AddCommonParam boolean z);
}
