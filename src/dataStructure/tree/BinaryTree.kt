package dataStructure.tree

class BinaryTree {

    var root: TreeNode? = null


    fun isEmpty(): Boolean {
        return root == null
    }


    fun length(node: TreeNode?): Int {

        return if (node == null) {
            0
        } else {
            val leftIndex = length(node.left)
            val rightIndex = length(node.right)

            if (leftIndex > rightIndex) {
                leftIndex + 1
            } else {
                rightIndex + 1
            }

        }
    }

    fun print() {
        this.root!!.print()
    }


}