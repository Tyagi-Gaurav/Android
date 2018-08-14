package done

import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val R = scanner.nextInt()
    val C = scanner.nextInt()

    var arr = Array(R, { CharArray(C) })

    for (i in 0..R - 1) {
        arr[i] = scanner.next().toCharArray()
    }

    val rowFun : (Int, Int, arr: Array<CharArray>) -> Char= {x, y, arr -> arr[x][y]}
    val colFun : (Int, Int, arr: Array<CharArray>) -> Char= {x, y, arr -> arr[y][x]}
    val words1 = getWordsUsing(R, C, arr, rowFun)
    val words2 = getWordsUsing(C, R, arr, colFun)

    words1.addAll(words2)
    val findFirst = words1.stream().sorted(Comparator.naturalOrder()).findFirst()

    println (findFirst.get())
}

fun getWordsUsing(R: Int, C: Int, arr: Array<CharArray>,
                  f : (Int, Int, arr: Array<CharArray>) -> Char): MutableList<String> {
    var words = mutableListOf<String>()

    for (i in 0..R - 1) {
        val c = StringBuilder()
        for (j in 0..C - 1) {
            val o = f.invoke(i, j, arr)
            if (o != '#') {
                c.append(o)
            } else {
                if (c.length >= 2) {
                    words.add(c.toString())
                }
                c.setLength(0)
            }
        }

        if (c.length >= 2) {
            words.add(c.toString())
        }
        c.setLength(0)
    }

    return words
}



