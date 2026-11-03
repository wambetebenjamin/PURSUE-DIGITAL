package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
final class bk extends com.google.android.play.core.assetpacks.internal.an {

    /* renamed from: a  reason: collision with root package name */
    private final File f16511a;

    /* renamed from: b  reason: collision with root package name */
    private final File f16512b;

    /* renamed from: c  reason: collision with root package name */
    private final NavigableMap f16513c = new TreeMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public bk(File file, File file2) throws IOException {
        this.f16511a = file;
        this.f16512b = file2;
        List<File> a2 = eq.a(file, file2);
        if (a2.isEmpty()) {
            throw new ck(String.format("Virtualized slice archive empty for %s, %s", file, file2));
        }
        long j2 = 0;
        for (File file3 : a2) {
            this.f16513c.put(Long.valueOf(j2), file3);
            j2 += file3.length();
        }
    }

    private final InputStream d(long j2, Long l) throws IOException {
        FileInputStream fileInputStream = new FileInputStream((File) this.f16513c.get(l));
        if (fileInputStream.skip(j2 - l.longValue()) == j2 - l.longValue()) {
            return fileInputStream;
        }
        throw new ck(String.format("Virtualized slice archive corrupt, could not skip in file with key %s", l));
    }

    @Override // com.google.android.play.core.assetpacks.internal.an
    public final long a() {
        Map.Entry lastEntry = this.f16513c.lastEntry();
        return ((Long) lastEntry.getKey()).longValue() + ((File) lastEntry.getValue()).length();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.play.core.assetpacks.internal.an
    public final InputStream b(long j2, long j3) throws IOException {
        if (j2 < 0 || j3 < 0) {
            throw new ck(String.format("Invalid input parameters %s, %s", Long.valueOf(j2), Long.valueOf(j3)));
        }
        long j4 = j2 + j3;
        if (j4 <= a()) {
            Long l = (Long) this.f16513c.floorKey(Long.valueOf(j2));
            Long l2 = (Long) this.f16513c.floorKey(Long.valueOf(j4));
            if (l.equals(l2)) {
                return new bj(d(j2, l), j3);
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(d(j2, l));
            Collection values = this.f16513c.subMap(l, false, l2, false).values();
            if (!values.isEmpty()) {
                arrayList.add(new dr(Collections.enumeration(values)));
            }
            arrayList.add(new bj(new FileInputStream((File) this.f16513c.get(l2)), j3 - (l2.longValue() - j2)));
            return new SequenceInputStream(Collections.enumeration(arrayList));
        }
        throw new ck(String.format("Trying to access archive out of bounds. Archive ends at: %s. Tried accessing: %s", Long.valueOf(a()), Long.valueOf(j4)));
    }
}
