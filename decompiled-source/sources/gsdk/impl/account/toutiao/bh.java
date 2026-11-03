package gsdk.impl.account.toutiao;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.bytedance.ttgame.sdk.module.utils.RTLUtils;
import java.util.Locale;
/* loaded from: classes10.dex */
public class bh extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    private final int f19395a;

    public bh(int i2) {
        this.f19395a = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int itemCount = state.getItemCount();
        boolean isRTLLanguage = RTLUtils.getInstance().isRTLLanguage(Locale.getDefault().getLanguage());
        if (childAdapterPosition < itemCount - 1) {
            if (isRTLLanguage) {
                rect.left = this.f19395a;
            } else {
                rect.right = this.f19395a;
            }
        }
    }
}
