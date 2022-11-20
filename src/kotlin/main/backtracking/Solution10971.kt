import java.util.StringTokenizer

const val NO_WAY = 0

fun main() {
    var tokenizer = StringTokenizer(readln())
    val n = tokenizer.nextToken().toInt()
    val graph: Array<IntArray?> = arrayOfNulls(n)
    for (i in 0 until n) {
        tokenizer = StringTokenizer(readln())
        graph[i] = IntArray(n) { tokenizer.nextToken().toInt() }
    }
    print(Solution10971().solution(n, graph))
}

class Solution10971 {
    fun solution(n: Int, graph: Array<IntArray?>): Int {
        var result = Int.MAX_VALUE
        val isVisited = BooleanArray(n)

        fun dfs(depth: Int, present: Int, start: Int, cost: Int) {
            if (depth == n) {
                if (present == start) {
                    result = minOf(result, cost)
                }
                return
            }

            for (i in 0 until n) {
                if (isVisited[i] ||
                    graph[present]!![i] == NO_WAY ||
                    cost + graph[present]!![i] > result) {
                    continue
                }

                isVisited[i] = true
                dfs(depth + 1, i, start, cost + graph[present]!![i])
                isVisited[i] = false
            }
        }

        for (i in 0 until n) {
            dfs(0, i, i, 0)
        }
        return result
    }
}