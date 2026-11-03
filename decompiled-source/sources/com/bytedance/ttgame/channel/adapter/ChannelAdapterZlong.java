package com.bytedance.ttgame.channel.adapter;

import android.app.Activity;
import android.content.Context;
import com.bytedance.bdturing.methods.l;
import com.bytedance.react.framework.RNConfig;
import com.bytedance.ttgame.channelapi.IChannelCallback;
import com.bytedance.ttgame.channelapi.IChannelZlong;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
/* compiled from: ChannelAdapterZlong.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002Jw\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016¢\u0006\u0002\u0010\u0013J\b\u0010\u0014\u001a\u00020\u0006H\u0016J\u0012\u0010\u0015\u001a\u00020\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0006H\u0016J6\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\r2\b\u0010\u001b\u001a\u0004\u0018\u00010\u00062\u001a\u0010\u001c\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001f0\u001e\u0018\u00010\u001dH\u0016J\u001e\u0010 \u001a\u00020\u00042\u0014\u0010\u001c\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0!\u0018\u00010\u001dH\u0016J\u0012\u0010\"\u001a\u00020\u00042\b\u0010#\u001a\u0004\u0018\u00010\u0006H\u0016J1\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\r2\u0010\u0010&\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0006\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016¢\u0006\u0002\u0010*J@\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\r2\b\u0010-\u001a\u0004\u0018\u00010\u00062\b\u0010.\u001a\u0004\u0018\u00010\u00062\u001a\u0010\u001c\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001f0\u001e\u0018\u00010\u001dH\u0016J$\u0010/\u001a\u00020\u00042\u001a\u0010\u001c\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001f0\u001e\u0018\u00010\u001dH\u0016J&\u00100\u001a\u00020\u00042\b\u00101\u001a\u0004\u0018\u0001022\b\u00103\u001a\u0004\u0018\u00010\u00062\b\u0010.\u001a\u0004\u0018\u00010\u0006H\u0016J8\u00104\u001a\u00020\u00062\b\u00105\u001a\u0004\u0018\u00010\u00062\b\u0010-\u001a\u0004\u0018\u00010\u00062\u001a\u0010\u001c\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001f0\u001e\u0018\u00010\u001dH\u0016J\u001c\u00106\u001a\u00020\u00042\b\u00107\u001a\u0004\u0018\u00010\u00062\b\u00108\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u00109\u001a\u00020\u0004H\u0016J.\u0010:\u001a\u00020\u00042\b\u0010;\u001a\u0004\u0018\u00010\u00062\u001a\u0010\u001c\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001f0\u001e\u0018\u00010\u001dH\u0016¨\u0006<"}, d2 = {"Lcom/bytedance/ttgame/channel/adapter/ChannelAdapterZlong;", "Lcom/bytedance/ttgame/channelapi/IChannelZlong;", "()V", "gameStarted", "", "strRole", "", "strGameUid", "roleLevel", "serverId", "serverName", "roleName", "balance", "", "partyName", "vipLevel", "pushInfo", "roleCreateTime", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/Long;)V", "getChannelID", "getDeviceId", "app", "Landroid/content/Context;", "getECID", "getNoticeState", "mode", "nodeName", l.VALUE_CALLBACK, "Lcom/bytedance/ttgame/channelapi/IChannelCallback;", "", "", "getProductsList", "", "initBBS", "bbsparams", "onRequestPermissionsResult", "requestCode", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "openBBS", RNConfig.ORIENTATION_KEY, "type", "customparams", "registerRewardsOutside", "rewardsPurchaseOutside", "activity", "Landroid/app/Activity;", "giftid", "sendExtAction", "extraString", "sendGameEventLog", "eventID", "extra", "showUserCenter", "startQRLoginPC", "customParams", "tt_channel_i18n_adapter_account_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChannelAdapterZlong implements IChannelZlong {
    @Override // com.bytedance.ttgame.channelapi.IChannelZlong
    public void gameStarted(String str, String str2, String str3, String str4, String str5, String str6, int i2, String str7, int i3, String str8, Long l) {
    }

    @Override // com.bytedance.ttgame.channelapi.IChannelZlong
    public String getChannelID() {
        return "";
    }

    @Override // com.bytedance.ttgame.channelapi.IChannelZlong
    public String getDeviceId(Context context) {
        return "";
    }

    @Override // com.bytedance.ttgame.channelapi.IChannelZlong
    public String getECID() {
        return "";
    }

    @Override // com.bytedance.ttgame.channelapi.IChannelZlong
    public void getNoticeState(int i2, String str, IChannelCallback<Map<String, Object>> iChannelCallback) {
    }

    @Override // com.bytedance.ttgame.channelapi.IChannelZlong
    public void getProductsList(IChannelCallback<List<Object>> iChannelCallback) {
    }

    @Override // com.bytedance.ttgame.channelapi.IChannelZlong
    public void initBBS(String str) {
    }

    @Override // com.bytedance.ttgame.channelapi.IChannelZlong
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
    }

    @Override // com.bytedance.ttgame.channelapi.IChannelZlong
    public void openBBS(int i2, String str, String str2, IChannelCallback<Map<String, Object>> iChannelCallback) {
    }

    @Override // com.bytedance.ttgame.channelapi.IChannelZlong
    public void registerRewardsOutside(IChannelCallback<Map<String, Object>> iChannelCallback) {
    }

    @Override // com.bytedance.ttgame.channelapi.IChannelZlong
    public void rewardsPurchaseOutside(Activity activity, String str, String str2) {
    }

    @Override // com.bytedance.ttgame.channelapi.IChannelZlong
    public String sendExtAction(String str, String str2, IChannelCallback<Map<String, Object>> iChannelCallback) {
        return "";
    }

    @Override // com.bytedance.ttgame.channelapi.IChannelZlong
    public void sendGameEventLog(String str, String str2) {
    }

    @Override // com.bytedance.ttgame.channelapi.IChannelZlong
    public void showUserCenter() {
    }

    @Override // com.bytedance.ttgame.channelapi.IChannelZlong
    public void startQRLoginPC(String str, IChannelCallback<Map<String, Object>> iChannelCallback) {
    }
}
