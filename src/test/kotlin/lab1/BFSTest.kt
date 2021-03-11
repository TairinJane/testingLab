package lab1

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BFSTest {

    @Test
    fun traverseFromDifferentNodes() {
        val graph = IntGraph(8).apply {
            addEdge(0, 2)
            addEdge(0, 3)
            addEdge(1, 3)
            addEdge(1, 5)
            addEdge(1, 6)
            addEdge(2, 5)
            addEdge(3, 5)
            addEdge(3, 7)
            addEdge(4, 6)
            addEdge(4, 7)
            addEdge(5, 7)
        }
        Assertions.assertEquals(listOf(3, 5, 7), bfs(graph, 3))
        Assertions.assertEquals(listOf(0, 2, 3, 5, 7), bfs(graph, 0))
        Assertions.assertEquals(listOf(1, 3, 5, 6, 7), bfs(graph, 1))
    }

    @Test
    fun cantTraverseToDisconnectedNode() {
        val disconnectedGraph = IntGraph(8).apply {
            addEdge(1, 3)
            addEdge(1, 5)
            addEdge(1, 6)
            addEdge(2, 4)
            addEdge(2, 5)
            addEdge(4, 7)
            addEdge(5, 6)
            addEdge(6, 7)
        }
        Assertions.assertEquals(listOf(1, 3, 5, 6, 7), bfs(disconnectedGraph, 1))
        Assertions.assertEquals(listOf(2, 4, 5, 7, 6), bfs(disconnectedGraph, 2))
        Assertions.assertEquals(listOf(0), bfs(disconnectedGraph, 0))
    }

    @Test
    fun traverseOneNode() {
        val graph = IntGraph(1).apply {
            addEdge(0, 0)
        }
        Assertions.assertEquals(listOf(0), bfs(graph, 0))
    }

    @Test
    fun smallGraph() {
        val graph = IntGraph(5).apply {
            addEdge(0, 3)
            addEdge(0, 2)
            addEdge(1, 0)
            addEdge(2, 1)
            addEdge(2, 4)
            addEdge(4, 1)
            addEdge(4, 3)
        }
        Assertions.assertEquals(listOf(0, 2, 3, 1, 4), bfs(graph, 0))
    }
}