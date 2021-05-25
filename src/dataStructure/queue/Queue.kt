package dataStructure.queue

class Queue {

    private var first = -1
    private var last = -1

    private val arr = IntArray(10)


    fun isEmpty(): Boolean {
        return first == -1 && last == -1
    }

    fun isFull(): Boolean {
        return (last == arr.size - 1 && first == -1) || (first == last && first != -1 )
    }

    fun enqueue(v: Int) {
        if (this.isFull()) {
            println("Queue is Full")
        } else {

            last = (last + 1) % arr.size

            // last++
            arr[last] = v
        }

    }


    fun dequeue(): Int {

        return if (this.isEmpty()) {
            -1
        } else {
            first = (first + 1) % arr.size
            val temp =   arr[first]

            if (first == last){
                first = -1
                last = -1
            }

            //  first++
            temp
        }


    }

}