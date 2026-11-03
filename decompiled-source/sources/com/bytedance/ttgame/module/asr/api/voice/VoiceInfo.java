package com.bytedance.ttgame.module.asr.api.voice;

import java.io.Serializable;
/* loaded from: classes4.dex */
public class VoiceInfo implements Serializable {
    private static final long serialVersionUID = 8001351174740948712L;
    private final long durationMs;
    private String voiceId;

    public VoiceInfo(long j2, String str) {
        this.durationMs = j2;
        this.voiceId = str;
    }

    public long getDurationMs() {
        return this.durationMs;
    }

    public String getVoiceId() {
        return this.voiceId;
    }

    public void setVoiceId(String str) {
        this.voiceId = str;
    }
}
