package com.oasis.notice;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class a extends NoticeAgent {
    @Override // com.oasis.notice.NoticeAgent
    public void fetchNotice(String str, NoticeListener noticeListener) {
        noticeListener.OnNoticeFinish(false, "DefaultNoticeAgent");
    }
}
