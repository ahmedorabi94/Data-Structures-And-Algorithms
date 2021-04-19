package dataStructure.linkedlist.hackerrankSolutions


data class SinglyLinkedListNode(var data: Int, var next: SinglyLinkedListNode? = null)


// Insert a node at a specific position in a linked list
fun insertNodeAtPosition(llist: SinglyLinkedListNode?, data: Int, position: Int): SinglyLinkedListNode? {

    val node = SinglyLinkedListNode(data)

    var head = llist


    if (position == 0) {
        node.next = head
        head = node
    } else {
        var i = llist
        // go to the previous node to add after it
        for (x in 0 until position - 1) {
            i = i!!.next
        }
        node.next = i!!.next
        i.next = node

    }

    return llist

}


fun deleteNode(llist: SinglyLinkedListNode?, position: Int): SinglyLinkedListNode? {

    if (llist == null) {
        return null
    } else if (position == 0) {
        return llist.next
    } else {

        var i = llist
        // go to the previous node to add after it
        for (x in 0 until position - 1) {
            i = i!!.next
        }
        // i want to remove j
        val j = i!!.next

        i.next = j!!.next

        return llist
    }
}

fun reversePrint(llist: SinglyLinkedListNode?): Unit {

    if (llist == null) {
        return
    }
    reversePrint(llist.next)
    println(llist.data)

}


fun getNode(llist: SinglyLinkedListNode?, positionFromTail: Int): Int {


    var nodes = 0
    var i = llist
    var head = llist

    while (i != null) {
        i = i.next
        nodes++
    }

    nodes = nodes - positionFromTail

    while (--nodes > 0) {
        head = head!!.next
    }


    return head!!.data

}
