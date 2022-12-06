package DFS_BFS

data class Color(val y: Int, val x: Int)

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val map = Array(n) { CharArray(n) }

    repeat(n) {
        map[it] = readLine().toCharArray()
    }
    print(Solution10026().solution(n, map))
}

class Solution10026 {
    private val sb = StringBuilder()
    private var n = 0
    private lateinit var map: Array<CharArray>
    private lateinit var visited: Array<BooleanArray>
    fun solution(n: Int, map: Array<CharArray>): String {
        this.n = n
        this.map = map
        this.visited = Array(n) { BooleanArray(n) }
        var blindCount = 0
        var count = 0

        for (y in 0 until n) {
            for (x in 0 until n) {
                if (isAlreadyVisited(y,x)) {
                    continue
                }
                count++
                bfs(false, y, x)
            }
        }

        visited = Array(n) { BooleanArray(n) }

        for (y in 0 until n) {
            for (x in 0 until n) {
                if (isAlreadyVisited(y,x)) {
                    continue
                }
                blindCount++
                bfs(true, y, x)
            }
        }

        sb.append(count).append(' ').append(blindCount)
        return sb.toString()
    }

    private fun bfs(isBlindness: Boolean, startY: Int, startX: Int) {
        val q = ArrayDeque<Color>()
        val dy = intArrayOf(0, 1, 0, -1)
        val dx = intArrayOf(1, 0, -1, 0)
        q.addFirst(Color(startY, startX))
        visited[startY][startX] = true
        while (!q.isEmpty()) {
            val now = q.removeFirst()

            for (i in 0 until 4) {
                val nextY = now.y + dy[i]
                val nextX = now.x + dx[i]

                if (isOutOfBound(nextY, nextX) ||
                    isAlreadyVisited(nextY, nextX) ||
                    isOtherColor(isBlindness, nextY, nextX, map[now.y][now.x])
                ) {
                    continue
                }
                visited[nextY][nextX] = true
                q.addLast(Color(nextY, nextX))
            }
        }
    }

    private fun isOutOfBound(y: Int, x: Int): Boolean {
        return y < 0 || y >= n || x < 0 || x >= n
    }

    private fun isAlreadyVisited(y: Int, x: Int): Boolean {
        return visited[y][x]
    }

    private fun isOtherColor(isBlindness: Boolean, y: Int, x: Int, c: Char): Boolean {
        return if (isBlindness) {
            when (map[y][x]) {
                'R', 'G' -> {
                    !((c == 'R') || (c == 'G'))
                }

                else -> map[y][x] != c
            }
        } else map[y][x] != c
    }

}