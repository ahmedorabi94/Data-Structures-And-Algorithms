package dataStructure.stack

import java.util.*
import kotlin.collections.ArrayList

fun getMax(operations: Array<String>): Array<Int> {

    val result = ArrayList<Int>()
    val stack = Stack<Int>()
    val maxStack = Stack<Int>()
    maxStack.push(Integer.MIN_VALUE)

    for (o in operations) {

        val n = o.substring(0, 1)
        var number = ""

        number = if (o.length == 1) {
            ""
        } else {
            o.substring(2, o.length)
        }

        when (n) {
            "1" -> {
                stack.push(number.toInt())

                val maxSoFar = maxStack.peek()

                if (number.toInt() > maxSoFar) {
                    maxStack.push(number.toInt())
                } else {
                    maxStack.push(maxSoFar)
                }
            }
            "2" -> {
                if (!stack.isEmpty()) {
                    stack.pop()
                    maxStack.pop()
                }

            }
            "3" -> {
                if (!stack.isEmpty()) {
                    result.add(maxStack.peek())
                }
            }
        }

    }
    return result.toTypedArray()

}