package androidx.core.app;

import android.app.Dialog;
import android.os.Build;
import android.view.View;
/* loaded from: classes.dex */
public class DialogCompat {
    private DialogCompat() {
    }

    public static View requireViewById(Dialog dialog, int i2) {
        if (Build.VERSION.SDK_INT >= 28) {
            return (View) Api28Impl.requireViewById(dialog, i2);
        }
        View findViewById = dialog.findViewById(i2);
        if (findViewById != null) {
            return findViewById;
        }
        throw new IllegalArgumentException("ID does not reference a View inside this Dialog");
    }

    /* loaded from: classes.dex */
    static class Api28Impl {
        private Api28Impl() {
        }

        static <T> T requireViewById(Dialog dialog, int i2) {
            return (T) dialog.requireViewById(i2);
        }
    }
}
