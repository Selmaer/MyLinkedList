package mylinkedlist;
class Node <T> {
    private T data;
    private Node prevNode;
    private Node nextNode;
    private static int count = 0;
    private int index;
    
    public Node() {
        index = count;
        prevNode = null;
        nextNode = null;
    }
    public Node (Node prevNode, T data) {
        this.data = data;
        this.prevNode = prevNode;
        count++;
        index = count;
    }
    
    public int getCounter() {
        return index;
    }
    public Node getPrevNode() {
        return prevNode;
    }
    public Node getNextNode() {
        return nextNode;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public T getData() {
        return data;
    }
    public void incCount() {
        count++;
    }
    public void incIndex() {
        index++;
    }
    public void decCount() {
        count--;
    }
    public void decIndex() {
        index--;
    }
    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }
    public void setPrevNode(Node prevNode) {
        this.prevNode = prevNode;
    }
    public void setData(T data) {
        this.data = data;
    }
}

public class MyLinkedList <T> {
    
    Node node;

    public void add(T data){
        if (node == null) {
            node = new Node();
            node.setData(data); 
        } else if (node.getNextNode() == null) {
            node.setNextNode(new Node(node, data));
            node = node.getNextNode();
        } else {
            node = node.getNextNode();
            add(data);
        }
    }
    
    public void add(T data, int index) throws Exception{
        node = getIndexNode(index);
        Node temp = new Node(node.getPrevNode(), data);
        node.getPrevNode().setNextNode(temp);
        temp.setNextNode(node);
        node.setPrevNode(temp);
        temp.setIndex(index);
        increaseFollowingIndexes(temp);
    }
    
    public void addFirst(T data) throws Exception {
        getFirst();
        node.setPrevNode(new Node(null, data));
        node.getPrevNode().setNextNode(node);
        node = node.getPrevNode();
        node.setIndex(0);
        increaseFollowingIndexes(node);
    }
    
    public void addLast(T data) {
        add(data);
    }
    
    public T getFirst() throws Exception {
        if (node == null) {
            throw new Exception("MyLinkedList is empty");
        } else if (node.getPrevNode() != null) {
            node = node.getPrevNode();
            getFirst();
        }
        return (T)node.getData();
    }
    
    public T getLast() throws Exception {
        if (node == null) {
            throw new Exception("MyLinkedList is empty");
        } else if (node.getNextNode() != null) {
            node = node.getNextNode();
            getLast();
        }
        return (T)node.getData();
    }
    
    public T get(int index) throws Exception {
        return (T)getIndexNode(index).getData();
    }
    
    public T removeFirst() throws Exception {
        getFirst();
        return removeCurrentNode();
    }
    
    public T removeLast() throws Exception {
        getLast();
        return removeCurrentNode();
    }
    
    public T remove(int index) throws Exception {
        getIndexNode(index);
        return removeCurrentNode();
    }
    
    
    @Override
    public String toString() {
        try {
        getIndexNode(0);
        }
        catch (Exception e) {
            System.out.println("THERE IS NO SUCH INDEX");
        }
        return "[" + getString(node) + "]";
    }
    
    private T removeCurrentNode() {
        T data = (T)node.getData();
        decreaseFollowingIndexes(node);
        if(node.getPrevNode() != null && node.getNextNode() != null) {
            node.getPrevNode().setNextNode(node.getNextNode());
            node.getNextNode().setPrevNode(node.getPrevNode());
            node = node.getPrevNode();
        } else if (node.getPrevNode() == null) {
            node = node.getNextNode();
            node.setPrevNode(null);
        } else if (node.getNextNode() == null) {
            node = node.getPrevNode();
            node.setNextNode(null);
        }
               
        return data;
    }
    private String getString (Node node) {
        if (node.getNextNode() == null) {
            return node.getData().toString();
        } else {
            return node.getData() + ", " + getString(node.getNextNode());
        }
    }
    private Node getIndexNode(int index) throws Exception {
        if (node == null) {
            throw new Exception("INDEX NOT FOUND");
        } else if (node.getIndex() > index) {
            node = node.getPrevNode();
            getIndexNode(index);
        } else if (node.getIndex() < index) {
            node = node.getNextNode();
            getIndexNode(index);
        }
        return node;
    }
    
    private void increaseFollowingIndexes (Node node) {
        if (node.getNextNode() != null) {
            node.getNextNode().incIndex();
            increaseFollowingIndexes (node.getNextNode());
        }
    }
    
    private void decreaseFollowingIndexes (Node node) {
        if (node.getNextNode() != null) {
            node.getNextNode().decIndex();
            increaseFollowingIndexes (node.getNextNode());
        }
    }
}
