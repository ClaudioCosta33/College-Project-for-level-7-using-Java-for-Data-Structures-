package LibraryProject;

import java.util.Objects;

class Node {
    Reader data;
    Node next;
}

class LinkedListQueue
{
    private Node front, rear;
    private int queueSize; // queue size


    public LinkedListQueue()
    {
        front = null;
        rear = null;
        queueSize = 0;
    }

    public boolean isEmpty()
    {
        return (queueSize == 0);
    }

    public Reader dequeue()
    {
        Reader data = front.data;
        front = front.next;
        if (isEmpty())
        {
            rear = null;
        }
        queueSize--;
        System.out.println("Reader " + data.lastname + " removed from the queue");
        return data;
    }

    public void enqueue(Reader data)
    {
        Node oldRear = rear;
        rear = new Node();
        rear.data = data;
        rear.next = null;
        if (isEmpty())
        {
            front = rear;
        }
        else  {
            oldRear.next = rear;
        }
        queueSize++;
        System.out.println("Reader " + data.lastname + " added to the queue");
    }

    public String getFront() {
        return front.data.lastname;
    }

    public void setFront(Node front) {
        this.front = front;
    }

    public Node getRear() {
        return rear;
    }

    public void setRear(Node rear) {
        this.rear = rear;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public void print_Queue() {
        Node temp = rear;
        System.out.println("Next person in the queue is :");
        temp.data.DisplayReaderInfo();
    }
    public int QueueIndex(String p) {
        Node temp = rear;
        for(int i=0;i<queueSize;i++)
        {
            if(Objects.equals(temp.data.lastname, p))
                return i;
        }
        return  -1;
    }
}