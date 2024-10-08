package com.project.foodrecipe.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.project.foodrecipe.Listeners.RecipeClickListener;
import com.project.foodrecipe.Models.Recipe;
import com.project.foodrecipe.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder> {
  //objects
    Context context;
    List<Recipe> list;
    RecipeClickListener listener; //pass this to below constructor

    //constructor
    public RandomRecipeAdapter(Context context, List<Recipe> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener=listener;

        //for onclick listener


    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipe, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {

        holder.textView_title.setText((list.get(position).title));
        holder.textView_title.setSelected(true);
        holder.textView_like.setText(list.get(position).aggregateLikes+" Likes");
        holder.textView_servings.setText(list.get(position).servings+" Servings");
        holder.textView_time.setText(list.get(position).readyInMinutes+" Minutes");
        Picasso.get().load(list.get(position).image).into(holder.imageView_food);

        //calling our onclick listener
        holder.random_list_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}

class RandomRecipeViewHolder extends RecyclerView.ViewHolder{

    //initialize all id's
    CardView random_list_container;
    TextView textView_title, textView_like, textView_servings, textView_time;
    ImageView imageView_food;

    //constructor
    public RandomRecipeViewHolder(@NonNull View itemView) {

        super(itemView);
        //intializing all id's
        random_list_container = itemView.findViewById(R.id.random_list_container);
        textView_title = itemView.findViewById(R.id.textView_title);
        textView_like = itemView.findViewById(R.id.textView_like);
        textView_servings = itemView.findViewById(R.id.textView_servings);
        textView_time = itemView.findViewById(R.id.textView_time);
        imageView_food = itemView.findViewById(R.id.imageView_food);
    }
}
