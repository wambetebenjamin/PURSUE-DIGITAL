package com.bytedance.praisedialoglib.manager;

import android.app.Activity;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.bytedance.praisedialoglib.callback.d;
import com.bytedance.praisedialoglib.googleplay.IGooglePlayDepend;
import com.bytedance.praisedialoglib.googleplay.IGooglePlayPraiseCallback;
import com.bytedance.praisedialoglib.request.a;
import com.bytedance.praisedialoglib.ui.PraiseDialogActivity;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.util.ArrayList;
import java.util.List;
/* compiled from: PraiseDialogManager.java */
/* loaded from: classes2.dex */
public class b {
    public static final int DEFAULT_INFO_STYLE = 0;
    public static final int INFO_STYLE_1 = 1;
    public static final int INFO_STYLE_2 = 2;

    /* renamed from: a  reason: collision with root package name */
    private static final String f11128a = "Click to enter the Comments page to post a good review";

    /* renamed from: b  reason: collision with root package name */
    private static final String f11129b = "Click the button in the lower right corner of the page to post a positive review";

    /* renamed from: c  reason: collision with root package name */
    private static final String f11130c = "Click to enter the review page to post a good review";

    /* renamed from: d  reason: collision with root package name */
    private static final String f11131d = "PraiseDialogManager";

    /* renamed from: e  reason: collision with root package name */
    private static b f11132e;

    /* renamed from: g  reason: collision with root package name */
    private String f11134g;

    /* renamed from: h  reason: collision with root package name */
    private String f11135h;

    /* renamed from: i  reason: collision with root package name */
    private int f11136i;
    private ArrayList<String> k;
    private ArrayList<String> l;
    private ArrayList<String> m;

    /* renamed from: j  reason: collision with root package name */
    private String f11137j = "";

    /* renamed from: f  reason: collision with root package name */
    private Handler f11133f = new Handler(Looper.getMainLooper());

    private b() {
        e();
    }

    public static b getInstance() {
        b bVar = f11132e;
        if (bVar != null) {
            return bVar;
        }
        synchronized (b.class) {
            if (f11132e == null) {
                f11132e = new b();
            }
        }
        return f11132e;
    }

    public void tryGetDialogEnable(final long j2, long j3, final d dVar) {
        if (a.getInstance().isDebugMode()) {
            this.f11133f.postDelayed(new Runnable() { // from class: com.bytedance.praisedialoglib.manager.b.1
                @Override // java.lang.Runnable
                public void run() {
                    b.this.a(dVar, 100, "current is debug mode");
                }
            }, j3);
        } else if (a()) {
            this.f11133f.postDelayed(new Runnable() { // from class: com.bytedance.praisedialoglib.manager.b.2
                @Override // java.lang.Runnable
                public void run() {
                    a.getInstance().a(new com.bytedance.praisedialoglib.request.a(String.valueOf(j2), new a.InterfaceC0167a() { // from class: com.bytedance.praisedialoglib.manager.b.2.1
                        @Override // com.bytedance.praisedialoglib.request.a.InterfaceC0167a
                        public void onSucceed(String str) {
                            b.this.a(dVar, 100, str);
                        }

                        @Override // com.bytedance.praisedialoglib.request.a.InterfaceC0167a
                        public void onFailed(int i2, String str) {
                            b.this.a(dVar, i2, str);
                        }
                    }));
                }
            }, j3);
        } else {
            a(dVar, -1, "setting switch is close");
        }
    }

    public void tryShowDialog(long j2, long j3, final Context context, final String str) {
        if (a.getInstance().isDebugMode() || a.getInstance().isCurDemo()) {
            this.f11133f.postDelayed(new Runnable() { // from class: com.bytedance.praisedialoglib.manager.b.3
                @Override // java.lang.Runnable
                public void run() {
                    b.this.showPraiseDialogDirectly(context, str);
                }
            }, j3);
        } else if (a()) {
            this.f11133f.postDelayed(new AnonymousClass4(j2, context, str), j3);
        } else if (a.getInstance().isCurDemo()) {
            this.f11133f.post(new Runnable() { // from class: com.bytedance.praisedialoglib.manager.b.5
                @Override // java.lang.Runnable
                public void run() {
                    Toast.makeText(context, "settings switch is close", 0).show();
                }
            });
        }
    }

    /* compiled from: PraiseDialogManager.java */
    /* renamed from: com.bytedance.praisedialoglib.manager.b$4  reason: invalid class name */
    /* loaded from: classes2.dex */
    class AnonymousClass4 implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ long f11149a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ Context f11150b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f11151c;

        AnonymousClass4(long j2, Context context, String str) {
            this.f11149a = j2;
            this.f11150b = context;
            this.f11151c = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.getInstance().a(new com.bytedance.praisedialoglib.request.a(String.valueOf(this.f11149a), new a.InterfaceC0167a() { // from class: com.bytedance.praisedialoglib.manager.b.4.1
                @Override // com.bytedance.praisedialoglib.request.a.InterfaceC0167a
                public void onSucceed(String str) {
                    if (a.getInstance().isMatchLocalCondition()) {
                        b.this.f11133f.post(new Runnable() { // from class: com.bytedance.praisedialoglib.manager.b.4.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                b.this.showPraiseDialogDirectly(AnonymousClass4.this.f11150b, AnonymousClass4.this.f11151c);
                            }
                        });
                    } else if (a.getInstance().isCurDemo()) {
                        b.this.f11133f.post(new Runnable() { // from class: com.bytedance.praisedialoglib.manager.b.4.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                Toast.makeText(AnonymousClass4.this.f11150b, "client condition not match", 0).show();
                            }
                        });
                    }
                }

                @Override // com.bytedance.praisedialoglib.request.a.InterfaceC0167a
                public void onFailed(int i2, String str) {
                    if (a.getInstance().isCurDemo()) {
                        b.this.f11133f.post(new Runnable() { // from class: com.bytedance.praisedialoglib.manager.b.4.1.3
                            @Override // java.lang.Runnable
                            public void run() {
                                Toast.makeText(AnonymousClass4.this.f11150b, "server condition not match", 0).show();
                            }
                        });
                    }
                }
            }));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final d dVar, final int i2, final String str) {
        this.f11133f.post(new Runnable() { // from class: com.bytedance.praisedialoglib.manager.b.6
            @Override // java.lang.Runnable
            public void run() {
                dVar.onGetDialogEnable(i2, str);
            }
        });
    }

    private boolean a() {
        return a.getInstance().getDialogEnable();
    }

    public void showPraiseDialogDirectly(final Context context, final String str) {
        d(context);
        if (a.getInstance().needShowDefaultDialog()) {
            Log.d(f11131d, "need  show default dialog");
            this.f11133f.post(new Runnable() { // from class: com.bytedance.praisedialoglib.manager.b.7
                @Override // java.lang.Runnable
                public void run() {
                    b.this.a(context, str);
                }
            });
            return;
        }
        Log.d(f11131d, "don't need default dialog");
        if (context instanceof com.bytedance.praisedialoglib.callback.a) {
            ((com.bytedance.praisedialoglib.callback.a) context).onPraiseDialogShow(new com.bytedance.praisedialoglib.callback.c() { // from class: com.bytedance.praisedialoglib.manager.b.8
                @Override // com.bytedance.praisedialoglib.callback.c
                public void onDialogShow() {
                    com.bytedance.praisedialoglib.utils.b.onEvent(com.bytedance.praisedialoglib.utils.b.EVENT_NAME_POP_SHOW, str);
                }

                @Override // com.bytedance.praisedialoglib.callback.c
                public void onDialogDismiss() {
                    com.bytedance.praisedialoglib.utils.b.onEvent(com.bytedance.praisedialoglib.utils.b.EVENT_NAME_POP_CLOSE, str);
                }

                @Override // com.bytedance.praisedialoglib.callback.c
                public void onPraiseBtnClick() {
                    com.bytedance.praisedialoglib.utils.b.onEvent(com.bytedance.praisedialoglib.utils.b.EVENT_NAME_POP_GOOD, str);
                    b.getInstance().goToMarket();
                }

                @Override // com.bytedance.praisedialoglib.callback.c
                public void onFeedbackBtnClick() {
                    com.bytedance.praisedialoglib.utils.b.onEvent(com.bytedance.praisedialoglib.utils.b.EVENT_NAME_POP_BAD, str);
                    b.getInstance().goToAppFeedback();
                }
            });
        } else if (a.getInstance().getIPraiseDialogActionCallback() != null) {
            a.getInstance().getIPraiseDialogActionCallback().onPraiseDialogShow(new com.bytedance.praisedialoglib.callback.c() { // from class: com.bytedance.praisedialoglib.manager.b.9
                @Override // com.bytedance.praisedialoglib.callback.c
                public void onDialogShow() {
                    com.bytedance.praisedialoglib.utils.b.onEvent(com.bytedance.praisedialoglib.utils.b.EVENT_NAME_POP_SHOW, str);
                }

                @Override // com.bytedance.praisedialoglib.callback.c
                public void onDialogDismiss() {
                    com.bytedance.praisedialoglib.utils.b.onEvent(com.bytedance.praisedialoglib.utils.b.EVENT_NAME_POP_CLOSE, str);
                }

                @Override // com.bytedance.praisedialoglib.callback.c
                public void onPraiseBtnClick() {
                    com.bytedance.praisedialoglib.utils.b.onEvent(com.bytedance.praisedialoglib.utils.b.EVENT_NAME_POP_GOOD, str);
                    b.getInstance().goToMarket();
                }

                @Override // com.bytedance.praisedialoglib.callback.c
                public void onFeedbackBtnClick() {
                    com.bytedance.praisedialoglib.utils.b.onEvent(com.bytedance.praisedialoglib.utils.b.EVENT_NAME_POP_BAD, str);
                    b.getInstance().goToAppFeedback();
                }
            });
        }
    }

    public void goToMarket() {
        Application application = c.getInstance().getApplication();
        if (TextUtils.isEmpty(this.f11135h) && a.getInstance().handleNoAppMarket()) {
            return;
        }
        if (a(application)) {
            com.bytedance.praisedialoglib.utils.b.onMarketEvent(this.f11135h);
        } else if (a(a.getInstance().getTopActivity())) {
            com.bytedance.praisedialoglib.utils.b.onMarketEvent(this.f11135h);
        } else if (c(a.getInstance().getTopActivity())) {
            com.bytedance.praisedialoglib.utils.b.onMarketEvent(this.f11135h);
        } else {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(b());
            if (!TextUtils.isEmpty(this.f11135h)) {
                intent.setPackage(this.f11135h);
            }
            boolean a2 = a(intent, application);
            if (!a2 && TextUtils.isEmpty(this.f11135h)) {
                goToAppFeedback();
                return;
            }
            if (!a2) {
                intent.setPackage(null);
                if (!a(intent, application)) {
                    goToAppFeedback();
                    return;
                }
            }
            if (this.f11136i == 1 && !TextUtils.isEmpty(this.f11137j)) {
                b(application, this.f11137j);
            }
            intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            application.startActivity(intent);
            com.bytedance.praisedialoglib.utils.b.onMarketEvent(this.f11135h);
        }
    }

    private boolean b(Context context) {
        if (!TextUtils.isEmpty(this.f11135h) && this.f11135h.equalsIgnoreCase(com.bytedance.praisedialoglib.b.f11116g) && !TextUtils.isEmpty(a.getInstance().b())) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("hiapplink://com.huawei.appmarket?appId=" + a.getInstance().b() + "&channelId=share&callType=SHARE"));
            intent.setFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setPackage(com.bytedance.praisedialoglib.b.f11116g);
            try {
                context.startActivity(intent);
                return true;
            } catch (ActivityNotFoundException e2) {
                Log.e(f11131d, e2.getLocalizedMessage());
            }
        }
        return false;
    }

    private boolean a(Context context) {
        if (!TextUtils.isEmpty(this.f11135h) && this.f11135h.equalsIgnoreCase(com.bytedance.praisedialoglib.b.SAMSUNG_APP_STORE_PKG_NAME)) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("samsungapps://ProductDetail/" + a.getInstance().getPackageName()));
            intent.setPackage(com.bytedance.praisedialoglib.b.SAMSUNG_APP_STORE_PKG_NAME);
            intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            try {
                context.startActivity(intent);
                return true;
            } catch (ActivityNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    private boolean a(Activity activity) {
        Log.d(f11131d, "checkIfNeedJumpOppoAppMarket is called, aimPkgName is " + this.f11135h);
        if (!TextUtils.isEmpty(this.f11135h) && (this.f11135h.equalsIgnoreCase(com.bytedance.praisedialoglib.b.f11110a) || this.f11135h.equalsIgnoreCase(com.bytedance.praisedialoglib.b.f11111b))) {
            if (b(a.getInstance().getTopActivity())) {
                Log.d(f11131d, "tryShowOppoCommentDialog is success");
                return true;
            }
            Intent intent = new Intent("android.intent.action.VIEW", b());
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setPackage(this.f11135h);
            try {
                activity.startActivityForResult(intent, 100);
                Log.d(f11131d, "jump appStore is success");
                return true;
            } catch (ActivityNotFoundException e2) {
                Log.e(f11131d, "checkIfNeedJumpOppoAppMarket jumpAppStore failed, " + e2);
                e2.printStackTrace();
            }
        }
        return false;
    }

    private static boolean b(Activity activity) {
        if (activity == null) {
            return false;
        }
        String str = com.bytedance.praisedialoglib.b.f11114e + a.getInstance().getPackageName();
        if (a(activity, com.bytedance.praisedialoglib.b.f11111b) > 84000) {
            return a(activity, Uri.parse(str), com.bytedance.praisedialoglib.b.f11111b);
        }
        if (a(activity, com.bytedance.praisedialoglib.b.f11110a) > 84000) {
            return a(activity, Uri.parse(str), com.bytedance.praisedialoglib.b.f11110a);
        }
        return false;
    }

    private boolean c(final Activity activity) {
        if (!TextUtils.isEmpty(this.f11135h) && this.f11135h.equalsIgnoreCase("com.android.vending") && com.bytedance.praisedialoglib.utils.a.a(activity, "com.android.vending") && com.bytedance.praisedialoglib.utils.a.a(activity, "com.google.android.gms")) {
            IGooglePlayDepend googlePlayDepend = DependManager.getGooglePlayDepend();
            if (googlePlayDepend != null) {
                googlePlayDepend.showInAppPraiseDialogForGp(activity, new IGooglePlayPraiseCallback() { // from class: com.bytedance.praisedialoglib.manager.b.10
                    @Override // com.bytedance.praisedialoglib.googleplay.IGooglePlayPraiseCallback
                    public void statusCallback(boolean z) {
                        if (z) {
                            return;
                        }
                        b.this.d(activity);
                    }
                });
                return true;
            }
            d(activity);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Activity activity) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(b());
        intent.setPackage("com.android.vending");
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intent.putExtra("pns.sandbox.dataflow_id", 1224741121);
        activity.startActivity(intent);
        com.bytedance.praisedialoglib.utils.b.onMarketEvent("com.android.vending");
    }

    private static long a(Activity activity, String str) {
        try {
            PackageInfo packageInfo = activity.getPackageManager().getPackageInfo(str, 128);
            if (packageInfo != null) {
                return Build.VERSION.SDK_INT >= 28 ? packageInfo.getLongVersionCode() : packageInfo.versionCode;
            }
            return -1L;
        } catch (PackageManager.NameNotFoundException e2) {
            Log.e(f11131d, "getVersionCode meet exception, " + e2);
            return -1L;
        }
    }

    private boolean e(Activity activity) {
        if (!com.bytedance.praisedialoglib.b.f11111b.equals(this.f11135h) || a(activity, com.bytedance.praisedialoglib.b.f11111b) <= 84000) {
            return !com.bytedance.praisedialoglib.b.f11110a.equals(this.f11135h) || a(activity, com.bytedance.praisedialoglib.b.f11110a) <= 84000;
        }
        return false;
    }

    private static boolean a(Activity activity, Uri uri, String str) {
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setPackage(str);
            intent.setData(uri);
            activity.startActivityForResult(intent, 100);
            Log.d(f11131d, "showOppoPraiseDialog is called, return true, uri is " + uri);
            return true;
        } catch (Throwable th) {
            Log.e(f11131d, "showOppoPraiseDialog meet err, " + th);
            return false;
        }
    }

    private Uri b() {
        return Uri.parse("market://details?id=" + a.getInstance().getPackageName());
    }

    public void goToAppFeedback() {
        a.getInstance().goToFeedback(c.getInstance().getApplication());
    }

    private boolean a(Intent intent, Context context) {
        if (intent == null) {
            return false;
        }
        try {
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536);
            if (queryIntentActivities != null) {
                return queryIntentActivities.size() > 0;
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private void d(Context context) {
        this.f11135h = "";
        String marketOrder = a.getInstance().getMarketOrder();
        this.f11134g = marketOrder;
        if (TextUtils.isEmpty(marketOrder)) {
            Log.d(f11131d, "initMarketOrder is called, but mMarketOrder is empty");
            return;
        }
        String[] split = this.f11134g.split("\\|");
        int length = split.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            String str = split[i2];
            if (com.bytedance.praisedialoglib.utils.a.a(context, str)) {
                this.f11135h = str;
                break;
            }
            i2++;
        }
        c();
    }

    private void c() {
        this.f11136i = 0;
        if (a.getInstance().getGuideStyle2List() != null && a.getInstance().getGuideStyle2List().contains(this.f11135h)) {
            this.f11136i = 2;
        } else if (a.getInstance().getGuideStyle1List() == null || !a.getInstance().getGuideStyle1List().contains(this.f11135h)) {
        } else {
            this.f11136i = 1;
            d();
        }
    }

    private void d() {
        if (this.k.contains(this.f11135h)) {
            this.f11137j = f11128a;
        } else if (this.l.contains(this.f11135h)) {
            this.f11137j = f11129b;
        } else if (this.m.contains(this.f11135h)) {
            this.f11137j = f11130c;
        }
    }

    private void e() {
        ArrayList<String> arrayList = new ArrayList<>();
        this.k = arrayList;
        arrayList.add(com.bytedance.praisedialoglib.b.f11110a);
        this.k.add("com.android.vending");
        ArrayList<String> arrayList2 = new ArrayList<>();
        this.l = arrayList2;
        arrayList2.add("com.android.vending");
        ArrayList<String> arrayList3 = new ArrayList<>();
        this.m = arrayList3;
        arrayList3.add("com.android.vending");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, String str) {
        if (context == null) {
            Log.e(f11131d, "context is null");
            return;
        }
        try {
            if ((context instanceof Activity) && (((Activity) context).isFinishing() || ((Activity) context).isDestroyed())) {
                Log.i(f11131d, "context is finishing or destroyed, replaced by application context");
                context = c.getInstance().getApplication().getBaseContext();
            }
            Intent intent = new Intent(context, PraiseDialogActivity.class);
            intent.setFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            intent.putExtra("from", str);
            a(intent);
            context.startActivity(intent);
        } catch (Throwable th) {
            Log.e(f11131d, "startActivity meet err, " + th);
        }
    }

    private void b(Context context, String str) {
        Toast makeText = Toast.makeText(context, str, 1);
        makeText.setGravity(17, 0, 0);
        makeText.show();
    }

    private void a(Intent intent) {
        if (a.getInstance().getBackgroundResId() != -1 && a.getInstance().getBackgroundResId() != 0) {
            intent.putExtra(com.bytedance.praisedialoglib.b.BACKGROUND_RES, a.getInstance().getBackgroundResId());
        }
        int middleImgResId = a.getInstance().getMiddleImgResId();
        if (this.f11136i == 2 && e(a.getInstance().getTopActivity())) {
            middleImgResId = a.getInstance().getMiddleImgResIdStyle2();
        }
        if (middleImgResId != -1 && middleImgResId != 0) {
            intent.putExtra(com.bytedance.praisedialoglib.b.MIDDLE_IMAGE_RES, middleImgResId);
        }
        if (!TextUtils.isEmpty(a.getInstance().getTitleStr())) {
            intent.putExtra(com.bytedance.praisedialoglib.b.MAIN_TITLE_TEXT, a.getInstance().getTitleStr());
        }
        if (!TextUtils.isEmpty(a.getInstance().getTitleStrColor())) {
            intent.putExtra(com.bytedance.praisedialoglib.b.MAIN_TITLE_TEXT_COLOR, a.getInstance().getTitleStrColor());
        }
        if (a.getInstance().getTitleStrSize() != -1 && a.getInstance().getTitleStrSize() != 0) {
            intent.putExtra(com.bytedance.praisedialoglib.b.MAIN_TITLE_TEXT_SIZE, a.getInstance().getTitleStrSize());
        }
        if (!TextUtils.isEmpty(a.getInstance().getSecondTitleStr())) {
            intent.putExtra(com.bytedance.praisedialoglib.b.SECOND_TITLE_TEXT, a.getInstance().getSecondTitleStr());
        }
        if (!TextUtils.isEmpty(a.getInstance().getSecondTitleStrColor())) {
            intent.putExtra(com.bytedance.praisedialoglib.b.SECOND_TITLE_TEXT_COLOR, a.getInstance().getSecondTitleStrColor());
        }
        if (a.getInstance().getSecondTitleStrSize() != -1 && a.getInstance().getSecondTitleStrSize() != 0) {
            intent.putExtra(com.bytedance.praisedialoglib.b.SECOND_TITLE_TEXT_SIZE, a.getInstance().getSecondTitleStrSize());
        }
        if (!TextUtils.isEmpty(a.getInstance().getNegativeBtnStr())) {
            intent.putExtra(com.bytedance.praisedialoglib.b.NEGATIVE_BTN_TEXT, a.getInstance().getNegativeBtnStr());
        }
        if (!TextUtils.isEmpty(a.getInstance().getNegativeBtnColor())) {
            intent.putExtra(com.bytedance.praisedialoglib.b.NEGATIVE_BTN_TEXT_COLOR, a.getInstance().getNegativeBtnColor());
        }
        if (a.getInstance().getNegativeBtnSize() != -1 && a.getInstance().getNegativeBtnSize() != 0) {
            intent.putExtra(com.bytedance.praisedialoglib.b.NEGATIVE_BTN_TEXT_SIZE, a.getInstance().getNegativeBtnSize());
        }
        if (a.getInstance().getNegativeBtnResId() != -1 && a.getInstance().getNegativeBtnResId() != 0) {
            intent.putExtra(com.bytedance.praisedialoglib.b.NEGATIVE_BTN_TEXT_BG, a.getInstance().getNegativeBtnResId());
        }
        if (!TextUtils.isEmpty(a.getInstance().getPositiveBtnStr())) {
            intent.putExtra(com.bytedance.praisedialoglib.b.POSITIVE_BTN_TEXT, a.getInstance().getPositiveBtnStr());
        }
        if (!TextUtils.isEmpty(a.getInstance().getPositiveBtnColor())) {
            intent.putExtra(com.bytedance.praisedialoglib.b.POSITIVE_BTN_TEXT_COLOR, a.getInstance().getPositiveBtnColor());
        }
        if (a.getInstance().getPositiveBtnSize() != -1 && a.getInstance().getPositiveBtnSize() != 0) {
            intent.putExtra(com.bytedance.praisedialoglib.b.POSITIVE_BTN_TEXT_SIZE, a.getInstance().getPositiveBtnSize());
        }
        if (a.getInstance().getPositiveBtnResId() == -1 || a.getInstance().getPositiveBtnResId() == 0) {
            return;
        }
        intent.putExtra(com.bytedance.praisedialoglib.b.POSITIVE_BTN_TEXT_BG, a.getInstance().getPositiveBtnResId());
    }
}
