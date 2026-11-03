package com.bytedance.pipo.pipocore.utils;

import android.os.Build;
import com.bytedance.crash.Ensure;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.ss.android.common.lib.AppLogNewUtils;
import com.ss.android.token.e;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;
/* compiled from: ReportUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u0017\u0018B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\bH\u0007J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006H\u0007J,\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\bH\u0007JJ\u0010\u000e\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u00062\u0018\u0010\r\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\bH\u0007J\u001c\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0007¨\u0006\u0019"}, d2 = {"Lcom/bytedance/pipo/pipocore/utils/ReportUtil;", "", "()V", "onEvent", "", "event", "", "params", "", "reportCustomerThrowable", "throwable", "", "message", "data", "reportNativeStack", "nativeStack", "javaStack", "threadName", "reportSdkSession", "sdkInfo", "Lcom/bytedance/pipo/pipocore/utils/ReportUtil$SDKInfo;", "appInfo", "Lcom/bytedance/pipo/pipocore/utils/ReportUtil$AppInfo;", "AppInfo", "SDKInfo", "pipocore_release"}, k = 1, mv = {1, 4, 1})
/* loaded from: classes2.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static final a f11007a = new a();

    private a() {
    }

    public static /* synthetic */ void a(b bVar, C0162a c0162a, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c0162a = null;
        }
        a(bVar, c0162a);
    }

    @JvmStatic
    public static final void a(b sdkInfo, C0162a c0162a) {
        String b2;
        Intrinsics.checkNotNullParameter(sdkInfo, "sdkInfo");
        if (sdkInfo.a().length() == 0) {
            throw new RuntimeException("SDK id is Empty");
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("sdk_aid", sdkInfo.a());
        linkedHashMap.put("sdk_version", sdkInfo.b());
        String str = "";
        linkedHashMap.put("app_version", (c0162a == null || (r1 = c0162a.a()) == null) ? "" : "");
        if (c0162a != null && (b2 = c0162a.b()) != null) {
            str = b2;
        }
        linkedHashMap.put("update_version_code", str);
        linkedHashMap.put("os_version", Build.VERSION.RELEASE);
        a(e.f18554d, linkedHashMap);
    }

    @JvmStatic
    public static final void a(String event, Map<String, String> params) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(params, "params");
        AppLogNewUtils.onEventV3(event, new JSONObject(params));
    }

    @JvmStatic
    public static final void a(String event) {
        Intrinsics.checkNotNullParameter(event, "event");
        a(event, new LinkedHashMap());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ReportUtil.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 4, 1})
    /* loaded from: classes2.dex */
    public static final class c implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Throwable f11012a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ String f11013b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Map f11014c;

        c(Throwable th, String str, Map map) {
            this.f11012a = th;
            this.f11013b = str;
            this.f11014c = map;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Ensure.ensureNotReachHere(this.f11012a, this.f11013b, this.f11014c);
        }
    }

    @JvmStatic
    public static final void a(Throwable throwable, String message, Map<String, String> data) {
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(data, "data");
        new Thread(new c(throwable, message, data)).start();
    }

    @JvmStatic
    public static final void a(Throwable throwable, String message) {
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        Intrinsics.checkNotNullParameter(message, "message");
        a(throwable, message, new LinkedHashMap());
    }

    /* compiled from: ReportUtil.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 4, 1})
    /* loaded from: classes2.dex */
    static final class d implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ String f11015a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ String f11016b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f11017c;

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ String f11018d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ Map f11019e;

        d(String str, String str2, String str3, String str4, Map map) {
            this.f11015a = str;
            this.f11016b = str2;
            this.f11017c = str3;
            this.f11018d = str4;
            this.f11019e = map;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Ensure.ensureNativeStack(this.f11015a, this.f11016b, this.f11017c, this.f11018d, this.f11019e);
        }
    }

    @JvmStatic
    public static final void a(String str, String str2, String str3, String str4, Map<String, String> map) {
        new Thread(new d(str, str2, str3, str4, map)).start();
    }

    /* compiled from: ReportUtil.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/bytedance/pipo/pipocore/utils/ReportUtil$AppInfo;", "", RemoteConfigConstants.RequestFieldKey.APP_VERSION, "", "updateVersionCode", "(Ljava/lang/String;Ljava/lang/String;)V", "getAppVersion", "()Ljava/lang/String;", "getUpdateVersionCode", "pipocore_release"}, k = 1, mv = {1, 4, 1})
    /* renamed from: com.bytedance.pipo.pipocore.utils.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static final class C0162a {

        /* renamed from: a  reason: collision with root package name */
        private final String f11008a;

        /* renamed from: b  reason: collision with root package name */
        private final String f11009b;

        public C0162a(String str, String str2) {
            this.f11008a = str;
            this.f11009b = str2;
        }

        public final String a() {
            return this.f11008a;
        }

        public final String b() {
            return this.f11009b;
        }
    }

    /* compiled from: ReportUtil.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/bytedance/pipo/pipocore/utils/ReportUtil$SDKInfo;", "", "sdkId", "", "sdkVersion", "(Ljava/lang/String;Ljava/lang/String;)V", "getSdkId", "()Ljava/lang/String;", "getSdkVersion", "pipocore_release"}, k = 1, mv = {1, 4, 1})
    /* loaded from: classes2.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private final String f11010a;

        /* renamed from: b  reason: collision with root package name */
        private final String f11011b;

        public b(String sdkId, String str) {
            Intrinsics.checkNotNullParameter(sdkId, "sdkId");
            this.f11010a = sdkId;
            this.f11011b = str;
        }

        public final String a() {
            return this.f11010a;
        }

        public /* synthetic */ b(String str, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i2 & 2) != 0 ? "" : str2);
        }

        public final String b() {
            return this.f11011b;
        }
    }
}
