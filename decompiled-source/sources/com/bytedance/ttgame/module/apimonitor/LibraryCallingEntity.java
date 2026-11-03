package com.bytedance.ttgame.module.apimonitor;
/* loaded from: classes4.dex */
final class LibraryCallingEntity implements CallingEntity {
    final String libraryName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LibraryCallingEntity(String str) {
        this.libraryName = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.libraryName.equals(((LibraryCallingEntity) obj).libraryName);
    }

    public int hashCode() {
        return this.libraryName.hashCode();
    }

    public String toString() {
        return "LibraryCallingEntity{libraryName='" + this.libraryName + "'}";
    }
}
