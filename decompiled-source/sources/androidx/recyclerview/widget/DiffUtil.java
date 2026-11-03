package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/* loaded from: classes.dex */
public class DiffUtil {
    private static final Comparator<Snake> SNAKE_COMPARATOR = new Comparator<Snake>() { // from class: androidx.recyclerview.widget.DiffUtil.1
        @Override // java.util.Comparator
        public int compare(Snake snake, Snake snake2) {
            int i2 = snake.x - snake2.x;
            return i2 == 0 ? snake.y - snake2.y : i2;
        }
    };

    /* loaded from: classes.dex */
    public static abstract class Callback {
        public abstract boolean areContentsTheSame(int i2, int i3);

        public abstract boolean areItemsTheSame(int i2, int i3);

        public Object getChangePayload(int i2, int i3) {
            return null;
        }

        public abstract int getNewListSize();

        public abstract int getOldListSize();
    }

    /* loaded from: classes.dex */
    public static abstract class ItemCallback<T> {
        public abstract boolean areContentsTheSame(T t, T t2);

        public abstract boolean areItemsTheSame(T t, T t2);

        public Object getChangePayload(T t, T t2) {
            return null;
        }
    }

    private DiffUtil() {
    }

    public static DiffResult calculateDiff(Callback callback) {
        return calculateDiff(callback, true);
    }

    public static DiffResult calculateDiff(Callback callback, boolean z) {
        int oldListSize = callback.getOldListSize();
        int newListSize = callback.getNewListSize();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new Range(0, oldListSize, 0, newListSize));
        int abs = oldListSize + newListSize + Math.abs(oldListSize - newListSize);
        int i2 = abs * 2;
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        ArrayList arrayList3 = new ArrayList();
        while (!arrayList2.isEmpty()) {
            Range range = (Range) arrayList2.remove(arrayList2.size() - 1);
            Snake diffPartial = diffPartial(callback, range.oldListStart, range.oldListEnd, range.newListStart, range.newListEnd, iArr, iArr2, abs);
            if (diffPartial != null) {
                if (diffPartial.size > 0) {
                    arrayList.add(diffPartial);
                }
                diffPartial.x += range.oldListStart;
                diffPartial.y += range.newListStart;
                Range range2 = arrayList3.isEmpty() ? new Range() : (Range) arrayList3.remove(arrayList3.size() - 1);
                range2.oldListStart = range.oldListStart;
                range2.newListStart = range.newListStart;
                if (diffPartial.reverse) {
                    range2.oldListEnd = diffPartial.x;
                    range2.newListEnd = diffPartial.y;
                } else if (diffPartial.removal) {
                    range2.oldListEnd = diffPartial.x - 1;
                    range2.newListEnd = diffPartial.y;
                } else {
                    range2.oldListEnd = diffPartial.x;
                    range2.newListEnd = diffPartial.y - 1;
                }
                arrayList2.add(range2);
                if (diffPartial.reverse) {
                    if (diffPartial.removal) {
                        range.oldListStart = diffPartial.x + diffPartial.size + 1;
                        range.newListStart = diffPartial.y + diffPartial.size;
                    } else {
                        range.oldListStart = diffPartial.x + diffPartial.size;
                        range.newListStart = diffPartial.y + diffPartial.size + 1;
                    }
                } else {
                    range.oldListStart = diffPartial.x + diffPartial.size;
                    range.newListStart = diffPartial.y + diffPartial.size;
                }
                arrayList2.add(range);
            } else {
                arrayList3.add(range);
            }
        }
        Collections.sort(arrayList, SNAKE_COMPARATOR);
        return new DiffResult(callback, arrayList, iArr, iArr2, z);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0042, code lost:
        if (r24[r13 - 1] < r24[r13 + r5]) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ba, code lost:
        if (r25[r12 - 1] < r25[r12 + 1]) goto L77;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00e3 A[LOOP:4: B:52:0x00cf->B:56:0x00e3, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00ee A[EDGE_INSN: B:88:0x00ee->B:58:0x00ee ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static androidx.recyclerview.widget.DiffUtil.Snake diffPartial(androidx.recyclerview.widget.DiffUtil.Callback r19, int r20, int r21, int r22, int r23, int[] r24, int[] r25, int r26) {
        /*
            Method dump skipped, instructions count: 309
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.DiffUtil.diffPartial(androidx.recyclerview.widget.DiffUtil$Callback, int, int, int, int, int[], int[], int):androidx.recyclerview.widget.DiffUtil$Snake");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Snake {
        boolean removal;
        boolean reverse;
        int size;
        int x;
        int y;

        Snake() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Range {
        int newListEnd;
        int newListStart;
        int oldListEnd;
        int oldListStart;

        public Range() {
        }

        public Range(int i2, int i3, int i4, int i5) {
            this.oldListStart = i2;
            this.oldListEnd = i3;
            this.newListStart = i4;
            this.newListEnd = i5;
        }
    }

    /* loaded from: classes.dex */
    public static class DiffResult {
        private static final int FLAG_CHANGED = 2;
        private static final int FLAG_IGNORE = 16;
        private static final int FLAG_MASK = 31;
        private static final int FLAG_MOVED_CHANGED = 4;
        private static final int FLAG_MOVED_NOT_CHANGED = 8;
        private static final int FLAG_NOT_CHANGED = 1;
        private static final int FLAG_OFFSET = 5;
        public static final int NO_POSITION = -1;
        private final Callback mCallback;
        private final boolean mDetectMoves;
        private final int[] mNewItemStatuses;
        private final int mNewListSize;
        private final int[] mOldItemStatuses;
        private final int mOldListSize;
        private final List<Snake> mSnakes;

        DiffResult(Callback callback, List<Snake> list, int[] iArr, int[] iArr2, boolean z) {
            this.mSnakes = list;
            this.mOldItemStatuses = iArr;
            this.mNewItemStatuses = iArr2;
            Arrays.fill(iArr, 0);
            Arrays.fill(iArr2, 0);
            this.mCallback = callback;
            this.mOldListSize = callback.getOldListSize();
            this.mNewListSize = callback.getNewListSize();
            this.mDetectMoves = z;
            addRootSnake();
            findMatchingItems();
        }

        private void addRootSnake() {
            Snake snake = this.mSnakes.isEmpty() ? null : this.mSnakes.get(0);
            if (snake != null && snake.x == 0 && snake.y == 0) {
                return;
            }
            Snake snake2 = new Snake();
            snake2.x = 0;
            snake2.y = 0;
            snake2.removal = false;
            snake2.size = 0;
            snake2.reverse = false;
            this.mSnakes.add(0, snake2);
        }

        private void findMatchingItems() {
            int i2 = this.mOldListSize;
            int i3 = this.mNewListSize;
            for (int size = this.mSnakes.size() - 1; size >= 0; size--) {
                Snake snake = this.mSnakes.get(size);
                int i4 = snake.x + snake.size;
                int i5 = snake.y + snake.size;
                if (this.mDetectMoves) {
                    while (i2 > i4) {
                        findAddition(i2, i3, size);
                        i2--;
                    }
                    while (i3 > i5) {
                        findRemoval(i2, i3, size);
                        i3--;
                    }
                }
                for (int i6 = 0; i6 < snake.size; i6++) {
                    int i7 = snake.x + i6;
                    int i8 = snake.y + i6;
                    int i9 = this.mCallback.areContentsTheSame(i7, i8) ? 1 : 2;
                    this.mOldItemStatuses[i7] = (i8 << 5) | i9;
                    this.mNewItemStatuses[i8] = (i7 << 5) | i9;
                }
                i2 = snake.x;
                i3 = snake.y;
            }
        }

        private void findAddition(int i2, int i3, int i4) {
            if (this.mOldItemStatuses[i2 - 1] != 0) {
                return;
            }
            findMatchingItem(i2, i3, i4, false);
        }

        private void findRemoval(int i2, int i3, int i4) {
            if (this.mNewItemStatuses[i3 - 1] != 0) {
                return;
            }
            findMatchingItem(i2, i3, i4, true);
        }

        public int convertOldPositionToNew(int i2) {
            if (i2 < 0 || i2 >= this.mOldListSize) {
                throw new IndexOutOfBoundsException("Index out of bounds - passed position = " + i2 + ", old list size = " + this.mOldListSize);
            }
            int i3 = this.mOldItemStatuses[i2];
            if ((i3 & 31) == 0) {
                return -1;
            }
            return i3 >> 5;
        }

        public int convertNewPositionToOld(int i2) {
            if (i2 < 0 || i2 >= this.mNewListSize) {
                throw new IndexOutOfBoundsException("Index out of bounds - passed position = " + i2 + ", new list size = " + this.mNewListSize);
            }
            int i3 = this.mNewItemStatuses[i2];
            if ((i3 & 31) == 0) {
                return -1;
            }
            return i3 >> 5;
        }

        private boolean findMatchingItem(int i2, int i3, int i4, boolean z) {
            int i5;
            int i6;
            int i7;
            if (z) {
                i3--;
                i6 = i2;
                i5 = i3;
            } else {
                i5 = i2 - 1;
                i6 = i5;
            }
            while (i4 >= 0) {
                Snake snake = this.mSnakes.get(i4);
                int i8 = snake.x + snake.size;
                int i9 = snake.y + snake.size;
                if (z) {
                    for (int i10 = i6 - 1; i10 >= i8; i10--) {
                        if (this.mCallback.areItemsTheSame(i10, i5)) {
                            i7 = this.mCallback.areContentsTheSame(i10, i5) ? 8 : 4;
                            this.mNewItemStatuses[i5] = (i10 << 5) | 16;
                            this.mOldItemStatuses[i10] = (i5 << 5) | i7;
                            return true;
                        }
                    }
                    continue;
                } else {
                    for (int i11 = i3 - 1; i11 >= i9; i11--) {
                        if (this.mCallback.areItemsTheSame(i5, i11)) {
                            i7 = this.mCallback.areContentsTheSame(i5, i11) ? 8 : 4;
                            int i12 = i2 - 1;
                            this.mOldItemStatuses[i12] = (i11 << 5) | 16;
                            this.mNewItemStatuses[i11] = (i12 << 5) | i7;
                            return true;
                        }
                    }
                    continue;
                }
                i6 = snake.x;
                i3 = snake.y;
                i4--;
            }
            return false;
        }

        public void dispatchUpdatesTo(RecyclerView.Adapter adapter) {
            dispatchUpdatesTo(new AdapterListUpdateCallback(adapter));
        }

        public void dispatchUpdatesTo(ListUpdateCallback listUpdateCallback) {
            BatchingListUpdateCallback batchingListUpdateCallback;
            if (listUpdateCallback instanceof BatchingListUpdateCallback) {
                batchingListUpdateCallback = (BatchingListUpdateCallback) listUpdateCallback;
            } else {
                batchingListUpdateCallback = new BatchingListUpdateCallback(listUpdateCallback);
            }
            ArrayList arrayList = new ArrayList();
            int i2 = this.mOldListSize;
            int i3 = this.mNewListSize;
            for (int size = this.mSnakes.size() - 1; size >= 0; size--) {
                Snake snake = this.mSnakes.get(size);
                int i4 = snake.size;
                int i5 = snake.x + i4;
                int i6 = snake.y + i4;
                if (i5 < i2) {
                    dispatchRemovals(arrayList, batchingListUpdateCallback, i5, i2 - i5, i5);
                }
                if (i6 < i3) {
                    dispatchAdditions(arrayList, batchingListUpdateCallback, i5, i3 - i6, i6);
                }
                for (int i7 = i4 - 1; i7 >= 0; i7--) {
                    if ((this.mOldItemStatuses[snake.x + i7] & 31) == 2) {
                        batchingListUpdateCallback.onChanged(snake.x + i7, 1, this.mCallback.getChangePayload(snake.x + i7, snake.y + i7));
                    }
                }
                i2 = snake.x;
                i3 = snake.y;
            }
            batchingListUpdateCallback.dispatchLastEvent();
        }

        private static PostponedUpdate removePostponedUpdate(List<PostponedUpdate> list, int i2, boolean z) {
            int size = list.size() - 1;
            while (size >= 0) {
                PostponedUpdate postponedUpdate = list.get(size);
                if (postponedUpdate.posInOwnerList == i2 && postponedUpdate.removal == z) {
                    list.remove(size);
                    while (size < list.size()) {
                        list.get(size).currentPos += z ? 1 : -1;
                        size++;
                    }
                    return postponedUpdate;
                }
                size--;
            }
            return null;
        }

        private void dispatchAdditions(List<PostponedUpdate> list, ListUpdateCallback listUpdateCallback, int i2, int i3, int i4) {
            if (!this.mDetectMoves) {
                listUpdateCallback.onInserted(i2, i3);
                return;
            }
            for (int i5 = i3 - 1; i5 >= 0; i5--) {
                int[] iArr = this.mNewItemStatuses;
                int i6 = i4 + i5;
                int i7 = iArr[i6] & 31;
                if (i7 == 0) {
                    listUpdateCallback.onInserted(i2, 1);
                    for (PostponedUpdate postponedUpdate : list) {
                        postponedUpdate.currentPos++;
                    }
                } else if (i7 == 4 || i7 == 8) {
                    int i8 = iArr[i6] >> 5;
                    listUpdateCallback.onMoved(removePostponedUpdate(list, i8, true).currentPos, i2);
                    if (i7 == 4) {
                        listUpdateCallback.onChanged(i2, 1, this.mCallback.getChangePayload(i8, i6));
                    }
                } else if (i7 == 16) {
                    list.add(new PostponedUpdate(i6, i2, false));
                } else {
                    throw new IllegalStateException("unknown flag for pos " + i6 + " " + Long.toBinaryString(i7));
                }
            }
        }

        private void dispatchRemovals(List<PostponedUpdate> list, ListUpdateCallback listUpdateCallback, int i2, int i3, int i4) {
            if (!this.mDetectMoves) {
                listUpdateCallback.onRemoved(i2, i3);
                return;
            }
            for (int i5 = i3 - 1; i5 >= 0; i5--) {
                int[] iArr = this.mOldItemStatuses;
                int i6 = i4 + i5;
                int i7 = iArr[i6] & 31;
                if (i7 == 0) {
                    listUpdateCallback.onRemoved(i2 + i5, 1);
                    for (PostponedUpdate postponedUpdate : list) {
                        postponedUpdate.currentPos--;
                    }
                } else if (i7 == 4 || i7 == 8) {
                    int i8 = iArr[i6] >> 5;
                    PostponedUpdate removePostponedUpdate = removePostponedUpdate(list, i8, false);
                    listUpdateCallback.onMoved(i2 + i5, removePostponedUpdate.currentPos - 1);
                    if (i7 == 4) {
                        listUpdateCallback.onChanged(removePostponedUpdate.currentPos - 1, 1, this.mCallback.getChangePayload(i6, i8));
                    }
                } else if (i7 == 16) {
                    list.add(new PostponedUpdate(i6, i2 + i5, true));
                } else {
                    throw new IllegalStateException("unknown flag for pos " + i6 + " " + Long.toBinaryString(i7));
                }
            }
        }

        List<Snake> getSnakes() {
            return this.mSnakes;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class PostponedUpdate {
        int currentPos;
        int posInOwnerList;
        boolean removal;

        public PostponedUpdate(int i2, int i3, boolean z) {
            this.posInOwnerList = i2;
            this.currentPos = i3;
            this.removal = z;
        }
    }
}
