##### **串行GC** -XX:+UseSerialGC

​	年轻代和老年待都是单线程的垃圾回收器，执行效率低

(1)-Xmx512m -Xms512m

- 19次DefNew平均0.015s
- 7次Full GC

(2)-Xmx4g -Xms4g

- 只发生了4次DefNew 平均0.07s
- young区的内存空间大小几乎不变

##### **并行 GC** -XX:+UseParallelGC

​	jdk8默认GC为并行GC

(1)-Xmx512m -Xms512m

- 29次PSYoungGen平均0.0045s(young区的名字不一样)
- 15次Full GC 平均0.034s，相差不大

(2)-Xmx4g -Xms4g

- 发生了6次PSYoungGen 平均0.0035s

  **没有关闭自适应参数的时候，young区容量，一直是在变化的，整个堆内存的容量也是在变化的。**

  **自适应参数关掉，空间大小不变，并且默认晋升old区的一些阈值，默认的晋升阈值是15。**

##### **CMS GC** -XX:+UseConcMarkSweepGC

​	young GC ,**串行**的垃圾回收器进行了升级改造变成了**多线程**的,CMS进行过程中可能会发生一次或多次的young GC。

(1)-Xmx512m -Xms512m

- ParNew平均0.014s


- 13次cms 
  - CMS Initial Mark （STW）平均 0.0007s
  - CMS Final Remark  （STW）平均 0.00025s

(2)-Xmx4g -Xms4g

- 6次ParNew 平均0.00025s，没有发生Full GC

**总的来说**CMS  GC STW时间比并行GC的延迟低得多，但是吞吐量不如并行GC

##### **G1 GC** -XX:+UseG1GC

(1)-Xmx512m -Xms512m

-   Concurrent Marking（并发标记）
  - Initial Mark（初始标记）平均0.0024s
  - Remark（再次标记）平均0.0014s
  - Cleanup（清理）平均0.0017s

发生了4次Full GC平均0.03s

(2)-Xmx4g -Xms4g

发生了11次Evacuation Pause,平均0.011s

总的分析下来MetaSpace区基本不变，并行GC适用于对于吞吐量要求高的需求，对于低延迟要求的建议使用CMS GC和G1 GC，而大于4g内存的建议用G1 GC。并且可以使用-XX:MaxGCPauseMillis控制每次GC最大的停顿毫秒数。















































