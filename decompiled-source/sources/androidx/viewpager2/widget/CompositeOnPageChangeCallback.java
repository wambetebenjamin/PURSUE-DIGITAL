package androidx.viewpager2.widget;

import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
/* loaded from: classes.dex */
final class CompositeOnPageChangeCallback extends ViewPager2.OnPageChangeCallback {
    private final List<ViewPager2.OnPageChangeCallback> mCallbacks;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompositeOnPageChangeCallback(int i2) {
        this.mCallbacks = new ArrayList(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addOnPageChangeCallback(ViewPager2.OnPageChangeCallback onPageChangeCallback) {
        this.mCallbacks.add(onPageChangeCallback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeOnPageChangeCallback(ViewPager2.OnPageChangeCallback onPageChangeCallback) {
        this.mCallbacks.remove(onPageChangeCallback);
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageScrolled(int i2, float f2, int i3) {
        try {
            for (ViewPager2.OnPageChangeCallback onPageChangeCallback : this.mCallbacks) {
                onPageChangeCallback.onPageScrolled(i2, f2, i3);
            }
        } catch (ConcurrentModificationException e2) {
            throwCallbackListModifiedWhileInUse(e2);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageSelected(int i2) {
        try {
            for (ViewPager2.OnPageChangeCallback onPageChangeCallback : this.mCallbacks) {
                onPageChangeCallback.onPageSelected(i2);
            }
        } catch (ConcurrentModificationException e2) {
            throwCallbackListModifiedWhileInUse(e2);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageScrollStateChanged(int i2) {
        try {
            for (ViewPager2.OnPageChangeCallback onPageChangeCallback : this.mCallbacks) {
                onPageChangeCallback.onPageScrollStateChanged(i2);
            }
        } catch (ConcurrentModificationException e2) {
            throwCallbackListModifiedWhileInUse(e2);
        }
    }

    private void throwCallbackListModifiedWhileInUse(ConcurrentModificationException concurrentModificationException) {
        throw new IllegalStateException("Adding and removing callbacks during dispatch to callbacks is not supported", concurrentModificationException);
    }
}
