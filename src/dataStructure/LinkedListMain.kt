package dataStructure

fun main() {

    val linkedList = LinkedList()


    linkedList.add('a',0)
    linkedList.add('b',1)
    linkedList.add('c',2) // a,b,c
    linkedList.add('h',1) // a,h,bc
    linkedList.add('x',0) // x a h b c
    linkedList.remove(1)
    linkedList.print()

}

fun maximizeNumber(N: Int, K: Int) {
    // Convert it into N to string
    val s = Integer.toString(N)
    val L = s.length

    // Stores the maximum value of N
    // after inserting K
    var result = ""
    var i = 0

    // Iterate till all digits that
    // are not less than K
    while (i < L && K <= s[i].toInt() - '0'.toInt()) {

        // Add the current digit to
        // the string result
        result += s[i]
        ++i
    }

    // Add digit 'K' to result
    result += (K + '0'.toInt()).toChar()

    // Iterate through all remaining
    // characters
    while (i < L) {

        // Add current digit to result
        result += s[i]
        ++i
    }

    // Print the maximum number formed
    println(result)
}