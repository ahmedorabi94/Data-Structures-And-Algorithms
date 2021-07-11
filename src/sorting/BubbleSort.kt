package sorting


fun <T : Comparable<T>> ArrayList<T>.bubbleSort(showPasses: Boolean = false) {
    // 1
    if (this.size < 2) return
    // 2
    for (end in (1 until this.size).reversed()) {
        var swapped = false
        // 3
        for (current in 0 until end) {
            if (this[current] > this[current + 1]) {
                // 4
                this.swapAt(current, current + 1)
                swapped = true
            }
        }
        // 5
        if(showPasses) println(this)
        // 6
        if (!swapped) return
    }
}

fun <T> ArrayList<T>.swapAt(first: Int, second: Int) {
    val aux = this[first]
    this[first] = this[second]
    this[second] = aux
}


fun bubbleSort(arr: IntArray) {
    val size = arr.size
    for (pass in 1 until size) {
        for (i in 0 until size - pass) {
            if (arr[i] > arr[i + 1]) {
                val temp = arr[i]
                arr[i] = arr[i + 1]
                arr[i + 1] = temp
            }
        }
    }
}

fun main() {

    val list = arrayListOf(9, 4, 10, 3,36,12,19,2,7)
    println("Original: $list")
    list.bubbleSort(true)
    println("Bubble sorted: $list")


//    val size = 10
//    val data = IntArray(size)
//    for (i in 0 until size) {
//        data[i] = (Math.random() * 100).toInt()
//    }
//    println("Before sorting: ")
//    for (i in 0 until size) {
//        println(data[i])
//    }
//    /*
//      for(int i = 0; i < size; ++i) {
//         for(int j = i; j > 0; --j) {
//            if (data[j-1] > data[j]) {
//               int temp = data[j];
//               data[j] = data[j-1];
//               data[j-1] = temp;
//            }
//         }
//      }
//      */
//    bubbleSort(data)
//    println()
//    println("After sorting: ")
//    for (i in 0 until size) {
//        println(data[i])
//    }
}