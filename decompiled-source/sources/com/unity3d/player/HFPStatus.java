package com.unity3d.player;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
/* loaded from: classes10.dex */
public class HFPStatus {

    /* renamed from: a  reason: collision with root package name */
    private Context f18824a;

    /* renamed from: d  reason: collision with root package name */
    private AudioManager f18827d;

    /* renamed from: b  reason: collision with root package name */
    private BroadcastReceiver f18825b = null;

    /* renamed from: c  reason: collision with root package name */
    private boolean f18826c = false;

    /* renamed from: e  reason: collision with root package name */
    private boolean f18828e = false;

    /* renamed from: f  reason: collision with root package name */
    private int f18829f = 1;

    public HFPStatus(Context context) {
        this.f18827d = null;
        this.f18824a = context;
        this.f18827d = (AudioManager) context.getSystemService("audio");
        initHFPStatusJni();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.f18828e) {
            this.f18828e = false;
            this.f18827d.stopBluetoothSco();
        }
    }

    private final native void deinitHFPStatusJni();

    private final native void initHFPStatusJni();

    public final void b() {
        clearHFPStat();
        deinitHFPStatusJni();
    }

    protected void clearHFPStat() {
        BroadcastReceiver broadcastReceiver = this.f18825b;
        if (broadcastReceiver != null) {
            this.f18824a.unregisterReceiver(broadcastReceiver);
            this.f18825b = null;
        }
        this.f18829f = 1;
        a();
    }

    protected boolean getHFPStat() {
        return this.f18829f == 2;
    }

    protected void requestHFPStat() {
        clearHFPStat();
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.unity3d.player.HFPStatus.1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                if (intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", -1) != 1) {
                    return;
                }
                HFPStatus hFPStatus = HFPStatus.this;
                hFPStatus.f18829f = 2;
                hFPStatus.a();
                HFPStatus hFPStatus2 = HFPStatus.this;
                if (hFPStatus2.f18826c) {
                    hFPStatus2.f18827d.setMode(3);
                }
            }
        };
        this.f18825b = broadcastReceiver;
        this.f18824a.registerReceiver(broadcastReceiver, new IntentFilter("android.media.ACTION_SCO_AUDIO_STATE_UPDATED"));
        try {
            this.f18828e = true;
            this.f18827d.startBluetoothSco();
        } catch (NullPointerException unused) {
            AbstractC0445z.Log(5, "startBluetoothSco() failed. no bluetooth device connected.");
        }
    }

    protected void setHFPRecordingStat(boolean z) {
        this.f18826c = z;
        if (z) {
            return;
        }
        this.f18827d.setMode(0);
    }
}
