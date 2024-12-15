package lk.acpt.demofx.dsa;

public class Queue {
    Node top;

    public void push(int data){
        Node node = new Node(data);

        if(top==null){
            top=node;
        }else{
            Node temp = top;

            while(temp.next!=null){
                temp=temp.next;
            }

            temp.next = node;
        }
    }
}
