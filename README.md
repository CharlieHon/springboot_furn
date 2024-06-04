# SpringBoot后端项目

## 实现功能08-分页显示列表

思路：
1. 后台使用 `MyBatis-Plus`分页插件完成查询
2. 修改 `FurnController`，增加处理分页显示代码
3. 完成前台代码，加入分页导航，并将分页请求和后台接口结合

## 实现功能12-后端校验

思路：
1. 后台使用 `JSR303` 数据校验，引入 `hibernate-validator.jar`
2. 前台使用 `ElementPlus` 进行数据绑定，并显示错误信息

## SpringBoot+Vue项目(前后端分离)小结

1. 前端技术栈：vue3+Axios+ElementsPlus;request&response拦截器;跨域问题
2. 后端技术栈：SpringBoot+MybatisPlus
3. 数据库：Mysql
4. 项目依赖管理：Maven
5. 分页：MybatisPlus分页插件
6. 切换数据源：DruidDataSources
7. 在LambdaQueryWrapper引出知识点lambda方法引用的`类型::实例方法`
