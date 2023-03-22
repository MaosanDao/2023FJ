# Service

## 定义

```java
service的讲解：
 1.特征点：
 service不是一个单独的进程（除非在注册文件中指定进程名字），否则它所在的进程就是应用的进程。
 2.service的停止
 除非service手动调用stopService和内部主动调起stopSelf，否则service一直运行。
 3.bindService
 bindService的生命周期和其绑定的组件相关联。
```

## 启动方式异同点

```java
启动service的方式有3种
  startService
  bindService
  同时开启上面2个
```

## 生命周期

```java
IntentService生命周期

start的生命周期：（但是如果先bind再start，则只会执行onStartCommand和onHandleIntent）
    构造方法
    onCreate
    onStartCommand
    onHandleIntent
    onDestroy（自己就销毁了）
bind的生命周期：
    构造方法
    onCreate
    onBind
    onUnbind
    onDestroy
    
Service生命周期
	构造方法
    onCreate
    onStartCommand
    onDestroy
    
   bind方式：
    构造方法
    onCreate
    onBind
    onUnbind
    onDestroy
```

## AIDL和IPC

```java
1.该服务定义在另外的一个APP A内部，且需要在注册文件中标明这个service的action
2.从APP B可以使用intent的setAction指定service，并将Intent的内容传递过来，交给这个service进行处理
3.处理完成后使用AIDL的机制，将这个实例的IBinder返回出去即可
```

## IntentService

```java
IntentService是简化版的Service，它不在需要手动关闭，每次执行完成后，会自己关闭
还有比较重要的一点就是，IntentService中的onHandleIntent里面，可以直接进行耗时操作，不会造成ANR，而service会ANR。

gogole直接将service和thread整合为一个方便的intentservice


主要在onHandleIntent中执行相关逻辑
也有两种启动方式：bind和start
    start的生命周期：（但是如果先bind再start，则只会执行onStartCommand和onHandleIntent）
        构造方法
        onCreate
        onStartCommand
        onHandleIntent
        onDestroy（自己就销毁了）
    bind的生命周期：
        构造方法
        onCreate
        onBind
        onUnbind
        onDestroy
```

## 前台服务

```java
运用场景：最常见的是音乐播放器，可以在通知栏中暂停、播放之类的操作
为啥用前台：因为后台运行的Service优先级很低，当内存不足的时候。系统会将后台Service进行回收

/**
 * 下面这个方法，是指：
 *      停止前台，但是不退出整个服务。 这个boolean表示是否取消掉前台服务的通知。false表示保留通知。
 *      此时服务变成了后台服务
 */
//stopForeground(false);
```

## 保活方案

```java
1.1像素Activity or Service，使用锁屏广播开启
2.JobScheduler
3.前台服务
4.互相唤醒IPC AIDL
5.系统中的onStartCommand的返回值，黏性服务，或者是通过监听系统的通知广播来开启应用（需要获取权限）
/**
 * onStartCommand这个方法，在startService首次和每一次都会触发
 *
 * @return 返回值有3种：
 * Service.START_STICKY=1：当service被kill了，系统会保留开始状态，当服务被重启，传过来的Intent可能为空
 * Service.START_NOT_STICKY=2：被kill了，系统不会重启
 * Service.START_REDELIVER_INTENT=3：系统自动重启了，并会传递之前的intent
 *
 * 默认返回为：Service.START_STICKY
 *
 */
```

## 和Thread的区别

```java
1.相同点
	都是执行的异步操作
2.不同点
	1.service是运行在主线程中，而thread则是在工作线程
	2.service不依赖于UI，当activity销毁， 进程还在，service依然可以运行；activity创建后，可以和service通信，创建binder实例，就可以调用service中的方法；service里需要创建子线程来处理耗时逻辑，否则会ANR；
	3.Thread依赖于UI，在activity中创建Thread，activity退出后就无法控制Thread；不同的activity之间无法控制同一个Thread；
```

## 参考文献

[Android Service详解(一)](https://juejin.cn/post/6844903781541347341)

[Android Service详解(二)](https://juejin.cn/post/6844903781931417614)

[Android 保活方案](https://juejin.cn/post/6844903464523268103#heading-11)	

[服务Service与线程Thread的区别](https://blog.csdn.net/Cricket_7/article/details/103468689)

