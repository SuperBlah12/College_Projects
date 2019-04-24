package edu.asu.bsse.tjcole2.pkmnss;

/**
 * Copyright (c) 2017 Tyler Cole.
 *
 * This file is part of PokemonStorageSystem.
 *
 * PokemonStorageSystem is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PokemonStorageSystem is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author   Tyler Cole    mailto:tjcole2@asu.edu.
 * @version April, 2017
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

import static edu.asu.bsse.tjcole2.pkmnss.R.id.gridview;

public class BoxActivity extends AppCompatActivity {
    TextView boxName;
    GridView gridview;
    databaseHelper db;
    int box = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box);

        boxName = (TextView) findViewById(R.id.boxName);
        gridview = (GridView) findViewById(R.id.gridview);
        Button left = (Button) findViewById(R.id.leftBtn);
        Button right = (Button) findViewById(R.id.rightBtn);

        db = new databaseHelper(getApplicationContext());
        try {
            db.openDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        prepareGrid(box);
        left.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(box > 1)
                    box--;
                else
                    box = 32;
                prepareGrid(box);
            }
        });
        right.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(box < 32)
                    box++;
                else
                    box = 1;
                prepareGrid(box);
            }
        });
        boxName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBoxDialog();
                prepareGrid(box);
            }
        });
    }

    private void prepareGrid(int box) {
        Integer[] boxSprites = new Integer[30];
        boxName.setText(db.getBoxName(box));
        for(int i = 0; i < 30; i++) {
            Pokemon x = db.getPokemon(box, (i+1));
            if (x.getLevel() == 0)
                boxSprites[i] = R.drawable.empty;
            else
                boxSprites[i] = x.getSprite();
        }
        gridview.setAdapter(new ImageAdapter(this,boxSprites));
        gridview.setColumnWidth(6);
        final int fBox = box;
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick (AdapterView < ? > parent, View v,
            int position, long id){
                Intent intent = new Intent(BoxActivity.this,AddPokemon.class);
                intent.putExtra("box", fBox);
                intent.putExtra("pos", position);
                intent.putExtra("pokemon",db.getPokemon(fBox,(position+1)));
                startActivityForResult(intent,1);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                if(data.getStringExtra("result").equals("SAVE")) {
                    Pokemon poke = (Pokemon) data.getSerializableExtra("pokemon");
                    db.addPokemonToBox(poke,
                            data.getIntExtra("box", 0),
                            data.getIntExtra("pos", 0));
                    prepareGrid(box);
                } else if (data.getStringExtra("result").equals("UPDATE")) {
                    Pokemon poke = (Pokemon) data.getSerializableExtra("pokemon");
                    db.updatePokemonData(poke,
                            data.getIntExtra("box", 0),
                            data.getIntExtra("pos", 0));
                    prepareGrid(box);
                } else if (data.getStringExtra("result").equals("DELETE")) {
                    db.deletePokemon(data.getIntExtra("box", 0),
                            data.getIntExtra("pos", 0));
                    prepareGrid(box);
                }
            }
        }
    }

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        // references to our images
        private Integer[] mThumbIds = {
                R.drawable.empty, R.drawable.empty, R.drawable.empty, R.drawable.empty, R.drawable.empty, R.drawable.empty,
                R.drawable.empty, R.drawable.empty, R.drawable.empty, R.drawable.empty, R.drawable.empty, R.drawable.empty,
                R.drawable.empty, R.drawable.empty, R.drawable.empty, R.drawable.empty, R.drawable.empty, R.drawable.empty,
                R.drawable.empty, R.drawable.empty, R.drawable.empty, R.drawable.empty, R.drawable.empty, R.drawable.empty,
                R.drawable.empty, R.drawable.empty, R.drawable.empty, R.drawable.empty, R.drawable.empty, R.drawable.empty,
        };

        public ImageAdapter(Context c, Integer[] grid) {
            mContext = c;
            mThumbIds = grid;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(180,250));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setPadding(0, 0, 0, 0);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }
    }

    public void showBoxDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Set new box name");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        input.setHint("Box Name");
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String str = input.getText().toString();
                if (str.length() > 14)
                    str = str.substring(0, 14);
                db.setBoxName(box, str);
                prepareGrid(box);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
