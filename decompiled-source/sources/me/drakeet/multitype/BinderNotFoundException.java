package me.drakeet.multitype;
/* loaded from: classes10.dex */
class BinderNotFoundException extends RuntimeException {
    /* JADX INFO: Access modifiers changed from: package-private */
    public BinderNotFoundException(Class<?> cls) {
        super("Do you have registered {className}.class to the binder in the adapter/pool?".replace("{className}", cls.getSimpleName()));
    }
}
