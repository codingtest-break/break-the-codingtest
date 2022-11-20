package backtracking

import java.util.StringTokenizer

fun main() {
    val n = readln().toInt()
    val tokenizer = StringTokenizer(readln())
    val operators = Array(n) {tokenizer.nextToken()}
    print(Solution2529().solution(n, operators))

}

private fun calculate(operator: String, num1: Long, num2: Long): Boolean {
    return when (operator) {
        ">" -> num1 > num2
        else -> num1 < num2
    }
}

class Solution2529 {
    fun solution(n: Int, operators: Array<String>): String {
        val sb = StringBuilder()
        var max = Long.MIN_VALUE
        var min = Long.MAX_VALUE
        val isVisited = BooleanArray(10)
        val nums = IntArray(n + 1)

        fun backTrack(depth : Int, num: Long) {
            if (depth == n) {
                max = maxOf(max, num)
                min = minOf(min, num)
                return
            }

            for (i in 0 until 10) {
                if (isVisited[i]) {
                    continue
                }

                val digit = num % 10
                if (calculate(operators[depth], digit, i.toLong())) {
                    isVisited[i] = true
                    nums[depth + 1] = i
                    backTrack(depth + 1, num*10 + i)
                    isVisited[i] = false
                }
            }
        }
        for (i in 0 until 10) {
            nums[0] = i
            isVisited[i] = true
            backTrack(0, i.toLong())
            isVisited[i] = false
        }
        return "${String.format("%0${n+1}d", max)}\n${String.format("%0${n+1}d", min)}"
    }
}