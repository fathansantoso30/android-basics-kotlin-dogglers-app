/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout.GRID
import com.example.dogglers.data.DataSource

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    // Initialize the data using the List found in data/DataSource
    private val myDataset = DataSource.dogs
    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        // Declare and initialize all of the list item UI components
        val imageView : ImageView = view!!.findViewById(R.id.dog_image)
        val textViewName : TextView = view!!.findViewById(R.id.dog_name)
        val textViewAge : TextView = view!!.findViewById(R.id.dog_age)
        val textViewHobbies : TextView = view!!.findViewById(R.id.dog_hobbies)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        // Use a conditional to determine the layout type and set it accordingly.

        val adapterLayout = when (layout) {
            GRID -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.grid_list_item, parent, false)
            }
            else -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.vertical_horizontal_list_item, parent, false)
            }
        }
        return DogCardViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int = myDataset.size // return the size of the data set instead of 0

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        // Get the data at the current position
        val item = myDataset[position]
        // Set the image resource for the current dog
        holder.imageView.setImageResource(item.imageResourceId)
        // Set the text for the current dog's name, age, hobbies
        val resources = context?.resources

        holder.textViewName.text = resources?.getString(R.string.dog_name, item.name )
        holder.textViewAge.text = resources?.getString(R.string.dog_age, item.age)
        holder.textViewHobbies.text = resources?.getString(R.string.dog_hobbies, item.hobbies)
    }
}
