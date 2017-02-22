package net;

/**
 * Created by kevin on 2016/12/13.
 */
public interface NetRequest {
    void onOperaSuccess(String str);
    void onOperaFailure();
    void onOver();
}
