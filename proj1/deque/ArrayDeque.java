package deque;

import java.util.Iterator;

public class ArrayDeque<Item> implements Deque<Item> {
    private Item[] items;
    private int size;
    public ArrayDeque(){
        items = (Item[]) new Object[100];
        size = 0;
    }
    private void resize(int capacity){
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items,0,a,0,size);
        items = a;
    }
    public void addLast(Item x){
        if(size== items.length){
            resize((int)size*2);
        }
        items[size] = x;
        size+=1;
    }
    public void addFirst(Item x){
        if(size == items.length){
            resize((int)size*2);
        }
        for(int i=size;i>0;i--){
            items[i] = items[i-1];
        }
        items[0]=x;
        size += 1;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        for(int i=0;i<size;i++){
            if(i!=size-1){
                System.out.print(items[i]+" ");
            }
            else{
                System.out.println(items[i]);
            }
        }
    }
    public Item removeFirst(){
        if(size==0){
            return null;
        }
        Item k = items[0];
        for(int i=0;i<size-1;i++){
            items[i]=items[i+1];
        }
        items[size-1]=null;
        size-=1;
        return k;
    }
    public Item removeLast(){
        if(size==0){
            return null;
        }
        Item k = items[size-1];
        items[size-1]=null;
        size-=1;
        return k;
    }
    public Item get(int i){
        if(i>size-1||i<0){
            return null;
        }
        return items[i];
    }
    private class DequeIterator implements Iterator<Item>{
        private int pos = 0;
        public boolean hasNext(){
            return pos<size;
        }
        public Item next(){
            return items[pos++];
        }
    }
    public Iterator<Item> iterator(){
        return new DequeIterator();
    }
    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(this == o) return true;
        if(!(o instanceof ArrayDeque<?>)) return false;
        ArrayDeque<?> other = (ArrayDeque<?>) o;
        if(other.size()!=this.size()) return false;
        Iterator<Item> t1 = this.iterator();
        Iterator<?> t2 = other.iterator();
        while(t1.hasNext()){
            Item a = t1.next();
            Object b = t2.next();
            if(a==null){
                if(b!=null) return false;
            }else if(!a.equals(b)){
                return false;
            }
        }
        return true;
    }
}
