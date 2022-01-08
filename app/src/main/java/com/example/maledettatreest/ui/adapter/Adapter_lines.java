package com.example.maledettatreest.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.maledettatreest.R;
import com.example.maledettatreest.models.ApplicationModel;
import com.example.maledettatreest.models.Line;
import com.example.maledettatreest.ui.bacheca.Bacheca;
import com.example.maledettatreest.ui.bacheca.BachecaPosts;
import com.example.maledettatreest.ui.bacheca.MapActivity;

import org.json.JSONException;

public class Adapter_lines extends RecyclerView.Adapter<ViewHolder> {
    private LayoutInflater mInflater;
    private Context context;

    public Adapter_lines(Bacheca fragment, Context context) {
        this.mInflater = LayoutInflater.from(fragment.getContext());
        this.context = context;
    }

    // viene richiamato quando si crea un nuovo oggetto di view (che rappresenta una cella)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.single_row_tratte, parent, false);
        return new ViewHolder(view);
    }

    // viene richiamato quando un oggetto di view (che rappresenta una cella) viene associato ai suoi dati (nota che un
    //parametro del metodo è la posizione dell’oggetto nella lista)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Line s = ApplicationModel.getInstance().getTratta(position);
        holder.setText(s);

        holder.map.setOnClickListener(v -> {
            Intent intent = new Intent(context, MapActivity.class);

            intent.putExtra("key", s.terminus1.did);
            context.startActivity(intent);
        });

        holder.direzione_1.setOnClickListener(v -> {
            Intent intent = new Intent(context, BachecaPosts.class);

            intent.putExtra("key", s.terminus1.did);
            intent.putExtra("partenza", s.terminus1.sname);
            intent.putExtra("arrivo", s.terminus2.sname);
            intent.putExtra("did_inverso", s.terminus2.did);
            context.startActivity(intent);
        });

        holder.direzione_2.setOnClickListener(v -> {
            Intent intent = new Intent(context, BachecaPosts.class);

            intent.putExtra("key", s.terminus2.did);
            intent.putExtra("partenza", s.terminus2.sname);
            intent.putExtra("arrivo", s.terminus1.sname);
            intent.putExtra("did_inverso", s.terminus1.did);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return ApplicationModel.getInstance().getSize();
    }
}
