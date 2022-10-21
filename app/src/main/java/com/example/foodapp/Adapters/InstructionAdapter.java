package com.example.foodapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Models.InstructionsResponse;
import com.example.foodapp.R;

import java.util.List;

public class InstructionAdapter extends RecyclerView.Adapter<InstructionsViewHolder>{
    Context context;
    List<InstructionsResponse> list;

    public InstructionAdapter(Context context, List<InstructionsResponse> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public InstructionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionsViewHolder holder, int position) {
        holder.textView_instruction_name.setText(list.get(position).name);
        holder.recyclerView_instruction_steps.setHasFixedSize(true);

        holder.recyclerView_instruction_steps.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        InstructionStepAdapter instructionStepAdapter = new InstructionStepAdapter(context, list.get(position).steps);
        holder.recyclerView_instruction_steps.setAdapter(instructionStepAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InstructionsViewHolder extends RecyclerView.ViewHolder{
    TextView textView_instruction_name;
    RecyclerView recyclerView_instruction_steps;
    public InstructionsViewHolder(@NonNull View itemView) {
        super(itemView);
        recyclerView_instruction_steps = itemView.findViewById(R.id.recyclerView_instruction_steps);
        textView_instruction_name = itemView.findViewById(R.id.textView_instruction_name);
    }
}
