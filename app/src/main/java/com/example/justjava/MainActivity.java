

/* IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/
package com.example.justjava;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.text.NumberFormat;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*TextView text=new TextView(this);
        text.setText("HELLO");
        text.setTextSize(48);         //METHODS ON TEXTVIEW
        text.setTextColor(Color.YELLOW);
        */

        setContentView(R.layout.activity_main);
    }



    /**
     * This method is called when the order button is clicked.
     */
  public void submitOrder(View view) {
        CheckBox cream=(CheckBox) findViewById(R.id.checkBox);
        boolean hascream= cream.isChecked();
        CheckBox choc=(CheckBox)findViewById(R.id.checkBox1);
        boolean choc1= choc.isChecked();
        int price = calprice(hascream,choc1);
        EditText name=(EditText) findViewById(R.id.name);
        String name1= name.getText().toString();
        String pm = summary(price,hascream,choc1,name1);

      Intent intent = new Intent(Intent.ACTION_SENDTO);
      intent.setData(Uri.parse("mailto:")); // only email apps should handle this

      intent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary of "+name1);
      intent.putExtra(Intent.EXTRA_TEXT,pm);
      if (intent.resolveActivity(getPackageManager()) != null) {
          startActivity(intent);
      }
        //displayMessage(pm);


      }




    public void inc(View view) {
        if(num+1>100)
            return;
        else
            num = num + 1;
        display(num);

    }

    public void dec(View view) {
        if (num - 1 < 0)
            return;
        else
            num = num - 1;
        display(num);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.n);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView summaryTextView = (TextView) findViewById(R.id.orderSummary);
//        summaryTextView.setText(message);
//    }

    public int calprice(boolean addcream, boolean choc) {
        int tot=5;
        if(addcream==true)
            tot=tot+1;
        if(choc==true)
            tot=tot+2;
        return ( tot * num);

    }

    private String summary(int price,boolean addcream,boolean choc,String name) {


        String price1 = "Name: "+name + "\n Whipped cream added?" +addcream + "\n Chocolates added?" +choc + "\n Quantity:" + num + "\n Total: ₹" + price + "\n Thank you";

        //displayMessage(price1);
        return (price1);
    }
}