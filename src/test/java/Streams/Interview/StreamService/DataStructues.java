package Streams.Interview.StreamService;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class DataStructues {

    int[] a = {64, 34, 25, 12, 22, 11, 90};

    @Test
    void bubbleSort(){

        for(int i=0;i<a.length;i++){
            for(int j=0;j<a.length-1;j++){
                if(a[j] < a[j+1]){
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }

        }
        System.out.println(Arrays.toString(a));
    }


    @Test
    void  selectionSort(){
        // first the Minimum element and put it in the first position and repeat.

        for(int i=0;i<a.length-1;i++){
            for(int j=i+1;j<a.length;j++){
                if(a[i] > a[j]){
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(a));

    }


    @Test
    void LinearSearch(){
        int findValue = 90;
        for(int i=0;i<a.length;i++){
            if(a[i] == 90){
                System.out.println(i);
            }
        }
    }

    @Test
    void binarySearch(){
        int target = 90;
        for(int i =0;i<a.length;i++){
            for(int j=0;j<a.length-1;j++){
                if(a[j]>a[j+1]){
                    int temp = a[j];
                    a[j] =a[j+1];
                    a[j+1] = temp;
                }
            }
        }

        System.out.println("Arrays :"+ Arrays.toString(a));


        int left = 0;
        int right = a.length-1;

        while (left<=right){
            int mid = left + (right-left)/2;
          if(a[mid] == target) {
              System.out.println("index :"+ mid);
              break;
          }
          else if (a[mid] < target) left = mid +1;
          else right = mid-1;
        }

    }
}
