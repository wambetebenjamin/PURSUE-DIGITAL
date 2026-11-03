package com.bytedance.ttgame.library.luffy.db;

import gsdk.impl.account.toutiao.dh;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: LuffyDatabaseStorage.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u001c\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J \u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\rH\u0016J \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\rH\u0016J\u0018\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u000e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0001R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/bytedance/ttgame/library/luffy/db/LuffyDatabaseStorage;", "Lcom/bytedance/ttgame/library/luffy/db/ILuffyDatabaseStorage;", "()V", "luffyDatabaseStorage", "contains", "", "repo", "", "key", "getAll", "", "", "getBytes", "", "defValue", "putBytes", "", "value", "remove", dh.z, "impl", "luffy_db_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public final class e implements c {

    /* renamed from: a  reason: collision with root package name */
    public static final e f14220a = new e();

    /* renamed from: b  reason: collision with root package name */
    private static c f14221b;

    private e() {
    }

    @Override // com.bytedance.ttgame.library.luffy.db.c
    public void a(String repo, String key, byte[] value) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        c cVar = f14221b;
        Intrinsics.checkNotNull(cVar);
        cVar.a(repo, key, value);
    }

    @Override // com.bytedance.ttgame.library.luffy.db.c
    public byte[] b(String repo, String key, byte[] defValue) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defValue, "defValue");
        c cVar = f14221b;
        Intrinsics.checkNotNull(cVar);
        return cVar.b(repo, key, defValue);
    }

    @Override // com.bytedance.ttgame.library.luffy.db.c
    public boolean a(String repo, String key) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        c cVar = f14221b;
        Intrinsics.checkNotNull(cVar);
        return cVar.a(repo, key);
    }

    @Override // com.bytedance.ttgame.library.luffy.db.c
    public void b(String repo, String key) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        c cVar = f14221b;
        Intrinsics.checkNotNull(cVar);
        cVar.b(repo, key);
    }

    @Override // com.bytedance.ttgame.library.luffy.db.c
    public Map<String, Object> a(String repo) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        c cVar = f14221b;
        Intrinsics.checkNotNull(cVar);
        return cVar.a(repo);
    }

    public final void a(c impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        f14221b = impl;
    }
}
