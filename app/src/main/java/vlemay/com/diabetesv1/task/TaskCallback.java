/* 
**
** Copyright 2014, Jules White
**
** 
*/
package vlemay.com.diabetesv1.task;

public interface TaskCallback<T> {

    public void success(T result);

    public void error(Exception e);

}
