package gsdk.impl.share.DEFAULT;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import com.bytedance.ttgame.framework.module.util.PermissionMediator;
import com.bytedance.ttgame.module.share.api.callback.TTRequestPermissionCallback;
import com.bytedance.ttgame.module.share.api.depend.ITTSharePermissionConfig;
import com.bytedance.ttgame.module.share.api.entity.TTShareModel;
/* loaded from: classes10.dex */
public class q implements ITTSharePermissionConfig {
    @Override // com.bytedance.ttgame.module.share.api.depend.ITTSharePermissionConfig
    public boolean hasPermission(Context context, String str) {
        return ActivityCompat.checkSelfPermission(context, str) == 0;
    }

    @Override // com.bytedance.ttgame.module.share.api.depend.ITTSharePermissionConfig
    public void requestPermissions(Activity activity, final String[] strArr, TTShareModel tTShareModel, final TTRequestPermissionCallback tTRequestPermissionCallback) {
        PermissionMediator.checkPermission(activity, strArr, new PermissionMediator.OnPermissionRequestListener() { // from class: gsdk.impl.share.DEFAULT.q.1
            @Override // com.bytedance.ttgame.framework.module.util.PermissionMediator.OnPermissionRequestListener
            public void onPermissionRequest(boolean z, String str) {
                Log.d("gsdk_service_share", "ITTSharePermissionConfigImpl, onPermissionRequest: permission -> " + strArr);
                a(z, str, tTRequestPermissionCallback);
            }

            @Override // com.bytedance.ttgame.framework.module.util.PermissionMediator.OnPermissionRequestListener
            public void onPermissionRequest(boolean z, String[] strArr2, int[] iArr) {
                Log.d("gsdk_service_share", "ITTSharePermissionConfigImpl, onPermissionRequest: permissions -> " + strArr2);
                if (strArr2 == null || strArr2.length <= 0) {
                    return;
                }
                for (String str : strArr2) {
                    a(z, str, tTRequestPermissionCallback);
                }
            }

            private void a(boolean z, String str, TTRequestPermissionCallback tTRequestPermissionCallback2) {
                Log.d("gsdk_service_share", "ITTSharePermissionConfigImpl, handleResult: permission -> " + str);
                if (tTRequestPermissionCallback2 == null) {
                    return;
                }
                if (z) {
                    tTRequestPermissionCallback2.onGranted();
                } else {
                    tTRequestPermissionCallback2.onDenied(str);
                }
            }
        });
    }
}
