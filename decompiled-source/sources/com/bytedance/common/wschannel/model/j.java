package com.bytedance.common.wschannel.model;

import android.content.ComponentName;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: WsComponent.java */
/* loaded from: classes2.dex */
public class j implements Parcelable {
    public static final Parcelable.Creator<j> CREATOR = new Parcelable.Creator<j>() { // from class: com.bytedance.common.wschannel.model.j.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public j createFromParcel(Parcel parcel) {
            j jVar = new j();
            jVar.f5854a = (ComponentName) parcel.readParcelable(getClass().getClassLoader());
            jVar.f5855b = parcel.createTypedArrayList(h.CREATOR);
            return jVar;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public j[] newArray(int i2) {
            return new j[i2];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    ComponentName f5854a;

    /* renamed from: b  reason: collision with root package name */
    List<h> f5855b;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ComponentName a() {
        return this.f5854a;
    }

    public void a(ComponentName componentName) {
        this.f5854a = componentName;
    }

    public List<h> b() {
        return this.f5855b;
    }

    public void a(List<h> list) {
        this.f5855b = list;
    }

    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        ComponentName componentName = this.f5854a;
        if (componentName != null) {
            jSONObject.put(com.bytedance.framwork.core.sdkmonitor.h.HOST_APP_PACKAGE_NAME, componentName.getPackageName());
            jSONObject.put("class_name", this.f5854a.getClassName());
        }
        List<h> list = this.f5855b;
        if (list != null && list.size() > 0) {
            JSONArray jSONArray = new JSONArray();
            for (h hVar : this.f5855b) {
                jSONArray.put(hVar.c());
            }
            jSONObject.put("accept_services", jSONArray);
        }
        return jSONObject;
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        String optString = jSONObject.optString(com.bytedance.framwork.core.sdkmonitor.h.HOST_APP_PACKAGE_NAME);
        String optString2 = jSONObject.optString("class_name");
        if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
            this.f5854a = new ComponentName(optString, optString2);
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("accept_services");
        if (optJSONArray != null) {
            this.f5855b = new ArrayList();
            int length = optJSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                h hVar = new h();
                hVar.a(optJSONArray.optJSONObject(i2));
                this.f5855b.add(hVar);
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.f5854a, i2);
        parcel.writeTypedList(this.f5855b);
    }
}
