package com.example.study;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ConditionalTest {
    @Test
    void conditional() {
        // true
        ApplicationContextRunner contextRunner = new ApplicationContextRunner();
        contextRunner.withUserConfiguration(Config1.class)
                .run(context -> {
                    assertThat(context).hasSingleBean(MyBean.class);
                    assertThat(context).hasSingleBean(Config1.class);
                });

        // false
        ApplicationContextRunner contextRunner2 = new ApplicationContextRunner();
        contextRunner2.withUserConfiguration(Config2.class)
                .run(context -> {
                    assertThat(context).doesNotHaveBean(MyBean.class);
                    assertThat(context).doesNotHaveBean(Config2.class);
                });
    }
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional{
        boolean value();
    }

//    @Retention(RetentionPolicy.RUNTIME)
//    @Target(ElementType.TYPE)
//    @Conditional(Config1.TrueCondition.class)
//    @interface TrueConditional{}

//    @Retention(RetentionPolicy.RUNTIME)
//    @Target(ElementType.TYPE)
//    @Conditional(Config2.FalseCondition.class)
//    @interface FalseConditional{}


    @Configuration
    @BooleanConditional(true)
    static class Config1 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }

//        static class TrueCondition implements Condition {
//            @Override
//            public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//                return true;
//            }
//        }
    }
    @Configuration
    @BooleanConditional(false)
    static class Config2 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }

//        static class FalseCondition implements Condition {
//            @Override
//            public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//                return false;
//            }
//        }
    }

    static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(BooleanConditional.class.getName());
            return (boolean) annotationAttributes.get("value");
        }
    }

    static class MyBean {

    }
}
