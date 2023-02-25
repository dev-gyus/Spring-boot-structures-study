package com.example.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {
    void simpleHelloService() {
        // given
        SimpleHelloService helloService = new SimpleHelloService(helloRepositoryStub);
        // when
        String ret = helloService.sayHello("Test");
        // then
        Assertions.assertThat(ret).isEqualTo("Hello Test");
    }

    private static HelloRepository helloRepositoryStub = new HelloRepository() {
            @Override
            public Hello findHello(String name) {
                return null;
            }

            @Override
            public void increaseCount(String name) {

            }
    };

    @Test
    void helloDecorator() {
        HelloDecorator decorator = new HelloDecorator(name -> name);
        String ret = decorator.sayHello("Testlll");
        Assertions.assertThat(ret).isEqualTo("*Test*");
    }
}
