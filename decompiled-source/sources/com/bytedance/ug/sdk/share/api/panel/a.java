package com.bytedance.ug.sdk.share.api.panel;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytedance.ug.sdk.share.api.entity.h;
import java.io.Serializable;
/* compiled from: IPanelItem.java */
/* loaded from: classes8.dex */
public interface a extends Serializable {
    int getIconId();

    String getIconUrl();

    c getItemType();

    int getTextId();

    String getTextStr();

    void onItemClick(Context context, View view, h hVar);

    void setItemView(View view, ImageView imageView, TextView textView);
}
