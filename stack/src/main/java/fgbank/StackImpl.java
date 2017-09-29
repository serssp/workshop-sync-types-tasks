package fgbank;

import java.util.EmptyStackException;

public class StackImpl implements Stack {
    private class Node {
        Node next;
        int x;

        public Node(int x, Node next) {
            this.next = next;
            this.x = x;
        }
    }

    private final Node NIL = new Node(0, null);
    private Node head = NIL;

    @Override
    public void push(int x) {
        Node curHead = head;
        Node node = new Node(x, curHead);
        head = node;
    }

    @Override
    public int pop() {
        if (head == NIL)
            throw new EmptyStackException();
        Node curHead = head;
        head = curHead.next;
        return curHead.x;
    }
}
