package com.bytedance.sdk.open.tiktok.base;

import android.os.Bundle;
import android.util.Log;
import com.bytedance.sdk.open.tiktok.common.constants.ParamKeyConstants;
import java.util.ArrayList;
/* loaded from: classes3.dex */
public class MediaContent {
    private static final String TAG = "AWEME.SDK.MediaContent";
    public IMediaObject mMediaObject;

    public MediaContent() {
    }

    public MediaContent(IMediaObject iMediaObject) {
        this.mMediaObject = iMediaObject;
    }

    public final int getType() {
        IMediaObject iMediaObject = this.mMediaObject;
        if (iMediaObject == null) {
            return 0;
        }
        return iMediaObject.type();
    }

    public final boolean checkArgs() {
        return this.mMediaObject.checkArgs();
    }

    /* loaded from: classes3.dex */
    public static class Builder {
        public static final String KEY_IDENTIFIER = "_dyobject_identifier_";

        public static Bundle toBundle(MediaContent mediaContent) {
            Bundle bundle = new Bundle();
            if (mediaContent.mMediaObject != null) {
                mediaContent.mMediaObject.serialize(bundle);
                ArrayList<String> stringArrayList = bundle.getStringArrayList(ParamKeyConstants.AWEME_EXTRA_MEDIA_MESSAGE_IMAGE_PATH);
                ArrayList<String> stringArrayList2 = bundle.getStringArrayList(ParamKeyConstants.AWEME_EXTRA_MEDIA_MESSAGE_VIDEO_PATH);
                String str = (stringArrayList2 == null || stringArrayList2.size() == 0) ? "" : "com.ss.android.ugc.aweme.opensdk.share.base.TikTokVideoObject";
                if (stringArrayList != null && stringArrayList.size() != 0) {
                    str = "com.ss.android.ugc.aweme.opensdk.share.base.TikTokImageObject";
                }
                bundle.putString(KEY_IDENTIFIER, str);
            }
            return bundle;
        }

        public static MediaContent fromBundle(Bundle bundle) {
            MediaContent mediaContent = new MediaContent();
            String string = bundle.getString(KEY_IDENTIFIER);
            if (string != null && string.length() > 0) {
                try {
                    if (string.contains("sdk")) {
                        string = string.replace("sdk", "sdk.account");
                    }
                    mediaContent.mMediaObject = (IMediaObject) Class.forName(string).newInstance();
                    mediaContent.mMediaObject.unserialize(bundle);
                    return mediaContent;
                } catch (Exception e2) {
                    Log.e(MediaContent.TAG, "get media object from bundle failed: unknown ident " + string + ", ex = " + e2.getMessage());
                }
            }
            return mediaContent;
        }
    }
}
