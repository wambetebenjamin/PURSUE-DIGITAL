package com.bytedance.ug.sdk.share.impl.helper;

import android.app.Activity;
import com.bytedance.ug.sdk.share.api.entity.h;
import com.bytedance.ug.sdk.share.impl.utils.k;
/* compiled from: VideoGuideShareHelper.java */
/* loaded from: classes8.dex */
public class d {
    public boolean shareVideo(h hVar) {
        if (hVar == null) {
            return false;
        }
        return a(hVar);
    }

    private boolean a(h hVar) {
        Activity topActivity = com.bytedance.ug.sdk.share.impl.config.a.getInstance().getTopActivity();
        if (topActivity == null) {
            return false;
        }
        int pref = k.getInstance().getPref(k.SP_SHOW_SHARE_VIDEO_SHARE_DIALOG, 0);
        if (pref >= com.bytedance.ug.sdk.share.impl.config.a.getInstance().getShowSaveVideoShareDialogTimes()) {
            return new e().directShareVideo(hVar);
        }
        k.getInstance().setPref(k.SP_SHOW_SHARE_VIDEO_SHARE_DIALOG, pref + 1);
        com.bytedance.ug.sdk.share.api.ui.h videoGuideDialog = hVar.getVideoGuideDialog();
        if (videoGuideDialog == null && (videoGuideDialog = com.bytedance.ug.sdk.share.impl.config.a.getInstance().getVideoGuideDialog(topActivity)) == null) {
            return false;
        }
        new com.bytedance.ug.sdk.share.impl.ui.video.a(topActivity, hVar, videoGuideDialog).show();
        return true;
    }
}
