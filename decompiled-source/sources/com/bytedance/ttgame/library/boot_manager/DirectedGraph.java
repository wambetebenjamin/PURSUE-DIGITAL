package com.bytedance.ttgame.library.boot_manager;

import android.util.Log;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class DirectedGraph {
    private static final String TAG = "DirectedGraph";
    private final Map<BootTask, Vertex> directedGraph = new LinkedHashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class Vertex {
        private final BootTask content;
        private int inDegree = 0;
        private final List<Edge> adjEdges = new LinkedList();

        static /* synthetic */ int access$106(Vertex vertex) {
            int i2 = vertex.inDegree - 1;
            vertex.inDegree = i2;
            return i2;
        }

        static /* synthetic */ int access$108(Vertex vertex) {
            int i2 = vertex.inDegree;
            vertex.inDegree = i2 + 1;
            return i2;
        }

        public Vertex(BootTask bootTask) {
            this.content = bootTask;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class Edge {
        private final Vertex endVertex;

        public Edge(Vertex vertex) {
            this.endVertex = vertex;
        }
    }

    public void buildGraph(Map<Class<?>, BootTask> map) {
        if (map.isEmpty()) {
            return;
        }
        for (BootTask bootTask : map.values()) {
            if (bootTask.dependOns() == null || bootTask.dependOns().size() == 0) {
                if (!this.directedGraph.containsKey(bootTask)) {
                    this.directedGraph.put(bootTask, new Vertex(bootTask));
                }
            } else {
                try {
                    for (String str : bootTask.dependOns()) {
                        Class<?> cls = Class.forName(str);
                        if (cls != null && map.containsKey(cls)) {
                            BootTask bootTask2 = map.get(cls);
                            Vertex vertex = this.directedGraph.get(bootTask2);
                            if (vertex == null) {
                                vertex = new Vertex(bootTask2);
                                this.directedGraph.put(bootTask2, vertex);
                            }
                            Vertex vertex2 = this.directedGraph.get(bootTask);
                            if (vertex2 == null) {
                                vertex2 = new Vertex(bootTask);
                                this.directedGraph.put(bootTask, vertex2);
                            }
                            vertex.adjEdges.add(new Edge(vertex2));
                            Vertex.access$108(vertex2);
                        }
                    }
                } catch (Throwable unused) {
                    Log.e(TAG, "解析任务依赖失败");
                }
            }
        }
    }

    public List<BootTask> topologicalSort() {
        ArrayList arrayList = new ArrayList();
        LinkedList linkedList = new LinkedList();
        for (Vertex vertex : this.directedGraph.values()) {
            if (vertex.inDegree == 0) {
                linkedList.offer(vertex);
            }
        }
        int i2 = 0;
        while (!linkedList.isEmpty()) {
            Vertex vertex2 = (Vertex) linkedList.poll();
            Log.d(TAG, vertex2.content + "");
            arrayList.add(vertex2.content);
            i2++;
            for (Edge edge : vertex2.adjEdges) {
                if (Vertex.access$106(edge.endVertex) == 0) {
                    linkedList.offer(edge.endVertex);
                }
            }
        }
        if (i2 == this.directedGraph.size()) {
            return arrayList;
        }
        throw new GraphicHasCircleException("circle detected");
    }
}
