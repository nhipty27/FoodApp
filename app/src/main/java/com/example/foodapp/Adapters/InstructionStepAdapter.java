package com.example.foodapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Models.Step;
import com.example.foodapp.R;

import java.util.List;

public class InstructionStepAdapter extends RecyclerView.Adapter<InstructionStepViewHolder>{
    Context context;
    List<Step> list;

    public InstructionStepAdapter(Context context, List<Step> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionStepViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction_step, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionStepViewHolder holder, int position) {
        holder.textView_instruction_step_number.setText(String.valueOf(list.get(position).number));
        holder.textView_instruction_step_title.setText(list.get(position).step);

        holder.recyclerView_instruction_ingredients.setHasFixedSize(true);
        holder.recyclerView_instruction_ingredients.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        InstructionIngredientAdapter instructionIngredientAdapter = new InstructionIngredientAdapter(context, list.get(position).ingredients);
        holder.recyclerView_instruction_ingredients.setAdapter(instructionIngredientAdapter);

        holder.recyclerView_instruction_equipments.setHasFixedSize(true);
        holder.recyclerView_instruction_equipments.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        InstructionEquipmentsAdapter instructionEquipmentsAdapter = new InstructionEquipmentsAdapter(context, list.get(position).equipment);
        holder.recyclerView_instruction_equipments.setAdapter(instructionEquipmentsAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InstructionStepViewHolder extends RecyclerView.ViewHolder{
    TextView textView_instruction_step_number, textView_instruction_step_title;
    RecyclerView recyclerView_instruction_equipments, recyclerView_instruction_ingredients;
    public InstructionStepViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_instruction_step_number = itemView.findViewById(R.id.textView_instruction_step_number);
        textView_instruction_step_title = itemView.findViewById(R.id.textView_instruction_step_title);
        recyclerView_instruction_equipments = itemView.findViewById(R.id.recyclerView_instruction_equipments);
        recyclerView_instruction_ingredients = itemView.findViewById(R.id.recyclerView_instruction_ingredients);
    }
}