class Graph<T>(private val directed: Boolean = false) {
    val adjacencyMap: HashMap<T, HashSet<T>> = HashMap()

    fun addEdge(from: T, to: T) {
        adjacencyMap
            .computeIfAbsent(from) { HashSet() }
            .add(to)
        if (!directed) adjacencyMap
            .computeIfAbsent(to) { HashSet() }
            .add(from)
    }

    override fun toString(): String = StringBuffer().apply {
        for (key in adjacencyMap.keys) {
            append("$key -> ")
            append(adjacencyMap[key]?.joinToString(", ", "[", "]\n"))
        }
    }.toString()

}

fun <T> breadthFirstTraversal(
    graph: Graph<T>,
    startNode: T,
    maxDepth: Int = Int.MAX_VALUE
): List<T> {
    println(graph.toString())

    class VisitedMap {
        val traversalList = mutableListOf<T>()

        val visitedMap = mutableMapOf<T, Boolean>().apply {
            for (node in graph.adjacencyMap.keys) this[node] = false
        }

        fun isNotVisited(node: T): Boolean = !visitedMap[node]!!

        fun markVisited(node: T) {
            visitedMap[node] = true
            traversalList.add(node)
        }
    }

    val visitedMap = VisitedMap()

    val depthMap = mutableMapOf<T, Int>().apply {
        for (node in graph.adjacencyMap.keys) this[node] = Int.MAX_VALUE
    }

    class Queue {
        val deck: ArrayDeque<T> = ArrayDeque()
        fun add(node: T, depth: Int) {
            deck.add(node)
            depthMap[node] = depth
        }

        fun addAdjacentNodes(currentNode: T, depth: Int) {
            for (node in graph.adjacencyMap[currentNode]!!) {
                add(node, depth)
            }
        }

        fun isNotEmpty() = deck.isNotEmpty()
        fun remove() = deck.removeFirst()
    }

    val queue = Queue()

    queue.add(startNode, 0)

    while (queue.isNotEmpty()) {
        val currentNode = queue.remove()
        val currentDepth = depthMap[currentNode]!!

        if (currentDepth <= maxDepth) {
            if (visitedMap.isNotVisited(currentNode)) {
                visitedMap.markVisited(currentNode)
                queue.addAdjacentNodes(currentNode, currentDepth + 1)
            }
        }

    }

    return visitedMap.traversalList
}