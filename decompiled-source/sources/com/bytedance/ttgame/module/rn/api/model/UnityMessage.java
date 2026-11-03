package com.bytedance.ttgame.module.rn.api.model;

import java.util.Map;
/* loaded from: classes6.dex */
public class UnityMessage {
    private String action;
    private String message;
    private String messageType;
    private Map<String, Object> params;

    public Map<String, Object> getParams() {
        return this.params;
    }

    public void setParams(Map<String, Object> map) {
        this.params = map;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setMessageType(String str) {
        this.messageType = str;
    }

    public String getMessageType() {
        return this.messageType;
    }
}
