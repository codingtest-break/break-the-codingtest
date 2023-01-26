package DP

fun main() {
    val n = readln().toInt()
    val arr = LongArray(101)
    arr[1] = 1
    for (i in 2..n) {
        arr[i] = arr[i-1] + arr[i-2]
    }

    println(arr[n])
}

class Solution_2748 {
}