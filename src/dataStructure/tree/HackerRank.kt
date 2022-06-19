package dataStructure.tree

import java.util.*


data class TreeNode2(var data: Int, var left: TreeNode2? = null, var right: TreeNode2? = null)


fun insert(root: TreeNode2?, data: Int): TreeNode2 {

    // it reaches the final node
    if (root == null) {
        return TreeNode2(data)
    }

    val curr: TreeNode2

    if (data >= root.data) {
        // go right
        curr = insert(root.right!!, data)
        root.right = curr
    } else {
        // go left
        curr = insert(root.left!!, data)
        root.left = curr
    }

    return root
}

fun height(root: TreeNode2?): Int {
    return if (root == null) {
        -1
    } else {
        val leftIndex = height(root.left)
        val rightIndex = height(root.right)
        if (leftIndex > rightIndex) {
            leftIndex + 1
        } else {
            rightIndex + 1
        }
    }
}

fun topView(root: TreeNode2?) {
    class QueueObj(node: TreeNode2, hd: Int) {
        var node: TreeNode2
        var hd: Int

        init {
            this.node = node
            this.hd = hd
        }
    }

    val q: Queue<QueueObj> = LinkedList<QueueObj>()
    val topViewMap: MutableMap<Int, TreeNode2?> = TreeMap<Int, TreeNode2?>()
    if (root == null) {
        return
    } else {
        q.add(QueueObj(root, 0))
    }

    // System.out.print(
    //     "The top view of the tree is : ");
    while (!q.isEmpty()) {
        val tmpNode: QueueObj = q.poll()
        if (!topViewMap.containsKey(tmpNode.hd)) {
            topViewMap[tmpNode.hd] = tmpNode.node
        }
        if (tmpNode.node.left != null) {
            q.add(QueueObj(tmpNode.node.left!!,
                    tmpNode.hd - 1))
        }
        if (tmpNode.node.right != null) {
            q.add(QueueObj(tmpNode.node.right!!,
                    tmpNode.hd + 1))
        }
    }
    for ((_, value) in topViewMap) {
        print(value?.data.toString() + " ")
    }


}

fun levelOrder(root: TreeNode2) {
    val queue: Queue<TreeNode2> = LinkedList<TreeNode2>()
    queue.add(root)
    while (!queue.isEmpty()) {
        val tempNode: TreeNode2 = queue.poll()
        System.out.printf("%d ", tempNode.data)
        if (tempNode.left != null) queue.add(tempNode.left)
        if (tempNode.right != null) queue.add(tempNode.right)
    }
}

