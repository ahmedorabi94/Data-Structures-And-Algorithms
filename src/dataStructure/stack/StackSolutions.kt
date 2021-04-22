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


fun isBalanced(s: String): String {

    if (s.isEmpty()) {
        return "NO"
    }

    var result = "NO"
    val stack = Stack<Char>()

    for (c in s.toCharArray()) {

        if (c == '(' || c == '[' || c == '{') {
            stack.push(c)
            // Check stack for corresponding closing parentheses, false if not valid
        } else if (c == ')' && !stack.empty() && stack.peek() == '(') {
            stack.pop()
        } else if (c == ']' && !stack.empty() && stack.peek() == '[') {
            stack.pop()
        } else if (c == '}' && !stack.empty() && stack.peek() == '{') {
            stack.pop()
        } else {
            return "NO"
        }

    }
    if (stack.isEmpty()) {
        result = "YES"
    }

    return result

}


fun equalStacks(h1: Array<Int>, h2: Array<Int>, h3: Array<Int>): Int {

    val s1 = Stack<Int>()
    val s2 = Stack<Int>()
    val s3 = Stack<Int>()

    var ha1 = 0
    var ha2 = 0
    var ha3 = 0
    var minHeight = 0

    for (x in h1.size - 1 downTo 0) {
        s1.push(h1[x])
        ha1 += h1[x]
    }
    for (x in h2.size - 1 downTo 0) {
        s2.push(h2[x])
        ha2 += h2[x]
    }
    for (x in h3.size - 1 downTo 0) {
        s3.push(h3[x])
        ha3 += h3[x]
    }

    minHeight = Math.min(ha1, Math.min(ha2, ha3))

    while (ha1 != ha2 || ha1 != ha3) {

        while (ha1 > minHeight) {
            ha1 -= s1.pop()
        }
        minHeight = Math.min(ha1, Math.min(ha2, ha3))


        while (ha2 > minHeight) {
            ha2 -= s2.pop()
        }
        minHeight = Math.min(ha1, Math.min(ha2, ha3))


        while (ha3 > minHeight) {
            ha3 -= s3.pop()
        }
        minHeight = Math.min(ha1, Math.min(ha2, ha3))


    }



    return minHeight

}