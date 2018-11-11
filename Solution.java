
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner scanner = new Scanner(System.in);
        String testCases = scanner.nextLine();
        int noOfTestCases = 0;
        boolean proceed = false;
        if((testCases != null) && !(testCases.isEmpty())) {
            noOfTestCases = Integer.valueOf(testCases);
            if(noOfTestCases < 0 || noOfTestCases > 50) {
                return;
            }
        }
        for(int tests = 0; tests < noOfTestCases; tests++) {
            String row1 = scanner.nextLine();
            String[] row1Arr = row1.split(" ");
            int matrixDimension = Integer.valueOf(row1Arr[0]);
            if(matrixDimension < 1 || matrixDimension > 100) {
                continue;
            }


            Map<String, Long> matrixMap = new HashMap<String, Long>();
            int noOfQueries = Integer.valueOf(row1Arr[1]);
            if(noOfQueries < 1 || noOfQueries > 1000) {
                continue;
            }
            for(int query = 0; query < noOfQueries; query++) {
                String q = scanner.nextLine();
                String[] qArr = q.split(" ");
                if(qArr[0].equalsIgnoreCase("UPDATE")) {
                    matrixMap = callUpdateMatrixRow(qArr, matrixMap);
                } else if(qArr[0].equalsIgnoreCase("QUERY")) {
                    long finalSum = callQueryMatrixMap(qArr, matrixMap, matrixDimension);
                    if(finalSum >= 0L) {
                        System.out.println(finalSum);
                    }
                } else {
                    continue;
                }
            }
        }
    }

    private static Map<String, Long> callUpdateMatrixRow(String[] qArr, Map<String, Long> matrixMap) {
        //key: x-y-z to be put into a hashMap against the given updateVal qArr[4]
        String key = qArr[1] + "-" + qArr[2] + "-" + qArr[3];
        if(matrixMap.containsKey(key)) {
            matrixMap.remove(key);
        }
        matrixMap.put(key, Long.valueOf(qArr[4]));
        return matrixMap;
    }

    private static long callQueryMatrixMap(String[] qArr, Map<String, Long> matrixMap, int matrixDimension) {
        long a1 = Long.valueOf(qArr[1]);
        long b1 = Long.valueOf(qArr[2]);
        long c1 = Long.valueOf(qArr[3]);
        long a2 = Long.valueOf(qArr[4]);
        long b2 = Long.valueOf(qArr[5]);
        long c2 = Long.valueOf(qArr[6]);


        long finalSum = 0L;
        Iterator<String> keys = matrixMap.keySet().iterator();
        while(keys.hasNext()) {
            String s = keys.next();
            String[] mapKeyArr = s.split("-");
            int a  = Integer.valueOf(mapKeyArr[0]);
            int b  = Integer.valueOf(mapKeyArr[1]);
            int c  = Integer.valueOf(mapKeyArr[2]);
            if((a >= a1)  && (a <= a2) && (a <= matrixDimension)) {
                if((b >= b1)  && (b <= b2) && (b <= matrixDimension)) {
                    if((c >= c1)  && (c <= c2) && (c <= matrixDimension)) {
                        Long val = matrixMap.get(s);
                        if(val != null) {
                            finalSum += val;
                        }
                    }
                }
            }
        }
        return finalSum;
    }
}