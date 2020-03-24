package com.wkq.order.modlue.novel.ui.activity.preview.threadPool;

public class PriorityRunnable implements Runnable {

    private final Priority priority;

    public PriorityRunnable(Priority priority) {
        this.priority = priority;
    }

    @Override
    public void run() {
      // nothing to do here.
    }

    public Priority getPriority() {
        return priority;
    }

}