package dataStructure

class LinkedList {

    private var head: ListNode? = null


    fun print() {

        // refer to first node
        var i = head

        while (i != null) {
            println(i.value)
            i = i.next
        }

    }

    fun add(v: Char, index: Int) {

        val node = ListNode(v)

        if (index == 0) {
            node.next = head
            head = node
        } else {
            var i = head
            // go to the previous node to add after it
            for (x in 0 until index - 1) {
                i = i!!.next
            }
            node.next = i!!.next
            i.next = node

        }


    }

    fun remove(index: Int) {

        if (index == 0) {
            head = head!!.next
        } else {
            var i = head
            // go to the previous node to add after it
            for (x in 0 until index - 1) {
                i = i!!.next
            }

            // i want to remove j
            val j = i!!.next

            i.next = j!!.next
        }


    }

}