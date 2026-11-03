package com.bytedance.ttgame.framework.gbridge.model;

import java.lang.reflect.Method;
import java.util.List;
/* loaded from: classes3.dex */
public class MethodInfo {
    private String callName;
    private boolean isMixCall;
    private boolean isSync;
    private Method method;
    private Object obj;
    private List<ParamInfo> params;
    private boolean threading;

    public boolean isSync() {
        return this.isSync;
    }

    public void setSync(boolean z) {
        this.isSync = z;
    }

    public Object getObj() {
        return this.obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Method getMethod() {
        return this.method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public List<ParamInfo> getParams() {
        return this.params;
    }

    public void setParams(List<ParamInfo> list) {
        this.params = list;
    }

    public String getCallName() {
        return this.callName;
    }

    public void setCallName(String str) {
        this.callName = str;
    }

    public boolean isThreading() {
        return this.threading;
    }

    public void setThreading(boolean z) {
        this.threading = z;
    }

    public boolean isMixCall() {
        return this.isMixCall;
    }

    public void setMixCall(boolean z) {
        this.isMixCall = z;
    }
}
