package me.drakeet.multitype;

import java.util.Arrays;
/* loaded from: classes10.dex */
final class ClassLinkerWrapper<T> implements Linker<T> {
    private final ItemViewBinder<T, ?>[] binders;
    private final ClassLinker<T> classLinker;

    private ClassLinkerWrapper(ClassLinker<T> classLinker, ItemViewBinder<T, ?>[] itemViewBinderArr) {
        this.classLinker = classLinker;
        this.binders = itemViewBinderArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> ClassLinkerWrapper<T> wrap(ClassLinker<T> classLinker, ItemViewBinder<T, ?>[] itemViewBinderArr) {
        return new ClassLinkerWrapper<>(classLinker, itemViewBinderArr);
    }

    @Override // me.drakeet.multitype.Linker
    public int index(int i2, T t) {
        Class<? extends ItemViewBinder<T, ?>> index = this.classLinker.index(i2, t);
        int i3 = 0;
        while (true) {
            ItemViewBinder<T, ?>[] itemViewBinderArr = this.binders;
            if (i3 < itemViewBinderArr.length) {
                if (itemViewBinderArr[i3].getClass().equals(index)) {
                    return i3;
                }
                i3++;
            } else {
                throw new IndexOutOfBoundsException(String.format("%s is out of your registered binders'(%s) bounds.", index.getName(), Arrays.toString(this.binders)));
            }
        }
    }
}
