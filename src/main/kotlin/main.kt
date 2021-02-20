import kotlin.math.cos

fun main(args: Array<String>) {
    /*for (x in -314..314) {
        println("cos(%.2f) = %.7f".format(x / 100.0, cos(x / 100.0)))
        println("series cos(%.2f) = %.7f".format(x / 100.0, cosToTaylorSeries(x / 100.0, 7)))
        println("actual == series? -> "+ cos(x / 100.0).equalsDelta(cosToTaylorSeries(x / 100.0, 7)))
        println()
    }*/

    /*val graph = Graph<Int>().apply {
        addEdge(0, 2)
        addEdge(1, 3)
        addEdge(1, 5)
        addEdge(1, 6)
        addEdge(2, 0)
        addEdge(2, 1)
        addEdge(2, 4)
        addEdge(3, 1)
        addEdge(3, 5)
        addEdge(4, 7)
        addEdge(5, 6)
        addEdge(5, 7)
        addEdge(6, 4)
        addEdge(6, 7)
        addEdge(7, 5)
    }

    val bfsList = breadthFirstTraversal(graph, 3)
    println(bfsList)

    val graph2 = Graph<Int>().apply {
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
    println(breadthFirstTraversal(graph2, 3))
    println(breadthFirstTraversal(graph2, 6))
    println(breadthFirstTraversal(graph2, 5))

    val graph3 = Graph<Int>().apply {
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
    println(breadthFirstTraversal(graph3, 7))
    println(breadthFirstTraversal(graph3, 2))
    println(breadthFirstTraversal(graph3, 0))*/

    val directedGraph = Graph<Int>(true).apply {
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
    println(breadthFirstTraversal(directedGraph, 0))
    println(breadthFirstTraversal(directedGraph, 7))
    println(breadthFirstTraversal(directedGraph, 2))
}