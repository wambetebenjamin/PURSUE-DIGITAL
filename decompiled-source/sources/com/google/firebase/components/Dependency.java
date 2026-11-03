package com.google.firebase.components;

import com.google.android.gms.fido.fido2.api.common.DevicePublicKeyStringDef;
import gsdk.impl.account.toutiao.dh;
/* loaded from: classes9.dex */
public final class Dependency {
    private final Qualified<?> anInterface;
    private final int injection;
    private final int type;

    private Dependency(Class<?> cls, int i2, int i3) {
        this(Qualified.unqualified(cls), i2, i3);
    }

    private Dependency(Qualified<?> qualified, int i2, int i3) {
        this.anInterface = (Qualified) Preconditions.checkNotNull(qualified, "Null dependency anInterface.");
        this.type = i2;
        this.injection = i3;
    }

    @Deprecated
    public static Dependency optional(Class<?> cls) {
        return new Dependency(cls, 0, 0);
    }

    public static Dependency deferred(Class<?> cls) {
        return new Dependency(cls, 0, 2);
    }

    public static Dependency deferred(Qualified<?> qualified) {
        return new Dependency(qualified, 0, 2);
    }

    public static Dependency required(Class<?> cls) {
        return new Dependency(cls, 1, 0);
    }

    public static Dependency required(Qualified<?> qualified) {
        return new Dependency(qualified, 1, 0);
    }

    public static Dependency setOf(Class<?> cls) {
        return new Dependency(cls, 2, 0);
    }

    public static Dependency setOf(Qualified<?> qualified) {
        return new Dependency(qualified, 2, 0);
    }

    public static Dependency optionalProvider(Class<?> cls) {
        return new Dependency(cls, 0, 1);
    }

    public static Dependency optionalProvider(Qualified<?> qualified) {
        return new Dependency(qualified, 0, 1);
    }

    public static Dependency requiredProvider(Class<?> cls) {
        return new Dependency(cls, 1, 1);
    }

    public static Dependency requiredProvider(Qualified<?> qualified) {
        return new Dependency(qualified, 1, 1);
    }

    public static Dependency setOfProvider(Class<?> cls) {
        return new Dependency(cls, 2, 1);
    }

    public static Dependency setOfProvider(Qualified<?> qualified) {
        return new Dependency(qualified, 2, 1);
    }

    public Qualified<?> getInterface() {
        return this.anInterface;
    }

    public boolean isRequired() {
        return this.type == 1;
    }

    public boolean isSet() {
        return this.type == 2;
    }

    public boolean isDirectInjection() {
        return this.injection == 0;
    }

    public boolean isDeferred() {
        return this.injection == 2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Dependency) {
            Dependency dependency = (Dependency) obj;
            return this.anInterface.equals(dependency.anInterface) && this.type == dependency.type && this.injection == dependency.injection;
        }
        return false;
    }

    public int hashCode() {
        return ((((this.anInterface.hashCode() ^ 1000003) * 1000003) ^ this.type) * 1000003) ^ this.injection;
    }

    public String toString() {
        StringBuilder append = new StringBuilder("Dependency{anInterface=").append(this.anInterface).append(", type=");
        int i2 = this.type;
        return append.append(i2 == 1 ? "required" : i2 == 0 ? "optional" : dh.z).append(", injection=").append(describeInjection(this.injection)).append("}").toString();
    }

    private static String describeInjection(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 2) {
                    return "deferred";
                }
                throw new AssertionError("Unsupported injection: " + i2);
            }
            return "provider";
        }
        return DevicePublicKeyStringDef.DIRECT;
    }
}
