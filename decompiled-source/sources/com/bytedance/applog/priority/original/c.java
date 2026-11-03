package com.bytedance.applog.priority.original;

import com.bytedance.applog.priority.original.i;
import java.util.HashMap;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import org.json.JSONObject;
/* compiled from: Database.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0016\u0010\u0007\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH&J\b\u0010\u000b\u001a\u00020\u0003H&J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\rH&JP\u0010\u000e\u001a*\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00110\u0010j\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0011`\u0012\u0012\u0004\u0012\u00020\u00130\u000f2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0015H&J\u001a\u0010\u0018\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u001bH&J\u0010\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u001d"}, d2 = {"Lcom/bytedance/applog/priority/original/DatabaseInterface;", "", "clear", "", "count", "", "groupId", "deleteAll", "keys", "", "Lcom/bytedance/applog/priority/original/Model$EventKey;", "destroy", "getAllGroupIds", "", "getBySizeAndCount", "Lkotlin/Pair;", "Ljava/util/HashMap;", "Lorg/json/JSONObject;", "Lkotlin/collections/HashMap;", "", "maxSize", "", "maxCount", "eventTtl", "insert", "key", "value", "", "isEmpty", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public interface c {
    int a(int i2);

    i.a a(i.a aVar, String str);

    Pair<HashMap<i.a, JSONObject>, Boolean> a(int i2, long j2, int i3, long j3);

    void a();

    void a(Iterable<i.a> iterable);

    Set<Integer> b();

    boolean b(int i2);

    void c();
}
