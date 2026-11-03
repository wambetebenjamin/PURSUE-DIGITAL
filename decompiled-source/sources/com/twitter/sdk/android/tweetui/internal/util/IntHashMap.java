package com.twitter.sdk.android.tweetui.internal.util;

import java.util.Objects;
/* loaded from: classes10.dex */
public class IntHashMap {
    private int count;
    private float loadFactor;
    private Entry[] table;
    private int threshold;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class Entry {
        public final int hash;
        public int key;
        public Entry next;
        public Object value;

        protected Entry(int i2, int i3, Object obj, Entry entry) {
            this.hash = i2;
            this.key = i3;
            this.value = obj;
            this.next = entry;
        }
    }

    public IntHashMap() {
        this(20, 0.75f);
    }

    public IntHashMap(int i2) {
        this(i2, 0.75f);
    }

    public IntHashMap(int i2, float f2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + i2);
        }
        if (f2 <= 0.0f) {
            throw new IllegalArgumentException("Illegal Load: " + f2);
        }
        i2 = i2 == 0 ? 1 : i2;
        this.loadFactor = f2;
        this.table = new Entry[i2];
        this.threshold = (int) (i2 * f2);
    }

    public int size() {
        return this.count;
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public boolean contains(Object obj) {
        Objects.requireNonNull(obj);
        Entry[] entryArr = this.table;
        int length = entryArr.length;
        while (true) {
            int i2 = length - 1;
            if (length <= 0) {
                return false;
            }
            for (Entry entry = entryArr[i2]; entry != null; entry = entry.next) {
                if (entry.value.equals(obj)) {
                    return true;
                }
            }
            length = i2;
        }
    }

    public boolean containsValue(Object obj) {
        return contains(obj);
    }

    public boolean containsKey(int i2) {
        Entry[] entryArr = this.table;
        for (Entry entry = entryArr[(Integer.MAX_VALUE & i2) % entryArr.length]; entry != null; entry = entry.next) {
            if (entry.hash == i2) {
                return true;
            }
        }
        return false;
    }

    public Object get(int i2) {
        Entry[] entryArr = this.table;
        for (Entry entry = entryArr[(Integer.MAX_VALUE & i2) % entryArr.length]; entry != null; entry = entry.next) {
            if (entry.hash == i2) {
                return entry.value;
            }
        }
        return null;
    }

    protected void rehash() {
        Entry[] entryArr = this.table;
        int length = entryArr.length;
        int i2 = (length * 2) + 1;
        Entry[] entryArr2 = new Entry[i2];
        this.threshold = (int) (i2 * this.loadFactor);
        this.table = entryArr2;
        while (true) {
            int i3 = length - 1;
            if (length <= 0) {
                return;
            }
            Entry entry = entryArr[i3];
            while (entry != null) {
                Entry entry2 = entry.next;
                int i4 = (entry.hash & Integer.MAX_VALUE) % i2;
                entry.next = entryArr2[i4];
                entryArr2[i4] = entry;
                entry = entry2;
            }
            length = i3;
        }
    }

    public Object put(int i2, Object obj) {
        Entry[] entryArr = this.table;
        int i3 = Integer.MAX_VALUE & i2;
        int length = i3 % entryArr.length;
        for (Entry entry = entryArr[length]; entry != null; entry = entry.next) {
            if (entry.hash == i2) {
                Object obj2 = entry.value;
                entry.value = obj;
                return obj2;
            }
        }
        if (this.count >= this.threshold) {
            rehash();
            entryArr = this.table;
            length = i3 % entryArr.length;
        }
        entryArr[length] = new Entry(i2, i2, obj, entryArr[length]);
        this.count++;
        return null;
    }

    public Object remove(int i2) {
        Entry[] entryArr = this.table;
        int length = (Integer.MAX_VALUE & i2) % entryArr.length;
        Entry entry = null;
        for (Entry entry2 = entryArr[length]; entry2 != null; entry2 = entry2.next) {
            if (entry2.hash != i2) {
                entry = entry2;
            } else {
                if (entry != null) {
                    entry.next = entry2.next;
                } else {
                    entryArr[length] = entry2.next;
                }
                this.count--;
                Object obj = entry2.value;
                entry2.value = null;
                return obj;
            }
        }
        return null;
    }

    public synchronized void clear() {
        Entry[] entryArr = this.table;
        int length = entryArr.length;
        while (true) {
            length--;
            if (length >= 0) {
                entryArr[length] = null;
            } else {
                this.count = 0;
            }
        }
    }
}
