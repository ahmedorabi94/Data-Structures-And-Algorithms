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