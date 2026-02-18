package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {

    @Parameterized.Parameter(0)
    public float bunPrice;

    @Parameterized.Parameter(1)
    public float[] ingredientPrices;

    @Parameterized.Parameter(2)
    public float expected;

    @Parameterized.Parameters(name = "bun={0}, ingredients={1} => price={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0f, new float[]{}, 0f},
                {100f, new float[]{}, 200f},
                {100f, new float[]{50f}, 250f},
                {200f, new float[]{100f, 200f, 300f}, 1000f},
        });
    }

    @Test
    public void getPrice_calculatesBunDoublePlusIngredientsSum() {
        Burger burger = new Burger();
        burger.setBuns(new Bun("any bun", bunPrice));
        for (Ingredient ingredient : buildIngredients(ingredientPrices)) {
            burger.addIngredient(ingredient);
        }

        assertEquals(expected, burger.getPrice(), 0.0001f);
    }

    private static List<Ingredient> buildIngredients(float[] prices) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < prices.length; i++) {
            ingredients.add(new Ingredient(IngredientType.SAUCE, "ingredient-" + i, prices[i]));
        }
        return ingredients;
    }
}

