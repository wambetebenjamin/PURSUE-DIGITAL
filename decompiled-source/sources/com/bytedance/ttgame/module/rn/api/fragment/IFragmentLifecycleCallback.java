package com.bytedance.ttgame.module.rn.api.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/* loaded from: classes6.dex */
public interface IFragmentLifecycleCallback {
    void onActivityCreated(Bundle bundle);

    void onAttach(Context context);

    void onCreate(Bundle bundle);

    void onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    void onDestory();

    void onDestoryView();

    void onDetach();

    void onHiddenChanged(boolean z);

    void onPause();

    void onResume();

    void onStart();

    void onStop();

    void onViewCreated(View view, Bundle bundle);
}
