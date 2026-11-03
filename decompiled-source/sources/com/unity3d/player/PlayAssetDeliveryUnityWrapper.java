package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import com.bytedance.common.wschannel.WsConstants;
import java.util.List;
/* loaded from: classes10.dex */
class PlayAssetDeliveryUnityWrapper {

    /* renamed from: b  reason: collision with root package name */
    private static PlayAssetDeliveryUnityWrapper f18873b;

    /* renamed from: a  reason: collision with root package name */
    private C0421n f18874a;

    private PlayAssetDeliveryUnityWrapper(Context context) {
        this.f18874a = null;
        if (f18873b != null) {
            throw new RuntimeException("PlayAssetDeliveryUnityWrapper should be created only once. Use getInstance() instead.");
        }
        try {
            if (getClass().getClassLoader().loadClass("com.google.android.play.core.assetpacks.AssetPackManager").getMethod("getPackStates", List.class).getReturnType().getName().equals("com.google.android.gms.tasks.Task")) {
                this.f18874a = a(context);
            }
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException unused) {
        }
    }

    private static C0421n a(Context context) {
        return C0421n.a(context);
    }

    private void a() {
        if (playCoreApiMissing()) {
            throw new RuntimeException("AssetPackManager API is not available! Make sure your gradle project includes 'com.google.android.play:asset-delivery' dependency.");
        }
    }

    public static synchronized PlayAssetDeliveryUnityWrapper getInstance() {
        PlayAssetDeliveryUnityWrapper playAssetDeliveryUnityWrapper;
        synchronized (PlayAssetDeliveryUnityWrapper.class) {
            while (true) {
                playAssetDeliveryUnityWrapper = f18873b;
                if (playAssetDeliveryUnityWrapper != null) {
                    break;
                }
                try {
                    PlayAssetDeliveryUnityWrapper.class.wait(WsConstants.EXIT_DELAY_TIME);
                } catch (InterruptedException e2) {
                    AbstractC0445z.Log(6, e2.getMessage());
                }
            }
            if (playAssetDeliveryUnityWrapper == null) {
                throw new RuntimeException("PlayAssetDeliveryUnityWrapper is not yet initialised.");
            }
        }
        return playAssetDeliveryUnityWrapper;
    }

    public static synchronized PlayAssetDeliveryUnityWrapper init(Context context) {
        PlayAssetDeliveryUnityWrapper playAssetDeliveryUnityWrapper;
        synchronized (PlayAssetDeliveryUnityWrapper.class) {
            if (f18873b != null) {
                throw new RuntimeException("PlayAssetDeliveryUnityWrapper.init() should be called only once. Use getInstance() instead.");
            }
            f18873b = new PlayAssetDeliveryUnityWrapper(context);
            PlayAssetDeliveryUnityWrapper.class.notifyAll();
            playAssetDeliveryUnityWrapper = f18873b;
        }
        return playAssetDeliveryUnityWrapper;
    }

    public void cancelAssetPackDownload(String str) {
        cancelAssetPackDownloads(new String[]{str});
    }

    public void cancelAssetPackDownloads(String[] strArr) {
        a();
        this.f18874a.a(strArr);
    }

    public void downloadAssetPack(String str, IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback) {
        downloadAssetPacks(new String[]{str}, iAssetPackManagerDownloadStatusCallback);
    }

    public void downloadAssetPacks(String[] strArr, IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback) {
        a();
        this.f18874a.a(strArr, iAssetPackManagerDownloadStatusCallback);
    }

    public String getAssetPackPath(String str) {
        a();
        return this.f18874a.a(str);
    }

    public void getAssetPackState(String str, IAssetPackManagerStatusQueryCallback iAssetPackManagerStatusQueryCallback) {
        getAssetPackStates(new String[]{str}, iAssetPackManagerStatusQueryCallback);
    }

    public void getAssetPackStates(String[] strArr, IAssetPackManagerStatusQueryCallback iAssetPackManagerStatusQueryCallback) {
        a();
        this.f18874a.a(strArr, iAssetPackManagerStatusQueryCallback);
    }

    public boolean playCoreApiMissing() {
        return this.f18874a == null;
    }

    public Object registerDownloadStatusListener(IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback) {
        a();
        return this.f18874a.a(iAssetPackManagerDownloadStatusCallback);
    }

    public void removeAssetPack(String str) {
        a();
        this.f18874a.b(str);
    }

    public void requestToUseMobileData(Activity activity, IAssetPackManagerMobileDataConfirmationCallback iAssetPackManagerMobileDataConfirmationCallback) {
        a();
        this.f18874a.a(activity, iAssetPackManagerMobileDataConfirmationCallback);
    }

    public void unregisterDownloadStatusListener(Object obj) {
        a();
        this.f18874a.a(obj);
    }
}
