package com.squareup.picasso;
/* loaded from: classes9.dex */
public interface Callback {

    /* loaded from: classes9.dex */
    public static class EmptyCallback implements Callback {
        @Override // com.squareup.picasso.Callback
        public void onError() {
        }

        @Override // com.squareup.picasso.Callback
        public void onSuccess() {
        }
    }

    void onError();

    void onSuccess();
}
