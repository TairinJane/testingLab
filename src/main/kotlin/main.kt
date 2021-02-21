fun main(args: Array<String>) {
    /*for (x in -314..314) {
        println("cos(%.2f) = %.7f".format(x / 100.0, cos(x / 100.0)))
        println("series cos(%.2f) = %.7f".format(x / 100.0, cosToTaylorSeries(x / 100.0, 7)))
        println("actual == series? -> "+ cos(x / 100.0).equalsDelta(cosToTaylorSeries(x / 100.0, 7)))
        println()
    }*/

    val directedGraph = IntGraph(8).apply {
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
    println(directedGraph.toString())
}