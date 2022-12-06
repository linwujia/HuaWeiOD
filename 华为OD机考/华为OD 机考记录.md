## 华为OD 机考记录

### 背景

​		本来以为公司Android开发就我一个人，裁员应该不会到我头上，没想到在11月的时候，被通知在裁员名单上，接下来就是跟行政谈赔偿问题，以及交接工作。休息了几天，修改简历，然后就开始了投简历找工作，年底工作不好找，面试机会少。3个星期过去了就面试了5家公司，有两家给了offer，涨薪幅度都比较小，在20%，拒绝了。在Boss上接触了华为OD的招聘，想着试试，就当练练手，华为OD招聘的第一步就是先要机考，机考过了才能进行下一步的面试。约了机考，机考是在牛客网线上完成，一个星期内必须完成考试。接着就是刷题，牛客网上有华为的[刷题库](https://www.nowcoder.com/exam/oj/ta?tpId=37)，也可以在[leetcode](https://leetcode.cn/)上刷。

### 机考

​		刷了5天的题，招聘方就催我去机考了，本来想第二天再去机考的，招聘方担心后面有问题，需要预留时间解决问题，以免手忙脚乱的，就让提前一天机考。当天晚上吃完饭，8点就开始了机考，前面那一堆录入信息、手机扫二维码、例题示范的就不详细讲了，下面开始讲试题。

​		首先说一下试题，总共有3道题，前面两道题属于简单类型的（每道题100分），后面一道题属于中等难度的(200分)，做完上一道题才能开始下一道题。

#### 第一道题

​		题意：反转单词，每行输入一个字符串，字符串含有单词和字符  **.,?**  ，要求对单词进行反转， 单词间空格>0， 字符 **.,?**  间空格 >= 0, 反转结果输出一行。(靠记忆整理)

示例1：

​	输入：

​			My name is Bob.

​	输出：

​			yM eman si boB.



示例2：

​	输入：

​			How are you ? i am fine

​	输出：

​			woH era uoy ? i ma enif



**解题思路过程：** 

​		看到这道题的第一想法，就是按空格分割字符串，然后对单词进行反转。考虑到分割后，有可能单词跟那三个标点符号会组合在一起，还得单独做处理，感觉处理逻辑上会很复杂。接着想有没有其他办法，看到单词反转，觉得有点像 栈，先进后出，这样就实现了单词反转。这样定义一个栈，一个一个字符串容器（StringBuilder），遍历字符串，如果不是空格，以及那三个标点字符，就将字符进栈，当碰到空格、三个标点字符的时候呢，就将栈的字符出栈添加到字符串容器中(StringBuilder)，然后在添加遍历到的这个空格或者是标点字符。

 **代码实现：**

​		思考好解题思路后，就开始动手了(机考是可以先在本地IDE上编码，自测通过后，再复制到牛客网上)

~~~java
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            System.out.println(reverseLine(line));
        }
    }

    public static String reverseLine(String line) {
        Stack<Character> stack = new Stack<>();//栈
        StringBuilder s = new StringBuilder();//字符串容器

        for (char c : line.toCharArray()) {
            if (c == ' ' || c == '.' || c == ',' || c == '?') {
                if (!stack.isEmpty()) s.append(stackToString(stack));//先将之前的反转，添加到(StringBuilder)字符串容器中
                s.append(c);//如果是上面这四个，直接添加到字符串容器(StringBuilder)中
            } else {
                stack.push(c);//不是上面的那些字符，就直接入栈
            }
        }

        return s.toString();
    }

    public static String stackToString(Stack<Character> stack) {
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb = sb.append(stack.pop());
        }

        return sb.toString();
    }
}
~~~



一阵霹雳巴拉的操作下来，代码写完了，自测通过，然后开始开心的提交代码

![测试结果](https://github.com/linwujia/HuaWeiOD/blob/master/%E5%8D%8E%E4%B8%BAOD%E6%9C%BA%E8%80%83/images/%E7%AC%AC%E4%B8%80%E9%A2%98%E6%B5%8B%E8%AF%95%E7%BB%93%E6%9E%9C1.png)

接下来就是牛客网显示测试用例只有33.xx%通过，郁闷了，接下来就是在本地IDE随便输了，盲测，结果发现

![自测结果](https://github.com/linwujia/HuaWeiOD/blob/master/%E5%8D%8E%E4%B8%BAOD%E6%9C%BA%E8%80%83/images/%E7%AC%AC%E4%B8%80%E9%A2%98%E8%87%AA%E6%B5%8B3.png)

哦豁，接下来就是本地IDE调试，发现最后一个单词fine，后面没有空格，也没有标点字符，所以就没有添加到字符串容器中了，接下来继续修改。

```java
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            System.out.println(reverseLine(line));
        }
    }

    public static String reverseLine(String line) {
        Stack<Character> stack = new Stack<>();//栈
        StringBuilder s = new StringBuilder();//字符串容器

        for (char c : line.toCharArray()) {
            if (c == ' ' || c == '.' || c == ',' || c == '?') {
                if (!stack.isEmpty()) s.append(stackToString(stack));//先将之前的反转，添加到(StringBuilder)字符串容器中
                s.append(c);//如果是上面这四个，直接添加到字符串容器(StringBuilder)中
            } else {
                stack.push(c);//不是上面的那些字符，就直接入栈
            }
        }

		//没有我可不行, 需要将最后栈中的字符添加到字符串容器中
        if (!stack.isEmpty()) s.append(stackToString(stack));

        return s.toString();
    }

    public static String stackToString(Stack<Character> stack) {
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb = sb.append(stack.pop());
        }

        return sb.toString();
    }
}
```

继续本地自测, 自测都通过了，重新复制到牛客网，提交，这下测试用例全部通过，开始第二题（花了半个小时了）

![本地自测](https://github.com/linwujia/HuaWeiOD/blob/master/%E5%8D%8E%E4%B8%BAOD%E6%9C%BA%E8%80%83/images/%E7%AC%AC%E4%B8%80%E9%A2%98%E8%87%AA%E6%B5%8B3.png)





#### 第二道题

​		题意：版本号比较，版本号规则：主版本号.次版本号.增量版本号-发布版本号，  主版本号、次版本号是必须的，主版本号、次版本号、增量版本号是数字，可能存在前导0，发布版本号为字符，可以按字典顺序进行比较，如果两个版本号相等，返回第一个版本号

​		输入：每个版本号按行输入，输入两个版本号

​		输出：版本号高的

示例1

​		输入：

​				1.05.1

​				1.5.1

​		输出：

​				1.05.1

​		两个版本号一样，输出第一个



示例2

​		输入：

​				1.5.2-A

​				1.5.2-a

​		输出：

​				1.5.2-a

​		因为 a 为97 大于 A 65



示例3

​		输入：

​				1.5

​				1.5.0

​		输出：

​				1.5.0

​		1.5.0有增量版本号，输出1.5.0



**解题思路过程：**第一想法，用两个指针，从两个字符串的头开始，不断向后移动遍历，进行比较，因为存在前导0，以及两个版本长度不一样，可能处理上比较麻烦（只是个人这么想，大家也可以尝试一下，当时考试，也没那么多时间尝试）。后面想还是按照最常规的方式处理吧，毕竟版本号字符串是固定，最长也就四段。对版本号字符串进行分割，如果包含 '-' ，分割出前面数字的版本号，再接着对前面的数字的版本号再按 '.' 进行分割，然后开始转换数组比较，最后再对后面发布版本号按字典顺序比较即可。

**代码实现：**

​	下面开始代码实现

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String first = in.nextLine();
        String second = in.nextLine();

        System.out.println(compareVersion(first, second));
    }

    public static String compareVersion(String first, String second) {
        if (first == null || first.isEmpty()) return second;

        if (second == null || second.isEmpty()) return first;

        String fNumVersion;
        String fStringVersion;
        if (first.contains("-")) {
            int index = first.indexOf("-");
            fNumVersion = first.substring(0, index);
            fStringVersion = first.substring(index + 1);
        } else {
            fNumVersion = first;
            fStringVersion = "";
        }

        String sNumVersion;
        String sStringVersion;
        if (second.contains("-")) {
            //因为是复制前面的，给自己埋下了坑
            int index = second.indexOf("-");
            //int index = first.indexOf("-"); //复制前面的，忘记修改了
            sNumVersion = second.substring(0, index);
            //sNumVersion = first.substring(0, index); //复制前面的，忘记修改了
            sStringVersion = second.substring(index + 1);
            //sStringVersion = first.substring(index + 1); //复制前面的，忘记修改了
        } else {
            sNumVersion = second;
            sStringVersion = "";
        }

        String[] fSplits = fNumVersion.split("\\.");
        String[] sSplits = sNumVersion.split("\\.");

        //比较主要、次要
        for (int i = 0; i < 2; i++) {
            int fVersion = Integer.parseInt(fSplits[i]);
            int sVersion = Integer.parseInt(sSplits[i]);

            if (fVersion > sVersion) return first;
            if (fVersion < sVersion) return second;
        }

        //有没有增量
        if (fSplits.length == 3 && fSplits.length == sSplits.length) {
            //有增量
            int fIncrease = Integer.parseInt(fSplits[2]);
            int sIncrease = Integer.parseInt(sSplits[2]);
            if (fIncrease > sIncrease) return first;
            if (fIncrease < sIncrease) return second;
        } else if (fSplits.length > sSplits.length) {
            return first;
        } else {
            return second;
        }

        if (fStringVersion.isEmpty()) {
            if (sStringVersion.isEmpty()) return first;
            return second;
        }

        int diff = fStringVersion.compareTo(sStringVersion);
        if (diff >= 0) {
            return first;
        } else {
            return second;
        }
    }
}
```



过程中，在分割前面数字版本号跟后面的发布版本号时，第二个版本号的处理是复制前面第一个的处理，但是有些变量忘记改过来了，导致提交的时候，测试用例只有67.xx%通过，当时引文怕时间不够，不知道后面一道中等难度会不会很难，就没有去定位原因，直接先去做第三题了（测试用例没有全部通过，也是可以提交做下一题的），这里是已经改正了的，改正后测试用例全部通过。代码里对当时忘记修改的，做了注释。



#### 第三道题

​		题意：字符串中只存在A或者B两种字符，求最少修改次数即可实现非递减字符串，可同时修改A或者B，非递减字符串，及后面的字符不会小于前面的字符（ASCII码值，这里只有A、B两种字符，只有B后面不出现A即为非递减字符串）

示例1

​		输入：

​				AABBA

​		输出：

​				1

​		因为修改最后一个A为B即可，AABBB 为非递减字符串， 修改BB为AA也可以，但是修改次数为2，非最少次数。



**解题思路过程：**刚看到这道题，真的一点思路也没有。然后就想着先统计B之后A的个数，一边写一边思考，突然想到遍历字符串，从左到右遍历，统计在第一出现B之后A的个数，然后从右往左遍历，统计第一出现A之后B的个数，例如上面AABBA, 从左到右遍历，从第一次出现B开始，紧按着的B顺延，然后开始统计A的次数，此时为1；接着从右往左遍历，统计第一次出现A之后B的个数，这里从后面的第一个A开始，后面即为B，统计个数为2，接着对这两个次数取最小值。其实也不知道行不行，但是当时也没有别的方法，先试试。

**代码实现：**

​		接着就继续写代码实现

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            System.out.println();
        }
    }

    public static int minModify(String s) {
        if (s == null || s.isEmpty()) return 0;

        char[] chars = s.toCharArray();

        //1.从前往后遍历，统计第一次出现B后面有多少个A
        boolean startB = false; //是否已经出现过B了
        int aSum = 0; // A的累计个数
        for (int i = 0; i < chars.length; i++) {
            //当前是B，而且之前没有出现过B，记录出现过B了
            if (chars[i] == 'B' && !startB) startB = true;

            //已经出现过B了，而且当前是A，则进行累加
            if (startB && chars[i] == 'A') aSum++;
        }

        //2.从后往前遍历，统计第一次出现A后面有多少个B
        boolean startA = false;//是否已经出现过A了
        int bSum = 0; // B的累计个数
        for (int i = chars.length - 1; i >= 0; i--) {
            //当前是A，而且之前没有出现过A，记录出现过A了
            if (chars[i] == 'A' && !startA) startA = true;

            if (startA && chars[i] == 'B') bSum++;
        }

        return Math.min(aSum, bSum);
    }
}
```



代码写完了，本地自测 AABBA 结果正常，然后复制代码到牛客网，提交测试，没想到全部测试用例测试通过，想想也就是运气好。

这时一看，剩下还有35分钟时间，就重新回到第二题，定位问题，最终第二题也全部测试用例测试通过。哈哈，第三题真的是运气。最终交卷，时间还剩下27分钟，不过也已经十点，可以洗洗睡了。

​		第二个收到招聘方微信，机考分数400分，后面就是提交资料，然后等待面试了。

![机考分数](https://github.com/linwujia/HuaWeiOD/blob/master/%E5%8D%8E%E4%B8%BAOD%E6%9C%BA%E8%80%83/images/%E6%9C%BA%E8%80%83%E5%88%86%E6%95%B0.png)

后记：可能这些对于大佬来说，觉得 so easy，本人也只是记录一下，同时希望能帮助到别人。

