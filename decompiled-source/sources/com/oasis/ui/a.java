package com.oasis.ui;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;
/* loaded from: classes9.dex */
public final class a implements e {
    private static int[] a(Context context, Point point) {
        Rect b2 = g.b(context);
        if (b2.width() + b2.height() < point.x + point.y) {
            return null;
        }
        return g.a(g.a(context));
    }

    @Override // com.oasis.ui.e
    public final boolean a() {
        return Build.VERSION.SDK_INT >= 28;
    }

    @Override // com.oasis.ui.e
    public final boolean a(Context context) {
        String str = Build.MODEL;
        return str.contains("DE106") || str.contains("XT1943-1");
    }

    @Override // com.oasis.ui.e
    public final boolean b() {
        return false;
    }

    @Override // com.oasis.ui.e
    public final int[] b(Context context) {
        Point point;
        String str = Build.MODEL;
        if (str.contains("DE106")) {
            point = new Point(2242, DownloadErrorCode.ERROR_TTNET_NOT_MODIFIED);
        } else if (!str.contains("XT1943-1")) {
            return null;
        } else {
            point = new Point(2246, DownloadErrorCode.ERROR_TTNET_NOT_MODIFIED);
        }
        return a(context, point);
    }
}
