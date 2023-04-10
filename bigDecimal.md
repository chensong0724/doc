# `BigDecimal`中的五个容易踩的坑

## 1.1 `new BigDecimal()`还是`BigDecimal#valueOf()`？

```java
BigDecimal bd1 = new BigDecimal(0.01);
BigDecimal bd2 = BigDecimal.valueOf(0.01);
System.out.println("bd1 = " + bd1);
System.out.println("bd2 = " + bd2);
```

输出：

```out
bd1 = 0.01000000000000000020816681711721685132943093776702880859375
bd2 = 0.01
```

造成的原因：

造成这种差异的原因是0.01这个数字计算机是无法精确表示的，送给`BigDecimal`的时候就已经丢精度了

BigDecimal#valueOf使用了浮点数相应的字符串来构造`BigDecimal`对象，因此避免了精度问题。所以大家要尽量要使用字符串而不是浮点数去构造`BigDecimal`对象，如果实在不行，就使用`BigDecimal#valueOf()`方法吧。

## 1.2 等值比较

```java
BigDecimal bd1 = new BigDecimal("1.0");
BigDecimal bd2 = new BigDecimal("1.00");
System.out.println(bd1.equals(bd2));
System.out.println(bd1.compareTo(bd2));
```

输出：

```out
false
0
```

原因：

BigDecimal`中`equals`方法的实现会比较两个数字的精度，而`compareTo方法则只会比较数值的大小。

## 1.3 `BigDecimal`并不代表无限精度

```java
BigDecimal a = new BigDecimal("1.0");
BigDecimal b = new BigDecimal("3.0");
a.divide(b) // results in the following exception.
```

输出:

```exception
Exception in thread "main" java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
	at java.math.BigDecimal.divide(BigDecimal.java:1693)
	at cn.htd.demo.TestBigDec.main(TestBigDec.java:15)
```

原因：

如果除法的商的结果是一个无限小数但是我们期望返回精确的结果，那程序就会抛出异常。回到我们的这个例子，我们需要告诉`JVM`我们不需要返回精确的结果就好了

**写成这样**

```java
BigDecimal a = new BigDecimal("1.0");
BigDecimal b = new BigDecimal("3.0");
a.divide(b, 2, RoundingMode.HALF_UP)// 0.33
```

## 1.4 `BigDecimal`转回`String`要小心

```out
String toString();     // 有必要时使用科学计数法
String toPlainString();   // 不使用科学计数法
String toEngineeringString();  // 工程计算中经常使用的记录数字的方法，与科学计数法类似，但要求10的幂必须是3的倍数
```

## 1.5 执行顺序不能调换（乘法交换律失效）

乘法满足交换律是一个常识，但是在计算机的世界里，会出现不满足乘法交换律的情况

```java
BigDecimal a = BigDecimal.valueOf(1.0);
BigDecimal b = BigDecimal.valueOf(3.0);
BigDecimal c = BigDecimal.valueOf(3.0);
System.out.println(a.divide(b, 2, RoundingMode.HALF_UP).multiply(c)); // 0.990
System.out.println(a.multiply(c).divide(b, 2, RoundingMode.HALF_UP)); // 1.00
```

## 金额计算

```java
        CurrencyUnit cny = Monetary.getCurrency("USD");
        Money money = Money.of(1.0, cny);
        System.out.println(money);

        /*计算*/
       /* CurrencyUnit cny = Monetary.getCurrency("CNY");
        Money oneYuan = Money.of(1.0, cny);
        Money threeYuan = oneYuan.add(Money.of(2.0, "CNY")); //CNY 3
        Money tenYuan = oneYuan.multiply(10); // CNY 10
        Money fiveFen = oneYuan.divide(2); //CNY 0.5*/

        /*比较*/
       /* Money fiveFen = Money.of(0.5, "USD"); //CNY 0.5
        Money anotherFiveFen = Money.of(0.50, "USD"); // CNY 0.50
        System.out.println(fiveFen.equals(anotherFiveFen)); // true*/
```

```
<dependency>
    <groupId>org.javamoney</groupId>
    <artifactId>moneta</artifactId>
    <version>1.4.2</version>
    <type>pom</type>
</dependency>
```

