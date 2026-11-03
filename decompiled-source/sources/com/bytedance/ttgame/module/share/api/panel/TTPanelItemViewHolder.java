package com.bytedance.ttgame.module.share.api.panel;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bytedance.ttgame.module.share.api.R;
/* loaded from: classes7.dex */
public class TTPanelItemViewHolder extends RecyclerView.ViewHolder {
    public ImageView icon;
    public TextView text;

    public TTPanelItemViewHolder(View view) {
        super(view);
        this.icon = (ImageView) view.findViewById(R.id.icon);
        this.text = (TextView) view.findViewById(R.id.text);
    }
}
