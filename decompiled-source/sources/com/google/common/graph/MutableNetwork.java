package com.google.common.graph;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public interface MutableNetwork<N, E> extends Network<N, E> {
    boolean addEdge(EndpointPair<N> endpointPair, E e2);

    boolean addEdge(N n, N n2, E e2);

    boolean addNode(N n);

    boolean removeEdge(E e2);

    boolean removeNode(N n);
}
