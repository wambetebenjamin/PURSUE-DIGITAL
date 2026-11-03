package com.bytedance.crash.util;

import com.meituan.robust.Constants;
import java.util.Arrays;
/* compiled from: NumRanges.java */
/* loaded from: classes2.dex */
public class o {

    /* renamed from: a  reason: collision with root package name */
    Long[] f7308a;

    /* renamed from: b  reason: collision with root package name */
    String f7309b;

    public o(String str) {
        this.f7309b = str;
    }

    public String a(long j2) {
        long j3;
        Long[] lArr = this.f7308a;
        if (lArr == null) {
            return "";
        }
        int length = lArr.length;
        int i2 = 0;
        long j4 = Long.MIN_VALUE;
        while (true) {
            if (i2 >= length) {
                j3 = Long.MAX_VALUE;
                break;
            }
            Long l = lArr[i2];
            if (j2 >= l.longValue()) {
                j4 = l.longValue();
                i2++;
            } else {
                j3 = l.longValue() - 1;
                break;
            }
        }
        String str = Constants.ARRAY_TYPE;
        if (j4 != Long.MIN_VALUE) {
            str = Constants.ARRAY_TYPE + j4 + this.f7309b;
        }
        String str2 = str + " - ";
        if (j3 != Long.MAX_VALUE) {
            str2 = str2 + j3 + this.f7309b;
        }
        return str2 + "]";
    }

    public void a(Long[] lArr) {
        Arrays.sort(lArr);
        this.f7308a = lArr;
    }

    public void a(long j2, long j3, long j4) {
        int i2 = ((int) ((j3 - j2) / j4)) + 1;
        Long[] lArr = new Long[i2];
        int i3 = 0;
        while (true) {
            int i4 = i2 - 1;
            if (i3 < i4) {
                lArr[i3] = Long.valueOf((i3 * j4) + j2);
                i3++;
            } else {
                lArr[i4] = Long.valueOf(j3);
                a(lArr);
                return;
            }
        }
    }
}
