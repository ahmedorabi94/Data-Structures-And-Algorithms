package sorting


fun main() {
    val size = 10
    val data = IntArray(size)
    for (i in 0 until size) {
        data[i] = (Math.random() * 100).toInt()
    }
    println("Before sorting: ")
    for (i in 0 until size) {
        print(data[i])
        print(" ")
    }
    for (i in 0 until size) {
        for (j in i downTo 1) {
            if (data[j - 1] > data[j]) {
                val temp = data[j]
                data[j] = data[j - 1]
                data[j - 1] = temp
            }
        }
    }
    println()
    println("After sorting: ")
    for (i in 0 until size) {
        print(data[i])
        print(" ")
    }
}
