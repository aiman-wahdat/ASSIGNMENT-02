package com.example.assign_02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> implements Filterable {

    private ArrayList<restaurant> resList;
    private ArrayList<restaurant> filteredList;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView TvResName, TvLocation, TvPhone, TvDescription, TvRatings;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TvResName = itemView.findViewById(R.id.TvResName);
            TvLocation = itemView.findViewById(R.id.TvLocation);
            TvPhone = itemView.findViewById(R.id.TvPhone);
            TvDescription = itemView.findViewById(R.id.TvDescription);
            TvRatings = itemView.findViewById(R.id.TvRatings);
        }
    }

    public RestaurantAdapter(Context context, ArrayList<restaurant> list) {
        this.context = context;
        this.resList = list;
        this.filteredList = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_single_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        restaurant restaurant = filteredList.get(position);
        holder.TvDescription.setText(restaurant.getDescription());
        holder.TvLocation.setText(restaurant.getLocation());
        holder.TvPhone.setText(restaurant.getPhone());
        holder.TvResName.setText(restaurant.getRestaurantName());
        holder.TvRatings.setText(restaurant.getRatings());
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                filteredList.clear();

                if (filterPattern.isEmpty()) {
                    filteredList.addAll(resList);
                } else {
                    for (restaurant restaurant : resList) {
                        if (restaurant.getRestaurantName().toLowerCase().contains(filterPattern)
                                || restaurant.getLocation().toLowerCase().contains(filterPattern)
                                || restaurant.getPhone().toLowerCase().contains(filterPattern)
                                || restaurant.getDescription().toLowerCase().contains(filterPattern)
                                || restaurant.getRatings().toLowerCase().contains(filterPattern)) {
                            filteredList.add(restaurant);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList = (ArrayList<restaurant>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
