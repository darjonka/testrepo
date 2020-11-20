import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {

        Map<Long, Long> occurrence = new HashMap<>();
        int lastCheckedIdx = -1;

        for (long i=1; i <= arr.get(arr.size()-1); i = i*r) {
            long count = 0;
            System.out.println("i is "+i);

            System.out.println("Last checked idx "+lastCheckedIdx);

            int j = lastCheckedIdx +1;
            while (j < arr.size() && i == arr.get(j).longValue()) {
                System.out.println("MATCH");
                 count++;
                lastCheckedIdx = j;
                j++;
            }

            occurrence.put(i, count);
        }

        System.out.println("Occurrence "+occurrence);


        if ((r == 1) &&  (occurrence.keySet().size() == 1)) {
            int len = arr.size();
            int variations = 0;
            for (int j=len; j>= len -3 +1 ; j--) {
                variations *= j;
            }
            return variations;
        }


        long sum = 0;
        long power = 1;
        long power2 = r*r;


        while (occurrence.containsKey(power * power2))  {
            sum += occurrence.get(power) * occurrence.get(power *r) * occurrence.get(power * power2);

            power = power * r;
        }

        return sum;



    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        String[] arrItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Long> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            long arrItem = Long.parseLong(arrItems[i]);
            arr.add(arrItem);
        }

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
