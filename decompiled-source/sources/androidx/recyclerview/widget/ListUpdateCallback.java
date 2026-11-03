package androidx.recyclerview.widget;
/* loaded from: classes.dex */
public interface ListUpdateCallback {
    void onChanged(int i2, int i3, Object obj);

    void onInserted(int i2, int i3);

    void onMoved(int i2, int i3);

    void onRemoved(int i2, int i3);
}
