## 如何打开 VisualVM 工具
首先保证Java的环境变量已经配置好，则可在命令窗口执命令
```bash
jvisualvm
```
### 安装 VisualGC 插件
Visual GC 插件主要是提供 JVM 实时的各分代内存情况的图标分析。
 
安装步骤
Tools->Plugins-> 找到Visual GC 执行安装
### 分析程序
　　插件安装好之后，在主界面可以看到本机目前正在执行的JAVA 应用程序
![image](http://okl932arn.bkt.clouddn.com/visual-GC.png)
选中程序，比如我这边分析要 Android Studio 这个开发工具的实时运行情况，双击，或者右键点出菜单选择。

![image](http://okl932arn.bkt.clouddn.com/Visual-GC-program.png)
在右侧的图标中，可以看到程序运行GC情况的一个实时图表。
另外其他菜单栏中还包括 线程数、线程运行状态 等的图标。
