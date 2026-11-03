package com.ss.android.common.applog.task;

import android.text.TextUtils;
import com.ss.android.common.applog.TaskSessionDao;
import com.ss.android.common.applog.TeaUtils;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: TaskSession.java */
/* loaded from: classes9.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private long f18067a;

    /* renamed from: b  reason: collision with root package name */
    private String f18068b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f18069c;

    /* renamed from: d  reason: collision with root package name */
    private String f18070d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f18071e;

    /* renamed from: f  reason: collision with root package name */
    private String f18072f;

    /* renamed from: g  reason: collision with root package name */
    private long f18073g;

    /* renamed from: h  reason: collision with root package name */
    private long f18074h;

    /* renamed from: i  reason: collision with root package name */
    private long f18075i;

    public boolean hasFrontSession() {
        return !TextUtils.isEmpty(this.f18070d);
    }

    public boolean hasEndSession() {
        return !TextUtils.isEmpty(this.f18072f);
    }

    public boolean isFrontContinuous() {
        return this.f18069c;
    }

    public String getFrontSessionId() {
        return this.f18070d;
    }

    public boolean isEndContinuous() {
        return this.f18071e;
    }

    public String getEndSessionId() {
        return this.f18072f;
    }

    public static d copyOf(d dVar) {
        if (dVar == null) {
            return null;
        }
        d dVar2 = new d();
        dVar2.f18067a = dVar.f18067a;
        dVar2.f18068b = dVar.f18068b;
        dVar2.f18069c = dVar.f18069c;
        dVar2.f18070d = dVar.f18070d;
        dVar2.f18071e = dVar.f18071e;
        dVar2.f18072f = dVar.f18072f;
        dVar2.f18073g = dVar.f18073g;
        dVar2.f18074h = dVar.f18074h;
        dVar2.f18075i = dVar.f18075i;
        return dVar2;
    }

    public String toJsonString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(com.bytedance.apm.constant.c.KEY_START_TIME, this.f18067a);
            jSONObject.put("session_id", this.f18068b);
            jSONObject.put("is_front_continuous", this.f18069c);
            jSONObject.put("front_session_id", this.f18070d);
            jSONObject.put("is_end_continuous", this.f18071e);
            jSONObject.put("end_session_id", this.f18072f);
            jSONObject.put("latest_end_time", this.f18073g);
            jSONObject.put("non_task_time", this.f18074h);
            jSONObject.put("tea_event_index", this.f18075i);
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static d fromString(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("session_id", "");
            d dVar = new d();
            dVar.f18068b = optString;
            dVar.f18067a = TeaUtils.optLong(jSONObject, com.bytedance.apm.constant.c.KEY_START_TIME);
            dVar.f18069c = jSONObject.optBoolean("is_front_continuous", false);
            dVar.f18070d = jSONObject.optString("front_session_id", "");
            dVar.f18071e = jSONObject.optBoolean("is_end_continuous", false);
            dVar.f18072f = jSONObject.optString("end_session_id", "");
            dVar.f18073g = TeaUtils.optLong(jSONObject, "latest_end_time");
            dVar.f18074h = TeaUtils.optLong(jSONObject, "non_task_time");
            dVar.f18075i = TeaUtils.optLong(jSONObject, "tea_event_index");
            return dVar;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private d() {
        this.f18069c = false;
        this.f18070d = null;
        this.f18071e = false;
        this.f18072f = null;
        this.f18074h = 0L;
        this.f18075i = 0L;
    }

    public d(long j2) {
        this.f18069c = false;
        this.f18070d = null;
        this.f18071e = false;
        this.f18072f = null;
        this.f18074h = 0L;
        this.f18075i = 0L;
        this.f18067a = j2;
        this.f18068b = TaskSessionDao.genSessionId();
        this.f18075i = TaskSessionDao.genTeaEventIndex();
    }

    public String getSessionId() {
        return this.f18068b;
    }

    public int getSessionType() {
        boolean z = this.f18069c;
        boolean z2 = this.f18071e;
        if (z || z2) {
            if (!z || z2) {
                return (z || !z2) ? 4 : 3;
            }
            return 2;
        }
        return 1;
    }

    public void setFrontSessionId(String str) {
        this.f18069c = true;
        this.f18070d = str;
    }

    public void setEndSessionId(String str) {
        this.f18071e = true;
        this.f18072f = str;
    }

    public void setLatestEndTime(long j2) {
        this.f18073g = j2;
    }

    public long getLatestEndTime() {
        return this.f18073g;
    }

    public void addNonTaskTime(long j2) {
        this.f18074h += j2;
    }

    public long getDuration() {
        return Math.max(0L, (this.f18073g - this.f18067a) - this.f18074h);
    }

    public long getDurationInSecond() {
        return Math.max(1L, getDuration() / 1000);
    }

    public String toString() {
        return toJsonString();
    }

    public long getStartTime() {
        return this.f18067a;
    }

    public long getEventIndex() {
        return this.f18075i;
    }
}
