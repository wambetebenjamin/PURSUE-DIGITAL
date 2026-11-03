package com.bytedance.common.utility.android;

import android.util.Pair;
import com.bytedance.common.utility.i;
import com.bytedance.common.utility.n;
import com.bytedance.common.utility.o;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: ContactsUtil.java */
/* loaded from: classes2.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5184a = "ContactsUtil";

    /* renamed from: b  reason: collision with root package name */
    private static final Pattern f5185b = Pattern.compile("[^+0-9]");

    private c() {
    }

    /* compiled from: ContactsUtil.java */
    /* loaded from: classes2.dex */
    public static class a {
        public String contactId;
        public String contactName;
        public List<String> phoneNumbers = new ArrayList();

        public a() {
        }

        public a(String str, String str2) {
            this.contactId = str;
            this.contactName = str2;
        }

        public JSONObject toNumberJsonWithEncrypt() {
            if (o.isEmpty(this.contactName) || this.phoneNumbers.isEmpty() || this.phoneNumbers.size() == 0 || o.isEmpty(this.contactId)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                JSONArray jSONArray = new JSONArray();
                for (String str : this.phoneNumbers) {
                    String encryptBySHA256 = n.encryptBySHA256(str);
                    if (!o.isEmpty(encryptBySHA256)) {
                        jSONArray.put(encryptBySHA256);
                    }
                }
                jSONObject.put("mobiles", jSONArray);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            return jSONObject;
        }

        public JSONObject toJson() {
            if (o.isEmpty(this.contactName) || i.isEmpty(this.phoneNumbers) || o.isEmpty(this.contactId)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("contact_name", this.contactName);
                jSONObject.put("contact_id", this.contactId);
                JSONArray jSONArray = new JSONArray();
                for (String str : this.phoneNumbers) {
                    jSONArray.put(str);
                }
                jSONObject.put("contact_number", jSONArray);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            return jSONObject;
        }
    }

    private static String a(String str, List<Pair<Pattern, String>> list) {
        if (list != null && list.size() != 0 && !o.isEmpty(str)) {
            String replaceAll = f5185b.matcher(str).replaceAll("");
            for (Pair<Pattern, String> pair : list) {
                Matcher matcher = ((Pattern) pair.first).matcher(replaceAll);
                if (matcher.matches()) {
                    return matcher.replaceAll((String) pair.second);
                }
            }
        }
        return null;
    }
}
