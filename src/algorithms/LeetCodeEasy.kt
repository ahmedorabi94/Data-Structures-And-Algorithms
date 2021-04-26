package algorithms

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

