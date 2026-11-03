package com.bytedance.ug.sdk.share.api.panel;

import android.app.Activity;
import com.bytedance.ug.sdk.share.api.callback.i;
import com.bytedance.ug.sdk.share.api.callback.j;
import com.bytedance.ug.sdk.share.api.entity.h;
import java.io.Serializable;
import org.json.JSONObject;
/* compiled from: PanelContent.java */
/* loaded from: classes8.dex */
public class b implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    private Activity f15636a;

    /* renamed from: b  reason: collision with root package name */
    private com.bytedance.ug.sdk.share.impl.ui.panel.c f15637b;

    /* renamed from: c  reason: collision with root package name */
    private i f15638c;

    /* renamed from: d  reason: collision with root package name */
    private j f15639d;

    /* renamed from: e  reason: collision with root package name */
    private h f15640e;

    /* renamed from: f  reason: collision with root package name */
    private String f15641f;

    /* renamed from: g  reason: collision with root package name */
    private String f15642g;

    /* renamed from: h  reason: collision with root package name */
    private String f15643h;

    /* renamed from: i  reason: collision with root package name */
    private JSONObject f15644i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f15645j;

    private b() {
    }

    public Activity getActivity() {
        return this.f15636a;
    }

    public i getOnPanelActionCallback() {
        return this.f15638c;
    }

    public j getPanelItemsCallback() {
        return this.f15639d;
    }

    public h getShareContent() {
        return this.f15640e;
    }

    public String getCancelText() {
        return this.f15641f;
    }

    public String getPanelId() {
        return this.f15642g;
    }

    public com.bytedance.ug.sdk.share.impl.ui.panel.c getPanel() {
        return this.f15637b;
    }

    public String getResourceId() {
        return this.f15643h;
    }

    public JSONObject getRequestData() {
        return this.f15644i;
    }

    public boolean isDisableGetShareInfo() {
        if (com.bytedance.ug.sdk.share.impl.config.a.getInstance().isLocalMode()) {
            return true;
        }
        return this.f15645j;
    }

    /* compiled from: PanelContent.java */
    /* loaded from: classes8.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private b f15646a;

        public a(Activity activity) {
            b bVar = new b();
            this.f15646a = bVar;
            bVar.f15636a = activity;
        }

        public a withPanel(com.bytedance.ug.sdk.share.impl.ui.panel.c cVar) {
            this.f15646a.f15637b = cVar;
            return this;
        }

        public a withPanelActionCallback(i iVar) {
            this.f15646a.f15638c = iVar;
            return this;
        }

        public a withShareContent(h hVar) {
            this.f15646a.f15640e = hVar;
            return this;
        }

        public a withCancelBtnText(String str) {
            this.f15646a.f15641f = str;
            return this;
        }

        public a withPanelId(String str) {
            this.f15646a.f15642g = str;
            return this;
        }

        public a withResourceId(String str) {
            this.f15646a.f15643h = str;
            return this;
        }

        public a withRequestData(JSONObject jSONObject) {
            this.f15646a.f15644i = jSONObject;
            return this;
        }

        public a withDisableGetShreInfo(boolean z) {
            this.f15646a.f15645j = z;
            return this;
        }

        public a withPanelItemsCallback(j jVar) {
            this.f15646a.f15639d = jVar;
            return this;
        }

        public b build() {
            if (this.f15646a.getShareContent() != null) {
                com.bytedance.ug.sdk.share.impl.manager.d.getInstance().setShareEventCallback(this.f15646a.getShareContent().getEventCallBack());
            }
            return this.f15646a;
        }
    }
}
