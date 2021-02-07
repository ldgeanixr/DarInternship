package com.example.queuestack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_records.btnMainPage
import kotlinx.android.synthetic.main.fragment_records.recyclerView
import kotlinx.android.synthetic.main.record_item.view.tvDraw
import kotlinx.android.synthetic.main.record_item.view.tvLoss
import kotlinx.android.synthetic.main.record_item.view.tvPlayerName
import kotlinx.android.synthetic.main.record_item.view.tvWin

class RecordsFragment : Fragment(R.layout.fragment_records) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recordsAdapter = RecordsAdapter(PlayerRepository.records)
        recyclerView.adapter = recordsAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        btnMainPage.setOnClickListener {
            val action = RecordsFragmentDirections.actionRecordsFragmentToMainFragment()
            findNavController().navigate(action)
        }
    }
}


class RecordsAdapter(
    var records: MutableList<Player>
) : RecyclerView.Adapter<RecordsAdapter.RecordsViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecordsAdapter.RecordsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.record_item, parent, false)
        return RecordsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecordsAdapter.RecordsViewHolder, position: Int) {
        holder.itemView.tvPlayerName.text = records[position].name
        holder.itemView.tvWin.text = records[position].win.toString()
        holder.itemView.tvLoss.text = records[position].loss.toString()
        holder.itemView.tvDraw.text = records[position].draw.toString()
    }

    override fun getItemCount(): Int {
        return records.size
    }

    class RecordsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}

