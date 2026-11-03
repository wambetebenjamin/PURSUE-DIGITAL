package com.oasis.ui.wechatsimulation;

import android.app.Activity;
import android.content.Intent;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityManager;
import com.oasis.android.ICommonCallbackWithJudge;
import com.oasis.utils.Utils;
import java.math.BigDecimal;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class WeChatSimulationAgent {

    /* renamed from: a  reason: collision with root package name */
    private static volatile boolean f17745a = false;

    /* renamed from: b  reason: collision with root package name */
    private static ICommonCallbackWithJudge f17746b;

    /* renamed from: c  reason: collision with root package name */
    static Class<?> f17747c;

    /* renamed from: d  reason: collision with root package name */
    static String f17748d;

    /* renamed from: e  reason: collision with root package name */
    static String f17749e;

    /* renamed from: f  reason: collision with root package name */
    static BigDecimal f17750f;

    /* renamed from: g  reason: collision with root package name */
    static BigDecimal f17751g;

    /* renamed from: h  reason: collision with root package name */
    static String f17752h;

    /* renamed from: i  reason: collision with root package name */
    static String f17753i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(int i2, String str) {
        if (str == null) {
            str = "";
        }
        Utils.JsonStringBuilder jsonStringBuilder = new Utils.JsonStringBuilder(32);
        jsonStringBuilder.append("code", i2);
        jsonStringBuilder.append("message", str);
        return jsonStringBuilder.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(boolean z, String str) {
        f17745a = false;
        Logger.d("WeChatSimulationAgent", "invokePayCallback: isSuccess == " + z + ", message == " + str);
        f17746b.invoke(z, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a() {
        return f17750f.compareTo(f17751g) <= 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(String str) {
        String str2 = f17752h;
        if (str2 == null || "".equals(str2)) {
            return true;
        }
        return f17752h.equals(str);
    }

    private static boolean b(String str) {
        String str2 = "param 'remain' in json has an wrong type, expect type is Float String!";
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("payee_name")) {
                try {
                    f17749e = jSONObject.getString("payee_name");
                    if (jSONObject.has("item_description")) {
                        try {
                            f17748d = jSONObject.getString("item_description");
                            if (jSONObject.has("item_price")) {
                                try {
                                    f17750f = new BigDecimal(jSONObject.getString("item_price"));
                                    if (jSONObject.has("remain")) {
                                        try {
                                            f17751g = new BigDecimal(jSONObject.getString("remain"));
                                            if (jSONObject.has("password")) {
                                                try {
                                                    String string = jSONObject.getString("password");
                                                    try {
                                                        Integer.valueOf(string);
                                                        int length = string.length();
                                                        if (length < 6) {
                                                            String str3 = "The length of param 'password' in json is too short, expect length is 6, now length is " + length + "!";
                                                            Logger.e("WeChatSimulationAgent", "parsePayParams: " + str3 + "!!!!!!!!!!!!!!!!!!!!!!");
                                                            a(false, a(-6, str3));
                                                            return false;
                                                        } else if (length > 6) {
                                                            String str4 = "The length of param 'password' in json is too long, expect length is 6, now length is " + length + "!";
                                                            Logger.e("WeChatSimulationAgent", "parsePayParams: " + str4 + "!!!!!!!!!!!!!!!!!!!!!!");
                                                            a(false, a(-7, str4));
                                                            return false;
                                                        } else {
                                                            f17752h = string;
                                                        }
                                                    } catch (Exception e2) {
                                                        e2.printStackTrace();
                                                        Logger.e("WeChatSimulationAgent", "parsePayParams: param 'password' in json has an wrong type, expect type is Integer String!!!!!!!!!!!!!!!!!!!!!!!");
                                                        a(false, a(-3, "param 'password' in json has an wrong type, expect type is Integer String!"));
                                                        return false;
                                                    }
                                                } catch (Exception e3) {
                                                    e3.printStackTrace();
                                                    Logger.e("WeChatSimulationAgent", "parsePayParams: param 'password' in json has an wrong type, expect type is Integer String!!!!!!!!!!!!!!!!!!!!!!!");
                                                    str2 = "param 'password' in json has an wrong type, expect type is Integer String!";
                                                }
                                            } else {
                                                Logger.w("WeChatSimulationAgent", "no password set!!!!!!");
                                                f17752h = "";
                                            }
                                            if (!jSONObject.has("remain_not_enough_tip_text")) {
                                                Logger.w("WeChatSimulationAgent", "parsePayParams: not found param 'remain_not_enough_tip_text' in json, oasis sdk will use default value!!!!!!!!");
                                                f17753i = "支付失败!";
                                                return true;
                                            }
                                            try {
                                                f17753i = jSONObject.getString("remain_not_enough_tip_text");
                                                return true;
                                            } catch (Exception e4) {
                                                e4.printStackTrace();
                                                Logger.e("WeChatSimulationAgent", "parsePayParams: param 'remain_not_enough_tip_text' in json has an wrong type, expect type is String!!!!!!!!!!!!!!!!!!!!!!!");
                                                a(false, a(-3, "param 'remain_not_enough_tip_text' in json has an wrong type, expect type is String!"));
                                                return true;
                                            }
                                        } catch (Exception e5) {
                                            e5.printStackTrace();
                                            Logger.e("WeChatSimulationAgent", "parsePayParams: param 'remain' in json has an wrong type, expect type is Float String!!!!!!!!!!!!!!!!!!!!!!!");
                                        }
                                    } else {
                                        str2 = "param 'remain' not found in json!";
                                    }
                                } catch (Exception e6) {
                                    e6.printStackTrace();
                                    Logger.e("WeChatSimulationAgent", "parsePayParams: param 'item_price' in json has an wrong type, expect type is Float String!!!!!!!!!!!!!!!!!!!!!!!");
                                    str2 = "param 'item_price' in json has an wrong type, expect type is Float String!";
                                }
                            } else {
                                str2 = "param 'item_price' not found in json!";
                            }
                        } catch (Exception e7) {
                            e7.printStackTrace();
                            Logger.e("WeChatSimulationAgent", "parsePayParams: param 'item_description' in json has an wrong type, expect type is String!!!!!!!!!!!!!!!!!!!!!!!");
                            str2 = "param 'item_description' in json has an wrong type, expect type is String!";
                        }
                    } else {
                        str2 = "param 'item_description' not found in json!";
                    }
                } catch (Exception e8) {
                    e8.printStackTrace();
                    Logger.e("WeChatSimulationAgent", "parsePayParams: param 'payee_name' in json has an wrong type, expect type is String!!!!!!!!!!!!!!!!!!!!!!!");
                    str2 = "param 'payee_name' in json has an wrong type, expect type is String!";
                }
            } else {
                str2 = "param 'payee_name' not found in json!";
            }
        } catch (Exception e9) {
            e9.printStackTrace();
            Logger.e("WeChatSimulationAgent", "parsePayParams: has an exception when parse json!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n" + e9);
            str2 = "catch an exception when parse json: " + e9.toString();
        }
        a(false, a(-3, str2));
        return false;
    }

    public static void pay(Activity activity, String str) {
        if (f17745a) {
            f17746b.invoke(false, a(-2, "you cannot pay repeat while last pay not finish!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"));
        } else if (activity == null) {
            a(false, a(-5, "activity param is null, please double check params!"));
        } else {
            f17747c = activity.getClass();
            f17745a = true;
            if (b(str)) {
                activity.startActivity(new Intent(activity, PayMainActivity.class));
            }
        }
    }

    public static void pay(String str) {
        pay(ActivityManager.getActivity(), str);
    }

    public static void setPayCallback(ICommonCallbackWithJudge iCommonCallbackWithJudge) {
        f17746b = iCommonCallbackWithJudge;
    }
}
