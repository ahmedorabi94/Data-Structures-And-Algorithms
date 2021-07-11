package sorting

// It is similar to the handpicking where we take the smallest element and put it in the first position
// and the second smallest in the second position and so on.
// The time complexity of selection sort is (O(n2)).
fun IntArray.selectionSort() {

    if (this.size < 2) {
        return
    }
    for (i in this.indices) {
        var minIndex = i

        for (j in (i + 1) until this.size) {
            if (this[minIndex] > this[j]) {
                minIndex = j
            }
        }

        // swap
        val temp = this[minIndex]
        this[minIndex] = this[i]
        this[i] = temp

    }


}


fun main() {
    val data = intArrayOf(5, 10, 1, 6, 2, 9, 3, 8, 7, 4)

    println("Before sorting: ")
    for (element in data) {
        println(element)
    }
    data.selectionSort()

    println()
    println("After sorting: ")
    for (element in data) {
        println(element)
    }
}