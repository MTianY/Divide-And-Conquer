public class Main {
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }

    static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return maxSubArray(nums, 0, nums.length);
    }

    /**
     * T(n) = T(n/2) + T(n/2) + O(n)
     * T(n) = 2T(n/2) + O(n)
     * 最终算出时间复杂度 O(nlogn)
     */

    // 求[begin, end)中最大连续子序列的和
    static int maxSubArray(int[] nums, int begin, int end) {
        if (end - begin < 2) return nums[begin];
        int mid = (begin + end) >> 1;

        // 求左半部分最大和
        int leftMax = Integer.MIN_VALUE;
        int leftSum = 0;
        for (int i = 0; i < mid; i++) {
            leftSum += nums[i];
            leftMax = Math.max(leftMax, leftSum);
        }

        // 求右半部分最大和
        int rightMax = Integer.MIN_VALUE;
        int rightSum = 0;
        for (int i = mid; i < end; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightMax, rightSum);
        }

        // 第三种 [begin, mid) 和 [mid, end) 中都有的, 取 leftMax+rightMax.
        // 分布在 mid 左右两侧的情况, 那么左边最大和加上右边最大和, 等于贯穿整个 begin 到 end 的最大和
        return Math.max(leftMax + rightMax, Math.max(maxSubArray(nums, begin,mid), maxSubArray(nums, mid, end)));
    }


    static int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        for (int begin = 0; begin < nums.length; begin++) {
            int sum = 0;
            for (int end = begin; end < nums.length; end++) {
                sum += nums[end];
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    static int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        for (int begin = 0; begin < nums.length; begin++) {
            for (int end = begin; end < nums.length; end++) {
                int sum = 0;
                for (int i = begin; i <= end; i++) {
                    sum += nums[i];
                }
                max = Math.max(max, sum);
            }
        }
        return max;
    }

}