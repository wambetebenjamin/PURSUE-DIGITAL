package com.oasis.android;

import android.app.AlertDialog;
import android.content.DialogInterface;
/* loaded from: classes9.dex */
public final class DialogManager {

    /* loaded from: classes9.dex */
    class a implements DialogInterface.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ DialogListener f17368a;

        a(DialogListener dialogListener) {
            this.f17368a = dialogListener;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            this.f17368a.onClick();
        }
    }

    /* loaded from: classes9.dex */
    class b implements DialogInterface.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ DialogListener f17369a;

        b(DialogListener dialogListener) {
            this.f17369a = dialogListener;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            this.f17369a.onClick();
        }
    }

    /* loaded from: classes9.dex */
    class c implements DialogInterface.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ DialogListener f17370a;

        c(DialogListener dialogListener) {
            this.f17370a = dialogListener;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            this.f17370a.onClick();
        }
    }

    public static void showConfirm(String str, String str2, String str3, DialogListener dialogListener, String str4, DialogListener dialogListener2) {
        b bVar = new b(dialogListener);
        c cVar = new c(dialogListener2);
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityManager.getActivity(), 5);
        builder.setTitle(str);
        builder.setMessage(str2);
        builder.setPositiveButton(str3, bVar);
        builder.setNegativeButton(str4, cVar);
        builder.create().show();
    }

    public static void showMessage(String str, String str2, String str3, DialogListener dialogListener) {
        a aVar = new a(dialogListener);
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityManager.getActivity(), 5);
        builder.setTitle(str);
        builder.setMessage(str2);
        builder.setNeutralButton(str3, aVar);
        builder.create().show();
    }
}
