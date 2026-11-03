package com.bytedance.ug.sdk.share.impl.ui.panel;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bytedance.share_ui.R;
/* compiled from: PanelItemViewHolder.java */
/* loaded from: classes8.dex */
public class d extends RecyclerView.ViewHolder {
    public ImageView icon;
    public TextView text;

    public d(View view) {
        super(view);
        this.icon = (ImageView) view.findViewById(R.id.icon);
        this.text = (TextView) view.findViewById(R.id.text);
    }
}
