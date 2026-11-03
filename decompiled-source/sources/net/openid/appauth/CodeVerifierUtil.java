package net.openid.appauth;

import android.util.Base64;
import com.bytedance.common.utility.n;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Pattern;
import net.openid.appauth.internal.Logger;
/* loaded from: classes10.dex */
public final class CodeVerifierUtil {
    public static final int DEFAULT_CODE_VERIFIER_ENTROPY = 64;
    public static final int MAX_CODE_VERIFIER_ENTROPY = 96;
    public static final int MAX_CODE_VERIFIER_LENGTH = 128;
    public static final int MIN_CODE_VERIFIER_ENTROPY = 32;
    public static final int MIN_CODE_VERIFIER_LENGTH = 43;
    private static final int PKCE_BASE64_ENCODE_SETTINGS = 11;
    private static final Pattern REGEX_CODE_VERIFIER = Pattern.compile("^[0-9a-zA-Z\\-\\.\\_\\~]{43,128}$");

    private CodeVerifierUtil() {
        throw new IllegalStateException("This type is not intended to be instantiated");
    }

    public static void checkCodeVerifier(String str) {
        Preconditions.checkArgument(43 <= str.length(), "codeVerifier length is shorter than allowed by the PKCE specification");
        Preconditions.checkArgument(str.length() <= 128, "codeVerifier length is longer than allowed by the PKCE specification");
        Preconditions.checkArgument(REGEX_CODE_VERIFIER.matcher(str).matches(), "codeVerifier string contains illegal characters");
    }

    public static String generateRandomCodeVerifier() {
        return generateRandomCodeVerifier(new SecureRandom(), 64);
    }

    public static String generateRandomCodeVerifier(SecureRandom secureRandom, int i2) {
        Preconditions.checkNotNull(secureRandom, "entropySource cannot be null");
        Preconditions.checkArgument(32 <= i2, "entropyBytes is less than the minimum permitted");
        Preconditions.checkArgument(i2 <= 96, "entropyBytes is greater than the maximum permitted");
        byte[] bArr = new byte[i2];
        secureRandom.nextBytes(bArr);
        return Base64.encodeToString(bArr, 11);
    }

    public static String deriveCodeVerifierChallenge(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(n.SHA_256);
            messageDigest.update(str.getBytes("ISO_8859_1"));
            return Base64.encodeToString(messageDigest.digest(), 11);
        } catch (UnsupportedEncodingException e2) {
            Logger.error("ISO-8859-1 encoding not supported on this device!", e2);
            throw new IllegalStateException("ISO-8859-1 encoding not supported", e2);
        } catch (NoSuchAlgorithmException e3) {
            Logger.warn("SHA-256 is not supported on this device! Using plain challenge", e3);
            return str;
        }
    }

    public static String getCodeVerifierChallengeMethod() {
        try {
            MessageDigest.getInstance(n.SHA_256);
            return AuthorizationRequest.CODE_CHALLENGE_METHOD_S256;
        } catch (NoSuchAlgorithmException unused) {
            return AuthorizationRequest.CODE_CHALLENGE_METHOD_PLAIN;
        }
    }
}
