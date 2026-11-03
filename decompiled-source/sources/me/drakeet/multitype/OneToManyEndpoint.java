package me.drakeet.multitype;
/* loaded from: classes10.dex */
public interface OneToManyEndpoint<T> {
    void withClassLinker(ClassLinker<T> classLinker);

    void withLinker(Linker<T> linker);
}
