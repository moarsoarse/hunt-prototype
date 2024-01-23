package com.hunt.worker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = WorkerPropertiesTest.class)
@EnableConfigurationProperties(WorkerProperties.class)
//@TestPropertySource(locations = "classpath:application.yml")
public class WorkerPropertiesTest implements InitializingBean {

    @Mock
    @Autowired
    private WorkerProperties workerProperties;

    @Test
    public void testApplicationProperties() {
        var entities = workerProperties.getEntities();
        // Assuming workerProperties has getters for the properties
        // You can use Mockito to verify that the properties are set correctly
        // For example:
        // Mockito.verify(workerProperties).getSomeProperty();
        // Mockito.verify(workerProperties).getAnotherProperty();
        // Add assertions to compare the expected values with the actual values
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        testApplicationProperties();
    }
}