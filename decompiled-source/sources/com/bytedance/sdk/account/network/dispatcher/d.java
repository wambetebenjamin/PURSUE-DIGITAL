package com.bytedance.sdk.account.network.dispatcher;

import android.os.Message;
import java.util.concurrent.BlockingQueue;
/* compiled from: DownloadDispatcher.java */
/* loaded from: classes2.dex */
public class d extends a {

    /* renamed from: e  reason: collision with root package name */
    private static final int f12666e = 1;

    @Override // com.bytedance.sdk.account.network.dispatcher.a
    public /* bridge */ /* synthetic */ boolean isRunning() {
        return super.isRunning();
    }

    @Override // com.bytedance.sdk.account.network.dispatcher.a
    public /* bridge */ /* synthetic */ void quit() {
        super.quit();
    }

    @Override // com.bytedance.sdk.account.network.dispatcher.a, java.lang.Thread, java.lang.Runnable
    public /* bridge */ /* synthetic */ void run() {
        super.run();
    }

    public d(BlockingQueue<e> blockingQueue, String str, String str2) {
        super(blockingQueue, str, str2);
    }

    @Override // com.bytedance.sdk.account.network.dispatcher.a, com.bytedance.common.utility.collection.WeakHandler.IHandler
    public void handleMsg(Message message) {
        if (message == null) {
            return;
        }
        try {
            if (message.what != 1) {
                return;
            }
            f12645b.handleShrinkDownloadRequestQueueSize();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.bytedance.sdk.account.network.dispatcher.a
    public void cancelEnQueueExpireMsg(c cVar) {
        if (cVar != null) {
            cVar.cancelEnDownloadQueueExpireMsg();
        }
    }

    @Override // com.bytedance.sdk.account.network.dispatcher.a
    public void sendShrinkExpireMsg() {
        cancelShrinkExpireMsg();
        this.f12648d.sendEmptyMessageDelayed(1, 2000L);
    }

    @Override // com.bytedance.sdk.account.network.dispatcher.a
    public void cancelShrinkExpireMsg() {
        this.f12648d.removeMessages(1);
    }
}
