package the_book.ch_1.Arrays_and_Strings

import java.util.*


fun main() {
    println("-----------")

   // println( containsDuplicate(intArrayOf(1,2,3,1)))
    println( checkInclusion("ab","eidboaoo"))
}


fun containsDuplicate(nums: IntArray): Boolean {

    val set = HashSet<Int>()

    for (i in nums.indices) {
       if (!set.add(nums[i])){
           return true
       }
    }

    return false

}

fun isAnagram(s: String, t: String): Boolean {

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

    return true
}

fun checkInclusion(s1: String, s2: String): Boolean {

    val first = s1.toCharArray()
    val sec = s2.toCharArray()

    Arrays.sort(first)
    Arrays.sort(sec)

    println(first)
    println(sec)

    for (i in first.indices) {
        if (first[i] != sec[i]) {
            return false
        }
    }

    return true
}

//443
fun compress(chars: CharArray): Int {


    return 0
}