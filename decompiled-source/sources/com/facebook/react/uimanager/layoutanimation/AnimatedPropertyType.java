package com.facebook.react.uimanager.layoutanimation;

import com.facebook.react.uimanager.ViewProps;
/* loaded from: classes8.dex */
enum AnimatedPropertyType {
    OPACITY,
    SCALE_X,
    SCALE_Y,
    SCALE_XY;

    public static AnimatedPropertyType fromString(String str) {
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1267206133:
                if (str.equals(ViewProps.OPACITY)) {
                    c2 = 0;
                    break;
                }
                break;
            case -908189618:
                if (str.equals(ViewProps.SCALE_X)) {
                    c2 = 1;
                    break;
                }
                break;
            case -908189617:
                if (str.equals(ViewProps.SCALE_Y)) {
                    c2 = 2;
                    break;
                }
                break;
            case 1910893003:
                if (str.equals("scaleXY")) {
                    c2 = 3;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return OPACITY;
            case 1:
                return SCALE_X;
            case 2:
                return SCALE_Y;
            case 3:
                return SCALE_XY;
            default:
                throw new IllegalArgumentException("Unsupported animated property: " + str);
        }
    }
}
