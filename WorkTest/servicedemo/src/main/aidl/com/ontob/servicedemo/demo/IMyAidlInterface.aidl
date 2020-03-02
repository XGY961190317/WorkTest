// IMyAidlInterface.aidl
package com.ontob.servicedemo.demo;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);
   /**
   * Define your own method: show the progress of the current service
    */

    void showProgress();
}
