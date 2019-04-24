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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class AddPokemon extends AppCompatActivity {

    int box;
    int pos;
    Pokemon mon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pokemon);

        pos = this.getIntent().getIntExtra("pos",0)+1;
        box = this.getIntent().getIntExtra("box",0);
        mon = (Pokemon) this.getIntent().getSerializableExtra("pokemon");

        final Spinner species  = (Spinner) findViewById(R.id.speciesSpn);
        final Spinner level  = (Spinner) findViewById(R.id.lvlSpn);
        final Spinner nature  = (Spinner) findViewById(R.id.natureSpn);

        Button save = (Button) findViewById(R.id.save);
        Button delete = (Button) findViewById(R.id.delete);

        final EditText nick = (EditText) findViewById(R.id.nickname);

        final EditText HP = (EditText) findViewById(R.id.hpText);
        final EditText ATK = (EditText) findViewById(R.id.atkText);
        final EditText DEF = (EditText) findViewById(R.id.defText);
        final EditText SAT = (EditText) findViewById(R.id.satText);
        final EditText SDF = (EditText) findViewById(R.id.sdfText);
        final EditText SPD = (EditText) findViewById(R.id.spdText);

        final EditText[] Moves = new EditText[4];
        Moves[0] = (EditText) findViewById(R.id.mv1);
        Moves[1] = (EditText) findViewById(R.id.mv2);
        Moves[2] = (EditText) findViewById(R.id.mv3);
        Moves[3] = (EditText) findViewById(R.id.mv4);

        String[] pokedex = getResources().getStringArray(R.array.pokedex);
        Integer[] lvl = new Integer[100];
        for(int i = 0; i < 100; i++)
            lvl[i] = (i+1);
        String[] nat = { "Hardy",    "Lonely",   "Brave",    "Adamant",  "Naughty",
                "Bold",	    "Docile",	"Relaxed",	"Impish",	"Lax",
                "Timid",    "Hasty",	"Serious",	"Jolly",	"Naive",
                "Modest",	"Mild",     "Quiet",	"Bashful",	"Rash",
                "Calm", 	"Gentle",	"Sassy",	"Careful",	"Quirky"};

        //Setup Species Spinner;
        species.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setSprite(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        ArrayAdapter<String> speciesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pokedex); //selected item will look like a spinner set from XML
        speciesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        species.setAdapter(speciesAdapter);

        //Setup Level Spinner
        ArrayAdapter<Integer> levelAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, lvl); //selected item will look like a spinner set from XML
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        level.setAdapter(levelAdapter);

        //Setup Nature Spinner
        ArrayAdapter<String> natureAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nat); //selected item will look like a spinner set from XML
        natureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nature.setAdapter(natureAdapter);

        if(mon.getLevel() != 0) {
            species.setSelection(((ArrayAdapter)species.getAdapter()).getPosition(mon.getSpecies()));
            nature.setSelection(((ArrayAdapter)nature.getAdapter()).getPosition(mon.getNature()));
            level.setSelection(((ArrayAdapter)level.getAdapter()).getPosition(mon.getLevel()));
            if(mon.getName() != mon.getSpecies())
                nick.setText(mon.getName());
            HP.setText(mon.getStatString("HP"));
            ATK.setText(mon.getStatString("ATK"));
            DEF.setText(mon.getStatString("DEF"));
            SAT.setText(mon.getStatString("SAT"));
            SDF.setText(mon.getStatString("SDF"));
            SPD.setText(mon.getStatString("SPD"));
            String[] mov = mon.getMoves();
            for(int z = 0; z < 4; z++){
                if(!mov[z].equals("[EMPTY]"))
                    Moves[z].setText(mov[z]);
            }
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ensure no stats are empty
                if(HP.getText().toString().equals("") ||
                        ATK.getText().toString().equals("") ||
                        DEF.getText().toString().equals("") ||
                        SAT.getText().toString().equals("") ||
                        SDF.getText().toString().equals("") ||
                        SPD.getText().toString().equals("")) {
                    Toast t = Toast.makeText(getApplicationContext(), "Fill in all Stats", Toast.LENGTH_SHORT);
                    t.show();
                    return;
                }
                //Ensure at least one move is present.
                if(Moves[0].getText().toString().equals("") &&
                        Moves[1].getText().toString().equals("") &&
                        Moves[2].getText().toString().equals("") &&
                        Moves[3].getText().toString().equals("")){
                    Toast t = Toast.makeText(getApplicationContext(), "Fill in at least one move", Toast.LENGTH_SHORT);
                    t.show();
                    return;
                }

                //Prepare Stats
                int[] Stats = new int[6];
                Stats[0] = Integer.parseInt(HP.getText().toString());
                Stats[1] = Integer.parseInt(ATK.getText().toString());
                Stats[2] = Integer.parseInt(DEF.getText().toString());
                Stats[3] = Integer.parseInt(SAT.getText().toString());
                Stats[4] = Integer.parseInt(SDF.getText().toString());
                Stats[5] = Integer.parseInt(SPD.getText().toString());

                //Prepare Moves
                String[] mvs = new String[4];
                for(int x = 0; x < 4; x++){
                    mvs[x] = Moves[x].getText().toString();
                }
                //Get Sprite
                int position = species.getSelectedItemPosition();
                String spriteID;
                if((position+1) < 10)
                    spriteID = "p00" + (position+1);
                else if((position+1) < 100)
                    spriteID = "p0" + (position+1);
                else
                    spriteID = "p" + (position+1);
                int sprite = getResources().getIdentifier(spriteID,"drawable",getApplication().getPackageName());

                //Get others
                String spc = (String) species.getSelectedItem();
                String nat = (String) nature.getSelectedItem();
                Integer lvl = (Integer) level.getSelectedItem();
                String nname = nick.getText().toString();

                Pokemon x = new Pokemon(spc,nname,Stats,lvl,nat,sprite,mvs);
                Intent intent = new Intent();
                if(mon.getLevel() == 0)
                    intent.putExtra("result","SAVE");
                else
                    intent.putExtra("result","UPDATE");
                intent.putExtra("pokemon", x);
                intent.putExtra("box", box);
                intent.putExtra("pos", pos);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("result","DELETE");
                intent.putExtra("box", box);
                intent.putExtra("pos", pos);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public void setSprite(int position){
        ImageView iv = (ImageView) findViewById(R.id.spriteView);
        String spriteID = "";
        if((position+1) < 10)
            spriteID = "p00" + (position+1);
        else if((position+1) < 100)
            spriteID = "p0" + (position+1);
        else
            spriteID = "p" + (position+1);
        iv.setImageDrawable(getDrawable(getResources().getIdentifier(spriteID,"drawable",getApplication().getPackageName())));
    }
}
