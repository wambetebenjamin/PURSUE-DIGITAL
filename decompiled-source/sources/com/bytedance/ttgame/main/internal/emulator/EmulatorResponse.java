package com.bytedance.ttgame.main.internal.emulator;

import com.bytedance.ttgame.base.BaseResponse;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes3.dex */
public class EmulatorResponse extends BaseResponse {
    @SerializedName("data")
    public Data data;

    /* loaded from: classes3.dex */
    public class Data {
        @SerializedName("is_emulator")
        public boolean isEmulator;

        public Data() {
        }
    }
}
