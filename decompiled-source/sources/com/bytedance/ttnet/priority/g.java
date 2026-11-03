package com.bytedance.ttnet.priority;

import com.bytedance.retrofit2.client.Request;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONObject;
/* compiled from: RequestPriorityController.java */
/* loaded from: classes8.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private Set<String> f15417a = new CopyOnWriteArraySet();

    /* renamed from: b  reason: collision with root package name */
    private Set<String> f15418b = new CopyOnWriteArraySet();

    /* renamed from: c  reason: collision with root package name */
    private Set<String> f15419c = new CopyOnWriteArraySet();

    public void a(JSONObject jSONObject) {
        this.f15417a = i.a(jSONObject, "high");
        this.f15418b = i.a(jSONObject, "normal");
        this.f15419c = i.a(jSONObject, "low");
    }

    public void a(Set<String> set, Set<String> set2, Set<String> set3) {
        this.f15417a.clear();
        this.f15418b.clear();
        this.f15419c.clear();
        this.f15417a.addAll(set);
        this.f15418b.addAll(set2);
        this.f15419c.addAll(set3);
    }

    public Request a(Request request) {
        Request.Builder newBuilder = request.newBuilder();
        if (a(this.f15417a, request.getPath(), newBuilder, 3, 4)) {
            return newBuilder.build();
        }
        if (a(this.f15419c, request.getPath(), newBuilder, -1, 1)) {
            return newBuilder.build();
        }
        int priorityLevel = request.getPriorityLevel();
        if (priorityLevel >= 3) {
            priorityLevel = 1;
        }
        if (priorityLevel <= -1) {
            priorityLevel = 0;
        }
        int i2 = priorityLevel;
        int requestPriorityLevel = request.getRequestPriorityLevel();
        if (requestPriorityLevel >= 4) {
            requestPriorityLevel = 3;
        }
        if (requestPriorityLevel >= 1) {
            requestPriorityLevel = 2;
        }
        if (a(this.f15418b, request.getPath(), newBuilder, i2, requestPriorityLevel)) {
            return newBuilder.build();
        }
        return newBuilder.priorityLevel(1).requestPriorityLevel(3).build();
    }

    private boolean a(Set<String> set, String str, Request.Builder builder, int i2, int i3) {
        if (i.a(set, str)) {
            builder.priorityLevel(i2);
            builder.requestPriorityLevel(i3);
            return true;
        }
        return false;
    }
}
