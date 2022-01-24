# 智慧停车-tjnu

# 项目文档
1.后端业务功能描述  
* 注册:
    >通过用户输入用户名(username)，密码(password)进行注册，前端判断用户名和密码的形式是否符号规范（如是否有空格，特殊符号等），后端判断用户名是否被占用
* 登录：
    >通过用户输入用户名，密码进行登录，前端判断用户名密码形式是否符合规范，后端在判断用户名，密码是否有错，若有错返回***，若无误返回token(参数名:access_token)和地图选项信息（包括地图的地址），前端保存token到内存中（之后的每一次请求都需携带access_token参数进行鉴权），并跳转到主页面，将地图信息进行展示
* 地图制作
    >通过EsMap平台进行地图制作
* 选择地图
    >用户在主页面选择了停车场地图，根据地图地址发送请求获取对应的网页并内嵌到安卓应用当中。后端返回地图后向华为云平台获取对应地图的信息，地图网页通过ajax向后端发送请求，得到车位信息后进行渲染
* 获取华为云token
    >通过华为云账号，根据开发文档
    https://support.huaweicloud.com/devg-iothub/iot_02_4008.html   
    进行操作
* 获取所有车位信息
    >连接华为云，通过订阅推送接口，在华为云平台先进行设置（将属性作为消息）  
    https://support.huaweicloud.com/usermanual-iothub/iot_01_0003.html  
    订阅：服务器向华为云平台获取消息  
    推送：华为云平台定时自动推送消息  
    之后可做的改进：对一些属性进行配置解耦合，增加对异常的处理  
* 根据车位信息渲染地图
    >用户在前端界面点击所选择地图后,发送请求获取对应地图的html文件并进行嵌入。除了html文件的地址（/tjnu01/index.html）还需要在请求头加token 
    （模拟获取的数据提前制作更多的设备数据）
    参考文档  
    https://www.esmap.cn/escopemap/content/cn/develope/map-fun.html  
    的常用方法  
    当加载完地图后，发起ajax请求获取车位数据，使用js修改房间的背景
* 预定车位操作
    >常用方法和地图事件，点击事件后，判断改房间是否空闲，若空闲弹出选择窗口，用户选择是否预定，若已有车，弹出提示窗口。用户选择后修改车位背景色，并标记为预定状态，需建一张表存储车位状态信息。  
    大概流程：在用户确定预约车位时跳转到预约信息填写界面，预约信息填写界面由前端写，包括内容：预约时间，车牌，停车场，车位（跳转时自动从上一个页面获取），用户名(通过token自动获取不用用户填写。跳转时向后端发送请求，后端可通过token解析出username再返回给前端)，当用户点击确认预定信息时，提交信息到后端。后端，进行价格计算，和车位状态的修改最后返回订单信息，前端进行支付操作后跳转到地图界面（支付操作暂时不用做，先完成车位状态的修改）
* 路线规划与引导
    >增加一个页面我的车位，特别标出客户预定的车位。当用户进入我的车位页面时携带token发送请求给后端，后端根据token解析出用户名，查询改用户所预定的停车场页面地址及其停车位。通过thymeleaf根据停车位参数对停车场页面进行渲染。对页面补充路径规划和导航功能。
    同时安卓端获取用户的位置，若在停车场内，则需设置初始位置，若没在则弹出弹窗提示（该功能之后写）。
* 反向寻车操作
    >前端获取用户目前的位置（前端做），后端根据用户名获取用户的车位，实现引导功能
* 当设备属性变化、判断车是否停入（是否为用户的车）
    >在设备表和订单中添加是否预定车辆停车字段。  
    当设备属性变化时，根据用户位置判断是否为预定用户，若是则对车位状态属性进行修改，并且对两表的是否停车属性进行修改。若不是，则进行相应的提示措施。  
    若是非预定的空车位则提示要求进行预定
* 用户信息完善，支付等
    >在订单的表中添加价格字段
* 其它（注释补充，文档编写，异常分析，变量逻辑规范等）
    >

2.前端页面  
3.技术实现  
* 注册：
    >
* 登录：
    >
* 地图制作
* 选择地图
* 获取华为云token
* 获取车位信息

4.API使用参考

#### 软件架构
软件架构说明


#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

#### 参与贡献

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
