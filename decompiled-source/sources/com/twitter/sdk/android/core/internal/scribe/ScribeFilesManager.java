package com.twitter.sdk.android.core.internal.scribe;

import android.content.Context;
import com.twitter.sdk.android.core.internal.CurrentTimeProvider;
import java.io.IOException;
import java.util.UUID;
/* loaded from: classes10.dex */
class ScribeFilesManager extends EventsFilesManager<ScribeEvent> {
    static final String FILE_EXTENSION = ".tap";
    static final String FILE_PREFIX = "se";

    /* JADX INFO: Access modifiers changed from: package-private */
    public ScribeFilesManager(Context context, EventTransform<ScribeEvent> eventTransform, CurrentTimeProvider currentTimeProvider, QueueFileEventStorage queueFileEventStorage, int i2) throws IOException {
        super(context, eventTransform, currentTimeProvider, queueFileEventStorage, i2);
    }

    @Override // com.twitter.sdk.android.core.internal.scribe.EventsFilesManager
    protected String generateUniqueRollOverFileName() {
        return FILE_PREFIX + "_" + UUID.randomUUID().toString() + "_" + this.currentTimeProvider.getCurrentTimeMillis() + FILE_EXTENSION;
    }
}
