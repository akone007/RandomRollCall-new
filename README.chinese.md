# CollCall

#### 介绍
java课程设计--基于有状态的课堂点名系统团队博客

#### 软件架构
软件架构说明
基于Java、JDBC、MySQL数据库、JavaSwing、第三方库等……


#### 安装教程

1.  jar包打包为本地exe可执行文件，只做演示；
2.  执行sql语句；对数据库进行配置 ： 账号默认root，密码默认123456，同时可以准备DataGrip进行对数据库的实时监控数据
3.  在文件中导入students.txt，注意文本的txt的编码集要为UTF-8，如果系统默认编码集打开文本文件出现乱码，那么需要将文本文件另存为UTF-8编码集的文件。
4.  注意txt文件中数据类型要符合规则 ： 
    （1）每个数据之间要用英文逗号分隔开
    （2）第一个数据为id，默认为123456……，第二个数据为string类型的字符，第三四五个数据分别表示called_times,answered_times,state(表示时候签到成功的状态量)
5.   本地部署完后点击exe可执行文件即可进入GUI界面，此时从后台导入了数据库中的文件并且可以试试更行数据，亦可以在最后将数据导出到database_content.txt

#### 使用说明

1.  部署sql文件连接数据库![输入图片说明](%E6%95%B0%E6%8D%AE%E5%BA%93%E9%83%A8%E7%BD%B2.png)
2.  将sql文件拖入DataGrip中，Ctrl+Enter运行所有代码即连接数据库成功，默认连接成功后清空数据库。![输入图片说明](%E6%95%B0%E6%8D%AE%E5%BA%93%E9%83%A8%E7%BD%B22.png)
3.  将本地准备好相应格式的txt文件![输入图片说明](txt%E5%87%86%E5%A4%87.png)
4.  点击exe文件中的Search.exe执行文件弹出窗口点击确定![输入图片说明](%E5%BC%B9%E5%87%BA%E7%AA%97%E5%8F%A3.png)
5.  点击相应按钮实现相应功能
签到功能 + 计算出勤率 ：![输入图片说明](%E7%AD%BE%E5%88%B0%E5%8A%9F%E8%83%BD+%E8%AE%A1%E7%AE%97%E5%87%BA%E5%8B%A4%E7%8E%87.png)
最多回答不上来的人数：![输入图片说明](%E6%9C%80%E5%A4%9A%E5%9B%9E%E7%AD%94%E4%B8%8D%E4%B8%8A%E6%9D%A5%E7%9A%84%E4%BA%BA%E6%95%B0.png)
若回答不上来的人数到达n个，则从回答出人中随机：
![输入图片说明](%E4%BB%8E%E5%9B%9E%E7%AD%94%E5%87%BA%E9%97%AE%E9%A2%98%E7%9A%84%E4%BA%BA%E4%B8%AD%E9%9A%8F%E6%9C%BA.png)
询问是否开始新的一轮点名：
![输入图片说明](%E6%96%B0%E4%B8%80%E8%BD%AE%E7%82%B9%E5%90%8D.png)
点击是则重新开始点名并且数据库持续更新，如果点击否则点名完成![输入图片说明](%E7%82%B9%E5%90%8D%E5%AE%8C%E6%88%90.png)
#### 
计算成绩数据![输入图片说明](%E8%AE%A1%E7%AE%97%E5%AD%A6%E7%94%9F%E4%BF%A1%E6%81%AF.png)
导出成绩数据![输入图片说明](%E5%AF%BC%E5%87%BA2.png)
退出程序：![输入图片说明](%E9%80%80%E5%87%BA%E7%A8%8B%E5%BA%8F.png)


参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
