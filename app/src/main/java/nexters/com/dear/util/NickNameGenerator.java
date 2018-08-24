package nexters.com.dear.util;

import java.util.Random;

public class NickNameGenerator {
    private static final String[] ColorNames = {"자주색", "다홍색", "분홍색", "주황색", "라임색", "노락색", "연노랑색",
            "초록색", "연두색", "청록색", "파란색", "하늘색", "남색", "보라색", "연보라색"};
    private static final String[] AnimalNames = {"호랑이", "앵무새", "물고기", "고양이", "강아지", "재규어", "팬더"
            , "사자", "표범", "곰", "미어캣", "북극곰", "햄스터", "개구리", "사슴"};

    public static String generate(){
        String result = null;
        Random rnd = new Random();
        result = ColorNames[rnd.nextInt(ColorNames.length)];
        result = result + " " + AnimalNames[rnd.nextInt(AnimalNames.length)];
        return result;
    }
}
