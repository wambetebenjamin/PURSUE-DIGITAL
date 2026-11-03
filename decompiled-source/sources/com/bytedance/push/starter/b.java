package com.bytedance.push.starter;

import android.util.Log;
import com.bytedance.applog.k;
import org.json.JSONObject;
/* compiled from: PushDataObserver.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static k f11925a = new k() { // from class: com.bytedance.push.starter.b.1
        @Override // com.bytedance.applog.k
        public void a(String str, String str2, String str3) {
            Log.d("PushStarter", "onIdLoaded");
            c.startPush();
        }

        @Override // com.bytedance.applog.k
        public void a(boolean z, String str, String str2, String str3, String str4, String str5, String str6) {
            Log.d("PushStarter", "onRemoteIdGet");
            c.startPush();
        }

        @Override // com.bytedance.applog.k
        public void a(boolean z, JSONObject jSONObject) {
            Log.d("PushStarter", "onRemoteIdGet");
        }

        @Override // com.bytedance.applog.k
        public void b(boolean z, JSONObject jSONObject) {
            Log.d("PushStarter", "onRemoteIdGet");
        }

        @Override // com.bytedance.applog.k
        public void a(String str, String str2) {
            Log.d("PushStarter", "onRemoteIdGet");
        }
    };
}
