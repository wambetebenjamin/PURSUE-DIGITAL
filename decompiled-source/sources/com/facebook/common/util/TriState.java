package com.facebook.common.util;

import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public enum TriState {
    YES,
    NO,
    UNSET;

    public boolean isSet() {
        return this != UNSET;
    }

    public static TriState valueOf(boolean z) {
        return z ? YES : NO;
    }

    public static TriState valueOf(Boolean bool) {
        return bool != null ? valueOf(bool.booleanValue()) : UNSET;
    }

    /* renamed from: com.facebook.common.util.TriState$1  reason: invalid class name */
    /* loaded from: classes8.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$common$util$TriState;

        static {
            int[] iArr = new int[TriState.values().length];
            $SwitchMap$com$facebook$common$util$TriState = iArr;
            try {
                iArr[TriState.YES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$common$util$TriState[TriState.NO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$common$util$TriState[TriState.UNSET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public boolean asBoolean() {
        int i2 = AnonymousClass1.$SwitchMap$com$facebook$common$util$TriState[ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    throw new IllegalStateException("No boolean equivalent for UNSET");
                }
                throw new IllegalStateException("Unrecognized TriState value: " + this);
            }
            return false;
        }
        return true;
    }

    public boolean asBoolean(boolean z) {
        int i2 = AnonymousClass1.$SwitchMap$com$facebook$common$util$TriState[ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    return z;
                }
                throw new IllegalStateException("Unrecognized TriState value: " + this);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public Boolean asBooleanObject() {
        int i2 = AnonymousClass1.$SwitchMap$com$facebook$common$util$TriState[ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    return null;
                }
                throw new IllegalStateException("Unrecognized TriState value: " + this);
            }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public int getDbValue() {
        int i2 = AnonymousClass1.$SwitchMap$com$facebook$common$util$TriState[ordinal()];
        int i3 = 1;
        if (i2 != 1) {
            i3 = 2;
            if (i2 != 2) {
                return 3;
            }
        }
        return i3;
    }

    public static TriState fromDbValue(int i2) {
        if (i2 != 1) {
            if (i2 == 2) {
                return NO;
            }
            return UNSET;
        }
        return YES;
    }
}
