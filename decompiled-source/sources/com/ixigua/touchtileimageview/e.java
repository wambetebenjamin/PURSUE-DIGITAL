package com.ixigua.touchtileimageview;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
/* compiled from: FixScrollJumpPageAdapter.java */
/* loaded from: classes9.dex */
public abstract class e {
    public static final int POSITION_NONE = -2;
    public static final int POSITION_UNCHANGED = -1;

    /* renamed from: a  reason: collision with root package name */
    private final DataSetObservable f17132a = new DataSetObservable();

    /* renamed from: b  reason: collision with root package name */
    private DataSetObserver f17133b;

    @Deprecated
    public void finishUpdate(View view) {
    }

    public abstract int getCount();

    public int getItemPosition(Object obj) {
        return -1;
    }

    public CharSequence getPageTitle(int i2) {
        return null;
    }

    public float getPageWidth(int i2) {
        return 1.0f;
    }

    public abstract boolean isViewFromObject(View view, Object obj);

    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
    }

    public Parcelable saveState() {
        return null;
    }

    @Deprecated
    public void setPrimaryItem(View view, int i2, Object obj) {
    }

    @Deprecated
    public void startUpdate(View view) {
    }

    public void startUpdate(ViewGroup viewGroup) {
        startUpdate((View) viewGroup);
    }

    public Object instantiateItem(ViewGroup viewGroup, int i2) {
        return instantiateItem((View) viewGroup, i2);
    }

    public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
        destroyItem((View) viewGroup, i2, obj);
    }

    public void setPrimaryItem(ViewGroup viewGroup, int i2, Object obj) {
        setPrimaryItem((View) viewGroup, i2, obj);
    }

    public void finishUpdate(ViewGroup viewGroup) {
        finishUpdate((View) viewGroup);
    }

    @Deprecated
    public Object instantiateItem(View view, int i2) {
        throw new UnsupportedOperationException("Required method instantiateItem was not overridden");
    }

    @Deprecated
    public void destroyItem(View view, int i2, Object obj) {
        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
    }

    public void notifyDataSetChanged() {
        synchronized (this) {
            DataSetObserver dataSetObserver = this.f17133b;
            if (dataSetObserver != null) {
                dataSetObserver.onChanged();
            }
        }
        this.f17132a.notifyChanged();
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.f17132a.registerObserver(dataSetObserver);
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.f17132a.unregisterObserver(dataSetObserver);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(DataSetObserver dataSetObserver) {
        synchronized (this) {
            this.f17133b = dataSetObserver;
        }
    }
}
