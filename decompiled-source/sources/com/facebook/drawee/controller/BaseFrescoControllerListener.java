package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Map;
/* loaded from: classes8.dex */
public class BaseFrescoControllerListener extends BaseControllerListener {
    @Override // com.facebook.drawee.controller.BaseControllerListener
    public void onControllerStart(ImageRequest imageRequest, long j2) {
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener
    public void onFailure(String str, ImageRequest imageRequest, Throwable th) {
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener
    public void onFinalImageSet(String str, Object obj, Animatable animatable, ImageRequest imageRequest, Map map) {
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener
    public void onRelease(String str, ImageRequest imageRequest) {
    }
}
