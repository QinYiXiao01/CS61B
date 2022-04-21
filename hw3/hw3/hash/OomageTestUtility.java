package hw3.hash;

import java.util.List;

public class OomageTestUtility { // 一个测试辅助工具类
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int N = oomages.size();
        int[] itemNums = new int[M]; // 对应bucket中有多少个hash
        for (Oomage o : oomages) {
            // 遍历所有的颜色，将其放入对应的bucket中
            int bucketIndex = (o.hashCode() & 0x7FFFFFFF) % M;
            itemNums[bucketIndex] += 1;
        }
        for (int i = 0; i < itemNums.length; i++) {
            if (!(itemNums[i] > N / 50 && itemNums[i] < N / 2.5)) {
                return false;
            }
        }
        return true;
    }
}
