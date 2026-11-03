package com.oasis.ui;

import android.content.Context;
import android.content.res.Resources;
/* loaded from: classes9.dex */
final class c implements e {
    @Override // com.oasis.ui.e
    public final boolean a() {
        return true;
    }

    @Override // com.oasis.ui.e
    public final boolean a(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_screen_has_notch", "bool", "android");
        if (identifier > 0) {
            return resources.getBoolean(identifier);
        }
        return false;
    }

    @Override // com.oasis.ui.e
    public final boolean b() {
        return false;
    }

    @Override // com.oasis.ui.e
    public final int[] b(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("notch_h", "integer", "android");
        if (identifier > 0) {
            return g.a(resources.getInteger(identifier));
        }
        return null;
    }
}
