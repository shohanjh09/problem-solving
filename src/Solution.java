import java.io.*;
import java.util.*;

class Solution {

    public static String makeString(char ch, int count) {
        String s = "";

        for(int i = 0; i < count; i++){
            s += ch;
        }

        return s;
    }

    public static String frequencySort(String s) {
        HashMap<Character, Integer> charCount = new HashMap<Character, Integer>();

        // make the map with the character count
        for(char ch : s.toCharArray()){
            charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);
        }

        List<Character> list = new ArrayList<Character>(charCount.keySet());
        // sorted the list with the most occurrence
        Collections.sort(list, (ch1, ch2) -> charCount.get(ch1) ==  charCount.get(ch2) ? ch1.compareTo(ch2) : charCount.get(ch2) - charCount.get(ch1));

        String result = new String();

        for(char ch : list){
            result += makeString(ch, charCount.get(ch));
        }

        return result;

    }

    public static int minimumAverageDifference(int[] nums) {
        int length = nums.length;

        // check the base case
        if(length < 2){
            return 0;
        }

        int minIndex = 0;
        long sum = 0;
        long partialSum = 0;
        long difference = Integer.MAX_VALUE;

        // calculte the sum of the given value
        for(int i = 0; i < length; i++){
            sum += nums[i];
        }

        System.out.println(sum);

        for(int i = 0; i < length; i++){
            int first = i + 1;
            int last = length - i - 1;

            // addind the element to the partiasum and removing the element from the overall sum like sliding window
            partialSum += nums[i];
            sum -= nums[i];

            long avgSum = last > 0 ? (sum / last) : 0;
            long partialAvgSum = partialSum / first;

            // finding the average diff
            long diff = Math.abs(avgSum - partialAvgSum);

            // checking the difference
            if(diff < difference){
                minIndex = i;
                difference = diff;
            }
        }

        return minIndex;
    }

    public static void main(String[] args) throws IOException {
        //int[] nums = {2,5,3,9,5,3};
        File file = new File("input.txt");
        byte[] bytes = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(bytes);
        fis.close();
        String[] valueStr = new String(bytes).trim().split(",");
        System.out.println("The length is: " + valueStr.length);
        int[] tall = new int[valueStr.length];
        for (int i = 0; i < valueStr.length; i++){
            tall[i] = Integer.parseInt(valueStr[i]);
            //System.out.println(tall[i]);
        }

        //int[] nums = {1,2};
        System.out.println(minimumAverageDifference(tall));
    }
}