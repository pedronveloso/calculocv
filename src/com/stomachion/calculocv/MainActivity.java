package com.stomachion.calculocv;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends Activity implements View.OnClickListener
{

    private EditText mEtInput;
    private TextView mTvResult;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btnCopy = (Button) findViewById(R.id.btn_copy_clipboard);
        btnCopy.setOnClickListener(this);
        mEtInput = (EditText) findViewById(R.id.et_value);
        mTvResult = (TextView) findViewById(R.id.tv_result);
        final NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(4);

        mEtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //recalc
                try{
                    Double input = new Double(s.toString());
                    Double result = (input * 380 * 1.732 * 0.8)/ 735.5;
                    mTvResult.setText(numberFormat.format(result).toString());
                } catch (Exception e){
                    // probably was not a number
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_copy_clipboard:
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("calculocv", mTvResult.getText().toString());
                clipboard.setPrimaryClip(clip);
                break;
        }
    }
}
