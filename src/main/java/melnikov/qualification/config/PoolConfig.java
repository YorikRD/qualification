package melnikov.qualification.config;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//@ConfigurationProperties(prefix = "pool.config")
//@Configuration // this config of something
//public class PoolConfig {
//    @Setter
//    private int corePoolSize;
//    @Setter
//    private int maxPoolSize;
//    @Setter
//    private String threadNamePrefix;
//
//    @Bean // create what this method returns & put it to spring container
////    @Primary // means if where are to such objects in spring container - take this
//    @Qualifier("executor") // give unick name
//    public TaskExecutor threadPool() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(corePoolSize);
//        executor.setMaxPoolSize(maxPoolSize);
//        setThreadNamePrefix(threadNamePrefix);
//        executor.initialize();
//        return executor;
//    }
//
//
//}
