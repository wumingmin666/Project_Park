# 智慧停车-tjnu

# 项目文档
### 一、后端业务功能简要描述  
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
### 二、前端页面（略）
### 三、技术实现
#### 前提补充
> 1. 环境配置
>   * 服务器:
>      - ip:122.112.227.127
>      - 操作系统：centos8
>      - 账号root->密码：13850982142wmm.
>   * mysql: 
>      - mysql数据库版本：8.0.28
>      - mysql数据库名:dbpark
>      - mysql数据库密码:Tjnu.666
>      - mysql数据库端口号:3306
>   * redis:
>      - 版本：6.2.6
>      - 端口号：6379
> 2. 工具类
>   * 创建响应结果类
>   * 控制层基类
>   * JWTUtil类，生成和解析token
#### 注册：
>1. 创建数据库表
>   * 表名：tbl_users
>   * 表结构：
>2. 创建用户实体类
>   * 类名：User
>3. 用户注册持久层
>   * 规划SQL语句  
>     插入语句
>     ```sql 
>     INSERT INTO
>     tbl_users (username, password, phone, email, created_time, modified_time)
>     VALUES
>     (#{username}, #{password}, #{phone}, #{email}, #{createdTime}, #{modifiedTime})
>     ```
>     查询语句  
>      ```sql
>      SELECT * FROM tbl_users WHERE username=#{username}
>      ```
>   * 接口与抽象方法  
>     - 接口:`UserMapper.java`  
>     - 方法1:`public Integer insert(User user);`  
>     - 方法2:`public User findByUsername(String username);`
>   * 配置SQL映射
>4. 用户注册业务层
>   * 规划异常：
>     - 插入异常：`InsertServiceException`
>     - 用户已存在异常:`UsernameDuplicatedServiceException`
>   * 创建接口抽象与方法
>     - 接口：`UserService.java`
>     - 方法：`public void register(User user);`
>   * 实现类及抽象方法：
>     - 查询用户名：从参数中获取用户名，并查询数据库中该用户是否存在，若存在则抛出用户名冲突异常
>     - 密码加密：从参数中获取密码，并进行md5加密
>     - 插入数据库：补充实体类信息（创建和修改时间），之后插入数据到数据库。
>     - 异常处理：对用户名冲突和插入异常进行抛出处理
> 5. 用户注册控制层
>   * 处理异常：对业务层抛出的在基类进行统一处理异常。
>   * 设计请求：
>     - url: http://122.112.227.127:8080/user/register
>     - 方法：get/post
>     - 参数：username,password映射到实体类中
>   * 处理请求：调用业务层register方法，传递参数为实体类User（包含username及password）,成功时返回状态码200
#### 登录：
>1. 用户登录持久层
>   * 规划SQL语句
>   - 查询语句 
>      ```sql
>      SELECT * FROM tbl_users WHERE username=#{username}
>      ```
>   * 接口与抽象方法
>     - 接口:`UserMapper.java`
>     - 方法:`public User findByUsername(String username);`
>   * 配置SQL映射
>2. 用户登录业务层
>   * 规划异常
>     - 用户不存在异常
>     - 密码错误异常
>   * 创建抽象方法
>     - `public User login(String username,String password) 
>   * 实现类及抽象方法
>     - 查询用户：根据username参数查询用户是否存在，若不存在则抛出异常，存在返回用户信息
>     - 密码加密：对参数password进行md5加密
>     - 密码验证：将加密后的密码和查询得到的用户信息中得密码进行对比不同则抛出异常
>     - 异常处理：对用户名不存在和密码错误进行异常处理
>3. 用户登录控制层
>   * 异常处理:对业务层抛出得异常在基类统一处理
>   * 设计请求
>      - url:http://122.112.227.127:8080/user/login
>      - 方法: post 
>      - 参数:username,password,location(用户的地理位置)
>   * 处理请求
>      - 判断用户是否存在且密码正确：调用业务层方法，返回用户信息实体类
>      - 生成token并加入到返回参数：通过JWTUtil生成token
>      - 添加地图信息:调用业务层parkService的findParkByDistance(location)方法以用户的位置为参数返回最近的几副地图 
>4. 用户拦截器
>   * 项目添加拦截器功能:
>     - 添加白名单和黑名单： 
#### 地图制作
>1. 地图命名:tjnu+数字（tjnu01）
>2. 停车位命名及参数:按顺序编号字符串1开始
>3. 停车场的数据库表：表名：tbl_park
#### 选择地图（1、获取地图信息列表. 2、获取确定的地图）
>1. 选择地图持久层
>   * 规划SQL语句
>     - 查询语句1：
>        ```sql
>        SELECT * FROM tbl_park
>        ```  
>      - 查询语句2：
>       ```sql
>       SELECT * FROM tbl_park WHERE pid=#{pid}
>       ```
>   * 接口与抽象方法:
>     - 接口：ParkMapper.java
>     - 方法1：`public List<Park> findAllPark();`
>     - 方法2：`public Park findParkByPid(Integer pid);`
>   * 配置SQL映射
> 2. 选择地图业务层
>   * 规划异常
>     - 地图查找失败异常
>     - 根据pid查找地图异常
>   * 创建抽象方法
>     - 方法1：`public HashMap<String,Park> findParkByDistance(String location);`
>     - 方法2；`public String getUrlByPid(Integer pid);`
>   * 实现类及抽象方法
>     - 1.查询地图：通过`List<Park> parkList=parkMapper.findAllPark();`获取地图集合
>     - 1.计算距离：遍历地图集合，通过`public Long getDistance(String l1,String l2)`计算距离并加入到新集合中
>     - 1.返回地图集合：从距离的集合中找最短的5个地图返回
>     - 2.查询地图url:通过参数pid查询对应地图实体类，从中获取url,并返回
> 3. 选择地图控制层
>   * 异常处理:对业务层抛出得异常在基类统一处理
>   * 请求的设计与处理：
>     - 1、获取地图信息列表：控制层在登录的控制层方法
>     - 2、获取确定的地图:通过url直接获取static下的静态资源，不需要写控制层
#### 获取华为云token（https://support.huaweicloud.com/devg-iothub/iot_02_4008.html）
>1. 构造请求
>2. 发送请求获取token
>3. 存储：（使用redis)
>4. 设置监听:创建监听类：ContextRefreshedListener.java
#### 获取车位信息（https://support.huaweicloud.com/usermanual-iothub/iot_01_0003.html）
>1. 华为云平台设置
>2. 设置推送
>3. 设置订阅
>4. 存储（暂时未完成，需要硬件配合）
#### 根据车位信息渲染地图
>1. 持久层
>   * 规划SQL语句
>     - 查询语句：`SELECT * FROM tbl_device_to_park WHERE pid=#{pid}`
>   * 接口与抽象方法
>     - 接口：DeviceToParkMapper.java
>     - 方法：`public List<DeviceToPark> findDeviceByPid(Integer pid);`
>   * 配置映射
>2. 业务层
>   * 规划异常
>     - 停车场信息不存在异常：`ParkNotFoundByPidServiceException`  
>   * 创建抽象方法
      - 方法：`public List<ParkToStatue> initialization(Integer pid);`
>   * 实现类及抽象方法
>     - 通过pid(停车场id)获取该停车场的所有车位信息的实体类集合
>     - 将获取的集合进行遍历，对park_id和statue属性进行映射到实体类ParkToStatue中
>3. 控制层
>   * 异常处理:对业务层抛出得异常在基类统一处理
>   * 请求的设计
>     - url:http://122.112.227.127:8080/device_to_park/initialization
>     - 方法:get/post
>     - 参数:pid（地图的id）
>     - 其它：前端地图页面通过ajax请求发起访问，获得信息后通过js进行渲染（安卓嵌套该页面，该页面已完成）
>   * 请求的处理
>     - 直接调用业务层方法：`deviceToParkService.initialization(pid)`
#### 预定车位操作(部分功能不完整)
>1. 持久层
>   * 规划SQL语句（判断车位状态，订单操作）
>     - 查询语句：`SELECT * FROM tbl_device_to_park WHERE park_id=#{parkId} and pid=#{pid}`
>     - 更新语句：`UPDATE tbl_device_to_park SET statue=2 WHERE pid=#{pid} and park_id=#{parkId}`
>     - 插入语句：`INSERT INTO
        tbl_order (booking_user, booking_start_time,booking_end_time,park_id,pid,plate_number,created_time,is_exist)
        VALUES
        (#{bookingUser}, #{bookingStartTime}, #{bookingEndTime}, #{parkId},#{pid},#{plateNumber} ,#{createdTime}, #{isExist})`
>   * 接口与抽象方法
>     - 接口：DeviceToParkMapper.java和OrderMapper.java
>     - 方法1：`public DeviceToPark findDeviceByParkId(Integer parkId,Integer pid);`
>     - 方法2：`public Integer updateStatueByParkId(Integer parkId,Integer pid);`
>     - 方法3：`public Integer insert(Order order);`
>   * 配置映射
>2. 业务层
>   * 规划异常
>     - 停车场不存在异常：`ParkNotFoundByPidServiceException`
>     - 车位不存在异常：`ParkIdNotFountServiceException.java`
>     - 订单插入异常:`InsertServiceException.java`
>     - 更新状态异常：`UpdateStatueServiceException.java`
>   * 创建抽象方法
>     - 方法1：`public Integer getStatue(String parkName,Integer pid)`
>     - 方法2：`public String booking(String parkName, Integer pid, String username, String plateName, String bookingStartTimeString, String bookingEndTimeString)`
>   * 实现类及抽象方法
>     - 1、获取车位信息：`DeviceToPark deviceToPark=deviceToParkMapper.findDeviceByParkId(parkId,pid);`
>     - 1、获取车位状态：`Integer statue=deviceToPark.getStatue();`
>     - 1、返回statue
>     - 2、更新车位状态
>     - 2、补充订单信息
>     - 2、插入订单信息
>3. 控制层
>   * 异常处理:对业务层抛出得异常在基类统一处理
>   * 请求的设计
>     - 1、url:http://122.112.227.127:8080/device_to_park/get_statue
>     - 1、方法:get/post
>     - 1、参数:pid（地图的id）,parkName(name映射为parkName):车位的id(字符串形式)
>     - 1、其它：前端地图页面通过ajax请求发起访问，获得信息后通过js进行渲染（安卓嵌套该页面，该页面已完成）
>     - 2、url:http://122.112.227.127:8080/order/booking
>     - 2、方法:get/post
>     - 2、参数：
>   * 请求的处理
>     - 1、直接调用业务层方法：`deviceToParkService.getStatue(parkName,pid);`
>     - 2、通过token获取username
>     - 2、将所有参数传给业务层,调用应用层方法
>     - 2、添加支付功能后对结果result进行处理
#### 路线规划与引导、反向寻车操作（两功能均在我的订单地图页面实现，获取地图即可，功能通过js,thymeleaf实现）
>1. 持久层
>   * 规划SQL语句
>     - 查询语句：`SELECT * FROM tbl_order WHERE booking_user=#{username}`
>   * 接口与抽象方法
>     - 接口：OrderMapper.java
>     - 方法：`public Order findOrderByUsername(String username);`
>   * 配置映射
>2. 业务层
>   * 规划异常
>     - 订单不存在异常：`OrderNotFoundServiceException.java`
>   * 创建抽象方法
>     - 方法:`public HashMap myBooking(String username)`
>   * 实现类及抽象方法
>     - 通过用户名查询订单
>     - 判断订单是否过期存在，考虑通过时间或is_delete(暂时省略，之后写该步骤，还需添加异常）
>     - 获取Park_id和pid,并返回
>3. 控制层
>   * 异常处理:对业务层抛出得异常在基类统一处理
>   * 请求的设计
>     - url:http://122.112.227.127:8080/order/my_booking
>     - 方法:get/post
>     - 参数:http请求对象
>   * 请求的处理
>     - 解析token获取username
>     - 获取用户订单信息(pid,parkName):`HashMap<String,Object> data=orderService.myBooking(username);`
>     - 获取用户的地图资源(url）:`String url=parkService.getUrlByPid(pid);`
>     - 地图处理并返回
#### 当设备属性变化、判断车是否停入（是否为用户的车）
#### 用户信息完善，支付等
### 四、API使用参考
#### 注册
>1. 接口说明：
>2. 调试
>3. URL
>4. 请求参数
> >| 名称 | 必选/可选 | 类型 | 位置 | 说明|
> >| --- |   ----- | ---- | --- | ---|
> >| username |必选 |String|path |参数说明：|
>5. 响应参数
>6. 请求示例
>7. 错误码
#### 登入
>1. 接口说明：
>2. 调试
>3. URL
>4. 请求参数
>5. 响应参数
>6. 请求示例
>7. 错误码
#### 首页（选择地图）
>1. 接口说明：
>2. 调试
>3. URL
>4. 请求参数
>5. 响应参数
>6. 请求示例
>7. 错误码
#### 预定车位
>1. 接口说明：
>2. 调试
>3. URL
>4. 请求参数
>5. 响应参数
>6. 请求示例
>7. 错误码
#### 订单地图界面（导航）
>1. 接口说明：
>2. 调试
>3. URL
>4. 请求参数
>5. 响应参数
>6. 请求示例
>7. 错误码
#### 个人信息
>1. 接口说明：
>2. 调试
>3. URL
>4. 请求参数
>5. 响应参数
>6. 请求示例
>7. 错误码

### 五、软件架构
