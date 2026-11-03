package androidx.room;

import androidx.sqlite.db.SupportSQLiteProgram;
import java.util.ArrayList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class QueryInterceptorProgram implements SupportSQLiteProgram {
    private List<Object> mBindArgsCache = new ArrayList();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindNull(int i2) {
        saveArgsToCache(i2, null);
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindLong(int i2, long j2) {
        saveArgsToCache(i2, Long.valueOf(j2));
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindDouble(int i2, double d2) {
        saveArgsToCache(i2, Double.valueOf(d2));
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindString(int i2, String str) {
        saveArgsToCache(i2, str);
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindBlob(int i2, byte[] bArr) {
        saveArgsToCache(i2, bArr);
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void clearBindings() {
        this.mBindArgsCache.clear();
    }

    private void saveArgsToCache(int i2, Object obj) {
        int i3 = i2 - 1;
        if (i3 >= this.mBindArgsCache.size()) {
            for (int size = this.mBindArgsCache.size(); size <= i3; size++) {
                this.mBindArgsCache.add(null);
            }
        }
        this.mBindArgsCache.set(i3, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Object> getBindArgs() {
        return this.mBindArgsCache;
    }
}
