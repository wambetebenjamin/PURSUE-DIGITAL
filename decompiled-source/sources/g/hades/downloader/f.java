package g.hades.downloader;

import android.util.Log;
import com.bytedance.frameworks.gpm.gameturbo.GameTurbo;
/* loaded from: classes10.dex */
public class f {
    public static int a(int i2, int i3) {
        int bindCoreOperation;
        int i4 = -2;
        try {
        } catch (Throwable th) {
            Log.w("Hades-J", "setCoreThreadID: " + i2 + ", type = " + i3 + ", e = " + th);
        }
        if (i3 == 0) {
            bindCoreOperation = GameTurbo.getInstance().setBindCoreOperation(3, i2);
        } else if (i3 == 1) {
            bindCoreOperation = GameTurbo.getInstance().setBindCoreOperation(1, i2);
        } else if (i3 == 2) {
            bindCoreOperation = GameTurbo.getInstance().setBindCoreOperation(2, i2);
        } else {
            if (i3 == 3) {
                GameTurbo.getInstance().setCoreThreadID(i2);
                i4 = 0;
            }
            Log.i("Hades-J", "setCoreThreadID: " + i2 + ", type = " + i3 + ", result = " + i4);
            return i4;
        }
        i4 = bindCoreOperation;
        Log.i("Hades-J", "setCoreThreadID: " + i2 + ", type = " + i3 + ", result = " + i4);
        return i4;
    }
}
