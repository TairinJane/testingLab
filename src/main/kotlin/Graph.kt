class IntGraph(nodeCount: Int, private val directed: Boolean = true) {
    val adjacencyMap: HashMap<Int, HashSet<Int>> = HashMap()

    init {
        for (node in 0 until nodeCount) {
            adjacencyMap[node] = HashSet()
        }
    }

    fun addEdge(from: Int, to: Int) {
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

fun bfs(
    graph: IntGraph,
    startNode: Int,
    maxDepth: Int = Int.MAX_VALUE
): List<Int> {

    val traversalList = mutableListOf<Int>()

    val visitedMap = mutableMapOf<Int, Boolean>().apply {
        for (node in graph.adjacencyMap.keys) this[node] = false
    }

    val depthMap = mutableMapOf<Int, Int>().apply {
        for (node in graph.adjacencyMap.keys) this[node] = Int.MAX_VALUE
    }

    val queue: ArrayDeque<Int> = ArrayDeque()
    queue.add(startNode)
    depthMap[startNode] = 0

    while (queue.isNotEmpty()) {
        val currentNode = queue.removeFirst()
        val currentDepth = depthMap[currentNode]!!

        if (currentDepth <= maxDepth) {
            if (!visitedMap[currentNode]!!) {
                visitedMap[currentNode] = true
                traversalList.add(currentNode)
                for (node in graph.adjacencyMap[currentNode]!!) {
                    queue.add(node)
                    depthMap[node] = currentDepth + 1
                }
            }
        }
    }

    return traversalList
}