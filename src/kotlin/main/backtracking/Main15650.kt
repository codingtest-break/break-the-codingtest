package backtracking

import java.util.StringTokenizer

fun main() {
    val stringTokenizer = StringTokenizer(readln())

    print(Main15650().solution(stringTokenizer.nextToken().toInt(), stringTokenizer.nextToken().toInt()))
}

class Main15650 {
    fun solution(n: Int, m: Int): String {
        val nums = IntArray(m)
        val stringBuilder = StringBuilder()

        fun backTrack(depth: Int, index: Int) {
            if(depth == m) {
                stringBuilder.append(nums.joinToString(separator = " ", postfix = "\n"))
                return
            }

            for (i in index ..n) {
                nums[depth] = i
                backTrack(depth + 1, i + 1)
            }
        }

        backTrack(0, 1)
        return stringBuilder.toString()
    }
}