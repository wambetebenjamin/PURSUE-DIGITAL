package com.bytedance.ug.sdk.share.api.panel;

import android.text.TextUtils;
import com.bytedance.ug.sdk.share.api.entity.g;
/* compiled from: ShareChannelType.java */
/* loaded from: classes8.dex */
public enum d implements c {
    WX_TIMELINE("moments"),
    WX("wechat"),
    QQ("qq"),
    QZONE("qzone"),
    WEIBO("weibo"),
    WEIBO_SG("weibo_sg"),
    FEILIAO(g.FL),
    DUOSHAN(g.DS),
    DINGDING(g.DD),
    DOUYIN("douyin"),
    DOUYIN_IM("douyin_im"),
    TOUTIAO("toutiao"),
    FEISHU(g.FEISHU),
    ZHIFUBAO(g.ZHIFUBAO),
    SYSTEM("sys_share"),
    COPY_LINK(g.COPY_LINK),
    SMS("sms"),
    EMAIL("email"),
    IMAGE_SHARE("image_share"),
    LONG_IMAGE(g.LONG_IMAGE),
    VIDEO_SAVE(g.f15564b),
    FACEBOOK("facebook"),
    FACEBOOK_STORY(g.FACEBOOK_STORY),
    FACEBOOK_LITE(g.FACEBOOK_LITE),
    MESSENGER("messenger"),
    MESSENGER_LITE(g.MESSENGER_LITE),
    LINE("line"),
    WHATSAPP("whatsapp"),
    WHATSAPP_STATUS(g.WHATSAPP_STATUS),
    INSTAGRAM("instagram"),
    INSTAGRAM_STORY(g.INSTAGRAM_STORY),
    TIKTOK("tiktok"),
    TWITTER("twitter"),
    KAKAO(g.KAKAO),
    KAKAO_STORY(g.KAKAO_STORY),
    SNAPCHAT(g.SNAPCHAT),
    BAND(g.BAND),
    IMGUR(g.IMGUR),
    NAVERBLOG(g.NAVERBLOG),
    NAVERCAFE(g.NAVERCAFE),
    VIBER(g.VIBER),
    VK("vk"),
    TELEGRAM(g.TELEGRAM),
    ZALO(g.ZALO),
    REDDIT(g.REDDIT);
    

    /* renamed from: a  reason: collision with root package name */
    private final String f15650a;

    /* renamed from: b  reason: collision with root package name */
    private final String f15651b;

    /* renamed from: c  reason: collision with root package name */
    private final String f15652c;

    d(String str) {
        this.f15650a = str;
        this.f15651b = "";
        this.f15652c = "";
    }

    d(String str, String str2, String str3) {
        this.f15650a = str;
        this.f15651b = str2;
        this.f15652c = str3;
    }

    public static d getShareItemType(String str) {
        d[] values;
        for (d dVar : values()) {
            if (dVar.f15650a.equals(str)) {
                return dVar;
            }
        }
        return null;
    }

    public static String getShareItemTypeName(d dVar) {
        return dVar == null ? "" : dVar.f15650a;
    }

    public static String getShareChannelName(d dVar) {
        if (dVar != null && !TextUtils.isEmpty(dVar.f15651b)) {
            return dVar.f15651b;
        }
        com.bytedance.ug.sdk.share.impl.share.api.b channel = com.bytedance.ug.sdk.share.impl.manager.c.getChannel(dVar);
        return (channel == null || channel.getChannelName() == null) ? "" : channel.getChannelName();
    }

    public static String getSharePackageName(d dVar) {
        if (dVar != null && !TextUtils.isEmpty(dVar.f15652c)) {
            return dVar.f15652c;
        }
        com.bytedance.ug.sdk.share.impl.share.api.b channel = com.bytedance.ug.sdk.share.impl.manager.c.getChannel(dVar);
        return (channel == null || channel.getPackageName() == null) ? "" : channel.getPackageName();
    }
}
