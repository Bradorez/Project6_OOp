import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T>
{

    public Node<T> head;
    private Node<T> tail;
    private int size;
    public LinkedList()
    {
        head = null;
        tail = null;
        size = 0;
    }


    private static class Node<T>
    {
        private T data;
        private Node<T> next;
        private Node<T> prev;

        Node(Node<T> prev, T data, Node<T> next)
        {
            this.data = data;
            this.next = next;
            this.prev = prev;

        }
    }

    public void add(T data)
    {
        Node<T> t = tail;
        Node<T> newNode = new Node<>(t, data, null);
        tail = newNode;
        if (t == null)
            head = newNode;
        else
            t.next = newNode;
        size++;
    }

    public T get(int num)
    {
        if (num < 0 || num >= size) throw new IndexOutOfBoundsException();
        Node<T> node = head;
        for(int i = 0; i < num; i++)
        {
            node = node.next;
        }
        return node.data;
    }

    public void add(int num, T data)
    {
        if (num < 0 || num > size) throw new IndexOutOfBoundsException();
        if (num == size) {
            add(data);
        } else {
            Node<T> node = getNode(num);
            Node<T> prevNode = node.prev;
            Node<T> newNode = new Node<>(prevNode, data, node);
            node.prev = newNode;
            if (prevNode == null)
                head = newNode;
            else
                prevNode.next = newNode;
            size++;
        }
    }

    private Node<T> getNode(int num) {
        if (num < 0 || num >= size) throw new IndexOutOfBoundsException();
        Node<T> node = head;
        for(int i = 0; i < num; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T>
    {
        private Node<T> node = head;

        public boolean hasNext()
        {
            return node != null;
        }

        public T next()
        {
            if (!hasNext()) throw new NoSuchElementException();
            T data = node.data;
            node = node.next;
            return data;
        }
    }
    public T removeHead()
    {
        if (this.head == null)
        {
            throw new NoSuchElementException("List is empty");
        }
        T element = this.head.data;
        this.head = this.head.next;
        if (this.head == null)
        {
            this.head = null;
        } else
        {
            this.head.prev = null;
        }
        this.size--;
        return element;
    }

    static class Stack<T>
    {
        Node<T> top;

        Stack()
        {
            this.top = null;
        }

        void push(T data)
        {
            Node<T> newNode = new Node<T>(null,data,null);
            newNode.data = data;
            if (top == null) {
                top = newNode;
            } else {
                newNode.next = top;
                top = newNode;
            }
        }

        T pop()
        {
            if (top == null)
            {
                return null;
            } else
            {
                T node = top.data;
                top = top.next;
                return node;
            }
        }

        T peek()
        {
            if (top == null)
            {
                return null;
            } else
            {
                return top.data;
            }
        }

        boolean isEmpty()
        {
            return top == null;
        }
    }
    static class Queue<T> extends LinkedList<T>
    {
        public void enqueue(T data)
        {
            this.add(data);
        }

        public T dequeue() {
            if (this.head != null) {
                return this.removeHead();
            } else {
                throw new NoSuchElementException("Queue is empty");
            }
        }
    }


    public static void main(String[] args)
    {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(2);
        list.add(1, 20);
        list.add(3);
        for (Integer i : list)
        {
            System.out.print(i + " ");
        }


        Stack<Integer> stack = new Stack<>();

        stack.push(12);
        System.out.println(stack.peek());
        stack.push(13);
        System.out.println(stack.peek());
        stack.push(14);
        System.out.println(stack.pop());
        System.out.println(stack.peek());



        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }


    }


/* Part 1.2
1. Adding at the end of the list is **O(1)** because we directly access the last node via the tail pointer.

2. Adding at the i-th position is **O(n(i))** because we may need to traverse the list to find the i-th position.

3. The provided traversal code has **O(n^2)** complexity.
   This is due to the combination of looping over the list size (`n`) and using the `get(i)`
   method (also `n`) in each loop iteration.

 */
