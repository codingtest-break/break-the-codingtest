package backtracking

import java.util.StringTokenizer

const val VOWELS = "aeiou"

fun main() {
    var tokenizer = StringTokenizer(readln())
    val l = tokenizer.nextToken().toInt()
    val c = tokenizer.nextToken().toInt()
    tokenizer = StringTokenizer(readln())
    val arr = Array(c) { tokenizer.nextToken() }
    arr.sort()
    print(Solution1759().solution(l, c, arr))
}

fun isVowel(letter: String) = letter in VOWELS
fun isRightPassword(countOfVowel: Int, countOfConsonant: Int) = countOfVowel >= 1 && countOfConsonant >= 2

class Solution1759 {
    fun solution(l: Int, c: Int, arr: Array<String>): String {
        val pickedLetters = Array(l) {""}
        val sb = StringBuilder()

        fun backtrack(depth: Int, prev: Int) {
            if (depth == l) {
                var countOfVowel = 0
                var countOfConsonant = 0

                for (i in pickedLetters) {
                    if (isVowel(i)) {
                        countOfVowel++
                    } else {
                        countOfConsonant++
                    }
                }

                if (isRightPassword(countOfVowel, countOfConsonant)) {
                    for (i in pickedLetters) {
                        sb.append(i)
                    }
                    sb.append('\n')
                }
                return
            }

            for (i in prev until c) {
                pickedLetters[depth] = arr[i]
                backtrack(depth + 1, i + 1)
            }
        }

        backtrack(0, 0)
        return sb.toString()
    }
}