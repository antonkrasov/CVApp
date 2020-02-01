package com.antonkrasov.cvapp.ui.main.cvadapter

import com.antonkrasov.cvapp.data.model.CV

class SectionsBuilder {

    fun build(cv: CV): List<BaseSection> {
        val list = ArrayList<BaseSection>()

        if (cv.title.isNullOrEmpty().not()) {
            list.add(
                DetailsSection(
                    title = cv.title,
                    subtitle = cv.subtitle,
                    description = cv.description,
                    details = cv.details
                )
            )
        }

        if (cv.links.isNullOrEmpty().not()) {
            list.add(LinksSection(cv.links))
        }

        if (cv.skills.isNullOrEmpty().not()) {
            list.add(SkillsSection(cv.skills))
        }

        if (cv.sections.isNullOrEmpty().not()) {
            for ((title, icon, items) in cv.sections!!) {
                list.add(HeaderSection(title, icon))
                if (items != null) {
                    for (dataItem in items) {
                        list.add(DataItemSection(dataItem))
                    }
                }
            }
        }

        return list
    }

}