package com.bytedance.ttgame.module.main.bridge.mapper;

import com.bytedance.bdinstall.e;
import com.bytedance.common.wschannel.WsConstants;
import com.bytedance.ttgame.module.main.bridge.model.GMBulletin;
import com.bytedance.ttgame.module.notice.api.Notice;
import com.bytedance.ttgame.module.notice.api.ResponseData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class GMBulletinMapper {
    private GMBulletinMapper() {
    }

    public static GMBulletin convert(ResponseData responseData, JSONObject jSONObject) {
        GMBulletin gMBulletin = new GMBulletin();
        if (responseData != null) {
            gMBulletin.totalPage = responseData.getTotalPage();
            gMBulletin.currentPage = responseData.getCurrentPage();
            gMBulletin.pageSize = responseData.getPageSize();
            gMBulletin.filters = convertParamToMap(jSONObject);
            List<Notice> data = responseData.getData();
            ArrayList arrayList = new ArrayList();
            if (data != null && !data.isEmpty()) {
                for (Notice notice : data) {
                    if (notice != null) {
                        GMBulletin.GMBulletinItem gMBulletinItem = new GMBulletin.GMBulletinItem();
                        gMBulletinItem.bID = notice.getAnnouncementId();
                        gMBulletinItem.language = notice.getLanguage();
                        gMBulletinItem.scene = notice.getScene();
                        gMBulletinItem.title = notice.getTitle();
                        gMBulletinItem.content = notice.getContent();
                        gMBulletinItem.targetURL = notice.getTargetUrl();
                        gMBulletinItem.priority = notice.getPriority();
                        gMBulletinItem.imageURL = notice.getImage();
                        gMBulletinItem.encoding = notice.getEncoding();
                        gMBulletinItem.buttonText = notice.getButtonText();
                        gMBulletinItem.startTime = notice.getStartTime();
                        gMBulletinItem.expireTime = notice.getExpireTime();
                        gMBulletinItem.times = notice.getTimes();
                        gMBulletinItem.frequency = notice.getFrequency();
                        gMBulletinItem.stick = notice.getStick();
                        gMBulletinItem.tab = notice.getTab();
                        gMBulletinItem.imageInfoJson = notice.getImageInfoJson();
                        gMBulletinItem.imageList = notice.getImageList();
                        gMBulletinItem.tabLabel = notice.getTabLabel();
                        gMBulletinItem.badgeSwitch = notice.getBadgeSwitch();
                        gMBulletinItem.extra = notice.getExtra();
                        arrayList.add(gMBulletinItem);
                    }
                }
            }
            gMBulletin.bulletinItems = arrayList;
        }
        return gMBulletin;
    }

    private static Map<String, String> convertParamToMap(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        hashMap.put("server_id", jSONObject.optString("serverId"));
        hashMap.put("game_language", jSONObject.optString(e.z));
        hashMap.put("game_region", jSONObject.optString("region"));
        hashMap.put("scene", String.valueOf(jSONObject.optInt("scene")));
        hashMap.put("sdk_open_id", jSONObject.optString("openId"));
        hashMap.put("zoneId", jSONObject.optString("zoneId"));
        hashMap.put("role_id", jSONObject.optString("roleId"));
        hashMap.put(WsConstants.KEY_CONNECTION_EXTRA_INFO, jSONObject.optString("extraInfo"));
        return hashMap;
    }
}
