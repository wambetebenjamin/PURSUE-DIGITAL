package com.bytedance.ttgame.module.share.bridge.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.bytedance.ttgame.framework.gbridge.spi.ServiceManager;
import com.bytedance.ttgame.module.share.api.IShareService;
import com.bytedance.ttgame.module.share.api.utils.ConstantKt;
/* loaded from: classes7.dex */
public class ShareTransferActivity extends Activity {
    private static final String TAG = "ShareTransferActivity";
    private boolean onPause;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent() == null) {
            finish();
        } else {
            doShare();
        }
    }

    private void doShare() {
        Log.d(TAG, "doShare");
        Intent intent = getIntent();
        intent.getStringExtra("panelId");
        intent.getBooleanExtra("userPanel", false);
        intent.getStringExtra("title");
        intent.getStringExtra(ConstantKt.DESC);
        intent.getStringExtra(ConstantKt.IMAGE_PATH);
        intent.getStringExtra(ConstantKt.THUMB_IMAGE_PATH);
        intent.getStringExtra(ConstantKt.IMAGE_URL);
        intent.getStringExtra("webPageUrl");
        intent.getStringExtra(ConstantKt.VIDEO_URL);
        intent.getStringExtra(ConstantKt.VIDEO_PATH);
        intent.getIntExtra("contentType", 0);
        intent.getIntExtra("shareSource", 0);
        intent.getStringExtra(ConstantKt.DY_UNIQUEID);
        intent.getStringExtra(ConstantKt.DY_HASHTAG);
        intent.getStringExtra(ConstantKt.DY_ANCHOR);
        intent.getStringExtra("tunnel");
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        ((IShareService) ServiceManager.get().getService("com.bytedance.ttgame.module.share.api.IShareService")).handleShareResultOnActivityResult(i2, i3, intent);
        Log.d(TAG, "onActivityResult");
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        ((IShareService) ServiceManager.get().getService("com.bytedance.ttgame.module.share.api.IShareService")).onRequestPermissionsResult(this, i2, strArr, iArr);
        Log.d(TAG, "onRequestPermissionsResult");
        if (isFinishing()) {
            return;
        }
        finish();
        Log.d(TAG, "onRequestPermissionsResult finish");
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.onPause = true;
        Log.d(TAG, "onPause");
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume：" + this.onPause);
        if (!this.onPause || isFinishing()) {
            return;
        }
        finish();
        Log.d(TAG, "onResume finish");
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "onBackPressed");
        if (isFinishing()) {
            return;
        }
        finish();
        Log.d(TAG, "onBackPressed finish");
    }
}
