public class Helper {

    public void readDataFromFile(){
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
