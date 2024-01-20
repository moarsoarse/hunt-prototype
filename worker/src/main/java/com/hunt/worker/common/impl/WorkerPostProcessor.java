package com.hunt.worker.common.impl;


import com.hunt.worker.common.functions.Worker;
import com.hunt.worker.common.impl.util.WorkerLoggerUtil;
import lombok.SneakyThrows;
import org.camunda.bpm.client.spring.impl.subscription.SubscriptionConfiguration;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import java.util.Map;

public class WorkerPostProcessor implements BeanDefinitionRegistryPostProcessor{

    protected static final WorkerLoggerUtil LOG = WorkerLoggerUtil.WORKER_LOGGER;

    protected Class<? extends WorkerSubscription> workerSubscription;

    public WorkerPostProcessor() {
        this(WorkerSubscription.class);
    }

    public WorkerPostProcessor(Class<? extends WorkerSubscription> workerSubscription) {
        this.workerSubscription = workerSubscription;
    }

   @SneakyThrows
   @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ListableBeanFactory listableBeanFactory = (ListableBeanFactory) registry;
        Map<String, Worker> workerBeans = listableBeanFactory.getBeansOfType(Worker.class);
        LOG.workerBeansFound(Worker.class, workerBeans);


        for (Map.Entry<String, Worker> workerBean: workerBeans.entrySet()) {
            Worker worker = workerBean.getValue();


            ExternalTaskHandler handlerBean = worker.toHandler();
            String handlerBeanName = workerBean + "Handler";
            BeanDefinition handlerBeanDefinition = BeanDefinitionBuilder.genericBeanDefinition(handlerBean.getClass()).getBeanDefinition();
            registry.registerBeanDefinition(handlerBeanName, handlerBeanDefinition);

            //context.registerBean(handlerBeanName, ExternalTaskHandler.class, workerBean::toHandler);
            SubscriptionConfiguration subscriptionConfiguration = getSubscriptionConfiguration(worker);
            BeanDefinition workerSubscriptionBeanDefinition = getSubscriptionBeanDefinition(handlerBeanName, subscriptionConfiguration);
            String subscriptionBeanName = workerBean + "Subscription";
            registry.registerBeanDefinition(subscriptionBeanName, workerSubscriptionBeanDefinition);
            LOG.beanRegistered(subscriptionBeanName, handlerBeanName);
        }
    }



    private BeanDefinition getSubscriptionBeanDefinition(String handlerBeanName, SubscriptionConfiguration subscriptionConfiguration) {

        return BeanDefinitionBuilder.genericBeanDefinition(workerSubscription)
                .addPropertyReference("externalTaskHandler", handlerBeanName)
                .addPropertyValue("subscriptionConfiguration", subscriptionConfiguration)
                .setDestroyMethodName("closeInternally")
                .getBeanDefinition();
    }


    private SubscriptionConfiguration getSubscriptionConfiguration(Worker<?, ?> workerBean) {
        String entityName = workerBean.getEntityName();
        String workerName = workerBean.getClass().getInterfaces()[0].getSimpleName();
        String topic = workerName + "." + entityName;
        SubscriptionConfiguration workerSubscriptionConfiguration = new SubscriptionConfiguration();
        workerSubscriptionConfiguration.setTopicName(topic);
        workerSubscriptionConfiguration.setAutoOpen(true);
        workerSubscriptionConfiguration.setIncludeExtensionProperties(false);
        workerSubscriptionConfiguration.setLocalVariables(true);
        workerSubscriptionConfiguration.setWithoutTenantId(true);
        return workerSubscriptionConfiguration;
    }

/*    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;
    }*/
}
