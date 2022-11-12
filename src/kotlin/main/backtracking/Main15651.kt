package backtracking

fun main() {
    val nAndM = readln().split(" ").map { it.toInt() }

    print(Main15651().solution(nAndM[0], nAndM[1]))
}
class Main15651 {
    fun solution(n: Int, m: Int): String {
        val stringBuilder = StringBuilder()
        val nums = IntArray(m)

        fun backTrack(depth : Int) {
            if (depth == m) {
                nums.forEach { stringBuilder.append(it).append(" ") }
                stringBuilder.append("\n")
                return
            }

            for (i in 1..n) {
                nums[depth] = i
                backTrack(depth + 1)
            }
        }
        backTrack(0)
        return stringBuilder.toString()
    }
}