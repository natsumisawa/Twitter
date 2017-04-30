package javatry.enjoy.sawa;

import javatry.colorbox.ColorBox;
import javatry.colorbox.color.BoxColor;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

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
        List<Integer> strContentsList = new ArrayList<>();
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
        // sawa [コメント] レビュワーにTODOコメントをつけると読んでもらいやすくなるよ、次からよろしく by yuto (2017/04/22)
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
        List<String> strContentsList = new ArrayList<>();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : boxSpaceList) {
                if (boxSpace.getContents() != null) {
                    strContentsList.add(boxSpace.getContents().toString());
                }
            }
        }
        // done sawa [修行] strContentsList.sort() を使ってやってみよう by yuto (2017/04/22)
        // done sawa そういえば "o1", "o2"って何？ by yuto (2017/04/27)
        // TODO done yuto 参考にしたコードに引っ張られたネーミングだったので、変更しました！ by sawa (2017/04/27)
        strContentsList.sort((str1, str2) -> Double.compare(str2.length(), str1.length()));
        // done sawa ログを綺麗に... by yuto (2017/04/22)
        // done sawa カラーボックスの中に文字列が一つしかないと落ちるのでどうにかしよう by yuto (2017/04/22)
        if (strContentsList.size() == 1) {
            log("カラーボックスの中に文字列は一つしかありません");
        } else {
            log("二番目に長い文字列は" + strContentsList.get(1) + "です");
        }
        // done sawa 以下を参考に、"バブルソート"のソースコードを思い出化してよう by yuto (2017/04/25)
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // バブルソートで書いたものがsortメソッドで簡潔になったため
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // for (int i = 0; i < strContentsList.size() - 1; i++) {
        //    for (int j = 0; j < strContentsList.size() - 1; j++) {
        //        if (strContentsList.get(j).length() < strContentsList.get(j + 1).length()) {
        //            String keepLonger = strContentsList.get(j);
        //            strContentsList.set(j, strContentsList.get(j + 1));
        //            strContentsList.set(j + 1, keepLonger);
        //        }
        //    }
        //}
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
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
        // one sawa インターフェースで受ける習慣を by yuto (2017/04/22)
        // done sawa 何で"Index"？ by yuto (2017/04/22)
        // done sawa ちなみに、順番を意識しないなら"Set"を使う方が良い by yuto (2017/04/22) by sawa
        Set<Integer> kamakuraColor = new CopyOnWriteArraySet<>();
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
                        kamakuraColor.add(i);
                    }
                }
            }
        }
        for (int index: kamakuraColor) {
            ColorBox colorBox = colorBoxList.get(index);
            BoxColor boxColor = colorBox.getColor();
            log("「かまくら」で始まる文字列をしまっているカラーボックスの色は" + boxColor.getColorName() + "です");
        }
    }

    /**
     * 「いぬ」で終わる文字列をしまっているカラーボックスの色は？
     */
    public void test_endsWith_findLastWord() {
        // done sawa さて、「いぬ」で終わる文字列をしまっているカラーボックスが2つ以上あったらこれで大丈夫でしょうか？ by yuto (2017/04/22)
        // done sawa インターフェースで受ける習慣を by yuto (2017/04/22)
        // done yuto [コメント] sawa 上のレビューは230行目の左辺をArrayListではなくListで宣言するべき、ということで合っていますか？ by sawa (2017/04/23)
        // done sawa [コメント] いぇす！！ by yuto (2017/04/25)
        // done sawa 何で"Index"？ by yuto (2017/04/22)
        // done sawa ちなみに、順番を意識しないなら"Set"を使う方が良い by yuto (2017/04/22)
        List<ColorBox> colorBoxList = getColorBoxList();
        Set<Integer> inuColorBox = new CopyOnWriteArraySet<>();
        for (int i = 0; i < colorBoxList.size(); i++) {
            ColorBox colorBox = colorBoxList.get(i);
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (int j = 0; j < spaceList.size(); j++) {
                BoxSpace boxSpace = spaceList.get(j);
                Object contents = boxSpace.getContents();
                // done sawa strContents は instanceof のif文の中で定義してもいいよね？ by yuto (2017/04/22)
                if (contents instanceof String) {
                    String strContents = (String) contents;
                    if (strContents.endsWith("いぬ")) {
                        inuColorBox.add(i);
                    }
                }
            }
        }
        for (int index: inuColorBox) {
            ColorBox colorBox = colorBoxList.get(index);
            BoxColor boxColor = colorBox.getColor();
            log("「いぬ」で終わる文字列をしまっているカラーボックスの色は" + boxColor.getColorName() + "です");
        }
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    // done sawa そろそろ"i"って書くのもめんどくさくなってきた頃だと思う、のでここからは拡張for文を使ってみよう by yuto (2017/04/22)
    // 拡張for文素敵ですね、もう"i"とはさよならしました by sawa
    // sawa [コメント] さよらなまでは言わないで、必要な時は使えばいいから 笑 by yuto (2017/04/22)
    // sawa [コメント] 間違いないですね笑 by sawa (2017/04/23)
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
                        // done sawa 1を足す理由をコメントに書いておこう by yuto (2017/04/22)
                        // done sawa 1だけずれる理由を... by yuto (2017/04/25)
                        // done sawa コメントは該当箇所の上に by yuto (2017/04/27)
                        //indexOfで取れる添え字にプラス1して前から何文字目か調べる
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
                        // done sawa 1を足す理由をコメントに書いておこう by yuto (2017/04/22)
                        // done sawa 1だけずれる理由を... by yuto (2017/04/25)
                        // done sawa コメントは該当箇所の上に by yuto (2017/04/27)
                        //添え字にプラス1して前から何文字目か調べる
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
                        // done sawa 細かすぎるけど「犬」ってかいちゃった...笑 by yuto (2017/04/22)
                        // yuto done [コメント] あ、、こういう意識大切ですね by sawa (2017/04/23)
                        log("「いぬ」で終わる文字列の最初の一文字は" + firstChar + "です");
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
                        // done sawa ログを綺麗に... by yuto (2017/04/22)
                        log("「かまくら」で始まる文字列の最後の一文字は" + strContents.charAt(strContents.length() - 1) + "です");
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
                    // done sawa 残念ながら done がついていない... by yuto (2017/04/27)
                    // done sawa 2012/06/04 との比較がどこにもないよ by yuto (2017/04/25)
                    LocalDateTime time = (LocalDateTime) contents;
                    // done sawa toString() した結果が yyyy-MM-dd 形式 じゃないこともあるので、LocalDate型で比較しよう by yuto (2017/04/27)
                    // done sawa "06"じゃなくて6 by yuto (2017/04/27)
                    if (time.toLocalDate().equals(LocalDate.of(2012, 6,4))) {
                        log("2012/06/04を示す日付が持っている秒は" + time.getSecond() + "です");
                    }
                }
            }
        }
    }

    /**
     * カラーボックスの中に入っている Map を "map:{ key = value ; key = value ; ... }" という形式で表示。
     */
    public void test_showMap() throws Exception {
        List<ColorBox> colorBoxList = getColorBoxList();
        for (ColorBox colorBox: colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object contents = boxSpace.getContents();
                if (contents instanceof Map<?, ?>) {
                    // done sawa 無駄な初期化をしている by yuto (2017/04/27)
                    // done sawa 警告が出ているよ、警告が出たままpushしないようにしよう by yuto (2017/04/27)
                    StringBuilder mapStr = new StringBuilder("map:{ ");
                    for (Map.Entry<? , ?> e: ((Map<?, ?>) contents).entrySet()) {
                        mapStr.append(e.getKey()).append(" = ").append(e.getValue());
                        mapStr.append(" ; ");
                    }
                    //最後の;を削除したsubstringを作成して出力
                    String str = mapStr.substring(0, mapStr.length() - 2);
                    log(str + "}");
                }
            }
        }
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
        String str = "map:{ key1 = value1 ; key2 = value2 ; key3 = value3 }";
        String s[] = str.split(" ");
        String contents[] = Arrays.copyOfRange(s, 1, 12);
        Map<String, String> map = new HashMap<>();
        int count = 0;
        while(true) {
            if (count > contents.length) {
                break;
            }
            String key = contents[count];
            String  value = contents[count + 2];
            // done sawa 警告が出ているよ by yuto (2017/04/27)
            map.put(key, value);
            //次のkeyが入っているインデックス番号は+4
            count += 4;
        }
        log(map.toString());
    }

    /**
     * "map:{ key1 = value1 ; key2 = map:{ nkey21 = nvalue21 ; nkey22 = nvalue22 } ; key3 = value3 }" <br />
     * という文字列をMapに変換してtoString()すると？ <br />
     * <br />
     * "map:{ key1 = value1 ; key2 = value2 ; key3 = map:{ nkey31 = nvalue31 ; nkey32 = nvalue32 } }" <br />
     * でも、同じプログラムでMapに変換できるようにするべし。
     */
    public void test_parseMap_deep() throws Exception {
        String str = "map:{ key1 = value1 ; key2 = map:{ nkey21 = nvalue21 ; nkey22 = nvalue22 } ; key3 = value3 }";
        String mapEle = str.substring(5, str.length() - 2); //map:{...}の削除
        String contents[] = mapEle.split(";");
        List<String[]> list = new ArrayList<>();
        // done sawa これは拡張for文でできる by yuto (2017/04/27)
        for (String e:contents) {
            list.add(e.split(" "));
        }
        Map<String, String> map = new HashMap<>();
        int count = 0;
        while (true) {
            if (count > list.size() - 1) {
                break;
            } else if(list.get(count).length == 4) {
                //valueの中にmapがない場合
                String key = list.get(count)[1];
                String value = list.get(count)[3];
                // Tdone sawa 警告が出ているよ by yuto (2017/04/27)
                map.put(key, value);
                count++;
            } else {
                //valueの中にmapがある場合
                String key = list.get(count)[1];
                StringBuilder strOfValue = new StringBuilder();
                for (String s: list.get(count + 1)) {
                    strOfValue.append(s);
                }
                String value = list.get(1)[3] + strOfValue;
                // done sawa 警告が出ているよ by yuto (2017/04/27)
                map.put(key, value);
                count += 2;
            }
        }
        log(map);
    }
}
