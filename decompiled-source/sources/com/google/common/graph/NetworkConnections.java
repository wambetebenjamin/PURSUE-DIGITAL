package com.google.common.graph;

import java.util.Set;
import javax.annotation.CheckForNull;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
interface NetworkConnections<N, E> {
    void addInEdge(E e2, N n, boolean z);

    void addOutEdge(E e2, N n);

    N adjacentNode(E e2);

    Set<N> adjacentNodes();

    Set<E> edgesConnecting(N n);

    Set<E> inEdges();

    Set<E> incidentEdges();

    Set<E> outEdges();

    Set<N> predecessors();

    @CheckForNull
    N removeInEdge(E e2, boolean z);

    N removeOutEdge(E e2);

    Set<N> successors();
}
