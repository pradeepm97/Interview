package Streams.Interview.StreamService;

import org.junit.jupiter.api.Test;

import java.util.*;

public class GeneralProblems {


    @Test
    void reArrangeArrayNumber() {
        int[] a = {12, -7, 45, 0, -22, 8, -3, 19, -15, 6};
        // reArrainge number in + - + - + - ......

        // My Solution : first convert it into Two Different Lists
        // positive List , Negative list

        int positive[] = Arrays.stream(a).filter(e -> e >= 0).toArray();
        int negative[] = Arrays.stream(a).filter(e -> e < 0).toArray();


        int i = 0;
        int p = 0;
        int n = 0;
        int[] result = new int[a.length];

        while (p < positive.length && n < negative.length) {
            result[i++] = positive[p++];
            result[i++] = negative[n++];
        }

        while (p < positive.length) {
            result[i++] = positive[p++];
        }
        while (n < negative.length) {
            result[i++] = negative[n++];
        }

        System.out.println(Arrays.toString(result));
    }


    @Test
    void longestSubString() {
        String s = "abcabcbb";
        int left = 0;
        int right = 0;
        int maxLenght = 0;
        int startIndex = 0;

        Set<Character> set = new HashSet<>();

        while (right < s.length()) {

            char ch = s.charAt(right);

            if (!set.contains(ch)) {
                set.add(ch);
                if (right - left + 1 > maxLenght) {
                    maxLenght = right - left + 1;
                    startIndex = left;
                }
                right++;
            } else {
                set.remove(s.charAt(left));
                left++;

            }
        }

        String longestString = s.substring(startIndex, startIndex + maxLenght);
        System.out.println("longestString :" + longestString + " maxLength : " + maxLenght);

    }

    @Test
    void twoSumProblem() {

        int[] a = {2, 7, 11, 15};

        int target = 17;

        Map<Integer, Integer> map = new HashMap<>();

        int[][] ab = new int[1][1];

        for (int i = 0; i < a.length; i++) {
            int c = target - a[i];

            if (map.containsKey(c)) {
                ab[0] = new int[]{map.get(c), i};
            }
            map.put(a[i], i);
        }

    }

    @Test
    void twoSumProblemWithSet() {
        int[] a = {2, 7, 11, 15};

        int target = 17;

        List<List<Integer>> list = new ArrayList<>();

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < a.length; i++) {
            int complimentValue = target - a[i];
            if (set.contains(complimentValue)) {
                list.add(Arrays.asList(complimentValue, a[i]));
            }
            set.add(a[i]);
        }

        System.out.println(list);

    }

    @Test
    void missingNumber() {
        // n * (n+1) / 2;
        // get the max Number in the Array.
        int[] a = {1, 4, 3, 5, 6, 7};

        int max = Arrays.stream(a).max().getAsInt();

        int expectedSum = max * (max + 1) / 2;

        System.out.println("missing value : " + (expectedSum - Arrays.stream(a).sum()));
    }


    @Test
    void subArray() {
        int[] a = {1, 2, 3, 7, 5};
        int targetSum = 15;
        int startValue = 0;
        int endValue = 0;


        for (int i = 0; i < a.length - 1; i++) {
            int j = i + 1;
            int sum = a[i];


            while (sum < targetSum) {
                sum = sum + a[j];
                j++;
            }

            if (targetSum == sum) {
                startValue = i;
                endValue = j - 1;
                break;
            }

        }

        for (int i = startValue; i <= endValue; i++) {
            System.out.print(a[i] + " ");
        }


    }


}
