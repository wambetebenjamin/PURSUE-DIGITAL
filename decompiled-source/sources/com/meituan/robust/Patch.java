package com.meituan.robust;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class Patch implements Cloneable {
    private String appHash;
    private boolean isAppliedSuccess;
    private boolean isAsyncLoad;
    private boolean isForce;
    private boolean isSupportSubProcess;
    private String localPath;
    private String md5;
    private String name;
    private String patchesInfoImplClassFullName;
    private boolean preset;
    private String sdkComponent;
    private String tempPath;
    private String url;
    private int pluginVersion = 0;
    private List<PatchedClassInfo> patchedClasses = new ArrayList();
    private boolean hasSo = false;
    private boolean hasJava = false;
    public boolean downloadedWhenEnsure = false;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getLocalPath() {
        return this.localPath + ".jar";
    }

    public void setLocalPath(String str) {
        this.localPath = str;
    }

    public String getMd5() {
        return this.md5;
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public int getPluginVersion() {
        return this.pluginVersion;
    }

    public void setPluginVersion(int i2) {
        this.pluginVersion = i2;
    }

    public boolean isPreset() {
        return this.preset;
    }

    public void setPreset(boolean z) {
        this.preset = z;
    }

    public boolean isAsyncLoad() {
        return this.isAsyncLoad;
    }

    public void setAsyncLoad(boolean z) {
        this.isAsyncLoad = z;
    }

    public boolean isForce() {
        return this.isForce;
    }

    public void setIsForce(boolean z) {
        this.isForce = z;
    }

    public boolean isSupportSubProcess() {
        return this.isSupportSubProcess;
    }

    public void setSupportSubProcess(boolean z) {
        this.isSupportSubProcess = z;
    }

    public String getSdkComponent() {
        return this.sdkComponent;
    }

    public void setSdkComponent(String str) {
        this.sdkComponent = str;
    }

    public boolean isAppliedSuccess() {
        return this.isAppliedSuccess;
    }

    public void setAppliedSuccess(boolean z) {
        this.isAppliedSuccess = z;
    }

    public void delete(String str) {
        new File(str).delete();
    }

    public String getPatchesInfoImplClassFullName() {
        return this.patchesInfoImplClassFullName;
    }

    public void setPatchesInfoImplClassFullName(String str) {
        this.patchesInfoImplClassFullName = str;
    }

    public String getAppHash() {
        return this.appHash;
    }

    public void setAppHash(String str) {
        this.appHash = str;
    }

    public String getTempPath() {
        return this.tempPath + "_temp.jar";
    }

    public void setTempPath(String str) {
        this.tempPath = str;
    }

    public List<PatchedClassInfo> getPatchedClasses() {
        return this.patchedClasses;
    }

    public void setPatchedClasses(List<PatchedClassInfo> list) {
        this.patchedClasses.addAll(list);
    }

    public boolean hasSo() {
        return this.hasSo;
    }

    public void setHasSo(boolean z) {
        this.hasSo = z;
    }

    public boolean hasJava() {
        return this.hasJava;
    }

    public void setHasJava(boolean z) {
        this.hasJava = z;
    }

    public boolean isOnlySo() {
        return this.hasSo && !this.hasJava;
    }

    /* renamed from: clone */
    public Patch m590clone() {
        try {
            return (Patch) super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }
}
