package androidx.constraintlayout.widget;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.react.uimanager.ViewProps;
/* loaded from: classes.dex */
public class ConstraintProperties {
    public static final int BASELINE = 5;
    public static final int BOTTOM = 4;
    public static final int END = 7;
    public static final int LEFT = 1;
    public static final int MATCH_CONSTRAINT = 0;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    public static final int PARENT_ID = 0;
    public static final int RIGHT = 2;
    public static final int START = 6;
    public static final int TOP = 3;
    public static final int UNSET = -1;
    public static final int WRAP_CONTENT = -2;
    ConstraintLayout.LayoutParams mParams;
    View mView;

    private String sideToString(int i2) {
        switch (i2) {
            case 1:
                return "left";
            case 2:
                return ViewProps.RIGHT;
            case 3:
                return "top";
            case 4:
                return ViewProps.BOTTOM;
            case 5:
                return "baseline";
            case 6:
                return "start";
            case 7:
                return "end";
            default:
                return "undefined";
        }
    }

    public void apply() {
    }

    public ConstraintProperties scaleY(float f2) {
        return this;
    }

    public ConstraintProperties center(int i2, int i3, int i4, int i5, int i6, int i7, float f2) {
        if (i4 >= 0) {
            if (i7 >= 0) {
                if (f2 <= 0.0f || f2 > 1.0f) {
                    throw new IllegalArgumentException("bias must be between 0 and 1 inclusive");
                }
                if (i3 == 1 || i3 == 2) {
                    connect(1, i2, i3, i4);
                    connect(2, i5, i6, i7);
                    this.mParams.horizontalBias = f2;
                } else if (i3 == 6 || i3 == 7) {
                    connect(6, i2, i3, i4);
                    connect(7, i5, i6, i7);
                    this.mParams.horizontalBias = f2;
                } else {
                    connect(3, i2, i3, i4);
                    connect(4, i5, i6, i7);
                    this.mParams.verticalBias = f2;
                }
                return this;
            }
            throw new IllegalArgumentException("margin must be > 0");
        }
        throw new IllegalArgumentException("margin must be > 0");
    }

    public ConstraintProperties centerHorizontally(int i2, int i3, int i4, int i5, int i6, int i7, float f2) {
        connect(1, i2, i3, i4);
        connect(2, i5, i6, i7);
        this.mParams.horizontalBias = f2;
        return this;
    }

    public ConstraintProperties centerHorizontallyRtl(int i2, int i3, int i4, int i5, int i6, int i7, float f2) {
        connect(6, i2, i3, i4);
        connect(7, i5, i6, i7);
        this.mParams.horizontalBias = f2;
        return this;
    }

    public ConstraintProperties centerVertically(int i2, int i3, int i4, int i5, int i6, int i7, float f2) {
        connect(3, i2, i3, i4);
        connect(4, i5, i6, i7);
        this.mParams.verticalBias = f2;
        return this;
    }

    public ConstraintProperties centerHorizontally(int i2) {
        if (i2 == 0) {
            center(0, 1, 0, 0, 2, 0, 0.5f);
        } else {
            center(i2, 2, 0, i2, 1, 0, 0.5f);
        }
        return this;
    }

    public ConstraintProperties centerHorizontallyRtl(int i2) {
        if (i2 == 0) {
            center(0, 6, 0, 0, 7, 0, 0.5f);
        } else {
            center(i2, 7, 0, i2, 6, 0, 0.5f);
        }
        return this;
    }

    public ConstraintProperties centerVertically(int i2) {
        if (i2 == 0) {
            center(0, 3, 0, 0, 4, 0, 0.5f);
        } else {
            center(i2, 4, 0, i2, 3, 0, 0.5f);
        }
        return this;
    }

    public ConstraintProperties removeConstraints(int i2) {
        switch (i2) {
            case 1:
                this.mParams.leftToRight = -1;
                this.mParams.leftToLeft = -1;
                this.mParams.leftMargin = -1;
                this.mParams.goneLeftMargin = -1;
                break;
            case 2:
                this.mParams.rightToRight = -1;
                this.mParams.rightToLeft = -1;
                this.mParams.rightMargin = -1;
                this.mParams.goneRightMargin = -1;
                break;
            case 3:
                this.mParams.topToBottom = -1;
                this.mParams.topToTop = -1;
                this.mParams.topMargin = -1;
                this.mParams.goneTopMargin = -1;
                break;
            case 4:
                this.mParams.bottomToTop = -1;
                this.mParams.bottomToBottom = -1;
                this.mParams.bottomMargin = -1;
                this.mParams.goneBottomMargin = -1;
                break;
            case 5:
                this.mParams.baselineToBaseline = -1;
                break;
            case 6:
                this.mParams.startToEnd = -1;
                this.mParams.startToStart = -1;
                this.mParams.setMarginStart(-1);
                this.mParams.goneStartMargin = -1;
                break;
            case 7:
                this.mParams.endToStart = -1;
                this.mParams.endToEnd = -1;
                this.mParams.setMarginEnd(-1);
                this.mParams.goneEndMargin = -1;
                break;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
        return this;
    }

    public ConstraintProperties margin(int i2, int i3) {
        switch (i2) {
            case 1:
                this.mParams.leftMargin = i3;
                break;
            case 2:
                this.mParams.rightMargin = i3;
                break;
            case 3:
                this.mParams.topMargin = i3;
                break;
            case 4:
                this.mParams.bottomMargin = i3;
                break;
            case 5:
                throw new IllegalArgumentException("baseline does not support margins");
            case 6:
                this.mParams.setMarginStart(i3);
                break;
            case 7:
                this.mParams.setMarginEnd(i3);
                break;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
        return this;
    }

    public ConstraintProperties goneMargin(int i2, int i3) {
        switch (i2) {
            case 1:
                this.mParams.goneLeftMargin = i3;
                break;
            case 2:
                this.mParams.goneRightMargin = i3;
                break;
            case 3:
                this.mParams.goneTopMargin = i3;
                break;
            case 4:
                this.mParams.goneBottomMargin = i3;
                break;
            case 5:
                throw new IllegalArgumentException("baseline does not support margins");
            case 6:
                this.mParams.goneStartMargin = i3;
                break;
            case 7:
                this.mParams.goneEndMargin = i3;
                break;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
        return this;
    }

    public ConstraintProperties horizontalBias(float f2) {
        this.mParams.horizontalBias = f2;
        return this;
    }

    public ConstraintProperties verticalBias(float f2) {
        this.mParams.verticalBias = f2;
        return this;
    }

    public ConstraintProperties dimensionRatio(String str) {
        this.mParams.dimensionRatio = str;
        return this;
    }

    public ConstraintProperties visibility(int i2) {
        this.mView.setVisibility(i2);
        return this;
    }

    public ConstraintProperties alpha(float f2) {
        this.mView.setAlpha(f2);
        return this;
    }

    public ConstraintProperties elevation(float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.mView.setElevation(f2);
        }
        return this;
    }

    public ConstraintProperties rotation(float f2) {
        this.mView.setRotation(f2);
        return this;
    }

    public ConstraintProperties rotationX(float f2) {
        this.mView.setRotationX(f2);
        return this;
    }

    public ConstraintProperties rotationY(float f2) {
        this.mView.setRotationY(f2);
        return this;
    }

    public ConstraintProperties scaleX(float f2) {
        this.mView.setScaleY(f2);
        return this;
    }

    public ConstraintProperties transformPivotX(float f2) {
        this.mView.setPivotX(f2);
        return this;
    }

    public ConstraintProperties transformPivotY(float f2) {
        this.mView.setPivotY(f2);
        return this;
    }

    public ConstraintProperties transformPivot(float f2, float f3) {
        this.mView.setPivotX(f2);
        this.mView.setPivotY(f3);
        return this;
    }

    public ConstraintProperties translationX(float f2) {
        this.mView.setTranslationX(f2);
        return this;
    }

    public ConstraintProperties translationY(float f2) {
        this.mView.setTranslationY(f2);
        return this;
    }

    public ConstraintProperties translation(float f2, float f3) {
        this.mView.setTranslationX(f2);
        this.mView.setTranslationY(f3);
        return this;
    }

    public ConstraintProperties translationZ(float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.mView.setTranslationZ(f2);
        }
        return this;
    }

    public ConstraintProperties constrainHeight(int i2) {
        this.mParams.height = i2;
        return this;
    }

    public ConstraintProperties constrainWidth(int i2) {
        this.mParams.width = i2;
        return this;
    }

    public ConstraintProperties constrainMaxHeight(int i2) {
        this.mParams.matchConstraintMaxHeight = i2;
        return this;
    }

    public ConstraintProperties constrainMaxWidth(int i2) {
        this.mParams.matchConstraintMaxWidth = i2;
        return this;
    }

    public ConstraintProperties constrainMinHeight(int i2) {
        this.mParams.matchConstraintMinHeight = i2;
        return this;
    }

    public ConstraintProperties constrainMinWidth(int i2) {
        this.mParams.matchConstraintMinWidth = i2;
        return this;
    }

    public ConstraintProperties constrainDefaultHeight(int i2) {
        this.mParams.matchConstraintDefaultHeight = i2;
        return this;
    }

    public ConstraintProperties constrainDefaultWidth(int i2) {
        this.mParams.matchConstraintDefaultWidth = i2;
        return this;
    }

    public ConstraintProperties horizontalWeight(float f2) {
        this.mParams.horizontalWeight = f2;
        return this;
    }

    public ConstraintProperties verticalWeight(float f2) {
        this.mParams.verticalWeight = f2;
        return this;
    }

    public ConstraintProperties horizontalChainStyle(int i2) {
        this.mParams.horizontalChainStyle = i2;
        return this;
    }

    public ConstraintProperties verticalChainStyle(int i2) {
        this.mParams.verticalChainStyle = i2;
        return this;
    }

    public ConstraintProperties addToHorizontalChain(int i2, int i3) {
        connect(1, i2, i2 == 0 ? 1 : 2, 0);
        connect(2, i3, i3 == 0 ? 2 : 1, 0);
        if (i2 != 0) {
            new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i2)).connect(2, this.mView.getId(), 1, 0);
        }
        if (i3 != 0) {
            new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i3)).connect(1, this.mView.getId(), 2, 0);
        }
        return this;
    }

    public ConstraintProperties addToHorizontalChainRTL(int i2, int i3) {
        connect(6, i2, i2 == 0 ? 6 : 7, 0);
        connect(7, i3, i3 == 0 ? 7 : 6, 0);
        if (i2 != 0) {
            new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i2)).connect(7, this.mView.getId(), 6, 0);
        }
        if (i3 != 0) {
            new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i3)).connect(6, this.mView.getId(), 7, 0);
        }
        return this;
    }

    public ConstraintProperties addToVerticalChain(int i2, int i3) {
        connect(3, i2, i2 == 0 ? 3 : 4, 0);
        connect(4, i3, i3 == 0 ? 4 : 3, 0);
        if (i2 != 0) {
            new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i2)).connect(4, this.mView.getId(), 3, 0);
        }
        if (i3 != 0) {
            new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i3)).connect(3, this.mView.getId(), 4, 0);
        }
        return this;
    }

    public ConstraintProperties removeFromVerticalChain() {
        int i2 = this.mParams.topToBottom;
        int i3 = this.mParams.bottomToTop;
        if (i2 != -1 || i3 != -1) {
            ConstraintProperties constraintProperties = new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i2));
            ConstraintProperties constraintProperties2 = new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i3));
            ConstraintLayout.LayoutParams layoutParams = this.mParams;
            if (i2 != -1 && i3 != -1) {
                constraintProperties.connect(4, i3, 3, 0);
                constraintProperties2.connect(3, i2, 4, 0);
            } else if (i2 != -1 || i3 != -1) {
                int i4 = layoutParams.bottomToBottom;
                ConstraintLayout.LayoutParams layoutParams2 = this.mParams;
                if (i4 != -1) {
                    constraintProperties.connect(4, layoutParams2.bottomToBottom, 4, 0);
                } else {
                    int i5 = layoutParams2.topToTop;
                    ConstraintLayout.LayoutParams layoutParams3 = this.mParams;
                    if (i5 != -1) {
                        constraintProperties2.connect(3, layoutParams3.topToTop, 3, 0);
                    }
                }
            }
        }
        removeConstraints(3);
        removeConstraints(4);
        return this;
    }

    public ConstraintProperties removeFromHorizontalChain() {
        int i2 = this.mParams.leftToRight;
        int i3 = this.mParams.rightToLeft;
        ConstraintLayout.LayoutParams layoutParams = this.mParams;
        if (i2 != -1 || i3 != -1) {
            ConstraintProperties constraintProperties = new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i2));
            ConstraintProperties constraintProperties2 = new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i3));
            ConstraintLayout.LayoutParams layoutParams2 = this.mParams;
            if (i2 != -1 && i3 != -1) {
                constraintProperties.connect(2, i3, 1, 0);
                constraintProperties2.connect(1, i2, 2, 0);
            } else if (i2 != -1 || i3 != -1) {
                int i4 = layoutParams2.rightToRight;
                ConstraintLayout.LayoutParams layoutParams3 = this.mParams;
                if (i4 != -1) {
                    constraintProperties.connect(2, layoutParams3.rightToRight, 2, 0);
                } else {
                    int i5 = layoutParams3.leftToLeft;
                    ConstraintLayout.LayoutParams layoutParams4 = this.mParams;
                    if (i5 != -1) {
                        constraintProperties2.connect(1, layoutParams4.leftToLeft, 1, 0);
                    }
                }
            }
            removeConstraints(1);
            removeConstraints(2);
        } else {
            int i6 = layoutParams.startToEnd;
            int i7 = this.mParams.endToStart;
            if (i6 != -1 || i7 != -1) {
                ConstraintProperties constraintProperties3 = new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i6));
                ConstraintProperties constraintProperties4 = new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i7));
                ConstraintLayout.LayoutParams layoutParams5 = this.mParams;
                if (i6 != -1 && i7 != -1) {
                    constraintProperties3.connect(7, i7, 6, 0);
                    constraintProperties4.connect(6, i2, 7, 0);
                } else if (i2 != -1 || i7 != -1) {
                    int i8 = layoutParams5.rightToRight;
                    ConstraintLayout.LayoutParams layoutParams6 = this.mParams;
                    if (i8 != -1) {
                        constraintProperties3.connect(7, layoutParams6.rightToRight, 7, 0);
                    } else {
                        int i9 = layoutParams6.leftToLeft;
                        ConstraintLayout.LayoutParams layoutParams7 = this.mParams;
                        if (i9 != -1) {
                            constraintProperties4.connect(6, layoutParams7.leftToLeft, 6, 0);
                        }
                    }
                }
            }
            removeConstraints(6);
            removeConstraints(7);
        }
        return this;
    }

    public ConstraintProperties connect(int i2, int i3, int i4, int i5) {
        switch (i2) {
            case 1:
                if (i4 == 1) {
                    this.mParams.leftToLeft = i3;
                    this.mParams.leftToRight = -1;
                } else if (i4 == 2) {
                    this.mParams.leftToRight = i3;
                    this.mParams.leftToLeft = -1;
                } else {
                    throw new IllegalArgumentException("Left to " + sideToString(i4) + " undefined");
                }
                this.mParams.leftMargin = i5;
                break;
            case 2:
                if (i4 == 1) {
                    this.mParams.rightToLeft = i3;
                    this.mParams.rightToRight = -1;
                } else if (i4 == 2) {
                    this.mParams.rightToRight = i3;
                    this.mParams.rightToLeft = -1;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                }
                this.mParams.rightMargin = i5;
                break;
            case 3:
                if (i4 == 3) {
                    this.mParams.topToTop = i3;
                    this.mParams.topToBottom = -1;
                    this.mParams.baselineToBaseline = -1;
                } else if (i4 == 4) {
                    this.mParams.topToBottom = i3;
                    this.mParams.topToTop = -1;
                    this.mParams.baselineToBaseline = -1;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                }
                this.mParams.topMargin = i5;
                break;
            case 4:
                if (i4 == 4) {
                    this.mParams.bottomToBottom = i3;
                    this.mParams.bottomToTop = -1;
                    this.mParams.baselineToBaseline = -1;
                } else if (i4 == 3) {
                    this.mParams.bottomToTop = i3;
                    this.mParams.bottomToBottom = -1;
                    this.mParams.baselineToBaseline = -1;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                }
                this.mParams.bottomMargin = i5;
                break;
            case 5:
                if (i4 == 5) {
                    this.mParams.baselineToBaseline = i3;
                    this.mParams.bottomToBottom = -1;
                    this.mParams.bottomToTop = -1;
                    this.mParams.topToTop = -1;
                    this.mParams.topToBottom = -1;
                    break;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                }
            case 6:
                if (i4 == 6) {
                    this.mParams.startToStart = i3;
                    this.mParams.startToEnd = -1;
                } else if (i4 == 7) {
                    this.mParams.startToEnd = i3;
                    this.mParams.startToStart = -1;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                }
                if (Build.VERSION.SDK_INT >= 17) {
                    this.mParams.setMarginStart(i5);
                    break;
                }
                break;
            case 7:
                if (i4 == 7) {
                    this.mParams.endToEnd = i3;
                    this.mParams.endToStart = -1;
                } else if (i4 == 6) {
                    this.mParams.endToStart = i3;
                    this.mParams.endToEnd = -1;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                }
                if (Build.VERSION.SDK_INT >= 17) {
                    this.mParams.setMarginEnd(i5);
                    break;
                }
                break;
            default:
                throw new IllegalArgumentException(sideToString(i2) + " to " + sideToString(i4) + " unknown");
        }
        return this;
    }

    public ConstraintProperties(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            this.mParams = (ConstraintLayout.LayoutParams) layoutParams;
            this.mView = view;
            return;
        }
        throw new RuntimeException("Only children of ConstraintLayout.LayoutParams supported");
    }
}
