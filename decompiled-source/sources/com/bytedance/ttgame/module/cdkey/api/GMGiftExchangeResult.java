package com.bytedance.ttgame.module.cdkey.api;

import androidx.core.app.NotificationCompat;
import com.bytedance.apm.constant.i;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.Metadata;
/* compiled from: Models.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR \u0010\t\u001a\b\u0018\u00010\nR\u00020\u0000X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult;", "", "()V", "code", "", "getCode", "()I", "setCode", "(I)V", "data", "Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean;", "getData", "()Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean;", "setData", "(Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean;)V", "message", "", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "DataBean", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class GMGiftExchangeResult {
    private int code;
    private DataBean data;
    private String message;

    public final int getCode() {
        return this.code;
    }

    public final void setCode(int i2) {
        this.code = i2;
    }

    public final DataBean getData() {
        return this.data;
    }

    public final void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public final String getMessage() {
        return this.message;
    }

    public final void setMessage(String str) {
        this.message = str;
    }

    /* compiled from: Models.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0004\u0018\u00002\u00020\u0001:\u0002\u0010\u0011B\u0005¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\f\u0018\u00010\u0004R\u00060\u0000R\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR$\u0010\n\u001a\f\u0018\u00010\u000bR\u00060\u0000R\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean;", "", "(Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult;)V", "process", "Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean$ProcessBean;", "Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult;", "getProcess", "()Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean$ProcessBean;", "setProcess", "(Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean$ProcessBean;)V", "rewards", "Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean$RewardsBean;", "getRewards", "()Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean$RewardsBean;", "setRewards", "(Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean$RewardsBean;)V", "ProcessBean", "RewardsBean", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public final class DataBean {
        private ProcessBean process;
        private RewardsBean rewards;

        public DataBean() {
        }

        public final ProcessBean getProcess() {
            return this.process;
        }

        public final void setProcess(ProcessBean processBean) {
            this.process = processBean;
        }

        public final RewardsBean getRewards() {
            return this.rewards;
        }

        public final void setRewards(RewardsBean rewardsBean) {
            this.rewards = rewardsBean;
        }

        /* compiled from: Models.kt */
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0004\u0018\u00002\u00020\u0001:\u0001\u001fB\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R(\u0010\u0017\u001a\u0010\u0018\u00010\u0018R\n0\u0000R\u00060\u0019R\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006 "}, d2 = {"Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean$ProcessBean;", "", "(Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean;)V", "code", "", "getCode", "()I", "setCode", "(I)V", "isActive", "", "()Z", "setActive", "(Z)V", "msg", "", "getMsg", "()Ljava/lang/String;", "setMsg", "(Ljava/lang/String;)V", ExchangeConstantKt.PROCESS_ID, "getProcess_id", "setProcess_id", NotificationCompat.CATEGORY_PROGRESS, "Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean$ProcessBean$ProgressBean;", "Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean;", "Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult;", "getProgress", "()Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean$ProcessBean$ProgressBean;", "setProgress", "(Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean$ProcessBean$ProgressBean;)V", "ProgressBean", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes4.dex */
        public final class ProcessBean {
            private int code;
            private boolean isActive;
            private String msg;
            private String process_id;
            private ProgressBean progress;

            public ProcessBean() {
            }

            public final String getProcess_id() {
                return this.process_id;
            }

            public final void setProcess_id(String str) {
                this.process_id = str;
            }

            public final boolean isActive() {
                return this.isActive;
            }

            public final void setActive(boolean z) {
                this.isActive = z;
            }

            public final ProgressBean getProgress() {
                return this.progress;
            }

            public final void setProgress(ProgressBean progressBean) {
                this.progress = progressBean;
            }

            public final int getCode() {
                return this.code;
            }

            public final void setCode(int i2) {
                this.code = i2;
            }

            public final String getMsg() {
                return this.msg;
            }

            public final void setMsg(String str) {
                this.msg = str;
            }

            /* compiled from: Models.kt */
            @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\f"}, d2 = {"Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean$ProcessBean$ProgressBean;", "", "(Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean$ProcessBean;)V", i.KEY_CURRENT_GALVANIC, "", "getCurrent", "()I", "setCurrent", "(I)V", "target", "getTarget", "setTarget", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
            /* loaded from: classes4.dex */
            public final class ProgressBean {
                private int current;
                private int target;

                public ProgressBean() {
                }

                public final int getCurrent() {
                    return this.current;
                }

                public final void setCurrent(int i2) {
                    this.current = i2;
                }

                public final int getTarget() {
                    return this.target;
                }

                public final void setTarget(int i2) {
                    this.target = i2;
                }
            }
        }

        /* compiled from: Models.kt */
        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0004\u0018\u00002\u00020\u0001:\u0001\fB\u0005¢\u0006\u0002\u0010\u0002R.\u0010\u0003\u001a\u0016\u0012\u0010\u0012\u000e0\u0005R\n0\u0000R\u00060\u0006R\u00020\u0007\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean$RewardsBean;", "", "(Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean;)V", FirebaseAnalytics.Param.ITEMS, "", "Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean$RewardsBean$ItemsBean;", "Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean;", "Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult;", "getItems", "()Ljava/util/List;", "setItems", "(Ljava/util/List;)V", "ItemsBean", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes4.dex */
        public final class RewardsBean {
            private List<ItemsBean> items;

            public RewardsBean() {
            }

            public final List<ItemsBean> getItems() {
                return this.items;
            }

            public final void setItems(List<ItemsBean> list) {
                this.items = list;
            }

            /* compiled from: Models.kt */
            @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean$RewardsBean$ItemsBean;", "", "(Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult$DataBean$RewardsBean;)V", "id", "", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", i.PERF_DISK_FILE_NUM, "", "getNum", "()I", "setNum", "(I)V", "type", "getType", "setType", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
            /* loaded from: classes4.dex */
            public final class ItemsBean {
                private String id;
                private int num;
                private String type;

                public ItemsBean() {
                }

                public final String getId() {
                    return this.id;
                }

                public final void setId(String str) {
                    this.id = str;
                }

                public final int getNum() {
                    return this.num;
                }

                public final void setNum(int i2) {
                    this.num = i2;
                }

                public final String getType() {
                    return this.type;
                }

                public final void setType(String str) {
                    this.type = str;
                }
            }
        }
    }
}
