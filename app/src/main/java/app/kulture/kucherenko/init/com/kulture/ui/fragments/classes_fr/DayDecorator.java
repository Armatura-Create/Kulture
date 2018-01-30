package app.kulture.kucherenko.init.com.kulture.ui.fragments.classes_fr;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.ui.MApplication;

/**
 * Created by st18rai on 01.11.17.
 */

public class DayDecorator implements DayViewDecorator {

    private Drawable highlightDrawable;

    DayDecorator() {
        highlightDrawable = MApplication.getInstance().getMResources().getDrawable(R.drawable.circle_background);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return day.equals(CalendarDay.today());
    }

    @Override
    public void decorate(DayViewFacade view) {

        view.setBackgroundDrawable(highlightDrawable);
        view.addSpan(new StyleSpan(Typeface.BOLD));
        view.addSpan(new ForegroundColorSpan(Color.BLUE));
        // view.addSpan(new RelativeSizeSpan(1.5f));

    }
}