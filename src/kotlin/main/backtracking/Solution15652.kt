package backtracking

import java.util.StringTokenizer

fun main() {
    val st = StringTokenizer(readln())

    print(Solution15652().solution(st.nextToken().toInt(), st.nextToken().toInt()))
}

class Solution15652 {
    fun solution(n: Int, m: Int): String {
        val sb = StringBuilder()
        val nums = IntArray(m)

        fun backTrack(depth : Int, prev : Int) {
            if (depth == m) {
                nums.forEach { sb.append(it).append(' ') }
                sb.append('\n')
                return
            }

            for (i in prev.. n) {
                nums[depth] = i
                backTrack(depth + 1, i)
            }
        }

        backTrack(0, 1)
        return sb.toString()
    }
}