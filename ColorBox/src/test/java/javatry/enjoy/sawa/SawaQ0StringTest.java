package javatry.enjoy.sawa;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import javatry.colorbox.ColorBox;
import javatry.colorbox.color.BoxColor;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;
import java.lang.NullPointerException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;

/**
 * 文字列のテスト。<br>
 * 何々は？と言われたら、それに該当するものをログに出力すること。
 * @author sawa
 */
public class SawaQ0StringTest extends ColorBoxTestCase {

    // TODO sawa 不要なimport文が残っている状態でpushしないようにしよう by yuto (2017/04/22)

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * 最初のカラーボックスの色の名前の文字数は？
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = getColorBoxList();
        ColorBox colorBox = colorBoxList.get(0);
        BoxColor boxColor = colorBox.getColor();
        String colorName = boxColor.getColorName();
        log(colorName, colorName.length());
    }

    /**
     * カラーボックスに入ってる文字列の中で、一番長い文字列は？
     */
    public void test_length_findMax() {
        List<ColorBox> colorBoxList = getColorBoxList();
        String strContentsMax = "";
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                if (contents instanceof String) {
                    String strContents = (String) contents;
                    if (strContents.length() > strContentsMax.length()) {
                        strContentsMax = strContents;
                    }
                }
            }
        }
        // TODO sawa ログを綺麗に出すことを意識してみよう、例えばここだと「一番長い文字列は「xxxx」です」みたいな感じ by yuto (2017/04/22)
        // TODO sawa 他のメソッドのログもよろしく by yuto (2017/04/22)
        log(strContentsMax);
    }

    /**
     * カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？
     */
    public void test_length_findMaxMinDiff() {
        // TODO sawa Nullチェックが必要であればif文でそうしよう、とりあえず try-catchじゃない by yuto (2017/04/22)
        try {
            int lengthMax = 0;
            int lengthMin = 0;
            List<ColorBox> colorBoxList = getColorBoxList();
            for (int i = 0; i < colorBoxList.size(); i++) {
                ColorBox colorBox = colorBoxList.get(i);
                List<BoxSpace> spaceList = colorBox.getSpaceList();
                for (int j = 0; j < spaceList.size(); j++) {
                    BoxSpace boxSpace = spaceList.get(j);
                    Object contents = boxSpace.getContents();
                    // TODO sawa strContents は instanceof のif文の中で定義してもいいよね？ by yuto (2017/04/22)
                    String strContents = "";
                    if (contents instanceof String) {
                        strContents = (String) contents;
                        if (strContents.length() > lengthMax) {
                            lengthMax = strContents.length();
                        } else if (strContents.length() < lengthMax) {
                            lengthMin = strContents.length();
                        }
                    }
                }
            }
            // TODO sawa "-" のまわりはスペースをいれよう by yuto (2017/04/22)
            log(lengthMax-lengthMin);
        } catch (NullPointerException e){
            log("文字列はありません");
        }
    }

    /**
     * カラーボックスに入ってる値 (文字列以外のものはtoString()) の中で、二番目に長い文字列は？ <br>
     * ソートして二番目を取得する、ってやり方で。
     */
    public void test_length_findSecondMax_bySort() {
        // TODO sawa あとでやる的なTODOコメントを自分で残しておこうね by yuto (2017/04/22)
        List<ColorBox> colorBoxList = getColorBoxList();
        String array[] = new String[colorBoxList.size()];
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            for (int j = 0; j < boxSpaceList.size(); j++) {
                BoxSpace boxSpace = boxSpaceList.get(j);
                Object contents = boxSpace.getContents();
                if (contents instanceof String) {
                    array[j] = (String) contents;
                } else if (contents != null) {
                    array[j] = contents.toString();
                }
            }
        }
        int intArray[] = null;
        for (int i = 0; i < colorBoxList.size(); i++) {
            intArray = new int[colorBoxList.get(i).getSpaceList().size()];
            intArray[i] = array[i].length();
        }
        Arrays.sort(intArray);
        log(array[1]);
        log(array[2]);
        log(array[5]);
        for (int i = 0; i < colorBoxList.size(); i++) {
            if (array[i].length() == intArray[1]) {
                log(array[i]);
            }
        }
    }

    /**
     * カラーボックスに入ってる値 (文字列以外のものはtoString()) の中で、二番目に長い文字列は？ <br>
     * ただし、ソートして二番目を取得する、ってやり方は利用しないこと。
     */
    public void test_length_findSecondMax_nonSorted() {
        // TODO sawa 無駄な空行削除 by yuto (2017/04/22)

        String strMax = "";
        String strMaxSecond = "";
        List<ColorBox> colorBoxList = getColorBoxList();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            for (int j = 0; j < boxSpaceList.size(); j++) {
                BoxSpace boxSpace = boxSpaceList.get(j);
                Object contents = boxSpace.getContents();
                String strContents = "";
                // TODO sawa StringもそうでないものもtoString()するなら、キャストを考慮しなくてもいいよ by yuto (2017/04/22)
                if (contents instanceof String) {
                    strContents = (String) contents;
                } else if (contents != null) {
                    strContents = contents.toString();
                }
                if (strContents.length() > strMax.length()) {
                    // TODO sawa このとき、strMaxSecondのなかには、3番目に大きな文字列が入っていないかなぁ.. by yuto (2017/04/22)
                    strMax = strContents;
                } else if (strContents.length() > strMaxSecond.length() && strContents.length() < strMax.length()) {
                    strMaxSecond = strContents;
                }
            }
        }
        log(strMaxSecond);
    }

    /**
     * カラーボックスに入ってる文字列の長さの合計は？
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = getColorBoxList();
        // TODO sawa 何のsumかな？ by yuto (2017/04/22)
        int sum = 0;
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                // TODO sawa strContents は instanceof のif文の中で定義してもいいよね？ by yuto (2017/04/22)
                String strContents = null;
                if (contents instanceof String) {
                    // TODO sawa このif節の中で計算すれば else節 がいらないよね by yuto (2017/04/22)
                    strContents = (String) contents;
                } else {
                    strContents = "";
                }
                sum += strContents.length();
            }
        }
        log(sum);
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * 「かまくら」で始まる文字列をしまっているカラーボックスの色は？
     */
    public void test_startsWith_findFirstWord() {
        // TODO sawa さて、「かまくら」で始まる文字列をしまっているカラーボックスが2つ以上あったらこれで大丈夫でしょうか？ by yuto (2017/04/22)
        List<ColorBox> colorBoxList = getColorBoxList();
        int num = 0;
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                // TODO sawa strContents は instanceof のif文の中で定義してもいいよね？ by yuto (2017/04/22)
                String strContents = null;
                if (contents instanceof String) {
                    strContents = (String) contents;
                    if (strContents.startsWith("かまくら")) {
                        num = i;
                    }
                }
            }
        }
        ColorBox colorBox = colorBoxList.get(num);
        BoxColor boxColor = colorBox.getColor();
        log(boxColor.getColorName());
    }

    /**
     * 「いぬ」で終わる文字列をしまっているカラーボックスの色は？
     */
    public void test_endsWith_findLastWord() {
        // TODO sawa さて、「いぬ」で終わる文字列をしまっているカラーボックスが2つ以上あったらこれで大丈夫でしょうか？ by yuto (2017/04/22)
        List<ColorBox> colorBoxList = getColorBoxList();
        int num = 0;
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                // TODO sawa strContents は instanceof のif文の中で定義してもいいよね？ by yuto (2017/04/22)
                String strContents = null;
                if (contents instanceof String) {
                    strContents = (String) contents;
                    if (strContents.endsWith("いぬ")) {
                        num = i;
                    }
                }
            }
        }
        ColorBox colorBox = colorBoxList.get(num);
        BoxColor boxColor = colorBox.getColor();
        log(boxColor.getColorName());
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    // TODO sawa そろそろ"i"って書くのもめんどくさくなってきた頃だと思う、のでここからは拡張for文を使ってみよう by yuto (2017/04/22)
    /**
     * あなたのカラーボックスに入ってる「いぬ」で終わる文字列で、「いぬ」は何文字目から始まる？
     */
    public void test_indexOf_findIndex() {
        List<ColorBox> colorBoxList = getColorBoxList();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                String strContents = null;
                if (contents instanceof String) {
                    strContents = (String) contents;
                    if (strContents.endsWith("いぬ")) {
                        // TODO sawa わざわざdogっていう変数つくる意味ないよね by yuto (2017/04/22)
                        String dog = strContents;
                        // TODO sawa "+"のまわりにスペースをいれよう by yuto (2017/04/22)
                        // TODO sawa 1を足す理由をコメントに書いておこう by yuto (2017/04/22)
                        log(dog.indexOf("いぬ")+1);
                    }
                }
            }
        }
    }

    /**
     * あなたのカラーボックスに入ってる「ず」を二つ以上含む文字列で、最後の「ず」は何文字目から始まる？
     */
    public void test_lastIndexOf_findIndex() {
        List<ColorBox> colorBoxList = getColorBoxList();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                String strContents = null;
                if (contents instanceof String) {
                    strContents = (String) contents;
                    if (strContents.matches(".*ず.*ず.*")) {
                        int num = strContents.lastIndexOf("ず");
                        // TODO sawa 本当に12文字目？ by yuto (2017/04/22)
                        log(num);
                    }
                }
            }
        }
    }

    // ===================================================================================
    //                                                                         substring()
    //                                                                         ===========
    /**
     * カラーボックスに入ってる「いぬ」で終わる文字列の最初の一文字は？
     */
    public void test_substring_findFirstChar() {
        // TODO sawa 無駄な空行削除 by yuto (2017/04/22)

        List<ColorBox> colorBoxList = getColorBoxList();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                String strContents = null;
                if (contents instanceof String) {
                    strContents = (String) contents;
                    if (strContents.endsWith("いぬ")) {
                        char strDog = strContents.charAt(0);
                        log(strDog);
                    }
                }
            }
        }
    }

    /**
     * カラーボックスに入ってる「かまくら」で始まる文字列の最後の一文字は？
     */
    public void test_substring_findLastChar() {
        List<ColorBox> colorBoxList = getColorBoxList();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                // TODO sawa strContents は instanceof のif文の中で定義してもいいよね？ by yuto (2017/04/22)
                String strContents = null;
                if (contents instanceof String) {
                    strContents = (String) contents;
                    if (strContents.startsWith("かまくら")) {
                        // TODO sawa "-"のまわりはスペースをいれよう by yuto (2017/04/22)
                        log(strContents.charAt(strContents.length()-1));
                    }
                }
            }
        }
    }



    // ===================================================================================
    //                                                                           replace()
    //                                                                           =========
    /**
     * カラーボックスに入ってる「ー」を含んだ文字列から「ー」を全て除去したら何文字？
     */
    public void test_replace_removeBo() {
        List<ColorBox> colorBoxList = getColorBoxList();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                // TODO sawa strContents は instanceof のif文の中で定義してもいいよね？ by yuto (2017/04/22)
                String strContents = null;
                if (contents instanceof String) {
                    strContents = (String) contents;
                    if (strContents.contains("ー")) {
                        log(strContents.replaceAll("ー", "").length());
                    }
                }
            }
        }
        // TODO sawa 無駄な空行削除 by yuto (2017/04/22)

    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * カラーボックスの中で、色の名前が一番長いものは？
     */
    public void test_findMaxColorSize() throws Exception {
        List<ColorBox> colorBoxList = getColorBoxList();
        // TODO sawa "最大の長さ"は数字が入っていそうな気がしない？ maxLangthColorName とかかな... by yuto (2017/04/22)
        String maxLength = "";
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            BoxColor boxColor = colorBox.getColor();
            String colorName = boxColor.getColorName();
            // TODO sawa 外側のif文って必要かな？ by yuto (2017/04/22)
            if (i == 0) {
                maxLength = colorName;
            } else {
                if (colorName.length() > maxLength.length()) {
                    maxLength = colorName;
                }
            }
        }
        log(maxLength);
    }

    /**
     * カラーボックスの中で、2012/06/04 を示す日付が持っている秒は？
     */
    public void test_findDBFluteBirthdateSecond() throws Exception {

    }

    /**
     * カラーボックスの中に入っている Map を "map:{ key = value ; key = value ; ... }" という形式で表示。
     */
    public void test_showMap() throws Exception {
    }

    /**
     * "map:{ key1 = value1 ; key2 = value2 ; key3 = value3 }" という文字列をMapに変換してtoString()すると？
     * <pre>
     * 変換後のMapの中身は、以下のようになっていること
     *  o key1というキーに対してvalue1という値
     *  o key2というキーに対してvalue2という値
     *  o key3というキーに対してvalue3という値
     * </pre>
     */
    public void test_parseMap() throws Exception {
    }

    /**
     * "map:{ key1 = value1 ; key2 = map:{ nkey21 = nvalue21 ; nkey22 = nvalue22 } ; key3 = value3 }" <br />
     * という文字列をMapに変換してtoString()すると？ <br />
     * <br />
     * "map:{ key1 = value1 ; key2 = value2 ; key3 = map:{ nkey31 = nvalue31 ; nkey32 = nvalue32 } }" <br />
     * でも、同じプログラムでMapに変換できるようにするべし。
     */
    public void test_parseMap_deep() throws Exception {
    }
}
