package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class BurgerOperationsTest {

    @Test
    public void setBuns_setsBunField() {
        Burger burger = new Burger();
        Bun bun = new Bun("white bun", 200);

        burger.setBuns(bun);

        assertSame(bun, burger.bun);
    }

    @Test
    public void addIngredient_addsToIngredientsList() {
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);

        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
        assertSame(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredient_removesByIndex() {
        Burger burger = new Burger();
        Ingredient first = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        Ingredient second = new Ingredient(IngredientType.FILLING, "cutlet", 100);
        burger.addIngredient(first);
        burger.addIngredient(second);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertSame(second, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredient_movesFromIndexToNewIndex() {
        Burger burger = new Burger();
        Ingredient first = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        Ingredient second = new Ingredient(IngredientType.SAUCE, "sour cream", 200);
        Ingredient third = new Ingredient(IngredientType.FILLING, "cutlet", 100);
        burger.addIngredient(first);
        burger.addIngredient(second);
        burger.addIngredient(third);

        burger.moveIngredient(2, 0);

        assertSame(third, burger.ingredients.get(0));
        assertSame(first, burger.ingredients.get(1));
        assertSame(second, burger.ingredients.get(2));
    }
}

