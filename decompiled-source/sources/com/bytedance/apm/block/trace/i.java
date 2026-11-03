package com.bytedance.apm.block.trace;
/* compiled from: MethodItem.java */
/* loaded from: classes.dex */
public class i {
    public int count = 1;
    public int depth;
    public int durTime;
    public int methodId;
    public long startTime;

    public i(int i2, int i3, long j2, int i4) {
        this.methodId = i2;
        this.durTime = i3;
        this.depth = i4;
        this.startTime = j2;
    }

    public String toString() {
        return this.depth + "," + this.methodId + "," + this.count + "," + this.durTime + "," + this.startTime;
    }

    public String toPrettyString() {
        return "{methodId=" + this.methodId + ", durTime=" + this.durTime + ", startTime=" + this.startTime + ", depth=" + this.depth + ", count=" + this.count + '}';
    }

    public String getKey() {
        return this.depth + "," + this.methodId + "," + this.count;
    }

    public boolean equals(Object obj) {
        if (obj instanceof i) {
            i iVar = (i) obj;
            return iVar.methodId == this.methodId && iVar.depth == this.depth;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public void mergeMore(long j2) {
        this.count++;
        this.durTime = (int) (this.durTime + j2);
    }

    public String print() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < this.depth; i2++) {
            stringBuffer.append('.');
        }
        return stringBuffer.toString() + this.methodId + " " + this.count + " " + this.durTime;
    }
}
