package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import com.bumptech.glide.util.Util;
import com.bytedance.ttgame.module.webview.api.WebViewConfig;
import com.meituan.robust.Constants;
/* loaded from: classes.dex */
class AttributeStrategy implements LruPoolStrategy {
    private final KeyPool keyPool = new KeyPool();
    private final GroupedLinkedMap<Key, Bitmap> groupedMap = new GroupedLinkedMap<>();

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruPoolStrategy
    public void put(Bitmap bitmap) {
        this.groupedMap.put(this.keyPool.get(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig()), bitmap);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruPoolStrategy
    public Bitmap get(int i2, int i3, Bitmap.Config config) {
        return this.groupedMap.get(this.keyPool.get(i2, i3, config));
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruPoolStrategy
    public Bitmap removeLast() {
        return this.groupedMap.removeLast();
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruPoolStrategy
    public String logBitmap(Bitmap bitmap) {
        return getBitmapString(bitmap);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruPoolStrategy
    public String logBitmap(int i2, int i3, Bitmap.Config config) {
        return getBitmapString(i2, i3, config);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruPoolStrategy
    public int getSize(Bitmap bitmap) {
        return Util.getBitmapByteSize(bitmap);
    }

    public String toString() {
        return "AttributeStrategy:\n  " + this.groupedMap;
    }

    private static String getBitmapString(Bitmap bitmap) {
        return getBitmapString(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
    }

    static String getBitmapString(int i2, int i3, Bitmap.Config config) {
        return Constants.ARRAY_TYPE + i2 + WebViewConfig.LOCATION_X + i3 + "], " + config;
    }

    /* loaded from: classes.dex */
    static class KeyPool extends BaseKeyPool<Key> {
        KeyPool() {
        }

        Key get(int i2, int i3, Bitmap.Config config) {
            Key key = get();
            key.init(i2, i3, config);
            return key;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bumptech.glide.load.engine.bitmap_recycle.BaseKeyPool
        public Key create() {
            return new Key(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Key implements Poolable {
        private Bitmap.Config config;
        private int height;
        private final KeyPool pool;
        private int width;

        public Key(KeyPool keyPool) {
            this.pool = keyPool;
        }

        public void init(int i2, int i3, Bitmap.Config config) {
            this.width = i2;
            this.height = i3;
            this.config = config;
        }

        public boolean equals(Object obj) {
            if (obj instanceof Key) {
                Key key = (Key) obj;
                return this.width == key.width && this.height == key.height && this.config == key.config;
            }
            return false;
        }

        public int hashCode() {
            int i2 = ((this.width * 31) + this.height) * 31;
            Bitmap.Config config = this.config;
            return i2 + (config != null ? config.hashCode() : 0);
        }

        public String toString() {
            return AttributeStrategy.getBitmapString(this.width, this.height, this.config);
        }

        @Override // com.bumptech.glide.load.engine.bitmap_recycle.Poolable
        public void offer() {
            this.pool.offer(this);
        }
    }
}
