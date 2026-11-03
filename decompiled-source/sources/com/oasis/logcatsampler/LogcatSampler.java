package com.oasis.logcatsampler;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.bytedance.librarian.c;
import com.facebook.internal.security.CertificateUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
/* loaded from: classes9.dex */
public class LogcatSampler {
    private static final String timePatten = "yyyy-MM-dd HH:mm:ss.SSS";
    private LogcatProxy proxy;
    private SimpleDateFormat format = new SimpleDateFormat(timePatten);
    private Thread samplerThread = new Thread(new Runnable() { // from class: com.oasis.logcatsampler.LogcatSampler.1
        @Override // java.lang.Runnable
        public void run() {
            LogcatSampler.this.sampler();
        }
    });

    public void init(LogcatProxy logcatProxy) {
        this.proxy = logcatProxy;
    }

    public void start() {
        this.samplerThread.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sampler() {
        String str;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("logcat -v tag *:i").getInputStream()));
            int i2 = 0;
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return;
                }
                if (readLine.length() > 0) {
                    i2++;
                    String[] split = readLine.split(CertificateUtil.DELIMITER, 2);
                    String str2 = ExifInterface.LONGITUDE_EAST;
                    if (split == null || split.length <= 1) {
                        str = "SampleErrorBy':'";
                    } else {
                        readLine = split[1];
                        String[] split2 = split[0].split(c.a.SEPARATOR);
                        if (split2 == null || split2.length <= 1) {
                            str = "SampleErrorBy'/'";
                        } else {
                            str2 = split2[0];
                            str = split2[1];
                        }
                    }
                    String format = this.format.format(new Date());
                    String str3 = i2 + "\t" + readLine;
                    LogcatProxy logcatProxy = this.proxy;
                    if (format == null) {
                        format = "null time";
                    }
                    if (str2 == null) {
                        str2 = "null priority";
                    }
                    if (str == null) {
                        str = "null tag";
                    }
                    if (str3 == null) {
                        str3 = "null info";
                    }
                    logcatProxy.log(format, str2, str, str3);
                }
            }
        } catch (IOException unused) {
            Log.e("oasis", "something wrong happened when sampling logcat.");
        }
    }
}
