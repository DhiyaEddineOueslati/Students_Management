package enig.dhiya_oueslati.atelier_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.text.MessageFormat;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>  {
    Context context;
    ArrayList<Etudiant> arrayList;
    OnItemClickListener onItemClickListener;

    public Adapter(Context context, ArrayList<Etudiant> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.etudiant_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nom.setText(MessageFormat.format("{0} {1}", arrayList.get(position).getprenom(), arrayList.get(position).getnom()));
        holder.email.setText(arrayList.get(position).getemail());
        holder.groupe.setText(arrayList.get(position).getgroupe());
        holder.itemView.setOnClickListener(view -> onItemClickListener.onClick(arrayList.get(position)));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nom, groupe, email;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.list_item_nom);
            groupe = itemView.findViewById(R.id.list_item_groupe);
            email = itemView.findViewById(R.id.list_item_email);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(Etudiant etudiant);
    }
}