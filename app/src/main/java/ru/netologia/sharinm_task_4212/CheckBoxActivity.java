package ru.netologia.sharinm_task_4212;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CheckBoxActivity extends AppCompatActivity {

    private EditText mInputMoney;
    private EditText mInputInfo;
    private Button mBtnOk;
    private CheckBox mBankCardChkBx;
    private CheckBox mMobilePhoneChkBx;
    private CheckBox mCashAddressChkBx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox);
        initViews();
    }

    private void initViews() {
        mInputMoney = findViewById(R.id.inputMoney);
        mInputInfo = findViewById(R.id.inputInfo);
        mBtnOk = findViewById(R.id.btnOK);
        mBankCardChkBx = findViewById(R.id.bankCardChkBx);
        mMobilePhoneChkBx = findViewById(R.id.mobilePhoneChkBx);
        mCashAddressChkBx = findViewById(R.id.cashAddressChkBx);
        mBankCardChkBx.setOnCheckedChangeListener(checkedChangeListener);
        mMobilePhoneChkBx.setOnCheckedChangeListener(checkedChangeListener);
        mCashAddressChkBx.setOnCheckedChangeListener(checkedChangeListener);
        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float summaFloat = 0;
                String textString = "";

                try {
                    summaFloat = Float.parseFloat(mInputMoney.getText().toString());
                } catch (Exception exp) {
                    Toast.makeText(CheckBoxActivity.this, getString(R.string.textMessageErrorNotSumma), Toast.LENGTH_LONG).show();
                }

                if ((mBankCardChkBx.isChecked() == false) && (mMobilePhoneChkBx.isChecked() == false) && (mCashAddressChkBx.isChecked() == false)) {
                    Toast.makeText(CheckBoxActivity.this, getString(R.string.textMessageErrorNotChecked), Toast.LENGTH_LONG).show();
                } else {
                    if (mBankCardChkBx.isChecked()) {
                        textString = getString(R.string.textMessageBankCard, summaFloat);
                    }

                    if (mMobilePhoneChkBx.isChecked()) {
                        textString = getString(R.string.textMessageMobilePhone, summaFloat);
                    }

                    if (mCashAddressChkBx.isChecked()) {
                        textString = getString(R.string.textMessageCashAddress, summaFloat);
                    }
                    Toast.makeText(CheckBoxActivity.this,  textString, Toast.LENGTH_LONG).show();
                }
            }
        });

        Button btnExit = findViewById(R.id.buttonExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void resetCheckBoxes(){
        mBankCardChkBx.setChecked(false);
        mMobilePhoneChkBx.setChecked(false);
        mCashAddressChkBx.setChecked(false);
    }

    CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) {
                switch (compoundButton.getId()) {
                    case R.id.bankCardChkBx:
                        resetCheckBoxes();
                        mBankCardChkBx.setChecked(true);
                        mInputInfo.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                    case R.id.mobilePhoneChkBx:
                        resetCheckBoxes();
                        mMobilePhoneChkBx.setChecked(true);
                        mInputInfo.setInputType(InputType.TYPE_CLASS_PHONE);
                        break;
                    case R.id.cashAddressChkBx:
                        resetCheckBoxes();
                        mInputInfo.setInputType(InputType.TYPE_CLASS_TEXT);
                        mCashAddressChkBx.setChecked(true);
                        break;
                    default:
                }
            }
        }
    };
}
