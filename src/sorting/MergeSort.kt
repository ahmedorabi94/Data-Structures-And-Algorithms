package sorting

fun mergeSort(arr: IntArray, low: Int, high: Int) {
    val size = arr.size
    if (low < high) {
        val middle = (low + high) / 2
        mergeSort(arr, low, middle)
        mergeSort(arr, middle + 1, high)
        merge(arr, low, middle, high)
    }
}

fun merge(arr: IntArray, low: Int, middle: Int, high: Int) {
    val size = arr.size
    val temp = IntArray(size)
    for (i in low..high) {
        temp[i] = arr[i]
    }
    var i = low
    var j = middle + 1
    var k = low
    while (i <= middle && j <= high) {
        if (temp[i] <= temp[j]) {
            arr[k] = temp[i]
            ++i
        } else {
            arr[k] = temp[j]
            ++j
        }
        ++k
    }
    while (i <= middle) {
        arr[k] = temp[i]
        ++k
        ++i
    }
}

fun main() {
    val size = 10
    val data = intArrayOf(5, 10, 1, 6, 2, 9, 3, 8, 7, 4)
    val low = 0
    val high = 9
    println("Before sorting: ")
    for (i in 0 until size) {
        println(data[i])
    }
    mergeSort(data, low, high)
    println()
    println("After sorting: ")
    for (i in 0 until size) {
        println(data[i])
    }
}