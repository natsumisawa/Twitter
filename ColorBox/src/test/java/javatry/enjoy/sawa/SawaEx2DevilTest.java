package javatry.enjoy.sawa;

import javatry.colorbox.ColorBox;
import javatry.colorbox.size.BoxSize;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;
import org.omg.CORBA.IMP_LIMIT;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
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
        Optional<Integer> onesPlaceOpt = getColorBoxList().stream()
            .filter(colorbox -> {
                String colorName = colorbox.getColor().getColorName();
                String endChar = colorName.substring(colorName.length() - 1);
                return getColorBoxList().stream()
                    .filter(colorBox -> colorBox.getSpaceList().stream().map(BoxSpace::getContents).anyMatch(Objects::isNull))
                    .map(colorBox -> colorBox.getColor().getColorName().substring(2, 3)).anyMatch(s -> s.equals(endChar));
            })
          //ここまでで「nullを含んでいるカラーボックスの色の名前の3文字目の文字で色の名前が終わっているカラーボックス」が取れた -> blue
            .map(colorBox -> {
                Integer depthNum = Integer.parseInt(new StringBuffer(String.valueOf(colorBox.getSize().getDepth())).reverse().toString().substring(1));
                // i = 4
                return getColorBoxList().stream()
                    .flatMap(box -> box.getSpaceList().stream())
                    .map(BoxSpace::getContents)
                    .filter(o -> o instanceof List<?>)
                    .map(list -> (List<?>) list)
                    .flatMap(Collection::stream)
                    .filter(o -> o instanceof BigDecimal)
                    .map(o -> (BigDecimal) o)
                    .filter(bigDecimal -> bigDecimal.scale() >= 2)
                    .filter(bigDecimal -> Integer.parseInt(bigDecimal.toString().substring(3, 4)) == depthNum)
                    .map(bigDecimal -> Integer.parseInt(bigDecimal.toString().substring(0, 1)))
                    .findFirst()
                    .orElse(null);
            }).findFirst();
        if (onesPlaceOpt.isPresent()) {
            Optional<Object> finalOpt = getColorBoxList().stream().filter(box -> box.getColor().getColorName().length() == onesPlaceOpt.get())
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(BoxSpace::getContents).findFirst();
            finalOpt.ifPresent(this::log);
        }

        //カラーボックスの深さの十の位の数字
        //Integer integer = getColorBoxList().stream().map(colorBox -> Integer.parseInt(new StringBuffer(String.valueOf(colorBox.getSize().getDepth())).reverse().toString().substring(1))).findFirst().get();
        //log(integer);

        //小数第二位より大きいbd
        //List<Integer> collect = getColorBoxList().stream().flatMap(box -> box.getSpaceList().stream())
        //    .map(BoxSpace::getContents)
        //    .filter(o -> o instanceof List<?>)
        //    .map(list -> (List<?>) list)
        //    .flatMap(Collection::stream)
        //    .filter(o -> o instanceof BigDecimal)
        //    .map(o -> (BigDecimal) o)
        //    .filter(bigDecimal -> bigDecimal.scale() >= 2)
        //    .map(bigDecimal -> Integer.parseInt(bigDecimal.toString().substring(2, 3))).collect(Collectors.toList());
        //for (Integer i: collect) {
        //    log(i);
        //}

        //nullを含んでいるカラーボックスの色の名前の3文字目
        //String s = getColorBoxList().stream()
        //    .filter(colorBox -> colorBox.getSpaceList().stream().map(BoxSpace::getContents).anyMatch(Objects::isNull))
        //    .map(colorBox -> colorBox.getColor().getColorName().substring(2, 3)).findFirst().get();
        //log(s);
    }
    
    /**
     * ボックスのどこかにtxtファイルが存在しています。その中身に今日の日付、名前(+あだ名)、本日学んだ内容を書いてください。<br>
     * そしてそのファイルを自分専用のディレクトリにコピーしてください。<br>
     * 書いたのち、コピー元、コピー先それぞれの中身を表示し、差がないことを確認してください。
     */
    public void test_devil3() {
    }

}
