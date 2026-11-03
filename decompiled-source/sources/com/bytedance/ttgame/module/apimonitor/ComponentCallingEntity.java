package com.bytedance.ttgame.module.apimonitor;
/* loaded from: classes4.dex */
final class ComponentCallingEntity implements CallingEntity {
    final String componentName;
    final String moduleName;
    final String moduleRole;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ComponentCallingEntity(String str, String str2, String str3) {
        this.componentName = str;
        this.moduleRole = str2;
        this.moduleName = str3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ComponentCallingEntity componentCallingEntity = (ComponentCallingEntity) obj;
        if (this.componentName.equals(componentCallingEntity.componentName) && this.moduleRole.equals(componentCallingEntity.moduleRole)) {
            return this.moduleName.equals(componentCallingEntity.moduleName);
        }
        return false;
    }

    public int hashCode() {
        return (((this.componentName.hashCode() * 31) + this.moduleRole.hashCode()) * 31) + this.moduleName.hashCode();
    }

    public String toString() {
        return "ComponentCallingEntity{componentName='" + this.componentName + "', moduleRole='" + this.moduleRole + "', moduleName='" + this.moduleName + "'}";
    }
}
