### 字符集(Charset)

字符集（Charset）：是一个系统支持的所有抽象字符的集合。字符是各种文字和符号的总称，包括各国家文字、标点符号、图形符号、数字等。


### 字符编码(Character Encoding)

字符编码：是一套法则，使用该法则能够对自然语言的字符的一个集合（如字母表或音节表），与其他东西的一个集合（如号码或电脉冲）进行配对。即在符号集合与数字系统之间建立对应关系，它是信息处理的一项基本技术。通常人们用符号集合（一般情况下就是文字）来表达信息,而以计算机为基础的信息处理系统则是利用元件（硬件）不同状态的组合来存储和处理信息的。元件不同状态的组合能代表数字系统的数字，因此字符编码就是将符号转换为计算机可以接受的数字系统的数，称为数字代码。

---

### 常用字符集和字符编码

#### 字符集

- ASCII字符集、GB2312字符集、BIG5字符集、GB18030字符集、Unicode字符集

- 计算机准确处理各种字符集文字，需要进行字符编码，一遍遍计算机能够识别和存储各种文字

#### ASCII 字符集&编码

- ASCII（American Standard Code for Information Interchange，美国信息交换标准代码）是基于拉丁字母的一套电脑编码系统。它主要用于显示现代英语，而其扩展版本`EASCII`则可以勉强显示其他西欧语言。它是现今最通用的单字节编码系统（但是有被Unicode追上的迹象），并等同于国际标准ISO/IEC 646。

- ASCII字符集：主要包括控制字符（回车键、退格、换行键等）；可显示字符（英文大小写字符、阿拉伯数字和西文符号）。

- ASCII编码：将ASCII字符集转换为计算机可以接受的数字系统的数的规则。使用7位（bits）表示一个字符，共128字符；但是7位编码的字符集只能支持128个字符，为了表示更多的欧洲常用字符对ASCII进行了扩展，ASCII扩展字符集使用8位（bits）表示一个字符，共256字符。ASCII字符集映射到数字编码规则如下图所示

![ASCII编码表.png](https://i.loli.net/2021/08/02/UtP5Dn6QHrW1uXA.png)

- ASCII的最大缺点是只能显示26个基本拉丁字母、阿拉伯数目字和英式标点符号，因此只能用于显示现代美国英语（而且在处理英语当中的外来词如naïve、café、élite等等时，所有重音符号都不得不去掉，即使这样做会违反拼写规则）。而EASCII虽然解决了部份西欧语言的显示问题，但对更多其他语言依然无能为力。因此现在的苹果电脑已经抛弃`ASCII`而转用`Unicode`。

#### GBXXXX字符集&编码

- 把那些127号之后的奇异符号们（即EASCII）取消掉，规定：**一个小于127的字符的意义与原来相同**，但**两个大于127的字符连在一起时，就表示一个汉字**，前面的一个字节（他称之为高字节）从0xA1用到 0xF7，后面一个字节（低字节）从0xA1到0xFE，这样我们就可以组合出大约7000多个简体汉字了。在这些编码里，还把数学符号、罗马希腊的 字母、日文的假名们都编进去了，连在ASCII里本来就有的数字、标点、字母都统统重新编了两个字节长的编码，这就是常说的"全角"字符，而原来在127号以下的那些就叫"半角"字符了

- 上述编码规则就是GB2312：

````
GB2312或GB2312-80是中国国家标准简体中文字符集，全称《信息交换用汉字编码字符集·*本集》，又称GB0，由中国国家标准总局发布，1981年5月1日实施。GB2312编码通行于中国大陆；新加坡等地也采用此编码。中国大陆几乎所有的中文系统和国际化的软件都支持GB2312。GB2312的出现，*本满足了汉字的计算机处理需要，它所收录的汉字已经覆盖中国大陆99.75%的使用频率。对于人名、古汉语等方面出现的罕用字，GB2312不能处理，这导致了后来GBK及GB 18030汉字字符集的出现
````

#### Unicode 

- Unicode编码系统为表达任意语言的任意字符而设计。它使用`4字节的数字`来表达每个字母、符号，或者表意文字(ideograph)。每个数字代表唯一的至少在某种语言中使用的符号。（并不是所有的数字都用上了，但是总数已经超过了65535，所以2个字节的数字是不够用的。）被几种语言共用的字符通常使用相同的数字来编码，除非存在一个在理的语源学(etymological)理由使不这样做。不考虑这种情况的话，每个字符对应一个数字，每个数字对应一个字符。即不存在二义性。不再需要记录"模式"了。U+0041总是代表'A'，即使这种语言没有'A'这个字符。

- 在计算机科学领域中，Unicode（统一码、万国码、单一码、标准万国码）是业界的一种标准,它可以使电脑得以体现世界上数十种文字的系统。Unicode 是基于通用字符集（Universal Character Set）的标准来发展，并且同时也以书本的形式对外发表

- **Unicode是字符集，UTF-32/ UTF-16/ UTF-8是三种字符编码方案**

##### UTF-8

- UTF-8（8-bit Unicode Transformation Format）是一种针对Unicode的可变长度字符编码（定长码），也是一种前缀码。它可以用来表示Unicode标准中的任何字符，且其编码中的第一个字节仍与ASCII兼容，这使得原来处理ASCII字符的软件无须或只须做少部份修改，即可继续使用。因此，它逐渐成为电子邮件、网页及其他存储或传送文字的应用中，优先采用的编码。互联网工程工作小组（IETF）要求所有互联网协议都必须支持UTF-8编码。

- UTF-8使用一至四个字节为每个字符编码：

1. 128个US-ASCII字符只需一个字节编码（Unicode范围由U+0000至U+007F）。
2. 带有附加符号的拉丁文、希腊文、西里尔字母、亚美尼亚语、希伯来文、阿拉伯文、叙利亚文及它拿字母则需要二个字节编码（Unicode范围由U+0080至U+07FF）。
3. 其他基本多文种平面（BMP）中的字符（这包含了大部分常用字）使用三个字节编码
4. 其他极少使用的Unicode辅助平面的字符使用四字节编码

##### 优点

- UTF-8是ASCII的一个超集。因为一个纯ASCII字符串也是一个合法的UTF-8字符串，所以现存的ASCII文本不需要转换。为传统的扩展ASCII字符集设计的软件通常可以不经修改或很少修改就能与UTF-8一起使用
- 使用标准的面向字节的排序例程对UTF-8排序将产生与基于Unicode代码点排序相同的结果。（尽管这只有有限的有用性，因为在任何特定语言或文化下都不太可能有仍可接受的文字排列顺序。）
- UTF-8和UTF-16都是可扩展标记语言文档的标准编码。所有其它编码都必须通过显式或文本声明来指定
- 任何面向字节的字符串搜索算法都可以用于UTF-8的数据（只要输入仅由完整的UTF-8字符组成）。但是，对于包含字符记数的正则表达式或其它结构必须小心。
- UTF-8字符串可以由一个简单的算法可靠地识别出来。就是，一个字符串在任何其它编码中表现为合法的UTF-8的可能性很低，并随字符串长度增长而减小。举例说，字符值C0,C1,F5至FF从来没有出现。为了更好的可靠性，可以使用正则表达式来统计非法过长和替代值（可以查看W3 FAQ: Multilingual Forms上的验证UTF-8字符串的正则表达式）。

##### 缺点

- 因为每个字符使用不同数量的字节编码，所以寻找串中第N个字符是一个O(N)复杂度的操作 — 即，串越长，则需要更多的时间来定位特定的字符。同时，还需要位变换来把字符编码成字节，把字节解码成字符。

----

#### Accept-Charset/Accept-Encoding/Accept-Language/Content-Type/Content-Encoding/Content-Language

- Accept-Charset：浏览器申明自己接收的字符集，这就是本文前面介绍的各种字符集和字符编码，如gb2312，utf-8（通常我们说Charset包括了相应的字符编码方案）；

- Accept-Encoding：浏览器申明自己接收的编码方法，通常指定压缩方法，是否支持压缩，支持什么压缩方法（gzip，deflate），（注意：这不是只字符编码）；

- Accept-Language：浏览器申明自己接收的语言。语言跟字符集的区别：中文是语言，中文有多种字符集，比如big5，gb2312，gbk等等；

- Content-Type：WEB服务器告诉浏览器自己响应的对象的类型和字符集。例如：Content-Type: text/html; charset='gb2312'

- Content-Encoding：WEB服务器表明自己使用了什么压缩方法（gzip，deflate）压缩响应中的对象。例如：Content-Encoding：gzip

- Content-Language：WEB服务器告诉浏览器自己响应的对象的语言。
