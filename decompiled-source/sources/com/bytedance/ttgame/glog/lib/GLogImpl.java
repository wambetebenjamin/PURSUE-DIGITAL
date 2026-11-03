package com.bytedance.ttgame.glog.lib;

import android.text.TextUtils;
import android.util.Log;
import com.bytedance.ttgame.glog.api.GLogApi;
import com.bytedance.ttgame.glog.api.GLogConfig;
import com.bytedance.ttgame.glog.api.IGLogUploadCallback;
import com.ss.android.agilelogger.b;
import com.ss.android.agilelogger.utils.a;
/* loaded from: classes3.dex */
abstract class GLogImpl implements GLogApi {
    protected static final String TAG = "GLog";
    private final String initTips = "----- Please initialize GLog first. -----";
    private boolean isInit = false;
    protected String logDirPath;

    @Override // com.bytedance.ttgame.glog.api.GLogApi
    public void activeUploadLog(long j2, long j3, String str, IGLogUploadCallback iGLogUploadCallback) {
    }

    @Override // com.bytedance.ttgame.glog.api.GLogApi
    public void cloudCollect(String str, long j2, long j3) {
    }

    @Override // com.bytedance.ttgame.glog.api.GLogApi
    public void init(GLogConfig gLogConfig) {
        if (this.isInit) {
            return;
        }
        String logDirPath = gLogConfig.getLogDirPath();
        if (TextUtils.isEmpty(logDirPath)) {
            logDirPath = a.getDefaultLogDir(gLogConfig.getContext()).getAbsolutePath();
        }
        this.logDirPath = logDirPath;
        com.ss.android.agilelogger.a.init(new b.a(gLogConfig.getContext()).setEncrypt(gLogConfig.isEncrypt()).setPubKey(gLogConfig.getPubKey()).setLevel(gLogConfig.getLevel()).setLogDirPath(this.logDirPath).build());
        com.ss.android.agilelogger.a.setDebug(gLogConfig.isDebug());
        this.isInit = true;
    }

    @Override // com.bytedance.ttgame.glog.api.GLogApi
    public void v(String str, String str2) {
        log(2, str, str2, null);
    }

    @Override // com.bytedance.ttgame.glog.api.GLogApi
    public void d(String str, String str2) {
        log(3, str, str2, null);
    }

    @Override // com.bytedance.ttgame.glog.api.GLogApi
    public void i(String str, String str2) {
        log(4, str, str2, null);
    }

    @Override // com.bytedance.ttgame.glog.api.GLogApi
    public void w(String str, String str2) {
        log(5, str, str2, null);
    }

    @Override // com.bytedance.ttgame.glog.api.GLogApi
    public void w(String str, String str2, Throwable th) {
        log(5, str, str2, th);
    }

    @Override // com.bytedance.ttgame.glog.api.GLogApi
    public void w(String str, Throwable th) {
        log(2, str, null, th);
    }

    @Override // com.bytedance.ttgame.glog.api.GLogApi
    public void e(String str, String str2) {
        log(6, str, str2, null);
    }

    @Override // com.bytedance.ttgame.glog.api.GLogApi
    public void e(String str, String str2, Throwable th) {
        log(6, str, str2, th);
    }

    @Override // com.bytedance.ttgame.glog.api.GLogApi
    public void e(String str, Throwable th) {
        log(6, str, null, th);
    }

    @Override // com.bytedance.ttgame.glog.api.GLogApi
    public void log(int i2, String str, String str2, Object obj) {
        if (!this.isInit) {
            Log.d(TAG, "----- Please initialize GLog first. -----");
        } else if (i2 == 2) {
            com.ss.android.agilelogger.a.v(str, str2);
        } else if (i2 == 3) {
            com.ss.android.agilelogger.a.d(str, str2);
        } else if (i2 == 4) {
            com.ss.android.agilelogger.a.i(str, str2);
        } else if (i2 == 5) {
            if (obj instanceof Throwable) {
                if (TextUtils.isEmpty(str2)) {
                    com.ss.android.agilelogger.a.w(str, (Throwable) obj);
                    return;
                } else {
                    com.ss.android.agilelogger.a.w(str, str2, (Throwable) obj);
                    return;
                }
            }
            com.ss.android.agilelogger.a.w(str, str2);
        } else if (i2 == 6) {
            if (obj instanceof Throwable) {
                if (TextUtils.isEmpty(str2)) {
                    com.ss.android.agilelogger.a.e(str, (Throwable) obj);
                    return;
                } else {
                    com.ss.android.agilelogger.a.e(str, str2, (Throwable) obj);
                    return;
                }
            }
            com.ss.android.agilelogger.a.e(str, str2);
        }
    }

    @Override // com.bytedance.ttgame.glog.api.GLogApi
    public void header(int i2, String str, String str2) {
        if (!this.isInit) {
            Log.d(TAG, "----- Please initialize GLog first. -----");
        } else {
            com.ss.android.agilelogger.a.header(i2, str, str2);
        }
    }

    @Override // com.bytedance.ttgame.glog.api.GLogApi
    public void changeLevel(int i2) {
        com.ss.android.agilelogger.a.changeLevel(i2);
    }

    @Override // com.bytedance.ttgame.glog.api.GLogApi
    public String getLogDirPath() {
        return this.logDirPath;
    }
}
