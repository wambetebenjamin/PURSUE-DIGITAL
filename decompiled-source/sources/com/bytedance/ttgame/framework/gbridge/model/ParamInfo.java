package com.bytedance.ttgame.framework.gbridge.model;
/* loaded from: classes3.dex */
public class ParamInfo {
    private String name;
    private boolean optional;
    private String simpleType;
    private String type;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getSimpleType() {
        return this.simpleType;
    }

    public void setSimpleType(String str) {
        this.simpleType = str;
    }

    public boolean isOptional() {
        return this.optional;
    }

    public void setOptional(boolean z) {
        this.optional = z;
    }
}
