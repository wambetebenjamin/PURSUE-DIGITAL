package me.drakeet.multitype;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public class MultiTypePool implements TypePool {
    private final List<ItemViewBinder<?, ?>> binders;
    private final List<Class<?>> classes;
    private final List<Linker<?>> linkers;

    public MultiTypePool() {
        this.classes = new ArrayList();
        this.binders = new ArrayList();
        this.linkers = new ArrayList();
    }

    public MultiTypePool(int i2) {
        this.classes = new ArrayList(i2);
        this.binders = new ArrayList(i2);
        this.linkers = new ArrayList(i2);
    }

    public MultiTypePool(List<Class<?>> list, List<ItemViewBinder<?, ?>> list2, List<Linker<?>> list3) {
        Preconditions.checkNotNull(list);
        Preconditions.checkNotNull(list2);
        Preconditions.checkNotNull(list3);
        this.classes = list;
        this.binders = list2;
        this.linkers = list3;
    }

    @Override // me.drakeet.multitype.TypePool
    public <T> void register(Class<? extends T> cls, ItemViewBinder<T, ?> itemViewBinder, Linker<T> linker) {
        Preconditions.checkNotNull(cls);
        Preconditions.checkNotNull(itemViewBinder);
        Preconditions.checkNotNull(linker);
        this.classes.add(cls);
        this.binders.add(itemViewBinder);
        this.linkers.add(linker);
    }

    @Override // me.drakeet.multitype.TypePool
    public boolean unregister(Class<?> cls) {
        Preconditions.checkNotNull(cls);
        boolean z = false;
        while (true) {
            int indexOf = this.classes.indexOf(cls);
            if (indexOf == -1) {
                return z;
            }
            this.classes.remove(indexOf);
            this.binders.remove(indexOf);
            this.linkers.remove(indexOf);
            z = true;
        }
    }

    @Override // me.drakeet.multitype.TypePool
    public int size() {
        return this.classes.size();
    }

    @Override // me.drakeet.multitype.TypePool
    public int firstIndexOf(Class<?> cls) {
        Preconditions.checkNotNull(cls);
        int indexOf = this.classes.indexOf(cls);
        if (indexOf != -1) {
            return indexOf;
        }
        for (int i2 = 0; i2 < this.classes.size(); i2++) {
            if (this.classes.get(i2).isAssignableFrom(cls)) {
                return i2;
            }
        }
        return -1;
    }

    @Override // me.drakeet.multitype.TypePool
    public Class<?> getClass(int i2) {
        return this.classes.get(i2);
    }

    @Override // me.drakeet.multitype.TypePool
    public ItemViewBinder<?, ?> getItemViewBinder(int i2) {
        return this.binders.get(i2);
    }

    @Override // me.drakeet.multitype.TypePool
    public Linker<?> getLinker(int i2) {
        return this.linkers.get(i2);
    }
}
