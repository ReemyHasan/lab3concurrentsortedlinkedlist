import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLockList extends SortList {

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public RWLockList() {
        super();
    }

    @Override
    public boolean add(Integer obj) {
        try {
            lock.writeLock().lock();
            Entry prev = this.head;
            Entry curr = prev.next;
            while (curr.object.compareTo(obj) < 0) {
                prev = curr;
                curr = prev.next;
            }
            if (curr.object.equals(obj) || prev.object.equals(obj)) {
                return false;
            } else {
                Entry newEntry = new Entry(obj);
                newEntry.next = curr;
                prev.next = newEntry;
                length+=1;
                return true;
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public boolean remove(Integer obj) {
        try {
            lock.writeLock().lock();
            Entry prev = this.head;
            Entry curr = prev.next;
            while (curr.object.compareTo(obj) < 0) {
                prev = curr;
                curr = prev.next;
            }
            if (curr.object.equals(obj)) {
                prev.next = curr.next;
                length-=1;
                successes_remove +=1;
                return true;
            } else {
                failures_remove +=1;
                return false;
            }
        } finally {
            lock.writeLock().unlock();
        }

    }

    @Override
    public boolean contain(Integer obj) {
        try {
            lock.writeLock().lock();
            lock.readLock().lock();

            Entry prev = this.head;
            Entry curr = prev.next;
            while (curr.object.compareTo(obj) < 0) {
                prev = curr;
                curr = prev.next;
            }

            if (curr.object.equals(obj) || prev.object.equals(obj)) {
                successes_contain +=1;
                return true;
            } else {
                failures_contain +=1;
                return false;
            }
        } finally {
            lock.readLock().unlock();
            lock.writeLock().unlock();
        }
    }

    @Override
    public boolean check() {
        try {
            Entry current = head;
            while (current.next != null) {
                if (current.object > current.next.object) {
                    return false; // Current element is greater than the next element
                }
                current = current.next;
            }
            return true;

        } finally {

        }
    }

    @Override
    public int getLength() {
        return length;
    }

}
