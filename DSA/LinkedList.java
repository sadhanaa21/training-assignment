import java.util.Scanner;
import java.util.InputMismatchException;

class linkedlist {
  int data;
  linkedlist next;

  static linkedlist start = null;

  int countlinkedlist() //count nodes
  {
    int c = 0;
    linkedlist temp = start;
    while (temp != null) {
      c++;
      temp = temp.next;
    }
    return c;
  }

  linkedlist msort(linkedlist a, linkedlist b) //merge sort to merge two sorted linkedlist
  {
    linkedlist result;
    if (a == null)
      return b;
    if (b == null)
      return a;
    if (a.data <= b.data) {
      result = a;
      result.next = msort(a.next, b);
    } else {
      result = b;
      result.next = msort(a, b.next);
    }
    return result;
  }
  void sort() // main method to sort
  {
    start = split(start);
    showAll();
  }
  linkedlist split(linkedlist h) // to break a linkedlist into two parts
  {
    if (h == null || h.next == null) {
      return h;
    }

    linkedlist a = getMiddle(h); // to find middle
    linkedlist b = a.next;

    a.next = null;

    linkedlist left = split(h);
    linkedlist right = split(b);

    linkedlist sortedlist = msort(left, right);
    return sortedlist;
  }

  public static linkedlist getMiddle(linkedlist head) //method to find middle
  {
    if (head == null)
      return head;

    linkedlist slow = head, fast = head;

    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  void update(int pre, int nw) // method to update a linkedlist
  {
    linkedlist temp = start;
    while (temp != null && temp.data != pre) {
      temp = temp.next;
    }
    if (temp == null)
      System.out.println("Data not found");
    else
      temp.data = nw;
  }

  void showAll() //method to print 
  {
    linkedlist temp = start;
    while (temp != null) {
      System.out.print(temp.data + "  ");
      temp = temp.next;
    }
    System.out.println();
  }

  void deleteAtB() //deletion at beginning
  {
    if (start == null) {
      System.out.println("list is empty");
      return;
    }
    linkedlist temp1 = start;
    start = start.next;
    temp1 = null;
  }

  void deleteAtE() // deletion at ending
  {
    if (start == null || start.next == null) {
      deleteAtB();
      return;
    }
    linkedlist temp1 = start;
    linkedlist temp2 = start;
    while (temp1.next != null) {
      temp2 = temp1;
      temp1 = temp1.next;
    }
    temp2.next = null;
    temp1 = null;
  }

  void deleteAtSP(int p) // deletion at specific point
  {
    if (p <= 1) {
      deleteAtB();
      return;
    }
    if (p > countlinkedlist()) {
      deleteAtE();
      return;
    }
    linkedlist temp1 = start;
    linkedlist t;
    int i;
    for (i = 1; i < p - 1; i++) {
      temp1 = temp1.next;
    }
    t = temp1.next;
    temp1.next = t.next;
    t = null;
  }

  void insertAtB(int x) // insertion at beginning
  {
    linkedlist temp = new linkedlist();
    temp.data = x;
    temp.next = start;
    start = temp;
  }

  void insertAtE(int x) // insertion at ending
  {
    if (start == null) {
      insertAtB(x);
      return;
    }
    linkedlist temp = new linkedlist();
    linkedlist temp1 = start;
    temp.data = x;
    temp.next = null;
    while (temp1.next != null)
      temp1 = temp1.next;

    temp1.next = temp;
  }

  boolean search(int x) // searching an element 
  {
    linkedlist temp = start;
    while (temp != null) {
      if (temp.data == x) return true;
      temp = temp.next;
    }
    return false;
  }

  void insertAtSP(int x, int p) // insertion at specific point
  {
    if (p <= 1) {
      insertAtB(x);
      return;
    }
    if (p >= countlinkedlist()) {
      insertAtE(x);
      return;
    }
    linkedlist temp = new linkedlist();
    linkedlist temp1 = start;
    temp.data = x;
    for (int i = 1; i < p - 1; i++)
      temp1 = temp1.next;

    temp.next = temp1.next;
    temp1.next = temp;
  }

}
class Demo {
  public static void main(String[] args) {
    linkedlist start = new linkedlist();
    int a, b;
    while (true) {
      System.out.println("===========================");
      System.out.println("*****Enter your choice*****");
      System.out.println("1.Insert at beginning");
      System.out.println("2.Insert at ending");
      System.out.println("3.Insert at specific position");
      System.out.println("4.Deletion at beginning");
      System.out.println("5.Deletion at ending");
      System.out.println("6.Deletion at specific position");
      System.out.println("7.Update");
      System.out.println("8.Search");
      System.out.println("9.ShowAll");
      System.out.println("10. Sort");
      System.out.println("11.Exit");
      System.out.println("===========================");
      try {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        switch (x) {
        case 1:
          System.out.println("Enter a number ");
          a = sc.nextInt();
          start.insertAtB(a);
          System.out.println("Done ");
          break;

        case 2:
          System.out.println("Enter a number ");
          b = sc.nextInt();
          start.insertAtE(b);
          System.out.println("Done ");
          break;

        case 3:
          System.out.println("Enter a number ");
          a = sc.nextInt();
          System.out.println("Enter a position ");
          b = sc.nextInt();
          start.insertAtSP(a, b);
          System.out.println("Done ");
          break;

        case 4:
          start.deleteAtB();
          System.out.println("Done ");
          break;

        case 5:
          start.deleteAtE();
          System.out.println("Done ");
          break;

        case 6:
          System.out.println("Enter a position ");
          b = sc.nextInt();
          start.deleteAtSP(b);
          System.out.println("Done ");
          break;

        case 7:
          System.out.println("Enter no. to be replaced ");
          a = sc.nextInt();
          System.out.println("Enter new number ");
          b = sc.nextInt();
          start.update(a, b);
          System.out.println("Done ");
          break;

        case 8:
          System.out.println("Enter a number ");
          b = sc.nextInt();
          System.out.println(start.search(b));
          break;

        case 9:
          start.showAll();
          break;

        case 10:
          start.sort();
          break;

        case 11:
          System.exit(0);
          break;

        default:
          System.out.println("Invalid");
          break;
        }
      } catch (InputMismatchException e) {
        System.out.println("Enter valid option");
      }
    }
  }
}