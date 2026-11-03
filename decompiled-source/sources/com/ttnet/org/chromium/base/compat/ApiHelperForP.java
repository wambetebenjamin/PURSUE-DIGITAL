package com.ttnet.org.chromium.base.compat;

import android.content.ClipboardManager;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.LinkProperties;
import android.os.LocaleList;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.view.textclassifier.TextClassifier;
import android.view.textclassifier.TextSelection;
/* loaded from: classes9.dex */
public final class ApiHelperForP {
    public static void clearPrimaryClip(ClipboardManager clipboardManager) {
    }

    private ApiHelperForP() {
    }

    public static boolean isPrivateDnsActive(LinkProperties linkProperties) {
        return linkProperties.isPrivateDnsActive();
    }

    public static String getPrivateDnsServerName(LinkProperties linkProperties) {
        return linkProperties.getPrivateDnsServerName();
    }

    public static long getLongVersionCode(PackageInfo packageInfo) {
        return packageInfo.getLongVersionCode();
    }

    public static boolean isLocationEnabled(LocationManager locationManager) {
        return locationManager.isLocationEnabled();
    }

    public static SignalStrength getSignalStrength(TelephonyManager telephonyManager) {
        return telephonyManager.getSignalStrength();
    }

    public static boolean hasSigningCertificate(PackageManager packageManager, String str, byte[] bArr, int i2) {
        return packageManager.hasSigningCertificate(str, bArr, i2);
    }

    public static TextSelection suggestSelection(TextClassifier textClassifier, TextSelection.Request request) {
        return textClassifier.suggestSelection(request);
    }

    public static TextSelection.Request.Builder newTextSelectionRequestBuilder(CharSequence charSequence, int i2, int i3) {
        return new TextSelection.Request.Builder(charSequence, i2, i3);
    }

    public static TextSelection.Request.Builder setDefaultLocales(TextSelection.Request.Builder builder, LocaleList localeList) {
        return builder.setDefaultLocales(localeList);
    }

    public static TextSelection.Request build(TextSelection.Request.Builder builder) {
        return builder.build();
    }
}
