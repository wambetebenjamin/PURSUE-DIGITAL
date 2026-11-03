package com.bytedance.apm.constant;

import java.util.ArrayList;
import java.util.List;
/* compiled from: ReportUrl.java */
/* loaded from: classes.dex */
public class n {
    public static final String AMERICA_DOMAIN_1 = "https://i.isnssdk.com";
    public static final String AMERICA_DOMAIN_2 = "https://mon.isnssdk.com";
    public static final String DEFAULT_EXCEPTION_UPLOAD_URL = "https://i.isnssdk.com/monitor/collect/c/exception";
    public static final String DEFAULT_FILE_UPLOAD_URL = "https://i.isnssdk.com/monitor/collect/c/logcollect";
    public static final List<String> EXCEPTION_UPLOAD_URL_LIST;
    public static final List<String> FETCH_SETTING_LIST;
    public static final List<String> REPORT_TRACING_URL_LIST;
    public static final List<String> REPORT_URL_LIST;
    public static final String SINGAPORE_DOMAIN_1 = "https://mon.byteoversea.com";
    public static final String SINGAPORE_DOMAIN_2 = "https://i.sgsnssdk.com";
    public static final List<String> SNG_EXCEPTION_UPLOAD_URL_LIST;
    public static final List<String> SNG_FETCH_SETTING_URL_LIST;
    public static final List<String> SNG_NORMAL_LOG_REPORT_URL_LIST;

    static {
        ArrayList arrayList = new ArrayList();
        FETCH_SETTING_LIST = arrayList;
        arrayList.add("https://i.isnssdk.com/monitor/appmonitor/v4/settings");
        arrayList.add("https://mon.isnssdk.com/monitor/appmonitor/v4/settings");
        ArrayList arrayList2 = new ArrayList();
        SNG_FETCH_SETTING_URL_LIST = arrayList2;
        arrayList2.add("https://mon.byteoversea.com/monitor/appmonitor/v4/settings");
        arrayList2.add("https://i.sgsnssdk.com/monitor/appmonitor/v4/settings");
        ArrayList arrayList3 = new ArrayList();
        REPORT_URL_LIST = arrayList3;
        arrayList3.add("https://i.isnssdk.com/monitor/collect/batch/");
        arrayList3.add("https://mon.isnssdk.com/monitor/collect/batch/");
        ArrayList arrayList4 = new ArrayList();
        REPORT_TRACING_URL_LIST = arrayList4;
        arrayList4.add("https://i.isnssdk.com/monitor/collect/c/trace_collect");
        ArrayList arrayList5 = new ArrayList();
        SNG_NORMAL_LOG_REPORT_URL_LIST = arrayList5;
        arrayList5.add("https://mon.byteoversea.com/monitor/collect/batch/");
        arrayList5.add("https://i.sgsnssdk.com/monitor/collect/batch/");
        ArrayList arrayList6 = new ArrayList();
        EXCEPTION_UPLOAD_URL_LIST = arrayList6;
        arrayList6.add(DEFAULT_EXCEPTION_UPLOAD_URL);
        arrayList6.add("https://mon.isnssdk.com/monitor/collect/c/exception");
        ArrayList arrayList7 = new ArrayList();
        SNG_EXCEPTION_UPLOAD_URL_LIST = arrayList7;
        arrayList7.add("https://mon.byteoversea.com/monitor/collect/c/exception");
        arrayList7.add("https://i.sgsnssdk.com/monitor/collect/c/exception");
    }
}
