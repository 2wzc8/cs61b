package deque;

public class ArrayDeque<Item> {
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
    public boolean isEmpty(){
        return size==0;
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
    //public Iterator<T> iterator(){
    //
    //}
    public boolean equals(ArrayDeque o){
        if(this.size()!=o.size()){
            return false;
        }
        for(int i=0;i<this.size();i++){
            if(this.get(i)!=o.get(i)){
                return false;
            }
        }
        return true;
    }
}
