package com.ttnet.org.chromium.net;

import java.util.concurrent.RejectedExecutionException;
/* loaded from: classes9.dex */
public final class InlineExecutionProhibitedException extends RejectedExecutionException {
    public InlineExecutionProhibitedException() {
        super("Inline execution is prohibited for this request");
    }
}
