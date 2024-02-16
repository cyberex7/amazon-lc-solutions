/*
* where visitors can shop for all things geek- related. The shopping cart on this website is a bit unusual. The cart items are represented in the form of a linked list where each node instance, a SinglyLinkedListNode, has a value, data (the item name), and a pointer to the next node, next.

You visited the website and initially, your cart had n items. You perform some operations with the cart. Each operation consists of two strings: an action and an item name.

There are three types of operations:
• PUSH_HEAD itemName: Insert a new item with data as itemName as the head of the linked list
• PUSH_TAIL itemName: Insert a new item with data as itemName as the tail of the linked list
• POP HEAD -: Delete the current head of the linked list. The dash at the right is a filler value since there is no itemName.
Return a reference to the head of the final linked list after applying all the queries.

Note: You are not allowed to use extra memory other than to create new nodes for queries PUSH_HEAD and PUSH_TAIL.
* */

class Node {
    String val;
    Node next;

    Node(String val) {
        this.val = val;
        this.next = null;
    }
}

public class ShoppingCart {
    public static Node solution(Node head, String[] cartCommands) {
        Node tail = head;
        if (head != null) {
            while (tail.next != null) {
                tail = tail.next;
            }
        }

        for (String command : cartCommands) {
            if (command.startsWith("PUSH_HEAD")) {
                String itemName = command.substring(10);
                Node newHead = new Node(itemName);
                newHead.next = head;
                if (tail == null) {
                    tail = newHead;
                }
                head = newHead;
            } else if (command.startsWith("PUSH_TAIL")) {
                String itemName = command.substring(10);
                Node newTail = new Node(itemName);
                if (head == null) {
                    head = newTail;
                } else {
                    tail.next = newTail;
                }
                tail = newTail;
            } else if (command.startsWith("POP_HEAD")) {
                if (head != null) {
                    head = head.next;
                    if (head == null) {
                        tail = null;
                    }
                }
            }
        }
        return head;
    }

    public static void printCart(Node head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println("TEST 1");
        Node cart1 = createCart(new String[]{"item_1", "item_2", "item_3", "item_4"});
        String[] cartCommands1 = {"PUSH_HEAD item_0", "PUSH_TAIL item_5", "POP_HEAD -"};
        printCart(solution(cart1, cartCommands1));
        System.out.println();

        // Additional test cases...
    }

    // Helper method to create a cart from an array of strings
    private static Node createCart(String[] items) {
        if (items == null || items.length == 0) return null;
        Node head = new Node(items[0]);
        Node current = head;
        for (int i = 1; i < items.length; i++) {
            current.next = new Node(items[i]);
            current = current.next;
        }
        return head;
    }
}

