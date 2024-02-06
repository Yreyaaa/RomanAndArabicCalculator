package org.kata.yreya;

import org.junit.jupiter.api.Assertions;


class MainTest {

    @org.junit.jupiter.api.Test
    public void testCalcPositive_1() throws Exception {

        String input = "3 + 8";

        String expected = "11";

        Assertions.assertEquals(expected, Main.calc(input));
    }

    @org.junit.jupiter.api.Test
    public void testCalcPositive_2() throws Exception {

        String input = "IX * IX";

        String expected = "LXXXI";

        Assertions.assertEquals(expected, Main.calc(input));
    }

    @org.junit.jupiter.api.Test
    public void testCalcPositive_3() throws Exception {

        String input = "VIII / III";

        String expected = "II";

        Assertions.assertEquals(expected, Main.calc(input));
    }

    @org.junit.jupiter.api.Test
    public void testCalcPositive_4() throws Exception {

        String input = "X - IX";

        String expected = "I";

        Assertions.assertEquals(expected, Main.calc(input));
    }


    @org.junit.jupiter.api.Test
    public void testCalcNegative_1() {

        String input = "3 + abc";

        Exception exception = Assertions.assertThrows(Exception.class, () -> Main.calc(input));

        String expectedMessage = "Неверный формат ввода, пример корректных входных данных: 3 + 6 или X * IV";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @org.junit.jupiter.api.Test
    public void testCalcNegative_2() {

        String input = "3 + 11";

        Exception exception = Assertions.assertThrows(Exception.class, () -> Main.calc(input));

        String expectedMessage = "Неверный формат ввода, пример корректных входных данных: 3 + 6 или X * IV";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @org.junit.jupiter.api.Test
    public void testCalcNegative_3() {

        String input = "X + 4";

        Exception exception = Assertions.assertThrows(Exception.class, () -> Main.calc(input));

        String expectedMessage = "Неверный формат ввода, пример корректных входных данных: 3 + 6 или X * IV";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @org.junit.jupiter.api.Test
    public void testCalcNegative_4() {

        String input = "IX - IX";

        Exception exception = Assertions.assertThrows(Exception.class, () -> Main.calc(input));

        String expectedMessage = "C римскими числами полученно значение выражения меньше 1";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


}
