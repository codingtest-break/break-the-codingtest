package backtracking

fun main() {
    val n = readln().toInt()
    print(Solution10974().solution(n))
}

class Solution10974 {
    fun solution(n: Int): String {
        val sb = StringBuilder()
        val isVisited = BooleanArray(n + 1)
        val nums = IntArray(n)

        fun backTrack(depth : Int) {
            if (depth == n) {
                nums.forEach { sb.append(it).append(" ") }
                sb.append('\n')
                return
            }

            for (i in 1.. n) {
                if (isVisited[i]) {
                    continue
                }

                isVisited[i] = true
                nums[depth] = i
                backTrack(depth + 1)
                isVisited[i] = false
            }
        }
        backTrack(0)
        return sb.toString()
    }
}