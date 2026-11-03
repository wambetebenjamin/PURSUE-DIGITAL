package com.bytedance.ttgame.module.main.bridge.model;

import com.bytedance.ttgame.module.notice.api.Image;
import java.util.List;
import java.util.Map;
/* loaded from: classes6.dex */
public class GMBulletin {
    public List<GMBulletinItem> bulletinItems;
    public int currentPage;
    public Map<String, String> filters;
    public int pageSize;
    public int totalPage;

    /* loaded from: classes6.dex */
    public static class GMBulletinItem {
        public String bID;
        public boolean badgeSwitch;
        public String buttonText;
        public String content;
        public String encoding;
        public double expireTime;
        public String extra;
        public int frequency;
        public String imageInfoJson;
        public List<Image> imageList;
        public String imageURL;
        public String language;
        public int priority;
        public int scene;
        public double startTime;
        public boolean stick;
        public String tab;
        public String tabLabel;
        public String targetURL;
        public int times;
        public String title;
    }
}
