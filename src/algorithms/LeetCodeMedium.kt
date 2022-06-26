package algorithms

import java.util.*


//4. Median of Two Sorted Arrays
fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    val n: Int = nums1.size + nums2.size

    val one = findKth(nums1, 0, nums2, 0, (n + 1) / 2)
    val two = findKth(nums1, 0, nums2, 0, (n + 2) / 2)

    return (one.toDouble() + two.toDouble()) / 2.0
}

fun findKth(nums1: IntArray, s1: Int, nums2: IntArray, s2: Int, k: Int): Int {

    if (s1 >= nums1.size) {
        return nums2[s2 + k - 1]
    }
    if (s2 >= nums2.size) {
        return nums1[s1 + k - 1]
    }
    if (k == 1) {
        return Math.min(nums1[s1], nums2[s2])
    }


    val m1 = s1 + k / 2 - 1
    val m2 = s2 + k / 2 - 1
    var mid1 = 0
    var mid2 = 0
    mid1 = if (m1 >= nums1.size) {
        Int.MAX_VALUE
    } else {
        nums1[m1]
    }
    mid2 = if (m2 >= nums2.size) {
        Int.MAX_VALUE
    } else {
        nums2[m2]
    }
    return if (mid1 < mid2) {
        findKth(nums1, m1 + 1, nums2, s2, k - k / 2)
    } else {
        findKth(nums1, s1, nums2, m2 + 1, k - k / 2)
    }
}

fun findMedianSortedArraysx(nums1: IntArray, nums2: IntArray): Double {
    // Check if num1 is smaller than num2
    // If not, then we will swap num1 with num2
    if (nums1.size > nums2.size) {
        return findMedianSortedArraysx(nums2, nums1)
    }
    // Lengths of two arrays
    val m = nums1.size
    val n = nums2.size
    // Pointers for binary search
    var start = 0
    var end = m
    // Binary search starts from here
    while (start <= end) {
        // Partitions of both the array
        val partitionNums1 = (start + end) / 2
        val partitionNums2 = (m + n + 1) / 2 - partitionNums1
        // Edge cases
        // If there are no elements left on the left side after partition
        val maxLeftNums1 = if (partitionNums1 == 0) Int.MIN_VALUE else nums1[partitionNums1 - 1]
        // If there are no elements left on the right side after partition
        val minRightNums1 = if (partitionNums1 == m) Int.MAX_VALUE else nums1[partitionNums1]
        // Similarly for nums2
        val maxLeftNums2 = if (partitionNums2 == 0) Int.MIN_VALUE else nums2[partitionNums2 - 1]
        val minRightNums2 = if (partitionNums2 == n) Int.MAX_VALUE else nums2[partitionNums2]
        // Check if we have found the match
        if (maxLeftNums1 <= minRightNums2 && maxLeftNums2 <= minRightNums1) {
            // Check if the combined array is of even/odd length
            return if ((m + n) % 2 == 0) {
                (Math.max(maxLeftNums1, maxLeftNums2) + Math.min(minRightNums1, minRightNums2)) / 2.0
            } else {
                Math.max(maxLeftNums1, maxLeftNums2).toDouble()
            }
        } else if (maxLeftNums1 > minRightNums2) {
            end = partitionNums1 - 1
        } else {
            start = partitionNums1 + 1
        }
    }
    throw IllegalArgumentException()
}

//////////////////////////////////////////////////////////////////
//Input: s = "babad"
//Output: "bab"
//5. Longest Palindromic Substring
fun longestPalindrome(s: String?): String {
    if (s == null || s.isEmpty()) return ""
    var start = 0
    var end = 0

    for (i in s.indices) {
        // مع i كنقطة مركزية
        val len1 = expandAroundCenter(s, i, i)
        // خذ الفاصل بين i و i + 1 كنقطة مجوفة
        val len2 = expandAroundCenter(s, i, i + 1)


        val len = Math.max(len1, len2)
        if (len > end - start) {
            start = i - (len - 1) / 2
            end = i + len / 2
        }
    }
    return s.substring(start, end + 1)
}

fun expandAroundCenter(s: String, i: Int, j: Int): Int {
    var i = i
    var j = j
    while (i >= 0 && j < s.length && s[i] == s[j]) {
        i--
        j++
    }
    return j - i - 1
}

/////////////////////////////////////////////////////////////////////
fun myAtoi(s: String): Int {

    if (s.isEmpty()) {
        return 0
    }

    var result = 0
    var idx = 0
    var sign = 1

    while (idx < s.length && s[idx] == ' ') {
        ++idx
    }
    if (idx == s.length) {
        return 0
    }
    if (s[idx] == '+' || s[idx] == '-') {
        sign = if (s[idx] == '+') 1 else -1
        ++idx
    }
    while (idx < s.length) {
        if (!Character.isDigit(s[idx])) {
            break
        }
        val digit = s[idx] - '0'
        if ((Int.MAX_VALUE - digit) / 10 < result) {
            return if (sign == 1) Int.MAX_VALUE else Int.MIN_VALUE
        }
        result = result * 10 + digit
        ++idx
    }

    return sign * result
}

// 8. String to Integer (atoi)
fun myAtoiTwo(s: String): Int {
    var str = s
    if (str.isEmpty()) {
        return 0
    }
    // MAX and MIN values for integers
    val max = Int.MAX_VALUE
    val min = Int.MIN_VALUE
    // Trimmed string
    str = str.trim()
    // Counter
    var i = 0
    // Flag to indicate if the number is negative
    val isNegative: Boolean = str.startsWith("-")
    // Flag to indicate if the number is positive
    val isPositive: Boolean = str.startsWith("+")

    if (isNegative) {
        i++
    } else if (isPositive) {
        i++
    }
    // This will store the converted number
    var result = 0.0
    // Loop for each numeric character in the string iff numeric characters are leading
    // characters in the string
    while (i < str.length && str[i] >= '0' && str[i] <= '9') {
        result = result * 10 + (str[i] - '0')
        i++
    }
    // Give back the sign to the converted number
    result = if (isNegative) -result else result
    println(result)
    if (result < min) {
        return min
    }
    return if (result > max) {
        max
    } else result.toInt()


}

///////////////////////////////////////////////////////////////////////////////////////////////
//Input: num = 3
//Output: "III"
//Input: num = 58
//Output: "LVIII"
//12. Integer to Roman
fun intToRoman(num: Int): String {

    var number = num

    val values = intArrayOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
    val strs = arrayOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
    val sb = StringBuilder()

    for (i in values.indices) {
        while (number >= values[i]) {
            number -= values[i]
            sb.append(strs[i])
        }
    }
    return sb.toString()

}

fun intToRomanTwo(num: Int): String? {
    val M = arrayOf("", "M", "MM", "MMM")
    val C = arrayOf("", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM")
    val X = arrayOf("", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC")
    val I = arrayOf("", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX")
    return M[num / 1000] + C[num % 1000 / 100] + X[num % 100 / 10] + I[num % 10]
}

//////////////////////////////////////////////////////////////////////////////
//36. Valid Sudoku
fun isValidSudoku(board: Array<CharArray>): Boolean {

    for (i in 0..8) {

        val rows = HashSet<Char>()
        val columns = HashSet<Char>()
        val cube = HashSet<Char>()

        for (j in 0..8) {

            if (board[i][j] != '.' && !rows.add(board[i][j])) return false
            if (board[j][i] != '.' && !columns.add(board[j][i])) return false

            val rowIndex = 3 * (i / 3)
            val colIndex = 3 * (i % 3)
            if (board[rowIndex + j / 3][colIndex + j % 3] != '.' && !cube.add(board[rowIndex + j / 3][colIndex + j % 3])) return false
        }

    }

    return true
}

/////////////////////////////////////////////////////////////////////////////////////////////////
//767. Reorganize String
fun reorganizeString(s: String?): String {
    if (s == null || s.isEmpty()) {
        return String()
    }
    val hm: MutableMap<Char, Int> = HashMap()
    var maxChar = s[0]
    for (c in s.toCharArray()) {
        hm[c] = hm.getOrDefault(c, 0) + 1
        if (hm[c]!! > hm[maxChar]!!) {
            maxChar = c
        }
    }
    if (hm[maxChar]!! > (s.length + 1) / 2) {
        return ""
    }
    var idx = 0
    val result = CharArray(s.length)
    while (idx < s.length && hm[maxChar]!! > 0) {
        result[idx] = maxChar
        idx += 2
        hm[maxChar] = hm[maxChar]!! - 1
    }
    for (c in hm.keys) {
        while (hm[c]!! > 0) {
            if (idx >= s.length) {
                idx = 1
            }
            result[idx] = c
            idx += 2
            hm[c] = hm[c]!! - 1
        }
    }
    return String(result)
}

//////////////////////////////////////////////////////////
//300. Longest Increasing Subsequence
fun lengthOfLIS(nums: IntArray): Int {
    val dp = IntArray(nums.size)
    var len = 0

    for (num in nums) {
        val idx = binarySearch(dp, 0, len, num)
        dp[idx] = num
        if (idx == len) {
            ++len
        }
    }

    return len
}

fun binarySearch(nums: IntArray, low: Int, high: Int, target: Int): Int {
    var low = low
    var high = high
    while (low < high) {
        val mid = low + (high - low) / 2
        if (nums[mid] == target) {
            return mid
        } else if (nums[mid] < target) {
            low = mid + 1
        } else {
            high = mid
        }
    }
    return low
}

//////////////////////////////////////////////////////////////////////////////////////////////
//289. Game of Life
fun gameOfLife(board: Array<IntArray>): Unit {
    val rows = board.size
    val cols: Int = board[0].size

    for (i in 0 until rows) {
        for (j in 0 until cols) {
            var lives = 0
            // Scan 3x3 region including (i, j)
            for (x in Math.max(0, i - 1) until Math.min(rows, i + 2)) {
                for (y in Math.max(0, j - 1) until Math.min(cols, j + 2)) {
                    lives += board[x][y] and 1
                }
            }
            // In the beginning, every 2nd bit is 0;
            // So we only need to care about when will the 2nd bit become 1.
            if (lives == 3 || lives - board[i][j] == 3) {
                board[i][j] = board[i][j] or 2
            }
        }
    }
    for (i in 0 until rows) {
        for (j in 0 until cols) {
            // Get the next state
            board[i][j] = board[i][j] shr 1
        }
    }
}

//////////////////////////////////////////////////////////////////////////////
//64. Minimum Path Sum
fun minPathSum(grid: Array<IntArray>): Int {
    val dp = Array(grid.size) { IntArray(grid[0].size) }

    for (row in grid.indices) {
        for (col in grid[row].indices) {

            if (row == 0 && col == 0) {
                dp[row][col] = grid[row][col]
            } else if (row == 0) {
                dp[row][col] = grid[row][col] + dp[row][col - 1]
            } else if (col == 0) {
                dp[row][col] = grid[row][col] + dp[row - 1][col]
            } else {
                dp[row][col] = grid[row][col] + Math.min(dp[row - 1][col], dp[row][col - 1])
            }

        }
    }

    return dp[grid.size - 1][grid[0].size - 1]
}

/////////////////////////////////////////////////////////////////////////////////////
//295. Find Median from Data Stream
internal class MedianFinder {
    var p1: PriorityQueue<Int> = PriorityQueue<Int>(Collections.reverseOrder())
    var p2: PriorityQueue<Int> = PriorityQueue()
    fun addNum(num: Int) {
        if (p1.isEmpty() || p1.peek() > num) {
            p1.offer(num)
        } else {
            p2.offer(num)
        }
        if (p1.size > p2.size + 1) {
            p2.offer(p1.poll())
        } else if (p2.size > p1.size + 1) {
            p1.offer(p2.poll())
        }
    }

    fun findMedian(): Double {
        return if (p1.size == p2.size) {
            (p1.peek() + p2.peek()).toDouble() / 2.0
        } else if (p1.size > p2.size) {
            p1.peek().toDouble()
        } else {
            p2.peek().toDouble()
        }
    }

}

///////////////////////////////////////////////////////////////////////////////////////////////
//273. Integer to English Words
var v1: Array<String> = arrayOf<String>(
    "",
    "One",
    "Two",
    "Three",
    "Four",
    "Five",
    "Six",
    "Seven",
    "Eight",
    "Nine",
    "Ten",
    "Eleven",
    "Twelve",
    "Thirteen",
    "Fourteen",
    "Fifteen",
    "Sixteen",
    "Seventeen",
    "Eighteen",
    "Nineteen"
)
var v2 = arrayOf("", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety")
fun numberToWords(num: Int): String? {
    var num = num
    if (num < 0) {
        return ""
    }
    if (num == 0) {
        return "Zero"
    }
    var rst = ""
    for (i in 0..3) {
        val partial = num - num / 1000 * 1000
        if (partial > 0) {
            rst = helper(partial, i) + " " + rst
        }
        num /= 1000
    }
    while (rst[rst.length - 1] == ' ') {
        rst = rst.substring(0, rst.length - 1)
    }
    return rst
}

fun helper(num: Int, i: Int): String {
    var num = num
    var str = ""
    if (num >= 100) {
        val hund = num / 100
        str = v1!![hund].toString() + " Hundred "
        num = num % 100
    }
    if (num < 20) {
        str += v1!![num].toString() + " "
    } else {
        val numTens = num / 10
        val numDigit = num % 10
        str += v2[numTens] + " "
        str += v1!![numDigit].toString() + " "
    }
    while (str[str.length - 1] == ' ') {
        str = str.substring(0, str.length - 1)
    }
    when (i) {
        1 -> str += " Thousand"
        2 -> str += " Million"
        3 -> str += " Billion"
    }
    return str
}

////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
//46. Permutations
fun permute(nums: IntArray): List<List<Int>> {
    if (nums.isEmpty()) {
        return Collections.emptyList()
    }
    val result: MutableList<List<Int>> = ArrayList()
    helper(nums, result, ArrayList())
    return result
}

fun helper(nums: IntArray, result: MutableList<List<Int>>, temp: MutableList<Int>) {
    if (temp.size == nums.size) {
        result.add(ArrayList(temp))
        return
    }
    for (i in nums.indices) {
        if (temp.contains(nums[i])) {
            continue
        }
        temp.add(nums[i])
        helper(nums, result, temp)
        temp.removeAt(temp.size - 1)
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////
//56. Merge Intervals
fun merge(intervals: Array<IntArray>): Array<IntArray> {
    if (intervals.size <= 1) {
        return intervals
    }
    Arrays.sort(intervals) { i1: IntArray, i2: IntArray -> Integer.compare(i1[0], i2[0]) }
    val result: MutableList<IntArray> = ArrayList()
    var interval = intervals[0]
    for (i in 1 until intervals.size) {
        if (interval[1] >= intervals[i][0]) {
            interval[1] = Math.max(interval[1], intervals[i][1])
        } else {
            result.add(interval)
            interval = intervals[i]
        }
    }
    result.add(interval)
    return result.toTypedArray()
}
/////////////////////////////////////////////////////////////////////////////////////////////

//17
fun letterCombinations(digits: String): List<String> {

    if (digits.isEmpty()) {
        return emptyList()
    }
    val result = ArrayList<String>()
    val map = HashMap<Char, String>()

    map['1'] = ""
    map['2'] = "abc"
    map['3'] = "def"
    map['4'] = "ghi"
    map['5'] = "jkl"
    map['6'] = "mno"
    map['7'] = "pqrs"
    map['8'] = "tuv"
    map['9'] = "wxyz"


    helper(digits, 0, StringBuilder(), result, map)

//    for (c in digits){
//        list.add(map[c]!!)
//    }
//
//    if (list.size == 1){
//        for (first in list[0]){
//            result.add(first + "")
//        }
//
//        return result
//    }
//
//    val ref = list[0]
//    for (i in 1 until list.size){
//        for (first in ref){
//            for (sec in list[i]){
//                val text = "$first$sec"
//                sb.append(text)
//                result.add(text)
//            }
//        }
//    }


    return result
}

private fun helper(digits: String, idx: Int, sb: StringBuilder, result: ArrayList<String>, keypad: Map<Char, String>) {

    if (sb.length == digits.length) {
        result.add(sb.toString())
        return
    }

    val letters = keypad[digits[idx]]
    for (i in 0 until letters!!.length) {
        sb.append(letters[i])
        helper(digits, idx + 1, sb, result, keypad)
        sb.deleteCharAt(sb.length - 1)
    }
}

//class SnapshotArray(length: Int) {
//
//  //  private val lists: Array<List<IntArray>>
//    val array = IntArray(length){0}
//    var snap_Id = 0
//
//    init {
//      //val x = arrayof
//    }
//
//
//
//    fun set(index: Int, `val`: Int) {
//        array[index] = `val`
//    }
//
//    fun snap(): Int {
//      return  snap_Id++
//      //  return snap_Id - 1
//    }
//
//    fun get(index: Int, snap_id: Int): Int {
//
//        if (index == snap_id){
//            return array.get(index)
//        }
//
//        return array.get(snap_id)
//    }
//
//
//}

class SnapshotArray(length: Int) {

    var snapshotArray: Array<TreeMap<Int, Int>?>
    var snap_id: Int

    init {
        snapshotArray = arrayOfNulls(length)
        snap_id = 0
        for (i in 0 until length) {
            snapshotArray[i] = TreeMap()
            snapshotArray[i]!![0] = 0
        }
    }

    fun set(index: Int, `val`: Int) {
        snapshotArray[index]!![snap_id] = `val`
    }

    fun snap(): Int {
        return snap_id++
    }

    fun get(index: Int, snap_id: Int): Int {
        return snapshotArray[index]!!.floorEntry(snap_id).value
    }

}

fun subarraySum(nums: IntArray, k: Int): Int {
    var count = 0
    var sum = 0
    for (i in nums.indices) {
        sum = nums[i]
        if (sum == k) {
            count ++
        }
        for (j in 1 + i until nums.size) {
            sum += nums[j]
            if (sum == k) {
                count++
            }
        }
    }


    return count
}


//2034. Stock Price Fluctuation
 class StockPrice {

    private val timestampToPrice = TreeMap<Int, Int>()
    private val pricesCount = TreeMap<Int, Int>()

    fun update(timestamp: Int, price: Int) {
        if (timestampToPrice.containsKey(timestamp)) {
            val prevPrice = timestampToPrice[timestamp]!!
            pricesCount.merge(
                prevPrice, -1
            ) { a: Int?, b: Int? -> Integer.sum(a!!, b!!) }
            if (pricesCount[prevPrice] == 0) pricesCount.remove(prevPrice)
        }

        timestampToPrice[timestamp] = price
        pricesCount.merge(price, 1) { a: Int?, b: Int? ->
            Integer.sum(
                a!!,
                b!!
            )
        }
    }

    fun current(): Int {
        return timestampToPrice.lastEntry().value
    }

    fun maximum(): Int {
        return pricesCount.lastKey()
    }

    fun minimum(): Int {
        return pricesCount.firstKey()
    }

}

fun main() {

    ///   println(intToRoman(3))
    //  println(letterCombinations("23"))
    println(subarraySum(intArrayOf(1, 1, 1), 2))
}