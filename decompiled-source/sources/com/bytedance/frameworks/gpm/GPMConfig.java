package com.bytedance.frameworks.gpm;

import android.util.Log;
import com.bytedance.frameworks.gpm.adapter.IUploader;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* loaded from: classes2.dex */
public class GPMConfig {
    private REGION region = REGION.CHINA;
    private int aid = 0;
    private String did = "";
    private boolean fpsSwitch = true;
    private boolean cpuSwitch = true;
    private boolean memorySwitch = true;
    private boolean threadSwitch = true;
    private int cpuInterval = 1000;
    private int memoryInterval = 8000;
    private int threadInterval = 8000;
    private IUploader uploader = null;
    private com.bytedance.frameworks.gpm.adapter.b sceneNotifier = null;
    private com.bytedance.frameworks.gpm.adapter.a logger = getDefaultLogger();
    private ExecutorService worker = getDefaultWorker();
    private boolean isTest = false;
    private Map<String, String> parameter = null;

    /* loaded from: classes2.dex */
    public enum REGION {
        CHINA,
        SINGAPORE,
        AMERICAN
    }

    public GPMConfig setRegion(REGION region) {
        this.region = region;
        return this;
    }

    public REGION getRegion() {
        return this.region;
    }

    public GPMConfig setAID(int i2) {
        this.aid = i2;
        return this;
    }

    public int getAID() {
        return this.aid;
    }

    public GPMConfig setDID(String str) {
        this.did = str;
        return this;
    }

    public String getDID() {
        return this.did;
    }

    public GPMConfig setFPSSwitch(boolean z) {
        this.fpsSwitch = z;
        return this;
    }

    public boolean getFPSSwitch() {
        return this.fpsSwitch;
    }

    public GPMConfig setCPUSwitch(boolean z) {
        this.cpuSwitch = z;
        return this;
    }

    public boolean getCPUSwitch() {
        return this.cpuSwitch;
    }

    public GPMConfig setMemorySwitch(boolean z) {
        this.memorySwitch = z;
        return this;
    }

    public boolean getMemorySwitch() {
        return this.memorySwitch;
    }

    public GPMConfig setUploader(IUploader iUploader) {
        this.uploader = iUploader;
        return this;
    }

    public boolean getThreadSwitch() {
        return this.threadSwitch;
    }

    public GPMConfig setThreadSwitch(boolean z) {
        this.threadSwitch = z;
        return this;
    }

    public IUploader getUploader() {
        return this.uploader;
    }

    public GPMConfig setSceneNotifier(com.bytedance.frameworks.gpm.adapter.b bVar) {
        this.sceneNotifier = bVar;
        return this;
    }

    public com.bytedance.frameworks.gpm.adapter.b getSceneNotifier() {
        return this.sceneNotifier;
    }

    public com.bytedance.frameworks.gpm.adapter.a getLogger() {
        return this.logger;
    }

    public GPMConfig setLogger(com.bytedance.frameworks.gpm.adapter.a aVar) {
        this.logger = aVar;
        return this;
    }

    public ExecutorService getWorker() {
        return this.worker;
    }

    public GPMConfig setWorker(ExecutorService executorService) {
        this.worker = executorService;
        return this;
    }

    public boolean isTest() {
        return this.isTest;
    }

    public GPMConfig setTest(boolean z) {
        this.isTest = z;
        return this;
    }

    public int getCpuInterval() {
        return this.cpuInterval;
    }

    public GPMConfig setCpuInterval(int i2) {
        this.cpuInterval = i2;
        return this;
    }

    public int getMemoryInterval() {
        return this.memoryInterval;
    }

    public GPMConfig setMemoryInterval(int i2) {
        this.memoryInterval = i2;
        return this;
    }

    public int getThreadInterval() {
        return this.threadInterval;
    }

    public GPMConfig setThreadInterval(int i2) {
        this.threadInterval = i2;
        return this;
    }

    public Map<String, String> getParameter() {
        return this.parameter;
    }

    public GPMConfig setParameter(Map<String, String> map) {
        this.parameter = map;
        return this;
    }

    private com.bytedance.frameworks.gpm.adapter.a getDefaultLogger() {
        return new com.bytedance.frameworks.gpm.adapter.a() { // from class: com.bytedance.frameworks.gpm.GPMConfig.1
            @Override // com.bytedance.frameworks.gpm.adapter.a
            public void d(String str, String str2) {
                Log.d(str, str2);
            }

            @Override // com.bytedance.frameworks.gpm.adapter.a
            public void e(String str, String str2) {
                Log.e(str, str2);
            }

            @Override // com.bytedance.frameworks.gpm.adapter.a
            public void w(String str, String str2) {
                Log.w(str, str2);
            }
        };
    }

    private ExecutorService getDefaultWorker() {
        return Executors.newSingleThreadExecutor();
    }
}
