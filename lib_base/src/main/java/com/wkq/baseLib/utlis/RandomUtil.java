package com.wkq.baseLib.utlis;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/25
 * <p>
 * 简介:
 */
import java.util.Random;

/**
 * @ClassName: RandomUtil
 * @Description: 随机数工具类
 * (分别使用java.util.Random、Apache Common Math3、Apache Common Lang3、TreadLocalRandom)
 */
public class RandomUtil {
    /**
     * 随机数Int的生成
     */
    // 随机数生成无边界的Int
    public static int getRandomForIntegerUnbounded() {
        int intUnbounded = new Random().nextInt();
        return intUnbounded;
    }

    // 生成有边界的Int
    public static int getRandomForIntegerBounded(int min, int max) {

        Random random=new Random();
        random.setSeed(System.currentTimeMillis());
        int intBounded = min + ((int) (new Random().nextFloat() * (max - min)));
        return intBounded;
    }
}