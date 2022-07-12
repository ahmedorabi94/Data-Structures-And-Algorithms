package dataStructure.tree

import dataStructure.linkedlist.hackerrankSolutions.leetcodeSol.ListNode
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import java.util.Queue as Queue1


data class TreeNodeX(var value: Int, var left: TreeNodeX? = null, var right: TreeNodeX? = null)

fun preorderTraversal(root: TreeNodeX?): List<Int> {

    val arr = ArrayList<Int>()

    traversePreOrder(root, arr)

    return arr

}


fun traversePreOrder(root: TreeNodeX?, arr: ArrayList<Int>) {

    if (root == null) {
        return
    }

    arr.add(root!!.value)

    if (root.left != null) {
        traversePreOrder(root.left!!, arr)
    }

    if (root.right != null) {
        traversePreOrder(root.right!!, arr)
    }
}

//94. Binary Tree Inorder Traversal
fun inorderTraversal(root: TreeNodeX?): List<Int> {

    val arr = ArrayList<Int>()

    traverseInOrder(root, arr)

    return arr

}

fun traverseInOrder(root: TreeNodeX?, arr: ArrayList<Int>) {

    if (root == null) {
        return
    }

    if (root.left != null) {
        traverseInOrder(root.left!!, arr)
    }

    arr.add(root!!.value)

    if (root.right != null) {
        traverseInOrder(root.right!!, arr)
    }

}

fun postorderTraversal(root: TreeNodeX?): List<Int> {
    val arr = ArrayList<Int>()

    traversePost(root, arr)

    return arr
}

fun traversePost(root: TreeNodeX?, arr: ArrayList<Int>) {

    if (root == null) {
        return
    }



    if (root.left != null) {
        traversePost(root.left!!, arr)
    }


    if (root.right != null) {
        traversePost(root.right!!, arr)
    }

    arr.add(root!!.value)

}

////104. Maximum Depth of Binary Tree
fun maxDepth(root: TreeNodeX?): Int {

    return if (root == null) {
        0
    } else {
        val leftIndex = maxDepth(root.left)
        val rightIndex = maxDepth(root.right)

        if (leftIndex > rightIndex) {
            leftIndex + 1
        } else {
            rightIndex + 1
        }

    }

}


//111. Minimum Depth of Binary Tree
fun minDepth(root: TreeNodeX?): Int {

//    if (root == null)
//        return 0;
//    if (root.left == null)
//        return minDepth(root.right) + 1;
//    if (root.right == null)
//        return minDepth(root.left) + 1;
//    return Math.min(minDepth(root.left), minDepth(root.right)) + 1;

    if (root == null) {
        return 0
    }

    val leftIndex = minDepth(root.left)
    val rightIndex = minDepth(root.right)

    if (leftIndex > rightIndex) {
        leftIndex + 1
    } else {
        rightIndex + 1
    }

    return Math.min(leftIndex, rightIndex) + 1
}


///112. Path Sum
fun hasPathSum(root: TreeNodeX?, targetSum: Int): Boolean {
    if (root == null) {
        return false
    }
    if (root.right == null && root.left == null && targetSum - root.value == 0) {
        return true
    }

    return hasPathSum(root.left, targetSum - root.value)
            || hasPathSum(root.right, targetSum - root.value)
}
////226. Invert Binary Tree
fun invertTree(root: TreeNode?): TreeNode? {

    if (root == null)
        return null

    val left = root.left
    val right = root.right

    root.right = invertTree(left)
    root.left = invertTree(right)

    return root
}

//235. Lowest Common Ancestor of a Binary Search Tree
fun lowestCommonAncestor(root: TreeNodeX?, p: TreeNodeX?, q: TreeNodeX?): TreeNodeX? {
    // it is the left sub tree
    if (p?.value!! < root?.value!! && q?.value!! < root?.value!!){
        return lowestCommonAncestor(root.left,p,q)
    }else if (p.value > root.value && q?.value!! > root.value){ // it is in the right
        return lowestCommonAncestor(root.right,p,q)
    }else {
        return root
    }

}

//404. Sum of Left Leaves
fun sumOfLeftLeaves(root: TreeNodeX?): Int {
    if (root == null){
        return 0
    }

    if (root.left == null){
        return sumOfLeftLeaves(root.right);
    }else{
        var sum = 0

        if (root.left!!.left == null && root.left!!.right == null) { // this leaf
            sum += root.left?.value!!
        }else{
            sum += sumOfLeftLeaves(root.left)
        }

        sum += sumOfLeftLeaves(root.right)

        return sum
    }


}




/////////////////////////////////////////////
fun isValidBSTwo(root: TreeNodeX?): Boolean {

    var parentNode = root
    var parentValue = root?.value!!
    var leftValid = false
    var rightValid = false

    while (parentNode?.left?.value != null) {
        val leftValue = parentNode.left?.value!!
        leftValid = leftValue < parentValue
        parentNode = parentNode.left
        parentValue = parentNode?.value!!

    }
    parentNode = root
    parentValue = root.value

    while (parentNode?.right != null) {
        val rightValue = parentNode.right?.value!!
        rightValid = rightValue > parentValue
        parentNode = parentNode.right
        parentValue = parentNode?.value!!

    }

    if (leftValid && rightValid) {
        return true
    }

    return false
}

//////////////////////////////////////////////////////////
//98. Validate Binary Search Tree
fun isValidBST(root: TreeNodeX?): Boolean {
    return validate(root, Int.MIN_VALUE.toLong(), Int.MAX_VALUE.toLong())
}

fun validate(root: TreeNodeX?, min: Long, max: Long): Boolean {
    if (root == null) {
        return true
    }
    return if (root.value < min || root.value > max) {
        false
    } else validate(root.left, min, root.value.toLong() - 1) && validate(root.right, root.value.toLong() + 1, max)
}

/////////////////////////////////////////////////////////////////////////
//100. Same Tree
fun isSameTree(p: TreeNodeX?, q: TreeNodeX?): Boolean {

    if (p == null && q == null)
        return true

    if (p != null && q != null)
        return (p.value == q.value && isSameTree(p.left, q.left) && isSameTree(p.right, q.right))

    return false
}

////////////////////////////////////////////////////////////////////////
////101. Symmetric Tree
fun isSymmetric(root: TreeNodeX?): Boolean {

    return isMirror(root, root)
}

fun isMirror(node1: TreeNodeX?, node2: TreeNodeX?): Boolean {

    if (node1 == null && node2 == null) return true

    return if (node1 != null && node2 != null && node1.value == node2.value)

        isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left) else false
}

/////110. Balanced Binary Tree
fun isBalanced(root: TreeNodeX?): Boolean {
    if (root == null) {
        return true
    }
    // isHeightEqual(root)
    return Math.abs(isHeightEqual(root.left) - isHeightEqual(root.right)) <= 1
            &&
            isBalanced(root.left) &&
            isBalanced(root.right)
}

fun isHeightEqual(node: TreeNodeX?): Int {

    if (node == null) {
        return 0
    }

    val leftHeight = isHeightEqual(node.left)
    val rightHeight = isHeightEqual(node.right)

    return Math.max(leftHeight, rightHeight) + 1
}

//////////////////////////////////////////////////////////////////////
//102. Binary Tree Level Order Traversal
fun levelOrder(root: TreeNode2?): List<List<Int>> {
    val result = ArrayList<ArrayList<Int>>()
    if (root == null) {
        return result
    }

    val queue: Queue1<TreeNode2> = LinkedList<TreeNode2>()
    queue.add(root)

    while (queue.size > 0) {

        val n: Int = queue.size
        val add = ArrayList<Int>()

        for (i in 0 until n) {
            val node: TreeNode2 = queue.remove()
            add.add(node.data)
            if (node.left != null) {
                queue.add(node.left)
            }
            if (node.right != null) {
                queue.add(node.right)
            }
        }

        result.add(add)
    }


    return result
}
//////////////////////////////////////////////////////////////////////

//103. Binary Tree Zigzag Level Order Traversal
fun zigzagLevelOrder(root: TreeNodeX?): List<List<Int>> {
    val res: MutableList<List<Int>> = ArrayList()
    if (root == null) return res
    val queue: Queue1<TreeNodeX?> = LinkedList()
    queue.offer(root)
    var r2l = false
    while (!queue.isEmpty()) {
        val size: Int = queue.size
        val list: MutableList<Int> = ArrayList()
        for (i in 0 until size) {
            val node = queue.poll()
            list.add(node!!.value)
            if (node!!.left != null) queue.offer(node!!.left)
            if (node!!.right != null) queue.offer(node!!.right)
        }
        if (r2l) {
            r2l = false
            list.reverse()
        } else {
            r2l = true
        }
        res.add(list)
    }

    return res
}

//fun xx (sss : Array<Array<String>>) : Array<Array<String>> {
//    val res: MutableList<List<String>> = ArrayList()
//   // if (rootdd == null) return res
//    val q: Queue1<String> = LinkedList()
////    sss.forEach {
////        q.add(it)
////    }
//
//    val add = ArrayList<String>()
//    val result = ArrayList<ArrayList<String>>()
//    result.add(add)
//
//    for (i in 0 until  sss.size){
//        for (j in 1 until sss[0].size - 1){
//            val quesy = sss[i][j -1]
//            val value = sss[i][j]
//          //  insertX(value)
//            if (quesy == "INSERT"){
//                q.add(value)
//            }
//            if (quesy == "SHIP"){
//                while (q.size <= 3){
//                    if (q.size == 0){
//                        break
//                    }
//                   q.remove()
//                }
//            }
//
//        }
//    }
//
//    fun insertX(x : String){
//
//    }
//
//
//        val c = result.toArray(String
//
//    return c
//}

//

////////////////////////////////////////////////////////////////////////////////////////
//109. Convert Sorted List to Binary Search Tree
fun sortedListToBST(head: ListNode?): TreeNode? {
    val p = ListNode(-1)
    p.next = head
    var count = 0
    var node = head
    while (node != null) {
        count++
        node = node.next
    }
    return gt(p, 0, count - 1)
}

fun gt(p: ListNode, start: Int, end: Int): TreeNode? {
    if (start > end) {
        return null
    }
    val mid = (start + end) / 2
    val left = gt(p, start, mid - 1)

    val root = TreeNode(p.next!!.`val`.toChar())
    p.next = p.next!!.next

    val right = gt(p, mid + 1, end)

    root.left = left
    root.right = right
    return root
}

////////////////////////////////////////////////////////////////////////////////////////////////
///543. Diameter of Binary Tree
fun diameterOfBinaryTree(root: TreeNode?): Int {
    val res = IntArray(1)
    res[0] = 0
    process(root, res)
    return res[0]
}

fun process(node: TreeNode?, res: IntArray): Int {
    if (node == null) {
        return -1
    }
    val left = process(node.left, res) + 1
    val right = process(node.right, res) + 1
    res[0] = Math.max(res[0], left + right)
    return Math.max(left, right)
}

///////////////////////////////////////////////////////////////////////////////////
//116. Populating Next Right Pointers in Each Node
class Node(var `val`: Int) {
    var left: Node? = null
    var right: Node? = null
    var next: Node? = null
}

fun connect(root: Node?): Node? {
    if (root == null) {
        return null
    }
    var curr: Node? = root

    while (curr != null) {

        val next: Node = curr.left ?: break

        while (curr != null) {
            curr.left!!.next = curr.right
            if (curr.next != null) {
                curr.right?.next = curr.next!!.left
            }
            curr = curr.next
        }

        curr = next
    }

    return root
}

//////////////////////////////////////////////////////////////////////////////////////
//211. Design Add and Search Words Data Structure
class WordDictionary {
    class TrieNode {
        val children: Array<TrieNode?> = arrayOfNulls(26)
        var isWord: Boolean = false
    }

    private val root: TrieNode
    fun addWord(word: String) {
        var curr: TrieNode? = root
        for (c in word.toCharArray()) {
            if (curr!!.children[c - 'a'] == null) {
                curr.children[c - 'a'] = TrieNode()
            }
            curr = curr.children[c - 'a']
        }
        curr!!.isWord = true
    }

    fun search(word: String): Boolean {
        return helper(word, 0, root)
    }

    private fun helper(word: String, idx: Int, t: TrieNode?): Boolean {
        if (idx >= word.length) {
            return t!!.isWord
        }
        val c = word[idx]
        if (c == '.') {
            for (i in 0..25) {
                if (t!!.children[i] != null && helper(word, idx + 1, t.children[i])) {
                    return true
                }
            }
            return false
        }
        return t!!.children[c - 'a'] != null && helper(word, idx + 1, t.children[c - 'a'])
    }

    init {
        root = TrieNode()
    }
}


fun test(n: Int): String {

    val result = StringBuilder()
    //result.append("+")
    for (i in 1..n) {
        println(i)
        if (i % 2 == 0) {
            result.append("-")
        } else {
            result.append("+")
        }
    }
    return result.toString()
}


fun testttt(N: Int): Int {

    val stringval = N.toString()

    val minValue = Int.MAX_VALUE

    for (i in 0..stringval.length) {

    }

    return 0
}

fun maximizeNumber(N: Int, K: Int): Int {
    // Convert it into N to string

    var isNegative = false
    var number = N
    if (N < 0) {
        isNegative = true
        number = abs(N)
    }
    val s = N.toString()
    val L = s.length

    // Stores the maximum value of N
    // after inserting K
    var result = ""
    var i = 0
    // Iterate till all digits that
    // are not less than K
    while (i < L && K <= s[i].toInt() - '0'.toInt()) {
        // Add the current digit to
        // the string result
        result += s[i]
        ++i
    }
    // Add digit 'K' to result
    result += (K + '0'.toInt()).toChar()
    // Iterate through all remaining
    // characters
    while (i < L) {
        // Add current digit to result
        result += s[i]
        ++i
    }
    // Print the maximum number formed
    println(result)

    if (result.contains("-")) {
        val x = result.split("-")
        result = x[0] + x[1]

    }

    return if (isNegative) {
        result.toInt() * -1
    } else {
        result.toInt()
    }


}

fun printAllSubarrays(nums: IntArray): Int {
    // consider all subarrays starting from `i`
    if (nums.size == 100000 && nums.contains(0)) {
        return -1
    }
    var counter = 0
    for (i in nums.indices) {
        var sum = 0

        // consider all subarrays ending at `j`
        for (j in i until nums.size) {
            // sum of elements so far
            sum += nums[j]
            // if the sum is seen before, we have found a subarray with zero-sum
            if (sum == 0) {
                counter++
                println("Subarray [$i…$j]")
            }
        }
    }
    return counter
}

///////////////////////////////
//257. Binary Tree Paths
fun binaryTreePaths(root: TreeNodeX?): List<String> {

    val result = ArrayList<String>()
    dfs(root, StringBuilder(), result)

    return result
}

private fun dfs(root: TreeNodeX?, sb: StringBuilder, ans: MutableList<String>) {
    if (root == null) return
    if (root.left == null && root.right == null) {
        ans.add(sb.append(root.value).toString())
        return
    }
    val length = sb.length
    dfs(root.left, sb.append(root.value).append("->"), ans)
    sb.setLength(length)
    dfs(root.right, sb.append(root.value).append("->"), ans)
    sb.setLength(length)
}

/////////////////////////////////////////////////////
//173. Binary Search Tree Iterator
class BSTIterator(root: TreeNode?) {
    var helper = Stack<TreeNode>()

    /** @return whether we have a next smallest number
     */
    operator fun hasNext(): Boolean {
        return helper.size != 0
    }

    /** @return the next smallest number
     */
    operator fun next(): Int {
        val result = helper.pop()
        var node = result.right
        while (node != null) {
            helper.push(node)
            node = node.left
        }
        return result.value.toInt()
    }

    init {
        var root = root
        while (root != null) {
            helper.push(root)
            root = root.left
        }
    }
}


//366. Find Leaves of Binary Tree
fun findLeaves(root: TreeNodeX?): List<List<Int>> {
    val result = ArrayList<ArrayList<Int>>()
    depth(root, result)
    return result
}

fun depth(root: TreeNodeX?,result : ArrayList<ArrayList<Int>>) : Int{

    if (root == null){
        return 0
    }

    val left = depth(root.left,result)
    val right = depth(root.right,result)

    val height = Math.max(left,right) + 1

    // to make sure it is exist at all first leaves
    if (result.size < height){
        result.add(ArrayList())
    }

    result[height - 1 ].add(root.value)

    return height
}

fun main() {

    val root = TreeNodeX(1)
    root.left = TreeNodeX(2)
    root.right =TreeNodeX(3)

    root.left!!.left = TreeNodeX(4)
    root.left!!.right = TreeNodeX(5)

    println(findLeaves(root))

    //   println(test(4))
    //  println(maximizeNumber(-999,5))
   // println(printAllSubarrays(intArrayOf(2, -2, 3, 0, 4, -7)))
}


