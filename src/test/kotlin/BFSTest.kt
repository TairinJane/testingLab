import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BFSTest {

    @Test
    fun traverseUndirectedFromDifferentNodes() {
        val graph = Graph<Int>().apply {
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
        Assertions.assertEquals(listOf(3, 0, 1, 5, 7, 2, 6, 4), breadthFirstTraversal(graph, 3))
        Assertions.assertEquals(listOf(6, 1, 4, 3, 5, 7, 0, 2), breadthFirstTraversal(graph, 6))
        Assertions.assertEquals(listOf(5, 1, 2, 3, 7, 6, 0, 4), breadthFirstTraversal(graph, 5))
    }

    @Test
    fun cantTraverseToDisconnectedNode() {
        val disconnectedGraph = Graph<Int>().apply {
            addEdge(0, 0)
            addEdge(1, 3)
            addEdge(1, 5)
            addEdge(1, 6)
            addEdge(2, 4)
            addEdge(2, 5)
            addEdge(4, 7)
            addEdge(5, 6)
            addEdge(6, 7)
        }
        Assertions.assertEquals(listOf(7, 4, 6, 2, 1, 5, 3), breadthFirstTraversal(disconnectedGraph, 7))
        Assertions.assertEquals(listOf(2, 4, 5, 7, 1, 6, 3), breadthFirstTraversal(disconnectedGraph, 2))
        Assertions.assertEquals(listOf(0), breadthFirstTraversal(disconnectedGraph, 0))
    }

    @Test
    fun traverseDirected() {
        val graph = Graph<Int>().apply {
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
        Assertions.assertEquals(listOf(3, 0, 1, 5, 7, 2, 6, 4), breadthFirstTraversal(graph, 3))
        Assertions.assertEquals(listOf(6, 1, 4, 3, 5, 7, 0, 2), breadthFirstTraversal(graph, 6))
        Assertions.assertEquals(listOf(5, 1, 2, 3, 7, 6, 0, 4), breadthFirstTraversal(graph, 5))
    }
}