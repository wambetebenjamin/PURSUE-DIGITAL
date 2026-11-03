package me.drakeet.multitype;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.List;
/* loaded from: classes10.dex */
public abstract class ItemViewBinder<T, VH extends RecyclerView.ViewHolder> {
    MultiTypeAdapter adapter;

    /* JADX INFO: Access modifiers changed from: protected */
    public long getItemId(T t) {
        return -1L;
    }

    protected abstract void onBindViewHolder(VH vh, T t);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract VH onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup);

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean onFailedToRecycleView(VH vh) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onViewAttachedToWindow(VH vh) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onViewDetachedFromWindow(VH vh) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onViewRecycled(VH vh) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onBindViewHolder(VH vh, T t, List<Object> list) {
        onBindViewHolder(vh, t);
    }

    protected final int getPosition(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition();
    }

    protected final MultiTypeAdapter getAdapter() {
        MultiTypeAdapter multiTypeAdapter = this.adapter;
        if (multiTypeAdapter != null) {
            return multiTypeAdapter;
        }
        throw new IllegalStateException("ItemViewBinder " + this + " not attached to MultiTypeAdapter. You should not call the method before registering the binder.");
    }
}
