package DFS_BFS

import java.util.StringTokenizer

fun main() {
    val tokenizer = StringTokenizer(readln())
    val n = tokenizer.nextToken().toInt()
    val k = tokenizer.nextToken().toInt()
    val map = IntArray(100001) { Int.MAX_VALUE }
    print(Solution1697().solution(n, k, map))
}

class Solution1697 {
    fun solution(n: Int, k: Int, map: IntArray): Int {
        fun bfs() {
            map[n] = 1
            val q = ArrayDeque<Int>()
            q.addFirst(n)

            while (!q.isEmpty()) {
                val now = q.removeFirst()
                var next: Int

                for (it in 0 until 4) {
                    next = when (it) {
                        0 -> {
                            if (now - 1 >= 0) now - 1 else continue
                        }

                        1 -> {
                            if (now + 1 < 100001) now + 1 else continue
                        }

                        else -> {
                            if ((now * 2) <= 100000) now * 2 else continue
                        }
                    }
                    if (map[next] == Int.MAX_VALUE) {
                        map[next] = map[now] + 1
                        q.addLast(next)
                    }
                }
            }
        }
        bfs()
        return map[k] - 1
    }
}