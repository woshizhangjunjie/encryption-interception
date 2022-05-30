## encryption-interception

### 一款基于springboot的加密拦截框架 

1.核心注解 InParamEncryption

2.目前仅支持bcrypt,md5,sha256加密
3.后续会推出指定字段以及公钥私钥的复杂加密

4.当前**2.0.0**版本 已经支持mybatis的dao层加密拦截

注:如果项目中有分页拦截器需要把加密拦截放到最前面
