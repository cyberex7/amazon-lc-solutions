import java.util.Comparator;
import java.util.PriorityQueue;

public class ProcessingPowerServers {

    public static void main(String[] args) {
        int[] processingPower = new int[]{4, 1, 4, 5, 3};
        int[] bootingPower = new int[]{8, 8, 10, 9, 12};
        long maxPower = 33;

        ProcessingPowerServers test = new ProcessingPowerServers();
        System.out.println(test.maxLengthValidSubArray(processingPower, bootingPower, maxPower)); // output should be 2
    }

    public int maxLengthValidSubArray(int[] processingPower, int[] bootingPower, long maxPower) {
        if (processingPower == null || bootingPower == null || processingPower.length == 0 || processingPower.length != bootingPower.length) {
            return 0;
        }

        PriorityQueue<Integer> maxBootingPower = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b.compareTo(a);
            }
        });

        int maxLength = 0;
        int start = 0;
        long currentSumProcessingPower = 0;

        for (int end = 0; end < processingPower.length; end++) {
            maxBootingPower.add(bootingPower[end]);
            currentSumProcessingPower += processingPower[end];
            long currentPower = maxBootingPower.peek() + currentSumProcessingPower * (end - start + 1);

            while (currentPower > maxPower && start <= end) {
                currentSumProcessingPower -= processingPower[start];
                maxBootingPower.remove(bootingPower[start]);
                start++;
                if (start <= end) {
                    currentPower = maxBootingPower.peek() + currentSumProcessingPower * (end - start + 1);
                }
            }

            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }
}
