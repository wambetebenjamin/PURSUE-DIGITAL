package com.bytedance.ttgame.sdk.module.skin.attr;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import com.brentvatne.react.ReactVideoViewManager;
import com.bytedance.ttgame.sdk.module.skin.ResourceManager;
import com.bytedance.ttgame.sdk.module.skin.SkinManager;
import com.bytedance.ttgame.sdk.module.skin.constants.SkinConfig;
/* loaded from: classes8.dex */
public enum SkinAttrType {
    BACKGROUND("background") { // from class: com.bytedance.ttgame.sdk.module.skin.attr.SkinAttrType.1
        @Override // com.bytedance.ttgame.sdk.module.skin.attr.SkinAttrType
        public void apply(View view, String str) {
            Drawable drawableByName = getResourceManager().getDrawableByName(str);
            Log.w(SkinConfig.TAG, "changing skin for background");
            if (drawableByName != null) {
                view.setBackgroundDrawable(drawableByName);
            } else {
                view.setBackgroundColor(getResourceManager().getColor(str));
            }
        }
    },
    COLOR("textColor") { // from class: com.bytedance.ttgame.sdk.module.skin.attr.SkinAttrType.2
        @Override // com.bytedance.ttgame.sdk.module.skin.attr.SkinAttrType
        public void apply(View view, String str) {
            ColorStateList colorStateList;
            Log.w(SkinConfig.TAG, "changing skin for textColor");
            if (view instanceof TextView) {
                ColorStateList colorStateList2 = getResourceManager().getColorStateList(str);
                if (colorStateList2 == null) {
                    return;
                }
                ((TextView) view).setTextColor(colorStateList2);
            } else if (!(view instanceof RadioButton) || (colorStateList = getResourceManager().getColorStateList(str)) == null) {
            } else {
                ((RadioButton) view).setTextColor(colorStateList);
            }
        }
    },
    SRC(ReactVideoViewManager.PROP_SRC) { // from class: com.bytedance.ttgame.sdk.module.skin.attr.SkinAttrType.3
        @Override // com.bytedance.ttgame.sdk.module.skin.attr.SkinAttrType
        public void apply(View view, String str) {
            Drawable drawableByName;
            Log.w(SkinConfig.TAG, "changing skin for src");
            if (!(view instanceof ImageView) || (drawableByName = getResourceManager().getDrawableByName(str)) == null) {
                return;
            }
            ((ImageView) view).setImageDrawable(drawableByName);
        }
    },
    DIVIDER("divider") { // from class: com.bytedance.ttgame.sdk.module.skin.attr.SkinAttrType.4
        @Override // com.bytedance.ttgame.sdk.module.skin.attr.SkinAttrType
        public void apply(View view, String str) {
            Drawable drawableByName;
            Log.w(SkinConfig.TAG, "changing skin for divider");
            if (!(view instanceof ListView) || (drawableByName = getResourceManager().getDrawableByName(str)) == null) {
                return;
            }
            ((ListView) view).setDivider(drawableByName);
        }
    },
    BUTTON("button") { // from class: com.bytedance.ttgame.sdk.module.skin.attr.SkinAttrType.5
        @Override // com.bytedance.ttgame.sdk.module.skin.attr.SkinAttrType
        public void apply(View view, String str) {
            Drawable drawableByName;
            Log.w(SkinConfig.TAG, "changing skin for button");
            if (!(view instanceof CheckBox) || (drawableByName = getResourceManager().getDrawableByName(str)) == null) {
                return;
            }
            ((CheckBox) view).setButtonDrawable(drawableByName);
        }
    },
    PROGRESS_DRAWABLE("progressDrawable") { // from class: com.bytedance.ttgame.sdk.module.skin.attr.SkinAttrType.6
        @Override // com.bytedance.ttgame.sdk.module.skin.attr.SkinAttrType
        public void apply(View view, String str) {
            Drawable drawableByName;
            Log.w(SkinConfig.TAG, "changing skin for progressDrawable");
            if (!(view instanceof ProgressBar) || (drawableByName = getResourceManager().getDrawableByName(str)) == null) {
                return;
            }
            ((ProgressBar) view).setProgressDrawable(drawableByName);
        }
    },
    LAYOUT_WIDTH("layout_width") { // from class: com.bytedance.ttgame.sdk.module.skin.attr.SkinAttrType.7
        @Override // com.bytedance.ttgame.sdk.module.skin.attr.SkinAttrType
        public void apply(View view, String str) {
            Log.w(SkinConfig.TAG, "changing skin for layout_width");
            int dimension = getResourceManager().getDimension(str);
            if (dimension == -1) {
                return;
            }
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = dimension;
            view.setLayoutParams(layoutParams);
        }
    },
    LAYOUT_HEIGHT("layout_height") { // from class: com.bytedance.ttgame.sdk.module.skin.attr.SkinAttrType.8
        @Override // com.bytedance.ttgame.sdk.module.skin.attr.SkinAttrType
        public void apply(View view, String str) {
            Log.w(SkinConfig.TAG, "changing skin for layout_height");
            int dimension = getResourceManager().getDimension(str);
            if (dimension == -1) {
                return;
            }
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = dimension;
            view.setLayoutParams(layoutParams);
        }
    };
    
    String attrType;

    public abstract void apply(View view, String str);

    SkinAttrType(String str) {
        this.attrType = str;
    }

    public String getAttrType() {
        return this.attrType;
    }

    public ResourceManager getResourceManager() {
        return SkinManager.INSTANCE.getResourceManager();
    }
}
