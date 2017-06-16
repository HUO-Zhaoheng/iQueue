# 项目进度

[TOC]

进度更新说明尽量详细, 包括**完成情况，BUG修复，问题遗留**等，并**同步更新**未完成内容。

## 已完成

2017.4.23 后端上传文件说明

**平台 eclips最新版本 neon**

在 **entity**包下是实体类，里面是待添加的类及属性方法，src下`applicationContext`是类的`Ioc`注入

*我已经写好helloworld样例*

`HomeController`

```java
@RequestMapping(value = "/", method = RequestMethod.GET)
public String home(Locale locale, Model model) {
  //TODO:
}
```

*是根据请求进行事件分发，上面只是样例，目前就是这样吧*

**尽快把实体类写好吧**



注意,由于我之前在群里说的**提交注意事项**大家没注意，所以为了分离文件

我新建了`dev`和`dev_front`两个分支，文档部分交到`master`下，前端部分的代码交到`dev_front`，后端`dev`下，测试无误后整合到`master`下(这个我来吧)

本周开始着手撸代码了，所以提交前先**pull一下，pull一下，pull一下啊**

## 待完成

## FAQ讨论 / 想法

