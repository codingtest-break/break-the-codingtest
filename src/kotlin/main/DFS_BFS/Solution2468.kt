package DFS_BFS

import java.util.StringTokenizer

data class Point(val y: Int, val x: Int)

fun main() {
    val n = readln().toInt()
    val checkHeights = BooleanArray(101)
    val heights = mutableListOf<Int>()
    val map = Array(n) { Array(n) { 0 } }
    heights.add(0)
    repeat(n) {y ->
        val tokenizer = StringTokenizer(readln())
        repeat(n) {x ->
            val height = tokenizer.nextToken().toInt()
            if (!checkHeights[height]) {
                heights.add(height)
                checkHeights[height] = true
            }
            map[y][x] = height
        }
    }
    heights.sort()
    print(Solution2468().solution(n, heights, map))
}

class Solution2468 {
    private lateinit var map: Array<Array<Int>>
    private lateinit var heights: MutableList<Int>
    private var n = 0

    fun solution(n: Int, heights: MutableList<Int>, map: Array<Array<Int>>): Int {
        this.map = map
        this.heights = heights
        this.n = n

        var result = Int.MIN_VALUE

        for (rain in heights) {
            result = maxOf(rainDrop(rain), result)
        }

        return result
    }

    private fun rainDrop(rain: Int): Int {
        var result = 0
        val isVisited = Array(n) {BooleanArray(n)}

        fun bfs(y: Int, x: Int) {
            val q = ArrayDeque<Point>()
            val dy = intArrayOf(-1, 0 , 1, 0)
            val dx = intArrayOf(0, 1, 0, -1)
            q.addLast(Point(y,x))
            isVisited[y][x] = true

            while (!q.isEmpty()) {
                val now = q.removeFirst()

                for (i in 0 until 4) {
                    val nextY = now.y + dy[i]
                    val nextX = now.x + dx[i]

                    if (isOutOfBound(nextY, nextX) ||
                        isFlowed(nextY, nextX, rain) ||
                            isVisited[nextY][nextX]) {
                        continue
                    }
                    isVisited[nextY][nextX] = true
                    q.addLast(Point(nextY, nextX))
                }
            }
        }

        for (y in 0 until n) {
            for (x in 0 until n) {
                if(isVisited[y][x] ||
                        isFlowed(y,x, rain)) {
                    continue
                }
                result++
                bfs(y, x)
            }
        }
        return result
    }



    private fun isFlowed(y: Int, x: Int, rain: Int): Boolean {
        return map[y][x] <= rain
    }

    private fun isOutOfBound(y: Int, x: Int): Boolean {
        return y < 0 || y >= n || x < 0 || x >= n
    }
}