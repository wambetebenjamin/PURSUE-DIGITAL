package com.bytedance.sdk.account.save;
/* compiled from: SaveConstants.java */
/* loaded from: classes2.dex */
public class c {
    public static int CACHE_NUMBER = 30;
    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;
    public static final int TYPE_3 = 3;

    /* compiled from: SaveConstants.java */
    /* loaded from: classes2.dex */
    public interface a {
        public static final int CODE_DELETE_ERROR = -5;
        public static final int CODE_INSERT_ERROR = -2;
        public static final int CODE_LOGIN_INFO_EMPTY = -6;
        public static final int CODE_MATCH_NOTHING = -7;
        public static final int CODE_QUERY_ERROR = -4;
        public static final int CODE_QUERY_LIST_ERROR = -3;
        public static final int CODE_SUCCESS = 0;
        public static final int CODE_UPDATE_ERROR = -1;
        public static final String MSG_DELETE_ERROR = "删除数据失败或者没有数据";
        public static final String MSG_INSERT_ERROR = "数据插入失败";
        public static final String MSG_LOGIN_INFO_EMPTY = "登录数据为空";
        public static final String MSG_MATCH_NOTHING = "和本地数据匹配为空";
        public static final String MSG_QUERY_ERROR = "获取数据获取失败或者没有数据";
        public static final String MSG_QUERY_LIST_ERROR = "获取多条数据失败或者没有数据";
        public static final String MSG_UPDATE_ERROR = "数据更新失败或者没有此数据";
    }

    /* compiled from: SaveConstants.java */
    /* loaded from: classes2.dex */
    public interface b {
        public static final String COUNTRY_CODE = "country_code";
    }
}
