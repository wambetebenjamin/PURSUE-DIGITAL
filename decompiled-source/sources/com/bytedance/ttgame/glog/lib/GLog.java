package com.bytedance.ttgame.glog.lib;

import android.util.Log;
import com.bytedance.ttgame.glog.api.GLogConfig;
import com.bytedance.ttgame.glog.api.IGLogUploadCallback;
/* loaded from: classes3.dex */
public class GLog extends GLogImpl {
    private static volatile GLog gLog;
    private boolean enableI18n;

    @Override // com.bytedance.ttgame.glog.lib.GLogImpl, com.bytedance.ttgame.glog.api.GLogApi
    public String getLogDirPath() {
        return "";
    }

    @Override // com.bytedance.ttgame.glog.lib.GLogImpl, com.bytedance.ttgame.glog.api.GLogApi
    public /* bridge */ /* synthetic */ void cloudCollect(String str, long j2, long j3) {
        super.cloudCollect(str, j2, j3);
    }

    public static GLog newInstance() {
        if (gLog == null) {
            synchronized (GLog.class) {
                if (gLog == null) {
                    gLog = new GLog();
                }
            }
        }
        return gLog;
    }

    private GLog() {
    }

    @Override // com.bytedance.ttgame.glog.lib.GLogImpl, com.bytedance.ttgame.glog.api.GLogApi
    public void init(GLogConfig gLogConfig) {
        this.enableI18n = gLogConfig.isEnableI18n();
        Log.d("GLog", "---------init GLog I18N.");
        if (this.enableI18n) {
            super.init(gLogConfig);
        } else {
            Log.d("GLog", "---------close i18n GLog feature, use Android Log instead.");
        }
    }

    @Override // com.bytedance.ttgame.glog.lib.GLogImpl, com.bytedance.ttgame.glog.api.GLogApi
    public void v(String str, String str2) {
        if (this.enableI18n) {
            super.v(str, str2);
        } else {
            Log.v(str, str2);
        }
    }

    @Override // com.bytedance.ttgame.glog.lib.GLogImpl, com.bytedance.ttgame.glog.api.GLogApi
    public void d(String str, String str2) {
        if (this.enableI18n) {
            super.d(str, str2);
        } else {
            Log.d(str, str2);
        }
    }

    @Override // com.bytedance.ttgame.glog.lib.GLogImpl, com.bytedance.ttgame.glog.api.GLogApi
    public void i(String str, String str2) {
        if (this.enableI18n) {
            super.i(str, str2);
        } else {
            Log.i(str, str2);
        }
    }

    @Override // com.bytedance.ttgame.glog.lib.GLogImpl, com.bytedance.ttgame.glog.api.GLogApi
    public void w(String str, String str2) {
        if (this.enableI18n) {
            super.w(str, str2);
        } else {
            Log.w(str, str2);
        }
    }

    @Override // com.bytedance.ttgame.glog.lib.GLogImpl, com.bytedance.ttgame.glog.api.GLogApi
    public void w(String str, String str2, Throwable th) {
        if (this.enableI18n) {
            super.w(str, str2, th);
        } else {
            Log.w(str, str2, th);
        }
    }

    @Override // com.bytedance.ttgame.glog.lib.GLogImpl, com.bytedance.ttgame.glog.api.GLogApi
    public void w(String str, Throwable th) {
        if (this.enableI18n) {
            super.w(str, th);
        } else {
            Log.w(str, th);
        }
    }

    @Override // com.bytedance.ttgame.glog.lib.GLogImpl, com.bytedance.ttgame.glog.api.GLogApi
    public void e(String str, String str2) {
        if (this.enableI18n) {
            super.e(str, str2);
        } else {
            Log.e(str, str2);
        }
    }

    @Override // com.bytedance.ttgame.glog.lib.GLogImpl, com.bytedance.ttgame.glog.api.GLogApi
    public void e(String str, String str2, Throwable th) {
        if (this.enableI18n) {
            super.e(str, str2, th);
        } else {
            Log.e(str, str2, th);
        }
    }

    @Override // com.bytedance.ttgame.glog.lib.GLogImpl, com.bytedance.ttgame.glog.api.GLogApi
    public void e(String str, Throwable th) {
        if (this.enableI18n) {
            super.e(str, th);
        } else {
            Log.e(str, "", th);
        }
    }

    @Override // com.bytedance.ttgame.glog.lib.GLogImpl, com.bytedance.ttgame.glog.api.GLogApi
    public void log(int i2, String str, String str2, Object obj) {
        if (this.enableI18n) {
            super.log(i2, str, str2, obj);
        } else {
            Log.println(i2, str, str2);
        }
    }

    @Override // com.bytedance.ttgame.glog.lib.GLogImpl, com.bytedance.ttgame.glog.api.GLogApi
    public void header(int i2, String str, String str2) {
        if (this.enableI18n) {
            super.header(i2, str, str2);
        } else {
            Log.println(i2, str, str2);
        }
    }

    @Override // com.bytedance.ttgame.glog.lib.GLogImpl, com.bytedance.ttgame.glog.api.GLogApi
    public void changeLevel(int i2) {
        if (this.enableI18n) {
            super.changeLevel(i2);
        }
    }

    @Override // com.bytedance.ttgame.glog.lib.GLogImpl, com.bytedance.ttgame.glog.api.GLogApi
    public void activeUploadLog(long j2, long j3, String str, IGLogUploadCallback iGLogUploadCallback) {
        super.activeUploadLog(j2, j3, str, iGLogUploadCallback);
    }
}
