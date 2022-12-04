import java.util.*;

interface Category{
    public List<Integer> getDices();
}

class ChanceCategory implements Category{
    int[] dices;

    public ChanceCategory(int[] dices){
        this.dices = dices;
    }

    public List<Integer> getDices(){
        List<Integer> list = new ArrayList<Integer>();

        for(int dice: dices){
            list.add(dice);
        }

        return list;
    }
}

class NumberCategory implements Category{
    int[] dices;
    int catId;

    public NumberCategory(int[] dices, int catId){
        this.dices = dices;
        this.catId = catId;
    }

    public List<Integer> getDices(){
        List<Integer> list = new ArrayList<Integer>();

        for(int dice: dices){
            if(dice == this.catId){
                list.add(dice);
            }
        }

        return list;
    }
}

class YatzyCategory implements Category{
    int[] dices;
    int UPPER_VALUE = 50;
    int LOWER_VALUE = 0;

    public YatzyCategory(int[] dices){
        this.dices = dices;
    }

    public List<Integer> getDices(){
        HashSet<Integer> set = new HashSet<Integer>();
        List<Integer> list = new ArrayList<Integer>();

        for(int dice: dices){
            set.add(dice);
        }

        if(set.size() == 1){
            list.add(this.UPPER_VALUE);
        } else {
            list.add(this.LOWER_VALUE);
        }

        return list;
    }
}

class PairCategory implements Category{
    int[] dices;

    public PairCategory(int[] dices){
        this.dices = dices;
    }

    public List<Integer> getDices(){
        List<Integer> list = new ArrayList<Integer>();
        TreeMap<Integer, Integer> pairDices = new TreeMap<Integer, Integer>();

        for(int dice: dices){
            pairDices.put(dice, pairDices.getOrDefault(dice, 0) + 1);
        }

        for(int key : pairDices.descendingKeySet()){
            if(pairDices.get(key) / 2 > 0){
                list.add(key * 2);
                break;
            }
        }

        return list;
    }
}

class TwoPairCategory implements Category{
    int[] dices;

    public TwoPairCategory(int[] dices){
        this.dices = dices;
    }

    public List<Integer> getDices(){
        List<Integer> list = new ArrayList<Integer>();
        TreeMap<Integer, Integer> pairDices = new TreeMap<Integer, Integer>();

        for(int dice: dices){
            pairDices.put(dice, pairDices.getOrDefault(dice, 0) + 1);
        }

        for(int key : pairDices.descendingKeySet()){
            if(pairDices.get(key) / 2 > 0){
                int divResult = pairDices.get(key) / 2;
                list.add(key * 2 * divResult);
            }
        }

        return list;
    }
}

class Score {
    Category category;

    public Score(Category category){
        this.category = category;
    }

    public int getSum(){
        List<Integer> dices = this.category.getDices();
        int sum = 0;

        for(int dice: dices){
            sum += dice;
        }

        return sum;
    }
}


public class Solution {
    public static String[] NUMBER_CATEGORY_LIST = {"", "one", "two", "three", "four", "five", "six"};
    public static String CHANCE = "chance";
    public static String YATZY = "yatzy";
    public static String PAIR = "pair";
    public static String TWO_PAIR = "two-pair";

    public static int getCategoryId(String categoryName){
       for(int i = 0; i < NUMBER_CATEGORY_LIST.length; i++){
           if(NUMBER_CATEGORY_LIST[i].equals(categoryName)){
               return i;
           }
       }

       return -1;
    }

    public static int getScore(int[] dices, String categoryName){
        int catId = getCategoryId(categoryName);
        Category category;

        if(catId > 0){
            category = new NumberCategory(dices, catId);
        } else if(categoryName.equals(CHANCE)){
            category = new ChanceCategory(dices);
        } else if(categoryName.equals(YATZY)){
            category = new YatzyCategory(dices);
        } else if(categoryName.equals(PAIR)){
            category = new PairCategory(dices);
        } else{
            category = new TwoPairCategory(dices);
        }

        Score score = new Score(category);

        return score.getSum();
    }


    public static void main(String[] args) {
        int sum = getScore(new int[]{1,1,1,2, 2}, "two");
        System.out.println(sum);
    }
}
