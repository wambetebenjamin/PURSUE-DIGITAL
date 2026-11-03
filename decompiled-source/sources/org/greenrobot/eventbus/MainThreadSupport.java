package org.greenrobot.eventbus;
/* loaded from: classes10.dex */
public interface MainThreadSupport {
    Poster createPoster(EventBus eventBus);

    boolean isMainThread();
}
