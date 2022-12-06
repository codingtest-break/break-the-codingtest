package DFS_BFS

import java.util.StringTokenizer

private const val EMPTY = 0
private const val WALL = 1
private const val VIRUS = 2

//private data class Point(val y: Int, val x: Int)

fun main() {
    var tokenizer = StringTokenizer(readln())
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()
    val map = Array(n){IntArray(m)}
    val q = mutableListOf<Point>()

    repeat(n) { y ->
        tokenizer = StringTokenizer(readln())
        repeat(m) {x ->
            val point = tokenizer.nextToken().toInt()
            if (point == VIRUS) {
                q.add(Point(y,x))
            }
            map[y][x] = point
        }
    }

    print(Solution14502().solution(n,m, map, q))
}

class Solution14502 {
    private lateinit var map: Array<IntArray>
    private lateinit var copyMap: Array<IntArray>
    private lateinit var virusList: MutableList<Point>
    private var n = 0
    private var m = 0
    private var result = Int.MIN_VALUE

    fun solution(n: Int, m: Int, map: Array<IntArray>, virusList: MutableList<Point>): Int {
        this.n = n
        this.m = m
        this.map = map
        this.virusList = virusList
        this.copyMap = map.copyOf()

        wallBacktrack(0, 0)
        return result
    }

    private fun wallBacktrack(wallCount: Int, prevY: Int) {
        if (wallCount == 3) {
            repeat(n) {
                map[it] = copyMap[it].copyOf()
            }
            bfs(virusList)
            result = maxOf(result, countNotInfected())
            repeat(n) {
                copyMap[it] = map[it].copyOf()
            }
            return
        }
        for (y in prevY until n) {
            for (x in 0 until m) {
                if (isWall(y, x) || isAlreadyVisited(y,x)) {
                    continue
                }
                copyMap[y][x] = WALL
                wallBacktrack(wallCount+1,y)
                copyMap[y][x] = EMPTY
            }
        }
    }

    private fun bfs(virusList: MutableList<Point>) {
        val q = ArrayDeque<Point>()
        val dy = intArrayOf(0, 1, 0, -1)
        val dx = intArrayOf(1, 0, -1, 0)
        q.addAll(virusList)
        while (!q.isEmpty()) {
            val now = q.removeFirst()

            for (i in 0 until 4) {
                val nextY = now.y + dy[i]
                val nextX = now.x + dx[i]

                if (isOutOfBound(nextY, nextX) ||
                        isWall(nextY, nextX) ||
                        isAlreadyVisited(nextY, nextX)) {
                    continue
                }
                copyMap[nextY][nextX] = VIRUS
                q.add(Point(nextY, nextX))
            }
        }
    }

    private fun isOutOfBound(y: Int, x: Int): Boolean {
        return y < 0 || y >= n || x < 0 || x >= m
    }

    private fun isWall(y: Int, x: Int): Boolean {
        return copyMap[y][x] == WALL
    }

    private fun isAlreadyVisited(y: Int, x: Int): Boolean {
        return copyMap[y][x] == VIRUS
    }

    private fun countNotInfected(): Int  {
        var result = 0
        for (row in copyMap) {
            result += row.count { it == EMPTY }
        }
        return result
    }
}