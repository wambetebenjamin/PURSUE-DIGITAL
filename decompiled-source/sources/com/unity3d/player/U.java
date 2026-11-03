package com.unity3d.player;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Insets;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
/* loaded from: classes10.dex */
final class U extends Dialog implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    protected Context f18900a;

    /* renamed from: b  reason: collision with root package name */
    protected UnityPlayer f18901b;

    /* renamed from: c  reason: collision with root package name */
    protected P f18902c;

    /* renamed from: d  reason: collision with root package name */
    protected S f18903d;

    /* renamed from: e  reason: collision with root package name */
    private C f18904e;

    public U(Context context, UnityPlayer unityPlayer) {
        super(context);
        this.f18902c = null;
        this.f18903d = null;
        this.f18904e = null;
        this.f18900a = context;
        this.f18901b = unityPlayer;
    }

    public final Rect a() {
        Rect rect = new Rect();
        this.f18901b.getWindowVisibleDisplayFrame(rect);
        int[] iArr = new int[2];
        this.f18901b.getLocationOnScreen(iArr);
        Point point = new Point(rect.left - iArr[0], rect.height() - this.f18902c.getHeight());
        Point point2 = new Point();
        getWindow().getWindowManager().getDefaultDisplay().getSize(point2);
        int height = this.f18901b.getHeight();
        int i2 = height - point2.y;
        int i3 = height - point.y;
        int height2 = this.f18902c.getHeight() + i2;
        UnityPlayer unityPlayer = this.f18901b;
        if (i3 != height2) {
            unityPlayer.reportSoftInputIsVisible(true);
        } else {
            unityPlayer.reportSoftInputIsVisible(false);
        }
        return new Rect(point.x, point.y, this.f18902c.getWidth(), i3);
    }

    public final void a(S s, boolean z, boolean z2) {
        this.f18903d = s;
        Window window = getWindow();
        window.requestFeature(1);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.x = 0;
        attributes.y = 0;
        window.setAttributes(attributes);
        b();
        this.f18902c = createSoftInputView(this.f18903d.f18883c);
        window.setLayout(-1, -2);
        window.clearFlags(2);
        window.clearFlags(DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25);
        window.clearFlags(DownloadExpSwitchCode.BACK_BUGFIX_SIGBUS);
        if (!z2) {
            window.addFlags(32);
            window.addFlags(262144);
        }
        a(z);
        getWindow().setSoftInputMode(5);
        this.f18904e = D.a(this, 1000000, new T(this));
    }

    public final void a(boolean z) {
        P p = this.f18902c;
        if (z) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) p.f18867b.getLayoutParams();
            layoutParams.height = 1;
            p.f18867b.setLayoutParams(layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) p.f18866a.getLayoutParams();
            layoutParams2.height = 1;
            p.f18866a.setLayoutParams(layoutParams2);
            Rect rect = p.f18870e;
            p.setPadding(rect.left, rect.top, rect.right, rect.bottom);
            p.setVisibility(4);
        } else {
            p.setVisibility(0);
            Rect rect2 = p.f18869d;
            p.setPadding(rect2.left, rect2.top, rect2.right, rect2.bottom);
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) p.f18867b.getLayoutParams();
            layoutParams3.height = -2;
            p.f18867b.setLayoutParams(layoutParams3);
            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) p.f18866a.getLayoutParams();
            layoutParams4.height = -2;
            p.f18866a.setLayoutParams(layoutParams4);
        }
        p.invalidate();
        p.requestLayout();
    }

    public final void b() {
        ColorDrawable colorDrawable = new ColorDrawable(0);
        if (!PlatformSupport.VANILLA_ICE_CREAM_SUPPORT) {
            getWindow().setBackgroundDrawable(colorDrawable);
            return;
        }
        Insets insets = this.f18901b.getActivity().getWindow().getDecorView().getRootWindowInsets().getInsets(WindowInsets.Type.displayCutout());
        getWindow().setBackgroundDrawable(new InsetDrawable((Drawable) colorDrawable, insets.left, insets.top, insets.right, 0));
    }

    protected P createSoftInputView(EditText editText) {
        P p = new P(this.f18900a, editText);
        p.f18866a.setOnClickListener(this);
        setContentView(p);
        return p;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.f18903d.d() || !(motionEvent.getAction() == 4 || this.f18903d.f18884d)) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.app.Dialog
    public final void onBackPressed() {
        Runnable runnable;
        C c2 = this.f18904e;
        if (c2 != null) {
            com.unity3d.player.a.c cVar = c2.f18805a;
            if (!(cVar != null) || (runnable = ((B) cVar).f18803a) == null) {
                return;
            }
            runnable.run();
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        S s = this.f18903d;
        s.a(s.b(), false);
    }

    @Override // android.app.Dialog
    public final void onStop() {
        C c2 = this.f18904e;
        if (c2 != null) {
            c2.unregisterOnBackPressedCallback();
            this.f18904e = null;
        }
        super.onStop();
    }
}
