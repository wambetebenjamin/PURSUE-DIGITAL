package com.facebook.soloader;

import android.os.StrictMode;
import android.util.Log;
import com.bytedance.librarian.c;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class DirectorySoSource extends SoSource {
    public static final int ON_LD_LIBRARY_PATH = 2;
    public static final int RESOLVE_DEPENDENCIES = 1;
    protected final List<String> denyList;
    protected final int flags;
    protected final File soDirectory;

    public DirectorySoSource(File file, int i2) {
        this(file, i2, new String[0]);
    }

    public DirectorySoSource(File file, int i2, String[] strArr) {
        this.soDirectory = file;
        this.flags = i2;
        this.denyList = Arrays.asList(strArr);
    }

    @Override // com.facebook.soloader.SoSource
    public int loadLibrary(String str, int i2, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        return loadLibraryFrom(str, i2, this.soDirectory, threadPolicy);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int loadLibraryFrom(String str, int i2, File file, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        if (SoLoader.sSoFileLoader == null) {
            throw new IllegalStateException("SoLoader.init() not yet called");
        }
        if (this.denyList.contains(str)) {
            Log.d("SoLoader", str + " is on the denyList, skip loading from " + file.getCanonicalPath());
            return 0;
        }
        File soFileByName = getSoFileByName(str);
        if (soFileByName == null) {
            Log.v("SoLoader", str + " not found on " + file.getCanonicalPath());
            return 0;
        }
        Log.d("SoLoader", str + " found on " + file.getCanonicalPath());
        if ((i2 & 1) != 0 && (this.flags & 2) != 0) {
            Log.d("SoLoader", str + " loaded implicitly");
            return 2;
        }
        ElfByteChannel elfByteChannel = null;
        boolean z = (this.flags & 1) != 0;
        boolean equals = soFileByName.getName().equals(str);
        if (z || !equals) {
            try {
                elfByteChannel = getChannel(soFileByName);
            } catch (Throwable th) {
                if (elfByteChannel != null) {
                    elfByteChannel.close();
                }
                throw th;
            }
        }
        if (z) {
            loadDependencies(str, elfByteChannel, i2, threadPolicy);
        } else {
            Log.d("SoLoader", "Not resolving dependencies for " + str);
        }
        try {
            if (equals) {
                SoLoader.sSoFileLoader.load(soFileByName.getAbsolutePath(), i2);
            } else {
                SoLoader.sSoFileLoader.loadBytes(soFileByName.getAbsolutePath(), elfByteChannel, i2);
            }
            if (elfByteChannel != null) {
                elfByteChannel.close();
            }
            return 1;
        } catch (UnsatisfiedLinkError e2) {
            if (e2.getMessage().contains("bad ELF magic")) {
                Log.d("SoLoader", "Corrupted lib file detected");
                if (elfByteChannel != null) {
                    elfByteChannel.close();
                }
                return 3;
            }
            throw e2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.soloader.SoSource
    @Nullable
    public File getSoFileByName(String str) throws IOException {
        File file = new File(this.soDirectory, str);
        if (file.exists()) {
            return file;
        }
        return null;
    }

    @Override // com.facebook.soloader.SoSource
    @Nullable
    public String getLibraryPath(String str) throws IOException {
        File soFileByName = getSoFileByName(str);
        if (soFileByName == null) {
            return null;
        }
        return soFileByName.getCanonicalPath();
    }

    @Override // com.facebook.soloader.SoSource
    @Nullable
    public String[] getLibraryDependencies(String str) throws IOException {
        File soFileByName = getSoFileByName(str);
        if (soFileByName == null) {
            return null;
        }
        ElfByteChannel channel = getChannel(soFileByName);
        try {
            String[] dependencies = getDependencies(str, channel);
            if (channel != null) {
                channel.close();
            }
            return dependencies;
        } catch (Throwable th) {
            if (channel != null) {
                try {
                    channel.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private void loadDependencies(String str, ElfByteChannel elfByteChannel, int i2, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        String[] dependencies = getDependencies(str, elfByteChannel);
        Log.d("SoLoader", "Loading " + str + "'s dependencies: " + Arrays.toString(dependencies));
        for (String str2 : dependencies) {
            if (!str2.startsWith(c.a.SEPARATOR)) {
                SoLoader.loadLibraryBySoName(str2, i2 | 1, threadPolicy);
            }
        }
    }

    protected ElfByteChannel getChannel(File file) throws IOException {
        return new ElfFileChannel(file);
    }

    protected String[] getDependencies(String str, ElfByteChannel elfByteChannel) throws IOException {
        if (SoLoader.SYSTRACE_LIBRARY_LOADING) {
            Api18TraceUtils.beginTraceSection("SoLoader.getElfDependencies[", str, "]");
        }
        try {
            return NativeDeps.getDependencies(str, elfByteChannel);
        } finally {
            if (SoLoader.SYSTRACE_LIBRARY_LOADING) {
                Api18TraceUtils.endSection();
            }
        }
    }

    @Override // com.facebook.soloader.SoSource
    @Nullable
    public File unpackLibrary(String str) throws IOException {
        return getSoFileByName(str);
    }

    @Override // com.facebook.soloader.SoSource
    public void addToLdLibraryPath(Collection<String> collection) {
        collection.add(this.soDirectory.getAbsolutePath());
    }

    @Override // com.facebook.soloader.SoSource
    public String toString() {
        String name;
        try {
            name = String.valueOf(this.soDirectory.getCanonicalPath());
        } catch (IOException unused) {
            name = this.soDirectory.getName();
        }
        return getClass().getName() + "[root = " + name + " flags = " + this.flags + ']';
    }
}
