package com.bytedance.ug.sdk.share.api.entity;

import android.graphics.Bitmap;
import com.bytedance.ug.sdk.share.api.callback.f;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
/* compiled from: ShareContent.java */
/* loaded from: classes8.dex */
public class h implements Serializable {
    private com.bytedance.ug.sdk.share.api.panel.d A;
    private com.bytedance.ug.sdk.share.api.ui.f B;
    private com.bytedance.ug.sdk.share.api.ui.c C;
    private com.bytedance.ug.sdk.share.api.ui.h D;
    private com.bytedance.ug.sdk.share.api.ui.i E;
    private com.bytedance.ug.sdk.share.api.ui.b F;
    private com.bytedance.ug.sdk.share.api.ui.e G;
    private com.bytedance.ug.sdk.share.impl.callback.f H;
    private String I;

    /* renamed from: J  reason: collision with root package name */
    private com.bytedance.ug.sdk.share.api.callback.f f15565J;
    private String K;
    private ArrayList<Bitmap> L;

    /* renamed from: a  reason: collision with root package name */
    private com.bytedance.ug.sdk.share.api.panel.d f15566a;

    /* renamed from: b  reason: collision with root package name */
    private String f15567b;

    /* renamed from: c  reason: collision with root package name */
    private String f15568c;

    /* renamed from: d  reason: collision with root package name */
    private String f15569d;

    /* renamed from: e  reason: collision with root package name */
    private String f15570e;

    /* renamed from: f  reason: collision with root package name */
    private String f15571f;

    /* renamed from: g  reason: collision with root package name */
    private String f15572g;

    /* renamed from: h  reason: collision with root package name */
    private String f15573h;

    /* renamed from: i  reason: collision with root package name */
    private String f15574i;

    /* renamed from: j  reason: collision with root package name */
    private String f15575j;
    private String k;
    private String l;
    private String m;
    private m n;
    private List<m> o;
    private s p;
    private s q;
    private Bitmap r;
    private com.bytedance.ug.sdk.share.api.callback.l s;
    private j t;
    private JSONObject u;
    private i v;
    private i w;
    private String x;
    private String y;
    private String z;

    private h() {
        this.n = m.NORMAL;
        this.v = i.ALL;
        this.w = i.TEXT;
    }

    public com.bytedance.ug.sdk.share.api.callback.l getEventCallBack() {
        return this.s;
    }

    public void setEventCallBack(com.bytedance.ug.sdk.share.api.callback.l lVar) {
        this.s = lVar;
    }

    public j getExtraParams() {
        return this.t;
    }

    public void setExtraParams(j jVar) {
        this.t = jVar;
    }

    public JSONObject getLogEventParams() {
        return this.u;
    }

    public Bitmap getImage() {
        return this.r;
    }

    public void setImage(Bitmap bitmap) {
        this.r = bitmap;
    }

    public String getTitle() {
        return this.f15573h;
    }

    public void setTitle(String str) {
        this.f15573h = str;
    }

    public String getTargetUrl() {
        return this.f15574i;
    }

    public String getCopyUrl() {
        return this.m;
    }

    public void setCopyUrl(String str) {
        this.m = str;
    }

    public void setTargetUrl(String str) {
        this.f15574i = str;
    }

    public String getText() {
        return this.l;
    }

    public void setText(String str) {
        this.l = str;
    }

    public m getShareStrategy() {
        return this.n;
    }

    public void setShareStrategy(m mVar) {
        this.n = mVar;
    }

    public List<m> getShareStrategyList() {
        return this.o;
    }

    public void setShareStrategyList(List<m> list) {
        this.o = list;
    }

    public s getTokenShareInfo() {
        return this.p;
    }

    public void setTokenShareInfo(s sVar) {
        this.p = sVar;
    }

    public s getImageTokenShareInfo() {
        return this.q;
    }

    public void setImageTokenShareInfo(s sVar) {
        this.q = sVar;
    }

    public com.bytedance.ug.sdk.share.api.panel.d getShareChanelType() {
        return this.f15566a;
    }

    public void setShareChannelType(com.bytedance.ug.sdk.share.api.panel.d dVar) {
        this.f15566a = dVar;
    }

    public i getShareContentType() {
        return this.v;
    }

    public void setShareContentType(i iVar) {
        this.v = iVar;
    }

    public i getSystemShareType() {
        return this.w;
    }

    public void setSystemShareType(i iVar) {
        this.w = iVar;
    }

    public String getImageUrl() {
        return this.f15567b;
    }

    public void a(ArrayList<Bitmap> arrayList) {
        this.L = arrayList;
    }

    public ArrayList<Bitmap> b() {
        return this.L;
    }

    public void setImageUrl(String str) {
        this.f15567b = str;
    }

    public String getVideoUrl() {
        return this.f15568c;
    }

    public void setVideoUrl(String str) {
        this.f15568c = str;
    }

    public String getVideoName() {
        return this.f15569d;
    }

    public void setVideoName(String str) {
        this.f15569d = str;
    }

    public String getAudioUrl() {
        return this.f15570e;
    }

    public void setAudioUrl(String str) {
        this.f15570e = str;
    }

    public void setFileUrl(String str) {
        this.f15575j = str;
    }

    public String getQrcodeImageUrl() {
        return this.f15571f;
    }

    public void setQrcodeImageUrl(String str) {
        this.f15571f = str;
    }

    public String getHiddenImageUrl() {
        return this.f15572g;
    }

    public String getFileUrl() {
        return this.f15575j;
    }

    public String getFileName() {
        return this.k;
    }

    public void setFileName(String str) {
        this.k = str;
    }

    public void setHiddenImageUrl(String str) {
        this.f15572g = str;
    }

    public String getFrom() {
        return this.z;
    }

    public void setFrom(String str) {
        this.z = str;
    }

    public com.bytedance.ug.sdk.share.api.panel.d getFromChannel() {
        return this.A;
    }

    public void setFromChannel(com.bytedance.ug.sdk.share.api.panel.d dVar) {
        this.A = dVar;
    }

    public String getPanelId() {
        return this.x;
    }

    public void setPanelId(String str) {
        this.x = str;
    }

    public String getResourceId() {
        return this.y;
    }

    public com.bytedance.ug.sdk.share.api.ui.f getShareTokenDialog() {
        return this.B;
    }

    public com.bytedance.ug.sdk.share.api.ui.c getImageTokenDialog() {
        return this.C;
    }

    public com.bytedance.ug.sdk.share.api.ui.h getVideoGuideDialog() {
        return this.D;
    }

    public com.bytedance.ug.sdk.share.api.ui.i getVideoShareDialog() {
        return this.E;
    }

    public com.bytedance.ug.sdk.share.api.ui.b getDownloadProgressDialog() {
        return this.F;
    }

    public com.bytedance.ug.sdk.share.api.ui.e getShareProgressView() {
        return this.G;
    }

    public com.bytedance.ug.sdk.share.impl.callback.f getVideoDialogCallback() {
        return this.H;
    }

    public void setVideoDialogCallback(com.bytedance.ug.sdk.share.impl.callback.f fVar) {
        this.H = fVar;
    }

    public void setResourceId(String str) {
        this.y = str;
    }

    public String getExtra() {
        return this.I;
    }

    public void setExtra(String str) {
        this.I = str;
    }

    public com.bytedance.ug.sdk.share.api.callback.f getShareTokenGenerator() {
        return this.f15565J;
    }

    public void setShareToken(String str) {
        this.K = str;
    }

    public String getShareToken() {
        return this.K;
    }

    /* compiled from: ShareContent.java */
    /* loaded from: classes8.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private h f15576a = new h();

        public a setShareContentType(i iVar) {
            if (iVar != null) {
                this.f15576a.v = iVar;
            }
            return this;
        }

        public a setSystemShareType(i iVar) {
            if (iVar != null) {
                this.f15576a.w = iVar;
            }
            return this;
        }

        public a setEventCallBack(com.bytedance.ug.sdk.share.api.callback.l lVar) {
            this.f15576a.s = lVar;
            return this;
        }

        public a setImage(Bitmap bitmap) {
            this.f15576a.r = bitmap;
            return this;
        }

        public a setTitle(String str) {
            this.f15576a.f15573h = str;
            return this;
        }

        public a setTargetUrl(String str) {
            this.f15576a.f15574i = str;
            return this;
        }

        public a setText(String str) {
            this.f15576a.l = str;
            return this;
        }

        public a setShareStrategy(m mVar) {
            this.f15576a.n = mVar;
            return this;
        }

        public a setShareStrategyList(List<m> list) {
            this.f15576a.o = list;
            return this;
        }

        public a setTokenShareInfo(s sVar) {
            this.f15576a.p = sVar;
            return this;
        }

        public a setImageTokenShareInfo(s sVar) {
            this.f15576a.q = sVar;
            return this;
        }

        public a setShareChannelType(com.bytedance.ug.sdk.share.api.panel.d dVar) {
            this.f15576a.f15566a = dVar;
            return this;
        }

        public a a(ArrayList<Bitmap> arrayList) {
            this.f15576a.L = arrayList;
            return this;
        }

        public a setImageUrl(String str) {
            this.f15576a.f15567b = str;
            return this;
        }

        public a setVideoUrl(String str) {
            this.f15576a.f15568c = str;
            return this;
        }

        public a setVideoName(String str) {
            this.f15576a.f15569d = str;
            return this;
        }

        public a setAudioUrl(String str) {
            this.f15576a.f15570e = str;
            return this;
        }

        public a setQrcodeImageUrl(String str) {
            this.f15576a.f15571f = str;
            return this;
        }

        public a setHiddenImageUrl(String str) {
            this.f15576a.f15572g = str;
            return this;
        }

        public a setCopyUrl(String str) {
            this.f15576a.m = str;
            return this;
        }

        public a setFileUrl(String str) {
            this.f15576a.f15575j = str;
            return this;
        }

        public a setFileName(String str) {
            this.f15576a.k = str;
            return this;
        }

        public a setExtraParams(j jVar) {
            this.f15576a.t = jVar;
            return this;
        }

        public a setLogEventParams(JSONObject jSONObject) {
            this.f15576a.u = jSONObject;
            return this;
        }

        public a setShareTokenDialog(com.bytedance.ug.sdk.share.api.ui.f fVar) {
            this.f15576a.B = fVar;
            return this;
        }

        public a setImageTokenDialog(com.bytedance.ug.sdk.share.api.ui.c cVar) {
            this.f15576a.C = cVar;
            return this;
        }

        public a setVideoGuideDialog(com.bytedance.ug.sdk.share.api.ui.h hVar) {
            this.f15576a.D = hVar;
            return this;
        }

        public a setVideoShareDialog(com.bytedance.ug.sdk.share.api.ui.i iVar) {
            this.f15576a.E = iVar;
            return this;
        }

        public a setDownloadProgressDialog(com.bytedance.ug.sdk.share.api.ui.b bVar) {
            this.f15576a.F = bVar;
            return this;
        }

        public a setShareProgressView(com.bytedance.ug.sdk.share.api.ui.e eVar) {
            this.f15576a.G = eVar;
            return this;
        }

        public a setVideoDialogCallback(com.bytedance.ug.sdk.share.impl.callback.f fVar) {
            this.f15576a.H = fVar;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public a a(String str) {
            this.f15576a.z = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public a a(com.bytedance.ug.sdk.share.api.panel.d dVar) {
            this.f15576a.A = dVar;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public a b(String str) {
            this.f15576a.x = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public a c(String str) {
            this.f15576a.y = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public a d(String str) {
            this.f15576a.I = str;
            return this;
        }

        public a setShareTokenGenerator(com.bytedance.ug.sdk.share.api.callback.f fVar) {
            this.f15576a.f15565J = fVar;
            return this;
        }

        public h build() {
            if (this.f15576a.f15565J == null) {
                this.f15576a.f15565J = new f.a();
            }
            return this.f15576a;
        }

        public a setShareToken(String str) {
            this.f15576a.K = str;
            return this;
        }
    }

    /* renamed from: clone */
    public h m290clone() {
        s sVar;
        s sVar2;
        j jVar = null;
        if (this.p != null) {
            sVar = new s();
            sVar.setTitle(this.p.getTitle());
            sVar.setDescription(this.p.getDescription());
            sVar.setTips(this.p.getTips());
        } else {
            sVar = null;
        }
        if (this.q != null) {
            sVar2 = new s();
            sVar2.setTitle(this.q.getTitle());
            sVar2.setDescription(this.q.getDescription());
            sVar2.setTips(this.q.getTips());
        } else {
            sVar2 = null;
        }
        if (this.t != null) {
            jVar = new j();
            jVar.setDYExtra(this.t.getDYExtra());
            jVar.setTiktokExtra(this.t.getTiktokExtra());
            jVar.setWXExtra(this.t.getWXExtra());
            jVar.setWBExtra(this.t.getWBExtra());
            jVar.setQQExtra(this.t.getQQExtra());
            jVar.setAppExtra(this.t.getAppExtra());
            jVar.setSystemExtra(this.t.getSystemExtra());
            jVar.setImagePanelExtra(this.t.getImagePanelExtra());
        }
        return new a().setShareContentType(this.v).setSystemShareType(this.w).setShareChannelType(this.f15566a).setShareStrategy(this.n).setShareStrategyList(this.o).setTitle(this.f15573h).setText(this.l).setTargetUrl(this.f15574i).setCopyUrl(this.m).setImage(this.r).a(this.L).setImageUrl(this.f15567b).setHiddenImageUrl(this.f15572g).setQrcodeImageUrl(this.f15571f).setVideoUrl(this.f15568c).setVideoName(this.f15569d).setAudioUrl(this.f15570e).setFileName(this.k).setFileUrl(this.f15575j).setShareTokenDialog(this.B).setImageTokenDialog(this.C).setVideoGuideDialog(this.D).setVideoShareDialog(this.E).setDownloadProgressDialog(this.F).setShareProgressView(this.G).setVideoDialogCallback(this.H).setEventCallBack(this.s).setTokenShareInfo(sVar).setImageTokenShareInfo(sVar2).setExtraParams(jVar).setLogEventParams(this.u).a(this.z).a(this.A).b(this.x).c(this.y).d(this.I).setShareTokenGenerator(this.f15565J).setShareToken(this.K).build();
    }
}
