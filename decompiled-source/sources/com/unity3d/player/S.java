package com.unity3d.player;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.EditText;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public abstract class S implements TextWatcher {

    /* renamed from: a  reason: collision with root package name */
    protected Context f18881a;

    /* renamed from: b  reason: collision with root package name */
    protected UnityPlayer f18882b;

    /* renamed from: c  reason: collision with root package name */
    protected EditText f18883c = createEditText(this);

    /* renamed from: d  reason: collision with root package name */
    protected boolean f18884d;

    /* renamed from: e  reason: collision with root package name */
    protected boolean f18885e;

    /* renamed from: f  reason: collision with root package name */
    protected F f18886f;

    /* renamed from: g  reason: collision with root package name */
    protected G f18887g;

    public S(Context context, UnityPlayer unityPlayer) {
        this.f18881a = context;
        this.f18882b = unityPlayer;
    }

    public final String a() {
        InputMethodSubtype currentInputMethodSubtype = ((InputMethodManager) this.f18881a.getSystemService("input_method")).getCurrentInputMethodSubtype();
        if (currentInputMethodSubtype == null) {
            return null;
        }
        String a2 = AbstractC0443y.a(currentInputMethodSubtype);
        if (a2 == null || a2.equals("")) {
            String mode = currentInputMethodSubtype.getMode();
            return mode + " " + currentInputMethodSubtype.getExtraValue();
        }
        return a2;
    }

    public void a(String str, int i2, boolean z, boolean z2, boolean z3, boolean z4, String str2, int i3, boolean z5, boolean z6) {
        this.f18885e = z6;
        setupTextInput(str, i2, z, z2, z3, z4, str2, i3);
        a(z5);
    }

    public final void a(String str, boolean z) {
        this.f18883c.setSelection(0, 0);
        this.f18882b.reportSoftInputStr(str, 1, z);
    }

    public abstract void a(boolean z);

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        this.f18882b.reportSoftInputStr(editable.toString(), 0, false);
        int selectionStart = this.f18883c.getSelectionStart();
        this.f18882b.reportSoftInputSelection(selectionStart, this.f18883c.getSelectionEnd() - selectionStart);
    }

    public final String b() {
        EditText editText = this.f18883c;
        if (editText == null) {
            return null;
        }
        return editText.getText().toString();
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    public abstract void c();

    protected abstract EditText createEditText(S s);

    public boolean d() {
        return this.f18885e;
    }

    public abstract void e();

    public final void f() {
        ((InputMethodManager) this.f18881a.getSystemService("input_method")).showSoftInput(this.f18883c, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void invokeOnClose() {
        G g2 = this.f18887g;
        if (g2 != null) {
            ((C0426p0) g2).a();
        }
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    protected void setupTextInput(String str, int i2, boolean z, boolean z2, boolean z3, boolean z4, String str2, int i3) {
        this.f18883c.setOnEditorActionListener(new Q(this));
        this.f18883c.setBackgroundColor(-1);
        this.f18883c.setImeOptions(6);
        this.f18883c.setText(str);
        this.f18883c.setHint(str2);
        this.f18883c.setHintTextColor(1627389952);
        EditText editText = this.f18883c;
        int i4 = (z ? 32768 : 524288) | (z2 ? 131072 : 0) | (z3 ? 128 : 0);
        if (i2 >= 0 && i2 <= 11) {
            int i5 = new int[]{1, 16385, 12290, 17, 2, 3, 8289, 33, 1, 16417, 17, 8194}[i2];
            if ((i5 & 2) != 0) {
                i4 = (z3 ? 16 : 0) | i5;
            } else {
                i4 |= i5;
            }
        }
        editText.setInputType(i4);
        this.f18883c.setImeOptions(DownloadExpSwitchCode.BACK_CLEAR_DATA);
        if (i3 > 0) {
            this.f18883c.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i3)});
        }
        this.f18883c.addTextChangedListener(this);
        EditText editText2 = this.f18883c;
        editText2.setSelection(editText2.getText().length());
        this.f18883c.setClickable(true);
    }
}
