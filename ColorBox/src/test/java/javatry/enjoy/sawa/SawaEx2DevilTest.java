package javatry.enjoy.sawa;

import javatry.colorbox.ColorBox;
import javatry.colorbox.size.BoxSize;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * デビルテスト。<br>
 * 何かやれって言われたらやること。
 * @author ikezaki
 */
public class SawaEx2DevilTest extends ColorBoxTestCase {

    /**
     * (このテストメソッドの中だけで) 赤いカラーボックスの高さを 160 に変更せよ
     */
    public void test_devil1() {
        getColorBoxList().stream().filter(colorBox -> colorBox.getColor().getColorName().equals("red"))
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(BoxSpace::getSize)
                .map(BoxSize::getHeight)
                .map(height -> 160)
                .forEach(newHeight -> log("高さを" + newHeight + "に変更しました"));
    }

    /**
     * nullを含んでいるカラーボックスの色の名前の3文字目の文字で色の名前が終わっているカラーボックスの深さの十の位の数字が小数点第二桁目になっているスペースの中のリストの中のBigDecimalの一の位の数字と同じ色の長さのカラーボックスの一番下のスペースに入っているものは？
     */
    public void test_devil2() {
        //こちら本番コード
        Stream<Integer> oneClass = getColorBoxList().stream()
            .flatMap(colorBox -> colorBox.getSpaceList().stream()
                .map(BoxSpace::getContents).filter(o -> o instanceof List<?>)
                .map(list -> (List<?>) list)
                .flatMap(Collection::stream)
                .filter(o -> o instanceof BigDecimal)
                .map(o -> (BigDecimal) o)
                .map(bigDecimal -> {
                    int scale = bigDecimal.scale();
                    if (scale > 2) {
                        return Integer.parseInt(bigDecimal.toString().substring(2, 3));
                    } else {
                        return -1;
                    }
                }).filter(integer -> integer != -1));

        log(oneClass.findFirst().get());


        Optional<Integer> tenClass = getColorBoxList().stream().filter(colorBox -> colorBox.getSpaceList().stream().map(BoxSpace::getContents) == null).filter(colorBox -> colorBox.getColor().getColorName().length() == 3)
            .map(colorBox -> Integer.parseInt(new StringBuffer(String.valueOf(colorBox.getSize().getDepth())).reverse().toString().substring(1))).findAny();

        if (tenClass.isPresent()) {
            log(tenClass.get());
            Optional<Integer> match = oneClass.filter(integer -> integer.equals(tenClass.get())).findFirst();
            if (match.isPresent()) {
                log(match.get());
            }
        }

//        getColorBoxList().stream().filter(colorBox -> colorBox.getSpaceList().stream().map(BoxSpace::getContents) == null)
//                .filter(colorBox -> colorBox.getColor().getColorName().length() == 3)
//                .filter(colorBox -> colorBox.getSize().getDepth() / 10 ==
//                        colorBox.getSpaceList().stream()
//                                .map(BoxSpace::getContents).filter(o -> o instanceof List<?>)
//                                .map(list -> (List<?>) list)
//                                .flatMap(Collection::stream)
//                                .filter(o -> o instanceof BigDecimal)
//                                .map(o -> (BigDecimal) o)
//                                .filter(bigDecimal -> bigDecimal.scale() == 2)
//                                .map(bigDecimal -> {
//                                    String bdStr = String.valueOf(bigDecimal.intValue());
//                                    return Integer.parseInt((bdStr.substring(bdStr.length() - 4)));
//                                }).findFirst().orElse(0)).flatMap(colorBox -> colorBox.getSpaceList().stream()).forEach(boxSpace -> log(boxSpace.getContents().toString()));
//                .flatMap(colorBox -> colorBox.getSpaceList().stream())
//                .map(BoxSpace::getContents).findFirst().map(o -> "あったよ" + o).orElse("なかったよ");


//        深さの十の位をとってきているよ  --->  3だった

//        nullを含んでいるカラーボックスの色の名前の3文字目の文字で色の名前が終わっているカラーボックスの深さの十の位の数字、まで実装したつもりだった
        ColorBox colorBox1 = getColorBoxList().stream()
            .filter(colorbox -> colorbox.getColor().getColorName().substring(2).equals(
                getColorBoxList().stream()
                    .filter(colorBox -> colorBox.getSpaceList().stream().map(BoxSpace::getContents) == null)
                    .map(colorBox -> colorBox.getColor().getColorName().substring(2)).findFirst().get())).findFirst().get();
        log(colorBox1.getSize().getWidth());

//            .map(colorBox -> Integer.parseInt(new StringBuffer(String.valueOf(colorBox.getSize().getDepth())).reverse().toString().substring(1))).findFirst().get();
//        log(integer);

        //小数点第二桁目があるbdの最初の一つ目をとってるよ   ---> 1がでる
//        String log = getColorBoxList().stream()
//                    .flatMap(colorBox -> colorBox.getSpaceList().stream()
//                    .map(BoxSpace::getContents).filter(o -> o instanceof List<?>)
//                    .map(list -> (List<?>) list)
//                    .flatMap(Collection::stream)
//                    .filter(o -> o instanceof BigDecimal)
//                    .map(o -> (BigDecimal) o)
//                    .map(bigDecimal -> {
//                        int scale = bigDecimal.scale();
//                        if (scale > 2) {
//                            return Integer.parseInt(bigDecimal.toString().substring(2, 3));
//                        } else {
//                            return 0;
//                        }
//                    }).filter(integer -> integer != 0)).findAny().map(integer -> "あった" + integer).orElse("ないよ");
////                .findFirst().map(bigDecimal -> "あったよ" + bigDecimal).orElse("なかったよ");
//        log(log);

        //保持しておくよ
//        String log = getColorBoxList().stream().filter(colorBox -> colorBox.getSpaceList().stream().map(BoxSpace::getContents) == null)
//                .filter(colorBox -> colorBox.getColor().getColorName().length() == 3)
//                .filter(colorBox -> Integer.parseInt(new StringBuffer(String.valueOf(colorBox.getSize().getDepth())).reverse().toString().substring(1)) ==
//                        colorBox.getSpaceList().stream()
//                                .map(BoxSpace::getContents).filter(o -> o instanceof List<?>)
//                                .map(list -> (List<?>) list)
//                                .flatMap(Collection::stream)
//                                .filter(o -> o instanceof BigDecimal)
//                                .map(o -> (BigDecimal) o)
//                                .filter(bigDecimal -> bigDecimal.scale() == 2)
//                                .map(bigDecimal -> {
//                                    String bdStr = String.valueOf(bigDecimal.intValue());
//                                    return Integer.parseInt((bdStr.substring(bdStr.length() - 4)));
//                                }).findFirst().orElse(0))
//                .flatMap(colorBox -> colorBox.getSpaceList().stream())
//                .map(BoxSpace::getContents).findFirst().map(o -> "あったよ" + o).orElse("なかったよ");
//        log(log);
    }
    
    /**
     * ボックスのどこかにtxtファイルが存在しています。その中身に今日の日付、名前(+あだ名)、本日学んだ内容を書いてください。<br>
     * そしてそのファイルを自分専用のディレクトリにコピーしてください。<br>
     * 書いたのち、コピー元、コピー先それぞれの中身を表示し、差がないことを確認してください。
     */
    public void test_devil3() {
    }

}
