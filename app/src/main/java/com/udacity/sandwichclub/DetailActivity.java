package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    ImageView ingredientsIv;
    TextView tvOrigin;
    TextView tvAlsoKnownAs;
    TextView tvIngredients;
    TextView tvDesc;

    String strDesc = "";
    String strOrigin = "";
    List<String> lstrAlsoKnownAs = new ArrayList<>();
    List<String> lstrIngredients = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        TextView tvOrigin = findViewById(R.id.origin_tv);
        TextView tvAlsoKnownAs = findViewById(R.id.also_known_tv);
        TextView tvIngredients = findViewById(R.id.ingredients_tv);
        TextView tvDesc = findViewById(R.id.description_tv);




        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }




        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
        strDesc = sandwich.getDescription();
        strOrigin = sandwich.getPlaceOfOrigin();
        lstrAlsoKnownAs = sandwich.getAlsoKnownAs();
        lstrIngredients = sandwich.getIngredients();

        populateUI();
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }
    //TODO Fix errors with populateUI()
    private void populateUI() {

        tvDesc.setText(strDesc);
        tvOrigin.setText(strOrigin);
        tvAlsoKnownAs.setText(lstrAlsoKnownAs.toString());
        tvIngredients.setText(lstrIngredients.toString());



    }
}
