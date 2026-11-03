package com.bytedance.crash.dumper;

import com.bytedance.crash.Global;
import com.bytedance.crash.util.y;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
/* compiled from: ProtectorInfo.java */
/* loaded from: classes2.dex */
public class p {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6587a = "protector_history.txt";

    /* renamed from: b  reason: collision with root package name */
    private static final String f6588b = "NPTH_PROT";

    /* renamed from: c  reason: collision with root package name */
    private File f6589c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f6590d = false;

    /* renamed from: e  reason: collision with root package name */
    private String f6591e = null;

    /* renamed from: f  reason: collision with root package name */
    private int f6592f = 0;

    /* renamed from: g  reason: collision with root package name */
    private int f6593g = 0;

    /* renamed from: h  reason: collision with root package name */
    private boolean f6594h = false;

    /* renamed from: i  reason: collision with root package name */
    private boolean f6595i = false;

    /* renamed from: j  reason: collision with root package name */
    private String f6596j = null;
    private String k = null;
    private String l = null;
    private String m = null;

    public p(File file) {
        this.f6589c = null;
        this.f6589c = file;
    }

    public boolean a() {
        long j2;
        long parseLong;
        long parseLong2;
        int parseInt;
        int parseInt2;
        String str;
        int i2;
        char c2;
        boolean z = false;
        z = false;
        if (this.f6589c == null) {
            return false;
        }
        File file = new File(Global.e(), f6587a);
        if (!file.exists() || file.length() == 0) {
            return false;
        }
        try {
            String[] split = this.f6589c.getName().split("-");
            if (split.length == 1) {
                j2 = Long.parseLong(split[0]);
                File parentFile = this.f6589c.getParentFile();
                if (parentFile != null) {
                    split = parentFile.getName().split("-");
                }
            } else {
                j2 = 0;
            }
            char c3 = 3;
            char c4 = 2;
            if (split.length == 2 || split.length == 3) {
                long parseLong3 = Long.parseLong(split[0]);
                int parseInt3 = Integer.parseInt(split[1]);
                if (split.length == 3) {
                    this.f6592f = Integer.parseInt(split[2]);
                    this.f6594h = true;
                }
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String readLine = bufferedReader.readLine();
                if (readLine != null && readLine.equals("V002")) {
                    StringBuilder sb = null;
                    while (true) {
                        String readLine2 = bufferedReader.readLine();
                        if (readLine2 == null) {
                            try {
                                break;
                            } catch (Throwable unused) {
                                return false;
                            }
                        }
                        String[] split2 = readLine2.split(" ");
                        if (split2.length == 5) {
                            try {
                                parseLong = Long.parseLong(split2[z ? 1 : 0]);
                                parseLong2 = Long.parseLong(split2[1]);
                                parseInt = Integer.parseInt(split2[c4]);
                                parseInt2 = Integer.parseInt(split2[c3]);
                                str = split2[4];
                            } catch (Throwable unused2) {
                            }
                            if (parseInt == parseInt3 && parseLong >= parseLong3 && ((j2 != 0 || parseLong2 == parseLong3) && (i2 == 0 || parseLong <= j2))) {
                                this.f6593g = parseInt2;
                                if (parseInt2 == 1) {
                                    this.f6596j = str;
                                }
                                if (!this.f6594h) {
                                    this.f6592f = parseInt2;
                                    this.k = str;
                                } else {
                                    int i3 = this.f6592f;
                                    if (i3 == parseInt2 + 1) {
                                        this.k = str;
                                    } else if (i3 == parseInt2) {
                                        this.l = str;
                                    }
                                }
                                if (sb == null) {
                                    try {
                                        this.f6590d = true;
                                        c2 = 2;
                                        try {
                                            this.f6591e = split2[1] + '-' + split2[2];
                                            sb = new StringBuilder();
                                        } catch (Throwable unused3) {
                                        }
                                    } catch (Throwable unused4) {
                                        c2 = 2;
                                    }
                                } else {
                                    c2 = 2;
                                }
                                sb.append("No.").append(parseInt2).append("\n  time: ").append(com.bytedance.crash.util.e.a(parseLong)).append("\n  scope: ").append(str).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                                c4 = c2;
                                z = false;
                                c3 = 3;
                            }
                        }
                        c2 = c4;
                        c4 = c2;
                        z = false;
                        c3 = 3;
                    }
                    if (this.f6594h && this.f6592f == this.f6593g) {
                        this.f6595i = true;
                    }
                    if (sb != null) {
                        this.m = ("sid: " + this.f6591e + "\nperformed_count: " + this.f6592f + "\ntotal_performed_count: " + this.f6593g + "\n--- --- ---\n") + sb.toString();
                    }
                    return true;
                }
                return false;
            }
            return false;
        } catch (Throwable unused5) {
            return z;
        }
    }

    public void b() {
        if (this.f6589c == null || this.m == null) {
            return;
        }
        try {
            com.bytedance.crash.util.m.writeFile(new File(this.f6589c, f6587a), this.m, false);
            y.i(f6588b, "dump history file: " + this.f6589c.getName());
        } catch (Throwable unused) {
        }
    }

    public boolean c() {
        return this.f6590d;
    }

    public String d() {
        return this.f6591e;
    }

    public int e() {
        return this.f6592f;
    }

    public int f() {
        return this.f6593g;
    }

    public boolean g() {
        return this.f6594h;
    }

    public boolean h() {
        return this.f6595i;
    }

    public String i() {
        return this.k;
    }

    public String j() {
        return this.f6596j;
    }

    public String k() {
        return this.l;
    }
}
