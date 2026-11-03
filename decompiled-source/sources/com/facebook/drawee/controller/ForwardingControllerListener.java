package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import android.util.Log;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class ForwardingControllerListener<INFO> extends BaseControllerListener<INFO> {
    private static final String TAG = "FdingControllerListener";
    private final List<ControllerListener<? super INFO>> mListeners = new ArrayList(2);

    public static <INFO> ForwardingControllerListener<INFO> create() {
        return new ForwardingControllerListener<>();
    }

    public static <INFO> ForwardingControllerListener<INFO> of(ControllerListener<? super INFO> controllerListener) {
        ForwardingControllerListener<INFO> create = create();
        create.addListener(controllerListener);
        return create;
    }

    public static <INFO> ForwardingControllerListener<INFO> of(ControllerListener<? super INFO> controllerListener, ControllerListener<? super INFO> controllerListener2) {
        ForwardingControllerListener<INFO> create = create();
        create.addListener(controllerListener);
        create.addListener(controllerListener2);
        return create;
    }

    public synchronized void addListener(ControllerListener<? super INFO> controllerListener) {
        this.mListeners.add(controllerListener);
    }

    public synchronized void removeListener(ControllerListener<? super INFO> controllerListener) {
        int indexOf = this.mListeners.indexOf(controllerListener);
        if (indexOf != -1) {
            this.mListeners.set(indexOf, null);
        }
    }

    public synchronized void clearListeners() {
        this.mListeners.clear();
    }

    private synchronized void onException(String str, Throwable th) {
        Log.e(TAG, str, th);
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
    public synchronized void onSubmit(String str, Object obj) {
        int size = this.mListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i2);
                if (controllerListener != null) {
                    controllerListener.onSubmit(str, obj);
                }
            } catch (Exception e2) {
                onException("InternalListener exception in onSubmit", e2);
            }
        }
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
    public synchronized void onFinalImageSet(String str, @Nullable INFO info, @Nullable Animatable animatable) {
        int size = this.mListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i2);
                if (controllerListener != null) {
                    controllerListener.onFinalImageSet(str, info, animatable);
                }
            } catch (Exception e2) {
                onException("InternalListener exception in onFinalImageSet", e2);
            }
        }
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener
    public void onFinalImageSet(String str, @Nullable INFO info, @Nullable Animatable animatable, ImageRequest imageRequest, Map map) {
        int size = this.mListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i2);
                if (controllerListener != null && (controllerListener instanceof BaseFrescoControllerListener)) {
                    ((BaseFrescoControllerListener) controllerListener).onFinalImageSet(str, info, animatable, imageRequest, map);
                }
            } catch (Exception e2) {
                onException("InternalListener exception in onFinalImageSet", e2);
            }
        }
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener
    public void onControllerStart(ImageRequest imageRequest, long j2) {
        int size = this.mListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i2);
                if (controllerListener instanceof BaseFrescoControllerListener) {
                    ((BaseFrescoControllerListener) controllerListener).onControllerStart(imageRequest, j2);
                }
            } catch (Exception e2) {
                onException("InternalListener exception in onFinalImageSet", e2);
            }
        }
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
    public void onIntermediateImageSet(String str, @Nullable INFO info) {
        int size = this.mListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i2);
                if (controllerListener != null) {
                    controllerListener.onIntermediateImageSet(str, info);
                }
            } catch (Exception e2) {
                onException("InternalListener exception in onIntermediateImageSet", e2);
            }
        }
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener
    public void onIntermediateImageSet(String str, @Nullable INFO info, @Nullable Animatable animatable) {
        int size = this.mListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i2);
                if (controllerListener instanceof BaseControllerListener) {
                    ((BaseControllerListener) controllerListener).onIntermediateImageSet(str, info, animatable);
                }
            } catch (Exception e2) {
                onException("InternalListener exception in onIntermediateImageSet", e2);
            }
        }
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
    public void onIntermediateImageFailed(String str, Throwable th) {
        int size = this.mListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i2);
                if (controllerListener != null) {
                    controllerListener.onIntermediateImageFailed(str, th);
                }
            } catch (Exception e2) {
                onException("InternalListener exception in onIntermediateImageFailed", e2);
            }
        }
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
    public synchronized void onFailure(String str, Throwable th) {
        int size = this.mListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i2);
                if (controllerListener != null) {
                    controllerListener.onFailure(str, th);
                }
            } catch (Exception e2) {
                onException("InternalListener exception in onFailure", e2);
            }
        }
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener
    public synchronized void onFailure(String str, ImageRequest imageRequest, Throwable th) {
        int size = this.mListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i2);
                if (controllerListener instanceof BaseFrescoControllerListener) {
                    ((BaseFrescoControllerListener) controllerListener).onFailure(str, imageRequest, th);
                }
            } catch (Exception e2) {
                onException("InternalListener exception in onFailure", e2);
            }
        }
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
    public synchronized void onRelease(String str) {
        int size = this.mListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i2);
                if (controllerListener != null) {
                    controllerListener.onRelease(str);
                }
            } catch (Exception e2) {
                onException("InternalListener exception in onRelease", e2);
            }
        }
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener
    public synchronized void onRelease(String str, ImageRequest imageRequest) {
        int size = this.mListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i2);
                if (controllerListener instanceof BaseFrescoControllerListener) {
                    ((BaseFrescoControllerListener) controllerListener).onRelease(str, imageRequest);
                }
            } catch (Exception e2) {
                onException("InternalListener exception in onRelease", e2);
            }
        }
    }
}
