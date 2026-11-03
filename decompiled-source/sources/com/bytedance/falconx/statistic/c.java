package com.bytedance.falconx.statistic;

import android.text.TextUtils;
import android.webkit.WebView;
import com.bytedance.apm.constant.l;
import com.bytedance.bdinstall.e;
import com.bytedance.falconx.g;
import com.bytedance.falconx.h;
import com.bytedance.falconx.utils.d;
import com.bytedance.librarian.c;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import org.json.JSONObject;
/* compiled from: StatisticThread.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static volatile c f7394a;

    /* renamed from: b  reason: collision with root package name */
    private Executor f7395b = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.bytedance.falconx.statistic.c.1
        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("falconx-io-thread");
            thread.setPriority(3);
            return thread;
        }
    });

    /* renamed from: d  reason: collision with root package name */
    private g f7396d;

    private c() {
    }

    public static c a() {
        if (f7394a == null) {
            synchronized (c.class) {
                if (f7394a == null) {
                    f7394a = new c();
                }
            }
        }
        return f7394a;
    }

    public void a(g gVar) {
        this.f7396d = gVar;
    }

    public void a(final WebView webView, final InterceptorModel interceptorModel) {
        this.f7395b.execute(new Runnable() { // from class: com.bytedance.falconx.statistic.c.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    h.a(webView, interceptorModel, true);
                    c cVar = c.this;
                    cVar.a(cVar.f7396d.j(), c.this.f7396d.i(), c.this.f7396d.k(), interceptorModel);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, String str3, InterceptorModel interceptorModel) {
        try {
            if (!TextUtils.isEmpty(this.f7396d.f()) && interceptorModel != null) {
                StatisticData statisticData = new StatisticData();
                statisticData.mCommon = new Common();
                statisticData.mCommon.appVersion = str;
                statisticData.mCommon.deviceId = str2;
                statisticData.mCommon.region = str3;
                String uuid = UUID.randomUUID().toString();
                interceptorModel.startTime = null;
                interceptorModel.logId = uuid;
                a g2 = this.f7396d.g();
                if (g2 != null) {
                    a(interceptorModel, statisticData.mCommon);
                    if ((g2 instanceof d) && interceptorModel.offlineStatus.intValue() == 1) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("aid", String.valueOf(g.l()));
                        jSONObject.put("gecko_sdk_version", "5.2.6-16k_page");
                        jSONObject.put("access_key", interceptorModel.accessKey);
                        jSONObject.put("channel", interceptorModel.channel);
                        jSONObject.put("package_id", interceptorModel.pkgVersion);
                        jSONObject.put("from", "1");
                        jSONObject.put("isCombo", interceptorModel.isCombo);
                        if (!TextUtils.isEmpty(interceptorModel.channel) && !TextUtils.isEmpty(interceptorModel.url)) {
                            String[] split = interceptorModel.url.split(interceptorModel.channel + c.a.SEPARATOR);
                            if (split.length == 2) {
                                jSONObject.put("path", split[1]);
                            }
                        }
                        jSONObject.put("offline_rule", interceptorModel.offlineRule);
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("read_duration", interceptorModel.offlineDuration);
                        ((d) g2).a(String.valueOf(1234), d.a.f7403a, jSONObject, jSONObject2, null, null, false);
                    }
                }
            }
        } catch (Throwable th) {
            com.bytedance.geckox.logger.b.w("falconx-report:", th);
        }
    }

    private static JSONObject a(InterceptorModel interceptorModel, Common common) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("params_for_special", "gecko");
        jSONObject.put("page_url", interceptorModel.pageUrl == null ? "" : interceptorModel.pageUrl);
        jSONObject.put("res_root_dir", interceptorModel.resRootDir == null ? "" : interceptorModel.resRootDir);
        jSONObject.put("resource_url", interceptorModel.url == null ? "" : interceptorModel.url);
        jSONObject.put("offline_rule", interceptorModel.offlineRule != null ? interceptorModel.offlineRule : "");
        jSONObject.put("mime_type", interceptorModel.mimeType);
        jSONObject.put("offline_status", interceptorModel.offlineStatus);
        jSONObject.put("offline_duration", interceptorModel.offlineDuration == null ? 0L : interceptorModel.offlineDuration.longValue());
        jSONObject.put("online_duration", interceptorModel.onlineDuration == null ? 0L : interceptorModel.onlineDuration.longValue());
        jSONObject.put("app_version", common.appVersion);
        jSONObject.put("sdk_version", common.sdkVersion);
        jSONObject.put("pkg_version", interceptorModel.pkgVersion != null ? interceptorModel.pkgVersion.longValue() : 0L);
        jSONObject.put("access_key", interceptorModel.accessKey);
        jSONObject.put("channel", interceptorModel.channel);
        jSONObject.put("os", common.os);
        jSONObject.put("device_id", common.deviceId);
        jSONObject.put("device_model", common.deviceModel);
        jSONObject.put("region", common.region);
        jSONObject.put(e.aE, interceptorModel.ac);
        jSONObject.put("err_code", interceptorModel.errCode);
        jSONObject.put("err_msg", interceptorModel.errMsg);
        jSONObject.put(l.KEY_LOG_ID, interceptorModel.logId);
        return jSONObject;
    }
}
