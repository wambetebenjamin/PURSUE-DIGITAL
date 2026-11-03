package com.oasis.unity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.oasis.analytics.AnalyticsAgent;
import com.oasis.android.ActivityManager;
import com.oasis.android.R;
import com.oasis.android.SplashActivity;
import com.oasis.android.SplashManager;
import com.oasis.android.f;
import com.unity3d.player.UnityPlayerActivity;
/* loaded from: classes9.dex */
public class OasisPlayerActivity extends UnityPlayerActivity {

    /* renamed from: a  reason: collision with root package name */
    private boolean f17757a;

    /* loaded from: classes9.dex */
    class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ View f17758a;

        /* renamed from: com.oasis.unity.OasisPlayerActivity$a$a  reason: collision with other inner class name */
        /* loaded from: classes9.dex */
        class RunnableC0321a implements Runnable {
            RunnableC0321a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                OasisPlayerActivity oasisPlayerActivity = OasisPlayerActivity.this;
                if (oasisPlayerActivity.f17757a) {
                    return;
                }
                oasisPlayerActivity.f17757a = true;
            }
        }

        a(View view) {
            this.f17758a = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f17758a.post(new RunnableC0321a());
        }
    }

    /* loaded from: classes9.dex */
    static /* synthetic */ class b {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f17761a;

        static {
            int[] iArr = new int[f.values().length];
            f17761a = iArr;
            try {
                iArr[f.MATRIX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f17761a[f.CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f17761a[f.FIT_XY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f17761a[f.FIT_END.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f17761a[f.FIT_START.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f17761a[f.FIT_CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f17761a[f.CENTER_CROP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f17761a[f.CENTER_INSIDE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private View a() {
        return ((ViewGroup) findViewById(16908290)).getChildAt(0);
    }

    private void a(String str, String str2, Object obj) {
        String str3;
        JsonElement asJsonPrimitive;
        try {
            Gson gson = new Gson();
            JsonObject jsonObject = new JsonObject();
            JsonObject jsonObject2 = new JsonObject();
            if (obj != null) {
                JsonElement jsonTree = gson.toJsonTree(obj);
                if (jsonTree.isJsonArray()) {
                    asJsonPrimitive = jsonTree.getAsJsonArray();
                    str3 = "array";
                } else if (jsonTree.isJsonObject()) {
                    jsonObject2 = jsonTree.getAsJsonObject();
                } else if (jsonTree.isJsonPrimitive()) {
                    str3 = "primitive";
                    asJsonPrimitive = jsonTree.getAsJsonPrimitive();
                }
                jsonObject2.add(str3, asJsonPrimitive);
            }
            jsonObject2.addProperty("call_location", str2);
            jsonObject.add("params", jsonObject2);
            jsonObject.addProperty("event", str);
            AnalyticsAgent.getInstance().reportEvent(jsonObject.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void manualResumeOasisActivity() {
        Log.i("Oasis", "ManualResumeOasisActivity!");
        onResume();
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i2, int i3, Intent intent) {
        ActivityManager.onActivityResult(i2, i3, intent);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        ActivityManager.onBackPressed();
    }

    @Override // com.unity3d.player.UnityPlayerActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActivityManager.onConfigurationChanged(configuration);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.unity3d.player.UnityPlayerActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        ImageView.ScaleType scaleType;
        Log.i("OasisPlayerActivity", "onCreate");
        super.onCreate(bundle);
        a("oasis_activity_on_create", "call onCreate", null);
        if (Boolean.parseBoolean(getString(R.string.splash_enable))) {
            getWindow().getDecorView().setBackgroundColor(Color.parseColor(getString(R.string.splash_background_color)));
            getWindow().getDecorView().setSystemUiVisibility(4102);
            this.f17757a = false;
            f fVar = f.values()[Integer.parseInt(getString(R.string.static_splash_align_mode))];
            View a2 = a();
            a2.setVisibility(4);
            ImageView imageView = new ImageView(this);
            SplashManager.setStaticSplashImageView(imageView);
            imageView.setBackgroundColor(getResources().getColor(R.color.splash_background_color));
            imageView.setImageResource(R.drawable.android_splash_icon_static);
            switch (b.f17761a[fVar.ordinal()]) {
                case 1:
                    scaleType = ImageView.ScaleType.MATRIX;
                    imageView.setScaleType(scaleType);
                    addContentView(imageView, new FrameLayout.LayoutParams(-1, -1, 17));
                    new Handler(Looper.getMainLooper()).postDelayed(new a(a2), Long.parseLong(getString(R.string.splash_delay_time)));
                    break;
                case 2:
                    scaleType = ImageView.ScaleType.CENTER;
                    imageView.setScaleType(scaleType);
                    addContentView(imageView, new FrameLayout.LayoutParams(-1, -1, 17));
                    new Handler(Looper.getMainLooper()).postDelayed(new a(a2), Long.parseLong(getString(R.string.splash_delay_time)));
                    break;
                case 3:
                    scaleType = ImageView.ScaleType.FIT_XY;
                    imageView.setScaleType(scaleType);
                    addContentView(imageView, new FrameLayout.LayoutParams(-1, -1, 17));
                    new Handler(Looper.getMainLooper()).postDelayed(new a(a2), Long.parseLong(getString(R.string.splash_delay_time)));
                    break;
                case 4:
                    scaleType = ImageView.ScaleType.FIT_END;
                    imageView.setScaleType(scaleType);
                    addContentView(imageView, new FrameLayout.LayoutParams(-1, -1, 17));
                    new Handler(Looper.getMainLooper()).postDelayed(new a(a2), Long.parseLong(getString(R.string.splash_delay_time)));
                    break;
                case 5:
                    scaleType = ImageView.ScaleType.FIT_START;
                    imageView.setScaleType(scaleType);
                    addContentView(imageView, new FrameLayout.LayoutParams(-1, -1, 17));
                    new Handler(Looper.getMainLooper()).postDelayed(new a(a2), Long.parseLong(getString(R.string.splash_delay_time)));
                    break;
                case 6:
                    scaleType = ImageView.ScaleType.FIT_CENTER;
                    imageView.setScaleType(scaleType);
                    addContentView(imageView, new FrameLayout.LayoutParams(-1, -1, 17));
                    new Handler(Looper.getMainLooper()).postDelayed(new a(a2), Long.parseLong(getString(R.string.splash_delay_time)));
                    break;
                case 7:
                    scaleType = ImageView.ScaleType.CENTER_CROP;
                    imageView.setScaleType(scaleType);
                    addContentView(imageView, new FrameLayout.LayoutParams(-1, -1, 17));
                    new Handler(Looper.getMainLooper()).postDelayed(new a(a2), Long.parseLong(getString(R.string.splash_delay_time)));
                    break;
                case 8:
                    scaleType = ImageView.ScaleType.CENTER_INSIDE;
                    imageView.setScaleType(scaleType);
                    addContentView(imageView, new FrameLayout.LayoutParams(-1, -1, 17));
                    new Handler(Looper.getMainLooper()).postDelayed(new a(a2), Long.parseLong(getString(R.string.splash_delay_time)));
                    break;
                default:
                    addContentView(imageView, new FrameLayout.LayoutParams(-1, -1, 17));
                    new Handler(Looper.getMainLooper()).postDelayed(new a(a2), Long.parseLong(getString(R.string.splash_delay_time)));
                    break;
            }
        }
        ActivityManager.onCreate(this, bundle);
        if (!Boolean.parseBoolean(getString(R.string.dynamic_splash_enable)) || SplashActivity.hasShowed) {
            return;
        }
        Intent intent = new Intent(this, SplashActivity.class);
        intent.setData(getIntent().getData());
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.unity3d.player.UnityPlayerActivity, android.app.Activity
    public void onDestroy() {
        Log.i("OasisPlayerActivity", "onDestroy");
        super.onDestroy();
        a("oasis_activity_on_destroy", "call onDestroy", null);
        ActivityManager.onDestroy();
    }

    @Override // com.unity3d.player.UnityPlayerActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        return ActivityManager.onKeyDown(i2, keyEvent) || super.onKeyDown(i2, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.unity3d.player.UnityPlayerActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        Log.i("OasisPlayerActivity", "onNewIntent");
        if (SplashActivity.launchURI != null) {
            Log.i("OasisPlayerActivity", "ignore super.onNewIntent(intent) .");
            SplashActivity.launchURI = null;
        } else {
            super.onNewIntent(intent);
        }
        ActivityManager.onNewIntent(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.unity3d.player.UnityPlayerActivity, android.app.Activity
    public void onPause() {
        Log.i("OasisPlayerActivity", "onPause");
        super.onPause();
        a("oasis_activity_on_pause", "call onPause", null);
        ActivityManager.onPause();
    }

    @Override // android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        ActivityManager.onRequestPermissionsResult(i2, strArr, iArr);
    }

    @Override // android.app.Activity
    protected void onRestart() {
        Log.i("OasisPlayerActivity", "onRestart");
        super.onRestart();
        a("oasis_activity_on_restart", "call onRestart", null);
        ActivityManager.onRestart();
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        ActivityManager.onRestoreInstanceState(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.unity3d.player.UnityPlayerActivity, android.app.Activity
    public void onResume() {
        Log.i("OasisPlayerActivity", "onResume");
        super.onResume();
        a("oasis_activity_on_resume", "call onResume", null);
        ActivityManager.onResume();
        onWindowFocusChanged(true);
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        ActivityManager.onSaveInstanceState(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.unity3d.player.UnityPlayerActivity, android.app.Activity
    public void onStart() {
        Log.i("OasisPlayerActivity", com.bytedance.apm.constant.a.ON_START);
        super.onStart();
        a("oasis_activity_on_start", "call onStart", null);
        ActivityManager.onStart();
        onWindowFocusChanged(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.unity3d.player.UnityPlayerActivity, android.app.Activity
    public void onStop() {
        Log.i("OasisPlayerActivity", "onStop");
        super.onStop();
        a("oasis_activity_on_stop", "call onStop", null);
        ActivityManager.onStop();
    }

    @Override // com.unity3d.player.UnityPlayerActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        Log.i("OasisPlayerActivity", "onWindowFocusChanged:" + z);
        super.onWindowFocusChanged(z);
    }

    public void setAllViewsVisible() {
        a().setVisibility(0);
    }
}
