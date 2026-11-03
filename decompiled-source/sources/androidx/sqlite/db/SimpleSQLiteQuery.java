package androidx.sqlite.db;
/* loaded from: classes.dex */
public final class SimpleSQLiteQuery implements SupportSQLiteQuery {
    private final Object[] mBindArgs;
    private final String mQuery;

    public SimpleSQLiteQuery(String str, Object[] objArr) {
        this.mQuery = str;
        this.mBindArgs = objArr;
    }

    public SimpleSQLiteQuery(String str) {
        this(str, null);
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public String getSql() {
        return this.mQuery;
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public void bindTo(SupportSQLiteProgram supportSQLiteProgram) {
        bind(supportSQLiteProgram, this.mBindArgs);
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public int getArgCount() {
        Object[] objArr = this.mBindArgs;
        if (objArr == null) {
            return 0;
        }
        return objArr.length;
    }

    public static void bind(SupportSQLiteProgram supportSQLiteProgram, Object[] objArr) {
        if (objArr == null) {
            return;
        }
        int length = objArr.length;
        int i2 = 0;
        while (i2 < length) {
            Object obj = objArr[i2];
            i2++;
            bind(supportSQLiteProgram, i2, obj);
        }
    }

    private static void bind(SupportSQLiteProgram supportSQLiteProgram, int i2, Object obj) {
        if (obj == null) {
            supportSQLiteProgram.bindNull(i2);
        } else if (obj instanceof byte[]) {
            supportSQLiteProgram.bindBlob(i2, (byte[]) obj);
        } else if (obj instanceof Float) {
            supportSQLiteProgram.bindDouble(i2, ((Float) obj).floatValue());
        } else if (obj instanceof Double) {
            supportSQLiteProgram.bindDouble(i2, ((Double) obj).doubleValue());
        } else if (obj instanceof Long) {
            supportSQLiteProgram.bindLong(i2, ((Long) obj).longValue());
        } else if (obj instanceof Integer) {
            supportSQLiteProgram.bindLong(i2, ((Integer) obj).intValue());
        } else if (obj instanceof Short) {
            supportSQLiteProgram.bindLong(i2, ((Short) obj).shortValue());
        } else if (obj instanceof Byte) {
            supportSQLiteProgram.bindLong(i2, ((Byte) obj).byteValue());
        } else if (obj instanceof String) {
            supportSQLiteProgram.bindString(i2, (String) obj);
        } else if (obj instanceof Boolean) {
            supportSQLiteProgram.bindLong(i2, ((Boolean) obj).booleanValue() ? 1L : 0L);
        } else {
            throw new IllegalArgumentException("Cannot bind " + obj + " at index " + i2 + " Supported types: null, byte[], float, double, long, int, short, byte, string");
        }
    }
}
