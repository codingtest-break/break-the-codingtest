package DFS_BFS

private const val SEA = 0
private const val LAND = 1

data class Point(val y: Int, val x: Int)

fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    do {
        val nAndM = readLine().split(" ").map { it.toInt() }.toIntArray()
        val map = Array(nAndM[1]) { Array(nAndM[0]) { 0 } }
        repeat(nAndM[1]) { i ->
            map[i] = readLine().split(" ").map { it.toInt() }.toTypedArray()
        }
        sb.append(Solution4963().solution(nAndM[0], nAndM[1], map)).append('\n')
    } while (nAndM[0] != 0 && nAndM[1] != 0)
    sb.setLength(sb.length-3)
    print(sb.toString())
}

class Solution4963 {
    private var w = 0
    private var h = 0
    private lateinit var map: Array<Array<Int>>
    private lateinit var visited: Array<BooleanArray>

    fun solution(w: Int, h: Int, map: Array<Array<Int>>): Int {
        this.w = w
        this.h = h
        this.map = map
        this.visited = Array(h){BooleanArray(w)}

        var result = 0

        for (y in 0 until h ) {
            for (x in 0 until w) {
                if (isSea(y, x) ||
                    isAlreadyVisited(y, x)) {
                    continue
                }
                result++
                bfs(Point(y,x))
            }
        }
        return result
    }

    private fun bfs(start: Point) {
        val q = ArrayDeque<Point>()
        val dy = intArrayOf(-1, -1, -1, 0,0,1,1,1)
        val dx = intArrayOf(-1, 0, 1, -1, 1, -1,0,1)
        q.addFirst(start)
        visited[start.y][start.x] = true

        while (!q.isEmpty()) {
            val now = q.removeFirst()

            for (i in 0 until 8) {
                val nextY = now.y + dy[i]
                val nextX = now.x + dx[i]

                if (isOutOfBound(nextY, nextX) ||
                        isSea(nextY, nextX) ||
                        isAlreadyVisited(nextY, nextX)) {
                    continue
                }
                visited[nextY][nextX] = true
                q.addLast(Point(nextY, nextX))
            }
        }
    }

    private fun isOutOfBound(y: Int, x:Int): Boolean {
        return y < 0 || y >= h || x < 0 || x >= w
    }

    private fun isSea(y: Int, x: Int): Boolean {
        return map[y][x] == SEA
    }

    private fun isAlreadyVisited(y: Int, x: Int): Boolean {
        return visited[y][x]
    }
}