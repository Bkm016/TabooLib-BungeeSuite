# TabooLib-BungeeSuite
> 用于 BungeeCord 与 Bukkit 之间的快速交流工具
---

### 交流方式
---
相比原版 BC 交流方式有所不同的是, 这个工具可以在单个方法内实现交流信息的**发送**与**接收**       
每条交流信息都有独立的识别序号, **不会**因为同时发送多条信息就导致无法准确的送达返回内容    
  
**Bukkit** 向 **BungeeCord** 发送数据
  
---
**Bukkit** 部分:  
```java
TBukkitChannel channel = TabooLibBukkit.getInst().getBukkitChannel();
TBukkitChannelTask.createTask()
    .channel(channel)
    .sender(Bukkit.getOnlinePlayer().iterator().next())
    .command("Hello World!")
    .result(result -> Bukkit.broadcastMessage("Hello: " + result[0]))
    .run();
```
**BungeeCord** 部分:
```java
@EventHandler
public void onCommand(BungeeCommandEvent e) {
    if (e.getString(0, "null").equalsIgnoreCase("Hello World!)) {
        // 返回信息
        e.response(e.getSender().getName());
    }
}
```
  
**BungeeCord** 向 **Bukkit** 发送数据
  
---
**BungeeCord** 部分:
```java
TBungeeChannel channel = TabooLibBungee.getInstace().getBungeeChannel();
TBungeeChannelTask.createTask()
    .channel(channel)
    .sender(BungeeCord.getInstace().getPlayer("BlackSKY"))
    .command("Hello BlackSKY!")
    .result(result -> BungeeCord.getInstace().getProxy().getLogger().info("Hello: " + result[0]))
    .run();
```
**Bukkit** 部分:
```java
@EventHandler
public void onCommand(BungeeCommandEvent e) {
    if (e.getString(0, "null").equalsIgnoreCase("Hello BlackSKY!)) {
        // 返回信息
        e.response("BungeeCord");
    }
}
```

### BungeeCord 频道约定
---
你可以在本插件的内使用 **BungeeCord** 频道约定内的部分插件信息     
而不需要另外创建 **BungeeCord** 插件信息的发送与接收频道    
```java
String sevrer = "Lobby";
Player sender = Bukkit.getOnlinePlayer().iterator().next();

TabooLibBukkit.getInst().getBukkitChannelExecutor().playerList(sender, sevrer, new TChannelResult() {

    @Override
    public void run(String[] result) {
        if (result[0].equals("-")) {
             Bukkit.broadcastMessage("&4服务器 &c" + sevrer + " &4不存在");
        }
        else if (sevrer.equalsIgnoreCase("all")) {
             Bukkit.broadcastMessage("&7当前全服玩家: &f" + Arrays.asList(result));
        } 
        else {
             Bukkit.broadcastMessage("&7服务器 &f" + sevrer + " &7当前玩家: &f" + Arrays.asList(result));
        }
    }
});
```

### 已实现功能
---
+ [x] 同时安装在 **BungeeCord** 和 **Bukkit** 中
+ [x] 脱离 **TabooLib** 运行
+ [ ] 简化 **BungeeCord** 频道约定的部分命令
    + [x] Connect
    + [x] ConnectOther
    + [x] IP
    + [x] PlayerCount
    + [x] PlayerList
    + [x] GetServers
    + [x] Message
    + [x] GetServer
    + [ ] Forward
    + [ ] ForwardToPlayer
    + [ ] UUID
    + [ ] UUIDOther
    + [ ] ServerIP
    + [x] KickPlayer
+ [x] 通过命令来执行一些基础的 **BungeeCord** 操作
    + [x] 跨服传送
    + [x] 踢出玩家
    + [x] 发送信息
    + [x] 全服公告
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
+ [X] 永久插件数据
+ [x] 子服权限查询
+ [x] 命令自动补全
+ [x] 错误子命令相似度匹配
