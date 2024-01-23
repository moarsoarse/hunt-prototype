package com.hunt.worker.impl.handler.hugger;

import org.springframework.stereotype.Component;

import java.util.TreeMap;

@Component
public class Extract {


    public Before<String> string() {
        return (externalTask, varName) -> externalTask.getVariable(varName).toString();
    }

    public Before<TreeMap<String, Object>> entity() {
        return (externalTask, varName) -> externalTask.getVariable(varName);
    }

}
