package com.angel.erasmushelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context context;
    String [][] datos;
    int[] datosImg;

    public Adapter(Context context, String[][] datos, int[] datosImg) {
        this.context = context;
        this.datos = datos;
        this.datosImg = datosImg;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View view = inflater.inflate(R.layout.user_experience_element,null);

        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        TextView tvSurname = (TextView) view.findViewById(R.id.tvSurname);
        TextView tvProvincia = (TextView) view.findViewById(R.id.tvProvince);
        TextView tvUniversity = (TextView) view.findViewById(R.id.tvHomeUniversity);
        TextView tvPromo = (TextView) view.findViewById(R.id.tvPromocion);
        TextView tvCountry = (TextView) view.findViewById(R.id.tvCountry);
        TextView tvOpinion = (TextView) view.findViewById(R.id.tvOpinion);
        ImageView ivImage = (ImageView) view.findViewById(R.id.ivImage);

        tvName.setText(datos[i][0]);
        tvSurname.setText(datos[i][1]);
        tvProvincia.setText(datos[i][2]);
        tvUniversity.setText(datos[i][3]);
        tvCountry.setText(datos[i][4]);
        tvPromo.setText(datos[i][5]);
        tvOpinion.setText(datos[i][6]);
        ivImage.setImageResource(datosImg[i]);
        ivImage.setTag(i);



        return view;
    }

    @Override
    public int getCount() {
        return datosImg.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}
