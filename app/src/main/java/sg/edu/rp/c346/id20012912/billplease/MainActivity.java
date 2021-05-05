package sg.edu.rp.c346.id20012912.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity
{

    EditText amt;
    EditText pax;

    ToggleButton SVC;
    ToggleButton GST;

    TextView BillTotal;
    TextView EachPays;

    Button split;
    Button reset;

    EditText discount;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amt = findViewById(R.id.Amtvalue);
        pax= findViewById(R.id.paxvalue);

        BillTotal= findViewById(R.id.BillAmt);
        EachPays= findViewById(R.id.Eachamt);

        SVC= findViewById(R.id.NoSys);
        GST= findViewById(R.id.Gst);

        split = findViewById(R.id.split);
        reset = findViewById(R.id.Reset);

        discount = findViewById(R.id.discountval);


        split.setOnClickListener(new OnClickListener()
        {
            String amtinput = amt.getText().toString();
            String numofpax = pax.getText().toString();

            @Override
            public void onClick(View v) {
                if (amtinput.length() > 0 && numofpax.length() > 0) {
                    double newAmt = 0.0;
                    String AmtStr = amt.getText().toString();

                    if (SVC.isChecked() == false && GST.isChecked() == false)
                    {
                        newAmt = Double.parseDouble(AmtStr);
                    }
                    else if (SVC.isChecked() == false && GST.isChecked() == true)
                    {
                        newAmt = Double.parseDouble(AmtStr) * 1.1;
                    }

                    else if (SVC.isChecked() == false && GST.isChecked() == false)
                    {
                        newAmt = Double.parseDouble(AmtStr) * 1.07;
                    }

                    else
                        {
                        newAmt = Double.parseDouble(AmtStr) * 1.17;
                    }

                    //Discount
                    String discountStr = discount.getText().toString();
                    if (discountStr.length() > 0)
                    {
                        newAmt = 1 - Double.parseDouble(discountStr) / 100;
                    }

                    String Amtformat = String.format("%.2f", newAmt);
                    BillTotal.setText("Total Bill:$ " + Amtformat);

                    String PersonStr = pax.getText().toString();
                    int Person = Integer.parseInt(PersonStr);
                    String perAmt = String.format("%.2f", newAmt / Person);

                    if (Person > 1) {
                        EachPays.setText("Each Pays:$ " + perAmt);
                    } else {
                        EachPays.setText("Each Pays:$ " + newAmt);
                    }


                }

                reset.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        amt.setText("");
                        pax.setText("");
                        SVC.setChecked(false);
                        GST.setChecked(false);
                        discount.setText(" ");
                    }
                });

    }
}