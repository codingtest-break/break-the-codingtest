package DFS_BFS

import java.util.StringTokenizer

private const val SQUARE = 1

data class Square(val y: Int, val x: Int)

fun main() {
    var tokenizer = StringTokenizer(readln())
    val m = tokenizer.nextToken().toInt()
    val n = tokenizer.nextToken().toInt()
    val k = tokenizer.nextToken().toInt()
    val map = Array(m) { IntArray(n) }
    repeat(k) {
        tokenizer = StringTokenizer(readln())
        val x1 = tokenizer.nextToken().toInt()
        val y1 = tokenizer.nextToken().toInt()
        val x2 = tokenizer.nextToken().toInt()
        val y2 = tokenizer.nextToken().toInt()

        for (y in y1 until y2) {
            for (x in x1 until x2) {
                if (map[y][x] == SQUARE) {
                    continue
                }
                map[y][x] = SQUARE
            }
        }
    }
    print(Solution2583().solution(m,n,map))
}

class Solution2583 {
    private var m = 0
    private var n = 0
    private lateinit var map: Array<IntArray>
    private lateinit var isVisited: Array<BooleanArray>
    fun solution(m: Int, n: Int, map: Array<IntArray>): String {
        this.m = m
        this.n = n
        this.map = map
        isVisited = Array(m){BooleanArray(n)}

        var result = 0
        val resultArr = mutableListOf<Int>()
        val sb = StringBuilder()
        for (y in 0 until m) {
            for (x in 0 until n) {
                if (isSquare(y,x) || isAlreadyVisited(y, x)) {
                    continue
                }
                result++
                resultArr.add(bfs(y,x))
            }
        }
        resultArr.sort()
        sb.append(result).append('\n')

        resultArr.forEach{ sb.append(it).append(' ')}
        sb.removeRange(sb.length - 1, sb.length)
        return sb.toString()
    }

    private fun bfs(y: Int, x: Int): Int {
        val dy = intArrayOf(0, 1, 0, -1)
        val dx = intArrayOf(1, 0, -1,0)
        var result = 0
        val q = ArrayDeque<Square>()
        q.addLast(Square(y,x))

        result++
        isVisited[y][x] = true

        while (!q.isEmpty()) {
            val now = q.removeFirst()

            for (i in 0 until 4) {
                val nextY = now.y + dy[i]
                val nextX = now.x + dx[i]

                if (isOutOfBound(nextY, nextX) ||
                        isSquare(nextY, nextX) ||
                        isAlreadyVisited(nextY, nextX)) {
                    continue
                }
                result++
                isVisited[nextY][nextX] = true
                q.addLast(Square(nextY, nextX))
            }
        }

        return result
    }

    private fun isOutOfBound(y: Int, x:Int): Boolean {
        return y < 0 || y >= m || x < 0 || x >= n
    }

    private fun isSquare(y:Int, x:Int): Boolean {
        return map[y][x] == SQUARE
    }

    private fun isAlreadyVisited(y: Int, x: Int): Boolean {
        return isVisited[y][x]
    }
}