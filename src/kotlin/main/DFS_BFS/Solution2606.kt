package DFS_BFS

import java.util.StringTokenizer

fun main() {
    val n = readln().toInt()
    val m = readln().toInt()
    val map = Array(n + 1) { Array(n + 1) { false } }
    repeat(m) {
        val tokenizer = StringTokenizer(readln())
        val y = tokenizer.nextToken().toInt()
        val x = tokenizer.nextToken().toInt()

        map[y][x] = true
        map[x][y] = true
    }
    print(Solution2606().solution(n, m, map))
}

class Solution2606 {
    fun solution(n: Int, m: Int, map: Array<Array<Boolean>>): Int {
        val isVisited = Array(n+1) { false }
        // 1과 연결되어 있는 노드 수를 구해야 하기 때문에 -1로 시작
        var result = -1

        fun dfs(present: Int) {
            isVisited[present] = true
            result++
            for (i in 1..n) {
                if(!isVisited[i] && map[present][i]) {
                    dfs(i)
                }
            }
        }

        dfs(1)
        return result
    }
}