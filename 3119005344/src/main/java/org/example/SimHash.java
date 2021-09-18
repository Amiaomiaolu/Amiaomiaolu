package org.example;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimHash {
    public static BigInteger simHash(String str) {
        int[] hashValue = new int[64];// 定义特征向量

        List<Term> termList = StandardTokenizer.segment(str);
        Map<String, Integer> wordMap = new HashMap<>();
        Map<String, String> stop = new HashMap<>();//停用的词性 如一些标点符号之类的;

        //根据词频计算权重
        for (Term term : termList) {
            String word = term.word;
            String nature = term.nature.toString();
            if (nature.equals("w")) {
                stop.put(word, "");
            } else {
                if (!wordMap.containsKey(word))
                    wordMap.put(word, 1);
                else {
                    int pre = wordMap.get(word);
                    wordMap.put(word, pre + 1);
                }
            }
        }

        int overCount = 5; //设定超频词汇的界限 ;

        Map<String, Integer> wordCount = new HashMap<>();

        for (Term term : termList) {
            String word = term.word; //分词字符串
            String nature = term.nature.toString(); // 分词属性
            //  过滤超频词
            if (wordCount.containsKey(word)) {
                int count = wordCount.get(word);
                if (count > overCount) {
                    continue;
                }
                wordCount.put(word, count + 1);
            } else {
                wordCount.put(word, 1);
            }

            // 过滤停用词性
            if (stop.containsKey(nature)) {
                continue;
            }

            //对每一个分词进行hash,hash成64bit的大整数.
            BigInteger hashNumber = hash(word);

            for (int i = 0; i < 64; i++) {
                BigInteger bitMask = new BigInteger("1").shiftLeft(i);
                //添加权重
                int weight = 1;
                if (wordMap.containsKey(nature)) {
                    weight = wordMap.get(nature);
                }
                //逢1加，逢0减
                if (hashNumber.and(bitMask).signum() != 0) {
                    // 这里是计算整个文档的所有特征的向量和
                    hashValue[i] += weight;
                } else {
                    hashValue[i] -= weight;
                }
            }
        }

        BigInteger fingerprint = new BigInteger("0");
        for (int i = 0; i < 64; i++) {
            // 最后对数组进行判断,大于0的记为1,小于等于0的记为0,得到一个 64bit 的数字签名.
            if (hashValue[i] >= 0) {
                fingerprint = fingerprint.add(new BigInteger("1").shiftLeft(i));
            }
        }
        return fingerprint;

    }

    //simHash,将分词hash
    public static BigInteger hash(String source) {
        if (source == null || source.length() == 0) {
            return new BigInteger("0");
        } else {
            //当source的长度过短hash算法会失效，所以要对过短的词进行补偿
            StringBuilder sourceBuilder = new StringBuilder(source);
            while (sourceBuilder.length() < 3) {
                sourceBuilder.append(sourceBuilder.charAt(0));
            }
            source = sourceBuilder.toString();
            char[] sourceArray = source.toCharArray();
            BigInteger ans = BigInteger.valueOf(((long) sourceArray[0]) << 7);
            BigInteger MAX_NUM = new BigInteger("1000003");

            BigInteger mask = new BigInteger("2").pow(64).subtract(new BigInteger("1"));

            for (char item : sourceArray) {
                BigInteger temp = BigInteger.valueOf(item);
                ans = ans.multiply(MAX_NUM).xor(temp).and(mask);
            }
            ans = ans.xor(new BigInteger(String.valueOf(source.length())));
            if (ans.equals(new BigInteger("-1"))) {
                ans = new BigInteger("-2");
            }

            return ans;
        }
    }


    //simHash,计算两个海明码之间的距离
    public static int hammingDistance(String s1, String s2) {
        BigInteger simHashNum = simHash(s1);
        BigInteger another = simHash(s2);

        BigInteger MAX_NUM = new BigInteger("1").shiftLeft(64).subtract(new BigInteger("1"));
        BigInteger x = simHashNum.xor(another).and(MAX_NUM);
        // 统计x中二进制位数为1的个数
        // 一个二进制数减去1，那么，从最后那个1（包括那个1）后面的数字全都反了，然后，n&(n-1)就相当于把后面的数字清0。
        int ans = 0;
        while (x.signum() != 0) {
            ans += 1;
            x = x.and(x.subtract(new BigInteger("1"))); //x &= x - 1
        }

        return ans;
    }

    public static double getAns(String s1, String s2) {
        return 1 - (double) hammingDistance(s1, s2) / 64.0;
    }
}
