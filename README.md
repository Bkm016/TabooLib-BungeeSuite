# TabooLib-BungeeSuite
> 用于 BungeeCord 与 Bukkit 之间的快速交流工具
---

### 交流方式
---
相比原版 BC 交流方式有所不同的是, 这个工具可以在单个方法内实现交流信息的**发送**与**接收**       
每条交流信息都有独立的识别序号, **不会**因为同时发送多条信息就导致无法准确的送达返回内容         
Bukkit 部分:  
```java
TBukkitChannel channel = TabooLibBukkit.getInst().getBukkitChannel();
TBukkitChannelTask.createTask()
    .channel(channel)
    .sender(Bukkit.getOnlinePlayer().iteraotr().next())
    .command("Hello World!")
    .result(new TChannelResult() {
  
        @Override
        public void run(String[] result) {
            Bukkit.broadcastMessage("Hello: " + result[0]);
        }
    }).run();
```
BungeeCord 部分
```java
@EventHandler
public void onCommand(BungeeCommandEvent e) {
    if (e.getArgs()[0].equalsIgnoreCase("Hello World!)) {
        // 返回信息
        e.response(e.getSender().getName());
    }
}
```


### 已实现功能
---
+ [x] 同时安装在 **BungeeCord** 和 **Bukkit** 中
+ [x] 脱离 **TabooLib** 运行
+ [ ] 通过命令来执行一些基础的 **BungeeCord** 操作
    + [x] 跨服传送
    + [x] 踢出玩家
    + [x] 发送信息
    + [ ] 全服公告
    + [x] 人数查询
    + [x] 玩家查询
    + [x] 玩家信息
    + [x] 子服列表
+ [ ] 通过命令来执行一些基础的 **TabooLib-BungeeSuite** 操作
    + [x] 临时玩家数据写入/删除/读取
    + [ ] 永久玩家数据写入/删除/读取
    + [ ] 临时插件数据写入/删除/读取
    + [ ] 永久插件数据写入/删除/读取
+ [x] 临时玩家数据
+ [ ] 永久玩家数据
+ [ ] 临时插件数据
+ [ ] 永久插件数据
+ [x] 子服权限查询
