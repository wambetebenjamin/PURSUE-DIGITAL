package com.just.agentweb;

import android.webkit.WebView;
/* loaded from: classes9.dex */
public class IndicatorHandler implements IndicatorController {
    private BaseIndicatorSpec mBaseIndicatorSpec;

    @Override // com.just.agentweb.IndicatorController
    public void progress(WebView webView, int i2) {
        if (i2 == 0) {
            reset();
        } else if (i2 > 0 && i2 <= 10) {
            showIndicator();
        } else if (i2 > 10 && i2 < 95) {
            setProgress(i2);
        } else {
            setProgress(i2);
            finish();
        }
    }

    @Override // com.just.agentweb.IndicatorController
    public BaseIndicatorSpec offerIndicator() {
        return this.mBaseIndicatorSpec;
    }

    public void reset() {
        BaseIndicatorSpec baseIndicatorSpec = this.mBaseIndicatorSpec;
        if (baseIndicatorSpec != null) {
            baseIndicatorSpec.reset();
        }
    }

    @Override // com.just.agentweb.IndicatorController
    public void finish() {
        BaseIndicatorSpec baseIndicatorSpec = this.mBaseIndicatorSpec;
        if (baseIndicatorSpec != null) {
            baseIndicatorSpec.hide();
        }
    }

    @Override // com.just.agentweb.IndicatorController
    public void setProgress(int i2) {
        BaseIndicatorSpec baseIndicatorSpec = this.mBaseIndicatorSpec;
        if (baseIndicatorSpec != null) {
            baseIndicatorSpec.setProgress(i2);
        }
    }

    @Override // com.just.agentweb.IndicatorController
    public void showIndicator() {
        BaseIndicatorSpec baseIndicatorSpec = this.mBaseIndicatorSpec;
        if (baseIndicatorSpec != null) {
            baseIndicatorSpec.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static IndicatorHandler getInstance() {
        return new IndicatorHandler();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IndicatorHandler inJectIndicator(BaseIndicatorSpec baseIndicatorSpec) {
        this.mBaseIndicatorSpec = baseIndicatorSpec;
        return this;
    }
}
