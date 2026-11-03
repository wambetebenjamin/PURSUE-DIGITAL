package com.google.firebase.heartbeatinfo;
/* loaded from: classes9.dex */
public interface HeartBeatInfo {
    HeartBeat getHeartBeatCode(String str);

    /* loaded from: classes9.dex */
    public enum HeartBeat {
        NONE(0),
        SDK(1),
        GLOBAL(2),
        COMBINED(3);
        
        private final int code;

        HeartBeat(int i2) {
            this.code = i2;
        }

        public int getCode() {
            return this.code;
        }
    }
}
