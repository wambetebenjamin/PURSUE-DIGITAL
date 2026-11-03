package com.bytedance.react.framework.floatingview;

import android.content.Context;
import android.widget.ImageView;
import com.bytedance.react.framework.R;
/* loaded from: classes2.dex */
public class EnFloatingView extends FloatingMagnetView {
    private final ImageView mIcon;

    public EnFloatingView(Context context) {
        this(context, R.layout.en_floating_view);
    }

    public EnFloatingView(Context context, int i2) {
        super(context, null);
        inflate(context, i2, this);
        this.mIcon = (ImageView) findViewById(R.id.icon);
    }

    public void setIconImage(int i2) {
        this.mIcon.setImageResource(i2);
    }
}
