# ssmp-wx

## 介绍
基于springcloud alibaba+ springboot+mybatis-plus+elasticsearch搭建的商城项目，系统包含商品，购物车，个人中心，我得订单，结算功能在内，对接了第三方：短信、图片等功能。 
后台接口RESTful 风格，支持前后端分离，可与app公用一套接口。  


#### 演示地址:
http://ssmp.zxjshop.cn<br/>
![输入图片说明](https://images.gitee.com/uploads/images/2021/0323/142655_59c0a3ed_376915.png "屏幕截图.png")

#### 后台系统<br/>
http://admin.zxjshop.cn


### 如何启动:
- 1：首先需要安装maven、jdk8以上环境
- 2：安装nacos注册中心
- 3：修改application.yml文件配置数据库连接等
- 4：执行ssmp-wx根目录下ssmp-wx.sql文件
- 5：安装elasticsearch
- 6：按顺序启动 执行xxxApplication.java文件
```
            ssmp-wx-api-user            用户管理模块
            ssmp-wx-api-product         商品购物车模块
            ssmp-wx-api-order           订单管理模块
            ssmp-wx-api                 前端管理模块
            ssmp-wx-task                定时任务模块
            ssmp-wx-gateway             服务网关
            ssmp-wx-commons             通用工具模块
```
### er设计图

![输入图片说明](https://images.gitee.com/uploads/images/2021/0224/150220_10ae82ff_376915.png "屏幕截图.png")

### 接口文档
![输入图片说明](https://images.gitee.com/uploads/images/2021/0210/173932_0c06f0fe_376915.png "屏幕截图.png")

### nacos截图
![输入图片说明](https://images.gitee.com/uploads/images/2021/0219/111631_aa8f7d11_376915.png "屏幕截图.png")

### sentinel截图
 ![输入图片说明](https://images.gitee.com/uploads/images/2021/0224/112104_703c2313_376915.png "屏幕截图.png")

### 技术框架
| 名称     | 版本  | 链接                                               |
|--------|-----|--------------------------------------------------|
| SpringCloud | Hoxton.SR1 | https://spring.io/projects/spring-cloud |
| SpringCloud-Alibaba | 2.1.0.RELEASE | https://github.com/alibaba/spring-cloud-alibaba |
| SpringBoot | 2.2.3 | https://spring.io/projects/spring-boot |
| Nacos | 2.2.3 | https://nacos.io/zh-cn/index.html |
| Sentinel | 2.2.3 | https://github.com/alibaba/sentinel |
| Lombok | 1.18.10 | https://projectlombok.org |
| Mybatis-Plus | 3.4.1 | https://mp.baomidou.com |
| hutool | 5.4.2 | https://www.hutool.cn |
| knife4j | 2.0.8 | https://doc.xiaominfo.com |  

### QQ群
<a target="_blank" href="https://qm.qq.com/cgi-bin/qm/qr?k=KBlFDIdTWlbYS3j5EWtyLEq6sjbATnN5&jump_from=webapi">~~335102947~~ </a>🈵️ 、<a target="_blank" href="https://qm.qq.com/cgi-bin/qm/qr?k=AYY4TTfGFuotB6a9lzLlPdljQO_G9AZF&jump_from=webapi">1090283135</a> 🔥



### 运行截图
<table>
    <tr>
        <td><img src="https://images.gitee.com/uploads/images/2021/0323/150013_d1a5d08e_376915.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2021/0323/141409_2e662bb1_376915.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2021/0323/141336_85ca83fd_376915.png"/></td>
    </tr>
     <tr>
          <td><img  src="https://images.gitee.com/uploads/images/2021/0323/141420_6b7a48a7_376915.png"/></td>
         <td><img src="https://images.gitee.com/uploads/images/2021/0323/141354_156e59f3_376915.png"/></td>
         <td><img  src="https://images.gitee.com/uploads/images/2021/0323/141830_92899590_376915.png"/></td>
     </tr>
</table>

