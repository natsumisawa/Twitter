package javatry.enjoy.sawa;

import javatry.colorbox.ColorBox;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class SawaQ2DateTest extends ColorBoxTestCase {

    // ===================================================================================
    //                                                                             Convert
    //                                                                             =======

    /**
     * カラーボックスに入っている日付をスラッシュ区切りのフォーマットで表示したら？
     */
    public void test_convert() {
        List<ColorBox> colorBoxList = getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object contents = boxSpace.getContents();
                if (contents instanceof LocalDate) {
                    int month = ((LocalDate) contents).getMonthValue();
                    int day = ((LocalDate) contents).getDayOfMonth();
                    log(month + "/" + day);
                } else if (contents instanceof LocalDateTime) {
                    int month = ((LocalDateTime) contents).getMonthValue();
                    int day = ((LocalDateTime) contents).getDayOfMonth();
                    log(month + "/" + day);
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
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object contents = boxSpace.getContents();
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
