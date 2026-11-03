package com.bytedance.ttgame.core.realtime;

import com.bytedance.ttgame.library.luffy.db.PrimaryKeyName;
import com.bytedance.ttgame.library.luffy.db.TableName;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
@TableName("realtime_event_pending")
/* loaded from: classes3.dex */
public class PendingRealtimeEvent implements Serializable {
    public static final long serialVersionUID = 723761088820506347L;
    @SerializedName("created_at")
    public long createdAt;
    @SerializedName("event")
    public String event;
    @SerializedName("event_time")
    public long eventTime;
    @SerializedName("expire_at")
    public long expireAt;
    @PrimaryKeyName
    @SerializedName("id")
    public String id;
    @SerializedName("params")
    public String params;
    @SerializedName("payload")
    public String payload;
}
