package backtracking

import java.util.StringTokenizer

fun main() {
    val tokenizer = StringTokenizer(readln())
    val r = tokenizer.nextToken().toInt()
    val c = tokenizer.nextToken().toInt()
    val map = Array(r) {CharArray(c)}
    for (i in 0 until r) {
        map[i] = readln().toCharArray()
    }
    print(Solution1987().solution(r, c, map))
}

class Solution1987 {
    fun solution(r: Int, c: Int, map: Array<CharArray>): Int {
        val dy = intArrayOf(0, 1, 0, -1)
        val dx = intArrayOf(1, 0, -1, 0)
        val alp = Array(r) {IntArray(c)}
        var result = 0

        fun isOutOfBound(y: Int, x: Int) : Boolean {
            return y < 0 || y >= r || x < 0 || x >= c
        }

        fun backtrack(depth: Int, nowY: Int, nowX: Int, bit: Int) {
            if(alp[nowY][nowX] == bit) return;
            alp[nowY][nowX] = bit;

            result = maxOf(result, depth)

            for (direction in 0 until 4) {
                val nextY = nowY + dy[direction]
                val nextX = nowX + dx[direction]

                if (isOutOfBound(nextY, nextX)) {
                    continue
                }

                val maskingBits = 1 shl map[nextY][nextX] - 'A'

                if (bit and maskingBits != 0) {
                    continue
                }

                backtrack(depth + 1, nextY, nextX, bit or (maskingBits))

            }
        }
        backtrack(1, 0, 0, 1 shl map[0][0] - 'A')
        return result

    }
}