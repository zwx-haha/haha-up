### JWT在JavaEE的开发中比比皆是,是什么让JWT那么出众,对程序员来说无非就是几个特点.
- 简单
- 好用
- 安全
---
### JWT是什么
> 你(我)真的理解JWT是什么了吗？<br>
首先JWT是一种标准。它不是框架，这个地方有很多人就混了。认为JWT就是框架是不对的。<br>
按照官网提供的标准(RFC 7519)做出来的东西就可以叫做是JWT。换言之，我们也可以自己写一个JWT标准的框架。
JWT的标准有两种实现一种叫做JWS(JSON Web Signature)，第二种叫做JWE(JSON Web Encryption)。<br>
---
### JWS(JSON Web Signature)
JWS就是我们通常所用的也是官方推荐使用的一种。

**JWS分为：头部(Header)、载荷(payLoad)、签名(signature)**

---
#### 头部(Header)
头部通常由令牌的类型(typ)和签名的算法(alg)组成。

这里有个点没有人说过，这个typ类型是媒介类型(Media Type)，可以认为是媒介类型、介质类型、设备类型等等都可以，用户可以根据令牌类型做不同的操作。例如有个项目是APP端和WEB端。那么这里就可以设置typ为APP或WEB。而官网中写了typ的媒介类型是JWT
```json
{
"alg": "HS256",
"typ": "APP/WEB"
}
```
由上面的JSON数据进行base64编码就是JWT的头部数据。

---
#### 载荷(Payload)
载荷是数据的主体部分
```text
iss: 签发者
sub: 主题
aud: 接收者
exp(expires): 过期时间
iat(issued at): 签发时间
nbf(not before): 早于某个时间不处理
jti(JWT ID): 唯一标识
```

主体部分可添加公开数据和用户可公开的数据。

这里就有个问题了，什么是用户不可公开的数据。我们举个栗子：用户的余额、用户的密码、用户的隐私数据(女性的年龄)等等都是不可公开的数据。
```json
{
  "iss": "朕水真水",
  "sub": "审批",
  "aud": "大牛",
  "userName": "朕水真水",
  "approve":"通过",
  "iat":"1597000000",
  "exp": 1597222222
}
```
由上面的JSON数据进行base64编码就是JWT的载荷数据。

---
#### 签名(Signature)
签名则是使用算法加密：头部数据.载荷数据

```text
HMACSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload), "密钥/盐")
```
通过算法加密后的结果就是签名。

将头部、载荷、签名用点(.)连接起来得到的结果就是JWS完整的数据

---
### 安全性问题
> 到这里是不是会用人疑惑。数据使用base64进行编码header和payload。只要用base64解码载荷就能得到载荷保存的数据了。为什么这个字符串会安全？

> 别着急，因为我们的数据是公开数据以及用户可公开的数据。即使有人拿到了这个token，也只能解析出公开的数据。并不能拿到私密的数据。

> 而篡改头部或者载荷的数据是不可能通过校验的。因为篡改头部和载荷之后的base64编码的字符串和原本的不一样，在校验token的时候会将头部和载荷进行加密，和签名进行比较。还记得签名是怎么生成的吗？是是由 [头部数据.载荷数据]经过算法加密生成的。这样你就知道了为什么不能篡改头部和载荷的数据了吧。

---
### JWE(JSON Web Encryption)
JWE表示使用基于JSON的数据结构的加密内容

**JWE分为5个部分：头部(Header)、内容加密的密钥(Content Encryption Key简称CEK)、密钥加密(JWE Encrypted Key)、初始化向量(JWE Initialization Vector)、内容加密(JWE Ciphertext)**

---
#### 头部(JOSE header)
通常alg填写密钥加密方式，enc中填写报文的加密方式

```json
{"alg":"RSA","enc":"AES128","typ":"JWE"}
```
|  加密内容   | 算法  |
|  ----  | ----  |
| 报文  | 对称算法 |
| 密钥加密  | 密钥加密 |

---
#### 内容加密的密钥(Content Encryption Key简称CEK)
这个密钥是256位的随机CEK

---
#### 密钥加密(JWE Encrypted Key)
将CEK进行公钥加密(非对称加密)，加密后得到的结果。

---
#### 初始化向量(JWE Initialization Vector)
生成随机的128位的JWE初始化向量

---
#### 内容加密
使用CEK + 初始化向量作为密钥，使用AES128算法对报文执行身份验证加密。

对于内容加密可以附加身份校验的加密，即CEK+初始化向量+附加密钥。

这个附加身份校验可以是受保护的头部(JOSE Header)，如：头部设置的加密方式(alg、enc)。进行base64编码后加到内容加密当中。这样可以防止重要的头部数据被恶意篡改。

这5个部分进行base64编码后使用点(.)连接就是JWE最终的数据。
```json
{
    "protected": "base64(header受保护部分)",
    "unprotected": {
        "typ":"JWE"
    },
    "header": {
		"alg":"RSA",
		"enc":"AES128",
		"typ":"JWE"
    },
    "encrypted_key": "base64(被加密后的CEK)",
    "iv": "base64(随机128位二进制)",
    "ciphertext": "base64(AES加密后的内容)",
    "tag": "base64(AES生成的128位的认证标记)"
}

```

---
### 解析
有人就要问了，JWE是怎么解析数据的呢？

其实我们可以推导出来。

1. 将5个部分用点(.)拆开进行base64解码
2. 将加密后的CEK进行(私钥)解密，得到CEK。
3. 使用CEK+初始化向量(如果由附加身份校验则加上)对加密后的内容解密
4. 得到内容数据

---
### 优点
- 经过2次加密，数据更加安全

- 直接对报文加密，数据准确

- 密钥CEK是随机生成，每次签发都不同更难被破解
