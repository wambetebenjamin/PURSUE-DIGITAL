package com.bytedance.frameworks.baselib.network.queryfilter;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.bytedance.common.utility.Logger;
import com.bytedance.frameworks.encryptor.EncryptorUtil;
import com.bytedance.retrofit2.client.Request;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: QueryEncryptAction.java */
/* loaded from: classes2.dex */
public class b extends c {

    /* renamed from: a  reason: collision with root package name */
    protected static final String f8391a = "x-tt-encrypt-queries";

    /* renamed from: f  reason: collision with root package name */
    private static final String f8392f = "b";

    /* renamed from: g  reason: collision with root package name */
    private static final String f8393g = "&";

    /* renamed from: h  reason: collision with root package name */
    private static final String f8394h = "=";

    /* renamed from: c  reason: collision with root package name */
    private List<String> f8395c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f8396d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f8397e;

    public b(int i2) {
        super(i2);
        this.f8395c = new ArrayList();
        this.f8396d = false;
        this.f8397e = false;
    }

    @Override // com.bytedance.frameworks.baselib.network.queryfilter.c
    protected void a(JSONObject jSONObject) {
        b(jSONObject);
        d.a(jSONObject.optJSONArray("encrypt_query_list"), this.f8395c);
        this.f8397e = jSONObject.optInt("encrypt_body_enabled", 0) > 0;
        if (!this.f8395c.isEmpty() || this.f8397e) {
            return;
        }
        this.f8396d = true;
    }

    @Override // com.bytedance.frameworks.baselib.network.queryfilter.c
    protected boolean a(Request request, Map<String, List<String>> map) {
        byte[] encrypt;
        boolean z = false;
        if (!this.f8396d && a(request)) {
            if (this.f8397e) {
                request.setBodyEncryptEnabled(true);
                z = true;
            }
            if (map.isEmpty()) {
                return z;
            }
            if (!this.f8395c.isEmpty() && !map.containsKey(f8391a)) {
                LinkedList linkedList = new LinkedList();
                for (String str : this.f8395c) {
                    if (map.containsKey(str)) {
                        linkedList.add(new Pair(str, map.get(str)));
                    }
                }
                if (linkedList.isEmpty()) {
                    return z;
                }
                String a2 = a(linkedList);
                if (TextUtils.isEmpty(a2)) {
                    return z;
                }
                byte[] bArr = null;
                try {
                    bArr = a2.getBytes("UTF-8");
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
                if (bArr == null || (encrypt = EncryptorUtil.encrypt(bArr, bArr.length)) == null) {
                    return z;
                }
                String encodeToString = Base64.encodeToString(encrypt, 2);
                if (Logger.debug()) {
                    Logger.d(f8392f, "encryptBytes:" + encrypt + " , encryptString:" + encodeToString);
                }
                LinkedList linkedList2 = new LinkedList();
                linkedList2.add(encodeToString);
                request.setQueryEncryptEnabled(true);
                map.put(f8391a, linkedList2);
                for (String str2 : this.f8395c) {
                    map.remove(str2);
                }
                return true;
            }
        }
        return z;
    }

    private static String a(List<Pair<String, List<String>>> list) {
        StringBuilder sb = new StringBuilder();
        for (Pair<String, List<String>> pair : list) {
            String str = (String) pair.first;
            List<String> list2 = (List) pair.second;
            if (list2 != null && list2.size() > 0) {
                for (String str2 : list2) {
                    if (sb.length() > 0) {
                        sb.append(f8393g);
                    }
                    sb.append(str);
                    sb.append(f8394h);
                    sb.append(str2);
                }
            }
        }
        return sb.toString();
    }
}
