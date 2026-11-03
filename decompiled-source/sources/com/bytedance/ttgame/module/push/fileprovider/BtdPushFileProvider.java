package com.bytedance.ttgame.module.push.fileprovider;

import android.content.Context;
import android.net.Uri;
import androidx.core.content.FileProvider;
import java.io.File;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: BtdPushFileProvider.kt */
/* loaded from: classes6.dex */
public final class BtdPushFileProvider extends FileProvider {

    /* renamed from: a  reason: collision with root package name */
    public static final a f14601a = new a(null);

    /* compiled from: BtdPushFileProvider.kt */
    /* loaded from: classes6.dex */
    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private a() {
        }

        public final Uri a(Context context, File file) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(file, "file");
            Uri uriForFile = FileProvider.getUriForFile(context, context.getPackageName() + ".btdpushfileprovider", file);
            Intrinsics.checkNotNullExpressionValue(uriForFile, "getUriForFile(context, authority, file)");
            return uriForFile;
        }
    }
}
