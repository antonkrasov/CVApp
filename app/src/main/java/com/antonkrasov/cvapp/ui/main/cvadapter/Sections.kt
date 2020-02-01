package com.antonkrasov.cvapp.ui.main.cvadapter

import com.antonkrasov.cvapp.data.model.DataItem
import com.antonkrasov.cvapp.data.model.Details

abstract class BaseSection(val type: Int)

class DetailsSection(
    val title: String?,
    val subtitle: String?,
    val description: String?,
    val details: Details?
) : BaseSection(CVAdapter.VIEW_TYPE_DETAILS)

class LinksSection(private val _links: Array<String>?) : BaseSection(CVAdapter.VIEW_TYPE_LINKS) {
    val linksCombined: String
        get() {
            if (_links == null) {
                return ""
            }

            val stringBuilder = StringBuilder()
            for (i in _links.indices) {
                stringBuilder.append(_links[i])
                if (i + 1 < _links.size) {
                    stringBuilder.append('\n')
                }
            }
            return stringBuilder.toString()
        }
}

class SkillsSection(val skills: Array<String>?) : BaseSection(CVAdapter.VIEW_TYPE_SKILLS)

class HeaderSection(val heading: String?, val iconID: String?) :
    BaseSection(CVAdapter.VIEW_TYPE_HEADER)

class DataItemSection(val dataItem: DataItem) : BaseSection(CVAdapter.VIEW_TYPE_DATA_ITEM)