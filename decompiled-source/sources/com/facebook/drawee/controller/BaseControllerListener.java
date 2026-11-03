package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Map;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class BaseControllerListener<INFO> implements ControllerListener<INFO> {
    private static final ControllerListener<Object> NO_OP_LISTENER = new BaseControllerListener();

    public void onControllerStart(ImageRequest imageRequest, long j2) {
    }

    public void onFailure(String str, ImageRequest imageRequest, Throwable th) {
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public void onFailure(String str, Throwable th) {
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public void onFinalImageSet(String str, @Nullable INFO info, @Nullable Animatable animatable) {
    }

    public void onFinalImageSet(String str, INFO info, Animatable animatable, ImageRequest imageRequest, Map map) {
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public void onIntermediateImageFailed(String str, Throwable th) {
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public void onIntermediateImageSet(String str, @Nullable INFO info) {
    }

    public void onIntermediateImageSet(String str, @Nullable INFO info, @Nullable Animatable animatable) {
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public void onRelease(String str) {
    }

    public void onRelease(String str, ImageRequest imageRequest) {
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public void onSubmit(String str, Object obj) {
    }

    public static <INFO> ControllerListener<INFO> getNoOpListener() {
        return (ControllerListener<INFO>) NO_OP_LISTENER;
    }
}
