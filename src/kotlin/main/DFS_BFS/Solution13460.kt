package DFS_BFS

import java.util.StringTokenizer

private const val RED = 'R'
private const val BLUE = 'B'
private const val WALL = '#'
private const val HOLE = '0'

data class Point(val y: Int, val x: Int)

fun main() {
    val tokenizer = StringTokenizer(readln())
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()
    val map = Array(n){CharArray(m)}

    repeat(n) {
        map[it] = readln().toCharArray()
    }

    var rPoint = Point(-1, -1)
    var bPoint = Point(-1, -1)
    repeat(n) {y ->
        repeat(m) {x ->
            if (map[y][x] == 'R') {
                rPoint = Point(y, x)
            } else if (map[y][x] == 'B') {
                bPoint = Point(y,x)
            }
        }
    }
    print(Solution13460().solution(n,m,map, rPoint, bPoint))
}

class Solution13460 {
    private lateinit var directionMaps: Array<Array<CharArray>>
    private lateinit var resultArr: IntArray
    private val dy = intArrayOf(0, 1, 0, -1)
    private val dx = intArrayOf(1, 0, -1, 0)
    private var n = 0
    private var m = 0
    private lateinit var map: Array<CharArray>
    fun solution(n: Int, m: Int, map: Array<CharArray>, rPoint: Point, bPoint: Point): Int {
        this.n = n
        this.m = m
        this.map = map
        directionMaps = Array(4){Array(n){ CharArray(m) }}
        resultArr = IntArray(4){Int.MAX_VALUE}
        repeat(4) {d ->
            repeat(n) {
                directionMaps[d][it] = map[it].copyOf()
            }
        }



        return if (resultArr.min() > 10) -1 else resultArr.min()
    }

    private fun isOutOfBound(y: Int, x: Int): Boolean {
        return y < 0 || y >= n || x < 0 || x >= m
    }

    private fun isWall(y: Int, x: Int): Boolean {
        return map[y][x] == WALL
    }

    private fun isHole(y: Int, x: Int): Boolean {
        return map[y][x] == HOLE
    }

    private fun isOtherMarble(y: Int, x: Int, marbleChar: Char, map: Array<CharArray>): Boolean {
        return when(marbleChar) {
            RED -> map[y][x] == BLUE
            else -> map[y][x] == RED
        }
    }
}