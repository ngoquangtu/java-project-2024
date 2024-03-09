public class DemoBuilderPattern {
    public static void main(String[] args)
    {
        MealBuilder mealBuilder =new MealBuilder();
        Meal vegMeal = mealBuilder.prepare();
        vegMeal.showItems();
        System.out.println("Total Cost: "+vegMeal.getCost()); 
        Meal nonVegMeal = mealBuilder.prepareNonMeal();
        nonVegMeal.showItems();
        System.out.println("Total Cost: "+nonVegMeal.getCost());    
        
    }
    
}
