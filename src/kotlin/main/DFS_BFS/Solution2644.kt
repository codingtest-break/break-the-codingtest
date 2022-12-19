package DFS_BFS

import java.util.StringTokenizer

fun main() {
    val n = readln().toInt()
    var tokenizer = StringTokenizer(readln())
    val p1 = tokenizer.nextToken().toInt()
    val p2 = tokenizer.nextToken().toInt()
    val m = readln().toInt()
    val graph = Array(n+1){BooleanArray(n+1)}

    repeat(m) {
        tokenizer = StringTokenizer(readln())
        val x = tokenizer.nextToken().toInt()
        val y = tokenizer.nextToken().toInt()
        graph[x][y] = true
        graph[y][x] = true
    }
    print(Solution2644().solution(n, p1, p2, graph))
}

class Solution2644 {
    private lateinit var visited: BooleanArray
    fun solution(n: Int, p1: Int, p2: Int, graph: Array<BooleanArray>): Int {
        var result = -1
        visited = BooleanArray(n+1)

        fun dfs(depth: Int, prev: Int, dest: Int) {
            visited[prev] = true

            if (prev == p2) {
                result = depth
                return
            }

            for (i in 1..n) {
               if (!graph[prev][i] || visited[i]) {
                   continue
               }
                dfs(depth + 1, i, dest)
            }
        }

        dfs(0, p1, p2)

        return result
    }


}