package backtracking

import java.util.StringTokenizer

fun main() {
    var tokenizer = StringTokenizer(readln())
    val n = tokenizer.nextToken().toInt()
    val s = tokenizer.nextToken().toInt()
    tokenizer = StringTokenizer(readln())
    val arr = IntArray(n) {tokenizer.nextToken().toInt()}
    print(Solution1182().solution(n, s, arr))
}

class Solution1182 {
    fun solution(n: Int, s: Int, arr: IntArray) : Int {
        var result = 0
        fun backTrack(depth : Int, prev: Int, sum: Int) {
            if (depth == n) {
                if (sum == s) {
                    result++
                }
                return
            }
            for (i in prev until n) {
                backTrack(depth + 1, i + 1, sum + arr[i])
            }
        }
        backTrack(0,  0, 0)
        if(s == 0) {
            result--
        }
        return result
    }
}