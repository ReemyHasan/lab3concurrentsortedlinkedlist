public class ContainThread extends TestThread implements Runnable{

    public ContainThread(RandomSeq seq, int seqPart, SortList setList) {
        super(seq, seqPart, setList);
    }

    @Override
    public void run() {
        for (int i = 0; i < nums.length; i++) {

            boolean v = list.contain(nums[i]);
//            if (v)
//                SyncListTest.successes_contain+=1;
//            else
//                SyncListTest.failures_contain+=1;
        }
    }
}
