package com.facebook.react.views.picker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.facebook.infer.annotation.Assertions;
import java.util.List;
/* loaded from: classes8.dex */
class ReactPickerAdapter extends ArrayAdapter<ReactPickerItem> {
    private final LayoutInflater mInflater;
    private Integer mPrimaryTextColor;

    public ReactPickerAdapter(Context context, List<ReactPickerItem> list) {
        super(context, 0, list);
        this.mInflater = (LayoutInflater) Assertions.assertNotNull(context.getSystemService("layout_inflater"));
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        return getView(i2, view, viewGroup, false);
    }

    @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        return getView(i2, view, viewGroup, true);
    }

    private View getView(int i2, View view, ViewGroup viewGroup, boolean z) {
        Integer num;
        ReactPickerItem item = getItem(i2);
        boolean z2 = false;
        if (view == null) {
            view = this.mInflater.inflate(z ? 17367049 : 17367048, viewGroup, false);
            view.setTag(((TextView) view).getTextColors());
            z2 = true;
        }
        TextView textView = (TextView) view;
        textView.setText(item.label);
        if (!z && (num = this.mPrimaryTextColor) != null) {
            textView.setTextColor(num.intValue());
        } else if (item.color != null) {
            textView.setTextColor(item.color.intValue());
        } else if (textView.getTag() != null && !z2) {
            textView.setTextColor((ColorStateList) textView.getTag());
        }
        return textView;
    }

    public Integer getPrimaryTextColor() {
        return this.mPrimaryTextColor;
    }

    public void setPrimaryTextColor(Integer num) {
        this.mPrimaryTextColor = num;
        notifyDataSetChanged();
    }
}
