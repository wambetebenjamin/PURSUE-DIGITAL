package com.bytedance.ug.sdk.share.api.callback;
/* compiled from: ExposedPanelActionCallback.java */
/* loaded from: classes8.dex */
public interface a {

    /* compiled from: ExposedPanelActionCallback.java */
    /* renamed from: com.bytedance.ug.sdk.share.api.callback.a$a  reason: collision with other inner class name */
    /* loaded from: classes8.dex */
    public static abstract class AbstractC0247a implements a {
        @Override // com.bytedance.ug.sdk.share.api.callback.a
        public boolean dismissLoading() {
            return false;
        }

        @Override // com.bytedance.ug.sdk.share.api.callback.a
        public boolean interceptPanelClick(com.bytedance.ug.sdk.share.api.entity.h hVar, d dVar) {
            return false;
        }

        @Override // com.bytedance.ug.sdk.share.api.callback.a
        public boolean showLoading() {
            return false;
        }
    }

    boolean dismissLoading();

    boolean interceptPanelClick(com.bytedance.ug.sdk.share.api.entity.h hVar, d dVar);

    boolean showLoading();
}
