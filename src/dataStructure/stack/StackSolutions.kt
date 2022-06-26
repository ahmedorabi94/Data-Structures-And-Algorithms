package dataStructure.stack

import java.util.*


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

fun isValid(s: String): Boolean {
    val map = HashMap<Char, Char>()
    map['('] = ')'
    map['['] = ']'
    map['{'] = '}'
    val stack = Stack<Char>()
    for (element in s) {
        val curr = element
        if (map.keys.contains(curr)) {
            stack.push(curr)
        } else if (map.values.contains(curr)) {
            if (!stack.empty() && map[stack.peek()] == curr) {
                stack.pop()
            } else {
                return false
            }
        }
    }
    return stack.empty()
}

//Input: nums = [23,2,4,6,7], k = 6
//Output: true
//Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.

fun checkSubarraySum(nums: IntArray?, k: Int): Boolean {
    if (nums == null || nums.size <= 1) {
        return false
    }
    val map = HashMap<Int, Int>()
    map.put(0, -1)

    var preSum = 0

    for (i in nums.indices) {
        preSum += nums[i]
        val mod = if (k == 0) preSum else preSum % k
        // 8
        //1
        //8
        if (map.containsKey(mod) && i - map[mod]!! > 1) {
            return true
        }
        if (!map.containsKey(mod)) {
            map.put(mod, i)
        }

    }

    return false
}

fun checkSubarraySumTwo(nums: IntArray?, k: Int): Boolean {
    if (nums == null || nums.size <= 1) {
        return false
    }
    var sum = 0

    for (i in nums.indices) {
        sum += nums[i]

        for (j in 1 until nums.size) {
            if (sum == k) {
                println("Sum is " + sum)
                println("Found number i $i , j ${j - 1}")
                return true
            }

            if (sum > k) {
                sum = 0
                break
            }

            sum += nums[j]
        }

    }

    return false
}

//Input: s = "cbaebabacd", p = "abc"
//Output: [0,6]
fun findAnagrams(s: String?, p: String): List<Int?>? {
    val rst = ArrayList<Int>()
    if (s == null || s.isEmpty() || s.length < p.length) {
        return rst
    }
    val map_p = IntArray(26)
    for (element in p) {
        //  map_p[element - 'a']++
        map_p[element - 'a'] = map_p[element - 'a'] + 1
    }
//    map_p.forEach {
//        println(it)
//    }
//    println('c' - 'a')
//    println(map_p[0])
//    println(map_p[1])
    // println(map_p[2])

    for (i in 0 until s.length - p.length) {
        val map_s = IntArray(26)
        for (j in p.indices) {
            map_s[s[i + j] - 'a']++
        }
        map_s.forEach {
            println(it)
        }
        if (isMatch(map_p, map_s)) {
            rst.add(i)
        }
    }

    return rst
}

fun isMatch(arr1: IntArray, arr2: IntArray): Boolean {
    for (i in arr1.indices) {
        if (arr1[i] != arr2[i]) {
            return false
        }
    }
    return true
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


fun findX(p: String) {
    val map_p = IntArray(26)
    for (element in p) {
        //  map_p[element - 'a']++
        map_p[element - 'a'] = map_p[element - 'a'] + 1
    }
    val list = ArrayList<Int>()
    map_p.forEach {
        print("${it} ")
        if (it != 0) {
            list.add(it)
        }
    }
    val ref = list[0]
    var result = true
    for (i in 1 until list.size) {
        if (ref != list[i]) {
            result = false
            break
        }
    }

    println(result)
}

// "the sky is blue"
//151. Reverse Words in a String
fun reverseWords(s: String): String {
    val result = StringBuilder()

    val x = s.trim().replace("\\s+".toRegex(), " ")
    val list = x.split(" ")
    for (i in list.size - 1 downTo 0) {
        result.append(list[i] + " ")
    }

    return result.toString().trim()
}

fun reverseOnlyLetters(S: String): String? {
    val sb = StringBuilder(S)
    var i = 0
    var j = S.length - 1
    while (i < j) {
        if (!Character.isLetter(sb[i])) {
            ++i
        } else if (!Character.isLetter(sb[j])) {
            --j
        } else {
            sb.setCharAt(i, S[j])
            sb.setCharAt(j--, S[i++])
        }
    }
    return sb.toString()
}

fun removeDuplicateLetters(s: String): String {
    val sb = StringBuilder()
    val set = HashSet<Char>()

    for (element in s) {
//        if (set.add(element)){
//            sb.append(element)
//        }
        set.add(element)
    }
    set.forEach {
        sb.append(it)
    }

    return sb.toString()
}


//Input: nums = [4,7,9,10], k = 1
//Output: 5
//Explanation: The first missing number is 5.
fun missingElement(nums: IntArray, k: Int): Int {
    val n = nums.size
    val leftMost = nums[0]
    var si = 0
    var ei = n - 1

    while (si <= ei) {
        val mid = si + (ei - si) / 2
        // missing element in b/w leftmost and nums[mid]
        val missingCount = nums[mid] - (leftMost + mid)   /// 7 - (4 + 1)  = 2
        if (missingCount < k) {
            // potential answer on be right side
            si = mid + 1
        } else {
            // potential answer on be left side
            ei = mid - 1
        }
    }
    // After the end of loop  ei will be on correct position
    // nums[ei] can be our potential answer
    //lostCount will contain How many elements we lost from leftMost
    val lostCount = nums[ei] - (leftMost + ei)
    // check how much element missed out on ei from leftMost k-lostedCount
    val diff = k - lostCount
    // add that diff in nums[ei]
    return nums[ei] + diff
}


fun evalRPN(tokens: Array<String>): Int {

    // val sign
    // val stack = Stack<String>()
    var sum = 0
    val nums = Stack<Int>()
    //["2","1","+","3","*"]
    for (token in tokens) {

        when (token) {
            "+" -> {
                nums.push(nums.pop() + nums.pop())
//                while (stack.isNotEmpty()) {
//                    sum += stack.pop().toInt()
//                }
            }
            "-" -> {
//                while (stack.isNotEmpty()) {
//                    sum -= stack.pop().toInt()
//                }

                val num = nums.pop()
                nums.push(nums.pop() - num)
            }
            "*" -> {
//                while (stack.isNotEmpty()) {
//                    sum *= stack.pop().toInt()
//                }
                nums.push(nums.pop() * nums.pop())
            }
            "/" -> {
//                while (stack.isNotEmpty()) {
//                    sum /= stack.pop().toInt()
//                }
                val num = nums.pop()
                nums.push(nums.pop() / num)
            }
            else -> {
                nums.push(token.toInt())
            }
        }

    }


    return nums.pop()
}


fun decodeString(s: String): String {
    //"3[a]2[bc]"
    val stack = Stack<String>()
    val nums = Stack<Int>()
    var num = 0
    var str = ""
    for (c in s) {

        if (c in '0'..'9') {
            num = 10 * num + (c - '0')
        } else if (c == '[') {
            nums.push(num)
            stack.push(str)
            num = 0
            str = ""

        } else if (c == ']') {

            val k = nums.pop()
            var text = stack.pop()

            for (i in 0 until k) {
                text += str
            }
            str = text

        } else {
            str += c

        }
    }


    return str
}


fun main(args: Array<String>) {

    println(decodeString("3[a]2[bc]"))
    println(decodeString("2[abc]3[cd]ef"))

    // println(decodeString("3[a]2[bc]"))
    println(decodeString("3[a2[c]]"))
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
    // val scanner = Scanner(System.`in`)
    // println(checkSubarraySumTwo(intArrayOf(23, 2, 4, 6, 7), 6))
    //println(findAnagrams("cbaebabacd", "abc"))
    // findX("abacbc")
    //  println(reverseWords("a good   example"))
    //  println(reverseOnlyLetters("ab-cd"))
    //  println(removeDuplicateLetters("bcabc"))
    //  println(evalRPN(arrayOf("2","1","+","3","*")))
    //  println(evalRPN(arrayOf("4","13","5","/","+")))
    // println(evalRPN(arrayOf("2","1","+","3","*")))
    //  println(missingElement(intArrayOf(4, 7, 9, 10), 1))
}

