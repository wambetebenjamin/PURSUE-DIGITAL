package com.bytedance.sdk.open.tiktok.base;

import android.os.Bundle;
import com.bytedance.sdk.open.tiktok.common.constants.ParamKeyConstants;
import java.util.ArrayList;
/* loaded from: classes3.dex */
public class ImageObject implements IMediaObject {
    private static final String TAG = "ImageObject";
    public ArrayList<String> mImagePaths;

    @Override // com.bytedance.sdk.open.tiktok.base.IMediaObject
    public boolean checkArgs() {
        return true;
    }

    @Override // com.bytedance.sdk.open.tiktok.base.IMediaObject
    public int type() {
        return 2;
    }

    public ImageObject() {
    }

    public ImageObject(ArrayList<String> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        this.mImagePaths = arrayList2;
        arrayList2.addAll(arrayList);
    }

    @Override // com.bytedance.sdk.open.tiktok.base.IMediaObject
    public void serialize(Bundle bundle) {
        bundle.putStringArrayList(ParamKeyConstants.AWEME_EXTRA_MEDIA_MESSAGE_IMAGE_PATH, this.mImagePaths);
    }

    @Override // com.bytedance.sdk.open.tiktok.base.IMediaObject
    public void unserialize(Bundle bundle) {
        this.mImagePaths = bundle.getStringArrayList(ParamKeyConstants.AWEME_EXTRA_MEDIA_MESSAGE_IMAGE_PATH);
    }
}
