package com.bytedance.push.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.common.wschannel.channel.impl.ok.f;
import com.bytedance.push.utils.a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/* compiled from: ManifestUtils.java */
/* loaded from: classes2.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private static List<String> f12040a;

    /* renamed from: b  reason: collision with root package name */
    private static Bundle f12041b;

    /* renamed from: c  reason: collision with root package name */
    private static List<ServiceInfo> f12042c;

    /* renamed from: d  reason: collision with root package name */
    private static List<ActivityInfo> f12043d;

    /* renamed from: e  reason: collision with root package name */
    private static List<ActivityInfo> f12044e;

    /* renamed from: f  reason: collision with root package name */
    private static List<ProviderInfo> f12045f;

    private static List<String> a(Context context) {
        String[] strArr;
        if (f12040a == null) {
            synchronized (i.class) {
                if (f12040a == null && (strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions) != null) {
                    f12040a = Arrays.asList(strArr);
                }
            }
        }
        return f12040a;
    }

    public static boolean checkPermissions(Context context, String str, String str2, List<String> list) {
        List<String> a2 = a(context);
        if (a2 == null || a2.isEmpty()) {
            com.bytedance.push.i.logger().e(str, str2 + ".You need to declare permission(s) {" + list + "} in AndroidManifest.xml");
            return false;
        }
        ArrayList arrayList = new ArrayList();
        for (String str3 : list) {
            if (!a2.contains(str3)) {
                arrayList.add(str3);
            }
        }
        if (arrayList.isEmpty()) {
            return true;
        }
        com.bytedance.push.i.logger().e(str, str2 + ".You need to declare permission(s) {" + arrayList + "} in AndroidManifest.xml");
        return false;
    }

    public static boolean checkMetadata(Context context, String str, String str2, List<String> list) {
        Bundle b2 = b(context);
        if ((b2 == null || b2.isEmpty()) && !list.isEmpty()) {
            com.bytedance.push.i.logger().e(str, str2 + " metadata is empty!!!");
            return false;
        }
        ArrayList arrayList = new ArrayList();
        for (String str3 : list) {
            if (b2 == null || !b2.containsKey(str3)) {
                arrayList.add(str3);
            }
        }
        if (arrayList.isEmpty()) {
            return true;
        }
        com.bytedance.push.i.logger().e(str, str2 + ".You need to declare metadata {" + arrayList + "} in AndroidManifest.xml");
        return false;
    }

    private static Bundle b(Context context) {
        if (f12041b == null) {
            synchronized (i.class) {
                if (f12041b == null) {
                    f12041b = context.getPackageManager().getPackageInfo(context.getPackageName(), 128).applicationInfo.metaData;
                }
            }
        }
        return f12041b;
    }

    public static boolean checkService(Context context, String str, String str2, List<a> list) {
        boolean z;
        if (list == null || list.isEmpty()) {
            return true;
        }
        List<ServiceInfo> c2 = c(context);
        if (c2 == null || c2.size() == 0) {
            com.bytedance.push.i.logger().e(str, str2 + ". You need to declare service(s) {" + list + "} in AndroidManifest.xml");
            return false;
        }
        ArrayList arrayList = new ArrayList();
        boolean z2 = true;
        for (a aVar : list) {
            Iterator<ServiceInfo> it = c2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                ServiceInfo next = it.next();
                if (TextUtils.equals(next.name, aVar.name)) {
                    boolean equals = TextUtils.equals(aVar.processName, next.processName);
                    boolean equals2 = !TextUtils.isEmpty(aVar.permission) ? TextUtils.equals(next.permission, aVar.permission) : true;
                    if (!equals) {
                        com.bytedance.push.i.logger().e(str, str2 + " service configure error: " + aVar.name + " should be declared in process " + aVar.processName + ", but now in " + next.processName);
                        z2 = false;
                    }
                    if (!equals2) {
                        com.bytedance.push.i.logger().e(str, str2 + " service configure error: " + aVar.name + " need permission(s) {" + aVar.permission + "}, but now " + next.permission);
                        z2 = false;
                    }
                    if (aVar.intentFilter != null) {
                        boolean z3 = true;
                        for (a.b bVar : aVar.intentFilter) {
                            if (bVar.f12020a != null) {
                                for (String str3 : bVar.f12020a) {
                                    if (!checkServiceFilter(context, aVar.name, bVar, str3)) {
                                        com.bytedance.push.i.logger().e(str, str2 + aVar.name + " need to declare {" + str3 + "} action(s) in AndroidManifest.xml");
                                        z3 = false;
                                    }
                                }
                            }
                        }
                        if (!z3) {
                            z2 = false;
                        }
                    }
                    z = true;
                }
            }
            if (!z) {
                arrayList.add(aVar);
            }
        }
        if (!arrayList.isEmpty()) {
            com.bytedance.push.i.logger().e(str, str2 + " need to declare service(s) {" + arrayList + "} in AndroidManifest.xml");
        }
        return z2 && arrayList.isEmpty();
    }

    private static List<ServiceInfo> c(Context context) {
        ServiceInfo[] serviceInfoArr;
        if (f12042c == null) {
            synchronized (i.class) {
                if (f12042c == null && (serviceInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 516).services) != null) {
                    f12042c = Arrays.asList(serviceInfoArr);
                }
            }
        }
        return f12042c;
    }

    public static boolean checkReceiver(Context context, String str, String str2, List<a> list) {
        boolean z;
        if (list == null || list.isEmpty()) {
            return true;
        }
        List<ActivityInfo> d2 = d(context);
        if (d2 == null || d2.size() == 0) {
            com.bytedance.push.i.logger().e(str, str2 + " error configuration:" + list);
            return false;
        }
        ArrayList arrayList = new ArrayList();
        boolean z2 = true;
        for (a aVar : list) {
            Iterator<ActivityInfo> it = d2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                ActivityInfo next = it.next();
                if (TextUtils.equals(next.name, aVar.name)) {
                    if (!TextUtils.equals(aVar.processName, next.processName)) {
                        com.bytedance.push.i.logger().e(str, str2 + " receiver configure error: " + aVar.name + " should be in" + aVar.processName + ",but now in " + next.processName + " process");
                        z2 = false;
                    }
                    if (!(!TextUtils.isEmpty(aVar.permission) ? TextUtils.equals(next.permission, aVar.permission) : true)) {
                        com.bytedance.push.i.logger().e(str, str2 + " receiver configure error: " + aVar.name + " need permission " + aVar.permission + ", but now the permission is:" + next.permission);
                        z2 = false;
                    }
                    if (aVar.intentFilter != null) {
                        boolean z3 = true;
                        for (a.b bVar : aVar.intentFilter) {
                            if (bVar.f12020a != null) {
                                for (String str3 : bVar.f12020a) {
                                    if (!a(context, aVar.name, bVar, str3)) {
                                        com.bytedance.push.i.logger().e(str, str2 + " " + aVar.name + " configure error，need action : " + str3);
                                        z3 = false;
                                    }
                                }
                            }
                        }
                        if (!z3) {
                            z2 = false;
                        }
                    }
                    z = true;
                }
            }
            if (!z) {
                arrayList.add(aVar);
            }
        }
        if (!arrayList.isEmpty()) {
            com.bytedance.push.i.logger().e(str, str2 + " receiver configure error: " + arrayList);
        }
        return z2 && arrayList.isEmpty();
    }

    private static List<ActivityInfo> d(Context context) {
        ActivityInfo[] activityInfoArr;
        if (f12043d == null) {
            synchronized (i.class) {
                if (f12043d == null && (activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), f.b.p).receivers) != null) {
                    f12043d = Arrays.asList(activityInfoArr);
                }
            }
        }
        return f12043d;
    }

    private static boolean a(Context context, String str, a.b bVar, String str2) {
        Intent intent = new Intent();
        intent.setPackage(context.getPackageName());
        if (bVar.f12021b != null) {
            for (String str3 : bVar.f12021b) {
                intent.addCategory(str3);
            }
        }
        intent.setAction(str2);
        if (bVar.f12022c != null) {
            if (!TextUtils.isEmpty(bVar.f12023d)) {
                intent.setDataAndType(bVar.f12022c, bVar.f12023d);
            } else {
                intent.setData(bVar.f12022c);
            }
        } else if (!TextUtils.isEmpty(bVar.f12023d)) {
            intent.setType(bVar.f12023d);
        }
        List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 576);
        if (queryBroadcastReceivers == null || queryBroadcastReceivers.size() <= 0) {
            return false;
        }
        for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
            if (resolveInfo.activityInfo != null && TextUtils.equals(resolveInfo.activityInfo.name, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkServiceFilter(Context context, String str, a.b bVar, String str2) {
        Intent intent = new Intent();
        intent.setPackage(context.getPackageName());
        if (bVar.f12021b != null) {
            for (String str3 : bVar.f12021b) {
                intent.addCategory(str3);
            }
        }
        intent.setAction(str2);
        if (bVar.f12022c != null) {
            intent.setData(bVar.f12022c);
        }
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 576);
        if (queryIntentServices == null || queryIntentServices.size() <= 0) {
            return false;
        }
        for (ResolveInfo resolveInfo : queryIntentServices) {
            if (resolveInfo.serviceInfo != null && (TextUtils.isEmpty(str) || TextUtils.equals(resolveInfo.serviceInfo.name, str))) {
                return true;
            }
        }
        return false;
    }

    private static boolean b(Context context, String str, a.b bVar, String str2) {
        Intent intent = new Intent();
        intent.setPackage(context.getPackageName());
        if (bVar.f12021b != null) {
            for (String str3 : bVar.f12021b) {
                intent.addCategory(str3);
            }
        }
        intent.setAction(str2);
        if (bVar.f12022c != null) {
            intent.setData(bVar.f12022c);
        }
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 576);
        if (queryIntentActivities == null || queryIntentActivities.size() <= 0) {
            return false;
        }
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            if (resolveInfo.activityInfo != null && TextUtils.equals(resolveInfo.activityInfo.name, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkActivities(Context context, String str, String str2, List<a> list) {
        boolean z;
        if (list == null || list.isEmpty()) {
            return true;
        }
        List<ActivityInfo> e2 = e(context);
        if (e2 == null || e2.size() == 0) {
            com.bytedance.push.i.logger().e(str, str2 + ". You need to declare activity(s) {" + list + "} in AndroidManifest.xml");
            return false;
        }
        ArrayList arrayList = new ArrayList();
        boolean z2 = true;
        for (a aVar : list) {
            Iterator<ActivityInfo> it = e2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                ActivityInfo next = it.next();
                if (TextUtils.equals(next.name, aVar.name)) {
                    if (!TextUtils.equals(aVar.processName, next.processName)) {
                        com.bytedance.push.i.logger().e(str, str2 + aVar.name + " should be declared in process " + aVar.processName + ", but now in process " + next.processName);
                        z2 = false;
                    }
                    if (!(!TextUtils.isEmpty(aVar.permission) ? TextUtils.equals(next.permission, aVar.permission) : true)) {
                        com.bytedance.push.i.logger().e(str, str2 + aVar.name + " need permission(s) {" + aVar.permission + "}, but now is " + next.permission);
                        z2 = false;
                    }
                    if (aVar.intentFilter != null) {
                        boolean z3 = true;
                        for (a.b bVar : aVar.intentFilter) {
                            if (bVar.f12020a != null) {
                                for (String str3 : bVar.f12020a) {
                                    if (!b(context, aVar.name, bVar, str3)) {
                                        com.bytedance.push.i.logger().e(str, str2 + " " + aVar.name + " need action " + str3);
                                        z3 = false;
                                    }
                                }
                            }
                        }
                        if (!z3) {
                            z2 = false;
                        }
                    }
                    z = true;
                }
            }
            if (!z) {
                arrayList.add(aVar);
            }
        }
        if (!arrayList.isEmpty()) {
            com.bytedance.push.i.logger().e(str, str2 + ".You should declare activities {" + arrayList + "} in AndroidManifest.xml");
        }
        return z2 && arrayList.isEmpty();
    }

    private static List<ActivityInfo> e(Context context) {
        ActivityInfo[] activityInfoArr;
        if (f12044e == null) {
            synchronized (i.class) {
                if (f12044e == null && (activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 513).activities) != null) {
                    f12044e = Arrays.asList(activityInfoArr);
                }
            }
        }
        return f12044e;
    }

    public static boolean checkContentProviders(Context context, String str, String str2, List<a> list) {
        boolean z;
        if (list == null || list.isEmpty()) {
            return true;
        }
        List<ProviderInfo> f2 = f(context);
        if (f2 == null || f2.size() == 0) {
            com.bytedance.push.i.logger().e(str, str2 + " need to declare ContentProvider(s) " + list);
            return false;
        }
        ArrayList arrayList = new ArrayList();
        boolean z2 = true;
        for (a aVar : list) {
            Iterator<ProviderInfo> it = f2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                ProviderInfo next = it.next();
                if (TextUtils.equals(next.name, aVar.name)) {
                    if (!TextUtils.equals(aVar.processName, next.processName)) {
                        com.bytedance.push.i.logger().e(str, str2 + aVar.name + " should be declared in process " + aVar.processName + ", but now is " + next.processName);
                        z2 = false;
                    }
                    if (!TextUtils.isEmpty(aVar.authorities) ? TextUtils.equals(next.authority, aVar.authorities) : true) {
                        z = true;
                    } else {
                        com.bytedance.push.i.logger().e(str, str2 + aVar.name + " need permission(s) {" + aVar.authorities + "}, but now is" + next.authority);
                        z = true;
                        z2 = false;
                    }
                }
            }
            if (!z) {
                arrayList.add(aVar);
            }
        }
        if (!arrayList.isEmpty()) {
            com.bytedance.push.i.logger().e(str, str2 + " .You should declare activities {" + arrayList + "} in AndroidManifest.xml");
        }
        return z2 && arrayList.isEmpty();
    }

    private static List<ProviderInfo> f(Context context) {
        ProviderInfo[] providerInfoArr;
        if (f12045f == null) {
            synchronized (i.class) {
                if (f12045f == null && (providerInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 520).providers) != null) {
                    f12045f = Arrays.asList(providerInfoArr);
                }
            }
        }
        return f12045f;
    }
}
