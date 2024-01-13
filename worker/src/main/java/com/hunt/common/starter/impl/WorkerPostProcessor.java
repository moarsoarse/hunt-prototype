package com.hunt.common.starter.impl;

import com.hunt.common.handler.function.WorkerFunction;
import com.hunt.common.handler.function.converter.HandlerFactory;
import com.hunt.common.starter.impl.util.WorkerLoggerUtil;
import org.camunda.bpm.client.spring.impl.subscription.SubscriptionConfiguration;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;

import java.util.Map;

public class WorkerPostProcessor implements BeanDefinitionRegistryPostProcessor {

  protected static final WorkerLoggerUtil LOG = WorkerLoggerUtil.WORKER_LOGGER;

  @Value("${spring.application.name}")
  protected String applicationName;

  protected Class<? extends WorkerSubscription> workerTopicSubscription;
  public WorkerPostProcessor() {
    this(WorkerSubscription.class);
  }

  public WorkerPostProcessor(Class<? extends WorkerSubscription> workerTopicSubscription) {
    this.workerTopicSubscription = workerTopicSubscription;
  }

  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
    ListableBeanFactory listableBeanFactory = (ListableBeanFactory) registry;
    Map<String, WorkerFunction> workerBeans = listableBeanFactory.getBeansOfType(WorkerFunction.class);
    LOG.workerBeansFound(WorkerFunction.class, workerBeans);

    for (String workerBeanName : workerBeans.keySet()) {
      WorkerFunction workerBean = workerBeans.get(workerBeanName);
      BeanDefinition workerBeanDefinition = registry.getBeanDefinition(workerBeanName);
      HandlerFactory handlerFactory = new HandlerFactory(workerBean);
      String topicName = String.join(".",applicationName,handlerFactory.getTopicType(),handlerFactory.getEntityName());
      GenericBeanDefinition handlerBeanDefinition = new GenericBeanDefinition();
      handlerBeanDefinition.setBeanClass(ExternalTaskHandler.class);
      handlerBeanDefinition.setInstanceSupplier(handlerFactory::getHandler);
      registry.registerBeanDefinition("testHandler", handlerBeanDefinition);
      SubscriptionConfiguration workerSubscriptionConfiguration = new SubscriptionConfiguration();
      workerSubscriptionConfiguration.setTopicName("testTopic");
      workerSubscriptionConfiguration.setAutoOpen(true);
      workerSubscriptionConfiguration.setIncludeExtensionProperties(false);
      workerSubscriptionConfiguration.setLocalVariables(true);
      workerSubscriptionConfiguration.setWithoutTenantId(true);


      BeanDefinition subscriptionBeanDefinition = getSubscriptionBeanDefinition("testHandler", workerSubscriptionConfiguration);
      String subscriptionBeanName = workerBeanName + "Subscription";
      registry.registerBeanDefinition(subscriptionBeanName, subscriptionBeanDefinition);
      LOG.beanRegistered(subscriptionBeanName, workerBeanName);
    }
  }

  protected BeanDefinition getSubscriptionBeanDefinition(String beanName,
                                                         SubscriptionConfiguration subscriptionConfiguration) {
    return BeanDefinitionBuilder.genericBeanDefinition(workerTopicSubscription)
        .addPropertyReference("externalTaskHandler", beanName)
        .addPropertyValue("subscriptionConfiguration", subscriptionConfiguration)
        .setDestroyMethodName("closeInternally")
        .getBeanDefinition();
  }


  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
  }

}
