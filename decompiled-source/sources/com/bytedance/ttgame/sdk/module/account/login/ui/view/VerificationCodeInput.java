package com.bytedance.ttgame.sdk.module.account.login.ui.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.ttgame.module.account.impl.R;
/* loaded from: classes3.dex */
public class VerificationCodeInput extends RelativeLayout {

    /* renamed from: c  reason: collision with root package name */
    private static final int f14974c = 6;

    /* renamed from: a  reason: collision with root package name */
    private PasteEditText f14975a;

    /* renamed from: b  reason: collision with root package name */
    private TextView[] f14976b;

    /* renamed from: d  reason: collision with root package name */
    private String f14977d;

    /* renamed from: e  reason: collision with root package name */
    private a f14978e;

    /* loaded from: classes3.dex */
    public interface a {
        void a();

        void a(String str);
    }

    public VerificationCodeInput(Context context) {
        this(context, null);
    }

    public VerificationCodeInput(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VerificationCodeInput(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        View.inflate(context, R.layout.view_verify_code, this);
        TextView[] textViewArr = new TextView[6];
        this.f14976b = textViewArr;
        textViewArr[0] = (TextView) findViewById(R.id.tv_0);
        this.f14976b[1] = (TextView) findViewById(R.id.tv_1);
        this.f14976b[2] = (TextView) findViewById(R.id.tv_2);
        this.f14976b[3] = (TextView) findViewById(R.id.tv_3);
        this.f14976b[4] = (TextView) findViewById(R.id.tv_4);
        this.f14976b[5] = (TextView) findViewById(R.id.tv_5);
        this.f14975a = (PasteEditText) findViewById(R.id.edit_text_view);
        a();
    }

    private void a() {
        this.f14975a.addTextChangedListener(new TextWatcher() { // from class: com.bytedance.ttgame.sdk.module.account.login.ui.view.VerificationCodeInput.1
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                VerificationCodeInput.this.f14975a.setSelection(VerificationCodeInput.this.f14975a.getText().length());
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                VerificationCodeInput verificationCodeInput = VerificationCodeInput.this;
                verificationCodeInput.f14977d = verificationCodeInput.f14975a.getText().toString();
                for (int i2 = 0; i2 < 6; i2++) {
                    if (i2 < VerificationCodeInput.this.f14977d.length()) {
                        VerificationCodeInput.this.f14976b[i2].setText(String.valueOf(VerificationCodeInput.this.f14977d.charAt(i2)));
                    } else {
                        VerificationCodeInput.this.f14976b[i2].setText("");
                    }
                }
                if (VerificationCodeInput.this.f14978e != null) {
                    VerificationCodeInput.this.f14978e.a();
                    if (VerificationCodeInput.this.f14977d.length() >= 6) {
                        VerificationCodeInput.this.f14978e.a(VerificationCodeInput.this.f14977d);
                        if (VerificationCodeInput.this.getContext() == null || VerificationCodeInput.this.f14975a == null) {
                            return;
                        }
                        ((InputMethodManager) VerificationCodeInput.this.getContext().getSystemService("input_method")).hideSoftInputFromWindow(VerificationCodeInput.this.f14975a.getWindowToken(), 0);
                    }
                }
            }
        });
    }

    public void setOnCompleteListener(a aVar) {
        this.f14978e = aVar;
    }

    public String getInputContent() {
        return this.f14975a.getText().toString();
    }

    public EditText getET() {
        return this.f14975a;
    }
}
