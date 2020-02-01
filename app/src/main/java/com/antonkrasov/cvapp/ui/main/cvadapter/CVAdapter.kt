package com.antonkrasov.cvapp.ui.main.cvadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.antonkrasov.cvapp.R
import com.antonkrasov.cvapp.data.model.CV
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.adapter_item_data_item.view.*
import kotlinx.android.synthetic.main.adapter_item_details.view.*
import kotlinx.android.synthetic.main.adapter_item_details.view.description
import kotlinx.android.synthetic.main.adapter_item_details.view.title
import kotlinx.android.synthetic.main.adapter_item_header.view.*
import kotlinx.android.synthetic.main.adapter_item_links.view.*
import kotlinx.android.synthetic.main.adapter_item_skills.view.*

class CVAdapter(layoutInflater: LayoutInflater, sectionsBuilder: SectionsBuilder) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var _items: List<BaseSection> = ArrayList()
    private val _layoutInflater = layoutInflater
    private val _sectionsBuilder = sectionsBuilder

    companion object {
        const val VIEW_TYPE_DETAILS = 0
        const val VIEW_TYPE_LINKS = 1
        const val VIEW_TYPE_SKILLS = 2
        const val VIEW_TYPE_HEADER = 3
        const val VIEW_TYPE_DATA_ITEM = 4

        @DrawableRes
        const val DEFAULT_SECTION_ICON = R.drawable.ic_default_section

        val ICONS = mapOf(
            "work" to R.drawable.ic_work,
            "education" to R.drawable.ic_education
        )
    }

    override fun getItemViewType(position: Int): Int {
        return _items[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        @LayoutRes val layoutRes = when (viewType) {
            VIEW_TYPE_DETAILS -> R.layout.adapter_item_details
            VIEW_TYPE_LINKS -> R.layout.adapter_item_links
            VIEW_TYPE_SKILLS -> R.layout.adapter_item_skills
            VIEW_TYPE_HEADER -> R.layout.adapter_item_header
            VIEW_TYPE_DATA_ITEM -> R.layout.adapter_item_data_item
            else -> throw IllegalArgumentException("Cannot find layoutRes for $viewType")
        }

        val view = _layoutInflater.inflate(layoutRes, parent, false)

        return when (viewType) {
            VIEW_TYPE_DETAILS -> DetailsViewHolder(view)
            VIEW_TYPE_LINKS -> LinksViewHolder(view)
            VIEW_TYPE_SKILLS -> SkillsViewHolder(view)
            VIEW_TYPE_HEADER -> HeaderViewHolder(view)
            VIEW_TYPE_DATA_ITEM -> DataItemViewHolder(view)
            else -> throw IllegalArgumentException("Cannot create ViewHolder for $viewType")
        }
    }

    override fun getItemCount(): Int = _items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val section = _items[position]
        when (getItemViewType(position)) {
            VIEW_TYPE_DETAILS -> onBindDetails(
                holder as DetailsViewHolder,
                section as DetailsSection
            )
            VIEW_TYPE_LINKS -> onBindLinks(
                holder as LinksViewHolder,
                section as LinksSection
            )
            VIEW_TYPE_SKILLS -> onBindSkills(
                holder as SkillsViewHolder,
                section as SkillsSection
            )
            VIEW_TYPE_HEADER -> onBindHeader(
                holder as HeaderViewHolder,
                section as HeaderSection
            )
            VIEW_TYPE_DATA_ITEM -> onBindDataItem(
                holder as DataItemViewHolder,
                section as DataItemSection
            )
        }
    }

    private fun onBindDataItem(holder: DataItemViewHolder, section: DataItemSection) {
        setValue(holder.itemView.title, section.dataItem.title)
        setValue(holder.itemView.time, section.dataItem.buildTime())
        setValue(holder.itemView.description, section.dataItem.description)
    }

    private fun onBindHeader(holder: HeaderViewHolder, section: HeaderSection) {
        holder.itemView.headerTitle.text = section.heading

        @DrawableRes var icon: Int = DEFAULT_SECTION_ICON
        val iconKey: String? = section.iconID
        if (iconKey != null) {
            val containsKey: Boolean = ICONS.containsKey(iconKey)
            if (containsKey) {
                icon = ICONS[iconKey] ?: DEFAULT_SECTION_ICON
            }
        }

        holder.itemView.headerTitle.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0)
    }

    private fun onBindSkills(holder: SkillsViewHolder, section: SkillsSection) {
        holder.itemView.chipGroup.removeAllViews()

        if (section.skills.isNullOrEmpty()) {
            return
        }

        for (skill in section.skills) {
            val chip = Chip(holder.itemView.context)
            chip.isClickable = false
            chip.isCheckable = false
            chip.text = skill
            holder.itemView.chipGroup.addView(chip)
        }
    }

    private fun onBindLinks(holder: LinksViewHolder, section: LinksSection) {
        holder.itemView.links.setText(section.linksCombined)
    }

    private fun onBindDetails(holder: DetailsViewHolder, section: DetailsSection) {
        setValue(holder.itemView.title, section.title)
        setValue(holder.itemView.subtitle, section.subtitle)
        setValue(holder.itemView.description, section.description)

        if (section.details != null) {
            setValue(holder.itemView.details, section.details.buildSingleString())
        }
    }

    private fun setValue(view: TextView, value: String?) {
        if (value.isNullOrEmpty()) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
            view.text = value
        }
    }

    fun setCV(cv: CV) {
        _items = _sectionsBuilder.build(cv)
        notifyDataSetChanged()
    }

}