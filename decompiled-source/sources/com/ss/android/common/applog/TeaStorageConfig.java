package com.ss.android.common.applog;

import android.accounts.Account;
/* loaded from: classes9.dex */
public class TeaStorageConfig {
    private Account account;
    private String dbName;
    private String encryptCountSPName;
    private String spName;

    public TeaStorageConfig(String str, Account account, String str2, String str3) {
        this.encryptCountSPName = str;
        this.account = account;
        this.dbName = str2;
        this.spName = str3;
    }

    public String getEncryptCountSPName() {
        return this.encryptCountSPName;
    }

    public Account getAccount() {
        return this.account;
    }

    public String getDbName() {
        return this.dbName;
    }

    public String getSpName() {
        return this.spName;
    }
}
