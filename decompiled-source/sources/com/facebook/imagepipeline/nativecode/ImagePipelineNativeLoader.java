package com.facebook.imagepipeline.nativecode;

import com.facebook.imageutils.FrescoSoLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes8.dex */
public class ImagePipelineNativeLoader {
    public static final List<String> DEPENDENCIES = Collections.unmodifiableList(new ArrayList());
    public static final String DSO_NAME = "imagepipeline";

    public static void load() {
        FrescoSoLoader.loadLibrary(DSO_NAME);
    }
}
