package DFS_BFS

import java.util.StringTokenizer

data class Tomato(val y: Int, val x: Int) {
    companion object {
        var m = 0
        var n = 0
        lateinit var map: Array<IntArray>

        fun isOutOfBound(y: Int, x: Int): Boolean {
            return y < 0 || y >= n || x < 0 || x >= m
        }

        fun isNotTomato(y: Int, x: Int): Boolean {
            return map[y][x] == -1
        }

        fun isAlreadyVisit(y: Int, x: Int): Boolean {
            return map[y][x] >= 1
        }
    }
}

fun main() {
    val mAndN = readln().split(" ").map { it.toInt() }
    val map = Array(mAndN[1]) {IntArray(mAndN[0])}
    val q : ArrayDeque<Tomato> = ArrayDeque()
    Tomato.m = mAndN[0]
    Tomato.n = mAndN[1]
    Tomato.map = map

    for (i in 0 until mAndN[1]) {
        val tokenizer = StringTokenizer(readln())
        for (j in 0 until mAndN[0]) {
            val nextValue = tokenizer.nextToken().toInt()
            map[i][j] = nextValue
            if (nextValue == 1) {
                q.add(Tomato(i,j))
            }
        }
    }
    print(Solution7576().solution(mAndN[0], mAndN[1], map, q))
}

class Solution7576 {
    fun solution(m: Int, n: Int, map: Array<IntArray>, q: ArrayDeque<Tomato>): Int {
        val dy = intArrayOf(0, 1, 0, -1)
        val dx = intArrayOf(1, 0, -1, 0)

        fun bfs() {
            while (!q.isEmpty()) {
                val now = q.removeFirst()

                repeat (4) {
                    val nextY = now.y + dy[it]
                    val nextX = now.x + dx[it]

                    if (!Tomato.isOutOfBound(nextY, nextX) &&
                            !Tomato.isNotTomato(nextY, nextX) &&
                            !Tomato.isAlreadyVisit(nextY, nextX)) {
                        q.add(Tomato(nextY, nextX))
                        map[nextY][nextX] = map[now.y][now.x] + 1
                    }
                }
            }
        }
        bfs()
        var result = 0
        for (y in 0 until n) {
            for (x in 0 until m) {
                if (map[y][x] == 0) {
                    return -1
                }
                result = maxOf(map[y][x], result)
            }
        }
        return result - 1
    }
}