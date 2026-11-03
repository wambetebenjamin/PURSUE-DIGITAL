package com.bytedance.ttgame.module.share.api.entity;
/* loaded from: classes7.dex */
public enum TTShareContentType {
    TEXT(0),
    IMAGE(1),
    H5(3),
    VIDEO(4),
    TEXT_IMAGE(5),
    NORMAL(100),
    SUPER_GROUP(6),
    FILE(7),
    MINI_APP(8),
    ALL(9);
    
    private int value;

    public int getValue() {
        return this.value;
    }

    TTShareContentType(int i2) {
        this.value = i2;
    }
}
