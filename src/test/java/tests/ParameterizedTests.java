package tests;

import models.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ParameterizedTests {

    @BeforeAll // Запускается перед запуском класса
    public static void  beforeAllTest() {
        // код
    }

    @BeforeEach // Запуск перед каждым тестом
    public void setUp() {
        // код
    }

    @AfterEach // Запуск после каждого теста
    public void tearDown() {
        // код
    }

    @Test
    @Disabled // Будет проигнорирован
    public void disableTest() {
        // код
    }


    // В качестве примера создадим некий список с электронными адресами пользователей
    List<String> userList = new ArrayList<>() {{
        add("okulov.m.i@gmail.com");
        add("petrov.a.v@gmail.com");
        add("ivanov.i.i@gmail.com");
        add("kalugin.v.i@gmail.com");
        add("lobov.r.b@gmail.com");
        add("yartsev.a.a@gmail.com");
        add("soev.m.m@gmail.com");
        add("pluzhnikov.g.v@gmail.com");
    }};

    @DisplayName("Проверка списка на наличие указанных пользователей") // Человекочитаемое название теста
    @ParameterizedTest
    @CsvSource({"okulov.m.i@gmail.com", "kalugin.v.i@gmail.com", "example@gmail.com"}) // Данные для параметризованного теста передаем в аннотацию
    public void checkUsersInList(String email) {
        Assertions.assertTrue(userList.contains(email));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/user.csv") // Данные для параметризованного теста берем из файла
    public void checkUsersInListFromFile(String email) {
        Assertions.assertTrue(userList.contains(email));
    }

    private static Stream<Arguments> userTest() {
        return Stream.of(
                Arguments.of(new User("okulov.m.i@gmail.com")),
                Arguments.of(new User("ivanov.i.i@gmail.com")),
                Arguments.of(new User("example@gmail.com"))
        );
    }

    @ParameterizedTest
    @MethodSource("userTest") // Перебираем стрим аргументов из переданного в аннтоацию метода
    public void checkUsersInListWithMethod(User user) {
        Assertions.assertTrue(userList.contains(user.getEmail()));
        // Для коммита
    }
}
