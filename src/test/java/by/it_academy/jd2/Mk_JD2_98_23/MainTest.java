package by.it_academy.jd2.Mk_JD2_98_23;

import org.junit.jupiter.api.*;



public class MainTest {


    @BeforeAll
    public static void beforeAll () {
        System.out.println("@BeforeAll");
    }


    @BeforeEach
    public  void beforeEach () {
        System.out.println("@BeforeEach");
    }



    @Test
    @DisplayName("Main Test  method 1")
    public void test() {

        Main main = new Main();
        Main.main(null);
        Assertions.assertEquals(3, main.inc(2));
    }

    @AfterEach
    public  void afterEach () {
        System.out.println("@AfterEach");
    }

    @AfterAll
    public static void afterAll () {
        System.out.println("@AfterAll");
    }
}
