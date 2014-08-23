package com.morphzelous.splitntip;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

public class MainActivity extends Activity {
    private TextView tvIns;
    private TextView tvEachOne;
    private TextView tvTotal;
    private EditText etBill;
    private TextView tvEachPay;
    private TextView tvTotalBill;
    private RadioGroup rgTaxNoTax;
    private RadioGroup rgTaxType;
    private RadioButton rbTaxPC;
    private RadioButton rbTaxAmt;
    private RadioButton rbBeforeTax;
    private RadioButton rbAfterTax;
    private RadioButton rbRoundUp;
    private RadioButton rbRoundDown;
    private RadioButton rbRoundNone;
    private EditText etTaxPC;
    private EditText etTaxAmt;
    private EditText etDummy;
    private EditText etSplits;
    private EditText etTipPC;
    private Button calcBtn;
    private Button btnRecalc;
    private Button zeroBtn;
    private Button oneBtn;
    private Button twoBtn;
    private Button threeBtn;
    private Button fourBtn;
    private Button fiveBtn;
    private Button sixBtn;
    private Button sevenBtn;
    private Button eightBtn;
    private Button nineBtn;
    private Button dotBtn;
    private Button slashBtn;
    private Button bkSpcBtn;
    private Button clearBtn;
    private LinearLayout llKeypad;
    private LinearLayout llButtons;
    private LinearLayout llSplits;
    private LinearLayout llTotal;

    private void flashBtn(final Button myBtnToFlash) {
        myBtnToFlash.setBackgroundResource(com.morphzelous.splitntip.R.drawable.btn_blue);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                myBtnToFlash.setBackgroundResource(com.morphzelous.splitntip.R.drawable.btn_black);
            }
        }, 50);

    }

    private void doCalculation() {
        double billAmtBeforeTax = 0.0;
        double taxPC;
        double tax;
        double totalAfterTax;
        double tipGiven;
        double eachPay;
        double billAmtAfterTax;
        DecimalFormat df = new DecimalFormat("#.00");

        int noSplits;
        double tipPC;

        tvEachOne.setText("Each one pays");
        tvTotal.setText("Total Bill");

        if ((!etBill.getText().toString().equals("")) && (!etSplits.getText().toString().equals("")) && (!etTipPC.getText().toString().equals(""))) {

            noSplits = Integer.parseInt(etSplits.getText().toString());
            tipPC = Double.parseDouble(etTipPC.getText().toString());

            if (!rbAfterTax.isChecked()) {

                //tax percent
                if (rbTaxPC.isChecked() && !etTaxPC.getText().toString().equals("")) {
                    billAmtBeforeTax = Double.parseDouble(etBill.getText().toString()); //100

                    //calculate tip
                    tipGiven = billAmtBeforeTax * tipPC / 100; //100 * 10/100 = 10
                    taxPC = Double.parseDouble(etTaxPC.getText().toString());
                    //taxPC =10;
                    tax = billAmtBeforeTax * taxPC / 100; //100 * 10/100 = 10
                    totalAfterTax = billAmtBeforeTax + tax + tipGiven;
                    eachPay = totalAfterTax / noSplits;

                    if (rbRoundNone.isChecked()) {

                        //Format to two decimal places
                        tvEachPay.setText(df.format(eachPay));

                        //set this value in totalAmt textfield
                        tvTotalBill.setText(df.format(totalAfterTax));

                    } else if (rbRoundUp.isChecked()) {
                        //Recalculate eachPay rounding up
                        eachPay = Math.ceil(eachPay);
                        //Format to two decimal places
                        tvEachPay.setText(df.format(eachPay));

                        //calculate total bill again
                        totalAfterTax = eachPay * noSplits;

                        //calculate tip given again
                        tipGiven = totalAfterTax - billAmtBeforeTax - tax;

                        //calculate tip % again
                        tipPC = tipGiven * 100 / billAmtBeforeTax;

                        //set this amount to tipPC
                        etTipPC.setText(df.format(tipPC));

                        //set this value in totalAfterTax Amt textfield
                        tvTotalBill.setText(df.format(totalAfterTax));

                    } else if (rbRoundDown.isChecked()) {

                        eachPay = Math.floor(eachPay);
                        //Format to two decimal places
                        tvEachPay.setText(df.format(eachPay));

                        //calculate total bill again
                        totalAfterTax = eachPay * noSplits;
                        tipGiven = totalAfterTax - billAmtBeforeTax - tax;
                        tipPC = tipGiven * 100 / billAmtBeforeTax;

                        //set this amount to tipPC
                        etTipPC.setText(df.format(tipPC));

                        //set this value in totalAmt textfield
                        tvTotalBill.setText(df.format(totalAfterTax));

                    } else
                        Toast.makeText(getApplicationContext(), "ERROR: Enter Bill Amount", Toast.LENGTH_SHORT).show();


                }//end if (rbTaxPC.isChecked())

                //tax amount
                else if (rbTaxAmt.isChecked() && !etTaxAmt.getText().toString().equals("")) {
                    billAmtBeforeTax = Double.parseDouble(etBill.getText().toString());//100

                    //calculate tip
                    tipGiven = billAmtBeforeTax * tipPC / 100;//100*10/100 = 10
                    tax = Double.parseDouble(etTaxAmt.getText().toString()); //10

                    totalAfterTax = billAmtBeforeTax + tax + tipGiven; //100 + 10 + 10 = 120

                    eachPay = totalAfterTax / noSplits;

                    if (rbRoundNone.isChecked()) {

                        //Format to two decimal places
                        tvEachPay.setText(df.format(eachPay));

                        //set this value in totalAmt textfield
                        tvTotalBill.setText(df.format(totalAfterTax));

                    } else if (rbRoundUp.isChecked()) {
                        //Recalculate eachPay rounding up
                        eachPay = Math.ceil(eachPay);
                        //Format to two decimal places
                        tvEachPay.setText(df.format(eachPay));

                        //calculate total bill again
                        totalAfterTax = eachPay * noSplits;

                        //calculate tip given again
                        tipGiven = totalAfterTax - billAmtBeforeTax - tax;

                        //calculate tip % again
                        tipPC = tipGiven * 100 / billAmtBeforeTax;

                        //set this amount to tipPC
                        etTipPC.setText(df.format(tipPC));

                        //set this value in totalAfterTax Amt textfield
                        tvTotalBill.setText(df.format(totalAfterTax));

                    } else if (rbRoundDown.isChecked()) {

                        eachPay = Math.floor(eachPay);
                        //Format to two decimal places
                        tvEachPay.setText(df.format(eachPay));

                        //calculate total bill again
                        totalAfterTax = eachPay * noSplits;
                        tipGiven = totalAfterTax - billAmtBeforeTax - tax;
                        tipPC = tipGiven * 100 / billAmtBeforeTax;

                        //set this amount to tipPC
                        etTipPC.setText(df.format(tipPC));

                        //set this value in totalAmt textfield
                        tvTotalBill.setText(df.format(totalAfterTax));
                    }

                }//end else if (rbTaxAmt.isChecked())
                else
                    Toast.makeText(getApplicationContext(), "ERROR: Either enter tax percent or tax amount", Toast.LENGTH_SHORT).show();

            }//end (!rbAfterTax.isChecked())


            //After taxes
            if (rbAfterTax.isChecked()) {
                billAmtAfterTax = Double.parseDouble(etBill.getText().toString());
                //calculate tip
                tipGiven = billAmtAfterTax * tipPC / 100;

                totalAfterTax = billAmtAfterTax + tipGiven;

                eachPay = totalAfterTax / noSplits;


                if (rbRoundNone.isChecked()) {
                    //Format to two decimal places
                    tvEachPay.setText(df.format(eachPay));

                    //set this value in totalAmt textfield
                    tvTotalBill.setText(df.format(totalAfterTax));

                } else if (rbRoundUp.isChecked()) {
                    eachPay = Math.ceil(eachPay);
                    //Format to two decimal places
                    tvEachPay.setText(df.format(eachPay));

                    //calculate total bill again
                    totalAfterTax = eachPay * noSplits;
                    tipGiven = totalAfterTax - billAmtAfterTax;
                    tipPC = tipGiven * 100 / billAmtAfterTax;

                    //set this amount to tipPC
                    etTipPC.setText(df.format(tipPC));

                    //set this value in totalAmt textfield
                    tvTotalBill.setText(df.format(totalAfterTax));

                } else if (rbRoundDown.isChecked()) {

                    eachPay = Math.floor(eachPay);
                    //Format to two decimal places
                    tvEachPay.setText(df.format(eachPay));

                    //calculate total bill again
                    totalAfterTax = eachPay * noSplits;
                    tipGiven = totalAfterTax - billAmtAfterTax;
                    tipPC = tipGiven * 100 / billAmtAfterTax;

                    //set this amount to tipPC
                    etTipPC.setText(df.format(tipPC));

                    //set this value in totalAmt textfield
                    tvTotalBill.setText(df.format(totalAfterTax));
                }


            } //end if (!aText[0].equals(""))

        }//end if (rbAfterTax.isChecked())

        else if (etBill.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "ERROR: Enter Amount", Toast.LENGTH_SHORT).show();
        else if (etSplits.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "ERROR: Enter # of Splits", Toast.LENGTH_SHORT).show();
        else if (etTipPC.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "ERROR: Enter Tip %", Toast.LENGTH_SHORT).show();
    }

    //calculate btn pressed
    private View.OnClickListener myClickListener = new View.OnClickListener()//responds to calculate button click
    {
        public void onClick(View v) {

            flashBtn(calcBtn);
            doCalculation();

            llKeypad.setVisibility(View.GONE);
            llButtons.setVisibility(View.GONE);

            llSplits.setVisibility(View.VISIBLE);
            llTotal.setVisibility(View.VISIBLE);
            btnRecalc.setVisibility(View.VISIBLE);

        }
    };//end myclick listener

    private View.OnClickListener recalcClickListener = new View.OnClickListener()//responds to calculate button click
    {
        public void onClick(View v) {

            llKeypad.setVisibility(View.VISIBLE);
            llButtons.setVisibility(View.VISIBLE);

            llSplits.setVisibility(View.GONE);
            llTotal.setVisibility(View.GONE);
            btnRecalc.setVisibility(View.GONE);


        }
    };//end myclick listener

    private View.OnClickListener zeroClickListener = new View.OnClickListener()//responds to calculate button click
    {
        public void onClick(View v) {

            flashBtn(zeroBtn);

            if (etBill.hasFocus())
                etBill.append("0");

            else if (etTaxPC.hasFocus())
                etTaxPC.append("0");

            else if (etSplits.hasFocus())
                etSplits.append("0");

            else if (etTipPC.hasFocus())
                etTipPC.append("0");

            else if (etTaxPC.hasFocus())
                etTaxPC.append("0");

            else if (etTaxAmt.hasFocus())
                etTaxAmt.append("0");

        }
    };

    private View.OnClickListener oneClickListener = new View.OnClickListener()//responds to calculate button click
    {
        public void onClick(View v) {

            flashBtn(oneBtn);

            if (etBill.hasFocus())
                etBill.append("1");

            else if (etSplits.hasFocus())
                etSplits.append("1");

            else if (etTipPC.hasFocus())
                etTipPC.append("1");

            else if (etTaxPC.hasFocus())
                etTaxPC.append("1");

            else if (etTaxAmt.hasFocus())
                etTaxAmt.append("1");

        }
    };

    private View.OnClickListener twoClickListener = new View.OnClickListener()//responds to calculate button click
    {
        public void onClick(View v) {

            flashBtn(twoBtn);

            if (etBill.hasFocus())
                etBill.append("2");

            else if (etSplits.hasFocus())
                etSplits.append("2");

            else if (etTipPC.hasFocus())
                etTipPC.append("2");

            else if (etTaxPC.hasFocus())
                etTaxPC.append("2");

            else if (etTaxAmt.hasFocus())
                etTaxAmt.append("2");

        }
    };

    private View.OnClickListener threeClickListener = new View.OnClickListener()//responds to calculate button click
    {
        public void onClick(View v) {

            flashBtn(threeBtn);

            if (etBill.hasFocus())
                etBill.append("3");

            else if (etSplits.hasFocus())
                etSplits.append("3");

            else if (etTipPC.hasFocus())
                etTipPC.append("3");

            else if (etTaxPC.hasFocus())
                etTaxPC.append("3");

            else if (etTaxAmt.hasFocus())
                etTaxAmt.append("3");

        }
    };

    private View.OnClickListener fourClickListener = new View.OnClickListener()//responds to calculate button click
    {
        public void onClick(View v) {

            flashBtn(fourBtn);

            if (etBill.hasFocus())
                etBill.append("4");

            else if (etSplits.hasFocus())
                etSplits.append("4");

            else if (etTipPC.hasFocus())
                etTipPC.append("4");

            else if (etTaxPC.hasFocus())
                etTaxPC.append("4");

            else if (etTaxAmt.hasFocus())
                etTaxAmt.append("4");

        }
    };

    private View.OnClickListener fiveClickListener = new View.OnClickListener()//responds to calculate button click
    {
        public void onClick(View v) {

            flashBtn(fiveBtn);

            if (etBill.hasFocus())
                etBill.append("5");

            else if (etSplits.hasFocus())
                etSplits.append("5");

            else if (etTipPC.hasFocus())
                etTipPC.append("5");

            else if (etTaxPC.hasFocus())
                etTaxPC.append("5");

            else if (etTaxAmt.hasFocus())
                etTaxAmt.append("5");

        }
    };

    private View.OnClickListener sixClickListener = new View.OnClickListener()//responds to calculate button click
    {
        public void onClick(View v) {

            flashBtn(sixBtn);

            if (etBill.hasFocus())
                etBill.append("6");

            else if (etSplits.hasFocus())
                etSplits.append("6");

            else if (etTipPC.hasFocus())
                etTipPC.append("6");

            else if (etTaxPC.hasFocus())
                etTaxPC.append("6");

            else if (etTaxAmt.hasFocus())
                etTaxAmt.append("6");

        }
    };

    private View.OnClickListener sevenClickListener = new View.OnClickListener()//responds to calculate button click
    {
        public void onClick(View v) {

            flashBtn(sevenBtn);

            if (etBill.hasFocus())
                etBill.append("7");

            else if (etSplits.hasFocus())
                etSplits.append("7");

            else if (etTipPC.hasFocus())
                etTipPC.append("7");

            else if (etTaxPC.hasFocus())
                etTaxPC.append("7");

            else if (etTaxAmt.hasFocus())
                etTaxAmt.append("7");

        }
    };

    private View.OnClickListener eightClickListener = new View.OnClickListener()//responds to calculate button click
    {
        public void onClick(View v) {

            flashBtn(eightBtn);

            if (etBill.hasFocus())
                etBill.append("8");

            else if (etSplits.hasFocus())
                etSplits.append("8");

            else if (etTipPC.hasFocus())
                etTipPC.append("8");

            else if (etTaxPC.hasFocus())
                etTaxPC.append("8");

            else if (etTaxAmt.hasFocus())
                etTaxAmt.append("8");

        }
    };

    private View.OnClickListener nineClickListener = new View.OnClickListener()//responds to calculate button click
    {
        public void onClick(View v) {

            flashBtn(nineBtn);

            if (etBill.hasFocus())
                etBill.append("9");

            else if (etSplits.hasFocus())
                etSplits.append("9");

            else if (etTipPC.hasFocus())
                etTipPC.append("9");

            else if (etTaxPC.hasFocus())
                etTaxPC.append("9");

            else if (etTaxAmt.hasFocus())
                etTaxAmt.append("9");

        }
    };

    private View.OnClickListener dotClickListener = new View.OnClickListener()//responds to calculate button click
    {
        public void onClick(View v) {

            flashBtn(dotBtn);

            if (etBill.hasFocus())
                etBill.append(".");

            else if (etSplits.hasFocus())
                Toast.makeText(getApplicationContext(), "No. of splits should be an Integer", Toast.LENGTH_SHORT).show();

            else if (etTipPC.hasFocus())
                etTipPC.append(".");

            else if (etTaxPC.hasFocus())
                etTaxPC.append(".");

            else if (etTaxAmt.hasFocus())
                etTaxAmt.append(".");

        }
    };

    private View.OnClickListener slashClickListener = new View.OnClickListener()//responds to calculate button click
    {
        public void onClick(View v) {

            flashBtn(slashBtn);

            if (etBill.hasFocus()) {
                etSplits.requestFocus();
                etSplits.setCursorVisible(true);
            } else if (etSplits.hasFocus()) {
                etTipPC.requestFocus();
                etTipPC.setCursorVisible(true);
            }


        }
    };

    private View.OnClickListener bkspcClickListener = new View.OnClickListener()//responds to calculate button click
    {
        public void onClick(View v) {

            flashBtn(bkSpcBtn);

            if (etBill.hasFocus()) {
                // Get edit text characters
                String billtextInBox = etBill.getText().toString();
                if (billtextInBox.length() > 0) {
                    //Remove last character//
                    String newText = billtextInBox.substring(0, billtextInBox.length() - 1);
                    // Update edit text
                    etBill.setText(newText);

                    //After insert, place cursor at right of last text
                    int pos = etBill.getText().length();
                    etBill.setSelection(pos);
                }
            } else if (etTaxPC.hasFocus()) {
                // Get edit text characters
                String taxpctextInBox = etTaxPC.getText().toString();
                if (taxpctextInBox.length() > 0) {
                    //Remove last character//
                    String newText = taxpctextInBox.substring(0, taxpctextInBox.length() - 1);
                    // Update edit text
                    etTaxPC.setText(newText);

                    //After insert, place cursor at right of last text
                    int pos = etTaxPC.getText().length();
                    etTaxPC.setSelection(pos);

                }
            } else if (etTaxAmt.hasFocus()) {
                // Get edit text characters
                String taxamttextInBox = etTaxAmt.getText().toString();
                if (taxamttextInBox.length() > 0) {
                    //Remove last character//
                    String newText = taxamttextInBox.substring(0, taxamttextInBox.length() - 1);
                    // Update edit text
                    etTaxAmt.setText(newText);

                    //After insert, place cursor at right of last text
                    int pos = etTaxAmt.getText().length();
                    etTaxAmt.setSelection(pos);
                }

            } else if (etSplits.hasFocus()) {
                // Get edit text characters
                String taxpctextInBox = etSplits.getText().toString();
                if (taxpctextInBox.length() > 0) {
                    //Remove last character//
                    String newText = taxpctextInBox.substring(0, taxpctextInBox.length() - 1);
                    // Update edit text
                    etSplits.setText(newText);

                    //After insert, place cursor at right of last text
                    int pos = etSplits.getText().length();
                    etSplits.setSelection(pos);

                }
            } else if (etTipPC.hasFocus()) {
                // Get edit text characters
                String taxpctextInBox = etTipPC.getText().toString();
                if (taxpctextInBox.length() > 0) {
                    //Remove last character//
                    String newText = taxpctextInBox.substring(0, taxpctextInBox.length() - 1);
                    // Update edit text
                    etTipPC.setText(newText);

                    //After insert, place cursor at right of last text
                    int pos = etTipPC.getText().length();
                    etTipPC.setSelection(pos);

                }
            }

        }//end onclick

    };

    private View.OnClickListener clearClickListener = new View.OnClickListener()//responds to calculate button click
    {
        public void onClick(View v) {

            flashBtn(clearBtn);

            etBill.setText("");
            etSplits.setText("");
            etTipPC.setText("");
            tvEachPay.setText("");
            tvTotalBill.setText("");
            etTaxAmt.setText("");
            etTaxPC.setText("");
            tvEachOne.setText("");
            tvTotal.setText("");

        }
    };


    public void onRadioButtonClicked(View view) {

        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case com.morphzelous.splitntip.R.id.radioButtonAfterTax:
                if (checked)
                    rbTaxPC.setEnabled(false);
                    rbTaxAmt.setEnabled(false);

                    etTaxAmt.setEnabled(false);
                    etTaxPC.setEnabled(false);

                    etTipPC.setEnabled(false);
                    etSplits.setEnabled(false);

                    etBill.setEnabled(true);
                    etBill.requestFocus();
                    etBill.setCursorVisible(true);
                    etSplits.setEnabled(true);
                    etTipPC.setEnabled(true);
                    tvIns.setText("Amount With Tax   ");
                break;

            case com.morphzelous.splitntip.R.id.radioButtonBeforeTax:
                if (checked)

                    rbTaxPC.setEnabled(true);
                    rbTaxPC.setChecked(false);

                    rbTaxAmt.setChecked(false);
                    rbTaxAmt.setEnabled(true);

                    Toast.makeText(getApplicationContext(), "Click either Tax Percent or Tax Amount", Toast.LENGTH_SHORT).show();

                    etTaxAmt.setEnabled(false);
                    etTaxPC.setEnabled(false);
                    etDummy.requestFocus();
                    etBill.setEnabled(false);
                    etTipPC.setEnabled(false);
                    etSplits.setEnabled(false);
                break;

            case com.morphzelous.splitntip.R.id.radioButtonTaxAmt:
                if (checked)

                    rbTaxPC.setEnabled(false);

                    etTaxAmt.setEnabled(true);
                    etBill.setEnabled(true);
                    etTipPC.setEnabled(true);
                    etSplits.setEnabled(true);
                    etTaxPC.setEnabled(false);
                    etTaxAmt.requestFocus();
                    tvIns.setText("Amount Without Tax");
                    Toast.makeText(getApplicationContext(), "To select Tax Percent, click Before tax", Toast.LENGTH_SHORT).show();
                break;

            case com.morphzelous.splitntip.R.id.radioButtonTaxPC:
                if (checked)

                    rbTaxAmt.setEnabled(false);
                    etTaxPC.setEnabled(true);
                    etBill.setEnabled(true);
                    etTipPC.setEnabled(true);
                    etSplits.setEnabled(true);
                    etTaxAmt.setEnabled(false);
                    etTaxPC.requestFocus();
                    tvIns.setText("Amount Without Tax");
                    Toast.makeText(getApplicationContext(), "To select Tax Amount, click Before tax", Toast.LENGTH_SHORT).show();
                break;

            case com.morphzelous.splitntip.R.id.radioButtonNone:
                if (checked)
                    Toast.makeText(getApplicationContext(), "PRESSED NONE", Toast.LENGTH_LONG).show();
                break;

            case com.morphzelous.splitntip.R.id.radioButtonUp:
                if (checked)
                    break;

            case com.morphzelous.splitntip.R.id.radioButtonDown:
                if (checked)
                    break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.morphzelous.splitntip.R.layout.activity_main);

        etDummy = (EditText) findViewById(com.morphzelous.splitntip.R.id.editTextDummy);
        etDummy.requestFocus();
        etDummy.setEnabled(false);

        rgTaxNoTax = (RadioGroup) findViewById(com.morphzelous.splitntip.R.id.radioGroup2);
        rgTaxType = (RadioGroup) findViewById(com.morphzelous.splitntip.R.id.radioGroup);

        rbTaxAmt = (RadioButton) findViewById(com.morphzelous.splitntip.R.id.radioButtonTaxAmt);
        rbTaxPC = (RadioButton) findViewById(com.morphzelous.splitntip.R.id.radioButtonTaxPC);
        rbBeforeTax = (RadioButton) findViewById(com.morphzelous.splitntip.R.id.radioButtonBeforeTax);
        rbAfterTax = (RadioButton) findViewById(com.morphzelous.splitntip.R.id.radioButtonAfterTax);
        rbRoundDown = (RadioButton) findViewById(com.morphzelous.splitntip.R.id.radioButtonDown);
        rbRoundNone = (RadioButton) findViewById(com.morphzelous.splitntip.R.id.radioButtonNone);
        rbRoundUp = (RadioButton) findViewById(com.morphzelous.splitntip.R.id.radioButtonUp);


        tvIns = (TextView) findViewById(com.morphzelous.splitntip.R.id.textViewInstruct);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "erbos_draco.ttf");

        tvEachOne = (TextView) findViewById(com.morphzelous.splitntip.R.id.textViewEachOne);
        tvTotal = (TextView) findViewById(com.morphzelous.splitntip.R.id.textViewTotal);

        etTaxAmt = (EditText) findViewById(com.morphzelous.splitntip.R.id.editTextTaxAmt);
        etTaxAmt.setTypeface(customFont);

        //disable keypad
        etTaxAmt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int inType = etTaxAmt.getInputType(); // backup the input type
                etTaxAmt.setInputType(InputType.TYPE_NULL); // disable soft input
                etTaxAmt.onTouchEvent(event); // call native handler
                etTaxAmt.setInputType(inType); // restore input type
                return true; // consume touch even
            }
        });

        etTaxPC = (EditText) findViewById(com.morphzelous.splitntip.R.id.editTextTaxPC);
        etTaxPC.setTypeface(customFont);

        //disable keypad
        etTaxPC.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int inType = etTaxPC.getInputType(); // backup the input type
                etTaxPC.setInputType(InputType.TYPE_NULL); // disable soft input
                etTaxPC.onTouchEvent(event); // call native handler
                etTaxPC.setInputType(inType); // restore input type
                return true; // consume touch even
            }
        });


        etTaxAmt.setEnabled(false);
        //etTaxPC.requestFocus();
        etTaxPC.setEnabled(false);


        etBill = (EditText) findViewById(com.morphzelous.splitntip.R.id.editTextBill);
        etBill.setTypeface(customFont);
        etBill.setEnabled(false);

        //disable keypad
        etBill.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int inType = etBill.getInputType(); // backup the input type
                etBill.setInputType(InputType.TYPE_NULL); // disable soft input
                etBill.onTouchEvent(event); // call native handler
                etBill.setInputType(inType); // restore input type
                return true; // consume touch even
            }
        });

        etSplits = (EditText) findViewById(com.morphzelous.splitntip.R.id.editTextSplits);
        etSplits.setTypeface(customFont);
        etSplits.setEnabled(false);

        //disable keypad
        etSplits.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int inType = etSplits.getInputType(); // backup the input type
                etSplits.setInputType(InputType.TYPE_NULL); // disable soft input
                etSplits.onTouchEvent(event); // call native handler
                etSplits.setInputType(inType); // restore input type
                return true; // consume touch even
            }
        });

        etTipPC = (EditText) findViewById(com.morphzelous.splitntip.R.id.editTextTipPc);
        etTipPC.setTypeface(customFont);
        etTipPC.setEnabled(false);

        //disable keypad
        etTipPC.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int inType = etTipPC.getInputType(); // backup the input type
                etTipPC.setInputType(InputType.TYPE_NULL); // disable soft input
                etTipPC.onTouchEvent(event); // call native handler
                etTipPC.setInputType(inType); // restore input type
                return true; // consume touch even
            }
        });


        tvEachPay = (TextView) findViewById(com.morphzelous.splitntip.R.id.textViewEachPay);
        tvEachPay.setTypeface(customFont);

        tvTotalBill = (TextView) findViewById(com.morphzelous.splitntip.R.id.textViewTotalBill);
        tvTotalBill.setTypeface(customFont);

        etBill.setText("");
        etSplits.setText("");
        etTipPC.setText("");
        tvEachPay.setText("");
        tvTotalBill.setText("");
        etTaxAmt.setText("");
        etTaxPC.setText("");
        tvEachOne.setText("");
        tvTotal.setText("");

        llKeypad = (LinearLayout) findViewById(com.morphzelous.splitntip.R.id.llKeypad);
        llButtons = (LinearLayout) findViewById(com.morphzelous.splitntip.R.id.llButtons);

        llSplits = (LinearLayout) findViewById(com.morphzelous.splitntip.R.id.llSplits);
        llTotal = (LinearLayout) findViewById(com.morphzelous.splitntip.R.id.llTotal);

        calcBtn = (Button) findViewById(com.morphzelous.splitntip.R.id.buttonCalc);
        calcBtn.setOnClickListener(myClickListener);

        btnRecalc = (Button) findViewById(com.morphzelous.splitntip.R.id.btnRecalc);

        btnRecalc.setOnClickListener(recalcClickListener);

        zeroBtn = (Button) findViewById(com.morphzelous.splitntip.R.id.button0);
        zeroBtn.setOnClickListener(zeroClickListener);

        oneBtn = (Button) findViewById(com.morphzelous.splitntip.R.id.button1);
        oneBtn.setOnClickListener(oneClickListener);

        twoBtn = (Button) findViewById(com.morphzelous.splitntip.R.id.button2);
        twoBtn.setOnClickListener(twoClickListener);

        threeBtn = (Button) findViewById(com.morphzelous.splitntip.R.id.button3);
        threeBtn.setOnClickListener(threeClickListener);

        fourBtn = (Button) findViewById(com.morphzelous.splitntip.R.id.button4);
        fourBtn.setOnClickListener(fourClickListener);

        fiveBtn = (Button) findViewById(com.morphzelous.splitntip.R.id.button5);
        fiveBtn.setOnClickListener(fiveClickListener);

        sixBtn = (Button) findViewById(com.morphzelous.splitntip.R.id.button6);
        sixBtn.setOnClickListener(sixClickListener);

        sevenBtn = (Button) findViewById(com.morphzelous.splitntip.R.id.button7);
        sevenBtn.setOnClickListener(sevenClickListener);

        eightBtn = (Button) findViewById(com.morphzelous.splitntip.R.id.button8);
        eightBtn.setOnClickListener(eightClickListener);

        nineBtn = (Button) findViewById(com.morphzelous.splitntip.R.id.button9);
        nineBtn.setOnClickListener(nineClickListener);

        dotBtn = (Button) findViewById(com.morphzelous.splitntip.R.id.buttonDot);
        dotBtn.setOnClickListener(dotClickListener);

        slashBtn = (Button) findViewById(com.morphzelous.splitntip.R.id.buttonStar);
        slashBtn.setOnClickListener(slashClickListener);

        bkSpcBtn = (Button) findViewById(com.morphzelous.splitntip.R.id.buttonBkSpc);
        bkSpcBtn.setOnClickListener(bkspcClickListener);

        clearBtn = (Button) findViewById(com.morphzelous.splitntip.R.id.buttonClear);
        clearBtn.setOnClickListener(clearClickListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.morphzelous.splitntip.R.menu.main, menu);
        return true;
    }

}
