# 安卓基础入门

### Log类
- Android中的日志工具类，用于获取程序从启动到关闭的日志信息
- 输出的日志分为五个类别：
```java
Log.v("MainActivity", "Verbose") // 无关紧要的信息
Log.d("MainActivity", "Debug") // 调试信息
Log.i("MainActivity", "Info") // 普通信息
Log.w("MainActivity", "Warning") // 警告信息
Log.e("MainActivity", "Error") // 报错信息
```

### 程序打包
- 先生产签名证书
- 在打包时使用签名证书