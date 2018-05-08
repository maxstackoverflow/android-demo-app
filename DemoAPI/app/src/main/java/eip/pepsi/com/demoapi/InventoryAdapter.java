package eip.pepsi.com.demoapi;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import eip.pepsi.com.demoapi.model.Inventory;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder>{

    private List<Inventory> items;

    public void setDataset(List<Inventory> items) {
        this.items = items;
        this.notifyDataSetChanged();
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_text, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Inventory inv = items.get(position);
        String display = inv.getBrand_name() + " " + inv.getFlvr_name()
                + " " + inv.getMtrl_type() + ", " + inv.getMtrl_size();
        holder.tv.setText(display);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;
        public ViewHolder(TextView itemView) {
            super(itemView);
            tv = itemView;
        }
    }
}