package com.bytedance.ttgame.module.notice.api;

import com.bytedance.apm.constant.c;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.google.gson.annotations.SerializedName;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes6.dex */
public class Notice {
    @SerializedName("announcement_id")
    public String announcementId;
    @SerializedName("badge_switch")
    public int badgeSwitch;
    @SerializedName("button_text")
    public String buttonText;
    public String content;
    public String encoding;
    @SerializedName("expire_time")
    public long expireTime;
    public String extra;
    @SerializedName("frequency")
    public int frequency;
    public String image;
    @SerializedName("image_info_json")
    public String imageInfoJson;
    @SerializedName("image_list")
    public List<Image> imageList;
    public String inscribe;
    public String language;
    public int priority;
    public int scene;
    @SerializedName(c.KEY_START_TIME)
    public long startTime;
    public int stick;
    public String tab;
    @SerializedName("tab_label")
    public String tabLabel;
    @SerializedName("target_url")
    public String targetUrl;
    public int times;
    public String title;

    public String getAnnouncementId() {
        return this.announcementId;
    }

    public int getScene() {
        return this.scene;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTargetUrl() {
        return this.targetUrl;
    }

    public String getImage() {
        return this.image;
    }

    public String getInscribe() {
        return this.inscribe;
    }

    public String getContent() {
        return this.content;
    }

    public boolean getStick() {
        return this.stick == 1;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public long getExpireTime() {
        return this.expireTime;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public String getButtonText() {
        return this.buttonText;
    }

    public int getPriority() {
        return this.priority;
    }

    public int getTimes() {
        return this.times;
    }

    public String getTab() {
        return this.tab;
    }

    public String getImageInfoJson() {
        return this.imageInfoJson;
    }

    public List<Image> getImageList() {
        return this.imageList;
    }

    public String getTabLabel() {
        return this.tabLabel;
    }

    public boolean getBadgeSwitch() {
        return this.badgeSwitch == 1;
    }

    public String getExtra() {
        return this.extra;
    }

    public String toString() {
        StringBuilder append = new StringBuilder().append("announcement_id: " + this.announcementId).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append("scene: " + this.scene).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append("language: " + this.language).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append("title: " + this.title).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append("targetUrl: " + this.targetUrl).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append("image: " + this.image).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append("inscribe: " + this.inscribe).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append("content: " + this.content).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append("stick: " + this.stick).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append("startTime: " + this.startTime).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append("expire_time: " + this.expireTime).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append("frequncy: " + this.frequency).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append("encoding: " + this.encoding).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append("button_text: " + this.buttonText).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append("priority: " + this.priority).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append("times: " + this.times).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append("tab: " + this.tab).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append("imageInfoJson: " + this.imageInfoJson).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append("imageList: [").append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        List<Image> list = this.imageList;
        if (list != null && !list.isEmpty()) {
            Iterator<Image> it = this.imageList.iterator();
            while (it.hasNext()) {
                append.append("{\n" + it.next().toString()).append("}\n");
            }
        }
        append.append("]\n").append("tabLabel: " + this.tabLabel).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append("badgeSwitch: " + getBadgeSwitch()).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append("extra: " + this.extra).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        return append.toString();
    }
}
