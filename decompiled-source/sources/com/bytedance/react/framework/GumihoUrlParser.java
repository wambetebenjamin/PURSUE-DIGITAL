package com.bytedance.react.framework;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
/* loaded from: classes2.dex */
public class GumihoUrlParser {
    private String channel;
    private String host;
    private String moduleName;
    private Bundle paramBundle;
    private boolean valid;

    public String getChannel() {
        return this.channel;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public String getHost() {
        return this.host;
    }

    public Bundle getParamBundle() {
        return this.paramBundle;
    }

    public boolean isValid() {
        return this.valid;
    }

    public static Bundle parseParams(String str) {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : str.split("&")) {
                String[] split = str2.split("=");
                if (split.length >= 2) {
                    bundle.putString(split[0], split[1]);
                }
            }
        }
        return bundle;
    }

    public GumihoUrlParser(Context context, String str) {
        this.valid = false;
        if (TextUtils.isEmpty(str)) {
            this.valid = false;
            return;
        }
        Uri parse = Uri.parse(str);
        if (parse == null) {
            this.valid = false;
            return;
        }
        String[] split = str.split("\\?");
        this.host = parse.getHost();
        if (split.length >= 2) {
            this.paramBundle = new Bundle();
            Bundle parseParams = parseParams(split[1]);
            this.channel = parseParams.getString(RNConfig.RN_COMPONENT);
            this.moduleName = parseParams.getString("rctModuleName");
            try {
                String decode = URLDecoder.decode(parseParams.getString("rctModuleParams", ""), "UTF-8");
                if (decode != null) {
                    this.paramBundle = RNUtil.convertJsonToBundle(decode);
                }
                if (this.paramBundle == null) {
                    this.paramBundle = new Bundle();
                }
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        if (this.paramBundle == null) {
            this.paramBundle = new Bundle();
        }
        this.valid = true;
    }
}
