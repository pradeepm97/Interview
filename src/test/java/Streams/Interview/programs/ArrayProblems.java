package Streams.Interview.programs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class ArrayProblems {


    @Test
    void secondLargestValue() {
        int[] a = {5, 2, 9, 1, 7};
        int result = Arrays.stream(a).boxed().sorted(Collections.reverseOrder()).skip(1).findFirst().orElse(-1);
        System.out.println(result);
    }

    @Test
    void RotateArray() {
        int[] a = {1, 2, 3, 4, 5, 7, 9};
        int k = 2;

        int[] result = new int[a.length];
        int index = 0;
        for (int i = k; i < a.length; i++) {
            result[index] = a[i];
            index++;
        }

        for (int i = 0; i <= k; i++) {
            if (i == k) break;
            result[index] = a[i];
            index++;
        }
        System.out.println(Arrays.toString(result));
    }


    @Test
    void findMissingNumber() {
        int[] a = {1, 2, 3, 5, 6};
        int n = 6;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = Arrays.stream(a).sum();
        int missingNumber = expectedSum - actualSum;
        System.out.println(missingNumber);
    }

    @Test
    void findDuplicateNumberWithOutUsingSet() {
        int[] a = {1, 2, 3, 4, 5, 3, 6, 1};
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            if (list.contains(a[i])) {
                System.out.println("dupilcate :: " + a[i]);
            } else {
                list.add(a[i]);
            }
        }
    }

    @Test
    void removeDuplicatesFromArray() {
        int[] a = {1, 2, 3, 4, 5, 3, 6, 1};
        int[] b = Arrays.stream(a).distinct().toArray();
        System.out.println(Arrays.toString(b));

        Arrays.stream(a).boxed().distinct().forEach(System.out::print);
        System.out.println();

        Set<Integer> set = new HashSet<>();
        Arrays.stream(a).boxed().filter(set::add).toList().forEach(System.out::print);
    }

    @Test
    void mergeTwoSortedArrays() {
        int[] a = {1, 3, 5, 7};
        int[] b = {2, 4, 6, 8};
        List<Integer> result = new ArrayList<>();
        result.addAll(Arrays.stream(a).boxed().toList());
        result.addAll(Arrays.stream(b).boxed().toList());

        System.out.println(Arrays.toString(result.stream().sorted().toArray()));
    }

    @Test
    void moveZeroesToEndOfArray() {
        int[] a = {0, 1, 0, 3, 12};
        int[] b = new int[a.length];
        int k = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                b[k] = a[i];
                k++;
            }
        }
        System.out.println(Arrays.toString(b));
    }

    @Test
    void findInterSectionOfTwoArrays() {
        int[] a = {1, 2, 2, 4, 5};
        int[] b = {2, 5, 5, 6, 7};
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < a.length; i++) {
            for (int i1 = 0; i1 < b.length; i1++) {
                if (a[i] == b[i1]) {
                    set.add(a[i]);
                }
            }
        }

        System.out.println(set);
    }


    @Test
    void UnionArrays() {
        int[] a = {1, 3, 5, 7};
        int[] b = {2, 3, 6, 7, 8};

        Set<Integer> set = new HashSet<>();
        for (int i : a) {
            set.add(i);
        }
        for (int i : b) {
            set.add(i);
        }
        System.out.println(set);
    }

    @Test
    void MajorityElement() {
        // that element should appear n/2 times. n is the length of array.
        int[] arr = {3, 3, 4, 2, 3, 3, 2};

        Arrays.stream(arr).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() > arr.length / 2).map(Map.Entry::getKey)
                .forEach(System.out::println);

    }

    @Test
    void twoSumProblem() {
        int[] a = {2, 10, 11, 7};
        // not required the index .
        int target = 9;

        Set<Integer> set = new HashSet<>();
        int[] ab = new int[2];

        for (int i = 0; i < a.length; i++) {
            int r = target - a[i];
            if (set.contains(r)) {
                ab[0] = r;
                ab[1] = a[i];
            }
            set.add(a[i]);
        }

        System.out.println(Arrays.toString(ab));

        System.out.println("-----------");
        // if i want to return the index i have to use Map
        Map<Integer, Integer> map = new HashMap<>();
        int[] abc = new int[2];
        for (int i = 0; i < a.length; i++) {
            int r = target - a[i];
            if (map.containsKey(r)) {
                abc[0] = map.get(r);
                abc[1] = i;
                break;
            }
            map.put(a[i], i);
        }
        System.out.println(Arrays.toString(abc));

    }

    @Test
    void threeSumProblem() {
        // all the 3unique value should sum to targetv value tipically 0.
        int[] a = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(a);
        int target = 0;

        for (int i = 0; i < a.length - 2; i++) {

            if (i > 0 && a[i] == a[i - 1]) continue;

            int left = i + 1;
            int right = a.length - 1;

            while (left < right) {
                int sum = a[i] + a[left] + a[right];

                if (sum == target) {
                    result.add(Arrays.asList(a[i], a[left], a[right]));

                    while (left < right && a[left] == a[left + 1]) left++;
                    while (left < right && a[right] == a[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }

        }
        System.out.println(result);
    }

    @Test
    void maxProductContinueSubArray() {
        int[] a = {2, 3, 0, -2, 8, 1, 4};

        int left = 1;
        int right = 1;
        int ans = a[0];
        int n = a.length - 1;


        for (int i = 0; i < a.length; i++) {

            left = left == 0 ? 1 : left;
            right = right == 0 ? 1 : right;

            left *= a[i];
            right *= a[n - i];

            ans = Math.max(ans, Math.max(left, right));
        }

        System.out.println(ans);

    }

    @Test
    void longestCommonSubSeqInTwoStrings() {
        String a = "abcdaf";
        String b = "acbcf";
        long count = 0;

        Map<Character, Long> map1 = a.chars().mapToObj(e -> (char) e)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Map<Character, Long> map2 = b.chars().mapToObj(e -> (char) e)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


        count = map1.keySet().stream()
                .filter(map2::containsKey)
                .mapToLong(k -> Math.min(map1.get(k), map2.get(k)))
                .sum();

        System.out.println("sum ::" + count);

    }

    @Test
    void longestIncreasingSubSequence(){
        int[] a = {10, 9, 2, 5, 3, 7, 101, 18};

        int[] dp = new int[a.length];
        int maxLength = 1;

        Arrays.fill(dp, 1);

        for(int i=1;i<a.length;i++){
            for(int j=0;j<i;j++){

                if(a[j] < a[i]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            maxLength = Math.max(dp[i],maxLength);
        }
        System.out.println(maxLength);
    }

    @Test
    void maxSubArraySum(){
        int[] a = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int currentSum = a[0];
        int maxSum = a[0];

        for (int i = 1; i < a.length; i++) {
            currentSum = Math.max(a[i], currentSum+a[i]);
            maxSum= Math.max(maxSum,currentSum);
        }

        System.out.println(maxSum);
    }

}



























