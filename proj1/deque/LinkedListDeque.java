package deque;

import java.util.Iterator;

public class LinkedListDeque<Item> implements Deque<Item>{
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
        if(i<0||i>size-1){
            return null;
        }
        ItemNode p = fronsent.next;
        while(i>0){
            p = p.next;
            i-=1;
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
    private class DequeIterator implements Iterator<Item>{
        private ItemNode current = fronsent.next;
        public boolean hasNext(){
            return current != backsent;
        }
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public Iterator<Item> iterator(){
        return new DequeIterator();
    }
    @Override
    public boolean equals(Object O){
        if(O==null) return false;
        if(this==O) return true;
        if(!(O instanceof LinkedListDeque<?>)) return false;
        LinkedListDeque<?> other = (LinkedListDeque<?>) O;
        if(this.size()!=other.size()) return false;
        Iterator<Item> it1 = this.iterator();
        Iterator<?> it2 = other.iterator();
        while(it1.hasNext()){
            Item a = it1.next();
            Object b = it2.next();
            if(a==null){
                if(b!=null) return false;
            }else if(!a.equals(b)){
                return false;
            }
        }
        return true;
    }
}
