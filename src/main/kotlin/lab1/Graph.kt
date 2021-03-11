package lab1

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
): List<Int> {

    println("Start")
    val traversalList = mutableListOf<Int>()

    val visitedMap = mutableMapOf<Int, Boolean>().apply {
        for (node in graph.adjacencyMap.keys) this[node] = false
    }

    val queue: ArrayDeque<Int> = ArrayDeque()
    queue.add(startNode)
    println("Add start node $startNode")

    while (queue.isNotEmpty()) {
        println("Queue is not empty: $queue")
        val currentNode = queue.removeFirst()

        if (!visitedMap[currentNode]!!) {
            println("Node $currentNode is not visited")
            visitedMap[currentNode] = true
            traversalList.add(currentNode)
            print("Add neighbours to queue:")
            for (node in graph.adjacencyMap[currentNode]!!) {
                queue.add(node)
                print(" $node")
            }
            println()
        } else println("Node $currentNode is visited")
    }
    println("Queue is empty -> traversal: $traversalList")

    return traversalList
}