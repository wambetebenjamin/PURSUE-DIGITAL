package com.bytedance.ttgame.module.notice.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes6.dex */
public class ResponseData {
    @SerializedName("current_page")
    public int currentPage;
    public List<Notice> data;
    @SerializedName("page_size")
    public int pageSize;
    @SerializedName("total_page")
    public int totalPage;

    public int getTotalPage() {
        return this.totalPage;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public List<Notice> getData() {
        return this.data;
    }
}
