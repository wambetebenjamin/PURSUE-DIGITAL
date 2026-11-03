package com.facebook.react.views.picker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SpinnerAdapter;
import androidx.appcompat.widget.AppCompatSpinner;
import java.util.List;
/* loaded from: classes8.dex */
public class ReactPicker extends AppCompatSpinner {
    private final AdapterView.OnItemSelectedListener mItemSelectedListener;
    private List<ReactPickerItem> mItems;
    private int mMode;
    private OnSelectListener mOnSelectListener;
    private List<ReactPickerItem> mStagedItems;
    private Integer mStagedPrimaryTextColor;
    private Integer mStagedSelection;
    private final Runnable measureAndLayout;

    /* loaded from: classes8.dex */
    public interface OnSelectListener {
        void onItemSelected(int i2);
    }

    public ReactPicker(Context context) {
        super(context);
        this.mMode = 0;
        this.mItemSelectedListener = new AdapterView.OnItemSelectedListener() { // from class: com.facebook.react.views.picker.ReactPicker.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j2) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(i2);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(-1);
                }
            }
        };
        this.measureAndLayout = new Runnable() { // from class: com.facebook.react.views.picker.ReactPicker.2
            @Override // java.lang.Runnable
            public void run() {
                ReactPicker reactPicker = ReactPicker.this;
                reactPicker.measure(View.MeasureSpec.makeMeasureSpec(reactPicker.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactPicker.this.getHeight(), 1073741824));
                ReactPicker reactPicker2 = ReactPicker.this;
                reactPicker2.layout(reactPicker2.getLeft(), ReactPicker.this.getTop(), ReactPicker.this.getRight(), ReactPicker.this.getBottom());
            }
        };
    }

    public ReactPicker(Context context, int i2) {
        super(context, i2);
        this.mMode = 0;
        this.mItemSelectedListener = new AdapterView.OnItemSelectedListener() { // from class: com.facebook.react.views.picker.ReactPicker.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i22, long j2) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(i22);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(-1);
                }
            }
        };
        this.measureAndLayout = new Runnable() { // from class: com.facebook.react.views.picker.ReactPicker.2
            @Override // java.lang.Runnable
            public void run() {
                ReactPicker reactPicker = ReactPicker.this;
                reactPicker.measure(View.MeasureSpec.makeMeasureSpec(reactPicker.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactPicker.this.getHeight(), 1073741824));
                ReactPicker reactPicker2 = ReactPicker.this;
                reactPicker2.layout(reactPicker2.getLeft(), ReactPicker.this.getTop(), ReactPicker.this.getRight(), ReactPicker.this.getBottom());
            }
        };
        this.mMode = i2;
    }

    public ReactPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMode = 0;
        this.mItemSelectedListener = new AdapterView.OnItemSelectedListener() { // from class: com.facebook.react.views.picker.ReactPicker.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i22, long j2) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(i22);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(-1);
                }
            }
        };
        this.measureAndLayout = new Runnable() { // from class: com.facebook.react.views.picker.ReactPicker.2
            @Override // java.lang.Runnable
            public void run() {
                ReactPicker reactPicker = ReactPicker.this;
                reactPicker.measure(View.MeasureSpec.makeMeasureSpec(reactPicker.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactPicker.this.getHeight(), 1073741824));
                ReactPicker reactPicker2 = ReactPicker.this;
                reactPicker2.layout(reactPicker2.getLeft(), ReactPicker.this.getTop(), ReactPicker.this.getRight(), ReactPicker.this.getBottom());
            }
        };
    }

    public ReactPicker(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mMode = 0;
        this.mItemSelectedListener = new AdapterView.OnItemSelectedListener() { // from class: com.facebook.react.views.picker.ReactPicker.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i22, long j2) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(i22);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(-1);
                }
            }
        };
        this.measureAndLayout = new Runnable() { // from class: com.facebook.react.views.picker.ReactPicker.2
            @Override // java.lang.Runnable
            public void run() {
                ReactPicker reactPicker = ReactPicker.this;
                reactPicker.measure(View.MeasureSpec.makeMeasureSpec(reactPicker.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactPicker.this.getHeight(), 1073741824));
                ReactPicker reactPicker2 = ReactPicker.this;
                reactPicker2.layout(reactPicker2.getLeft(), ReactPicker.this.getTop(), ReactPicker.this.getRight(), ReactPicker.this.getBottom());
            }
        };
    }

    public ReactPicker(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mMode = 0;
        this.mItemSelectedListener = new AdapterView.OnItemSelectedListener() { // from class: com.facebook.react.views.picker.ReactPicker.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i22, long j2) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(i22);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(-1);
                }
            }
        };
        this.measureAndLayout = new Runnable() { // from class: com.facebook.react.views.picker.ReactPicker.2
            @Override // java.lang.Runnable
            public void run() {
                ReactPicker reactPicker = ReactPicker.this;
                reactPicker.measure(View.MeasureSpec.makeMeasureSpec(reactPicker.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactPicker.this.getHeight(), 1073741824));
                ReactPicker reactPicker2 = ReactPicker.this;
                reactPicker2.layout(reactPicker2.getLeft(), ReactPicker.this.getTop(), ReactPicker.this.getRight(), ReactPicker.this.getBottom());
            }
        };
        this.mMode = i3;
    }

    @Override // android.widget.AbsSpinner, android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        post(this.measureAndLayout);
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (getOnItemSelectedListener() == null) {
            setOnItemSelectedListener(this.mItemSelectedListener);
        }
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.mOnSelectListener = onSelectListener;
    }

    public OnSelectListener getOnSelectListener() {
        return this.mOnSelectListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStagedItems(List<ReactPickerItem> list) {
        this.mStagedItems = list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStagedSelection(int i2) {
        this.mStagedSelection = Integer.valueOf(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setImmediateSelection(int i2) {
        if (i2 != getSelectedItemPosition()) {
            setOnItemSelectedListener(null);
            setSelection(i2, false);
            setOnItemSelectedListener(this.mItemSelectedListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStagedPrimaryTextColor(Integer num) {
        this.mStagedPrimaryTextColor = num;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void commitStagedData() {
        setOnItemSelectedListener(null);
        ReactPickerAdapter reactPickerAdapter = (ReactPickerAdapter) getAdapter();
        int selectedItemPosition = getSelectedItemPosition();
        List<ReactPickerItem> list = this.mStagedItems;
        if (list != null && list != this.mItems) {
            this.mItems = list;
            this.mStagedItems = null;
            if (reactPickerAdapter == null) {
                reactPickerAdapter = new ReactPickerAdapter(getContext(), this.mItems);
                setAdapter((SpinnerAdapter) reactPickerAdapter);
            } else {
                reactPickerAdapter.clear();
                reactPickerAdapter.addAll(this.mItems);
                reactPickerAdapter.notifyDataSetChanged();
            }
        }
        Integer num = this.mStagedSelection;
        if (num != null && num.intValue() != selectedItemPosition) {
            setSelection(this.mStagedSelection.intValue(), false);
            this.mStagedSelection = null;
        }
        Integer num2 = this.mStagedPrimaryTextColor;
        if (num2 != null && reactPickerAdapter != null && num2 != reactPickerAdapter.getPrimaryTextColor()) {
            reactPickerAdapter.setPrimaryTextColor(this.mStagedPrimaryTextColor);
            this.mStagedPrimaryTextColor = null;
        }
        setOnItemSelectedListener(this.mItemSelectedListener);
    }

    public int getMode() {
        return this.mMode;
    }
}
