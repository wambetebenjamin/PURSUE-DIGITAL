package androidx.core.view.accessibility;

import android.os.Build;
import android.os.Parcelable;
import android.view.View;
import android.view.accessibility.AccessibilityRecord;
import java.util.List;
/* loaded from: classes.dex */
public class AccessibilityRecordCompat {
    private final AccessibilityRecord mRecord;

    @Deprecated
    public AccessibilityRecordCompat(Object obj) {
        this.mRecord = (AccessibilityRecord) obj;
    }

    @Deprecated
    public Object getImpl() {
        return this.mRecord;
    }

    @Deprecated
    public static AccessibilityRecordCompat obtain(AccessibilityRecordCompat accessibilityRecordCompat) {
        return new AccessibilityRecordCompat(AccessibilityRecord.obtain(accessibilityRecordCompat.mRecord));
    }

    @Deprecated
    public static AccessibilityRecordCompat obtain() {
        return new AccessibilityRecordCompat(AccessibilityRecord.obtain());
    }

    @Deprecated
    public void setSource(View view) {
        this.mRecord.setSource(view);
    }

    @Deprecated
    public void setSource(View view, int i2) {
        setSource(this.mRecord, view, i2);
    }

    public static void setSource(AccessibilityRecord accessibilityRecord, View view, int i2) {
        if (Build.VERSION.SDK_INT >= 16) {
            Api16Impl.setSource(accessibilityRecord, view, i2);
        }
    }

    @Deprecated
    public AccessibilityNodeInfoCompat getSource() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mRecord.getSource());
    }

    @Deprecated
    public int getWindowId() {
        return this.mRecord.getWindowId();
    }

    @Deprecated
    public boolean isChecked() {
        return this.mRecord.isChecked();
    }

    @Deprecated
    public void setChecked(boolean z) {
        this.mRecord.setChecked(z);
    }

    @Deprecated
    public boolean isEnabled() {
        return this.mRecord.isEnabled();
    }

    @Deprecated
    public void setEnabled(boolean z) {
        this.mRecord.setEnabled(z);
    }

    @Deprecated
    public boolean isPassword() {
        return this.mRecord.isPassword();
    }

    @Deprecated
    public void setPassword(boolean z) {
        this.mRecord.setPassword(z);
    }

    @Deprecated
    public boolean isFullScreen() {
        return this.mRecord.isFullScreen();
    }

    @Deprecated
    public void setFullScreen(boolean z) {
        this.mRecord.setFullScreen(z);
    }

    @Deprecated
    public boolean isScrollable() {
        return this.mRecord.isScrollable();
    }

    @Deprecated
    public void setScrollable(boolean z) {
        this.mRecord.setScrollable(z);
    }

    @Deprecated
    public int getItemCount() {
        return this.mRecord.getItemCount();
    }

    @Deprecated
    public void setItemCount(int i2) {
        this.mRecord.setItemCount(i2);
    }

    @Deprecated
    public int getCurrentItemIndex() {
        return this.mRecord.getCurrentItemIndex();
    }

    @Deprecated
    public void setCurrentItemIndex(int i2) {
        this.mRecord.setCurrentItemIndex(i2);
    }

    @Deprecated
    public int getFromIndex() {
        return this.mRecord.getFromIndex();
    }

    @Deprecated
    public void setFromIndex(int i2) {
        this.mRecord.setFromIndex(i2);
    }

    @Deprecated
    public int getToIndex() {
        return this.mRecord.getToIndex();
    }

    @Deprecated
    public void setToIndex(int i2) {
        this.mRecord.setToIndex(i2);
    }

    @Deprecated
    public int getScrollX() {
        return this.mRecord.getScrollX();
    }

    @Deprecated
    public void setScrollX(int i2) {
        this.mRecord.setScrollX(i2);
    }

    @Deprecated
    public int getScrollY() {
        return this.mRecord.getScrollY();
    }

    @Deprecated
    public void setScrollY(int i2) {
        this.mRecord.setScrollY(i2);
    }

    @Deprecated
    public int getMaxScrollX() {
        return getMaxScrollX(this.mRecord);
    }

    public static int getMaxScrollX(AccessibilityRecord accessibilityRecord) {
        if (Build.VERSION.SDK_INT >= 15) {
            return Api15Impl.getMaxScrollX(accessibilityRecord);
        }
        return 0;
    }

    @Deprecated
    public void setMaxScrollX(int i2) {
        setMaxScrollX(this.mRecord, i2);
    }

    public static void setMaxScrollX(AccessibilityRecord accessibilityRecord, int i2) {
        if (Build.VERSION.SDK_INT >= 15) {
            Api15Impl.setMaxScrollX(accessibilityRecord, i2);
        }
    }

    @Deprecated
    public int getMaxScrollY() {
        return getMaxScrollY(this.mRecord);
    }

    public static int getMaxScrollY(AccessibilityRecord accessibilityRecord) {
        if (Build.VERSION.SDK_INT >= 15) {
            return Api15Impl.getMaxScrollY(accessibilityRecord);
        }
        return 0;
    }

    @Deprecated
    public void setMaxScrollY(int i2) {
        setMaxScrollY(this.mRecord, i2);
    }

    public static void setMaxScrollY(AccessibilityRecord accessibilityRecord, int i2) {
        if (Build.VERSION.SDK_INT >= 15) {
            Api15Impl.setMaxScrollY(accessibilityRecord, i2);
        }
    }

    @Deprecated
    public int getAddedCount() {
        return this.mRecord.getAddedCount();
    }

    @Deprecated
    public void setAddedCount(int i2) {
        this.mRecord.setAddedCount(i2);
    }

    @Deprecated
    public int getRemovedCount() {
        return this.mRecord.getRemovedCount();
    }

    @Deprecated
    public void setRemovedCount(int i2) {
        this.mRecord.setRemovedCount(i2);
    }

    @Deprecated
    public CharSequence getClassName() {
        return this.mRecord.getClassName();
    }

    @Deprecated
    public void setClassName(CharSequence charSequence) {
        this.mRecord.setClassName(charSequence);
    }

    @Deprecated
    public List<CharSequence> getText() {
        return this.mRecord.getText();
    }

    @Deprecated
    public CharSequence getBeforeText() {
        return this.mRecord.getBeforeText();
    }

    @Deprecated
    public void setBeforeText(CharSequence charSequence) {
        this.mRecord.setBeforeText(charSequence);
    }

    @Deprecated
    public CharSequence getContentDescription() {
        return this.mRecord.getContentDescription();
    }

    @Deprecated
    public void setContentDescription(CharSequence charSequence) {
        this.mRecord.setContentDescription(charSequence);
    }

    @Deprecated
    public Parcelable getParcelableData() {
        return this.mRecord.getParcelableData();
    }

    @Deprecated
    public void setParcelableData(Parcelable parcelable) {
        this.mRecord.setParcelableData(parcelable);
    }

    @Deprecated
    public void recycle() {
        this.mRecord.recycle();
    }

    @Deprecated
    public int hashCode() {
        AccessibilityRecord accessibilityRecord = this.mRecord;
        if (accessibilityRecord == null) {
            return 0;
        }
        return accessibilityRecord.hashCode();
    }

    @Deprecated
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AccessibilityRecordCompat) {
            AccessibilityRecordCompat accessibilityRecordCompat = (AccessibilityRecordCompat) obj;
            AccessibilityRecord accessibilityRecord = this.mRecord;
            if (accessibilityRecord == null) {
                return accessibilityRecordCompat.mRecord == null;
            }
            return accessibilityRecord.equals(accessibilityRecordCompat.mRecord);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Api16Impl {
        private Api16Impl() {
        }

        static void setSource(AccessibilityRecord accessibilityRecord, View view, int i2) {
            accessibilityRecord.setSource(view, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Api15Impl {
        private Api15Impl() {
        }

        static int getMaxScrollX(AccessibilityRecord accessibilityRecord) {
            return accessibilityRecord.getMaxScrollX();
        }

        static void setMaxScrollX(AccessibilityRecord accessibilityRecord, int i2) {
            accessibilityRecord.setMaxScrollX(i2);
        }

        static int getMaxScrollY(AccessibilityRecord accessibilityRecord) {
            return accessibilityRecord.getMaxScrollY();
        }

        static void setMaxScrollY(AccessibilityRecord accessibilityRecord, int i2) {
            accessibilityRecord.setMaxScrollY(i2);
        }
    }
}
