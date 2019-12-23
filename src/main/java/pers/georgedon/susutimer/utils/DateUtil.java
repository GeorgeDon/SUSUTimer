package pers.georgedon.susutimer.utils;

public class DateUtil {

    public static String formatDuration(Long duration) {
        duration=duration/1000;
        Long hour = duration / 3600;
        duration = duration % 3600;
        Long mins = duration / 60;
        Long second = duration % 60;
        return String.format("%02d  :  %02d  :  %02d", hour, mins, second);
    }
}
