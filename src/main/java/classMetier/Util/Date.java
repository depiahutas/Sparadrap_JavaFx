package classMetier.Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Date {
    private static DateFormat dateFormat;

    /**
     * date au jour de la création
     * @return string date au format jj-mm-aaaa
     */
    public static String newDate() {

        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdt.format(new java.util.Date());


        dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE);

        return strDate;
    }

}
