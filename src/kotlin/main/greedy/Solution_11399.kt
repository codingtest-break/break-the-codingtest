package greedy

fun main() {
    var answer = 0
    readln().toInt()
    readln().split(" ").map { it.toInt() }.sorted().fold(0) { sum, next ->
        answer += sum + next
        sum + next
    }
    println(answer)
}

class Solution_11399 {

}