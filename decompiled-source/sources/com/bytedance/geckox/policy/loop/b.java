package com.bytedance.geckox.policy.loop;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.bytedance.geckox.d;
import com.bytedance.geckox.model.CheckRequestBodyModel;
import com.bytedance.geckox.model.CheckRequestParamModel;
import com.bytedance.geckox.policy.loop.model.LoopRequestModel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: LoopPolicy.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final int f9259a = 1000;

    /* renamed from: b  reason: collision with root package name */
    private static final int f9260b = 3;

    /* renamed from: d  reason: collision with root package name */
    private String f9262d;

    /* renamed from: f  reason: collision with root package name */
    private int f9264f;

    /* renamed from: h  reason: collision with root package name */
    private a f9266h;

    /* renamed from: c  reason: collision with root package name */
    private Handler f9261c = new Handler(Looper.getMainLooper()) { // from class: com.bytedance.geckox.policy.loop.b.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.arg1 == 3) {
                com.bytedance.geckox.logger.b.a(d.f8984a, "[loop]loop msg.what:", Integer.valueOf(message.what), "interval:", Integer.valueOf(b.this.f9264f), "time:", Long.valueOf(System.currentTimeMillis()));
                if (b.this.f9265g == null || b.this.f9265g.isEmpty()) {
                    return;
                }
                if (b.this.f9266h != null) {
                    b.this.f9266h.a(b.this.f9264f, b.this.f9265g);
                }
                b.this.c();
            }
        }
    };

    /* renamed from: e  reason: collision with root package name */
    private AtomicBoolean f9263e = new AtomicBoolean(false);

    /* renamed from: g  reason: collision with root package name */
    private Map<String, LoopRequestModel> f9265g = new ConcurrentHashMap();

    /* compiled from: LoopPolicy.java */
    /* loaded from: classes2.dex */
    public interface a {
        void a(int i2, Map<String, LoopRequestModel> map);
    }

    public b(String str, int i2) {
        this.f9262d = str;
        this.f9264f = i2;
    }

    public synchronized void a(String str, List<String> list, Map<String, List<CheckRequestBodyModel.TargetChannel>> map, Map<String, Map<String, Object>> map2) {
        try {
            if (map != null) {
                for (String str2 : map.keySet()) {
                    LoopRequestModel a2 = a(str2);
                    if (!TextUtils.isEmpty(str) && !"default".equals(str) && !a2.getDeployment().getGroupName().contains(str)) {
                        a2.getDeployment().getGroupName().add(str);
                    }
                    if (map2 != null && map2.get(str2) != null) {
                        a2.getCustom().putAll(map2.get(str2));
                    }
                    List<CheckRequestBodyModel.TargetChannel> list2 = map.get(str2);
                    if (list2 != null && !list2.isEmpty()) {
                        for (CheckRequestBodyModel.TargetChannel targetChannel : list2) {
                            Iterator<CheckRequestBodyModel.TargetChannel> it = a2.getDeployment().getTargetChannels().iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    if (it.next().channelName.equals(targetChannel.channelName)) {
                                        break;
                                    }
                                } else {
                                    a2.getDeployment().getTargetChannels().add(targetChannel);
                                    break;
                                }
                            }
                        }
                        this.f9265g.put(str2, a2);
                    }
                    this.f9265g.put(str2, a2);
                }
            } else if (map2 != null) {
                for (String str3 : map2.keySet()) {
                    LoopRequestModel a3 = a(str3);
                    if (!TextUtils.isEmpty(str) && !"default".equals(str) && !a3.getDeployment().getGroupName().contains(str)) {
                        a3.getDeployment().getGroupName().add(str);
                    }
                    Map<String, Object> map3 = map2.get(str3);
                    if (map3 != null) {
                        a3.getCustom().putAll(map3);
                    }
                    this.f9265g.put(str3, a3);
                }
            } else if (!TextUtils.isEmpty(str) && !"default".equals(str)) {
                if (list != null && !list.isEmpty()) {
                    for (String str4 : list) {
                        LoopRequestModel a4 = a(str4);
                        a4.getDeployment().getGroupName().add(str);
                        this.f9265g.put(str4, a4);
                    }
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void a(Map<String, CheckRequestParamModel> map, Map<String, Map<String, Object>> map2) {
        if (map == null) {
            return;
        }
        for (String str : map.keySet()) {
            CheckRequestParamModel checkRequestParamModel = map.get(str);
            if (checkRequestParamModel != null) {
                LoopRequestModel a2 = a(str);
                String group = checkRequestParamModel.getGroup();
                if (!TextUtils.isEmpty(group) && !a2.getDeployment().getGroupName().contains(group)) {
                    a2.getDeployment().getGroupName().add(group);
                }
                if (map2 != null && map2.get(str) != null) {
                    a2.getCustom().putAll(map2.get(str));
                }
                List<CheckRequestBodyModel.TargetChannel> targetChannels = checkRequestParamModel.getTargetChannels();
                if (targetChannels != null && !targetChannels.isEmpty()) {
                    for (CheckRequestBodyModel.TargetChannel targetChannel : targetChannels) {
                        Iterator<CheckRequestBodyModel.TargetChannel> it = a2.getDeployment().getTargetChannels().iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (it.next().channelName.equals(targetChannel.channelName)) {
                                    break;
                                }
                            } else {
                                a2.getDeployment().getTargetChannels().add(targetChannel);
                                break;
                            }
                        }
                    }
                    this.f9265g.put(str, a2);
                }
                this.f9265g.put(str, a2);
            }
        }
    }

    private LoopRequestModel a(String str) {
        LoopRequestModel loopRequestModel = this.f9265g.get(str);
        return loopRequestModel == null ? new LoopRequestModel(new HashMap(), new LoopRequestModel.LoopDeploymentModel()) : loopRequestModel;
    }

    public void a(int i2) {
        if (this.f9264f == i2) {
            return;
        }
        if (this.f9263e.get()) {
            this.f9261c.removeMessages(this.f9264f);
            this.f9263e.set(false);
        }
        this.f9264f = i2;
        a();
    }

    public synchronized void a() {
        int i2 = this.f9264f;
        if (i2 == 0) {
            return;
        }
        if (!this.f9261c.hasMessages(i2) && !this.f9263e.get()) {
            com.bytedance.geckox.logger.b.a(d.f8984a, "[loop]start loop,interval level:", this.f9262d, ",combine deployments:", this.f9265g);
            c();
        }
    }

    public void b() {
        this.f9263e.set(false);
        this.f9261c.removeMessages(this.f9264f);
        this.f9261c = null;
        this.f9265g.clear();
        this.f9265g = null;
    }

    public void a(a aVar) {
        this.f9266h = aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.f9263e.set(true);
        Message obtainMessage = this.f9261c.obtainMessage();
        obtainMessage.what = this.f9264f;
        obtainMessage.arg1 = 3;
        this.f9261c.sendMessageDelayed(obtainMessage, this.f9264f * 1000);
    }
}
