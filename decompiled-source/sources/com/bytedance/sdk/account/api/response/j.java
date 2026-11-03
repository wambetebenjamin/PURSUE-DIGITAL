package com.bytedance.sdk.account.api.response;

import java.util.List;
import org.json.JSONObject;
/* compiled from: CancelIndexResponse.java */
/* loaded from: classes2.dex */
public class j extends com.bytedance.sdk.account.api.call.b {
    public Boolean auditStatus;
    public List<a> conditionsList;
    public boolean hasMobile;
    public boolean identified;
    public int protocol;
    public JSONObject rawData;

    /* compiled from: CancelIndexResponse.java */
    /* loaded from: classes2.dex */
    public static class a {
        public String text;
    }

    public j(boolean z, int i2) {
        super(z, i2);
    }
}
