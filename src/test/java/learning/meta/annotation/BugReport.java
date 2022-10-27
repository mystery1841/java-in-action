package learning.meta.annotation;

public @interface BugReport {
    enum Status {UNCONFIRMED, CONFIRMED, FIXED, NOTABUG}
    boolean showStopper() default false;
    String assignedTo() default "[none]";
    Class<?> testCase() default Void.class;
    Status status() default Status.UNCONFIRMED;
    String[] reportedBy();
}
