package com.vangelis.activity;

/**
 * Function：singleInstance
 * Created on 2023/3/23.
 * Comment：
 *  SingleInstance就一个意思：当前的activity必定会在一个新的TASK中，且只有它一个Activity。有且只有一个。
 *  当它进行跳转的时候，就相当于是给intent附加了一个：FLAG_ACTIVITY_NEW_TASK 的标识
 *
 *  扩展知识：
 *      为什么当service跳转到activity的时候，会报错：Calling startActivity() from outside of an Activity context
 *                                              requires the FLAG_ACTIVITY_NEW_TASK flag.
 *                                              Is this really what you want?
 *
 *      因为，虽然service是context的扩展，也可以在service内部使用startactivity进行跳转，但是其实service并没有
 *      所存在的task栈，所以，系统并不知道新跳转的这个activity放入哪个栈中，所以就会报错这个。
 *          当然，解决办法就是：在intent的flag中新增：FLAG_ACTIVITY_NEW_TASK，意味着，这是个新的栈，放入即可。
 *
 *
 * @author Wangpei
 */
public class SingleInstanceActivity extends BaseActivity{
    @Override
    protected int setLayoutId() {
        return R.layout.single_instance_layout;
    }

    @Override
    protected String setActivityName() {
        return "SingleInstanceActivity";
    }
}