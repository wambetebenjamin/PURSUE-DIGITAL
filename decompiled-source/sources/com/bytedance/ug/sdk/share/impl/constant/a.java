package com.bytedance.ug.sdk.share.impl.constant;

import com.bytedance.ug.sdk.share.api.panel.d;
import java.util.HashMap;
/* compiled from: ShareChannelConstants.java */
/* loaded from: classes8.dex */
public class a {
    public static final String CONFIG_BASE_PATH = "com.bytedance.ug.sdk.share.keep.impl.";
    public static HashMap<d, String> sShareConfig;

    static {
        HashMap<d, String> hashMap = new HashMap<>();
        sShareConfig = hashMap;
        hashMap.put(d.WX, "com.bytedance.ug.sdk.share.keep.impl.WXShareImpl");
        sShareConfig.put(d.WX_TIMELINE, "com.bytedance.ug.sdk.share.keep.impl.WXTimelineShareImpl");
        sShareConfig.put(d.QQ, "com.bytedance.ug.sdk.share.keep.impl.QQShareImpl");
        sShareConfig.put(d.QZONE, "com.bytedance.ug.sdk.share.keep.impl.QZoneShareImpl");
        sShareConfig.put(d.DINGDING, "com.bytedance.ug.sdk.share.keep.impl.DDShareImpl");
        sShareConfig.put(d.DOUYIN, "com.bytedance.ug.sdk.share.keep.impl.DYShareImpl");
        sShareConfig.put(d.DOUYIN_IM, "com.bytedance.ug.sdk.share.keep.impl.DYIMShareImpl");
        sShareConfig.put(d.WEIBO, "com.bytedance.ug.sdk.share.keep.impl.WBShareImpl");
        sShareConfig.put(d.WEIBO_SG, "com.bytedance.ug.sdk.share.keep.impl.WBSGShareImpl");
        sShareConfig.put(d.FEILIAO, "com.bytedance.ug.sdk.share.keep.impl.FLShareImpl");
        sShareConfig.put(d.DUOSHAN, "com.bytedance.ug.sdk.share.keep.impl.DSShareImpl");
        sShareConfig.put(d.TOUTIAO, "com.bytedance.ug.sdk.share.keep.impl.TTShareImpl");
        sShareConfig.put(d.FEISHU, "com.bytedance.ug.sdk.share.keep.impl.FSShareImpl");
        sShareConfig.put(d.ZHIFUBAO, "com.bytedance.ug.sdk.share.keep.impl.ZFBShareImpl");
        sShareConfig.put(d.FACEBOOK, "com.bytedance.ug.sdk.share.keep.impl.FacebookShareImpl");
        sShareConfig.put(d.FACEBOOK_STORY, "com.bytedance.ug.sdk.share.keep.impl.FacebookStoryShareImpl");
        sShareConfig.put(d.LINE, "com.bytedance.ug.sdk.share.keep.impl.LineShareImpl");
        sShareConfig.put(d.WHATSAPP, "com.bytedance.ug.sdk.share.keep.impl.WhatsAppShareImpl");
        sShareConfig.put(d.WHATSAPP_STATUS, "com.bytedance.ug.sdk.share.keep.impl.WhatsAppStatusShareImpl");
        sShareConfig.put(d.INSTAGRAM, "com.bytedance.ug.sdk.share.keep.impl.InstagramShareImpl");
        sShareConfig.put(d.INSTAGRAM_STORY, "com.bytedance.ug.sdk.share.keep.impl.InstagramStoryShareImpl");
        sShareConfig.put(d.TIKTOK, "com.bytedance.ug.sdk.share.keep.impl.TiktokShareImpl");
        sShareConfig.put(d.TWITTER, "com.bytedance.ug.sdk.share.keep.impl.TwitterShareImpl");
        sShareConfig.put(d.KAKAO, "com.bytedance.ug.sdk.share.keep.impl.KakaoShareImpl");
        sShareConfig.put(d.KAKAO_STORY, "com.bytedance.ug.sdk.share.keep.impl.KakaoStoryShareImpl");
        sShareConfig.put(d.SNAPCHAT, "com.bytedance.ug.sdk.share.keep.impl.SnapChatShareImpl");
        sShareConfig.put(d.MESSENGER, "com.bytedance.ug.sdk.share.keep.impl.MessengerShareImpl");
        sShareConfig.put(d.VK, "com.bytedance.ug.sdk.share.keep.impl.VKShareImpl");
        sShareConfig.put(d.TELEGRAM, "com.bytedance.ug.sdk.share.keep.impl.TelegramShareImpl");
        sShareConfig.put(d.IMAGE_SHARE, "com.bytedance.ug.sdk.share.keep.impl.ImageShareImpl");
        sShareConfig.put(d.LONG_IMAGE, "com.bytedance.ug.sdk.share.keep.impl.LongImageShareImpl");
        sShareConfig.put(d.VIDEO_SAVE, "com.bytedance.ug.sdk.share.keep.impl.VideoSaveImpl");
    }
}
