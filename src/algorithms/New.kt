package algorithms

import dataStructure.linkedlist.hackerrankSolutions.leetcodeSol.ListNode
import dataStructure.tree.TreeNode
import dataStructure.tree.TreeNodeX
import java.util.*


//66. Plus One
fun plusOne(digits: IntArray): IntArray {

    for (i in digits.size - 1 downTo 0) {
        if (digits[i] < 9) {
            val num = digits[i] + 1
            digits[i] = num
            return digits
        }

        digits[i] = 0
    }

    val res = IntArray(digits.size + 1)
    res[0] = 1

    return res
}

fun mySqrt(x: Int): Int {

    if (x < 2) {
        return x
    }

    var low = 1
    var high = x

    while (low <= high) {
        val mid = low + (high - low) / 2
        val squaredVal = mid.toLong() * mid
        if (squaredVal.toInt() == x) {
            return mid
        } else if (squaredVal > x) {
            high = mid - 1
        } else {
            low = mid + 1
        }
    }

    return low - 1
}

//70. Climbing Stairs
fun climbStairs(n: Int): Int {

    // dp[i] := # of distinct ways to climb to i-th stair
    val dp = IntArray(n + 1)
    dp[0] = 1
    dp[1] = 1

    for (i in 2..n) {
        dp[i] = dp[i - 1] + dp[i - 2]

    }

    return dp[n]
}


//88. Merge Sorted Array
fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {

    var left = m - 1
    var right = n - 1    // last index in sec array
    var current = left + right + 1


    while (right >= 0) {
        if (left >= 0 && nums1[left] > nums2[right]) {
            nums1[current] = nums1[left]
            current--
            left--
        } else {
            nums1[current] = nums2[right]
            current--
            right--
        }

    }


}

//136. Single Number
fun singleNumber(A: IntArray): Int {
    var result = 0
    for (i in A.indices) {
        result = result xor A[i]
    }

    return result
}


fun hasCyclex(head: ListNode?): Boolean {

    var p = head
    val list = ArrayList<Int>()
    while (p != null) {
        val number = p.`val`
        list.add(number)
        p = p.next
    }

    val set = HashSet<Int>()
    list.forEach {
        if (!set.add(it)) {
            return true
        }
    }


    return false
}

fun hasCycle(head: ListNode?): Boolean {
    var slow = head
    var fast = head
    while (fast?.next != null) {
        slow = slow!!.next
        fast = fast.next!!.next

        if (slow === fast) return true
    }
    return false
}

fun majorityElement(nums: IntArray): Int {

    var ref = nums[0]
    var counter = 0
    for (i in 1 until nums.size) {

        if (counter == 0) {
            ref = nums[i]
            counter = 1

        } else {
            if (nums[i] == ref) {
                counter++
            } else {
                counter--
            }
        }

    }

    return ref
}

fun titleToNumber(s: String): Int {
    var result = 0
    for (element in s) {
        val digit = element - 'A' + 1
        result = result * 26 + digit
    }
    return result
}

//118. Pascal's Triangle
fun generate(numRows: Int): List<List<Int>> {

    val result = ArrayList<ArrayList<Int>>()
    val row = ArrayList<Int>()

    for (i in 0 until numRows) {
        row.add(0, 1)
        for (j in 1 until row.size - 1) {
            row[j] = row[j] + row[j + 1]
        }

        result.add(ArrayList(row))
    }


    return result
}

//349. Intersection of Two Arrays
fun intersection(nums1: IntArray, nums2: IntArray): IntArray {


    return IntArray(0)
}

//268. Missing Number
fun missingNumber(nums: IntArray): Int {

    val size = nums.size

    val set = nums.toSet()
    println(set)

    for (i in 0..size) {
        if (!set.contains(i)) {
            return i
        }
    }

    return 0
}

fun missingNumber2(nums: IntArray): Int {
    var miss = 0
    for (i in nums.indices) {
        miss = miss xor i + 1
        miss = miss xor nums[i]
    }
    return miss
}


//0, 10,5,2
//852. Peak Index in a Mountain Array
fun peakIndexInMountainArray(arr: IntArray): Int {
    //  var result = 0
    var left = 0
    var right = arr.size - 1

    while (left < right) {

        val mid = (left + right) / 2

        if (arr[mid + 1] > arr[mid]) {
            left = mid + 1
        } else {
            right = mid
        }


    }

    return left
}

//977. Squares of a Sorted Array
fun sortedSquares(nums: IntArray): IntArray {
    for (i in nums.indices) {
        nums[i] = nums[i] * nums[i]
    }

    Arrays.sort(nums)

    return nums
}

//844. Backspace String Compare
fun backspaceCompare(s: String, t: String): Boolean {

    val s_sb = StringBuilder()
    val t_sb = StringBuilder()


    //ab#c
    //ab##
    for (i in s.indices) {

        if (s[i] == '#') {
            continue
        }

        if ((i < s.length - 1 && s[i + 1] == '#')) {
            continue
        } else if ((i < s.length - 2 && s[i + 2] == '#')) {
            continue
        } else {
            s_sb.append(s[i])
        }

    }

    for (i in t.indices) {

        if (t[i] == '#') {
            continue
        }

        if ((i < t.length - 1 && t[i + 1] == '#')) {
            continue
        } else if ((i < t.length - 2 && t[i + 2] == '#')) {
            continue
        } else {
            t_sb.append(t[i])
        }

    }

    println(s_sb.toString())
    println(t_sb.toString())

    return compareTest(s) == compareTest(t)


}

fun compareTest(s: String): String {
    val sb = StringBuilder()

    for (i in s.indices) {
        if (s[i] != '#') {
            sb.append(s[i])
        } else {
            sb.deleteCharAt(sb.length - 1)
        }
    }

    return sb.toString()
}

//2022. Convert 1D Array Into 2D Array
fun construct2DArray(original: IntArray, m: Int, n: Int): Array<IntArray>? {
    if (original.size != m * n) return arrayOf()

    val ans = Array(m) { IntArray(n) }

    for (i in original.indices) {
        ans[i / n][i % n] = original[i]
    }
    return ans
}

//287. Find the Duplicate Number
fun findDuplicate(nums: IntArray): Int {
    var slow = nums[nums[0]]
    var fast = nums[nums[nums[0]]]
    while (slow != fast) {
        slow = nums[slow]
        fast = nums[nums[fast]]
    }
    slow = nums[0]
    while (slow != fast) {
        slow = nums[slow]
        fast = nums[fast]
    }
    return slow
}


//442. Find All Duplicates in an Array
fun findDuplicates(nums: IntArray): List<Int> {

    val result = ArrayList<Int>()

    for (i in 0 until nums.size) {
        val index = Math.abs(nums[i]) - 1

        if (nums[index] < 0) {
            result.add(index + 1)
        }

        nums[index] = nums[index] * -1
    }

    return result

}

//128. Longest Consecutive Sequence
fun longestConsecutive(nums: IntArray): Int {

    if (nums.isEmpty()) {
        return 0
    }

    Arrays.sort(nums)

    var counter = 1
    var longCounter = 1

    for (i in 1 until nums.size) {
        if (nums[i] != nums[i - 1]) {
            if (nums[i] == nums[i - 1] + 1) {
                counter++
            } else {
                longCounter = Math.max(counter, longCounter)
                counter = 1
            }
        }
    }


    return Math.max(longCounter, counter)
}


//78. Subsets
fun subsets(nums: IntArray): List<List<Int>> {

    val result = ArrayList<ArrayList<Int>>()

    dfs(nums, 0, result, ArrayList())
    return result

}

private fun dfs(nums: IntArray, k: Int, result: ArrayList<ArrayList<Int>>, temp: ArrayList<Int>) {

    result.add(ArrayList(temp))

    for (i in k until nums.size) {
        temp.add(nums[i])
        dfs(nums, i + 1, result, temp)
        temp.removeAt(temp.size - 1)
    }

}

//22. Generate Parentheses
fun generateParenthesis(n: Int): List<String>? {
    val ans: MutableList<String> = ArrayList()
    dfs(n, n, StringBuilder(), ans)
    return ans
}

private fun dfs(l: Int, r: Int, sb: StringBuilder, ans: MutableList<String>) {

    if (l == 0 && r == 0) {
        ans.add(sb.toString())
        return
    }
    if (l > 0) {
        sb.append("(")
        dfs(l - 1, r, sb, ans)
        sb.deleteCharAt(sb.length - 1)
    }
    if (l < r) {
        sb.append(")")
        dfs(l, r - 1, sb, ans)
        sb.deleteCharAt(sb.length - 1)
    }
}

//162. Find Peak Element
fun findPeakElement(nums: IntArray): Int {
    var left = 0
    var right = nums.size - 1

    while (left < right) {
        val mid = (left + right) / 2
        if (nums[mid + 1] >= nums[mid]) {
            left = mid + 1
        } else {
            right = mid
        }
    }

    return left
}


//658. Find K Closest Elements
//Input: arr = [1,2,3,4,5], k = 4, x = 3
fun findClosestElements(arr: IntArray, k: Int, x: Int): List<Int>? {
    var left = 0
    var right = arr.size - k

    while (left < right) {
        val mid = (left + right) / 2
        if (x - arr[mid] > arr[mid + k] - x) {
            left = mid + 1
        } else {
            right = mid
        }
    }

    val result = ArrayList<Int>()
    for (i in left until left + k) {
        result.add(arr[i])
    }
    return result
}


//215. Kth Largest Element in an Array
fun findKthLargest(nums: IntArray, k: Int): Int {
    val minHeap = PriorityQueue<Int>()

    for (num in nums) {
        minHeap.add(num)
        if (minHeap.size > k) {
            minHeap.remove()
        }
    }
    return minHeap.remove()
}

//347. Top K Frequent Elements
fun topKFrequent(nums: IntArray, k: Int): IntArray {

    if (nums.size == k) {
        return nums
    }

    val map = HashMap<Int, Int>()
    for (n in nums) {
        map[n] = map.getOrDefault(n, 0) + 1
    }


    val pq = PriorityQueue<Int> { n1, n2 -> map[n1]!! - map[n2]!! }

    for (n in map.keys) {
        pq.add(n)
        if (pq.size > k) {
            pq.poll()
        }
    }


    val result = IntArray(k)
    for (i in 0 until k) {
        result[i] = pq.poll()
    }

    return result
}


//113. Path Sum II
fun pathSum2(root: TreeNodeX?, targetSum: Int): List<List<Int>> {
    val result = ArrayList<ArrayList<Int>>()

    hasPathSum(root, targetSum, result, ArrayList())

    return result
}

fun hasPathSum(root: TreeNodeX?, targetSum: Int, result: ArrayList<ArrayList<Int>>, temp: ArrayList<Int>) {
    if (root == null) {
        return
    }
    temp.add(root.value)
    if (root.right == null && root.left == null && targetSum - root.value == 0) {
        result.add(ArrayList(temp))
    }

    hasPathSum(root.left, targetSum - root.value, result, temp)
    hasPathSum(root.right, targetSum - root.value, result, temp)
    temp.removeAt(temp.size - 1)
}

fun lowestCommonAncestor(root: TreeNode?, p: TreeNode, q: TreeNode): TreeNode? {
    if (root == null) {
        return null
    }
    if (root == p || root == q) {
        return root
    }
    val left = lowestCommonAncestor(root.left, p, q)
    val right = lowestCommonAncestor(root.right, p, q)

    if (left != null && right != null) {
        return root
    }
    return if (left != null) {
        left
    } else {
        right
    }
}

//208. Implement Trie (Prefix Tree)
class TrieeNode {
    var children = arrayOfNulls<TrieeNode>(26)
    var isWord = false
}

class Triee {

    private val root = TrieeNode()

    fun insert(word: String) {
        var node: TrieeNode = root
        for (c in word.toCharArray()) {
            val i = c - 'a'
            if (node.children[i] == null) { // we need to create new char because it doesnt exits
                node.children[i] = TrieeNode()
            }
            node = node.children[i]!! // go to next node
        }
        node.isWord = true
    }

    fun search(word: String): Boolean {
        var node: TrieeNode = root
        for (c in word.toCharArray()) {
            val i = c - 'a'
            if (node.children[i] == null) { // we didnt fins this charachter
                return false
            }
            node = node.children[i]!!
        }
        return node.isWord
    }

    fun startsWith(prefix: String): Boolean {
        var node: TrieeNode = root
        for (c in prefix.toCharArray()) {
            val i = c - 'a'
            if (node.children[i] == null) { // we didnt fins this charachter
                return false
            }
            node = node.children[i]!!
        }
        return true
    }

}

//662. Maximum Width of Binary Tree
fun widthOfBinaryTree(root: TreeNode?): Int {
    if (root == null) return 0
    var ans = 0
    val q: Deque<Pair<TreeNode, Int>> = ArrayDeque() // {node, index}
    q.offer(Pair(root, 1))

    while (!q.isEmpty()) {

        val offset: Int = q.peekFirst().second * 2
        ans = Math.max(ans, q.peekLast().second - q.peekFirst().second + 1)


        for (sz in q.size downTo 1) {
            val node = q.peekFirst().first
            val index: Int = q.pollFirst().second

            if (node.left != null) {
                q.offer(Pair(node.left!!, index * 2 - offset))
            }
            if (node.right != null) {
                q.offer(Pair(node.right!!, index * 2 + 1 - offset))
            }
        }


    }

    return ans
}

fun widthOfBinaryTree2(root: TreeNode?): Int {
    if (root == null) return 0
    var ans = 0
    val q: Deque<Pair<TreeNode, Int>> = ArrayDeque() // {node, index}
    q.offer(Pair(root, 0))

    while (!q.isEmpty()) {

        ans = Math.max(ans, q.peekLast().second - q.peekFirst().second + 1)
        for (i in 0 until q.size) {
            val node = q.peekFirst().first
            val index: Int = q.pollFirst().second

            if (node.left != null) {
                q.offer(Pair(node.left!!, index * 2 + 1))
            }
            if (node.right != null) {
                q.offer(Pair(node.right!!, index * 2 + 2))
            }
        }

    }

    return ans
}

//11. Container With Most Water
fun maxArea(height: IntArray): Int {
    var maxarea = 0
    for (left in height.indices) {
        for (right in left + 1 until height.size) {
            val width = right - left
            maxarea = Math.max(maxarea, Math.min(height[left], height[right]) * width)
        }
    }
    return maxarea
}

//238. Product of Array Except Self
fun productExceptSelf(nums: IntArray): IntArray {
    val result = IntArray(nums.size)
    val prefix = IntArray(nums.size) // prefix product

    val suffix = IntArray(nums.size)

    prefix[0] = 1
    for (i in 1 until nums.size) {
        prefix[i] = nums[i - 1] * prefix[i - 1]
    }

    suffix[nums.size - 1] = 1
    for (i in nums.size - 2 downTo 0) {
        suffix[i] = nums[i + 1] * suffix[i + 1]
    }


    for (i in nums.indices) {
        result[i] = prefix[i] * suffix[i]
    }

    return result
}

fun productExceptSelf2(nums: IntArray): IntArray? {
    val n = nums.size
    val ans = IntArray(n)
    ans[0] = 1

    // use ans as the prefix product array
    for (i in 1 until n) {
        ans[i] = ans[i - 1] * nums[i - 1]
    }
    var suffix = 1 // suffix product
    for (i in n - 1 downTo 0) {
        ans[i] *= suffix
        suffix *= nums[i]
    }
    return ans
}

//19. Remove Nth Node From End of List
fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {

    var list = head
    var numOfNodes = 0

    while (list != null) {
        numOfNodes++
        list = list.next
    }

    var length = numOfNodes - n
    val newNode = ListNode(-1)
    var parent = newNode
    parent.next = head

    while (length > 0){
        parent = parent.next!!
        length--
    }

    parent.next = parent.next?.next

    return newNode.next
}



fun main() {

    val root = TreeNodeX(1)
    root.left = TreeNodeX(3)
    root.left!!.left = TreeNodeX(5)
    root.left!!.left!!.right = TreeNodeX(3)
    root.right = TreeNodeX(2)
    root.right!!.right = TreeNodeX(9)
    //  root.right!!.right!!.right = TreeNodeX(6)

    //  println(widthOfBinaryTree(root))

    //  println(plusOne(intArrayOf(2,3,9)))
    //  println(mySqrt(8))
    // val node = ListNode(3, ListNode(2, ListNode(0, ListNode(-4, ListNode(2)))))
    // val node1 = ListNode(1, ListNode(2))
    // println(hasCycle(node))
    //// println(backspaceCompare("ab#c", "ad#c"))
    // println(backspaceCompare("ab##", "c#d#"))
    // println(backspaceCompare("a#c", "b"))
    // println(subsets(intArrayOf(1,2,3)))
    val res = topKFrequent(intArrayOf(1, 1, 1, 2, 2, 3), 2)
    res.forEach {
        println(it)
    }
    // println(sortedSquares(intArrayOf(-7, -3, 2, 3, 11)))
    //println(missingNumber(intArrayOf(0, 1)))
    // println(missingNumber(intArrayOf(9, 6, 4, 2, 3, 5, 7, 0, 1)))
}