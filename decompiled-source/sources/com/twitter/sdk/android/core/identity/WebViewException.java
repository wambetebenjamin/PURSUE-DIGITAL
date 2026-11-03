package com.twitter.sdk.android.core.identity;
/* loaded from: classes10.dex */
class WebViewException extends Exception {
    private static final long serialVersionUID = -7397331487240298819L;
    private final int errorCode;
    private final String failingUrl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebViewException(int i2, String str, String str2) {
        super(str);
        this.errorCode = i2;
        this.failingUrl = str2;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getDescription() {
        return getMessage();
    }

    public String getFailingUrl() {
        return this.failingUrl;
    }
}
