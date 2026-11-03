package com.bytedance.applog.priority.original;

import kotlin.Metadata;
/* compiled from: SqliteDatabase.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"colEventJson", "", "colEventName", "colEventStained", "colEventType", "colGroupId", "colMonitorId", "colTimestamp", "createTableSql", "eventTableName", "sqliteDbVersion", "", "priority_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class m {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3280a = 1;

    /* renamed from: b  reason: collision with root package name */
    public static final String f3281b = "event";

    /* renamed from: c  reason: collision with root package name */
    public static final String f3282c = "group_id";

    /* renamed from: d  reason: collision with root package name */
    public static final String f3283d = "monitor_id";

    /* renamed from: e  reason: collision with root package name */
    public static final String f3284e = "event_type";

    /* renamed from: f  reason: collision with root package name */
    public static final String f3285f = "event_stained";

    /* renamed from: g  reason: collision with root package name */
    public static final String f3286g = "timestamp";

    /* renamed from: h  reason: collision with root package name */
    public static final String f3287h = "event_name";

    /* renamed from: i  reason: collision with root package name */
    public static final String f3288i = "event_json";

    /* renamed from: j  reason: collision with root package name */
    public static final String f3289j = "CREATE TABLE IF NOT EXISTS event (\nid integer primary key autoincrement, \ngroup_id integer NOT NULL, \nmonitor_id varchar(32) NOT NULL, \nevent_type integer NOT NULL, \nevent_stained integer default 0, \ntimestamp integer NOT NULL, \nevent_name varchar(255) NOT NULL, \nevent_json TEXT NOT NULL \n)";
}
