package DFS_BFS

import java.util.*

fun StringTokenizer.nextInt(): Int {
    return this.nextToken().toInt()
}

data class Point(val y: Int, val x: Int)

fun main() {
    val t = readln().toInt()
    var map : Array<Array<Int>>
    var visited : Array<BooleanArray>
    var plantedList: MutableList<Point>
    repeat(t) {
        var tokenizer = StringTokenizer(readln())

        val m = tokenizer.nextInt()
        val n = tokenizer.nextInt()
        val k = tokenizer.nextInt()

        map = Array(n) { Array(m) { 0 } }
        visited = Array(n) { BooleanArray(m) }

        repeat(k) {
            tokenizer = StringTokenizer(readln())
            val x = tokenizer.nextInt()
            val y = tokenizer.nextInt()
            map[y][x] = 1
        }
        println(Solution1012().solution(m, n, map, visited))
    }
}


class Solution1012 {
    private var m: Int = 0
    private var n: Int = 0
    private lateinit var map: Array<Array<Int>>
    private lateinit var visited: Array<BooleanArray>
    private var result = 0

    fun solution(
        m: Int,
        n: Int,
        map: Array<Array<Int>>,
        visited: Array<BooleanArray>,
    ): Int {
        this.m = m
        this.n = n
        this.map = map
        this.visited = visited

        val dy = intArrayOf(0, 1, 0, -1)
        val dx = intArrayOf(1, 0, -1, 0)

        val q: Queue<Point> = LinkedList()

        for (y in 0 until n) {
            for (x in 0 until m) {
                if (isAlreadyVisited(y,x) || isNotPlant(y,x)) {
                    continue
                }
                q.add(Point(y,x))
                visited[y][x] = true
                result++
                bfs(q, dy, dx)
            }
        }
        return result
    }

    private fun bfs(q: Queue<Point>, dy: IntArray, dx: IntArray) {
        while (!q.isEmpty()) {
            val now = q.poll()

            for (i in 0 until 4) {
                val nextY = now.y + dy[i]
                val nextX = now.x + dx[i]

                if (isOutOfBound(nextY, nextX) ||
                    isNotPlant(nextY, nextX) ||
                    isAlreadyVisited(nextY, nextX)
                ) {
                    continue
                }
                visited[nextY][nextX] = true
                q.add(Point(nextY, nextX))
            }
        }

    }

    private fun isOutOfBound(y: Int, x: Int): Boolean {
        return y < 0 || y >= n || x < 0 || x >= m
    }

    private fun isNotPlant(y: Int, x: Int): Boolean {
        return map[y][x] == 0
    }

    private fun isAlreadyVisited(y: Int, x: Int): Boolean {
        return visited[y][x]
    }

}