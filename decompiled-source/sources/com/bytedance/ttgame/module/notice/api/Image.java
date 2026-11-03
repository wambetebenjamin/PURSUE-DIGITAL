package com.bytedance.ttgame.module.notice.api;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes6.dex */
public class Image {
    @SerializedName("image_info_json")
    public String imageInfoJson;
    @SerializedName("image_jump_link")
    public String imageJumpLink;
    @SerializedName("image_link")
    public String imageLink;

    public String getImageLink() {
        return this.imageLink;
    }

    public String getImageJumpLink() {
        return this.imageJumpLink;
    }

    public String getImageInfoJson() {
        return this.imageInfoJson;
    }

    public String toString() {
        return ("imageLink: " + this.imageLink) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ("imageJumpLink: " + this.imageJumpLink) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ("imageInfoJson: " + this.imageInfoJson) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
    }
}
