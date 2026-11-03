package com.bytedancehttpdns.httpdns.util;

import java.util.regex.Pattern;
/* loaded from: classes8.dex */
public class IPAddressUtils {
    private static Pattern ipv4Pattern = Pattern.compile("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$");
    private static final Pattern IPV6_STD_PATTERN = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");
    private static final Pattern IPV6_HEX_COMPRESSED_PATTERN = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");

    public static boolean isIpv6StdAddress(String str) {
        return IPV6_STD_PATTERN.matcher(str).matches();
    }

    public static boolean isIpv6HexCompressedAddress(String str) {
        return IPV6_HEX_COMPRESSED_PATTERN.matcher(str).matches();
    }

    public static boolean isValidIpv6(String str) {
        return isIpv6StdAddress(str) || isIpv6HexCompressedAddress(str);
    }

    public static boolean isValidIpv4(String str) {
        int i2;
        int indexOf;
        if (str != null) {
            try {
                if (str.length() >= 7 && str.length() <= 15 && !str.equals("")) {
                    if (ipv4Pattern.matcher(str).matches()) {
                        return true;
                    }
                }
            } catch (Throwable unused) {
                try {
                    int indexOf2 = str.indexOf(46);
                    if (indexOf2 != -1 && str.charAt(0) != '-' && Integer.parseInt(str.substring(0, indexOf2)) <= 255 && (indexOf = str.indexOf(46, (i2 = indexOf2 + 1))) != -1 && str.charAt(i2) != '-' && Integer.parseInt(str.substring(i2, indexOf)) <= 255) {
                        int i3 = indexOf + 1;
                        int indexOf3 = str.indexOf(46, i3);
                        if (indexOf3 != -1 && str.charAt(i3) != '-' && Integer.parseInt(str.substring(i3, indexOf3)) <= 255) {
                            int i4 = indexOf3 + 1;
                            if (str.charAt(i4) != '-' && Integer.parseInt(str.substring(i4, str.length())) <= 255) {
                                if (str.charAt(str.length() - 1) != '.') {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }
                } catch (Exception unused2) {
                }
                return false;
            }
        }
        return false;
    }
}
