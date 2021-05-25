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

// java

/*

// Print the Elements of a Linked List
 static void printLinkedList(SinglyLinkedListNode head) {

        SinglyLinkedListNode i = head;
        while(i !=null){
            System.out.println(i.data);
            i = i.next;
        }


    }

//Insert a Node at the Tail of a Linked List
static SinglyLinkedListNode insertNodeAtTail(SinglyLinkedListNode head, int data) {


         SinglyLinkedListNode node = new SinglyLinkedListNode(data);

         if(head == null){
             node.next = head;
             head = node;
         }else{
            SinglyLinkedListNode i = head;

            while(i.next !=null){

              i=i.next;
            }
             node.next = i.next;
            i.next = node;

         }

       return head;
    }

  //Insert a node at the head of a linked list
     static SinglyLinkedListNode insertNodeAtHead(SinglyLinkedListNode llist, int data) {

     SinglyLinkedListNode node = new SinglyLinkedListNode(data);

        node.next = llist;
        llist = node;

       return llist;

    }

     static SinglyLinkedListNode reverse(SinglyLinkedListNode head) {

    if (head == null || head.next == null) return head;
    SinglyLinkedListNode remaing = reverse(head.next);
    head.next.next = head;
    head.next = null;
    return remaing;


    }

    static boolean compareLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {

     SinglyLinkedListNode p1= head1;
     SinglyLinkedListNode p2= head2;

     while(p1 != null || p2 != null){

         if(p1 == null || p2 ==null){
             return false;
         }

         int data1= p1.data;
         int data2 = p2.data;

         if(data1 != data2){
             return false;
         }


         p1 = p1.next;
         p2 = p2.next;

     }

    return true;
}


   static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
    SinglyLinkedListNode head = new SinglyLinkedListNode(0);
    SinglyLinkedListNode p = head;
    SinglyLinkedListNode p1 = head1;
    SinglyLinkedListNode p2 = head2;

        while(p1 != null && p2!= null){
            int val1 = p1.data;
            int val2 = p2.data;

            if(val1 < val2){
                p.next = p1;
                p1=p1.next;
            }else {
                p.next = p2;
                p2=p2.next;
            }

            p =p.next;

        }

        if(p1 !=null){
            p.next = p1;
        }
      if(p2 !=null){
            p.next = p2;
        }

    return head.next;
    }



public static Node removeDuplicates(Node head) {
      if (head == null || head.next == null){
        return head;
    }
    if (head.data == head.next.data){
        head.next = head.next.next;
        removeDuplicates(head);
    }else{
        removeDuplicates(head.next);
    }
    return head;
    }



* */

