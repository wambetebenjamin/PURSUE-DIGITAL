package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManagerInterface;
/* loaded from: classes8.dex */
public abstract class BaseViewManagerDelegate<T extends View, U extends BaseViewManagerInterface<T>> implements ViewManagerDelegate<T> {
    protected final U mViewManager;

    public BaseViewManagerDelegate(U u) {
        this.mViewManager = u;
    }

    @Override // com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1721943862:
                if (str.equals(ViewProps.TRANSLATE_X)) {
                    c2 = 0;
                    break;
                }
                break;
            case -1721943861:
                if (str.equals(ViewProps.TRANSLATE_Y)) {
                    c2 = 1;
                    break;
                }
                break;
            case -1267206133:
                if (str.equals(ViewProps.OPACITY)) {
                    c2 = 2;
                    break;
                }
                break;
            case -1228066334:
                if (str.equals(ViewProps.BORDER_TOP_LEFT_RADIUS)) {
                    c2 = 3;
                    break;
                }
                break;
            case -908189618:
                if (str.equals(ViewProps.SCALE_X)) {
                    c2 = 4;
                    break;
                }
                break;
            case -908189617:
                if (str.equals(ViewProps.SCALE_Y)) {
                    c2 = 5;
                    break;
                }
                break;
            case -877170387:
                if (str.equals(ViewProps.TEST_ID)) {
                    c2 = 6;
                    break;
                }
                break;
            case -731417480:
                if (str.equals(ViewProps.Z_INDEX)) {
                    c2 = 7;
                    break;
                }
                break;
            case -101663499:
                if (str.equals(ViewProps.ACCESSIBILITY_HINT)) {
                    c2 = '\b';
                    break;
                }
                break;
            case -101359900:
                if (str.equals(ViewProps.ACCESSIBILITY_ROLE)) {
                    c2 = '\t';
                    break;
                }
                break;
            case -80891667:
                if (str.equals(ViewProps.RENDER_TO_HARDWARE_TEXTURE)) {
                    c2 = '\n';
                    break;
                }
                break;
            case -40300674:
                if (str.equals(ViewProps.ROTATION)) {
                    c2 = 11;
                    break;
                }
                break;
            case -4379043:
                if (str.equals(ViewProps.ELEVATION)) {
                    c2 = '\f';
                    break;
                }
                break;
            case 36255470:
                if (str.equals(ViewProps.ACCESSIBILITY_LIVE_REGION)) {
                    c2 = '\r';
                    break;
                }
                break;
            case 333432965:
                if (str.equals(ViewProps.BORDER_TOP_RIGHT_RADIUS)) {
                    c2 = 14;
                    break;
                }
                break;
            case 581268560:
                if (str.equals(ViewProps.BORDER_BOTTOM_LEFT_RADIUS)) {
                    c2 = 15;
                    break;
                }
                break;
            case 588239831:
                if (str.equals(ViewProps.BORDER_BOTTOM_RIGHT_RADIUS)) {
                    c2 = 16;
                    break;
                }
                break;
            case 746986311:
                if (str.equals(ViewProps.IMPORTANT_FOR_ACCESSIBILITY)) {
                    c2 = 17;
                    break;
                }
                break;
            case 1052666732:
                if (str.equals(ViewProps.TRANSFORM)) {
                    c2 = 18;
                    break;
                }
                break;
            case 1146842694:
                if (str.equals(ViewProps.ACCESSIBILITY_LABEL)) {
                    c2 = 19;
                    break;
                }
                break;
            case 1153872867:
                if (str.equals(ViewProps.ACCESSIBILITY_STATE)) {
                    c2 = 20;
                    break;
                }
                break;
            case 1287124693:
                if (str.equals(ViewProps.BACKGROUND_COLOR)) {
                    c2 = 21;
                    break;
                }
                break;
            case 1349188574:
                if (str.equals(ViewProps.BORDER_RADIUS)) {
                    c2 = 22;
                    break;
                }
                break;
            case 1505602511:
                if (str.equals(ViewProps.ACCESSIBILITY_ACTIONS)) {
                    c2 = 23;
                    break;
                }
                break;
            case 2045685618:
                if (str.equals(ViewProps.NATIVE_ID)) {
                    c2 = 24;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                this.mViewManager.setTranslateX(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case 1:
                this.mViewManager.setTranslateY(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case 2:
                this.mViewManager.setOpacity(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                return;
            case 3:
                this.mViewManager.setBorderTopLeftRadius(t, obj != null ? ((Double) obj).floatValue() : Float.NaN);
                return;
            case 4:
                this.mViewManager.setScaleX(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                return;
            case 5:
                this.mViewManager.setScaleY(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                return;
            case 6:
                this.mViewManager.setTestId(t, (String) obj);
                return;
            case 7:
                this.mViewManager.setZIndex(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case '\b':
                this.mViewManager.setAccessibilityHint(t, (String) obj);
                return;
            case '\t':
                this.mViewManager.setAccessibilityRole(t, (String) obj);
                return;
            case '\n':
                this.mViewManager.setRenderToHardwareTexture(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 11:
                this.mViewManager.setRotation(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case '\f':
                this.mViewManager.setElevation(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case '\r':
                this.mViewManager.setAccessibilityLiveRegion(t, (String) obj);
                return;
            case 14:
                this.mViewManager.setBorderTopRightRadius(t, obj != null ? ((Double) obj).floatValue() : Float.NaN);
                return;
            case 15:
                this.mViewManager.setBorderBottomLeftRadius(t, obj != null ? ((Double) obj).floatValue() : Float.NaN);
                return;
            case 16:
                this.mViewManager.setBorderBottomRightRadius(t, obj != null ? ((Double) obj).floatValue() : Float.NaN);
                return;
            case 17:
                this.mViewManager.setImportantForAccessibility(t, (String) obj);
                return;
            case 18:
                this.mViewManager.setTransform(t, (ReadableArray) obj);
                return;
            case 19:
                this.mViewManager.setAccessibilityLabel(t, (String) obj);
                return;
            case 20:
                this.mViewManager.setViewState(t, (ReadableMap) obj);
                return;
            case 21:
                this.mViewManager.setBackgroundColor(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case 22:
                this.mViewManager.setBorderRadius(t, obj != null ? ((Double) obj).floatValue() : Float.NaN);
                return;
            case 23:
                this.mViewManager.setAccessibilityActions(t, (ReadableArray) obj);
                return;
            case 24:
                this.mViewManager.setNativeId(t, (String) obj);
                return;
            default:
                return;
        }
    }
}
