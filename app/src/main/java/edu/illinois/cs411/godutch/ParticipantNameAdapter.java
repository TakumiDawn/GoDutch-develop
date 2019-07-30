package edu.illinois.cs411.godutch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ParticipantNameAdapter extends RecyclerView.Adapter<ParticipantNameAdapter.ViewHolder> {

    private Context context;
    private List<ParticipantNameData> my_data;

    public ParticipantNameAdapter(Context context, List<ParticipantNameData> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.participant_name_card_layout,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.name.setText(my_data.get(position).getName());



//        Glide.with(context).load(my_data.get(position).getImage_link()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public CheckBox name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (CheckBox) itemView.findViewById(R.id.participantNameCheckBox);
        }
    }
}
