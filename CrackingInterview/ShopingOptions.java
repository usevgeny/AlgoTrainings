package CrackingInterview;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ShopingOptions {
    int result = 0;
// An Amazon customer wants to buy a pair of jeans, a pair of shoes, a skirt, and a top but has 
//    a limited budget in dollars. Given different pricing options for each product, determine how 
//    many options our customer has to buy 1 of each product. You cannot spend more money 
//    than the budgeted amount. 

    public static int[] priceOfJeans = new int[] { 2, 3 };
    public static int[] priceOfShoes = new int[] { 4 };
    public static int[] priceOfSkirts = new int[] { 2, 3 };
    public static int[] priceOfTops = new int[] { 1, 2 };
    public static int budget = 10;

    public static int[][] allPrices = new int[][] {
            priceOfJeans,
            priceOfShoes,
            priceOfSkirts,
            priceOfTops
    };

    public static void main(String[] args) {
        System.out.println(countOptions(allPrices, budget));
        System.out.println(getNumberOfOptions(priceOfJeans, priceOfShoes,priceOfSkirts,priceOfTops, budget));
    }

    public static int countOptions(int[][] allPrices, int budget) {
        int count = 0;
        // Use recursion to iterate over all possible combinations
        count = countCombinations(allPrices, 0, 0, budget);
        return count;
    }

    private static int countCombinations(int[][] allPrices, int itemIndex, int currentCost, int budget) {
        // Base case: if we have considered all items
        if (itemIndex == allPrices.length - 1) {
            return (currentCost <= budget) ? 1 : 0;
        }

        int count = 0;

        // Consider each price for the current item
        for (int price : allPrices[itemIndex]) {
            int newCost = currentCost + price;
            if (price > budget || newCost > budget) {
                continue;
            }
            count += countCombinations(allPrices, itemIndex + 1, newCost, budget);
        }

        return count;
    }

    public static int getNumberOfOptions(int[] priceOfJeans,
            int[] priceOfShoes, int[] priceOfSkirts, int[] priceOfTops, int dollars) {
        if (dollars < 1 || dollars > 1000000000)
            throw new RuntimeException("wrong value for budget");

        validate(priceOfJeans, "jeans");
        validate(priceOfShoes, "shoes");
        validate(priceOfSkirts, "skirts");
        validate(priceOfTops, "tops");
        int numberOfOptions = 0;
        for (int priceOfJean : priceOfJeans) {
            if (priceOfJean >= dollars)
                continue;
            for (int priceOfShoe : priceOfShoes) {
                if (priceOfJean + priceOfShoe >= dollars)
                    continue;
                for (int priceOfSkirt : priceOfSkirts) {
                    if (priceOfJean + priceOfShoe + priceOfSkirt >= dollars)
                        continue;
                    for (int priceOfTop : priceOfTops) {
                        if (priceOfJean + priceOfShoe + priceOfSkirt + priceOfTop <= dollars)
                            numberOfOptions += 1;
                    }
                }
            }
        }
        return numberOfOptions;
    }

    private static void validate(int[] array, String arrayName) {
        if (array.length < 1 || array.length > 1000)
            throw new RuntimeException("wrong size in array " + arrayName);

        for (int price : array) {
            if (price < 1 || price > 1000000000)
                throw new RuntimeException("wrong value in array " + arrayName);
        }
    }

}