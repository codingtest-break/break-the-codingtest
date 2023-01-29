package greedy

import java.util.*

fun main() {
    val tokenizer = StringTokenizer(readln())
    val n = tokenizer.nextToken().toInt()
    val k = tokenizer.nextToken().toInt()
    val numArr = IntArray(n)
    repeat(n) {
        numArr[it] = readln().toInt()
    }
    numArr.reverse()
    println(Solution_11047().solution(n, k, numArr))
}

class Solution_11047 {
    fun solution(n: Int, k: Int, numArr: IntArray): Int {
        var answer = 0
        var change = k
        for (coin in numArr) {
            if (change == 0) {
                break
            }
            if (change / coin == 0) {
                continue
            } else {
                answer += (change / coin)
                change %= coin
            }
        }
        return answer
    }
}