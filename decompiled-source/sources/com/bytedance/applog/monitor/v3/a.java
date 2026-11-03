package com.bytedance.applog.monitor.v3;

import com.bytedance.sdk.account.platform.base.i;
/* compiled from: EventStage.java */
/* loaded from: classes.dex */
public enum a {
    UNKNOWN(i.a.ERROR_CODE_UNKNOW),
    COLLECTED("00"),
    PARAMS_INVALID_DROP("01"),
    NOT_INIT_DROP("02"),
    PRE_PROCESS_START("03"),
    CACHED_BEFORE_HANDLE("04"),
    CACHE_DROP_BEFORE_HANDLE("05"),
    DATA_LIST_ADDED("06"),
    PRE_PROCESS_END("0F"),
    PROCESS_START("10"),
    TOURIST_BREAK("11"),
    FILTER_EVENT_BLOCKED("12"),
    FILTER_SERVER_BLOCKED("13"),
    FILTER_CUSTOM_BLOCKED("14"),
    FILTER_SAMPLING_DROPPED("15"),
    NO_SESSION_DROP("16"),
    PROCESS_END("1F"),
    DB_SAVE_START("20"),
    HANDLE_REALTIME_START("21"),
    REALTIME_SEND_START("22"),
    REALTIME_SEND_FAILED("23"),
    REALTIME_CRASH_DROP("24"),
    PRIORITY_HANDLE("28"),
    PRIORITY_ACCEPT("29"),
    DB_INSERT_START("2A"),
    DB_SAVE_FAILED("2B"),
    DB_SAVE_END("2F"),
    REPORT_START("30"),
    PACK_END("31"),
    PACK_INSERT_START("34"),
    PACK_INSERT_FAILED("35"),
    PACK_INSERT_END("36"),
    POST_START("3A"),
    POST_FAILED("3B"),
    REPORT_END("3F");
    

    /* renamed from: J  reason: collision with root package name */
    private final String f2895J;

    a(String str) {
        this.f2895J = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.f2895J;
    }

    public static a a(String str) {
        a[] values;
        for (a aVar : values()) {
            if (aVar.f2895J.equals(str)) {
                return aVar;
            }
        }
        return UNKNOWN;
    }
}
