package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.build.BuildConfig;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes9.dex */
public class LifetimeAssert {
    static TestHook sTestHook;
    private final Object mTarget;
    final WrappedReference mWrapper;

    /* loaded from: classes9.dex */
    interface TestHook {
        void onCleaned(WrappedReference wrappedReference, String str);
    }

    /* loaded from: classes9.dex */
    static class LifetimeAssertException extends RuntimeException {
        LifetimeAssertException(String str, Throwable th) {
            super(str, th);
        }
    }

    /* loaded from: classes9.dex */
    private static class CreationException extends RuntimeException {
        CreationException() {
            super("vvv This is where object was created. vvv");
        }
    }

    /* loaded from: classes9.dex */
    static class WrappedReference extends PhantomReference<Object> {
        final CreationException mCreationException;
        boolean mSafeToGc;
        final Class<?> mTargetClass;
        private static ReferenceQueue<Object> sReferenceQueue = new ReferenceQueue<>();
        private static Set<WrappedReference> sActiveWrappers = Collections.synchronizedSet(new HashSet());

        public WrappedReference(Object obj, CreationException creationException, boolean z) {
            super(obj, sReferenceQueue);
            this.mCreationException = creationException;
            this.mSafeToGc = z;
            this.mTargetClass = obj.getClass();
            sActiveWrappers.add(this);
        }

        static {
            new Thread("GcStateAssertQueue") { // from class: com.ttnet.org.chromium.base.LifetimeAssert.WrappedReference.1
                {
                    setDaemon(true);
                    start();
                }

                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    WrappedReference wrappedReference;
                    String format;
                    while (true) {
                        try {
                            wrappedReference = (WrappedReference) WrappedReference.sReferenceQueue.remove();
                            if (WrappedReference.sActiveWrappers.remove(wrappedReference)) {
                                if (!wrappedReference.mSafeToGc) {
                                    format = String.format("Object of type %s was GC'ed without cleanup. Refer to \"Caused by\" for where object was created.", wrappedReference.mTargetClass.getName());
                                    if (LifetimeAssert.sTestHook == null) {
                                        break;
                                    }
                                    LifetimeAssert.sTestHook.onCleaned(wrappedReference, format);
                                } else if (LifetimeAssert.sTestHook != null) {
                                    LifetimeAssert.sTestHook.onCleaned(wrappedReference, null);
                                }
                            }
                        } catch (InterruptedException e2) {
                            throw new RuntimeException(e2);
                        }
                    }
                    throw new LifetimeAssertException(format, wrappedReference.mCreationException);
                }
            };
        }
    }

    private LifetimeAssert(WrappedReference wrappedReference, Object obj) {
        this.mWrapper = wrappedReference;
        this.mTarget = obj;
    }

    public static LifetimeAssert create(Object obj) {
        if (BuildConfig.ENABLE_ASSERTS) {
            return new LifetimeAssert(new WrappedReference(obj, new CreationException(), false), obj);
        }
        return null;
    }

    public static LifetimeAssert create(Object obj, boolean z) {
        if (BuildConfig.ENABLE_ASSERTS) {
            return new LifetimeAssert(new WrappedReference(obj, new CreationException(), z), obj);
        }
        return null;
    }

    public static void setSafeToGc(LifetimeAssert lifetimeAssert, boolean z) {
        if (BuildConfig.ENABLE_ASSERTS) {
            synchronized (lifetimeAssert.mTarget) {
                lifetimeAssert.mWrapper.mSafeToGc = z;
            }
        }
    }

    public static void assertAllInstancesDestroyedForTesting() {
        if (BuildConfig.ENABLE_ASSERTS) {
            synchronized (WrappedReference.sActiveWrappers) {
                for (WrappedReference wrappedReference : WrappedReference.sActiveWrappers) {
                    if (!wrappedReference.mSafeToGc) {
                        throw new LifetimeAssertException(String.format("Object of type %s was not destroyed after test completed. Refer to \"Caused by\" for where object was created.", wrappedReference.mTargetClass.getName()), wrappedReference.mCreationException);
                    }
                }
                WrappedReference.sActiveWrappers.clear();
            }
        }
    }

    public static void resetForTesting() {
        if (BuildConfig.ENABLE_ASSERTS) {
            WrappedReference.sActiveWrappers.clear();
        }
    }
}
