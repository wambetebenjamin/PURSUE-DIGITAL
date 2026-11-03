package com.bytedance.ttgame.channel.account.pojo;
/* loaded from: classes3.dex */
public class VerifyLoginParams {
    private String channelId;
    private String data;
    private boolean isQuote;
    private long userId;
    private int userType;

    public int getUserType() {
        return this.userType;
    }

    public void setUserType(int i2) {
        this.userType = i2;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public void setChannelId(String str) {
        this.channelId = str;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String str) {
        this.data = str;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long j2) {
        this.userId = j2;
    }

    public boolean isQuote() {
        return this.isQuote;
    }

    public void setQuote(boolean z) {
        this.isQuote = z;
    }
}
