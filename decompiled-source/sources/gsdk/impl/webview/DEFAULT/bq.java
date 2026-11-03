package gsdk.impl.webview.DEFAULT;

import android.os.Environment;
import android.os.StatFs;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
/* compiled from: DeviceRomUtils.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/bytedance/ttgame/module/webview/util/DeviceRomUtils;", "", "()V", "Companion", "webview_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class bq {

    /* renamed from: a  reason: collision with root package name */
    public static final a f21007a = new a(null);

    /* compiled from: DeviceRomUtils.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004J\b\u0010\b\u001a\u0004\u0018\u00010\u0004¨\u0006\t"}, d2 = {"Lcom/bytedance/ttgame/module/webview/util/DeviceRomUtils$Companion;", "", "()V", "formatFileSize", "", com.bytedance.apm.constant.i.PERF_DISK_FILE_SIZE, "", "getRomAvailableSize", "getRomTotalSize", "webview_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes10.dex */
    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private a() {
        }

        public final String a() {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return a(statFs.getBlockSize() * statFs.getBlockCount());
        }

        public final String b() {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return a(statFs.getBlockSize() * statFs.getAvailableBlocks());
        }

        private final String a(long j2) {
            if (j2 < 1024) {
                return new StringBuilder().append(j2).append('B').toString();
            }
            if (j2 < 1048576) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format(Locale.US, "%.2fKB", Arrays.copyOf(new Object[]{Double.valueOf(j2 / 1024.0d)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                return format;
            } else if (j2 < 1073741824) {
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                String format2 = String.format(Locale.US, "%.2fMB", Arrays.copyOf(new Object[]{Double.valueOf(j2 / 1048576.0d)}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                return format2;
            } else {
                StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                String format3 = String.format(Locale.US, "%.2fGB", Arrays.copyOf(new Object[]{Double.valueOf(j2 / 1.073741824E9d)}, 1));
                Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
                return format3;
            }
        }
    }
}
