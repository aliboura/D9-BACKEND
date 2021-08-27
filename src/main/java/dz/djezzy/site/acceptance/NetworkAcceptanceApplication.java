package dz.djezzy.site.acceptance;

import dz.djezzy.site.acceptance.business.data.audit.AuditorAwareImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

//@Import(SwaggerConfiguration.class)
@Configuration
@SpringBootApplication
@EnableAsync
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class NetworkAcceptanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetworkAcceptanceApplication.class, args);
    }

    /**
     * Audit configs
     *
     * @return AuditorAware Implementation
     */
    @Bean
    public AuditorAwareImpl auditorAware() {
        return new AuditorAwareImpl();
    }


}
