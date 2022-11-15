package backtracking

private const val MAX_SELECT_NUMBER = 6

fun main() = with(System.`in`.bufferedReader()) {
     this.lineSequence().forEach { line ->
         val inputs = line.split(" ").map { it.toInt() }
         if (inputs.size > 1) {
             val splitLists =
                 inputs.withIndex().partition { it.index == 0 }.toList().map { list -> list.map { it.value } }
             println(Solution6603().solution(splitLists[0][0], splitLists[1].toIntArray()))
         }
    }
}


class Solution6603 {
    fun solution(k: Int, numberArr: IntArray): String {
        val selectedNums = IntArray(MAX_SELECT_NUMBER)
        val sb = StringBuilder()

        fun backTrack(depth : Int, prev: Int) {
            if (depth == MAX_SELECT_NUMBER) {
                selectedNums.forEach { sb.append(it).append(' ') }
                sb.append('\n')
                return
            }

            for (i in prev until k) {
                selectedNums[depth] = numberArr[i]
                backTrack(depth + 1, i + 1)
            }
        }
        backTrack(0, 0)
        return sb.toString()
    }
}