import java.util.concurrent.locks.ReentrantLock;

public class LockList extends SortList {

    ReentrantLock lock = new ReentrantLock();

    public LockList() {
        super();
    }

    @Override
    public boolean add(Integer obj) {
        try {
            lock.lock();

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
            lock.unlock();
        }
    }

    @Override
    public boolean remove(Integer obj) {
        try {
            lock.lock();
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
            lock.unlock();
        }

    }

    @Override
    public boolean contain(Integer obj) {
        try {
            lock.lock();
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
            lock.unlock();
        }
    }

    @Override
    public boolean check() {
            Entry current = head;
            while (current.next != null) {
                if (current.object > current.next.object) {
                    return false;
                }
                current = current.next;
            }
            return true;

    }

    @Override
    public int getLength() {
        return length;
    }
}
