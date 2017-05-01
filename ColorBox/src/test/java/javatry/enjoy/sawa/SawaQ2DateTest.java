package javatry.enjoy.sawa;

import javatry.colorbox.ColorBox;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Locale.JAPAN;

/**
 * 日付関連のテスト。<br>
 * 何々は？と言われたら、それに該当するものをログに出力すること。
 * @author ikezaki
 */
// TODO sawa @author by yuki.wakisaka (2017/04/30)
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
                    // TODO done sawa 毎回この形式に合わせるの、きっといい感じのclassがあるよ by yuki.wakisaka (2017/04/30)
                    // TODO [コメント] ＼(^o^)／ by sawa
                    String date = ((LocalDate) contents).format(DateTimeFormatter.ofPattern("MM/dd"));
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
        // TODO sawa for文では日付の抽出だけをして、最後にlogを書き出すときに曜日にしよう。機能の切り分け。 by yuki.wakisaka (2017/04/30)
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object contents = boxSpace.getContents();
                // TODO sawa ここで宣言する意味ないな by yuki.wakisaka (2017/04/30)
                String week = "";
                if (contents instanceof LocalDate) {
                    week = ((LocalDate) contents).getDayOfWeek().getDisplayName(TextStyle.FULL, JAPAN);
                    log("カラーボックスに入っている最初の日付の曜日は" + week + "です");
                    break;
                } else if (contents instanceof LocalDateTime) {
                    week = ((LocalDateTime) contents).getDayOfWeek().getDisplayName(TextStyle.FULL, JAPAN);
                    log("カラーボックスに入っている最初の日付の曜日は" + week + "です");
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
            // TODO sawa "yellow".equals(...)の方が良い
            // 理由を考えてみて、コメントに残してくださいなー by yuki.wakisaka (2017/04/30)
            if (colorBox.getColor().getColorName().equals("yellow")) {
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
        String entryWeek = entryDay.getDayOfWeek().getDisplayName(TextStyle.FULL, JAPAN);
        // TODO sawa 文字列ではなく、DayOfWeekで比較したほうが安全（タイポとか、TextStyleの差異とか） by yuki.wakisaka (2017/04/30)
        if (entryWeek.equals("土曜日") || entryWeek.equals("日曜日")) {
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
                // TODO sawa 両方LocalDateに落とし込んでるのはGOOD
                // だけど、二つの日付がLocalDateとLocalDateTimeのときしか対応してなくない？
                // （両方LocalDateだと動かなそう） by yuki.wakisaka (2017/04/30)
                LocalDate date1 = null;
                LocalDate date2 = null;
                for (BoxSpace boxSpace : spaceList) {
                    Object contents = boxSpace.getContents();
                    if (contents instanceof LocalDate) {
                        date1 = (LocalDate) contents;
                        log(date1);
                    }
                    if (contents instanceof LocalDateTime) {
                        date2 = ((LocalDateTime) contents).toLocalDate();
                        log(date2);
                    }
                }
                if (date1 != null && date2 != null) {
                    long gap = ChronoUnit.DAYS.between(date2, date1);
                    log("二つの日付の日数の差は" + (gap) + "日です");
                }
            }
        }
    }
}
