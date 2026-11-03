package com.reactnativecommunity.webview.permissions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import androidx.core.app.ActivityCompat;
/* loaded from: classes9.dex */
public class ProxyPermissionActivity extends Activity {
    public static final String KEY_PERMISSIONS = "permissions";
    public static final String KEY_REQUEST_CODE = "request_code";
    public static final String KEY_RESULT_RECEIVER = "result_receiver";
    public static final int REQUEST_CODE_PERMISSION = 10011;
    private static final String TAG = "{gumiho_webview}";
    private int mPermissionType = -1;
    private ResultReceiver mResultReceiver;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            Intent intent = getIntent();
            this.mResultReceiver = (ResultReceiver) intent.getParcelableExtra("result_receiver");
            int intExtra = intent.getIntExtra("request_code", 10011);
            this.mPermissionType = intExtra;
            String[] stringArrayExtra = intent.getStringArrayExtra("permissions");
            if (stringArrayExtra == null) {
                stringArrayExtra = new String[0];
            }
            ActivityCompat.requestPermissions(this, stringArrayExtra, intExtra);
            return;
        }
        this.mResultReceiver = (ResultReceiver) bundle.getParcelable("result_receiver");
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("result_receiver", this.mResultReceiver);
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        Bundle bundle = new Bundle();
        for (int i3 = 0; i3 < strArr.length; i3++) {
            bundle.putInt(strArr[i3], iArr[i3]);
        }
        this.mResultReceiver.send(i2, bundle);
        finish();
    }
}
