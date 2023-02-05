package com.example.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {
    @Test
    void helloController() {
        // HelloService = test stub
        HelloController helloController = new HelloController(name -> name);

        String test = helloController.hello("Test");

        Assertions.assertThat(test).isEqualTo("Test");
    }

    @Test
    void failsHelloController() {
        // HelloService = test stub
        HelloController helloController = new HelloController(name -> name);

        Assertions.assertThatThrownBy(() -> helloController.hello(""))
                .isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> helloController.hello(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
