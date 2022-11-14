package com.yuta.hexdecimalconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private EditText edit_input, edit_output;
    private RadioGroup radio_inputType, radio_outputType;
    private RadioButton input_bin, input_dec, input_hex,
            output_bin, output_dec, output_hex;

    /**
     * 1 bin
     * 2 dec
     * 3 hex
     */
    private HexDecimalTypes inputType = HexDecimalTypes.DEC;
    private HexDecimalTypes outputType = HexDecimalTypes.BIN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_input = findViewById(R.id.edit_input);
        edit_output = findViewById(R.id.edit_output);

        radio_inputType = findViewById(R.id.radio_inputType);
        radio_outputType = findViewById(R.id.radio_outputType);

        input_bin = findViewById(R.id.input_bin);
        input_dec = findViewById(R.id.input_dec);
        input_hex = findViewById(R.id.input_hex);
        output_bin = findViewById(R.id.output_bin);
        output_dec = findViewById(R.id.output_dec);
        output_hex = findViewById(R.id.output_hex);
        edit_output.setKeyListener(null);
    }

    @Override
    protected void onStart() {
        super.onStart();
        edit_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String inputStr = charSequence.toString().replaceAll("\n", "");
                Log.d("onTextChanged", inputStr);
//                if(inputStr.equals("")) {
//
//                }
                String outputStr = convertHexDecimal(inputStr);

                edit_output.setText(outputStr);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        radio_inputType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.input_bin:
                        inputType = HexDecimalTypes.BIN;
                        break;
                    case R.id.input_dec:
                        inputType = HexDecimalTypes.DEC;
                        break;
                    case R.id.input_hex:
                        inputType = HexDecimalTypes.HEX;
                        break;
                }

                String newResult = convertHexDecimal(edit_input.getText().toString().replaceAll("\n", ""));
                edit_output.setText(newResult);
            }
        });

        radio_outputType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.output_bin:
                        outputType = HexDecimalTypes.BIN;
                        break;
                    case R.id.output_dec:
                        outputType = HexDecimalTypes.DEC;
                        break;
                    case R.id.output_hex:
                        outputType = HexDecimalTypes.HEX;
                        break;
                }
                String newResult = convertHexDecimal(edit_input.getText().toString().replaceAll("\n", ""));
                edit_output.setText(newResult);
            }
        });
    }


    private String convertHexDecimal(String inputStr) {
        String outputStr = "";
        try {
            int decInt;
            switch(inputType) {
                case BIN:
                    decInt = Integer.parseInt(inputStr, 2);
                    break;
                case DEC:
                    decInt = Integer.parseInt(inputStr);
                    break;
                case HEX:
                    decInt = Integer.parseInt(inputStr, 16);
                    break;
                default:
                    decInt = Integer.parseInt(inputStr);
            }

            switch(outputType) {
                case BIN:
                    outputStr = Integer.toBinaryString(decInt);
                    break;
                case DEC:
                    outputStr = Integer.toString(decInt);
                    break;
                case HEX:
                    outputStr = Integer.toHexString(decInt);
                    break;
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return outputStr;
    }
}