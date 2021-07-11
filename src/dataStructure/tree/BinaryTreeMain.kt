package dataStructure.tree


fun main() {

    val tree = BinaryTree()

    tree.root = TreeNode('a')
    tree.root!!.left = TreeNode('b')
    tree.root!!.right = TreeNode('c')
    tree.root!!.left!!.left = TreeNode('d')
    tree.root!!.left!!.right = TreeNode('f')

    println("Height of tree is : " +
            tree.length(tree.root))


    tree.print()


}
