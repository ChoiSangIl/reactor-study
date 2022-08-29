package bart.study.chapter01.flow;

import java.util.Random;

public class TempInfo {

    public static final Random random = new Random();

    private final String town;
    private final int temp;

    public TempInfo(String town, int temp) {
        this.town = town;
        this.temp = temp;
    }

    public static TempInfo fetch(String town) {
        if (random.nextInt(10) == 0) { // 10분의 1 확률로 작업 실패
            throw new RuntimeException("Error!");
        }
        return new TempInfo(town, random.nextInt(100)); // 임의의 화씨 온도를 반환
    }

    @Override
    public String toString() {
        return town + " : " + temp;
    }

    public int getTemp() {
        return temp;
    }

    public String getTown() {
        return town;
    }
}