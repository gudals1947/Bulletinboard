package com.example.se02.bulletinboard;

import android.content.Context;
import android.widget.Toast;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
/**
 * Created by 94_08 on 2017-12-03.
 */

public class BaseballGame {


        public static boolean isCorrect = false;
        private Context context;
        Map<Integer, Integer> ansMap = new LinkedHashMap<Integer, Integer>();

        public BaseballGame() {
        }

        public BaseballGame(Context context) {
            this.context = context;
        }

        public String process(int ans, int ran) {
            int strike = 0;
            int ball = 0;

            Map<Integer, Integer> ranMap = new LinkedHashMap<Integer, Integer>();

            //난수 (정답)자리수 구분
            ranMap.put(0, ran/1000);// 천자리
            ranMap.put(1, (ran%1000) / 100); //백의자리
            ranMap.put(2, (ran % 100) / 10); //십의 자리
            ranMap.put(3, ran % 10); //일의자리

            // strike 확인
            for (int i = 0; i < 4; i++) {
                if (ansMap.get(i) == ranMap.get(i)) {
                    strike++;
                }
            }

            // 4 strike 가 아니면
            if (strike != 4) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if ((i != j) && (ansMap.get(i) == ranMap.get(j))) {
                            ball++;
                        }
                    }
                }
            } else {
                isCorrect = true;        // 정답을 맞춘경우
                Toast.makeText(context, "정답입니다!", Toast.LENGTH_SHORT).show();
            }

            String result = strike + " strike, " + ball + " ball";
            return result;
        }

        // 네자리인지 확인
        public int isValid(String inStr) {

            int retInt;
            try {
                retInt = Integer.parseInt(inStr);
            } catch (NumberFormatException e) {
                Toast.makeText(context, "숫자가 아닙니다", Toast.LENGTH_SHORT).show();
                return -1;
            }
            if (retInt > 9999 || retInt < 1000) {
                Toast.makeText(context, "4 자리의 정수가 아닙니다.", Toast.LENGTH_SHORT).show();
                return -1;
            }


            if (!hasSameNum(retInt)) {
                return -1;
            }

            return retInt;
        }

        private boolean hasSameNum(int retInt) {
            ansMap.put(0, retInt/1000);
            ansMap.put(1, (retInt % 1000) /100);
            ansMap.put(2, (retInt % 100) / 10);
            ansMap.put(3, retInt % 10);

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if ((j != i) && (ansMap.get(i) == ansMap.get(j))) {
                        Toast.makeText(context, "네 자리의 숫자가 서로 다른 정수를 입력해 주세요..", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
            }
            return true;
        }

        public int genRanNum() {
            int retVal = 0;
            while (retVal < 1000) {
                retVal = new Random().nextInt(10000);

                if (!hasSameNum(retVal)) {
                    retVal = -1;
                }
            }

            System.out.println("Generated Random Number : " + retVal);
            return retVal;
        }

}
