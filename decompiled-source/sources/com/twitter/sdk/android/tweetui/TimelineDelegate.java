package com.twitter.sdk.android.tweetui;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Identifiable;
import java.util.ArrayList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public class TimelineDelegate<T extends Identifiable> {
    static final long CAPACITY = 200;
    List<T> itemList;
    final DataSetObservable listAdapterObservable;
    final Timeline<T> timeline;
    final TimelineStateHolder timelineStateHolder;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TimelineDelegate(Timeline<T> timeline) {
        this(timeline, null, null);
    }

    TimelineDelegate(Timeline<T> timeline, DataSetObservable dataSetObservable, List<T> list) {
        if (timeline == null) {
            throw new IllegalArgumentException("Timeline must not be null");
        }
        this.timeline = timeline;
        this.timelineStateHolder = new TimelineStateHolder();
        if (dataSetObservable == null) {
            this.listAdapterObservable = new DataSetObservable();
        } else {
            this.listAdapterObservable = dataSetObservable;
        }
        if (list == null) {
            this.itemList = new ArrayList();
        } else {
            this.itemList = list;
        }
    }

    public void refresh(Callback<TimelineResult<T>> callback) {
        this.timelineStateHolder.resetCursors();
        loadNext(this.timelineStateHolder.positionForNext(), new RefreshCallback(callback, this.timelineStateHolder));
    }

    public void next(Callback<TimelineResult<T>> callback) {
        loadNext(this.timelineStateHolder.positionForNext(), new NextCallback(callback, this.timelineStateHolder));
    }

    public void previous() {
        loadPrevious(this.timelineStateHolder.positionForPrevious(), new PreviousCallback(this.timelineStateHolder));
    }

    public int getCount() {
        return this.itemList.size();
    }

    public Timeline getTimeline() {
        return this.timeline;
    }

    public T getItem(int i2) {
        if (isLastPosition(i2)) {
            previous();
        }
        return this.itemList.get(i2);
    }

    public long getItemId(int i2) {
        return this.itemList.get(i2).getId();
    }

    public void setItemById(T t) {
        for (int i2 = 0; i2 < this.itemList.size(); i2++) {
            if (t.getId() == this.itemList.get(i2).getId()) {
                this.itemList.set(i2, t);
            }
        }
        notifyDataSetChanged();
    }

    boolean withinMaxCapacity() {
        return ((long) this.itemList.size()) < CAPACITY;
    }

    boolean isLastPosition(int i2) {
        return i2 == this.itemList.size() - 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void loadNext(Long l, Callback<TimelineResult<T>> callback) {
        if (withinMaxCapacity()) {
            if (this.timelineStateHolder.startTimelineRequest()) {
                this.timeline.next(l, callback);
                return;
            } else {
                callback.failure(new TwitterException("Request already in flight"));
                return;
            }
        }
        callback.failure(new TwitterException("Max capacity reached"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void loadPrevious(Long l, Callback<TimelineResult<T>> callback) {
        if (withinMaxCapacity()) {
            if (this.timelineStateHolder.startTimelineRequest()) {
                this.timeline.previous(l, callback);
                return;
            } else {
                callback.failure(new TwitterException("Request already in flight"));
                return;
            }
        }
        callback.failure(new TwitterException("Max capacity reached"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public class DefaultCallback extends Callback<TimelineResult<T>> {
        final Callback<TimelineResult<T>> developerCallback;
        final TimelineStateHolder timelineStateHolder;

        DefaultCallback(Callback<TimelineResult<T>> callback, TimelineStateHolder timelineStateHolder) {
            this.developerCallback = callback;
            this.timelineStateHolder = timelineStateHolder;
        }

        @Override // com.twitter.sdk.android.core.Callback
        public void success(Result<TimelineResult<T>> result) {
            this.timelineStateHolder.finishTimelineRequest();
            Callback<TimelineResult<T>> callback = this.developerCallback;
            if (callback != null) {
                callback.success(result);
            }
        }

        @Override // com.twitter.sdk.android.core.Callback
        public void failure(TwitterException twitterException) {
            this.timelineStateHolder.finishTimelineRequest();
            Callback<TimelineResult<T>> callback = this.developerCallback;
            if (callback != null) {
                callback.failure(twitterException);
            }
        }
    }

    /* loaded from: classes10.dex */
    class NextCallback extends TimelineDelegate<T>.DefaultCallback {
        /* JADX INFO: Access modifiers changed from: package-private */
        public NextCallback(Callback<TimelineResult<T>> callback, TimelineStateHolder timelineStateHolder) {
            super(callback, timelineStateHolder);
        }

        @Override // com.twitter.sdk.android.tweetui.TimelineDelegate.DefaultCallback, com.twitter.sdk.android.core.Callback
        public void success(Result<TimelineResult<T>> result) {
            if (result.data.items.size() > 0) {
                ArrayList arrayList = new ArrayList(result.data.items);
                arrayList.addAll(TimelineDelegate.this.itemList);
                TimelineDelegate.this.itemList = arrayList;
                TimelineDelegate.this.notifyDataSetChanged();
                this.timelineStateHolder.setNextCursor(result.data.timelineCursor);
            }
            super.success(result);
        }
    }

    /* loaded from: classes10.dex */
    class RefreshCallback extends TimelineDelegate<T>.NextCallback {
        /* JADX INFO: Access modifiers changed from: package-private */
        public RefreshCallback(Callback<TimelineResult<T>> callback, TimelineStateHolder timelineStateHolder) {
            super(callback, timelineStateHolder);
        }

        @Override // com.twitter.sdk.android.tweetui.TimelineDelegate.NextCallback, com.twitter.sdk.android.tweetui.TimelineDelegate.DefaultCallback, com.twitter.sdk.android.core.Callback
        public void success(Result<TimelineResult<T>> result) {
            if (result.data.items.size() > 0) {
                TimelineDelegate.this.itemList.clear();
            }
            super.success(result);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public class PreviousCallback extends TimelineDelegate<T>.DefaultCallback {
        /* JADX INFO: Access modifiers changed from: package-private */
        public PreviousCallback(TimelineStateHolder timelineStateHolder) {
            super(null, timelineStateHolder);
        }

        @Override // com.twitter.sdk.android.tweetui.TimelineDelegate.DefaultCallback, com.twitter.sdk.android.core.Callback
        public void success(Result<TimelineResult<T>> result) {
            if (result.data.items.size() > 0) {
                TimelineDelegate.this.itemList.addAll(result.data.items);
                TimelineDelegate.this.notifyDataSetChanged();
                this.timelineStateHolder.setPreviousCursor(result.data.timelineCursor);
            }
            super.success(result);
        }
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.listAdapterObservable.registerObserver(dataSetObserver);
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.listAdapterObservable.unregisterObserver(dataSetObserver);
    }

    public void notifyDataSetChanged() {
        this.listAdapterObservable.notifyChanged();
    }

    public void notifyDataSetInvalidated() {
        this.listAdapterObservable.notifyInvalidated();
    }
}
