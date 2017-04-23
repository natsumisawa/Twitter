package javatry.enjoy.sawa;

import javatry.colorbox.ColorBox;
import javatry.colorbox.color.BoxColor;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 文字列のテスト。<br>
 * 何々は？と言われたら、それに該当するものをログに出力すること。
 * @author sawa
 */
public class SawaQ0StringTest extends ColorBoxTestCase {

    // done sawa 不要なimport文が残っている状態でpushしないようにしよう by yuto (2017/04/22)

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
        // done sawa ログを綺麗に出すことを意識してみよう、例えばここだと「一番長い文字列は「xxxx」です」みたいな感じ by yuto (2017/04/22)
        // done sawa 他のメソッドのログもよろしく by yuto (2017/04/22)
        log("一番長い文字列は" + strContentsMax + "です");
    }

    /**
     * カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？
     */
    public void test_length_findMaxMinDiff() {
        // done sawa Nullチェックが必要であればif文でそうしよう、とりあえず try-catchじゃない by yuto (2017/04/22)
        ArrayList<Integer> strContentsList = new ArrayList<>();
        List<ColorBox> colorBoxList = getColorBoxList();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                // done sawa strContents は instanceof のif文の中で定義してもいいよね？ by yuto (2017/04/22)
                if (contents instanceof String) {
                    String strContents = (String) contents;
                    strContentsList.add(strContents.length());
                }
            }
        }
        // 最小値が保存できてなかったので以下のコードを修正しました　by sawa (2017/4/23)
        // TODO sawa [コメント] レビュワーにTODOコメントをつけると読んでもらいやすくなるよ、次からよろしく by yuto (2017/04/22)
        int maxLength = 0;
        int minLength = strContentsList.get(0);
        for (int i = 0; i < strContentsList.size(); i++) {
            maxLength = Math.max(strContentsList.get(i), maxLength);
            minLength = Math.min(strContentsList.get(i), minLength);
        }
        // done sawa "-" のまわりはスペースをいれよう by yuto (2017/04/22)
        log("一番長い文字列と短い文字列の差は" + (maxLength - minLength) + "です");
    }

    /**
     * カラーボックスに入ってる値 (文字列以外のものはtoString()) の中で、二番目に長い文字列は？ <br>
     * ソートして二番目を取得する、ってやり方で。
     */
    public void test_length_findSecondMax_bySort() {
        // done sawa あとでやる的なTODOコメントを自分で残しておこうね by yuto (2017/04/22)
        List<ColorBox> colorBoxList = getColorBoxList();
        ArrayList<String> strContentsList = new ArrayList<>();
        for (ColorBox colorBox: colorBoxList) {
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : boxSpaceList) {
                if (boxSpace.getContents() != null) {
                    strContentsList.add(boxSpace.getContents().toString());
                }
            }
        }
        // TODO sawa [修行] strContentsList.sort() を使ってやってみよう by yuto (2017/04/22)
        //バブルソート
        for (int i = 0; i < strContentsList.size() - 1; i++) {
            for (int j = 0; j < strContentsList.size() - 1; j++) {
                if (strContentsList.get(j).length() < strContentsList.get(j + 1).length()) {
                    String keepLonger = strContentsList.get(j);
                    strContentsList.set(j, strContentsList.get(j + 1));
                    strContentsList.set(j + 1, keepLonger);
                }
            }
        }
        // TODO sawa ログを綺麗に... by yuto (2017/04/22)
        // TODO sawa カラーボックスの中に文字列が一つしかないと落ちるのでどうにかしよう by yuto (2017/04/22)
        log(strContentsList.get(1));
    }

    /**
     * カラーボックスに入ってる値 (文字列以外のものはtoString()) の中で、二番目に長い文字列は？ <br>
     * ただし、ソートして二番目を取得する、ってやり方は利用しないこと。
     */
    public void test_length_findSecondMax_nonSorted() {
        // done sawa 無駄な空行削除 by yuto (2017/04/22)
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
                // done sawa StringもそうでないものもtoString()するなら、キャストを考慮しなくてもいいよ by yuto (2017/04/22)
                if (contents != null) {
                    strContents = contents.toString();
                }
                if (strContents.length() > strMax.length()) {
                    // done sawa このとき、strMaxSecondのなかには、3番目に大きな文字列が入っていないかなぁ.. by yuto (2017/04/22)
                    strMaxSecond = strMax;
                    strMax = strContents;
                } else if (strContents.length() > strMaxSecond.length() && strContents.length() < strMax.length()) {
                    strMaxSecond = strContents;
                }
            }
        }
        log("二番目に長い文字列は" + strMaxSecond + "です");
    }

    /**
     * カラーボックスに入ってる文字列の長さの合計は？
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = getColorBoxList();
        // done sawa 何のsumかな？ by yuto (2017/04/22)
        int contentsLengthSum = 0;
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                // done sawa strContents は instanceof のif文の中で定義してもいいよね？ by yuto (2017/04/22)
                if (contents instanceof String) {
                    // done sawa このif節の中で計算すれば else節 がいらないよね by yuto (2017/04/22)
                    String strContents = (String) contents;
                    contentsLengthSum += strContents.length();
                }
            }
        }
        log("文字列の長さの合計は" + contentsLengthSum + "です");
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * 「かまくら」で始まる文字列をしまっているカラーボックスの色は？
     */
    public void test_startsWith_findFirstWord() {
        // done sawa さて、「かまくら」で始まる文字列をしまっているカラーボックスが2つ以上あったらこれで大丈夫でしょうか？ by yuto (2017/04/22)
        List<ColorBox> colorBoxList = getColorBoxList();
        // TODO sawa インターフェースで受ける習慣を by yuto (2017/04/22)
        // TODO sawa 何で"Index"？ by yuto (2017/04/22)
        // TODO sawa ちなみに、順番を意識しないなら"Set"を使う方が良い by yuto (2017/04/22)
        ArrayList<Integer> kamakuraIndex = new ArrayList<>();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                // done sawa strContents は instanceof のif文の中で定義してもいいよね？ by yuto (2017/04/22)
                if (contents instanceof String) {
                    String strContents = (String) contents;
                    if (strContents.startsWith("かまくら")) {
                        kamakuraIndex.add(i);
                    }
                }
            }
        }
        for (int i = 0; i < kamakuraIndex.size(); i++) {
            ColorBox colorBox = colorBoxList.get(kamakuraIndex.get(i));
            BoxColor boxColor = colorBox.getColor();
            log("「かまくら」で始まる文字列をしまっているカラーボックスの色は" + boxColor.getColorName() + "です");
        }
    }

    /**
     * 「いぬ」で終わる文字列をしまっているカラーボックスの色は？
     */
    public void test_endsWith_findLastWord() {
        // TODO done sawa さて、「いぬ」で終わる文字列をしまっているカラーボックスが2つ以上あったらこれで大丈夫でしょうか？ by yuto (2017/04/22)
        List<ColorBox> colorBoxList = getColorBoxList();
        // TODO sawa インターフェースで受ける習慣を by yuto (2017/04/22)
        // TODO sawa 何で"Index"？ by yuto (2017/04/22)
        // TODO sawa ちなみに、順番を意識しないなら"Set"を使う方が良い by yuto (2017/04/22)
        ArrayList<Integer> inuIndex = new ArrayList<>();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                // TODO done sawa strContents は instanceof のif文の中で定義してもいいよね？ by yuto (2017/04/22)
                if (contents instanceof String) {
                    String strContents = (String) contents;
                    if (strContents.endsWith("いぬ")) {
                        inuIndex.add(i);
                    }
                }
            }
        }
        for (int i = 0; i < inuIndex.size(); i++) {
            ColorBox colorBox = colorBoxList.get(inuIndex.get(i));
            BoxColor boxColor = colorBox.getColor();
            log("「いぬ」で終わる文字列をしまっているカラーボックスの色は" + boxColor.getColorName() + "です");
        }
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    // done sawa そろそろ"i"って書くのもめんどくさくなってきた頃だと思う、のでここからは拡張for文を使ってみよう by yuto (2017/04/22)
    // 拡張for文素敵ですね、もう"i"とはさよならしました by sawa
    // TODO sawa [コメント] さよらなまでは言わないで、必要な時は使えばいいから 笑 by yuto (2017/04/22)
    /**
     * あなたのカラーボックスに入ってる「いぬ」で終わる文字列で、「いぬ」は何文字目から始まる？
     */
    public void test_indexOf_findIndex() {
        List<ColorBox> colorBoxList = getColorBoxList();
        for (ColorBox colorBox: colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace: spaceList) {
                Object contents = boxSpace.getContents();
                if (contents instanceof String) {
                    String strContents = (String) contents;
                    if (strContents.endsWith("いぬ")) {
                        // done sawa わざわざdogっていう変数つくる意味ないよね by yuto (2017/04/22)
                        // done sawa "+"のまわりにスペースをいれよう by yuto (2017/04/22)
                        // TODO done sawa 1を足す理由をコメントに書いておこう by yuto (2017/04/22)
                        int dogStartIndex = strContents.indexOf("いぬ") + 1;
                        log("「いぬ」は" + dogStartIndex + "文字目です");
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
        for (ColorBox colorBox: colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace: spaceList) {
                Object contents = boxSpace.getContents();
                if (contents instanceof String) {
                    String strContents = (String) contents;
                    if (strContents.matches(".*ず.*ず.*")) {
                        int index = strContents.lastIndexOf("ず");
                        // done sawa 本当に12文字目？ by yuto (2017/04/22)
                        // TODO done sawa 1を足す理由をコメントに書いておこう by yuto (2017/04/22)
                        log("最後の「ず」は" + (index + 1) + "文字目です");
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
        // done sawa 無駄な空行削除 by yuto (2017/04/22)
        List<ColorBox> colorBoxList = getColorBoxList();
        for (ColorBox colorBox: colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace: spaceList) {
                Object contents = boxSpace.getContents();
                if (contents instanceof String) {
                    String strContents = (String) contents;
                    if (strContents.endsWith("いぬ")) {
                        char firstChar = strContents.charAt(0);
                        // TODO sawa 細かすぎるけど「犬」ってかいちゃった...笑 by yuto (2017/04/22)
                        log("犬で終わる文字列の最初の一文字は" + firstChar + "です");
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
        for (ColorBox colorBox: colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace: spaceList) {
                Object contents = boxSpace.getContents();
                // done sawa strContents は instanceof のif文の中で定義してもいいよね？ by yuto (2017/04/22)
                if (contents instanceof String) {
                    String strContents = (String) contents;
                    if (strContents.startsWith("かまくら")) {
                        // done sawa "-"のまわりはスペースをいれよう by yuto (2017/04/22)
                        // TODO sawa ログを綺麗に... by yuto (2017/04/22)
                        log(strContents.charAt(strContents.length() - 1));
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
        for (ColorBox colorBox: colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace: spaceList) {
                Object contents = boxSpace.getContents();
                // done sawa strContents は instanceof のif文の中で定義してもいいよね？ by yuto (2017/04/22)
                if (contents instanceof String) {
                    String strContents = (String) contents;
                    if (strContents.contains("ー")) {
                        int strLength = strContents.replaceAll("ー", "").length();
                        log("「ー」を削除すると" + strLength + "文字です");
                    }
                }
            }
        }
        // done sawa 無駄な空行削除 by yuto (2017/04/22)
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * カラーボックスの中で、色の名前が一番長いものは？
     */
    public void test_findMaxColorSize() throws Exception {
        List<ColorBox> colorBoxList = getColorBoxList();
        // done sawa "最大の長さ"は数字が入っていそうな気がしない？ maxLangthColorName とかかな... by yuto (2017/04/22)
        String longestColorName = "";
        for (ColorBox colorBox: colorBoxList) {
            BoxColor boxColor = colorBox.getColor();
            String colorName = boxColor.getColorName();
            // done sawa 外側のif文って必要かな？ by yuto (2017/04/22)
            if (colorName.length() > longestColorName.length()) {
                longestColorName = colorName;
            }
        }
        log("色の名前が一番長いものは" + longestColorName + "です");
    }

    /**
     * カラーボックスの中で、2012/06/04 を示す日付が持っている秒は？
     */
    public void test_findDBFluteBirthdateSecond() throws Exception {
        List<ColorBox> colorBoxList = getColorBoxList();
        for (ColorBox colorBox: colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object contents = boxSpace.getContents();
                if (contents instanceof LocalDateTime) {
                    LocalDateTime time = (LocalDateTime) contents;
                    log("2012/06/04 を示す日付が持っている秒は" + time.getSecond() + "です");
                }
            }
        }
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
