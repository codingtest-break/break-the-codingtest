package backtracking

//import java.util.*

fun main() {
    val nAndM = readln().split(" ").map { it.toInt() }

    val n = nAndM[0]
    val m = nAndM[1]

    print(Main15649(n, m).solution())
//    test().testing()
}

class Main15649(private val n: Int, private val m: Int) {
    fun solution(): String {
        val visited: MutableList<Boolean> = MutableList(n) { false }
        val nums: MutableList<Int> = MutableList(m) { 0 }
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
//
//class test() {
//    fun ArrayList<Int>.last1() : Unit {
//        print(this.last())
//    }
//    fun testing() {
//       val list =  arrayListOf<Int>(1, 2, 3)
//        list.last1()
//    }
//
//
//}
