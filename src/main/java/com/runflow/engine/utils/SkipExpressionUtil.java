package com.runflow.engine.utils;

import com.runflow.engine.ActivitiException;
import com.runflow.engine.ExecutionEntity;

public class SkipExpressionUtil {

    public static boolean isSkipExpressionEnabled(ExecutionEntity execution, String skipExpression) {
        if (skipExpression == null) {
            return false;
        }
        return checkSkipExpressionVariable(execution);
    }


    private static boolean checkSkipExpressionVariable(ExecutionEntity execution) {
            throw new ActivitiException("不支持");
    }

}