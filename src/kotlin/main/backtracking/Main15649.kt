package backtracking

fun main() {
    val nAndM = readln().split(" ").map { it.toInt() }

    print(Main15649(nAndM[0], nAndM[1]).solution())
}

class Main15649(private val n: Int, private val m: Int) {
    fun solution(): String {
        val visited = BooleanArray(n)
        val nums = IntArray(m)
        val stringBuilder = StringBuilder()

         fun backtrack(depth: Int) {
            if (depth == m) {
                stringBuilder.append(nums.joinToString(separator = " ", postfix = "\n"))
                return
            }

            for (i in 0 until n) {
                if (visited[i]) {
                    continue
                }
                visited[i] = true
                nums[depth] = i + 1
                backtrack(depth + 1)
                visited[i] = false
            }
        }
        backtrack(0)
        return stringBuilder.toString()
    }
}