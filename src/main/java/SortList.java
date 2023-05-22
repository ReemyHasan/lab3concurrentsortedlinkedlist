public abstract class SortList {
    public  int failures_contain = 0;
    public  int successes_contain= 0;
    public  int failures_remove = 0;
    public  int successes_remove = 0;
    public Entry head;
    public int length = 0 ;
    public SortList() {
        this.head = new Entry(Integer.MIN_VALUE);
       this.head.next =new Entry(Integer.MAX_VALUE);
    }

    public  abstract  boolean add(Integer obj);
    public  abstract  boolean remove(Integer obj);
    public  abstract  boolean contain(Integer obj);
    public  abstract  boolean check();
    public  abstract  int getLength();
    public void printList(){
        Entry curr = this.head;
        while (curr != null){
            System.out.println(curr.object);
            curr = curr.next;

        }
    }
}
