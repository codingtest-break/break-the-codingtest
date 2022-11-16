package backtracking

import kotlin.math.abs
import kotlin.math.min

fun main() {
    val n = readln().toInt()
    val ability = Array (n) {
        Array(n) { 0 }
    }
    for (i in 0 until n) {
        ability[i] = readln().split(" ").map { it.toInt() }.toTypedArray()
    }
    print(Solution14889().solution(n, ability))
}

class Solution14889 {
    fun solution(n: Int, ability: Array<Array<Int>>) : Int {
        var result = Int.MAX_VALUE
        val maxDepth = n / 2
        val startNums = IntArray(maxDepth)
        val linkNums = IntArray(maxDepth)
        val isVisited = BooleanArray(n)

        fun backTrack(depth : Int, prev : Int) {
            if (depth == maxDepth) {
                var startTeamAbility = 0
                var linkTeamAbility = 0

                var linkIdx = 0
                for (i in 0 until n) {
                    if (!isVisited[i]) {
                        linkNums[linkIdx++] = i
                    }
                }

                for (i in 0 until maxDepth) {
                    for (j in i + 1 until maxDepth) {
                        startTeamAbility += ability[startNums[i]][startNums[j]] + ability[startNums[j]][startNums[i]]
                        linkTeamAbility += ability[linkNums[i]][linkNums[j]] + ability[linkNums[j]][linkNums[i]]
                    }
                }
                result = min(result, abs(startTeamAbility - linkTeamAbility))
                return
            }

            for (i in prev until n) {
                if (isVisited[i]) {
                    continue
                }

                isVisited[i] = true
                startNums[depth] = i
                backTrack(depth + 1, i + 1)
                isVisited[i] = false
            }
        }
        backTrack(0, 0)
        return result
    }
}