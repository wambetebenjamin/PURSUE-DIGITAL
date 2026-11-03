package com.bytedance.applog.priority;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
/* compiled from: PriorityConst.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0007R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/bytedance/applog/priority/PriorityConst;", "", "()V", "OPTIONS_KEYS", "", "", "getOPTIONS_KEYS", "()Ljava/util/List;", "OPTIONS_KEY_BACKOFF_PRIORITY", "OPTIONS_KEY_GROUP_PRIORITY", "OPTIONS_KEY_PRIORITY_CONFIG", "RESPONSE_KEYS", "getRESPONSE_KEYS", "RESPONSE_KEY_BACKOFF_CONFIG", "RESPONSE_KEY_GROUP_LIST", "RESPONSE_KEY_PRIORITY_CONFIG", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class h {

    /* renamed from: b  reason: collision with root package name */
    public static final String f3133b = "event_priority_config";

    /* renamed from: f  reason: collision with root package name */
    public static final String f3137f = "event_priority_config";

    /* renamed from: a  reason: collision with root package name */
    public static final h f3132a = new h();

    /* renamed from: c  reason: collision with root package name */
    public static final String f3134c = "backoff_priority";

    /* renamed from: d  reason: collision with root package name */
    public static final String f3135d = "group_priority";

    /* renamed from: h  reason: collision with root package name */
    private static final List<String> f3139h = CollectionsKt.listOf((Object[]) new String[]{f3134c, f3135d, "event_priority_config"});

    /* renamed from: e  reason: collision with root package name */
    public static final String f3136e = "backoff_priority_list";

    /* renamed from: g  reason: collision with root package name */
    public static final String f3138g = "group_priority_list";

    /* renamed from: i  reason: collision with root package name */
    private static final List<String> f3140i = CollectionsKt.listOf((Object[]) new String[]{f3136e, "event_priority_config", f3138g});

    private h() {
    }

    public final List<String> a() {
        return f3139h;
    }

    public final List<String> b() {
        return f3140i;
    }
}
