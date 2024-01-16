package com.hunt.worker.common.starter.impl;


import com.hunt.worker.common.functions.Worker;
import com.hunt.worker.common.starter.impl.util.WorkerLoggerUtil;
import org.camunda.bpm.client.spring.impl.subscription.SubscriptionConfiguration;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Map;

public class WorkerPostProcessor implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

  protected static final WorkerLoggerUtil LOG = WorkerLoggerUtil.WORKER_LOGGER;

  protected ApplicationContext applicationContext;

  protected Class<? extends WorkerSubscription> workerTopicSubscription;
  public WorkerPostProcessor() {
    this(WorkerSubscription.class);
  }

  public WorkerPostProcessor(Class<? extends WorkerSubscription> workerTopicSubscription) {
    this.workerTopicSubscription = workerTopicSubscription;
  }

 // @SneakyThrows
  @Override
  public void postProcessBeanDefinitionRegistry(@NotNull BeanDefinitionRegistry registry) throws BeansException {
      GenericApplicationContext context = (GenericApplicationContext) applicationContext;
      ListableBeanFactory listableBeanFactory = (ListableBeanFactory) registry;
      Map<String, Worker> workerBeans = listableBeanFactory.getBeansOfType(Worker.class);
      LOG.workerBeansFound(Worker.class, workerBeans);
      for (String workerBeanName : workerBeans.keySet()) {
          Worker workerBean = workerBeans.get(workerBeanName);
          ExternalTaskHandler handler = workerBean.toHandler();
          String handlerBeanName = workerBeanName + "Handler";
          
          BeanDefinition handlerBeanDefinition = BeanDefinitionBuilder.genericBeanDefinition(handler.getClass()).getBeanDefinition();
          registry.registerBeanDefinition(workerBeanName + "Handler2", handlerBeanDefinition);
          context.registerBean(handlerBeanName, ExternalTaskHandler.class, workerBean::toHandler);
          
          LOG.beanRegistered(handlerBeanName,workerBeanName);
          
          BeanDefinition workerSubscriptionBeanDefinition = getSubscriptionBeanDefinition(handlerBeanName,workerBean);
          
          String subscriptionBeanName = workerBeanName + "Subscription";
          registry.registerBeanDefinition(subscriptionBeanName, workerSubscriptionBeanDefinition);
          LOG.beanRegistered(subscriptionBeanName, handlerBeanName);
      }
  }
    
    @NotNull
    private BeanDefinition getSubscriptionBeanDefinition(String handlerBeanName,Worker workerBean) {
        SubscriptionConfiguration workerSubscriptionConfiguration = getSubscriptionConfiguration(workerBean);
        return BeanDefinitionBuilder.genericBeanDefinition(workerTopicSubscription)
                .addPropertyReference("externalTaskHandler", handlerBeanName)
                .addPropertyValue("subscriptionConfiguration", workerSubscriptionConfiguration)
                .setDestroyMethodName("closeInternally")
                .getBeanDefinition();
    }
    
    @NotNull
    private SubscriptionConfiguration getSubscriptionConfiguration(Worker<?,?> workerBean) {
        String entityName=workerBean.getEntityName();
        String workerName = workerBean.getClass().getInterfaces()[0].getSimpleName();
        
        String topic = applicationContext.getApplicationName()+"."+ workerName+"."+entityName;
        SubscriptionConfiguration workerSubscriptionConfiguration = new SubscriptionConfiguration();
        workerSubscriptionConfiguration.setTopicName(topic);
        workerSubscriptionConfiguration.setAutoOpen(true);
        workerSubscriptionConfiguration.setIncludeExtensionProperties(false);
        workerSubscriptionConfiguration.setLocalVariables(true);
        workerSubscriptionConfiguration.setWithoutTenantId(true);
        return workerSubscriptionConfiguration;
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
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;
    }
}
