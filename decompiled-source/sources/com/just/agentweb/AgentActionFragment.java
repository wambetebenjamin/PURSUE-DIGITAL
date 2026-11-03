package com.just.agentweb;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes9.dex */
public final class AgentActionFragment extends Fragment {
    public static final String FRAGMENT_TAG = "AgentWebActionFragment";
    public static final String KEY_FROM_INTENTION = "KEY_FROM_INTENTION";
    public static final String KEY_URI = "KEY_URI";
    public static final int REQUEST_CODE = 596;
    private static final String TAG = "AgentActionFragment";
    private boolean isViewCreated = false;
    private Action mAction;

    /* loaded from: classes9.dex */
    public interface ChooserListener {
        void onChoiceResult(int i2, int i3, Intent intent);
    }

    /* loaded from: classes9.dex */
    public interface PermissionListener {
        void onRequestPermissionsResult(String[] strArr, int[] iArr, Bundle bundle);
    }

    /* loaded from: classes9.dex */
    public interface RationaleListener {
        void onRationaleResult(boolean z, Bundle bundle);
    }

    private void resetAction() {
    }

    public static void start(Activity activity, Action action) {
        FragmentManager supportFragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
        AgentActionFragment agentActionFragment = (AgentActionFragment) supportFragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (agentActionFragment == null) {
            agentActionFragment = new AgentActionFragment();
            supportFragmentManager.beginTransaction().add(agentActionFragment, FRAGMENT_TAG).commitAllowingStateLoss();
        }
        agentActionFragment.mAction = action;
        if (agentActionFragment.isViewCreated) {
            agentActionFragment.runAction();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            LogUtils.i(TAG, "savedInstanceState:" + bundle);
            return;
        }
        this.isViewCreated = true;
        runAction();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    private void runAction() {
        Action action = this.mAction;
        if (action == null) {
            resetAction();
        } else if (action.getAction() == 1) {
            if (Build.VERSION.SDK_INT >= 23) {
                requestPermission(this.mAction);
            } else {
                resetAction();
            }
        } else if (this.mAction.getAction() == 3) {
            captureCamera();
        } else if (this.mAction.getAction() == 4) {
            recordVideo();
        } else {
            choose();
        }
    }

    private void choose() {
        try {
            if (this.mAction.getChooserListener() == null) {
                return;
            }
            Intent intent = this.mAction.getIntent();
            if (intent == null) {
                resetAction();
            } else {
                startActivityForResult(intent, REQUEST_CODE);
            }
        } catch (Throwable th) {
            LogUtils.i(TAG, "找不到文件选择器");
            chooserActionCallback(-1, null);
            if (LogUtils.isDebug()) {
                th.printStackTrace();
            }
        }
    }

    private void chooserActionCallback(int i2, Intent intent) {
        if (this.mAction.getChooserListener() != null) {
            this.mAction.getChooserListener().onChoiceResult(REQUEST_CODE, i2, intent);
        }
        resetAction();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (i2 == 596) {
            if (this.mAction.getUri() != null) {
                chooserActionCallback(i3, new Intent().putExtra(KEY_URI, this.mAction.getUri()));
            } else {
                chooserActionCallback(i3, intent);
            }
        }
        resetAction();
    }

    private void requestPermission(Action action) {
        ArrayList<String> permissions = action.getPermissions();
        if (AgentWebUtils.isEmptyCollection(permissions)) {
            resetAction();
            return;
        }
        boolean z = false;
        if (this.mAction.getRationaleListener() != null) {
            Iterator<String> it = permissions.iterator();
            while (it.hasNext() && !(z = shouldShowRequestPermissionRationale(it.next()))) {
            }
            this.mAction.getRationaleListener().onRationaleResult(z, new Bundle());
            resetAction();
        } else if (this.mAction.getPermissionListener() != null) {
            requestPermissions((String[]) permissions.toArray(new String[0]), 1);
        }
    }

    private void captureCamera() {
        try {
            if (this.mAction.getChooserListener() == null) {
                resetAction();
                return;
            }
            File createImageFile = AgentWebUtils.createImageFile(getActivity());
            if (createImageFile == null) {
                this.mAction.getChooserListener().onChoiceResult(REQUEST_CODE, 0, null);
            }
            Intent intentCaptureCompat = AgentWebUtils.getIntentCaptureCompat(getActivity(), createImageFile);
            this.mAction.setUri((Uri) intentCaptureCompat.getParcelableExtra("output"));
            startActivityForResult(intentCaptureCompat, REQUEST_CODE);
        } catch (Throwable th) {
            LogUtils.e(TAG, "找不到系统相机");
            if (this.mAction.getChooserListener() != null) {
                this.mAction.getChooserListener().onChoiceResult(REQUEST_CODE, 0, null);
            }
            resetAction();
            if (LogUtils.isDebug()) {
                th.printStackTrace();
            }
        }
    }

    private void recordVideo() {
        try {
            if (this.mAction.getChooserListener() == null) {
                resetAction();
                return;
            }
            File createVideoFile = AgentWebUtils.createVideoFile(getActivity());
            if (createVideoFile == null) {
                this.mAction.getChooserListener().onChoiceResult(REQUEST_CODE, 0, null);
                resetAction();
                return;
            }
            Intent intentVideoCompat = AgentWebUtils.getIntentVideoCompat(getActivity(), createVideoFile);
            this.mAction.setUri((Uri) intentVideoCompat.getParcelableExtra("output"));
            startActivityForResult(intentVideoCompat, REQUEST_CODE);
        } catch (Throwable th) {
            LogUtils.e(TAG, "找不到系统相机");
            if (this.mAction.getChooserListener() != null) {
                this.mAction.getChooserListener().onChoiceResult(REQUEST_CODE, 0, null);
            }
            resetAction();
            if (LogUtils.isDebug()) {
                th.printStackTrace();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        if (this.mAction.getPermissionListener() != null) {
            Bundle bundle = new Bundle();
            bundle.putInt(KEY_FROM_INTENTION, this.mAction.getFromIntention());
            this.mAction.getPermissionListener().onRequestPermissionsResult(strArr, iArr, bundle);
        }
        resetAction();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }
}
