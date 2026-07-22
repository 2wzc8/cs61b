package deque;

public class LinkedListDeque<Item> {
    private class ItemNode{
        public Item item;
        public  ItemNode next;
        public ItemNode prev;
        public ItemNode(Item i,ItemNode n,ItemNode p){
            item = i;
            next = n;
            prev = p;
        }
    }
    private ItemNode fronsent;
    private ItemNode backsent;
    private int size;
    public LinkedListDeque(){
        fronsent = new ItemNode(null,null,null);
        backsent = new ItemNode(null,fronsent,fronsent);
        fronsent.prev = backsent;
        fronsent.next = backsent;
        size = 0;
    }
    public LinkedListDeque(Item x){
        fronsent = new ItemNode(null,null,null);
        fronsent.next = new ItemNode(x,null,fronsent);
        backsent = new ItemNode(null,fronsent,fronsent.next);
        fronsent.next.next = backsent;
        fronsent.prev = backsent;
        size = 1;
    }
    public void addLast(Item x){
        backsent.prev.next = new ItemNode(x,backsent,backsent.prev);
        backsent.prev = backsent.prev.next;
        size += 1;
    }
    public void addFirst(Item x){
        fronsent.next.prev = new ItemNode(x,fronsent.next,fronsent);
        fronsent.next = fronsent.next.prev;
        size += 1;
    }
    public Item removeLast(){
        if(backsent.prev!=fronsent&&size!=0){
            Item k = backsent.prev.item;
            backsent.prev.prev.next = backsent;
            backsent.prev = backsent.prev.prev;
            if(size==1){
                fronsent.next=backsent;
            }
            size -= 1;
            return k;
        }
        return null;
    }
    public Item removeFirst(){
        if(backsent.prev!=fronsent&&size!=0){
            Item k = fronsent.next.item;
            fronsent.next.prev = fronsent;
            fronsent.next = fronsent.next.next;
            if(size==1){
                backsent.prev = fronsent;
            }
            size -= 1;
            return k;
        }
        return null;
    }
    public int size(){
        return size;
    }
    public Item get(int i){
        ItemNode p = fronsent.next;
        if(i==0){
            return null;
        }
        while(i>0){
            if(p.next!=backsent){
                p = p.next;
            }
            if(p.next==backsent){
                break;
            }
            i -= 1;
        }
        if(i!=0){
            return null;
        }
        return p.item;
    }
    public Item getRecursive(int i){
        if(i<0||i>size-1){
            return null;
        }
        return getRecursiveHelper(fronsent.next,i);
    }
    private Item getRecursiveHelper(ItemNode node, int i) {
        if (i == 0) {
            return node.item;
        }
        return getRecursiveHelper(node.next, i - 1);
    }
    public boolean isEmpty(){
        return size==0;
    }
    public void printDeque(){
        ItemNode p = fronsent.next;
        while(p!=backsent){
            if(p!=backsent.prev){
                System.out.print(p.item+" ");
                p=p.next;
            }
            else{
                System.out.println(p.item);
                p=p.next;
            }
        }
    }
    //public Iterator<Item> iterator(){
//lecture 11 才讲，还没看到
    //}
    public boolean equals(LinkedListDeque O){
        if(this.size()!=O.size()){
            return false;
        }
        ItemNode p = this.fronsent.next;
        ItemNode o = O.fronsent.next;
        while(p!=this.backsent&&o!=O.backsent){
            if(p.item!=o.item){
                return false;
            }
        }
        return true;
    }
}
