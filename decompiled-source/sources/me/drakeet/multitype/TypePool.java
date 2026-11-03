package me.drakeet.multitype;
/* loaded from: classes10.dex */
public interface TypePool {
    int firstIndexOf(Class<?> cls);

    Class<?> getClass(int i2);

    ItemViewBinder<?, ?> getItemViewBinder(int i2);

    Linker<?> getLinker(int i2);

    <T> void register(Class<? extends T> cls, ItemViewBinder<T, ?> itemViewBinder, Linker<T> linker);

    int size();

    boolean unregister(Class<?> cls);
}
