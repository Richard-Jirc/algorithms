/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class QueueByLinkedLists {

    private Node first, last;

    public class Node {
        String item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void intoQueue(String item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldLast.next = last;
    }

    public String getQueue() {
        String item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        return item;
    }

    public void printAll() {
        for (Node x = first; x != null; x = x.next) {
            System.out.print(x.item + ", ");
        }
    }

    public static void main(String[] args) {
        int n = 20;
        int[] test = new int[n];
        QueueByLinkedLists ObjOne = new QueueByLinkedLists();

        for (int i = 0; i < n; i++) {
            int key = (int) (Math.random() * 100);
            ObjOne.intoQueue(key + "*");
            test[i] = key;
        }
        ObjOne.getQueue();

        for (int k = 0; k < n; k++) {
            System.out.print(test[k] + ", ");
        }
        System.out.println();

        ObjOne.printAll();

    }
}
