package com.runflow.engine.impl.agenda;

import com.runflow.engine.ActivitiException;
import com.runflow.engine.Agenda;
import com.runflow.engine.ExecutionEntity;
import com.runflow.engine.ExecutionEntityImpl;
import com.runflow.engine.delegate.DefaultActivitiEngineAgenda;
import com.runflow.engine.impl.CommandContext;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.HasExecutionListeners;

public abstract class AbstractOperation implements Runnable {

    protected CommandContext commandContext;
    protected Agenda agenda;
    protected ExecutionEntityImpl execution;

    public AbstractOperation() {

    }

    public AbstractOperation(CommandContext commandContext, ExecutionEntityImpl execution) {
        this.commandContext = commandContext;
        this.execution = execution;
        this.agenda = commandContext.getAgenda();
    }

    /**
     * Helper method to match the activityId of an execution with a FlowElement of the process definition referenced by the execution.
     */
    protected FlowElement getCurrentFlowElement(final ExecutionEntity execution) {
        if (execution.getCurrentFlowElement() != null) {
            return execution.getCurrentFlowElement();
        } else if (execution.getCurrentActivityId() != null) {
            //通过id查找
//            String processDefinitionId = execution.getProcessDefinitionId();
//            org.activiti.bpmn.model.Process process = ProcessDefinitionUtil.getProcess(processDefinitionId);
//            String activityId = execution.getCurrentActivityId();
//            FlowElement currentFlowElement = process.getFlowElement(activityId, true);
//            return currentFlowElement;
        }
        return null;
    }

    /**
     * Executes the execution listeners defined on the given element, with the given event type.
     * Uses the {@link #execution} of this operation instance as argument for the execution listener.
     */
    protected void executeExecutionListeners(HasExecutionListeners elementWithExecutionListeners, String eventType) {
        executeExecutionListeners(elementWithExecutionListeners, execution, eventType);
    }

    /**
     * Executes the execution listeners defined on the given element, with the given event type,
     * and passing the provided execution to the {@link ExecutionListener} instances.
     */
    protected void executeExecutionListeners(HasExecutionListeners elementWithExecutionListeners,
                                             ExecutionEntity executionEntity, String eventType) {
      throw new ActivitiException("不支持监听器");
    }

    /**
     * Returns the first parent execution of the provided execution that is a scope.
     */
    protected ExecutionEntity findFirstParentScopeExecution(ExecutionEntity executionEntity) {
        throw new ActivitiException("不支持持久化");
    }

    public CommandContext getCommandContext() {
        return commandContext;
    }

    public void setCommandContext(CommandContext commandContext) {
        this.commandContext = commandContext;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(DefaultActivitiEngineAgenda agenda) {
        this.agenda = agenda;
    }

    public ExecutionEntityImpl getExecution() {
        return execution;
    }

    public void setExecution(ExecutionEntityImpl execution) {
        this.execution = execution;
    }

}