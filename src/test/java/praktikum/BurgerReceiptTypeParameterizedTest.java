package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerReceiptTypeParameterizedTest {

    @Parameterized.Parameter(0)
    public IngredientType type;

    @Parameterized.Parameter(1)
    public String expectedLowercase;

    @Parameterized.Parameters(name = "type={0} => \"{1}\"")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "sauce"},
                {IngredientType.FILLING, "filling"},
        });
    }

    @Test
    public void getReceipt_includesIngredientLineWithLowercasedType_andUsesDependencies() {
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("test bun");
        when(bun.getPrice()).thenReturn(100f);

        Ingredient ingredient = mock(Ingredient.class);
        when(ingredient.getType()).thenReturn(type);
        when(ingredient.getName()).thenReturn("test ingredient");
        when(ingredient.getPrice()).thenReturn(50f);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains(String.format("= %s %s =%n", expectedLowercase, "test ingredient")));

        verify(bun, times(2)).getName();
        verify(bun, times(1)).getPrice();
        verify(ingredient, times(1)).getType();
        verify(ingredient, times(1)).getName();
        verify(ingredient, times(1)).getPrice();
    }
}

