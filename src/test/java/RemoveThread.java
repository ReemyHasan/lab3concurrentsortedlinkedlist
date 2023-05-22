public class RemoveThread extends TestThread implements Runnable{
    public RemoveThread(RandomSeq seq, int seqPart, SortList setList) {
        super(seq, seqPart, setList);
    }

    @Override
    public void run() {
        for (int i = 0; i < nums.length; i++) {
            boolean v = list.remove(nums[i]);
//            if (v)
//                SyncListTest.successes_remove +=1;
//            else
//                SyncListTest.failures_remove +=1;
        }
    }
}