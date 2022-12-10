package DFS_BFS

import java.util.StringTokenizer

data class Point(val y: Int, val x: Int, val isBroken: Int)

fun main() {
    var tokenizer = StringTokenizer(readln())
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()
    val map = Array(n) { IntArray(m) }

    repeat(n) { i ->
        map[i] = readln().toCharArray().map { it.code - '0'.code }.toIntArray()
    }
    print(Solution2206().solution(n, m, map))
}

class Solution2206 {
    private var n = 0
    private var m = 0
    private lateinit var map: Array<IntArray>
    private lateinit var visited: Array<Array<Array<Int>>>
    fun solution(n: Int, m: Int, map: Array<IntArray>): Int {
        this.n = n
        this.m = m
        this.map = map
        this.visited = Array(2) { Array(n) { Array(m) { 0 } } }
        val dy = intArrayOf(0, 1, 0, -1)
        val dx = intArrayOf(1, 0, -1, 0)

        fun bfs() : Int {
            val q = ArrayDeque<Point>()
            q.addLast(Point(0, 0, 0))
            visited[0][0][0] = 1
            while (!q.isEmpty()) {
                val now = q.removeFirst()

                if (now.y == n - 1 && now.x == m - 1) {
                    return visited[now.isBroken][now.y][now.x]
                }

                for (i in 0 until 4) {
                    val nextY = now.y + dy[i]
                    val nextX = now.x + dx[i]
                    if (isInBound(nextY, nextX)) {
                        if (isNotVisited(nextY, nextX, now.isBroken) &&
                            isNotWall(nextY, nextX)
                        ) {
                            visited[now.isBroken][nextY][nextX] = visited[now.isBroken][now.y][now.x] + 1
                            q.addLast(Point(nextY, nextX, now.isBroken))
                        } else if (!isNotWall(nextY, nextX) &&
                            now.isBroken == 0
                        ) {
                            visited[1][nextY][nextX] = visited[0][now.y][now.x] + 1
                            q.addLast(Point(nextY, nextX, 1))
                        }

                    }
                }
            }
            return -1
        }


        val result =  bfs()

        visited.forEach {
            it.forEach { y -> println(y.joinToString()) }
            println()
        }

        return result
    }

    private fun isInBound(y: Int, x: Int): Boolean {
        return y in 0 until n && x in 0 until m
    }

    private fun isNotVisited(y: Int, x: Int, isBroken: Int): Boolean {
        return visited[isBroken][y][x] == 0
    }

    private fun isNotWall(y: Int, x: Int): Boolean {
        return map[y][x] != 1
    }
}