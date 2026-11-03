package com.facebook.react.fabric.mounting.mountitems;

import com.bytedance.librarian.c;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
/* loaded from: classes8.dex */
public class RemoveDeleteMultiMountItem implements MountItem {
    private static final int DELETE_FLAG = 2;
    private static final int FLAGS_INDEX = 3;
    private static final int INSTRUCTION_FIELDS_LEN = 4;
    private static final int PARENT_TAG_INDEX = 1;
    private static final int REMOVE_FLAG = 1;
    private static final int TAG_INDEX = 0;
    private static final int VIEW_INDEX_INDEX = 2;
    private int[] mMetadata;

    public RemoveDeleteMultiMountItem(int[] iArr) {
        this.mMetadata = iArr;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(MountingManager mountingManager) {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = this.mMetadata;
            if (i3 >= iArr.length) {
                break;
            }
            if ((iArr[i3 + 3] & 1) != 0) {
                mountingManager.removeViewAt(iArr[i3 + 1], iArr[i3 + 2]);
            }
            i3 += 4;
        }
        while (true) {
            int[] iArr2 = this.mMetadata;
            if (i2 >= iArr2.length) {
                return;
            }
            if ((iArr2[i2 + 3] & 2) != 0) {
                mountingManager.deleteView(iArr2[i2 + 0]);
            }
            i2 += 4;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < this.mMetadata.length; i2 += 4) {
            if (sb.length() > 0) {
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
            sb.append("RemoveDeleteMultiMountItem (").append((i2 / 4) + 1).append(c.a.SEPARATOR).append(this.mMetadata.length / 4).append("): [").append(this.mMetadata[i2 + 0]).append("] parent [").append(this.mMetadata[i2 + 1]).append("] idx ").append(this.mMetadata[i2 + 2]).append(" ").append(this.mMetadata[i2 + 3]);
        }
        return sb.toString();
    }
}
