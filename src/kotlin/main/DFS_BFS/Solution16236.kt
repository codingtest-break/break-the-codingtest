package DFS_BFS

import java.util.StringTokenizer

private const val SHARK = 9

data class Fish(val y: Int, val x: Int, val dist: Int)

fun main() {
    val n = readln().toInt()
    val map = Array(n){IntArray(n)}
    var startY = 0
    var startX = 0
    repeat(n) {y ->
        val tokenizer = StringTokenizer(readln())
        repeat(n) {x ->
            val nextValue = tokenizer.nextToken().toInt()
            map[y][x] = nextValue
            if (nextValue == SHARK) {
                startY = y
                startX = x
            }
        }
    }

    print(Solution16236().solution(n,map, startY, startX))
}

class Solution16236 {
    private var n = 0
    private lateinit var map: Array<IntArray>
    private var sharkSize = 2
    private var numOfGrow = 2
    private val dy = intArrayOf(-1, 0, 0, 1)
    private val dx = intArrayOf(0, -1, 1, 0)

    private val comparator = Comparator<Fish> {
        a,b ->
            when {
                (a.y > b.y) -> 1
                (a.y == b.y) -> {
                    when {
                        (a.x > b.x) -> 1
                        else -> 0
                    }
                }
                else -> -1
            }
    }

    fun solution(n: Int, map: Array<IntArray>, startY: Int, startX: Int): Int {
        this.n = n
        this.map = map
        var result = 0
        map[startY][startX] = 0
        var bfsResult = bfs(Fish(startY, startX, 0))
        result += bfsResult.dist

        while (bfsResult.dist != 0) {
             bfsResult = bfs(Fish(bfsResult.y, bfsResult.x, 0))
             result += bfsResult.dist
        }

        return result
    }

    private fun bfs(start: Fish): Fish {
        val q = ArrayDeque<Fish>()
        val visited = Array(n){BooleanArray(n)}
        q.addLast(start)
        val list = mutableListOf<Fish>()
        while (!q.isEmpty()) {
            val now = q.removeFirst()

            if (map[now.y][now.x] in 1 until sharkSize) {
                numOfGrow--
                if (numOfGrow == 0) {
                    sharkSize += 1
                    numOfGrow = sharkSize
                }
                map[now.y][now.x] = 0

                return Fish(now.y, now.x, now.dist)
            }

            for (i in 0 until 4) {
                val nextY = now.y + dy[i]
                val nextX = now.x + dx[i]

                if (isOutOfBound(nextY, nextX) ||
                            isLargerThanSharkSize(nextY, nextX) ||
                            isAlreadyVisited(visited,nextY, nextX)
                ) {
                    continue
                }
                list.add(Fish(nextY, nextX, now.dist + 1))
                visited[nextY][nextX] = true
            }
            list.sortWith(comparator)
        }


        return Fish(-1, -1, 0)
    }

    private fun isOutOfBound(y: Int, x: Int): Boolean {
        return y < 0 || y >= n || x < 0 || x >= n
    }

    private fun isLargerThanSharkSize(y: Int, x: Int): Boolean {
        return sharkSize < map[y][x]
    }

    private fun isAlreadyVisited(visited: Array<BooleanArray>, y: Int, x: Int): Boolean {
        return visited[y][x]
    }


}