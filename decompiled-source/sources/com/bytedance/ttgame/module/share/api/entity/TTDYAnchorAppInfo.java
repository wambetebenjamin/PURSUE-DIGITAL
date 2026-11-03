package com.bytedance.ttgame.module.share.api.entity;

import org.json.JSONObject;
/* loaded from: classes7.dex */
public class TTDYAnchorAppInfo {
    private JSONObject gameExtra;
    private int mAnchorBusinessType;

    public int getAnchorBusinessType() {
        return this.mAnchorBusinessType;
    }

    public void setAnchorBusinessType(int i2) {
        this.mAnchorBusinessType = i2;
    }

    public JSONObject getGameExtra() {
        return this.gameExtra;
    }

    public void setGameExtra(JSONObject jSONObject) {
        this.gameExtra = jSONObject;
    }

    public String toString() {
        return "TTDYAnchorAppInfo{mAnchorBusinessType=" + this.mAnchorBusinessType + ", gameExtra=" + this.gameExtra + '}';
    }
}
