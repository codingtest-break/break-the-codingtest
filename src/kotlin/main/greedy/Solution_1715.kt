package greedy

import java.util.PriorityQueue

fun main() {
    val n = readln().toInt()
    val list = ArrayList<Int>()
    for (i in 0 until n) {
        list.add(readln().toInt())
    }
    if (n == 1) {
        println(0)
        return
    }
    val pq = PriorityQueue<Int>()
    pq.addAll(list)
    var answer = 0
    while (!pq.isEmpty()) {
        if (pq.size > 2) {
            val out1 = pq.poll()
            val out2 = pq.poll()
            answer += out1 + out2
            pq.add(out1+out2)
        } else {
            answer += pq.poll()
        }
    }
    print(answer)
}

class Solution_1715 {
}