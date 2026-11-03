package com.bytedance.ug.sdk.share.impl.network.model;

import android.net.Uri;
import android.text.TextUtils;
import com.bytedance.ug.sdk.share.api.entity.h;
import com.bytedance.ug.sdk.share.api.entity.m;
import com.bytedance.ug.sdk.share.api.entity.s;
import com.bytedance.ug.sdk.share.api.panel.d;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class ShareInfo implements Serializable {
    @SerializedName("channel")
    private String mChannel;
    @SerializedName("share_data")
    private ShareDetail mShareDetail;
    @SerializedName("method")
    private String mStrategy;

    public String getChannel() {
        return this.mChannel;
    }

    public void setChannel(String str) {
        this.mChannel = str;
    }

    public String getStrategy() {
        return this.mStrategy;
    }

    public void setStrategy(String str) {
        this.mStrategy = str;
    }

    public ShareDetail getShareDetail() {
        return this.mShareDetail;
    }

    public void setShareDetail(ShareDetail shareDetail) {
        this.mShareDetail = shareDetail;
    }

    public static h applyToShareModel(ShareInfo shareInfo, h hVar) {
        String strategy = shareInfo.getStrategy();
        if (!TextUtils.isEmpty(strategy)) {
            hVar.setShareStrategy(m.getStrategyByType(strategy));
        }
        ShareDetail shareDetail = shareInfo.getShareDetail();
        if (shareDetail != null) {
            String title = shareDetail.getTitle();
            if (!TextUtils.isEmpty(title)) {
                hVar.setTitle(title);
            }
            String description = shareDetail.getDescription();
            if (!TextUtils.isEmpty(description)) {
                hVar.setText(description);
            }
            String imageUrl = shareDetail.getImageUrl();
            if (!TextUtils.isEmpty(imageUrl)) {
                hVar.setImageUrl(imageUrl);
            }
            String qrCodeImageUrl = shareDetail.getQrCodeImageUrl();
            if (!TextUtils.isEmpty(qrCodeImageUrl)) {
                hVar.setQrcodeImageUrl(qrCodeImageUrl);
            }
            String hiddenImageUrl = shareDetail.getHiddenImageUrl();
            if (!TextUtils.isEmpty(hiddenImageUrl)) {
                hVar.setHiddenImageUrl(hiddenImageUrl);
            }
            String shareUrl = shareDetail.getShareUrl();
            if (!TextUtils.isEmpty(shareUrl)) {
                try {
                    Uri parse = Uri.parse(shareUrl);
                    if (hVar.getShareChanelType() != d.COPY_LINK && TextUtils.isEmpty(parse.getQueryParameter("share_token"))) {
                        hVar.setTargetUrl(parse.buildUpon().appendQueryParameter("share_token", hVar.getShareToken()).build().toString());
                    } else {
                        hVar.setTargetUrl(shareUrl);
                    }
                } catch (Exception unused) {
                    hVar.setTargetUrl(shareUrl);
                }
            }
            String videoUrl = shareDetail.getVideoUrl();
            if (!TextUtils.isEmpty(videoUrl)) {
                hVar.setVideoUrl(videoUrl);
            }
            String audioUrl = shareDetail.getAudioUrl();
            if (!TextUtils.isEmpty(audioUrl)) {
                hVar.setAudioUrl(audioUrl);
            }
            String extra = shareDetail.getExtra();
            if (!TextUtils.isEmpty(extra)) {
                hVar.setExtra(extra);
            }
            TokenInfo tokenInfo = shareDetail.getTokenInfo();
            if (tokenInfo != null) {
                s sVar = new s();
                sVar.setTitle(tokenInfo.getTitle());
                sVar.setDescription(tokenInfo.getDescription());
                sVar.setTips(tokenInfo.getTips());
                hVar.setTokenShareInfo(sVar);
            }
            TokenInfo imageTokenInfo = shareDetail.getImageTokenInfo();
            if (imageTokenInfo != null) {
                s sVar2 = new s();
                sVar2.setTitle(imageTokenInfo.getTitle());
                sVar2.setDescription(imageTokenInfo.getDescription());
                sVar2.setTips(imageTokenInfo.getTips());
                hVar.setImageTokenShareInfo(sVar2);
            }
        }
        return hVar;
    }

    public static h applyTokenToShareModel(ShareInfo shareInfo, h hVar) {
        ShareDetail shareDetail = shareInfo.getShareDetail();
        if (shareDetail != null) {
            TokenInfo tokenInfo = shareDetail.getTokenInfo();
            if (tokenInfo != null) {
                s sVar = new s();
                sVar.setTitle(tokenInfo.getTitle());
                sVar.setDescription(tokenInfo.getDescription());
                sVar.setTips(tokenInfo.getTips());
                hVar.setTokenShareInfo(sVar);
            }
            TokenInfo imageTokenInfo = shareDetail.getImageTokenInfo();
            if (imageTokenInfo != null) {
                s sVar2 = new s();
                sVar2.setTitle(imageTokenInfo.getTitle());
                sVar2.setDescription(imageTokenInfo.getDescription());
                sVar2.setTips(imageTokenInfo.getTips());
                hVar.setImageTokenShareInfo(sVar2);
            }
        }
        return hVar;
    }
}
