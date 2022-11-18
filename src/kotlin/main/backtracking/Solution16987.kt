package backtracking

import java.util.StringTokenizer

data class Egg(
    var durability: Int,
    val weight: Int
) {
    fun amIBroken() = durability <= 0

    companion object {
        fun revive(oneEgg: Egg, otherEgg: Egg) {
            oneEgg.durability += otherEgg.weight
            otherEgg.durability += oneEgg.weight
        }

        fun crash(oneEgg: Egg, otherEgg: Egg) {
            oneEgg.durability -= otherEgg.weight
            otherEgg.durability -= oneEgg.weight
        }
    }
}

fun main() {
    val n = readln().toInt()
    val eggArray: Array<Egg?> = arrayOfNulls(n)
    for (i in 0 until n) {
        val tokenizer = StringTokenizer(readln())
        eggArray[i] = Egg(tokenizer.nextToken().toInt(), tokenizer.nextToken().toInt())
    }
    print(Solution16987().solution(n, eggArray))
}

class Solution16987 {
    fun solution(n: Int, eggArray: Array<Egg?>): Int {
        var result = Int.MIN_VALUE

        if (n == 1) {
            return 0
        }

        fun backTrack(depth: Int, totalNumOfBroken: Int) {
            if (depth == n) {
                result = maxOf(result, totalNumOfBroken)
                return
            }

            if (eggArray[depth]!!.amIBroken() || totalNumOfBroken == n - 1) {
                backTrack(depth + 1, totalNumOfBroken)
                return
            }

            for (i in 0 until n) {
                if (depth == i || eggArray[i]!!.amIBroken()) {
                    continue
                }

                var numOfBroken = totalNumOfBroken

                Egg.crash(eggArray[depth]!!, eggArray[i]!!)
                if (eggArray[depth]!!.amIBroken()) {
                    numOfBroken++
                }
                if (eggArray[i]!!.amIBroken()) {
                    numOfBroken++
                }
                backTrack(depth + 1, numOfBroken)
                Egg.revive(eggArray[depth]!!, eggArray[i]!!)
            }
        }

        backTrack(0, 0)

        return result
    }
}