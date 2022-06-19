package dataStructure.linkedlist.hackerrankSolutions.leetcodeSol

import java.util.*


data class ListNode(var `val`: Int, var next: ListNode? = null)


fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {

    val head = ListNode(0)
    var dummy = head

    var p1 = l1
    var p2 = l2

    var carry = 0

    while (p1 != null || p2 != null) {
        val x = p1?.`val` ?: 0
        val y = p2?.`val` ?: 0

        val sum = x + y + carry
        carry = sum / 10
        println("carry $carry")

        dummy.next = ListNode(sum % 10)
        dummy = dummy.next!!

        if (p1 != null) {
            p1 = p1.next
        }
        if (p2 != null) {
            p2 = p2.next
        }

    }

    if (carry != 0) {
        dummy.next = ListNode(carry)
    }

    return head.next
}
/////////////////////////////////////////////////////////////////////
fun mergeKLists(lists: Array<ListNode?>?): ListNode? {
    if (lists == null || lists.isEmpty()) {
        return null
    }
    val minHeap = PriorityQueue(Comparator { a: ListNode?, b: ListNode? -> a!!.`val` - b!!.`val` })
    val dummy = ListNode(-1)
    var curr: ListNode? = dummy
    for (l in lists) {
        if (l != null) {
            minHeap.add(l)
        }
    }
    while (!minHeap.isEmpty()) {
        curr!!.next = minHeap.poll()
        curr = curr.next
        if (curr!!.next != null) {
            minHeap.add(curr.next)
        }
    }
    return dummy.next
}
/////////////////////////////////////////////////////////////////////////////
//61. Rotate List
fun rotateRight(head: ListNode?, k: Int): ListNode? {
    var head = head
    var k = k

    if (head?.next == null) {
        return head
    }

    var runner = head
    var len = 1

    while (runner!!.next != null) {
        runner = runner.next
        len++
    }

    runner.next = head
    k = k % len

    for (i in 0 until len - k) {
        runner = runner!!.next
    }
    head = runner!!.next
    runner.next = null
    return head
}
///////////////////////////////////////////////////////////////////////////////////
//146. LRU Cache
class LRUCache(capacity: Int) {
    class DLLNode {
        var key: Int
        var value: Int
        var next: DLLNode?
        var prev: DLLNode?

        constructor() {
            key = -1
            value = -1
            next = null
            prev = null
        }

        constructor(k: Int, v: Int) {
            key = k
            value = v
            next = null
            prev = null
        }
    }

    private val head: DLLNode
    private val tail: DLLNode
    private val hm: MutableMap<Int, DLLNode?>
    private val cap: Int = capacity
    private var size: Int = 0
    operator fun get(key: Int): Int {
        if (!hm.containsKey(key)) {
            return -1
        }
        val node = hm[key]
        moveToHead(node)
        return node!!.value
    }

    fun put(key: Int, value: Int) {
        if (hm.containsKey(key)) {
            val node = hm[key]
            node!!.value = value
            hm[key] = node
            moveToHead(node)
            return
        }
        val node = DLLNode(key, value)
        hm[key] = node
        moveToHead(node)
        ++size
        if (size > cap) {
            remove(tail.prev)
            --size
        }
    }

    private fun remove(node: DLLNode?) {
        node!!.prev!!.next = node.next
        node.next!!.prev = node.prev
        hm.remove(node.key)
    }

    private fun moveToHead(node: DLLNode?) {
        if (node!!.prev != null) {
            node.prev!!.next = node.next
        }
        if (node.next != null) {
            node.next!!.prev = node.prev
        }
        node.next = head.next
        node.prev = head
        head.next!!.prev = node
        head.next = node
    }

    init {
        hm = HashMap()
        head = DLLNode()
        tail = DLLNode()
        head.next = tail
        tail.prev = head
    }
}