package com.twitter.sdk.android.tweetui;

import android.content.Context;
import android.database.DataSetObserver;
import android.widget.BaseAdapter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.models.Identifiable;
/* loaded from: classes10.dex */
abstract class TimelineListAdapter<T extends Identifiable> extends BaseAdapter {
    protected final Context context;
    protected final TimelineDelegate<T> delegate;

    TimelineListAdapter(Context context, Timeline<T> timeline) {
        this(context, new TimelineDelegate(timeline));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TimelineListAdapter(Context context, TimelineDelegate<T> timelineDelegate) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null");
        }
        this.context = context;
        this.delegate = timelineDelegate;
        timelineDelegate.refresh(null);
    }

    public void refresh(Callback<TimelineResult<T>> callback) {
        this.delegate.refresh(callback);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.delegate.getCount();
    }

    @Override // android.widget.Adapter
    public T getItem(int i2) {
        return this.delegate.getItem(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return this.delegate.getItemId(i2);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.delegate.registerDataSetObserver(dataSetObserver);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.delegate.unregisterDataSetObserver(dataSetObserver);
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        this.delegate.notifyDataSetChanged();
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetInvalidated() {
        this.delegate.notifyDataSetInvalidated();
    }
}
