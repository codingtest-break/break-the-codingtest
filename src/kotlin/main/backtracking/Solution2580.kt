package backtracking

import java.util.StringTokenizer

private val CHECK_BIT_ARR =
    intArrayOf(1, 1 shl 1, 1 shl 2, 1 shl 3, 1 shl 4, 1 shl 5, 1 shl 6, 1 shl 7, 1 shl 8)
private const val UPPER_SIDE_FLAG = 0
private const val MIDDLE_SIDE_FLAG = 1
private const val LOWER_SIDE_FLAG = 2
private const val MAX_BOARD_SIZE = 9
private const val EMPTY_POINT = 0

data class Point(val y: Int, val x: Int) {
    val sector: Int

    init {
        sector = findSector(y, x)
    }

    /*
        0 1 2
        3 4 5
        6 7 8
     */
    private fun findSector(y: Int, x: Int): Int {
        when {
            findSide(y) == UPPER_SIDE_FLAG && findSide(x) == UPPER_SIDE_FLAG -> return 0
            findSide(y) == UPPER_SIDE_FLAG && findSide(x) == MIDDLE_SIDE_FLAG -> return 1
            findSide(y) == UPPER_SIDE_FLAG && findSide(x) == LOWER_SIDE_FLAG -> return 2
            findSide(y) == MIDDLE_SIDE_FLAG && findSide(x) == UPPER_SIDE_FLAG -> return 3
            findSide(y) == MIDDLE_SIDE_FLAG && findSide(x) == MIDDLE_SIDE_FLAG -> return 4
            findSide(y) == MIDDLE_SIDE_FLAG && findSide(x) == LOWER_SIDE_FLAG -> return 5
            findSide(y) == LOWER_SIDE_FLAG && findSide(x) == UPPER_SIDE_FLAG -> return 6
            findSide(y) == LOWER_SIDE_FLAG && findSide(x) == MIDDLE_SIDE_FLAG -> return 7
            findSide(y) == LOWER_SIDE_FLAG && findSide(x) == LOWER_SIDE_FLAG -> return 8
        }
        return -1
    }

    private fun findSide(num: Int): Int {
        return when (num) {
            0, 1, 2 -> UPPER_SIDE_FLAG
            3, 4, 5 -> MIDDLE_SIDE_FLAG
            6, 7, 8 -> LOWER_SIDE_FLAG
            else -> return -1
        }
    }
}

private fun writeBoard(board: Array<IntArray>, sb: StringBuilder) {
    for (i in 0 until MAX_BOARD_SIZE) {
        for (j in 0 until MAX_BOARD_SIZE) {
            sb.append(board[i][j]).append(' ')
        }
        sb.append('\n')
    }
}

fun main() {
    val board = Array(MAX_BOARD_SIZE) { IntArray(MAX_BOARD_SIZE) }
    val modifyPointList = mutableListOf<Point>()
    val horizontalLineBitArray = IntArray(9)
    val verticalLineBitArray = IntArray(9)
    val sectorBitArray = IntArray(9)

    for (i in 0 until MAX_BOARD_SIZE) {
        val tokenizer = StringTokenizer(readln())
        for (j in 0 until MAX_BOARD_SIZE) {
            val num = tokenizer.nextToken().toInt()
            board[i][j] = num
            if (num == EMPTY_POINT) {
                modifyPointList.add(Point(i, j))
            } else {
                horizontalLineBitArray[i] = horizontalLineBitArray[i] or CHECK_BIT_ARR[num - 1]
                verticalLineBitArray[j] = verticalLineBitArray[j] or CHECK_BIT_ARR[num - 1]
                val sectorIdx = Point(i, j).sector
                sectorBitArray[sectorIdx] = sectorBitArray[sectorIdx] or CHECK_BIT_ARR[num - 1]
            }
        }
    }
    print(
        Solution2580().solution(
            board,
            modifyPointList,
            horizontalLineBitArray,
            verticalLineBitArray,
            sectorBitArray
        )
    )
}

class Solution2580 {
    fun solution(
        board: Array<IntArray>,
        modifyPointList: MutableList<Point>,
        horizontalLineBitArray: IntArray,
        verticalLineBitArray: IntArray,
        sectorBitArray: IntArray
    ): String {
        var isRight = false
        val sb = StringBuilder()
        val maxDepth = modifyPointList.size

        fun writeBit(y: Int, x: Int, sector: Int, bitNum: Int) {
            horizontalLineBitArray[y] = horizontalLineBitArray[y] or bitNum
            verticalLineBitArray[x] = verticalLineBitArray[x] or bitNum
            sectorBitArray[sector] = sectorBitArray[sector] or bitNum
        }

        fun removeBit(y: Int, x: Int, sector: Int, bitNum: Int) {
            horizontalLineBitArray[y] = horizontalLineBitArray[y] xor bitNum
            verticalLineBitArray[x] = verticalLineBitArray[x] xor bitNum
            sectorBitArray[sector] = sectorBitArray[sector] xor bitNum
        }

        fun backtrack(depth: Int) {
            if (isRight) {
                return
            }

            if (depth == maxDepth) {
                isRight = true
                writeBoard(board, sb)
                return
            }

                val currentEmptyPoint = modifyPointList[depth]

                val notAvailableBit = horizontalLineBitArray[currentEmptyPoint.y] or
                        verticalLineBitArray[currentEmptyPoint.x] or
                        sectorBitArray[currentEmptyPoint.sector]
                var checkBit = 1
                for (inputNum in 1..9) {
                    if (notAvailableBit and checkBit != 0) {
                        checkBit = checkBit shl 1
                        continue
                    }

                    writeBit(currentEmptyPoint.y, currentEmptyPoint.x, currentEmptyPoint.sector, checkBit)
                    board[currentEmptyPoint.y][currentEmptyPoint.x] = inputNum

                    backtrack(depth + 1)

                    removeBit(currentEmptyPoint.y, currentEmptyPoint.x, currentEmptyPoint.sector, checkBit)
                    checkBit = checkBit shl 1
                }
        }
        backtrack(0)
        return sb.toString()
    }
}