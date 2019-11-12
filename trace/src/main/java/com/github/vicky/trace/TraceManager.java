package com.github.wkigen.trace;

import android.os.Trace;
import android.util.Log;
import android.view.Choreographer;

public class TraceManager {
    private static final String TAG = TraceManager.class.getSimpleName();

    private long mjinkTime = 100;

    private long mLastFrameTime;
    private long mStartTime;

    private static TraceManager mInstance;

    private TraceManager(){ }

    public static TraceManager getInstance(){
        if (mInstance == null){
            synchronized (TraceManager.class){
                if (mInstance == null){
                    mInstance = new TraceManager();
                }
            }
        }
        return mInstance;
    }

    public void startTrace(){
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long frameTime) {
                long fps = 1000000000/(frameTime - mLastFrameTime);
                Log.e(TAG,"fps:"+fps);

                mLastFrameTime = frameTime;
                Choreographer.getInstance().postFrameCallback(this);
            }
        });
    }

    public void traceStartMethod(){
        mStartTime = System.currentTimeMillis();
    }

    public void traceEndMethod(){
        long offTime = System.currentTimeMillis() - mStartTime;
        if (offTime > mjinkTime) {
            Log.e(TAG,"-----------------------------Trace-start----------------------------");
            Log.e(TAG, "class file:" + Thread.currentThread().getStackTrace()[3].getFileName());
            Log.e(TAG, "class:" + Thread.currentThread().getStackTrace()[3].getClassName());
            Log.e(TAG, "method:" + Thread.currentThread().getStackTrace()[3].getMethodName());
            Log.e(TAG, "method cost:" + offTime);
            Log.e(TAG,"-----------------------------Trace-end----------------------------");
        }
    }
}
