package edu.illinois.cs411.godutch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<PurchaseData> my_data;

    public CustomAdapter(Context context, List<PurchaseData> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tax.setText(Double.toString(my_data.get(position).getTax()));
        holder.date.setText(my_data.get(position).getDate());
        holder.store.setText(my_data.get(position).getStoreAddress());



//        Glide.with(context).load(my_data.get(position).getImage_link()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView tax;
        public TextView store;
        public TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            tax = (TextView) itemView.findViewById(R.id.mainTax);
            store = (TextView) itemView.findViewById(R.id.mainStore);
            date = (TextView) itemView.findViewById(R.id.mainDate);
        }
    }
}
