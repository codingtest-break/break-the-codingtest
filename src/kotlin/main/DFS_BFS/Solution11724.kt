package DFS_BFS

import java.util.StringTokenizer

fun main() {
    var tokenizer = StringTokenizer(readln())
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()

//    val graph = Array(n){BooleanArray(n)}
    val graph = List<MutableList<Int>>(n) { mutableListOf()}
    repeat(m) {
        tokenizer = StringTokenizer(readln())
        val i = tokenizer.nextToken().toInt()
        val j = tokenizer.nextToken().toInt()
        graph[i-1].add(j-1)
        graph[j-1].add(i-1)
    }
    print(Solution11724().solution(n,m,graph))
}

class Solution11724 {
    private lateinit var isVisited: BooleanArray
    private lateinit var graph: List<MutableList<Int>>
    fun solution(n: Int, m: Int, graph: List<MutableList<Int>>): Int {
        var result = 0
        isVisited = BooleanArray(n)
        this.graph = graph

        for (node in 0 until n) {
            if (isVisited[node]) {
                continue
            }
            result++
            bfs(node)
        }
        return result
    }

    private fun bfs(node: Int) {
        val q = ArrayDeque<Int>()
        isVisited[node] = true
        q.addFirst(node)
        while (!q.isEmpty()) {
            val now = q.removeFirst()
            for (next in graph[now]) {
                if (isVisited[next]) {
                    continue
                }
                isVisited[next] = true
                q.addFirst(next)
            }
        }
    }
}