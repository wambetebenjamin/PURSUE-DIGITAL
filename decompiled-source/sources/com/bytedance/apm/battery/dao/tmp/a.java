package com.bytedance.apm.battery.dao.tmp;

import android.content.ContentValues;
import com.bytedance.apm.battery.dao.a;
import com.bytedance.apm.entity.b;
import java.util.List;
/* compiled from: BatteryTmpLogDao.java */
/* loaded from: classes.dex */
public class a extends com.bytedance.apm.battery.dao.a<b> implements a.InterfaceC0013a<b> {

    /* renamed from: b  reason: collision with root package name */
    private static volatile a f601b = null;

    /* renamed from: c  reason: collision with root package name */
    private static String f602c = "_id <= ? ";

    /* renamed from: d  reason: collision with root package name */
    private static final String[] f603d = {"_id", "front", "type", "timestamp", com.bytedance.apm.battery.dao.b.BATTERY_COL_ACCUMULATION, "version_id", "source", "status", "scene", "process", com.bytedance.apm.battery.dao.b.BATTERY_COL_MAIN_PROCESS, "sid"};

    /* renamed from: e  reason: collision with root package name */
    private static String f604e = "main_process = 1 AND delete_flag = 0";

    /* renamed from: f  reason: collision with root package name */
    private static String f605f = "main_process = 0 AND delete_flag = 0 AND timestamp <= ? ";

    @Override // com.bytedance.apm.battery.dao.a
    public String getTableName() {
        return com.bytedance.apm.battery.dao.b.T_BATTERY;
    }

    private a() {
    }

    public static a getInstance() {
        if (f601b == null) {
            synchronized (a.class) {
                if (f601b == null) {
                    f601b = new a();
                }
            }
        }
        return f601b;
    }

    @Override // com.bytedance.apm.battery.dao.a
    public String[] getColumns() {
        return f603d;
    }

    @Override // com.bytedance.apm.battery.dao.a
    public ContentValues getContentValues(b bVar) {
        if (bVar == null) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("front", Integer.valueOf(bVar.isFront() ? 1 : 0));
        contentValues.put("source", bVar.getSource());
        contentValues.put("type", bVar.getType());
        contentValues.put("timestamp", Long.valueOf(bVar.getTime()));
        contentValues.put(com.bytedance.apm.battery.dao.b.BATTERY_COL_ACCUMULATION, Long.valueOf(bVar.getAccumulation()));
        contentValues.put("version_id", Long.valueOf(bVar.getVersionId()));
        contentValues.put("status", Integer.valueOf(bVar.isStatus() ? 1 : 0));
        contentValues.put("scene", bVar.getScene());
        contentValues.put(com.bytedance.apm.battery.dao.b.BATTERY_COL_MAIN_PROCESS, Integer.valueOf(bVar.isMainProcess() ? 1 : 0));
        contentValues.put("process", bVar.getProcessName());
        contentValues.put("sid", bVar.getStartUuid());
        return contentValues;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.bytedance.apm.battery.dao.a.InterfaceC0013a
    public b get(a.b bVar) {
        long j2 = bVar.getLong("_id");
        long j3 = bVar.getLong("front");
        String string = bVar.getString("type");
        long j4 = bVar.getLong("timestamp");
        long j5 = bVar.getLong(com.bytedance.apm.battery.dao.b.BATTERY_COL_ACCUMULATION);
        long j6 = bVar.getLong("version_id");
        String string2 = bVar.getString("source");
        long j7 = bVar.getLong("status");
        String string3 = bVar.getString("scene");
        int i2 = bVar.getInt(com.bytedance.apm.battery.dao.b.BATTERY_COL_MAIN_PROCESS);
        String string4 = bVar.getString("process");
        b bVar2 = new b(j3 != 0, j4, string, j7 != 0, j5, string3, string2);
        bVar2.setProcessName(string4);
        bVar2.setId(j2);
        bVar2.setVersionId(j6);
        bVar2.setMainProcess(i2 == 1);
        bVar2.setStartUuid(bVar.getString("sid"));
        return bVar2;
    }

    public synchronized long saveLog(b bVar) {
        if (bVar == null) {
            return -1L;
        }
        try {
            ContentValues contentValues = new ContentValues();
            int i2 = 1;
            contentValues.put("front", Integer.valueOf(bVar.isFront() ? 1 : 0));
            contentValues.put("source", bVar.getSource());
            contentValues.put("type", bVar.getType());
            contentValues.put("timestamp", Long.valueOf(bVar.getTime()));
            contentValues.put(com.bytedance.apm.battery.dao.b.BATTERY_COL_ACCUMULATION, Long.valueOf(bVar.getAccumulation()));
            contentValues.put("version_id", Long.valueOf(bVar.getVersionId()));
            contentValues.put("status", Integer.valueOf(bVar.isStatus() ? 1 : 0));
            contentValues.put("scene", bVar.getScene());
            if (!bVar.isMainProcess()) {
                i2 = 0;
            }
            contentValues.put(com.bytedance.apm.battery.dao.b.BATTERY_COL_MAIN_PROCESS, Integer.valueOf(i2));
            contentValues.put("process", bVar.getProcessName());
            contentValues.put("sid", bVar.getStartUuid());
            return insert(contentValues);
        } catch (Exception unused) {
            return -1L;
        }
    }

    public synchronized List<b> getLogs(boolean z, long j2) {
        List<b> query;
        if (z) {
            query = query(f604e, null, "_id", this);
        } else {
            query = query(f605f, new String[]{String.valueOf(j2)}, "_id", this);
        }
        return query;
    }

    public synchronized void updateDeleteFlag(long j2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(com.bytedance.apm.battery.dao.b.COL_DELETE, (Integer) 1);
        update(contentValues, f602c, new String[]{String.valueOf(j2)});
    }
}
