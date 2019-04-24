package edu.asu.bsse.tjcole2.pkmnss;

import android.util.Log;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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

public class Pokemon implements Serializable {
    public String species = "MissingNo";

    public String name = species;

    public int level;

    public String nature;

    public int sprite = R.drawable.p000;

    public static Map<String, Integer> stats = new HashMap<String,Integer>();

    public String[] moves = {"Tackle","[EMPTY]","[EMPTY]","[EMPTY"};

    Pokemon(String species, String name, int[] Stats, int Level, String Nature, int sprite, String[] moves) {
        this.species = species;

        if(name != "")
            this.name = name;
        else
            this.name = species;

        this.level = Level;
        this.sprite = sprite;
        this.nature = Nature;

        if(Stats.length != 6)
            return;
        this.stats.put("HP",Stats[0]);
        this.stats.put("ATK",Stats[1]);
        this.stats.put("DEF",Stats[2]);
        this.stats.put("SAT",Stats[3]);
        this.stats.put("SDF",Stats[4]);
        this.stats.put("SPD",Stats[5]);

        if(moves.length != 4)
            return;
        for(int i = 0; i < 4; i++){
            if(moves[i] == null || moves[i].equals(""))
                this.moves[i] = "[EMPTY]";
            else
                this.moves[i] = moves[i];
        }
    }

    Pokemon(String pokemon){
        String[] data = pokemon.split("\\|");
        species = data[0];
        name = data[1];
        level = Integer.parseInt(data[2]);
        sprite = Integer.parseInt(data[3]);
        stats.put("HP",Integer.parseInt(data[4]));
        stats.put("ATK",Integer.parseInt(data[5]));
        stats.put("DEF",Integer.parseInt(data[6]));
        stats.put("SAT",Integer.parseInt(data[7]));
        stats.put("SDF",Integer.parseInt(data[8]));
        stats.put("SPD",Integer.parseInt(data[9]));
        moves[0] = data[10];
        moves[1] = data[11];
        moves[2] = data[12];
        moves[3] = data[13];
    }

    Pokemon(){
        level = 0;
        nature = "NULL";
        this.stats.put("HP",0);
        this.stats.put("ATK",0);
        this.stats.put("DEF",0);
        this.stats.put("SAT",0);
        this.stats.put("SDF",0);
        this.stats.put("SPD",0);
    }

    public String toString(){
        String pokemon = "";
        pokemon = species + "|" + name + "|" + level + "|" + sprite +
                "|" + stats.get("HP") + "|" + stats.get("ATK") + "|" +
                stats.get("DEF") + "|" + stats.get("SAT") + "|" +
                stats.get("SDF") + "|" + stats.get("SPD") + "|" +
                moves[0] + "|" + moves[1] + "|" + moves[2] + "|" + moves[3];
        return pokemon;
    }

    public int getSprite(){
        return sprite;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public String getNature() {
        return nature;
    }

    public String getSpecies() {
        return species;
    }

    public String[] getMoves() {
        return moves;
    }

    public String getStatString(String stat){
        return ""+stats.get(stat);
    }

    /* Discarded Ideas
    //BASE
    public static Map<String,Integer> Base = new HashMap<String,Integer>();

    //IVs
    public static Map<String,Integer> IVs = new HashMap<String,Integer>();

    //EVs
    public static Map<String,Integer> EVs = new HashMap<String,Integer>();

    Pokemon(int[] stats, int[] Base, int Level, String Nature, int sprite, int[] EVs, int[] IVs) {
        this.stats.put("HP",stats[0]);
        this.stats.put("ATK",stats[1]);
        this.stats.put("DEF",stats[2]);
        this.stats.put("SAT",stats[3]);
        this.stats.put("SDF",stats[4]);
        this.stats.put("SPD",stats[5]);

        this.Base.put("HP",Base[0]);
        this.Base.put("ATK",Base[1]);
        this.Base.put("DEF",Base[2]);
        this.Base.put("SAT",Base[3]);
        this.Base.put("SDF",Base[4]);
        this.Base.put("SPD",Base[5]);

        this.level = Level;
        this.sprite = sprite;
        this.nature = Nature;

        this.IVs.put("HP",IVs[0]);
        this.IVs.put("ATK",IVs[1]);
        this.IVs.put("DEF",IVs[2]);
        this.IVs.put("SAT",IVs[3]);
        this.IVs.put("SDF",IVs[4]);
        this.IVs.put("SPD",IVs[5]);

        this.EVs.put("HP",EVs[0]);
        this.EVs.put("ATK",EVs[1]);
        this.EVs.put("DEF",EVs[2]);
        this.EVs.put("SAT",EVs[3]);
        this.EVs.put("SDF",EVs[4]);
        this.EVs.put("SPD",EVs[5]);
    }

    Pokemon(int[] stats, int[] Base, int Level, String Nature, int sprite, int[] Vs, boolean IVs) {
        this.stats.put("HP",stats[0]);
        this.stats.put("ATK",stats[1]);
        this.stats.put("DEF",stats[2]);
        this.stats.put("SAT",stats[3]);
        this.stats.put("SDF",stats[4]);
        this.stats.put("SPD",stats[5]);

        this.Base.put("HP",Base[0]);
        this.Base.put("ATK",Base[1]);
        this.Base.put("DEF",Base[2]);
        this.Base.put("SAT",Base[3]);
        this.Base.put("SDF",Base[4]);
        this.Base.put("SPD",Base[5]);

        this.level = Level;
        this.sprite = sprite;
        this.nature = Nature;

        if(IVs) {
            this.IVs.put("HP", Vs[0]);
            this.IVs.put("ATK", Vs[1]);
            this.IVs.put("DEF", Vs[2]);
            this.IVs.put("SAT", Vs[3]);
            this.IVs.put("SDF", Vs[4]);
            this.IVs.put("SPD", Vs[5]);
        } else {
            this.EVs.put("HP", Vs[0]);
            this.EVs.put("ATK", Vs[1]);
            this.EVs.put("DEF", Vs[2]);
            this.EVs.put("SAT", Vs[3]);
            this.EVs.put("SDF", Vs[4]);
            this.EVs.put("SPD", Vs[5]);
        }
    }*/

}
