package com.android.billingclient.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes.dex */
public final class InAppMessageParams {
    private final ArrayList zza;

    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    /* loaded from: classes.dex */
    public static final class Builder {
        private final Set zza = new HashSet();

        public Builder addAllInAppMessageCategoriesToShow() {
            this.zza.add(2);
            return this;
        }

        public Builder addInAppMessageCategoryToShow(int i2) {
            this.zza.add(Integer.valueOf(i2));
            return this;
        }

        public InAppMessageParams build() {
            return new InAppMessageParams(this.zza, null);
        }
    }

    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface InAppMessageCategoryId {
        public static final int TRANSACTIONAL = 2;
        public static final int UNKNOWN_IN_APP_MESSAGE_CATEGORY_ID = 0;
    }

    /* synthetic */ InAppMessageParams(Set set, zzdm zzdmVar) {
        this.zza = new ArrayList(Collections.unmodifiableList(new ArrayList(set)));
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ArrayList zza() {
        return this.zza;
    }
}
