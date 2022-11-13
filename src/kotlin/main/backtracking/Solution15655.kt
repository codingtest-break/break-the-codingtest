package backtracking

import java.util.StringTokenizer

fun main() {
    var tokenizer = StringTokenizer(readln(), " ")
    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()
    tokenizer = StringTokenizer(readln(), " ")
    val nums = Array(n) {tokenizer.nextToken()}
    nums.sortBy { it.toInt() }
    print(Solution15655().solution(n, m, nums))
}

class Solution15655 {
    fun solution(n: Int, m: Int, nums: Array<String>): String {
        val sb = StringBuilder()
        val orders = IntArray(m)

        fun backTrack(depth : Int, prev: Int) {
            if (depth == m) {
                orders.forEach { sb.append(nums[it]).append(' ') }
                sb.append('\n')
                return
            }

            for (i in prev until n) {
                orders[depth] = i
                backTrack(depth + 1, i + 1)
            }
        }
        backTrack(0, 0)
        return sb.toString()
    }
}