package com.appsflyer.internal;

import java.util.Map;
/* loaded from: classes.dex */
public class AFc1fSDK {
    public static final byte[] $$a = null;
    public static final int $$b = 0;
    private static int $10 = 0;
    private static int $11 = 1;
    private static byte[] AFLogger$LogLevel;
    private static byte[] AFVersionDeclaration;
    private static byte[] AppsFlyer2dXConversionCallback;
    private static Object afErrorLogForExcManagerOnly;
    private static final Map<String, Object> afLogForce;
    public static final Map<Integer, Object> afRDLog;
    private static Object getLevel;
    private static int init;
    private static long onAppOpenAttributionNative;
    private static int onInstallConversionFailureNative;

    /* JADX WARN: Code restructure failed: missing block: B:11:0x003a, code lost:
        if ((r11 == null ? 'F' : '\n') != '\n') goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005c, code lost:
        if ((r11 != null) != true) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005e, code lost:
        r9 = r2 + 17;
        com.appsflyer.internal.AFc1fSDK.$11 = r9 % 128;
        r9 = r9 % 2;
        r2 = (r2 + 118) - 1;
        com.appsflyer.internal.AFc1fSDK.$11 = r2 % 128;
        r2 = r2 % 2;
        r2 = r10;
        r9 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0072, code lost:
        r9 = 0;
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0087  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x00a0 -> B:20:0x0073). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String $$c(int r9, short r10, short r11) {
        /*
            Method dump skipped, instructions count: 183
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFc1fSDK.$$c(int, short, short):java.lang.String");
    }

    public static int AFInAppEventParameterName(int i2) {
        int i3 = $11;
        int i4 = ((i3 | 13) << 1) - (i3 ^ 13);
        $10 = i4 % 128;
        int i5 = i4 % 2;
        Object obj = afErrorLogForExcManagerOnly;
        int i6 = ((i3 | 125) << 1) - (i3 ^ 125);
        int i7 = i6 % 128;
        $10 = i7;
        int i8 = i6 % 2;
        int i9 = (i7 & 75) + (i7 | 75);
        $11 = i9 % 128;
        int i10 = i9 % 2;
        try {
            Object[] objArr = {Integer.valueOf(i2)};
            byte[] bArr = $$a;
            int intValue = ((Integer) Class.forName($$c(bArr[225], (short) 523, bArr[6]), true, (ClassLoader) getLevel).getMethod($$c(bArr[176], (short) 335, bArr[225]), Integer.TYPE).invoke(obj, objArr)).intValue();
            int i11 = $11;
            int i12 = (i11 & 47) + (i11 | 47);
            $10 = i12 % 128;
            if ((i12 % 2 != 0 ? 'B' : '_') != 'B') {
                return intValue;
            }
            int i13 = 84 / 0;
            return intValue;
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause != null) {
                throw cause;
            }
            throw th;
        }
    }

    public static Object AFInAppEventType(int i2, char c2, int i3) {
        Object obj;
        int i4 = ($10 + 8) - 1;
        int i5 = i4 % 128;
        $11 = i5;
        if (i4 % 2 == 0) {
            obj = afErrorLogForExcManagerOnly;
            int i6 = 18 / 0;
        } else {
            obj = afErrorLogForExcManagerOnly;
        }
        int i7 = i5 + 31;
        $10 = i7 % 128;
        int i8 = i7 % 2;
        int i9 = ((i5 | 29) << 1) - (i5 ^ 29);
        $10 = i9 % 128;
        int i10 = i9 % 2;
        try {
            Object[] objArr = {Integer.valueOf(i2), Character.valueOf(c2), Integer.valueOf(i3)};
            byte[] bArr = $$a;
            Class<?> cls = Class.forName($$c(bArr[225], (short) 523, bArr[6]), true, (ClassLoader) getLevel);
            byte b2 = bArr[10];
            Object invoke = cls.getMethod($$c(b2, (short) ((b2 ^ 710) | (b2 & 710)), bArr[794]), Integer.TYPE, Character.TYPE, Integer.TYPE).invoke(obj, objArr);
            int i11 = $10;
            int i12 = (i11 & 43) + (i11 | 43);
            $11 = i12 % 128;
            if (!(i12 % 2 == 0)) {
                return invoke;
            }
            Object[] objArr2 = null;
            int length = objArr2.length;
            return invoke;
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause != null) {
                throw cause;
            }
            throw th;
        }
    }

    public static int AFKeystoreWrapper(Object obj) {
        int i2 = $10;
        int i3 = ((i2 | 113) << 1) - (i2 ^ 113);
        $11 = i3 % 128;
        int i4 = i3 % 2;
        Object obj2 = afErrorLogForExcManagerOnly;
        int i5 = (i2 + 92) - 1;
        $11 = i5 % 128;
        int i6 = i5 % 2;
        int i7 = i2 + 63;
        $11 = i7 % 128;
        int i8 = i7 % 2;
        try {
            Object[] objArr = {obj};
            byte[] bArr = $$a;
            Class<?> cls = Class.forName($$c(bArr[225], (short) 523, bArr[6]), true, (ClassLoader) getLevel);
            byte b2 = bArr[10];
            int intValue = ((Integer) cls.getMethod($$c(b2, (short) ((b2 ^ 710) | (b2 & 710)), bArr[794]), Object.class).invoke(obj2, objArr)).intValue();
            int i9 = $11;
            int i10 = (i9 ^ 57) + ((i9 & 57) << 1);
            $10 = i10 % 128;
            int i11 = i10 % 2;
            return intValue;
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause != null) {
                throw cause;
            }
            throw th;
        }
    }

    static void init$0() {
        int i2;
        int i3 = ($11 + 24) - 1;
        $10 = i3 % 128;
        if (!(i3 % 2 != 0)) {
            byte[] bArr = new byte[982];
            System.arraycopy("1_£KÍõ\u0003?Ïò\u0001þ\u000eûô\u0015ôDÇüû\u0010ô\u0005\u000eö>Ì8\nì\u00164ÆûBíÎ\u0010\u0001\u0002ô\u000e\u0002\u001cÜÿü\u0002\"à\u0003\u000e\u0005õ\nì\u00164Ã\fô\b:ÜÛ\u0007\u0000\u0010ùï\u0004\u0001\u000eøû4Ò\u0001\u0005\u0004\u0007\u0003î\fû\u0002\nì\u00164Á\u0006ûBíÊ\u0006\u0010$Î\u0010\u0001\u0002ôô\u000bó\u0004\u0007\u00067ÀýFíÎ\rþ\tAÏ\u0012ô\u0000\u000bû\u0002\nì\u00164ÆûBíÊ\u0006\u0010%Ð\u0001\u0012é1Üÿü\u0002\"à\u0003\u000e\u0005õ\u0004ö÷\u000eÿ>¼û\u0007\u0000\u0010ù@æÜ\"éùÿþú6àî6Ø\fï\u0001(Þ\u000fþ\u0000ô\u000e\u0005þ\u001fÒ\n\u0001ôõ\u0003?Ïò\u0001þ\u000eûô\u0015ôDÇüû\u0010ô\u0005\u000eö>ïüä3Ì\u0014\u0010ú(«\u0003ò/Þþ\bó0Üøü\u000b\u0000î*ê\u0006\nö\u0010\u0003ò2ãÿü\u0004\"Üø\u000e\u0005þ\u0004ö÷\u000eÿ>¼û\u0007\u0000\u0010ù@ëàî3Þþ\bõ\f\u0000\u0007ý\u0003ÿü\u0004\u0004ö÷\u000eÿ>¼û\u0007\u0000\u0010ù@íâï\u000f\"àî6Ø\fï\u0001(Þ\u000fþ\u0000ô\u0003ò2Õ\f\u0000#ãÿü\u0004\"Üøû\f\füþÜ.Ò\u0001,Ð\u0012øû!Ü\n\f\u0016ú\u0018ù»\u0000P»\u0006ö\u0001\u000b\u0002ÿùùTµ\b\u0000óLõ\u0003?Ïò\u0001þ\u000eûô\u0015ôDÇüû\u0010ô\u0005\u000eö>ïüä3É\u0017\u0010ú(\u0001\u0016û\u0017ù\u0016ý\u0015ù\u0016ù\u0019ù\nì\u00164Ã\fô\b:ìØ\fï\u0001(Þ\u000fþ\u0000ôô\u000bó\u0004\u0007\u00067Îò\u0001CîÒ\u0001*Üþ\u000e\u0002öú\u000fò#î\u0005þ\u0016â\u0003ô\nì\u00164ÆûBëäî\u0014\u0019Üÿü\u0002\"à\u0003\u000e\u0005õþ\u000fþ!àî\nì\u00164ÆûBíÊ\u0006\u0010%Ð\u0001\u0012é+Û\u0002\u0005ü\u0002\"à\u0003\u000e\u0005õõ\u0003@Îò\u0001þ\u000eûô\u0015ôEÆüû\u0010ô\u0005\u000eö?îüä3É\u0017\u0010ú(\u0001\t\u0003ú\u0003ò2Ø\fï\u0001(Þ\u000fþ\u0000ôÌ\u0004î\u00143Ì\u0004î\u00143\u0000ú\bò\u0010\u0003ò/\u0003\u0010úí\u0017üû\u000eî\fô\u0012\u001aä\bñ\u0012ðú*ðî\r$Ú\bù\tøû\u0002øþý\u000fÍõ\u0003?Ïò\u0001þ\u000eûô\u0015ôDÇüû\u0010ô\u0005\u000eö>Í7\nì\u00164ÆûB»\bþ\rüø\u0003ò%ß\u0004\u0000\fôÿü\u0003ò4àð\u0005\u0004ø\u0002\u0010\u0016ðî\rô\u000bó\u0004\u0007\u00067Îò\u0001Cîßð\fô\u000eöü&í÷\u000e\u0005þ\u0016öø\u0011\u0017ê\nì\u00164ÆûBéÞþ\b\u0017Û\u0002\u0005ü\u0002\"à\u0003\u000e\u0005õ\u0003ô\u0018æ\nö\u0010\nì\u00164ÆûBæû\u000bÎ\u0016ÿöý\fû\u0002ô\u000bó\u0004\u0007\u00067»\u0010î\u0005GÛðî\u0005 â\fþú\u0010î\r\u001dä÷\u0000\u0003ò,Ü\u0006ö\f\tö,Ò\u0001\u0005\u0004\u0007\u0003î\fû\u0002\nì\u00164ÆûBéÞþ\bõ\u0003þ\u0005\bî%æ*Õ\u0012ÿð\fû\u0002\u0016þ\u0014ùô\u000bó\u0004\u0007\u00067º\u0002\fþ?ÛÜ\n\f\u0002\u000fööø\u0011ï\u0004\u0001\u000eøû!ìý\t\u0019åþ\u0001\u0004÷\nì\u00164ÆûBéÞþ\b\"àó\u0011ò\núý\u0006þ\u0006.Ê\u0006\u0010%Ð\u0001\u0012éþ\u000fþ\"Ø\fï\u0001õ\u0003@Îò\u0001þ\u000eûô\u0015ô\u0006\u0012ò\u000eî\fô\u0012\u001aä\bñ\u0012ðú6Üø\u000e\u0003ð\u0006þ\n\u0005ó\nì\u00164ÆûBæÜÿü\u0002\"à\u0003\u000e\u0005õ".getBytes("ISO-8859-1"), 0, bArr, 0, 982);
            $$a = bArr;
            i2 = 55;
        } else {
            byte[] bArr2 = new byte[982];
            System.arraycopy("1_£KÍõ\u0003?Ïò\u0001þ\u000eûô\u0015ôDÇüû\u0010ô\u0005\u000eö>Ì8\nì\u00164ÆûBíÎ\u0010\u0001\u0002ô\u000e\u0002\u001cÜÿü\u0002\"à\u0003\u000e\u0005õ\nì\u00164Ã\fô\b:ÜÛ\u0007\u0000\u0010ùï\u0004\u0001\u000eøû4Ò\u0001\u0005\u0004\u0007\u0003î\fû\u0002\nì\u00164Á\u0006ûBíÊ\u0006\u0010$Î\u0010\u0001\u0002ôô\u000bó\u0004\u0007\u00067ÀýFíÎ\rþ\tAÏ\u0012ô\u0000\u000bû\u0002\nì\u00164ÆûBíÊ\u0006\u0010%Ð\u0001\u0012é1Üÿü\u0002\"à\u0003\u000e\u0005õ\u0004ö÷\u000eÿ>¼û\u0007\u0000\u0010ù@æÜ\"éùÿþú6àî6Ø\fï\u0001(Þ\u000fþ\u0000ô\u000e\u0005þ\u001fÒ\n\u0001ôõ\u0003?Ïò\u0001þ\u000eûô\u0015ôDÇüû\u0010ô\u0005\u000eö>ïüä3Ì\u0014\u0010ú(«\u0003ò/Þþ\bó0Üøü\u000b\u0000î*ê\u0006\nö\u0010\u0003ò2ãÿü\u0004\"Üø\u000e\u0005þ\u0004ö÷\u000eÿ>¼û\u0007\u0000\u0010ù@ëàî3Þþ\bõ\f\u0000\u0007ý\u0003ÿü\u0004\u0004ö÷\u000eÿ>¼û\u0007\u0000\u0010ù@íâï\u000f\"àî6Ø\fï\u0001(Þ\u000fþ\u0000ô\u0003ò2Õ\f\u0000#ãÿü\u0004\"Üøû\f\füþÜ.Ò\u0001,Ð\u0012øû!Ü\n\f\u0016ú\u0018ù»\u0000P»\u0006ö\u0001\u000b\u0002ÿùùTµ\b\u0000óLõ\u0003?Ïò\u0001þ\u000eûô\u0015ôDÇüû\u0010ô\u0005\u000eö>ïüä3É\u0017\u0010ú(\u0001\u0016û\u0017ù\u0016ý\u0015ù\u0016ù\u0019ù\nì\u00164Ã\fô\b:ìØ\fï\u0001(Þ\u000fþ\u0000ôô\u000bó\u0004\u0007\u00067Îò\u0001CîÒ\u0001*Üþ\u000e\u0002öú\u000fò#î\u0005þ\u0016â\u0003ô\nì\u00164ÆûBëäî\u0014\u0019Üÿü\u0002\"à\u0003\u000e\u0005õþ\u000fþ!àî\nì\u00164ÆûBíÊ\u0006\u0010%Ð\u0001\u0012é+Û\u0002\u0005ü\u0002\"à\u0003\u000e\u0005õõ\u0003@Îò\u0001þ\u000eûô\u0015ôEÆüû\u0010ô\u0005\u000eö?îüä3É\u0017\u0010ú(\u0001\t\u0003ú\u0003ò2Ø\fï\u0001(Þ\u000fþ\u0000ôÌ\u0004î\u00143Ì\u0004î\u00143\u0000ú\bò\u0010\u0003ò/\u0003\u0010úí\u0017üû\u000eî\fô\u0012\u001aä\bñ\u0012ðú*ðî\r$Ú\bù\tøû\u0002øþý\u000fÍõ\u0003?Ïò\u0001þ\u000eûô\u0015ôDÇüû\u0010ô\u0005\u000eö>Í7\nì\u00164ÆûB»\bþ\rüø\u0003ò%ß\u0004\u0000\fôÿü\u0003ò4àð\u0005\u0004ø\u0002\u0010\u0016ðî\rô\u000bó\u0004\u0007\u00067Îò\u0001Cîßð\fô\u000eöü&í÷\u000e\u0005þ\u0016öø\u0011\u0017ê\nì\u00164ÆûBéÞþ\b\u0017Û\u0002\u0005ü\u0002\"à\u0003\u000e\u0005õ\u0003ô\u0018æ\nö\u0010\nì\u00164ÆûBæû\u000bÎ\u0016ÿöý\fû\u0002ô\u000bó\u0004\u0007\u00067»\u0010î\u0005GÛðî\u0005 â\fþú\u0010î\r\u001dä÷\u0000\u0003ò,Ü\u0006ö\f\tö,Ò\u0001\u0005\u0004\u0007\u0003î\fû\u0002\nì\u00164ÆûBéÞþ\bõ\u0003þ\u0005\bî%æ*Õ\u0012ÿð\fû\u0002\u0016þ\u0014ùô\u000bó\u0004\u0007\u00067º\u0002\fþ?ÛÜ\n\f\u0002\u000fööø\u0011ï\u0004\u0001\u000eøû!ìý\t\u0019åþ\u0001\u0004÷\nì\u00164ÆûBéÞþ\b\"àó\u0011ò\núý\u0006þ\u0006.Ê\u0006\u0010%Ð\u0001\u0012éþ\u000fþ\"Ø\fï\u0001õ\u0003@Îò\u0001þ\u000eûô\u0015ô\u0006\u0012ò\u000eî\fô\u0012\u001aä\bñ\u0012ðú6Üø\u000e\u0003ð\u0006þ\n\u0005ó\nì\u00164ÆûBæÜÿü\u0002\"à\u0003\u000e\u0005õ".getBytes("ISO-8859-1"), 0, bArr2, 0, 982);
            $$a = bArr2;
            i2 = 125;
        }
        $$b = i2;
        int i4 = $10;
        int i5 = (i4 ^ 71) + ((i4 & 71) << 1);
        $11 = i5 % 128;
        int i6 = i5 % 2;
    }

    private AFc1fSDK() {
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:701:0x175c
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    static {
        /*
            Method dump skipped, instructions count: 6768
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFc1fSDK.<clinit>():void");
    }
}
