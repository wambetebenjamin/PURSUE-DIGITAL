package com.bytedance.ttgame.library.luffy.db;

import java.util.Map;
import kotlin.Metadata;
/* compiled from: ILuffyDatabaseStorage.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u001c\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\b2\u0006\u0010\u0004\u001a\u00020\u0005H&J \u0010\t\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\nH&J \u0010\f\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\nH&J\u0018\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0010"}, d2 = {"Lcom/bytedance/ttgame/library/luffy/db/ILuffyDatabaseStorage;", "", "contains", "", "repo", "", "key", "getAll", "", "getBytes", "", "defValue", "putBytes", "", "value", "remove", "luffy_db_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public interface c {
    Map<String, Object> a(String str);

    void a(String str, String str2, byte[] bArr);

    boolean a(String str, String str2);

    void b(String str, String str2);

    byte[] b(String str, String str2, byte[] bArr);
}
