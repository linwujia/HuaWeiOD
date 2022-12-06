package com.linwj.auto.annotate.lib;

import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    /*public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            System.out.println(reverseLine(line));
        }
    }*/

    /*public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String first = in.nextLine();
        String second = in.nextLine();

        System.out.println(compareVersion(first, second));
    }*/

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            System.out.println(minModify(line));
        }
    }

    /**
     * 第三题，最小修改实现非递减字符串
     * @param s
     * @return
     */
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

    /**
     * 版本比较
     * @param first
     * @param second
     * @return
     */
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

    /**
     * 第一道题 反转单词
     * @param line
     * @return
     */
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