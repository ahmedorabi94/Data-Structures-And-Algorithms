package algorithms

import dataStructure.linkedlist.hackerrankSolutions.leetcodeSol.ListNode
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

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

    for (i in 0 until numRows){
        row.add(0,1)
        for (j in 1 until row.size - 1){
            row[j] = row[j] + row[j+1]
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
    var result = 0
    val size = nums.size
//    Arrays.sort(nums)
//                          //0,1,3
//    for (i in 1..size){   //1,2,3
//        if (nums[i-1] != 0){
//            if (i+1 != nums[i+1]){
//                return i
//            }
//        }
//
//
//    }

    val set = nums.toSet()
    println(set)

    for (i in 0..size){
        if (!set.contains(i)){
            return i
        }
    }



    return 0
}

fun main() {

    //  println(plusOne(intArrayOf(2,3,9)))
    //  println(mySqrt(8))
   // val node = ListNode(3, ListNode(2, ListNode(0, ListNode(-4, ListNode(2)))))
   // val node1 = ListNode(1, ListNode(2))
   // println(hasCycle(node))
  //  println(hasCycle(node1))

    println(missingNumber(intArrayOf(0,1)))
    println(missingNumber(intArrayOf(9,6,4,2,3,5,7,0,1)))
}