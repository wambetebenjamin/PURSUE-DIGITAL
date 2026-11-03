package androidx.navigation;

import android.os.Bundle;
import androidx.navigation.NavDestination;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/* loaded from: classes.dex */
public abstract class Navigator<D extends NavDestination> {

    /* loaded from: classes.dex */
    public interface Extras {
    }

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes.dex */
    public @interface Name {
        String value();
    }

    public abstract D createDestination();

    public abstract NavDestination navigate(D d2, Bundle bundle, NavOptions navOptions, Extras extras);

    public void onRestoreState(Bundle bundle) {
    }

    public Bundle onSaveState() {
        return null;
    }

    public abstract boolean popBackStack();
}
