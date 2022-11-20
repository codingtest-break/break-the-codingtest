package backtracking

import java.util.StringTokenizer

const val TEACHER = "T"
const val OBSTACLE = "O"
const val STUDENT = "S"
const val NOTHING = "X"
const val MAX_OBSTACLE_COUNT = 3

data class Point(val y: Int, val x: Int)

class Validator(
    private val n: Int,
    private val map: Array<Array<String>>,
) {
    fun isOutside(y: Int, x: Int): Boolean {
        return y >= n || y < 0 || x >= n || x < 0
    }

    fun isHereSomething(y: Int, x: Int): Boolean {
        return map[y][x] != NOTHING
    }

    fun isAnyoneDetected(teacherPosition: Point): Boolean {
        return checkNorth(teacherPosition) ||
                checkEast(teacherPosition) ||
                checkSouth(teacherPosition) ||
                checkWest(teacherPosition)
    }

    private fun checkWest(teacherPosition: Point): Boolean {
        var isMeet = false
        var isObstacle = false

        for (i in teacherPosition.x - 1 downTo 0) {
            if (map[teacherPosition.y][i] == OBSTACLE) {
                isObstacle = true
            }
            if (map[teacherPosition.y][i] == STUDENT) {
                isMeet = true
            }

            if (isObstacle) {
                return false
            }
            if (isMeet) {
                return true
            }
        }
        return false
    }

    private fun checkSouth(teacherPosition: Point): Boolean {
        var isMeet = false
        var isObstacle = false
        for (i in teacherPosition.y + 1 until n) {
            if (map[i][teacherPosition.x] == STUDENT) {
                isMeet = true
            }
            if (map[i][teacherPosition.x] == OBSTACLE) {
                isObstacle = true
            }
            if (isObstacle) {
                return false
            }
            if (isMeet) {
                return true
            }
        }
        return false
    }

    private fun checkEast(teacherPosition: Point): Boolean {
        var isMeet = false
        var isObstacle = false
        for (i in teacherPosition.x + 1 until n) {
            if (map[teacherPosition.y][i] == STUDENT) {
                isMeet = true
            }
            if (map[teacherPosition.y][i] == OBSTACLE) {
                isObstacle = true
            }
            if (isObstacle) {
                return false
            }
            if (isMeet) {
                return true
            }
        }
        return false
    }

    private fun checkNorth(teacherPosition: Point): Boolean {
        var isMeet = false
        var isObstacle = false
        for (i in teacherPosition.y - 1 downTo 0) {
            if (map[i][teacherPosition.x] == STUDENT) {
                isMeet = true
            }
            if (map[i][teacherPosition.x] == OBSTACLE) {
                isObstacle = true
            }
            if (isObstacle) {
                return false
            }
            if (isMeet) {
                return true
            }
        }
        return false
    }
}

fun main() {
    val n = readln().toInt()
    val map = Array(n) { Array(n) { "" } }
    val teachers = mutableListOf<Point>()
    repeat(n) { y ->
        val tokenizer = StringTokenizer(readln())
        repeat(n) { x ->
            map[y][x] = tokenizer.nextToken()
            if (map[y][x] == TEACHER) {
                teachers.add(Point(y, x))
            }
        }
    }
    print(Solution18428().solution(n, map, teachers))
}

class Solution18428 {
    fun solution(
        n: Int,
        map: Array<Array<String>>,
        teachers: MutableList<Point>,
    ): String {
        val validator = Validator(n, map)
        var canPerfectlyHide = false

        fun backTrack(depth: Int) {
            if (canPerfectlyHide) {
                return
            }
            if (depth == MAX_OBSTACLE_COUNT) {
                for (i in 0 until teachers.size) {
                    if (validator.isAnyoneDetected(teachers[i])) {
                        return
                    }
                }
                canPerfectlyHide = true
                return
            }

            for (y in 0 until n) {
                for (x in 0 until n) {
                    if (validator.isOutside(y, x) ||
                        validator.isHereSomething(y, x)
                    ) {
                        continue
                    }
                    map[y][x] = OBSTACLE
                    backTrack(depth + 1)
                    map[y][x] = NOTHING
                }
            }
        }
        backTrack(0)
        return if (canPerfectlyHide) "YES" else "NO"
    }
}