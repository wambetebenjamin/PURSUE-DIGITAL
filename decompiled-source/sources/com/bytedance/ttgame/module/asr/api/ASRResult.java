package com.bytedance.ttgame.module.asr.api;
/* loaded from: classes4.dex */
public class ASRResult {
    private final String originalData;
    private final String translateResult;
    private final String voiceId;
    private final long voiceTime;

    public ASRResult(String str, String str2, long j2, String str3) {
        this.translateResult = str;
        this.voiceId = str2;
        this.voiceTime = j2;
        this.originalData = str3;
    }

    public String getTranslateResult() {
        return this.translateResult;
    }

    public String getVoiceId() {
        return this.voiceId;
    }

    public long getVoiceTime() {
        return this.voiceTime;
    }

    public String getOriginalData() {
        return this.originalData;
    }

    public String toString() {
        return "ASRResult{translateResult='" + this.translateResult + "', voiceId='" + this.voiceId + "', voiceTime=" + this.voiceTime + ", originalData='" + this.originalData + "'}";
    }
}
