package DFS_BFS

import java.util.StringTokenizer

private const val ROTTEN_TOMATO = 1
private const val FRESH_TOMATO = 0
private const val EMPTY = -1

data class Tomato(val z: Int, val y: Int, val x: Int)

fun main() {
    var tokenizer = StringTokenizer(readln())
    val m = tokenizer.nextToken().toInt()
    val n = tokenizer.nextToken().toInt()
    val h = tokenizer.nextToken().toInt()
    val map = Array(h){Array(n){Array(m){0} } }

    val q = ArrayDeque<Tomato>()

    for (z in 0 until h) {
        for (y in 0 until n) {
            tokenizer = StringTokenizer(readln())
            for (x in 0 until m) {
                val slot = tokenizer.nextToken().toInt()
                map[z][y][x] = slot
                if (slot == ROTTEN_TOMATO)  {
                    q.addLast(Tomato(z,y,x))
                }
            }
        }
    }

    print(Solution7569().solution(m,n,h,map,q))
}

class Solution7569 {
    private lateinit var map: Array<Array<Array<Int>>>
    private lateinit var q: ArrayDeque<Tomato>
    private var m = 0
    private var n = 0
    private var h = 0
    private val dz = intArrayOf(0,0,0,0,1,-1)
    private val dy = intArrayOf(0,0,1,-1,0,0)
    private val dx = intArrayOf(1,-1,0,0,0,0)

    fun solution(m: Int, n: Int, h: Int, map: Array<Array<Array<Int>>>, q: ArrayDeque<Tomato>): Int {
        this.m = m
        this.n = n
        this.h = h
        this.map = map
        this.q = q

        var result = 0

        fun bfs() {
            while (!q.isEmpty()) {
                val now = q.removeFirst()

                for (i in 0 until 6) {
                    val nextZ = now.z + dz[i]
                    val nextY = now.y + dy[i]
                    val nextX = now.x + dx[i]
                    if (isOutOfBound(nextZ, nextY, nextX) ||
                            isEmpty(nextZ, nextY, nextX) ||
                            isAlreadyVisited(nextZ, nextY, nextX)) {
                        continue
                    }
                    map[nextZ][nextY][nextX] = map[now.z][now.y][now.x] + 1
                    q.addLast(Tomato(nextZ, nextY, nextX))
                }
            }
        }

        bfs()

        for (z in 0 until h) {
            for (y in 0 until n) {
                for (x in 0 until m) {
                    if (map[z][y][x] == FRESH_TOMATO) {
                        return -1
                    }
                    result = maxOf(result, map[z][y][x])
                }
            }
        }

        return result - 1
    }

    private fun isOutOfBound(z: Int, y: Int, x: Int): Boolean {
        return z !in 0 until h || y !in 0 until n || x !in 0 until m
    }

    private fun isAlreadyVisited(z: Int, y: Int, x: Int): Boolean {
        return map[z][y][x] >= ROTTEN_TOMATO
    }

    private fun isEmpty(z: Int, y: Int, x: Int): Boolean {
        return map[z][y][x] == EMPTY
    }
}