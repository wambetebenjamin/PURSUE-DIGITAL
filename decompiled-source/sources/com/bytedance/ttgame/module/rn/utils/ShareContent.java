package com.bytedance.ttgame.module.rn.utils;

import com.bytedance.ttgame.module.rn.modules.RNSafeMapUtils;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes6.dex */
public class ShareContent {
    private static final String DESC_KEY = "desc";
    private static final String DOUYIN_MODEL_KEY = "shareDouYinExtendModel";
    private static final String IMAGE_KEY = "imageUrl";
    private static final String IMAGE_LIST_KEY = "imageList";
    private static final String SHARE_CONTENT_TYPE_KEY = "shareContentType";
    private static final String SHARE_TYPE_KEY = "shareType";
    private static final String STATE_KEY = "state";
    private static final String TITLE_KEY = "title";
    private static final String VIDEO_KEY = "videoUrl";
    private static final String VIDEO_PATH_KEY = "videoPath";
    private static final String WEB_URL_KEY = "webPageUrl";
    private HashMap contentMap;

    public ShareContent(HashMap hashMap) {
        this.contentMap = hashMap;
    }

    public String getShareType() {
        return RNSafeMapUtils.getString(this.contentMap, "shareType");
    }

    public String getShareContentType() {
        return RNSafeMapUtils.getString(this.contentMap, SHARE_CONTENT_TYPE_KEY);
    }

    public String getImageUrl() {
        return RNSafeMapUtils.getString(this.contentMap, "imageUrl");
    }

    public List getImageList() {
        return RNSafeMapUtils.getArray(this.contentMap, IMAGE_LIST_KEY);
    }

    public String getState() {
        return RNSafeMapUtils.getString(this.contentMap, "state");
    }

    public String getVideoUrl() {
        return RNSafeMapUtils.getString(this.contentMap, "videoUrl");
    }

    public String getVideoPath() {
        return RNSafeMapUtils.getString(this.contentMap, "videoPath");
    }

    public String getTitle() {
        return RNSafeMapUtils.getString(this.contentMap, "title");
    }

    public String getWebUrl() {
        return RNSafeMapUtils.getString(this.contentMap, WEB_URL_KEY);
    }

    public String getDesc() {
        return RNSafeMapUtils.getString(this.contentMap, "desc");
    }

    public HashMap getDouyinModel() {
        return RNSafeMapUtils.getMap(this.contentMap, DOUYIN_MODEL_KEY);
    }
}
