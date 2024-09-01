package pl.romczaj.opentelemetry.order.app;

import java.util.concurrent.Executor;
import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.core.task.support.ContextPropagatingTaskDecorator;

@Configuration
class ExecutorConfiguration {

    @Bean
    public Executor mailExecutor(TaskDecorator propagationTaskDecorator) {
        return new ThreadPoolTaskExecutorBuilder()
            .corePoolSize(5)
            .maxPoolSize(10)
            .queueCapacity(10)
            .threadNamePrefix("mail-executor-")
            .taskDecorator(propagationTaskDecorator)
            .build();
    }

    @Bean
    public Executor paymentExecutor(TaskDecorator propagationTaskDecorator) {
        return new ThreadPoolTaskExecutorBuilder()
            .corePoolSize(5)
            .maxPoolSize(10)
            .queueCapacity(10)
            .threadNamePrefix("payment-executor-")
            .taskDecorator(propagationTaskDecorator)
            .build();
    }

    @Bean
    public TaskDecorator propagationTaskDecorator() {
        return new ContextPropagatingTaskDecorator();
    }

}
