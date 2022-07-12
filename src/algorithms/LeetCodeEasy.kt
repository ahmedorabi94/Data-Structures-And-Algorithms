package algorithms

import java.util.*


fun twoSum(nums: IntArray, target: Int): IntArray {

    for (i in nums.indices) {
        for (j in i + 1 until nums.size) {
            val one = nums[i]
            val two = nums[j]
            if (one + two == target) {
                return intArrayOf(i, j)
            }
        }
    }
    return intArrayOf()
}

// (2,7,11,15)  9
fun twoSumUsingHashMap(nums: IntArray, target: Int): IntArray {

    val indexMap = HashMap<Int, Int>()

    for (i in nums.indices) {
        val requiredNum = (target - nums[i])
        if (indexMap.containsKey(requiredNum)) {
            return intArrayOf(indexMap[requiredNum]!!, i)
        }
        indexMap[nums[i]] = i
    }

    return intArrayOf()

}

fun twoSumX(nums: IntArray, target: Int): IntArray? {

    val numToIndex: MutableMap<Int, Int?> = HashMap()
    for (i in nums.indices) {
        if (numToIndex.containsKey(target - nums[i])) return intArrayOf(numToIndex[target - nums[i]]!!, i)
        numToIndex[nums[i]] = i
    }
    return intArrayOf()
}
///////////////////////////////////////////////////////////////////////////////


// Input: s = "abcabcbb"
fun lengthOfLongestSubstring(s: String): Int {

    if (s == "") {
        return 0
    }

    var maxLength = 0
    var start = 0
    var end = 0

    val set = HashSet<Char>()


    //abc        end = 3
    //          start = 3

//    while (end < s.length) {
//        if (set.add(s[end])) {
//            end++
//            maxLength = Math.max(maxLength, set.size)
//        } else {
//            set.remove(s[start])
//            start++
//        }
//    }

    var count = 0
    var max = 0
    for (i in 0 until s.length){
        if (set.add(s[i])){
            count++
            max = Math.max(max,set.size)
        }else{
          //  count--
            while (set.isNotEmpty()){
                set.remove(s[count])
                count--
            }

            count =0
        }
    }






    return max
}

// Input: s = "babad"
//Output: "bab"
////////////////////////////////////////////////////////////////////////////
//13. Roman to Integer
fun romanToInt(s: String): Int {

    val nums = IntArray(s.length)

    var sum = 0

    for (i in s.indices) {
        when (s[i]) {
            'M' -> nums[i] = 1000
            'D' -> nums[i] = 500
            'C' -> nums[i] = 100
            'L' -> nums[i] = 50
            'X' -> nums[i] = 10
            'V' -> nums[i] = 5
            'I' -> nums[i] = 1
        }
    }


    for (i in 0 until nums.size - 1) {
        if (nums[i] < nums[i + 1]) {
            sum -= nums[i]
        } else {
            sum += nums[i]
        }
    }



    return sum + nums[nums.size - 1]
}
///////////////////////////////////////////////////////////////////////////////////////////


// Input: strs = ["flower","flow","flight"]
//Output: "fl"
fun longestCommonPrefix(strs: Array<String>): String {

    var ref = strs[0]

    for (i in 1 until strs.size) {

        val str = strs[i]
        var j = 0

        while (j < ref.length && j < str.length && ref[j] == str[j]) {
            j++
        }

        ref = ref.substring(0, j)
        if (ref.isEmpty()) {
            return ""
        }

    }


    return ref
}

///////////////////////////////////////////////////////////////
//20. Valid Parentheses
fun isValid(s: String?): Boolean {
    if (s == null || s.isEmpty()) {
        return false
    }
    val stack = Stack<Char>()
    for (c in s.toCharArray()) {

        if (c == '(' || c == '[' || c == '{') {
            stack.push(c)
        } else if (c == ')' && !stack.empty() && stack.peek() == '(') {
            stack.pop()
        } else if (c == ']' && !stack.empty() && stack.peek() == '[') {
            stack.pop()
        } else if (c == '}' && !stack.empty() && stack.peek() == '{') {
            stack.pop()
        } else {
            return false
        }

    }

    return stack.empty()
}

////////////////////////////////////////////////////////////////////////////////////////////
fun strStr(haystack: String, needle: String): Int {

    if (needle.isEmpty()) {
        return 0
    }
    if (haystack == needle) {
        return 0
    }
    if (haystack.isEmpty()) {
        return -1
    }

    // haystack = "hello", needle = "ll"
    for (i in 0 until haystack.length - needle.length + 1) {

        if (haystack.substring(i, i + needle.length) == needle) {
            return i
        }

    }

    return -1

}


fun lengthOfLastWord(s: String): Int {

    var length = 0

    if (s == " " || s.isEmpty()) {
        return 0
    }

    val a = s.trim()

    for (i in a.indices) {

        if (a[i] == ' ') {
            length = 0
        } else {
            length++
        }

    }

    return length

}


fun addBinary(a: String, b: String): String {


    val sb = StringBuilder()
    var carry = 0
    var aLength = a.length - 1
    var bLength = b.length - 1

    while (aLength >= 0 || bLength >= 0) {

        var sum = 0

        if (aLength >= 0 && a[aLength] == '1') {
            sum++
        }

        if (bLength >= 0 && b[bLength] == '1') {
            sum++
        }

        sum += carry

        carry = if (sum >= 2) {
            1
        } else {
            0
        }

        sb.insert(0, sum % 2)

        aLength--
        bLength--
    }

    if (carry == 1) {
        sb.insert(0, 1)
    }


    return sb.toString()
}

// Input: s = "anagram", t = "nagaram"
// Input: s = "rat", t = "car"
fun isAnagram(s: String, t: String): Boolean {


    val arr = ArrayList<Boolean>()

    if (s.length != t.length) {
        return false
    }

    val first = s.toCharArray()
    val sec = t.toCharArray()

    Arrays.sort(first)
    Arrays.sort(sec)


    for (i in first.indices) {
        if (first[i] != sec[i]) {
            return false
        }
    }


//    if (s.length < t.length){
//        first = t
//        sec = s
//    }else{
//        first = s
//        sec = t
//    }
//
//
//    for (i in first.indices) {
//        val x = first[i]
//        for (j in sec.indices) {
//            if (x == sec[j]) {
//                arr.add(true)
//                break
//            }
//        }
//    }
//
//    println("${arr.size}   ${first.length}")
//
//    if (arr.size == first.length) {
//        return true
//    }


    return true
}

// Input: s = "egg", t = "add"
// true
fun isIsomorphic(s: String, t: String): Boolean {


    val firstStr = s.toCharArray()
    val secStr = t.toCharArray()

    var firstIndex = 0
    var secIndex = 0

    var firstIndexTwo = 0
    var secIndexTwo = 0

    for (i in firstStr.indices) {
        for (j in i + 1 until firstStr.size) {
            if (firstStr[i] == firstStr[j]) {
                firstIndex = i
                secIndex = j
            }
        }
    }

    for (i in secStr.indices) {
        for (j in i + 1 until secStr.size) {
            if (secStr[i] == secStr[j]) {
                firstIndexTwo = i
                secIndexTwo = j
            }
        }
    }

    if (firstIndex != firstIndexTwo || secIndex != secIndexTwo) {
        return false
    }



    return true
}

//9. Palindrome Number
fun isPalindrome(s: Int): Boolean {

    val sb = StringBuilder()
    for (x in s.toString()) {
        sb.append(x.toLowerCase())
    }
    println(sb.toString())

    val str = sb.toString()
    val x = 0 + str.length / 2

    for (i in 0 until x) {

        val y = (str.length - 1) - i
        if (str[i] != str[y]) {
            return false
        }

    }

    return true
}

/////////////////////////////////////////////////////////////
//121. Best Time to Buy and Sell Stock
//[7,1,5,3,6,4]
fun maxProfit(prices: IntArray): Int {
    if (prices.isEmpty()) {
        return 0
    }

    var minPrices = prices[0]
    var profit = 0

    for (i in 1 until prices.size) {

        if (prices[i] < minPrices) {
            minPrices = Math.min(minPrices, prices[i])
        }

        profit = Math.max(profit, prices[i] - minPrices)
    }

    return profit
}

//////////////////////////////////////////////////////////////////////
//155. Min Stack
class MinStack() {
    private val stack = Stack<Int>()
    private val minStack = Stack<Int>()

    fun push(x: Int) {
        stack.push(x)

        if (!minStack.isEmpty()) {
            if (x <= minStack.peek()) {
                minStack.push(x)
            }
        } else {
            minStack.push(x)
        }
    }

    fun pop() {
        val x = stack.pop()

        if (!minStack.isEmpty()) {
            if (x == minStack.peek()) {
                minStack.pop()
            }
        }

    }

    fun top(): Int {
        return stack.peek()
    }

    fun getMin(): Int {
        return minStack.peek()
    }

}


//26. Remove Duplicates from Sorted Array
fun removeDuplicates(nums: IntArray?): Int {
    if (nums == null || nums.isEmpty()) {
        return 0
    }
    var i = 0
    for (j in nums.indices) {
        if (nums[i] != nums[j]) {
            ++i
            nums[i] = nums[j]
        }
    }
    return i + 1
}
//////////////////////////////
///27. Remove Element
//[3,2,2,3]  , 3
//[2,2,_,_]

//Input: nums = [0,1,2,2,3,0,4,2], val = 2
//Output: 5, nums = [0,1,4,0,3,_,_,_]
fun removeElement(nums: IntArray, `val`: Int): Int {
    var i = 0

    for (x in nums.indices){

        if (nums[x] == `val`){
            continue
        }else{
            nums[i]=nums[x]
            i++
        }
    }

    return i
}

//35. Search Insert Position
//Input: nums = [1,3,5,6], target = 5
//Output: 2
fun searchInsert(nums: IntArray, target: Int): Int {

    for (i in nums.indices){
        if (nums[i] == target){
            return i
        }else{
            if (nums[i] + 1 == target){
                return i + 1
            }else if (nums[i] - 1 == target){
                return i
            }
        }
    }

    if (target > nums[nums.size -1]){
        return nums.size
    }

    return 0
}


///53. Maximum Subarray
fun maxSubArray(nums: IntArray): Int {
    var sum = 0
    var sum2 = Int.MIN_VALUE
    for (i in nums.indices){
        sum += nums[i]
        sum2 = Math.max(sum,sum2)
        if (sum < 0){
            sum = 0
        }
    }

    return sum2
}

/////////////////////////////////////////////////////////////
fun main() {

  //  val result = isPalindrome(121)

    // val result = twoSumUsingHashMap(intArrayOf(2,7,11,15),9)

   // println(searchInsert(intArrayOf(1,3,5,6),5))
  //  println(maxSubArray(intArrayOf(-2,1,-3,4,-1,2,1,-5,4)))
    println(lengthOfLongestSubstring("pwwkew"))
   // println(removeElement(intArrayOf(0,1,2,2,3,0,4,2),2))
}

