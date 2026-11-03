package com.bytedance.sdk.open.tiktok.base;

import android.os.Bundle;
import com.bytedance.sdk.open.tiktok.common.constants.ParamKeyConstants;
import java.util.ArrayList;
/* loaded from: classes3.dex */
public class VideoObject implements IMediaObject {
    private static final String TAG = "VideoObject";
    public ArrayList<String> mVideoPaths;

    @Override // com.bytedance.sdk.open.tiktok.base.IMediaObject
    public boolean checkArgs() {
        return true;
    }

    @Override // com.bytedance.sdk.open.tiktok.base.IMediaObject
    public int type() {
        return 3;
    }

    @Override // com.bytedance.sdk.open.tiktok.base.IMediaObject
    public void serialize(Bundle bundle) {
        bundle.putStringArrayList(ParamKeyConstants.AWEME_EXTRA_MEDIA_MESSAGE_VIDEO_PATH, this.mVideoPaths);
    }

    @Override // com.bytedance.sdk.open.tiktok.base.IMediaObject
    public void unserialize(Bundle bundle) {
        this.mVideoPaths = bundle.getStringArrayList(ParamKeyConstants.AWEME_EXTRA_MEDIA_MESSAGE_VIDEO_PATH);
    }
}
