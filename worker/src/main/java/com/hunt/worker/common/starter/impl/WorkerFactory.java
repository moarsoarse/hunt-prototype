package com.hunt.worker.common.starter.impl;

import com.hunt.worker.common.starter.BasicAuthProperties;
import com.hunt.worker.common.starter.WorkerProperties;
import org.camunda.bpm.client.interceptor.auth.BasicAuthProvider;
import org.camunda.bpm.client.spring.impl.client.ClientConfiguration;
import org.camunda.bpm.client.spring.impl.client.ClientFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class WorkerFactory extends ClientFactory {

  @Autowired
  protected WorkerProperties workerProperties;

  @Override
  public void afterPropertiesSet() throws Exception {
    applyPropertiesFrom(workerProperties);
    addBasicAuthInterceptor();
    super.afterPropertiesSet();
  }

  protected void addBasicAuthInterceptor() {
    BasicAuthProperties basicAuth = workerProperties.getBasicAuth();
    if (basicAuth != null) {

      String username = basicAuth.getUsername();
      String password = basicAuth.getPassword();
      BasicAuthProvider basicAuthProvider = new BasicAuthProvider(username, password);

      getRequestInterceptors().add(basicAuthProvider);
    }
  }

  public void applyPropertiesFrom(WorkerProperties clientConfigurationProps) {
    ClientConfiguration clientConfiguration = new ClientConfiguration();
    if (clientConfigurationProps.getBaseUrl() != null) {
      clientConfiguration.setBaseUrl(clientConfigurationProps.getBaseUrl());
    }
    if (clientConfigurationProps.getWorkerId() != null) {
      clientConfiguration.setWorkerId(clientConfigurationProps.getWorkerId());
    }
    if (clientConfigurationProps.getMaxTasks() != null) {
      clientConfiguration.setMaxTasks(clientConfigurationProps.getMaxTasks());
    }
    if (clientConfigurationProps.getUsePriority() != null && !clientConfigurationProps.getUsePriority()) {
      clientConfiguration.setUsePriority(false);
    }
    if (clientConfigurationProps.getDefaultSerializationFormat() != null) {
      clientConfiguration.setDefaultSerializationFormat(clientConfigurationProps.getDefaultSerializationFormat());
    }
    if (clientConfigurationProps.getDateFormat() != null) {
      clientConfiguration.setDateFormat(clientConfigurationProps.getDateFormat());
    }
    if (clientConfigurationProps.getLockDuration() != null) {
      clientConfiguration.setLockDuration(clientConfigurationProps.getLockDuration());
    }
    if (clientConfigurationProps.getAsyncResponseTimeout() != null) {
      clientConfiguration.setAsyncResponseTimeout(clientConfigurationProps.getAsyncResponseTimeout());
    }
    if (clientConfigurationProps.getDisableAutoFetching() != null &&
        clientConfigurationProps.getDisableAutoFetching()) {
      clientConfiguration.setDisableAutoFetching(true);
    }
    if (clientConfigurationProps.getDisableBackoffStrategy() != null &&
        clientConfigurationProps.getDisableBackoffStrategy()) {
      clientConfiguration.setDisableBackoffStrategy(true);
    }
    setClientConfiguration(clientConfiguration);
  }

}