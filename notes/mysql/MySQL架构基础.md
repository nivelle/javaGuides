### mysql 整体架构

[![sfBoMd.md.png](https://z3.ax1x.com/2021/01/20/sfBoMd.md.png)](https://imgtu.com/i/sfBoMd)
### 性能配置

- `–skip-grant-tables`:跳过权限验证
- 增删改数据（DML),修改表结构的操作（DDL)
- `set global slow_query_log = on`; //开启慢查询日志

#### 如果你的 MySQL 现在出现了性能瓶颈，而且瓶颈在 IO 上，可以通过哪些方法来提升性能呢？

1. 设置 **binlog_group_commit_sync_delay** 和 **binlog_group_commit_sync_no_delay_count** 参数,减少 binlog
   的写盘次数。这个方法是基于“额外的故意等待”来实现的，因此可能会增加语句的响应时间，但没有丢失数据的风险。

2. 将 **sync_binlog** 设置为大于 1 的值（比较常见是 100~1000）。这样做的风险是，主机掉电时会丢 binlog 日志。此时数据还在系统缓存

3. 将 **innodb_flush_log_at_trx_commit** 设置为 2。这样做的风险是，主机掉电的时候会丢数据。每一秒才能刷一次盘

---

### MySQL 全表扫描对server层的影响

#### 服务端并不保存完整的结果集,取数据和发数据的流程如下:

1. 获取一行，写到`net_buffer`中。这块内存的大小是由参数，**net_buffer_length** 定义的，默认是16K,也就是1页

2. 重复获取行，直到`net_buffer`写满，调用网络接口发出去

3. 如果发送成功，就清空`net_buffer`,然后继续取下一行，并写入`net_buffer`

4. 如果发送函数返回`EAGAIN` 或 `WSAEWOULDBLOCK` ,就表示本地网络栈(socket send buffer)写满了，进入等待。直到网络栈重新可写，再继续发送。

[![cQ7jhD.jpg](https://z3.ax1x.com/2021/04/05/cQ7jhD.jpg)](https://imgtu.com/i/cQ7jhD)

- 一个查询在发送过程中，占用的MySQL 内部的内存最大就是**net_buffer_length** 

- `socket send buffer` 也不可能达到200G(默认定义 /proc/sys/net/core/vmem_default),如果socket send buffer被写满，就会暂停读数据流

----

### mysql 主备切换流程

![mysql主备流程图.png](https://i.loli.net/2021/03/01/wd8g3csYOtCFx7M.png)

备库 B 跟主库 A 之间维持了一个长连接。主库 A 内部有一个线程，专门用于服务备库 B 的这个长连接。一个事务日志同步的完整过程是这样的：

1. 在备库 B 上通过 `change master` 命令，设置主库 A 的 IP、端口、用户名、密码，以及要从哪个位置开始请求 binlog，这个位置包含文件名和日志偏移量。

2. 在备库 B 上执行 `start slave` 命令，这时候备库会启动两个线程，就是图中的 `io_thread` 和 `sql_thread`。其中 io_thread 负责与主库建立连接。

3. 主库 A 校验完用户名、密码后，开始按照备库 B 传过来的位置，从本地读取 binlog，发给 B。

4. 备库 B 拿到 binlog 后，写到本地文件，称为中转日志（relay log）。

5. `sql_thread` 读取中转日志，解析出日志里的命令，并执行。


----

### 全表扫描对InnoDB的影响

- InnoDB内存的作用:保存更新结果，再配合redo log ,避免了随机写盘

- 内存的数据页是在`buffer pool` 中管理的，在WAL里Buffer Pool 起到了加速更新作用，实际上，buffer pool还有一个更重要的作用，就是加速查询

- 由于有WAL机制,当事务提交的时候，磁盘上的数据页是旧的，这个时候有个查询来读取这个数据页，不需要马上把redo log 应用到数据页，因为这个时候内存数据页的结果是最新的，直接读取内存页就可以了。

- 对`buffer pool` 采取分区策略，避免LRU算法对yong区的影响，保证了buffer pool响应正常业务的查询命中率

### 设计范式

#### 基础概念

- 超键:唯一标识元组的属性集合叫做超键

- 候选键:如果超键不包括多余的属性，那么这个超键就是候选键

- 主键:从候选键中选择一个作为主键

- 主属性:包含在任意一个候选键的属性为主属性，能唯一确定记录的属性

- 非主属性:不包含在任何一个候选键中的属性，不能唯一确定记录的属性

#### 范式概念

- 第一范式:保证表中每个属性都保持原子性

- 第二范式:保证表中的非主属性与候选键完全依赖，不能仅仅依赖候选键的一部分属性，而必须全部依赖。
  一个表就就是一个独立的对象，一张表只表达一个意思 针对联合主键，非主属性完全依赖于联合主键，而非部分。

##### 不符合第二范式的后果：

````
1. 数据冗余
2. 插入异常
3. 删除异常
4. 更新异常
````

- 第三范式:保证表中的非住属性与候选键不存在传递依赖，非主属性不依赖于另外一个非主属性，从而间接依赖了候选键。也就是非主属性只能依赖于主键。