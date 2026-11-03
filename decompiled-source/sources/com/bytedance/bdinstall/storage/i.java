package com.bytedance.bdinstall.storage;

import android.accounts.Account;
import android.content.SharedPreferences;
import com.bytedance.bdinstall.am;
/* compiled from: StorageSettings.java */
/* loaded from: classes.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private String f4001a;

    /* renamed from: b  reason: collision with root package name */
    private SharedPreferences f4002b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f4003c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f4004d;

    /* renamed from: e  reason: collision with root package name */
    private Account f4005e;

    /* renamed from: f  reason: collision with root package name */
    private String f4006f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f4007g = false;

    /* renamed from: h  reason: collision with root package name */
    private am f4008h;

    String a() {
        return this.f4001a;
    }

    public void a(String str) {
        this.f4001a = str;
    }

    public SharedPreferences b() {
        return this.f4002b;
    }

    public void a(SharedPreferences sharedPreferences) {
        this.f4002b = sharedPreferences;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c() {
        return this.f4003c;
    }

    public void a(boolean z) {
        this.f4003c = z;
    }

    public boolean d() {
        return this.f4004d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(boolean z) {
        this.f4004d = z;
    }

    public Account e() {
        return this.f4005e;
    }

    public void a(Account account, String str) {
        this.f4005e = account;
        this.f4006f = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String f() {
        return this.f4006f;
    }

    public boolean g() {
        return this.f4007g;
    }

    public void c(boolean z) {
        this.f4007g = z;
    }

    public void a(am amVar) {
        this.f4008h = amVar;
    }

    public am h() {
        return this.f4008h;
    }
}
