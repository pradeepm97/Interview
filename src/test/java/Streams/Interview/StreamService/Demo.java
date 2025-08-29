package Streams.Interview.StreamService;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;


@Slf4j
public class Demo {

    @Test
    public void test() {
        int[] a =  {-1, 1, 1, 2, 6, 4, 6, 7,3};
        int target = 9;

        Set<Integer> set = new HashSet<>();

        List<List<Integer>> list = new ArrayList<>();


        for (int i=0;i<a.length;i++){
            int remainingValue = target - a[i]; //  2
            if(set.contains(remainingValue)){
                list.add(Arrays.asList(remainingValue,a[i]));
            }
            set.add(a[i]);
        }

        System.out.println(list);








    }

}

