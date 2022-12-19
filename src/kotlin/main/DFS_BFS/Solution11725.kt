package DFS_BFS

import java.util.StringTokenizer

fun main() {
    val n = readln().toInt()
    val map = Array<MutableList<Int>>(n+1){ mutableListOf()}
    repeat(n-1) {
        val tokenizer = StringTokenizer(readln())
        val parent = tokenizer.nextToken().toInt()
        val children = tokenizer.nextToken().toInt()
        map[parent].add(children)
        map[children].add(parent)
    }
    print(Solution11725().solution(n, map))
}

class Solution11725 {
    private lateinit var map: Array<MutableList<Int>>
    private lateinit var isVisited: BooleanArray
    private lateinit var resultArr: IntArray
    fun solution(n: Int, map: Array<MutableList<Int>>): String {
        this.map = map
        isVisited = BooleanArray(n+1)
        resultArr = IntArray(n+1)
        val sb = StringBuilder()

        dfs(1)

        for (i in 2..n) {
            sb.append(resultArr[i]).append('\n')
        }
        return sb.toString()
    }

    private fun dfs(present: Int) {
        if (isVisited[present]) {
            return
        }
        isVisited[present] = true
        for (i in map[present]) {
            if (isVisited[i]) {
                continue
            }
            resultArr[i] = present
            dfs(i)
        }
    }
}