### 构建
1. 下载源码包,通过 idea import 根目录的 `build.gradle` 到项目中
2. 遇到 `Could not HEAD Received status code 401 from server: Unauthorized` 错误
    * 注释掉 `build.gradle` 中的 `id 'io.spring.gradle-enterprise-conventions' version '0.0.2'`
3. 重新编译即可.