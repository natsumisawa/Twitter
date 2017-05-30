package javatry.enjoy.sawa;

import javatry.colorbox.size.BoxSize;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;

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
        getColorBoxList().stream().filter(colorBox -> colorBox.getSpaceList().stream().map(boxSpace -> boxSpace.getContents()) == null)
                .filter(colorBox -> colorBox.getColor().getColorName().length() == 3)
                .filter(colorBox -> colorBox.getSize().getDepth())

    }
    
    /**
     * ボックスのどこかにtxtファイルが存在しています。その中身に今日の日付、名前(+あだ名)、本日学んだ内容を書いてください。<br>
     * そしてそのファイルを自分専用のディレクトリにコピーしてください。<br>
     * 書いたのち、コピー元、コピー先それぞれの中身を表示し、差がないことを確認してください。
     */
    public void test_devil3() {
    }

}
