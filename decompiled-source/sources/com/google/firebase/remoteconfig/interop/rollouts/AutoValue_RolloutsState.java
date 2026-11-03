package com.google.firebase.remoteconfig.interop.rollouts;

import java.util.Objects;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class AutoValue_RolloutsState extends RolloutsState {
    private final Set<RolloutAssignment> rolloutAssignments;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_RolloutsState(Set<RolloutAssignment> set) {
        Objects.requireNonNull(set, "Null rolloutAssignments");
        this.rolloutAssignments = set;
    }

    @Override // com.google.firebase.remoteconfig.interop.rollouts.RolloutsState
    public Set<RolloutAssignment> getRolloutAssignments() {
        return this.rolloutAssignments;
    }

    public String toString() {
        return "RolloutsState{rolloutAssignments=" + this.rolloutAssignments + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RolloutsState) {
            return this.rolloutAssignments.equals(((RolloutsState) obj).getRolloutAssignments());
        }
        return false;
    }

    public int hashCode() {
        return this.rolloutAssignments.hashCode() ^ 1000003;
    }
}
