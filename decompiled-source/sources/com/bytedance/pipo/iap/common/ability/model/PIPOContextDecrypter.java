package com.bytedance.pipo.iap.common.ability.model;

import android.util.Base64;
import com.bytedance.pipo.iap.common.ability.monitor.b;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONObject;
/* compiled from: PIPOContextDecrypter.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/bytedance/pipo/iap/common/ability/model/PIPOContextDecrypter;", "", "()V", "Companion", "iap_common_ability_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PIPOContextDecrypter {
    public static final Companion Companion = new Companion(null);
    private static final String DECRYPT_CONTEXT_IS_EMPTY = "2";
    private static final String DECRYPT_FAIL = "0";
    private static final String DECRYPT_SUCCESS = "1";
    private static final String KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC9+vioH5puDAzIPjrZGwyIYIGmuSmJe+Q2yJbOqgs8x++OSC3Og8m/0Ii6VDMsAt5jYo4lvXdLoYyE/BYHQlgTSpoH07HNQ1wqWgF9mhKEgPV2nRSNCbgxKtcS6nPCSA/r/9QjGfL1Hmmtn5J0lejjT+qIZplXkU4IdYtu+8RdXwIDAQAB";
    private static Cipher cipher = null;
    private static final String splitter = ",,";

    @JvmStatic
    private static final DecryptResult decryptPipoContext(String str) {
        return Companion.decryptPipoContext(str);
    }

    @JvmStatic
    public static final DecryptResult parsePipoContext(String str) {
        return Companion.parsePipoContext(str);
    }

    /* compiled from: PIPOContextDecrypter.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H\u0003J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/bytedance/pipo/iap/common/ability/model/PIPOContextDecrypter$Companion;", "", "()V", "DECRYPT_CONTEXT_IS_EMPTY", "", "DECRYPT_FAIL", "DECRYPT_SUCCESS", "KEY", "cipher", "Ljavax/crypto/Cipher;", "splitter", "decryptPipoContext", "Lcom/bytedance/pipo/iap/common/ability/model/DecryptResult;", "encryptedPipoContext", "initCipher", "", "parsePipoContext", "paramsJson", "iap_common_ability_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final void initCipher() {
            PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(PIPOContextDecrypter.KEY, 0)));
            PIPOContextDecrypter.cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            Cipher cipher = PIPOContextDecrypter.cipher;
            if (cipher == null) {
                return;
            }
            cipher.init(2, generatePublic);
        }

        @JvmStatic
        public final DecryptResult parsePipoContext(String paramsJson) {
            String message;
            Intrinsics.checkNotNullParameter(paramsJson, "paramsJson");
            long currentTimeMillis = System.currentTimeMillis();
            String str = "0";
            try {
                String pipoContextEncrypt = new JSONObject(paramsJson).optString("pipo_context_encrypt");
                Intrinsics.checkNotNullExpressionValue(pipoContextEncrypt, "pipoContextEncrypt");
                boolean z = true;
                if (pipoContextEncrypt.length() > 0) {
                    try {
                        DecryptResult decryptPipoContext = decryptPipoContext(pipoContextEncrypt);
                        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                        if (decryptPipoContext.getDecryptedPipoContext().length() <= 0) {
                            z = false;
                        }
                        if (z) {
                            b.f10650a.a(decryptPipoContext.getDecryptedPipoContext(), "1", "", currentTimeMillis2);
                            return new DecryptResult(decryptPipoContext.getDecryptedPipoContext(), "");
                        }
                        message = decryptPipoContext.getErrMsg();
                    } catch (Exception e2) {
                        e = e2;
                        message = e.getMessage();
                        if (message == null) {
                            message = "parse exception";
                        }
                        b.f10650a.a(paramsJson, str, message, System.currentTimeMillis() - currentTimeMillis);
                        return new DecryptResult("", message);
                    }
                } else {
                    str = "2";
                    message = "pipo_context_encrypt is empty";
                }
            } catch (Exception e3) {
                e = e3;
            }
            b.f10650a.a(paramsJson, str, message, System.currentTimeMillis() - currentTimeMillis);
            return new DecryptResult("", message);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final DecryptResult decryptPipoContext(String str) {
            try {
                if (PIPOContextDecrypter.cipher == null) {
                    initCipher();
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                for (String str2 : StringsKt.split$default((CharSequence) str, new String[]{PIPOContextDecrypter.splitter}, false, 0, 6, (Object) null)) {
                    byte[] decode = Base64.decode(str2, 0);
                    Cipher cipher = PIPOContextDecrypter.cipher;
                    byte[] doFinal = cipher == null ? null : cipher.doFinal(decode);
                    if (doFinal != null) {
                        byteArrayOutputStream.write(doFinal);
                    }
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                Intrinsics.checkNotNullExpressionValue(byteArray, "outputStream.toByteArray()");
                return new DecryptResult(new String(byteArray, Charsets.UTF_8), "");
            } catch (Exception e2) {
                return new DecryptResult("", Intrinsics.stringPlus("pipo_context decrypt exception ", e2));
            }
        }
    }
}
