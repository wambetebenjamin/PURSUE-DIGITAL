package com.bytedance.ttnet.http;

import org.json.JSONObject;
/* compiled from: RequestContext.java */
/* loaded from: classes8.dex */
public class e extends com.bytedance.frameworks.baselib.network.http.b {
    public int cdn_request_num;
    public long decode_time;
    public boolean force_no_https;
    public JSONObject headers;
    public int https_to_http;
    public String local_sign;
    public String ss_sign;
    public boolean using_https;
    public int https_fail_times = -1;
    public boolean need_header = false;
    public boolean body_is_json = false;
}
