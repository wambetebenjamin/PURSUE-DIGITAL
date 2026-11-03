package com.oasis.crash;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
import com.oasis.channel.CrashListener;
/* loaded from: classes9.dex */
public abstract class CrashAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static CrashAgent f17520a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17521b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17522c = true;
    protected CrashAgent nextAgent = null;

    public CrashAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v15 */
    public static CrashAgent createInstance(Context context) {
        String[] stringArray;
        CrashAgent crashAgent;
        NoClassDefFoundError e2;
        InstantiationException e3;
        IllegalAccessException e4;
        ClassNotFoundException e5;
        StringBuilder sb;
        InstantiationException instantiationException;
        StringBuilder append;
        StringBuilder sb2;
        String str;
        ClassNotFoundException classNotFoundException;
        ClassNotFoundException classNotFoundException2;
        if (f17522c || f17520a == null) {
            CrashAgent crashAgent2 = null;
            for (String str2 : context.getResources().getStringArray(R.array.crash_agent_class)) {
                Logger.i("CrashAgent", "Start to create the CrashAgent: " + str2);
                if (str2 != null) {
                    try {
                    } catch (ClassNotFoundException e6) {
                        crashAgent = crashAgent2;
                        e5 = e6;
                    } catch (IllegalAccessException e7) {
                        crashAgent = crashAgent2;
                        e4 = e7;
                    } catch (InstantiationException e8) {
                        crashAgent = crashAgent2;
                        e3 = e8;
                    } catch (NoClassDefFoundError e9) {
                        crashAgent = crashAgent2;
                        e2 = e9;
                    }
                    if (str2.length() > 0) {
                        crashAgent = (CrashAgent) Class.forName(str2).newInstance();
                        if (crashAgent2 == null) {
                            f17520a = crashAgent;
                            try {
                                Logger.i("CrashAgent", str2 + " is instance");
                            } catch (ClassNotFoundException e10) {
                                e5 = e10;
                                sb2 = new StringBuilder();
                                str = "Throw ClassNotFoundException :";
                                classNotFoundException2 = e5;
                                append = sb2.append(str);
                                classNotFoundException = classNotFoundException2;
                                Logger.e("CrashAgent", append.append(str2).toString(), classNotFoundException);
                                crashAgent2 = crashAgent;
                            } catch (IllegalAccessException e11) {
                                e4 = e11;
                                sb2 = new StringBuilder();
                                str = "Throw IllegalAccessException :";
                                classNotFoundException2 = e4;
                                append = sb2.append(str);
                                classNotFoundException = classNotFoundException2;
                                Logger.e("CrashAgent", append.append(str2).toString(), classNotFoundException);
                                crashAgent2 = crashAgent;
                            } catch (InstantiationException e12) {
                                e3 = e12;
                                sb = new StringBuilder();
                                instantiationException = e3;
                                append = sb.append("Throw InstantiationException :");
                                classNotFoundException = instantiationException;
                                Logger.e("CrashAgent", append.append(str2).toString(), classNotFoundException);
                                crashAgent2 = crashAgent;
                            } catch (NoClassDefFoundError e13) {
                                e2 = e13;
                                sb = new StringBuilder();
                                instantiationException = e2;
                                append = sb.append("Throw InstantiationException :");
                                classNotFoundException = instantiationException;
                                Logger.e("CrashAgent", append.append(str2).toString(), classNotFoundException);
                                crashAgent2 = crashAgent;
                            }
                        } else {
                            crashAgent2.nextAgent = crashAgent;
                        }
                        Logger.i("CrashAgent", "Create the CrashAgent:" + str2 + " success");
                        crashAgent2 = crashAgent;
                    }
                }
            }
            if (f17520a == null) {
                Logger.i("CrashAgent", "Create default CrashAgent.");
                f17520a = new a();
                f17521b = true;
            }
            return f17520a;
        }
        throw new AssertionError();
    }

    public static CrashAgent getInstance() {
        if (f17522c || f17520a != null) {
            return f17520a;
        }
        throw new AssertionError();
    }

    public void addCustomCrashParams(String str) {
    }

    public void addTags(String str) {
    }

    public abstract void debug(String str, String str2);

    public abstract void error(String str, String str2);

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public abstract void info(String str, String str2);

    public boolean isUseDefault() {
        return f17521b;
    }

    public abstract void postException(String str, String str2, String str3);

    public abstract void postExceptionWithParam(String str, String str2, String str3, String str4, String str5);

    public abstract void putUserData(String str, String str2);

    public abstract void removeUserData(String str);

    public void setPatchVersion(String str) {
    }

    public abstract void setUserID(String str);

    public abstract void setUserSceneTag(int i2);

    public void uploadFileAtPath(String str, String str2, String str3, String str4, CrashListener crashListener) {
    }

    public abstract void verbose(String str, String str2);

    public abstract void warn(String str, String str2);
}
