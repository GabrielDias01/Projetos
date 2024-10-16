import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import com.example.*;

public class TestCalculadora {

    Calculadora calc = new Calculadora();

    @Test
    public void testeSoma() {
        double resultado = calc.soma(3, 8);
        assertEquals(11, resultado, 0);

    }

    @Test
    public void testeSubtracao() {
        double resultado = calc.subtracao(10, 3);
        assertEquals(7, resultado, 0);
    }

    @Test
    public void testeMultiplicacao() {
        double resultado = calc.multiplicacao(5, 3);
        assertEquals(15, resultado, 0);
    }

    @Test
    public void testeDivisao() {
        assertThrows(IllegalArgumentException.class, () -> calc.divisao(10, 0));
    }
}
