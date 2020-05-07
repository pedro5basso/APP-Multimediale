package com.angel.erasmushelper;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class ExperienceActivity extends Activity {

    ListView lista;

    String [][] datos = {
            {"María", "Estacio","Cádiz","Univeridad de Málaga (UMA)","España","2019/2020","¡Muy Recomendable!"},
            {"Marina", "Corts","Granada","Universidad de Granada (UGR)","España","2019/2020","killo tá to xulo"},
            {"Silvia", "López","Valencia","Universidad Politécnica de Valencia (UPV)","España","2019/2020","Tot molt recomanable, Nano"},
            {"Ander", "Azkue","San Sebstián","Universidad del País Vasco  (UPV/EHU)","España","2019/2020","Egin dezakezun onena da"},
            {"Roberto", "Márquez","Cádiz","Uniersidad de Cádiz (UCA)","España","2019/2020","Haré que mis hijos vengan aquí a hacer su erasmus"},
            {"Javier", "Gómez","Madrid","Universidad Autónoma de Madrid (UAM)","España","2019/2020","Repitiría sin dudarlo"},
            {"Alejandro", "Cruzado","Cartagena","Universidad de Valencia (UV)","España","2018/2019","Acho una ciudad muy bonita"},
            {"Steven", "Palma","San José","Universidad San Marcos","Costa Rica","2019/2020","Todo genial, Carepichas!"},
            {"Eivydas", "Jasiukevicius","Vilna","Vilnaus Universitetas","Lituania","2019/2020","Amazing!"}

    };

    int[] datosimg ={R.drawable.me,R.drawable.mc,R.drawable.sl,
                        R.drawable.aa, R.drawable.rm, R.drawable.jg,
                        R.drawable.ah,R.drawable.sp, R.drawable.e};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.experience_layout);

        lista = (ListView) findViewById(R.id.lvListUsers);

        lista.setAdapter(new Adapter(this,datos, datosimg));

    }
}
