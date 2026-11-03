package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.viewmanagers.SliderManagerInterface;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
/* loaded from: classes8.dex */
public class SliderManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & SliderManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public SliderManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1900655011:
                if (str.equals("maximumTrackTintColor")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1736983259:
                if (str.equals("thumbImage")) {
                    c2 = 1;
                    break;
                }
                break;
            case -1609594047:
                if (str.equals(ViewProps.ENABLED)) {
                    c2 = 2;
                    break;
                }
                break;
            case -1021497397:
                if (str.equals("minimumTrackTintColor")) {
                    c2 = 3;
                    break;
                }
                break;
            case -981448432:
                if (str.equals("maximumTrackImage")) {
                    c2 = 4;
                    break;
                }
                break;
            case -877170387:
                if (str.equals(ViewProps.TEST_ID)) {
                    c2 = 5;
                    break;
                }
                break;
            case 3540684:
                if (str.equals("step")) {
                    c2 = 6;
                    break;
                }
                break;
            case 111972721:
                if (str.equals("value")) {
                    c2 = 7;
                    break;
                }
                break;
            case 270940796:
                if (str.equals("disabled")) {
                    c2 = '\b';
                    break;
                }
                break;
            case 718061361:
                if (str.equals("maximumValue")) {
                    c2 = '\t';
                    break;
                }
                break;
            case 1139400400:
                if (str.equals("trackImage")) {
                    c2 = '\n';
                    break;
                }
                break;
            case 1192487427:
                if (str.equals("minimumValue")) {
                    c2 = 11;
                    break;
                }
                break;
            case 1333596542:
                if (str.equals("minimumTrackImage")) {
                    c2 = '\f';
                    break;
                }
                break;
            case 1912319986:
                if (str.equals("thumbTintColor")) {
                    c2 = '\r';
                    break;
                }
                break;
        }
        double d2 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        switch (c2) {
            case 0:
                ((SliderManagerInterface) this.mViewManager).setMaximumTrackTintColor(t, obj != null ? Integer.valueOf(((Double) obj).intValue()) : null);
                return;
            case 1:
                ((SliderManagerInterface) this.mViewManager).setThumbImage(t, (ReadableMap) obj);
                return;
            case 2:
                ((SliderManagerInterface) this.mViewManager).setEnabled(t, obj != null ? ((Boolean) obj).booleanValue() : true);
                return;
            case 3:
                ((SliderManagerInterface) this.mViewManager).setMinimumTrackTintColor(t, obj != null ? Integer.valueOf(((Double) obj).intValue()) : null);
                return;
            case 4:
                ((SliderManagerInterface) this.mViewManager).setMaximumTrackImage(t, (ReadableMap) obj);
                return;
            case 5:
                ((SliderManagerInterface) this.mViewManager).setTestID(t, obj == null ? "" : (String) obj);
                return;
            case 6:
                SliderManagerInterface sliderManagerInterface = (SliderManagerInterface) this.mViewManager;
                if (obj != null) {
                    d2 = ((Double) obj).doubleValue();
                }
                sliderManagerInterface.setStep(t, d2);
                return;
            case 7:
                SliderManagerInterface sliderManagerInterface2 = (SliderManagerInterface) this.mViewManager;
                if (obj != null) {
                    d2 = ((Double) obj).doubleValue();
                }
                sliderManagerInterface2.setValue(t, d2);
                return;
            case '\b':
                ((SliderManagerInterface) this.mViewManager).setDisabled(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case '\t':
                ((SliderManagerInterface) this.mViewManager).setMaximumValue(t, obj == null ? 1.0d : ((Double) obj).doubleValue());
                return;
            case '\n':
                ((SliderManagerInterface) this.mViewManager).setTrackImage(t, (ReadableMap) obj);
                return;
            case 11:
                SliderManagerInterface sliderManagerInterface3 = (SliderManagerInterface) this.mViewManager;
                if (obj != null) {
                    d2 = ((Double) obj).doubleValue();
                }
                sliderManagerInterface3.setMinimumValue(t, d2);
                return;
            case '\f':
                ((SliderManagerInterface) this.mViewManager).setMinimumTrackImage(t, (ReadableMap) obj);
                return;
            case '\r':
                ((SliderManagerInterface) this.mViewManager).setThumbTintColor(t, obj != null ? Integer.valueOf(((Double) obj).intValue()) : null);
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
