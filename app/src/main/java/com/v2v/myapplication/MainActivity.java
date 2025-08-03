package com.v2v.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // UI references
    private EditText   etName, etQuantity;
    private RadioGroup radioGroupSize;
    private RadioButton rbSmall, rbMedium, rbLarge;
    private CheckBox    cbCheese, cbOlives, cbBellPeppers, cbMushrooms;
    private Button      btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);   // ← your main layout file

        etName           = findViewById(R.id.etName);
        etQuantity       = findViewById(R.id.etQuantity);

        radioGroupSize   = findViewById(R.id.radioGroupSize);
        rbSmall          = findViewById(R.id.rbSmall);
        rbMedium         = findViewById(R.id.rbMedium);
        rbLarge          = findViewById(R.id.rbLarge);

        cbCheese         = findViewById(R.id.cbCheese);
        cbOlives         = findViewById(R.id.cbOlives);
        cbBellPeppers    = findViewById(R.id.cbBellPeppers);
        cbMushrooms      = findViewById(R.id.cbMushrooms);

        btnOrder         = findViewById(R.id.btnOrder);

        btnOrder.setOnClickListener(v -> showOrderSummary());
    }


    private void showOrderSummary() {

        String name     = etName.getText().toString().trim();
        String quantity = etQuantity.getText().toString().trim();

        int selectedSizeId = radioGroupSize.getCheckedRadioButtonId();
        RadioButton selectedSizeBtn = findViewById(selectedSizeId);
        String size = (selectedSizeBtn != null)
                ? selectedSizeBtn.getText().toString()
                : "Not selected";

        StringBuilder top = new StringBuilder();
        if (cbCheese.isChecked())      top.append("Extra Cheese, ");
        if (cbOlives.isChecked())      top.append("Olives, ");
        if (cbBellPeppers.isChecked()) top.append("Bell Peppers, ");
        if (cbMushrooms.isChecked())   top.append("Mushrooms, ");

        String toppings = (top.length() > 0)
                ? top.substring(0, top.length() - 2)   // remove last ", "
                : "None";

        View dialogView = getLayoutInflater()
                .inflate(R.layout.dialog_order_summary, null, false);

        ((TextView) dialogView.findViewById(R.id.tvName))     .setText("Name: " + name);
        ((TextView) dialogView.findViewById(R.id.tvSize))     .setText("Size: " + size);
        ((TextView) dialogView.findViewById(R.id.tvToppings)) .setText("Toppings: " + toppings);
        ((TextView) dialogView.findViewById(R.id.tvQuantity)) .setText("Quantity: " + quantity);

        new AlertDialog.Builder(this)
                .setView(dialogView)
                .setPositiveButton("OK", null)
                .show();
    }
}
