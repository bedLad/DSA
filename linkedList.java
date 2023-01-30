import java.util.LinkedList;
  
public class LL {
  
  public static class Node {
    int data;
    Node next;
      
    public Node(int val) {
      this.data = val;
      this.next = null;
    }
  }
  
  public class DlNode {
    int data;
    DlNode next;
    DlNode prev;
    
    public DlNode(int val) {
      this.data = val;
      this.next = null;
      this.prev = null;
    }
  }
  
  public static Node head;
  public static Node tail;
  public static int size;
  
  public void addFirst(int data) {
    Node newNode = new Node(data);
    if(head == null) {
      head = newNode;
      tail = newNode;
      ++size;
      return;
    }
    newNode.next = head;
    head = newNode;
    ++size;
    return;
  }
  public void addLast(int data) {
    Node newNode = new Node(data);
    if(head == null) {
      head = newNode;
      tail = newNode;
      return;
    }
    tail.next = newNode;
    tail = newNode;
    ++size;
    return;
  }
  public void Add(int index, int data) {
    Node temp = head;
    Node newNode = new Node(data);
    int i=0;
    if(temp == null) {
      System.out.print("null");
      return;
    }
    while(i != index-1) {
      temp = temp.next;
      ++i;
    }
    newNode.next = temp.next;
    temp.next = newNode;
    ++size;
    return;
  }
  public void removeFirst() {
    if(head == null) {
      System.out.print("null");
      return;
    }
    Node temp = head.next;
    head.next = null;
    head = temp;
    --size;
    return;
  }
  public void removeLast() {
    if(tail == null) {
      System.out.print("null");
      return;
    }
    Node prev=head;
    for(int i=0; i<size-2; ++i) 
      prev = prev.next;
    
    prev.next = null;
    tail = prev.next;
    --size;
    return;
  }
  public int Search(Node head, int key) {
    if(head == null) {
      System.out.println("null");
      return -1;
    }
    if(head.data == key) {
      return 0;
    }
    return Search(head.next, key)+1;
  }
  public Node ReverseList(Node head) {
    Node temp = head;
    Node prev = null, ahead = null;
    while(temp != null) {
      ahead = temp.next;
      temp.next = prev;
      prev = temp;
      temp = ahead;
    }
    head = prev;
    return head;
  }
  public Node RecReverse(Node head) {
    if(head == null || head.next == null)
      return head;
    
    Node ahead = head.next;
    head.next = null;
    Node rev = RecReverse(ahead);
    ahead.next = head;
    return rev;
  }
  public static int RemoveNthNode(Node head, int n) {
    if(head.next == null)
      return 1;
    int loc = RemoveNthNode(head.next, n);
    if(loc == n) {
      head.next = head.next.next;
    }
    return loc+1;
  }
  private boolean hasCycle() {
    Node f=head, s=head;
    while(f!=null && f.next!=null) {
      f = f.next.next;
      s = s.next;
      if(f == s) 
        return true;
    }
    return false;
  }
  private void remCycle() {
    Node f=head, s=head;
    Node prev = null;
    while(f!=null && f.next!=null) {
      f = f.next.next;
      s = s.next;
      System.out.println(f.data + " " + s.data);
      if(f == s) {
        System.out.println(f.data + " " + s.data);
        s = head;
        while(s != f) {
          prev = f;
          s = s.next;
          f = f.next;
        }
        prev.next = null;
        return;
      }
      prev = f;
    }
  }
  public void Print(Node head) {
    Node temp = head;
    if(temp == null) {
      System.out.println("null");
      return;
    }
    System.out.print(temp.data+" -> ");
    Print(temp.next);
  }
  
  // MergeSort for linked-list
  public Node MergeSort(Node head) {
    // base case
    if(head == null || head.next == null)
      return head;
    // find mid
    Node mid=FindMid(head);
    Node rHead = mid.next;
    // delinking the left half from right half
    mid.next = null;
    // MergeSort on left
    Node left = MergeSort(head);
    // MergeSort on right
    Node right = MergeSort(rHead);
    // Merge the lists
    
    return Merge(left, right);
  }
  private Node FindMid(Node head) {
    Node s=head, f=head.next;
    while(f!=null && f.next!=null) {
      s=s.next; f=f.next.next;
    }
    return s;
  }
  private Node Merge(Node head1, Node head2) {
    Node mergedLL = new Node(-1);
    Node temp = mergedLL;
    
    while(head1!=null && head2!=null) {
      if(head1.data <= head2.data) {
        temp.next = head1;
        temp = temp.next;
        head1 = head1.next;
      } else {
        temp.next = head2;
        temp = temp.next;
        head2 = head2.next;
      }
    }
    
    while(head1 != null) {
      temp.next = head1;
      temp = temp.next;
      head1 = head1.next;
    }
    while(head2 != null) {
      temp.next = head2;
      temp = temp.next;
      head2 = head2.next;
    }
    
    return mergedLL.next;
  }
  
  // zig-zag linkedlist
  private Node ZigZag(Node head) {
    Node mid = FindMid(head);
    Node rHead = mid.next;
    mid.next = null;
    
    rHead = ReverseList(rHead);
    Print(head);
    Print(rHead);
    return ZigZagMerge(head, rHead);
  }
  private Node ZigZagMerge(Node head1, Node head2) {
    Node mer = new Node(-1);
    Node temp = mer;
    
    while(head1 != null && head2 != null) {
      temp.next = head1;
      temp = temp.next;
      System.out.println(head1.data);
      head1 = head1.next;
      temp.next = head2;
      temp = temp.next;
      System.out.println(head2.data);
      head2 = head2.next;
    }
    
    return mer.next;
  }
  
  public static void main(String[] args) {
    LL ll = new LL();
    
    ll.addFirst(2);
    ll.addFirst(1);
    ll.addLast(3);
    ll.addFirst(98);
    ll.addLast(4);
    ll.Add(2,9);
    
    ll.Print(ll.head);
    Node x = ll.ZigZag(ll.head);
    ll.Print(x);
  }
}
