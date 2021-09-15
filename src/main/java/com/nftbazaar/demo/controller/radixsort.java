package com.nftbazaar.demo.controller;

import org.springframework.util.StopWatch;

import java.util.Arrays;

public class radixsort {
    public static void main(String[] args) {

        int[] array={103,78,78100,4,5678,1111,25,
                210,444,91,2,999,123,1000,888,2400,1001,10932,
        12,55,91,1324,34534,435345,2342,57856,324243,456463,7567567,23424,54646,12334,4535,75614,
        4354367,345655,341,91,63,90,12312345,53255,9045,1453,1907,1905,9,501,404,9052,1670,
        2560,4470,10101010,2020200,55060,32406,903345,123964,93456,49,3455456};
        //210,103,4,25,78
        //04,210,103,25,78
        //04,25,78,123,210
        System.out.println(Arrays.toString(array));
        for (int i=0; i<array.length-1; i++){
            for (int j=0; j<array.length-1; j++){
                if(array[j]%10 > array[j+1]%10){
                    int tmp = array[j+1];
                    array[j+1]= array[j];
                    array[j]= tmp;
                }
            }
        }
        System.out.println(Arrays.toString(array));

        for (int k=0; k<array.length-1;k++){
            for (int m=0; m<array.length-1; m++){
                if(array[m]/10 > array[m+1]/10){
                    int tmp = array[m+1];
                    array[m+1]= array[m];
                    array[m]= tmp;
                }
            }
        }

        System.out.println(Arrays.toString(array));
    }

}
