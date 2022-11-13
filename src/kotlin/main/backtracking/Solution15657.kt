package backtracking

import java.util.StringTokenizer

fun main() {
    var tokenizer = StringTokenizer(readln(), " ")
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()
    tokenizer = StringTokenizer(readln(), " ")
    val nums = IntArray(n) {tokenizer.nextToken().toInt()}
    nums.sort()

    print(Solution15657().solution(n, m, nums))
}

class Solution15657 {
    fun solution(n: Int, m: Int, nums: IntArray): String {
        val sb = StringBuilder()
        val selectedNums = IntArray(m)

        fun backTrack(depth : Int, prev: Int) {
            if (depth == m) {
                selectedNums.forEach { sb.append(it).append(' ') }
                sb.append('\n')
                return
            }

            for (i in prev until n) {
                selectedNums[depth] = nums[i]
                backTrack(depth + 1, i)
            }
        }
        backTrack(0, 0)
        return sb.toString()
    }
}