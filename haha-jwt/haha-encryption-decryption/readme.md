# java加解密
## 密码算法分类
- **消息编程，Base64：为了适应一些系统不支持非ASCII的数据，所以需要将数据转换成ASCII格式，Base64就是一种转换方式，Base64是将3个8bit的字节转换成为4个6bit的字节，转换后的字节高位补0，如aaaaaaaabbbbbbbbcccccccc会被转换成00aaaaaa,00aabbbb,00bbbbcc,00cccccc,接受端进行反向解码即可。**
- **消息摘要：MD类、SHA类、MAC(消息验证码,在MD类与SHA类上增加了密钥的支持)**
- **对称密码：DES、3DES、AES(对称密码的标准(最新)，增加了密钥的长度)**
- **非对称密码：RSA、DH密钥交换**
- **数字签名：RSASignature(基于RSA)、DSASignature(基于DSA)**
