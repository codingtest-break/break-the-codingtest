package greedy

fun main() {
    val str = readln().split("")
    val charMap = str.filter { it != "" }.sorted().groupingBy { it }.eachCount().toMutableMap()
    val charCountArray = charMap.values.filter { it % 2 == 1 }
    if (charCountArray.size >= 2) {
        println("I'm Sorry Hansoo")
        return
    }
    var middleStr = ""
    for (char in charMap.keys) {
        if ((charMap[char]?.rem(2) ?: 0) == 1) {
            middleStr = char
            break
        }
    }
    var pallindrom = ""
    for (char in charMap.keys) {
        while (charMap[char]!! > 0) {
            if (charMap[char]!! == 1) {
                break
            }
            pallindrom += char
            charMap[char] = charMap[char]!! - 2
        }
    }
    print(pallindrom + middleStr + pallindrom.reversed())
}

class Solution_1213 {
}