package com.ss.android.socialbase.downloader.utils;
/* loaded from: classes9.dex */
public class DownloadStenographer {
    private static final int MAX_NODE_COUNT = 20;
    private static final int SECONDS_TO_MILLS = 1000;
    private int count;
    private Node head;
    private int maxCount;
    private Node tail;

    public DownloadStenographer() {
        this.maxCount = 10;
    }

    public DownloadStenographer(int i2) {
        this.maxCount = i2 > 20 ? 20 : i2;
    }

    public boolean markProgress(long j2, long j3) {
        synchronized (this) {
            Node node = this.head;
            if (node != null) {
                if (j2 >= node.curBytes && j3 >= node.when) {
                    Node node2 = node.next;
                    if (node2 != null && j3 - node2.when < 1000) {
                        node.curBytes = j2;
                        node.when = j3;
                        return true;
                    }
                }
                return false;
            }
            Node obtainNode = obtainNode();
            obtainNode.curBytes = j2;
            obtainNode.when = j3;
            if (node != null) {
                obtainNode.next = node;
                node.prev = obtainNode;
            }
            this.head = obtainNode;
            return true;
        }
    }

    public long getRecentDownloadSpeed(long j2, long j3) {
        synchronized (this) {
            Node node = this.head;
            if (node == null) {
                return -1L;
            }
            Node findFirstNodeNearWhen = findFirstNodeNearWhen(j2);
            if (findFirstNodeNearWhen == null) {
                return -1L;
            }
            long j4 = node.curBytes - findFirstNodeNearWhen.curBytes;
            long j5 = j3 - findFirstNodeNearWhen.when;
            if (j4 < 0 || j5 <= 0) {
                return -1L;
            }
            return j4 / j5;
        }
    }

    private Node obtainNode() {
        Node node;
        int i2 = this.count;
        if (i2 >= this.maxCount && (node = this.tail) != null) {
            Node node2 = node.prev;
            node.prev = null;
            this.tail = node2;
            if (node2 != null) {
                node2.next = null;
            }
            return node;
        }
        this.count = i2 + 1;
        return new Node();
    }

    private Node findFirstNodeNearWhen(long j2) {
        Node node = this.head;
        Node node2 = null;
        while (node != null && node.when > j2) {
            node2 = node;
            node = node.next;
        }
        return (node == null || node2 == null || node == node2 || j2 - node.when >= node2.when - j2) ? node2 : node;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class Node {
        long curBytes;
        Node next;
        Node prev;
        long when;

        private Node() {
        }
    }
}
