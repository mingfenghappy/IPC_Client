# Android IPC Client DEMO
##项目结构
该项目是IPC方式通信的Client端demo，项目分为2部分，包含ipc_messenger_client,aidl client.
###ipc_messenger_client
通过aidl方式实现IPC通信client demo
###aidl client(即app)
通过aidl方式实现IPC通信 client demo

Android Aidl是实现IPC通信的一种重要方式，花时间深入研究一下

IPC的根本目的还是为了实现函数的调用，即使是传递数据也是要通过函数调用的方式，为什么呢？因为程序运行总是要知道状态，要有逻辑上的行为，因此必须通讯函数才能体现出行为。
IPC的机制除了进程，或者说不同的应用程序之间进行通讯，同时也能够让不同的组件之间进行像普通对象那样进行实时的调用。因为Android的组件都是由系统框架统一的进行构建和销毁，所以你就无法创建对象，因此，就没有办法像普通的对象那样进行组合或者聚合，从而也就没有办法进行直接调用。但是IPC的方式就可以让Activity/Service获取另外一个Service对象的引用，从而直接调用其上的方法。

还有，就是这些IPC方法总是产生客户/服务端模式的调用，也即是客户端组件（Activity/Service）持有服务端Service的组件，只能是客户端主动调用服务端的方法，服务端无法反过来调用客户端的方法，因为IPC的另一端Service无法获取客户端的对象。

test revert


test revert revert
[Android 3种IPC方式示例](http://blog.csdn.net/hitlion2008/article/details/9773251/)
