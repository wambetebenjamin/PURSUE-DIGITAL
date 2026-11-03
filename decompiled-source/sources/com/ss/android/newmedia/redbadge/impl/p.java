package com.ss.android.newmedia.redbadge.impl;

import android.content.ComponentName;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import java.util.Arrays;
import java.util.List;
/* compiled from: VivoHomeBadger.java */
/* loaded from: classes9.dex */
public class p extends com.ss.android.newmedia.redbadge.h {

    /* renamed from: a  reason: collision with root package name */
    private final String f18443a = "VivoHomeBadger";

    @Override // com.ss.android.newmedia.redbadge.h
    public int a(Context context, ComponentName componentName) {
        return -1;
    }

    @Override // com.ss.android.newmedia.redbadge.h
    public boolean a() {
        return false;
    }

    @Override // com.ss.android.newmedia.redbadge.a
    public void executeBadge(Context context, ComponentName componentName, int i2) {
        if (context == null || componentName == null) {
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        try {
            Intent intent = new Intent("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
            intent.putExtra("packageName", context.getPackageName());
            intent.putExtra("className", componentName.getClassName());
            intent.putExtra("notificationNum", i2);
            if (Build.VERSION.SDK_INT >= 26) {
                intent.addFlags(16777216);
            }
            context.sendBroadcast(intent);
        } catch (Throwable th) {
            th.printStackTrace();
            throw new com.ss.android.newmedia.redbadge.d(th.getMessage());
        }
    }

    @Override // com.ss.android.newmedia.redbadge.a
    public List<String> getSupportLaunchers() {
        return Arrays.asList("com.vivo.launcher", "com.bbk.launcher2");
    }

    @Override // com.ss.android.newmedia.redbadge.h
    public boolean a(Context context, ComponentName componentName, int i2) {
        Throwable th;
        ContentProviderClient contentProviderClient;
        boolean z = false;
        if (context == null || componentName == null) {
            com.bytedance.push.utils.h.d("VivoHomeBadger", "args is null");
            return false;
        } else if (i2 <= 0) {
            com.bytedance.push.utils.h.d("VivoHomeBadger", "count must bigger than 0");
            return false;
        } else {
            if (i2 != 1) {
                com.bytedance.push.utils.h.w("VivoHomeBadger", "vv only support add 1");
            }
            Uri parse = Uri.parse("content://com.vivo.abe.provider.launcher.notification.num");
            Bundle bundle = new Bundle();
            bundle.putString("package", context.getPackageName());
            bundle.putString("class", componentName.getClassName());
            ContentProviderClient contentProviderClient2 = null;
            try {
                if (Build.VERSION.SDK_INT >= 17) {
                    contentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient(parse);
                    try {
                        if (contentProviderClient != null) {
                            try {
                                Bundle call = contentProviderClient.call("add_badge", null, bundle);
                                if (call != null) {
                                    if (call.getInt("result") == 0) {
                                        z = true;
                                    }
                                }
                                if (contentProviderClient != null) {
                                    if (Build.VERSION.SDK_INT >= 24) {
                                        contentProviderClient.close();
                                    } else {
                                        contentProviderClient.release();
                                    }
                                }
                                return z;
                            } catch (RemoteException e2) {
                                e2.printStackTrace();
                            }
                        }
                        contentProviderClient2 = contentProviderClient;
                    } catch (Throwable th2) {
                        th = th2;
                        if (contentProviderClient != null) {
                            if (Build.VERSION.SDK_INT >= 24) {
                                contentProviderClient.close();
                            } else {
                                contentProviderClient.release();
                            }
                        }
                        throw th;
                    }
                }
                if (contentProviderClient2 != null) {
                    if (Build.VERSION.SDK_INT >= 24) {
                        contentProviderClient2.close();
                    } else {
                        contentProviderClient2.release();
                    }
                }
                return false;
            } catch (Throwable th3) {
                th = th3;
                contentProviderClient = null;
            }
        }
    }

    @Override // com.ss.android.newmedia.redbadge.h
    public boolean b(Context context, ComponentName componentName, int i2) {
        Throwable th;
        ContentProviderClient contentProviderClient;
        boolean z = false;
        if (context == null || componentName == null) {
            com.bytedance.push.utils.h.d("VivoHomeBadger", "args is null");
            return false;
        } else if (i2 <= 0) {
            com.bytedance.push.utils.h.d("VivoHomeBadger", "count must bigger than 0");
            return false;
        } else {
            Uri parse = Uri.parse("content://com.vivo.abe.provider.launcher.notification.num");
            Bundle bundle = new Bundle();
            bundle.putString("package", context.getPackageName());
            bundle.putString("class", componentName.getClassName());
            bundle.putInt("badgenumber", i2);
            ContentProviderClient contentProviderClient2 = null;
            try {
                if (Build.VERSION.SDK_INT >= 17) {
                    contentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient(parse);
                    try {
                        if (contentProviderClient != null) {
                            try {
                                Bundle call = contentProviderClient.call("reduce_badge", null, bundle);
                                if (call != null) {
                                    if (call.getInt("result") == 0) {
                                        z = true;
                                    }
                                }
                                if (contentProviderClient != null) {
                                    if (Build.VERSION.SDK_INT >= 24) {
                                        contentProviderClient.close();
                                    } else {
                                        contentProviderClient.release();
                                    }
                                }
                                return z;
                            } catch (RemoteException e2) {
                                e2.printStackTrace();
                            }
                        }
                        contentProviderClient2 = contentProviderClient;
                    } catch (Throwable th2) {
                        th = th2;
                        if (contentProviderClient != null) {
                            if (Build.VERSION.SDK_INT >= 24) {
                                contentProviderClient.close();
                            } else {
                                contentProviderClient.release();
                            }
                        }
                        throw th;
                    }
                }
                if (contentProviderClient2 != null) {
                    if (Build.VERSION.SDK_INT >= 24) {
                        contentProviderClient2.close();
                    } else {
                        contentProviderClient2.release();
                    }
                }
                return false;
            } catch (Throwable th3) {
                th = th3;
                contentProviderClient = null;
            }
        }
    }

    @Override // com.ss.android.newmedia.redbadge.h
    public boolean a(int i2) {
        if (i2 > 1) {
            com.bytedance.push.utils.h.d("VivoHomeBadger", "vv brand only support add 1,but count > 1");
            return false;
        }
        return true;
    }
}
