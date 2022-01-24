package dataStructure.tree

data class TreeNode(var value: Char, var left: TreeNode? = null, var right: TreeNode? = null) {


    fun isLeaf(): Boolean {
        return this.left == null && this.right == null
    }


    // preorder
    fun print(){
        // preorder print parent first then all elements on left first then go right and repeat again
        println(this.value)

        if (this.left != null){
            this.left!!.print()
        }


        // inOrder     println(this.value)   print  all elements on left first from bottom then parent then go right then repeat again

        if (this.right != null){
            this.right!!.print()
        }


        // postOrder     println(this.value)   print  all elements on left first from bottom then go right then parent then repeat again

    }

    fun preorderTraversal(root: TreeNode?): List<Char> {

        val arr = ArrayList<Char>()


        traverse(root,arr)

        return arr

    }

    fun traverse(root : TreeNode? , arr : ArrayList<Char>){

        if(root == null){
            return
        }

        arr.add(root.value)

        if (root.left!= null){
            traverse(root.left!!,arr)
        }

        if (root.right!= null){
            traverse(root.right!!,arr)
        }

    }



}