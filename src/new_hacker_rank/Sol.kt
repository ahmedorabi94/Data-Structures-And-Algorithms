package new_hacker_rank

import java.util.*
import kotlin.math.roundToLong

//
//internal object StringSegmentation {
//    fun canSegmentString(s: String, dictionary: Set<String?>): Boolean {
//        for (i in 1..s.length) {
//            val first = s.substring(0, i)
//            if (dictionary.contains(first)) {
//                val second = s.substring(i)
//                if (second == null || second.length == 0 || dictionary.contains(second) || canSegmentString(second, dictionary)) {
//                    return true
//                }
//            }
//        }
//        return false
//    }
//
//    @JvmStatic
//    fun main(args: Array<String>) {
//        val dictionary: MutableSet<String?> = HashSet()
//        var s = String()
//        s = "hellonow"
//        dictionary.add("hello")
//        dictionary.add("hell")
//        dictionary.add("on")
//        dictionary.add("now")
//        if (canSegmentString(s, dictionary)) {
//            println("String Can be Segmented")
//        } else {
//            println("String Can NOT be Segmented")
//        }
//    }
//}
//////////////////////////////////////////////
//// Null terminating strings are not used in java
//fun strRev(str: CharArray?, start: Int, end: Int): Unit {
//    var start = start
//    var end = end
//    if (str == null || str.size < 2) {
//        return
//    }
//    while (start < end) {
//        val temp = str[start]
//        str[start] = str[end]
//        str[end] = temp
//        start++
//        end--
//    }
//}
//
//fun reverseWords(sentence: CharArray?) {
//
//    // Here sentence is a null-terminated string ending with char '\0'.
//    if (sentence == null || sentence.size == 0) {
//        return
//    }
//
//    // To reverse all words in the string, we will first reverse
//    // the string. Now all the words are in the desired location, but
//    // in reverse order: "Hello World" -> "dlroW olleH".
//    val len = sentence.size
//    strRev(sentence, 0, len - 2)
//
//    // Now, let's iterate the sentence and reverse each word in place.
//    // "dlroW olleH" -> "World Hello"
//    var start = 0
//    var end: Int
//    while (true) {
//        // find the  start index of a word while skipping spaces.
//        while (sentence[start] == ' ') {
//            ++start
//        }
//        if (start >= sentence.size - 1) {
//            break
//        }
//
//        // find the end index of the word.
//        end = start + 1
//        while (end < sentence.size - 1 && sentence[end] != ' ') {
//            ++end
//        }
//
//        // let's reverse the word in-place.
//        strRev(sentence, start, end - 1)
//        start = end
//    }
//}
//
//fun getArray(t: String): CharArray? {
//    val s = CharArray(t.length + 1)
//    var i = 0
//    while (i < t.length) {
//        s[i] = t[i]
//        ++i
//    }
//    return s
//}
///////////////////////////////////////////////////
//fun solveCoinChange(denominations: IntArray, amount: Int): Int {
//    val solution = IntArray(amount + 1)
//    solution[0] = 1
//    for (den in denominations) {
//        for (i in den until amount + 1) {
//            solution[i] += solution[i - den]
//        }
//    }
//    return solution[solution.size - 1]
//}
////////////////////////////////////////////////////
//fun factorial(n: Int): Int {
//    return if (n == 0 || n == 1) 1 else n * factorial(n - 1)
//}
//
//fun find_kth_permutation(
//        v: List<Char?>,
//        k: Int,
//        result: StringBuilder) {
//    var k = k
//    if (v.isEmpty()) {
//        return
//    }
//    val n = v.size
//    // count is number of permutations starting with first digit
//    val count = factorial(n - 1)
//    val selected = (k - 1) / count
//    result.append(v[selected])
//    //v.removeAt(selected)
//    k = k - count * selected
//    find_kth_permutation(v, k, result)
//}
//
//fun get_permutation(n: Int, k: Int): String? {
//    val v: MutableList<Char?> = ArrayList()
//    var c = '1'
//    for (i in 1..n) {
//        v.add(c)
//        c++
//    }
//    val result = StringBuilder()
//    find_kth_permutation(v, k, result)
//    return result.toString()
//}
/////////////////////////////////////////////////////////////////////////
//// Return the minimum number
//// of swaps required to sort the array
//fun minSwaps(arr: IntArray, N: Int): Int {
//    var ans = 0
//    val temp = Arrays.copyOfRange(arr, 0, N)
//    Arrays.sort(temp)
//    for (i in 0 until N) {
//
//        // This is checking whether
//        // the current element is
//        // at the right place or not
//        if (arr[i] != temp[i]) {
//            ans++
//
//            // Swap the current element
//            // with the right index
//            // so that arr[0] to arr[i] is sorted
//            swap(arr, i, indexOf(arr, temp[i]))
//        }
//    }
//    return ans
//}
//
//fun swap(arr: IntArray, i: Int, j: Int) {
//    val temp = arr[i]
//    arr[i] = arr[j]
//    arr[j] = temp
//}
//
//fun indexOf(arr: IntArray, ele: Int): Int {
//    for (i in arr.indices) {
//        if (arr[i] == ele) {
//            return i
//        }
//    }
//    return -1
//}
/////////////////////////////////////////////////////////////////////////////////////////
//fun isCircular(path: CharArray): Boolean {
//    // Initialize starting
//    // point for robot as
//    // (0, 0) and starting
//    // direction as N North
//    var x = 0
//    var y = 0
//    var dir = 0
//
//    // Traverse the path given for robot
//    for (i in path.indices) {
//        // Find current move
//        val move = path[i]
//
//        // If move is left or
//        // right, then change direction
//        if (move == 'R') dir = (dir + 1) % 4 else if (move == 'L') dir = (4 + dir - 1) % 4 else  // if (move == 'G')
//        {
//            if (dir == 0) y++ else if (dir == 1) x++ else if (dir == 2) y-- else  // dir == 3
//                x--
//        }
//    }
//
//    // If robot comes back to
//    // (0, 0), then path is cyclic
//    return x == 0 && y == 0
//}
////////////////////////////////////////////////////////////////////////////////////////////////////
//internal class Node(item: Int) {
//    var data: Int
//    var left: Node?
//    var right: Node?
//
//    init {
//        data = item
//        right = null
//        left = right
//    }
//}
//
//internal class BinaryTree {
//    fun printSpiral(node: Node?) {
//        if (node == null) return  // NULL check
//
//        // Create two stacks to store alternate levels
//        // For levels to be printed from right to left
//        val s1 = Stack<Node?>()
//        // For levels to be printed from left to right
//        val s2 = Stack<Node?>()
//
//        // Push first level to first stack 's1'
//        s1.push(node)
//
//        // Keep printing while any of the stacks has some nodes
//        while (!s1.empty() || !s2.empty()) {
//            // Print nodes of current level from s1 and push nodes of
//            // next level to s2
//            while (!s1.empty()) {
//                val temp = s1.peek()
//                s1.pop()
//                print(temp!!.data.toString() + " ")
//
//                // Note that is right is pushed before left
//                if (temp.right != null) s2.push(temp.right)
//                if (temp.left != null) s2.push(temp.left)
//            }
//
//            // Print nodes of current level from s2 and push nodes of
//            // next level to s1
//            while (!s2.empty()) {
//                val temp = s2.peek()
//                s2.pop()
//                print(temp!!.data.toString() + " ")
//
//                // Note that is left is pushed before right
//                if (temp.left != null) s1.push(temp.left)
//                if (temp.right != null) s1.push(temp.right)
//            }
//        }
//    }
//
//    companion object {
//        var root: Node? = null
//
//        @JvmStatic
//        fun main(args: Array<String>) {
//            val tree = BinaryTree()
//            root = Node(1)
//            root!!.left = Node(2)
//            root!!.right = Node(3)
//            root!!.left!!.left = Node(7)
//            root!!.left!!.right = Node(6)
//            root!!.right!!.left = Node(5)
//            root!!.right!!.right = Node(4)
//            println("Spiral Order traversal of Binary Tree is ")
//            tree.printSpiral(root)
//        }
//    }
//}
//////////////////////////////////////////////
//fun suggestedProducts(P: Array<String>, S: String): List<List<String>> {
//    Arrays.sort(P)
//    val ans: MutableList<List<String>> = ArrayList()
//    var left = 0
//    var right = P.size - 1
//    for (i in S.indices) {
//        val res: MutableList<String> = ArrayList()
//        val c = S[i]
//        while (left <= right && (P[left].length == i || P[left][i] < c)) left++
//        while (left <= right && (P[right].length == i || P[right][i] > c)) right--
//        var j = 0
//        while (j < 3 && left + j <= right) {
//            res.add(P[left + j])
//            j++
//        }
//        ans.add(res)
//    }
//    return ans
//}
//////////////////////////////////////////////////////////
//fun suggestedProductsx(products: Array<String>, searchWord: String): List<List<String>> {
//    val ans: MutableList<List<String>> = ArrayList()
//    Arrays.sort(products)
//    for (i in 1..searchWord.length) {
//        val temp: MutableList<String> = ArrayList()
//        var count = 0
//        for (word in products) {
//            if (word.length >= i && word.substring(0, i) == searchWord.substring(0, i)) {
//                temp.add(word)
//                count++
//            }
//            if (count >= 3) break
//        }
//        ans.add(temp)
//    }
//    return ans
//}

fun x (rank : Array<Int>) : Long{
    var result =0
    rank.sort()

    rank.forEach {
        println(it)
    }

    var ref = rank[0]

//    for (i in rank.size -1 downTo 1){
//        if (rank[i] - ref > 1){
//            result = result + 1
//        }
//        ref = rank[i]
//    }


        for (j in 0 until  rank.size  ){
            for (i in rank.size -1 downTo 1 ){
                if (rank[i] - rank[j] > 1){
                    result = result + 1
                }
            }
    //    ref = rank[i]
    }

    return result.toLong()
}
// 1,2,3,4

fun main() {
   // println(x(arrayOf(4,1,3,2)))
   //println(x(arrayOf(1,5,4)))
    println(x(arrayOf(3,1,2)))
}