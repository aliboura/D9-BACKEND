package dz.djezzy.site.acceptance.config;

import dz.djezzy.site.acceptance.business.services.CategoriesService;
import dz.djezzy.site.acceptance.business.services.DecisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class CollectionConfig {

    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private DecisionService decisionService;

    @Bean
    public HashMap<String, Integer> categoryLabels() {
        return categoriesService.getLabelMaps();
    }

    @Bean
    public HashMap<String, Integer> decisionLabels() {
        return decisionService.getDecisionLabel(1);
    }
}
