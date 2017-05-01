package javatry.enjoy.sawa;

import javatry.colorbox.ColorBox;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Locale.JAPAN;

/**
 * 日付関連のテスト。<br>
 * 何々は？と言われたら、それに該当するものをログに出力すること。
 * @author ikezaki
 */
// TODO sawa @author by yuki.wakisaka (2017/04/30)
// TODO sawa おーい by yuki.wakisaka (2017/05/01)
public class SawaQ2DateTest extends ColorBoxTestCase {

    // ===================================================================================
    //                                                                             Convert
    //                                                                             =======

    /**
     * カラーボックスに入っている日付をスラッシュ区切りのフォーマットで表示したら？
     */
    public void test_convert() {
        // TODO sawa colorBoxListはforの引数でしか呼ばれないので、変数に出さずに書いてみよう by yuki.wakisaka (2017/04/30)
        // TODO [コメント] このTODOだけどうしてもわかりませんでした、一旦飛ばします by sawa
        List<ColorBox> colorBoxList = getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object contents = boxSpace.getContents();
                if (contents instanceof LocalDate) {
                    // done sawa 毎回この形式に合わせるの、きっといい感じのclassがあるよ by yuki.wakisaka (2017/04/30)
                    // [コメント] ＼(^o^)／ by sawa
                    // TODO sawa FormatterはLocalDateでもLocalDateTimeでも使いまわすので、外で定義しちゃうのがいいかな。
                    // 今回の出力形式を定義するという意味では、1行目とかでもいいくらい。 by yuki.wakisaka (2017/05/01)
                    String date = ((LocalDate) contents).format(DateTimeFormatter.ofPattern("MM/dd"));
                    // TODO sawa log(...) はfor文の外に出しておこう。処理の切り分け。 by yuki.wakisaka (2017/05/01)
                    log("カラーボックスに入っている日付は" + date + "です");
                } else if (contents instanceof LocalDateTime) {
                    String date  = ((LocalDateTime) contents).format(DateTimeFormatter.ofPattern("MM/dd"));
                    log("カラーボックスに入っている日付は" + date + "です");
                }
            }
        }
    }

    // ===================================================================================
    //                                                                              Basic
    //                                                                             =======

    /**
     * カラーボックスに入っている最初の日付は何曜日？
     */
    public void test_weekOfDay() {
        List<ColorBox> colorBoxList = getColorBoxList();
        // done sawa for文では日付の抽出だけをして、最後にlogを書き出すときに曜日にしよう。機能の切り分け。 by yuki.wakisaka (2017/04/30)
        // TODO ↑ log(...)はfor文の外に出しちゃおう、の意も含む！ by yuki.wakisaka (2017/05/01)
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object contents = boxSpace.getContents();
                // done sawa ここで宣言する意味ないな by yuki.wakisaka (2017/04/30)
                if (contents instanceof LocalDate) {
                    LocalDate date = ((LocalDate) contents);
                    log("カラーボックスに入っている最初の日付の曜日は" + date.getDayOfWeek().getDisplayName(TextStyle.FULL, JAPAN) + "です");
                    break;
                } else if (contents instanceof LocalDateTime) {
                    LocalDateTime date = ((LocalDateTime) contents);
                    log("カラーボックスに入っている最初の日付の曜日は" + date.getDayOfWeek().getDisplayName(TextStyle.FULL, JAPAN) + "です");
                    break;
                }
            }
        }
    }

    /**
     * 色がyellowのカラーボックスに入っている二つの日付にそれぞれ3日足すと、それぞれ何曜日になる？
     */
    public void test_addThreeDays() {
        List<ColorBox> colorBoxList = getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            // TODO done sawa "yellow".equals(...)の方が良い
            // 理由を考えてみて、コメントに残してくださいなー by yuki.wakisaka (2017/04/30)
            // TODO [コメント] "yello"が先にくれば、colorBox.getColor().getColorName()で色の名前を取り出していることがすぐにわかるから(?) by sawa (2017/05/01)
            // TODO sawa ↑ この問題に限らず、文字列比較する際は定数を先に持ってきたほうがいい、という意味合いですね。 "hoge".equals(someStr) みたいな
            // もっと、実害がでうるかも..というところの理由があります！ by yuki.wakisaka (2017/05/01)
            if (("yellow").equals(colorBox.getColor().getColorName())) {
                List<BoxSpace> spaceList = colorBox.getSpaceList();
                for (BoxSpace boxSpace : spaceList) {
                    Object contents = boxSpace.getContents();
                    if (contents instanceof LocalDate) {
                        LocalDate newDate = ((LocalDate) contents).plus(3, DAYS);
                        String week = newDate.getDayOfWeek().getDisplayName(TextStyle.FULL, JAPAN);
                        log("一つ目の日付に3日足すと曜日は" + week + "です");
                    } else if (contents instanceof LocalDateTime) {
                        LocalDateTime newDate = ((LocalDateTime) contents).plus(3, DAYS);
                        String week = newDate.getDayOfWeek().getDisplayName(TextStyle.FULL, JAPAN);
                        log("二つ目の日付に3日足すと曜日は" + week + "です");
                    }
                }
            }
        }
    }

    /**
     * 来年(2018年)の新卒が入社する日は何曜日？
     */
    public void test_weekOfDayOf2017Newcomer() {
        LocalDate entryDay = LocalDate.of(2018, 4, 1);
        // TODO sawa entryWeekでは「入社する週」なので entryDayOfWeekのがいいすね by yuki.wakisaka (2017/05/01)
        // TODO これをStringではなくDayOfWeekで持てば、比較するときも楽 by yuki.wakisaka (2017/05/01)
        String entryWeek = entryDay.getDayOfWeek().getDisplayName(TextStyle.FULL, JAPAN);
        // done sawa 文字列ではなく、DayOfWeekで比較したほうが安全（タイポとか、TextStyleの差異とか） by yuki.wakisaka (2017/04/30)
        if (entryDay.getDayOfWeek().equals(DayOfWeek.SATURDAY) || entryDay.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            // TODO sawa ここでentryWeekに月曜を再代入してlogに出すほうが、出力形式にブレが減る by yuki.wakisaka (2017/05/01)
            log("月曜日です");
        } else {
            log(entryWeek + "です");
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========

    /**
     * 色がyellowのカラーボックスに入っている二つの日付の日数の差は？
     */
    public void test_diffDay() {
        List<ColorBox> colorBoxList = getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            if (colorBox.getColor().getColorName().equals("yellow")) {
                List<BoxSpace> spaceList = colorBox.getSpaceList();
                // done sawa 両方LocalDateに落とし込んでるのはGOOD
                // だけど、二つの日付がLocalDateとLocalDateTimeのときしか対応してなくない？
                // （両方LocalDateだと動かなそう） by yuki.wakisaka (2017/04/30)
                List<LocalDate> date = new ArrayList<>();
                for (BoxSpace boxSpace : spaceList) {
                    Object contents = boxSpace.getContents();
                    if (contents instanceof LocalDate) {
                        date.add((LocalDate) contents);
                    }
                    if (contents instanceof LocalDateTime) {
                        date.add(((LocalDateTime) contents).toLocalDate());
                    }
                }
                // TODO sawa 1つの場合のlogも出そう。& 3つ以上の場合は？ by yuki.wakisaka (2017/05/01)
                if (date.size() >= 2) {
                    long gap = ChronoUnit.DAYS.between(date.get(1), date.get(0));
                    log("二つの日付の日数の差は" + (gap) + "日です");
                }
            }
        }
    }
}
