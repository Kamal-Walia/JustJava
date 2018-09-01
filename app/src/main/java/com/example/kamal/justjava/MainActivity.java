package com.example.kamal.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    private int p=120,qty=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        display(qty);
        displayPrice(qty*p);
    }
    private void displayPrice(int number) {
        TextView priceTextView = findViewById(R.id.price);
        CheckBox cb = findViewById(R.id.checkBox);
        String str="Add Whip Cream? " +cb.isChecked()+" \nQuantity: "+qty+"\nTotal:  ₹"+number;
        priceTextView.setText("Add Whip Cream? " +cb.isChecked()+" \nQuantity: "+qty+"\nTotal:  ₹"+number);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order");
        intent.putExtra(Intent.EXTRA_TEXT,str);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.qty);
        quantityTextView.setText("" + number);
    }

    public void increment(View view) {
        if(qty==100)
            Toast.makeText(this,"You Cannot Have More Than 100 Cups Of Coffee In A Single Order..!",Toast.LENGTH_SHORT).show();
        else
        display(++qty);
    }
    public void decrement(View view) {
        if(qty>0)
            display(--qty);
        else
            Toast.makeText(this,"You Cannot Have Negative Cups Of Coffee..!",Toast.LENGTH_SHORT).show();
    }
}