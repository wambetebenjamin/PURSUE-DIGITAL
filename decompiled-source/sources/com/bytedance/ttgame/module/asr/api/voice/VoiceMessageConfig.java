package com.bytedance.ttgame.module.asr.api.voice;

import java.util.concurrent.TimeUnit;
/* loaded from: classes4.dex */
public class VoiceMessageConfig {
    public static final int PLAY_MODE_DEFAULT = 0;
    public static final int PLAY_MODE_SPEAKERPHONE = 1;
    private final long mExpireTime;
    private final int mPlayMode;
    private final IPlayVoiceListener mPlayVoiceListener;
    private final TimeUnit mUnit;
    private final String mVoiceStorePath;

    public String toString() {
        return "VoiceMessageConfig{mVoiceStorePath='" + this.mVoiceStorePath + "', mExpireTime=" + this.mExpireTime + ", mUnit=" + this.mUnit + ", mPlayMode=" + this.mPlayMode + ", mPlayVoiceListener=" + this.mPlayVoiceListener + '}';
    }

    private VoiceMessageConfig(Builder builder) {
        this.mVoiceStorePath = builder.voiceStorePath;
        this.mExpireTime = builder.expireTime;
        this.mUnit = builder.unit;
        this.mPlayMode = builder.playMode;
        this.mPlayVoiceListener = builder.playListener;
    }

    public String getVoiceStorePath() {
        return this.mVoiceStorePath;
    }

    public long getExpireTime() {
        return this.mExpireTime;
    }

    public TimeUnit getUnit() {
        return this.mUnit;
    }

    public int getPlayMode() {
        return this.mPlayMode;
    }

    public IPlayVoiceListener getPlayVoiceListener() {
        return this.mPlayVoiceListener;
    }

    /* loaded from: classes4.dex */
    public static class Builder {
        long expireTime;
        IPlayVoiceListener playListener;
        int playMode;
        TimeUnit unit;
        String voiceStorePath;

        public Builder() {
            this.expireTime = 30L;
            this.unit = TimeUnit.DAYS;
            this.playMode = 0;
        }

        public Builder(VoiceMessageConfig voiceMessageConfig) {
            this.expireTime = 30L;
            this.unit = TimeUnit.DAYS;
            this.playMode = 0;
            this.voiceStorePath = voiceMessageConfig.getVoiceStorePath();
            this.expireTime = voiceMessageConfig.getExpireTime();
            this.unit = voiceMessageConfig.getUnit();
            this.playMode = voiceMessageConfig.getPlayMode();
            this.playListener = voiceMessageConfig.getPlayVoiceListener();
        }

        public Builder setVoiceStorePath(String str) {
            this.voiceStorePath = str;
            return this;
        }

        public Builder setExpireTime(long j2, TimeUnit timeUnit) {
            this.expireTime = j2;
            this.unit = timeUnit;
            return this;
        }

        public Builder setPlayMode(int i2) {
            this.playMode = i2;
            return this;
        }

        public Builder setPlayVoiceListener(IPlayVoiceListener iPlayVoiceListener) {
            this.playListener = iPlayVoiceListener;
            return this;
        }

        public VoiceMessageConfig build() {
            return new VoiceMessageConfig(this);
        }
    }
}
