package sorting

fun quickSort(arr: IntArray, left: Int, right: Int) {
    var i = left
    var j = right
    var temp: Int
    val pivot = arr[(left + right) / 2]

    while (i <= j) {
        while (arr[i] < pivot) {
            i++
        }
        while (arr[j] > pivot) {
            j--
        }

        if (i <= j) {
            temp = arr[i]
            arr[i] = arr[j]
            arr[j] = temp
            i++
            j--
        }
    }

    if (left < j) quickSort(arr, left, j)
    if (i < right) quickSort(arr, i, right)
}

fun display(arr: IntArray) {
    for (i in arr.indices) {
        print(arr[i])
        print(" ")
    }
}

// i took element (Pivot) and try to put it in its right place in the array
// element before pivot smaller than it and element after pivot bigger than it
// try this again on the right array and left array
// O(nlogn)
// Pros : doesn`t need space(memory) to make sort like mergeSort
// Cons : it has problem if the array it is already sorted it will be O(n^2)
fun main() {
    val data = intArrayOf(5, 10, 1, 9, 4, 8, 3, 6, 2, 7)
    println("Unsorted array: ")
    display(data)
    println("")
    quickSort(data, 0, data.size - 1)
    println("Sorted array: ")
    display(data)
}