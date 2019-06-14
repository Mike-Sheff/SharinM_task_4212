package ru.netologia.sharinm_task_4212;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.GregorianCalendar;

public class CalendarActivity extends AppCompatActivity {

    private Button mChooseStartDate;
    private Button mChooseEndDate;
    private CalendarView mStartDateCalendar;
    private CalendarView mEndDateCalendar;
    private Button mBtnOK;
    private Button mBtnClear;

    private long mStartDate;
    private String mStartDateTxt;
    private long mEndDate;
    private String mEndDateTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initViews();

        mStartDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                mStartDateTxt = getString(R.string.formatDate,i, i1, i2);
                mChooseStartDate.setText(getString(R.string.textBtnChooseStartDate, mStartDateTxt));
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(i, i1, i2);
                mStartDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);

                mEndDateCalendar.setVisibility(View.VISIBLE);
                mStartDateCalendar.setVisibility(View.GONE);
            }
        });

        mEndDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                mEndDateTxt = getString(R.string.formatDate,i, i1, i2);
                mChooseEndDate.setText(getString(R.string.textBtnChooseEndDate, mEndDateTxt));
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(i, i1, i2);
                mEndDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);

                mBtnOK.setVisibility(View.VISIBLE);
                mBtnClear.setVisibility(View.VISIBLE);
            }
        });

        mBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mStartDate > mEndDate) {
                    Toast.makeText(CalendarActivity.this, getString(R.string.textError), Toast.LENGTH_LONG).show();
                    mChooseStartDate.setText(getString(R.string.textBtnChooseStartDate,""));
                    mChooseEndDate.setText(getString(R.string.textBtnChooseEndDate,""));

                    mStartDateCalendar.setVisibility(View.VISIBLE);
                    mEndDateCalendar.setVisibility(View.GONE);
                    mBtnOK.setVisibility(View.INVISIBLE);
                    mBtnClear.setVisibility(View.INVISIBLE);
                } else {
                    Toast.makeText(CalendarActivity.this, getString(R.string.textMessageChoseDate, mStartDateTxt, mEndDateTxt), Toast.LENGTH_LONG).show();
                }
            }
        });

        mBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChooseStartDate.setText(getString(R.string.textBtnChooseStartDate, ""));
                mChooseEndDate.setText(getString(R.string.textBtnChooseEndDate,""));

                mStartDateCalendar.setVisibility(View.VISIBLE);
                mEndDateCalendar.setVisibility(View.GONE);

                mBtnOK.setVisibility(View.INVISIBLE);
                mBtnClear.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void initViews() {
        mChooseStartDate = findViewById(R.id.chooseStartDate);
        mChooseEndDate = findViewById(R.id.chooseEndDate);
        mStartDateCalendar = findViewById(R.id.startDateCalendar);
        mEndDateCalendar = findViewById(R.id.endDateCalendar);
        mBtnOK = findViewById(R.id.btnOK);
        mBtnClear = findViewById(R.id.btnClear);

        // Скроем календари при запуске приложения
        mBtnOK.setVisibility(View.INVISIBLE);
        mBtnClear.setVisibility(View.INVISIBLE);
        mStartDateCalendar.setVisibility(View.VISIBLE);
        mEndDateCalendar.setVisibility(View.GONE);
    }
}
