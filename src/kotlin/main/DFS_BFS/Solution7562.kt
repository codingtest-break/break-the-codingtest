package DFS_BFS

import java.util.StringTokenizer

data class Position(val y: Int, val x: Int, val depth: Int = 0)

fun main() {
    val t = readln().toInt()
    repeat(t) {
        val n = readln().toInt()
        var tokenizer = StringTokenizer(readln())
        val start = Position(tokenizer.nextToken().toInt(), tokenizer.nextToken().toInt())
        tokenizer = StringTokenizer(readln())
        val end = Position(tokenizer.nextToken().toInt(), tokenizer.nextToken().toInt())
        println(Solution7562().solution(n, start, end))
    }
}

class Solution7562 {
    private var n = 0
    private lateinit var end: Position
    private lateinit var isVisited: Array<BooleanArray>
    fun solution(n: Int, start: Position, end: Position): Int {
        this.n = n
        this.end = end
        isVisited = Array(n){ BooleanArray(n) }
        val dy = intArrayOf(-2,-1,1,2,2,1,-1,-2)
        val dx = intArrayOf(1,2,2,1,-1,-2,-2,-1)

        if (start.y == end.y && start.x == end.x) {
            return 0
        }

        var result = 0

        fun bfs() {
            val q = ArrayDeque<Position>()
            q.addLast(start)
            isVisited[start.y][start.x] = true
            while (!q.isEmpty()) {
                val now = q.removeFirst()
                if (now.y == end.y && now.x == end.x) {
                    result = now.depth
                    return
                }

                repeat (8) {i ->
                    val nextY = now.y + dy[i]
                    val nextX = now.x + dx[i]

                    if (!isOutOfBound(nextY, nextX) &&
                            !isAlreadyVisited(nextY, nextX)) {

                        isVisited[nextY][nextX] = true
                        q.addLast(Position(nextY, nextX, now.depth + 1))
                    }
                }
            }
        }

        bfs()
        return result
    }

    private fun isOutOfBound(y: Int, x: Int): Boolean {
        return y !in 0 until n || x !in 0 until n
    }

    private fun isAlreadyVisited(y: Int, x: Int): Boolean {
        return isVisited[y][x]
    }
}