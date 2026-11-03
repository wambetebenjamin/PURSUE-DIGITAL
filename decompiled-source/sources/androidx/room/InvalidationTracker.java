package androidx.room;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.util.Log;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.bytedance.retrofit2.HttpMethodContrants;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
/* loaded from: classes.dex */
public class InvalidationTracker {
    private static final String CREATE_TRACKING_TABLE_SQL = "CREATE TEMP TABLE room_table_modification_log(table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)";
    private static final String INVALIDATED_COLUMN_NAME = "invalidated";
    static final String RESET_UPDATED_TABLES_SQL = "UPDATE room_table_modification_log SET invalidated = 0 WHERE invalidated = 1 ";
    static final String SELECT_UPDATED_TABLES_SQL = "SELECT * FROM room_table_modification_log WHERE invalidated = 1;";
    private static final String TABLE_ID_COLUMN_NAME = "table_id";
    private static final String[] TRIGGERS = {"UPDATE", HttpMethodContrants.DELETE, "INSERT"};
    private static final String UPDATE_TABLE_NAME = "room_table_modification_log";
    AutoCloser mAutoCloser;
    volatile SupportSQLiteStatement mCleanupStatement;
    final RoomDatabase mDatabase;
    private volatile boolean mInitialized;
    private final InvalidationLiveDataContainer mInvalidationLiveDataContainer;
    private MultiInstanceInvalidationClient mMultiInstanceInvalidationClient;
    private ObservedTableTracker mObservedTableTracker;
    final SafeIterableMap<Observer, ObserverWrapper> mObserverMap;
    AtomicBoolean mPendingRefresh;
    Runnable mRefreshRunnable;
    final HashMap<String, Integer> mTableIdLookup;
    final String[] mTableNames;
    private Map<String, Set<String>> mViewTables;

    public InvalidationTracker(RoomDatabase roomDatabase, String... strArr) {
        this(roomDatabase, new HashMap(), Collections.emptyMap(), strArr);
    }

    public InvalidationTracker(RoomDatabase roomDatabase, Map<String, String> map, Map<String, Set<String>> map2, String... strArr) {
        this.mAutoCloser = null;
        this.mPendingRefresh = new AtomicBoolean(false);
        this.mInitialized = false;
        this.mObserverMap = new SafeIterableMap<>();
        this.mRefreshRunnable = new Runnable() { // from class: androidx.room.InvalidationTracker.1
            /* JADX WARN: Code restructure failed: missing block: B:25:0x007d, code lost:
                if (r5.this$0.mAutoCloser != null) goto L31;
             */
            /* JADX WARN: Code restructure failed: missing block: B:26:0x007f, code lost:
                r5.this$0.mAutoCloser.decrementCountAndScheduleClose();
             */
            /* JADX WARN: Code restructure failed: missing block: B:37:0x009f, code lost:
                if (r5.this$0.mAutoCloser == null) goto L32;
             */
            /* JADX WARN: Code restructure failed: missing block: B:39:0x00a2, code lost:
                if (r1 == null) goto L51;
             */
            /* JADX WARN: Code restructure failed: missing block: B:41:0x00a8, code lost:
                if (r1.isEmpty() != false) goto L50;
             */
            /* JADX WARN: Code restructure failed: missing block: B:42:0x00aa, code lost:
                r0 = r5.this$0.mObserverMap;
             */
            /* JADX WARN: Code restructure failed: missing block: B:43:0x00ae, code lost:
                monitor-enter(r0);
             */
            /* JADX WARN: Code restructure failed: missing block: B:44:0x00af, code lost:
                r2 = r5.this$0.mObserverMap.iterator();
             */
            /* JADX WARN: Code restructure failed: missing block: B:46:0x00bb, code lost:
                if (r2.hasNext() == false) goto L42;
             */
            /* JADX WARN: Code restructure failed: missing block: B:47:0x00bd, code lost:
                r2.next().getValue().notifyByTableInvalidStatus(r1);
             */
            /* JADX WARN: Code restructure failed: missing block: B:48:0x00cd, code lost:
                monitor-exit(r0);
             */
            /* JADX WARN: Code restructure failed: missing block: B:53:0x00d2, code lost:
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:67:?, code lost:
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:68:?, code lost:
                return;
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 228
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.room.InvalidationTracker.AnonymousClass1.run():void");
            }

            private Set<Integer> checkUpdatedTable() {
                HashSet hashSet = new HashSet();
                Cursor query = InvalidationTracker.this.mDatabase.query(new SimpleSQLiteQuery(InvalidationTracker.SELECT_UPDATED_TABLES_SQL));
                while (query.moveToNext()) {
                    try {
                        hashSet.add(Integer.valueOf(query.getInt(0)));
                    } catch (Throwable th) {
                        query.close();
                        throw th;
                    }
                }
                query.close();
                if (!hashSet.isEmpty()) {
                    InvalidationTracker.this.mCleanupStatement.executeUpdateDelete();
                }
                return hashSet;
            }
        };
        this.mDatabase = roomDatabase;
        this.mObservedTableTracker = new ObservedTableTracker(strArr.length);
        this.mTableIdLookup = new HashMap<>();
        this.mViewTables = map2;
        this.mInvalidationLiveDataContainer = new InvalidationLiveDataContainer(roomDatabase);
        int length = strArr.length;
        this.mTableNames = new String[length];
        for (int i2 = 0; i2 < length; i2++) {
            String lowerCase = strArr[i2].toLowerCase(Locale.US);
            this.mTableIdLookup.put(lowerCase, Integer.valueOf(i2));
            String str = map.get(strArr[i2]);
            if (str != null) {
                this.mTableNames[i2] = str.toLowerCase(Locale.US);
            } else {
                this.mTableNames[i2] = lowerCase;
            }
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String lowerCase2 = entry.getValue().toLowerCase(Locale.US);
            if (this.mTableIdLookup.containsKey(lowerCase2)) {
                String lowerCase3 = entry.getKey().toLowerCase(Locale.US);
                HashMap<String, Integer> hashMap = this.mTableIdLookup;
                hashMap.put(lowerCase3, hashMap.get(lowerCase2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAutoCloser(AutoCloser autoCloser) {
        this.mAutoCloser = autoCloser;
        autoCloser.setAutoCloseCallback(new Runnable() { // from class: androidx.room.InvalidationTracker$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                InvalidationTracker.this.onAutoCloseCallback();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void internalInit(SupportSQLiteDatabase supportSQLiteDatabase) {
        synchronized (this) {
            if (this.mInitialized) {
                Log.e("ROOM", "Invalidation tracker is initialized twice :/.");
                return;
            }
            supportSQLiteDatabase.execSQL("PRAGMA temp_store = MEMORY;");
            supportSQLiteDatabase.execSQL("PRAGMA recursive_triggers='ON';");
            supportSQLiteDatabase.execSQL(CREATE_TRACKING_TABLE_SQL);
            syncTriggers(supportSQLiteDatabase);
            this.mCleanupStatement = supportSQLiteDatabase.compileStatement(RESET_UPDATED_TABLES_SQL);
            this.mInitialized = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onAutoCloseCallback() {
        synchronized (this) {
            this.mInitialized = false;
            this.mObservedTableTracker.resetTriggerState();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startMultiInstanceInvalidation(Context context, String str, Intent intent) {
        this.mMultiInstanceInvalidationClient = new MultiInstanceInvalidationClient(context, str, intent, this, this.mDatabase.getQueryExecutor());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stopMultiInstanceInvalidation() {
        MultiInstanceInvalidationClient multiInstanceInvalidationClient = this.mMultiInstanceInvalidationClient;
        if (multiInstanceInvalidationClient != null) {
            multiInstanceInvalidationClient.stop();
            this.mMultiInstanceInvalidationClient = null;
        }
    }

    private static void appendTriggerName(StringBuilder sb, String str, String str2) {
        sb.append("`").append("room_table_modification_trigger_").append(str).append("_").append(str2).append("`");
    }

    private void stopTrackingTable(SupportSQLiteDatabase supportSQLiteDatabase, int i2) {
        String[] strArr;
        String str = this.mTableNames[i2];
        StringBuilder sb = new StringBuilder();
        for (String str2 : TRIGGERS) {
            sb.setLength(0);
            sb.append("DROP TRIGGER IF EXISTS ");
            appendTriggerName(sb, str, str2);
            supportSQLiteDatabase.execSQL(sb.toString());
        }
    }

    private void startTrackingTable(SupportSQLiteDatabase supportSQLiteDatabase, int i2) {
        String[] strArr;
        supportSQLiteDatabase.execSQL("INSERT OR IGNORE INTO room_table_modification_log VALUES(" + i2 + ", 0)");
        String str = this.mTableNames[i2];
        StringBuilder sb = new StringBuilder();
        for (String str2 : TRIGGERS) {
            sb.setLength(0);
            sb.append("CREATE TEMP TRIGGER IF NOT EXISTS ");
            appendTriggerName(sb, str, str2);
            sb.append(" AFTER ").append(str2).append(" ON `").append(str).append("` BEGIN UPDATE ").append(UPDATE_TABLE_NAME).append(" SET ").append(INVALIDATED_COLUMN_NAME).append(" = 1").append(" WHERE ").append(TABLE_ID_COLUMN_NAME).append(" = ").append(i2).append(" AND ").append(INVALIDATED_COLUMN_NAME).append(" = 0").append("; END");
            supportSQLiteDatabase.execSQL(sb.toString());
        }
    }

    public void addObserver(Observer observer) {
        ObserverWrapper putIfAbsent;
        String[] resolveViews = resolveViews(observer.mTables);
        int[] iArr = new int[resolveViews.length];
        int length = resolveViews.length;
        for (int i2 = 0; i2 < length; i2++) {
            Integer num = this.mTableIdLookup.get(resolveViews[i2].toLowerCase(Locale.US));
            if (num == null) {
                throw new IllegalArgumentException("There is no table with name " + resolveViews[i2]);
            }
            iArr[i2] = num.intValue();
        }
        ObserverWrapper observerWrapper = new ObserverWrapper(observer, iArr, resolveViews);
        synchronized (this.mObserverMap) {
            putIfAbsent = this.mObserverMap.putIfAbsent(observer, observerWrapper);
        }
        if (putIfAbsent == null && this.mObservedTableTracker.onAdded(iArr)) {
            syncTriggers();
        }
    }

    private String[] validateAndResolveTableNames(String[] strArr) {
        String[] resolveViews = resolveViews(strArr);
        for (String str : resolveViews) {
            if (!this.mTableIdLookup.containsKey(str.toLowerCase(Locale.US))) {
                throw new IllegalArgumentException("There is no table with name " + str);
            }
        }
        return resolveViews;
    }

    private String[] resolveViews(String[] strArr) {
        HashSet hashSet = new HashSet();
        for (String str : strArr) {
            String lowerCase = str.toLowerCase(Locale.US);
            if (this.mViewTables.containsKey(lowerCase)) {
                hashSet.addAll(this.mViewTables.get(lowerCase));
            } else {
                hashSet.add(str);
            }
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }

    private static void beginTransactionInternal(SupportSQLiteDatabase supportSQLiteDatabase) {
        if (Build.VERSION.SDK_INT >= 16 && supportSQLiteDatabase.isWriteAheadLoggingEnabled()) {
            supportSQLiteDatabase.beginTransactionNonExclusive();
        } else {
            supportSQLiteDatabase.beginTransaction();
        }
    }

    public void addWeakObserver(Observer observer) {
        addObserver(new WeakObserver(this, observer));
    }

    public void removeObserver(Observer observer) {
        ObserverWrapper remove;
        synchronized (this.mObserverMap) {
            remove = this.mObserverMap.remove(observer);
        }
        if (remove == null || !this.mObservedTableTracker.onRemoved(remove.mTableIds)) {
            return;
        }
        syncTriggers();
    }

    boolean ensureInitialization() {
        if (this.mDatabase.isOpen()) {
            if (!this.mInitialized) {
                this.mDatabase.getOpenHelper().getWritableDatabase();
            }
            if (this.mInitialized) {
                return true;
            }
            Log.e("ROOM", "database is not initialized even though it is open");
            return false;
        }
        return false;
    }

    public void refreshVersionsAsync() {
        if (this.mPendingRefresh.compareAndSet(false, true)) {
            AutoCloser autoCloser = this.mAutoCloser;
            if (autoCloser != null) {
                autoCloser.incrementCountAndEnsureDbIsOpen();
            }
            this.mDatabase.getQueryExecutor().execute(this.mRefreshRunnable);
        }
    }

    public void refreshVersionsSync() {
        AutoCloser autoCloser = this.mAutoCloser;
        if (autoCloser != null) {
            autoCloser.incrementCountAndEnsureDbIsOpen();
        }
        syncTriggers();
        this.mRefreshRunnable.run();
    }

    public void notifyObserversByTableNames(String... strArr) {
        synchronized (this.mObserverMap) {
            Iterator<Map.Entry<Observer, ObserverWrapper>> it = this.mObserverMap.iterator();
            while (it.hasNext()) {
                Map.Entry<Observer, ObserverWrapper> next = it.next();
                if (!next.getKey().isRemote()) {
                    next.getValue().notifyByTableNames(strArr);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void syncTriggers(SupportSQLiteDatabase supportSQLiteDatabase) {
        if (supportSQLiteDatabase.inTransaction()) {
            return;
        }
        while (true) {
            try {
                Lock closeLock = this.mDatabase.getCloseLock();
                closeLock.lock();
                try {
                    int[] tablesToSync = this.mObservedTableTracker.getTablesToSync();
                    if (tablesToSync == null) {
                        return;
                    }
                    int length = tablesToSync.length;
                    beginTransactionInternal(supportSQLiteDatabase);
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = tablesToSync[i2];
                        if (i3 == 1) {
                            startTrackingTable(supportSQLiteDatabase, i2);
                        } else if (i3 == 2) {
                            stopTrackingTable(supportSQLiteDatabase, i2);
                        }
                    }
                    supportSQLiteDatabase.setTransactionSuccessful();
                    supportSQLiteDatabase.endTransaction();
                    this.mObservedTableTracker.onSyncCompleted();
                } finally {
                    closeLock.unlock();
                }
            } catch (SQLiteException | IllegalStateException e2) {
                Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e2);
                return;
            }
        }
    }

    void syncTriggers() {
        if (this.mDatabase.isOpen()) {
            syncTriggers(this.mDatabase.getOpenHelper().getWritableDatabase());
        }
    }

    @Deprecated
    public <T> LiveData<T> createLiveData(String[] strArr, Callable<T> callable) {
        return createLiveData(strArr, false, callable);
    }

    public <T> LiveData<T> createLiveData(String[] strArr, boolean z, Callable<T> callable) {
        return this.mInvalidationLiveDataContainer.create(validateAndResolveTableNames(strArr), z, callable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class ObserverWrapper {
        final Observer mObserver;
        private final Set<String> mSingleTableSet;
        final int[] mTableIds;
        private final String[] mTableNames;

        ObserverWrapper(Observer observer, int[] iArr, String[] strArr) {
            this.mObserver = observer;
            this.mTableIds = iArr;
            this.mTableNames = strArr;
            if (iArr.length == 1) {
                HashSet hashSet = new HashSet();
                hashSet.add(strArr[0]);
                this.mSingleTableSet = Collections.unmodifiableSet(hashSet);
                return;
            }
            this.mSingleTableSet = null;
        }

        void notifyByTableInvalidStatus(Set<Integer> set) {
            int length = this.mTableIds.length;
            Set<String> set2 = null;
            for (int i2 = 0; i2 < length; i2++) {
                if (set.contains(Integer.valueOf(this.mTableIds[i2]))) {
                    if (length == 1) {
                        set2 = this.mSingleTableSet;
                    } else {
                        if (set2 == null) {
                            set2 = new HashSet<>(length);
                        }
                        set2.add(this.mTableNames[i2]);
                    }
                }
            }
            if (set2 != null) {
                this.mObserver.onInvalidated(set2);
            }
        }

        void notifyByTableNames(String[] strArr) {
            Set<String> set = null;
            if (this.mTableNames.length == 1) {
                int length = strArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (strArr[i2].equalsIgnoreCase(this.mTableNames[0])) {
                        set = this.mSingleTableSet;
                        break;
                    } else {
                        i2++;
                    }
                }
            } else {
                HashSet hashSet = new HashSet();
                for (String str : strArr) {
                    String[] strArr2 = this.mTableNames;
                    int length2 = strArr2.length;
                    int i3 = 0;
                    while (true) {
                        if (i3 < length2) {
                            String str2 = strArr2[i3];
                            if (str2.equalsIgnoreCase(str)) {
                                hashSet.add(str2);
                                break;
                            }
                            i3++;
                        }
                    }
                }
                if (hashSet.size() > 0) {
                    set = hashSet;
                }
            }
            if (set != null) {
                this.mObserver.onInvalidated(set);
            }
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Observer {
        final String[] mTables;

        boolean isRemote() {
            return false;
        }

        public abstract void onInvalidated(Set<String> set);

        protected Observer(String str, String... strArr) {
            String[] strArr2 = (String[]) Arrays.copyOf(strArr, strArr.length + 1);
            this.mTables = strArr2;
            strArr2[strArr.length] = str;
        }

        public Observer(String[] strArr) {
            this.mTables = (String[]) Arrays.copyOf(strArr, strArr.length);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class ObservedTableTracker {
        static final int ADD = 1;
        static final int NO_OP = 0;
        static final int REMOVE = 2;
        boolean mNeedsSync;
        boolean mPendingSync;
        final long[] mTableObservers;
        final int[] mTriggerStateChanges;
        final boolean[] mTriggerStates;

        ObservedTableTracker(int i2) {
            long[] jArr = new long[i2];
            this.mTableObservers = jArr;
            boolean[] zArr = new boolean[i2];
            this.mTriggerStates = zArr;
            this.mTriggerStateChanges = new int[i2];
            Arrays.fill(jArr, 0L);
            Arrays.fill(zArr, false);
        }

        boolean onAdded(int... iArr) {
            boolean z;
            synchronized (this) {
                z = false;
                for (int i2 : iArr) {
                    long[] jArr = this.mTableObservers;
                    long j2 = jArr[i2];
                    jArr[i2] = 1 + j2;
                    if (j2 == 0) {
                        this.mNeedsSync = true;
                        z = true;
                    }
                }
            }
            return z;
        }

        boolean onRemoved(int... iArr) {
            boolean z;
            synchronized (this) {
                z = false;
                for (int i2 : iArr) {
                    long[] jArr = this.mTableObservers;
                    long j2 = jArr[i2];
                    jArr[i2] = j2 - 1;
                    if (j2 == 1) {
                        this.mNeedsSync = true;
                        z = true;
                    }
                }
            }
            return z;
        }

        void resetTriggerState() {
            synchronized (this) {
                Arrays.fill(this.mTriggerStates, false);
                this.mNeedsSync = true;
            }
        }

        int[] getTablesToSync() {
            synchronized (this) {
                if (this.mNeedsSync && !this.mPendingSync) {
                    int length = this.mTableObservers.length;
                    int i2 = 0;
                    while (true) {
                        int i3 = 1;
                        if (i2 < length) {
                            boolean z = this.mTableObservers[i2] > 0;
                            boolean[] zArr = this.mTriggerStates;
                            if (z != zArr[i2]) {
                                int[] iArr = this.mTriggerStateChanges;
                                if (!z) {
                                    i3 = 2;
                                }
                                iArr[i2] = i3;
                            } else {
                                this.mTriggerStateChanges[i2] = 0;
                            }
                            zArr[i2] = z;
                            i2++;
                        } else {
                            this.mPendingSync = true;
                            this.mNeedsSync = false;
                            return this.mTriggerStateChanges;
                        }
                    }
                }
                return null;
            }
        }

        void onSyncCompleted() {
            synchronized (this) {
                this.mPendingSync = false;
            }
        }
    }

    /* loaded from: classes.dex */
    static class WeakObserver extends Observer {
        final WeakReference<Observer> mDelegateRef;
        final InvalidationTracker mTracker;

        WeakObserver(InvalidationTracker invalidationTracker, Observer observer) {
            super(observer.mTables);
            this.mTracker = invalidationTracker;
            this.mDelegateRef = new WeakReference<>(observer);
        }

        @Override // androidx.room.InvalidationTracker.Observer
        public void onInvalidated(Set<String> set) {
            Observer observer = this.mDelegateRef.get();
            if (observer == null) {
                this.mTracker.removeObserver(this);
            } else {
                observer.onInvalidated(set);
            }
        }
    }
}
