package com.bytedance.ttgame.channel.adapter;

import android.app.Activity;
import com.bytedance.ttgame.channelapi.IChannelTanwan;
import java.util.HashMap;
import kotlin.Metadata;
/* compiled from: ChannelAdapterTanwan.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J2\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH\u0016J2\u0010\f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH\u0016JL\u0010\r\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J2\u0010\u0015\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH\u0016¨\u0006\u0016"}, d2 = {"Lcom/bytedance/ttgame/channel/adapter/ChannelAdapterTanwan;", "Lcom/bytedance/ttgame/channelapi/IChannelTanwan;", "()V", "eventReport", "", "activity", "Landroid/app/Activity;", "eventName", "", "params", "Ljava/util/HashMap;", "", "eventTrack", "sendGameEventLog", "serverId", "serverName", "roleId", "roleName", "roleLevel", "moneyNum", "", "ttEventReport", "tt_channel_i18n_adapter_account_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChannelAdapterTanwan implements IChannelTanwan {
    @Override // com.bytedance.ttgame.channelapi.IChannelTanwan
    public void eventReport(Activity activity, String str, HashMap<String, Object> hashMap) {
    }

    @Override // com.bytedance.ttgame.channelapi.IChannelTanwan
    public void eventTrack(Activity activity, String str, HashMap<String, Object> hashMap) {
    }

    @Override // com.bytedance.ttgame.channelapi.IChannelTanwan
    public void sendGameEventLog(String str, String str2, String str3, String str4, String str5, String str6, int i2) {
    }

    @Override // com.bytedance.ttgame.channelapi.IChannelTanwan
    public void ttEventReport(Activity activity, String str, HashMap<String, Object> hashMap) {
    }
}
