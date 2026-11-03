package com.bytedance.ttgame.glog.api;

import android.content.Context;
/* loaded from: classes3.dex */
public class GLogConfig {
    private Context context;
    private boolean debug;
    private boolean enableI18n;
    private boolean encrypt;
    private int level;
    private String logDirPath;
    private String pubKey;

    private GLogConfig() {
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getLogDirPath() {
        return this.logDirPath;
    }

    public void setLogDirPath(String str) {
        this.logDirPath = str;
    }

    public boolean isEncrypt() {
        return this.encrypt;
    }

    public void setEncrypt(boolean z) {
        this.encrypt = z;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i2) {
        this.level = i2;
    }

    public String getPubKey() {
        return this.pubKey;
    }

    public void setPubKey(String str) {
        this.pubKey = str;
    }

    public boolean isDebug() {
        return this.debug;
    }

    public void setDebug(boolean z) {
        this.debug = z;
    }

    public boolean isEnableI18n() {
        return this.enableI18n;
    }

    public void setEnableI18n(boolean z) {
        this.enableI18n = z;
    }

    /* loaded from: classes3.dex */
    public static class Builder {
        private Context context;
        private String logDirPath;
        private boolean encrypt = true;
        private int level = 3;
        private String pubKey = "fecbb32b759120b672045f74edc41d159b6a426ffc863b9e0be9ad4be12824546f549959b838993a430344f15197221e87bd362298814c75f5068148b980306f";
        private boolean debug = true;
        private boolean enableI18n = true;

        public Builder(Context context) {
            if (context == null) {
                throw new RuntimeException("context must not be null");
            }
            Context applicationContext = context.getApplicationContext();
            this.context = applicationContext != null ? applicationContext : context;
        }

        public Builder setLogDirPath(String str) {
            this.logDirPath = str;
            return this;
        }

        public Builder setEncrypt(boolean z) {
            this.encrypt = z;
            return this;
        }

        public Builder setLevel(int i2) {
            this.level = i2;
            return this;
        }

        public Builder setPubKey(String str) {
            this.pubKey = str;
            return this;
        }

        public Builder setDebug(boolean z) {
            this.debug = z;
            return this;
        }

        public Builder setEnableI18n(boolean z) {
            this.enableI18n = z;
            return this;
        }

        public GLogConfig build() {
            GLogConfig gLogConfig = new GLogConfig();
            gLogConfig.setContext(this.context);
            gLogConfig.setLogDirPath(this.logDirPath);
            gLogConfig.setEncrypt(this.encrypt);
            gLogConfig.setLevel(this.level);
            gLogConfig.setPubKey(this.pubKey);
            gLogConfig.setDebug(this.debug);
            gLogConfig.setEnableI18n(this.enableI18n);
            return gLogConfig;
        }
    }
}
